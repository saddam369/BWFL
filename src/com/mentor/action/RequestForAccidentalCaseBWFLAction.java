package com.mentor.action;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.mentor.impl.RequestForAccidentalCaseBWFLImpl;
import com.sun.star.awt.ActionEvent;

public class RequestForAccidentalCaseBWFLAction {
	



		RequestForAccidentalCaseBWFLImpl impl = new RequestForAccidentalCaseBWFLImpl();

		private int distillery_id;
		private String gatepass_type;
		private String gatepass_no;
		private Date gatepass_date;
		private String accident_address;
		private String accident_district_id;
		private ArrayList accident_district_list = new ArrayList();
		private Date accident_date;
		private String hidden;
		private String name;
		private String address;
		private ArrayList display_list = new ArrayList();

		public ArrayList getDisplay_list() {
			this.display_list = impl.display_detail(this);
			return display_list;
		}

		public void setDisplay_list(ArrayList display_list) {
			this.display_list = display_list;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getHidden() {
			impl.getDetails(this);
			return hidden;
		}

		public void setHidden(String hidden) {
			this.hidden = hidden;
		}

		public int getDistillery_id() {
			return distillery_id;
		}

		public void setDistillery_id(int distillery_id) {
			this.distillery_id = distillery_id;
		}

		public String getGatepass_type() {
			return gatepass_type;
		}

		public void setGatepass_type(String gatepass_type) {
			this.gatepass_type = gatepass_type;
		}

		public String getGatepass_no() {
			return gatepass_no;
		}

		public void setGatepass_no(String gatepass_no) {
			this.gatepass_no = gatepass_no;
		}

		public Date getGatepass_date() {
			return gatepass_date;
		}

		public void setGatepass_date(Date gatepass_date) {
			this.gatepass_date = gatepass_date;
		}

		public String getAccident_address() {
			return accident_address;
		}

		public void setAccident_address(String accident_address) {
			this.accident_address = accident_address;
		}

		public String getAccident_district_id() {
			return accident_district_id;
		}

		public void setAccident_district_id(String accident_district_id) {
			this.accident_district_id = accident_district_id;
		}

		public ArrayList getAccident_district_list() {
			this.accident_district_list = impl.getDistrictList(this);
			return accident_district_list;
		}

		public void setAccident_district_list(ArrayList accident_district_list) {
			this.accident_district_list = accident_district_list;
		}

		public Date getAccident_date() {
			return accident_date;
		}

		public void setAccident_date(Date accident_date) {
			this.accident_date = accident_date;
		}

		public void save() {
			try {
				if (this.isValidate()) {
					if (impl.SaveImpl(this)) {
						this.display_list = impl.display_detail(this);

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void reset() {
			this.gatepass_type = "";
			this.gatepass_no = null;
			this.gatepass_date = null;
			this.accident_address = null;
			this.accident_date = null;
			this.accident_district_list.clear();
			this.accident_district_id = null;
		}

		private boolean validate;

		public boolean isValidate() {
			validate = true;
			if (this.gatepass_type == null || this.gatepass_type.trim() == ""
					|| this.gatepass_type.trim().length() == 0) {
				validate = false;
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Select gatepass type !",
								"Select gatepass type !"));
			} else if (this.gatepass_no == null || this.gatepass_no.trim() == ""
					|| this.gatepass_no.trim().length() == 0) {
				validate = false;
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Enter gatepass Number !",
								"Enter gatepass Number !"));
			} else if (this.gatepass_date == null) {
				validate = false;
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Select gatepass date !",
								"Select gatepass date !"));
			} else if (this.accident_address == null
					|| this.accident_address.trim() == ""
					|| this.accident_address.trim().length() == 0) {
				validate = false;
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Enter accident location address !",
								"Enter accident location address !"));
			} else if (this.accident_district_id == null
					|| this.accident_district_id.trim() == ""
					|| this.accident_district_id.trim().length() == 0) {
				validate = false;
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Select accident location district !",
								"Select accident location district !"));
			} else if (this.accident_date == null) {
				validate = false;
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Select accident date !",
								"Select accident date !"));
			}
			return validate;
		}

		public void setValidate(boolean validate) {
			this.validate = validate;
		}

		private int bwflId;
		private String bwflName;
		private String bwflAdrs;
		private int bwflLicenseTypeId;

		public int getBwflId() {
			return bwflId;
		}

		public void setBwflId(int bwflId) {
			this.bwflId = bwflId;
		}

		public String getBwflName() {
			return bwflName;
		}

		public void setBwflName(String bwflName) {
			this.bwflName = bwflName;
		}

		public String getBwflAdrs() {
			return bwflAdrs;
		}

		public void setBwflAdrs(String bwflAdrs) {
			this.bwflAdrs = bwflAdrs;
		}

		public int getBwflLicenseTypeId() {
			return bwflLicenseTypeId;
		}

		public void setBwflLicenseTypeId(int bwflLicenseTypeId) {
			this.bwflLicenseTypeId = bwflLicenseTypeId;
		}

		public String gatepassMethod(ActionEvent eve) {

			System.out.println("in fetch event ------");

			impl.displayImpl(this);
			

			return "";
		}

		/*
		 * public String gatepassMethod(ValueChangeEvent vce) { try { String val =
		 * (String) vce.getNewValue(); // int
		 * vat_id=Integer.parseInt(String.valueOf(val)); this.setGatepass_no(val);
		 * impl.displayImpl(this, val);
		 * 
		 * } catch (Exception e) {
		 * 
		 * }
		 * 
		 * return "";
		 * 
		 * }
		 */
		private String vch_type;
		private String vch_licNo;

		public String getVch_type() {
			return vch_type;
		}

		public void setVch_type(String vch_type) {
			this.vch_type = vch_type;
		}

		public String getVch_licNo() {
			return vch_licNo;
		}

		public void setVch_licNo(String vch_licNo) {
			this.vch_licNo = vch_licNo;
		}

	}



