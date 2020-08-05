package com.my.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.exception.FindException;
import com.my.service.ProductService;
import com.my.vo.Product;

/**
 * Servlet implementation class ProductDetailServlet
 */
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
	public ProductDetailServlet() {
		productService = new ProductService();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String prod_no = req.getParameter("prod_no");
		try {
			Product product = productService.findByNo(prod_no);
			req.setAttribute("p", product);
			String servletPath = "/productDetail.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(servletPath);
			dispatcher.forward(req, resp);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
}
