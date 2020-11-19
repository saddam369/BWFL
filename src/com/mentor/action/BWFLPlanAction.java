package com.mentor.action;

import java.text.DateFormat;
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

import com.mentor.datatable.BWFLPlanDataTable;
import com.mentor.impl.BWFLPlanImpl;
import com.mentor.utility.Validate;

public class BWFLPlanAction {
	
	


	BWFLPlanImpl impl = new BWFLPlanImpl();

	private int id;
	private String permitno="";
	private Date permitdt = new Date();
	private Date validityDate;
	private String permitNoEnterd;
	private boolean update;
	

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
			//e.printStackTrace();
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

	private String distillery_id="0";
	private String distillery_nm;
	private String distillery_adrs;
	private String hidden;
	private Date dateOfBottling;

	private String liqureTypeId;
	private ArrayList liqureTypeList = new ArrayList();
	private String licenceType="0";
	

	private ArrayList brandPackagingDataList = new ArrayList();

	private ArrayList showDataTableList = new ArrayList();

	private boolean addRowFlagPackge = true;

	private String licenceNum;
	private ArrayList licenceNoList = new ArrayList();

	private Date cr_date;

	private String checkLicenceType;
	

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

		BWFLPlanDataTable dt = new BWFLPlanDataTable();
		dt.setSno(brandPackagingDataList.size() + 1);
		brandPackagingDataList.add(dt);

		return "";
	}

	public void deleteRowMethodPackaging(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
		BWFLPlanDataTable dt = (BWFLPlanDataTable) this.brandPackagingDataList.get(uiTable.getRowIndex());
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

	private boolean flagNew=false;
	
	
	
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
			 impl.getId(this);			
		} catch (Exception e) {
			e.printStackTrace();
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
		
		try
		{
			Object obj = event.getNewValue();
			String id = String.valueOf(obj);
			this.licenceNum = impl.getLicenseNo(this,id);
			this.brandPackagingDataList = impl.getAddRowData(this,this.getLicenceType());
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		

	}

	// --------------------------------------

	public void save() throws ParseException {

		if (isValidateInput()) {

			Date dt = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(dt);
			c.add(Calendar.DATE, 1);
			dt = c.getTime();

			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			if(impl.checkpermit(permitNoEnterd,this)==true){
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Permit Already Exists !!!","Permit Already Exists !!!"));
			}else{
			if(this.update)
			{
				impl.update(this);
			}else{
				impl.save(this);
			}
		}}

	}

	public void reset() 
	{
		this.liqureTypeId = "";
		this.liqureTypeList.clear();
		this.licenceType = "0";
		this.licenceNum = "";
		this.licenceNoList.clear();
		this.brandPackagingDataList.clear();
		this.permitNoEnterd = "";
		this.distillery_id = "0";
		this.disabledFlag=false;
		this.bottlngType="";
		this.validityDate=null;
		this.setUpdate(false);
	}

	private boolean validateInput;

	public boolean isValidateInput() {

		this.validateInput = true;

		if (!(Validate.validateStrReq("LicenseType", this.getLicenceType())))
			validateInput = false;

		else if (!(Validate.validateStrReq("licNo", this.getLicenceNum())))
			validateInput = false;
		else if (!(Validate.validateDate("permitdate", this.getPermitdt())))
			validateInput = false;
		else if (!(Validate.validateStrReq("radioMapUnmap", this.getBottlngType())))
			validateInput = false;
		else if (!(Validate.validateDate("validtill",this.getValidityDate())))
			validateInput = false;

		else if (this.getValidityDate().before(this.getPermitdt())) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(
							"Validity Date Can Not be less than Permit Date",
							"Validity Date Can Not be less than Permit Date"));
			validateInput = false;
		} else if (!(Validate.validateStrReq("permit",this.getPermitNoEnterd())))
			validateInput = false;
		if (validateInput) {
			if (this.brandPackagingDataList.size() > 0) {
				for (int i = 0; i < brandPackagingDataList.size(); i++) {
					BWFLPlanDataTable table = new BWFLPlanDataTable();
					table = (BWFLPlanDataTable) brandPackagingDataList.get(i);
					if (!(Validate.validateStrReqRow(i, "Packaging",table.getBrandPackagingData_Packaging())))
						validateInput = false;

					else if (!(Validate.validateStrReqRow(i, "Packaging",table.getBrandPackagingData_Packaging())))
						validateInput = false;
					else if (!(Validate.validateStrReqRow(i, "Quantity",table.getBrandPackagingData_Quantity())))
						validateInput = false;
					else if (!(Validate.validateDouble("PlanNoOfBottling",table.getBrandPackagingData_PlanNoOfBottling())))
						validateInput = false;
					
				}
			}

		}

		return validateInput;
	}

	public void setValidateInput(boolean validateInput) {
		this.validateInput = validateInput;
	}

	BWFLPlanDataTable bopd;

	public BWFLPlanDataTable getBopd() {
		return bopd;
	}

	public void setBopd(BWFLPlanDataTable bopd) {
		this.bopd = bopd;
	}

	public String finalizeData() {

		try {
			impl.dataFinalize(this, bopd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

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
	
	
	
	//--------------------------------------deleteData-----------------------------
	//@rvind
		private Date plan_dt;
		
		public Date getPlan_dt() {
			return plan_dt;
		}

		public void setPlan_dt(Date plan_dt) {
			this.plan_dt = plan_dt;
		}
		public void deleteData(ActionEvent e)
		{
			UIDataTable uiTable=(UIDataTable)e.getComponent().getParent().getParent();
			BWFLPlanDataTable dt=(BWFLPlanDataTable)this.displayDataList.get(uiTable.getRowIndex());
			
			/*int_distillery_id- Integer.parseInt( action.getDistillery_id())
			,int_brand_id- Integer.parseInt(table.getBrandPackagingData_Brand())
			,int_pack_id- Integer.parseInt(table.getBrandPackagingData_Packaging())
			,plan_dt- Utility.convertUtilDateToSQLDate(new Date())
			,permitno- this.getIddeo(action)+"-2018-19-"+action.getPermitNoEnterd()+"-"+challanId*/
			
			this.setInt_distillery_idUpdate(dt.getDistillery_id());//---------
			this.setInt_brand_idUpdate(dt.getInt_brand_idUpdate());//-----------
			this.setInt_pack_idUpdate(dt.getInt_pack_idUpdate());//----------
			this.setPlan_dt(dt.getPlan_dt());//--
			this.setPermitNoNew(dt.getPermitNmbr_dt());//-------------
			if(impl.delete(this)==1){
				System.out.println("record deleted");
			}else{
				System.out.println("record not deleted");
			}
	
		}
	
//--------------------------------------updateData-----------------------------
	
	public void updateData(ActionEvent e)
	{
		UIDataTable uiTable=(UIDataTable)e.getComponent().getParent().getParent();
		BWFLPlanDataTable dt=(BWFLPlanDataTable)this.displayDataList.get(uiTable.getRowIndex());
		
		
		
		this.setPermitno_enteredUpdate(dt.getPermitno_enteredUpdate());
		this.setVch_license_typeUpdate(dt.getLicenseType_dt());
		this.setInt_distillery_idUpdate(dt.getDistillery_id());
		this.setInt_brand_idUpdate(dt.getInt_brand_idUpdate());
		this.setInt_pack_idUpdate(dt.getInt_pack_idUpdate());
		this.setSeqUpdate(dt.getSeqUpdate());
		this.setLicence_noUpdate(dt.getLicenseNmbr_dt());
		this.setGatepass_noUpdate(dt.getGatepass_noUpdate());
		this.setPermitDatUpdate(dt.getPermitDt_dt());
		
		//this.setEtinUpdate(dt.getEtinUpdate());
		this.setPermitDateNew(dt.getPermitDt_dt());
		this.setLicenseNoNew(dt.getLicenseNmbr_dt());
		this.setPermitNoNew(dt.getPermitNmbr_dt());
		this.brandPackagingDataList=impl.getNewData(this);
		//this.unitlist = impl.getUnit_For_Update(this.licenceType,this.licenseNoNew,this);
		
		getDistillery_id();
		this.setDistillery_id(dt.getDistillery_id());
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

//---------------------updateValidDate-----------------------------------------------------------------------------
	
	private Date newValidDate=new Date();
	
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

	public void updateValidDate(ActionEvent e)
	{
		UIDataTable uiTable=(UIDataTable)e.getComponent().getParent().getParent();
		BWFLPlanDataTable dt=(BWFLPlanDataTable)this.displayDataListExt.get(uiTable.getRowIndex());
		
		
		
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
		this.brandPackagingDataList=impl.getNewData(this);
		//this.unitlist = impl.getUnit_For_Update(this.licenceType,this.licenseNoNew,this);
		
		getDistillery_id();
		this.setDistillery_id(dt.getDistillery_id());
		dt.getBrandPackagingData_BrandList();
		impl.updateValidTillExt(this,dt.getNewvalidityDate());
		
	}
	
	
	private ArrayList displayDataListExt = new ArrayList();
	
	
	
	public ArrayList getDisplayDataListExt() {
		
		try {

			this.displayDataListExt = impl.getDisplayListExt(this);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return displayDataListExt;
	}

	public void setDisplayDataListExt(ArrayList displayDataListExt) {
		this.displayDataListExt = displayDataListExt;
	}

	
	private String licenceTypeId;
	private String userDistrict;
	

	public String getLicenceTypeId() {
		return licenceTypeId;
	}

	public void setLicenceTypeId(String licenceTypeId) {
		this.licenceTypeId = licenceTypeId;
	}

	public String getUserDistrict() {
		return userDistrict;
	}

	public void setUserDistrict(String userDistrict) {
		this.userDistrict = userDistrict;
	}

	

}
