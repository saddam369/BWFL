package com.mentor.action;

import java.util.ArrayList;
import java.util.Date;
import javax.faces.event.ValueChangeEvent;

import com.mentor.impl.ReportOnDispatcherFL1FL1AImpl;

public class ReportOnDispatcherFL1FL1AAction {

	ReportOnDispatcherFL1FL1AImpl impl = new ReportOnDispatcherFL1FL1AImpl();

	private String pdfname;
	private boolean printFlag = false;
	private String radiogroup = "S";
	private Date fromdate;
	private Date todate;
	private String radioFL1andFL1A = "1";
	private String radio;
	private String successMsg;
	private String errorMsg;
	private String hidden;

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
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public String getRadioFL1andFL1A() {
		return radioFL1andFL1A;
	}

	public void setRadioFL1andFL1A(String radioFL1andFL1A) {
		this.radioFL1andFL1A = radioFL1andFL1A;
	}

	public Date getFromdate() {
		return fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public Date getTodate() {
		return todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	public String getRadiogroup() {
		return radiogroup;
	}

	public void setRadiogroup(String radiogroup) {
		this.radiogroup = radiogroup;
	}

	public String getPdfname() {
		return pdfname;
	}

	public void setPdfname(String pdfname) {
		this.pdfname = pdfname;
	}

	public boolean isPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(boolean printFlag) {
		this.printFlag = printFlag;
	}

	public void print() {

		if (this.validation() == true) {

			if (this.getRadio().trim().equals("Brewery")) {

				impl.printForAllDistrictForBrewery(this);

			} else if (this.getRadio().trim().equals("FL")) {

				impl.print_for_All_District(this);

			} else if (this.getRadio().trim().equals("CL")) {

				impl.printForCL(this);
			}

		}

	}

	public void changeRadio(ValueChangeEvent e) {

		this.errorMsg = "";
		this.successMsg = "";
	}

	public void reset() {

		this.radiogroup = "S";
		this.printFlag = false;
		this.printFlag2 = false;
		this.pdfname = "";
		this.fromdate = null;
		this.todate = null;
		this.radioFL1andFL1A = "1";
		this.districtFlag = false;
		this.errorMsg = "";
		this.successMsg = "";
		this.radio = "";
	}

	private boolean districtFlag;

	public boolean isDistrictFlag() {
		return districtFlag;
	}

	public void setDistrictFlag(boolean districtFlag) {
		this.districtFlag = districtFlag;
	}

	public void radioListiner(ValueChangeEvent e) {
		String val = (String) e.getNewValue();
		if (val.equalsIgnoreCase("1")) {
			this.districtFlag = false;
		} else {
			this.districtFlag = true;
		}
		this.printFlag = false;
		this.pdfname = "";
	}

	private int districtid;

	public int getDistrictid() {
		return districtid;
	}

	public void setDistrictid(int districtid) {
		this.districtid = districtid;
	}

	// -----------------------------------------

	private boolean printFlag2;

	public boolean isPrintFlag2() {
		return printFlag2;
	}

	public void setPrintFlag2(boolean printFlag2) {
		this.printFlag2 = printFlag2;
	}

	private ArrayList districts_list;

	public ArrayList getDistricts_list() {
		this.districts_list = impl.getdistricts(this);
		return districts_list;
	}

	public void setDistricts_list(ArrayList districts_list) {
		this.districts_list = districts_list;
	}

	public boolean validation() {

		boolean isValid = false;

		if (impl.isCheckRadio(this) && impl.isCheckFromDate(this)
				&& impl.isChecktoDate(this)) {

			isValid = true;
		}

		return isValid;
	}

}
