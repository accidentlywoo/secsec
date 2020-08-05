package com.my.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.service.CustomerService;
import com.my.service.OrderService;
import com.my.service.ProductService;
import com.my.vo.Customer;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String realPath;
	private OrderService orderService;
	private CustomerService customerService;
	private ProductService productService;

	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext servletContext = this.getServletContext();
		String customerEnvFileName = servletContext.getInitParameter("customerEnv");
		realPath = servletContext.getRealPath(customerEnvFileName);
		orderService = new OrderService();
		customerService = new CustomerService(realPath);
		productService = new ProductService();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("loginInfo");
		// 장바구니 여러개 중에 하나 주문!
		String[] prodNoList = req.getParameterValues("prod_no");
		Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
		System.out.println("cart  : "+cart );
		String servletPath;
		try {
			if (id == null || "".equals(id)) {
				throw new AddException("로그인 되지않은 사용자입니다.");
			} else {
				OrderInfo orderInfo = new OrderInfo();
				List<OrderLine> orderList = new ArrayList<OrderLine>();
				Product product = null;
				Customer customer = customerService.findById(id);
				for (String item : prodNoList) {
					cart.remove(item);
					product = productService.findByNo(item);
					OrderLine orderLine = new OrderLine();
					orderLine.setOrder_p(product);
					orderLine.setOrder_quantity(cart.get(item));
					orderList.add(orderLine);
				}
				orderInfo.setOrder_c(customer);
				orderInfo.setLines(orderList);
				orderService.add(orderInfo);
			}
			session.setAttribute("cart", cart);
			servletPath = "/success.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(servletPath);
			dispatcher.forward(req, res);
		} catch (AddException | FindException e) {
			servletPath = "/fail.jsp";
			req.setAttribute("errorMsg", "로그인 하지 않은 사용자입니다.");
			RequestDispatcher dispatcher = req.getRequestDispatcher(servletPath);
			dispatcher.forward(req, res);
		}
	}
}
