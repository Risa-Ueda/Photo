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
		<h2>HOME PAGE</h2>
			<form action="Home" method="post"><!-- Home.javaに飛ぶ -->
				<input class="submit" type="submit" name="button" value="image"><!-- buttonにimageの値を入れる -->
				<input class="submit" type="submit" name="button" value="profile"><!-- buttonにprofileの値を入れる -->
				<input class="submit" type="submit" name="button" value="logout"><!-- buttonにlogoutの値を入れる -->
				<h4>ALL Posted</h4>
					<c:if test="${post !=null}"><!-- もしpostがnullじゃない場合 -->
						<c:forEach var="i" items="${post}"><!-- postの数だけ繰り返し -->
							<div class="username">${i.username}</div><!-- usernameを表示 -->
								<br>
							<img src=${i.imgname} width=400 height=auto><!-- ファイル名を代入し画像を表示 -->
							<div class="comment">comment:${i.comment}<c:if test="${empty i.comment}">No comment</c:if></div><!-- commentを表示 -->				
								<br>
						</c:forEach>
					</c:if>
			</form>
	</div>
</body>
</html>