<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Management Screen</title>
</head>
<body>
	<div align="center">
		<h1>Employee List</h1>
		
		<table border="1">

			<th>Name</th>
			<th>Email</th>
			<th>Address</th>
			<th>Telephone</th>
			<th>Action</th>

			<c:forEach var="employee" items="${listEmployee}"  varStatus="status">
				<tr>
					<td>${employee.name}</td>
					<td>${employee.email}</td>
					<td>${employee.address}</td>
					<td><c:out value="${employee.telephone}" default="guest" /></td>
					<td><c:out value="${employee.actor.name}" default="guest" /></td>
					<td><a href="editEmployee?id=${employee.id}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="deleteEmployee?id=${employee.id}">Delete</a></td>

				</tr>
 				<tr>
				   <td> Фильмы</td>
				</tr>
<%-- 				<c:forEach var="empl" items="${employee.getFilmlist()}">
				<tr>
					<td>${empl.name}</td>
				</tr>
				</c:forEach> --%>
				
				<c:forEach var="film" items="${employee.filmlist}">
				<tr>
					<td>${film.id}</td>
					<td>${film.name}</td>
					<td><a href="deleteFilm?idfilm=${film.id}&employeeId=${employee.id}">Delete</a></td>
				</tr>
				</c:forEach> 
				
				<tr> 
				   <td>Адреса</td>
				</tr>
				<c:forEach var="empl" items="${employee.addresslist}">
				<tr>
					<td>${empl.id}</td>
					<td>${empl.name}</td>
					<td><a href="deleteAdres?idadr=${empl.id}&employeeId=${employee.id}">Delete</a></td>
				</tr>
				</c:forEach>
				
			</c:forEach>
		</table>
		

		
		
		<h4>
			New Employee Register <a href="newEmployee">here</a>
		</h4>
	</div>
</body>
</html>