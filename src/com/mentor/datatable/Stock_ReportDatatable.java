package com.mentor.datatable;

public class Stock_ReportDatatable {
	private int sno;
	private int size;
	private String brand_name;
	private String package_name;
	private String name;
	private int current_stock;	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}

	public int getCurrent_stock() {
		return current_stock;
	}

	public void setCurrent_stock(int current_stock) {
		this.current_stock = current_stock;
	}
}
