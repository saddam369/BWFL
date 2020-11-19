package com.mentor.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletContext;

import org.richfaces.component.UIDataTable;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.mentor.datatable.BWFL_Reciept_Details_Datatable;
import com.mentor.datatable.BWFL_ScanUploadDT;
import com.mentor.datatable.GatepassToDistrictWholesale_FL2DDataTable;
import com.mentor.impl.BWFLImportImpl;
import com.mentor.impl.BWFL_ScanUploadImpl;
import com.mentor.utility.Validate;

/**
 * @author Arvind
 * 
 */
public class BWFL_ScanUploadAction {

	BWFL_ScanUploadImpl impl = new BWFL_ScanUploadImpl();
	private String hidden;
	private BWFL_ScanUploadDT reciptDetail;
	private boolean view_full_form_flag;
	private String expOrderNo;
	private Date expOrderDt;
	private String transVehicleNo;
	private String routeDetails;
	private int bwflDistId;
	private String bwflDistMoblie;

	private String distillery_name;
	private String license_type;
	private String licensee_name;
	private String license_number;
	private String firm_address;

	private ArrayList bwfl_datalist = new ArrayList();
	private ArrayList view_formData = new ArrayList();
	private ArrayList reciept_list = new ArrayList();
	private ArrayList datalist = new ArrayList();
	private boolean dataListFlag = true;
	private String permitno;
	private int dispatched_bottles = 0;
	private String breakage = "";
	private int slno;
	private int sno;
	private String pdfName;
	private boolean gatePassFlag;
	private Boolean printBtnFlag;
	
	private int groupid;
	
	
	
	
	
	
	 public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public Boolean getPrintBtnFlag() {
		return printBtnFlag;
	}

	public void setPrintBtnFlag(Boolean printBtnFlag) {
		this.printBtnFlag = printBtnFlag;
	}

	public boolean isGatePassFlag() {
		return gatePassFlag;
	}

	public void setGatePassFlag(boolean gatePassFlag) {
		this.gatePassFlag = gatePassFlag;
	}

	private boolean printFlag=true;
	
	public boolean isPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(boolean printFlag) {
		this.printFlag = printFlag;
	}

	public String getPdfName() {
		return pdfName;
	}

	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getSlno() {
		return slno;
	}

	public void setSlno(int slno) {
		this.slno = slno;
	}

	public String getPermitno() {
		return permitno;
	}

	public void setPermitno(String permitno) {
		this.permitno = permitno;
	}

	public int getDispatched_bottles() {
		return dispatched_bottles;
	}

	public void setDispatched_bottles(int dispatched_bottles) {
		this.dispatched_bottles = dispatched_bottles;
	}

	public String getBreakage() {
		return breakage;
	}

	public void setBreakage(String breakage) {
		this.breakage = breakage;
	}

	public boolean isDataListFlag() {
		return dataListFlag;
	}

	public void setDataListFlag(boolean dataListFlag) {
		this.dataListFlag = dataListFlag;
	}

	public int getBwflDistId() {
		return bwflDistId;
	}

	public void setBwflDistId(int bwflDistId) {
		this.bwflDistId = bwflDistId;
	}

	public String getBwflDistMoblie() {
		return bwflDistMoblie;
	}

	public void setBwflDistMoblie(String bwflDistMoblie) {
		this.bwflDistMoblie = bwflDistMoblie;
	}

	public String getHidden() {

		impl.getDetails(this);
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public BWFL_ScanUploadDT getReciptDetail() {
		return reciptDetail;
	}

	public void setReciptDetail(BWFL_ScanUploadDT reciptDetail) {
		this.reciptDetail = reciptDetail;
	}

	public boolean isView_full_form_flag() {
		return view_full_form_flag;
	}

	public void setView_full_form_flag(boolean view_full_form_flag) {
		this.view_full_form_flag = view_full_form_flag;
	}

	public String getExpOrderNo() {
		return expOrderNo;
	}

	public void setExpOrderNo(String expOrderNo) {
		this.expOrderNo = expOrderNo;
	}

	public Date getExpOrderDt() {
		return expOrderDt;
	}

	public void setExpOrderDt(Date expOrderDt) {
		this.expOrderDt = expOrderDt;
	}

	public String getTransVehicleNo() {
		return transVehicleNo;
	}

	public void setTransVehicleNo(String transVehicleNo) {
		this.transVehicleNo = transVehicleNo;
	}

	public String getRouteDetails() {
		return routeDetails;
	}

	public void setRouteDetails(String routeDetails) {
		this.routeDetails = routeDetails;
	}

	public ArrayList getBwfl_datalist() {/*
										 * try { this.bwfl_datalist =
										 * impl.getDetails_BWFL(this); } catch
										 * (Exception e) { e.printStackTrace();
										 * }
										 */
		return bwfl_datalist;
	}

	public void setBwfl_datalist(ArrayList bwfl_datalist) {
		this.bwfl_datalist = bwfl_datalist;
	}

	public ArrayList getDatalist() {
		if (this.dataListFlag == true) {
			this.setView_full_form_flag(true);
			this.datalist = impl.getFormDetails(this, reciptDetail);
			this.dataListFlag = false;
		}
		// System.out.println("reciptDetail.getPassnofrst()===="+reciptDetail.getPassnofrst());
		this.setScanGatePassNo("17");
		return datalist;
	}

	// reciptDetail.getPassnofrst()
	public void setDatalist(ArrayList datalist) {
		this.datalist = datalist;
	}

	public ArrayList getView_formData() {
		this.setView_full_form_flag(true);

		this.view_formData = impl.getFormDetails(this, reciptDetail);
		this.setScanGatePassNo(reciptDetail.getPassnofrst());
		return view_formData;
	}

	public void setView_formData(ArrayList view_formData) {
		this.view_formData = view_formData;
	}

	
	private boolean listFlagForPrint = true;
	
	
	public boolean isListFlagForPrint() {
		return listFlagForPrint;
	}

	public void setListFlagForPrint(boolean listFlagForPrint) {
		this.listFlagForPrint = listFlagForPrint;
	}

	public ArrayList getReciept_list() {

		try {
			
			if (this.listFlagForPrint == true) {
				this.reciept_list = impl.getRecieptsList(this);
				this.listFlagForPrint = false;
		}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return reciept_list;
	}

	public void setReciept_list(ArrayList reciept_list) {
		this.reciept_list = reciept_list;
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

	public String getLicensee_name() {
		return licensee_name;
	}

	public void setLicensee_name(String licensee_name) {
		this.licensee_name = licensee_name;
	}

	public String getLicense_number() {
		return license_number;
	}

	public void setLicense_number(String license_number) {
		this.license_number = license_number;
	}

	public String getFirm_address() {
		impl.getLicAddress(this);
		return firm_address;
	}

	public void setFirm_address(String firm_address) {
		this.firm_address = firm_address;
	}

	public String view_details() {

		try {
			this.setView_full_form_flag(true);
			this.view_formData = impl.getFormDetails(this, reciptDetail);
			this.setScanGatePassNo(reciptDetail.getPassnofrst());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public void save(ActionEvent e) {

	}

	public void reset() {
		this.view_full_form_flag = false;
		this.dataListFlag = true;
		BWFL_ScanUploadDT dt = new BWFL_ScanUploadDT();
		dt.setDispatched_bottles(0);
		dt.setBreakage("");
		this.gatePassFlag=false;
		listFlagForPrint=true;
		this.tableFlag = false;
		
		this.routeDetails = null;
		this.transVehicleNo = null;
		this.expOrderNo = null;
		this.expOrderDt = null;
	}

	// -------------------------uploader--------------------------

	private String excelFilename;
	private String excelFilepath;
	private String csvFilename;
	private String csvFilepath;
	private String scanGatePassNo;
	private int excelCases;
	ArrayList getVal = new ArrayList();

	
	public String getCsvFilename() {
		return csvFilename;
	}

	public void setCsvFilename(String csvFilename) {
		this.csvFilename = csvFilename;
	}

	public String getCsvFilepath() {
		return csvFilepath;
	}

	public void setCsvFilepath(String csvFilepath) {
		this.csvFilepath = csvFilepath;
	}

	public int getExcelCases() {
		return excelCases;
	}

	public void setExcelCases(int excelCases) {
		this.excelCases = excelCases;
	}

	public String getScanGatePassNo() {
		return scanGatePassNo;
	}

	public void setScanGatePassNo(String scanGatePassNo) {
		this.scanGatePassNo = scanGatePassNo;
	}

	public String getExcelFilename() {
		return excelFilename;
	}

	public void setExcelFilename(String excelFilename) {
		this.excelFilename = excelFilename;
	}

	public String getExcelFilepath() {
		return excelFilepath;
	}

	public void setExcelFilepath(String excelFilepath) {
		this.excelFilepath = excelFilepath;
	}

	public void uploadExcel(UploadEvent event) {

		try {
			int size = 0;
			int counter = 0;
			UploadItem item = event.getUploadItem();

			String FullfileName = item.getFileName();

			String path = item.getFile().getPath();

			String fileName = FullfileName.substring(FullfileName
					.lastIndexOf("\\") + 1);

			ExternalContext con = FacesContext.getCurrentInstance()
					.getExternalContext();

			ServletContext sCon = (ServletContext) con.getContext();

			System.out.println("filename" + FullfileName
					+ "---------------filename" + fileName);
			size = item.getFileSize();
			this.excelFilename = FullfileName;
			this.excelFilepath = path;

		} catch (Exception ee) {
			ee.printStackTrace();
			System.out.println("exception in upload@");
		} finally {

		}

	}

	public String importExcel() {
		impl.saveExcelData(this);
		return "";
	}

	public ArrayList getGetVal() {
		
		return getVal;
	}

	public void setGetVal(ArrayList getVal) {
		this.getVal = getVal;
	}

	public void delete() {
		impl.deleteData(this);
		//this.tableFlag = false;
		//gatePassFlag=false;
		this.getVal = impl.getExcelData(this);
	}

	private boolean valid;
	public boolean isValid() {
		valid = true;
		if (valid) {
			for (int i = 0; i < this.view_formData.size(); i++) {
				BWFL_ScanUploadDT table = new BWFL_ScanUploadDT();
				table = (BWFL_ScanUploadDT) this.view_formData.get(i);
				if (!(Validate.validateStrReq("exportOrder",
						this.getExpOrderNo())))
					valid = false;
				else if (!(Validate.validateStrReq("recievedfromuser",
						table.getReceived_from_usr())))
					valid = false;
				else if (!(Validate.validateOnlyInt("recievedfromuser",
						table.getReceived_from_usr())))
					valid = false;
				else if (!(Validate.validateDate("exportOrderdate",
						this.getExpOrderDt())))
					valid = false;
				else if (!(Validate.validateStrReq("vehiclenmbr",
						this.getTransVehicleNo())))
					valid = false;
				else if (!(Validate.validateStrReq("routedtl",
						this.getRouteDetails())))
					valid = false;
			}

		}

		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public void finalSubmit() {
		
		//if (impl.excelCases(this) == impl.recieveCases(this)) {
			if(impl.checkEtinNmbr(this)==true){
			if (isValid()  && bottlingNotDoneFlag==false) {				
				if (impl.updateDispatch(this) == true ) {

					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Data Save Successfully","Data Save Successfully"));
					
					this.tableFlag = false;
					reset();
					listFlagForPrint=true;
				}
				
			} else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error Occured while saving !!! ","Error Occured while saving !!!"));
			}
		} else {
			/*FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(
			"No.of cases in Gatepass and in Uploaded Excel does not match! ","No.of cases in Gatepass and in Uploaded Excel does not match!"));*/
			
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(
			" Cases For All Brand or Package are not uploaded !! ","Cases For All Brand or Package are not uploaded !! "));
		}

	}

	public void updateData(ActionEvent e) {
		
		
		
		if (this.routeDetails.length()>0 &&  this.routeDetails!=null && this.transVehicleNo.length()>0 &&  this.transVehicleNo!=null
				&&	this.expOrderNo.length()>0 &&  this.expOrderNo!=null &&  this.expOrderDt!=null  ) {
		 UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
					.getParent();
			BWFL_ScanUploadDT dt = (BWFL_ScanUploadDT)this.getDatalist().get(uiTable.getRowIndex()); 
			if(dt.getDispatched_bottles() <=dt.getInt_planned_bottles()){
		if (impl.updateData(this,dt)) {
			
			FacesContext.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Data Save Successfully",
									"Data Save Successfully"));
			reset();
		}}else{
			
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Dispatched bottles should not be greater than Available Bottles",
							"Dispatched bottles should not be greater than Available Bottles"));
			
				reset();
			
		}}else {
			
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"(*) marked entries should not be null",
							"(*) marked entries should not be null"));

			 
		}
	}

	private String gatepass;
	private Date dispatch_date;

	public String getGatepass() {
		return gatepass;
	}

	public void setGatepass(String gatepass) {
		this.gatepass = gatepass;
	}

	public Date getDispatch_date() {
		return dispatch_date;
	}

	public void setDispatch_date(Date dispatch_date) {
		this.dispatch_date = dispatch_date;
	}

	public void printreport(ActionEvent e) {
		 UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		BWFL_ScanUploadDT dt = (BWFL_ScanUploadDT)this.reciept_list.get(uiTable.getRowIndex()); 
		this.setDispatch_date(dt.getDispatch_date());
		this.setGatepass(dt.getPassnothrd());
		if (impl.printReport(this)==true) {
		 dt.setPrintFlag(true);
		
		} else { 
			dt.setPrintFlag(false);

		}

	}
	
	private String gatepassForCsv;
	private String permitNmbr;
	
	public String getGatepassForCsv() {
		return gatepassForCsv;
	}

	public void setGatepassForCsv(String gatepassForCsv) {
		this.gatepassForCsv = gatepassForCsv;
	}


	public String getPermitNmbr() {
		return permitNmbr;
	}

	public void setPermitNmbr(String permitNmbr) {
		this.permitNmbr = permitNmbr;
	}
	
	private String mappedUnmapedType;

	public String getMappedUnmapedType() {
		return mappedUnmapedType;
	}

	public void setMappedUnmapedType(String mappedUnmapedType) {
		this.mappedUnmapedType = mappedUnmapedType;
	}

	public void scanAndUpload(ActionEvent ae) {
		UIDataTable uiTable = (UIDataTable) ae.getComponent().getParent().getParent();
		BWFL_ScanUploadDT dt = (BWFL_ScanUploadDT)this.reciept_list.get(uiTable.getRowIndex()); 
		
		this.setDispatch_date(dt.getDispatch_date());
		this.setGatepass(dt.getPassnothrd());		
		this.setScanGatePassNo(dt.getPassnothrd());
		this.setGatepassForCsv(dt.getPassnothrd());
		this.setGroupid(dt.getGroupid());
		this.setPermitNmbr(dt.getPermitNmbr_dt());
		this.setMappedUnmapedType(dt.getMappedUnmapedType_dt());
		
		if (impl.totalCasesOnPermit(this) == impl.totalCasesDispatchedOnPermit(this)){
			this.gatePassFlag = true;
			this.tableFlag = true;
		}else{
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(
			" Few Brands or Package on Permit No. "+ this.permitNmbr + " are pending for save.The csv file will be uploaded for all brand & package on this Permit No. not for a individual brand or package !! ", 
			" Few Brands or Package on Permit No. "+ this.permitNmbr + " are pending for save.The csv file will be uploaded for all brand & package on this Permit No. not for a individual brand or package !! "));
		}
		

		//System.out.println("this.gatepassForCsv-----------"+this.gatepassForCsv);
		
		this.getVal = impl.getExcelData(this);
	}
	private boolean finflg;
	
	
	public boolean isFinflg() {
		return finflg;
	}

	public void setFinflg(boolean finflg) {
		this.finflg = finflg;
	}

	public void uploadCsv(UploadEvent event) {
		try {
			int size = 0;
			int counter = 0;
			UploadItem item = event.getUploadItem();
			String FullfileName = item.getFileName();
			String path = item.getFile().getPath();
			String fileName = FullfileName.substring(FullfileName.lastIndexOf("\\") + 1);
			ExternalContext con = FacesContext.getCurrentInstance().getExternalContext();
			ServletContext sCon = (ServletContext) con.getContext();
			
			size = item.getFileSize();
			this.setCsvFilename(FullfileName);
			this.setCsvFilepath(path);
		} catch (Exception ee) {
			ee.printStackTrace();
			
		} finally {
		}
	}
	public String csvSubmit() throws IOException {
		impl.saveCSV(this);
		
		this.bottlingNotDoneFlag=false;
		this.gatePassFlag = true;
		this.tableFlag = true;

		this.getVal = impl.getExcelData(this);
		return "";
	}
	public void finalSubmit1(){
		if(impl.updateDispatch(this)){
			this.gatePassFlag=false;
			this.reset();
			this.getReciept_list();
		}
	}
	
	
	private boolean bottlingNotDoneFlag=false;
	private boolean tableFlag;

	public boolean isBottlingNotDoneFlag() {
		return bottlingNotDoneFlag;
	}

	public void setBottlingNotDoneFlag(boolean bottlingNotDoneFlag) {
		this.bottlingNotDoneFlag = bottlingNotDoneFlag;
	}
	
	public boolean isTableFlag() {
		return tableFlag;
	}

	public void setTableFlag(boolean tableFlag) {
		this.tableFlag = tableFlag;
	}
	private String select_lic_no;
    private ArrayList licno=new ArrayList();
	public ArrayList getLicno() {
		
		try{
			this.licno=impl.getLicense();
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
	public void licChangeLsnr(ValueChangeEvent event) {
		Object obj = event.getNewValue();
		String id = String.valueOf(obj); 
		this.select_lic_no=id;
		this.dataListFlag=true;
		this.listFlagForPrint=true;
	}
}
