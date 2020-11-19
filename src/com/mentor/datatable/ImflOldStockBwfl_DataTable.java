package com.mentor.datatable;

import java.util.Date;

public class ImflOldStockBwfl_DataTable {

	private String draftePdfName;
	private String pdfName;
	private boolean printFlag;
	private int breakage;
	private int slno;
	private int int_brand_id;
	private int int_pckg_id;
	private int size;
	private String vch_brand;
	private String batchNo;
	private int boxAvailable = 0;
	private int int_bottle_avaliable = 0;
	private int dispatchBoxes;
	public double db_duty = 0;
	public double db_add_duty = 0;
	public double calculated_duty;
	public double calculated_add_duty;
	public int int_dispatch;
	private String pckg_name;
	private int balance = 0;
	private int dispatchbottls;
	
	public int getDispatchbottls() {
		this.dispatchbottls=(this.size*this.dispatchBoxes)-this.breakage;
		return dispatchbottls;
	}

	public void setDispatchbottls(int dispatchbottls) {
		this.dispatchbottls = dispatchbottls;
	}

	public boolean isPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(boolean printFlag) {
		this.printFlag = printFlag;
	}

	public String getPdfName() {
		return pdfName;
	}

	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}

	public String getDraftePdfName() {
		return draftePdfName;
	}

	public void setDraftePdfName(String draftePdfName) {
		this.draftePdfName = draftePdfName;
	}

	public int getBreakage() {
		return breakage;
	}

	public void setBreakage(int breakage) {
		this.breakage = breakage;
	}

	public int getBalance() 
	{
		if (this.int_bottle_avaliable > 0) 
		{
		this.balance = this.int_bottle_avaliable - this.breakage;
	    }
		return balance;
	
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getSlno() {
		return slno;
	}

	public void setSlno(int slno) {
		this.slno = slno;
	}

	public int getInt_brand_id() {
		return int_brand_id;
	}

	public void setInt_brand_id(int int_brand_id) {
		this.int_brand_id = int_brand_id;
	}

	public int getInt_pckg_id() {
		return int_pckg_id;
	}

	public void setInt_pckg_id(int int_pckg_id) {
		this.int_pckg_id = int_pckg_id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getVch_brand() {
		return vch_brand;
	}

	public void setVch_brand(String vch_brand) {
		this.vch_brand = vch_brand;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public int getBoxAvailable() {
		return boxAvailable;
	}

	public void setBoxAvailable(int boxAvailable) {
		this.boxAvailable = boxAvailable;
	}

	public int getInt_bottle_avaliable() {
		return int_bottle_avaliable;
	}

	public void setInt_bottle_avaliable(int int_bottle_avaliable) {
		this.int_bottle_avaliable = int_bottle_avaliable;
	}

	public int getDispatchBoxes() {
		return dispatchBoxes;
	}

	public void setDispatchBoxes(int dispatchBoxes) {
		this.dispatchBoxes = dispatchBoxes;
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

	public double getCalculated_duty() {

		this.calculated_duty = this.dispatchbottls * this.db_duty;

		return calculated_duty;
	}

	public void setCalculated_duty(double calculated_duty) {
		this.calculated_duty = calculated_duty;
	}

	public double getCalculated_add_duty() {

		this.calculated_add_duty = this.dispatchbottls * this.db_add_duty;

		return calculated_add_duty;
	}

	public void setCalculated_add_duty(double calculated_add_duty) {
		this.calculated_add_duty = calculated_add_duty;
	}

	public int getInt_dispatch() {

		this.int_dispatch = this.dispatchBoxes * this.size;

		return int_dispatch;
	}

	public void setInt_dispatch(int int_dispatch) {
		this.int_dispatch = int_dispatch;
	}

	public String getPckg_name() {
		return pckg_name;
	}

	public void setPckg_name(String pckg_name) {
		this.pckg_name = pckg_name;
	}

	private int sno;
	private int bwflId;
	private String vch_bwfl_name;
	private String vch_bwfl_address;
	private String vch_gatepass_no;
	private Date dt_date;
	private String vch_from;
	private String vch_to;
	private String vch_from_lic_no;
	private String vch_to_lic_no;
	private String vch_bond;
	private Date curr_date;
	private int int_max_id;
	private double db_total_duty;
	private double db_total_additional_duty;
	private int findispatchedbox = 0;
	private int findispatchedbttl = 0;
	private String gtinno = "";

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	
	
	

	public int getBwflId() {
		return bwflId;
	}

	public void setBwflId(int bwflId) {
		this.bwflId = bwflId;
	}

	public String getVch_bwfl_name() {
		return vch_bwfl_name;
	}

	public void setVch_bwfl_name(String vch_bwfl_name) {
		this.vch_bwfl_name = vch_bwfl_name;
	}

	public String getVch_bwfl_address() {
		return vch_bwfl_address;
	}

	public void setVch_bwfl_address(String vch_bwfl_address) {
		this.vch_bwfl_address = vch_bwfl_address;
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

	public double getDb_total_duty() {
		return db_total_duty;
	}

	public void setDb_total_duty(double db_total_duty) {
		this.db_total_duty = db_total_duty;
	}

	public double getDb_total_additional_duty() {
		return db_total_additional_duty;
	}

	public void setDb_total_additional_duty(double db_total_additional_duty) {
		this.db_total_additional_duty = db_total_additional_duty;
	}

	public int getFindispatchedbox() {
		return findispatchedbox;
	}

	public void setFindispatchedbox(int findispatchedbox) {
		this.findispatchedbox = findispatchedbox;
	}

	public int getFindispatchedbttl() {
		return findispatchedbttl;
	}

	public void setFindispatchedbttl(int findispatchedbttl) {
		this.findispatchedbttl = findispatchedbttl;
	}

	public String getGtinno() {
		return gtinno;
	}

	public void setGtinno(String gtinno) {
		this.gtinno = gtinno;
	}

	// =========================for print report=============================

	private boolean finalizePrint;
	private boolean printDraft;
	public String PdfDraft1;
	private boolean draftprintFlag;
	private boolean draftprintFlag1;
	private String pdfNameDt1;
	private boolean myFlagDt;
	private boolean myFlagDt1;

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

	public String getPdfDraft1() {
		return PdfDraft1;
	}

	public void setPdfDraft1(String pdfDraft1) {
		PdfDraft1 = pdfDraft1;
	}

	public boolean isDraftprintFlag() {
		return draftprintFlag;
	}

	public void setDraftprintFlag(boolean draftprintFlag) {
		this.draftprintFlag = draftprintFlag;
	}

	public boolean isDraftprintFlag1() {
		return draftprintFlag1;
	}

	public void setDraftprintFlag1(boolean draftprintFlag1) {
		this.draftprintFlag1 = draftprintFlag1;
	}

	public String getPdfNameDt1() {
		return pdfNameDt1;
	}

	public void setPdfNameDt1(String pdfNameDt1) {
		this.pdfNameDt1 = pdfNameDt1;
	}

	public boolean isMyFlagDt() {
		return myFlagDt;
	}

	public void setMyFlagDt(boolean myFlagDt) {
		this.myFlagDt = myFlagDt;
	}

	public boolean isMyFlagDt1() {
		return myFlagDt1;
	}

	public void setMyFlagDt1(boolean myFlagDt1) {
		this.myFlagDt1 = myFlagDt1;
	}
	
	
	//-----------------------scan and upload excel---------------------------
	
	
	
	private String gatepass;
	private String casecode;
	public String cascodeMatching;

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

	public String getCascodeMatching() {
		return cascodeMatching;
	}

	public void setCascodeMatching(String cascodeMatching) {
		this.cascodeMatching = cascodeMatching;
	}
	
	

}
