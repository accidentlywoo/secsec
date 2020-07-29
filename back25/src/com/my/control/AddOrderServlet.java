package com.my.control;

import java.io.IOException;
import java.util.ArrayList;
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
		System.out.println(req.getParameter("prod_no"));
		HttpSession session = req.getSession();
		
		String id = (String)session.getAttribute("loginInfo");
		Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
		
		String[] prodNoList = req.getParameterValues("prod_no");
		System.out.println(prodNoList);
		String servletPath;
		if(id == null || "".equals(id)) {
			servletPath = "/fail.jsp";
//			throw new AddException("로그인 되지않은 사용자입니다.");
			req.setAttribute("msg", "로그인 하지 않은 사용자입니다.");
		}else {
			servletPath = "/success.jsp";
			OrderInfo orderInfo = new OrderInfo();
			Customer customer = null;
			List<OrderLine>  orderList = new ArrayList<OrderLine>(); 
			OrderLine orderLine = null;
			Product product = null;
			try {
				for(String key : cart.keySet()) {
					product = productService.findByNo(key);
					orderLine.setOrder_p(product);
					orderLine.setOrder_quantity(cart.get(key));
					orderList.add(orderLine);
				}
				customer = customerService.findById(id);
				orderInfo.setLines(orderList);
				orderInfo.setOrder_c(customer);
				
			} catch (FindException e) {
				e.getMessage();
			}
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(servletPath);
		dispatcher.forward(req, res);
	}
}
