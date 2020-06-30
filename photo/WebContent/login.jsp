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
		<p>${message}</p>
			<form action="Login" method="post"><!-- Login.javaに飛ぶ -->
				<label>NAME: </label>
				<input class="txt" type="text" name="name"><!-- ユーザーのnameを入力 -->
					<br>
				<label>PASS: </label>
				<input class="txt" type="password" name="pass"><!-- ユーザーのpassを入力 -->
					<br>
				<input class="submit" type="submit" name="button" value="login"><!-- buttonにloginの値を入れる -->	
					<br><br>
				<input class="submit" type="submit" name="button" value="createaccount"><!-- buttonにcreateaccountの値を入れる -->
					<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
			</form>
	</div>
</body>
</html>