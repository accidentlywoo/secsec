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
public class PutCartController implements Controller{
	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<String, Integer>();
			cart.put(request.getParameter("prod_no"), Integer.parseInt(request.getParameter("quantity")));
			session.setAttribute("cart", cart);
		}
		// 장바구니에 같은 상품을 넣으면 + 연산을 해보자.
		String prodNo = request.getParameter("prod_no");
		int paramQuantity = Integer.parseInt(request.getParameter("quantity"));

		Integer quantity = cart.get(prodNo);
		int totalQuantity = 0;
		if (quantity != null) {
			totalQuantity = quantity + Integer.parseInt(request.getParameter("quantity"));
		}
		totalQuantity = Integer.parseInt(request.getParameter("quantity"));
		cart.put(request.getParameter("prod_no"), totalQuantity);
		session.setAttribute("cart", cart);

		String servletPath = "/success.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(servletPath);
		dispatcher.forward(request, response);
		return null;
	}
}
