<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Blogs</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}/user/">Home</a><br/>

	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<td><b>Blog </b></td>
			<td><b>Blog TITLE</b></td>
			<td><b>CONTENT</b></td>
			<td><b>POSTED BY</b></td>
			<!-- <td><b>DATE</b></td> -->
			<td><b>CATEGORIES</b></td>
			<td><b>Date</b></td>
			<td><b>Functions</b></td>
		</tr>
		<c:forEach var="blog" items="${blogs}">
			<tr>
				<td>${blog.id}</td>
				<td>${blog.title}</td>
				<td>${blog.content}</td>
				<td>${blog.user.username}</td>
				<%-- <td>${blog.date}</td> --%>
				<td><c:forEach var="categ" items="${blog.categories}">
                    	${categ} , 
                    </c:forEach></td>
                <td>${blog.date}</td>
                <td><b>[<a type="button" href="${contextPath}/blog/update.htm?id=${blog.id}&&title=${blog.title}&&content=${blog.content}">Update</a>]
					   <%-- [<a type="button" href="${contextPath}/blog/update.htm?id=${blog.id}&&title=${blog.title}">Update</a>] --%>
					   [<a type="button" href="${contextPath}/blog/delete.htm?title=${blog.title}">Delete</a>]</b></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>