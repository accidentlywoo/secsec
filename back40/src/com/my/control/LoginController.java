package com.my.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.exception.FindException;
import com.my.service.CustomerService;
import com.my.vo.Customer;

public class LoginController implements Controller{
	private CustomerService service ;
	private String realPath;
	public LoginController() {
	}

	public LoginController(String realPath) {
		this.realPath = realPath; // 생성자 주입
		service = new CustomerService(realPath);
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(service == null) {
			service = new CustomerService(realPath);
		}
		System.out.println("in LoginController execute");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		try {
			service.login(id, pwd);
			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", id);
			service.login(id, pwd);
			return  "/success.jsp";
		} catch (FindException e) {
			return "/fail.jsp";
		}
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath; // setter 주입
	}
}
