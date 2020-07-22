package com.my.service;

import com.my.dao.ProductDAO;
import com.my.exception.FindException;
import com.my.vo.Product;

public class ProductServiceImpl {
	private ProductDAO productDAO;
	
	public ProductServiceImpl() {
		productDAO = new ProductDAO();
	}
	public Product findByNo(String prod_no) throws FindException {
		return productDAO.selectByNo(prod_no);
	}
}
