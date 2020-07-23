<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.FileInputStream"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page buffer="none" %>
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
<%--
	 위 내용은 왜 안나오나????
-> Buffer! 응답할때 기본 8k byte의 버퍼를 갖고 있따.
-> 예외 발생했을때 Buffer를 날려버렸다.
 --%>
<%
	FileInputStream fis;
	try{
		fis = new FileInputStream("c:/a.txt");	
	}catch(FileNotFoundException e){
		String servletPath = "/back25/error.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(servletPath);
		dispatcher.forward(request, response); // forward에서 기존 버퍼내용을 clear하고 응답을 한다.
	}
	
%>
</body>
</html>