<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored ="false" %> 
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="auctionBean" class="main.model.data.Auction" scope="session" />
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
	Starting Price: <c:out value="${auctionBean.startingPrice}" />
	Bid Increment: <c:out value="${auctionBean.bidIncrements}" />
	Closing Time: <c:out value="${auctionBean.endDate}" />
</c:if>	
	


</body>
</html>