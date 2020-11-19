package com.mentor.datatable;

import java.util.Date;

public class Hbr_Advance_Duty_RegisterDataTable {
	
	private int srNo;
	private double challan_amount;
	private double dispatch_duty;
	private double balance;
	private String discription;
	private Date date;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getSrNo() {
		return srNo;
	}
	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	public double getChallan_amount() {
		return challan_amount;
	}
	public void setChallan_amount(double challan_amount) {
		this.challan_amount = challan_amount;
	}
	public double getDispatch_duty() {
		return dispatch_duty;
	}
	public void setDispatch_duty(double dispatch_duty) {
		this.dispatch_duty = dispatch_duty;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}

}
