package com.mentor.action;

import java.io.IOException;
import java.text.DecimalFormat;
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





import com.mentor.datatable.ImflOldStockBwfl_DataTable;
import com.mentor.impl.ImflOldStockBwfl_Impl;
import com.mentor.utility.Validate;



public class ImflOldStockFL36 {

	ImflOldStockBwfl_Impl impl = new ImflOldStockBwfl_Impl();

	private String draftpdfname;
	private String pdfname;
	private String hidden;
	private int bwflId;
	private int licenseTypeId;
	private String vch_bwfl_name;
	private String vch_bwfl_address;
	private Date dt_date = new Date();
	private Date validtill_date;
	private String vch_from;
	private String vch_to;
	private String vch_from_lic_no;
	private String vch_to_lic_no;
	private String routeDtl;
	private String vehicleNo;
	private String vehicleDrvrName;
	private String vehicleAgencyNmAdrs;
	private double grossWeight = 0;
	private double tierWeight = 0;
	private double netWeight = 0;
	public String vch_bond = "1";
	public ArrayList displaylist = new ArrayList();
	public ArrayList fromLicList = new ArrayList();
	public ArrayList toLicList = new ArrayList();
	private String bwfllicno;
	
	
	
	
	public String getBwfllicno() {
		return bwfllicno;
	}

	public void setBwfllicno(String bwfllicno) {
		this.bwfllicno = bwfllicno;
	}

	public int getLicenseTypeId() {
		return licenseTypeId;
	}

	public void setLicenseTypeId(int licenseTypeId) {
		this.licenseTypeId = licenseTypeId;
	}

	public String getVch_bond() {
		return vch_bond;
	}

	public void setVch_bond(String vch_bond) {
		this.vch_bond = vch_bond;
	}

	public String getPdfname() {
		return pdfname;
	}

	public void setPdfname(String pdfname) {
		this.pdfname = pdfname;
	}

	public String getHidden() {
		try {
			impl.getDetails(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	



	public int getBwflId() {
		return bwflId;
	}

	public void setBwflId(int bwflId) {
		this.bwflId = bwflId;
	}

	public String getVch_bwfl_name() {
		return vch_bwfl_name;
	}

	public void setVch_bwfl_name(String vch_bwfl_name) {
		this.vch_bwfl_name = vch_bwfl_name;
	}

	public String getVch_bwfl_address() {
		return vch_bwfl_address;
	}

	public void setVch_bwfl_address(String vch_bwfl_address) {
		this.vch_bwfl_address = vch_bwfl_address;
	}

	public Date getDt_date() {
		return dt_date;
	}

	public void setDt_date(Date dt_date) {
		this.dt_date = dt_date;
	}

	public Date getValidtill_date() {
		return validtill_date;
	}

	public void setValidtill_date(Date validtill_date) {
		this.validtill_date = validtill_date;
	}

	public String getVch_from() {
		return vch_from;
	}

	public void setVch_from(String vch_from) {
		this.vch_from = vch_from;
	}

	public String getVch_to() {
		return vch_to;
	}

	public void setVch_to(String vch_to) {
		this.vch_to = vch_to;
	}

	public String getVch_from_lic_no() {
		return vch_from_lic_no;
	}

	public void setVch_from_lic_no(String vch_from_lic_no) {
		this.vch_from_lic_no = vch_from_lic_no;
	}

	public String getVch_to_lic_no() {
		return vch_to_lic_no;
	}

	public void setVch_to_lic_no(String vch_to_lic_no) {
		this.vch_to_lic_no = vch_to_lic_no;
	}

	public String getRouteDtl() {
		return routeDtl;
	}

	public void setRouteDtl(String routeDtl) {
		this.routeDtl = routeDtl;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getVehicleDrvrName() {
		return vehicleDrvrName;
	}

	public void setVehicleDrvrName(String vehicleDrvrName) {
		this.vehicleDrvrName = vehicleDrvrName;
	}

	public String getVehicleAgencyNmAdrs() {
		return vehicleAgencyNmAdrs;
	}

	public void setVehicleAgencyNmAdrs(String vehicleAgencyNmAdrs) {
		this.vehicleAgencyNmAdrs = vehicleAgencyNmAdrs;
	}

	public double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public double getTierWeight() {
		return tierWeight;
	}

	public void setTierWeight(double tierWeight) {
		this.tierWeight = tierWeight;
	}

	public double getNetWeight() {
		if (this.grossWeight > 0.0 && this.tierWeight > 0.0) {
			this.netWeight = this.grossWeight - this.tierWeight;
		} else {
			this.netWeight = 0.0;
		}
		return netWeight;
	}

	public void setNetWeight(double netWeight) {
		this.netWeight = netWeight;
	}

	public ArrayList getDisplaylist() {
		return displaylist;
	}

	public void setDisplaylist(ArrayList displaylist) {
		this.displaylist = displaylist;
	}
	
	// function truncating 
	static double truncateTo( double unroundedNumber, int decimalPlaces )
	{
	    int truncatedNumberInt = (int)( unroundedNumber * Math.pow( 10, decimalPlaces ) );
	    double truncatedNumber = (double)( truncatedNumberInt / Math.pow( 10, decimalPlaces ) );
	    return truncatedNumber;
	}
	
	
	
	public double getDb_total_value() {

		double duty = 0.0;
		try {

			for (int i = 0; i < this.displaylist.size(); i++) {
				ImflOldStockBwfl_DataTable dt = (ImflOldStockBwfl_DataTable) this
						.getDisplaylist().get(i);
				duty = duty+dt.getCalculated_duty();

			}
			db_total_value = (duty);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return truncateTo(db_total_value, 2);
	}

	public void setDb_total_value(double db_total_value) {
		this.db_total_value = db_total_value;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public String getDraftpdfname() {
		return draftpdfname;
	}

	public void setDraftpdfname(String draftpdfname) {
		this.draftpdfname = draftpdfname;
	}

	public double getDb_total_add_value() {

		double addduty = 0.0;
		try {

			for (int i = 0; i < this.displaylist.size(); i++) {
				ImflOldStockBwfl_DataTable dt = (ImflOldStockBwfl_DataTable) this
						.getDisplaylist().get(i);
				addduty = addduty + dt.getCalculated_add_duty();

			}
			db_total_add_value = (addduty);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return truncateTo(db_total_add_value, 2);
	}

	public void setDb_total_add_value(double db_total_add_value) {
		this.db_total_add_value = db_total_add_value;
	}

	public double getSum_add() {
		return sum_add;
	}

	public void setSum_add(double sum_add) {
		this.sum_add = sum_add;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isAddflag() {
		return addflag;
	}

	public void setAddflag(boolean addflag) {
		this.addflag = addflag;
	}

	public ArrayList getFromLicList() {
		return fromLicList;
	}

	public void setFromLicList(ArrayList fromLicList) {
		this.fromLicList = fromLicList;
	}

	public ArrayList getToLicList() {
		return toLicList;
	}

	public void setToLicList(ArrayList toLicList) {
		this.toLicList = toLicList;
	}

	public String fromListMethod(ValueChangeEvent vce) 
	{
		System.out.println("--------------------"+this.bwflId);
		Object obj = vce.getNewValue();
		String s = (String) obj;

		if (s.equalsIgnoreCase("FL3")) {
			this.vch_from = "FL3";
			this.fromLicList = impl.fromLicListImpl(this);

			// this.displaylist = impl.displaylistImpl(this, vch_from_lic_no);

		} else if (s.equalsIgnoreCase("FL3A")) {
			this.vch_from = "FL3A";
			this.fromLicList = impl.fromLicListImpl(this);
			// this.displaylist = impl.displaylistImpl(this, vch_from_lic_no);
		}

		else {
		}

		return "";
	}

	
	private boolean drpdwnFlg;
	private boolean drpdwnFlg1;
	
	private int district1;
	private int district2;
	private int district3;
	private int districtId;
	
	private ArrayList districtList = new ArrayList();
	private ArrayList brclicNmbrList = new ArrayList();
	private ArrayList licNmbrList = new ArrayList();
	
	
	

	

	public String listMethod(ValueChangeEvent vce) {

		String val = (String) vce.getNewValue();
		this.displaylist = impl.displaylistImpl(this, val);
	
		if (val.equalsIgnoreCase("DW")) {

			this.licNmbrList = impl.getlicenseNmbr(this);
			this.setDrpdwnFlg(true);
			this.setDrpdwnFlg1(false);
			if (this.getLicenseTypeId() == 1|| this.getLicenseTypeId() == 3) 
			{
				System.out.println("license id"+this.getLicenseTypeId());
				this.setLicNoflg(true);
				this.setLicNoflg1(false);
				this.setLicNoflg2(false);
			} 
			else if (this.getLicenseTypeId() == 2 || this.getLicenseTypeId() == 4) 
			{
				System.out.println("license id"+this.getLicenseTypeId());
				this.setLicNoflg(false);
				this.setLicNoflg1(true);
				this.setLicNoflg2(false);
			}
			
		} 
		else if (val.equalsIgnoreCase("BRC")) 
		{
			System.out.println("license id"+this.getLicenseTypeId());
			this.brclicNmbrList = impl.getbrcLicenseNo(this);
			this.setLicNoflg(false);
			this.setLicNoflg1(false);
			this.setLicNoflg2(false);
			this.setDrpdwnFlg(false);
			this.setDrpdwnFlg1(true);
		}
		
		
		return "";
	}

	public String toListMethod(ValueChangeEvent vce) {

		Object obj = vce.getNewValue();
		String s = (String) obj;

		if (s != null) {
			if (s.equalsIgnoreCase("FL1")) {
				this.vch_to = "FL1";
				this.toLicList = impl.toliclistImpl(this);
			}

			else if (s.equalsIgnoreCase("FL1A")) {
				this.vch_to = "FL1A";
				this.toLicList = impl.toliclistImpl1a(this);

			}
		} else {
		}

		return "";
	}

	// ==================FOR TOTAL DUTY==========================

	public double db_total_value = 0;
	public double sum = 0;
	public boolean flag = true;

	public void calculateTotalDuty(ActionEvent ae) {

		if (isFlag()) {
			this.setSum(0);
			for (int i = 0; i < this.displaylist.size(); i++) {
				ImflOldStockBwfl_DataTable dt = (ImflOldStockBwfl_DataTable) this.displaylist
						.get(i);
				this.setSum(this.getSum() + dt.getCalculated_duty());

			}
		}
		this.flag = false;

	}

	// ==================FOR ADDITIONAL TOTAL DUTY==========================

	public double db_total_add_value = 0;
	public double sum_add = 0;
	public boolean addflag = true;

	public void calculateTotalAddDuty(ActionEvent ae) {

		if (isAddflag()) {
			this.setSum_add(0);
			for (int i = 0; i < this.displaylist.size(); i++) {
				ImflOldStockBwfl_DataTable dt = (ImflOldStockBwfl_DataTable) this.displaylist
						.get(i);

				this.setSum_add(this.getSum_add() + dt.getCalculated_add_duty());

			}
		}
		this.addflag = false;

	}

	private boolean validateInput1;

	public boolean isValidateInput1() {

		validateInput1 = true;
		if (!(Validate.validateDate("date", this.getDt_date())))
			validateInput1 = false;
		else if (!(Validate.validateDate("validtill", this.getValidtill_date())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("radiofrom", this.getVch_from())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("radioto", this.getVch_to())))
			validateInput1 = false;
		/*else if (!(Validate.validateStrReq("vch_from_lic_no",
				this.getVch_from_lic_no())))
			validateInput1 = false;*/
		//else if (!(Validate.validateStrReq("vch_to_lic_no",
				//this.getVch_to_lic_no())))
			//validateInput1 = false;
		else if (!(Validate.validateStrReq("routedtl", this.getRouteDtl())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("vehiclenmbr", this.getVehicleNo())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("routedetails", this.getRouteDtl())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("vehicleno", this.getVehicleNo())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("drivername",
				this.getVehicleDrvrName())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("vehicleAgencyNmAdrs",
				this.getVehicleAgencyNmAdrs())))
			validateInput1 = false;
		else if (!(Validate
				.validateDouble("grossWeight", this.getGrossWeight())))
			validateInput1 = false;
		else if (!(Validate.validateDouble("tierweight", this.getTierWeight())))
			validateInput1 = false;

		for (int i = 0; i < this.displaylist.size(); i++) {
			ImflOldStockBwfl_DataTable dt = (ImflOldStockBwfl_DataTable) displaylist
					.get(i);
			if (dt.getDispatchBoxes() > 0 && dt.getInt_dispatch() > 0) {
				if (dt.getBatchNo() == null || dt.getBatchNo().equals("")) {
					validateInput1 = false;
					FacesContext.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_ERROR,
											"Enter Batch No At Line No !!! "
													+ (i + 1),
											"Enter Batch No At Line No !!! "
													+ (i + 1)));
					break;
				}
			}
		}
		int sumBottles = 0;
		int sumBoxes = 0;
		for (int i = 0; i < this.displaylist.size(); i++) {
			ImflOldStockBwfl_DataTable dt = (ImflOldStockBwfl_DataTable) displaylist
					.get(i);
			sumBottles += dt.getInt_dispatch();
			sumBoxes += dt.getDispatchBoxes();
			if (dt.getDispatchBoxes() > dt.getBoxAvailable()) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										"Dispatch Boxes Should Be Less Than Available Boxes !!! ",
										"Dispatch Boxes Should Be Less Than Available Boxes !!!"));
				validateInput1 = false;
			}
		}
		if (sumBoxes == 0) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Dispatch Boxes Should Be Greater Than Zero !!! ",
							"Dispatch Boxes Should Be Greater Than Zero !!!"));
			validateInput1 = false;
		} else if (sumBottles == 0) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"Dispatch Bottles Should Be Greater Than Zero !!! ",
									"Dispatch Bottles Should Be Greater Than Zero !!!"));
			validateInput1 = false;
		}

		return validateInput1;
	}

	public void setValidateInput1(boolean validateInput1) {
		this.validateInput1 = validateInput1;
	}

	public void saveMethod() {
		try {
			if (isValidateInput1()) {
				impl.saveMethodImpl(this);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ArrayList getListWholesale = new ArrayList();
	private boolean listFlagForPrint = true;

	public ArrayList getGetListWholesale() {
		if (this.listFlagForPrint == true) {
			this.getListWholesale = impl.getWholeSaleManufactureList(this);
			this.listFlagForPrint = false;
		}
		return getListWholesale;
	}

	public void setGetListWholesale(ArrayList getListWholesale) {
		this.getListWholesale = getListWholesale;
	}

	public boolean isListFlagForPrint() {
		return listFlagForPrint;
	}

	public void setListFlagForPrint(boolean listFlagForPrint) {
		this.listFlagForPrint = listFlagForPrint;
	}

	// ---------------------print draft report---------------

	private String PdfDraft;
	private String pdfName;
	private Date printDate;
	private String printGatePassNo;

	public String getPdfDraft() {
		return PdfDraft;
	}

	public void setPdfDraft(String pdfDraft) {
		PdfDraft = pdfDraft;
	}

	public String getPdfName() {
		return pdfName;
	}

	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}

	public Date getPrintDate() {
		return printDate;
	}

	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}

	public String getPrintGatePassNo() {
		return printGatePassNo;
	}

	public void setPrintGatePassNo(String printGatePassNo) {
		this.printGatePassNo = printGatePassNo;
	}

	public void printDraftReport(ActionEvent e) {

		try {
			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
			ImflOldStockBwfl_DataTable dt = (ImflOldStockBwfl_DataTable) this.getListWholesale.get(uiTable.getRowIndex());

			this.setPrintDate(dt.getDt_date());
			this.setPrintGatePassNo(dt.getVch_gatepass_no());

			if (impl.printDraftReport(this) == true) {
				dt.setDraftprintFlag(true);

			} else {

				dt.setDraftprintFlag(false);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void printReport(ActionEvent e) {

		try {
			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
			ImflOldStockBwfl_DataTable dt = (ImflOldStockBwfl_DataTable) this.getListWholesale.get(uiTable.getRowIndex());

			this.setPrintDate(dt.getDt_date());
			this.setPrintGatePassNo(dt.getVch_gatepass_no());

			if (impl.printReport(this) == true) {
				dt.setMyFlagDt(true);

			} else {

				dt.setMyFlagDt(false);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	// --------------------scan and upload excel---------------------------
	private boolean finalizePrint;
	public boolean isFinalizePrint() {
		return finalizePrint;
	}

	public void setFinalizePrint(boolean finalizePrint) {
		this.finalizePrint = finalizePrint;
	}


	private String scanGatePassNo;
	private String ScanVchFrom;
	private boolean gatePassFlag;
	private String excelFilename;
	private String excelFilepath;
	private boolean bottlingNotDoneFlag=false;
	private ArrayList getVal = new ArrayList();
	private boolean valFlag;
	private int excelCases;
	

	
	public int getExcelCases() {
		return excelCases;
	}

	public void setExcelCases(int excelCases) {
		this.excelCases = excelCases;
	}

	public boolean isValFlag() {
		return valFlag;
	}

	public void setValFlag(boolean valFlag) {
		this.valFlag = valFlag;
	}

	public String getScanGatePassNo() {
		return scanGatePassNo;
	}

	public void setScanGatePassNo(String scanGatePassNo) {
		this.scanGatePassNo = scanGatePassNo;
	}

	public String getScanVchFrom() {
		return ScanVchFrom;
	}

	public void setScanVchFrom(String scanVchFrom) {
		ScanVchFrom = scanVchFrom;
	}

	public boolean isGatePassFlag() {
		return gatePassFlag;
	}

	public void setGatePassFlag(boolean gatePassFlag) {
		this.gatePassFlag = gatePassFlag;
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

	public boolean isBottlingNotDoneFlag() {
		return bottlingNotDoneFlag;
	}

	public void setBottlingNotDoneFlag(boolean bottlingNotDoneFlag) {
		this.bottlingNotDoneFlag = bottlingNotDoneFlag;
	}

	public ArrayList getGetVal() {
		return getVal;
	}

	public void setGetVal(ArrayList getVal) {
		this.getVal = getVal;
	}

	public void scanAndUpload(ActionEvent ae) {

		UIDataTable uiTable = (UIDataTable) ae.getComponent().getParent().getParent();
		ImflOldStockBwfl_DataTable dt = (ImflOldStockBwfl_DataTable) this.getListWholesale.get(uiTable.getRowIndex());

		this.setScanGatePassNo(dt.getVch_gatepass_no());
		this.setScanVchFrom(dt.getVch_from());
		
		this.gatePassFlag = true;
		this.getVal = impl.getCsvData(this);
	}
	
	
	public void uploadExcel(UploadEvent event)
	{

		try {
			int size = 0;
			int counter = 0;
			UploadItem item = event.getUploadItem();

			String FullfileName = item.getFileName();

			String path = item.getFile().getPath();

			String fileName = FullfileName.substring(FullfileName.lastIndexOf("\\") + 1);

			ExternalContext con = FacesContext.getCurrentInstance().getExternalContext();

			ServletContext sCon = (ServletContext) con.getContext();

			//System.out.println("filename" + FullfileName+ "---------------filename" + fileName);
			size = item.getFileSize();
			this.excelFilename = FullfileName;
			this.excelFilepath = path;

		} catch (Exception ee) {
			ee.printStackTrace();
			
		} finally {

		}

	
	}
	
	/*public String importExcel()
	{
		impl.saveExcelData(this);
		
		this.bottlingNotDoneFlag=false;
		this.gatePassFlag = true;		
		this.getVal = impl.getExcelData(this);
		
		return "";
	}
*/
	public void finalSubmit()
	{
	 
		if (impl.csvCases(this) == impl.recieveCases(this)) {

			if (impl.updateFL3(this) == true) 
			{							
				this.gatePassFlag = false;
				listFlagForPrint=true;
				
				

				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Data Save Successfully","Data Save Successfully"));
			} 
			else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error Occured while saving !!! ",
								"Error Occured while saving !!!"));
			}
		} 
	else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("No.of cases in Gatepass and in Uploaded Excel does not match! ", "No.of cases in Gatepass and in Uploaded Excel does not match!"));
		}

	
	}
	
	public void delete()
	{
		impl.deleteData(this);		
		this.gatePassFlag = false;
	}
	
	public void clearAll() {

		this.bwflId = 0;
		this.vch_bwfl_address = "";
		this.vehicleNo = null;
		this.routeDtl = null;
		this.vehicleDrvrName = null;
		this.vehicleAgencyNmAdrs = null;
		this.dt_date = new Date();
		this.validtill_date = null;
		this.vch_from = "";
		this.vch_to = "";
		this.vch_from_lic_no = "";
		this.vch_to_lic_no = "";
		this.vch_bond = "1";
		this.fromLicList.clear();
		this.toLicList.clear();
		this.displaylist.clear();
		this.db_total_value = 0;
		this.db_total_add_value = 0;
		this.sum = 0;
		this.sum_add = 0;
		this.tierWeight = 0;
		this.grossWeight = 0;
		this.netWeight = 0;
		this.listFlagForPrint = true;
	}
	public void uploadCsv(UploadEvent event) {

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

			size = item.getFileSize();
			this.setCsvFilename(FullfileName);
			this.setCsvFilepath(path);

		} catch (Exception ee) {
			ee.printStackTrace();
			 
		} finally {

		}

	}
	public void csvSubmit()  
	{
		try
		{
		 
		impl.saveCSV(this);
		this.gatePassFlag = true;
		this.getVal = impl.getCsvData(this);
		}
		catch(Exception e)
		{
			
		}

		
	}
	private String licenseNumber;
	private String licenseeAdrs;
	private String licenseeName;
	private String licenseeDistrict;
	private String csvFilename;
	private String csvFilepath;
	private String exclcsv;
	private String brc_to_lic;

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getBrc_to_lic() {
		return brc_to_lic;
	}

	public void setBrc_to_lic(String brc_to_lic) {
		this.brc_to_lic = brc_to_lic;
	}

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

	public String getExclcsv() {
		return exclcsv;
	}

	public void setExclcsv(String exclcsv) {
		this.exclcsv = exclcsv;
	}

	

	
	public boolean isDrpdwnFlg() {
		return drpdwnFlg;
	}

	public void setDrpdwnFlg(boolean drpdwnFlg) {
		this.drpdwnFlg = drpdwnFlg;
	}

	public boolean isDrpdwnFlg1() {
		return drpdwnFlg1;
	}

	public void setDrpdwnFlg1(boolean drpdwnFlg1) {
		this.drpdwnFlg1 = drpdwnFlg1;
	}

	public int getDistrict1() {
		return district1;
	}

	public void setDistrict1(int district1) {
		this.district1 = district1;
	}

	public int getDistrict2() {
		return district2;
	}

	public void setDistrict2(int district2) {
		this.district2 = district2;
	}

	public int getDistrict3() {
		return district3;
	}

	public void setDistrict3(int district3) {
		this.district3 = district3;
	}

	public ArrayList getDistrictList() {
		try {
			this.districtList = impl.getDistrictList(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return districtList;
	}

	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}

	public ArrayList getBrclicNmbrList() {
		return brclicNmbrList;
	}

	public void setBrclicNmbrList(ArrayList brclicNmbrList) {
		this.brclicNmbrList = brclicNmbrList;
	}

	public ArrayList getLicNmbrList() {
		return licNmbrList;
	}

	public void setLicNmbrList(ArrayList licNmbrList) {
		this.licNmbrList = licNmbrList;
	}
	public String drpdownMethod(ValueChangeEvent vce) {

		String val = (String) vce.getNewValue();
		impl.getlicenseeDetail(this, val);
		this.licenseNumber=val;

		return "";
	}

	public String waredrpMethod(ValueChangeEvent vce) {

		String val = (String) vce.getNewValue();
		impl.getwareLicenseeDetail(this, val);
		this.licenseNumber=val;

		return "";
	}

	public String getLicenseeAdrs() {
		return licenseeAdrs;
	}

	public void setLicenseeAdrs(String licenseeAdrs) {
		this.licenseeAdrs = licenseeAdrs;
	}

	public String getLicenseeName() {
		return licenseeName;
	}

	public void setLicenseeName(String licenseeName) {
		this.licenseeName = licenseeName;
	}

	public String getLicenseeDistrict() {
		return licenseeDistrict;
	}

	public void setLicenseeDistrict(String licenseeDistrict) {
		this.licenseeDistrict = licenseeDistrict;
	}

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}
private int licenseeid;

public int getLicenseeid() {
	return licenseeid;
}

public void setLicenseeid(int licenseeid) {
	this.licenseeid = licenseeid;
}

private String officerEmail;

public String getOfficerEmail() {
	return officerEmail;
}

public void setOfficerEmail(String officerEmail) {
	this.officerEmail = officerEmail;
}


private boolean licNoflg;
private boolean licNoflg1;
private boolean licNoflg2;

public boolean isLicNoflg() {
	return licNoflg;
}

public void setLicNoflg(boolean licNoflg) {
	this.licNoflg = licNoflg;
}

public boolean isLicNoflg1() {
	return licNoflg1;
}

public void setLicNoflg1(boolean licNoflg1) {
	this.licNoflg1 = licNoflg1;
}

public boolean isLicNoflg2() {
	return licNoflg2;
}

public void setLicNoflg2(boolean licNoflg2) {
	this.licNoflg2 = licNoflg2;
}



	
}
