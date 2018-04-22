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

<title>Blogs</title>

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
						href="${contextPath}/user/myaccount?userId=${user.personID}">My
							Account</a></li>
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

		<div class="row row-offcanvas row-offcanvas-right">

			<div class="col-xs-12 col-sm-9">
				<p class="pull-right visible-xs">
					<button type="button" class="btn btn-primary btn-xs"
						data-toggle="offcanvas">Toggle nav</button>
				</p>
				<div class="jumbotron">
					<h1>Hello, world!</h1>
					<p>This is an example to show the potential of an offcanvas
						layout pattern in Bootstrap. Try some responsive-range viewport
						sizes to see it in action.</p>
				</div>
				<form action="${contextPath}/blog/search.htm" method="post">
					Keyword:<input type="text" name='keyword' />
					<input type="radio" name="option" value="title">Title
					<input type="radio" name="option" value="content">Content
				    <input type="radio" name="option" value="author">Author
					<button type="submit" class="btn btn-default">Search</button>	
				</form>

				<div class="row">
					<div class="col-xs-6 col-lg-4">
						<c:forEach var="blog" items="${blogs}">
							<div class="blog-post">
								<h2 class="blog-post-title">${blog.title}</h2>
								<p class="blog-post-meta">${blog.date}
									By<a href="#">${blog.user.username}</a>
								</p>
								<p>${blog.content}</p>
								<p>
									<a class="btn btn-default"
										href="${contextPath}/blog/detail.htm?blogId=${blog.id}&&userId=${user.personID}"
										role="button">View details &raquo;</a>
								</p>

							</div>
							<!-- /.blog-post -->
						</c:forEach>
					</div>


				</div>
			</div>
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
				<div class="list-group">
					<br> <br> <br>
					<ur>
					<li><a
						href="${contextPath}/blog/list.htm?userId=${user.personID}">All
							Blogs</a></li>
					<c:forEach var="cat" items="${cats}">
						<li><a
							href="${contextPath}/blog/listByCategory.htm?catId=${cat.categoryId}">${cat.title}</a></li>
					</c:forEach> </ur>
				</div>
			</div>
		</div>

	</div>
	<!-- /.container -->
</body>
</html>
