package com.chirko.course.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chirko.course.entity.Message;
import com.chirko.course.entity.MessageRepository;
import com.chirko.course.entity.User;
import com.chirko.course.entity.UserRepository;

@Controller
public class AppController {
	@Autowired
	private UserRepository urp;
	@Autowired
	private MessageRepository mrp;
	
	@RequestMapping("/")
	 public String index() {	 
	        return "index";
	    }
	
	@RequestMapping("/signIn")
	 public String signIn(HttpServletRequest request ) {
		if(request.getParameterMap().containsKey("error")) {
			String err = request.getParameter("error");
			request.setAttribute("error", err);
		}
	        return "signIn";
	    }
	
	@RequestMapping("/signUp")
	 public String signUp(HttpServletRequest request) {
		if(request.getParameterMap().containsKey("error")) {
			String err = request.getParameter("error");
			request.setAttribute("error", err);
		}
		return "signUp";
	        
	    }
	
	
	@Transactional
	@RequestMapping("/create_user")
		public ModelAndView create_user(HttpServletRequest request,
				@RequestParam("name") String name,@RequestParam("pass") String pass,
				@RequestParam("email") String email) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(urp.existsByName(name)) {
			return new ModelAndView("redirect:/signUp","error", "Username taken");
		}else if(urp.existsByEmail(email)){
			return new ModelAndView("redirect:/signUp","error", "User with this email already registered");
		}else if(!email.contains("@")) {
			return new ModelAndView("redirect:/signUp","error", "Not a valid email: "+email+", try again with valid one");
		}
		session.setAttribute("username", name);
		User user = new User();
		user.setEmail(email);
		user.setPass(pass);
		user.setName(name);
		urp.save(user);
		
		return new ModelAndView("redirect:/group/main");
		
	}
	
	@Transactional
	@RequestMapping("/sign_user")
	public ModelAndView signUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		User user = urp.findByNameAndPass(name, pass);
		HttpSession session = request.getSession();
		if(user != null) {
			session.setAttribute("username", name);
			return  new ModelAndView("redirect:/group/main");
		
		}
		return new ModelAndView("redirect:/signIn","error", "No such user");
	
	
	}
	@Transactional
	@RequestMapping("/sign_out")
	public String signOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			HttpSession session = request.getSession();
			session.removeAttribute("username");
		return "redirect:/";
	
	
	}
	

	@Transactional
	@RequestMapping(value = "/group/{groupname}",method=RequestMethod.POST)
		public String sendMessage(HttpServletRequest request) throws ServletException, IOException {
		String message = request.getParameter("messageField");
		message = message.replaceAll("\\<.*?>", "");
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		String groupname=(String)session.getAttribute("currentGN");
		Message msg = new Message();
		
		msg.setGroupname(groupname);
		msg.setMessage(message);
		msg.setUsername(username);
		msg.setPublishedOn(timestamp);
		
		mrp.save(msg);
		
		return "redirect:/group/"+groupname;
		
	}
	
	@RequestMapping(value = "/group/create",method=RequestMethod.POST)
		public String createGroup(HttpServletRequest request) {
		String gname;
		if(request.getParameter("gname").equals("create")) {
			HttpSession session = request.getSession();
			gname = (String)session.getAttribute("currentGN");
		}else {
			gname = request.getParameter("gname");
		}

			return "redirect:/group/"+gname;
	}
	
	@Transactional
	@RequestMapping(value = "/group/{groupname}",method=RequestMethod.GET)
		public ModelAndView success(@PathVariable("groupname") String groupname,HttpServletRequest request) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("username")!=null) {
			ModelAndView mnv = new ModelAndView("success");
			
			session.setAttribute("currentGN",groupname);
			ArrayList<Message> messageList = mrp.findAllByGroupname(groupname);
			mnv.addObject("messageList", messageList);
			
			ArrayList<String> groupList = mrp.findAllUniqueGroupnameRow();
			mnv.addObject("groupList", groupList);
			return mnv;
		}
		ModelAndView mnv = new ModelAndView("redirect:/");
		return mnv;
		
	}
	
	@Transactional
	@RequestMapping("/delete/{msgId}")
	public String deleteMessge(@PathVariable("msgId") long id,HttpServletRequest request) {
		mrp.deleteById(id);
		HttpSession session = request.getSession();
		String groupname=(String)session.getAttribute("currentGN");
		return "redirect:/group/"+groupname;
	}
	
	
}
