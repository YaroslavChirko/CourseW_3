<%@page import="com.chirko.course.entity.Message"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<style>
    <%@include file="/WEB-INF/css/style.css"%>
</style>



<title>Chat</title>
</head>
<body>
<div class="title"><h2>Welcome to the chat <%
String currentGN = (String)session.getAttribute("currentGN");
out.print(currentGN); %>,user
 <%  out.print((String)session.getAttribute("username"));%></h2>
 <form action="/sign_out" >
    <p><input type="submit" value="sign out"></p>
  </form>
 </div>
 
 <div class="groups">
<h2>Available groups</h2>
<%ArrayList<String> groupList = (ArrayList<String>)request.getAttribute("groupList");
for(String grp: groupList){
	out.println("<a href='/group/"+grp+"'>"+grp+"</a><br>");
	}%>
	<form action=<%out.print("/group/create"); %> method="post" autocomplete="off">
	<p><input type="text" name="gname" required="required"></p>
    <p><input type="submit" value="Create group" ></p>
  </form>
	</div>
 
<div class="msgField" id="msgField">
<%
ArrayList<Message>messageList = (ArrayList<Message>)request.getAttribute("messageList");
for(Message msg: messageList){

		out.println("<p style=\"color:blue\">"+msg.getUsername()+":</p> "+msg.getMessage()+"<p style=\"color:gray\"> at "+msg.getPublishedOn()+"</p>");
	if(((String)session.getAttribute("username")).equals("admin")){
		out.println("<a href='/delete/"+msg.getId()+"'>Delete</a>");
	}
}%>
</div>

<footer>
<form action=<%out.print("/group/"+currentGN); %> method="post">
    <p><b>Write messages here:</b></p>
    <p><textarea rows="10" cols="45" maxlength ="7000" name="messageField" required="required"></textarea></p>
    <p><input type="submit" value="Send"></p>
  </form>
  <form action=<%out.print("/group/"+currentGN); %>>
    <p><input type="submit" value="Refresh"></p>
  </form>
  </footer>
</body>
<script>
var scrollingElement = (document.scrollingElement || document.body);
scrollingElement.scrollTop = scrollingElement.scrollHeight;
var element = document.getElementById("msgField");
element.scrollTop = element.scrollHeight - element.clientHeight;
</script>
</html>