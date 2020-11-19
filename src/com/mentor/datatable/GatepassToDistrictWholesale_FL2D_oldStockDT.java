package com.mentor.datatable;

import java.util.Date;

public class GatepassToDistrictWholesale_FL2D_oldStockDT {

	public int int_brand_id;
	public String vch_brand;
	public int int_package_ml;
	public int int_bottle_avaliable = 0;
	private int balance = 0;

	private String desc = "";

	public int int_pckg_id;
	private int boxAvailable;
	private int breakage = 0;
	private int size;
	private boolean select;
	private String batchNo;

	private int dispatchbox;
	private int dispatchbottls;

	private String pdfName;
	private boolean printFlag = false;
	private int seq;

	public double db_duty = 0;
	public double db_add_duty = 0;
	public double db_total_duty = 0;
	public double calculated_duty;
	public double calculated_add_duty;
	public String cascodeMatching;
	public String getCascodeMatching() {
		return cascodeMatching;
	}

	public void setCascodeMatching(String cascodeMatching) {
		this.cascodeMatching = cascodeMatching;
	}
	public double getCalculated_add_duty() {
		this.calculated_add_duty = this.dispatchbottls * this.db_add_duty;

		// System.out.println("calculated additional duty----------------"+this.calculated_add_duty);
		return calculated_add_duty;
	}

	public void setCalculated_add_duty(double calculated_add_duty) {
		this.calculated_add_duty = calculated_add_duty;
	}

	public double getDb_duty() {
		return db_duty;
	}

	public void setDb_duty(double db_duty) {
		this.db_duty = db_duty;
	}

	public double getDb_add_duty() {
		return db_add_duty;
	}

	public void setDb_add_duty(double db_add_duty) {
		this.db_add_duty = db_add_duty;
	}

	public double getDb_total_duty() {
		return db_total_duty;
	}

	public void setDb_total_duty(double db_total_duty) {
		this.db_total_duty = db_total_duty;
	}

	public double getCalculated_duty() {

		this.calculated_duty = this.dispatchbottls * this.db_duty;
		// System.out.println("calculated duty----------------"+this.calculated_duty);

		return calculated_duty;
	}

	public void setCalculated_duty(double calculated_duty) {
		this.calculated_duty = calculated_duty;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getDispatchbox() {
		return dispatchbox;
	}

	public void setDispatchbox(int dispatchbox) {
		this.dispatchbox = dispatchbox;
	}

	public int getDispatchbottls() {
		dispatchbottls = (dispatchbox * size)-breakage;
		return dispatchbottls;
	}

	public void setDispatchbottls(int dispatchbottls) {
		this.dispatchbottls = dispatchbottls;
	}

	public String getPdfName() {
		return pdfName;
	}

	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}

	public boolean isPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(boolean printFlag) {
		this.printFlag = printFlag;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public int getBalance() {
		if (int_bottle_avaliable > 0) {
			balance = int_bottle_avaliable - breakage;
		}
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getBreakage() {
		return breakage;
	}

	public void setBreakage(int breakage) {
		this.breakage = breakage;
	}

	public int getBoxAvailable() {
		return boxAvailable;
	}

	public void setBoxAvailable(int boxAvailable) {
		this.boxAvailable = boxAvailable;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getInt_pckg_id() {
		return int_pckg_id;
	}

	public void setInt_pckg_id(int int_pckg_id) {
		this.int_pckg_id = int_pckg_id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getVch_brand() {
		return vch_brand;
	}

	public void setVch_brand(String vch_brand) {
		this.vch_brand = vch_brand;
	}

	public int getInt_bottle_avaliable() {
		return int_bottle_avaliable;
	}

	public void setInt_bottle_avaliable(int int_bottle_avaliable) {
		this.int_bottle_avaliable = int_bottle_avaliable;
	}

	public int getInt_brand_id() {
		return int_brand_id;
	}

	public void setInt_brand_id(int int_brand_id) {
		this.int_brand_id = int_brand_id;
	}

	public int getInt_package_ml() {
		return int_package_ml;
	}

	public void setInt_package_ml(int int_package_ml) {
		this.int_package_ml = int_package_ml;
	}

	private int sno;
	private int int_dist_id;
	private String vch_distillary_name;
	private String vch_distillary_address;
	private String vch_gatepass_no;
	private Date dt_date;
	private String vch_from;
	private String vch_to;
	private String vch_from_lic_no;
	private String vch_to_lic_no;
	private String vch_bond;
	private Date curr_date;
	private int int_max_id;
	private double db_total_duty_2;
	private double db_total_additional_duty;
	private String gatepassNo;

	public String getGatepassNo() {
		return gatepassNo;
	}

	public void setGatepassNo(String gatepassNo) {
		this.gatepassNo = gatepassNo;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getInt_dist_id() {
		return int_dist_id;
	}

	public void setInt_dist_id(int int_dist_id) {
		this.int_dist_id = int_dist_id;
	}

	public String getVch_distillary_name() {
		return vch_distillary_name;
	}

	public void setVch_distillary_name(String vch_distillary_name) {
		this.vch_distillary_name = vch_distillary_name;
	}

	public String getVch_distillary_address() {
		return vch_distillary_address;
	}

	public void setVch_distillary_address(String vch_distillary_address) {
		this.vch_distillary_address = vch_distillary_address;
	}

	public String getVch_gatepass_no() {
		return vch_gatepass_no;
	}

	public void setVch_gatepass_no(String vch_gatepass_no) {
		this.vch_gatepass_no = vch_gatepass_no;
	}

	public Date getDt_date() {
		return dt_date;
	}

	public void setDt_date(Date dt_date) {
		this.dt_date = dt_date;
	}

	public String getVch_from() {
		return vch_from;
	}

	public void setVch_from(String vch_from) {
		this.vch_from = vch_from;
	}

	public String getVch_to() {
		return vch_to;
	}

	public void setVch_to(String vch_to) {
		this.vch_to = vch_to;
	}

	public String getVch_from_lic_no() {
		return vch_from_lic_no;
	}

	public void setVch_from_lic_no(String vch_from_lic_no) {
		this.vch_from_lic_no = vch_from_lic_no;
	}

	public String getVch_to_lic_no() {
		return vch_to_lic_no;
	}

	public void setVch_to_lic_no(String vch_to_lic_no) {
		this.vch_to_lic_no = vch_to_lic_no;
	}

	public String getVch_bond() {
		return vch_bond;
	}

	public void setVch_bond(String vch_bond) {
		this.vch_bond = vch_bond;
	}

	public Date getCurr_date() {
		return curr_date;
	}

	public void setCurr_date(Date curr_date) {
		this.curr_date = curr_date;
	}

	public int getInt_max_id() {
		return int_max_id;
	}

	public void setInt_max_id(int int_max_id) {
		this.int_max_id = int_max_id;
	}

	public double getDb_total_duty_2() {
		return db_total_duty_2;
	}

	public void setDb_total_duty_2(double db_total_duty_2) {
		this.db_total_duty_2 = db_total_duty_2;
	}

	public double getDb_total_additional_duty() {
		return db_total_additional_duty;
	}

	public void setDb_total_additional_duty(double db_total_additional_duty) {
		this.db_total_additional_duty = db_total_additional_duty;
	}

	// ----------------uploading gatepass------------------

	private String gatepass;
	private String casecode;
	private boolean draftprintFlag;
	private String draftpdfName;
	private boolean finalizePrint;
	private boolean printDraft;

	public String getDraftpdfName() {
		return draftpdfName;
	}

	public void setDraftpdfName(String draftpdfName) {
		this.draftpdfName = draftpdfName;
	}

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

	public boolean isDraftprintFlag() {
		return draftprintFlag;
	}

	public void setDraftprintFlag(boolean draftprintFlag) {
		this.draftprintFlag = draftprintFlag;
	}

	public boolean isFinalizePrint() {
		return finalizePrint;
	}

	public void setFinalizePrint(boolean finalizePrint) {
		this.finalizePrint = finalizePrint;
	}

	public boolean isPrintDraft() {
		return printDraft;
	}

	public void setPrintDraft(boolean printDraft) {
		this.printDraft = printDraft;
	}

	// ---------------------cancel gatepass-----------------------

	private int dispatcBotlsDt;
	private int brandIdDt;
	private int pckgIdDt;
	private int seqDt;
	private double totalDuty;

	public double getTotalDuty() {
		return totalDuty;
	}

	public void setTotalDuty(double totalDuty) {
		this.totalDuty = totalDuty;
	}

	public int getDispatcBotlsDt() {
		return dispatcBotlsDt;
	}

	public void setDispatcBotlsDt(int dispatcBotlsDt) {
		this.dispatcBotlsDt = dispatcBotlsDt;
	}

	public int getBrandIdDt() {
		return brandIdDt;
	}

	public void setBrandIdDt(int brandIdDt) {
		this.brandIdDt = brandIdDt;
	}

	public int getPckgIdDt() {
		return pckgIdDt;
	}

	public void setPckgIdDt(int pckgIdDt) {
		this.pckgIdDt = pckgIdDt;
	}

	public int getSeqDt() {
		return seqDt;
	}

	public void setSeqDt(int seqDt) {
		this.seqDt = seqDt;
	}

}
