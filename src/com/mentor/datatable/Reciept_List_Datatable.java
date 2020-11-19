package com.mentor.datatable;

import java.util.Date;

public class Reciept_List_Datatable {
	private int sno;
	private String passno;
	private String licno;
	private String recievedby;
	private Date recievingdate;
	private int recievedliquor;
	private int plannedBottlesdt;
	private int recievedBottlesdt;
	private String brand;
	
	private String permitnodisp;
	
	
	
	public String getPermitnodisp() {
		return permitnodisp;
	}
	public void setPermitnodisp(String permitnodisp) {
		this.permitnodisp = permitnodisp;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getPlannedBottlesdt() {
		return plannedBottlesdt;
	}
	public void setPlannedBottlesdt(int plannedBottlesdt) {
		this.plannedBottlesdt = plannedBottlesdt;
	}
	public int getRecievedBottlesdt() {
		return recievedBottlesdt;
	}
	public void setRecievedBottlesdt(int recievedBottlesdt) {
		this.recievedBottlesdt = recievedBottlesdt;
	}	
		
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getPassno() {
		return passno;
	}
	public void setPassno(String passno) {
		this.passno = passno;
	}
	public String getLicno() {
		return licno;
	}
	public void setLicno(String licno) {
		this.licno = licno;
	}
	public String getRecievedby() {
		return recievedby;
	}
	public void setRecievedby(String recievedby) {
		this.recievedby = recievedby;
	}
	public Date getRecievingdate() {
		return recievingdate;
	}
	public void setRecievingdate(Date recievingdate) {
		this.recievingdate = recievingdate;
	}
	public int getRecievedliquor() {
		return recievedliquor;
	}
	public void setRecievedliquor(int recievedliquor) {
		this.recievedliquor = recievedliquor;
	}
	
	
}
