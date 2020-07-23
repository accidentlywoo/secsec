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
	private String pm;
	private String version;
	private String charset;
	
//	public ParamServlet() {
//		ServletContext context = this.getServletContext();
//		pm = context.getInitParameter("pm");
//		version = context.getInitParameter("version");
//		
//		charset = this.getInitParameter("charset");
		// NPE
//	}
	

	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext context = this.getServletContext();
		pm = context.getInitParameter("pm");
		version = context.getInitParameter("version");
		
		charset = this.getInitParameter("charset");
	}


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		res.setContentType("text/html;charset="+charset);
		PrintWriter writer = res.getWriter();
		writer.print("프로젝트 관리자 : "+pm);
		writer.print("<br>프로젝트 버전 : " + version);
		writer.print("<br>사용자가 입력한 ID : " + id);
	}
}
