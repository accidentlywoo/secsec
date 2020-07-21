package com.my.vo;

public class Employee extends Person{
	private String no;
	private String dept;
	private int salary;
	private double comm;
	
	public Employee() {
		super();
	}
	
	public Employee(String name, String no, int salary) {
		this(name, no, null, salary);
	}

	public Employee(String name, String no, String dept, int salary) {
		this(name, no, dept, salary, 0.0);
	}
	public Employee(String name,String no, String dept, int salary, double comm) {
		//this.setName(name);
		this.name = name;
		this.no = no;
		this.dept = dept;
		this.salary = salary;
		this.comm = comm;
	}
	/**
	 * 실급여를 계산한다 기본급 + (기본급 * 수달률)
	 * @return 실급여를 반환한다
	 */
	public double clacTotalSalary() {
		return salary + (salary * comm);
	}
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public double getComm() {
		return comm;
	}
	public void setComm(double comm) {
		this.comm = comm;
	}
	public void printInfo() {
		//super.printInfo();
		System.out.println("Employee [no=" + no + ", dept=" + dept + ", salary=" + salary + ", comm=" + comm + "]");
	}
	
	@Override
	public String toString() {
		return "Employee [no=" + no + ", dept=" + dept + ", salary=" + salary + ", comm=" + comm + "]";
	}
	//valueof 와  toString의 오버라이딩을 알아보자!
}
