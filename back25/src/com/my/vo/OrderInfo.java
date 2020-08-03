package com.my.vo;

import java.util.List;

public class OrderInfo {
	private int order_no;
	//private String order_id;
	private Customer order_c;  // one to one [Has A]
	private String order_dt; // java.util.Date사용 java.sql.Date는 util을 상속받은 하위클래스이다.
	private List<OrderLine> lines; // one to many [Has A]
	public OrderInfo() {}
	
	public OrderInfo(Customer order_c) {
		this.order_c = order_c;
	}
	public OrderInfo(int order_no, Customer order_c, String order_dt) {
		this(order_no, order_c,null,order_dt);
	}
	
	public OrderInfo(int order_no, Customer order_c, List<OrderLine> lines) {
		this(order_no, order_c,lines,null);
	}

	public OrderInfo(int order_no, Customer order_c, List<OrderLine> lines, String order_dt) {
		this.order_no = order_no;
		this.order_c = order_c;
		this.order_dt = order_dt;
		this.lines = lines;
	}

	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public Customer getOrder_c() {
		return order_c;
	}
	public void setOrder_c(Customer order_c) {
		this.order_c = order_c;
	}
	public String getOrder_dt() {
		return order_dt;
	}
	public void setOrder_dt(String order_dt) {
		this.order_dt = order_dt;
	}

	public List<OrderLine> getLines() {
		return lines;
	}

	public void setLines(List<OrderLine> lines) {
		this.lines = lines;
	}
}
