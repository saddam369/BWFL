package com.mentor.action;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletContext;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.mentor.datatable.FL2_2B_StockDataTable;
import com.mentor.datatable.Stock_FL2_FL2B_activeDt;
import com.mentor.datatable.GatepassToDistrict_FL2_FL2B_DT;
import com.mentor.datatable.Stock_FL2_FL2B_activeDt;
import com.mentor.impl.FL2_2B_StockImpl;
import com.mentor.impl.Stock_FL2_FL2B_activeImpl;
import com.mentor.impl.Stock_FL2_FL2B_activeImpl;
import com.mentor.utility.Validate;

//new action

public class Stock_FL2_FL2B_activeAction {

	Stock_FL2_FL2B_activeImpl impl = new Stock_FL2_FL2B_activeImpl();

	private String name;
	private String address;
	private String loginType;
	private String hidden;
	private int dist_id;
	private String gatePassNo;
	private Date createdDate;
	private String licenseNmbr;
	private String excelFilename;
	private String excelFilepath;
	private String permitNo = "NA";
	private int receiveCases;
	private int excelCases;
private String vchFrom;
	
	
	
	public String getVchFrom() {
		return vchFrom;
	}

	public void setVchFrom(String vchFrom) {
		this.vchFrom = vchFrom;
	}
	public int getReceiveCases() {
		return receiveCases;
	}

	public void setReceiveCases(int receiveCases) {
		this.receiveCases = receiveCases;
	}

	public int getExcelCases() {
		return excelCases;
	}

	public void setExcelCases(int excelCases) {
		this.excelCases = excelCases;
	}

	public String getPermitNo() {
		return permitNo;
	}

	public void setPermitNo(String permitNo) {
		this.permitNo = permitNo;
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

	public ArrayList gatePssDisplaylist = new ArrayList();

	public ArrayList show_Data_table = new ArrayList();

	private String unitid;
	private String unittype;

	public String getUnitid() {
		return unitid;
	}

	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}

	public String getUnittype() {
		return unittype;
	}

	public void setUnittype(String unittype) {
		this.unittype = unittype;
	}

	public String getLicenseNmbr() {
		return licenseNmbr;
	}

	public void setLicenseNmbr(String licenseNmbr) {
		this.licenseNmbr = licenseNmbr;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public ArrayList getShow_Data_table() {
		this.show_Data_table = impl.getDataTable(this);
		return show_Data_table;
	}

	public void setShow_Data_table(ArrayList show_Data_table) {
		this.show_Data_table = show_Data_table;
	}

	public ArrayList getGatePssDisplaylist() {
		return gatePssDisplaylist;
	}

	public void setGatePssDisplaylist(ArrayList gatePssDisplaylist) {
		this.gatePssDisplaylist = gatePssDisplaylist;
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

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getHidden() {
		try {
			impl.getDetails(this);
		} catch (Exception e) {
		}
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public int getDist_id() {
		return dist_id;
	}

	public void setDist_id(int dist_id) {
		this.dist_id = dist_id;
	}

	public String getGatePassNo() {
		return gatePassNo;
	}

	public void setGatePassNo(String gatePassNo) {
		this.gatePassNo = gatePassNo;
	}

	private String licenseing;
	private String oldnew;

	/*
	 * public void check() { System.out.println(getLicenseing()); if
	 * (impl.checkgatePass(this) == true) { this.gatePssDisplaylist =
	 * impl.gatePassDetail(this); } else {
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("Please Enter Correct GATEPASS NO.",
	 * "Please Enter Correct GATEPASS NO."));
	 * 
	 * } this.setScanGatePassNo(this.getGatePassNo());
	 * 
	 * }
	 */
	public void radioListiner(ValueChangeEvent e) {

	}

	// --------------------------Reset ----------------

	public void reset() {

		this.gatePassNo = "";
		//this.createdDate = null;
		// this. createdDate= new Date();
		this.gatePssDisplaylist.clear();

	}

	public String getOldnew() {
		return oldnew;
	}

	public void setOldnew(String oldnew) {
		this.oldnew = oldnew;
	}

	private boolean validateInput;

	public boolean isValidateInput() {
		validateInput = true;

		if (!(Validate.validateStrReq("gatepass", this.getGatePassNo())))
			validateInput = false;
		else if (!(Validate.validateDate("gatepassdate", this.getCreatedDate())))
			validateInput = false;
		else if (!(Validate.validateStrReq("licensing", this.getLicenseing())))
			validateInput = false;

		return validateInput;
	}

	public void check() {

		// if (impl.checkgatePass(this) == true)
		// {
		if (isValidateInput()) {
			this.gatePssDisplaylist = impl.gatePassDetail(this);
		}

		/*
		 * else { FacesContext.getCurrentInstance().addMessage(null,new
		 * FacesMessage( FacesMessage.SEVERITY_ERROR, "Total !!! ",
		 * "Total  !!!")); System.out.println("kindly validate the"); }
		 */

		// }
		// else {
		// FacesContext.getCurrentInstance().addMessage(null,new
		// FacesMessage("Please Enter Correct GATEPASS NO.","Please Enter Correct GATEPASS NO."));

		// }

	}

	public void saveMethod() {
		int box = 0;
		if (isValidateInput1()) {
			for (int i = 0; i < this.gatePssDisplaylist.size(); i++) {
				Stock_FL2_FL2B_activeDt dt = (Stock_FL2_FL2B_activeDt) this
						.getGatePssDisplaylist().get(i);
				box += dt.getRecivTotalBottel();
			}
			if (box > 0) {
				impl.saveMethodImpl(this);
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Receive Bottles cannot be zero!",
								"Receive Bottles cannot be zero!"));
			}
		}

	}

	public String dateListiner(ValueChangeEvent vce) {
		Date val = (Date) vce.getNewValue();
		// this.show_Data_table = impl.getDataTable(this, val);
		return "";
	}

	private boolean validateInput1;

	public boolean isValidateInput1() {
		validateInput1 = true;

		if (!(Validate.validateStrReq("gatepassNo", this.getGatePassNo())))
			validateInput1 = false;

		else if (!(Validate.validateDate("date", this.getCreatedDate())))
			validateInput1 = false;

		for (int i = 0; i < this.gatePssDisplaylist.size(); i++) {
			Stock_FL2_FL2B_activeDt dt = (Stock_FL2_FL2B_activeDt) gatePssDisplaylist
					.get(i);

			if (dt.getRecivTotalBottel() > dt.getInt_bottle_reciv()) {
				validateInput1 = false;
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										"Total Recieve Bottles Should Be Less Than Available Bottles !!! ",
										"Total Recieve Bottles Should Be Less Than Available Bottles !!!"));

				break;
			}
		}

		return validateInput1;
	}

	public void setValidateInput1(boolean validateInput1) {
		this.validateInput1 = validateInput1;
	}

	public String getLicenseing() {
		return licenseing;
	}

	public void setLicenseing(String licenseing) {
		this.licenseing = licenseing;
	}

	// ------------------------flag for rendering
	// -----------------------------------------
	private boolean uploadFlag;
	private boolean submitFlag;
	private boolean cancelFlag;
	private boolean tableFlag;
	private boolean excelFlag;
	private boolean kindlyUploadFlag;

	public void delete() {
		new Stock_FL2_FL2B_activeImpl().deleteData(this);
		this.submitFlag = false;
		this.cancelFlag = false;
		this.tableFlag = false;
		this.uploadFlag = false;
		this.kindlyUploadFlag = false;
		this.gatePassFlag = false;
		this.excelFlag = false;

	}

	// ------------------------------final submit
	// ------------------------------------------
	public void finalSubmit() {
		System.out.println("-----excel   cases------------" + excelCases);
		System.out.println("-----receive cases------------" + receiveCases);
		if (excelCases == receiveCases) {
			this.kindlyUploadFlag = false;
			this.gatePassFlag = false;
			this.uploadFlag = false;
			this.tableFlag = false;
			this.cancelFlag = false;
			this.submitFlag = false;
			this.excelFlag = false;
			this.tableFlag = false;
			this.submitFlag = false;
			this.cancelFlag = false;
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage("Record save successfully ",
							"Record save successfully "));
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage("Invalid cases is provided ",
							"Invalid cases is provided "));
		}

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

	// ------------------------sending excel data in
	// database---------------------------
	public String importExcel() {
		 
		if (gatePassNo != null && createdDate != null && licenseing != null) {
			new Stock_FL2_FL2B_activeImpl().saveExcelData(this);
		}
		// this.getImpdatalist();
		this.submitFlag = true;
		this.cancelFlag = true;
		this.tableFlag = true;
		this.kindlyUploadFlag = true;
		this.gatePassFlag = true;

		return "";
	}

	// ------------------------getting excel data from
	// database---------------------------
	ArrayList list = new ArrayList();

	public ArrayList getList() {
		this.list = impl.getExcelData(this);
		return list;
	}

	public void setList(ArrayList list) {
		this.list = list;
	}

	public boolean isUploadFlag() {
		return uploadFlag;
	}

	public void setUploadFlag(boolean uploadFlag) {
		this.uploadFlag = uploadFlag;
	}

	public boolean isSubmitFlag() {
		return submitFlag;
	}

	public void setSubmitFlag(boolean submitFlag) {
		this.submitFlag = submitFlag;
	}

	public boolean isCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public boolean isTableFlag() {
		return tableFlag;
	}

	public void setTableFlag(boolean tableFlag) {
		this.tableFlag = tableFlag;
	}

	public boolean isExcelFlag() {
		return excelFlag;
	}

	public void setExcelFlag(boolean excelFlag) {
		this.excelFlag = excelFlag;
	}

	public boolean isKindlyUploadFlag() {
		return kindlyUploadFlag;
	}

	public void setKindlyUploadFlag(boolean kindlyUploadFlag) {
		this.kindlyUploadFlag = kindlyUploadFlag;
	}

	public boolean isGatePassFlag() {
		return gatePassFlag;
	}

	public void setGatePassFlag(boolean gatePassFlag) {
		this.gatePassFlag = gatePassFlag;
	}

	public String getScanGatePassNo() {
		return scanGatePassNo;
	}

	public void setScanGatePassNo(String scanGatePassNo) {
		this.scanGatePassNo = scanGatePassNo;
	}

	private boolean gatePassFlag;
	private String scanGatePassNo;
	

}
