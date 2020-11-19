package com.mentor.datatable;

public class ImportChallanForPermitBWFL_FL2DDT {

	private int sno;
	private int app_id;
	private double import_fee;
	private double special_fee;
	//private String import_fee_challan_no;
	private String vch_status;
	private boolean checkbox;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getApp_id() {
		return app_id;
	}
	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}
	public double getImport_fee() {
		return import_fee;
	}
	public void setImport_fee(double import_fee) {
		this.import_fee = import_fee;
	}
	public double getSpecial_fee() {
		return special_fee;
	}
	public void setSpecial_fee(double special_fee) {
		this.special_fee = special_fee;
	}
	/*public String getImport_fee_challan_no() {
		return import_fee_challan_no;
	}
	public void setImport_fee_challan_no(String import_fee_challan_no) {
		this.import_fee_challan_no = import_fee_challan_no;
	}*/
	public String getVch_status() {
		return vch_status;
	}
	public void setVch_status(String vch_status) {
		this.vch_status = vch_status;
	}
	public boolean isCheckbox() {
		return checkbox;
	}
	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}
	
}
