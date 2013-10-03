<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored ="false" %> 
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Amazezon - User Login</title>
</head>
<body>
	<jsp:useBean id="userBean" class="main.model.UserDAO" scope="session" />
	
	<c:if test="${loginStatus == 'failed'}">
		<c:out value="Login failed - try again" />
		
	</c:if>
	
	<%
		//change the loginStatus as we don't want to display the above message again.
		request.getSession().setAttribute("loginStatus", "notLoggedIn"); 
	%>
	
	
	<div id="login">
		<form name="loginForm" action="controller" method="POST">
			<input type="hidden" name="action" value="login"/>
			<table>
			<tr>
				<td>Username</td>
				<td><input type="text" name="username" maxlength="50" /></td>
			</tr>
			<tr>	
				<td>Password</td>
				<td><input type="text" name="password" maxlength="50" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="submitLogin" value="Login"></td>
			</tr>
			</table>
		</form>
	</div>
	
</body>
</html>