package com.mentor.action;

import java.util.ArrayList;

import javax.faces.event.ValueChangeEvent;

import com.mentor.impl.AdminImpl;

public class AdminAction {

	public int getDistillery_id() {
		return distillery_id;
	}

	public void setDistillery_id(int distillery_id) {
		this.distillery_id = distillery_id;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	AdminImpl impl = new AdminImpl();
	private String hidden;
	private int distillery_id;
	private int brand_id;
	private String name;
	private String loginType;
	private String brand_name;
	private int brandid;
	private boolean brandflag;
	private boolean tableflag;
	private boolean tableflag1;
	private String brand_whole;
	private String wholeSalename;
	private int applicant;
	private ArrayList brand_list = new ArrayList();
	private ArrayList whole_list = new ArrayList();
	
	
	
	

	public int getApplicant() {
		return applicant;
	}

	public void setApplicant(int applicant) {
		this.applicant = applicant;
	}

	public String getWholeSalename() {
		return wholeSalename;
	}

	public void setWholeSalename(String wholeSalename) {
		this.wholeSalename = wholeSalename;
	}

	public boolean isTableflag1() {
		return tableflag1;
	}

	public void setTableflag1(boolean tableflag1) {
		this.tableflag1 = tableflag1;
	}

	public String getBrand_whole() {
		return brand_whole;
	}

	public void setBrand_whole(String brand_whole) {
		this.brand_whole = brand_whole;
	}

	public boolean isBrandflag() {
		return brandflag;
	}

	public void setBrandflag(boolean brandflag) {
		this.brandflag = brandflag;
	}

	public boolean isTableflag() {
		return tableflag;
	}

	public void setTableflag(boolean tableflag) {
		this.tableflag = tableflag;
	}

	public int getBrandid() {
		return brandid;
	}

	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}

	public int getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public ArrayList getBrand_list() {
		try {
			this.brand_list = impl.brand_listImpl(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return brand_list;
	}

	public void setYearList(ArrayList yearList) {
		this.brand_list = brand_list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private ArrayList brandPackagingShowDataList = new ArrayList();

	public ArrayList getBrandPackagingShowDataList() {

		/*
		 * this.brandPackagingShowDataList = new AdminImpl()
		 * .getShowDataTable(this);
		 */
		return brandPackagingShowDataList;
	}

	public void setBrandPackagingShowDataList(
			ArrayList brandPackagingShowDataList) {
		this.brandPackagingShowDataList = brandPackagingShowDataList;
	}

	public void changeMethod(ValueChangeEvent e) {
		this.brandflag = true;
		this.tableflag = true;
		this.tableflag1= false;
		this.wholeSalename=null;
		this.brandPackagingShowDataList1.clear();
		String i = (String) e.getNewValue();
		
		this.brandid = Integer.parseInt(i);

		if (i.equalsIgnoreCase("0")) {

			this.brandPackagingShowDataList.clear();
		} else {

			this.brandPackagingShowDataList = new AdminImpl().getShowDataTable(
					this, this.brandid);
		}

	}
	
	private ArrayList brandPackagingShowDataList1 = new ArrayList();

	public ArrayList getBrandPackagingShowDataList1() {

		/*
		 * this.brandPackagingShowDataList = new AdminImpl()
		 * .getShowDataTable(this);
		 */
		return brandPackagingShowDataList1;
	}

	public void setBrandPackagingShowDataList1(
			ArrayList brandPackagingShowDataList1) {
		this.brandPackagingShowDataList1 = brandPackagingShowDataList1;
	}
	
	public void changeMethod1(ValueChangeEvent e) {
		this.brandflag = true;
		this.tableflag= false;
		this.tableflag1 = true;
		this.brandPackagingShowDataList.clear();
		this.brand_name=null;
		String i = (String) e.getNewValue();

		this.applicant = Integer.parseInt(i);

		if (i.equalsIgnoreCase("0")) {

			this.brandPackagingShowDataList1.clear();
		} else {

			this.brandPackagingShowDataList1 = new AdminImpl().getShowDataTable1(
					this, this.applicant);
		}

	}
	
	public ArrayList getWhole_list() {
		try {
			this.whole_list = impl.whole_listImpl(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return whole_list;
	}
//-----------------------------
	private int open;
	private int reci;
	private int disp;
	private int bale;
	private int open1;
	private int reci1;
	private int disp1;
	private int bale1;
	
	





	public int getOpen1() {
		return open1;
	}

	public void setOpen1(int open1) {
		this.open1 = open1;
	}

	public int getReci1() {
		return reci1;
	}

	public void setReci1(int reci1) {
		this.reci1 = reci1;
	}

	public int getDisp1() {
		return disp1;
	}

	public void setDisp1(int disp1) {
		this.disp1 = disp1;
	}

	public int getBale1() {
		return bale1;
	}

	public void setBale1(int bale1) {
		this.bale1 = bale1;
	}

	public int getReci() {
		return reci;
	}

	public void setReci(int reci) {
		this.reci = reci;
	}

	public int getDisp() {
		return disp;
	}

	public void setDisp(int disp) {
		this.disp = disp;
	}

	public int getBale() {
		return bale;
	}

	public void setBale(int bale) {
		this.bale = bale;
	}

	public int getOpen() {
		return open;
	}

	public void setOpen(int open) {
		this.open = open;
	}
	
	

}
