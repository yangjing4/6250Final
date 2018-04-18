<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/user/">Home</a><br/>

	<h2>Updating a New Blog</h2>


	<form:form action="${contextPath}/blog/update.htm" method="post"
		commandName="blog">

		<table>
			<tr>
				<td>Posted By</td>
				<td>${sessionScope.user.username}</td>
				<td><form:hidden path="postedBy"
						value="${sessionScope.user.personID}" /></td>
			</tr>
			
			<tr>
				<td>Blog ID</td>
				<td>${blog.id}</td>
				<td><form:hidden path="id"
						value="${blog.id}" /></td>
			</tr>

			<tr>
				<td>Category:</td>
				<td><form:input path="categories" value="${blog.categories}"
						 required="required"/></td>
			</tr>

			<tr>
				<td>Blog Title:</td>
				<td><form:input type="text" path="title" size="30" required="required" value="${blog.title}"/></td>
			</tr>

			<tr>
				<td>Content:</td>
				<td><form:input type="text" path="content" size="30" required="required" value="${blog.content}"/></td>
			</tr>
			
			<%-- <tr>
				<td>Date:</td>
				<td><form:input type="text" path="date" size="30" required="required" value="${blog.date}"/></td>
			</tr> --%>

			<tr>
				<td colspan="2"><input type="submit" value="Post Blog" /></td>
			</tr>
		</table>

	</form:form>

</body>
</html>