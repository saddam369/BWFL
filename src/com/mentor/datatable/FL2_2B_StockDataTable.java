package com.mentor.datatable;

import java.util.Date;

public class FL2_2B_StockDataTable {//new datatable
	public int int_brand_id;
	public String vch_brand;
	public int int_package_ml;
	public int int_bottle_reciv = 0;
	public int int_pckg_id;
	public String vch_pakg_Name;
	private int boxreciv;
	private int breakage = 0;
	private String batchNo;
	private String gatepasss;
	private int slno;
	private int recivTotalBottel = 0;
	private int size;

	
	
	
	public String getGatepasss() {
		return gatepasss;
	}

	public void setGatepasss(String gatepasss) {
		this.gatepasss = gatepasss;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getRecivTotalBottel() {
		this.recivTotalBottel = this.int_bottle_reciv - this.breakage;
		return recivTotalBottel;
	}

	public void setRecivTotalBottel(int recivTotalBottel) {
		this.recivTotalBottel = recivTotalBottel;
	}

	public String getVch_pakg_Name() {
		return vch_pakg_Name;
	}

	public void setVch_pakg_Name(String vch_pakg_Name) {
		this.vch_pakg_Name = vch_pakg_Name;
	}

	public int getInt_brand_id() {
		return int_brand_id;
	}

	public void setInt_brand_id(int int_brand_id) {
		this.int_brand_id = int_brand_id;
	}

	public String getVch_brand() {
		return vch_brand;
	}

	public void setVch_brand(String vch_brand) {
		this.vch_brand = vch_brand;
	}

	public int getInt_package_ml() {
		return int_package_ml;
	}

	public void setInt_package_ml(int int_package_ml) {
		this.int_package_ml = int_package_ml;
	}

	public int getInt_bottle_reciv() {
		return int_bottle_reciv;
	}

	public void setInt_bottle_reciv(int int_bottle_reciv) {
		this.int_bottle_reciv = int_bottle_reciv;
	}

	public int getInt_pckg_id() {
		return int_pckg_id;
	}

	public void setInt_pckg_id(int int_pckg_id) {
		this.int_pckg_id = int_pckg_id;
	}

	public int getBoxreciv() {
		return boxreciv;
	}

	public void setBoxreciv(int boxreciv) {
		this.boxreciv = boxreciv;
	}

	public int getBreakage() {
		return breakage;
	}

	public void setBreakage(int breakage) {
		this.breakage = breakage;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public int getSlno() {
		return slno;
	}

	public void setSlno(int slno) {
		this.slno = slno;
	}

	// ======================= show data table -------------

	private int sno_data_table;
	private Date creatDate_data_table;
	public String vch_brand_data_table;
	public int int_bottle_reciv_data_table = 0;
	private int breakage_data_table = 0;
	private int recivTotalBottel_data_table = 0;

	public Date getCreatDate_data_table() {
		return creatDate_data_table;
	}

	public void setCreatDate_data_table(Date creatDate_data_table) {
		this.creatDate_data_table = creatDate_data_table;
	}

	public int getSno_data_table() {
		return sno_data_table;
	}

	public void setSno_data_table(int sno_data_table) {
		this.sno_data_table = sno_data_table;
	}

	public String getVch_brand_data_table() {
		return vch_brand_data_table;
	}

	public void setVch_brand_data_table(String vch_brand_data_table) {
		this.vch_brand_data_table = vch_brand_data_table;
	}

	public int getInt_bottle_reciv_data_table() {
		return int_bottle_reciv_data_table;
	}

	public void setInt_bottle_reciv_data_table(int int_bottle_reciv_data_table) {
		this.int_bottle_reciv_data_table = int_bottle_reciv_data_table;
	}

	public int getBreakage_data_table() {
		return breakage_data_table;
	}

	public void setBreakage_data_table(int breakage_data_table) {
		this.breakage_data_table = breakage_data_table;
	}

	public int getRecivTotalBottel_data_table() {
		return recivTotalBottel_data_table;
	}

	public void setRecivTotalBottel_data_table(int recivTotalBottel_data_table) {
		this.recivTotalBottel_data_table = recivTotalBottel_data_table;
	}
	//--------------------------------------------gate pass and case code----------
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
