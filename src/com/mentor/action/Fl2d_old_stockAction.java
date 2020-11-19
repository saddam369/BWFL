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

import com.mentor.datatable.Fl2d_old_stockDT;
import com.mentor.impl.Fl2d_old_stockImpl;

import com.mentor.utility.Validate;

public class Fl2d_old_stockAction {
	

	
	Fl2d_old_stockImpl impl=new Fl2d_old_stockImpl();
	
	private int distillery_id; 
	private String distillery_nm;
	private String distillery_adrs;
	private String hidden;
	private Date dateOfBottling=new Date();
	
	private String liqureTypeId ;
	private ArrayList liqureTypeList=new ArrayList();
	private String licenceType;
	
	private ArrayList brandPackagingDataList=new ArrayList();
	
	private ArrayList showDataTableList=new ArrayList();
	
	private boolean addRowFlagPackge = true;
	
	
	private String licenceNoId;
	private ArrayList licenceNoList=new ArrayList();
	
	
	private Date cr_date;
	private String checkLicenceType;
	
	private boolean licenseNoFlag;
	
	private Fl2d_old_stockDT bopd;
	
	
	
	public Fl2d_old_stockDT getBopd() {
		return bopd;
	}


	public void setBopd(Fl2d_old_stockDT bopd) {
		this.bopd = bopd;
	}


	public boolean isLicenseNoFlag() {
		return licenseNoFlag;
	}


	public void setLicenseNoFlag(boolean licenseNoFlag) {
		this.licenseNoFlag = licenseNoFlag;
	}


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



	public String getLicenceNoId() {
		return licenceNoId;
	}



	public void setLicenceNoId(String licenceNoId) {
		this.licenceNoId = licenceNoId;
	}



	public ArrayList getLicenceNoList() {
		return licenceNoList;
	}



	public void setLicenceNoList(ArrayList licenceNoList) {
		this.licenceNoList = licenceNoList;
	}



	public String addRowMethodPackaging() {
			
			Fl2d_old_stockDT dt = new Fl2d_old_stockDT();
			dt.setSno(brandPackagingDataList.size() + 1);
			brandPackagingDataList.add(dt);
		
		return "";
	}
	
	
	
	public void deleteRowMethodPackaging(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		Fl2d_old_stockDT dt = (Fl2d_old_stockDT) this.brandPackagingDataList.get(
				uiTable.getRowIndex());
		this.brandPackagingDataList.remove(dt);
	}
	
	
	
	public boolean isAddRowFlagPackge() {
		return addRowFlagPackge;
	}
	public void setAddRowFlagPackge(boolean addRowFlagPackge) {
		this.addRowFlagPackge = addRowFlagPackge;
	}
	public Date getDateOfBottling() {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			this.dateOfBottling = sdf.parse("01-03-2018");

		} catch (Exception e) {
			e.printStackTrace();
		}
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
		
		System.out.println("license id=" +this.distillery_id);
		
		this.liqureTypeList=impl.getLiqureType();
		return liqureTypeList;
	}
	public void setLiqureTypeList(ArrayList liqureTypeList) {
		this.liqureTypeList = liqureTypeList;
	}
	public String getLicenceType() {
		return licenceType;
	}
	public void setLicenceType(String licenceType) {
		this.licenceType = licenceType;
	}
	public ArrayList getBrandPackagingDataList() {
		
	//	this.brandPackagingDataList=impl.getAddRowData(this);
		return brandPackagingDataList;
	}
	public void setBrandPackagingDataList(ArrayList brandPackagingDataList) {
		this.brandPackagingDataList = brandPackagingDataList;
	}
	public ArrayList getShowDataTableList() {
		this.showDataTableList=impl.getData(this);
		return showDataTableList;
	}
	public void setShowDataTableList(ArrayList showDataTableList) {
		this.showDataTableList = showDataTableList;
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
		try{
		impl.getSugarmill(this);
	}catch (Exception e) {
		// TODO: handle exception
	}
		
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;

}

	public void licencelistener(ValueChangeEvent event)
	{
		Object obj=event.getNewValue();
		
	//	int vat=Integer.parseInt(String.valueOf(obj));
		String id =String.valueOf(obj);
	//	this.brandPackagingDataList=impl.getAddRowData(this, id);
		this.licenceNoList=impl.getLicenseNo(this,id);
		
		
		
	}
	
	
	public void getaddrowdata(ValueChangeEvent event)
	{
		Object obj=event.getNewValue();
		String id =String.valueOf(obj);
	//	this.brandPackagingDataList=impl.getAddRowData(this, this.getLicenceType());
	//	this.licenceNoList=impl.getLicenseNo(this,id);
		
		
		
	}
	
	
	
//--------------------------------------
	
				public void save() throws ParseException
				{
				if(isValidateInput())
				{
					
					 
					
				/*	Date dt = new Date();
					Calendar c = Calendar.getInstance(); 
					c.setTime(dt); 
					c.add(Calendar.DATE, 1);
					dt = c.getTime();
				 
					DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

					 
					if(dateOfBottling.after(dt)){
						FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(" You can submit plan for today or tomorrow only ! ", "You can submit plan for today or tomorrow only !")); 
					
					}else if(formatter.parse(formatter.format(dateOfBottling)).before(formatter.parse(formatter.format(new Date())))){
						System.out.println("dateOfBottling===="+dateOfBottling);
						System.out.println("dt1===="+new Date());
						FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(" You can submit plan for today or tomorrow only !! ", "You can submit plan for today or tomorrow only !!")); 
						
					}
					
					else{*/
					
					
					impl.save(this); 
					}}
				//}
	
		public void reset()
		{
			
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				this.dateOfBottling = sdf.parse("01-03-2018");
			
			this.liqureTypeId="";
			this.liqureTypeList.clear();
			this.licenceType="";
			this.licenceNoId="";
			this.licenceNoList.clear();
			this.brandPackagingDataList.clear();
			this.licenseNoFlag=false;
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
	
		
		private boolean validateInput;

		public boolean isValidateInput() {
			
			  this.validateInput=true;
			  
			  if(!(Validate.validateStrReq("LiquorType",this.getLiqureTypeId())))validateInput=false;
				 else if(!(Validate.validateStrReq("LicenseType", this.getLicenceType())))validateInput=false;
				 
				 else if(!(Validate.validateStrReq("License", this.getLicenceNoId())))validateInput=false;
				 
			  
			  
			 if(validateInput)
			  {
				 if(this.brandPackagingDataList.size()>0) 
				 {
					 for(int i=0;i<brandPackagingDataList.size();i++)
					 {
					Fl2d_old_stockDT table=new Fl2d_old_stockDT();
			    	table=(Fl2d_old_stockDT)brandPackagingDataList.get(i); 
			    	  if(!(Validate.validateStrReqRow(i,"Brand", table.getBrandPackagingData_Brand())))validateInput=false;
			    	  else if(!(Validate.validateStrReqRow(i,"Packaging", table.getBrandPackagingData_Packaging())))validateInput=false;
			    	  else if(!(Validate.validateStrReqRow(i,"Quantity", table.getBrandPackagingData_Quantity())))validateInput=false;
			    	  else if(!(Validate.validateDouble("PlanNoOfBottling",table.getBrandPackagingData_PlanNoOfBottling())))validateInput=false;
			    	  //else if(!(Validate.validateStrReqRow(i,"",String.valueOf(table.getCapacitySprit()))))validateInput=false;
			    	// else if(!(Validate.validateDouble("installdCap",table.getCapacitySprit())))validateInput=false;
					 }
				 }
				  
			  }
			
			
			return validateInput;
		}



		public void setValidateInput(boolean validateInput) {
			this.validateInput = validateInput;
		}
		
		
		public String finalizeData()
		{
			
			try{
				new Fl2d_old_stockImpl().dataFinalize(this,bopd);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return "";
		}

}
