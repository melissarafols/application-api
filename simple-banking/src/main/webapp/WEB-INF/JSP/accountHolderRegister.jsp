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
		<h1>Simple Banking System - Create New Account</h1>
		<label>${errorMessage}</label>
	<form:form action="/saveNewAccount"
		modelAttribute="accountHolder" method="post">
		<div class="container">
				<label>First Name : </label> <input type="text"
				placeholder="Enter First Name" name="firstName" required>
				<label>Middle Name : </label> <input type="text"
				placeholder="Enter Middle Name" name="middleName" required>
				<label>Last Name : </label> <input type="text"
				placeholder="Enter Last Name" name="lastName">
				<label>Amount : </label> <input type="text"
				placeholder="Enter Opening Balance" name="accountBalance" required>
			<button type="submit">Save New Account</button>

		</div>
	</form:form>
	
	</center>
</body>
</html>
