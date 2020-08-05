package com.my.vo;

import java.io.Serializable;

public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	//private String name;
	private Postal postal;// 도로명 우편번호 정보
	protected String name;
	private String addr;
	
	public Person() {}
	public Person(String name, String addr) {
		this(null, name, addr);
	}
	
	public Person(Postal postal, String name, String addr) {
		this.postal = postal;
		this.name = name;
		this.addr = addr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Postal getPostal() {
		return postal;
	}
	public void setPostal(Postal postal) {
		this.postal = postal;
	}
	public void printInfo() {
		System.out.println("Person [name=" + name + ", addr=" + addr + "]");
	}
}