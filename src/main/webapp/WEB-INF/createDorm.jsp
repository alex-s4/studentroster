<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Dorm</title>
</head>
<body>
	<h1>New Dorm</h1>
	<a href="../dorms">Dashboard</a>
	
	<form:form method="POST" action="/createDorm" modelAttribute="newDorm">
	
		<table>
			<tr>
				<td><form:label path="name">Name:</form:label></td>
				<td><form:input path="name"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit"></td>
			</tr>
		
		</table>
	
	
	</form:form>
</body>
</html>