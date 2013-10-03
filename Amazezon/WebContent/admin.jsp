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

	<c:choose>
		<c:when test="${userBean.loggedIn}">
			Hello <c:out value="${userBean.firstName}" />
			<a href=http://localhost:8080/Amazezon/new-auction.jsp>Create a New Auction</a>
		</c:when>
		<c:otherwise>
			<p>Sorry you're not logged in!</p>
		</c:otherwise>
		
	</c:choose>
	


</body>
</html>