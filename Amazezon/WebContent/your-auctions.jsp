<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>Amazezon - Create an auction</title>
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
	<div id="register">
		<form name="createAuctionForm" action="controller" method="POST">
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
				<td><input type="text" name="picture" /></td>
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
	
</body>
</html>