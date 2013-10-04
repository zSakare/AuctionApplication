<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored ="false" %> 
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="auctionBean" class="main.model.data.Auction" scope="session" />
<jsp:useBean id="userBean" class="main.model.dao.UserDAO" scope="session"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Auction</title>
</head>
<body>
<c:if test="${auctionBean.title ne ''}">
	<img src="data:image/jpg;base64,<c:out value='${auctionBean.picture}'/>" />
	<h1><c:out value="${auctionBean.title}" /></h1>
	<h2>Description</h2>
	<c:out value="${auctionBean.description}" />
	<h2>Postage</h2>
	<c:out value="${auctionBean.postageDetails}" />
	<h2>Current Bid</h2>
	<!--  
	<c:out value="${auctionBean.startingPrice}" /><br />
	<c:out value="${auctionBean.bidIncrements}" /><br />-->
	Bid Closes at <c:out value="${auctionBean.closingTime}" /><br />
	<c:if test="${userBean.isAdmin}">
		
		<form name="haltAuctionForm" action="controller" method="POST">
		<input type="hidden" name="action" value="haltAuction" />
		<input type="hidden" name="auctionid" maxlength="50" value="${auctionBean.auctionID}"/>
		<input type="submit" name="haltAuctionSubmit" value="Halt This Auction!" />
		</form>
		
		
		<form name="removeAuctionForm" action="controller" method="POST">
		<input type="hidden" name="action" value="removeAuction" />
		<input type="hidden" name="auctionid" maxlength="50" value="${auctionBean.auctionID}"/>
		<input type="submit" name="removeAuctionSubmit" value="Remove This Auction!" />
		</form>
	</c:if>
</c:if>	
	


</body>
</html>