<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
String name = request.getParameter("name");
String buildingno = request.getParameter("buildingno");
String addr = request.getParameter("addr");
if(!"".equals(id) && !"".equals(pwd) &&  !"".equals(name) && !"".equals(buildingno) && !"".equals(addr)){
%>{"status": "success"}  
<%}else{
%>{"status": "fail"}
<%}%>