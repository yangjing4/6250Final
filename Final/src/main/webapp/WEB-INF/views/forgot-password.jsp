<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forget Password</title>

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

	<font color="red">${captchamsg}</font>
	<form action="${contextPath}/user/forgotpassword.htm" method="POST">

		<div class="form-group">
			<label for="exampleInputEmail1">Enter your email</label> <input
				type="email" class="form-control" name="useremail"
				placeholder="Email" required="required" />
		</div>
		<div class="form-group">
	  <label for="captchaCode" class="prompt">Retype the characters from the picture:</label> 
				<%
					// Adding BotDetect Captcha to the page
					Captcha captcha = Captcha.load(request, "CaptchaObject");
					captcha.setUserInputID("captchaCode");

					String captchaHtml = captcha.getHtml();
					out.write(captchaHtml);
				%> 
	 <input id="captchaCode" type="text" name="captchaCode" required="required"/>
     </div>
	 <input type="submit" class="btn btn-default" value="Submit" />
	</form>	
	

</body>
</html>