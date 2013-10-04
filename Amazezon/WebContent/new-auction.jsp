<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="newAuctionForm" class="main.form.NewAuctionForm" scope="session"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Amazezon - Create an auction</title>
</head>
<body>
	
	
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
	
</body>
</html>