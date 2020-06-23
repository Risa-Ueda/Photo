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
		<div class="top">
			<p>HOME PAGE</p>
				<form action="Home" method="post">	
					<input class="submit" type="submit" name="button" value="image">
					<input class="submit" type="submit" name="button" value="like">
					<input class="submit" type="submit" name="button" value="logout">
					<p>ALL Posted</p>
						<c:if test="${post !=null}">
							<c:forEach var="i" items="${post}">
								<div class="username">${i.username}</div>
									<br>
								<div class="imgname"><img src=${i.imgname} width=400 height=auto></div>
								<div class="comment">comment:${i.comment}</div>
								<input class="submit" type="submit" name="button" value="delete"><input type="hidden" name="id" value="${i.id}">
									<br>
							</c:forEach>
						</c:if>
				</form>
		</div>
</body>
</html>