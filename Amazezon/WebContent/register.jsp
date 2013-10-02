<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Amazezon - User Registration</title>
</head>
<body>
	<jsp:useBean id="userBean" class="main.model.UserDAO" scope="session" />
	
	<div id="register">
		<form name="userRegistrationForm" action="controller" method="POST">
			<input type="hidden" name="action" value="register"/>
			<table>
			<tr>
				<td>Username*</td>
				<td><input type="text" name="username" maxlength="50" /></td>
			</tr>
			<tr>	
				<td>Password*</td>
				<td><input type="text" name="password" maxlength="50" /></td>
			</tr>
			<tr>	
				<td>Confirm Password*</td>
				<td><input type="text" name="passwordConfirm" maxlength="50" /></td>
			</tr>
			<tr>
				<td>Email*</td>
				<td><input type="text" name="email" maxlength="100" /></td>
			</tr>
			<tr>
				<td>Firstname</td>
				<td><input type="text" name="firstName" maxlength="50" /></td>
				
			</tr>
			<tr>	
				<td>Lastname</td>
				<td><input type="text" name="lastName" maxlength="50" /></td>
			</tr>
			<tr>	
				<td>Address</td>
				<td><input type="text" name="address" maxlength="100" /></td>
			</tr>
			<tr>	
				<td>Year of Birth</td>
				<td><input type="text" name="dob" maxlength="4" /></td>
			</tr>
			<tr>	
				<td>Credit Card Number*</td>
				<td><input type="text" name="creditCard" maxlength="19" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="submitRegistration" value="Register"></td>
			</tr>
			</table>
		</form>
	</div>
	
</body>
</html>