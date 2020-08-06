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
public class ProductListController implements Controller {
	private ProductService productService;
	private String realPath;
	
	public ProductListController() {
	}
	
	public ProductListController(String realPath) {
		this.realPath = realPath;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(productService == null) {
			productService = new ProductService();
		}
		try {
			List<Product> list = productService.findAll();
			request.setAttribute("list", list);
			return "/jsp/productList.jsp";
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
