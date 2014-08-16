<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html ng-app>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.0-beta.5/angular.min.js"></script>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<table>
	<tbody>
		<tr>
			<td>Customer Name : </td>
			<td>${customer.firstName} ${customer.lastName }</td>
		</tr>
		<tr>
			<td>Street a: </td>
			<td>${customer.address.street }</td>
		</tr>
		<tr>
			<td>City : </td>
			<td>${customer.address.city }</td>
		</tr>
		<tr>
			<td>State : </td>
			<td>${customer.address.state }</td>
		</tr>
		<tr>
			<td>Country : </td>
			<td>${customer.address.country }</td>
		</tr>
		
		
	</tbody>
</table>
</body>
</html>
