package com.mentor.action;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.richfaces.component.UIDataTable;

import com.mentor.datatable.RollOverStock_fl2d_NonRenewedBrand_DEODT;
import com.mentor.impl.RollOverStock_fl2d_NonRenewedBrand_DEOImpl;

public class RollOverStock_fl2d_NonRenewedBrand_DEOAction {

	

	RollOverStock_fl2d_NonRenewedBrand_DEOImpl impl = new RollOverStock_fl2d_NonRenewedBrand_DEOImpl();
	RollOverStock_fl2d_NonRenewedBrand_DEODT dt;
private String district_id;
private String district_name;
private ArrayList unitList = new ArrayList();
private ArrayList displaylist = new ArrayList();
private ArrayList display_challan_list = new ArrayList();
private String unitID;
private String hidden;
private String viewflg="F";
private double total_mrp;
private double total_duty;
private String submit_flg="F";
private String radio_type="FL2D";

RollOverStock_fl2d_NonRenewedBrand_DEODT deoDt;


public RollOverStock_fl2d_NonRenewedBrand_DEODT getDeoDt() {
	return deoDt;
}
public void setDeoDt(RollOverStock_fl2d_NonRenewedBrand_DEODT deoDt) {
	this.deoDt = deoDt;
}
public String getRadio_type() {
	return radio_type;
}
public void setRadio_type(String radio_type) {
	this.radio_type = radio_type;
}
public String getSubmit_flg() {
	return submit_flg;
}
public void setSubmit_flg(String submit_flg) {
	this.submit_flg = submit_flg;
}
public double getTotal_duty() {
	return total_duty;
}
public void setTotal_duty(double total_duty) {
	this.total_duty = total_duty;
}
public double getTotal_mrp() {
	return total_mrp;
}
public void setTotal_mrp(double total_mrp) {
	this.total_mrp = total_mrp;
}


public ArrayList getDisplaylist() {
	return displaylist;
}

public void setDisplaylist(ArrayList displaylist) {
	this.displaylist = displaylist;
}


public ArrayList getDisplay_challan_list() {
	return display_challan_list;
}
public void setDisplay_challan_list(ArrayList display_challan_list) {
	this.display_challan_list = display_challan_list;
}
public String getViewflg() {
	return viewflg;
}

public void setViewflg(String viewflg) {
	this.viewflg = viewflg;
}

public String getHidden() {
	try{
		impl.getDetails(this);
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return hidden;
}

public void setHidden(String hidden) {
	this.hidden = hidden;
}


public String getUnitID() {
	return unitID;
}

public void setUnitID(String unitID) {
	this.unitID = unitID;
}

public ArrayList getUnitList() {
	try {
	this.unitList = impl.getUnitListImpl(this);
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	return unitList;
}

public void setUnitList(ArrayList unitList) {
	this.unitList = unitList;
}

public String getDistrict_name() {
	return district_name;
}

public void setDistrict_name(String district_name) {
	this.district_name = district_name;
}

public String getDistrict_id() {
	return district_id;
}

public void setDistrict_id(String district_id) {
	this.district_id = district_id;
}
public void unitChngListener(ValueChangeEvent e) {

	try {
		
		String val = (String) e.getNewValue();
		this.setUnitID(val);
	this.displaylist=impl.displaylist(this);
	///this.challantable=impl.displaychallanlist(this);
		
		
		

	} catch (Exception e1) {
		e1.printStackTrace();
	}

}

public ArrayList challantable=new ArrayList();


public ArrayList getChallantable() {
	return challantable;
}

public void setChallantable(ArrayList challantable) {
	this.challantable = challantable;
}

private boolean validateInput;

public boolean isValidateInput() {
	
	  this.validateInput=true;
	  
			
			for (int i = 0; i < this.getChallantable().size(); i++) {
				RollOverStock_fl2d_NonRenewedBrand_DEODT dt1 = (RollOverStock_fl2d_NonRenewedBrand_DEODT) this
						.getChallantable().get(i);
				
				if (dt1.getChallan_date() == null) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("Select Challan Date ",
									"Select Challan Date"));
					validateInput = false;
				}
				
				///if(!(Validate.validateDate("Date",dt.getChallan_date())))validateInput=false;
	
	 
			}
		
	
	  
	

	
	return validateInput;
}



public void setValidateInput(boolean validateInput) {
	this.validateInput = validateInput;
}
public String finalizeData()
{
	
	
	try{
		if (isValidateInput()) {

		if(!new RollOverStock_fl2d_NonRenewedBrand_DEOImpl().validatechallan(this,dt)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Challan  Not Found ! ", "Challan  Not Found!"));	
		}else
		{
			
		boolean flag=new RollOverStock_fl2d_NonRenewedBrand_DEOImpl().updatechallandetail(this,dt);
		if(flag)
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Challan Submitted SucessFully", "Challan Submitted SucessFully"));
			this.reset();
			
		
		
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Challan Not  Submitted ", "Challan Not  Submittedy"));	
		}
		 
		}
		}
			
	}catch(Exception e)
	{
		e.getMessage();
	}
	
	return "";
}

public String dfinalize()
{
	
	
	try{
		

		impl.dfinalizedata(this);
		 
		
			
	}catch(Exception e)
	{
		e.getMessage();
	}
	
	return "";
}
public String addRowMethod() {

RollOverStock_fl2d_NonRenewedBrand_DEODT dt = new RollOverStock_fl2d_NonRenewedBrand_DEODT();
dt.setSrNo_challan(challantable.size() + 1);
System.out.println("======getSrNo======"+dt.getSrNo_challan());
challantable.add(dt);

return "";
}

public void deleteRowMethodlabel(ActionEvent e) {
UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
		.getParent();
RollOverStock_fl2d_NonRenewedBrand_DEODT dt = (RollOverStock_fl2d_NonRenewedBrand_DEODT) this.getChallantable().get(uiTable.getRowIndex());
this.challantable.remove(dt);
}

public void reset(){
	this.setUnitID(null);
	 this.displaylist.clear();
	 this.setTotal_mrp(0);
	 this.setTotal_duty(0);
	 this.display_challan_list.clear();
	 this.challantable.clear();
	 this.submit_flg="F";
	 this.setViewflg("F");

}

public void radiochange(ValueChangeEvent e) {

	try {
		
		String val = (String) e.getNewValue();
		this.setRadio_type(val);
		this.unitList = impl.getUnitListImpl(this);
		this.displaylist.clear();
		 this.setTotal_mrp(0);
		 this.setTotal_duty(0);
		 this.display_challan_list.clear();
		 this.challantable.clear();
		 this.submit_flg="F";
		 this.setViewflg("F");
		
		

	} catch (Exception e1) {
		e1.printStackTrace();
	}

}
public String calculate_fees()
{
	
	
	try{
		
		int count=0;

			 if(this.getDisplaylist().size()>0)
		        {
				for(int i=0;i< this.getDisplaylist().size(); i++){
					
						
					RollOverStock_fl2d_NonRenewedBrand_DEODT	 dt = (RollOverStock_fl2d_NonRenewedBrand_DEODT) this.getDisplaylist().get(i);
					
					
				
					if(dt.isCheckbox()){
						this.total_mrp+=dt.getMrp();;
		        		this.total_duty+=dt.getDiff_duty();
		        	
						count++;
					}}
					
				
			} 
			
			
			
			if(count==0){
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Select Atleast one row"));
			}else{
				this.setViewflg("T");
				System.out.println("---check========");
			}
	}catch(Exception e)
	{
		e.getMessage();
	}
	
	return "";
}

public void generateExcel()
{
	try{
		
		new RollOverStock_fl2d_NonRenewedBrand_DEOImpl().dataFinalize(this, deoDt);
		this.displaylist=impl.displaylist(this);
		
	}catch(Exception e)
	{
		e.printStackTrace();
	}

}




	
	
	
	
	
	
	
}
