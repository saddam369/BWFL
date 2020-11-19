package com.mentor.datatable;

import java.util.ArrayList;

import com.mentor.impl.FL2DBrandRegistrationImpl;

public class FL2D_BrandDetailDataTable {


	
	
	private String brandName;
	public String liquorCategory;
	public ArrayList liq_cat_list=new ArrayList();
	public ArrayList imfl_type_list=new ArrayList();
	private int sno;	
	private String strength_addrow;
	private String imflType;
	private String manufacturerDetails;
	
	
	
	public String getManufacturerDetails() {
		return manufacturerDetails;
	}
	public void setManufacturerDetails(String manufacturerDetails) {
		this.manufacturerDetails = manufacturerDetails;
	}
	public String getImflType() {
		return imflType;
	}
	public void setImflType(String imflType) {
		this.imflType = imflType;
	}		
	public String getStrength_addrow() {
		return strength_addrow;
	}
	public void setStrength_addrow(String strength_addrow) {
		this.strength_addrow = strength_addrow;
	}
		
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public ArrayList getImfl_type_list() {
		this.imfl_type_list = new FL2DBrandRegistrationImpl().liqTypeListImpl(this);
		return imfl_type_list;
	}
	public void setImfl_type_list(ArrayList imfl_type_list) {
		this.imfl_type_list = imfl_type_list;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}	
	public String getLiquorCategory() {
		return liquorCategory;
	}
	public void setLiquorCategory(String liquorCategory) {
		this.liquorCategory = liquorCategory;
	}
	public ArrayList getLiq_cat_list() {
		this.liq_cat_list = new FL2DBrandRegistrationImpl().liqCatListImpl(this);
		return liq_cat_list;
	}
	public void setLiq_cat_list(ArrayList liq_cat_list) {
		this.liq_cat_list = liq_cat_list;
	}
	
}
