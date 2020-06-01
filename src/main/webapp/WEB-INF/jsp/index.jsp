<html>
<head>
<title>Welcome page</title>
<style>
    <%@include file="/WEB-INF/css/style.css"%>
</style>
</head>
<body>
<div class="title">
<h2>Welcome To the chat!<br>
Please sign in if you already have an account<br>
or sign up if you are new to this page</h2>
</div>
<div class="submitForms">
<!--  <div class="button" onclick="window.location='signUp'">sign up</div>-->
<div class="button"><form action="signUp" autocomplete="off">
<input type="submit" value="sign up"><br>
</form></div>
<!--  
<div class="button" onclick="window.location='signIn'">sign in</div>
-->
<div class="button"><form action="signIn" >
<input type="submit" value="sign in"><br>
</form></div>
</div>



</body>
</html>