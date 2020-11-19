package com.mentor.datatable;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.mentor.connectiondb.ConnectionToDataBase;
import com.mentor.impl.Fl2d_old_stockImpl;
import com.mentor.utility.ResourceUtil;

public class Fl2d_old_stockDT {
	
	Fl2d_old_stockImpl impl=new Fl2d_old_stockImpl();
	
	private int sno;
	private int pack_size;
	
	public int getPack_size() {
		return pack_size;
	}
	public void setPack_size(int pack_size) {
		this.pack_size = pack_size;
	}

	private String brandPackagingData_Brand;
	private ArrayList brandPackagingData_BrandList=new ArrayList();
	
	
	private String brandPackagingData_Packaging;
	private ArrayList brandPackagingData_PackagingList=new ArrayList();
	
	
	private String brandPackagingData_Quantity;
	private ArrayList brandPackagingData_QuantityList=new ArrayList();
	
	
	private double brandPackagingData_PlanNoOfBottling;
	private double brandPackagingData_NoOfBoxes;
	
	
	private int brandId;
	private int packagingId;
	private String licenceNo;
	private String licencenoo;
	private String finalizedFlag;
	private Date finalize_Date;
	private String finalizedDateString;
	private String gtinno;
	private int planid;
	private int seq;
	
	
	
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getPlanid() {
		return planid;
	}
	public void setPlanid(int planid) {
		this.planid = planid;
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
	public String getFinalizedFlag() {
		return finalizedFlag;
	}
	public void setFinalizedFlag(String finalizedFlag) {
		this.finalizedFlag = finalizedFlag;
	}
	public Date getFinalize_Date() {
		return finalize_Date;
	}
	public void setFinalize_Date(Date finalize_Date) {
		this.finalize_Date = finalize_Date;
	}
	public String getFinalizedDateString() {
		return finalizedDateString;
	}
	public void setFinalizedDateString(String finalizedDateString) {
		this.finalizedDateString = finalizedDateString;
	}
	public ArrayList getBrandPackagingData_BrandList() {
		this.brandPackagingData_BrandList=impl.getBrandName();
		return brandPackagingData_BrandList;
	}
	public void setBrandPackagingData_BrandList(
			ArrayList brandPackagingData_BrandList) {
		this.brandPackagingData_BrandList = brandPackagingData_BrandList;
	}
	
	
	public ArrayList getBrandPackagingData_PackagingList() {
		if(this.brandPackagingData_Brand !=null){
		this.brandPackagingData_PackagingList=impl.getPackagingName(brandPackagingData_Brand);
		}
		return brandPackagingData_PackagingList;
	}
	public void setBrandPackagingData_PackagingList(
			ArrayList brandPackagingData_PackagingList) {
		this.brandPackagingData_PackagingList = brandPackagingData_PackagingList;
	}
	
	
	public ArrayList getBrandPackagingData_QuantityList() {
		if(this.brandPackagingData_Packaging !=null && this.brandPackagingData_Brand !=null)
		{
		this.brandPackagingData_QuantityList=impl.getquantity(brandPackagingData_Brand,brandPackagingData_Packaging);
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
	public void setBrandPackagingData_Packaging(String brandPackagingData_Packaging) {
		this.brandPackagingData_Packaging = brandPackagingData_Packaging;
	}
	public String getBrandPackagingData_Quantity() {
		
		if(this.brandPackagingData_Packaging !=null && this.brandPackagingData_Brand !=null)
		{
	//	this.brandPackagingData_QuantityList=impl.getquantity(brandPackagingData_Brand,brandPackagingData_Packaging);
		
			this.brandPackagingData_Quantity=impl.getqty(brandPackagingData_Brand,brandPackagingData_Packaging);
			
		//	System.out.println("--------  qty ----------"+this.brandPackagingData_Quantity);
		}
		
		return brandPackagingData_Quantity;
	}
	public void setBrandPackagingData_Quantity(String brandPackagingData_Quantity) {
		this.brandPackagingData_Quantity = brandPackagingData_Quantity;
	}
	public double getBrandPackagingData_PlanNoOfBottling() {

		 if(brandPackagingData_Quantity!=null && brandPackagingData_Quantity.length()>0 && brandPackagingData_Brand!=null && brandPackagingData_Brand.length()>0){
			if(this.brandPackagingData_NoOfBoxes>0){
		//	this.brandPackagingData_NoOfBoxes=Math.ceil(this.getboxNo()/this.brandPackagingData_PlanNoOfBottling);
			
			this.brandPackagingData_PlanNoOfBottling=(this.brandPackagingData_NoOfBoxes*this.getboxNo());
			
			}else
			{
				this.brandPackagingData_PlanNoOfBottling=0;
			}
			
		 }
		return brandPackagingData_PlanNoOfBottling;
	}
	public void setBrandPackagingData_PlanNoOfBottling(
			double brandPackagingData_PlanNoOfBottling) {
		this.brandPackagingData_PlanNoOfBottling = brandPackagingData_PlanNoOfBottling;
	}
	public double getBrandPackagingData_NoOfBoxes() { 
		return brandPackagingData_NoOfBoxes;
	}
	public void setBrandPackagingData_NoOfBoxes(double brandPackagingData_NoOfBoxes) {
		this.brandPackagingData_NoOfBoxes = brandPackagingData_NoOfBoxes;
	}
	
	
	
	public double getboxNo()
	{
		double box=0.0;
		
		
	//	System.out.println("------------   11111111111   ------");
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		try{

			String queryList=
					 

					"SELECT a.brand_id, b.package_id ,export_box_size "+
							"	from distillery.brand_registration a , "+ 
							"	distillery.packaging_details b "+
							"	where a.brand_id=b.brand_id_fk  "+
					
							"	and brand_id =?  and b.package_id=?";
			
			con=ConnectionToDataBase.getConnection() ;
			
			
			pstmt=con.prepareStatement(queryList);
			
			pstmt.setInt(1, Integer.parseInt(this.brandPackagingData_Brand));
			pstmt.setInt(2, Integer.parseInt(this.brandPackagingData_Packaging));

			rs= pstmt.executeQuery();

			while(rs.next())
			{
				box=rs.getInt("export_box_size");
				
				
				//System.out.println("  ---------   2222222222  --------  ");

			}

			
		}catch(SQLException se){
			se.printStackTrace();
		}
		finally{try{
			if(pstmt!=null)pstmt.close();
			if(con!=null) con.close();	

		}catch(SQLException se){
			se.printStackTrace();
		}
		}
		
		return box;
		
	}
	
	///-------------------------------  box id get from paking -------------------
	
	
	
	public int getboxId()
	{
		int box=0;;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		try{
			
			
		//	System.out.println("----------- 33333333333   ---------");

			String queryList=
					
					"SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id ,b.box_id "+
							"	from distillery.brand_registration a , "+ 
							"	distillery.packaging_details b "+
							"	where a.brand_id=b.brand_id_fk  "+
					//		"	and a.distillery_id=?  "+
							"	and brand_id =?  and b.package_id=?";
			
			con=ConnectionToDataBase.getConnection() ;
			
			
			pstmt=con.prepareStatement(queryList);

		//	pstmt.setInt(1, this.getdistid());
			pstmt.setInt(1, Integer.parseInt(this.brandPackagingData_Brand));
			pstmt.setInt(2, Integer.parseInt(this.brandPackagingData_Packaging));
			
			
			rs= pstmt.executeQuery();

			while(rs.next())
			{
				box=rs.getInt("box_id");
				
		//		System.out.println("------------  444444444444  ------"+box);
				

			}

			
		}catch(SQLException se){
			se.printStackTrace();
		}
		finally{try{
			if(pstmt!=null)pstmt.close();
			if(con!=null) con.close();	

		}catch(SQLException se){
			se.printStackTrace();
		}
		}
		
		return box;
		
	}
	
	
//////////////---------------------------m didt id ----------------------
	
	
public int getdistid( ) {

int id=0;
Connection con=null;
PreparedStatement pstmt=null;
ResultSet rs =null;
try{

//	System.out.println("----------  55555555555555  ---------");
	
	
	String queryList="SELECT int_app_id_f,vch_undertaking_name,vch_wrk_add  from  dis_mst_pd1_pd2_lic where vch_wrk_phon="+ResourceUtil.getUserNameAllReq().trim();
	con=ConnectionToDataBase.getConnection() ;
	pstmt=con.prepareStatement(queryList);

	rs= pstmt.executeQuery();

	while(rs.next())
	{ 
		
		id=rs.getInt("int_app_id_f");
	
//	System.out.println("--------------  666666666   ----" +id  );
		 
	}

	
}catch(SQLException se){
	se.printStackTrace();
}
finally{try{
	if(pstmt!=null)pstmt.close();
	if(con!=null) con.close();	

}catch(SQLException se){
	se.printStackTrace();
}
}
return id;

}

	
	
	
	
	
	
	
	
	
	//--------------------------------   Show Data Table  ------------------------------------------
	
	
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
	

}
