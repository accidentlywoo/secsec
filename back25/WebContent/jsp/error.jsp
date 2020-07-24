<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %><%-- 내장객체로 Exception 제공 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="background: yellow;">
<h1>에러발생</h1>
<p>
예외 내용 : <%= exception.getMessage() %>
</p>
</body>
</html>