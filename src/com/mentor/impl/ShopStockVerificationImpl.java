package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.mentor.action.ShopStockVerificationAction;
import com.mentor.datatable.ShopStockVerificationDT;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class ShopStockVerificationImpl {

	public boolean getShopStatus(ShopStockVerificationAction act){


		int id = 0;
		boolean flag=false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionToDataBase.getConnection();

			
  String selQr ="select * from distillery.shop_data_19_20 where shopid='"+act.getShop_id()+"' and approval_flg is null";
		  
		
		
		//System.out.println("login dtl="+selQr);
			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();
			
			 
			if(rs.next()){
				flag=true;
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				
				if (rs != null)
					rs.close();
				
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return flag;
	}
	
	
	public boolean getDetails(ShopStockVerificationAction act){


		int id = 0;
		boolean flag=false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionToDataBase.getConnection();

			
  String selQr =" select distinct serial_no, vch_name_of_shop, vch_name_of_licensee, vch_shop_type from " +
  		        " retail.retail_shop where serial_no="+act.getShop_id()+" and " +
  		        " district_id=(select distinct districtid from district where  deo='"+ResourceUtil.getUserNameAllReq()+"')";
		  
		
		
		  System.out.println(" dtl="+selQr);
			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();
			
			 
			if (rs.next()) {
				act.setShop_name(rs.getString("vch_name_of_shop"));
				act.setLicensee_name(rs.getString("vch_name_of_licensee"));
				act.setShop_type(rs.getString("vch_shop_type"));
				flag=true;
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				
				if (rs != null)
					rs.close();
				
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		return flag;
	}
	
	public ArrayList stockDetail(ShopStockVerificationAction act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		int i = 1;
	 
		try {
		 selQr ="select distinct x.*, x.no_of_bottel*x.no_of_box as total_bottel, b.brand_name from " +
		 		" (select  count(*) as no_of_box,size, brand_id, pkg_id, casecode,                                 "+
				"	((substring(casecode from 24 for 3))::numeric) as no_of_bottel from distillery.shop_data_19_20 "+
				"	 where shopid='"+act.getShop_id()+"'                                                                          "+
				"	group by brand_id, pkg_id, pkg_id, casecode,size)x, distillery.brand_registration_19_20 b           "+
				"	where x.brand_id::int=b.brand_id ";
		
		 System.out.println("query--- "+selQr);
		 conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(selQr);
	 
			rs = ps.executeQuery();
			while (rs.next()) {
			   ShopStockVerificationDT dt = new ShopStockVerificationDT();
			   dt.setSrNo(i);
			   dt.setEtin(rs.getString("casecode"));
			   dt.setBrand(rs.getString("brand_name"));
			   dt.setSize(rs.getInt("size"));
			   dt.setNo_of_box(rs.getInt("no_of_box"));
			   dt.setTotal_no_of_bottles(rs.getInt("total_bottel"));
			
			   list.add(dt);
				//i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (conn != null)
					conn.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;

	}
	
	
	public double getRolloverFee(int shop_id) {

		double rolloverFee = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

String queryList = 	"select COALESCE(sum(x.final_mrp),0) as final_mrp from                                        "+
				"  (select a.brand_id,a.brand_name,b.package_id,quantity,                                         "+
				"  c.casecode,c.shopid,                                                                           "+
				"  case when int_fl2d_id>0                                                                        "+
				"  then permit else adduty+duty end as t_duty,mrp as oldmrp,mrp*2/100 as two_percent              "+
				"  ,mrp*2/100 as t_mrp,(((substring(c.casecode from 24 for 3))::numeric) *(mrp*2/100))as final_mrp "+
				"  from distillery.brand_registration_19_20 a,                                                    "+
				"  distillery.packaging_details_19_20 b , distillery.shop_data_19_20 c                            "+
				"  where a.brand_id=b.brand_id_fk and a.domain is null  and b.brand_id_fk=c.brand_id::numeric     "+
				" and b.package_id=c.pkg_id::numeric                                                              "+
				"  and (for_csd_civil='Civil' or for_csd_civil is null) and c.shopid='"+shop_id+"' order by edp)x";


			System.out.println("import fee="+queryList);
			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				rolloverFee = rs.getDouble("final_mrp");

			}

		} catch ( Exception se) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));	
			
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
		return rolloverFee;

	}
	
	public double getDiff_fee(int shop_id) {

		double diff_Fee = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

String queryList = 	"select COALESCE(sum(x.diff_dyty),0) as diff_dyty from                                                                                                                    "+
					"  (                                                                                                                                                                      "+
					"  select x.brand_id,x.package_id,((substring(y.casecode from 24 for 3))::numeric*x.diff_dyty)as diff_dyty from                                                           "+
					"  (select brand_id,package_id,diff_dyty from                                                                                                                             "+
					"  (select x.brand_id,x.package_id,COALESCE(y.t_duty,0)-COALESCE(x.t_duty,0) as diff_dyty from                                                                            "+
					"  (select brand_id,package_id,quantity,case when int_fl2d_id>0 then permit else adduty+duty end as t_duty,edp,mrp from distillery.brand_registration_19_20 a,            "+
					"  distillery.packaging_details_19_20 b where a.brand_id=b.brand_id_fk and a.domain is null and vch_renew is null and vch_renewp is null  and                             "+
					"  (for_csd_civil='Civil' or for_csd_civil is null) order by edp)x,                                                                                                       "+
					"  (select brand_id,package_id,quantity,case when int_fl2d_id>0 then permit_new else adduty_new+duty_new end as t_duty,edp,mrp from distillery.brand_registration_19_20 a,"+
					"  distillery.packaging_details_19_20 b where a.brand_id=b.brand_id_fk and a.domain is null and vch_renew is null and vch_renewp is null  and                             "+
					"  (for_csd_civil='Civil' or for_csd_civil is null) order by edp)y                                                                                                        "+
					"  where x.brand_id=y.brand_id and x.package_id=y.package_id )y where diff_dyty>0                                                                                         "+
					"  union                                                                                                                                                                  "+
					"  select brand_id,package_id,diff_dyty from ( select x.brand_id,x.package_id,COALESCE(y.t_duty,0)-COALESCE(x.t_duty,0) as diff_dyty from                                 "+
					"  (select brand_id,package_id,quantity,case when int_fl2d_id>0 then permit else adduty+duty end as t_duty,edp,mrp from distillery.brand_registration_19_20 a,            "+
					"  distillery.packaging_details_19_20 b where a.brand_id=b.brand_id_fk and a.domain is null and vch_renew='Y' and vch_renewp='Y'                                          "+
					"  and (for_csd_civil='Civil' or for_csd_civil is null) order by edp)x,(select brand_id,package_id,quantity,case when int_fl2d_id>0                                       "+
					"  then permit else adduty+duty end as t_duty,edp,mrp from distillery.brand_registration_20_21 a,distillery.packaging_details_20_21 b where a.brand_id=b.brand_id_fk)y    "+
					"  where x.brand_id=y.brand_id and x.package_id=y.package_id )x where diff_dyty>0)x                                                                                       "+
					" ,distillery.shop_data_19_20 y "+
					" where  x.brand_id=y.brand_id::numeric and x.package_id=y.pkg_id::numeric "+
					" and y.shopid='"+shop_id+"' "+
					" )x";
			

            System.out.println("import fee="+queryList);
			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				diff_Fee = rs.getDouble("diff_dyty");

			}

		} catch ( Exception se) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));	
			
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
		return diff_Fee;

	}
	
	
	public boolean getChallanStatus(ShopStockVerificationAction act) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int j=0;
		try {

			  con = ConnectionToDataBase.getConnection();
			  
			for(int i=0;i<act.getTable().size();i++){
				ShopStockVerificationDT dt = (ShopStockVerificationDT) act.getTable().get(i);
				
           String queryList = "SELECT * from licence.mst_challan_master WHERE vch_challan_id='"+dt.getChallan_no()+"' and vch_trn_status='SUCCESS' ";
			
			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			if (rs.next()) {
              j++;
				

			}else{
				
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Challan No. "+dt.getChallan_no()+" not Found", "Challan No. "+dt.getChallan_no()+" not Found"));
				
			}

		} 
		}catch ( Exception se) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));	
			
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
		
		if(j==act.getTable().size()){
		 return true;
		 }else
		 {
			 return false;
		 }
		

	}
	
	public boolean saveImpl(ShopStockVerificationAction act, String rollover_challan, String diff_challann, int shop_id){


		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		String query = ""; 
		
		
		boolean flg=false;
		 try {

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			 
			
				
			query="Update distillery.shop_data_19_20 set rollover_challan_no=?, diff_duty_challan_no=?, approval_dt=?," +
					"approval_flg='A' where shopid='"+shop_id+"'";
					
					 pstmt=conn.prepareStatement(query);
				
					 pstmt.setString(1, rollover_challan);
					 pstmt.setString(2, diff_challann);
					 pstmt.setDate(3,Utility.convertUtilDateToSQLDate(new Date()));
					 saveStatus = pstmt.executeUpdate();
				 System.out.println("update query =="+query);
				 System.out.println("rollover_challan =="+rollover_challan);
				 System.out.println("diff_challann =="+diff_challann);
			
			if (saveStatus > 0) {
				conn.setAutoCommit(true);
				flg=true;
				act.setFlag(false);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("SAVED SUCCESSFULLY !", "SAVED SUCCESSFULLY !"));
				
				

			} else {
				// action.reset();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,Constants.NOT_SAVED);

			}

		}

		catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}

		finally {
			try {

				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	return flg;

	}
}
