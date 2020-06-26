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
		<h3>POST FINISH PAGE</h3>
		<form action="Home" method="post"><!-- Home.javaに飛ぶ -->
			<p>UserName</p>
			<p>${username}</p><!-- post.jspで受け取ったusernameを表示 -->
			<p>Posted Image</p>
			<img src=${imgname} width=400 height=auto><!-- post.jspで受け取ったimgnameを代入し画像を表示 -->
				<br>
			<p>Comment</p>
			<p>${comment}</p><!-- post.jspで受け取ったコメントを表示 -->
			<input class="submit" type="submit" name="button" value="home"><!-- buttonにhomeの値を入れる -->
		</form>
	</div>
</body>
</html>