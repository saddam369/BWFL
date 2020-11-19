package com.mentor.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.richfaces.component.UIDataTable;

import com.mentor.datatable.PendingPermitDT;
import com.mentor.impl.PendingPermitImpl;

public class PendingPermitAction {

	PendingPermitImpl impl = new PendingPermitImpl();

	private String hidden;
	private String radioType = "N";
	private int bwflId;
	private int unitID;
	private String bwflName;
	private String licenseType;
	private String licenseNmbr;
	private String appDate;
	private int districtId;
	private String districtName;
	private int requestID;
	private int totalBoxes;
	private int totalBotls;
	private String parentUnitNm;
	private String parentUnitAdrs;
	private String parentUnitState;
	private boolean hideDataTable = true;
	private boolean viewPanelFlg;
	private String fillRemrks;
	private String deoRemrks;
	private double totalDuty;
	private double totalAddDuty;
	private double totalImportFee;
	private double totalSpecialFee;
	private double balRgstrDuty;
	private double balRgstrImportFee;
	private double balRgstrSpecialFee;
	private String byRoad_byRoute;
	private String routeDetail;
	private String validUptoDt;
	private Date fillValidDt;
	private String mapped_unmapped;
	private int custom_id; 
	private String scanning_fee_challan_no;
	private double total_scanning_fee;
    private String control_id;
    private String service_id;
    private String unit_id;
    private String request_id;
    private String objectionRejectionCode="";
    private ArrayList rejectionList=new ArrayList();
	public String getObjectionRejectionCode() {
		return objectionRejectionCode;
	}

	public void setObjectionRejectionCode(String objectionRejectionCode) {
		this.objectionRejectionCode = objectionRejectionCode;
	}

	public ArrayList getRejectionList() {
		
		try{
		this.rejectionList=new PendingPermitImpl().objectionRejectionList(this.service_id);	
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return rejectionList;
	}

	public void setRejectionList(ArrayList rejectionList) {
		this.rejectionList = rejectionList;
	}

	public String getControl_id() {
		return control_id;
	}

	public void setControl_id(String control_id) {
		this.control_id = control_id;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public double getTotal_scanning_fee() {
		return total_scanning_fee;
	}

	public void setTotal_scanning_fee(double total_scanning_fee) {
		this.total_scanning_fee = total_scanning_fee;
	}

	public String getScanning_fee_challan_no() {
		return scanning_fee_challan_no;
	}

	public void setScanning_fee_challan_no(String scanning_fee_challan_no) {
		this.scanning_fee_challan_no = scanning_fee_challan_no;
	}

	private ArrayList displayRegUsers = new ArrayList();
	private ArrayList displayBrandDetails = new ArrayList();
	

	
	
	public int getCustom_id() {
		return custom_id;
	}

	public void setCustom_id(int custom_id) {
		this.custom_id = custom_id;
	}

	public Date getFillValidDt() {
		return fillValidDt;
	}

	public void setFillValidDt(Date fillValidDt) {
		this.fillValidDt = fillValidDt;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getRadioType() {
		return radioType;
	}

	public void setRadioType(String radioType) {
		this.radioType = radioType;
	}

	public int getBwflId() {
		return bwflId;
	}

	public void setBwflId(int bwflId) {
		this.bwflId = bwflId;
	}

	public int getUnitID() {
		return unitID;
	}

	public void setUnitID(int unitID) {
		this.unitID = unitID;
	}

	public String getBwflName() {
		return bwflName;
	}

	public void setBwflName(String bwflName) {
		this.bwflName = bwflName;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public String getLicenseNmbr() {
		return licenseNmbr;
	}

	public void setLicenseNmbr(String licenseNmbr) {
		this.licenseNmbr = licenseNmbr;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public int getTotalBoxes() {
		return totalBoxes;
	}

	public void setTotalBoxes(int totalBoxes) {
		this.totalBoxes = totalBoxes;
	}

	public int getTotalBotls() {
		return totalBotls;
	}

	public void setTotalBotls(int totalBotls) {
		this.totalBotls = totalBotls;
	}

	public String getParentUnitNm() {
		return parentUnitNm;
	}

	public void setParentUnitNm(String parentUnitNm) {
		this.parentUnitNm = parentUnitNm;
	}

	public String getParentUnitAdrs() {
		return parentUnitAdrs;
	}

	public void setParentUnitAdrs(String parentUnitAdrs) {
		this.parentUnitAdrs = parentUnitAdrs;
	}

	public String getParentUnitState() {
		return parentUnitState;
	}

	public void setParentUnitState(String parentUnitState) {
		this.parentUnitState = parentUnitState;
	}

	public boolean isHideDataTable() {
		return hideDataTable;
	}

	public void setHideDataTable(boolean hideDataTable) {
		this.hideDataTable = hideDataTable;
	}

	public boolean isViewPanelFlg() {
		return viewPanelFlg;
	}

	public void setViewPanelFlg(boolean viewPanelFlg) {
		this.viewPanelFlg = viewPanelFlg;
	}

	public String getFillRemrks() {
		return fillRemrks;
	}

	public void setFillRemrks(String fillRemrks) {
		this.fillRemrks = fillRemrks;
	}

	public String getDeoRemrks() {
		return deoRemrks;
	}

	public void setDeoRemrks(String deoRemrks) {
		this.deoRemrks = deoRemrks;
	}

	public double getTotalDuty() {
		return totalDuty;
	}

	public void setTotalDuty(double totalDuty) {
		this.totalDuty = totalDuty;
	}

	public double getTotalAddDuty() {
		return totalAddDuty;
	}

	public void setTotalAddDuty(double totalAddDuty) {
		this.totalAddDuty = totalAddDuty;
	}

	public double getTotalImportFee() {
		return totalImportFee;
	}

	public void setTotalImportFee(double totalImportFee) {
		this.totalImportFee = totalImportFee;
	}

	public double getTotalSpecialFee() {
		return totalSpecialFee;
	}

	public void setTotalSpecialFee(double totalSpecialFee) {
		this.totalSpecialFee = totalSpecialFee;
	}

	public double getBalRgstrDuty() {
		return balRgstrDuty;
	}

	public void setBalRgstrDuty(double balRgstrDuty) {
		this.balRgstrDuty = balRgstrDuty;
	}

	public double getBalRgstrImportFee() {
		return balRgstrImportFee;
	}

	public void setBalRgstrImportFee(double balRgstrImportFee) {
		this.balRgstrImportFee = balRgstrImportFee;
	}

	public double getBalRgstrSpecialFee() {
		return balRgstrSpecialFee;
	}

	public void setBalRgstrSpecialFee(double balRgstrSpecialFee) {
		this.balRgstrSpecialFee = balRgstrSpecialFee;
	}

	public String getByRoad_byRoute() {
		return byRoad_byRoute;
	}

	public void setByRoad_byRoute(String byRoad_byRoute) {
		this.byRoad_byRoute = byRoad_byRoute;
	}

	public String getRouteDetail() {
		return routeDetail;
	}

	public void setRouteDetail(String routeDetail) {
		this.routeDetail = routeDetail;
	}

	public String getValidUptoDt() {
		return validUptoDt;
	}

	public void setValidUptoDt(String validUptoDt) {
		this.validUptoDt = validUptoDt;
	}

	public String getMapped_unmapped() {
		return mapped_unmapped;
	}

	public void setMapped_unmapped(String mapped_unmapped) {
		this.mapped_unmapped = mapped_unmapped;
	}

	private boolean listFlagForPrint = true;
	

	public boolean isListFlagForPrint() {
		return listFlagForPrint;
	}

	public void setListFlagForPrint(boolean listFlagForPrint) {
		this.listFlagForPrint = listFlagForPrint;
	}

	public ArrayList getDisplayRegUsers() {
		try {
			if (this.radioType != null) {
				if(this.listFlagForPrint == true)
				{
				this.displayRegUsers = impl.displayRegUsersImpl(this);
				this.listFlagForPrint = false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return displayRegUsers;
	}

	public void setDisplayRegUsers(ArrayList displayRegUsers) {
		this.displayRegUsers = displayRegUsers;
	}

	public ArrayList getDisplayBrandDetails() {
		return displayBrandDetails;
	}

	public void setDisplayBrandDetails(ArrayList displayBrandDetails) {
		this.displayBrandDetails = displayBrandDetails;
	}

	public void radioListener(ValueChangeEvent e) {

		try {
			String val = (String) e.getNewValue();
			this.viewPanelFlg = false;
			this.listFlagForPrint = true;
			this.setRadioType(val);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
	
	private String consignorNmAdrs;

	public String getConsignorNmAdrs() {
		return consignorNmAdrs;
	}

	public void setConsignorNmAdrs(String consignorNmAdrs) {
		this.consignorNmAdrs = consignorNmAdrs;
	}

	public void viewDetails(ActionEvent e) {
		try {
			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
					.getParent();
			PendingPermitDT dt = (PendingPermitDT) this.getDisplayRegUsers()
					.get(uiTable.getRowIndex());

			this.hideDataTable = false;
			this.viewPanelFlg = true;

			this.setControl_id(dt.getControl_id());
			this.setUnit_id(dt.getUnit_id());
			this.setService_id(dt.getService_id());
			this.setRequest_id(dt.getRequest_id());
			this.setAppId(dt.getAppId_dt());
			this.setRequestID(dt.getRequestID_dt());
			this.setUnitID(dt.getUnitID_dt());
			this.setBwflId(dt.getBwflID_dt());
			this.setAppDate(dt.getAppDate_dt());
			this.setBwflName(dt.getBwflName_dt());
			this.setDistrictId(dt.getDistrictId_dt());
			this.setDistrictName(dt.getDistrictName_dt());
			this.setParentUnitNm(dt.getParentUnitNm_dt());
			this.setParentUnitAdrs(dt.getParentUnitAdrs_dt());
			this.setParentUnitState(dt.getParentUnitState_dt());
			this.setLicenseType(dt.getLicenseType_dt());
			this.setLicenseNmbr(dt.getLicenseNmbr_dt());
			this.setMapped_unmapped(dt.getMapped_unmapped_dt());
			this.setByRoad_byRoute(dt.getByRoad_byRoute_dt());
			this.setRouteDetail(dt.getRouteDetail_dt());
			this.setValidUptoDt(dt.getValidUptoDt_dt());
			this.setDeoRemrks(dt.getDeoRemark_dt());
			this.setLoginType(dt.getLoginType_dt());
			this.setCrDate(dt.getCrDate_dt());
			this.setPermitNmbr(dt.getPermitNmbr_dt());
			this.setApprovalDate(dt.getApprovalDate_dt());
			this.setApprovalTym(dt.getApprovalTym_dt());
			this.setApprovalUser(dt.getApprovalUser_dt());
			this.setImportFeeChalanNo(dt.getImportFeeChalanNo_dt());
			this.setSpclFeeChalanNo(dt.getSpclFeeChalanNo_dt());
			this.setConsignorNmAdrs(dt.getConsignorNmAdrs_dt());
			this.setCustom_id(dt.getCustom_id());
			this.setScanning_fee_challan_no(dt.getScanning_fee_challan_no());
			this.setControl_id(dt.getControl_id());
			this.setUnit_id(dt.getUnit_id());
			this.setService_id(dt.getService_id());
			this.setRequest_id(dt.getRequest_id());
			System.out.println("custom isd =00="+dt.getCustom_id());
			if(dt.getLoginType_dt().equalsIgnoreCase("FL2D")){
				this.setFl2dDutyFlg(true);
			}else{
				this.setFl2dDutyFlg(false);
			}

			//impl.getAdvancRgstrBalance(this);
			this.displayBrandDetails = impl.displayBrandDetailsImpl(this);
			// this.displayChalanDetails = impl.displayChalanDetailsImpl(this);
			
			
			//================prasoooon==========================================
			
			if(impl.check_specialimport(this) == true){
				System.out.println("---getimport_special_fee-");
			   impl.getimport_special_fee(this);
			  this.flag_import = "T" ;
			
			}else{
				
			this.displayChalanDetails = impl.displayChalanDetailsImpl(this);
			this.flag_challan = "T" ;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public String approveApplication() {
		try {
			
				String st = this.appDate;
				Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(st);
			
				if (this.fillRemrks != null && this.fillRemrks.length() > 0 && this.fillValidDt != null) {
					if (this.fillValidDt.after(date1) || this.fillValidDt.compareTo(date1)==0 ) {
					if (this.checkBox == true) {
					impl.approveApplicationImpl(this);
					} else {
						FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Make Sure Challan Verified and Certified !!","Make Sure Challan Verified and Certified !!"));
				} }else {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Validity Date Should Be greater than or equal to application date !!"," Validity Date Should Be greater than or equal to application date!!"));
				}}else {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Please Fill Remarks And Date!!","Please Fill Remarks And Date!!"));
				}
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}

	public String rejectApplication() {
		try {

			if (this.fillRemrks != null && this.fillRemrks.length() > 0) {
				
				if(this.control_id.equals("NA"))
				{
					
				if(this.radioType.equalsIgnoreCase("N")){
					impl.rejectApplicationImpl(this);
				}
				else if(this.radioType.equalsIgnoreCase("A")){
					impl.rejectApprovedApplication(this);
				}
				
				}else {
					if(!this.getObjectionRejectionCode().equals("0")&&!this.control_id.equals("NA"))
					{
					if(this.radioType.equalsIgnoreCase("N")){
						impl.rejectApplicationImpl(this);
					}
					else if(this.radioType.equalsIgnoreCase("A")){
						impl.rejectApprovedApplication(this);
					}
					}else{
						FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Please Select Reason!!","Please Select Reason!!"));	
					}
					
				}
				
			} else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Please Fill Remarks!!","Please Fill Remarks!!"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	//-------------------print permit-----------------
	
	public void printReport(ActionEvent e) {

		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
		PendingPermitDT dt = (PendingPermitDT) this.getDisplayRegUsers().get(uiTable.getRowIndex());
		
		this.setBwflId(dt.getBwflID_dt());
		this.setRequestID(dt.getRequestID_dt());
		//impl.getAdvancRgstrBalanceForPrint(this);

		if(dt.getLoginType_dt().equalsIgnoreCase("BWFL")){
			if (impl.printReportBWFL(this, dt) == true) {
				dt.setPrintFlag(true);
				this.displayRegUsers = impl.displayRegUsersImpl(this);
			} else {
				dt.setPrintFlag(false);
				

			}
		}else if(dt.getLoginType_dt().equalsIgnoreCase("FL2D")){
			if (impl.printReportFL2D(this, dt) == true) {
				dt.setPrintFlag(true);
				this.displayRegUsers = impl.displayRegUsersImpl(this);
			} else {
				dt.setPrintFlag(false);
				

			}
		}
		

	}

	// ----------------------close application-----------------------

	public void closeApplication() {

		this.displayRegUsers = impl.displayRegUsersImpl(this);
		this.viewPanelFlg = false;
		this.hideDataTable = true;
		this.listFlagForPrint = true;
		this.fillRemrks = null;
		this.licenseType = null;
		this.totalAddDuty = 0.0;
		this.totalDuty = 0.0;
		this.totalImportFee = 0.0;
		this.totalSpecialFee = 0.0;
		this.balRgstrDuty = 0.0;
		this.balRgstrImportFee = 0.0;
		this.balRgstrSpecialFee = 0.0;
		this.balFL2DImportFee = 0.0;
		this.balFL2DSpecialFee = 0.0;
		this.totalBoxes = 0;
		this.totalBotls = 0;
		this.bwflId = 0;
		this.requestID = 0;
		this.unitID = 0;
		this.bwflName = null;
		this.appDate = null;
		this.districtId = 0;
		this.districtName = null;
		this.parentUnitAdrs = null;
		this.parentUnitNm = null;
		this.parentUnitState = null;
		this.licenseType = null;
		this.licenseNmbr = null;
		this.validUptoDt = null;
		this.byRoad_byRoute = null;
		this.mapped_unmapped = null;
		this.routeDetail = null;
		this.deoRemrks = null;
		this.displayBrandDetails.clear();
		this.displayChalanDetails.clear();
		this.loginType = null;
		this.crDate = null;
		this.permitNmbr = null;
		this.approvalDate = null;
		this.approvalTym = null;
		this.approvalUser = null;
		this.importFeeChalanNo = null;
		this.spclFeeChalanNo = null;
		this.appId = 0;
		this.fillValidDt = null;
		this.checkBox = false;
		this.flag_import = "F";
		this.flag_challan ="F";


	}
	
	private boolean fl2dDutyFlg;
	private String loginType;
	private Date crDate;
	private String permitNmbr;
	private Date approvalDate;
	private String approvalTym;
	private String approvalUser;
	private double balFL2DImportFee;
	private double balFL2DSpecialFee;
	private boolean checkBox;
	private ArrayList displayChalanDetails = new ArrayList();
	private String importFeeChalanNo;
	private String spclFeeChalanNo;
	private int appId;

	public boolean isFl2dDutyFlg() {
		return fl2dDutyFlg;
	}

	public void setFl2dDutyFlg(boolean fl2dDutyFlg) {
		this.fl2dDutyFlg = fl2dDutyFlg;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public Date getCrDate() {
		return crDate;
	}

	public void setCrDate(Date crDate) {
		this.crDate = crDate;
	}

	public String getPermitNmbr() {
		return permitNmbr;
	}

	public void setPermitNmbr(String permitNmbr) {
		this.permitNmbr = permitNmbr;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getApprovalTym() {
		return approvalTym;
	}

	public void setApprovalTym(String approvalTym) {
		this.approvalTym = approvalTym;
	}

	public String getApprovalUser() {
		return approvalUser;
	}

	public void setApprovalUser(String approvalUser) {
		this.approvalUser = approvalUser;
	}

	public double getBalFL2DImportFee() {
		return balFL2DImportFee;
	}

	public void setBalFL2DImportFee(double balFL2DImportFee) {
		this.balFL2DImportFee = balFL2DImportFee;
	}

	public double getBalFL2DSpecialFee() {
		return balFL2DSpecialFee;
	}

	public void setBalFL2DSpecialFee(double balFL2DSpecialFee) {
		this.balFL2DSpecialFee = balFL2DSpecialFee;
	}

	public boolean isCheckBox() {
		return checkBox;
	}

	public void setCheckBox(boolean checkBox) {
		this.checkBox = checkBox;
	}

	public ArrayList getDisplayChalanDetails() {
		return displayChalanDetails;
	}

	public void setDisplayChalanDetails(ArrayList displayChalanDetails) {
		this.displayChalanDetails = displayChalanDetails;
	}

	public String getImportFeeChalanNo() {
		return importFeeChalanNo;
	}

	public void setImportFeeChalanNo(String importFeeChalanNo) {
		this.importFeeChalanNo = importFeeChalanNo;
	}

	public String getSpclFeeChalanNo() {
		return spclFeeChalanNo;
	}

	public void setSpclFeeChalanNo(String spclFeeChalanNo) {
		this.spclFeeChalanNo = spclFeeChalanNo;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	
	private boolean showMainPanel_flg=false;
	private String popupPermitNmbr;
	
	public boolean isShowMainPanel_flg() {
		return showMainPanel_flg;
	}

	public void setShowMainPanel_flg(boolean showMainPanel_flg) {
		this.showMainPanel_flg = showMainPanel_flg;
	}
	

	public String getPopupPermitNmbr() {
		return popupPermitNmbr;
	}

	public void setPopupPermitNmbr(String popupPermitNmbr) {
		this.popupPermitNmbr = popupPermitNmbr;
	}

	public void closePopup(){
		this.showMainPanel_flg = false;
		this.popupPermitNmbr = null;
	}
	
	
//================================prasoooooooooooooooooooooooooooooooooooooooooooooon 28-09-2020---------=============	
	
	private String total_advance_importFee ;
	private String total_advance_specialFee ;
	
	private String flag_challan = "F";
	private String flag_import = "F";
	public String getTotal_advance_importFee() {
		return total_advance_importFee;
	}

	public void setTotal_advance_importFee(String total_advance_importFee) {
		this.total_advance_importFee = total_advance_importFee;
	}

	public String getTotal_advance_specialFee() {
		return total_advance_specialFee;
	}

	public void setTotal_advance_specialFee(String total_advance_specialFee) {
		this.total_advance_specialFee = total_advance_specialFee;
	}

	public String getFlag_challan() {
		return flag_challan;
	}

	public void setFlag_challan(String flag_challan) {
		this.flag_challan = flag_challan;
	}

	public String getFlag_import() {
		return flag_import;
	}

	public void setFlag_import(String flag_import) {
		this.flag_import = flag_import;
	}
	
	
	
		
	

}
