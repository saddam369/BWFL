package com.mentor.action;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.richfaces.component.UIDataTable;

import com.mentor.datatable.Application_Csd_dt;
import com.mentor.datatable.ShopStockVerificationDT;
import com.mentor.impl.ShopStockVerificationImpl;

public class ShopStockVerificationAction {

	ShopStockVerificationImpl impl = new ShopStockVerificationImpl();
	
	private int shop_id;
	private double rolloverFee;
	private double diffrential_duty;
	private String shop_name;
	private String licensee_name;
	private String shop_type;
	private ArrayList stockList = new ArrayList();
	private ArrayList table = new ArrayList();
	private boolean flag;
	
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public ArrayList getStockList() {
		return stockList;
	}
	public void setStockList(ArrayList stockList) {
		this.stockList = stockList;
	}
	public ArrayList getTable() {
		return table;
	}
	public void setTable(ArrayList table) {
		this.table = table;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public double getRolloverFee() {
		return rolloverFee;
	}
	public void setRolloverFee(double rolloverFee) {
		this.rolloverFee = rolloverFee;
	}
	public double getDiffrential_duty() {
		return diffrential_duty;
	}
	public void setDiffrential_duty(double diffrential_duty) {
		this.diffrential_duty = diffrential_duty;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getLicensee_name() {
		return licensee_name;
	}
	public void setLicensee_name(String licensee_name) {
		this.licensee_name = licensee_name;
	}
	public String getShop_type() {
		return shop_type;
	}
	public void setShop_type(String shop_type) {
		this.shop_type = shop_type;
	}
	
	
public String addRowMethodPackaging() {
		
		//if (table.size() < 5) {
                                                                                               
			ShopStockVerificationDT dt = new ShopStockVerificationDT();  
			if(table.size()<2){
			   dt.setSrNo1(table.size() + 1); 
			   table.add(dt); 
			}                                                 
			  
			                                                                                   
		//} else {                                                                             
		//	System.out.println("excd");                                                        
		//}                                                                                    
		return "";                                                                             
	}                                                                                          
	public void deleteRowMethodPackaging(ActionEvent e) {                                      
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()                       
				.getParent();                                                                  
		ShopStockVerificationDT dt = (ShopStockVerificationDT) this.table.get(uiTable               
				.getRowIndex());                                                               
		this.table.remove(dt);                                                           
	} 
	public void display(){
		if(this.shop_id>0){
		if(impl.getShopStatus(this)){
			this.flag=true;
			if(impl.getDetails(this)){
			this.rolloverFee = impl.getRolloverFee(this.getShop_id());
			this.diffrential_duty = impl.getDiff_fee(this.getShop_id());
			this.stockList=impl.stockDetail(this);
			}
		}else{
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Shop ID "+this.getShop_id()+" Has Been Verified", "Shop ID "+this.getShop_id()+" Has Been Verified"));
		}
		}else{
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Please Enter Shop ID !!", "Please Enter Shop ID !!"));
		}
	}
	
	public void save(){
		
		String RolloverChallan="";
		String Diff_challan="";
		double rolloverFee=0.0;
		double diff_fee=0.0; 
		
		if(this.getTable().size()>0){
			for(int i=0;i<this.getTable().size();i++){
				ShopStockVerificationDT dt = (ShopStockVerificationDT) this.getTable().get(i);
				
				if(dt.getFees_type().equalsIgnoreCase("R")){
					RolloverChallan=dt.getChallan_no();
					rolloverFee = dt.getAmount();
				}else if(dt.getFees_type().equalsIgnoreCase("D")){
					Diff_challan=dt.getChallan_no();
					diff_fee = dt.getAmount();
				}
			}
			
			if(impl.getChallanStatus(this)){
				
				if(diff_fee>=this.getDiffrential_duty() && rolloverFee>=this.getRolloverFee()){
					
				  impl.saveImpl(this,RolloverChallan,Diff_challan, this.shop_id);
				  
				  
				}else{
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Amount Should Not be Less Than Rollover Fee or Diffrential Duty", 
							"Amount Should Not be Less Than Rollover Fee or Diffrential Duty"));
				}
				}
			}
		}
		
	
	public void generate(){
		
		
	}
}
