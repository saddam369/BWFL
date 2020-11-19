package com.mentor.action;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.event.ValueChangeEvent;

import com.mentor.impl.LicenceNameImpl;

public class LicenceNameAction {

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


	private String hidden;
	private int distillery_id;
	private String name;
	private String loginType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getHidden() {
		try{
			new LicenceNameImpl().getDetails(this);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return hidden;
	}
	
	private ArrayList brandPackagingShowDataList=new ArrayList();
    public ArrayList getBrandPackagingShowDataList() {
    	
    	if(this.listFlag == false)
    	{
    		this.brandPackagingShowDataList=new LicenceNameImpl().getShowDataTable(this);
    		this.listFlag = false;
    	}
    	
		return brandPackagingShowDataList;
	}

	public void setBrandPackagingShowDataList(ArrayList brandPackagingShowDataList) {
		this.brandPackagingShowDataList = brandPackagingShowDataList;
	}
	
	
	private Date dtDate = new Date();
	private boolean listFlag;
	
	
	public boolean isListFlag() {
		return listFlag;
	}

	public void setListFlag(boolean listFlag) {
		this.listFlag = listFlag;
	}

	public Date getDtDate() {
		return dtDate;
	}

	public void setDtDate(Date dtDate) {
		this.dtDate = dtDate;
	}
	
	public void  getData(ValueChangeEvent ve)
	{
		Date val = (Date) ve.getNewValue();
		this.brandPackagingShowDataList=new LicenceNameImpl().getShowDataTable(this);
		
	}
}
