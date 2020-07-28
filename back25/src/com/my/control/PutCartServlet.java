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
		Map<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
		if(cart == null) {
			cart = new HashMap<String, Integer>();
			session.setAttribute("cart", cart);
			cart.put(req.getParameter("prod_no"), Integer.parseInt(req.getParameter("quantity")));
		}else {
			// 장바구니에 같은 상품을 넣으면 + 연산을 해보자.
			int quantity = cart.get("prod_no");
			int totalQuantity = quantity + Integer.parseInt(req.getParameter("quantity"));
			cart.put(req.getParameter("prod_no"), totalQuantity);
		}
		String servletPath ="/success.jsp";
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(servletPath);
		dispatcher.forward(req, res);
	}
}
