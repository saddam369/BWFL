package com.mentor.datatable;

public class BWFLAdjustmentOfflinePassDT {

	private int srNo;
	private int brandId_dt;
	private int pckgId_dt;
	private int size_dt;
	private int availableBox_dt;
	private int availableBottle_dt;
	private int breakage_dt;
	private int dispatchBox_dt;
	private int dispatchBottls_dt;
	private String brandName_dt;
	private String pckgName_dt;
	private String permitNmber_dt;
	private int seq_dt;
	private String licenseNmbr_dt;
	public double duty_dt = 0;
	public double addDuty_dt = 0;

	private int show_seq;
	private String show_crtdDate;
	private String show_brandName;
	private String show_pckgName;
	private int show_dispatchBox;
	private int show_dispatchBottls;
	private String show_permitNmbr;
	private int show_brandId;
	private int show_pckgId;
	private int show_size;


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

	public int getShow_size() {
		return show_size;
	}

	public void setShow_size(int show_size) {
		this.show_size = show_size;
	}

	public int getShow_seq() {
		return show_seq;
	}

	public void setShow_seq(int show_seq) {
		this.show_seq = show_seq;
	}

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public int getSeq_dt() {
		return seq_dt;
	}

	public void setSeq_dt(int seq_dt) {
		this.seq_dt = seq_dt;
	}

	public String getLicenseNmbr_dt() {
		return licenseNmbr_dt;
	}

	public void setLicenseNmbr_dt(String licenseNmbr_dt) {
		this.licenseNmbr_dt = licenseNmbr_dt;
	}

	public double getDuty_dt() {
		return duty_dt;
	}

	public void setDuty_dt(double duty_dt) {
		this.duty_dt = duty_dt;
	}

	public double getAddDuty_dt() {
		return addDuty_dt;
	}

	public void setAddDuty_dt(double addDuty_dt) {
		this.addDuty_dt = addDuty_dt;
	}

	public String getPermitNmber_dt() {
		return permitNmber_dt;
	}

	public void setPermitNmber_dt(String permitNmber_dt) {
		this.permitNmber_dt = permitNmber_dt;
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

	public int getSize_dt() {
		return size_dt;
	}

	public void setSize_dt(int size_dt) {
		this.size_dt = size_dt;
	}

	public int getAvailableBox_dt() {
		return availableBox_dt;
	}

	public void setAvailableBox_dt(int availableBox_dt) {
		this.availableBox_dt = availableBox_dt;
	}

	public int getAvailableBottle_dt() {
		return availableBottle_dt;
	}

	public void setAvailableBottle_dt(int availableBottle_dt) {
		this.availableBottle_dt = availableBottle_dt;
	}

	public int getBreakage_dt() {
		return breakage_dt;
	}

	public void setBreakage_dt(int breakage_dt) {
		this.breakage_dt = breakage_dt;
	}

	public int getDispatchBox_dt() {
		return dispatchBox_dt;
	}

	public void setDispatchBox_dt(int dispatchBox_dt) {
		this.dispatchBox_dt = dispatchBox_dt;
	}

	public int getDispatchBottls_dt() {
		try {
			if (this.dispatchBox_dt > 0) {
				this.dispatchBottls_dt = ((this.dispatchBox_dt * this.size_dt) - this.breakage_dt);

				System.out.println("dispatchBottls_dt-------------"
						+ this.dispatchBottls_dt);
			} else {
				this.dispatchBottls_dt = 0;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dispatchBottls_dt;
	}

	public void setDispatchBottls_dt(int dispatchBottls_dt) {
		this.dispatchBottls_dt = dispatchBottls_dt;
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

	public String getShow_crtdDate() {
		return show_crtdDate;
	}

	public void setShow_crtdDate(String show_crtdDate) {
		this.show_crtdDate = show_crtdDate;
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

	public int getShow_dispatchBox() {
		return show_dispatchBox;
	}

	public void setShow_dispatchBox(int show_dispatchBox) {
		this.show_dispatchBox = show_dispatchBox;
	}

	public int getShow_dispatchBottls() {
		return show_dispatchBottls;
	}

	public void setShow_dispatchBottls(int show_dispatchBottls) {
		this.show_dispatchBottls = show_dispatchBottls;
	}

	public String getShow_permitNmbr() {
		return show_permitNmbr;
	}

	public void setShow_permitNmbr(String show_permitNmbr) {
		this.show_permitNmbr = show_permitNmbr;
	}

}
