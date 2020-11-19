package com.mentor.datatable;

import java.util.Date;

public class PermitWiseStockLeftBWFLDataTable {
	
	private int per_Seq;
	private String per_PermitNo;
	private String per_Brand_Pack_Name;
	private int per_Brand_Id;
	private int per_Pckg_Id;
	private int per_Left_Qty;
	private String per_etin_No;
	private int per_Size;
	private int per_box_Case;
	private Date per_Pland_Date;
	private int per_liquor_type;
	private String per_LicenceNo;
	private int per_Seq_mst_btl_pln;
	private int per_quantity;
	
	
	
	
	
	
	
	
	public int getPer_quantity() {
		return per_quantity;
	}
	public void setPer_quantity(int per_quantity) {
		this.per_quantity = per_quantity;
	}
	public Date getPer_Pland_Date() {
		return per_Pland_Date;
	}
	public void setPer_Pland_Date(Date per_Pland_Date) {
		this.per_Pland_Date = per_Pland_Date;
	}
	public int getPer_liquor_type() {
		return per_liquor_type;
	}
	public void setPer_liquor_type(int per_liquor_type) {
		this.per_liquor_type = per_liquor_type;
	}
	public String getPer_LicenceNo() {
		return per_LicenceNo;
	}
	public void setPer_LicenceNo(String per_LicenceNo) {
		this.per_LicenceNo = per_LicenceNo;
	}
	public int getPer_Seq_mst_btl_pln() {
		return per_Seq_mst_btl_pln;
	}
	public void setPer_Seq_mst_btl_pln(int per_Seq_mst_btl_pln) {
		this.per_Seq_mst_btl_pln = per_Seq_mst_btl_pln;
	}
	public int getPer_Size() {
		return per_Size;
	}
	public void setPer_Size(int per_Size) {
		this.per_Size = per_Size;
	}
	public int getPer_box_Case() {
		return per_box_Case;
	}
	public void setPer_box_Case(int per_box_Case) {
		this.per_box_Case = per_box_Case;
	}
	public int getPer_Seq() {
		return per_Seq;
	}
	public void setPer_Seq(int per_Seq) {
		this.per_Seq = per_Seq;
	}
	public String getPer_PermitNo() {
		return per_PermitNo;
	}
	public void setPer_PermitNo(String per_PermitNo) {
		this.per_PermitNo = per_PermitNo;
	}
	public String getPer_Brand_Pack_Name() {
		return per_Brand_Pack_Name;
	}
	public void setPer_Brand_Pack_Name(String per_Brand_Pack_Name) {
		this.per_Brand_Pack_Name = per_Brand_Pack_Name;
	}
	public int getPer_Brand_Id() {
		return per_Brand_Id;
	}
	public void setPer_Brand_Id(int per_Brand_Id) {
		this.per_Brand_Id = per_Brand_Id;
	}
	public int getPer_Pckg_Id() {
		return per_Pckg_Id;
	}
	public void setPer_Pckg_Id(int per_Pckg_Id) {
		this.per_Pckg_Id = per_Pckg_Id;
	}
	
	
	
	public int getPer_Left_Qty() {
		
		if(this.per_box_Case>0)
		{
			this.per_Left_Qty=this.per_Size*this.per_box_Case;
		}else{
			this.per_Left_Qty=0;
		}
		
		return per_Left_Qty;
	}
	public void setPer_Left_Qty(int per_Left_Qty) {
		this.per_Left_Qty = per_Left_Qty;
	}
	public String getPer_etin_No() {
		return per_etin_No;
	}
	public void setPer_etin_No(String per_etin_No) {
		this.per_etin_No = per_etin_No;
	}
	
	
	//=============================== save after =====================
	
	
	
	
	private int sav_Seq;
	private String sav_PermitNo;
	private String sav_Brand_Pack_Name;
	private int sav_Brand_Id;
	private int sav_Pckg_Id;
	private int sav_Left_Qty;
	private String sav_etin_No;
	private Date sav_Created_Date;
	private int sav_Size;
	private int sav_box_Case;
	private String sav_finlize_Flag;

	
	


	
	
	
	public String getSav_finlize_Flag() {
		return sav_finlize_Flag;
	}
	public void setSav_finlize_Flag(String sav_finlize_Flag) {
		this.sav_finlize_Flag = sav_finlize_Flag;
	}
	public int getSav_Seq() {
		return sav_Seq;
	}
	public void setSav_Seq(int sav_Seq) {
		this.sav_Seq = sav_Seq;
	}
	public String getSav_PermitNo() {
		return sav_PermitNo;
	}
	public void setSav_PermitNo(String sav_PermitNo) {
		this.sav_PermitNo = sav_PermitNo;
	}
	public String getSav_Brand_Pack_Name() {
		return sav_Brand_Pack_Name;
	}
	public void setSav_Brand_Pack_Name(String sav_Brand_Pack_Name) {
		this.sav_Brand_Pack_Name = sav_Brand_Pack_Name;
	}
	public int getSav_Brand_Id() {
		return sav_Brand_Id;
	}
	public void setSav_Brand_Id(int sav_Brand_Id) {
		this.sav_Brand_Id = sav_Brand_Id;
	}
	public int getSav_Pckg_Id() {
		return sav_Pckg_Id;
	}
	public void setSav_Pckg_Id(int sav_Pckg_Id) {
		this.sav_Pckg_Id = sav_Pckg_Id;
	}
	public int getSav_Left_Qty() {
		return sav_Left_Qty;
	}
	public void setSav_Left_Qty(int sav_Left_Qty) {
		this.sav_Left_Qty = sav_Left_Qty;
	}
	public String getSav_etin_No() {
		return sav_etin_No;
	}
	public void setSav_etin_No(String sav_etin_No) {
		this.sav_etin_No = sav_etin_No;
	}
	public Date getSav_Created_Date() {
		return sav_Created_Date;
	}
	public void setSav_Created_Date(Date sav_Created_Date) {
		this.sav_Created_Date = sav_Created_Date;
	}
	public int getSav_Size() {
		return sav_Size;
	}
	public void setSav_Size(int sav_Size) {
		this.sav_Size = sav_Size;
	}
	public int getSav_box_Case() {
		return sav_box_Case;
	}
	public void setSav_box_Case(int sav_box_Case) {
		this.sav_box_Case = sav_box_Case;
	}
	
	
	
	//============================= scn upload =======================================
	
	
	private String gatepass;
	private String casecode;








	public String getGatepass() {
		return gatepass;
	}
	public void setGatepass(String gatepass) {
		this.gatepass = gatepass;
	}
	public String getCasecode() {
		return casecode;
	}
	public void setCasecode(String casecode) {
		this.casecode = casecode;
	}
	
	
	
	
	

}
