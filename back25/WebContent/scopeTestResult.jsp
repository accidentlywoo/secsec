<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>scopeTestResult.jsp</h2>
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