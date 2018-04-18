<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<form:form action="${contextPath}/user/update.htm" commandName="user" method="post">
		<table>
		<tr>
				<td>User Id:</td>
				<td><form:input path="personID" size="30" required="required" value="${user.personID}"/>
					<font color="red"><form:errors path="personID" /></font></td>
			</tr>
		<tr>
				<td>First Name:</td>
				<td><form:input path="firstName" size="30" required="required" value="${user.firstName}"/>
					<font color="red"><form:errors path="firstName" /></font></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName" size="30" required="required" value="${user.lastName}"/>
					<font color="red"><form:errors path="lastName" /></font></td>
			</tr>


			<tr>
				<td>User Name:</td>
				<td><form:input path="username" size="30" required="required" value="${user.username}"/>
					<font color="red"><form:errors path="username" /></font></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><form:password path="password" size="30"
						required="required" value="${user.password}"/> <font color="red" ><form:errors
							path="password" /></font></td>
			</tr>

			<tr>
				<td>Email Id:</td>
				<td><form:input path="email.emailAddress"  size="30"
						type="email" required="required" value="${user.email.emailAddress}"/> <font color="red"><form:errors
							path="email.emailAddress" /></font></td>
			</tr>

			 <tr>
			    <td>User Type:</td>
			    <td><form:input path="type"  size="30"
						type="text" required="required" value="${user.type}"/> <font color="red"><form:errors
							path="email.emailAddress" /></font></td>
			</tr>
		
		    <td colspan="2"><input type="submit" value="Update" /></td>
		</tr>
				
		</table>
	</form:form>

</body>
</html>