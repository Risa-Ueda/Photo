<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>POST FIN</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div>
		<p>POST FINISH PAGE</p>
		<form action="Home" method="post">			
			<p>UserName</p>
			<p>${username}</p>
			<p>Posted Image</p>
			<img src=${imgname} width=400 height=auto>
				<br>
			<p>Comment</p>
			<p>${comment}</p>
			<input class="submit" type="submit" name="button" value="home">
		</form>
	</div>
</body>
</html>