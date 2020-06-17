<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>CREATE</title>
<link rel="stylesheet" href="home.css">
</head>
<body>
	<div>
		<p>CREATE PAGE</p>
			<form action="CreateFinish" method="post">
			<p>${message}</p>
			<img src=${imgname}.jpg width=400 height=auto>
				<br>
				<input class="submit" type="submit" name="color" value="WB">
				<input class="submit" type="submit" name="color" value="Color">
				<input class="submit" type="submit" name="color" value="Drowing">
				<br>
			<input type="text" name="comment" placeholder="add your comment!">
				<br>
			<input type="text" name="username" value="risa" placeholder="add your comment!">
				<br>
			<input type="text" name="imagename" value="sky" placeholder="add your comment!">
				<br>
			<input class="submit" type="submit" name="CreateFinish" value="CreateFinish">
			</form>
	</div>
</body>
</html>