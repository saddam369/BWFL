package com.mentor.datatable;

import java.util.ArrayList;
import java.util.Date;

import com.mentor.impl.StockAdjustmentImpl;

public class StockAdjustmentDT {
	
	StockAdjustmentImpl impl = new StockAdjustmentImpl();

	private int sno;
	private String brandPackagingData_Brand;
	private ArrayList brandPackagingData_BrandList = new ArrayList();

	private String brandPackagingData_Packaging;
	private ArrayList brandPackagingData_PackagingList = new ArrayList();

	private String brandPackagingData_Quantity;
	private ArrayList brandPackagingData_QuantityList = new ArrayList();

	private int brandPackagingData_PlanNoOfBottling;
	private int brandPackagingData_NoOfBoxes;
	private int brandPackagingData_Breakage;

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getBrandPackagingData_Brand() {
		return brandPackagingData_Brand;
	}

	public void setBrandPackagingData_Brand(String brandPackagingData_Brand) {
		this.brandPackagingData_Brand = brandPackagingData_Brand;
	}

	public ArrayList getBrandPackagingData_BrandList() {
		try{
			this.brandPackagingData_BrandList=impl.getBrandName();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return brandPackagingData_BrandList;
	}

	public void setBrandPackagingData_BrandList(ArrayList brandPackagingData_BrandList) {
		this.brandPackagingData_BrandList = brandPackagingData_BrandList;
	}

	public String getBrandPackagingData_Packaging() {
		return brandPackagingData_Packaging;
	}

	public void setBrandPackagingData_Packaging(String brandPackagingData_Packaging) {
		this.brandPackagingData_Packaging = brandPackagingData_Packaging;
	}

	public ArrayList getBrandPackagingData_PackagingList() {
		try{
			if(this.brandPackagingData_Brand !=null){
				this.brandPackagingData_PackagingList=impl.getPackagingName(brandPackagingData_Brand);
				}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return brandPackagingData_PackagingList;
	}

	public void setBrandPackagingData_PackagingList(ArrayList brandPackagingData_PackagingList) {
		this.brandPackagingData_PackagingList = brandPackagingData_PackagingList;
	}

	public String getBrandPackagingData_Quantity() {
		try{
			if(this.brandPackagingData_Packaging !=null && this.brandPackagingData_Brand !=null)
			{
				this.brandPackagingData_Quantity=impl.getqty(brandPackagingData_Brand,brandPackagingData_Packaging);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return brandPackagingData_Quantity;
	}

	public void setBrandPackagingData_Quantity(String brandPackagingData_Quantity) {
		this.brandPackagingData_Quantity = brandPackagingData_Quantity;
	}

	public ArrayList getBrandPackagingData_QuantityList() {
		try{
			if(this.brandPackagingData_Packaging !=null && this.brandPackagingData_Brand !=null)
			{
			this.brandPackagingData_QuantityList=impl.getquantity(brandPackagingData_Brand,brandPackagingData_Packaging);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return brandPackagingData_QuantityList;
	}

	public void setBrandPackagingData_QuantityList(ArrayList brandPackagingData_QuantityList) {
		this.brandPackagingData_QuantityList = brandPackagingData_QuantityList;
	}

	public int getBrandPackagingData_PlanNoOfBottling() {
		try{
			if(brandPackagingData_Quantity!=null && brandPackagingData_Quantity.length()>0 && brandPackagingData_Brand!=null && brandPackagingData_Brand.length()>0){
				if(this.brandPackagingData_NoOfBoxes>0){
				this.brandPackagingData_PlanNoOfBottling = ((this.brandPackagingData_NoOfBoxes * impl.getsize(brandPackagingData_Brand,brandPackagingData_Packaging)) - this.getBrandPackagingData_Breakage());
				}else
				{
					this.brandPackagingData_PlanNoOfBottling=0;
				}
				}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return brandPackagingData_PlanNoOfBottling;
	}

	public void setBrandPackagingData_PlanNoOfBottling(
			int brandPackagingData_PlanNoOfBottling) {
		this.brandPackagingData_PlanNoOfBottling = brandPackagingData_PlanNoOfBottling;
	}

	public int getBrandPackagingData_NoOfBoxes() {
		return brandPackagingData_NoOfBoxes;
	}

	public void setBrandPackagingData_NoOfBoxes(int brandPackagingData_NoOfBoxes) {
		this.brandPackagingData_NoOfBoxes = brandPackagingData_NoOfBoxes;
	}

	public int getBrandPackagingData_Breakage() {
		return brandPackagingData_Breakage;
	}

	public void setBrandPackagingData_Breakage(int brandPackagingData_Breakage) {
		this.brandPackagingData_Breakage = brandPackagingData_Breakage;
	}

	// ----------------------show datatable-------------------------

	private String brandName;
	private String packageName;
	private int srNo;
	private int quantity;
	private int no_of_box;
	private int no_of_planned_bottle;
	private Date plan_date;

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getNo_of_box() {
		return no_of_box;
	}

	public void setNo_of_box(int no_of_box) {
		this.no_of_box = no_of_box;
	}

	public int getNo_of_planned_bottle() {
		return no_of_planned_bottle;
	}

	public void setNo_of_planned_bottle(int no_of_planned_bottle) {
		this.no_of_planned_bottle = no_of_planned_bottle;
	}

	public Date getPlan_date() {
		return plan_date;
	}

	public void setPlan_date(Date plan_date) {
		this.plan_date = plan_date;
	}

	 
	public String current_stock;

	public String getCurrent_stock() {
		
		try{
		
		if(this.brandPackagingData_Packaging !=null && this.brandPackagingData_Brand !=null){
			current_stock = String.valueOf(impl.getCurrentStock(this)) ;
		}
		else{
			current_stock = "-";
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return current_stock;
	}

	public void setCurrent_stock(String current_stock) {
		this.current_stock = current_stock;
	}
	
	

	
}
