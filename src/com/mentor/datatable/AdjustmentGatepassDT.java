package com.mentor.datatable;

import java.util.Date;

public class AdjustmentGatepassDT {

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
	private int actboxe;
	private int int_bottle_avaliableact;
	private String permit;
	private int dispatcBotlsDt;
	private int brandIdDt;
	private int pckgIdDt;
	private int seqDt;
	private String barndname;
	private int upsize;
	private String uppermit;
	private int brbottle;
	private String brandname;
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public int getUpsize() {
		return upsize;
	}
	public void setUpsize(int upsize) {
		this.upsize = upsize;
	}
	public String getUppermit() {
		return uppermit;
	}
	public void setUppermit(String uppermit) {
		this.uppermit = uppermit;
	}
	public int getBrbottle() {
		return brbottle;
	}
	public void setBrbottle(int brbottle) {
		this.brbottle = brbottle;
	}
	public String getBarndname() {
		return barndname;
	}
	public void setBarndname(String barndname) {
		this.barndname = barndname;
	}
	public int getUpbottle() {
		return upbottle;
	}
	public void setUpbottle(int upbottle) {
		this.upbottle = upbottle;
	}
	public int getUpbox() {
		return upbox;
	}
	public void setUpbox(int upbox) {
		this.upbox = upbox;
	}
	public Date getUpdate() {
		return update;
	}
	public void setUpdate(Date update) {
		this.update = update;
	}
	private int upbottle;
	private int upbox;
	private Date update;
	
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
	private Date plan_dt;
	
	public Date getPlan_dt() {
		return plan_dt;
	}
	public void setPlan_dt(Date plan_dt) {
		this.plan_dt = plan_dt;
	}
	public String getPermit() {
		return permit;
	}
	public void setPermit(String permit) {
		this.permit = permit;
	}
	public int getInt_bottle_avaliableact() {
		
		if(this.actboxe>0){
			this.int_bottle_avaliableact=this.actboxe *size;
			}
		return int_bottle_avaliableact;
	}
	public void setInt_bottle_avaliableact(int int_bottle_avaliableact) {
		this.int_bottle_avaliableact = int_bottle_avaliableact;
	}
	public int getActboxe() {
		
		return actboxe;
	}
	public void setActboxe(int actboxe) {
		this.actboxe = actboxe;
	}
	private int sno;
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
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
	public int getInt_bottle_avaliable() {
		
		
		
		return int_bottle_avaliable;
	}
	public void setInt_bottle_avaliable(int int_bottle_avaliable) {
		this.int_bottle_avaliable = int_bottle_avaliable;
	}
	public int getBalance() {
		if (this.actboxe > 0) {
			this.balance = this.int_bottle_avaliableact - this.breakage;
		}
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getInt_pckg_id() {
		return int_pckg_id;
	}
	public void setInt_pckg_id(int int_pckg_id) {
		this.int_pckg_id = int_pckg_id;
	}
	public int getBoxAvailable() {
		return boxAvailable;
	}
	public void setBoxAvailable(int boxAvailable) {
		this.boxAvailable = boxAvailable;
	}
	public int getBreakage() {
		return breakage;
	}
	public void setBreakage(int breakage) {
		this.breakage = breakage;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public boolean isSelect() {
		return select;
	}
	public void setSelect(boolean select) {
		this.select = select;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public int getDispatchbox() {
		return dispatchbox;
	}
	public void setDispatchbox(int dispatchbox) {
		this.dispatchbox = dispatchbox;
	}
	public int getDispatchbottls() {
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
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
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
		return calculated_duty;
	}
	public void setCalculated_duty(double calculated_duty) {
		this.calculated_duty = calculated_duty;
	}
	public double getCalculated_add_duty() {
		return calculated_add_duty;
	}
	public void setCalculated_add_duty(double calculated_add_duty) {
		this.calculated_add_duty = calculated_add_duty;
	}
	
}
