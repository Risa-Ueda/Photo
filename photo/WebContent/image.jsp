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
		<p>IMAGE PAGE</p>
			<form action="Image" method="post">
				<img src=sky.jpg width=400 height=auto>
				<img src=pc.jpg width=400 height=auto>
				<img src=time.jpg width=400 height=auto>	
					<br>
				<input class="submit" type="submit" name="imagename" value="sky.jpg">
				<input class="submit" type="submit" name="imagename" value="pc.jpg">
				<input class="submit" type="submit" name="imagename" value="time.jpg">
			</form>	
				<br>
			<!-- 画像アップロード用フォーム -->
			<form action="Upload" enctype="multipart/form-data" method="post">
				<input type="file" name="image">
				<input class="submit" type="submit" value="upload" name="button">
				<input class="submit" type="submit" value="view" name="button">
			</form>
	</div>
</body>
</html>