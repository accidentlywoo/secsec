<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="com.my.vo.Product"%><%-- <%@ : 페이지 지시자 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ObjectMapper mapper = new ObjectMapper();

Product product = (Product)request.getAttribute("p");
//String prod_no = product.getProd_no();
//String prod_name = product.getProd_name();
//int prod_price = product.getProd_price();
String jsonString = mapper.writeValueAsString(product);
%>
<%=jsonString %>
<%-- {
	"prod_no":"<%=prod_no %>"
	,"prod_name":"<%=prod_name %>"
	,"prod_price":"<%=prod_price %>"
	,"prod_detail":""
} --%>