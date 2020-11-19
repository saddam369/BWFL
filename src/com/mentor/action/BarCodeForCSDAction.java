package com.mentor.action;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.mentor.datatable.BarCodeForCSDDataTable;
import com.mentor.datatable.EnteryOfPermitDataTable;
import com.mentor.impl.BWFL_StockReceiveImpl;
import com.mentor.impl.BarCodeForCSDImpl;
import com.mentor.utility.Validate;

public class BarCodeForCSDAction {
	
	private String hidden;
	private String distillery_nm;
	private String distillery_adrs;
	private int distillery_id; 
	
	private String permit_No;
	private Date dtdate;
	private String opt_first;
	
	private String mobile_No;
	
	private String csd_Id;
	private ArrayList csdList=new ArrayList();
	
	
	private ArrayList brandPackagingDataList= new ArrayList();
	
	private boolean branddata_Flag;
	
	
	
	public boolean isBranddata_Flag() {
		return branddata_Flag;
	}
	public void setBranddata_Flag(boolean branddata_Flag) {
		this.branddata_Flag = branddata_Flag;
	}
	
	
	public String getMobile_No() {
		BarCodeForCSDImpl impl=new BarCodeForCSDImpl();
		try{if(this.csd_Id!=null && this.csd_Id.equalsIgnoreCase("0")){
		this.mobile_No=impl.getMobile_No(this, csd_Id);
		}
		}
		catch(Exception ex)
		{ex.printStackTrace();}
		return mobile_No;
	}
	public void setMobile_No(String mobile_No) {
		this.mobile_No = mobile_No;
	}
	public ArrayList getBrandPackagingDataList() {
		BarCodeForCSDImpl impl=new BarCodeForCSDImpl();
		this.brandPackagingDataList=impl.getDataBrandDetail(this);
		return brandPackagingDataList;
	}
	public void setBrandPackagingDataList(ArrayList brandPackagingDataList) {
		this.brandPackagingDataList = brandPackagingDataList;
	}
	
	
	public String getHidden() {
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
	public String getPermit_No() {
		return permit_No;
	}
	public void setPermit_No(String permit_No) {
		this.permit_No = permit_No;
	}
	public Date getDtdate() {
		return dtdate;
	}
	public void setDtdate(Date dtdate) {
		this.dtdate = dtdate;
	}
	public String getOpt_first() {
		return opt_first;
	}
	public void setOpt_first(String opt_first) {
		this.opt_first = opt_first;
	}
	public String getCsd_Id() {
		return csd_Id;
	}
	public void setCsd_Id(String csd_Id) {
		this.csd_Id = csd_Id;
	}
	public ArrayList getCsdList() {
		BarCodeForCSDImpl impl= new BarCodeForCSDImpl();
		this.csdList=impl.getcdsName();
		return csdList;
	}
	public void setCsdList(ArrayList csdList) {
		this.csdList = csdList;
	}
	
	//------------------
	
	
	public void dist_detail(ValueChangeEvent event)
	{
		BarCodeForCSDImpl impl=new BarCodeForCSDImpl();
		this.branddata_Flag=false;
		Object obj=event.getNewValue();
		String id =String.valueOf(obj);
		impl.getMobile_No(this,id);
		
	}
	
	//------------------- get OTP ------------------
	
	
	public void  getotp()
	{
		BarCodeForCSDImpl impl=new BarCodeForCSDImpl();
	
	try{
		impl.getAndSendMessage(this);
	}catch (Exception e) {
	e.printStackTrace();
	}

}
	
	//-----------------  check otp -------------
	
	public String  verify()
	{
BarCodeForCSDImpl impl=new BarCodeForCSDImpl();
	try{
			if(isValidateInput())	{
		if(impl.validateotp(opt_first,this)==true)
		{
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("OTP Verified !!!", "OTP Verified !!!"));
			this.branddata_Flag=true;
		}else{
			this.branddata_Flag=false;
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Invalid OTP !!!", "Invalid OTP !!!"));
			  
		}
				
			}
	}catch (Exception e) {
	e.printStackTrace();
	}


	  return "";
		   
	}
	
	//--------------------------------------------------------
	
	//-------------------------------- validation -----------------
	
			private boolean validateInput;

			public boolean isValidateInput() {
				
				  this.validateInput=true;
				  
				  
				  
					 if(!(Validate.validateStrReq("prmit", this.getPermit_No())))validateInput=false;
					 else if(!(Validate.validateDate("dateValue", this.getDtdate())))validateInput=false;
					
				   
				   
				//	 else  if(!(Validate.validateStrReq("licNo",this.getLicenceNo())))validateInput=false;
					 else if(!(Validate.validateStrReq("contact", this.getMobile_No())))validateInput=false;
					// else if(!(Validate.validateOnlyInt("contact", this.getMobile_No())))validateInput=false;
					 
					
					
					 else if(this.csd_Id.equalsIgnoreCase("0"))
					 {FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("CSD Name", "CSD Name"));
					  
					 validateInput=false;}
						// if(!(Validate.validateStrReq("distllryname",this.getDistilleryId())))validateInput=false; 
					 
				
				 
				
				return validateInput;
			}



			public void setValidateInput(boolean validateInput) {
				this.validateInput = validateInput;
			}
			
			
			
		//----------------------------- rset ----------
			
			
			public void reset()
			{
			
				this. permit_No="";
				this. dtdate=null;
				this. opt_first="";
				
				this. mobile_No="";
				
				this. csd_Id="";
				this. csdList.clear();
				
				
				this. brandPackagingDataList.clear();
				
				this. branddata_Flag=false;
				
				
				
			}
	
			BarCodeForCSDDataTable bopd;

			
			public BarCodeForCSDDataTable getBopd() {
				return bopd;
			}
			public void setBopd(BarCodeForCSDDataTable bopd) {
				this.bopd = bopd;
			}
			public String finalizeData()
			{
				
				try{
					new BarCodeForCSDImpl().dataFinalize(this,bopd);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
				return "";
			}
	
	
	
}
