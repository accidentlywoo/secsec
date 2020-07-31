<%@page import="com.my.model.PageBean"%>
<%@page import="com.my.vo.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	PageBean bean = (PageBean)request.getAttribute("pageBean");
%>
<div class=boardInfo"">현제 페이지 : <span><%=bean.getCurrentPage() %></span>/  총페이지 수 :  <span><%=bean.getEndPage() %></span></div>
<div class="boardList">
	<div class="board">
		<div class="board_no">게시글 번호</div>
		<div class="board_title">제목</div>
		<div class="board_writer">작성자</div>
		<div class="board_dt">날짜</div>
	</div>
	<%
		for (Board item : bean.getList()) {
	%>
	<div class="board">
		<div class="board_no"><%=item.getBoard_no()%></div>
		<div class="board_title">
		<%for(int i = 0; i < item.getLevel(); i++){
			%>
			&#09;&#09;
			<%
		} %>
		<%=item.getBoard_title()%>
		</div>
		<div class="board_writer"><%=item.getBoard_writer()%></div>
		<div class="board_dt"><%=item.getBoard_dt()%></div>
	</div>
	<%
		}
	%>
	<%
	int totalPage = bean.getTotalPage();
	if(totalPage > 0){
		%>
		<ul>
		<%
		for(int j = 0; j < totalPage; j++){
			%>
				<li><a><%=j+1 %></a></li>
			<%
		}
		%>
		</ul>
		<%
	}
	%>
</div>