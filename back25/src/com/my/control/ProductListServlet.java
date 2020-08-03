package com.my.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.exception.FindException;
import com.my.service.ProductService;
import com.my.vo.Product;

/**
 * Servlet implementation class ProductListServlet
 */
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
	
	public ProductListServlet() {
		productService = new ProductService();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			List<Product> list = productService.findAll();
			req.setAttribute("list", list);
			String servletPath = "/jsp/productList.jsp";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(servletPath);
			dispatcher.forward(req, res);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
}
