<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>IMAGE</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div>
		<h2>IMAGE PAGE</h2>
			<form action="Image" method="post"><!-- Image.javaに飛ぶ -->
				<img src=sky.jpg width=400 height=auto><!-- sky.jpgの画像表示 -->
				<img src=pc.jpg width=400 height=auto><!-- pc.jpgの画像表示 -->
				<img src=time.jpg width=400 height=auto><!-- time.jpgの画像表示 -->
					<br>
				<input class="submit" type="submit" name="imagename" value="sky.jpg"><!-- sky.jpgをimagenameに値を入れる -->
				<input class="submit" type="submit" name="imagename" value="pc.jpg"><!-- pc.jpgをimagenameに値を入れる -->
				<input class="submit" type="submit" name="imagename" value="time.jpg"><!-- time.jpgをimagenameに値を入れる -->
			</form>	
				<br>
			<p>${message}</p>
			<form action="Upload" enctype="multipart/form-data" method="post"><!-- Upload.javaに飛ぶ -->
				<input type="file" name="image"><!-- フォルダを開き、画像を取得 -->
				<input class="submit" type="submit" value="upload" name="button"><!-- buttonにuploadの値を入れる -->
			</form>
				<br>
		<a class="submit" href="javascript:history.back();">return</a><!-- 履歴のひとつ前のページに戻る -->
	</div>
</body>
</html>