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

/**
 * Servlet implementation class PutCartServlet
 */
public class PutCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
		if(cart == null) {
			System.out.println("check point : "+req.getParameter("quantity"));
			cart = new HashMap<String, Integer>();
			cart.put(req.getParameter("prod_no"), Integer.parseInt(req.getParameter("quantity")));
			session.setAttribute("cart", cart);
		}else {
			// 장바구니에 같은 상품을 넣으면 + 연산을 해보자.
			String prodNo = req.getParameter("prod_no");
			int paramQuantity = Integer.parseInt(req.getParameter("quantity"));

			Integer quantity = cart.get(prodNo);
			int totalQuantity = 0;
			if(quantity != null) {
				totalQuantity = quantity + Integer.parseInt(req.getParameter("quantity"));
			}
			totalQuantity = Integer.parseInt(req.getParameter("quantity"));
			cart.put(req.getParameter("prod_no"), totalQuantity);
			session.setAttribute("cart", cart);
		}
		String servletPath ="/success.jsp";
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(servletPath);
		dispatcher.forward(req, res);
	}
}
