package com.mentor.datatable;

import java.util.Date;

public class AdvanceDutyRegisterBWFL_FL2D_DEODT {
	
	
	
	
	

	
	private int srNo;
	private Date date_Dt;
	private double produceBal;
	private double disptchBal;
	private double declarwstBal;
	private double cancelBal;
	private double balanceTot;
	private double opningBal;
	
	private String description;


	
	
	
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getOpningBal() {
		return opningBal;
	}
	public void setOpningBal(double opningBal) {
		this.opningBal = opningBal;
	}
	public int getSrNo() {
		return srNo;
	}
	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	public Date getDate_Dt() {
		return date_Dt;
	}
	public void setDate_Dt(Date date_Dt) {
		this.date_Dt = date_Dt;
	}
	public double getProduceBal() {
		return produceBal;
	}
	public void setProduceBal(double produceBal) {
		this.produceBal = produceBal;
	}
	public double getDisptchBal() {
		return disptchBal;
	}
	public void setDisptchBal(double disptchBal) {
		this.disptchBal = disptchBal;
	}
	
	
	
	
	
	public double getDeclarwstBal() {
		return declarwstBal;
	}
	public void setDeclarwstBal(double declarwstBal) {
		this.declarwstBal = declarwstBal;
	}
	public double getCancelBal() {
		return cancelBal;
	}
	public void setCancelBal(double cancelBal) {
		this.cancelBal = cancelBal;
	}
	public double getBalanceTot() {
		return balanceTot;
	}
	public void setBalanceTot(double balanceTot) {
		this.balanceTot = balanceTot;
	}

}
