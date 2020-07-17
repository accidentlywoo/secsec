<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String prod_no = request.getParameter("prod_no");
String strQuantity = request.getParameter("quantity");
if(!"".equals(prod_no) && !"".equals(strQuantity)){
%>{"status":"success"}	
<%}else{
%>{"status": "fail"}
<%}
%>