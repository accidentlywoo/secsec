<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>c:set tag</h3>
	<p>
		<%-- page에 attribute 추가 --%>
		<c:set var="num" value="10" />
		<c:set var="num" value="hello"/>
		num : <%=pageContext.getAttribute("num") %><br>
		num(EL) : ${pageScope.num } <br>
		
		<c:set var="num1" value="2"/>
		<c:set var="num2" value="3"/>
		<c:set var="sum" value="${num1+num2 }"/><br>
		
		sum : ${pageScope.sum}
		
		<c:remove var="sum"/>
		<hr>
		sum : ${sum }
	</p>
	<h3>c:if tag</h3>
	<p>
		<%-- 요청전달데이터 opt값이 add인 경우 if(request.getParameter("opt").equals("add")) --%>
		<c:if test="${ param.opt == 'add'}">
			<span>추가 작업을 선택했습니다.</span>
		</c:if>
		<hr>
		c:if는 else가 없기 때문에 choose문을 사용해야 한다. (java에서 switch문과 비슷하다.)
	</p>
	<h3>c:choose tag</h3>
	<p>
		<c:choose><%-- switch --%>
			<c:when test="${ param.opt == 'add'}"><%-- case --%>
				<span>추가 작업을 선택했습니다.</span>
			</c:when>
			<c:when test="${ param.opt == 'update'}">
				<span>수정작업을 선택했습니다.</span>
			</c:when>
			<c:when test="${ param.opt == 'delete'}">
				<span>삭제 작업을 선택했습니다.</span>
			</c:when>
			<c:otherwise><%-- default --%>
				<span>그외의 작업을 선택했습니다.</span>
			</c:otherwise>
		</c:choose>
	</p>
	<h3>c:forEach tag</h3>
	<table border="1">
		<tr>
			<c:forEach begin="1" end="9" var="i" varStatus="status" step="2" >
				<td>${status.index} : ${status.count} : ${i }</td>
			</c:forEach>
		</tr>
	</table>
	<table border="1">
		<tr>
			<c:forEach begin="1" end="9" var="i" varStatus="status" >
			<%-- 짝수셀인 경우에는 td의 배경색을 다르게 지정하시오 --%>
				<td 
				<c:if test="${i%2 == 0}">
					style="background-color: #0f0"
				</c:if>
					>${status.index} : ${status.count} : ${i }</td>
			</c:forEach>
		</tr>
	</table>
	<hr>
	<h3>fmt:formatNumber, fmt:formatDate 태그</h3>
	<p>
		출력도 한다 요놈 <br>
		숫자 1234.56789 : <fmt:formatNumber value="1234.56789" pattern="#,##0.00" />
	</p>
	<p>
		<%
		Date dt = new Date(); 
		request.setAttribute("dt", dt);
		%>
		시간 출력 : <fmt:formatDate value="${requestScope.dt}" pattern="yyyy-MM-dd a h:mm:ss"/>
	</p>
</body>
</html>