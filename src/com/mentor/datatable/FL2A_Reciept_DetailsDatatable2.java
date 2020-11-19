package com.mentor.datatable;

import java.util.Date;

public class FL2A_Reciept_DetailsDatatable2 {

	private int brewery_id;
	private int distillery_id;
	private int int_brand_id;
	private int int_pack_id;
	private int int_quantity;
	private int int_planned_bottles=0;
	private int int_boxes;
	private int int_liquor_type;
	private String vch_license_type;
	private Date plan_dt;
	private String licence_no;
	private Date cr_date;
	private Date finalized_date;
	private String finalized_flag;
	private int slno;	
	private String received_liqour;
	private String received_from_usr;	
	private int recivecases;
	private int noOfBottle;
	private int size;
	private String permitno;
	private String brand;
	private int passno;

	
	public int getPassno() {
		return passno;
	}
	public void setPassno(int passno) {
		this.passno = passno;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getPermitno() {
			return permitno;
		}
		public void setPermitno(String permitno) {
			this.permitno = permitno;
		}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getRecivecases() {
		return recivecases;
	}
	public void setRecivecases(int recivecases) {
		this.recivecases = recivecases;
	}
	public int getNoOfBottle() {		
		this.noOfBottle = this.int_planned_bottles-this.recivecases;
		return noOfBottle;
	}
	public void setNoOfBottle(int noOfBottle) {
		this.noOfBottle = noOfBottle;
	}
	
	public String getReceived_liqour() {
		return received_liqour;
	}
	public void setReceived_liqour(String received_liqour) {
		this.received_liqour = received_liqour;
	}
	public String getReceived_from_usr() {
		return received_from_usr;
	}
	public void setReceived_from_usr(String received_from_usr) {
		this.received_from_usr = received_from_usr;
	}
	public int getSlno() {
		return slno;
	}
	public void setSlno(int slno) {
		this.slno = slno;
	}
	public int getBrewery_id() {
		return brewery_id;
	}
	public void setBrewery_id(int brewery_id) {
		this.brewery_id = brewery_id;
	}
	public int getDistillery_id() {
		return distillery_id;
	}
	public void setDistillery_id(int distillery_id) {
		this.distillery_id = distillery_id;
	}
	public int getInt_brand_id() {
		return int_brand_id;
	}
	public void setInt_brand_id(int int_brand_id) {
		this.int_brand_id = int_brand_id;
	}
	public int getInt_pack_id() {
		return int_pack_id;
	}
	public void setInt_pack_id(int int_pack_id) {
		this.int_pack_id = int_pack_id;
	}
	public int getInt_quantity() {
		return int_quantity;
	}
	public void setInt_quantity(int int_quantity) {
		this.int_quantity = int_quantity;
	}
	public int getInt_planned_bottles() {
		return int_planned_bottles;
	}
	public void setInt_planned_bottles(int int_planned_bottles) {
		this.int_planned_bottles = int_planned_bottles;
	}
	public int getInt_boxes() {
		return int_boxes;
	}
	public void setInt_boxes(int int_boxes) {
		this.int_boxes = int_boxes;
	}
	public int getInt_liquor_type() {
		return int_liquor_type;
	}
	public void setInt_liquor_type(int int_liquor_type) {
		this.int_liquor_type = int_liquor_type;
	}
	public String getVch_license_type() {
		return vch_license_type;
	}
	public void setVch_license_type(String vch_license_type) {
		this.vch_license_type = vch_license_type;
	}
	public Date getPlan_dt() {
		return plan_dt;
	}
	public void setPlan_dt(Date plan_dt) {
		this.plan_dt = plan_dt;
	}
	public String getLicence_no() {
		return licence_no;
	}
	public void setLicence_no(String licence_no) {
		this.licence_no = licence_no;
	}
	public Date getCr_date() {
		return cr_date;
	}
	public void setCr_date(Date cr_date) {
		this.cr_date = cr_date;
	}
	public Date getFinalized_date() {
		return finalized_date;
	}
	public void setFinalized_date(Date finalized_date) {
		this.finalized_date = finalized_date;
	}
	public String getFinalized_flag() {
		return finalized_flag;
	}
	public void setFinalized_flag(String finalized_flag) {
		this.finalized_flag = finalized_flag;
	}

}
