<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="nav nav-pills mb-3 nonSignIn">
	<c:choose>
		<%-- <c:when test="${empty cookie.id }"> --%>
		<c:when test="${empty sessionScope.loginInfo }">
			<li class="nav-item"><a href="#" class="nav-link active login">로그인</a>
			</li>
			<li class="nav-item"><a href="#" class="nav-link signup">회원가입</a>
			</li>
			<li class="nav-item"><a class="nav-link">학번 찾기</a></li>
			<li class="nav-item"><a class="nav-link">비밀번호 찾기</a></li>
		</c:when>
		<c:otherwise>
			<li class="nav-item"><a href="#" class="nav-link active logout">로그아웃</a>
			</li>
			<li class="nav-item"><a href="#" class="nav-link signup">내정보
					보기</a></li>
		</c:otherwise>
	</c:choose>
	<li class="nav-item"><a href="#" class="nav-link productList">상품목록</a>
	</li>
	<li class="nav-item"><a href="#" class="nav-link cartList">장바구니</a>
	</li>
</ul>