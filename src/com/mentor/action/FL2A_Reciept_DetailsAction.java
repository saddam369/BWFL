package com.mentor.action;

import java.util.ArrayList;
import java.util.Date;
import javax.faces.event.ActionEvent;

import com.mentor.datatable.FL2A_Reciept_DetailsDatatable;
import com.mentor.datatable.FL2A_Reciept_DetailsDatatable2;
import com.mentor.impl.FL2A_Reciept_DetailsImpl;
import com.mentor.utility.Validate;

public class FL2A_Reciept_DetailsAction {

	FL2A_Reciept_DetailsImpl impl = new FL2A_Reciept_DetailsImpl();
	private String hidden;
	private String user; // =ResourceUtil.getUserNameAllReq();
	//private String bwfl_int_brewery_id;
	//private String bwfl_int_distillery_id;
	//private String bwfl_vch_brewery_contact_number;
	//private String bwfl_vch_distillery_contact_number;
	private int bwfl_int_id_pk;
	private ArrayList bwfl_datalist;
	private boolean view_full_form_flag;
	private boolean tablerefresh = true;

	public boolean isTablerefresh() {
		return tablerefresh;
	}

	public void setTablerefresh(boolean tablerefresh) {
		this.tablerefresh = tablerefresh;
	}

	private FL2A_Reciept_DetailsDatatable reciptDetail;

	public FL2A_Reciept_DetailsDatatable getReciptDetail() {
		return reciptDetail;
	}

	public void setReciptDetail(FL2A_Reciept_DetailsDatatable reciptDetail) {
		this.reciptDetail = reciptDetail;
	}

	public ArrayList getBwfl_datalist() {
		this.bwfl_datalist = new FL2A_Reciept_DetailsImpl().getDetails_BWFL(this);
		return bwfl_datalist;
	}

	public void setBwfl_datalist(ArrayList bwfl_datalist) {
		this.bwfl_datalist = bwfl_datalist;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getHidden() {
		new FL2A_Reciept_DetailsImpl().getDetails(this);
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

/*	public String getBwfl_int_brewery_id() {
		return bwfl_int_brewery_id;
	}

	public void setBwfl_int_brewery_id(String bwfl_int_brewery_id) {
		this.bwfl_int_brewery_id = bwfl_int_brewery_id;
	}

	public String getBwfl_int_distillery_id() {
		return bwfl_int_distillery_id;
	}

	public void setBwfl_int_distillery_id(String bwfl_int_distillery_id) {
		this.bwfl_int_distillery_id = bwfl_int_distillery_id;
	}

	public String getBwfl_vch_brewery_contact_number() {
		return bwfl_vch_brewery_contact_number;
	}

	public void setBwfl_vch_brewery_contact_number(
			String bwfl_vch_brewery_contact_number) {
		this.bwfl_vch_brewery_contact_number = bwfl_vch_brewery_contact_number;
	}

	public String getBwfl_vch_distillery_contact_number() {
		return bwfl_vch_distillery_contact_number;
	}

	public void setBwfl_vch_distillery_contact_number(
			String bwfl_vch_distillery_contact_number) {
		this.bwfl_vch_distillery_contact_number = bwfl_vch_distillery_contact_number;
	}
*/
	public int getBwfl_int_id_pk() {
		return bwfl_int_id_pk;
	}

	public void setBwfl_int_id_pk(int bwfl_int_id_pk) {
		this.bwfl_int_id_pk = bwfl_int_id_pk;
	}

	public boolean isView_full_form_flag() {
		return view_full_form_flag;
	}

	public void setView_full_form_flag(boolean view_full_form_flag) {
		this.view_full_form_flag = view_full_form_flag;
	}

	public String view_details() {
		try {
			
			this.setView_full_form_flag(true);
			this.view_formData = new FL2A_Reciept_DetailsImpl().getFormDetails(this, reciptDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	// ===========================================================Form Detail
	// Fields================================

	private boolean breweryFlag;
	private boolean distilleryFlag;
	private String brewerynm;
	private String distillerynm;
	private String licenseNumber;
	private String licenseType;
	private String licenseeName;
	private String mobile;
	private String fatherName;
	private String districtName;
	private String email;
	private String firmName;
	private String age;
	private String sex;
	private String gstin;
	private String pan;
	private String adhar;
	private String unitName;
	private Date issueDate;
	private Date validUpto;
	private String address;
	private String contactDistillery;
	private String contactBrewery;
	private String recievedby;
	private String remarks;
	private Date receiving_date;

	public Date getReceiving_date() {
		return receiving_date;
	}

	public void setReceiving_date(Date receiving_date) {
		this.receiving_date = receiving_date;
	}

	public String getRecievedby() {
		return recievedby;
	}

	public void setRecievedby(String recievedby) {
		this.recievedby = recievedby;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean isBreweryFlag() {
		return breweryFlag;
	}

	public void setBreweryFlag(boolean breweryFlag) {
		this.breweryFlag = breweryFlag;
	}

	public boolean isDistilleryFlag() {
		return distilleryFlag;
	}

	public void setDistilleryFlag(boolean distilleryFlag) {
		this.distilleryFlag = distilleryFlag;
	}

	public String getBrewerynm() {
		return brewerynm;
	}

	public void setBrewerynm(String brewerynm) {
		this.brewerynm = brewerynm;
	}

	public String getDistillerynm() {
		return distillerynm;
	}

	public void setDistillerynm(String distillerynm) {
		this.distillerynm = distillerynm;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public String getLicenseeName() {
		return licenseeName;
	}

	public void setLicenseeName(String licenseeName) {
		this.licenseeName = licenseeName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getAdhar() {
		return adhar;
	}

	public void setAdhar(String adhar) {
		this.adhar = adhar;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getValidUpto() {
		return validUpto;
	}

	public void setValidUpto(Date validUpto) {
		this.validUpto = validUpto;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactDistillery() {
		return contactDistillery;
	}

	public void setContactDistillery(String contactDistillery) {
		this.contactDistillery = contactDistillery;
	}

	public String getContactBrewery() {
		return contactBrewery;
	}

	public void setContactBrewery(String contactBrewery) {
		this.contactBrewery = contactBrewery;
	}

	// ================================================================================================================
	private ArrayList view_formData;
	private boolean valid;

	public boolean isValid() {
		valid = true;
		if (valid) {
			for (int i = 0; i < this.view_formData.size(); i++) {
				FL2A_Reciept_DetailsDatatable2 table = new FL2A_Reciept_DetailsDatatable2();
				table = (FL2A_Reciept_DetailsDatatable2) this.view_formData.get(i);
				if (!(Validate.validateStrReq("Recievedby", this.getRecievedby())))valid = false;
				//else if (!(Validate.validateStrReq("recievedfromuser", table.getReceived_from_usr())))valid = false;
				//else if (!(Validate.validateOnlyInt("recievedfromuser", table.getReceived_from_usr())))valid = false;
				else if (!(Validate.validateDate("recievingdate", this.getReceiving_date())))valid = false;
			}
		}
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public ArrayList getView_formData() {
		return view_formData;
	}

	public void setView_formData(ArrayList view_formData) {
		this.view_formData = view_formData;
	}

	// ================================================================================================================
	public void save(ActionEvent e) {

		if (isValid()) {
			impl.saveDetail(this);
		}
	}

	public void reset() {
		this.view_full_form_flag = false;
	}

	private ArrayList reciept_list;

	public ArrayList getReciept_list() {
		this.reciept_list = new FL2A_Reciept_DetailsImpl().getRecieptsList(this);
		return reciept_list;
	}

	public void setReciept_list(ArrayList reciept_list) {
		this.reciept_list = reciept_list;
	}

	public void calculatenOfBottle(ActionEvent event) {
	}
	
	
	
	
	
	//---------------------------------------------
	
	
	

	
	
	
	
	
	
	
	
	
	
	

}
