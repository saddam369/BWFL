package com.mentor.datatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.mentor.impl.BWFLImportImpl;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.ResourceUtil;

public class BWFLImportDataTable {

	BWFLImportImpl impl = new BWFLImportImpl();

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
	private boolean updateflg;
	private String permitno;
	 
		private String cancel_reason;
		private String cancel_flg;
		private Date finalizeDate;
		private boolean cancel_order_flg;
		private String etin;
		 private String groupId;
		 private String digital_sign_appId_dt;
		 private String digital_sign_bwflID_dt;
		 private String digital_sign_loginType_dt;
		 private String digital_sign_permitNmbr_dt;
		 private boolean digital_sign_Flag;
		 
	public BWFLImportImpl getImpl() {
			return impl;
		}
		public void setImpl(BWFLImportImpl impl) {
			this.impl = impl;
		}
		public String getDigital_sign_appId_dt() {
			return digital_sign_appId_dt;
		}
		public void setDigital_sign_appId_dt(String digital_sign_appId_dt) {
			this.digital_sign_appId_dt = digital_sign_appId_dt;
		}
		public String getDigital_sign_bwflID_dt() {
			return digital_sign_bwflID_dt;
		}
		public void setDigital_sign_bwflID_dt(String digital_sign_bwflID_dt) {
			this.digital_sign_bwflID_dt = digital_sign_bwflID_dt;
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
	public String getGroupId() {
			return groupId;
		}
		public void setGroupId(String groupId) {
			this.groupId = groupId;
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

	private Date permitdt;
	private Date cr_date;

	//@rvind
	private Date plan_dt;
	
	public Date getPlan_dt() {
		return plan_dt;
	}

	public void setPlan_dt(Date plan_dt) {
		this.plan_dt = plan_dt;
	}

	private int seq;
	private int newml;
	private int newsize;
	private int groupseq;
	private String distillery_id;
	
	private boolean updateDisabled=false;
	
	private long caseseq;
	private long seqfrm;
	private long no_of_bottle_per_case;
	private long entry_no_of_bottle_per_case;
	private boolean entry_no_of_bottle_per_case_flag;
	
	public boolean isEntry_no_of_bottle_per_case_flag() {
		return entry_no_of_bottle_per_case_flag;
	}
	public void setEntry_no_of_bottle_per_case_flag(
			boolean entry_no_of_bottle_per_case_flag) {
		this.entry_no_of_bottle_per_case_flag = entry_no_of_bottle_per_case_flag;
	}
	
	public long getEntry_no_of_bottle_per_case() {
		
		if(this.entry_no_of_bottle_per_case==0)
		{
			this.entry_no_of_bottle_per_case=this.no_of_bottle_per_case;
		}
		return entry_no_of_bottle_per_case;
	}
	public void setEntry_no_of_bottle_per_case(long entry_no_of_bottle_per_case) {
		this.entry_no_of_bottle_per_case = entry_no_of_bottle_per_case;
	}
	public long getNo_of_bottle_per_case() {
		try{ 
		this.no_of_bottle_per_case=new Double(this.getboxNo()).intValue();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return no_of_bottle_per_case;
	}
	public void setNo_of_bottle_per_case(long no_of_bottle_per_case) {
		this.no_of_bottle_per_case = no_of_bottle_per_case;
	}
	public long getSeqfrm() {
		return seqfrm;
	}
	public void setSeqfrm(long seqfrm) {
		this.seqfrm = seqfrm;
	}
	public long getCaseseq() {
		return caseseq;
	}
	public void setCaseseq(long caseseq) {
		this.caseseq = caseseq;
	}
	
	
	
	
	
	
	
	public boolean isUpdateDisabled() {
		return updateDisabled;
	}

	public void setUpdateDisabled(boolean updateDisabled) {
		this.updateDisabled = updateDisabled;
	}
	
	
	

	public String getDistillery_id() {
		return distillery_id;
	}

	public void setDistillery_id(String distillery_id) {
		this.distillery_id = distillery_id;
	}

	public int getGroupseq() {
		return groupseq;
	}

	public void setGroupseq(int groupseq) {
		this.groupseq = groupseq;
	}

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

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public Date getPermitdt() {
		return permitdt;
	}

	public void setPermitdt(Date permitdt) {
		this.permitdt = permitdt;
	}

	public String getPermitno() {
		return permitno;
	}

	public void setPermitno(String permitno) {
		this.permitno = permitno;
	}

	private String permitnoD;

	public String getPermitnoD() {
		return permitnoD;
	}

	public void setPermitnoD(String permitnoD) {
		this.permitnoD = permitnoD;
	}

	private String gtinno;

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
		//this.brandPackagingData_BrandList = impl.getBrandName();
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

			// System.out.println("--------  qty ----------"+this.brandPackagingData_Quantity);
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
				// this.brandPackagingData_NoOfBoxes=Math.ceil(this.getboxNo()/this.brandPackagingData_PlanNoOfBottling);

			 
					this.brandPackagingData_PlanNoOfBottling=(this.brandPackagingData_NoOfBoxes*new Double(this.entry_no_of_bottle_per_case).intValue());
				 
			
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

	public int getboxNo() {
		int box = 0;

		// System.out.println("------------   11111111111   ------");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList =

			" SELECT box_id, box_size FROM distillery.box_size_details where box_id=? ";

			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, this.getboxId());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				box = rs.getInt("box_size");

				// System.out.println("  ---------   2222222222  --------  ");

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception se) {
			se.printStackTrace();
		}finally {
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

	// /------------------------------- box id get from paking
	// -------------------

	public int getboxId() {
		int box = 0;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			// System.out.println("----------- 33333333333   ---------");

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
			if(this.brandPackagingData_Brand==null || this.brandPackagingData_Packaging==null){
				pstmt.setInt(1,0);
				pstmt.setInt(2,0);
			}else{
				pstmt.setInt(1, Integer.parseInt(this.brandPackagingData_Brand));
				pstmt.setInt(2, Integer.parseInt(this.brandPackagingData_Packaging));
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				box = rs.getInt("box_id");

				// System.out.println("------------  444444444444  ------"+box);

			}

		} catch (SQLException se) {
			se.printStackTrace();
		}  catch (Exception se) {
			se.printStackTrace();
		}finally {
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

			// System.out.println("----------  55555555555555  ---------");

			String queryList = "SELECT int_app_id_f,vch_undertaking_name,vch_wrk_add  from  public.dis_mst_pd1_pd2_lic where vch_wrk_phon="
					+ ResourceUtil.getUserNameAllReq().trim();
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();
			 System.out.println("--------------  666666666   ----" + queryList);
			while (rs.next()) {

				id = rs.getInt("int_app_id_f");

				// System.out.println("--------------  666666666   ----" +id );

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

	private String brandPackagingData_etinNmbr;

	public String getBrandPackagingData_etinNmbr() {
		if (this.brandPackagingData_Packaging != null && this.brandPackagingData_Brand != null) {

			this.brandPackagingData_etinNmbr = impl.getEtinNmbr(brandPackagingData_Brand, brandPackagingData_Packaging);

			System.out.println("--------  etin ----------"+ this.brandPackagingData_Quantity);
		}
		return brandPackagingData_etinNmbr;
	}

	public void setBrandPackagingData_etinNmbr(
			String brandPackagingData_etinNmbr) {
		this.brandPackagingData_etinNmbr = brandPackagingData_etinNmbr;
	}

	// ---------------display data--------------

	private int snothrd;
	private String licenseType_dt;
	private String unitName_dt;
	private String licenseNmbr_dt;
	private String mappedType_dt;
	private String permitNmbr_dt;
	private Date permitDt_dt;
	private int dispbox_dt;
	private int dispbot_dt;

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

	public String getUnitName_dt() {
		return unitName_dt;
	}

	public void setUnitName_dt(String unitName_dt) {
		this.unitName_dt = unitName_dt;
	}

	public String getLicenseNmbr_dt() {
		return licenseNmbr_dt;
	}

	public void setLicenseNmbr_dt(String licenseNmbr_dt) {
		this.licenseNmbr_dt = licenseNmbr_dt;
	}

	public String getMappedType_dt() {
		return mappedType_dt;
	}

	public void setMappedType_dt(String mappedType_dt) {
		this.mappedType_dt = mappedType_dt;
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

	public int getDispbox_dt() {
		return dispbox_dt;
	}

	public void setDispbox_dt(int dispbox_dt) {
		this.dispbox_dt = dispbox_dt;
	}

	public int getDispbot_dt() {
		return dispbot_dt;
	}

	public void setDispbot_dt(int dispbot_dt) {
		this.dispbot_dt = dispbot_dt;
	}

	//-------------
	  private String permitno_enteredUpdate;
	  private String int_brand_idUpdate;
	    private String int_pack_idUpdate; 
	    private int seqUpdate; 
	    private String gatepass_noUpdate;
	    private String etinUpdate;
	  
	  
	  
	  
	public String getEtinUpdate() {
			return etinUpdate;
		}

		public void setEtinUpdate(String etinUpdate) {
			this.etinUpdate = etinUpdate;
		}

	public String getGatepass_noUpdate() {
			return gatepass_noUpdate;
		}

		public void setGatepass_noUpdate(String gatepass_noUpdate) {
			this.gatepass_noUpdate = gatepass_noUpdate;
		}

	public int getSeqUpdate() {
			return seqUpdate;
		}

		public void setSeqUpdate(int seqUpdate) {
			this.seqUpdate = seqUpdate;
		}

	public String getInt_brand_idUpdate() {
			return int_brand_idUpdate;
		}

		public void setInt_brand_idUpdate(String int_brand_idUpdate) {
			this.int_brand_idUpdate = int_brand_idUpdate;
		}

		public String getInt_pack_idUpdate() {
			return int_pack_idUpdate;
		}

		public void setInt_pack_idUpdate(String int_pack_idUpdate) {
			this.int_pack_idUpdate = int_pack_idUpdate;
		}

	public String getPermitno_enteredUpdate() {
		return permitno_enteredUpdate;
	}

	public void setPermitno_enteredUpdate(String permitno_enteredUpdate) {
		this.permitno_enteredUpdate = permitno_enteredUpdate;
	}
	  
	//--------------------------------validity ext---------------------------------------------------------  
	  
	private Date newvalidityDate;
	public Date getNewvalidityDate() {
		return newvalidityDate;
	}

	public void setNewvalidityDate(Date newvalidityDate) {
		this.newvalidityDate = newvalidityDate;
	}
	  
	private Date oldvalidityDate;

	public Date getOldvalidityDate() {
		return oldvalidityDate;
	}

	public void setOldvalidityDate(Date oldvalidityDate) {
		this.oldvalidityDate = oldvalidityDate;
	}
	
	
	public double permitFee_dt = 0;
	public double calPermitFee_dt = 0;

	public double getPermitFee_dt() {
		if (this.brandPackagingData_Packaging != null && this.brandPackagingData_Brand != null) {
			//this.permitFee_dt = impl.getPermitFee(brandPackagingData_Brand, brandPackagingData_Packaging);
			//this.permitFee_dt = impl.getPermitFee(brandPackagingData_Brand, brandPackagingData_Packaging);
			this.permitFee_dt=impl.getBondPermitFee();
			System.out.println("--------  permit fee ----------"+ this.permitFee_dt);
		}
		return permitFee_dt;
	}

	public void setPermitFee_dt(double permitFee_dt) {
		this.permitFee_dt = permitFee_dt;
	}

	public double getCalPermitFee_dt() {
		
		try {
			this.calPermitFee_dt=(this.brandPackagingData_PlanNoOfBottling*Integer.parseInt(this.brandPackagingData_Quantity)*this.permitFee_dt)/1000;	
		 } catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return calPermitFee_dt;
	}

	public void setCalPermitFee_dt(double calPermitFee_dt) {
		this.calPermitFee_dt = calPermitFee_dt;
	}
	
	
	
	  
	
private String cancel_req_dt_time;
public String getCancel_req_dt_time() {
	return cancel_req_dt_time;
}
public void setCancel_req_dt_time(String cancel_req_dt_time) {
	this.cancel_req_dt_time = cancel_req_dt_time;
}


}
