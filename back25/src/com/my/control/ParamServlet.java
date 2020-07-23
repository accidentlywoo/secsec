package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext context = this.getServletContext();
		String pm = context.getInitParameter("pm");
		String version = context.getInitParameter("version");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = res.getWriter();
		writer.print("프로젝트 관리자 : "+pm);
		writer.print("\n 프로젝트 버전 : " + version);
	}
}
