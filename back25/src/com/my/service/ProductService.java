package com.my.service;

import java.util.List;

import com.my.dao.ProductDAO;
import com.my.exception.FindException;
import com.my.vo.Product;

public class ProductService {
	private ProductDAO productDAO;
	
	public ProductService() {
		productDAO = new ProductDAO();
	}
	public Product findByNo(String prod_no) throws FindException {
		return productDAO.selectByNo(prod_no);
	}
	public List<Product> findAll() throws FindException{
		return productDAO.selectAll();
	}
}
