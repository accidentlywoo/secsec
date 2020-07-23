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
		String pm = context.getInitParameter("pm"); // ServletContext parameter떙기기
		String version = context.getInitParameter("version");
		
		String charset = this.getInitParameter("charset"); // Servlet Class Parameter 땡겨오기
		
		String id = req.getParameter("id");
		
		res.setContentType("text/html;charset="+charset);
		PrintWriter writer = res.getWriter();
		writer.print("프로젝트 관리자 : "+pm);
		writer.print("<br>프로젝트 버전 : " + version);
		writer.print("<br>사용자가 입력한 ID : " + id);
	}
}
