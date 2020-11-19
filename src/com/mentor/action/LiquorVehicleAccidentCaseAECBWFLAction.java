package com.mentor.action;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.richfaces.component.UIDataTable;

import com.mentor.datatable.LiquorVehicleAccidentCaseAECBWFLDt;
import com.mentor.impl.LiquorVehicleAccidentCaseAECBWFLImpl;


public class LiquorVehicleAccidentCaseAECBWFLAction {
	
	



		LiquorVehicleAccidentCaseAECBWFLImpl impl = new LiquorVehicleAccidentCaseAECBWFLImpl();

		private String radioType = "N";

		public String getRadioType() {
			return radioType;
		}

		public void setRadioType(String radioType) {
			this.radioType = radioType;
		}

		public void changelist(ValueChangeEvent e) {
			try {
				String val = (String) e.getNewValue();
				this.setRadioType(val);
				this.viewflag = false;
				impl.getDetails(this);
				this.display_list = impl.display_detail(this);

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

		private int distillery_id, district_id, req_id;
		private String gatepass_type, license_type, vch_from;
		private String gatepass_no;
		private Date gatepass_date;
		private String accident_address;
		private String accident_district_name;
		private Date accident_date, validity_date;
		private String hidden;
		private String name;
		private String address;
		private ArrayList display_list = new ArrayList();
		private ArrayList gatepass_list, showDataTableList = new ArrayList();
		private ArrayList brand_list = new ArrayList();
		private boolean viewflag, barcode_flag;

		public Date getValidity_date() {
			return validity_date;
		}

		public void setValidity_date(Date validity_date) {
			this.validity_date = validity_date;
		}

		public boolean isBarcode_flag() {
			return barcode_flag;
		}

		public void setBarcode_flag(boolean barcode_flag) {
			this.barcode_flag = barcode_flag;
		}

		public ArrayList getShowDataTableList() {
			return showDataTableList;
		}

		public void setShowDataTableList(ArrayList showDataTableList) {
			this.showDataTableList = showDataTableList;
		}

		public String getVch_from() {
			return vch_from;
		}

		public void setVch_from(String vch_from) {
			this.vch_from = vch_from;
		}

		public String getLicense_type() {
			return license_type;
		}

		public void setLicense_type(String license_type) {
			this.license_type = license_type;
		}

		public boolean isViewflag() {
			return viewflag;
		}

		public void setViewflag(boolean viewflag) {
			this.viewflag = viewflag;
		}

		public int getReq_id() {
			return req_id;
		}

		public void setReq_id(int req_id) {
			this.req_id = req_id;
		}

		public int getDistrict_id() {
			return district_id;
		}

		public void setDistrict_id(int district_id) {
			this.district_id = district_id;
		}

		public ArrayList getGatepass_list() {
			return gatepass_list;
		}

		public void setGatepass_list(ArrayList gatepass_list) {
			this.gatepass_list = gatepass_list;
		}

		public ArrayList getBrand_list() {
			return brand_list;
		}

		public void setBrand_list(ArrayList brand_list) {
			this.brand_list = brand_list;
		}

		public String getAccident_district_name() {
			return accident_district_name;
		}

		public void setAccident_district_name(String accident_district_name) {
			this.accident_district_name = accident_district_name;
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

		public Date getAccident_date() {
			return accident_date;
		}

		public void setAccident_date(Date accident_date) {
			this.accident_date = accident_date;
		}

		public String getHidden() {
			return hidden;
		}

		public void setHidden(String hidden) {
			this.hidden = hidden;
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

		public ArrayList getDisplay_list() {
			if (radioType != null) {
				impl.getDetails(this);
				this.display_list = impl.display_detail(this);
			}
			return display_list;
		}

		public void setDisplay_list(ArrayList display_list) {
			this.display_list = display_list;
		}

		public void view(ActionEvent e) {
			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
					.getParent();
			LiquorVehicleAccidentCaseAECBWFLDt dt = (LiquorVehicleAccidentCaseAECBWFLDt) this.display_list
					.get(uiTable.getRowIndex());

			this.setGatepass_type(dt.getGatepass_type());
			this.setGatepass_no(dt.getGatepass_no());
			this.setGatepass_date(dt.getGatepass_date());
			this.setAccident_address(dt.getAccident_address());
			this.setAccident_date(dt.getAccident_date());
			this.setAccident_district_name(dt.getAccident_district_name());
			this.setLicenseNo(dt.getLicenseNo());
			this.setLicenseNm(dt.getLicenseNm());
			this.setLicenserootDetails(dt.getLicenserootDetails());
			this.setLicenseAddrss(dt.getAccident_address());
			this.setVehcleNo(dt.getVehcleNo());
			this.setVehicaleagecyAdd(dt.getVehicaleagecyAdd());
			this.setDriverNm(dt.getDriverNm());
			this.name = dt.getDistillery_name();
			this.distillery_id = dt.getDistillery_id();
			this.req_id = dt.getReq_id();
			this.vch_from = dt.getVch_from();
			this.viewflag = true;
			this.gatepass_list = impl.gatepass_detail(this);
			this.brand_list = impl.brand_detail(this);
			impl.gatapassList(this);
		}

		public void printDetail(ActionEvent e) {
			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
					.getParent();
			LiquorVehicleAccidentCaseAECBWFLDt dt = (LiquorVehicleAccidentCaseAECBWFLDt) this.gatepass_list
					.get(uiTable.getRowIndex());
		}



		public void back() {
			this.viewflag = false;
			this.barcode_flag = false;
		}

		public String dist_name;
		private int dist_id;

		public String getDist_name() {
			return dist_name;
		}

		public void setDist_name(String dist_name) {
			this.dist_name = dist_name;
		}

		public int getDist_id() {
			return dist_id;
		}

		public void setDist_id(int dist_id) {
			this.dist_id = dist_id;
		}

		public void verfy() {
			try {

				impl.approvedupdate(this);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void reject() {
			try {
				impl.rejectdata(this);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		private String licenseNm;
		private String licenseNo;
		private String licenseAddrss;
		private String licenserootDetails;

		public String getLicenseNm() {
			return licenseNm;
		}

		public void setLicenseNm(String licenseNm) {
			this.licenseNm = licenseNm;
		}

		public String getLicenseNo() {
			return licenseNo;
		}

		public void setLicenseNo(String licenseNo) {
			this.licenseNo = licenseNo;
		}

		public String getLicenseAddrss() {
			return licenseAddrss;
		}

		public void setLicenseAddrss(String licenseAddrss) {
			this.licenseAddrss = licenseAddrss;
		}

		public String getLicenserootDetails() {
			return licenserootDetails;
		}

		public void setLicenserootDetails(String licenserootDetails) {
			this.licenserootDetails = licenserootDetails;
		}

		public String getVehcleNo() {
			return vehcleNo;
		}

		public void setVehcleNo(String vehcleNo) {
			this.vehcleNo = vehcleNo;
		}

		public String getDriverNm() {
			return driverNm;
		}

		public void setDriverNm(String driverNm) {
			this.driverNm = driverNm;
		}

		public String getVehicaleagecyAdd() {
			return vehicaleagecyAdd;
		}

		public void setVehicaleagecyAdd(String vehicaleagecyAdd) {
			this.vehicaleagecyAdd = vehicaleagecyAdd;
		}

		private String vehcleNo;
		private String driverNm;
		private String vehicaleagecyAdd;

		private Date date_list;

		public Date getDate_list() {
			return date_list;
		}

		public void setDate_list(Date date_list) {
			this.date_list = date_list;
		}

		public String getLicenseno_list() {
			return licenseno_list;
		}

		public void setLicenseno_list(String licenseno_list) {
			this.licenseno_list = licenseno_list;
		}

		public String getLicensetype_list() {
			return licensetype_list;
		}

		public void setLicensetype_list(String licensetype_list) {
			this.licensetype_list = licensetype_list;
		}

		public String getGatapassNo_list() {
			return gatapassNo_list;
		}

		public void setGatapassNo_list(String gatapassNo_list) {
			this.gatapassNo_list = gatapassNo_list;
		}

		private String licenseno_list;
		private String licensetype_list;
		private String gatapassNo_list;
		LiquorVehicleAccidentCaseAECBWFLDt bopd;

		public LiquorVehicleAccidentCaseAECBWFLDt getBopd() {
			return bopd;
		}

		public void setBopd(LiquorVehicleAccidentCaseAECBWFLDt bopd) {
			this.bopd = bopd;
		}

		public void genrateBarcode(ActionEvent e) {
			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
					.getParent();
			LiquorVehicleAccidentCaseAECBWFLDt dt = (LiquorVehicleAccidentCaseAECBWFLDt) this.display_list
					.get(uiTable.getRowIndex());
			this.barcode_flag = true;
			this.distillery_id = dt.getDistillery_id();
			this.gatepass_no = dt.getGatepass_no();
			this.gatepass_type = dt.getGatepass_type();
			this.showDataTableList = impl.getDatacl(this);

		}

		public String finalizeData(ActionEvent e) {
			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
					.getParent();
			LiquorVehicleAccidentCaseAECBWFLDt dt = (LiquorVehicleAccidentCaseAECBWFLDt) this.showDataTableList
					.get(uiTable.getRowIndex());
			try {

		if (dt.getMaped_unmaped_flag().equals("M")) {

					new LiquorVehicleAccidentCaseAECBWFLImpl().dataFinalize(this, dt);

				} else {
					new LiquorVehicleAccidentCaseAECBWFLImpl().finalizedUnmappedData(
							this, dt);

				}
				this.showDataTableList = impl.getDatacl(this);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			return "";
		}

		public String receive(ActionEvent e) {
			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
					.getParent();
			LiquorVehicleAccidentCaseAECBWFLDt dt = (LiquorVehicleAccidentCaseAECBWFLDt) this.showDataTableList
					.get(uiTable.getRowIndex());
			try {
				if (impl.recieveUpdate(this, dt)) {
					this.showDataTableList = impl.getDatacl(this);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			return "";
		}
		

	}



