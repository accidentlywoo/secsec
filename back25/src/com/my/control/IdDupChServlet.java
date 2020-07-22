package com.my.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.exception.FindException;
import com.my.service.CustomerService;
import com.my.vo.Customer;

public class IdDupChServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletContext = this.getServletContext();
		String realPath = servletContext.getRealPath("customers.properties");
		CustomerService customerService = new CustomerService(realPath);
		
		String id = request.getParameter("id");
		
		try {
			Customer customer = customerService.findById(id);
			String servletPath = "/success.jsp";
			if(customer != null) {
				servletPath = "/fail.jsp";
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(servletPath);
			dispatcher.forward(request, response);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
}
