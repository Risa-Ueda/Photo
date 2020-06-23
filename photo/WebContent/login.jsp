<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>LOGIN</title>
<link rel="stylesheet" href="login.css">
</head>
<body>
	<div>
		<h1>Login Page</h1>
		<p>${message}</p>　<!-- messageはログアアウトしましたになる -->
			<form action="Login" method="post">
				<label>NAME: </label>
				<input type="text" name="name">
				<br>
				<label>PASS: </label>
				<input type="password" name="pass">
				<br>
				<input class="submit" type="submit" name="button" value="login">	
				<br>
				<input class="submit" type="submit" name="button" value="createaccount">
				<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
			</form>
	</div>
</body>
</html>