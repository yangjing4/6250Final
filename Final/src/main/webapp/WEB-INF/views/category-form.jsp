<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script>
	function ajaxEvent() {

		var xmlHttp;
		try // Firefox, Opera 8.0+, Safari
		{
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try // Internet Explorer
			{
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					alert("Your browser does not support AJAX!");
					return false;
				}
			}
		}

		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				document.getElementById("categorydiv").innerHTML = xmlHttp.responseText;
			}
		}

		var queryString = document.getElementById("queryString").value;

		xmlHttp.open("POST", "../ajax/ajaxservice.htm?category=" + queryString,
				true);
		xmlHttp.send();
	}
</script>

<title>Category Create</title>

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
		<!-- /.container -->
	</nav>
	<!-- /.navbar -->
	<br />



	<a href="${contextPath}/user/visitor">Home</a>
	<br />

	<h2>Add a New Category</h2>


	<form:form action="${contextPath}/category/add?userId=${user.personID}" method="post"
		commandName="category">

		<div class="form-group">
			<label for="exampleInputEmail1">Category Title:</label>
			<form:input path="title" size="30" required="required"
				id="queryString" onkeyup="ajaxEvent()" />
			<font color="red"><form:errors path="title" /></font> <input
				type="submit" class="btn btn-default" value="Create Category" />

		</div>
		<div id="categorydiv"></div>
	</form:form>

</body>
</html>