<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
<form action="update" method="post">
	<input type="hidden" name="article_no" value="${article.article_no}"/>
	<h2>번호:</h2>
	${article.article_no}
	<br>
	<h2>제목:</h2>
	<input type="text" name="title" value="${article.title}" />
	<br>
	<h2>내용:</h2>
	<textarea name="content" rows="5" cols="40">${content}</textarea>
	<p></p>
	<br>
	<input type="submit" value="글 수정" />
</form>
</body>
</html>