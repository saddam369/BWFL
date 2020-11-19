package com.mentor.datatable;





import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.mentor.impl.BWFLImportImpl;
import com.mentor.impl.FL2D_Permit_validity_ExtImpl;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.ResourceUtil;

public class FL2D_Permit_validity_ExtDataTable {

	FL2D_Permit_validity_ExtImpl impl = new FL2D_Permit_validity_ExtImpl();


	// ---------------display data--------------

	private int snothrd;
	private String licenseType_dt;
	private String licenseNmbr_dt;
	private String permitNmbr_dt;
	private Date permitDt_dt;
	private Date newvalidityDate;
	private Date oldvalidityDate;
	 public Date getOldvalidityDate() {
		return oldvalidityDate;
	}

	public void setOldvalidityDate(Date oldvalidityDate) {
		this.oldvalidityDate = oldvalidityDate;
	}

	private String digital_sign_appId_dt;
	 private String digital_sign_fl2d_dt;
	 private String digital_sign_loginType_dt;
	 private String digital_sign_permitNmbr_dt;
	 private boolean digital_sign_Flag;
	
	
	public FL2D_Permit_validity_ExtImpl getImpl() {
		return impl;
	}

	public void setImpl(FL2D_Permit_validity_ExtImpl impl) {
		this.impl = impl;
	}

	public String getDigital_sign_appId_dt() {
		return digital_sign_appId_dt;
	}

	public void setDigital_sign_appId_dt(String digital_sign_appId_dt) {
		this.digital_sign_appId_dt = digital_sign_appId_dt;
	}

	public String getDigital_sign_fl2d_dt() {
		return digital_sign_fl2d_dt;
	}

	public void setDigital_sign_fl2d_dt(String digital_sign_fl2d_dt) {
		this.digital_sign_fl2d_dt = digital_sign_fl2d_dt;
	}

	public String getDigital_sign_loginType_dt() {
		return digital_sign_loginType_dt;
	}

	public void setDigital_sign_loginType_dt(String digital_sign_loginType_dt) {
		this.digital_sign_loginType_dt = digital_sign_loginType_dt;
	}

	public String getDigital_sign_permitNmbr_dt() {
		return digital_sign_permitNmbr_dt;
	}

	public void setDigital_sign_permitNmbr_dt(String digital_sign_permitNmbr_dt) {
		this.digital_sign_permitNmbr_dt = digital_sign_permitNmbr_dt;
	}

	public boolean isDigital_sign_Flag() {
		return digital_sign_Flag;
	}

	public void setDigital_sign_Flag(boolean digital_sign_Flag) {
		this.digital_sign_Flag = digital_sign_Flag;
	}

	/*dt.setSnothrd(j);
	dt.setNewvalidityDate(rs.getDate("valid_upto"));
	dt.setLicenseNmbr_dt(rs.getString("vch_licence_no"));
	dt.setLicenseType_dt(rs.getString("vch_firm_name"));
	dt.setPermitNmbr_dt(rs.getString("permit_nmbr"));
    dt.setPermitDt_dt(rs.getString("print_permit_dt"));*/
	public int getSnothrd() {
		return snothrd;
	}

	public void setSnothrd(int snothrd) {
		this.snothrd = snothrd;
	}

	public String getLicenseType_dt() {
		return licenseType_dt;
	}

	public void setLicenseType_dt(String licenseType_dt) {
		this.licenseType_dt = licenseType_dt;
	}



	public String getLicenseNmbr_dt() {
		return licenseNmbr_dt;
	}

	public void setLicenseNmbr_dt(String licenseNmbr_dt) {
		this.licenseNmbr_dt = licenseNmbr_dt;
	}

	

	public String getPermitNmbr_dt() {
		return permitNmbr_dt;
	}

	public void setPermitNmbr_dt(String permitNmbr_dt) {
		this.permitNmbr_dt = permitNmbr_dt;
	}

	public Date getPermitDt_dt() {
		return permitDt_dt;
	}

	public void setPermitDt_dt(Date permitDt_dt) {
		this.permitDt_dt = permitDt_dt;
	}

	  
	
	public Date getNewvalidityDate() {
		return newvalidityDate;
	}

	public void setNewvalidityDate(Date newvalidityDate) {
		this.newvalidityDate = newvalidityDate;
	}
	  
}