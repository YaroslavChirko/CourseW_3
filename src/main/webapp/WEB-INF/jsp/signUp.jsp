<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign Up</title>
<style>
    <%@include file="/WEB-INF/css/style.css"%>
</style>
</head>
<body>

<div class="sign">
<%
if(request.getParameterMap().containsKey("error")){
	out.print("<h2>"+(String)request.getParameter("error")+"</h2>");
}else{
	out.print("<h2>Sign Up</h2>");
}
%>

<form action="create_user" autocomplete="off" id="sign_fm">
name: <input type="text" name="name" required="required"><br><br>
pass: <input type="password" name="pass" required="required"><br><br>
email: <input type="text" name="email" required="required"><br><br>
<input type="submit" value="sign up"><br>
</form>
</div>
</body>
</html>