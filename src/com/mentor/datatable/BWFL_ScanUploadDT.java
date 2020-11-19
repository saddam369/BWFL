package com.mentor.datatable;

import java.util.Date;

import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Validate;

public class BWFL_ScanUploadDT {

	// ------------------------for first
	// datatable----------------------------------
	private int snofrst;
	private Date datefrst;
	private int distilleryIdfrst;
	private String licenseNmbrfrst;
	private String permitnofrst;
	private String passnofrst;
	private Boolean printBtnFlag = false;
	private int groupid;

	private int dispbox;
	private int dispbot;
	private double scanning_fee;
	private double add_fee;

	public double getScanning_fee() {
		return scanning_fee;
	}

	public void setScanning_fee(double scanning_fee) {
		this.scanning_fee = scanning_fee;
	}

	public double getAdd_fee() {
		return add_fee;
	}

	public void setAdd_fee(double add_fee) {
		this.add_fee = add_fee;
	}

	public int getDispbot() {
		return dispbot;
	}

	public void setDispbot(int dispbot) {
		this.dispbot = dispbot;
	}

	public int getDispbox() {
		return dispbox;
	}

	public void setDispbox(int dispbox) {
		this.dispbox = dispbox;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public String cascodeMatching;

	public String getCascodeMatching() {
		return cascodeMatching;
	}

	public void setCascodeMatching(String cascodeMatching) {
		this.cascodeMatching = cascodeMatching;
	}

	public Boolean getPrintBtnFlag() {
		return printBtnFlag;
	}

	public void setPrintBtnFlag(Boolean printBtnFlag) {
		this.printBtnFlag = printBtnFlag;
	}

	public int getSnofrst() {
		return snofrst;
	}

	public void setSnofrst(int snofrst) {
		this.snofrst = snofrst;
	}

	public Date getDatefrst() {
		return datefrst;
	}

	public void setDatefrst(Date datefrst) {
		this.datefrst = datefrst;
	}

	public int getDistilleryIdfrst() {
		return distilleryIdfrst;
	}

	public void setDistilleryIdfrst(int distilleryIdfrst) {
		this.distilleryIdfrst = distilleryIdfrst;
	}

	public String getLicenseNmbrfrst() {
		return licenseNmbrfrst;
	}

	public void setLicenseNmbrfrst(String licenseNmbrfrst) {
		this.licenseNmbrfrst = licenseNmbrfrst;
	}

	public String getPermitnofrst() {
		return permitnofrst;
	}

	public void setPermitnofrst(String permitnofrst) {
		this.permitnofrst = permitnofrst;
	}

	public String getPassnofrst() {
		return passnofrst;
	}

	public void setPassnofrst(String passnofrst) {
		this.passnofrst = passnofrst;
	}

	// ------------------------ first datatable
	// end----------------------------------

	// ------------------------for third
	// datatable----------------------------------

	private int snothrd;
	private Date datethrd;
	private int distilleryIdthrd;
	private String licenseNmbrthrd;
	private String permitnothrd;
	private String passnothrd;

	public int getSnothrd() {
		return snothrd;
	}

	public void setSnothrd(int snothrd) {
		this.snothrd = snothrd;
	}

	public Date getDatethrd() {
		return datethrd;
	}

	public void setDatethrd(Date datethrd) {
		this.datethrd = datethrd;
	}

	public int getDistilleryIdthrd() {
		return distilleryIdthrd;
	}

	public void setDistilleryIdthrd(int distilleryIdthrd) {
		this.distilleryIdthrd = distilleryIdthrd;
	}

	public String getLicenseNmbrthrd() {
		return licenseNmbrthrd;
	}

	public void setLicenseNmbrthrd(String licenseNmbrthrd) {
		this.licenseNmbrthrd = licenseNmbrthrd;
	}

	public String getPermitnothrd() {
		return permitnothrd;
	}

	public void setPermitnothrd(String permitnothrd) {
		this.permitnothrd = permitnothrd;
	}

	public String getPassnothrd() {
		return passnothrd;
	}

	public void setPassnothrd(String passnothrd) {
		this.passnothrd = passnothrd;
	}

	// -------------------------end of third data
	// table----------------------------

	private int brewery_id;
	private int distillery_id;
	private int int_brand_id;
	private int int_pack_id;
	private int int_quantity;
	private int int_planned_bottles = 0;
	private int int_boxes;
	private int int_liquor_type;
	private String vch_license_type;
	private Date plan_dt;
	private String licence_no;
	private Date cr_date;
	private Date finalized_date;
	private String finalized_flag;
	private int slno;
	private String received_liqour;
	private String received_from_usr;
	private int size;
	private String permitno;
	private String brand;
	private String gatepassno;
	private int dispatched_bottles = 0;
	private String breakage = "";
	private Date dispatch_date;
	private boolean printFlag;
	private String pdfName;
	private String permitno1;

	public String getPermitno1() {
		return permitno1;
	}

	public void setPermitno1(String permitno1) {
		this.permitno1 = permitno1;
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

	public Date getDispatch_date() {
		return dispatch_date;
	}

	public void setDispatch_date(Date dispatch_date) {
		this.dispatch_date = dispatch_date;
	}

	public int getDispatched_bottles() {
		if (this.breakage == "") {
			dispatched_bottles = 0;
		} else if ((Validate.validateOnlyInt("recievedfromuser", this.breakage))) {
			dispatched_bottles = this.int_planned_bottles
					- Integer.parseInt(this.breakage);
		} else if (!(Validate
				.validateOnlyInt("recievedfromuser", this.breakage))) {
			System.out.println("not validateOnlyInt");
			ResourceUtil.addErrorMessage(this.breakage, Constants.ONLY_INT);
		}

		return dispatched_bottles;
	}

	public void setDispatched_bottles(int dispatched_bottles) {
		this.dispatched_bottles = dispatched_bottles;
	}

	public String getBreakage() {
		return breakage;
	}

	public void setBreakage(String breakage) {
		this.breakage = breakage;
	}

	public int getBrewery_id() {
		return brewery_id;
	}

	public void setBrewery_id(int brewery_id) {
		this.brewery_id = brewery_id;
	}

	public int getDistillery_id() {
		return distillery_id;
	}

	public void setDistillery_id(int distillery_id) {
		this.distillery_id = distillery_id;
	}

	public int getInt_brand_id() {
		return int_brand_id;
	}

	public void setInt_brand_id(int int_brand_id) {
		this.int_brand_id = int_brand_id;
	}

	public int getInt_pack_id() {
		return int_pack_id;
	}

	public void setInt_pack_id(int int_pack_id) {
		this.int_pack_id = int_pack_id;
	}

	public int getInt_quantity() {
		return int_quantity;
	}

	public void setInt_quantity(int int_quantity) {
		this.int_quantity = int_quantity;
	}

	public int getInt_planned_bottles() {
		return int_planned_bottles;
	}

	public void setInt_planned_bottles(int int_planned_bottles) {
		this.int_planned_bottles = int_planned_bottles;
	}

	public int getInt_boxes() {
		return int_boxes;
	}

	public void setInt_boxes(int int_boxes) {
		this.int_boxes = int_boxes;
	}

	public int getInt_liquor_type() {
		return int_liquor_type;
	}

	public void setInt_liquor_type(int int_liquor_type) {
		this.int_liquor_type = int_liquor_type;
	}

	public String getVch_license_type() {
		return vch_license_type;
	}

	public void setVch_license_type(String vch_license_type) {
		this.vch_license_type = vch_license_type;
	}

	public Date getPlan_dt() {
		return plan_dt;
	}

	public void setPlan_dt(Date plan_dt) {
		this.plan_dt = plan_dt;
	}

	public String getLicence_no() {
		return licence_no;
	}

	public void setLicence_no(String licence_no) {
		this.licence_no = licence_no;
	}

	public Date getCr_date() {
		return cr_date;
	}

	public void setCr_date(Date cr_date) {
		this.cr_date = cr_date;
	}

	public Date getFinalized_date() {

		return finalized_date;
	}

	public void setFinalized_date(Date finalized_date) {
		this.finalized_date = finalized_date;
	}

	public String getFinalized_flag() {
		return finalized_flag;
	}

	public void setFinalized_flag(String finalized_flag) {
		this.finalized_flag = finalized_flag;
	}

	public int getSlno() {
		return slno;
	}

	public void setSlno(int slno) {
		this.slno = slno;
	}

	public String getReceived_liqour() {
		return received_liqour;
	}

	public void setReceived_liqour(String received_liqour) {
		this.received_liqour = received_liqour;
	}

	public String getReceived_from_usr() {
		return received_from_usr;
	}

	public void setReceived_from_usr(String received_from_usr) {
		this.received_from_usr = received_from_usr;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getPermitno() {
		return permitno;
	}

	public void setPermitno(String permitno) {
		this.permitno = permitno;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getGatepassno() {
		return gatepassno;
	}

	public void setGatepassno(String gatepassno) {
		this.gatepassno = gatepassno;
	}

	// ---------------------show datatable----------------------------

	private int sno;
	private int passno;
	private String licno;
	private String exportOrderNo;
	private Date exportOrderDt;
	private int vehicleNmber;
	private String brandDt;
	private String permitnodisp;

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getPassno() {
		return passno;
	}

	public void setPassno(int passno) {
		this.passno = passno;
	}

	public String getLicno() {
		return licno;
	}

	public void setLicno(String licno) {
		this.licno = licno;
	}

	public String getExportOrderNo() {
		return exportOrderNo;
	}

	public void setExportOrderNo(String exportOrderNo) {
		this.exportOrderNo = exportOrderNo;
	}

	public Date getExportOrderDt() {
		return exportOrderDt;
	}

	public void setExportOrderDt(Date exportOrderDt) {
		this.exportOrderDt = exportOrderDt;
	}

	public int getVehicleNmber() {
		return vehicleNmber;
	}

	public void setVehicleNmber(int vehicleNmber) {
		this.vehicleNmber = vehicleNmber;
	}

	public String getBrandDt() {
		return brandDt;
	}

	public void setBrandDt(String brandDt) {
		this.brandDt = brandDt;
	}

	public String getPermitnodisp() {
		return permitnodisp;
	}

	public void setPermitnodisp(String permitnodisp) {
		this.permitnodisp = permitnodisp;
	}

	private Date dateDt;
	private String licenseNmbr;

	public Date getDateDt() {
		return dateDt;
	}

	public void setDateDt(Date dateDt) {
		this.dateDt = dateDt;
	}

	public String getLicenseNmbr() {
		return licenseNmbr;
	}

	public void setLicenseNmbr(String licenseNmbr) {
		this.licenseNmbr = licenseNmbr;
	}

	// ----------------uploading gatepass------------------

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

	// ==================newly added==================

	private String permitNmbr_dt;
	private String brandNm_dt;
	private String pckgNm_dt;

	public String getPermitNmbr_dt() {
		return permitNmbr_dt;
	}

	public void setPermitNmbr_dt(String permitNmbr_dt) {
		this.permitNmbr_dt = permitNmbr_dt;
	}

	public String getBrandNm_dt() {
		return brandNm_dt;
	}

	public void setBrandNm_dt(String brandNm_dt) {
		this.brandNm_dt = brandNm_dt;
	}

	public String getPckgNm_dt() {
		return pckgNm_dt;
	}

	public void setPckgNm_dt(String pckgNm_dt) {
		this.pckgNm_dt = pckgNm_dt;
	}

	private String brand_name;
	private int casecoseBrandSize;
	private Date date_plan;

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public int getCasecoseBrandSize() {
		return casecoseBrandSize;
	}

	public void setCasecoseBrandSize(int casecoseBrandSize) {
		this.casecoseBrandSize = casecoseBrandSize;
	}

	public Date getDate_plan() {
		return date_plan;
	}

	public void setDate_plan(Date date_plan) {
		this.date_plan = date_plan;
	}

	private String mappedUnmapedType_dt;

	public String getMappedUnmapedType_dt() {
		return mappedUnmapedType_dt;
	}

	public void setMappedUnmapedType_dt(String mappedUnmapedType_dt) {
		this.mappedUnmapedType_dt = mappedUnmapedType_dt;
	}

}
