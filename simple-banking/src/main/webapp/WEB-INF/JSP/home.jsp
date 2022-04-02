<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Simple Banking System</title>
<style>
Body {
	font-family: Calibri, Helvetica, sans-serif;
	background-color: pink;
}

button {
	background-color: #4CAF50;
	width: 100%;
	color: orange;
	padding: 15px;
	margin: 10px 0px;
	border: none;
	cursor: pointer;
}

form {
	border: 3px solid #f1f1f1;
	width: 30%;
}

input[type=text], input[type=password] {
	width: 100%;
	margin: 8px 0;
	padding: 12px 20px;
	display: inline-block;
	border: 2px solid green;
	box-sizing: border-box;
}

button:hover {
	opacity: 0.7;
}

.container {
	padding: 25px;
	background-color: lightblue;
}
</style>
</head>
<body>
	<center>
		<h1>Simple Banking System</h1>
		<label style="color:red">
		
<c:if test="${not empty errorAccountDetails}">
${errorAccountDetails}
	</c:if></label>
	<form:form action="/getAccountDetails"
		modelAttribute="accountHolder" method="post">
		<div class="container">
			<label>Account Pin : </label> <input type="text"
				placeholder="Enter Account Pin" name="accountPin" required>
				<label>First Name : </label> <input type="text"
				placeholder="Enter First Name" name="firstName" required>
				<label>Last Name: </label> <input type="text"
				placeholder="Enter Last Name" name="lastName" required>
			<button type="submit">View Account Details</button>
			<i> <a href="/registerAccountHolder">Register Account Holder</a></i>

		</div>
	</form:form>
	
	</center>
</body>
</html>
