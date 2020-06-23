<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>ACCOUNT PAGE</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div>
		<p>ACCOUNT CREATE PAGE</p>
		<form action="Account" method="post">
			<p>${message}</p>
			<input type="text" name="username" placeholder="username">
				<br>
			<input type="text" name="password" placeholder="password">
				<br>
			<input class="submit" type="submit" name="button" value="account">
			<input class="submit" type="submit" name="button" value="login">
		</form>
	</div>
</body>
</html>