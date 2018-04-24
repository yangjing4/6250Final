<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html lang="en">
  <head>
  
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Index</title>

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
 
 <div class="jumbotron">
      <div class="container">
        <h1>Hello, world!</h1>
        <p>This is where you can share your idea and emotions, and where you can communicate with others who can understand you!</p>
        
      </div>
    </div>
 

     <div class="container">
 
      <div class="row">
      
      <%-- <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <c:forEach var="cat" items="${map.cats}">
              <li><a href="#">${cat.title}</a></li>
             </c:forEach>
          </ul>
       </div> --%>

        <div class="col-md-4">
		<c:forEach var="blog" items="${blogs}">
          <div class="blog-post">
            <h2 class="blog-post-title">${blog.title}</h2>
            <p class="blog-post-meta">${blog.date} By <a href="#">${blog.user.username}</a></p>
            <p>${blog.content}</p>
            <p><a class="btn btn-default" href="${contextPath}/user/login.htm" role="button">View details &raquo;</a></p>
           
          </div><!-- /.blog-post -->
        </c:forEach>
        </div><!-- /.blog-main -->

      </div><!-- /.row -->
    </div><!-- /.container -->
  </body>
</html>
