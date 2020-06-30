<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>CREATE FIN</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div>
		<h2>DELETE PAGE</h2>
		<h6>投稿が削除されました</h6>
			<form action="Home" method="post"><!-- Home.javaに飛ぶ -->
				<input class="submit" type="submit" name="button" value="home"><!-- buttonにhomeの値を入れる -->
			</form>
	</div>
</body>
</html>