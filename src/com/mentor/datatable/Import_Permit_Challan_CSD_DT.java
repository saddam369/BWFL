package com.mentor.datatable;

public class Import_Permit_Challan_CSD_DT {

	private int sno;
	private int app_id;
	private double import_fee;
	private double special_fee;
	//private String import_fee_challan_no;
	private String vch_status;
	private boolean checkbox;
	private double excise_duty;
	private double scannFee;
	private double coronaFee;
	
	
	public double getScannFee() {
		return scannFee;
	}
	public void setScannFee(double scannFee) {
		this.scannFee = scannFee;
	}
	public double getCoronaFee() {
		return coronaFee;
	}
	public void setCoronaFee(double coronaFee) {
		this.coronaFee = coronaFee;
	}
	public double getExcise_duty() {
		return excise_duty;
	}
	public void setExcise_duty(double excise_duty) {
		this.excise_duty = excise_duty;
	}
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
