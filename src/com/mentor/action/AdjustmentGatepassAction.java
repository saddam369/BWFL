package com.mentor.action;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.richfaces.component.UIDataTable;

import com.mentor.datatable.AdjustmentGatepassDT;
import com.mentor.datatable.GatepassToDistrictWholesale_FL2DDataTable;
import com.mentor.impl.AdjustmentGatepassImpl;
import com.mentor.impl.GatepassToDistrictWholesale_FL2DImpl;

public class AdjustmentGatepassAction {
	AdjustmentGatepassImpl impl=new AdjustmentGatepassImpl();
	
	public int dist_id;
	public String name;
	public String address = "NA";

	public String vch_gatepass_no;
	public Date dt_date = new Date();
	public String vch_from;
	public String vch_to;
	public String vch_from_lic_no = "NA";
	public String vch_to_lic_no;
	private String hidden;
	private String vch_licence_no;
	public ArrayList displaylist = new ArrayList();
	private ArrayList updatelist = new ArrayList();
	private String flag="T";
	private Date newdate=new Date();
	
	
	
	public Date getNewdate() {
		return newdate;
	}



	public void setNewdate(Date newdate) {
		this.newdate = newdate;
	}



	public String getFlag() {
		return flag;
	}



	public void setFlag(String flag) {
		this.flag = flag;
	}



	public ArrayList getUpdatelist() {
		this.updatelist = new AdjustmentGatepassImpl()
		.getupdatelist(this);
		return updatelist;
	}



	public void setUpdatelist(ArrayList updatelist) {
		this.updatelist = updatelist;
	}



	


	public ArrayList getDisplaylist() {
		return displaylist;
	}



	public void setDisplaylist(ArrayList displaylist) {
		this.displaylist = displaylist;
	}



	public String getVch_licence_no() {
		return vch_licence_no;
	}



	public void setVch_licence_no(String vch_licence_no) {
		this.vch_licence_no = vch_licence_no;
	}



	public void setHidden(String hidden) {
		this.hidden = hidden;
	}



	public AdjustmentGatepassImpl getImpl() {
		return impl;
	}



	public void setImpl(AdjustmentGatepassImpl impl) {
		this.impl = impl;
	}



	public int getDist_id() {
		return dist_id;
	}



	public void setDist_id(int dist_id) {
		this.dist_id = dist_id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getVch_gatepass_no() {
		return vch_gatepass_no;
	}



	public void setVch_gatepass_no(String vch_gatepass_no) {
		this.vch_gatepass_no = vch_gatepass_no;
	}



	public Date getDt_date() {
		return dt_date;
	}



	public void setDt_date(Date dt_date) {
		this.dt_date = dt_date;
	}



	public String getVch_from() {
		return vch_from;
	}



	public void setVch_from(String vch_from) {
		this.vch_from = vch_from;
	}



	public String getVch_to() {
		return vch_to;
	}



	public void setVch_to(String vch_to) {
		this.vch_to = vch_to;
	}



	public String getVch_from_lic_no() {
		return vch_from_lic_no;
	}



	public void setVch_from_lic_no(String vch_from_lic_no) {
		this.vch_from_lic_no = vch_from_lic_no;
	}



	public String getVch_to_lic_no() {
		return vch_to_lic_no;
	}



	public void setVch_to_lic_no(String vch_to_lic_no) {
		this.vch_to_lic_no = vch_to_lic_no;
	}



	public String getHidden() {
		try {
			impl.getDetails(this);
			if(this.flag.equalsIgnoreCase("T")){
			this.displaylist = impl.displaylistImpl(this);
			this.updatelist = impl.getupdatelist(this);
			}
		
		} catch (Exception e) {
		}
		return hidden;
	}
	
	public void update(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		AdjustmentGatepassDT dt = (AdjustmentGatepassDT) this.getDisplaylist()
				.get(uiTable.getRowIndex());

		if(dt.getBoxAvailable()>=dt.getActboxe()){
		
		impl.update(this,dt.getInt_brand_id(),dt.getInt_pckg_id(),dt.getBalance(),
				dt.getSize(),dt.getPermit(),dt.getActboxe(),dt.getBreakage(),dt.getPlan_dt(),dt.getSeq());
	
		}else{
			
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Actual Box should not be greater than Portal Stock Box !!! ",
							"Actual Box should not be greater than Portal Stock Box !!!"));
			
		}
		}

}
