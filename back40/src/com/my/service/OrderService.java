package com.my.service;

import com.my.dao.OrderDAO;
import com.my.exception.AddException;
import com.my.vo.OrderInfo;

public class OrderService {
	private OrderDAO orderDAO;
	
	public OrderService() {
		orderDAO = new OrderDAO();
	}
	public void add(OrderInfo order) throws AddException {
		orderDAO.insert(order);
	}
}
