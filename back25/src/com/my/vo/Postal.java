package com.my.vo;

public class Postal {
	private String zipcode;
	private String buildingno;
	private String city;
	private String doro;
	private String building;
	
	
	public Postal() {	}
	public Postal(String zipcode, String buildingno, String city, String doro, String building) {
		this.zipcode = zipcode;
		this.buildingno = buildingno;
		this.city = city;
		this.doro = doro;
		this.building = building;
	}
	public String getZipcode() {
		return zipcode;
	}
	public String getBuildingno() {
		return buildingno;
	}
	public String getCity() {
		return city;
	}
	public String getDoro() {
		return doro;
	}
	public String getBuilding() {
		return building;
	}
	@Override
	public String toString() {
		return "Postal [zipcode=" + zipcode + ", buildingno=" + buildingno + ", city=" + city + ", doro=" + doro
				+ ", building=" + building + "]";
	}
}
