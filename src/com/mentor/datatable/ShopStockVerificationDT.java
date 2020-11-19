package com.mentor.datatable;

import java.util.Date;

public class ShopStockVerificationDT {

	private int srNo;
	private int srNo1;
	private String brand;
	private int size;
	private int no_of_box;
	private int total_no_of_bottles;
	private String challan_no;
	private Date challan_date;
	private String fees_type;
	private double amount;
	private String etin;
	
	public String getEtin() {
		return etin;
	}
	public void setEtin(String etin) {
		this.etin = etin;
	}
	public int getSrNo() {
		return srNo;
	}
	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	public int getSrNo1() {
		return srNo1;
	}
	public void setSrNo1(int srNo1) {
		this.srNo1 = srNo1;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getNo_of_box() {
		return no_of_box;
	}
	public void setNo_of_box(int no_of_box) {
		this.no_of_box = no_of_box;
	}
	public int getTotal_no_of_bottles() {
		return total_no_of_bottles;
	}
	public void setTotal_no_of_bottles(int total_no_of_bottles) {
		this.total_no_of_bottles = total_no_of_bottles;
	}
	public String getChallan_no() {
		return challan_no;
	}
	public void setChallan_no(String challan_no) {
		this.challan_no = challan_no;
	}
	public Date getChallan_date() {
		return challan_date;
	}
	public void setChallan_date(Date challan_date) {
		this.challan_date = challan_date;
	}
	public String getFees_type() {
		return fees_type;
	}
	public void setFees_type(String fees_type) {
		this.fees_type = fees_type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
