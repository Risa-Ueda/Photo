<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>CREATE</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div>
		<p>CREATE PAGE</p>
			<form action="CreateFinish" method="post">
			<p>${message}</p>
			<img src=${imgname}.jpg width=400 height=auto>
				<br>
			<input type="text" name="comment" placeholder="add your comment!">
				<br>
			<input type="text" name="username" value="${username}" placeholder="username">
				<br>
			<input type="text" name="imagename" value="${imgname}">
				<br>
			<input class="submit" type="submit" name="CreateFinish" value="CreateFinish">
			</form>
	</div>
</body>
</html>