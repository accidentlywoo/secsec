<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = request.getParameter("id");
if("id1".equals(id)){ //ID중복인 경우
%>{"status":"success"}
<%}else{%>{"status":"fail"}
<%}%>