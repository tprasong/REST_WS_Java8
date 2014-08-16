<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>
	<table>
		<tbody>
			<tr>
				<td>Customer Name :</td>
				<td>${customer.firstName}${customer.lastName }</td>
			</tr>
			<tr>
				<td>Street :</td>
				<td>${customer.address.street }</td>
			</tr>
			<tr>
				<td>City a :</td>
				<td>${customer.address.city }</td>
			</tr>
			<tr>
				<td>State :</td>
				<td>${customer.address.state }</td>
			</tr>
			<tr>
				<td>Country :</td>
				<td>${customer.address.country }</td>
			</tr>


		</tbody>
	</table>
</body>
</html>
