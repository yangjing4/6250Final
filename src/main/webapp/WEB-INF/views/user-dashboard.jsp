<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>

<html>
<head>
    <title>User DashBoard</title>

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
          <a class="navbar-brand" href="#">${user.username}'s BLOG</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="${contextPath}/blog/list?userId=${user.personID}">Home</a></li>
            <li><a href="${contextPath}/blog/mylist?userId=${user.personID}">My Blog</a></li>
            <li><a href="${contextPath}/user/update.htm?userId=${user.personID}">My Account</a></li>
          </ul>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
    </nav><!-- /.navbar -->
 <br/>
 <br/>
 <br/>
 <br/>
	
    <div class="container">

      <div class="row">
      
     
      <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li><a href="${contextPath}/category/add.htm" >Add Category</a></li>
            <li><a href="${contextPath}/blog/add.htm" >Add Blog</a></li>
            <li><a href="${contextPath}/blog/list?userId=${user.personID}" >View All Blogs</a></li>
          </ul>
          
        </div>
        

        <div class="col-sm-8 blog-main ">
		<c:forEach var="blog" items="${blogs}">
          <div class="blog-post">
            <h2 class="blog-post-title ">${blog.title}</h2>
            <p class="blog-post-meta ">${blog.date} By <a href="#">${blog.user.username}</a></p>
            <p >${blog.content}</p>
            <p><a class="btn btn-default" href="${contextPath}/blog/detail.htm?blogId=${blog.id}&&userId=${user.personID}" role="button">View details &raquo;</a></p>
           
          </div><!-- /.blog-post -->
        </c:forEach>

          <nav>
            <ul class="pager">
              <li><a href="#">Previous</a></li>
              <li><a href="#">Next</a></li>
            </ul>
          </nav>

        </div><!-- /.blog-main -->
        </div>
        </div>

</body>
</html>