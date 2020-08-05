package com.my.control.pathTest;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PathTest
 */
public class PathTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("request.getMethod()= " + req.getMethod());
		System.out.println("request.getRequestURL()= "+req.getRequestURL()); //http://IP/ContextPath/ServletPath
		System.out.println("request.getRequestURI()= "+req.getRequestURI());
		System.out.println("request.getServerName()= "+req.getServerName());
		System.out.println("request.getServletPath()= "+req.getServletPath());
		System.out.println("request.getContextPath()= "+req.getContextPath()); // 현재 배포된 프로젝트 이름 -> /back25
		
		System.out.println("=================");
		
		Enumeration< String> names = req.getHeaderNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			System.out.println(name+" = "+req.getHeader(name)); // user-agent
		}
	}
}
