<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>HOME PAGE</title>
<link rel="stylesheet" href="home.css">
</head>
<body>
	<div>
		<p>HOME PAGE</p>
		<p>MY Picture</p>
		<form action="Home" method="post">
		<p>${message}</p>
		
		<c:if test="${list !=null}">
			<c:forEach var="i" items="${list}">
						<div class="username">@hello_world-${username}</div>
						<div class="imgname">${imgname}.jpg</div>	
					
			</c:forEach>
		</c:if>
			
		
		<input class="submit" type="submit" name="button" value="image">
		<input class="submit" type="submit" name="button" value="like">
		<input class="submit" type="submit" name="button" value="return">
		</form>
</body>
</html>