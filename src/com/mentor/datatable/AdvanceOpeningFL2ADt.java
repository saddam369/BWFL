package com.mentor.datatable;

import java.util.Date;

public class AdvanceOpeningFL2ADt {
	private int sr_no;
	private String unit_typ;
	private String fee_typ;
	private Date opn_dt;
	
	private String amount;

	public int getSr_no() {
		return sr_no;
	}

	public void setSr_no(int sr_no) {
		this.sr_no = sr_no;
	}

	public String getUnit_typ() {
		return unit_typ;
	}

	public void setUnit_typ(String unit_typ) {
		this.unit_typ = unit_typ;
	}

	public String getFee_typ() {
		return fee_typ;
	}

	public void setFee_typ(String fee_typ) {
		this.fee_typ = fee_typ;
	}

	public Date getOpn_dt() {
		return opn_dt;
	}

	public void setOpn_dt(Date opn_dt) {
		this.opn_dt = opn_dt;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	private String unit_nm;

	public String getUnit_nm() {
		return unit_nm;
	}

	public void setUnit_nm(String unit_nm) {
		this.unit_nm = unit_nm;
	}
}
