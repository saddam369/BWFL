package com.mentor.datatable;

import java.util.ArrayList;
import com.mentor.impl.BWFLBrandRegistrationImpl;

public class BWFL_BrandDatatable {


	private int sno;
	private String desc_id;
	private String description_addrow;
	private String package_name;
	private String quantity;
	private String package_type;
	public String mrp;
	private String edp;
	private String duty;
	private String addDuty;
	private String wsMargin;
	private String retMargin;
	private String gct;
	private int box_id;
	private ArrayList box_size_list;
	private boolean disableFlag;
	private ArrayList quantityList;

	private double mrp1;
	private double edp1;
	private double duty1;
	private double addDuty1;
	private double wsMargin1;
	private double retMargin1;
	
	
	
	
	public double getMrp1() {
		return mrp1;
	}

	public void setMrp1(double mrp1) {
		this.mrp1 = mrp1;
	}

	public double getEdp1() {
		return edp1;
	}

	public void setEdp1(double edp1) {
		this.edp1 = edp1;
	}

	public double getDuty1() {
		return duty1;
	}

	public void setDuty1(double duty1) {
		this.duty1 = duty1;
	}

	public double getAddDuty1() {
		return addDuty1;
	}

	public void setAddDuty1(double addDuty1) {
		this.addDuty1 = addDuty1;
	}

	public double getWsMargin1() {
		return wsMargin1;
	}

	public void setWsMargin1(double wsMargin1) {
		this.wsMargin1 = wsMargin1;
	}

	public double getRetMargin1() {
		return retMargin1;
	}

	public void setRetMargin1(double retMargin1) {
		this.retMargin1 = retMargin1;
	}

	public ArrayList getQuantityList() {
		BWFLBrandRegistrationImpl impl = new BWFLBrandRegistrationImpl();
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

	public String getGct() {
		return gct;
	}

	public void setGct(String gct) {
		this.gct = gct;
	}

	public int getBox_id() {
		return box_id;
	}

	public void setBox_id(int box_id) {
		this.box_id = box_id;
	}

	public ArrayList getBox_size_list() {
		BWFLBrandRegistrationImpl impl = new BWFLBrandRegistrationImpl();
		// this.box_size_list = impl.getBoxSize(this,quantity);
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

	public String getEdp() {
		return edp;
	}

	public void setEdp(String edp) {
		this.edp = edp;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getAddDuty() {
		return addDuty;
	}

	public void setAddDuty(String addDuty) {
		this.addDuty = addDuty;
	}

	public String getWsMargin() {
		return wsMargin;
	}

	public void setWsMargin(String wsMargin) {
		this.wsMargin = wsMargin;
	}

	public String getRetMargin() {
		return retMargin;
	}

	public void setRetMargin(String retMargin) {
		this.retMargin = retMargin;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
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
