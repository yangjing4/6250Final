<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Blog Form</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/user/">Home</a><br/>

	<h2>Posting a New Blog</h2>


	<form:form action="${contextPath}/blog/add" method="post"
		commandName="blog">

		<table>
			<tr>
				<td>Posted By</td>
				<td>${sessionScope.user.username}</td>
				<td><form:hidden path="postedBy"
						value="${sessionScope.user.personID}" /></td>
			</tr>

			<tr>
				<td>Category:</td>
				<td><form:select path="categories" items="${categories}"
						multiple="true" required="required" /></td>
			</tr>

			<tr>
				<td>Advert Title:</td>
				<td><form:input type="text" path="title" size="30" required="required"/></td>
			</tr>

			<tr>
				<td>Message:</td>
				<td><form:input type="text" path="content" size="30" required="required"/></td>
			</tr>
			
			<%-- <tr>
				<td>Date:</td>
				<td><form:input type="text" path="date" size="30" required="required"/></td>
			</tr> --%>

			<tr>
				<td colspan="2"><input type="submit" value="Post Blog" /></td>
			</tr>
		</table>

	</form:form>

</body>
</html>