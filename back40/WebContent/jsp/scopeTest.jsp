<%@page import="com.my.exception.MethodOverridingExceptionSample.P"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
pageContext.setAttribute("pAttr", "page"); // 현재 사용중인 page에 속성추가(JSP 객체 - Servlet Class에는 기본적으로 업는 속성)
pageContext.setAttribute("rAttr", "request", PageContext.REQUEST_SCOPE); // request 에 속성 추가
// = request.setAttribute("rAttr", "request"); 같은 표현
pageContext.setAttribute("sAttr", "session", PageContext.SESSION_SCOPE);// session 에 속성 추가
pageContext.setAttribute("aAttr", "application", PageContext.APPLICATION_SCOPE);// application에 속성 추가
%>
<p>
	Page 속성값 : <%=pageContext.getAttribute("pAttr") %>
</p>
<p>
	Request 속성값 : <%=pageContext.getAttribute("rAttr", PageContext.REQUEST_SCOPE) %><br>
	<%=request.getAttribute("rAttr") %>
</p>
<p>
	Session 속성값 : <%=pageContext.getAttribute("sAttr", PageContext.SESSION_SCOPE) %><br>
	<%=session.getAttribute("sAttr") %>
</p>
<p>
	Application 속성값 : <%=pageContext.getAttribute("aAttr", PageContext.APPLICATION_SCOPE) %><br>
	<%=application.getAttribute("aAttr") %>
</p>
<p>
	<%-- <jsp:forward page="scopeTestResult.jsp"></jsp:forward> --%>
	<jsp:include page="scopeTestResult.jsp"></jsp:include>
</p>
</body>
</html>