<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h3>지시자를 이용한 포함</h3>
		<%@include file="/jq/login.html" %>
	</div>
	<div>
		<h3>태그를 이용한 포함</h3>
		<jsp:include page="/jq/login.html"></jsp:include>
	</div>
</body>
</html>