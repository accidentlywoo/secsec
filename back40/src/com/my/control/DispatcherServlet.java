package com.my.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/") // .html, .jpg, .css등 static 파일 경로도 타게된다.
public class DispatcherServlet extends HttpServlet{

	private Controller loginController = new LoginController();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in DispatcherServlet");
		String servletPath = request.getServletPath();
		String forwardServletPath = loginController.excute(request, resp);
		if("/login".equals(servletPath)) {
			ServletContext sc = this.getServletContext();
			String customerEnvFileName = this.getInitParameter("customerEnv");
			if(!"".equals(forwardServletPath)) {
				RequestDispatcher reqdp  =  request.getRequestDispatcher(servletPath);
				reqdp.forward(request, resp);
			}
		}else if("/logout".equals(servletPath)) {
			
		}
		
	}
}
