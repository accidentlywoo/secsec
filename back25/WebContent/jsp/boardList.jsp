<%@page import="com.my.model.PageBean"%>
<%@page import="com.my.vo.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--
	PageBean bean = (PageBean)request.getAttribute("pageBean");
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="bean" value="${requestScope.pageBean }" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $(()=> {
        let $pageGroupButton = $('.pageGroup ul li>a');
        //jQuery로 DOM객체를 찾을 때 배열은 자바스크립트 객체의 배열로 반환한다.
        // 미쳐
        let $boardPage = $('.boadPage');
        $($pageGroupButton).click(e=>{
            let page = $(e.target).html();
            $.ajax({
                url: '/back25/${bean.url}'
                ,data:'currentPage='+ page
                ,success: data => {
                    $boardPage.html(data.trim()); 
                }
            });
            return false;//prevent bubbling
        });
        let $boardWrite = $('.boardWrite > a');
        $boardWrite.click(e => {
            $.ajax({
                url: '/back25/jsp/write.html'
                ,success: data => {
                    $boardPage.html(data.trim()); 
                }
            });
        });
        //----- 글 Click Start -------
        $boardDetail = $('.boardList > div.board');
        $boardDetail.not(':first').click(e => {
            let $boardNo = e.target.parentElement.children[0].innerText.trim();
            $.ajax({
                url:'/back25/board/detail'
                ,data:{board_no: $boardNo}
                ,success: data => {
                    $boardPage.html(data.trim());
                }
            });
            $boardPage.empty();
            return false;
        });
        // ----- 글 Click End -------
    });
</script>
<div class="boadPage">
    <div class=boardInfo"">
        현제 페이지 : <span>
            <%--=bean.getCurrentPage() --%>${bean.currentPage}</span>/ 총페이지 수 : <span>
            <%--=bean.getEndPage() --%>${bean.endPage}</span>
    </div>
    <div class="boardWrite">
    	<a>글쓰기</a>
    </div>
    <div class="boardList">
        <div class="board">
            <div class="board_no">게시글 번호</div>
            <div class="board_title">제목</div>
            <div class="board_writer">작성자</div>
            <div class="board_dt">날짜</div>
        </div>
        <%--
            for (Board item : bean.getList()) {
        --%>
        <c:forEach var="item" items="${bean.list}">
            <div class="board">
                <div class="board_no">
                    <%--=item.getBoard_no()--%>${item.board_no}</div>
                <div class="board_title">${item.board_title}</div>
                <div class="board_writer">
                    <%--=item.getBoard_writer()--%>${item.board_writer }</div>
                <div class="board_dt">
                    <%--=item.getBoard_dt()--%>${item.board_dt}</div>
            </div>
        </c:forEach>
    </div>
    <div class="pageGroup">
     	<c:if test="${bean.startPage  > 1}">
     		<a>&#91;prev&#93;</a>
     	</c:if>
        <c:forEach var="i" begin="${bean.startPage}" end="${bean.endPage}">
            <ul>
                <li>&#91;<a>${i}</a>&#93;</li>
            </ul>
        </c:forEach>
        <c:if test="${bean.endPage  < totalPage}">
     		<a>&#91;next&#93;</a>
     	</c:if>
    </div>
</div>