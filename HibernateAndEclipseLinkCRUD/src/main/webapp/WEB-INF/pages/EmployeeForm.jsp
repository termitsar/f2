<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Contact</title>
</head>
<body>
	<div align="center">
		<h1>New/Edit Employee</h1>
		<form:form action="saveEmployee" method="post"
			modelAttribute="employee">
			<table>
				<form:hidden path="id" />
				<tr>
					<td>Name:</td>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<td>Address:</td>
					<td><form:input path="address" /></td>
				</tr>
				<tr>
					<td>Telephone:</td>
					<td><form:input path="telephone" /></td>
				</tr>
				<tr>
					<td>Актер:</td>
					<td><form:input path="actor.name" /></td>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						name="action" value="save_employee"></td>
				</tr>
			</table>

			<td><form:hidden path="actor.employee.id" /></td>
			<td><form:hidden path="actor.id" /></td>

			<form:input path="new_adres.name" />
			<td colspan="2" align="center"><input type="submit"	name="action" value="save_adres"></td>
			<table>
				<c:forEach var="adres" items="${employee.addresslist}"
					varStatus="status">
					<tr>
						<td><form:hidden path="addresslist[${status.index}].id" /></td>
						<td><form:hidden
								path="addresslist[${status.index}].employee.id" /></td>
						<td>${adres.id}</td>
						<td><form:input path="addresslist[${status.index}].name" /></td>
					</tr>
				</c:forEach>
			</table>

		
			</br></br>

			
			<form:select path="New_film">
			<c:forEach var="film" items="${employee.filmlist_full}"	varStatus="status">
					<form:option value="${film.id}" label="${film.name}" />
			</c:forEach>
			</form:select>
			<td colspan="2" align="center"><input type="submit"	name="action" value="save_film"></td>
			
			<table>
				<c:forEach var="film" items="${employee.filmlist}"
					varStatus="status">
					<tr>
						<td><form:input path="filmlist[${status.index}].name" /></td>
						<td>${film.id}</td>
						<td>${film.name}</td>
						<td><form:hidden path="filmlist[${status.index}].id" /></td>
					</tr>
				</c:forEach>
			</table>
			

		</form:form>

	</div>
</body>
</html>