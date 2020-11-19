package com.mentor.action;

import java.util.ArrayList;

import com.mentor.datatable.GroupingOfUnmappedDT;
import com.mentor.impl.GroupingOfUnmappedImpl;

public class GroupingOfUnmappedAction {

	GroupingOfUnmappedImpl impl = new GroupingOfUnmappedImpl();

	GroupingOfUnmappedDT bopd;
	private int distillery_id;
	private String distillery_nm;
	private String distillery_adrs;
	private String hidden;
	private String licenceType;
	private ArrayList showDataTableList = new ArrayList();
	private ArrayList savedDataTableList = new ArrayList();

	public String getLicenceType() {
		return licenceType;
	}

	public void setLicenceType(String licenceType) {
		this.licenceType = licenceType;
	}

	public GroupingOfUnmappedDT getBopd() {
		return bopd;
	}

	public void setBopd(GroupingOfUnmappedDT bopd) {
		this.bopd = bopd;
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
		try {

			impl.getId(this);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public ArrayList getShowDataTableList() {
		try {
			this.showDataTableList = impl.getData(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showDataTableList;
	}

	public void setShowDataTableList(ArrayList showDataTableList) {
		this.showDataTableList = showDataTableList;
	}

	public ArrayList getSavedDataTableList() {
		try {
			this.savedDataTableList = impl.getSavedData(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savedDataTableList;
	}

	public void setSavedDataTableList(ArrayList savedDataTableList) {
		this.savedDataTableList = savedDataTableList;
	}

	public String finalizeData() {
		try{
		
		new GroupingOfUnmappedImpl().finalizeData(bopd);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}

	public void checkList() {
		
		try {
			ArrayList list = new ArrayList();
			ArrayList list1 = new ArrayList();
			for (int i = 0; i < showDataTableList.size(); i++) {

				GroupingOfUnmappedDT dt = new GroupingOfUnmappedDT();
				dt = (GroupingOfUnmappedDT) showDataTableList.get(i);

				if (dt.isGroup()) {

					list.add(dt);
				}
			}
			if (list.size() > 0) {
				
				
				for (int j = 0; j < list.size(); j++) {
					GroupingOfUnmappedDT dt1 = (GroupingOfUnmappedDT) list.get(j);
	
					list1.add(dt1);
					
				}
				impl.save(this, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
		

	public void reset() {

		this.showDataTableList.clear();
		this.licenceType = "";

	}

}
