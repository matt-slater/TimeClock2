<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Admin</title>
	<link rel="stylesheet" type="text/css" href="css/styles.css" />
</head>

<body>
	<div class="panel">
	registered users:
	<table border="2">
		<tr>
			<th>Username</th>
			<th>First Name</th>
			<th>Last Name</th>
		</tr>
		<c:forEach items="${users}" var="x">
			<tr>
				<td>
					<c:out value="${x.getUsername()}" />
				</td>
				<td>
					<c:out value="${x.getfName()}" />
				</td>
				<td>
					<c:out value="${x.getlName()}" />
				</td>
			</tr>
		</c:forEach>
	</table>
	Clocked In users:
	<table border="2">
		<tr>
			<th>Username</th>
			<th>Wage</th>
			<th>Clock In Time</th>
			<th>hours worked</th>
		</tr>
		<c:forEach items="${currentShifts}" var="x">
			<tr>
				<td>
					<c:out value="${x.getUser().getUsername()}" />
				</td>
				<td>
					<c:out value="${x.getUser().getWage()}" />
				</td>
				<td>
					<c:out value="${x.getClockIn()}" />
				</td>
				<td>
					<c:out value="${x.getDurationFromTime(time.getTime())}" />
				</td>
			</tr>
		</c:forEach>
	</table>
	</div>
</body>

</html>