<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored ="false" %> 
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="auctionBean" class="main.model.data.Auction" scope="session" />
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
<title>View Auction</title>
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
<c:if test="${auctionBean.title ne ''}">

	<c:if test="${auctionBean.picture ne ''}">
		<img src="data:image/jpg;base64,<c:out value='${auctionBean.picture}'/>" />
	</c:if>
	<h1><c:out value="${auctionBean.title}" /></h1>
	<c:if test="${auctionBean.description ne ''}">
		<h2>Description</h2>
		<c:out value="${auctionBean.description}" />
	</c:if>
	<h2>Postage</h2>
	<c:out value="${auctionBean.postageDetails}" />
	<h2>Current Bid</h2>
	<!--  
	<c:out value="${auctionBean.startingPrice}" /><br />
	<c:out value="${auctionBean.bidIncrements}" /><br />-->
	<c:choose>
		<c:when test="${auctionBean.closed}">
			Auction ended at  <c:out value="${auctionBean.endDate}" /> <br />
		</c:when>
		<c:otherwise>
			Auction closes at <c:out value="${auctionBean.endDate}" /><br />
		</c:otherwise>
		
	</c:choose>
	
	<c:if test="${userBean.isAdmin}">
		
		<form name="haltAuctionForm" action="controller" method="POST">
		<input type="hidden" name="action" value="haltAuction" />
		<input type="hidden" name="auctionid" maxlength="50" value="${auctionBean.auctionID}"/>
		<input type="hidden" name="title" maxlength="50" value="${auctionBean.title}"/>
		<input type="submit" name="haltAuctionSubmit" value="Halt This Auction!" />
		</form>
		
		
		<form name="removeAuctionForm" action="controller" method="POST">
		<input type="hidden" name="action" value="removeAuction" />
		<input type="hidden" name="auctionid" maxlength="50" value="${auctionBean.auctionID}"/>
		<input type="hidden" name="title" maxlength="50" value="${auctionBean.title}"/>
		<input type="submit" name="removeAuctionSubmit" value="Remove This Auction!" />
		</form>
	</c:if>
	<c:if test="${userBean.loggedIn && not userBean.isAdmin && not auctionBean.closed}">
		<form name="bidForm" action="controller" method="POST">
			<input type="hidden" name="action" value="bid" />
			<input type="text" name="bidAmount" />
			<input type="submit" name="submitBid" value="Submit Bid" />
		</form>
	</c:if>
</c:if>	
	


</body>
</html>