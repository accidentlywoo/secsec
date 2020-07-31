<%@page import="com.my.vo.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<Board> list = (List) request.getAttribute("list");
%>
<div class=boardInfo"">현제 페이지 : /  총페이지 수 : </div>
<div class="boardList">
	<div class="board">
		<div class="board_no">게시글 번호</div>
		<div class="board_title">제목</div>
		<div class="board_writer">작성자</div>
		<div class="board_dt">날짜</div>
	</div>
	<%
		for (Board item : list) {
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
</div>