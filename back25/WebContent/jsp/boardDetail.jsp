<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<<c:set var="board" value="${requestScope.board }"></c:set>
<div>
	 <div class="board">
            <div class="board_no">게시글 번호</div>
            <div class="board_title">제목</div>
            <div class="board_writer">작성자</div>
            <div class="board_dt">날짜</div>
        </div>
        <div class="board">
            <div class="board_no">${board.board_no }</div>
            <div class="board_title">${board. board_title}</div>
            <div class="board_writer">${board.board_writer}</div>
            <div class="board_dt">${board. board_dt}</div>
        </div>
</div>