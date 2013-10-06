<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="searchForm" class="main.form.SearchForm" scope="page"/>
<jsp:useBean id="auctionList" class="main.model.data.AuctionList" scope="session"/>
<jsp:useBean id="auction" class="main.model.data.Auction" scope="session"/>
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
<title>Amazezon - Auctions</title>
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
	
	<c:out value="${userBean.messages}" /> 
	<br>
	
	<div id="search">
		<form name="auctionSearchForm" action="controller" method="GET">
			<input type="hidden" name="action" value="auctionSearch"/>
			<table>
			<tr>
				<td>Item Name</td>
				<td><input type="text" name="title" maxlength="10" value="${searchForm.title}"/></td>
			</tr>
			<tr>
				<td>Category</td>
				<td><input type="text" name="category" maxlength="50" value="${searchForm.category}"/></td>
			</tr>
			<tr>
				<td><input type="submit" name="submitSearch" value="Search"></td>
			</tr>
			</table>
		</form>
	</div>
	<table>
		<c:if test="${!empty auctionList.auctions}">
			
				
				<c:forEach var="auction" items="${auctionList.auctions}">
					<form name="viewAuctionForm" action="controller" method="POST">
					<input type="hidden" name="action" value="viewAuction"/>
					<input type="hidden" name="auctionID" value="${auction.auctionID}">
						<table>	
							<tr>
								<td> <b>Title: </b>${auction.title} </td>
							</tr>
							<tr>
								<td> <b>Closing Time: </b>${auction.closingTime} </td>
							</tr>
							<tr>
								<td> <b>Description: </b>${auction.description} </td>
							</tr>
							<tr>
								<td><input type="submit" value="View it here!" /></td>
							</tr>
							
						</table>
					</form>
				</c:forEach>
			
		</c:if>
	</table>
</body>
</html>