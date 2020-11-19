package com.mentor.datatable;

import java.util.Date;

public class GroupingOfUnmappedDT {

	private Date showDataTable_Date;
	private String showDataTable_LiqureType;
	private String showDataTable_LicenceType;
	private String showDataTable_Brand;
	private String showDataTable_Packging;
	private String showDataTable_Quntity;
	private String showDataTable_PlanNoOfBottling;
	private String showDataTable_NoOfBoxes;
	private String permitnoD;
	private String finalizedFlag;
	private String gtinno;
	private int seq;
	private Date finalize_Date;
	private String finalizedDateString;
	private int brandId;
	private int packagingId;
	private String licenceNo;
	private String licencenoo;
	private int newml;
	private int newsize;
	private boolean group;
	private int disliery_id;
	
	public Date getFinalizeDate() {
		return finalizeDate;
	}

	public void setFinalizeDate(Date finalizeDate) {
		this.finalizeDate = finalizeDate;
	}

	private String provided_case_seq;
	private String provided_bottling_seq;
	private String recived_cases;
	private Date finalizeDate;
	
	
	
	
	public String getProvided_case_seq() {
		return provided_case_seq;
	}

	public void setProvided_case_seq(String provided_case_seq) {
		this.provided_case_seq = provided_case_seq;
	}

	public String getProvided_bottling_seq() {
		return provided_bottling_seq;
	}

	public void setProvided_bottling_seq(String provided_bottling_seq) {
		this.provided_bottling_seq = provided_bottling_seq;
	}

	public String getRecived_cases() {
		return recived_cases;
	}

	public void setRecived_cases(String recived_cases) {
		this.recived_cases = recived_cases;
	}

	public int getDisliery_id() {
		return disliery_id;
	}

	public void setDisliery_id(int disliery_id) {
		this.disliery_id = disliery_id;
	}

	private Date showDataTable_groupId;

	public Date getShowDataTable_Date() {
		return showDataTable_Date;
	}

	public void setShowDataTable_Date(Date showDataTable_Date) {
		this.showDataTable_Date = showDataTable_Date;
	}

	public String getShowDataTable_LiqureType() {
		return showDataTable_LiqureType;
	}

	public void setShowDataTable_LiqureType(String showDataTable_LiqureType) {
		this.showDataTable_LiqureType = showDataTable_LiqureType;
	}

	public String getShowDataTable_LicenceType() {
		return showDataTable_LicenceType;
	}

	public void setShowDataTable_LicenceType(String showDataTable_LicenceType) {
		this.showDataTable_LicenceType = showDataTable_LicenceType;
	}

	public String getShowDataTable_Brand() {
		return showDataTable_Brand;
	}

	public void setShowDataTable_Brand(String showDataTable_Brand) {
		this.showDataTable_Brand = showDataTable_Brand;
	}

	public String getShowDataTable_Packging() {
		return showDataTable_Packging;
	}

	public void setShowDataTable_Packging(String showDataTable_Packging) {
		this.showDataTable_Packging = showDataTable_Packging;
	}

	public String getShowDataTable_Quntity() {
		return showDataTable_Quntity;
	}

	public void setShowDataTable_Quntity(String showDataTable_Quntity) {
		this.showDataTable_Quntity = showDataTable_Quntity;
	}

	public String getShowDataTable_PlanNoOfBottling() {
		return showDataTable_PlanNoOfBottling;
	}

	public void setShowDataTable_PlanNoOfBottling(
			String showDataTable_PlanNoOfBottling) {
		this.showDataTable_PlanNoOfBottling = showDataTable_PlanNoOfBottling;
	}

	public String getShowDataTable_NoOfBoxes() {
		return showDataTable_NoOfBoxes;
	}

	public void setShowDataTable_NoOfBoxes(String showDataTable_NoOfBoxes) {
		this.showDataTable_NoOfBoxes = showDataTable_NoOfBoxes;
	}

	public String getPermitnoD() {
		return permitnoD;
	}

	public void setPermitnoD(String permitnoD) {
		this.permitnoD = permitnoD;
	}

	public String getFinalizedFlag() {
		return finalizedFlag;
	}

	public void setFinalizedFlag(String finalizedFlag) {
		this.finalizedFlag = finalizedFlag;
	}

	public String getGtinno() {
		return gtinno;
	}

	public void setGtinno(String gtinno) {
		this.gtinno = gtinno;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public Date getFinalize_Date() {
		return finalize_Date;
	}

	public void setFinalize_Date(Date finalize_Date) {
		this.finalize_Date = finalize_Date;
	}

	public String getFinalizedDateString() {
		return finalizedDateString;
	}

	public void setFinalizedDateString(String finalizedDateString) {
		this.finalizedDateString = finalizedDateString;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public int getPackagingId() {
		return packagingId;
	}

	public void setPackagingId(int packagingId) {
		this.packagingId = packagingId;
	}

	public String getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public String getLicencenoo() {
		return licencenoo;
	}

	public void setLicencenoo(String licencenoo) {
		this.licencenoo = licencenoo;
	}

	public int getNewml() {
		return newml;
	}

	public void setNewml(int newml) {
		this.newml = newml;
	}

	public int getNewsize() {
		return newsize;
	}

	public void setNewsize(int newsize) {
		this.newsize = newsize;
	}

	public boolean isGroup() {
		return group;
	}

	public void setGroup(boolean group) {
		this.group = group;
	}

	public Date getShowDataTable_groupId() {
		return showDataTable_groupId;
	}

	public void setShowDataTable_groupId(Date showDataTable_groupId) {
		this.showDataTable_groupId = showDataTable_groupId;
	}

	// ------------------second data table------------------

	private Date planDateDt;
	private int groupIdDt;
	private String liquorTypeDt;
	private String licenseTypeDt;
	private String brandNmDt;
	private String pckgNmDt;
	private String quantityDt;
	private int nmbrOfBoxesDt;
	private int nmbrOfBottlesDt;
    
	public Date getPlanDateDt() {
		return planDateDt;
	}

	public void setPlanDateDt(Date planDateDt) {
		this.planDateDt = planDateDt;
	}

	public int getGroupIdDt() {
		return groupIdDt;
	}

	public void setGroupIdDt(int groupIdDt) {
		this.groupIdDt = groupIdDt;
	}

	public String getLiquorTypeDt() {
		return liquorTypeDt;
	}

	public void setLiquorTypeDt(String liquorTypeDt) {
		this.liquorTypeDt = liquorTypeDt;
	}

	public String getLicenseTypeDt() {
		return licenseTypeDt;
	}

	public void setLicenseTypeDt(String licenseTypeDt) {
		this.licenseTypeDt = licenseTypeDt;
	}

	public String getBrandNmDt() {
		return brandNmDt;
	}

	public void setBrandNmDt(String brandNmDt) {
		this.brandNmDt = brandNmDt;
	}

	public String getPckgNmDt() {
		return pckgNmDt;
	}

	public void setPckgNmDt(String pckgNmDt) {
		this.pckgNmDt = pckgNmDt;
	}

	public String getQuantityDt() {
		return quantityDt;
	}

	public void setQuantityDt(String quantityDt) {
		this.quantityDt = quantityDt;
	}

	public int getNmbrOfBoxesDt() {
		return nmbrOfBoxesDt;
	}

	public void setNmbrOfBoxesDt(int nmbrOfBoxesDt) {
		this.nmbrOfBoxesDt = nmbrOfBoxesDt;
	}

	public int getNmbrOfBottlesDt() {
		return nmbrOfBottlesDt;
	}

	public void setNmbrOfBottlesDt(int nmbrOfBottlesDt) {
		this.nmbrOfBottlesDt = nmbrOfBottlesDt;
	}

}
