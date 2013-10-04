<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="searchForm" class="main.form.SearchForm" scope="page"/>
<jsp:useBean id="auctionList" class="main.model.data.AuctionList" scope="session"/>
<jsp:useBean id="auction" class="main.model.data.Auction" scope="session"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="search">
		<form name="auctionSearchForm" action="controller" method="GET">
			<input type="hidden" name="action" value="auctionSearch"/>
			<table>
			<tr>
				<td>Item Name</td>
				<td><input type="text" name="title" maxlength="10" value="${form.getTitle}"/></td>
			</tr>
			<tr>
				<td>Category</td>
				<td><input type="text" name="category" maxlength="50" value="${form.getCategory}"/></td>
			</tr>
			<tr>
				<td><input type="submit" name="submitSearch" value="auctionSearch"></td>
			</tr>
			</table>
		</form>
	</div>
	<table>
		<c:if test="${!empty auctionList.auctions}">
			<c:forEach var="auction" items="${auctionList.auctions}">
				<tr>
					<td> ${auction.auctionID} </td>
				</tr>
				<tr>
					<td> ${auction.title} </td>
				</tr>
				<tr>
					<td> ${auction.closingTime} </td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</body>
</html>