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
	</div>
</body>
</html>