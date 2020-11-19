package com.mentor.datatable;

import java.util.Date;

public class GatepassToDistrictBWFLDT {

	public int int_brand_id;
	public String vch_brand;
	public int int_pckg_id;
	private String packageName;
	private int size;
	public int int_bottle_avaliable = 0;
	private int boxAvailable;
	private int breakage = 0;
	private int balance = 0;
	private String batchNo;
	private int dispatchbox;
	private int dispatchbottls;
	private int seq;
	private String licenseNodt;
	public double db_duty = 0;
	public double db_add_duty = 0;
	public double db_total_duty = 0;
	public double calculated_duty = 0;
	public double calculated_add_duty;
	private int slno;
	public double cesh_tot;
	public double cesh;
	
	
	
public double getCesh_tot() {
	cesh_tot=this.dispatchbottls * this.cesh;
		return cesh_tot;
	}

	public void setCesh_tot(double cesh_tot) {
		this.cesh_tot = cesh_tot;
	}

 
	public double getCesh() {
		return cesh;
	}

	public void setCesh(double cesh) {
		this.cesh = cesh;
	}

	public int getSlno() {
		return slno;
	}

	public void setSlno(int slno) {
		this.slno = slno;
	}

	public double getCalculated_add_duty() {

		this.calculated_add_duty = this.dispatchbottls * this.db_add_duty;
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
		return calculated_duty;
	}

	public void setCalculated_duty(double calculated_duty) {
		this.calculated_duty = calculated_duty;
	}

	public String getLicenseNodt() {
		return licenseNodt;
	}

	public void setLicenseNodt(String licenseNodt) {
		this.licenseNodt = licenseNodt;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
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

	public int getInt_pckg_id() {
		return int_pckg_id;
	}

	public void setInt_pckg_id(int int_pckg_id) {
		this.int_pckg_id = int_pckg_id;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getInt_bottle_avaliable() {
		return int_bottle_avaliable;
	}

	public void setInt_bottle_avaliable(int int_bottle_avaliable) {
		this.int_bottle_avaliable = int_bottle_avaliable;
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

	public int getBalance() {
		if (this.int_bottle_avaliable > 0) {
			this.balance = this.int_bottle_avaliable - this.breakage;
		}
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
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
		this.dispatchbottls = this.dispatchbox * this.size;
		return dispatchbottls;
	}

	public void setDispatchbottls(int dispatchbottls) {
		this.dispatchbottls = dispatchbottls;
	}

	private boolean printFlag = false;
	private int serialNo;
	private Date currentDate;
	private String vchTO;
	private String gatepassNo;
	private String licenseNo;
	private int maxId;
	private String pdfName;
	private String vch_from;           

	public String getVch_from() {
		return vch_from;
	}

	public void setVch_from(String vch_from) {
		this.vch_from = vch_from;
	}

	public String getPdfName() {
		return pdfName;
	}

	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}

	public int getMaxId() {
		return maxId;
	}

	public void setMaxId(int maxId) {
		this.maxId = maxId;
	}

	public boolean isPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(boolean printFlag) {
		this.printFlag = printFlag;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public String getVchTO() {
		return vchTO;
	}

	public void setVchTO(String vchTO) {
		this.vchTO = vchTO;
	}

	public String getGatepassNo() {
		return gatepassNo;
	}

	public void setGatepassNo(String gatepassNo) {
		this.gatepassNo = gatepassNo;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	
	
	
	// ----------------uploading gatepass------------------

		private String gatepass;
		private String casecode;
		private boolean draftprintFlag;
		private String draftpdfName;
		private boolean finalizePrint;
		private boolean printDraft;
		

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

		public String getDraftpdfName() {
			return draftpdfName;
		}

		public void setDraftpdfName(String draftpdfName) {
			this.draftpdfName = draftpdfName;
		}
		private String brand_name;
		private Date date_plan;
		private int casecoseBrandSize;
		public String cascodeMatching;
		public String getBrand_name() {
			return brand_name;
		}

		public void setBrand_name(String brand_name) {
			this.brand_name = brand_name;
		}

		public Date getDate_plan() {
			return date_plan;
		}

		public void setDate_plan(Date date_plan) {
			this.date_plan = date_plan;
		}

		public int getCasecoseBrandSize() {
			return casecoseBrandSize;
		}

		public void setCasecoseBrandSize(int casecoseBrandSize) {
			this.casecoseBrandSize = casecoseBrandSize;
		}

		public String getCascodeMatching() {
			return cascodeMatching;
		}

		public void setCascodeMatching(String cascodeMatching) {
			this.cascodeMatching = cascodeMatching;
		}
		private String indentNo;
		private String indentDate;
		private String brandName;
		private String packageSize;
		private int dispatchboxIndent;
		private int brandIdIndent;
		private int packageIdIndent;
		private int snoIndent;
		private boolean  selected;


		public boolean isSelected() {
			return selected;
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
		}

		public int getBrandIdIndent() {
			return brandIdIndent;
		}

		public void setBrandIdIndent(int brandIdIndent) {
			this.brandIdIndent = brandIdIndent;
		}

		public int getPackageIdIndent() {
			return packageIdIndent;
		}

		public void setPackageIdIndent(int packageIdIndent) {
			this.packageIdIndent = packageIdIndent;
		}

		public String getIndentNo() {
			return indentNo;
		}

		public void setIndentNo(String indentNo) {
			this.indentNo = indentNo;
		}

		public String getIndentDate() {
			return indentDate;
		}

		public void setIndentDate(String indentDate) {
			this.indentDate = indentDate;
		}

		public String getBrandName() {
			return brandName;
		}

		public void setBrandName(String brandName) {
			this.brandName = brandName;
		}

		public String getPackageSize() {
			return packageSize;
		}

		public void setPackageSize(String packageSize) {
			this.packageSize = packageSize;
		}

		public int getDispatchboxIndent() {
			return dispatchboxIndent;
		}

		public void setDispatchboxIndent(int dispatchboxIndent) {
			this.dispatchboxIndent = dispatchboxIndent;
		}

		public int getSnoIndent() {
			return snoIndent;
		}

		public void setSnoIndent(int snoIndent) {
			this.snoIndent = snoIndent;
		}
		
		
		
		
	private int indentbox;


	public int getIndentbox() {
		return indentbox;
	}

	public void setIndentbox(int indentbox) {
		this.indentbox = indentbox;
	}	
	
	
	private String indentNumber="";

	public String getIndentNumber() 
	{
		return indentNumber;
	}

	public void setIndentNumber(String indentNumber) 
	{
		this.indentNumber = indentNumber;
	}
	
		
}
