package com.mentor.datatable;

import java.util.ArrayList;
import java.util.Date;

import com.mentor.impl.GatepassToDistrict_FL2_FL2B_oldStockImpl;

public class GatepassToDistrict_FL2_FL2B_oldStockDT {

	GatepassToDistrict_FL2_FL2B_oldStockImpl impl = new GatepassToDistrict_FL2_FL2B_oldStockImpl();

	private String vch_from;

	public String getVch_from() {
		return vch_from;
	}

	public void setVch_from(String vch_from) {
		this.vch_from = vch_from;
	}

	ArrayList getVal = new ArrayList();

	public ArrayList getGetVal() {
		return getVal;
	}

	public void setGetVal(ArrayList getVal) {
		this.getVal = getVal;
	}

	private Date dt_date;

	private String vch_gatepass_no;
	private boolean draftprintFlag;
	private boolean finflg;
	private String draftpdfname;
	private boolean printFlag_CaseCode = false;

	private String cnclbrnd;
	private String cnclpck;
	private int cnclbot;

	private int fl2_2bIdDt;

	public int getFl2_2bIdDt() {
		return fl2_2bIdDt;
	}

	public void setFl2_2bIdDt(int fl2_2bIdDt) {
		this.fl2_2bIdDt = fl2_2bIdDt;
	}

	public String getCnclbrnd() {
		return cnclbrnd;
	}

	public void setCnclbrnd(String cnclbrnd) {
		this.cnclbrnd = cnclbrnd;
	}

	public String getCnclpck() {
		return cnclpck;
	}

	public void setCnclpck(String cnclpck) {
		this.cnclpck = cnclpck;
	}

	public int getCnclbot() {
		return cnclbot;
	}

	public void setCnclbot(int cnclbot) {
		this.cnclbot = cnclbot;
	}

	public boolean isPrintFlag_CaseCode() {
		return printFlag_CaseCode;
	}

	public void setPrintFlag_CaseCode(boolean printFlag_CaseCode) {
		this.printFlag_CaseCode = printFlag_CaseCode;
	}

	public String getDraftpdfname() {
		return draftpdfname;
	}

	public void setDraftpdfname(String draftpdfname) {
		this.draftpdfname = draftpdfname;
	}

	public boolean isFinflg() {
		return finflg;
	}

	public void setFinflg(boolean finflg) {
		this.finflg = finflg;
	}

	public boolean isDraftprintFlag() {
		return draftprintFlag;
	}

	public void setDraftprintFlag(boolean draftprintFlag) {
		this.draftprintFlag = draftprintFlag;
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

	private int slno;
	private boolean printDraftFlagDt;
	private String pdfDraft;

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

		this.dispatchbottls = ((this.dispatchbox * this.size) - this.breakage);

		return dispatchbottls;
	}

	public void setDispatchbottls(int dispatchbottls) {
		this.dispatchbottls = dispatchbottls;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	private boolean printFlag = false;
	private int serialNo;
	private Date currentDate;
	private String vchTO;
	private String gatepassNo;
	private String licenseNo;
	private int maxId;
	private String pdfName;
	private String type;
	private Date casecodedt;

	public Date getCasecodedt() {
		return casecodedt;
	}

	public void setCasecodedt(Date casecodedt) {
		this.casecodedt = casecodedt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public int getMaxId() {
		return maxId;
	}

	public void setMaxId(int maxId) {
		this.maxId = maxId;
	}

	public String getPdfName() {
		return pdfName;
	}

	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}

	private String casecode;
	private String gatepass;

	public String getCasecode() {
		return casecode;
	}

	public void setCasecode(String casecode) {
		this.casecode = casecode;
	}

	public String getGatepass() {
		return gatepass;
	}

	public void setGatepass(String gatepass) {
		this.gatepass = gatepass;
	}

	public boolean isPrintDraftFlagDt() {
		return printDraftFlagDt;
	}

	public void setPrintDraftFlagDt(boolean printDraftFlagDt) {
		this.printDraftFlagDt = printDraftFlagDt;
	}

	public String getPdfDraft() {
		return pdfDraft;
	}

	public void setPdfDraft(String pdfDraft) {
		this.pdfDraft = pdfDraft;
	}

	// ---------------------cancel gatepass-----------------------

	private int dispatcBotlsDt;
	private int brandIdDt;
	private int pckgIdDt;
	private int seqDt;

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

	private String brandUnitName_dt;

	public String getBrandUnitName_dt() {
		return brandUnitName_dt;
	}

	public void setBrandUnitName_dt(String brandUnitName_dt) {
		this.brandUnitName_dt = brandUnitName_dt;
	}

	private int seq_dt;

	public int getSeq_dt() {
		return seq_dt;
	}

	public void setSeq_dt(int seq_dt) {
		this.seq_dt = seq_dt;
	}

	private String brandPackagingData_Brand;
	private ArrayList brandPackagingData_BrandList = new ArrayList();

	private String brandPackagingData_Packaging;
	private ArrayList brandPackagingData_PackagingList = new ArrayList();

	private String brandPackagingData_Quantity;
	private ArrayList brandPackagingData_QuantityList = new ArrayList();
	private int srNo;

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public String getBrandPackagingData_Brand() {
		return brandPackagingData_Brand;
	}

	public void setBrandPackagingData_Brand(String brandPackagingData_Brand) {
		this.brandPackagingData_Brand = brandPackagingData_Brand;
	}

	public ArrayList getBrandPackagingData_BrandList() {
		try {
			this.brandPackagingData_BrandList = impl.getBrandName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return brandPackagingData_BrandList;
	}

	public void setBrandPackagingData_BrandList(
			ArrayList brandPackagingData_BrandList) {
		this.brandPackagingData_BrandList = brandPackagingData_BrandList;
	}

	public String getBrandPackagingData_Packaging() {
		return brandPackagingData_Packaging;
	}

	public void setBrandPackagingData_Packaging(
			String brandPackagingData_Packaging) {
		this.brandPackagingData_Packaging = brandPackagingData_Packaging;
	}

	public ArrayList getBrandPackagingData_PackagingList() {
		try {
			if (this.brandPackagingData_Brand != null) {
				this.brandPackagingData_PackagingList = impl
						.getPackagingName(brandPackagingData_Brand);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return brandPackagingData_PackagingList;
	}

	public void setBrandPackagingData_PackagingList(
			ArrayList brandPackagingData_PackagingList) {
		this.brandPackagingData_PackagingList = brandPackagingData_PackagingList;
	}

	public String getBrandPackagingData_Quantity() {
		try {
			if (this.brandPackagingData_Packaging != null&& this.brandPackagingData_Brand != null) {
				this.brandPackagingData_Quantity = impl.getqty(brandPackagingData_Brand, brandPackagingData_Packaging);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return brandPackagingData_Quantity;
	}

	public void setBrandPackagingData_Quantity(
			String brandPackagingData_Quantity) {
		this.brandPackagingData_Quantity = brandPackagingData_Quantity;
	}

	public ArrayList getBrandPackagingData_QuantityList() {
		return brandPackagingData_QuantityList;
	}

	public void setBrandPackagingData_QuantityList(
			ArrayList brandPackagingData_QuantityList) {
		this.brandPackagingData_QuantityList = brandPackagingData_QuantityList;
	}

	private boolean brandFlg;
	private String brandValue;
	private String quantity_dt;

	public String getQuantity_dt() {
		return quantity_dt;
	}

	public void setQuantity_dt(String quantity_dt) {
		this.quantity_dt = quantity_dt;
	}

	public boolean isBrandFlg() {
		return brandFlg;
	}

	public void setBrandFlg(boolean brandFlg) {
		this.brandFlg = brandFlg;
	}

	public String getBrandValue() {
		return brandValue;
	}

	public void setBrandValue(String brandValue) {
		this.brandValue = brandValue;
	}

	public double db_duty = 0;
	public double db_add_duty = 0;
	public double calculated_duty = 0;
	public double calculated_add_duty;

	public double getDb_duty() {
		try {
			if (this.brandPackagingData_Packaging != null&& this.brandPackagingData_Brand != null) {
				this.db_duty = impl.getDuty(brandPackagingData_Brand, brandPackagingData_Packaging);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return db_duty;
	}

	public void setDb_duty(double db_duty) {
		this.db_duty = db_duty;
	}

	public double getDb_add_duty() {
		try {
			if (this.brandPackagingData_Packaging != null&& this.brandPackagingData_Brand != null) {
				this.db_add_duty = impl.getAddDuty(brandPackagingData_Brand, brandPackagingData_Packaging);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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

}
