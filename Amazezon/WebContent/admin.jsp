<%@page import="main.model.dao.AuctionDAO"%>
<%@page import="main.model.data.Auction"%>
<%@ page import="java.util.List" %>
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
<title>Amazezon - Admin</title>
</head>
<body>
	<ul class="horizontal">
	    <li><a href="register.jsp">Register</a></li>
	    <li><a href="login.jsp">Login</a></li>
	    <li><a href="auction.jsp">Auction List</a></li>
	    <li><a href="new-auction.jsp">New Auction</a></li>
	    <li><a href="admin.jsp">Admin</a></li>
	</ul>
	<br>
	<jsp:useBean id="userBean" class="main.model.dao.UserDAO" scope="session" />
	<p><c:out value="${userBean.messages}" /></p>
	<jsp:setProperty name="userBean" property="messages" value="" />
	<c:choose>
		<c:when test="${userBean.loggedIn && userBean.isAdmin}">
			Hello <c:out value="${userBean.firstName}" />
			
			
			
			
			
			
			<h3>Ban a user</h3>
			<form name="banForm" action="controller" method="POST">
				<input type="hidden" name="action" value="ban" />
				Username: <input type="text" name="username" maxlength="50" />
				<input type="submit" name="banSubmit" value="Ban!" />
			</form>
			
		</c:when>
		<c:when test="${not userBean.loggedIn}">
			<p>Sorry you're not logged in!</p>
		</c:when>
		<c:when test="${not userBean.isAdmin}">
			<p>Sorry you're not an admin!</p>
			
		</c:when>
		
		
	</c:choose>
	


</body>
</html>