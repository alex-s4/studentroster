<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dorms</title>
</head>
<body>
	<h1>Dorms</h1>
	<a href="../dorms/new">Add a new dorm</a>
	<a href="../students/new">Add a new student</a>
	
	<table>
		<tr>
			<th>Dorm</th>
			<th>Action</th>
		</tr>
		
		<c:forEach var="dorm" items="${allDorms}">
			<tr>
				<td>${dorm.getName()}</td>
				<td><a href="../dorms/${dorm.getId()}">See Students</a></td>
			</tr>
		</c:forEach>
		
	
	</table>
</body>
</html>