package com.mentor.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;

import org.richfaces.component.UIDataTable;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;
import com.mentor.datatable.Rollover_Stock_for_fl2dDataTable;
import com.mentor.impl.Rollover_Stock_for_fl2dImpl;




public class Rollover_Stock_for_fl2dAction {
	
	

	Rollover_Stock_for_fl2dImpl impl=new Rollover_Stock_for_fl2dImpl ();
	Rollover_Stock_for_fl2dDataTable dt=new Rollover_Stock_for_fl2dDataTable();
	Rollover_Stock_for_fl2dDataTable dt1=new Rollover_Stock_for_fl2dDataTable();
	
    private ArrayList displaylist=new ArrayList();
    private int app_id;
	private String lic_type_str;
	private String lic_no;
	private String name;
	private String hidden;
	private boolean flag=true;
	private boolean saveflag=true;
	private int loginid;
	private int savelog_id;
    private String etin_unit_id;
    private String brand_name;
    private String etin;
    private boolean scanflag=true;
    private boolean backflag=false;
    private boolean tab_flag=false;
	private String package_name;
	private int pckg_id;
	private int brand_id;
	private int stock_box;
	private int rolloverbox;
private boolean com_flg;
    
    public boolean isCom_flg() {
		return com_flg;
	}
	public void setCom_flg(boolean com_flg) {
		this.com_flg = com_flg;
	}
	private boolean tableflag = false ;
	
	
	public boolean isTableflag() {
		return tableflag;
	}
	public void setTableflag(boolean tableflag) {
		this.tableflag = tableflag;
	}
	public int getRolloverbox() {
		return rolloverbox;
	}
	public void setRolloverbox(int rolloverbox) {
		this.rolloverbox = rolloverbox;
	}
	public int getStock_box() {
		return stock_box;
	}
	public void setStock_box(int stock_box) {
		this.stock_box = stock_box;
	}
	public boolean isTab_flag() {
		return tab_flag;
	}
	public void setTab_flag(boolean tab_flag) {
		this.tab_flag = tab_flag;
	}
	public int getPckg_id() {
		return pckg_id;
	}
	public void setPckg_id(int pckg_id) {
		this.pckg_id = pckg_id;
	}
	public int getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
    public boolean isScanflag() {
		return scanflag;
	}

	public void setScanflag(boolean scanflag) {
		this.scanflag = scanflag;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getEtin() {
		return etin;
	}

	public void setEtin(String etin) {
		this.etin = etin;
	}

	public String getEtin_unit_id() {
		return etin_unit_id;
	}

	public void setEtin_unit_id(String etin_unit_id) {
		this.etin_unit_id = etin_unit_id;
	}

	public ArrayList getDisplaylist() {
		
    	
    	if (this.flag==true) {
    		this.displaylist=impl.displaylistimpl(this);
    		this.flag=false;
		}
    	
	  
    	
    	return displaylist;
	}

	public void setDisplaylist(ArrayList displaylist) {
		this.displaylist = displaylist;
	}

	public int getApp_id() {
		return app_id;
	}

	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}

	public String getLic_type_str() {
		return lic_type_str;
	}

	public void setLic_type_str(String lic_type_str) {
		this.lic_type_str = lic_type_str;
	}

	public String getLic_no() {
		return lic_no;
	}

	public void setLic_no(String lic_no) {
		this.lic_no = lic_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHidden() {
		impl.getDetails(this);
 if (this.getSavelog_id()==this.getLoginid()) {
			//	System.out.println("===========================");
				this.saveflag=false;
			}else {
				this.saveflag=true;
			}
			
		//}
			
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	
	
	public void save() {
		try{
			 
			if(this.getRolloverbox()>0 && this.getRolloverbox() !=0)
			{
				if(impl.displaylist1implcount(this)==this.getRolloverbox()) {
				impl.saveData(this);
				impl.getDetails(this);
				this.displaylist=impl.displaylistimpl(this);
				}else {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Uploaded Boxes Mismatch!!! ",
							"Uploaded Boxes Mismatch!!! "));
				}
			}
			else
			{
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Please Fill  Rollover Boxes Without Breakage!!! ",
						"Please Fill  Rollover Boxes Without Breakage!!! "));	
				
			}
		}
		catch (Exception e)
		{
			
		}

			
		
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isSaveflag() {
		return saveflag;
	}

	public void setSaveflag(boolean saveflag) {
		this.saveflag = saveflag;
	}

	public int getLoginid() {
		return loginid;
	}

	public void setLoginid(int loginid) {
		this.loginid = loginid;
	}

	public int getSavelog_id() {
		return savelog_id;
	}

	public void setSavelog_id(int savelog_id) {
		this.savelog_id = savelog_id;
	}
    private String licence_no;
	
	public String getLicence_no() {
		return licence_no;
	}
	public void setLicence_no(String licence_no) {
		this.licence_no = licence_no;
	}
	public void scanAndUpload(ActionEvent e){
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		Rollover_Stock_for_fl2dAction act= new Rollover_Stock_for_fl2dAction();
		Rollover_Stock_for_fl2dDataTable dt=(Rollover_Stock_for_fl2dDataTable) this.getDisplaylist() .get(uiTable.getRowIndex());
		
		this.setGatepassForCsv(act.getEtin_unit_id());
		
		if (dt.getAvl_bottle()>=dt.getBox()) {
			impl.scan(this ,dt.getBrand_id() ,dt.getPckg_id());
			 this.scanflag=false;
			this.backflag=true;
			this.tab_flag=true;
			this.setBrand(dt.getBrand_id());
			this.setBrand_id(dt.getBrand_id());
			this.setPackage_id(dt.getPckg_id());
			this.setLic_type(dt.getLic_typ());
			this.setLicence_no(dt.getLic_no());
			this.setSize(dt.getSize());
			this.setRolloverbox(dt.getBox());
			this.setRolloverbottles(dt.getBox()*dt.getSize());
			this.setSeq(dt.getSeq());
			 

			this.gatePassFlag=true;
		}
	
		else{
			
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Actual Box should not be greater than Portal Stock Box !!! ",
							"Actual Box should not be greater than Portal Stock Box !!!"));
			
		}
		}
		
	//===========scan and upload===================//
    private boolean gatePassFlag;
    private String gatepassForCsv;
    


	public boolean isGatePassFlag() {
		return gatePassFlag;
	}

	public void setGatePassFlag(boolean gatePassFlag) {
		this.gatePassFlag = gatePassFlag;
	}

	public String getGatepassForCsv() {
		return gatepassForCsv;
	}

	public void setGatepassForCsv(String gatepassForCsv) {
		this.gatepassForCsv = gatepassForCsv;
	}	
	
    
	
	public boolean isBackflag() {
		return backflag;
	}

	public void setBackflag(boolean backflag) {
		this.backflag = backflag;
	}

	public void back(){
		this.scanflag=true;gatePassFlag=false;
		this.backflag=false;
		this.tab_flag=false;
		this.etin_unit_id="";
		
	}
		
	public void uploadCsv(UploadEvent event) {
		try {
			int size = 0;
			int counter = 0;
			UploadItem item = event.getUploadItem();
			String FullfileName = item.getFileName();
			String path = item.getFile().getPath();
			String fileName = FullfileName.substring(FullfileName.lastIndexOf("\\") + 1);
			javax.faces.context.ExternalContext con = FacesContext.getCurrentInstance().getExternalContext();
			ServletContext sCon = (ServletContext) con.getContext();
			
			size = item.getFileSize();
			this.setCsvFilename(FullfileName);
			this.setCsvFilepath(path);
		} catch (Exception ee) {
			ee.printStackTrace();
			
		} finally {
		}
	
	//==================uploader ============================================//
	
	
	}
	
	
	
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

			javax.faces.context.ExternalContext con = FacesContext.getCurrentInstance().getExternalContext();

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
		//impl.saveExcelData(this);
		return "";
	}

	public ArrayList getGetVal() {
		
		return getVal;
	}

	public void setGetVal(ArrayList getVal) {
		this.getVal = getVal;
	}
	
	
	
	public String csvSubmit() throws IOException {
		impl.saveCSV(this);
		this.gatePassFlag=true;
		
		this.tableflag=true ;
		

//		this.getVal = impl.getExcelData(this);
		return "";
	}
	
	
	/*public void finalSubmit1(){
		if(impl.updateDispatch(this)){
			this.gatePassFlag=false;
			this.reset();
			this.getReciept_list();*/
		
	
//===========================================================//
	
	//public boolean uploadflag ;
	private ArrayList displaylist1=new ArrayList();

	
	public void setDisplaylist1(ArrayList displaylist1) {
			this.displaylist1 = displaylist1;
		}
	public ArrayList getDisplaylist1() {
			
	    	
	    		this.displaylist1=impl.displaylist1impl1(this);
	    
		  
	    	
	    	return displaylist1;
		}
	
	// save method new
	
	
	// reset code new
	public void delete() {

		impl.resetcsvcasecode(this);
	}
	
	private int stockbottle;
    
    public int getStockbottle() {
		return stockbottle;
	}
	public void setStockbottle(int stockbottle) {
		this.stockbottle = stockbottle;
	}
	public int getRolloverbottles() {
		return rolloverbottles;
	}
	public void setRolloverbottles(int rolloverbottles) {
		this.rolloverbottles = rolloverbottles;
	}
	public int getPackage_id() {
		return package_id;
	}
	public void setPackage_id(int package_id) {
		this.package_id = package_id;
	}
	public int getBrand() {
		return brand;
	}
	public void setBrand(int brand) {
		this.brand = brand;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getStockbox() {
		return stockbox;
	}
	public void setStockbox(int stockbox) {
		this.stockbox = stockbox;
	}
	public String getLic_type() {
		return lic_type;
	}
	public void setLic_type(String lic_type) {
		this.lic_type = lic_type;
	}

	private int  rolloverbottles;
  
    private int package_id;
    private int brand;
    private int size;
    private int stockbox;
    private String lic_type;
	public void freez(ActionEvent e) {
		try{
			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
					.getParent();
			
			Rollover_Stock_for_fl2dDataTable dt=(Rollover_Stock_for_fl2dDataTable) this.getDisplaylist() .get(uiTable.getRowIndex());
			
			 impl.finalize(this,dt);
			 flag=true;
			 this.displaylist=impl.displaylistimpl(this);
			 
		}
		catch (Exception ee)
		{
			
		}

			
		
	}
	private int seq;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	

}
