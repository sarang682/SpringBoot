<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 쓰기</title>
</head>
<body>
<form action="write" method="post">
<input type="hidden" name="writer" value="${sessionScope.login.id}" />
<input type="hidden" name="name" value="${sessionScope.login.name}" />
제목:
<br>
<input type="text" name="title" />
<br>
<br>
내용:
<br>
<textarea name="content" rows="5" cols="40"></textarea>
<br>
<br>
<input type="submit" value="새 글 등록" />
</form>
</body>
</html>