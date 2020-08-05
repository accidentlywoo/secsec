<%@page import="java.util.ArrayList"%>
<%@page import="com.my.vo.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
ObjectMapper mapper = new ObjectMapper();

List<Product> list =(List) request.getAttribute("list");
String jsonStr = mapper.writeValueAsString(list);
%>
<%=jsonStr %>