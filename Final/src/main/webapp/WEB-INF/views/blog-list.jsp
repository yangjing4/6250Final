<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Blog List</title>

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
				<a class="navbar-brand" href="#">BLOG</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="${contextPath}/user/manager">Home</a></li>
					<li><a href="${contextPath}/user/list.htm">View All Users</a></li>
					<li><a href="${contextPath}/blog/listAll.htm">View All
							Blogs</a></li>
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
	<div class="container">
		<div class="row">
			<div class="col-sm-3 sidebar">
				<ul class="nav nav-sidebar">
					<c:forEach var="cat" items="${cats}">
						<li><a
							href="${contextPath}/blog/listAllByCategory.htm?catId=${cat.categoryId}">${cat.title}</a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="col-sm-offset-3 col-md-offset main">
				<h1 class="page-header">Dashboard</h1>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<td><b>Blog </b></td>
								<td><b>Blog TITLE</b></td>
								<td><b>CONTENT</b></td>
								<td><b>POSTED BY</b></td>
								<td><b>CATEGORIES</b></td>
								<td><b>Date</b></td>
								<td><b>Functions</b></td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="blog" items="${blogs}">
								<tr>
									<td>${blog.id}</td>
									<td>${blog.title}</td>
									<td>${blog.content}</td>
									<td>${blog.user.username}</td>
									<td><c:forEach var="categ" items="${blog.categories}">
                    	${categ} , 
                    </c:forEach></td>
									<td>${blog.date}</td>
									<td><b> <a type="button"
											href="${contextPath}/blog/managerdelete.htm?id=${blog.id}">Delete</a>
									</b></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>