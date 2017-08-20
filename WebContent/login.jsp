<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>32Bit Cv Management</title>
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
	
	<link rel="stylesheet prefetch" href="https://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900">
	<link rel="stylesheet prefetch" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="css/login.css">
</head>
<body>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

	<%
	 response.setHeader("Cache-Control","no-cache");
	  response.setHeader("Cache-Control","no-store");
	  response.setHeader("Pragma","no-cache");
	  response.setDateHeader ("Expires", 0);
	if(request.getSession().getValue("isLoggedIn")=="true") {
	%>
		<c:redirect url="index.jsp"/>	<%
	}%>

	<div class="pen-title">
	
		<img src="images/32bit2.png">
	</div>
	
	<div class="container">
	  <div class="card"></div>
	  <div class="card">
	    <h1 class="title">Login</h1>
	    <form action="Login" method="post">
	      <div class="input-container">
	        <input type="text" name="LoginUsername" required="required"/>
	        <label>Username</label>
	        <div class="bar"></div>
	      </div>
	   
	      <div class="input-container">
	        <input type="text" name="LoginPassword" required="required"/>
	        <label>Password</label>
	        <div class="bar"></div>
	      </div>
	      <div class="button-container" style="padding-top: 10px;">
	        <button type="submit" ><span>Go</span></button>
	      </div>
	
	    </form>
	  </div>
	</div>

	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="js/login.js"></script>


</body>
</html>