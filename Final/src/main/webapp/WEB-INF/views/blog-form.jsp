<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Blog ADD</title>

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
					<li class="active"><a
						href="${contextPath}/blog/list?userId=${user.personID}">Home</a></li>
					<li><a
						href="${contextPath}/blog/mylist?userId=${user.personID}">My
							Blog</a></li>
					<li><a
						href="${contextPath}/user/update.htm?userId=${user.personID}">My
							Account</a></li>
					<li><a href="${contextPath}/user/login.htm">Log out</a></li>
				</ul>
			</div>
			<!-- /.nav-collapse -->
		</div>
		<!-- /.container -->
	</nav>
	<!-- /.navbar -->
	<br />
	<br />
	<br />
	<br />
	<br />

	<h2>Posting a New Blog</h2>

	<form:form action="${contextPath}/blog/add?userId=${user.personID}" method="post"
		commandName="blog">
		<div class="form-group">
			<label for="exampleInputEmail1">${sessionScope.user.personID}</label>
			<form:hidden path="postedBy" value="${sessionScope.user.personID}" />
		</div>

		<div class="form-group">
			<label for="exampleInputEmail1">Category</label>
			<form:select path="categories" items="${categories}" multiple="true"
				required="required" />
		</div>

		<div class="form-group">
			<label for="exampleInputEmail1">Blog Title</label>
			<form:input type="text" path="title" class="form-control"
				value="${blog.title}" required="required" />
		</div>

		<div class="form-group">
			<label for="exampleInputEmail1">Blog Content</label>
			<form:textarea type="text" path="content" class="form-control"
				value="${blog.content}" required="required" row="10" />
		</div>

		<input type="submit" class="btn btn-default" value="Post Blog" />

	</form:form>


</body>
</html>