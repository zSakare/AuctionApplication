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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Amazezon - Admin</title>
</head>
<body>
	<jsp:useBean id="userBean" class="main.model.dao.UserDAO" scope="session" />
	<p><c:out value="${userBean.messages}" /></p>
	<jsp:setProperty name="userBean" property="messages" value="" />
	<c:choose>
		<c:when test="${userBean.loggedIn && userBean.isAdmin}">
			Hello <c:out value="${userBean.firstName}" />
			
			<h3>Halt an Auction</h3>
			<form name="haltAuctionForm" action="controller" method="POST">
			<input type="hidden" name="action" value="haltAuction" />
			Auction id: <input type="text" name="auctionid" maxlength="50" />
			<input type="submit" name="haltAuctionSubmit" value="Halt Auction!" />
			</form>
			
			<h3>Remove an auction</h3>
			<form name="removeAuctionForm" action="controller" method="POST">
			<input type="hidden" name="action" value="removeAuction" />
			Auction id: <input type="text" name="auctionid" maxlength="50" />
			<input type="submit" name="removeAuctionSubmit" value="Remove Auction!" />
			</form>
			<% 
				AuctionDAO auctionDAO = new AuctionDAO();
			    List<Auction> allAuctions = auctionDAO.getAllAuctions();
				request.getSession().setAttribute("allAuctions", allAuctions);
			%>
			
			<c:if test="${!empty allAuctions}">
				<c:forEach var="auction" items="${allAuctions}">
				<table>
					<tr>
						<td><b>ID</b></td><td> ${auction.auctionID} </td>
					</tr>
					<tr>
						<td> <b>Title</b> </td><td>${auction.title} </td>
					</tr>
					<tr>
						<td><b>End Time</b></td><td> ${auction.closingTime} </td>
					</tr>
					<tr>
						<td><b>Image</b></td><td> <img src="data:image/jpg;base64,<c:out value='${auction.picture}'/>" /></td>
					</tr>
					</table>
				</c:forEach>
			</c:if>
			
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