<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category Created Successful</title>

     <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	 <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	 <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	 
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
 <br/>


	    <a href="${contextPath}/user/visitor">Home</a><br/>
    
        <h2>${message}</h2>
        
    </body>
</html>