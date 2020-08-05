<%@page import="com.my.vo.Product"%>
<%@page import="java.util.Map"%>
<%@page import="com.fasterxml.jackson.databind.ObjectWriter"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@ page language="java" contentType="application/json"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="totalPrice" value="0"/>
{"status": "success"
,"cart":[
<c:forEach items="${requestScope.cartDetail}" var="kv" varStatus="status">
   <c:set var="p" value="${kv.key}"/>
   <c:if test="${status.index > 0}">,</c:if>
{ "prod_no": "${p.prod_no}"
 ,"prod_name": "${p.prod_name}"
 ,"prod_price": "<fmt:formatNumber pattern="#,##0" value="${p.prod_price}"/>" 
 ,"quantity": ${kv.value}
 ,"money": "<fmt:formatNumber pattern="#,##0" value="${p.prod_price * kv.value}"/>"
}
<c:set var="totalPrice" value="${totalPrice+ (p.prod_price * kv.value)}"/>
</c:forEach>
]
,"totalPrice": "<fmt:formatNumber pattern="#,##0" value="${totalPrice}"/>"
}