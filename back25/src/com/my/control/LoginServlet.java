package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.exception.FindException;
import com.my.service.CustomerService;
import com.my.vo.Customer;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService service;
	private String realPath;
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext sc = this.getServletContext();
		String customerEnvFileName = sc.getInitParameter("custonerEnv");
//		realPath = sc.getRealPath("customers.properties");
		realPath = sc.getRealPath(customerEnvFileName);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * ServletContext sc = this.getServletContext(); String realPath =
		 * sc.getRealPath("customers.properties"); CustomerService service = new
		 */
		service = new CustomerService(realPath);
		 
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
//		String mime = "application/json";
//		response.setContentType(mime);
//		PrintWriter out = response.getWriter();
		try {
			Customer customer = service.findById(id);
			String servletPath;
			if(customer == null) {
				servletPath = "/fail.jsp";
//				out.print("{\"status\":\"fail\"}");
//				return;
			}else if(!customer.getPwd().equals(pwd)) {
				servletPath = "/fail.jsp";
//				out.print("{\"status\":\"fail\"}");
//				return;
			}else {
				servletPath = "/success.jsp";
				Cookie cookie = new Cookie("id", id);
				response.addCookie(cookie);
			}				
			service.login(id, pwd);

			RequestDispatcher dispatcher = request.getRequestDispatcher(servletPath);
			dispatcher.forward(request, response);
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
