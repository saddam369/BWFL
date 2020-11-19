package com.mentor.datatable;

import java.util.Date;

public class Fl2ScanningShowDataTable {
	
	private int srNo;
	
	private int plan_seq;
	private String etin;
	private int box_size;
	
	private int branid;
	private int pckid;
	private int fl2id;
	
	
	
public int getBranid() {
		return branid;
	}
	public void setBranid(int branid) {
		this.branid = branid;
	}
	public int getPckid() {
		return pckid;
	}
	public void setPckid(int pckid) {
		this.pckid = pckid;
	}
	public int getFl2id() {
		return fl2id;
	}
	public void setFl2id(int fl2id) {
		this.fl2id = fl2id;
	}
private String finalizedDateString;
	
	public String getFinalizedDateString() {
		return finalizedDateString;
	}
	public void setFinalizedDateString(String finalizedDateString) {
		this.finalizedDateString = finalizedDateString;
	}
	
	public int getBox_size() {
		return box_size;
	}
	public void setBox_size(int box_size) {
		this.box_size = box_size;
	}
	public int getPlan_seq() {
		return plan_seq;
	}
	public void setPlan_seq(int plan_seq) {
		this.plan_seq = plan_seq;
	}
	public String getEtin() {
		return etin;
	}
	public void setEtin(String etin) {
		this.etin = etin;
	}
	private String finalizedFlag;
	private String transferFlag;
	
	
	
	public String getTransferFlag() {
		return transferFlag;
	}
	public void setTransferFlag(String transferFlag) {
		this.transferFlag = transferFlag;
	}
	public String getFinalizedFlag() {
		return finalizedFlag;
	}
	public void setFinalizedFlag(String finalizedFlag) {
		this.finalizedFlag = finalizedFlag;
	}
	public int getSrNo() {
		return srNo;
	}
	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	private String brandName;
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
	private String packageName;
	private int quantity;
	private int no_of_box;
	private int no_of_planned_bottle;
	private int no_of_box_old;
	private int no_of_planned_bottle_Old;
	private Date plan_date;

	public int getNo_of_box_old() 
	{
		return no_of_box_old;
	}
	public void setNo_of_box_old(int no_of_box_old) {
		this.no_of_box_old = no_of_box_old;
	}
	public int getNo_of_planned_bottle_Old() {
		return no_of_planned_bottle_Old;
	}
	public void setNo_of_planned_bottle_Old(int no_of_planned_bottle_Old) {
		this.no_of_planned_bottle_Old = no_of_planned_bottle_Old;
	}
	
	private int branidF;
	private int pckidF;
	private int fl2idF;
	private int seqF;
	private String finalizeFlagLast;
	



	public String getFinalizeFlagLast() {
		return finalizeFlagLast;
	}
	public void setFinalizeFlagLast(String finalizeFlagLast) {
		this.finalizeFlagLast = finalizeFlagLast;
	}
	public int getSeqF() {
		return seqF;
	}
	public void setSeqF(int seqF) {
		this.seqF = seqF;
	}
	public int getBranidF() {
		return branidF;
	}
	public void setBranidF(int branidF) {
		this.branidF = branidF;
	}
	public int getPckidF() {
		return pckidF;
	}
	public void setPckidF(int pckidF) {
		this.pckidF = pckidF;
	}
	public int getFl2idF() {
		return fl2idF;
	}
	public void setFl2idF(int fl2idF) {
		this.fl2idF = fl2idF;
	}
	
	

}
