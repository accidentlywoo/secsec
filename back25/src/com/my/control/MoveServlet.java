package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 페이지 이동 방법
 * 	1) 서버에서 이동 : Forward
 * 		RequestDispatcher의 forward()
 * 		기존페이지의 request/response를 다른 페이지에게 전달
 * 		기존페이지의 응답버퍼내용ㅇ을 CLEAR하고 전달함(response 응답 버퍼는 8키로바이트)
 * 	
 * 	2)클라이언트에서 재요청 : Redirect
 * 		response.sendRedirect()
 * 
 * 	3) 다은페이지내용 포함 : include
 * 		RequestDispatcher의 include()
 * 		기존페이지의 request/response를 다른 페이지에게 전달, 
 * 		기존페이지의 응답버퍼내용을 CLEAR 안하고 전달함
 */
public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String opt = request.getParameter("opt");
		PrintWriter printWriter = response.getWriter();
		printWriter.print("MOVE SERVLET");
		if(opt == null || opt.equals("")) {
			show(request, response);
		}else if(opt.equals("forward")) {
			String servletPath = "/first"; // "/back25/first" -> url : /back25/back25/first 로 생성
			// "first" -> 상대 경로로 결정된다. 현재
			// "/first" -> 절대경로는 ServletPath 경로 기준으로 잡는다.
			RequestDispatcher dispatcher = request.getRequestDispatcher(servletPath);
			dispatcher.forward(request, response);
		}else if(opt.equals("redirect")) {
			String browserPath = "/back25/first";
			response.sendRedirect(browserPath);
		}else if(opt.equals("include")) {
			String servletPath = "jq/login.html";
			RequestDispatcher dispatcher = request.getRequestDispatcher(servletPath);
			dispatcher.include(request, response);
		}
	}
	protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<body>");
		out.print("<ul>");
		
		out.print("<li>");
		out.print("<a href=\"move?opt=forward?a=hello&b=7\">");
		out.print("포워드");
		out.print("</a>");
		out.print("</li>");
		
		out.print("<li>");
		out.print("<a href=\"move?opt=redirect?a=hello&b=7\">");
		out.print("리다이렉트");
		out.print("</a>");
		out.print("</li>");
		
		out.print("<li>");
		out.print("<a href=\"move?opt=include\">");
		out.print("인클루드");
		out.print("</a>");
		out.print("</li>");
		
		out.print("</ul>");
		out.print("</body>");
		out.print("</html>");
	}
}
