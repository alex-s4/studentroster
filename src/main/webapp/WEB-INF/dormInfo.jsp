<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${dormShown.getName()}</title>
</head>
<body>
	<h1>${dormShown.getName()} Students</h1>
	<a href="../dorms">Dashboard</a>
	<form:form method="POST" action="/dorms/${dormShown.getId()}" modelAttribute="dormShown"> 
		<input type="hidden" name="_method" value="PUT">
		<form:input type="hidden" path="name" value="${ dormShown.getName()}"/>
		<table>
			<tr>
				<td>
					<form:label path="students">Students:</form:label><br>
					<form:select path="students" multiple="false">
                      	<c:forEach var="student" items="${allStudents}">
                      		<form:option value="${student.getId()}">${student.getName()} (${student.getDorm().getName()})</form:option>
                      	</c:forEach>
                    </form:select>
				</td>
				<td>
					<input type="submit" value="Add">
				</td>
			</tr>
		</table>
	</form:form>
	
	<table>
		<tr>
			<th>Student</th>
			<th>Action</th>
		</tr>
		
		<c:forEach var="studentInDorm" items="${studentsInDorm}">
			<tr>
				<td>${studentInDorm.getName()}</td>
				<td><a href="/dorms/${dormShown.getId()}/remove/${studentInDorm.getId()}">Remove</a></td>
			</tr>
		</c:forEach>
			
		
	</table>
</body>
</html>