package com.mentor.datatable;

import java.util.Date;

public class BarCodeForCSDDataTable {
	
	
	private String brandPackagingData_Brand_ID;
	
	private String brandPackagingData_Brand_Name;
	private String brandPackagingData_Brand_Size;
	private String brandPackagingData_Brand_No_OF_Bottels;
	private String showDataTable_Quntity;
	private String showDataTable_PlanNoOfBottling;
	private String showDataTable_NoOfBoxes;
	private String gtinno;
	private int csd;
	
	
private String finalizedFlag;



	
	public int getCsd() {
	return csd;
}
public void setCsd(int csd) {
	this.csd = csd;
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
	public String getBrandPackagingData_Brand_ID() {
		return brandPackagingData_Brand_ID;
	}
	public void setBrandPackagingData_Brand_ID(String brandPackagingData_Brand_ID) {
		this.brandPackagingData_Brand_ID = brandPackagingData_Brand_ID;
	}
	public String getBrandPackagingData_Brand_Name() {
		return brandPackagingData_Brand_Name;
	}
	public void setBrandPackagingData_Brand_Name(
			String brandPackagingData_Brand_Name) {
		this.brandPackagingData_Brand_Name = brandPackagingData_Brand_Name;
	}
	public String getBrandPackagingData_Brand_Size() {
		return brandPackagingData_Brand_Size;
	}
	public void setBrandPackagingData_Brand_Size(
			String brandPackagingData_Brand_Size) {
		this.brandPackagingData_Brand_Size = brandPackagingData_Brand_Size;
	}
	public String getBrandPackagingData_Brand_No_OF_Bottels() {
		return brandPackagingData_Brand_No_OF_Bottels;
	}
	public void setBrandPackagingData_Brand_No_OF_Bottels(
			String brandPackagingData_Brand_No_OF_Bottels) {
		this.brandPackagingData_Brand_No_OF_Bottels = brandPackagingData_Brand_No_OF_Bottels;
	}
	
	private Date finalize_Date;
	private String finalizedDateString;
	
	public String getFinalizedDateString() {
		return finalizedDateString;
	}
	public void setFinalizedDateString(String finalizedDateString) {
		this.finalizedDateString = finalizedDateString;
	}
	public Date getFinalize_Date() {
		return finalize_Date;
	}
	public void setFinalize_Date(Date finalize_Date) {
		this.finalize_Date = finalize_Date;
	}
private int request_id;
private Date showDataTable_Date;



	
	public Date getShowDataTable_Date() {
	return showDataTable_Date;
}
public void setShowDataTable_Date(Date showDataTable_Date) {
	this.showDataTable_Date = showDataTable_Date;
}
	public int getRequest_id() {
		return request_id;
	}
	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}
	private int newml;
	private int newsize;
	
	private String licencenoo;
	
	
	
	
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
}
