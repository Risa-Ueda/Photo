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
		<h3>${username} PAGE</h3>
		<div>Posts:${posts}${message}</div><!-- post数を表示 -->
		<a class="submit" href="javascript:history.back();">return</a><!-- 履歴のひとつ前のページに戻る -->
			<c:if test="${post !=null}"><!-- postがnullじゃない場合 -->
				<c:forEach var="i" items="${post}"><!-- postの分繰り返す -->
						<br>
					<div class="imgname"><img src=${i.imgname} width=400 height=auto></div><!-- imgnameにファイル名を代入し画像を表示 -->
					<div class="comment">comment:${i.comment}<c:if test="${empty i.comment}">No comment</c:if></div><!-- commentを表示 -->
						<form action="Delete" method="post"><!-- Home.javaに飛ぶ -->
							<input class="submit" type="submit" name="button" value="delete"><!-- buttonにdeleteの値を入れる -->
							<input type="hidden" name="id" value="${i.id}"><!-- deleteの時に必要なidを取得するためにidを非表示で代入 -->
						</form>
					<div style="border-top: 2px solid #ffcce6;"></div>
						<br>
				</c:forEach>
			</c:if>
	</div>
</body>
</html>