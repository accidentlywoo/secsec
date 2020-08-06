<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<!-- HTML 주석 -->

<head>
    <title>웰컴파일</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=3.0">
    <link rel="stylesheet" href="/back25/static/css/reset.css">
    <link rel="stylesheet" href="/back25/static/css/main.css">
    <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
    <link rel="stylesheet" href="/back25/static/css/bootstrap-4.5.0-dist/css/bootstrap.min.css">
    <style>
        body>div>section>div>.product:hover {
            border: 1px solid teal;
            background-color: greenyellow;
        }
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(function () {
            $("h1 .home").click(e => {
                window.location.reload();
            });
            //dom트리에서 class속성이 divContent인 div객체의 하위요소 section객체찾기
            let $section = $("div.divContent>section");

            //----------로그인 메뉴 START-------------
            //dom트리에서 nav>ul>li>a요소의 class속성이 login인 객체 찾기
            let $loginMenu = $('nav>ul>li>a.login');
            $loginMenu.click(() => {
                let oldSection = $section.html();
                $.ajax({
                    url: '/back25/login.html'
                    , success: data => {
                        oldSection += data;
                        $section.html(oldSection);
                        $('form>label>input[name=id]').val(localStorage.getItem("loginId"));
                        // $('form>label>input[name=pwd]').val(localStorage.getItem("loginPwd"));

                    }//end success function(data)
                });
                //$section.html("");
                $section.empty();
                return false;
            });
            //----------로그인 메뉴 END-----------
            //----------로그인버튼 CLICK START ------
            //DOM에 존재하지 않지만 향후 추가될 객체에 대한 이벤트 처리
            //on()
            $("div.divContent>section").on("click"
                , "form#loginComponent>button[type=submit]"
                , function () {
                    var idValue = $('form>label>input[name=id]').val();
                    var pwdValue = $('form>label>input[name=pwd]').val();
                    /*
                    로그인 버튼이 클릭되면 checkbox가 선택된 경우 localstorage에 저장
                    */
                    if (typeof (Storage) !== "undefined") {
                        if ($('form>label>input[type=checkbox]').prop("checked") && idValue != "") {

                            localStorage.setItem("loginId", idValue);
                        } else {
                            localStorage.removeItem("loginId");
                        }
                    } else {
                        alert("지원하지 않는 브라우저 입니다.");
                    }

                    $.ajax({
                        url: '/back25/login'
                        , method: 'POST'
                        , data: { id: idValue, pwd: pwdValue }
                        , success: function (data) {
                            // var responseObj = JSON.parse(data); //{"status": "success"}또는 {"status":"fail"}
                            if (data.status == "success") {
                                alert('로그인 성공');
                                location.reload();
                            } else {
                                alert("로그인 실패");
                                $('form>label>input[name=id]').focus();
                                $('form>label>input[name=id]').select();
                            }
                        }
                    });
                    return false;
                });

            $("div.divContent>section").on("submit"
                , "form"
                , function () {
                    return false;
                });
            //----------로그인버튼 CLICK END ------

            // ----------로그아웃------------
            let $logoutMenu = $('nav > ul li .logout');
            $logoutMenu.click(() => {
                $.ajax({
                    url: '/back25/logout'
                    , success: data => {
                        if (data.status == 'success') {
                            alert('로그아웃 되었습니다.');
                            location.reload();
                        }
                    }
                });
            });

            //----------로그아웃 CLICK END ------
            //회원 가입
            let $signupMenu = $('nav>ul>li>a.signup');
            $signupMenu.click(() => {
                location.href = '/back25/jq/signup.html';
            });
            //----------상품목록 메뉴 START----------
            //dom트리에서 nav>ul>li>a요소의 class속성이 productList인 객체 찾기
            let $productListMenu = $('nav>ul>li>a.productList');
            $productListMenu.trigger('click');
            $productListMenu.click(() => {
                let oldSection = $section.html();
                $.ajax({
                    url: '/back25/productList'
                    , success: data => {
                        //응답내용을 자바스크립트객체로 변환:배열형태로 응답
                        let responseArrObj = JSON.parse(data);
                        let sectionData = '<div class="products">';
                        responseArrObj.forEach((element, index) => {
                            let prod_no = element.prod_no;
                            let prod_name = element.prod_name;
                            let prod_price = element.prod_price;
                            //	             응답내용 처리				
                            sectionData += '<div class="product">';
                            sectionData += "<ul>";
                            sectionData += '<li class="prod_no" style="  visibility: hidden;">'; sectionData += prod_no; sectionData += '</li>';
                            //cartList +=  '<img src="/back25/images/'+element['prod_no']+'.jpg>';
                            sectionData += '<li><img src="/back25/images/' + prod_no + '.jpg"></li>';
                            sectionData += "<li>"; sectionData += prod_name; sectionData += "</li>";
                            sectionData += "<li>"; sectionData += prod_price; sectionData += "</li>";
                            sectionData += "</ul>";
                            sectionData += "</div>";
                        });
                        sectionData += "</div>";
                        sectionData += oldSection;
                        $section.html(sectionData);
                    }
                });
                return false;
            });

            $productListMenu.trigger('click'); // $productListMenu의 click 이벤트 강제 발생 
            //----------상품목록 메뉴 END-----------
            // -- 상품별 click start --
            $("section").on('click', ".products > .product", e => {
                let prodId = e.currentTarget.querySelector(".prod_no").innerHTML;
                var prodDetail = '';
                var targetObj = e.currentTarget;
                console.log("targetObj.nextSibling", targetObj.nextSibling);
                console.log("targetObj : ", targetObj.hasChildNodes());
                if (targetObj.nextSibling !== null &&
                    targetObj.nextSibling.className == 'productDetail') {
                    $(targetObj.nextSibling).remove();
                    return false;
                }
                $.ajax({
                    url: '/back25/productDetail'
                    , data: { 'prod_no': prodId }
                    , success: data => {
                        let responseObj = JSON.parse(data);
                        prodDetail += '<form class="productDetail">';
                        prodDetail = prodDetail + '<input type="text" value=' + responseObj['prod_no'] + ' name="prod_no"  readonly>';
                        prodDetail = prodDetail + '<input type="text" value=' + responseObj['prod_name'] + ' readonly>';
                        prodDetail = prodDetail + '<input type="text" value=' + responseObj['prod_price'] + ' readonly>';
                        prodDetail = prodDetail + '<input type="text" value=' + responseObj['prod_detail'] + ' readonly>';
                        prodDetail = prodDetail + '<label>수량 <input type="number" value="1" min="0" name="quantity" required></label>';
                        prodDetail = prodDetail + '<button type="submit">장바구니 담기</button>';
                        prodDetail = prodDetail + '</form>';
                        $(targetObj).after(prodDetail);
                    }
                });
                return false;
            });
            // -- 상품별 click end --
            // -- 장바구니 리스트 --
            $("nav li > a.cartList").click(e => {
                let $section = $("div.divContent>section");
                $.ajax({
                    url: '/back25/viewCart'
                    , success: data => {
                        if (data != "") {
                            let cartList = '<form class="cartList">';
                            cartList += '<ul>';
                            if (data.status != 'fail') {
                                data.cart.forEach((element, index) => {
                                    cartList += '<li>';
                                    cartList += '<input type="checkbox">';

                                    cartList += '<input type="text" value=' + element['prod_no'] + ' name="prod_no" class ="prodNo" readonly>';
                                    cartList += '<input type="text" value=' + element['prod_name'] + ' readonly>';
                                    cartList += '<input type="text" value=' + element['prod_price'] + ' readonly>';
                                    cartList += '<label>금액 <input type="text"value=' + element['prod_price'] + ' readonly></label>';
                                    cartList += '<label>수량 <input type="number" min="0" value=' + element['quantity'] + ' name="quantity" readonly></label>';
                                    cartList += '</li>';
                                });
                                cartList += '<li>';
                                cartList += '<label><button type="button" class="order_check">전체 선택/해제</button></label>'
                                cartList += '<label><button type="button" class="order">선택 주문하기</button></label>'
                                cartList += '</li>';
                                cartList += '</ul></form>';
                                $section.html(cartList);
                            } else {
                                cartList = '<h2>장바구니가 비었습니다" </h2>';
                                $section.html(cartList);
                            }
                        } else {
                            cartList = '<h2>장바구니가 비었습니다" </h2>';
                            $section.html(cartList);
                        }
                    }
                });
                $section.html("");
                return false;
            });

            // -- 장바구니 담기 --
            $("section").on('click', '.productDetail button[type=submit]', e => {
                var targetObj = e.currentTarget.parentElement;
                var formDataSerialize = $(targetObj).serialize();
                $.ajax({
                    url: '/back25/putCart'
                    , method: 'POST'
                    , data: formDataSerialize
                    , success: data => {
                        if (data.status == 'success') {
                            alert('장바구니 넣기 성공');
                            window.location.reload;
                        } else {
                            alert('장바구니 넣기 실패');
                        }
                    }
                });
                return false;
            });

            $("section").on('click', 'form.cartList button.order_check', () => {
                let $checked = $('form.cartList ul input[type=checkbox]');
                if ($checked.is(':checked')) {
                    $checked.prop('checked', false);
                } else {
                    $checked.prop('checked', true);
                }
            });
            // ----- 주문하기 버튼 Click Start -------
            $("section").on('click', 'form.cartList button.order', () => {
                let $checkedBox = $('form.cartList').serialize();
                $.ajax({
                    url: '/back25/addOrder'
                    , data: $checkedBox
                    , success: data => {
                        if (data.status == 'success') {
                            alert('주문 성공했습니다.');
                        } else {
                            alert("주문 실패 : " + data.msg);
                        }
                    }
                });
                return false;
            });
            // ------- 주문하기 End ---------
            // ------- 주문목록 ---------
            $("section nav li > a.cartList").click(() => {
                $.ajax({
                    url: '/back25/orderList'
                    , success: data => {

                    }
                });
            });
            // ------- 주문목록 End ---------
            // --------- 게시판 ------------
            $("nav li > a.boardList").click(e => {
                let $section = $("div.divContent>section");
                $.ajax({
                    url:'/back25/board/list'
                    ,success: data => {
                        $section.html(data.trim());
                    }
                });
                $section.html("");
                return false;
            });
            // ------- 게시판 End ---------
        });
    </script>
</head>

<body>
    <div class="container divContent">
        <h1>
            <a class="home">여니</a>
        </h1>
        <nav>
            <%-- <jsp:include page="/back25/fragment/menu.jsp"></jsp:include>
        --%>
            <%@include file="./fragment/menu.jsp" %>
        </nav>
        <section>

            <div class="loginPopUp" style="display:none">
            </div>
            <p>우물 속에는 달이 밝고 구름이 흐르고 하늘이 펼치고 파아란 바람이 불고 가을이 있고 추억처럼 사나이가 있습니다.</p>
            <div id="div1">
                <h3>텍스트 관련 태그들</h3>
                <p>
                    단락 1.
                </p>
                <p>
                    단락 2.
                    대웅제약은 <mark>중간엽줄기세포</mark>를 이용해 자체 개발한 신종 코로나바이러스 감염증(코로나19) 호흡기 증상 치료제 후보물질(DWP710)에 대해 인도네시아 정부로부터
                    임상 1상 시험 계획을 승인받았다고 6일 밝혔다.<br>

                    대웅제약은 합자 법인 대웅인피온과 함께 인도네시아에서 임상시험에 나선다.<br>

                    대웅인피온은 인도네시아 보건복지부와 함께 DWP710을 임상중이다.<br>
                </p>
            </div>
            <div id="div2">
                <h3>목록을 만드는 태그</h3>
                <ul>
                    <li>자바</li>
                    <li>오라클</li>
                    <li>HTML</li>
                </ul>
                <ol>
                    <li>자바</li>
                    <li>오라클</li>
                    <li>HTML</li>
                </ol>
                <dl>
                    <dt>제목</dt>
                    <dd>내용</dd>
                </dl>
                <h3>표를 만드는 태그</h3>
                <table style="width: 50%;border: 1px solid;">
                    <!-- 
                        <thead><tr><td>A</td><td>B</td></tr></thead>
                        <tbody><tr><td>C</td><td>D</td></tr></tbody>
                        <tbody><tr><td>E</td><td>F</td></tr></tbody> 
                        요즘은 tr, td로만 사용한다.
                        -->
                    <!-- <tr><td>A</td><td>B</td></tr> 
                        자동으로 thead, tbody 태그가 붙여진다
                        <tr><td>A</td><td>B</td></tr>
                        <tr><td>A</td><td>B</td></tr> -->
                    <tr>
                        <th>A</th>
                        <th>B</th>
                    </tr>
                    <tr>
                        <td>가나다</td>
                        <td>라마바</td>
                    </tr>
                    <tr>
                        <td>노잼노잼</td>
                        <td>노잼</td>
                    </tr>
                    <tr>
                        <td colspan="2">셀병합</td>
                    </tr>
                </table>
            </div>
            <div id="div3">
                <h3>이미지</h3>
                <img src="./images/movie_image.jpg" alt="살아있다.">
                <figure>
                    <img src="./images/movie_image.jpg" alt="살아있다.">
                    <figcaption>살아있다.</figcaption>
                </figure>
            </div>
            <div id="div4">
                <h3>링크 만들기</h3>
                <a href="http://www.naver.com">네이버</a>
                <a href="http://www.daum.net" target="_blank">다음</a>
                <br>
                <a href="#div1">텍스트</a>
                <a href="#div2">목록</a>
                <%-- <iframe src="../sub.html" width="500px"></iframe> --%>
            </div>
            <input type="number" min="0">
        </section>
    </div>
</body>

</html>