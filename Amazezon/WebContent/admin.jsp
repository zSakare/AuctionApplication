<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored ="false" %> 
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Amazezon - Admin</title>
</head>
<body>
	<p><c:out value="${userBean.messages}" /></p>
	<jsp:setProperty name="userBean" property="messages" value="" />
	<c:choose>
		<c:when test="${userBean.isAdmin}">
			Hello <c:out value="${userBean.firstName}" />
			<h3>Halt an Auction</h3>
			<form name="haltAuctionForm" action="controller" method="POST">
			<input type="hidden" name="action" value="haltAuction" />
			Auction Name: <input type="text" name="auctionName" maxlength="50" />
			<input type="submit" name="haltAuctionSubmit" value="Halt Auction!" />
			</form>
			<h3>Remove an auction</h3>
			<form name="removeAuctionForm" action="controller" method="POST">
			<input type="hidden" name="action" value="removeAuction" />
			Auction Name: <input type="text" name="auctionName" maxlength="50" />
			<input type="submit" name="removeAuctionSubmit" value="Remove Auction!" />
			</form>
			<h3>Ban a user</h3>
			<form name="banForm" action="controller" method="POST">
			<input type="hidden" name="action" value="ban" />
			Username: <input type="text" name="username" maxlength="50" />
			<input type="submit" name="banSubmit" value="Ban!" />
			</form>
			
		</c:when>
		<c:otherwise>
			<p>Sorry you're not an admin!</p>
		</c:otherwise>
		
	</c:choose>
	


</body>
</html>