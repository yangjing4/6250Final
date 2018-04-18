<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	var count = 1;

	//用来判断是删除 还是增加按钮 以便count值进行计算
	function checkCount(boolOK, coun) {
		if (boolOK == true) {
			return count++;
		} else {
			count--;
		}
	}
	//添加一个input标签 同时也对它的ID和Name进行赋值。
	function AddInput() {
		countAA = checkCount(true, count);
		var question = document.getElementById("question");
		//创建input
		var input = document.createElement("input");
		input.type = "text";
		input.id = "reply";
		input.name = "reply";
		question.appendChild(input); //向元素增加子节点 最为最后一个子节点

		var input = document.createElement("input");
		input.type = "button";
		input.id = "questions[" + count + "]";
		input.name = "questions[" + count + "].name";
		input.value = "Submit";

		question.appendChild(input);

		//创建一个空格
		var br = document.createElement("br");
		question.appendChild(br);
	}
</script>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="../../favicon.ico">

<title>Detail</title>

<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
</head>
<body>
<body onmousemove="initEvent()">
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
	<!-- /.container --> </nav>
	<!-- /.navbar -->
	<br />
	<br />
	<br />
	<br />
	
		<div class="container">
			<div class="blog-header">
				<h1 class="blog-title">${blog.title}</h1>
				<p class="blog-post-meta">${blog.date}
					By<a href="#">${blog.user.username}</a>
				</p>
				<p class="lead blog-description">${blog.content}</p>
				<p>
					Categories:
					<c:forEach var="categ" items="${blog.categories}">
                    	${categ} , 
        </c:forEach>
				</p>

			</div>
			<p>Comment</p>
			<c:forEach var="com" items="${comments}">
				<div class="panel panel-default">
					<div class="panel-footer">${com.date}Post By
						${com.user.username}</div>
					<div class="panel-body">
						${com.content}
						<br/>
						<p>Reply</p>
						<c:forEach var="reply" items="${com.replys}">
	                    	<div class="panel panel-default">
								<div class="panel-footer">${reply.date} PostBy
									${reply.user.username}</div>
								<div class="panel-body">${reply.content}</div>
							</div> 
                    </c:forEach>
					<%-- <c:forEach var="reply" items="${replys}">
						<div class="panel panel-default">
							<div class="panel-footer">${reply.date} PostBy
								${reply.user.username}</div>
							<div class="panel-body">${reply.content}</div>
						</div>
					</c:forEach> --%>
					<form:form
							action="${contextPath}/reply/add?commentId=${com.commentID}&&userId=${user.personID}"
							method="post" commandName="reply">
							<form:textarea type="text" path="content" class="form-control"
								rows="3" required="required" />
							<input class="btn btn-default" type="submit" value="Submit">
						</form:form>
					</div>
				</div>

			</c:forEach>
	<form:form action="${contextPath}/comment/add?blogId=${blog.id}&&userId=${user.personID}"
		method="post" commandName="comment">
			<form:textarea type="text" path="content" class="form-control"
				rows="3" required="required" />
			<input class="btn btn-default" type="submit" value="Submit">
	</form:form>
	</div>


</body>
</html>