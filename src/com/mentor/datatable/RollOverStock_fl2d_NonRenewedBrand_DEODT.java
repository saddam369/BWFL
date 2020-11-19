package com.mentor.datatable;

import java.util.Date;

public class RollOverStock_fl2d_NonRenewedBrand_DEODT {

	

	private int srno;
	private int brand_id;
	private String brand_name;
	private int package_id;
	private int size;
	private int rolloverbox;
	private int rolloverbottles;
	private double old_duty;
	private double old_adduty;
	private double new_duty;
	private double new_adduty;
	private double diff_duty;
	private int srNo_challan;
	private String challanname;
	private Date challan_date;
	private double mrp;
	private String etin;
	private String new_brand_name;
	private int new_brand_id;
	private int new_package_id;
	private String old_etin;
	private String new_etin;
	private String unit_id;
	private String old_unit_id;
	private String unit_type;
	private String excelPath;
	private String excelFlag;
	
	
	public String getExcelPath() {
		return excelPath;
	}
	public void setExcelPath(String excelPath) {
		this.excelPath = excelPath;
	}
	public String getExcelFlag() {
		return excelFlag;
	}
	public void setExcelFlag(String excelFlag) {
		this.excelFlag = excelFlag;
	}
	public String getUnit_type() {
		return unit_type;
	}
	public void setUnit_type(String unit_type) {
		this.unit_type = unit_type;
	}
	public String getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}
	public String getOld_unit_id() {
		return old_unit_id;
	}
	public void setOld_unit_id(String old_unit_id) {
		this.old_unit_id = old_unit_id;
	}
	public String getOld_etin() {
		return old_etin;
	}
	public void setOld_etin(String old_etin) {
		this.old_etin = old_etin;
	}
	public String getNew_etin() {
		return new_etin;
	}
	public void setNew_etin(String new_etin) {
		this.new_etin = new_etin;
	}
	public int getNew_brand_id() {
		return new_brand_id;
	}
	public int getNew_package_id() {
		return new_package_id;
	}
	public void setNew_brand_id(int new_brand_id) {
		this.new_brand_id = new_brand_id;
	}
	public void setNew_package_id(int new_package_id) {
		this.new_package_id = new_package_id;
	}
	public String getNew_brand_name() {
		return new_brand_name;
	}
	public void setNew_brand_name(String new_brand_name) {
		this.new_brand_name = new_brand_name;
	}
	
	public String getEtin() {
		return etin;
	}
	public void setEtin(String etin) {
		this.etin = etin;
	}
	public double getMrp() {
		return mrp;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
	public Date getChallan_date() {
		return challan_date;
	}
	public void setChallan_date(Date challan_date) {
		this.challan_date = challan_date;
	}
	public String getChallanname() {
		return challanname;
	}
	public void setChallanname(String challanname) {
		this.challanname = challanname;
	}
	public int getSrNo_challan() {
		return srNo_challan;
	}
	public void setSrNo_challan(int srNo_challan) {
		this.srNo_challan = srNo_challan;
	}
	public int getSrno() {
		return srno;
	}
	public void setSrno(int srno) {
		this.srno = srno;
	}
	public int getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public int getPackage_id() {
		return package_id;
	}
	public void setPackage_id(int package_id) {
		this.package_id = package_id;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getRolloverbox() {
		return rolloverbox;
	}
	public void setRolloverbox(int rolloverbox) {
		this.rolloverbox = rolloverbox;
	}
	public int getRolloverbottles() {
		return rolloverbottles;
	}
	public void setRolloverbottles(int rolloverbottles) {
		this.rolloverbottles = rolloverbottles;
	}
	public double getOld_duty() {
		return old_duty;
	}
	public void setOld_duty(double old_duty) {
		this.old_duty = old_duty;
	}
	public double getOld_adduty() {
		return old_adduty;
	}
	public void setOld_adduty(double old_adduty) {
		this.old_adduty = old_adduty;
	}
	public double getNew_duty() {
		return new_duty;
	}
	public void setNew_duty(double new_duty) {
		this.new_duty = new_duty;
	}
	public double getNew_adduty() {
		return new_adduty;
	}
	public void setNew_adduty(double new_adduty) {
		this.new_adduty = new_adduty;
	}
	public double getDiff_duty() {
		return diff_duty;
	}
	public void setDiff_duty(double diff_duty) {
		this.diff_duty = diff_duty;
	}
	
	private int seq;




	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}


	private boolean checkbox;
	private String checkbox_flg;




	public boolean isCheckbox() {
		return checkbox;
	}
	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}
	public String getCheckbox_flg() {
		return checkbox_flg;
	}
	public void setCheckbox_flg(String checkbox_flg) {
		this.checkbox_flg = checkbox_flg;
	}
	

	
	
	
	
	
	
	
	
	
	
	
}
