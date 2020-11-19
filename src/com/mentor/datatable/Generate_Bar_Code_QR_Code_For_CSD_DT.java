package com.mentor.datatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.mentor.impl.Generate_BarCode_QRCode_For_CSD_Impl;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.ResourceUtil;

public class Generate_Bar_Code_QR_Code_For_CSD_DT {

	Generate_BarCode_QRCode_For_CSD_Impl impl = new Generate_BarCode_QRCode_For_CSD_Impl();
	private int unit_id;
	private String unit_name;
	private long seqfrm;
	private Date cr_date;
	private boolean flag;
	private boolean pdf_flag;
	
	
	public boolean isPdf_flag() {
		return pdf_flag;
	}
	public void setPdf_flag(boolean pdf_flag) {
		this.pdf_flag = pdf_flag;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	private String cancel_req_dt_time;
	
	public String getCancel_req_dt_time() {
		return cancel_req_dt_time;
	}
	public void setCancel_req_dt_time(String cancel_req_dt_time) {
		this.cancel_req_dt_time = cancel_req_dt_time;
	}

	private boolean noOfBottleFlg;
	public boolean isNoOfBottleFlg() {
		return noOfBottleFlg;
	}
	public void setNoOfBottleFlg(boolean noOfBottleFlg) {
		this.noOfBottleFlg = noOfBottleFlg;
	}
	public Date getCr_date() {
		return cr_date;
	}

	public void setCr_date(Date cr_date) {
		this.cr_date = cr_date;
	}

	public long getSeqfrm() {
		return seqfrm;
	}

	public void setSeqfrm(long seqfrm) {
		this.seqfrm = seqfrm;
	}

	private long caseseq;

	public long getCaseseq() {
		return caseseq;
	}

	public void setCaseseq(long caseseq) {
		this.caseseq = caseseq;
	}

	public int getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	private int sno;

	private String brandPackagingData_Brand;
	private ArrayList brandPackagingData_BrandList = new ArrayList();

	private String brandPackagingData_Packaging;
	private ArrayList brandPackagingData_PackagingList = new ArrayList();

	private String brandPackagingData_Quantity;
	private ArrayList brandPackagingData_QuantityList = new ArrayList();

	private int brandPackagingData_PlanNoOfBottling = 0;
	private int brandPackagingData_NoOfBoxes = 0;

	private int brandId;
	private int packagingId;
	private String licenceNo;
	private String licencenoo;

	private String gtinno;
	private int request_id;

	public int getRequest_id() {
		return request_id;
	}

	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}

	private int newml;
	private int newsize;

	public int getNewml() {
		return newml;
	}

	public void setNewml(int newml) {
		this.newml = newml;
	}

	public int getNewsize() {
		return newsize;
	}

	public void setNewsize(int newsize) {
		this.newsize = newsize;
	}

	public int getboxNo() {
		int box = 0;

		// ////System.out.println("------------   11111111111   ------");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList =

			"SELECT a.brand_id, b.package_id ,export_box_size "
					+ "	from distillery.brand_registration_19_20 a , "
					+ "	distillery.packaging_details_19_20 b "
					+ "	where a.brand_id=b.brand_id_fk  " +

					"	and brand_id =?  and b.package_id=?";

			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, Integer.parseInt(this.brandPackagingData_Brand));
			pstmt.setInt(2, Integer.parseInt(this.brandPackagingData_Packaging));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				box = rs.getInt("export_box_size");

				// ////System.out.println("  ---------   2222222222  --------  ");

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		noOfBottlesPerCase = box;
		return box;

	}

	private boolean updateflg;

	public boolean isUpdateflg() {
		return updateflg;
	}

	public void setUpdateflg(boolean updateflg) {
		this.updateflg = updateflg;
	}

	public String getGtinno() {
		return gtinno;
	}

	public void setGtinno(String gtinno) {
		this.gtinno = gtinno;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public int getPackagingId() {
		return packagingId;
	}

	public void setPackagingId(int packagingId) {
		this.packagingId = packagingId;
	}

	public String getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public String getLicencenoo() {
		return licencenoo;
	}

	public void setLicencenoo(String licencenoo) {
		this.licencenoo = licencenoo;
	}

	public ArrayList getBrandPackagingData_BrandList() {
		this.brandPackagingData_BrandList = impl.getBrandName();

		return brandPackagingData_BrandList;
	}

	public void setBrandPackagingData_BrandList(
			ArrayList brandPackagingData_BrandList) {
		this.brandPackagingData_BrandList = brandPackagingData_BrandList;
	}

	public ArrayList getBrandPackagingData_PackagingList() {
		if (this.brandPackagingData_Brand != null) {
			this.brandPackagingData_PackagingList = impl
					.getPackagingName(brandPackagingData_Brand);
			// this.getboxNo();
		}
		return brandPackagingData_PackagingList;
	}

	public void setBrandPackagingData_PackagingList(
			ArrayList brandPackagingData_PackagingList) {
		this.brandPackagingData_PackagingList = brandPackagingData_PackagingList;
	}

	public ArrayList getBrandPackagingData_QuantityList() {
		if (this.brandPackagingData_Packaging != null
				&& this.brandPackagingData_Brand != null) {
			this.brandPackagingData_QuantityList = impl.getquantity(
					brandPackagingData_Brand, brandPackagingData_Packaging);
		}

		return brandPackagingData_QuantityList;
	}

	public void setBrandPackagingData_QuantityList(
			ArrayList brandPackagingData_QuantityList) {
		this.brandPackagingData_QuantityList = brandPackagingData_QuantityList;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getBrandPackagingData_Brand() {
		return brandPackagingData_Brand;
	}

	public void setBrandPackagingData_Brand(String brandPackagingData_Brand) {
		this.brandPackagingData_Brand = brandPackagingData_Brand;
	}

	public String getBrandPackagingData_Packaging() {
		return brandPackagingData_Packaging;
	}

	public void setBrandPackagingData_Packaging(
			String brandPackagingData_Packaging) {
		this.brandPackagingData_Packaging = brandPackagingData_Packaging;
	}

	public String getBrandPackagingData_Quantity() {

		if (this.brandPackagingData_Packaging != null
				&& this.brandPackagingData_Brand != null) {
			// this.brandPackagingData_QuantityList=impl.getquantity(brandPackagingData_Brand,brandPackagingData_Packaging);

			this.brandPackagingData_Quantity = impl.getqty(
					brandPackagingData_Brand, brandPackagingData_Packaging,this);

			// ////System.out.println("--------  qty ----------"+this.brandPackagingData_Quantity);
		}

		return brandPackagingData_Quantity;
	}

	public void setBrandPackagingData_Quantity(
			String brandPackagingData_Quantity) {
		this.brandPackagingData_Quantity = brandPackagingData_Quantity;
	}

	public int getBrandPackagingData_PlanNoOfBottling() {

		if (brandPackagingData_Quantity != null
				&& brandPackagingData_Quantity.length() > 0) {
			if (this.brandPackagingData_NoOfBoxes > 0) {

				/*
				 * if(noOfBottlesPerCase!=0){
				 * this.brandPackagingData_NoOfBoxes=this.noOfBottlesPerCase; }
				 */

				// this.brandPackagingData_NoOfBoxes=Math.ceil(this.getboxNo()/this.brandPackagingData_PlanNoOfBottling);
				// ////System.out.println("this.getboxNo()="+this.getboxNo());
				if (noOfBottlesPerCase == 0) {
					this.noOfBottlesPerCase = this.getboxNo();
					// this.brandPackagingData_PlanNoOfBottling=(this.brandPackagingData_NoOfBoxes*this.getboxNo());
				}
				// else{
				this.brandPackagingData_PlanNoOfBottling = (this.brandPackagingData_NoOfBoxes * this.noOfBottlesPerCase);
				// }
			} else {
				this.brandPackagingData_PlanNoOfBottling = 0;
			}

		}
		return brandPackagingData_PlanNoOfBottling;
	}

	public void setBrandPackagingData_PlanNoOfBottling(
			int brandPackagingData_PlanNoOfBottling) {
		this.brandPackagingData_PlanNoOfBottling = brandPackagingData_PlanNoOfBottling;
	}

	public int getBrandPackagingData_NoOfBoxes() {
		return brandPackagingData_NoOfBoxes;
	}

	public void setBrandPackagingData_NoOfBoxes(int brandPackagingData_NoOfBoxes) {
		this.brandPackagingData_NoOfBoxes = brandPackagingData_NoOfBoxes;
	}

	/*
	 * public int getboxNo() { int box=0;
	 * 
	 * 
	 * // ////System.out.println("------------   11111111111   ------");
	 * 
	 * Connection con=null; PreparedStatement pstmt=null; ResultSet rs =null;
	 * try{
	 * 
	 * String queryList=
	 * 
	 * " SELECT box_id, box_size "+
	 * "	FROM distillery.box_size_details where box_id=? ";
	 * 
	 * con=ConnectionToDataBase.getConnection() ;
	 * 
	 * 
	 * pstmt=con.prepareStatement(queryList);
	 * 
	 * pstmt.setInt(1, this.getboxId());
	 * 
	 * rs= pstmt.executeQuery();
	 * 
	 * while(rs.next()) { box=rs.getInt("box_size");
	 * 
	 * 
	 * // ////System.out.println("  ---------   2222222222  --------  ");
	 * 
	 * }
	 * 
	 * 
	 * }catch(SQLException se){ se.printStackTrace(); } finally{try{
	 * if(pstmt!=null)pstmt.close(); if(con!=null) con.close();
	 * 
	 * }catch(SQLException se){ se.printStackTrace(); } }
	 * 
	 * return box;
	 * 
	 * }
	 */
	// /------------------------------- box id get from paking
	// -------------------

	public int getboxId() {
		int box = 0;
		;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			// ////System.out.println("----------- 33333333333   ---------");

			String queryList =

			"SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id ,b.box_id "
					+ "	from distillery.brand_registration_19_20 a , "
					+ "	distillery.packaging_details_19_20 b "
					+ "	where a.brand_id=b.brand_id_fk  " +
					// "	and a.distillery_id=?  "+
					"	and brand_id =?  and b.package_id=?";

			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			// pstmt.setInt(1, this.getdistid());
			pstmt.setInt(1, Integer.parseInt(this.brandPackagingData_Brand));
			pstmt.setInt(2, Integer.parseInt(this.brandPackagingData_Packaging));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				box = rs.getInt("box_id");

				// ////System.out.println("------------  444444444444  ------"+box);

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return box;

	}

	// ////////////---------------------------m didt id ----------------------

	public int getdistid() {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			// ////System.out.println("----------  55555555555555  ---------");

			// String
			// queryList="SELECT int_app_id_f,vch_undertaking_name,vch_wrk_add  from  dis_mst_pd1_pd2_lic where vch_wrk_phon="+ResourceUtil.getUserNameAllReq().trim();

			String queryList = " int_app_id, vch_firm_name  from licence.fl2_2b_2d_19_20 where vch_mobile_no = '"
					+ ResourceUtil.getUserNameAllReq().trim() + "' ";

			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				id = rs.getInt("int_app_id");

				// ////System.out.println("--------------  666666666   ----" +id );

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return id;

	}

	// -------------------------------- Show Data Table
	// ------------------------------------------

	private Date showDataTable_Date;
	private String showDataTable_LiqureType;
	private String showDataTable_LicenceType;
	private String showDataTable_Brand;
	private String showDataTable_Packging;
	private String showDataTable_Quntity;
	private String showDataTable_PlanNoOfBottling;
	private String showDataTable_NoOfBoxes;
	private String showDataTable_PermitNo;

	public String getShowDataTable_PermitNo() {
		return showDataTable_PermitNo;
	}

	public void setShowDataTable_PermitNo(String showDataTable_PermitNo) {
		this.showDataTable_PermitNo = showDataTable_PermitNo;
	}

	public Date getShowDataTable_Date() {
		return showDataTable_Date;
	}

	public void setShowDataTable_Date(Date showDataTable_Date) {
		this.showDataTable_Date = showDataTable_Date;
	}

	public String getShowDataTable_LiqureType() {
		return showDataTable_LiqureType;
	}

	public void setShowDataTable_LiqureType(String showDataTable_LiqureType) {
		this.showDataTable_LiqureType = showDataTable_LiqureType;
	}

	public String getShowDataTable_LicenceType() {
		return showDataTable_LicenceType;
	}

	public void setShowDataTable_LicenceType(String showDataTable_LicenceType) {
		this.showDataTable_LicenceType = showDataTable_LicenceType;
	}

	public String getShowDataTable_Brand() {
		return showDataTable_Brand;
	}

	public void setShowDataTable_Brand(String showDataTable_Brand) {
		this.showDataTable_Brand = showDataTable_Brand;
	}

	public String getShowDataTable_Packging() {
		return showDataTable_Packging;
	}

	public void setShowDataTable_Packging(String showDataTable_Packging) {
		this.showDataTable_Packging = showDataTable_Packging;
	}

	public String getShowDataTable_Quntity() {
		return showDataTable_Quntity;
	}

	public void setShowDataTable_Quntity(String showDataTable_Quntity) {
		this.showDataTable_Quntity = showDataTable_Quntity;
	}

	public String getShowDataTable_PlanNoOfBottling() {
		return showDataTable_PlanNoOfBottling;
	}

	public void setShowDataTable_PlanNoOfBottling(
			String showDataTable_PlanNoOfBottling) {
		this.showDataTable_PlanNoOfBottling = showDataTable_PlanNoOfBottling;
	}

	public String getShowDataTable_NoOfBoxes() {
		return showDataTable_NoOfBoxes;
	}

	public void setShowDataTable_NoOfBoxes(String showDataTable_NoOfBoxes) {
		this.showDataTable_NoOfBoxes = showDataTable_NoOfBoxes;
	}

	private String finalizedFlag;

	public String getFinalizedFlag() {
		return finalizedFlag;
	}

	public void setFinalizedFlag(String finalizedFlag) {
		this.finalizedFlag = finalizedFlag;
	}

	private Date finalize_Date;
	private String finalizedDateString;

	public String getFinalizedDateString() {
		return finalizedDateString;
	}

	public void setFinalizedDateString(String finalizedDateString) {
		this.finalizedDateString = finalizedDateString;
	}

	public Date getFinalize_Date() {
		return finalize_Date;
	}

	public void setFinalize_Date(Date finalize_Date) {
		this.finalize_Date = finalize_Date;
	}

	// @rvind

	private String liqureTypeId;
	private int noOfBottlesPerCase;

	public String getLiqureTypeId() {
		return liqureTypeId;
	}

	public void setLiqureTypeId(String liqureTypeId) {
		this.liqureTypeId = liqureTypeId;
	}

	private boolean flg;

	public boolean isFlg() {
		return flg;
	}

	public void setFlg(boolean flg) {
		this.flg = flg;
	}

	public int getNoOfBottlesPerCase() {

		if (brandPackagingData_Quantity != null
				&& brandPackagingData_Quantity.length() > 0) {

			if (noOfBottlesPerCase == 0) {
				this.noOfBottlesPerCase = this.getboxNo();

			}
		}

		return noOfBottlesPerCase;
	}

	public void setNoOfBottlesPerCase(int noOfBottlesPerCase) {
		this.noOfBottlesPerCase = noOfBottlesPerCase;
	}
	

	public double permitFee_dt = 0;
	public double calPermitFee_dt = 0;

	public double getPermitFee_dt() {
		if (this.brandPackagingData_Packaging != null && this.brandPackagingData_Brand != null) {

			this.permitFee_dt = impl.getPermitFee(brandPackagingData_Brand, brandPackagingData_Packaging);

			////System.out.println("--------  permit fee ----------"+ this.permitFee_dt);
		}
		return permitFee_dt;
	}

	public void setPermitFee_dt(double permitFee_dt) {
		this.permitFee_dt = permitFee_dt;
	}

	public double getCalPermitFee_dt() {
		
		try {
			this.calPermitFee_dt = this.brandPackagingData_PlanNoOfBottling*this.permitFee_dt;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return calPermitFee_dt;
	}

	public void setCalPermitFee_dt(double calPermitFee_dt) {
		this.calPermitFee_dt = calPermitFee_dt;
	}
private int dutyId;
public int getDutyId() {
	return dutyId;
}

public void setDutyId(int dutyId) {
	this.dutyId = dutyId;
}
private int int_distillery_id;
public int getInt_distillery_id() {
	return int_distillery_id;
}

public void setInt_distillery_id(int int_distillery_id) {
	this.int_distillery_id = int_distillery_id;
}
//////////////////////////////////////////////////////////////////////////////////////////////////


    private String permitno;
	public String getPermitno() {
		return permitno;
	}
	public void setPermitno(String permitno) {
		this.permitno = permitno;
	}
	public String getCancel_reason() {
		return cancel_reason;
	}
	public void setCancel_reason(String cancel_reason) {
		this.cancel_reason = cancel_reason;
	}
	public String getCancel_flg() {
		return cancel_flg;
	}
	public void setCancel_flg(String cancel_flg) {
		this.cancel_flg = cancel_flg;
	}
	public Date getFinalizeDate() {
		return finalizeDate;
	}
	public void setFinalizeDate(Date finalizeDate) {
		this.finalizeDate = finalizeDate;
	}
	public boolean isCancel_order_flg() {
		return cancel_order_flg;
	}
	public void setCancel_order_flg(boolean cancel_order_flg) {
		this.cancel_order_flg = cancel_order_flg;
	}
	public String getEtin() {
		return etin;
	}
	public void setEtin(String etin) {
		this.etin = etin;
	}

	private String cancel_reason;
	private String cancel_flg;
	private Date finalizeDate;
	private boolean cancel_order_flg;
	private String etin;



}
