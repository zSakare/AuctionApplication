<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored ="false" %> 
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style type="text/css">
	    ul.horizontal {
			margin:0;
			padding:0;
		}
		
		ul.horizontal li {
			display:block;
			float:left;
			padding:0 10px;
		}
	</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Amazezon - User Login</title>
</head>
<body>
	<ul class="horizontal">
	    <li><a href="register.jsp">Register</a></li>
	    <li><a href="login.jsp">Login</a></li>
	    <li><a href="auction.jsp">Auction List</a></li>
	    <li><a href="new-auction.jsp">New Auction</a></li>
	    <li><a href="admin.jsp">Admin</a></li>
	    <li><a href="userpage.jsp">Profile</a></li>
	</ul>
	<br>
	<jsp:useBean id="userBean" class="main.model.dao.UserDAO" scope="session" />
	
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
				<td><input type="password" name="password" maxlength="50" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="submitLogin" value="Login"></td>
			</tr>
			</table>
		</form>
	</div>
	
</body>
</html>