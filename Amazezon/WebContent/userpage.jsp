<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored ="false" %> 
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="userBean" class="main.model.dao.UserDAO" scope="session" />
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
<title>Insert title here</title>
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
	<c:choose>
		<c:when test="${userBean.loggedIn}">
			Hello <c:out value="${userBean.firstName}" />
			<a href=new-auction.jsp>Create a New Auction</a>
			<c:out value="${userBean.messages}" />
			<jsp:setProperty name="userBean" property="messages" value=""/>
		</c:when>
		<c:otherwise>
			Sorry you're not logged in!
		</c:otherwise>
		
	</c:choose>
	


</body>
</html>