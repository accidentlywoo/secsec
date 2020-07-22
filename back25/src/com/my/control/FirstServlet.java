package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * 다양한 요청 정보가 올 수 있다. 
	 *  경우 1 : http://localhost/back25/first?a=hello&b=1&c=one&c=twi&c=three
	 *  경우 2 : http://localhost/back25/first?a=
	 *  경우 3 : http://localhost/back25/first?b=1&c=one&c=twi&c=three
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FirsyServlet의 doGet()입니다.");
		String a = request.getParameter("a"); // 경우 2 : ?a= -> ""로 반환. 경우 3 : a-> null NPE 주의
		String b = request.getParameter("b");
		int intB = Integer.parseInt(b);
		String[] c = request.getParameterValues("c");

		System.out.println("a =  "+ a);
		System.out.println("intB = "+intB);
		if(c != null) {
			for(String ci : c) {
				System.out.println("c = "+ ci);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		System.out.println(id + "와 "+pwd+"를 입력하셨습니다.");
		
		// 응답형식 [MIME 표준 형식] 지정
//		response.setContentType("text/plain;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();// sout는 PrintStream 타입
		out.println("왜 나눈 안대앵 ");
		out.print("로그인 성공");
	}

}
