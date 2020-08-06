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

public class IdDupChController implements Controller{
	private static final long serialVersionUID = 1L;
	private CustomerService customerService;
	private String realPath;
	public IdDupChController() {
	}
	
	public IdDupChController(String realPath) {
		this.realPath = realPath;
		this.customerService = new CustomerService(realPath);
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		new CustomerService(realPath);
		String id = request.getParameter("id");
		try {
			Customer customer = customerService.findById(id);
			if(customer != null) {
				return "/fail.jsp";
			}
			return "/success.jsp";
		} catch (FindException e) {
			return "/fail.jsp";
		}
	}

	public String getRealPath() {
		return realPath;
	}
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
}
