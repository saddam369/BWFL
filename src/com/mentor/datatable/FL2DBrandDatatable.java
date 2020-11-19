package com.mentor.datatable;

import java.util.ArrayList;

import com.mentor.impl.FL2DBrandRegistrationImpl;

public class FL2DBrandDatatable {



	private int sno;
	private String desc_id;
	private String description_addrow;
	private String package_name;
	private String quantity;
	private String package_type;
	
	private int box_id;
	private ArrayList box_size_list;
	private boolean disableFlag;
	private ArrayList quantityList;

	
	private double permit;
	private double mrp1;
	private double duty;
	
	public double getDuty() {
		return duty;
	}

	public void setDuty(double duty) {
		this.duty = duty;
	}


	public double getPermit() {
		return permit;
	}

	public void setPermit(double permit) {
		this.permit = permit;
	}
	
	

private String boxsize_id;
	private ArrayList boxsize_list =new ArrayList();
	



public String getBoxsize_id() {
		return boxsize_id;
	}

	public void setBoxsize_id(String boxsize_id) {
		this.boxsize_id = boxsize_id;
	}

	public ArrayList getBoxsize_list() {
		FL2DBrandRegistrationImpl impl = new FL2DBrandRegistrationImpl();
		this.boxsize_list=impl.boxsizedetail();
		
		return boxsize_list;
	}

	public void setBoxsize_list(ArrayList boxsize_list) {
		this.boxsize_list = boxsize_list;
	}




	

	public double getMrp1() {
		return mrp1;
	}

	public void setMrp1(double mrp1) {
		this.mrp1 = mrp1;
	}

	public ArrayList getQuantityList() {
		FL2DBrandRegistrationImpl impl = new FL2DBrandRegistrationImpl();
		this.quantityList = impl.getQntyDetails(this);
		return quantityList;
	}

	public void setQuantityList(ArrayList quantityList) {
		this.quantityList = quantityList;
	}

	public boolean isDisableFlag() {
		return disableFlag;
	}

	public void setDisableFlag(boolean disableFlag) {
		this.disableFlag = disableFlag;
	}


	public int getBox_id() {
		return box_id;
	}

	public void setBox_id(int box_id) {
		this.box_id = box_id;
	}

	public ArrayList getBox_size_list() {
		FL2DBrandRegistrationImpl impl = new FL2DBrandRegistrationImpl();
		return box_size_list;
	}

	public void setBox_size_list(ArrayList box_size_list) {
		this.box_size_list = box_size_list;
	}

	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}

	public String getPackage_type() {
		return package_type;
	}

	public void setPackage_type(String package_type) {
		this.package_type = package_type;
	}

	
	public String getDescription_addrow() {
		return description_addrow;
	}

	public void setDescription_addrow(String description_addrow) {
		this.description_addrow = description_addrow;
	}

	public String getDesc_id() {
		return desc_id;
	}

	public void setDesc_id(String desc_id) {
		this.desc_id = desc_id;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

}
