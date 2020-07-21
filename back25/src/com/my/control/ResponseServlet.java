package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mime = "application/json";
		resp.setContentType(mime); // 응답형식 지정
		
		PrintWriter out = resp.getWriter(); // 응답출력스트림 얻기
		out.print("{\"status\":\"success\"}"); // 출력스트림에 쓰기

		// HTML 형식으로 응답하기		
//		String mime = "text/html";
//		resp.setContentType(mime); // 응답형식 지정
//		
//		PrintWriter out = resp.getWriter(); // 응답출력스트림 얻기
//		out.print("<h1>html 형태의 응답</h1>"); // 출력스트림에 쓰기
//
//		System.out.println("resp.getBufferSize() = "+resp.getBufferSize()); 
//		System.out.println("===================");
//		Collection<String> names = resp.getHeaderNames();
//		if(names.size()!=0) {
//			for(String name : names) {
//				String value = resp.getHeader(name);
//				System.out.println(name + " : "+ value);
//			}
//		}
	}
}
