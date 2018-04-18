<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List</title>

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

	<div class="container-fluid">
		<div class="row">
			<div class="main">
				<h1 class="page-header">Dashboard</h1>

				<div class="row placeholders">
					<div class="col-xs-6 placeholder">
						<img
							src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
							width="200" height="200" class="img-responsive"
							alt="Generic placeholder thumbnail">
						<h4>Manager</h4>
						
					</div>
					<div class="col-xs-6  placeholder">
						<img
							src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
							width="200" height="200" class="img-responsive"
							alt="Generic placeholder thumbnail">
						<h4>Visitor</h4>
						
					</div>
				</div>

				<h2 class="sub-header">Section title</h2>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<td><b>User ID</b></td>
								<td><b>User Name</b></td>
								<td><b>First Name</b></td>
								<td><b>Last Name</b></td>
								<td><b>User Email</b></td>
								<td><b>User Type</b></td>
								<td><b>Functions</b></td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="user" items="${users}">
								<tr>
									<td>${user.personID}</td>
									<td>${user.username}</td>
									<td>${user.firstName}</td>
									<td>${user.lastName}</td>
									<td>${user.email.emailAddress}</td>
									<td>${user.type}</td>
									<td><b>[<a type="button"
											href="${contextPath}/user/update.htm?userId=${user.personID}&&username=${user.username}&&firstName=${user.firstName}&&lastName=${user.lastName}&&email=${user.email.emailAddress}">Update</a>]
											[<a type="button"
											href="${contextPath}/user/delete.htm?userId=${user.personID}">Delete</a>]
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