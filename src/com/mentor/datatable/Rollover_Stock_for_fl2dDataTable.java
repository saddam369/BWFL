package com.mentor.datatable;

public class Rollover_Stock_for_fl2dDataTable {
	
	

	private int sno;
	private int srno ;
	
	
	private int box;
	public int getSrno() {
		return srno;
	}
	public void setSrno(int srno) {
		this.srno = srno;
	}
	private int avl_box ;
	private String brand_name;
	public int getAvl_box() {
		return avl_box;
	}
	public void setAvl_box(int avl_box) {
		this.avl_box = avl_box;
	}
	private int size;
	private int avl_bottle;
	private int breakage = 0;
	private int ro_bottle;
	private String package_name;
	private int pckg_id;
	private int brand_id;
	private int stock_box;
	
	
	
	
	public int getStock_box() {
		return stock_box;
	}
	public void setStock_box(int stock_box) {
		this.stock_box = stock_box;
	}
	public int getPckg_id() {
		return pckg_id;
	}
	public void setPckg_id(int pckg_id) {
		this.pckg_id = pckg_id;
	}
	public int getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
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
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getAvl_bottle() {
		return avl_bottle;
	}
	public void setAvl_bottle(int avl_bottle) {
		this.avl_bottle = avl_bottle;
	}
	public int getBox() {
		return box;
	}
	public void setBox(int box) {
		this.box = box;
	}
	public int getBreakage() {
		return breakage;
	}
	public void setBreakage(int breakage) {
		this.breakage = breakage;
	}
	public int getRo_bottle() {
		this.ro_bottle = (this.box * this.size);
		
		return ro_bottle;
	}
	public void setRo_bottle(int ro_bottle) {
		this.ro_bottle = ro_bottle;
	}
	   
	//==================uploader ============================================
	 

	
	   private String etin;
	   private String casecode;


	public String getEtin() {
		return etin;
	}
	public void setEtin(String etin) {
		this.etin = etin;
	}
	public String getCasecode() {
		return casecode;
	}
	public void setCasecode(String casecode) {
		this.casecode = casecode;
	}

	private boolean final_flag = false ;
	public boolean isFinal_flag(){
		
		return final_flag ;
	}
	public void setFinal_flag(boolean final_flag)
	{
		this.final_flag = final_flag ;
	}
	
	private String lic_typ;
	private String lic_no;
	public String getLic_typ() {
		return lic_typ;
	}
	public void setLic_typ(String lic_typ) {
		this.lic_typ = lic_typ;
	}
	public String getLic_no() {
		return lic_no;
	}
	public void setLic_no(String lic_no) {
		this.lic_no = lic_no;
	}
	
	private int seq;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
private boolean com_flg;
    
    public boolean isCom_flg() {
		return com_flg;
	}
	public void setCom_flg(boolean com_flg) {
		this.com_flg = com_flg;
	}

}
