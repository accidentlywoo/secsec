package com.my.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.CustomerDAO;
import com.my.dao.CustomerDAOOracle;
import com.my.exception.FindException;
import com.my.vo.Customer;

/**
 * Servlet implementation class ModelServletTest
 */
public class ModelServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		CustomerDAO dao;
		dao = new CustomerDAOOracle();
		
		try {
			Customer c = dao.selectById("id1");
			System.out.println(c);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
}
