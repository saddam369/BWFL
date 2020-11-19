package com.mentor.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.richfaces.component.UIDataTable;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.mentor.datatable.BWFLImportDataTable;
import com.mentor.impl.BWFLImportImpl;
import com.mentor.utility.Constants;
import com.mentor.utility.Validate;

public class BWFLImportAction {

	BWFLImportImpl impl = new BWFLImportImpl();

	private int id;
	private String permitno = "";
	private Date permitdt = new Date();
	private Date validityDate;
	private String permitNoEnterd;
	private boolean update;
	private int etin_unit_id;
	private int district;
	private String select_lic_no;
    private ArrayList licno=new ArrayList();
    
    private String popup="N";
    private BWFLImportDataTable datatable;
    
    private boolean data_list_reload_Flag=true;
    
    
    
    
    
    
    
    
    
	public boolean isData_list_reload_Flag() {
		return data_list_reload_Flag;
	}

	public void setData_list_reload_Flag(boolean data_list_reload_Flag) {
		this.data_list_reload_Flag = data_list_reload_Flag;
	}

	public BWFLImportDataTable getDatatable() {
		return datatable;
	}

	public void setDatatable(BWFLImportDataTable datatable) {
		this.datatable = datatable;
	}

	public String getPopup() {
		return popup;
	}

	public void setPopup(String popup) {
		this.popup = popup;
	}

	public ArrayList getLicno() {
		
		try{
			this.licno=new BWFLImportImpl().getLicense();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return licno;
	}

	public void setLicno(ArrayList licno) {
		this.licno = licno;
	}

	public String getSelect_lic_no() {
		return select_lic_no;
	}

	public void setSelect_lic_no(String select_lic_no) {
		this.select_lic_no = select_lic_no;
	}

	public int getDistrict() {
		return district;
	}

	public void setDistrict(int district) {
		this.district = district;
	}

	public int getEtin_unit_id() {
		return etin_unit_id;
	}

	public void setEtin_unit_id(int etin_unit_id) {
		this.etin_unit_id = etin_unit_id;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public String getPermitNoEnterd() {
		return permitNoEnterd;
	}

	public void setPermitNoEnterd(String permitNoEnterd) {
		this.permitNoEnterd = permitNoEnterd;
	}

	public Date getValidityDate() {
		try {

			if (bottlngType.equals("U")) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(this.permitdt);
				cal.add(Calendar.MONTH, 1);
				this.validityDate = cal.getTime();
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return validityDate;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}

	private String bottlngType;

	public String getBottlngType() {
		return bottlngType;
	}

	public void setBottlngType(String bottlngType) {
		this.bottlngType = bottlngType;
	}

	public String getPermitno() {
		return permitno;
	}

	public void setPermitno(String permitno) {
		this.permitno = permitno;
	}

	public Date getPermitdt() {
		return permitdt;
	}

	public void setPermitdt(Date permitdt) {
		this.permitdt = permitdt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int receiptLiquor;

	public int getReceiptLiquor() {
		return receiptLiquor;
	}

	public void setReceiptLiquor(int receiptLiquor) {
		this.receiptLiquor = receiptLiquor;
	}

	private String distillery_id = "0";
	private String distillery_nm;
	private String distillery_adrs;
	private String hidden;
	private Date dateOfBottling;

	private String liqureTypeId;
	private ArrayList liqureTypeList = new ArrayList();
	private String licenceType = "0";

	private ArrayList brandPackagingDataList = new ArrayList();

	private ArrayList showDataTableList = new ArrayList();

	private boolean addRowFlagPackge = true;

	private String licenceNum;
	private ArrayList licenceNoList = new ArrayList();

	private Date cr_date;

	private String checkLicenceType;
	private ArrayList unitlist = new ArrayList();

	public ArrayList getUnitlist() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return unitlist;
	}

	public void setUnitlist(ArrayList unitlist) {
		this.unitlist = unitlist;
	}

	public String getCheckLicenceType() {
		return checkLicenceType;
	}

	public void setCheckLicenceType(String checkLicenceType) {
		this.checkLicenceType = checkLicenceType;
	}

	public Date getCr_date() {
		return cr_date;
	}

	public void setCr_date(Date cr_date) {
		this.cr_date = cr_date;
	}

	public String getLicenceNum() {
		return licenceNum;
	}

	public void setLicenceNum(String licenceNum) {
		this.licenceNum = licenceNum;
	}

	public ArrayList getLicenceNoList() {
		try {
			// this.licenceNoList = impl.getLicenseNo(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return licenceNoList;
	}

	public void setLicenceNoList(ArrayList licenceNoList) {
		this.licenceNoList = licenceNoList;
	}

	public String addRowMethodPackaging() {

		BWFLImportDataTable dt = new BWFLImportDataTable();
		dt.setSno(brandPackagingDataList.size() + 1);
		brandPackagingDataList.add(dt);

		return "";
	}

	public void deleteRowMethodPackaging(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		BWFLImportDataTable dt = (BWFLImportDataTable) this.brandPackagingDataList
				.get(uiTable.getRowIndex());
		this.brandPackagingDataList.remove(dt);
	}

	public boolean isAddRowFlagPackge() {
		return addRowFlagPackge;
	}

	public void setAddRowFlagPackge(boolean addRowFlagPackge) {
		this.addRowFlagPackge = addRowFlagPackge;
	}

	public Date getDateOfBottling() {
		return dateOfBottling;
	}

	public void setDateOfBottling(Date dateOfBottling) {
		this.dateOfBottling = dateOfBottling;
	}

	public String getLiqureTypeId() {
		return liqureTypeId;
	}

	public void setLiqureTypeId(String liqureTypeId) {
		this.liqureTypeId = liqureTypeId;
	}

	public ArrayList getLiqureTypeList() {
		this.liqureTypeList = impl.getLiqureType();
		return liqureTypeList;
	}

	public void setLiqureTypeList(ArrayList liqureTypeList) {
		this.liqureTypeList = liqureTypeList;
	}

	public String getLicenceType() {

		return licenceType;
	}

	private boolean flagNew = false;

	public boolean isFlagNew() {
		return flagNew;
	}

	public void setFlagNew(boolean flagNew) {
		this.flagNew = flagNew;
	}

	public void setLicenceType(String licenceType) {
		this.licenceType = licenceType;
	}

	public ArrayList getBrandPackagingDataList() {

		// this.brandPackagingDataList=impl.getAddRowData(this);
		return brandPackagingDataList;
	}

	public void setBrandPackagingDataList(ArrayList brandPackagingDataList) {
		this.brandPackagingDataList = brandPackagingDataList;
	}

	public ArrayList getShowDataTableList() {
		this.showDataTableList = impl.getData(this);
		return showDataTableList;
	}

	public void setShowDataTableList(ArrayList showDataTableList) {
		this.showDataTableList = showDataTableList;
	}

	public String getDistillery_id() {
		return distillery_id;
	}

	public void setDistillery_id(String distillery_id) {
		this.distillery_id = distillery_id;
	}

	public String getDistillery_nm() {
		return distillery_nm;
	}

	public void setDistillery_nm(String distillery_nm) {
		this.distillery_nm = distillery_nm;
	}

	public String getDistillery_adrs() {
		return distillery_adrs;
	}

	public void setDistillery_adrs(String distillery_adrs) {
		this.distillery_adrs = distillery_adrs;
	}

	public String mappedhidden;

	public String getMappedhidden() {
		try {

			impl.getId(this);
			// impl.getSugarmill(this);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return mappedhidden;
	}

	public void setMappedhidden(String mappedhidden) {
		this.mappedhidden = mappedhidden;
	}

	public String getHidden() {
		try {

			// impl.getId(this);
			// impl.getSugarmill(this);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;

	}

	public void licencelistener(ValueChangeEvent event) {
		Object obj = event.getNewValue();

		String id = String.valueOf(obj);
		this.licenceType = id;
		// this.licenceNoList = impl.getLicenseNo(this, id);

	}

	public void getaddrowdata(ValueChangeEvent event) {
		try {
			Object obj = event.getNewValue();
			String id = String.valueOf(obj);
			//System.out.println("id="+id);
			if(id!=null && id.length()>0 && id!="" ){
			impl.getLicNoUnitId(this, id);
			}
			this.distillery_id=id;
			//this.licenceNum = impl.getLicenseNo(this, id);			
			//this.brandPackagingDataList = impl.getAddRowData(this,this.getLicenceType());
//System.out.println("distillery_id="+distillery_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getunit(ValueChangeEvent event) {
		Object obj = event.getNewValue();
		String id = String.valueOf(obj);
		this.unitlist = impl.getUnit(id);

	}

	// --------------------------------------

	public void save() throws ParseException {
		try {

			if (isValidateInput()) {

				Date dt = new Date();
				Calendar c = Calendar.getInstance();
				c.setTime(dt);
				c.add(Calendar.DATE, 1);
				dt = c.getTime();

				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				/*if (impl.checkpermit(permitNoEnterd, this) == true) {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Permit Already Exists !!!","Permit Already Exists !!!"));
				} else {
					if (this.update) {
						impl.update(this);
					} else {
						impl.save(this);
					}
				}*/
				
				 
					if (this.update) {
						impl.update(this);
					} else {
						//@rvind maurya
						if (impl.checkpermit(permitNoEnterd, this) == true) {
							FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Permit Already Exists !!!","Permit Already Exists !!!"));
						}
						else{
							impl.save(this);
						}
						
					}
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void reset() {
		this.liqureTypeId = "";
		this.liqureTypeList.clear();
		this.licenceType = "0";
		this.licenceNum = "";
		this.licenceNoList.clear();
		this.brandPackagingDataList.clear();
		this.permitNoEnterd = "";
		this.distillery_id = "0";
		this.disabledFlag = false;
		this.bottlngType = "";
		this.validityDate = null;
		this.setUpdate(false);
		this.db_totalFees = 0;
	}

	private boolean validateInput;

	public boolean isValidateInput() {

		this.validateInput = true;

		if (!(Validate.validateStrReq("LicenseType", this.getLicenceType())))
			validateInput = false;

		else if (!(Validate.validateStrReq("licNo", this.getLicenceNum())))
			validateInput = false;
		else if (!(Validate.validateStrReq("bottlngType", this.getBottlngType())))
			validateInput = false;
		
		else if (!(Validate.validateDate("permitdt", this.getPermitdt())))
			validateInput = false;
		else if (!(Validate.validateDate("validityDate",this.getValidityDate())))
			validateInput = false;
		else if (this.getValidityDate().before(this.getPermitdt())) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(
							"Validity Date Can Not be less than Permit Date",
							"Validity Date Can Not be less than Permit Date"));
			validateInput = false;
		} else if (!(Validate.validateStrReq("permitNo",this.getPermitNoEnterd())))
			validateInput = false; 
		else if (!(Validate.validateStrReq("validityDate", String.valueOf(this.getValidityDate()))))
			validateInput = false;
		if (!(Validate.validateStrReq("distillery_id", this.getDistillery_id())))
			validateInput = false;
		if (validateInput) {
			if (this.brandPackagingDataList.size() > 0) {
				for (int i = 0; i < brandPackagingDataList.size(); i++) {
					BWFLImportDataTable table = new BWFLImportDataTable();
					table = (BWFLImportDataTable) brandPackagingDataList.get(i);
					if (!(Validate.validateStrReqRow(i, "Brand",table.getBrandPackagingData_Brand())))
						validateInput = false;
					else if (!(Validate.validateStrReqRow(i, "Packaging",table.getBrandPackagingData_Packaging())))
						validateInput = false;
				else if (!(Validate.validateStrReqRow(i, "Packaging",table.getBrandPackagingData_Packaging())))
						validateInput = false;
					else if (!(Validate.validateStrReqRow(i, "Quantity",table.getBrandPackagingData_Quantity())))
						validateInput = false;
					 
					else if (!(Validate.validateStrReqRow(i, "etin",table.getBrandPackagingData_etinNmbr())))
						validateInput = false;
					else if (!(Validate.validateDouble("NoOfBoxes",table.getBrandPackagingData_NoOfBoxes())))
						validateInput = false;
					else if (!(Validate.validateDouble("bottle_per_case",table.getEntry_no_of_bottle_per_case())))
						validateInput = false;
					else if (!(Validate.validateInteger("PlanNoOfBottling",table.getBrandPackagingData_PlanNoOfBottling())))
						validateInput = false;
					// else  
					// if(!(Validate.validateStrReqRow(i,"",String.valueOf(table.getCapacitySprit()))))validateInput=false;
					// else
					// if(!(Validate.validateDouble("installdCap",table.getCapacitySprit())))validateInput=false;
				}
			}

		}

		return validateInput;
	}

	public void setValidateInput(boolean validateInput) {
		this.validateInput = validateInput;
	}

	BWFLImportDataTable bopd;

	public BWFLImportDataTable getBopd() {
		return bopd;
	}

	public void setBopd(BWFLImportDataTable bopd) {
		this.bopd = bopd;
	}

	public String finalizeData() {

		try {
			new BWFLImportImpl().dataFinalize(this, bopd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
	private String int_distillery_id;
	private int int_brand_id;
	private int int_pack_id;
	private Date plan_dt_popup;
	private String permitno_popup;
	private int seq;
	private String remark;
	public String getInt_distillery_id() {
		return int_distillery_id;
	}
	public void setInt_distillery_id(String int_distillery_id) {
		this.int_distillery_id = int_distillery_id;
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
	public Date getPlan_dt_popup() {
		return plan_dt_popup;
	}

	public void setPlan_dt_popup(Date plan_dt_popup) {
		this.plan_dt_popup = plan_dt_popup;
	}

	public String getPermitno_popup() {
		return permitno_popup;
	}

	public void setPermitno_popup(String permitno_popup) {
		this.permitno_popup = permitno_popup;
	}

	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

/*	public void cancelData(ActionEvent e) {
		try {
			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
			BWFLImportDataTable dt = (BWFLImportDataTable) this.showDataTableList.get(uiTable.getRowIndex());             
			this.int_distillery_id=dt.getDistillery_id();
			this.int_brand_id=dt.getBrandId();
			this.int_pack_id=dt.getPackagingId();
			this.plan_dt_popup=dt.getShowDataTable_Date();
			this.permitno_popup=dt.getPermitnoD();
			this.seq=dt.getSeq();
			 
			 
			 
			 
			 
			 
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		 
	}*/

	private String pdfval = "/";

	public String getPdfval() {
		return pdfval;
	}

	public void setPdfval(String pdfval) {
		this.pdfval = pdfval;
	}

	public void print() {

		try {
			// new BWFLImportImpl().generateReport(bopd, this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ------------------display code--------------------

	private ArrayList displayDataList = new ArrayList();

	public ArrayList getDisplayDataList() {
		try {

			this.displayDataList = impl.getDisplayList(this);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return displayDataList;
	}

	public void setDisplayDataList(ArrayList displayDataList) {
		this.displayDataList = displayDataList;
	}

	// --------------------------------------deleteData-----------------------------
	// @rvind
	private Date plan_dt;

	public Date getPlan_dt() {
		return plan_dt;
	}

	public void setPlan_dt(Date plan_dt) {
		this.plan_dt = plan_dt;
	}

	public void deleteData(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		BWFLImportDataTable dt = (BWFLImportDataTable) this.displayDataList
				.get(uiTable.getRowIndex());

		/*
		 * int_distillery_id- Integer.parseInt( action.getDistillery_id())
		 * ,int_brand_id- Integer.parseInt(table.getBrandPackagingData_Brand())
		 * ,int_pack_id-
		 * Integer.parseInt(table.getBrandPackagingData_Packaging()) ,plan_dt-
		 * Utility.convertUtilDateToSQLDate(new Date()) ,permitno-
		 * this.getIddeo(
		 * action)+"-2018-19-"+action.getPermitNoEnterd()+"-"+challanId
		 */

		this.setInt_distillery_idUpdate(dt.getDistillery_id());// ---------
		this.setInt_brand_idUpdate(dt.getInt_brand_idUpdate());// -----------
		this.setInt_pack_idUpdate(dt.getInt_pack_idUpdate());// ----------
		this.setPlan_dt(dt.getPlan_dt());// --
		this.setPermitNoNew(dt.getPermitNmbr_dt());// -------------
		

		this.setPermitno_enteredUpdate(dt.getPermitno_enteredUpdate());
		this.setVch_license_typeUpdate(dt.getLicenseType_dt());
		this.setInt_distillery_idUpdate(dt.getDistillery_id());
		this.setInt_brand_idUpdate(dt.getInt_brand_idUpdate());
		this.setInt_pack_idUpdate(dt.getInt_pack_idUpdate());
		this.setSeqUpdate(dt.getSeqUpdate());
		this.setLicence_noUpdate(dt.getLicenseNmbr_dt());
		this.setGatepass_noUpdate(dt.getGatepass_noUpdate());
		this.setPermitDatUpdate(dt.getPermitDt_dt());

		// this.setEtinUpdate(dt.getEtinUpdate());
		this.setPermitDateNew(dt.getPermitDt_dt());
		this.setLicenseNoNew(dt.getLicenseNmbr_dt());
		this.setPermitNoNew(dt.getPermitNmbr_dt());
		this.brandPackagingDataList = impl.getNewData(this);
		this.unitlist = impl.getUnit_For_Update(this.licenceType,this.licenseNoNew, this);
		this.dutyRegisterId=impl.getDutyRegisterId(dt.getPermitNmbr_dt());
		//System.out.println("dutyRegisterId="+this.dutyRegisterId);
		getDistillery_id();
		this.setDistillery_id(dt.getDistillery_id());
		impl.getLicNoUnitId(this, dt.getDistillery_id());
		
		if (impl.delete(this) == 1) {
			//System.out.println("record deleted");
		} else {
			//System.out.println("record not deleted");
		}

	}

	// --------------------------------------updateData-----------------------------

	public void updateData(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		BWFLImportDataTable dt = (BWFLImportDataTable) this.displayDataList
				.get(uiTable.getRowIndex());

		this.setPermitno_enteredUpdate(dt.getPermitno_enteredUpdate());
		this.setVch_license_typeUpdate(dt.getLicenseType_dt());
		this.setInt_distillery_idUpdate(dt.getDistillery_id());
		this.setInt_brand_idUpdate(dt.getInt_brand_idUpdate());
		this.setInt_pack_idUpdate(dt.getInt_pack_idUpdate());
		this.setSeqUpdate(dt.getSeqUpdate());
		this.setLicence_noUpdate(dt.getLicenseNmbr_dt());
		this.setGatepass_noUpdate(dt.getGatepass_noUpdate());
		this.setPermitDatUpdate(dt.getPermitDt_dt());

		// this.setEtinUpdate(dt.getEtinUpdate());
		this.setPermitDateNew(dt.getPermitDt_dt());
		this.setLicenseNoNew(dt.getLicenseNmbr_dt());
		this.setPermitNoNew(dt.getPermitNmbr_dt());
		this.brandPackagingDataList = impl.getNewData(this);
		this.unitlist = impl.getUnit_For_Update(this.licenceType,this.licenseNoNew, this);
		this.dutyRegisterId=impl.getDutyRegisterId(dt.getPermitNmbr_dt());
		//System.out.println("dutyRegisterId="+this.dutyRegisterId);
		getDistillery_id();
		this.setDistillery_id(dt.getDistillery_id());
		impl.getLicNoUnitId(this, dt.getDistillery_id());
		dt.getBrandPackagingData_BrandList();

		this.setUpdate(true);
	}

	private String permitno_enteredUpdate;
	private String vch_license_typeUpdate;
	private String int_distillery_idUpdate;
	private String int_brand_idUpdate;
	private String int_pack_idUpdate;
	private int seqUpdate;
	private String licence_noUpdate;
	private String gatepass_noUpdate;
	private Date permitDatUpdate;
	private String etinUpdate;

	private String permitNoNew;
	private String licenseNoNew;
	private Date permitDateNew;

	private boolean disabledFlag;

	public String getEtinUpdate() {
		return etinUpdate;
	}

	public void setEtinUpdate(String etinUpdate) {
		this.etinUpdate = etinUpdate;
	}

	public boolean isDisabledFlag() {
		return disabledFlag;
	}

	public void setDisabledFlag(boolean disabledFlag) {
		this.disabledFlag = disabledFlag;
	}

	public String getPermitNoNew() {
		return permitNoNew;
	}

	public void setPermitNoNew(String permitNoNew) {
		this.permitNoNew = permitNoNew;
	}

	public String getLicenseNoNew() {
		return licenseNoNew;
	}

	public void setLicenseNoNew(String licenseNoNew) {
		this.licenseNoNew = licenseNoNew;
	}

	public Date getPermitDateNew() {
		return permitDateNew;
	}

	public void setPermitDateNew(Date permitDateNew) {
		this.permitDateNew = permitDateNew;
	}

	public String getPermitno_enteredUpdate() {
		return permitno_enteredUpdate;
	}

	public void setPermitno_enteredUpdate(String permitno_enteredUpdate) {
		this.permitno_enteredUpdate = permitno_enteredUpdate;
	}

	public String getVch_license_typeUpdate() {
		return vch_license_typeUpdate;
	}

	public void setVch_license_typeUpdate(String vch_license_typeUpdate) {
		this.vch_license_typeUpdate = vch_license_typeUpdate;
	}

	public String getInt_distillery_idUpdate() {
		return int_distillery_idUpdate;
	}

	public void setInt_distillery_idUpdate(String int_distillery_idUpdate) {
		this.int_distillery_idUpdate = int_distillery_idUpdate;
	}

	public String getInt_brand_idUpdate() {
		return int_brand_idUpdate;
	}

	public void setInt_brand_idUpdate(String int_brand_idUpdate) {
		this.int_brand_idUpdate = int_brand_idUpdate;
	}

	public String getInt_pack_idUpdate() {
		return int_pack_idUpdate;
	}

	public void setInt_pack_idUpdate(String int_pack_idUpdate) {
		this.int_pack_idUpdate = int_pack_idUpdate;
	}

	public int getSeqUpdate() {
		return seqUpdate;
	}

	public void setSeqUpdate(int seqUpdate) {
		this.seqUpdate = seqUpdate;
	}

	public String getLicence_noUpdate() {
		return licence_noUpdate;
	}

	public void setLicence_noUpdate(String licence_noUpdate) {
		this.licence_noUpdate = licence_noUpdate;
	}

	public String getGatepass_noUpdate() {
		return gatepass_noUpdate;
	}

	public void setGatepass_noUpdate(String gatepass_noUpdate) {
		this.gatepass_noUpdate = gatepass_noUpdate;
	}

	public Date getPermitDatUpdate() {
		return permitDatUpdate;
	}

	public void setPermitDatUpdate(Date permitDatUpdate) {
		this.permitDatUpdate = permitDatUpdate;
	}

	// ---------------------updateValidDate-----------------------------------------------------------------------------

	private Date newValidDate = new Date();

	public Date getNewValidDate() {
		return newValidDate;
	}

	public void setNewValidDate(Date newValidDate) {
		this.newValidDate = newValidDate;
	}

	private Date old_valid_date;

	public Date getOld_valid_date() {
		return old_valid_date;
	}

	public void setOld_valid_date(Date old_valid_date) {
		this.old_valid_date = old_valid_date;
	}

	public void updateValidDate(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		BWFLImportDataTable dt = (BWFLImportDataTable) this.displayDataListExt.get(uiTable.getRowIndex());

		this.setPermitno_enteredUpdate(dt.getPermitno_enteredUpdate());
		this.setVch_license_typeUpdate(dt.getLicenseType_dt());
		this.setInt_distillery_idUpdate(dt.getDistillery_id());
		this.setInt_brand_idUpdate(dt.getInt_brand_idUpdate());
		this.setInt_pack_idUpdate(dt.getInt_pack_idUpdate());
		this.setSeqUpdate(dt.getSeqUpdate());
		this.setLicence_noUpdate(dt.getLicenseNmbr_dt());
		this.setGatepass_noUpdate(dt.getGatepass_noUpdate());
		this.setPermitDatUpdate(dt.getPermitDt_dt());
		this.setOld_valid_date(dt.getOldvalidityDate());
		this.setPermitDateNew(dt.getPermitDt_dt());
		this.setLicenseNoNew(dt.getLicenseNmbr_dt());
		this.setPermitNoNew(dt.getPermitNmbr_dt());
		this.brandPackagingDataList = impl.getNewData(this);
		this.unitlist = impl.getUnit_For_Update(this.licenceType,this.licenseNoNew, this);

		getDistillery_id();
		this.setDistillery_id(dt.getDistillery_id());
		dt.getBrandPackagingData_BrandList();
		impl.updateValidTillExt(this, dt.getNewvalidityDate());

	}

	private ArrayList displayDataListExt = new ArrayList();

	public ArrayList getDisplayDataListExt() {

		try {
if(this.isData_list_reload_Flag())
{
			this.displayDataListExt = impl.getDisplayListExt(this);
			this.data_list_reload_Flag=false;
}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return displayDataListExt;
	}

	public void setDisplayDataListExt(ArrayList displayDataListExt) {
		this.displayDataListExt = displayDataListExt;
	}

	public double db_totalFees = 0.0;

	public double getDb_totalFees() {

		double duty = 0.0;
		try {

			for (int i = 0; i < this.brandPackagingDataList.size(); i++) {
				BWFLImportDataTable dt = (BWFLImportDataTable) this.getBrandPackagingDataList().get(i);
				duty += dt.getCalPermitFee_dt();
				//System.out.println("duty----------------" + duty);
			}
			// db_total_value=Math.round(duty);
			db_totalFees = duty;

		} catch (Exception e) {
			e.printStackTrace();
		}
		DecimalFormat formatter = new DecimalFormat("#.##");
		db_totalFees=Double.parseDouble(formatter.format(db_totalFees));
		return db_totalFees;
	}

	public void setDb_totalFees(double db_totalFees) {
		this.db_totalFees = db_totalFees;
	}

	public double sum = 0;
	
	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	// ---------------------------- calculate duty --------------
	
	public void calculateTotalFee(ActionEvent ae) {

		this.setSum(0);
		for (int i = 0; i < this.brandPackagingDataList.size(); i++) {
			BWFLImportDataTable dt = (BWFLImportDataTable) this.brandPackagingDataList.get(i);

			this.setSum(this.getSum() + dt.getCalPermitFee_dt());

		}

	}

	private int unit_id;
	//private String unit_name;
	public int getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}

/*	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}*/
	
	private int dutyRegisterId;
	public int getDutyRegisterId() {
		return dutyRegisterId;
	}

	public void setDutyRegisterId(int dutyRegisterId) {
		this.dutyRegisterId = dutyRegisterId;
	}
	public void cancelRequest() {

		try {
			if (this.remark != null && this.remark.length() > 0) {
				impl.dataCancel(this);
			} else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
				" Please Fill Remark !!","Please Fill Remark !!"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	 private BWFLImportDataTable cancelData;	
	    private String cancel_order_no;
	    private Date cancel_order_date;
	    private String uploadedPdfName;
	    private boolean popupValidate;
	    private String authority_name;
	    private boolean doc1upload;
	    private String permitNo;
	    
	    
	    public boolean isPopupValidate() {
	    	
	    	this.popupValidate=true;
	    	 if (!(Validate.validateStrReq("CancellationOrderNo", this.getCancel_order_no())))
	    		 this.popupValidate = false;
			 else if (!(Validate.validateDate("OrderDate", this.getCancel_order_date())))
				 this.popupValidate = false;
			 else 	if (!(Validate.validateStrReq("AuthorityName", this.getAuthority_name())))
				 this.popupValidate = false;

			 else 	if (!(Validate.validateBoolean("UploadPermitCancelOrder", this.doc1upload)))
				 this.popupValidate = false;
	          return popupValidate;
		}

		public String getAuthority_name() {
			return authority_name;
		}

		public void setAuthority_name(String authority_name) {
			this.authority_name = authority_name;
		}

		public boolean isDoc1upload() {
			return doc1upload;
		}

		public void setDoc1upload(boolean doc1upload) {
			this.doc1upload = doc1upload;
		}

		public String getPermitNo() {
			return permitNo;
		}

		public void setPermitNo(String permitNo) {
			this.permitNo = permitNo;
		}

		public BWFLImportDataTable getCancelData() {
			
			
			
			return cancelData;
		}

		public void setCancelData(BWFLImportDataTable cancelData) {
			this.cancelData = cancelData;
		}

		public String getCancel_order_no() {
			return cancel_order_no;
		}

		public void setCancel_order_no(String cancel_order_no) {
			this.cancel_order_no = cancel_order_no;
		}

		public Date getCancel_order_date() {
			return cancel_order_date;
		}

		public void setCancel_order_date(Date cancel_order_date) {
			this.cancel_order_date = cancel_order_date;
		}

		public String getUploadedPdfName() {
			return uploadedPdfName;
		}

		public void setUploadedPdfName(String uploadedPdfName) {
			this.uploadedPdfName = uploadedPdfName;
		}

		public void setPopupValidate(boolean popupValidate) {
			this.popupValidate = popupValidate;
		}
		
		public String showPopUpData()
		{
			try{
				System.out.println("come in   pop up check");
				this.int_distillery_id=datatable.getDistillery_id();
				this.int_brand_id=datatable.getBrandId();
				this.int_pack_id=datatable.getPackagingId();
				this.plan_dt_popup=datatable.getShowDataTable_Date();
	 			this.permitno_popup=datatable.getPermitnoD();
				this.seq=datatable.getSeq();
                int box= Integer.parseInt(datatable.getShowDataTable_NoOfBoxes());
				 
				int fl36_box=new BWFLImportImpl().checkFl36GatepassBwfl(datatable);
				int fl11_box=new BWFLImportImpl().checkFl11GatepassBwfl(datatable); 
				
				if(fl36_box==box&&fl11_box==box)
				{
					
					System.out.println("come in   pop up check true");
					this.popup="N";
				}else {
					System.out.println("come in   pop up check false");
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Sorry You Can Not Cancel Because Of Gatepass Generated On This Permit No", "Sorry You Can Not Cancel Because Of Gatepass Generated On This Permit No"));
					this.popup="S";
					
				}
				
				System.out.println("POPOPOPOPOPOPOP[POP1 "+ this.popup);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return "";
		}
		
		
		public void cancelOrder()
		{
			
			
			try{
				if(isPopupValidate())
					{
					new BWFLImportImpl().cancelPermit(this.cancelData, this);
						}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
		
		public void cancelOrderClose()
		{
			try{
				this.cancel_order_date=null;
				this.authority_name="";
				this.doc1upload=false;
				this.cancel_order_no="";
				this.setUploadedPdfName("");
			}catch(Exception e)
			{
				e.printStackTrace();
			}	
		}
		
		
		
		String mypath = Constants.JBOSS_SERVER_PATH
				+ Constants.JBOSS_LINX_PATH + File.separator + "ExciseUp"
				+ File.separator + "Bwfl" + File.separator
				+ "cancelpermit";
	 BufferedInputStream in = null;
	 
	 public void fileUpload(UploadEvent event)
	{
		
	String name="";
		
		try {
			
			InputStream inFile = null;
			UploadItem item = event.getUploadItem();
			String FullfileName = item.getFileName();
			String FullfileExt = null;
			if (FullfileName != null && FullfileName.length() > 4) {
				FullfileExt = FullfileName.substring(FullfileName
						.lastIndexOf("."));
			}
			String path = item.getFile().getAbsolutePath();
			String filePath = item.getFile().getPath();
			if (filePath != null) {
				inFile = new FileInputStream(path);
				boolean success = false;
				if (!(new File(mypath).exists())) {
					File file = new File(mypath);
					success = file.mkdirs();
				}
				inFile = new FileInputStream(path);
				in = new BufferedInputStream(inFile);

				name=new BWFLImportImpl().getUploadSequence()+".pdf";
				FileOutputStream out = new FileOutputStream(this.mypath
						+ File.separator + name );

				BufferedOutputStream outb = new BufferedOutputStream(out);
				int z = 0;
				while ((z = this.in.read()) != -1) {
					outb.write(z);

				}
				
				outb.flush();
				outb.close();
				 
					
					 
					
					doc1upload = true;
				 this.setUploadedPdfName(name);

			} else {
				System.out.println("NO FILE READ STARTED.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*
 * This Method is used for reprint permit and render esign button
 * @author atul
 * @Assigned by Mitali
 * 
 */
	 /*
	  * and this datatable object is used when you click on reprintbutton then this variable 
	  * this variable store the value and this variable value used in reprint method and pass in impl print Report
	  * bwfl method in impl
	  */
	  private BWFLImportDataTable bwflImportDataTable;
	 public BWFLImportDataTable getBwflImportDataTable() {
		return bwflImportDataTable;
	}

	public void setBwflImportDataTable(BWFLImportDataTable bwflImportDataTable) {
		this.bwflImportDataTable = bwflImportDataTable;
	}

	public void rePrint()
	 {
		 
		 try{
			 new BWFLImportImpl().printReportBWFL(this.bwflImportDataTable);
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 
	 }
		
		
		

}
