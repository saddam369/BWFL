package com.mentor.action;

import java.util.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.mentor.impl.Opening_For_Import_Duty_Impl;

public class Opening_For_Import_Duty_Action {

	Opening_For_Import_Duty_Impl impl = new Opening_For_Import_Duty_Impl();
	private double amount;
	private Date date;
	private ArrayList unit_list = new ArrayList();
	private int unit_id;
	
	public int getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}
	public ArrayList getUnit_list() {
		this.unit_list=impl.getdisUnitList();
		return unit_list;
	}
	public void setUnit_list(ArrayList unit_list) {
		this.unit_list = unit_list;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void save(){
		if(this.unit_id>0){
			if(this.amount>0.0){
				if(this.date!=null){
		            impl.saveImpl(this);
		            this.reset();
				}else{
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please select Date !!","Please select Date !! "));
				}
			}else{
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please Enter Amount !!","Please Enter Amount !!"));
			     }
		}else{
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please Select Unit !!","Please Select Unit !!"));
		     }
	}
	
	public void reset(){
		this.unit_list.clear();
		this.unit_id=0;
		this.amount=0.0;
		this.date=null;
	}
	
}
