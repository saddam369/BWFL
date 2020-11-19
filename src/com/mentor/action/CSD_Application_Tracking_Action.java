package com.mentor.action;

import java.util.*;

import com.mentor.impl.CSD_Application_Tracking_Impl;

public class CSD_Application_Tracking_Action {
 
	CSD_Application_Tracking_Impl impl = new CSD_Application_Tracking_Impl();
	private ArrayList datalist = new ArrayList();
	private String name;
	private String address;
	private String hidden;
	private int int_id;
	private String lic_no;
	private int disttrict_id;
	
	public int getInt_id() {
		return int_id;
	}
	public void setInt_id(int int_id) {
		this.int_id = int_id;
	}
	public String getLic_no() {
		return lic_no;
	}
	public void setLic_no(String lic_no) {
		this.lic_no = lic_no;
	}
	public int getDisttrict_id() {
		return disttrict_id;
	}
	public void setDisttrict_id(int disttrict_id) {
		this.disttrict_id = disttrict_id;
	}
	public String getHidden() {
		impl.getDetail(this);
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	public ArrayList getDatalist() {
		this.datalist = impl.getApplications(this);
		return datalist;
	}
	public void setDatalist(ArrayList datalist) {
		this.datalist = datalist;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
