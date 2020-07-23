<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%! 
	int count = 0;
	int totalScore = 0;
%>
<% 
	int score = Integer.parseInt(request.getParameter("score"));
	this.totalScore += score;
	this.count++;
%> 
<cite>선택한 별점은 <%=score %> 점 입니다.</cite>
<br>
<span>평균 별점 : <%=this.totalScore / this.count %></span>
<br>
<span>총 별점 참여 수 :<%=this.count %> </span>
<br>
<a href="/back25/jq/score.html">별점주기로 이동</a>
</body>
</html>