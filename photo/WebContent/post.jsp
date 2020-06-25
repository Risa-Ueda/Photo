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
			<form action="Post" method="post">
				<p>${message}</p>
				<img src=${imgname} width=400 height=auto>
				<div class="att"style="font-size: 13px;">画像が表示されない場合はページを更新してください</div>
					<br>
				<input type="text" name="comment" placeholder="add your comment!">
					<br>
				<input type="text" name="username" value="${name}" placeholder="username">
					<br>
				<input type="text" name="imagename" value="${imgname}" placeholder="imagename">
					<br>
				<input class="submit" type="submit" name="PostFinish" value="PostFinish">
			</form>
			<br>
			<a class="submit" href="javascript:history.back();">return</a>
	</div>
</body>
</html>