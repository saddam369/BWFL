package com.mentor.action;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.event.ValueChangeEvent;

import com.mentor.impl.BWFLRegistrationDetailsImpl;
import com.mentor.utility.Validate;

public class BWFLRegistrationDetailsAction {

	BWFLRegistrationDetailsImpl impl = new BWFLRegistrationDetailsImpl();

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String successMsg;
	public String errorMsg;
	private String hidden;
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
	private boolean flag = true;
	private String breweryId;
	private ArrayList breweryList = new ArrayList();
	private boolean breweryFlag;
	private boolean distilleryFlag;
	private String distilleryId;
	private ArrayList distilleryList = new ArrayList();
	private String contactDistillery;
	private String contactBrewery;
	private boolean distContactFlag;
	private boolean breweryContactFlag;
    private String dbAddress;
	
	
	
	public String getDbAddress() {
		return dbAddress;
	}

	public void setDbAddress(String dbAddress) {
		this.dbAddress = dbAddress;
	}

	public boolean isDistContactFlag() {
		return distContactFlag;
	}

	public void setDistContactFlag(boolean distContactFlag) {
		this.distContactFlag = distContactFlag;
	}

	public boolean isBreweryContactFlag() {
		return breweryContactFlag;
	}

	public void setBreweryContactFlag(boolean breweryContactFlag) {
		this.breweryContactFlag = breweryContactFlag;
	}

	public String getContactBrewery() {
		return contactBrewery;
	}

	public void setContactBrewery(String contactBrewery) {
		this.contactBrewery = contactBrewery;
	}

	public String getContactDistillery() {
		return contactDistillery;
	}

	public void setContactDistillery(String contactDistillery) {
		this.contactDistillery = contactDistillery;
	}

	

	public ArrayList getDistilleryList() {

		if (this.distilleryFlag == true) {

			this.distilleryList = impl.getDistilleryList();

		}
		return distilleryList;
	}

	public void setDistilleryList(ArrayList distilleryList) {
		this.distilleryList = distilleryList;
	}

	public boolean isDistilleryFlag() {
		return distilleryFlag;
	}

	public void setDistilleryFlag(boolean distilleryFlag) {
		this.distilleryFlag = distilleryFlag;
	}

	public boolean isBreweryFlag() {
		return breweryFlag;
	}

	public void setBreweryFlag(boolean breweryFlag) {
		this.breweryFlag = breweryFlag;
	}

	

	public String getBreweryId() {
		return breweryId;
	}

	public void setBreweryId(String breweryId) {
		this.breweryId = breweryId;
	}

	public String getDistilleryId() {
		return distilleryId;
	}

	public void setDistilleryId(String distilleryId) {
		this.distilleryId = distilleryId;
	}

	public ArrayList getBreweryList() {

		if (this.breweryFlag == true) {

			this.breweryList = impl.getBreweryList();
		}
		return breweryList;
	}

	public void setBreweryList(ArrayList breweryList) {
		this.breweryList = breweryList;
	}

	public String getSuccessMsg() {
		return successMsg;
	}

	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getHidden() {

		if (this.isFlag() == true) {

			impl.getData(this);
			this.flag = false;
		}

		return hidden;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
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

	public void save() {
		if (this.validation() == true) {			
			impl.updateMethod(this);
		}
	}

	public void reset() {

		this.errorMsg = "";
		this.successMsg = "";
		this.flag = true;

	}

	public boolean validation() {
		boolean isValid = false;		
		if (impl.isEmail(this) && impl.isAge(this) && impl.isAdhar(this) && impl.isContactAlreadyExist(this)) {
			isValid = true;
		}
		if(!(Validate.validateStrReq("mobile",this.getContactDistillery())))isValid=false;
		else if(!(Validate.validateOnlyInt("mobile",this.getContactDistillery())))isValid=false;		
		return isValid;
	}

	private String radio;

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public void changeRadio(ValueChangeEvent e) {

		String obj = (String) e.getNewValue();

		this.flag = true;
		this.radio = obj;

		if (obj.trim().equals("Distillery")) {

			this.distilleryFlag = true;
			this.breweryFlag = false;
			 

		} else if (obj.trim().equals("Brewery")) {

			this.distilleryFlag = false;
			this.breweryFlag = true;
			 

		}
		
		else if (obj.trim().equals("Winery")) {

			this.distilleryFlag = false;
			this.breweryFlag = true;
			 

		}
		else {

			this.breweryFlag = false;
			this.distilleryFlag = false;
		}
	}

}
