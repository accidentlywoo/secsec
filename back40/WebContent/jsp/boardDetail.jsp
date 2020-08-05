<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="board" value="${requestScope.board }"></c:set>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $(() => {
        let $boardReply = $('.boardReply');
        let $replyButton = $('button.reply');
        $replyButton.click(e => {
            console.log($(e.target));
            if(e.target.innerHTML != '답글 감추기'){
                e.target.innerHTML = '답글 감추기';
                $boardReply.hide();
            }else{
                e.target.innerHTML = '답글쓰기';
                $boardReply.show();
            }
        });
        let $submitReply = $('button.submitReply');
    });
</script>
<div>
    <div class="board">
        <div class="board_no">게시글 번호</div>
        <div class="board_title">제목</div>
        <div class="board_writer">작성자</div>
        <div class="board_dt">날짜</div>
    </div>
    <div class="board">
        <div class="board_no">${board.board_no}</div>
        <div class="board_title">${board.board_title}</div>
        <div class="board_writer">${board.board_writer}</div>
        <div class="board_dt">${board.board_dt}</div>
        <div class="board_content">
            <pre>${board.board_content}</pre>
        </div>
    </div>
    <button type="button" class="reply">답글쓰기</button>
    <button type="button" class="update">수정</button>
    <button type="button" class="delete">삭제</button>
</div>
<div class="boardReply">
    <form>
        <input type="hidden" name="parent_no" value="${board.board_no}">
        <label for="board_title">제목</label>
        <input type="text" name="board_title">
        <label for="board_content">글 내용</label>
        <input type="text" name="board_content">
        <button type="button" class="submitReply">답글쓰기 전송 제출</button>
    </form>
</div>