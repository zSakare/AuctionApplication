<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored ="false" %> 
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="form" class="main.form.RegistrationForm" scope="session"/>
<jsp:useBean id="userBean" class="main.model.dao.UserDAO" scope="session" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Amazezon - User Registration</title>
</head>
<body>
	<c:choose>
		<c:when test="${userBean.errorInForm}">
			<c:out value="A field that cannot be empty is missing a value." />
		</c:when>
		<c:otherwise>
			<%
				if (!userBean.getErrorInForm()) {
					userBean.logOut();	
				}
			%>	
		</c:otherwise>
	</c:choose>
	
	
	<div id="register">
		<form name="userRegistrationForm" action="controller" method="POST">
			<input type="hidden" name="action" value="register"/>
			<table>
			<tr>
				<td>Username*</td>
				<td><input type="text" name="username" maxlength="50" value="${form.username}"/></td>
			</tr>
			<tr>	
				<td>Password*</td>
				<td><input type="password" name="password" maxlength="50" value="${form.password}"/></td>
			</tr>
			<tr>	
				<td>Confirm Password*</td>
				<td><input type="password" name="passwordConfirm" maxlength="50" value="${form.passwordConfirm}"/></td>
			</tr>
			<tr>
				<td>Email*</td>
				<td><input type="text" name="email" maxlength="100" value="${form.email}"/></td>
			</tr>
			<tr>
				<td>Firstname</td>
				<td><input type="text" name="firstName" maxlength="50" value="${form.firstname}"/></td>
			</tr>
			<tr>	
				<td>Lastname</td>
				<td><input type="text" name="lastName" maxlength="50" value="${form.lastname}"/></td>
			</tr>
			<tr>	
				<td>Address</td>
				<td><input type="text" name="address" maxlength="100" value="${form.address}"/></td>
			</tr>
			<tr>	
				<td>Year of Birth</td>
				<td><input type="text" name="dob" maxlength="4" value="${form.dob}"/></td>
			</tr>
			<tr>	
				<td>Credit Card Number*</td>
				<td><input type="text" name="creditCard" maxlength="19" value="${form.creditCard}"/></td>
			</tr>
			<tr>
				<td><input type="submit" name="submitRegistration" value="Register"></td>
			</tr>
			</table>
		</form>
	</div>

	
</body>
</html>