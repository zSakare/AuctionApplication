<%@page import="main.form.validator.FormError"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored ="false" %> 
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="newAuctionForm" class="main.form.NewAuctionForm" scope="session"/>
<jsp:useBean id="userBean" class="main.model.dao.UserDAO" scope="session"/>
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
<title>Amazezon - Create an auction</title>
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
	<c:choose>
		<c:when test="${userBean.loggedIn && not userBean.isAdmin && not userBean.banned}">
			<c:if test="${formErrors != ''}">
				<c:forEach var="formError" items="${formErrors}">
					<c:out value="${formError.error}"/><br>
				</c:forEach>
				<c:set var="formErrors" scope="session"/>
			</c:if>
			
			
			
			<div id="newAuction">
				<form name="createAuctionForm" action="controller" method="POST" enctype="multipart/form-data">
					<input type="hidden" name="action" value="createAuction"/>
					<table>
					<tr>
						<td>Title*</td>
						<td><input type="text" name="title" value="${newAuctionForm.title}"/></td>
					</tr>
					<tr>	
						<td>Category</td>
						<td><input type="text" name="category" value="${newAuctionForm.category}"/></td>
					</tr>
					<tr>	
						<td>Picture</td>
						<td><input type="file" name="picture" size="40"></td>
					</tr>
					<tr>
						<td>Description</td>
						<td><input type="text" name="description" value="${newAuctionForm.description}"/></td>
					</tr>
					<tr>
						<td>Postage Details*</td>
						<td><input type="text" name="postageDetails" value="${newAuctionForm.postageDetails}"/></td>
						
					</tr>
					<tr>	
						<td>Reserve Price*</td>
						<td><input type="text" name="reservePrice" value="${newAuctionForm.reservePrice}"/></td>
					</tr>
					<tr>	
						<td>Bidding Start Price*</td>
						<td><input type="text" name="biddingStartPrice" value="${newAuctionForm.biddingStartPrice}"/></td>
					</tr>
					<tr>	
						<td>Bidding Increments*</td>
						<td><input type="text" name="biddingIncrements" value="${newAuctionForm.biddingIncrement}"/></td>
					</tr>
					<tr>	
						<td>End Time</td>
						<td><input type="text" name="endTime" value="${newAuctionForm.endTime}"/></td>
					</tr>
					<tr>
						<td><input type="submit" name="submitAuction" value="Create Auction"></td>
					</tr>
					</table>
				</form>
			</div>
		</c:when>
		<c:otherwise>
			<p>You don't have the correct permissions to create an auction</p>
		</c:otherwise>
	</c:choose>
</body>
</html>