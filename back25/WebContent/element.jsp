<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  -> language는 기본 java, 다른건 지원안함, content type에서 인코딩지정하면 pageEncoding 생량 --%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>

<body>
    <h1>JSP 구성요소</h1>
    <ul>
        <li>HTML 요소</li>
        <li>JSP 요소
            <ol>
                <li>Scripting Element
                    <ul>
                        <li>
                            <p>
                                scriptlet : <br>
                                <%
                                int j;
                                j=10;
                                out.print(j);
                                out.print("요청방식 : "+ request.getMethod());
                                %>
                                <br>
                                -jsp용 java파일의 _jspService()내부에 들어갈 구문
                            </p>
                        </li>
                        <li>
                        	<p>
                        	expression :<br>
                            <%= j%>
                            <br>
                            -jsp용 java파일의 _jspService()내부에 들어갈 구문<br>
                            out.print()를 자동호출함 ex) scriptlet 에서 out.print(j); 과 같다.<br>
                            ;차이 주의
                            </p>
                        </li>
                        <li>
                        	<p>
                        	declaration :<br>
                        	<%! int j; // 인스턴스 변수 %>
                        	<br>
                        	-jsp용 java파일의 _jspService() 외부에 들어갈 구문
                        	<br>
                        	<b>j값 : <%=j %> (지역변수),
                        		인스턴스 변수 j값<%=this.j %>
                        		<%! void m(){//out.print("hello"); 내장 객체 사용 불가
                        		} %>
                        		<%
                        		this.j++;
                        		j++;
                        		%>
                        	</b>
                        	</p>
                       	 </li>
                    </ul>
                </li>
                <li>Directive Element
                	<ul>
                		<li>
                			page 지시자 = jsp용 java 파일이 만들어질때 필요한 정보를 기술
                		</li>
                		<li>
                		
                		</li>
                		<li>
                			
                		</li>
                	</ul>
                </li>
                <li>Action Tag Element</li>
            </ol>
        </li>
    </ul>
    <br>
    <h3>내장 객체[기본 객체]들</h3>
    <p>
    	<b>
        public void _jspService(final javax.servlet.http.HttpServletRequest request, final
        javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException{
        </b>
        <br>
        final javax.servlet.jsp.PageContext pageContext;
        <br>
        javax.servlet.http.HttpSession session = null;
        <br>
        final javax.servlet.ServletContext application;
        <br>
        final javax.servlet.ServletConfig config; -> 옛날 버전을 위해 잔존하는 객체
        <br>
        javax.servlet.jsp.JspWriter out = null;
        <br>
        final java.lang.Object page = this;
        <br>
        javax.servlet.jsp.JspWriter _jspx_out = null;
        <br>
        javax.servlet.jsp.PageContext _jspx_page_context = null;
        <br>
        }
    </p>
    <hr>
    <h3>주석 Comment</h3>
    <p>
        HTML 주석 : out.print() 의 인자가 안됨, 응답에 포함
        <!-- HTML 주석 냐옹이야! 보안 최악 -->
        <br>
        Java 주석 : out.print()의 인자가 안됨, 응답에 포함 안됨
        <%//Java 주석 냐옹이야! %>
        <br>
        JSP 주석 : JSP용 Java파일에 포함 안됨
        <%--JSP주석 냐옹이야! --%>
        <br>
        -> 결론 : JSP주석을 써라
    </p>
</body>

</html>