<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Account</title>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
 <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <c:set var="contextPath" value="${pageContext.request.contextPath}" />
</head>
<body>

<nav class="navbar navbar-fixed-top navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">BLOG</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="${contextPath}/">Home</a></li>
            <li><a href="${contextPath}/user/login.htm">Log In</a></li>
            <li><a href="${contextPath}/user/create.htm">Register</a></li>
          </ul>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
    </nav><!-- /.navbar -->
 <br/>
 <br/>
 <br/>
 <br/>

<div class="container">
	<form:form  class="form-signin" action="${contextPath}/user/update.htm" commandName="user" method="post">
	<h2 class="form-signin-heading">Please Register a New Account</h2>
		<div class="form-group">
			<label for="exampleInputEmail1">User ID</label>
			 <form:input type="text" path="personID" class="form-control" name="personID"  />
			 <font color="red"><form:errors path="personID" /></font>	
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">First Name</label>
			 <form:input type="text" path="firstName" class="form-control" name="firstName"  />
			 <font color="red"><form:errors path="firstName" /></font>	
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Last Name</label>
			 <form:input type="text" path="lastName" class="form-control" name="lastName"/>
			 <font color="red"><form:errors path="lastName" /></font>	
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Last Name</label>
			 <form:input type="text" path="username" class="form-control" name="username"/>
			 <font color="red"><form:errors path="username" /></font>	
		</div>
		<div class="form-group">
		    <label for="exampleInputPassword1">Password</label>
		    <form:input type="password" path="password" class="form-control" name="password"  placeholder="Password"/>
		    <font><form:errors path="password" /></font>
	  </div>
	  <div class="form-group">
		    <label for="exampleInputEmail1">Email address</label>
		    <form:input type="email" path="email.emailAddress" class="form-control" name="email.emailAddress"  placeholder="Email"/>
		    <font color="red"><form:errors path="email.emailAddress" /></font>
	  </div>
	  <button type="submit" class="btn btn-default">Update && Save</button>	
	  </form:form>
</div>

</body>
</html>