<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="success" class="java.lang.String" scope="session"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Amazezon - Confirmation</title>
</head>
<body>
	<c:if test="${success eq 'true'}">
		Confirmed! Click <a href='http://localhost:8080/Amazezon/login.jsp'>here</a> to login. 
	</c:if>
	<c:if test="${not success eq 'true'}">
		Oops! Looks like something went wrong, go back to login <a href='http://localhost:8080/Amazezon/login.jsp'>here</a>.
	</c:if>
</body>
</html>