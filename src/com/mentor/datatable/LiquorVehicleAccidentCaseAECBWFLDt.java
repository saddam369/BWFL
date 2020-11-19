package com.mentor.datatable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LiquorVehicleAccidentCaseAECBWFLDt {


	

		private int srno, sr1, sr2, damage_box, damage_bottle, return_box,
				return_bottle, prcced_dispatch_box, prcced_dispatch_bottle,
				int_brand_id, int_pack_id;
		private int req_id, distillery_id;
		private String gatepass_type;
		private String gatepass_no;
		private Date gatepass_date;
		private String accident_address;
		private String accident_district_name;
		private Date accident_date;
		private Date req_dt, finalised_date, valid_up_to;

		private String brand_name;
		private String size, finalizedDateString;
		private int box_size, int_liquor_type;
		private int dispatch_box;
		private int dispatch_bottle, showDataTable_breakgae;
		private String license_type, vch_from, license_no, distillery_name,
				pdfname, maped_unmaped_flag, code_generate_through, seqform, seqto,
				caseseq, finalized_falg;
		private boolean finalizePrint, printFlag;
		private double quantity;

		// =============showDataTable===============================

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date = formatter.format(new Date());
		// Date date1 = formatter.parse(date);
		private int plan_id;
		private Date showDataTable_Date;
		private Date receive_date;
		private String showDataTable_LiqureType;
		private String showDataTable_LicenceType;
		private String showDataTable_Brand;
		private String showDataTable_Packging;
		private String showDataTable_Quntity;
		private String showDataTable_PlanNoOfBottling;
		private String showDataTable_NoOfBoxes, receive_flg;

		public Date getValid_up_to() {
			return valid_up_to;
		}

		public void setValid_up_to(Date valid_up_to) {
			this.valid_up_to = valid_up_to;
		}

		public String getReceive_flg() {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String date = formatter.format(new Date());
			Date date1;
			try {

				date1 = formatter.parse(date);
				if (this.receive_flg.equalsIgnoreCase("N")) {
					this.receive_date = date1;
				}
			} catch (ParseException e) {

				e.printStackTrace();
			}

			return receive_flg;
		}

		public void setReceive_flg(String receive_flg) {
			this.receive_flg = receive_flg;
		}

		public Date getReceive_date() {
			return receive_date;
		}

		public void setReceive_date(Date receive_date) {
			this.receive_date = receive_date;
		}

		public int getShowDataTable_breakgae() {
			return showDataTable_breakgae;
		}

		public void setShowDataTable_breakgae(int showDataTable_breakgae) {
			this.showDataTable_breakgae = showDataTable_breakgae;
		}

		public String getFinalizedDateString() {
			return finalizedDateString;
		}

		public void setFinalizedDateString(String finalizedDateString) {
			this.finalizedDateString = finalizedDateString;
		}

		public int getPlan_id() {
			return plan_id;
		}

		public Date getFinalised_date() {
			return finalised_date;
		}

		public void setFinalised_date(Date finalised_date) {
			this.finalised_date = finalised_date;
		}

		public String getCode_generate_through() {
			return code_generate_through;
		}

		public void setCode_generate_through(String code_generate_through) {
			this.code_generate_through = code_generate_through;
		}

		public String getSeqform() {
			return seqform;
		}

		public void setSeqform(String seqform) {
			this.seqform = seqform;
		}

		public String getSeqto() {
			return seqto;
		}

		public void setSeqto(String seqto) {
			this.seqto = seqto;
		}

		public String getCaseseq() {
			return caseseq;
		}

		public void setCaseseq(String caseseq) {
			this.caseseq = caseseq;
		}

		public String getFinalized_falg() {
			return finalized_falg;
		}

		public void setFinalized_falg(String finalized_falg) {
			this.finalized_falg = finalized_falg;
		}

		public void setPlan_id(int plan_id) {
			this.plan_id = plan_id;
		}

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

		public double getQuantity() {
			return quantity;
		}

		public void setQuantity(double quantity) {
			this.quantity = quantity;
		}

		public int getInt_liquor_type() {
			return int_liquor_type;
		}

		public void setInt_liquor_type(int int_liquor_type) {
			this.int_liquor_type = int_liquor_type;
		}

		public String getVch_from() {
			return vch_from;
		}

		public void setVch_from(String vch_from) {
			this.vch_from = vch_from;
		}

		public String getMaped_unmaped_flag() {
			return maped_unmaped_flag;
		}

		public void setMaped_unmaped_flag(String maped_unmaped_flag) {
			this.maped_unmaped_flag = maped_unmaped_flag;
		}

		public boolean isPrintFlag() {
			return printFlag;
		}

		public void setPrintFlag(boolean printFlag) {
			this.printFlag = printFlag;
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

		public int getDamage_box() {
			return damage_box;
		}

		public void setDamage_box(int damage_box) {
			this.damage_box = damage_box;
		}

		public int getDamage_bottle() {
			return damage_bottle;
		}

		public void setDamage_bottle(int damage_bottle) {
			this.damage_bottle = damage_bottle;
		}

		public int getReturn_box() {
			return return_box;
		}

		public void setReturn_box(int return_box) {
			this.return_box = return_box;
		}

		public int getReturn_bottle() {
			return return_bottle;
		}

		public void setReturn_bottle(int return_bottle) {
			this.return_bottle = return_bottle;
		}

		public int getPrcced_dispatch_box() {
			return prcced_dispatch_box;
		}

		public void setPrcced_dispatch_box(int prcced_dispatch_box) {
			this.prcced_dispatch_box = prcced_dispatch_box;
		}

		public int getPrcced_dispatch_bottle() {
			return prcced_dispatch_bottle;
		}

		public void setPrcced_dispatch_bottle(int prcced_dispatch_bottle) {
			this.prcced_dispatch_bottle = prcced_dispatch_bottle;
		}

		public String getPdfname() {
			return pdfname;
		}

		public void setPdfname(String pdfname) {
			this.pdfname = pdfname;
		}

		public boolean isFinalizePrint() {
			return finalizePrint;
		}

		public void setFinalizePrint(boolean finalizePrint) {
			this.finalizePrint = finalizePrint;
		}

		public int getSr1() {
			return sr1;
		}

		public void setSr1(int sr1) {
			this.sr1 = sr1;
		}

		public int getSr2() {
			return sr2;
		}

		public void setSr2(int sr2) {
			this.sr2 = sr2;
		}

		public String getDistillery_name() {
			return distillery_name;
		}

		public void setDistillery_name(String distillery_name) {
			this.distillery_name = distillery_name;
		}

		public String getLicense_type() {
			return license_type;
		}

		public void setLicense_type(String license_type) {
			this.license_type = license_type;
		}

		public String getLicense_no() {
			return license_no;
		}

		public void setLicense_no(String license_no) {
			this.license_no = license_no;
		}

		public String getBrand_name() {
			return brand_name;
		}

		public void setBrand_name(String brand_name) {
			this.brand_name = brand_name;
		}

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}

		public int getBox_size() {
			return box_size;
		}

		public void setBox_size(int box_size) {
			this.box_size = box_size;
		}

		public int getDispatch_box() {
			return dispatch_box;
		}

		public void setDispatch_box(int dispatch_box) {
			this.dispatch_box = dispatch_box;
		}

		public int getDispatch_bottle() {
			return dispatch_bottle;
		}

		public void setDispatch_bottle(int dispatch_bottle) {
			this.dispatch_bottle = dispatch_bottle;
		}

		public int getSrno() {
			return srno;
		}

		public void setSrno(int srno) {
			this.srno = srno;
		}

		public int getReq_id() {
			return req_id;
		}

		public void setReq_id(int req_id) {
			this.req_id = req_id;
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

		public String getAccident_district_name() {
			return accident_district_name;
		}

		public void setAccident_district_name(String accident_district_name) {
			this.accident_district_name = accident_district_name;
		}

		public Date getAccident_date() {
			return accident_date;
		}

		public void setAccident_date(Date accident_date) {
			this.accident_date = accident_date;
		}

		public Date getReq_dt() {
			return req_dt;
		}

		public void setReq_dt(Date req_dt) {
			this.req_dt = req_dt;
		}

		private String status;

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		private String licenseNm;

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

		private String licenseNo;
		private String licenseAddrss;
		private String licenserootDetails;
		private String vehcleNo;
		private String driverNm;
		private String vehicaleagecyAdd;
		private int group_id;

		public int getGroup_id() {
			return group_id;
		}

		public void setGroup_id(int group_id) {
			this.group_id = group_id;
		}
		
		private int showDataTabl_barcode_bottle;

		public int getShowDataTabl_barcode_bottle() {
			return showDataTabl_barcode_bottle;
		}

		public void setShowDataTabl_barcode_bottle(int showDataTabl_barcode_bottle) {
			this.showDataTabl_barcode_bottle = showDataTabl_barcode_bottle;
		}


	}



