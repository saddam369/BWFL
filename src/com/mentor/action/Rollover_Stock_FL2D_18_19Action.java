package com.mentor.action;

import java.util.ArrayList;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.richfaces.component.UIDataTable;

import com.mentor.datatable.Rollover_Stock_FL2D_18_19DT;
import com.mentor.impl.Rollover_Stock_FL2D_18_19Impl;
import com.mentor.utility.Validate;

public class Rollover_Stock_FL2D_18_19Action {


	Rollover_Stock_FL2D_18_19Impl impl =new Rollover_Stock_FL2D_18_19Impl();
	Rollover_Stock_FL2D_18_19DT prt;
	
	private String hidden;
	private ArrayList displaylist=new ArrayList();
	private String unit_name;
	private int unit_id;
	private String unit_address;
	private String licence_type;
	private String licence_no;
	private int totalbox;
	private double totalmrp;
	private double totalrolloverfees;
	private double totaldiff_duty;
	private String buttn_flg;
	
	
	
	
	public Rollover_Stock_FL2D_18_19DT getPrt() {
		return prt;
	}

	public void setPrt(Rollover_Stock_FL2D_18_19DT prt) {
		this.prt = prt;
	}
	private ArrayList displaylist1=new ArrayList();





	public ArrayList getDisplaylist1() {
		try{
		
				this.displaylist1=impl.displaylistdata(this);
				
		
			
		}catch(Exception e){
			e.getMessage();
		}
		return displaylist1;
	}

	public void setDisplaylist1(ArrayList displaylist1) {
		this.displaylist1 = displaylist1;
	}
	public String getButtn_flg() {
		return buttn_flg;
	}

	public void setButtn_flg(String buttn_flg) {
		this.buttn_flg = buttn_flg;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public int getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}

	public String getUnit_address() {
		return unit_address;
	}

	public void setUnit_address(String unit_address) {
		this.unit_address = unit_address;
	}

	public String getLicence_type() {
		return licence_type;
	}

	public void setLicence_type(String licence_type) {
		this.licence_type = licence_type;
	}

	public String getLicence_no() {
		return licence_no;
	}

	public void setLicence_no(String licence_no) {
		this.licence_no = licence_no;
	}
private String list_flg="T";

	public String getList_flg() {
	return list_flg;
}

public void setList_flg(String list_flg) {
	this.list_flg = list_flg;
}

	public ArrayList getDisplaylist() {
		
		try{
			/*if(this.list_flg=="T"){
				this.displaylist=impl.displaylistdata(this);
				this.setList_flg("F");
			}*/
			
		}catch(Exception e){
			e.getMessage();
		}
		return displaylist;
	}

	public void setDisplaylist(ArrayList displaylist) {
		this.displaylist = displaylist;
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
	
	public String addRowMethod() {
	
		Rollover_Stock_FL2D_18_19DT dt = new Rollover_Stock_FL2D_18_19DT();
			dt.setSno(this.displaylist.size() + 1);
			displaylist.add(dt);
	
	
	return "";
}

public void deleteRowMethod(ActionEvent e) {
	UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
	Rollover_Stock_FL2D_18_19DT dt = (Rollover_Stock_FL2D_18_19DT) this.displaylist.get(uiTable.getRowIndex());
	this.displaylist.remove(dt);
}

public int getTotalbox() {
	try {
		totalbox = 0;
		
		
		for (int i = 0; i < this.getDisplaylist().size(); i++) {
			Rollover_Stock_FL2D_18_19DT dt = new Rollover_Stock_FL2D_18_19DT();
			dt = (Rollover_Stock_FL2D_18_19DT) this.getDisplaylist().get(i);
			
			if(dt.getBox()!=0){
			this.totalbox += dt.getBox();
			
			}else{
				
				//System.out.println("-----boxxxxouotouotouotouot=====");
			}
			}
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	return totalbox;
}

public void setTotalbox(int totalbox) {
	this.totalbox = totalbox;
}

public double getTotalmrp() {
	try {
		totalmrp = 0.0;
		
		
		for (int i = 0; i < this.getDisplaylist().size(); i++) {
			Rollover_Stock_FL2D_18_19DT dt = new Rollover_Stock_FL2D_18_19DT();
			dt = (Rollover_Stock_FL2D_18_19DT) this.getDisplaylist().get(i);
			if(dt.getMrp()!=0){
			this.totalmrp += dt.getMrp();
		
			}else{
				
			}
			}
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	return totalmrp;
}

public void setTotalmrp(double totalmrp) {
	this.totalmrp = totalmrp;
}

public double getTotalrolloverfees() {
	try {
		totalrolloverfees = 0.0;
		
		
		for (int i = 0; i < this.getDisplaylist().size(); i++) {
			Rollover_Stock_FL2D_18_19DT dt = new Rollover_Stock_FL2D_18_19DT();
			dt = (Rollover_Stock_FL2D_18_19DT) this.getDisplaylist().get(i);
			if(dt.getRollover_fee()!=0){
			this.totalrolloverfees += dt.getRollover_fee();
		
			}else{
				
			}
			}
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	return totalrolloverfees;
}

public void setTotalrolloverfees(double totalrolloverfees) {
	this.totalrolloverfees = totalrolloverfees;
}

public double getTotaldiff_duty() {
	try {
		totaldiff_duty = 0.0;
		
		
		for (int i = 0; i < this.getDisplaylist().size(); i++) {
			Rollover_Stock_FL2D_18_19DT dt = new Rollover_Stock_FL2D_18_19DT();
			dt = (Rollover_Stock_FL2D_18_19DT) this.getDisplaylist().get(i);
			if(dt.getDiff_duty()!=0){
			this.totaldiff_duty += dt.getDiff_duty();
		
			}else{
				
			}
			}
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	return totaldiff_duty;
}

public void setTotaldiff_duty(double totaldiff_duty) {
	this.totaldiff_duty = totaldiff_duty;
}

public void reset(){
	
	this.displaylist.clear();
	this.displaylist1=impl.displaylistdata(this);
	
}

public void saveMethod(){
	try{
	
		if (isValidateInput()) {

		
        impl.saveData(this);
		
		}
			
	}catch(Exception e)
	{
		e.getMessage();
	}

}
public void finaliz(){
	try{
		
		
		impl.finalizdata(this,prt);
	
			
	}catch(Exception e)
	{
		e.getMessage();
	}

}


private boolean validateInput;

public boolean isValidateInput() {
	
	  this.validateInput=true;
	  
			
		for (int i = 0; i < this.getDisplaylist().size(); i++) {
			Rollover_Stock_FL2D_18_19DT dt = new Rollover_Stock_FL2D_18_19DT();
			dt = (Rollover_Stock_FL2D_18_19DT) this.getDisplaylist().get(i);
			 //System.out.println("---validate enter in for loop  ===");
			 
			if(!(Validate.validateStrReq("brandlist",dt.getBrandPackagingData_Brand())))validateInput=false;
			else if(!(Validate.validateStrReq("packlist",dt.getBrandPackagingData_Packaging())))validateInput=false;
			else if(!(Validate.validateInteger("box",dt.getBox())))validateInput=false;
			else if(!(Validate.validateDouble("mrp",dt.getMrp())))validateInput=false;
			
			else if(!(Validate.validateDouble("roll_fees",dt.getRollover_fee())))validateInput=false;
			///else if(!(Validate.validateDouble("diff_duty",dt.getDiff_duty())))validateInput=false;
			else if(!(Validate.validateDouble("cowcess_fee",dt.getCowcess())))validateInput=false;
			//cowcess_fee
				}
					
	
	return validateInput;
}



public void setValidateInput(boolean validateInput) {
	this.validateInput = validateInput;
}

public String finalizeData()
{
	
	try{
		
			
		    new Rollover_Stock_FL2D_18_19Impl().dataFinalize(this,prt);
			
		
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return "";
}


private int etin_unit_id;





public int getEtin_unit_id() {
	return etin_unit_id;
}

public void setEtin_unit_id(int etin_unit_id) {
	this.etin_unit_id = etin_unit_id;
}

private double totalcowcess;




public double getTotalcowcess() {
	
	try {
		totalcowcess = 0.0;
		
		
		for (int i = 0; i < this.getDisplaylist().size(); i++) {
			Rollover_Stock_FL2D_18_19DT dt = new Rollover_Stock_FL2D_18_19DT();
			dt = (Rollover_Stock_FL2D_18_19DT) this.getDisplaylist().get(i);
			if(dt.getCowcess()!=0){
				
			this.totalcowcess += dt.getCowcess();
		
			}else{
				
			}
			}
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	return totalcowcess;
}

public void setTotalcowcess(double totalcowcess) {
	this.totalcowcess = totalcowcess;
}




}
