<%@page import="com.my.vo.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--
	 요청 객체의 속성 중 "c"라는 이름의 속성 값 얻기
	Customer 타입으로 강제 형변환
	속성값이 null이면 Customer 객체 생성해서 요청객체의 속성(속성명 : "c") 으러 츠시
  --%>
<%--속성값 객체의 id인스턴스변수값을 "id1"로 설정 --%>
<%--속성값객체의 id인스턴스변수값얻어서 응답출력 --%>
<%--
	Customer customer = (Customer)request.getAttribute("c");
	if(customer == null){
		customer = new Customer();
		request.setAttribute("customer", customer);
	}
	customer.setId("id1");
	out.print(customer.getId());
--%>
<jsp:useBean id="customer" class="com.my.vo.Customer" scope="request"></jsp:useBean>
<jsp:setProperty name="customer" property="id" value="id1"/><%-- customer.setId("id1"); --%>
<jsp:getProperty name="customer" property="id"/><%--out.print(customer.getId()); --%>
<%-- 
class -> 각 엔지 벤터사(제우스,콤캣 등)마다 동작방식이 달라서 FQCN을 쓰지않으면 문제가 생길 수 있다.
get/set할 attribute 대상을 scope으로 지정한다.
 --%>
 <%--
 문제점! -> 확장성이 떨어진다.
 Has A 관계가 있는 객체에대한 핸들링이 불가하다
  --%>
  <%--
<jsp:setProperty name="customer" property="postal.buildingno" value="1111"/>
<jsp:getProperty name="customer" property="postal.buildingno" />\
-> 에러 발생!! 사용하지 말자!
-> 이 문제를 해결하기 위해 나온 EL!
 --%>
</body>
</html>