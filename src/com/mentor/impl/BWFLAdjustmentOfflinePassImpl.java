package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.mentor.action.BWFLAdjustmentOfflinePassAction;
import com.mentor.datatable.BWFLAdjustmentOfflinePassDT;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class BWFLAdjustmentOfflinePassImpl {

	// =====================get bwfl details=================================

	public String getUserDetails(BWFLAdjustmentOfflinePassAction act) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionToDataBase.getConnection();

			String selQr = 	" SELECT mobile_number, vch_firm_name, vch_firm_add, int_id, vch_license_type " +
							" FROM bwfl.registration_of_bwfl_lic_holder " +
							" WHERE  mobile_number='"+ ResourceUtil.getUserNameAllReq().trim()+ "' AND vch_approval='V' ";

			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				act.setUserId(rs.getInt("int_id"));
				act.setUserName(rs.getString("vch_firm_name"));
				act.setUserAdrs(rs.getString("vch_firm_add"));
				act.setUserTypeId(rs.getInt("vch_license_type"));
				if (rs.getString("vch_license_type").equals("1")) {
					act.setUserType("BWFL2A");
				} else if (rs.getString("vch_license_type").equals("2")) {
					act.setUserType("BWFL2B");
				} else if (rs.getString("vch_license_type").equals("3")) {
					act.setUserType("BWFL2C");
				} else if (rs.getString("vch_license_type").equals("4")) {
					act.setUserType("BWFL2D");
				}

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

	// ====================display stock available==========================

	public ArrayList availableStockImpl(BWFLAdjustmentOfflinePassAction act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		int i = 1;

		try {

			selQr = " SELECT DISTINCT c.offline_flag, c.permitno, c.int_brand_id, b.brand_name,c.int_pack_id, a.package_name, c.cr_date, " +
					" c.seq, c.licence_no,  c.int_recieved_bottles-coalesce(c.dispatch_36,0) as avlbottle ,a.duty, a.adduty, " +
					" ((c.int_planned_bottles-COALESCE(c.dispatch_36,0))/d.box_size) as available_box, d.box_size  " +
					" FROM distillery.packaging_details a, distillery.brand_registration b, " +
					" bwfl_license.mst_receipt c, distillery.box_size_details d  " +
					" WHERE a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.int_brand_id  " +
					" AND a.package_id=c.int_pack_id AND b.brand_id=c.int_brand_id  AND c.int_bwfl_id='"+act.getUserId()+"'  " +
					" AND c.int_recieved_bottles-coalesce(c.dispatch_36,0)>0  " +
					" AND COALESCE(c.dispatch_36,0)<c.int_planned_bottles AND a.box_id=d.box_id " +
					" AND a.quantity=d.qnt_ml_detail AND (c.offline_flag!='T' or c.offline_flag IS NULL) " +
					" ORDER BY c.cr_date DESC  ";

			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(selQr);
			
			//System.out.println("selQr--------------------"+selQr);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				
				BWFLAdjustmentOfflinePassDT dt = new BWFLAdjustmentOfflinePassDT();
				
				dt.setSrNo(i);
				dt.setSeq_dt(rs.getInt("seq"));
				dt.setBrandId_dt(rs.getInt("int_brand_id"));
				dt.setPckgId_dt(rs.getInt("int_pack_id"));
				dt.setBrandName_dt(rs.getString("brand_name"));				
				dt.setPckgName_dt(rs.getString("package_name"));				
				dt.setSize_dt(rs.getInt("box_size"));
				dt.setAvailableBottle_dt(rs.getInt("avlbottle"));
				dt.setAvailableBox_dt(rs.getInt("available_box"));
				dt.setLicenseNmbr_dt(rs.getString("licence_no"));
				dt.setPermitNmber_dt(rs.getString("permitno"));
				dt.setDuty_dt(rs.getDouble("duty"));
				dt.setAddDuty_dt(rs.getDouble("adduty"));
				

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
	
	
	// =====================get max id sequence==============================

	public int maxId() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = " SELECT max(seq) as id FROM bwfl_license.bwfl_adjustment_offline_stock ";
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
	
	
	public void saveMethodImpl(BWFLAdjustmentOfflinePassAction act, BWFLAdjustmentOfflinePassDT dt)
		{
			
			Connection conn=null;
			PreparedStatement pstmt=null;
					
			int seq = this.maxId();
			int status=0;
									
			String insQr = 	" INSERT INTO bwfl_license.bwfl_adjustment_offline_stock( " +
							" seq, bwfl_id, bwfl_license_type, permit_no, brand_id, pckg_id, size, " +
							" actual_box, actual_bottle, breakage, created_date, created_by) " +
							" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";	
					
			try{
				
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				
			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(insQr);
			
			pstmt.setInt(1, seq);
			pstmt.setInt(2, act.getUserId());
			pstmt.setString(3, act.getUserType());
			pstmt.setString(4, dt.getPermitNmber_dt());
			pstmt.setInt(5, dt.getBrandId_dt());
			pstmt.setInt(6, dt.getPckgId_dt());
			pstmt.setInt(7, dt.getSize_dt());
			pstmt.setInt(8, dt.getDispatchBox_dt());
			pstmt.setInt(9, dt.getDispatchBottls_dt());
			pstmt.setInt(10, dt.getBreakage_dt());
			pstmt.setDate(11, Utility.convertUtilDateToSQLDate(new Date()));
			pstmt.setString(12, ResourceUtil.getUserNameAllReq().trim());
			
			status=   pstmt.executeUpdate();
			
			//System.out.println("status1-------------"+status);
			
			if (status > 0) {
				status = 0;
				
				String updtQr = " UPDATE bwfl_license.mst_receipt SET " +
								" dispatch_36 = COALESCE(int_recieved_bottles,0)- "+ dt.getDispatchBottls_dt()+ ", offline_flag='T' " +
								" WHERE int_bwfl_id='"+ act.getUserId()+ "' AND int_brand_id='"+ dt.getBrandId_dt()+ "' " +
								" AND int_pack_id='"+ dt.getPckgId_dt()+ "' AND seq='"+ dt.getSeq_dt()+ "' " +
								" AND permitno='"+ dt.getPermitNmber_dt() + "' ";

				pstmt = conn.prepareStatement(updtQr);

				//System.out.println("updtQr----------------"+ updtQr);

				status =  pstmt.executeUpdate();
				//System.out.println("status2-------------"+status);
			}

			if (status > 0) {
				status = 0;

				String updtQr1 = 	" UPDATE bwfl_license.receipt_stock SET " +
									" int_dispatched_botls = COALESCE(int_stock_botls,0)- " + dt.getDispatchBottls_dt()+ " "+
									" WHERE int_bwfl_id='"+ act.getUserId()+ "' AND int_brand_id='"+ dt.getBrandId_dt()+ "'  " +
									" AND int_pckg_id='"+ dt.getPckgId_dt() + "' AND permit='"+ dt.getPermitNmber_dt() + "' ";

				pstmt = conn.prepareStatement(updtQr1);

				//System.out.println("updtQr----------------"+ updtQr1);

				status = pstmt.executeUpdate();
				//System.out.println("status3-------------"+status);
			}
			
				if(status>0)
				{
					conn.commit();
					act.setListFlag(true);
					ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,Constants.SAVED_SUCESSFULLY);
					
					
				}else{
					conn.rollback();					
					ResourceUtil.addMessage(Constants.NOT_SAVED,Constants.NOT_SAVED);
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}finally{
				
				try{
					if(conn!=null)conn.close();
					if(pstmt!=null)pstmt.close();
					
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
		}
	
	
	// ====================show saved data after insert==========================

		public ArrayList showSavedDataImpl(BWFLAdjustmentOfflinePassAction act) {

			ArrayList list = new ArrayList();
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String selQr = null;
			int i = 1;

			try {

				selQr = " SELECT a.seq, a.bwfl_id, a.bwfl_license_type, a.permit_no, a.brand_id, a.pckg_id,  " +
						" a.size, a.actual_box, a.actual_bottle, a.breakage, " +
						" a.created_date, a.created_by, b.brand_name, c.package_name " +
						" FROM bwfl_license.bwfl_adjustment_offline_stock a, distillery.brand_registration b,  " +
						" distillery.packaging_details c " +
						" WHERE b.brand_id=c.brand_id_fk AND a.brand_id=c.brand_id_fk AND a.pckg_id=c.package_id " +
						" AND a.brand_id=b.brand_id AND a.bwfl_id="+act.getUserId()+" " +
						" ORDER BY a.created_date DESC ";

				conn = ConnectionToDataBase.getConnection();
				ps = conn.prepareStatement(selQr);
				rs = ps.executeQuery();
				while (rs.next()) {
					
					BWFLAdjustmentOfflinePassDT dt = new BWFLAdjustmentOfflinePassDT();
					
					dt.setSrNo(i);
					dt.setShow_seq(rs.getInt("seq"));
					dt.setShow_brandId(rs.getInt("brand_id"));
					dt.setShow_pckgId(rs.getInt("pckg_id"));
					dt.setShow_brandName(rs.getString("brand_name"));				
					dt.setShow_pckgName(rs.getString("package_name"));				
					dt.setShow_size(rs.getInt("size"));
					dt.setShow_dispatchBottls(rs.getInt("actual_bottle"));
					dt.setShow_dispatchBox(rs.getInt("actual_box"));					
					dt.setShow_permitNmbr(rs.getString("permit_no"));
					
					if (rs.getDate("created_date") != null) {

						SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
						String crDate = date.format(Utility.convertSqlDateToUtilDate(rs.getDate("created_date")));
						dt.setShow_crtdDate(crDate);

					} else {

						dt.setShow_crtdDate("");
					}
					
					

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
