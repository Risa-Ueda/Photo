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
		<h2>Profile PAGE</h2>
			<form action="Profile" method="post"><!-- Profile.javaに飛ぶ -->
				<c:if test="${post !=null}"><!-- アカウントがnullじゃない場合 -->
					<c:forEach var="i" items="${post}"><!-- アカウントがある分を繰り返す -->
						<input class="submit" type="submit" name="username" value="${i.username}"><!-- usernameのボタンを表示 -->
							<br><br>
					</c:forEach>
				</c:if>
			</form>
				<br>
		<a class="submit" href="javascript:history.back();">return</a><!-- 履歴のひとつ前のページに戻る -->
	</div>
</body>
</html>