package com.mentor.action;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.richfaces.component.UIDataTable;

import com.mentor.datatable.BarCodeForCSDDataTable;
import com.mentor.datatable.EnteryOfPermitDataTable;
import com.mentor.impl.BarCodeForCSDImpl;
import com.mentor.impl.EnteryOfPermitImpl;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Validate;

public class EnteryOfPermitAction {

	EnteryOfPermitImpl impl = new EnteryOfPermitImpl();
	
	private String hidden;
	private String distillery_nm;
	private String distillery_adrs;
	private int distillery_id;
	private String radioButton;
	private String cdsName;
	private String cdsAddress;
	private String permitNo;
	private Date issueDate;
	private String distilleryBreweryName;
	private String distilleryBreweryAddress;
	private String licenceNo;
	private String contactNo;
	private boolean distilleryBreweryNameText_Flag = true;
	private boolean distillerylist_Flag;
	private boolean brewerylist_Flag;
	private String email_id;
	private int val=0;
	
	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	private ArrayList brewerylist = new ArrayList();
	private String breweryId;

	private ArrayList distillerylist = new ArrayList();
	private String distilleryId;

	private ArrayList brandPackagingDataList = new ArrayList();
	private ArrayList brandPackagingDataList1 = new ArrayList();
	private ArrayList showDataTableList = new ArrayList();

	public ArrayList getShowDataTableList() {
		EnteryOfPermitImpl impl = new EnteryOfPermitImpl();
		this.showDataTableList = impl.getData(this);
		return showDataTableList;
	}

	public void setShowDataTableList(ArrayList showDataTableList) {
		this.showDataTableList = showDataTableList;
	}

	

	public String getHidden() {

		try {
			EnteryOfPermitImpl impl = new EnteryOfPermitImpl();

			impl.getSugarmill(this);
			if (this.radioButton.equalsIgnoreCase("FUPD")) {
				impl.getdistellry(this, distilleryId);
			} else if (this.radioButton.equalsIgnoreCase("FUPB")) {
				impl.getbrewery(this, breweryId);
			} else {
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
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

	public int getDistillery_id() {
		return distillery_id;
	}

	public void setDistillery_id(int distillery_id) {
		this.distillery_id = distillery_id;
	}

	public String getRadioButton() {
		return radioButton;
	}

	public void setRadioButton(String radioButton) {
		this.radioButton = radioButton;
	}

	public String getCdsName() {
		return cdsName;
	}

	public void setCdsName(String cdsName) {
		this.cdsName = cdsName;
	}

	public String getCdsAddress() {
		return cdsAddress;
	}

	public void setCdsAddress(String cdsAddress) {
		this.cdsAddress = cdsAddress;
	}

	public String getPermitNo() {
		return permitNo;
	}

	public void setPermitNo(String permitNo) {
		this.permitNo = permitNo;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getDistilleryBreweryName() {
		return distilleryBreweryName;
	}

	public void setDistilleryBreweryName(String distilleryBreweryName) {
		this.distilleryBreweryName = distilleryBreweryName;
	}

	public String getDistilleryBreweryAddress() {
		return distilleryBreweryAddress;
	}

	public void setDistilleryBreweryAddress(String distilleryBreweryAddress) {
		this.distilleryBreweryAddress = distilleryBreweryAddress;
	}

	public String getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public boolean isDistilleryBreweryNameText_Flag() {
		return distilleryBreweryNameText_Flag;
	}

	public void setDistilleryBreweryNameText_Flag(
			boolean distilleryBreweryNameText_Flag) {
		this.distilleryBreweryNameText_Flag = distilleryBreweryNameText_Flag;
	}

	public boolean isDistillerylist_Flag() {
		return distillerylist_Flag;
	}

	public void setDistillerylist_Flag(boolean distillerylist_Flag) {
		this.distillerylist_Flag = distillerylist_Flag;
	}

	public boolean isBrewerylist_Flag() {
		return brewerylist_Flag;
	}

	public void setBrewerylist_Flag(boolean brewerylist_Flag) {
		this.brewerylist_Flag = brewerylist_Flag;
	}

	public ArrayList getBrewerylist() {
		if(val==1){
			this.brewerylist = impl.breweryList(this);
		}
		return brewerylist;
	}

	public void setBrewerylist(ArrayList brewerylist) {
		this.brewerylist = brewerylist;
	}

	public String getBreweryId() {
		return breweryId;
	}

	public void setBreweryId(String breweryId) {
		this.breweryId = breweryId;
	}

	public ArrayList getDistillerylist() {
		if(this.val==1){
			this.distillerylist = impl.distilleryList(this);
		}
		return distillerylist;
	}

	public void setDistillerylist(ArrayList distillerylist) {
		this.distillerylist = distillerylist;
	}

	public String getDistilleryId() {
		return distilleryId;
	}

	public void setDistilleryId(String distilleryId) {
		this.distilleryId = distilleryId;
	}

	public String listMethod(ValueChangeEvent vce) {

		EnteryOfPermitImpl impl = new EnteryOfPermitImpl();
		this.distilleryBreweryAddress = "";
		this.licenceNo = "";
		this.breweryId = "";
		this.distilleryId = "";
		this.brewerylist.clear();
		this.distillerylist.clear();

		String val = (String) vce.getNewValue();

		if (val.equalsIgnoreCase("FUPD")) {
			this.distilleryBreweryNameText_Flag = false;
			this.distillerylist_Flag = true;
			this.brewerylist_Flag = false;
			this.distillerylist = impl.distilleryList(this);

		} else if (val.equalsIgnoreCase("FUPB")) {
			this.distilleryBreweryNameText_Flag = false;
			this.distillerylist_Flag = false;
			this.brewerylist_Flag = true;
			this.brewerylist = impl.breweryList(this);
		} else {
			this.distilleryBreweryNameText_Flag = true;
			this.distillerylist_Flag = false;
			this.brewerylist_Flag = false;
			this.distillerylist.clear();
			this.brewerylist.clear();
		}

		return "";
	}

	// ------------ get distillery name add, etc
	public void dist_detail(ValueChangeEvent event) {
		EnteryOfPermitImpl impl = new EnteryOfPermitImpl();

		Object obj = event.getNewValue();
		String id = String.valueOf(obj);
		impl.getdistellry(this, id);

	}

	// ------------ get brewery name add, etc
	public void brewery_detail(ValueChangeEvent event) {
		EnteryOfPermitImpl impl = new EnteryOfPermitImpl();

		Object obj = event.getNewValue();
		String id = String.valueOf(obj);
		impl.getbrewery(this, id);

	}

	// ------------------------------ add row --------------

	public String addRowMethodPackaging() {

		EnteryOfPermitDataTable dt = new EnteryOfPermitDataTable();
		dt.setSno(brandPackagingDataList.size() + 1);
		brandPackagingDataList.add(dt);

		return "";
	}

	public void deleteRowMethodPackaging(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		EnteryOfPermitDataTable dt = (EnteryOfPermitDataTable) this.brandPackagingDataList
				.get(uiTable.getRowIndex());
		this.brandPackagingDataList.remove(dt);
	}

	
	/*public ArrayList getBrandPackagingDataList() {
		BarCodeForCSDImpl impl=new BarCodeForCSDImpl();
		this.brandPackagingDataList=impl.getDataBrandDetail(this);
		return brandPackagingDataList;
	}
	public void setBrandPackagingDataList1(ArrayList brandPackagingDataList) {
		this.brandPackagingDataList = brandPackagingDataList;
	}*/
	
	
	
	
	
	
	
	
	public void save() {
		EnteryOfPermitImpl impl = new EnteryOfPermitImpl();
						
		if (isValidateInput()) {
			if( this.statusFlg==true)
			{
				impl.updtDetailsImpl(this);
				this.val=0;
			}
			else{
				impl.save(this);
				this.val=0;
			}
			
		}
	}

	public void reset() {

		this.radioButton = "";
		this.cdsName = "";
		this.cdsAddress = "";
		this.permitNo = "";
		this.issueDate = null;
		this.distilleryBreweryName = "";
		this.distilleryBreweryAddress = "";
		this.licenceNo = "";
		this.contactNo = "";
		this.distilleryBreweryNameText_Flag = true;
		this.distillerylist_Flag = false;
		this.brewerylist_Flag = false;

		this.brandPackagingDataList.clear();
		this.brewerylist.clear();
		this.breweryId = "";

		this.distillerylist.clear();
		this.distilleryId = "";
		this.email_id = "";
		
		this.statusFlg = false;
		this.disableFlg = false;
		this.val=0;

	}

	// -------------------------------- validation -----------------

	private boolean validateInput;

	public boolean isValidateInput() {

		this.validateInput = true;

		if (!(Validate.validateStrReq("radio", this.getRadioButton())))
			validateInput = false;
		// else
		// if(!(Validate.validateStrReq("csdname",this.getCdsName())))validateInput=false;
		// else if(!(Validate.validateStrReq("csdaddress",
		// this.getCdsAddress())))validateInput=false;
		else if (!(Validate.validateStrReq("prmit", this.getPermitNo())))
			validateInput = false;
		else if (!(Validate.validateDate("dateValue", this.getIssueDate())))
			validateInput = false;

		// else
		// if(!(Validate.validateStrReq("licNo",this.getLicenceNo())))validateInput=false;
		else if (!(Validate.validateStrReq("contact", this.getContactNo())))
			validateInput = false;
		else if (!(Validate.validateOnlyInt("contact", this.getContactNo())))
			validateInput = false;

		else if (!(Validate.validateStrReq("emailid", this.getEmail_id())))
			validateInput = false;

		else if (this.radioButton.equalsIgnoreCase("FUPD")) {
			if (this.distilleryId.equalsIgnoreCase("0")) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Distillery / Brewery Name","Distillery / Brewery Name"));

				validateInput = false;
			}
			// if(!(Validate.validateStrReq("distllryname",this.getDistilleryId())))validateInput=false;
		} else if (this.radioButton.equalsIgnoreCase("FUPB")) {
			if (this.breweryId.equalsIgnoreCase("0")) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Distillery / Brewery Name","Distillery / Brewery Name"));

				validateInput = false;
			}
			// if(!(Validate.validateStrReq("distllryname",this.getBreweryId())))validateInput=false;
		} else {
			if (!(Validate.validateStrReq("distllryname",
					this.getDistilleryBreweryName())))
				validateInput = false;
		}

		if (validateInput) {
			if (this.brandPackagingDataList.size() > 0) {
				for (int i = 0; i < brandPackagingDataList.size(); i++) {
					EnteryOfPermitDataTable table = new EnteryOfPermitDataTable();
					table = (EnteryOfPermitDataTable) brandPackagingDataList
							.get(i);
					if (!(Validate.validateStrReqRow(i, "Brand",
							table.getBrandPackagingData_Brand())))
						validateInput = false;
					else if (!(Validate.validateStrReqRow(i, "size",
							table.getBrandPackagingData_Packaging())))
						validateInput = false;
					// else if(!(Validate.validateStrReqRow(i,"Quantity",
					// table.getBrandPackagingData_Quantity())))validateInput=false;
					else if (!(Validate.validateDouble("PlanNoOfBottling",
							table.getBrandPackagingData_PlanNoOfBottling())))
						validateInput = false;
					// else
					// if(!(Validate.validateStrReqRow(i,"",String.valueOf(table.getCapacitySprit()))))validateInput=false;
					// else
					// if(!(Validate.validateDouble("installdCap",table.getCapacitySprit())))validateInput=false;
				}
			}

		}

		// -----------------------------

		if (validateInput) {
			if (Validate.emailFieldValidate(this.email_id)) {
				validateInput = true;
			} else {
				ResourceUtil.addErrorMessage(Constants.EMAIL_CHECK,
						Constants.EMAIL_CHECK);
				validateInput = false;
			}
		}

		return validateInput;
	}

	public void setValidateInput(boolean validateInput) {
		this.validateInput = validateInput;
	}

	// --------------------update details----------------------------

	private boolean statusFlg;
	private int reqId;
	private boolean disableFlg=false;
	
	
	public boolean isStatusFlg() {
		return statusFlg;
	}

	public void setStatusFlg(boolean statusFlg) {
		this.statusFlg = statusFlg;
	}

	public int getReqId() {
		return reqId;
	}

	public void setReqId(int reqId) {
		this.reqId = reqId;
	}
	
	public boolean isDisableFlg() {
		return disableFlg;
	}

	public void setDisableFlg(boolean disableFlg) {
		this.disableFlg = disableFlg;
	}

	public void updtDetails(ActionEvent e) {

		try {
			this.statusFlg = true;
			this.disableFlg = true;
			this.val=1;
			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
			EnteryOfPermitDataTable dt = (EnteryOfPermitDataTable) this.showDataTableList.get(uiTable.getRowIndex());
			
			this.setPermitNo(dt.getShowDataTable_Permit_No());
			this.setIssueDate(dt.getShowDataTable_Date());
			this.setContactNo(dt.getShowDataTable_contact_NO());
			this.setEmail_id(dt.getShowDataTable_Licence_No());
			this.setDistilleryBreweryName(dt.getShowDataTable_distBrewNm());
			this.setDistilleryBreweryAddress(dt.getShowDataTable_distBrewAdd());
			this.setRadioButton(dt.getShowDataTable_radio());
			this.setDistillery_id(dt.getShowDataTable_Distid());
			this.setBreweryId(dt.getShowDataTable_brewId());
			this.setReqId(dt.getRequest_id());
			
			this.brandPackagingDataList = impl.getUpdtData(this, dt.getRequest_id());
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	public ArrayList getBrandPackagingDataList1() {
		try{
		EnteryOfPermitImpl impl=new EnteryOfPermitImpl();
		this.brandPackagingDataList1=impl.getDataBrandDetail(this);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return brandPackagingDataList1;
	}
	public void setBrandPackagingDataList1(ArrayList brandPackagingDataList1) {
		this.brandPackagingDataList1 = brandPackagingDataList1;
	}
	

	public ArrayList getBrandPackagingDataList() {
			return brandPackagingDataList;
		}

		public void setBrandPackagingDataList(ArrayList brandPackagingDataList) {
			this.brandPackagingDataList = brandPackagingDataList;
		}
	
	public String finalizeData()
	{
		
		try{
			new EnteryOfPermitImpl().dataFinalize(this,bopd);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return "";
	}
	BarCodeForCSDDataTable bopd;

	
	public BarCodeForCSDDataTable getBopd() {
		return bopd;
	}
	public void setBopd(BarCodeForCSDDataTable bopd) {
		this.bopd = bopd;
	}
	
	
	
}
