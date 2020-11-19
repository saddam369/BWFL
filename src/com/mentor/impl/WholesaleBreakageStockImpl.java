package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.mentor.action.WholesaleBreakageStockAction;
import com.mentor.datatable.WholesaleBreakageStockDT;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class WholesaleBreakageStockImpl {
	
	public String getDetails(WholesaleBreakageStockAction act) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionToDataBase.getConnection();

			String selQr = 	" SELECT int_app_id, vch_licence_no, vch_firm_name, vch_license_type, vch_core_address " +
							" FROM licence.fl2_2b_2d_20_21  " +
							" WHERE loginid='"+ ResourceUtil.getUserNameAllReq().trim() + "' ";

			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				act.setLoginUserId(rs.getInt("int_app_id"));
				act.setLoginUserNm(rs.getString("vch_firm_name"));
				act.setLoginUserAdrs(rs.getString("vch_core_address"));
				act.setLoginUserType(rs.getString("vch_license_type"));
				act.setLicenseNmbr(rs.getString("vch_licence_no"));
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
		return "";

	}
	
	
	//=============display data in datatable=================
	
	public ArrayList showStockList(WholesaleBreakageStockAction act){


		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		int i = 1, cases = 0;

		try {
			
				selQr = " SELECT c.id, c.type, c.brand_id, c.pckg_id, c.size  as box_size, a.package_name, b.brand_name,  "+  
						" b.unit_nm, (c.recv_total_bottels-COALESCE(c.dispatchbotl,0)) as avlbottle                       "+                                                   
						" FROM distillery.packaging_details_20_21 a   ,                                                   "+
						" distillery.brand_registration_20_21 b,fl2d.fl2_2b_stock_20_21 c                                 "+                        
						" WHERE a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.brand_id                                     "+              
						" AND a.package_id=c.pckg_id AND b.brand_id=c.brand_id                                            "+
						" AND c.recv_total_bottels-COALESCE(c.dispatchbotl,0)>0  AND c.id='"+act.getLoginUserId()+"'      "+                                                                                      
						" GROUP BY c.brand_id, c.id, c.pckg_id, a.package_name, c.type, b.brand_name, c.size, b.unit_nm      "+
						" ORDER BY  b.unit_nm, a.package_name           ";
			
			
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(selQr);

			 System.out.println("stock query--------------"+selQr);

			rs = ps.executeQuery();
			while (rs.next()) {
				WholesaleBreakageStockDT dt = new WholesaleBreakageStockDT();
				
				dt.setSrNo(i);
				dt.setBrandId_dt(rs.getInt("brand_id"));
				dt.setPckgId_dt(rs.getInt("pckg_id"));
				dt.setBoxSize_dt(rs.getInt("box_size"));
				dt.setAvlBottle_dt(rs.getInt("avlbottle"));
				dt.setBrandName_dt(rs.getString("unit_nm"));
				dt.setPckgName_dt(rs.getString("package_name"));
				

				list.add(dt);
				i++;
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
	
	// =====================get max id sequence =============================

	public int maxId() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String query = " SELECT max(seq) as id FROM fl2d.wholesale_godown_stock_breakage ";
		
		
		int maxid = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxid = rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return maxid + 1;

	}
	
	
	//==========================save method==================================
	
	
	public void saveMethodImpl(WholesaleBreakageStockAction act){

		
		Connection conn=null;
		PreparedStatement pstmt=null;		
		int seq = this.maxId();
		int status=0;
	
		try{
						
		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);

			
			for(int i=0; i<act.getStockList().size();i++)
			{

				WholesaleBreakageStockDT dt=(WholesaleBreakageStockDT)act.getStockList().get(i);
				
				String insQr1 = " INSERT INTO fl2d.wholesale_godown_stock_breakage_20_21( "+
								" seq, fl2_2b_id, brand_id, pckg_id, box_size, avl_bottle, breakage, stock_bottle, cr_date, fl2_2b_type) "+
								" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)  ";
				
				if(dt.getBreakage_dt() > 0){
	
				pstmt = conn.prepareStatement(insQr1);
				
				 
				pstmt.setInt(1, seq);
				pstmt.setInt(2, act.getLoginUserId());				
				pstmt.setInt(3, dt.getBrandId_dt());
				pstmt.setInt(4, dt.getPckgId_dt());
				pstmt.setInt(5, dt.getBoxSize_dt());
				pstmt.setInt(6, dt.getAvlBottle_dt());
				pstmt.setInt(7, dt.getBreakage_dt());
				pstmt.setInt(8, dt.getStockBottle_dt());
				pstmt.setDate(9, Utility.convertUtilDateToSQLDate(new Date()));
				pstmt.setString(10, act.getLoginUserType());
				
			    status =  pstmt.executeUpdate();
			    
			   
			    
			    seq=seq+1;
			    
			    if(status > 0){
			    	
			    	status = 0;
			    	
			    	String updtQr = " UPDATE fl2d.fl2_2b_stock_20_21 SET "+
									" dispatchbotl = COALESCE(dispatchbotl,0)+"+dt.getBreakage_dt()+" "+
									" WHERE id="+act.getLoginUserId()+" AND type='"+act.getLoginUserType()+"'  " +
									" AND brand_id="+dt.getBrandId_dt()+" AND pckg_id="+dt.getPckgId_dt()+"   ";
			    	
			    	pstmt = conn.prepareStatement(updtQr);
			    	
			    	status =  pstmt.executeUpdate();
			    	
			    	 
			    	
			    	
			    }
			    			    
				}		    
			
			}
		
		
			if(status>0)
			{
				conn.setAutoCommit(true);
				act.setListFlagForPrint(true);
				ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,Constants.SAVED_SUCESSFULLY);

			}else{
				conn.rollback();				
				ResourceUtil.addMessage(Constants.NOT_SAVED,Constants.NOT_SAVED);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
			
		}finally{
			
			try{
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
				
				
			}catch(SQLException e)
			{
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
			}
		}
		
	
	}
	
	
	//=============display saved data in datatable=================
	
	public ArrayList displaySavedData(WholesaleBreakageStockAction act){


		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		int i = 1;

		try {
			
				selQr = " SELECT a.seq, a.fl2_2b_id, a.brand_id, a.pckg_id, a.box_size, a.avl_bottle, a.breakage, "+
						" a.stock_bottle, a.cr_date, a.fl2_2b_type, c.package_name, b.brand_name, b.unit_nm       "+
						" FROM fl2d.wholesale_godown_stock_breakage_20_21 a, distillery.brand_registration_20_21 b,     "+
						" distillery.packaging_details_20_21 c                                                    "+
						" WHERE b.brand_id=c.brand_id_fk                                                          "+
						" AND a.pckg_id=c.package_id AND a.brand_id=b.brand_id                                    "+
						" AND a.fl2_2b_id="+act.getLoginUserId()+"                                                "+
						" ORDER BY a.cr_date DESC, a.seq           ";
			
			
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(selQr);

		 

			rs = ps.executeQuery();
			while (rs.next()) {
				WholesaleBreakageStockDT dt = new WholesaleBreakageStockDT();
				
				dt.setSrNo(i);
				dt.setShow_seq(rs.getInt("seq"));
				dt.setShow_userId(rs.getInt("fl2_2b_id"));
				dt.setShow_crDate(rs.getDate("cr_date"));
				dt.setShow_brandId(rs.getInt("brand_id"));
				dt.setShow_pckgId(rs.getInt("pckg_id"));
				dt.setShow_boxSize(rs.getInt("box_size"));
				dt.setShow_breakage(rs.getInt("breakage"));
				dt.setShow_brandName(rs.getString("unit_nm"));
				dt.setShow_pckgName(rs.getString("package_name"));
				

				list.add(dt);
				i++;
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

}
