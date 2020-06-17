<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>CREATE FIN</title>
<link rel="stylesheet" href="home.css">
</head>
<body>
	<div>
		<p>CREATE FINISH PAGE</p>
		<form action="Backhome" method="post">			
			
					<p>${username}さん</p>
					<img src=${imgname}.jpg width=400 height=auto>
					<br>
			<input class="submit" type="submit" name="button" value="home">
		</form>
	</div>
</body>
</html>