<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

input[type=text], select, table {
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

.holder {
	border: 3px solid #f1f1f1;
	width: 30%;
}

.container {
	padding: 25px;
	background-color: lightblue;
}

table, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<center>
		<h1>Simple Banking System - Account Details</h1>
		<label>${errorMessage}</label>

		<div class="holder">
			<div class="container">
				<ul style="list-style: none; align-content: left">
					<li><b>Account Pin:</b> ${acctDtl.accountPin}</li>
					<li><b>First Name:</b> ${acctDtl.firstName}</li>
					<li><b>Middle Name:</b> ${acctDtl.middleName}</li>
					<li><b>Last Name:</b> ${acctDtl.lastName}</li>
					<li><b>Current Balance:</b> ${acctDtl.accountBalance}</li>
				</ul>
			</div>
		</div>

		<h3>Create New Transaction</h3>
		<form:form action="/createTransaction"
			modelAttribute="accountTransaction" method="post">
			<div class="container">
			<input type="hidden" value="${acctDtl.accountPin}" name="accountPin" readonly/>
				<label>Amount : </label> <input type="text"
					placeholder="Enter Amount" name="amount" required> <label>Transaction
					Type : </label> <select name="transactionType">
					<option>--</option>
					<option value="CREDIT">Deposit</option>
					<option value="DEBIT">Withdrawal</option>
				</select>
				<button type="submit">Save New Transaction</button>

			</div>
		</form:form>

		<h3>Transaction History</h3>

		<div class="holder">
			<div class="container">
				<c:if test="${empty transactions}">
					<i>No Transactions to Display</i>
				</c:if>
				<c:if test="${not empty transactions}">

					<table style="table-layout : fixed;width:400px">
						<tr>
							<th style="width:100px">ID</th>
							<th style="width:100px">Debit</th>
							<th style="width:100px">Credit</th>
							<th style="width:100px">Running Balance</th>
							<th style="width:100px">Transaction Date</th>
						</tr>
						<c:forEach items="${transactions}" var="trans">
							<tr>
								<td style="width:100px">${trans.id}</td>
								<td style="width:100px">${trans.debitAmount}</td>
								<td style="width:100px">${trans.creditAmount}</td>
								<td style="width:100px">${trans.runningBalance}</td>
								<td style="width:100px">${trans.dateTime}</td>
							</tr>
						</c:forEach>

					</table>
				</c:if>
			</div>
		</div>
	</center>
</body>
</html>
