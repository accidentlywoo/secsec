package com.my.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.exception.FindException;
import com.my.service.ProductService;
import com.my.vo.Product;

public class ViewCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
	
	public ViewCartServlet() {
		productService = new ProductService();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Map<String, Integer> cart = (Map<String, Integer>)session.getAttribute("cart");
		String servletPath = null;
		if(cart == null) {
			servletPath ="/fail.jsp";
		}else {
			Map<Product, Integer> cartDetail = new HashMap<Product, Integer>();
			for(String prod_no : cart.keySet()) {
				try {
					cartDetail.put(productService.findByNo(prod_no), cart.get(prod_no));
				} catch (FindException e) {
					e.printStackTrace();
				}
			}
			req.setAttribute("cartDetail", cartDetail);
			servletPath = "/jsp/viewCart.jsp";
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(servletPath);
		dispatcher.forward(req, resp);
	}
}
