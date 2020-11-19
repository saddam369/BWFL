package com.mentor.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletContext;

import org.richfaces.component.UIDataTable;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.mentor.datatable.GatepassToDistrictBWFLDT;
import com.mentor.datatable.PermitWiseStockLeftBWFLDataTable;
import com.mentor.impl.PermitWiseStockLeftBWFLImpl;

public class PermitWiseStockLeftBWFLAction {
	
	PermitWiseStockLeftBWFLImpl impl=new PermitWiseStockLeftBWFLImpl();
	
	private int distillery_id;
	private String distillery_nm;
	private String distillery_adrs;
	private String hidden;
	private String permit_Id;
	private ArrayList permit_List=new ArrayList();
	private ArrayList permitWiseBrandPackShow=new ArrayList();
	private ArrayList showData_PermitWiseSave=new ArrayList();
	private String licenceType;
	PermitWiseStockLeftBWFLDataTable bopd;
	private boolean permitWiseBrandPackShow_Flag=true;
	private String scan_PermitNo;
	private boolean gatePassFlag;
	ArrayList getVal = new ArrayList();
	
	
	
	
	
	public boolean isGatePassFlag() {
		return gatePassFlag;
	}

	public void setGatePassFlag(boolean gatePassFlag) {
		this.gatePassFlag = gatePassFlag;
	}

	public String getScan_PermitNo() {
		return scan_PermitNo;
	}

	public void setScan_PermitNo(String scan_PermitNo) {
		this.scan_PermitNo = scan_PermitNo;
	}

	public boolean isPermitWiseBrandPackShow_Flag() {
		return permitWiseBrandPackShow_Flag;
	}

	public void setPermitWiseBrandPackShow_Flag(boolean permitWiseBrandPackShow_Flag) {
		this.permitWiseBrandPackShow_Flag = permitWiseBrandPackShow_Flag;
	}

	public String getLicenceType() {
		return licenceType;
	}

	public void setLicenceType(String licenceType) {
		this.licenceType = licenceType;
	}

	public PermitWiseStockLeftBWFLDataTable getBopd() {
		return bopd;
	}
	
	public void setBopd(PermitWiseStockLeftBWFLDataTable bopd) {
		this.bopd = bopd;
	}
	public int getDistillery_id() {
		return distillery_id;
	}
	public void setDistillery_id(int distillery_id) {
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
	public String getHidden() {
		impl.getId(this);
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	public String getPermit_Id() {
		return permit_Id;
	}
	public void setPermit_Id(String permit_Id) {
		this.permit_Id = permit_Id;
	}
	public ArrayList getPermit_List() {
		this.permit_List=impl.getpermitList(this);
		return permit_List;
	}
	public void setPermit_List(ArrayList permit_List) {
		this.permit_List = permit_List;
	}
	public ArrayList getPermitWiseBrandPackShow() {
		
		if(this.permitWiseBrandPackShow_Flag==true)
		{
		if(this.permit_Id!=null)
		{
			this.permitWiseBrandPackShow=impl.getData(this);
			this.permitWiseBrandPackShow_Flag=false;
		}else{
			this.permitWiseBrandPackShow.clear();
			this.permitWiseBrandPackShow_Flag=true;
		}
		}
		
		return permitWiseBrandPackShow;
	}
	public void setPermitWiseBrandPackShow(ArrayList permitWiseBrandPackShow) {
		this.permitWiseBrandPackShow = permitWiseBrandPackShow;
	}
	public ArrayList getShowData_PermitWiseSave() {
		
	
			this.showData_PermitWiseSave=impl.get_After_saveData_Show(this);
		
		
		return showData_PermitWiseSave;
	}
	public void setShowData_PermitWiseSave(ArrayList showData_PermitWiseSave) {
		this.showData_PermitWiseSave = showData_PermitWiseSave;
	}
	
	
	
	
	
	
	
	
	
//	================================================ ====
	
	
	public void permitListnr(ValueChangeEvent e) {

		try {
			
			String id = (String) e.getNewValue();
			this.setPermit_Id(id);
			this.permitWiseBrandPackShow_Flag=true;
			
		} catch (Exception e1) {

			e1.printStackTrace();
		}
	}
	

	
	
	public void savePermitData()
	{
		impl.saveData_Permit(this);
	}
	
	
	
	
	public void reset()
	{
		this.permitWiseBrandPackShow_Flag=true;
		this.scan_PermitNo=null;
		this.gatePassFlag=false;
	}
	
	
	
	
	///=============================== for csv ============================
	
	private String csvFilename;
	private String csvFilepath;
	
	

	
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

	public ArrayList getGetVal() {
		this.getVal = impl.getExcelData(this);
		return getVal;
	}

	public void setGetVal(ArrayList getVal) {
		this.getVal = getVal;
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

			System.out.println("filename" + FullfileName+ "---------------filename" + fileName);
					
			size = item.getFileSize();
			this.setCsvFilename(FullfileName);
			this.setCsvFilepath(path);

		} catch (Exception ee) {
			ee.printStackTrace();
			System.out.println("exception in upload@");
		} finally {

		}

	}

	
	
	
	
	
	
	public void scanAndUpload(ActionEvent ae) {
		UIDataTable uiTable = (UIDataTable) ae.getComponent().getParent()
				.getParent();
		PermitWiseStockLeftBWFLDataTable dt = (PermitWiseStockLeftBWFLDataTable) this.showData_PermitWiseSave
				.get(uiTable.getRowIndex());

		this.setScan_PermitNo(dt.getSav_PermitNo());
		this.gatePassFlag = true;
		
	}
	
	
	
	
	public String csvSubmit() throws IOException {
		
		impl.deleteData_upload_CSv(this);
		
		impl.saveCSV(this);
		this.gatePassFlag = true;

		return "";
	}
	
	
	
	
	
	
	public void delete (){
		impl.deleteData(this);
	}
	
	
	
	
	
	
	public void finalSubmit() {
		
		
		if (impl.count_ETIN_No(this) == impl.temp_Count_ETIN(this)) {
		
		if (impl.excelCases(this) == impl.recieveCases(this)) {

			if (impl.updateDispatch(this) == true) {
				
				this.reset();
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Data Save Successfully","Data Save Successfully"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error Occured while saving !!! ",
								"Error Occured while saving !!!"));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(
				"No.of cases in Permit and in Uploaded Excel does not match! ","No.of cases in Permit and in Uploaded Excel does not match!"));
		}
		
		
		
	}else{
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(
				"Number of ETIN and Uploaded Excel number of ETIN does not match! ","Number of ETIN and Uploaded Excel number of ETIN does not match!"));
	
	}

	}
	
	
	
	
	

}
