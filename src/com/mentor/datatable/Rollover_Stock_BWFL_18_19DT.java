package com.mentor.datatable;

import java.util.ArrayList;

import com.mentor.impl.Rollover_Stock_BWFL_18_19Impl;



public class Rollover_Stock_BWFL_18_19DT {


	Rollover_Stock_BWFL_18_19Impl impl = new Rollover_Stock_BWFL_18_19Impl();
	private int sno;
	private String brand_name;
	private String packg;
	private String size;
	private int box;
	private double mrp;
	private double rollover_fee;
	private double diff_duty;
	private int brand_id;
	private int packg_id;
	private int packg_type;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getPackg() {
		return packg;
	}
	public void setPackg(String packg) {
		this.packg = packg;
	}

	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getBox() {
		return box;
	}
	public void setBox(int box) {
		this.box = box;
	}
	public double getMrp() {
		return mrp;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
	public double getRollover_fee() {
		return rollover_fee;
	}
	public void setRollover_fee(double rollover_fee) {
		this.rollover_fee = rollover_fee;
	}
	public double getDiff_duty() {
		return diff_duty;
	}
	public void setDiff_duty(double diff_duty) {
		this.diff_duty = diff_duty;
	}
	public int getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}
	public int getPackg_id() {
		return packg_id;
	}
	public void setPackg_id(int packg_id) {
		this.packg_id = packg_id;
	}
	public int getPackg_type() {
		return packg_type;
	}
	public void setPackg_type(int packg_type) {
		this.packg_type = packg_type;
	}
	
	private ArrayList brandPackagingData_BrandList=new ArrayList();
	private ArrayList brandPackagingData_PackagingList=new ArrayList();
	private String brandPackagingData_Brand;
	private String brandPackagingData_Packaging;
	private String brandPackagingData_Quantity;
	private int s_no;
	private int s_box;
	private double s_mrp;
	private double s_rollover_fee;
	private double s_diff_duty;
	
	
	
	
	public int getS_no() {
		return s_no;
	}
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
	public int getS_box() {
		return s_box;
	}
	public void setS_box(int s_box) {
		this.s_box = s_box;
	}
	public double getS_mrp() {
		return s_mrp;
	}
	public void setS_mrp(double s_mrp) {
		this.s_mrp = s_mrp;
	}
	public double getS_rollover_fee() {
		return s_rollover_fee;
	}
	public void setS_rollover_fee(double s_rollover_fee) {
		this.s_rollover_fee = s_rollover_fee;
	}
	public double getS_diff_duty() {
		return s_diff_duty;
	}
	public void setS_diff_duty(double s_diff_duty) {
		this.s_diff_duty = s_diff_duty;
	}
	public String getBrandPackagingData_Brand() {
		return brandPackagingData_Brand;
	}
	public void setBrandPackagingData_Brand(String brandPackagingData_Brand) {
		this.brandPackagingData_Brand = brandPackagingData_Brand;
	}
	
	public String getBrandPackagingData_Packaging() {
		return brandPackagingData_Packaging;
	}
	public void setBrandPackagingData_Packaging(String brandPackagingData_Packaging) {
		this.brandPackagingData_Packaging = brandPackagingData_Packaging;
	}
	public ArrayList getBrandPackagingData_BrandList() {
		this.brandPackagingData_BrandList=impl.getBrandName();
		return brandPackagingData_BrandList;
	}
	public void setBrandPackagingData_BrandList(
			ArrayList brandPackagingData_BrandList) {
		this.brandPackagingData_BrandList = brandPackagingData_BrandList;
	}
	public ArrayList getBrandPackagingData_PackagingList() {
		if(this.brandPackagingData_Brand !=null){	
		this.brandPackagingData_PackagingList=impl.getPackagingName(brandPackagingData_Brand);
		}
		return brandPackagingData_PackagingList;
	}
	public void setBrandPackagingData_PackagingList(
			ArrayList brandPackagingData_PackagingList) {
		this.brandPackagingData_PackagingList = brandPackagingData_PackagingList;
	}
	
public String getBrandPackagingData_Quantity() {
		
		if(this.brandPackagingData_Packaging !=null && this.brandPackagingData_Brand !=null)
		{
	
		
			this.brandPackagingData_Quantity=impl.getqty(brandPackagingData_Brand,brandPackagingData_Packaging);
			
		
		}
		
		return brandPackagingData_Quantity;
	}
	public void setBrandPackagingData_Quantity(String brandPackagingData_Quantity) {
		this.brandPackagingData_Quantity = brandPackagingData_Quantity;
	}
	private String buttn_flg;
	private String licence_no;
	private String licence_type;
	

	public String getButtn_flg() {
		return buttn_flg;
	}
	public void setButtn_flg(String buttn_flg) {
		this.buttn_flg = buttn_flg;
	}
	public String getLicence_no() {
		return licence_no;
	}
	public void setLicence_no(String licence_no) {
		this.licence_no = licence_no;
	}
	public String getLicence_type() {
		return licence_type;
	}
	public void setLicence_type(String licence_type) {
		this.licence_type = licence_type;
	}
	
	private int bottles;

	public int getBottles() {
		return bottles;
	}
	public void setBottles(int bottles) {
		this.bottles = bottles;
	}
	private String excel_flg;
	private String excel_flg_dis;

	public String getExcel_flg() {
		return excel_flg;
	}
	public void setExcel_flg(String excel_flg) {
		this.excel_flg = excel_flg;
	}
	public String getExcel_flg_dis() {
		return excel_flg_dis;
	}
	public void setExcel_flg_dis(String excel_flg_dis) {
		this.excel_flg_dis = excel_flg_dis;
	}
	
private String finalizedDateString;
private int planid;

	
	public int getPlanid() {
	return planid;
}
public void setPlanid(int planid) {
	this.planid = planid;
}
	public String getFinalizedDateString() {
		return finalizedDateString;
	}
	public void setFinalizedDateString(String finalizedDateString) {
		this.finalizedDateString = finalizedDateString;
	}
	
	private int quantity_ml;

	public int getQuantity_ml() {
		return quantity_ml;
	}
	public void setQuantity_ml(int quantity_ml) {
		this.quantity_ml = quantity_ml;
	}
	private String gtin_no;

	public String getGtin_no() {
		return gtin_no;
	}
	public void setGtin_no(String gtin_no) {
		this.gtin_no = gtin_no;
	}
	private double cowcess;

	public double getCowcess() {
		return cowcess;
	}
	public void setCowcess(double cowcess) {
		this.cowcess = cowcess;
	}
	private double s_cowcess;

	public double getS_cowcess() {
		return s_cowcess;
	}
	public void setS_cowcess(double s_cowcess) {
		this.s_cowcess = s_cowcess;
	}


	
	
	
}
