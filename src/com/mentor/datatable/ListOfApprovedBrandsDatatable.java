package com.mentor.datatable;

public class ListOfApprovedBrandsDatatable {
	
	private String brand;
	private String package1;
	private String etin;
	private int sno;
	private double mrp;
	private String registring;
	private int quantity;
	
	
	
	
	public String getPackage1() {
		return package1;
	}
	public void setPackage1(String package1) {
		this.package1 = package1;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	
	public String getRegistring() {
		return registring;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setRegistring(String registring) {
		this.registring = registring;
	}
	public double getMrp() {
		return mrp;
	}
	public String getEtin() {
		return etin;
	}
	public void setEtin(String etin) {
		this.etin = etin;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
}
