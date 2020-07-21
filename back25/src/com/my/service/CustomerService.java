package com.my.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.my.dao.CustomerDAO;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.Customer;

public class CustomerService {
	private CustomerDAO customerDAO;
	public CustomerService(String path) {
		Properties env = new Properties();
		try {
			
			env.load(new FileInputStream(path));
			String daoClassName = env.getProperty("dao");
			// 런타임 다이나믹 로드해보기!
			Class<?> clazz = Class.forName(daoClassName);
			// 작업으로 로그한 클래스 타입으로 객체 생성
			Object object = clazz.newInstance(); //public 매개변수 없는 생성자 호출
			// 객체를 인스턴스 변수 dao에 대입
			customerDAO = (CustomerDAO)object;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		//-> properties 파일만 설정값이 변경되면 재 컴파일 없이, 변경 가능.
	}
	public CustomerService(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}
	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
	
	public void add(Customer customer) throws AddException, FindException{
		customerDAO.insert(customer);
	}
	
	public List<Customer> findAll() throws FindException{
		return customerDAO.selectAll();
	}
	
	public Customer findById(String id) throws FindException{ 
		return customerDAO.selectById(id);
	}

	public List<Customer> findByName(String word) throws FindException{ 
		return customerDAO.selectByName(word);
	}
	
	public void login(String id, String pwd) throws FindException{
		try { 
			Customer customer = customerDAO.selectById(id);
			
			if(!customer.getPwd().equals(pwd)) {
				throw new FindException("로그인 실패");
			}

		} catch (FindException e) {
			throw new FindException("로그인 실패");
		}
		System.out.println("로그인 성공");
	}
	public void  modify(Customer customer) throws ModifyException, FindException {
		try {
			customerDAO.update(customer);
		} catch (ModifyException e) {
			throw new ModifyException("정보 수정에 실패했습니다.");
		}
	}
	public void remove(Customer customer) throws RemoveException, FindException {
		try {
			customerDAO.delete(customer.getId());
		} catch (RemoveException e) {
			throw new RemoveException("삭제에 실패했습니다.");
		}
	}
}
