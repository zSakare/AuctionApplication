<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored ="false" %> 
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Amazezon - Create an auction</title>
</head>
<body>
	<c:choose>
		<c:when test="${userBean.loggedIn && not userBean.isAdmin}">
			<div id="newAuction">
				<form name="createAuctionForm" action="controller" method="POST" enctype="multipart/form-data">
					<input type="hidden" name="action" value="createAuction"/>
					<table>
					<tr>
						<td>Title*</td>
						<td><input type="text" name="title" /></td>
					</tr>
					<tr>	
						<td>Category</td>
						<td><input type="text" name="category" /></td>
					</tr>
					<tr>	
						<td>Picture</td>
						<td><input type="file" name="picture" size="40"></td>
					</tr>
					<tr>
						<td>Description</td>
						<td><input type="text" name="description" /></td>
					</tr>
					<tr>
						<td>Postage Details*</td>
						<td><input type="text" name="postageDetails" /></td>
						
					</tr>
					<tr>	
						<td>Reserve Price*</td>
						<td><input type="text" name="reservePrice" /></td>
					</tr>
					<tr>	
						<td>Bidding Start Price*</td>
						<td><input type="text" name="biddingStartPrice" /></td>
					</tr>
					<tr>	
						<td>Bidding Increments*</td>
						<td><input type="text" name="biddingIncrements" /></td>
					</tr>
					<tr>	
						<td>End Time</td>
						<td><input type="text" name="endTime" /></td>
					</tr>
					<tr>
						<td><input type="submit" name="submitAuction" value="sumbitAuction"></td>
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