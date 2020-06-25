<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Profile PAGE</title>
<link rel="stylesheet" href="home.css">
</head>
<body>
	<div class="top">
		<p>Profile PAGE</p>
		<div>User:${username}</div>
		<div>Post:${posts}</div>
		<p>${message}</p>
		<a class="submit" href="javascript:history.back();">return</a>
				<c:if test="${post !=null}">
							<c:forEach var="i" items="${post}">
									<br>
								<div class="imgname"><img src=${i.imgname} width=400 height=auto></div>
								<div class="comment">comment:${i.comment}</div>
								<input class="submit" type="submit" name="button" value="delete"><input type="hidden" name="id" value="${i.id}">
								<div style="border-top: 2px solid #ffcce6;"></div>
									<br>
							</c:forEach>
						</c:if>
	</div>
</body>
</html>