<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Profile PAGE</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div>
		<p>Profile PAGE</p>
		<form action="Profile" method="post">
		<c:if test="${post !=null}">
							<c:forEach var="i" items="${post}">
								<div class="username">${i.username}</div>
								<input class="submit" type="submit" name="username" value="${i.username}">
									<br>
							</c:forEach>
						</c:if>
					</form>
					<br>
					<a class="submit" href="javascript:history.back();">return</a>
	</div>
</body>
</html>