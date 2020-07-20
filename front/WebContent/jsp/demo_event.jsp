<%@ page contentType="text/event-stream; charset=UTF-8"%>
<%
response.setHeader("Cache-Control","no-store"); // HTTP 1.0 ver
response.setHeader("Pragma", "no-chae");
response.setDateHeader("Expires", 0);
if(request.getProtocol().equals("HTTP/1.1")) //HTTP 1.1ver
	response.setHeader("Cache-Control", "no-cache");
%>
<%="retry: 10000\n"%>
<%="data: WELCOME TO My Server\n"%>
<%="data: The server time is: "+new java.util.Date()+"\n"%>
<%="event: first\n"%>
<%="retry: 5000\n"%>
<%="data: hello\n\n"%>
<%out.flush();%>