<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Blogs update</title>

<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
</head>
<body>
	<nav class="navbar navbar-fixed-top navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">${user.username}'s BLOG</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="${contextPath}/blog/list?userId=${user.personID}">Home</a></li>
				<li><a
					href="${contextPath}/blog/mylist?userId=${user.personID}">My
						Blog</a></li>
				<li><a
					href="${contextPath}/user/update?userId=${user.personID}">My
						Account</a></li>
				<li><a href="${contextPath}/user/login.htm">Log out</a></li>
			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>
	<!-- /.container --> </nav>
	<!-- /.navbar -->
	<br />
	<br />
	<br />
	<br />

	<br />

	<h2>Updating a New Blog</h2>


	<form:form action="${contextPath}/blog/update" method="post"
		commandName="blog">
		<div class="form-group">
			<label for="exampleInputEmail1">Posted By</label>
			<form:input type="text" path="postedBy" class="form-control"
				value="${sessionScope.user.personID}" name="postedBy" readonly="true" />
		</div>

		<div class="form-group">
			<label for="exampleInputEmail1">Blog ID</label>
			<form:input type="text" path="id" class="form-control"
				value="${blog.id}" name="blogId" readonly="true" />
		</div>

		<div class="form-group">
			<label for="exampleInputEmail1">Category</label>
			<form:input type="text" path="categories" class="form-control"
				value="${blog.categories}" required="required" />
		</div>

		<div class="form-group">
			<label for="exampleInputEmail1">Blog Title</label>
			<form:input type="text" path="title" class="form-control"
				value="${blog.title}" required="required" />
		</div>

		<div class="form-group">
			<label for="exampleInputEmail1">Blog Content</label>
			<form:textarea type="text" path="content" class="form-control"
				rows='5' value="${blog.content}" required="required" />
		</div>

		<input type="submit" class="btn btn-default" value="Update Blog" />

	</form:form>

</body>
</html>