package com.mentor.datatable;

import java.util.Date;

public class WholesaleBreakageStockDT {

	private int srNo;
	private int brandId_dt;
	private int pckgId_dt;
	private int breakage_dt;
	private int avlBottle_dt;
	private int stockBottle_dt;
	private String brandName_dt;
	private String pckgName_dt;
	private int boxSize_dt;

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public int getBrandId_dt() {
		return brandId_dt;
	}

	public void setBrandId_dt(int brandId_dt) {
		this.brandId_dt = brandId_dt;
	}

	public int getPckgId_dt() {
		return pckgId_dt;
	}

	public void setPckgId_dt(int pckgId_dt) {
		this.pckgId_dt = pckgId_dt;
	}

	public int getBreakage_dt() {
		return breakage_dt;
	}

	public void setBreakage_dt(int breakage_dt) {
		this.breakage_dt = breakage_dt;
	}

	public int getAvlBottle_dt() {
		return avlBottle_dt;
	}

	public void setAvlBottle_dt(int avlBottle_dt) {
		this.avlBottle_dt = avlBottle_dt;
	}

	public int getStockBottle_dt() {

		this.stockBottle_dt = this.avlBottle_dt - this.breakage_dt;

		// System.out.println("stock bottle=============="+this.stockBottle_dt);

		return stockBottle_dt;
	}

	public void setStockBottle_dt(int stockBottle_dt) {
		this.stockBottle_dt = stockBottle_dt;
	}

	public String getBrandName_dt() {
		return brandName_dt;
	}

	public void setBrandName_dt(String brandName_dt) {
		this.brandName_dt = brandName_dt;
	}

	public String getPckgName_dt() {
		return pckgName_dt;
	}

	public void setPckgName_dt(String pckgName_dt) {
		this.pckgName_dt = pckgName_dt;
	}

	public int getBoxSize_dt() {
		return boxSize_dt;
	}

	public void setBoxSize_dt(int boxSize_dt) {
		this.boxSize_dt = boxSize_dt;
	}

	private int show_seq;
	private int show_brandId;
	private int show_pckgId;
	private int show_userId;
	private int show_breakage;
	private int show_boxSize;
	private Date show_crDate;
	private String show_brandName;
	private String show_pckgName;

	public int getShow_seq() {
		return show_seq;
	}

	public void setShow_seq(int show_seq) {
		this.show_seq = show_seq;
	}

	public int getShow_brandId() {
		return show_brandId;
	}

	public void setShow_brandId(int show_brandId) {
		this.show_brandId = show_brandId;
	}

	public int getShow_pckgId() {
		return show_pckgId;
	}

	public void setShow_pckgId(int show_pckgId) {
		this.show_pckgId = show_pckgId;
	}

	public int getShow_userId() {
		return show_userId;
	}

	public void setShow_userId(int show_userId) {
		this.show_userId = show_userId;
	}

	public int getShow_breakage() {
		return show_breakage;
	}

	public void setShow_breakage(int show_breakage) {
		this.show_breakage = show_breakage;
	}

	public int getShow_boxSize() {
		return show_boxSize;
	}

	public void setShow_boxSize(int show_boxSize) {
		this.show_boxSize = show_boxSize;
	}

	public Date getShow_crDate() {
		return show_crDate;
	}

	public void setShow_crDate(Date show_crDate) {
		this.show_crDate = show_crDate;
	}

	public String getShow_brandName() {
		return show_brandName;
	}

	public void setShow_brandName(String show_brandName) {
		this.show_brandName = show_brandName;
	}

	public String getShow_pckgName() {
		return show_pckgName;
	}

	public void setShow_pckgName(String show_pckgName) {
		this.show_pckgName = show_pckgName;
	}

}
