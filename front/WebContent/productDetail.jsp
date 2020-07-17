<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String prod_no = request.getParameter("prod_no");
%>
{"prod_no": "<%=prod_no%>"
  ,"prod_name": "블론드 서머 라떼"
  ,"prod_price": 1000
  ,"prod_detail": "서머 플로트 크림을 디저트처럼 즐기는 블론드 에스프레소 아이스 라떼!"
  }
