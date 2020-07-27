<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<% 
	Cookie[] cArr = request.getCookies();
	if(cArr != null){
		for(Cookie c: cArr){
%>
<%=c.getName() %> = <%= c.getValue() %>
		<br>
<%
		}
	}
%>
	<hr>
<%
	String cName = request.getParameter("cName");
	String cValue = request.getParameter("cValue");
	if(cName != null && "".equals(cName) && cValue != null && "".equals(cValue)){
		Cookie c = new Cookie(cName, cValue);
		c.setMaxAge(60);
		//c.setMaxAge(-1);
		response.addCookie(c);
	}
%>
	<a href="cookieTest.jsp">��Ű����</a>
	<form action="cookieTest.jsp">
		<label>
			��Ű �̸� : <input type="text" name="cName">
		</label><br>
		<label>
			��Ű �� : <input type="text" name="cValue">
		</label><br>
		<button type="submit">��Ű �߰�</button>
	</form>
</body>
</html>