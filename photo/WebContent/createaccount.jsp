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
		<h3>ACCOUNT CREATE PAGE</h3>
		<form action="Account" method="post"><!-- Account.javaに飛ぶ -->
			<p>${message}</p>
			<p>登録する名前とパスワードを入力してください</p>
				<br>
			<input type="text" name="username" placeholder="username"><!-- usernameを入力 -->
				<br><br>
			<input type="text" name="password" placeholder="password"><!-- passwordを入力 -->
				<br><br>
			<input class="submit" type="submit" name="button" value="create"><!-- buttonにcreateの値を入れる -->
				<br><br>
			<input class="submit" type="submit" name="button" value="login"><!-- buttonにloginの値を入れる -->
		</form>
	</div>
</body>
</html>