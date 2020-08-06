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

public class ProductDetailController implements Controller {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
	public ProductDetailController() {
		productService = new ProductService();
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String prod_no = request.getParameter("prod_no");
		try {
			Product product = productService.findByNo(prod_no);
			request.setAttribute("p", product);
			String servletPath = "/productDetail.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(servletPath);
			dispatcher.forward(request, response);
		} catch (FindException e) {
			e.printStackTrace();
		}
		return null;
	}
}
