package com.mentor.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;

import org.richfaces.component.UIDataTable;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.mentor.datatable.rollover_Stock_20_21_fl2_dt;
import com.mentor.impl.RollOverOfNonRenewalBrandImpl;

public class RollOverOfNonRenewalBrandAction {
	RollOverOfNonRenewalBrandImpl impl=new RollOverOfNonRenewalBrandImpl();
	rollover_Stock_20_21_fl2_dt dt= new rollover_Stock_20_21_fl2_dt();
	
	private boolean save_disable=false;
	public boolean isSave_disable() {
		return save_disable;
	}

	public void setSave_disable(boolean save_disable) {
		this.save_disable = save_disable;
	}
	//-----------------hidden variable-------------------------------------------------------------------------------------------
	private String hidden;
    private boolean hiddenflg=true;
	public String getHidden() {
		try{
		if (hiddenflg) {
			impl.getDetails(this);
		//	impl.getDetails1(this, dt);
			this.hiddenflg=false;
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public boolean isHiddenflg() {
		return hiddenflg;
	}

	public void setHiddenflg(boolean hiddenflg) {
		this.hiddenflg = hiddenflg;
	}
	//-----------------------------------------------hidden---close----------------------------------------------------------------------------
	//---------------------------------------------login--Details------------------------------------------------------------------------------
	private String vch_licence_no;
	private int fl2_fl2bId;
	private String fl2_fl2bName;
	private String fl2_fl2bAdrs;
	private String fl2LicenseType;
	private String vch_from;
	private boolean flag;
	
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getVch_licence_no() {
		return vch_licence_no;
	}

	public void setVch_licence_no(String vch_licence_no) {
		this.vch_licence_no = vch_licence_no;
	}

	public int getFl2_fl2bId() {
		return fl2_fl2bId;
	}

	public void setFl2_fl2bId(int fl2_fl2bId) {
		this.fl2_fl2bId = fl2_fl2bId;
	}

	public String getFl2_fl2bName() {
		return fl2_fl2bName;
	}

	public void setFl2_fl2bName(String fl2_fl2bName) {
		this.fl2_fl2bName = fl2_fl2bName;
	}

	public String getFl2_fl2bAdrs() {
		return fl2_fl2bAdrs;
	}

	public void setFl2_fl2bAdrs(String fl2_fl2bAdrs) {
		this.fl2_fl2bAdrs = fl2_fl2bAdrs;
	}

	public String getFl2LicenseType() {
		return fl2LicenseType;
	}

	public void setFl2LicenseType(String fl2LicenseType) {
		this.fl2LicenseType = fl2LicenseType;
	}

	public String getVch_from() {
		return vch_from;
	}

	public void setVch_from(String vch_from) {
		this.vch_from = vch_from;
	}
	
	
	
	
	//------------------------------displaylist--------details-------by arvind ------------------------------------------------------------
	ArrayList displaylist = new ArrayList();
	private boolean listflg=true;
	
	public boolean isListflg() {
		return listflg;
	}

	public void setListflg(boolean listflg) {
		this.listflg = listflg;
	}

	public ArrayList getDisplaylist() {
		try {
			if(isListflg()){
			this.displaylist = impl.displaylistImpl2(this);
			//impl.getDetails1(this,prt);
			this.listflg=false;
			}
		} catch (Exception e) {

		}

		return displaylist;
	}

	public void setDisplaylist(ArrayList displaylist) {
		this.displaylist = displaylist;
		
	}
	rollover_Stock_20_21_fl2_dt prt;
	public rollover_Stock_20_21_fl2_dt getPrt() {
		return prt;
	}

	public void setPrt(rollover_Stock_20_21_fl2_dt prt) {
		this.prt = prt;
	}
	
	//---------------------------------close--displaylist---------------------------------------------------------------------------------------
	//---------------------------------rolloverbox and bottels  variable----------------------------------------------------------------
	private int rollerboxses;
	private int rollerbottles;
	private int size;
	
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getRollerboxses() {
		return rollerboxses;
	}

	public void setRollerboxses(int rollerboxses) {
		this.rollerboxses = rollerboxses;
	}

	public int getRollerbottles() {
		return rollerbottles;
	}

	public void setRollerbottles(int rollerbottles) {
		this.rollerbottles = rollerbottles;
	}
//----------------------------------------view flag------------------------------------------------------------------------------------
	private String viewflg="F";
	public String getViewflg() {
		return viewflg;
	}

	public void setViewflg(String viewflg) {
		this.viewflg = viewflg;
	}


	//--------open------scan ----and --upload methods -------------------------by arvind  verma-----------------------------------------
	
	

	

	public void upload(){
		System.out.println("=======upload=====");
		try {
			
			if(impl.getNew_Brand_Name(prt.getEtin_new())!=null)
			{
			System.out.println("=======getRolloverbox===getStockbottle=="+prt.getRolloverbox()+"===and----"+prt.getStockbottle());
			///if (dt.getRolloverbox()<=dt.getStockbottle()) {
			if (prt.getRolloverbox()<=prt.getStockbottle()) {
			this.setFlag(false);
		
			
			try {this.setRollerboxses(prt.getRolloverbox());
			 this.setSize(prt.getSize());
			this.setRollerbottles(prt.getRolloverbox()*prt.getSize());
			System.out.println("=======setRollerbottles====="+this.getRollerbottles());
			impl.viewdetail1(this,prt);
			 this.csvdetail=impl.showcsvDetail(this);
			
			} catch (Exception ex) {
				 ex.printStackTrace();
			}
			//System.out.println("=========view flag"+this.viewflg);
			this.setViewflg("T");
			}else{
				
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Actual Box should not be greater than Portal Stock Box !!! ",
								"Actual Box should not be greater than Portal Stock Box !!!"));
					
			}
		}
		
		else{
			
			
			
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Entered New Etin Brand Is Not Availble In 20-21 !!! ",
							"Entered New Etin Brand Is Not Availble In 20-21 !!!"));
			
			
		}
			} catch (Exception ex) {
		 ex.printStackTrace();
	}
	}
	
      //-----------------------------------pannel2 details----------------------------------------------------by arvind------------------

	private String brand_nm;
	private String etn;
	private String brand_id;
	private String new_etin;
	private String new_brand_name;
	public String getNew_brand_name() {
		return new_brand_name;
	}

	public void setNew_brand_name(String new_brand_name) {
		this.new_brand_name = new_brand_name;
	}

	public String getNew_etin() {
		return new_etin;
	}

	public void setNew_etin(String new_etin) {
		this.new_etin = new_etin;
	}

	public String getBrand_nm() {
		return brand_nm;
	}

	public void setBrand_nm(String brand_nm) {
		this.brand_nm = brand_nm;
	}

	public String getEtn() {
		return etn;
	}

	public void setEtn(String etn) {
		this.etn = etn;
	}

	public String getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}
	
	//------------------------------------------------------pannel 2 close--------------------------------by arvind---------------------
	
	private boolean btflag;
	public boolean isBtflag() {
		return btflag;
	}
	public void setBtflag(boolean btflag) {
		this.btflag = btflag;
	}
	//------------------------------------------------------------------------------------------------------------------------------------
	
	public String csvSubmit() throws IOException {
		try {
		 impl.saveCSV(this);
		 this.setFlag(true);
		 }catch (Exception e) {
		e.getMessage();
		}
		return "";
	}

	//-------------------------------------------------
	private String csvFilepath;
	public String getCsvFilepath() {
		return csvFilepath;
	}

	public void setCsvFilepath(String csvFilepath) {
		this.csvFilepath = csvFilepath;
	}
	
	//-----------------------------rollover csv details---------------------------------------------------
	ArrayList csvdetail = new ArrayList();

	public ArrayList getCsvdetail() {
		
         //this.csvdetail=impl.showcsvDetail(this);
		return csvdetail;
	}

	public void setCsvdetail(ArrayList csvdetail) {
		this.csvdetail = csvdetail;
	}

	
	private String csvFilename;
	
	public String getCsvFilename() {
		return csvFilename;
	}

	public void setCsvFilename(String csvFilename) {
		this.csvFilename = csvFilename;
	}

	
	//--------------------------------upload csv------------------------------------------------
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
		//	System.out.println("====================path==="+path);
			this.setCsvFilepath(path);
		} catch (Exception ee) {
		//	ee.printStackTrace();
		//	
		} finally {
		}
		
		
	}
	
	//-------------------------------save methods created by arvind------------------------------------------
	
	 public void save(){
	    	try{
	    		if(this.getRollerboxses()>0 && this.getRollerboxses() !=0)
				{
					if(impl.displaylist1implcount(this)==this.getRollerboxses()) {
	    		impl.save(this,prt);
				//impl.getDetails1(this, dt);
	    		this.csvdetail=impl.showcsvDetail(this);
				this.displaylist = impl.displaylistImpl2(this);
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
					
	    	
	    }catch(Exception e){
	    //	e.printStackTrace();
	    }
	    }

//-----------------------------reset -----methods ---create by Arvind-Verma-----------------------------------------------------------	
		  public void reset(){
	    	 this.setViewflg("F");
	    	 this.setFlag(false);
	 
	     }
//-----------------------------Delete methods --------Created by Arvind-Verma----------------------------------------------------------		  
	     public void delete(){
	    	 
	    	 impl.resetcsvcasecode(this);
	     }
	
	//------------------------------------pack_id variable-----------------arvind verma------------------------
	private int pack_id;
	public int getPack_id() {
		return pack_id;
	}

	public void setPack_id(int pack_id) {
		this.pack_id = pack_id;
	}
	
	
	
	
	
	public void payment(){
		try {
			this.setViewflg("P");
			impl.viewdetail1(this,prt);

			impl.viewdetail11(this,prt);
			impl.viewdetail12(this,prt);
			} catch (Exception ex) {
		//ex.printStackTrace();
	}
	}
	public String finalizeData()
	{
		
		
		try{
			

			if(!new RollOverOfNonRenewalBrandImpl().validatechallan(this,prt)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Challan  Not Found ! ", "Challan  Not Found!"));	
			}else
			{
			boolean flag=new RollOverOfNonRenewalBrandImpl().updatechallandetail(this,dt);
			if(flag)
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Challan Submitted SucessFully", "Challan Submitted SucessFully"));
				this.challantable.clear();
				this.setViewflg("F");
				this.displaylist = impl.displaylistImpl2(this);
			
			
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Challan Not  Submitted ", "Challan Not  Submittedy"));	
			}
			 
			}
				
		}catch(Exception e)
		{
			e.getMessage();
		}
		
		return "";
	}

public String addRowMethod() {
	
	rollover_Stock_20_21_fl2_dt dt = new rollover_Stock_20_21_fl2_dt();
	dt.setSrNo(challantable.size() + 1);
	System.out.println("======getSrNo======"+dt.getSrNo());
	challantable.add(dt);

return "";
}

public void deleteRowMethodlabel(ActionEvent e) {
	UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
			.getParent();
	rollover_Stock_20_21_fl2_dt dt = (rollover_Stock_20_21_fl2_dt) this.getChallantable().get(uiTable.getRowIndex());
	this.challantable.remove(dt);
}
	
	private Double mrp;
	private Double duty;
	public ArrayList challantable=new ArrayList();


	public ArrayList getChallantable() {
		return challantable;
	}

	public void setChallantable(ArrayList challantable) {
		this.challantable = challantable;
	}

	public Double getMrp() {
		return mrp;
	}

	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}

	public Double getDuty() {
		return duty;
	}

	public void setDuty(Double duty) {
		this.duty = duty;
	}
	private String save_flg="T";
	public String getSave_flg() {
		return save_flg;
	}

	public void setSave_flg(String save_flg) {
		this.save_flg = save_flg;
	}
	
private boolean com_flg;
    
    public boolean isCom_flg() {
		return com_flg;
	}
	public void setCom_flg(boolean com_flg) {
		this.com_flg = com_flg;
	}
	public void freez() {
		try{
			/// impl.finalize(this,prt);
			impl.finalize(this,prt);
			 listflg=true;
				impl.displaylistImpl2(this); 
			 
		}
		catch (Exception e)
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
