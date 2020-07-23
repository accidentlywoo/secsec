<%@page import="java.io.FileInputStream"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	for(int i = 0; i < 1000; i ++){
%>
	<span><%=i %> ,</span>
<%
	}
%>

<%
	FileInputStream fis;
	fis = new FileInputStream("c:/a.txt");
%>
</body>
</html>