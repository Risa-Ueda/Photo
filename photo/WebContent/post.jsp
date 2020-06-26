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
		<h3>CREATE PAGE</h3>
			<form action="Post" method="post"><!-- Post.javaに飛ぶ -->
				<p>${message}</p>
				<img src=${imgname} width=400 height=auto><!-- imgnameにファイル名を代入して画像を表示 -->
				<div style="font-size: 13px;">画像が表示されない場合はページを更新してください</div>
					<br>
				<input class="txt" type="text" name="comment" placeholder="add your comment!"><!-- commentを入力 -->
					<br>
				<input class="txt" type="text" name="username" value="${name}" placeholder="username"><!-- sessionで取得したnameを表示 -->
					<br>
				<input class="txt" type="text" name="imagename" value="${imgname}" placeholder="imagename"><!-- Image.javaで取得したimagenameを表示 -->
					<br>
				<input class="submit" type="submit" name="PostFinish" value="PostFinish"><!-- buttonにPostFinishの値を入れる -->
			</form>
				<br>
		<a class="submit" href="javascript:history.back();">return</a><!-- 履歴のひとつ前のページに戻る -->
	</div>
</body>
</html>