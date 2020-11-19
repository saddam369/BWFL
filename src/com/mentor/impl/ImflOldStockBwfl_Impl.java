package com.mentor.impl;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


 
import com.mentor.action.ImflOldStockFL36;

import com.mentor.connectiondb.ConnectionToDataBase;

import com.mentor.datatable.ImflOldStockBwfl_DataTable;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;


public class ImflOldStockBwfl_Impl {

	// ===================get distillery details=================================


	public String getDetails(ImflOldStockFL36 act) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			String selQr = " SELECT mobile_number, vch_firm_name, vch_firm_add, int_id, vch_license_type,vch_license_number "
					+ " FROM bwfl.registration_of_bwfl_lic_holder "
					+ " WHERE  mobile_number='"+ ResourceUtil.getUserNameAllReq().trim() +"' " +
					" AND vch_approval='V' ";

			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				act.setBwflId(rs.getInt("int_id"));
				act.setVch_bwfl_name(rs.getString("vch_firm_name"));
				act.setVch_bwfl_address(rs.getString("vch_firm_add"));
				act.setLicenseTypeId(rs.getInt("vch_license_type"));
				act.setBwfllicno(rs.getString("vch_license_number"));
				if (rs.getString("vch_license_type").equals("1")) 
				{
				act.setVch_from("BWFL2A");
				} 
				else if (rs.getString("vch_license_type").equals("2")) 
				{
					act.setVch_from("BWFL2B");
				} 
				else if (rs.getString("vch_license_type").equals("3")) 
				{
					act.setVch_from("BWFL2C");
				} 
				else if (rs.getString("vch_license_type").equals("4")) 
				{
					act.setVch_from("BWFL2D");
				}

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (ps2 != null)
					ps2.close();
				if (rs != null)
					rs.close();
				if (rs2 != null)
					rs2.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return "";

	}

	
	//============================get from license list===============================
	
	
	public ArrayList fromLicListImpl(ImflOldStockFL36 act)
	{

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("");
		list.add(item);
		try {
			String query = 	" SELECT DISTINCT licence_no  FROM bwfl_license.bwfl_oldstock_entry_17_18 " +
							" WHERE int_bwfl_id ='"+act.getBwflId()+"'  " +
							" AND vch_license_type = '"+act.getVch_from()+"' ";

			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);
			
			//System.out.println("query------from license--------"+query);
			
			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();

				item.setValue(rs.getString("licence_no"));
				item.setLabel(rs.getString("licence_no"));

				list.add(item);

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
	
	public ArrayList toliclistImpl1a(ImflOldStockFL36 act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("NA");
		list.add(item);
		try {
			String query = "SELECT vch_license_fl1a FROM licence.fl3a_fl1a " +
					" WHERE int_brewery_id='"+act.getBwflId()+"' " +
					" AND vch_licence_type='"+act.getVch_to()+"' and vch_lic_unit_type='B'";

			conn = ConnectionToDataBase.getConnection();

			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();

				// item.setValue(rs.getInt("int_state_id"));
				item.setValue(rs.getString("vch_license_fl1a"));
				item.setLabel(rs.getString("vch_license_fl1a"));

				list.add(item);

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
	public ArrayList toliclistImpl(ImflOldStockFL36 act)
	{
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("NA");
		list.add(item);
		try {
			String query = "SELECT vch_licence_no FROM licence.licence_entery_fl3_fl1" +
					" WHERE int_app_id='"+act.getBwflId()+"' " +
					" AND vch_licence_type='"+act.getVch_to()+"' and vch_lic_unit_type='B'";
			;

			conn = ConnectionToDataBase.getConnection();

			// System.out.println("FL1 Query -- " + query);
			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();

				// item.setValue(rs.getInt("int_state_id"));
				item.setValue(rs.getString("vch_licence_no"));
				item.setLabel(rs.getString("vch_licence_no"));

				list.add(item);

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
	/*public ArrayList toLicListImpl1A(ImflOldStockFL36 act)
	{

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("");
		list.add(item);
		
		
		try {
			String query = 	" SELECT vch_license_fl1a FROM licence.fl3a_fl1a " +
							" WHERE int_distillery_id='"+ act.getDistId()+ "' " +
							" AND vch_licence_type='"+ act.getVch_to() + "' AND vch_lic_unit_type='D'";
			

			conn = ConnectionToDataBase.getConnection();

			ps = conn.prepareStatement(query);
			
			//System.out.println("query------to license FL1A--------"+query);

			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();
				
				item.setValue(rs.getString("vch_license_fl1a"));
				item.setLabel(rs.getString("vch_license_fl1a"));

				list.add(item);

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
	*/
	
	//==========================display data in first datatable=================================
	
	
	
	public ArrayList displaylistImpl(ImflOldStockFL36 act, String lic)
	{

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;		
		int i = 1;

		try {
			
			selQr = " SELECT distinct a.package_name, a.package_id, a.duty, a.adduty," +
					"  c.brand_id, b.brand_name,"
					+ " c.pack_id, c.stock_bottels-c.dispatch_bottels as avlbottle, d.box_size,"
					+ " ROUND(((c.stock_bottels-c.dispatch_bottels)/d.box_size)) as avlbox "
					
					+ " FROM distillery.packaging_details a, distillery.brand_registration b, "
					+ "  bwfl_license.fl11_bwfl_old_stock c, distillery.box_size_details d "
					
					+ " WHERE a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.brand_id "
					+ " AND a.package_id=c.pack_id AND b.brand_id=c.brand_id "
					+ " AND c.bwfl_id='"+act.getBwflId()+"' " 
				
					+ " AND c.stock_bottels-c.dispatch_bottels>0 "
					+ "   AND a.quantity=d.qnt_ml_detail order by b.brand_name,a.package_name ";


			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(selQr);
			
		System.out.println("-------first table"+selQr);
		
			rs = ps.executeQuery();
			while (rs.next()) 
			{
				ImflOldStockBwfl_DataTable dt = new ImflOldStockBwfl_DataTable();
				
				dt.setInt_brand_id(rs.getInt("brand_id"));
				dt.setInt_pckg_id(rs.getInt("pack_id"));
				dt.setVch_brand(rs.getString("brand_name"));
				dt.setInt_bottle_avaliable(rs.getInt("avlbottle"));
				dt.setPckg_name(rs.getString("package_name"));
				//dt.setDb_duty(rs.getDouble("duty"));
				//dt.setDb_add_duty(rs.getDouble("adduty"));
				dt.setSize(rs.getInt("box_size"));
				dt.setBoxAvailable(rs.getInt("avlbox"));
				
				dt.setSlno(i);

				
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
	
	
	// =====================get max id from gatepass_to_manufacturewholesale_imfl_old_stock ==============================

	public int maxId()
	{

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(seq) as id FROM " +
						" bwfl_license.gatepass_to_districtwholesale_2017_18_bwfl_old_stock ";
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
	
	
	// =====================get max id from fl1_stock_trxn_imfl_old_stock ==============================

	public int maxIdDtl()
	{


		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(seq) as id FROM bwfl.fl1_stock_trxn_old_stock_17_18 ";
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
	
	//=======================get max id from duty_register==================
	
	
	public int getMaxIdDuty()
	{

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int maxid = 0;

		try {
			String query = "SELECT max(int_id) from distillery.duty_register ";
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxid = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		return maxid;
	
	}
	
	//=============================save data====================================
	
	
	
	/*public String saveMethodImpl(ImflOldStockFL36 act)
	{
		Connection con = null;
		PreparedStatement ps = null, ps2 = null, ps2D = null, ps3 = null, ps4 = null, ps5 = null;
		ResultSet rs = null;
		String sql = "";
		String updtQr = "";
		String updtQr1 = "";
		String sql3 = "";
		String insQr = "";
		int status = 0 ;
		double duty = 0;
		double addduty = 0;
		int dispatch = 0;
		int seq = this.maxId();
		int seqDtl = this.maxIdDtl();
		String gatepass = String.valueOf(act.getBwflId()) + "-2017-18-"+ act.getVch_from() + "-" + seq;

		java.sql.Timestamp date1 = new java.sql.Timestamp(new java.util.Date().getTime());

		try {

			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);


			sql=      " INSERT INTO bwfl_license.gatepass_to_districtwholesale_2017_18_bwfl_old_stock( "
					+ " int_bwfl_id, vch_gatepass_no, dt_date, vch_to, vch_to_lic_no, curr_date, licencee_id, "
					+ " vch_route_detail, vch_vehicle_no, vehicle_driver_name, vehicle_agency_name_adrs, "
					+ " licensee_name, licensee_adrs, district_1, district_2, district_3, valid_till, "
					+ " licence_district, seq, gross_weight, tier_weight, net_weight, vch_from) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			
		
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, act.getBwflId());
			ps.setString(2, gatepass);
			ps.setDate(3, Utility.convertUtilDateToSQLDate(act.getDt_date()));
			ps.setString(4, act.getVch_to());
			if (act.getVch_to().equalsIgnoreCase("DW")) 
			{
				ps.setString(5, act.getVch_to_lic_no());
			} 
			else if (act.getVch_to().equalsIgnoreCase("BRC")) 
			{
				ps.setString(5, act.getBrc_to_lic());
			}
			ps.setDate(6, Utility.convertUtilDateToSQLDate(new Date()));
			ps.setInt(7, 0);
			ps.setString(8, act.getRouteDtl());
			ps.setString(9, act.getVehicleNo());
			ps.setString(10, act.getVehicleDrvrName());
			ps.setString(11, act.getVehicleAgencyNmAdrs());
			ps.setString(12, act.getLicenseeName());
			ps.setString(13, act.getLicenseeAdrs());
			ps.setInt(14, act.getDistrict1());
			ps.setInt(15, act.getDistrict2());
			ps.setInt(16, act.getDistrict3());
			ps.setDate(17,Utility.convertUtilDateToSQLDate(act.getValidtill_date()));
			ps.setInt(18, act.getDistrictId());
			ps.setInt(19, seq);
			ps.setDouble(20, act.getGrossWeight());
			ps.setDouble(21, act.getTierWeight());
			ps.setDouble(22, act.getNetWeight());
			ps.setString(23, act.getVch_from());

			status = ps.executeUpdate();
			
			

			for (int i = 0; i < act.displaylist.size(); i++) {
				ImflOldStockBwfl_DataTable dt = (ImflOldStockBwfl_DataTable) act.getDisplaylist().get(i);	
				if (dt.getInt_dispatch() > 0 && dt.getDispatchBoxes() > 0) {
					if (status > 0) {
						status = 0;
				
					
						insQr=	" INSERT INTO bwfl.fl1_stock_trxn_old_stock_17_18_bwfl( " +
								" int_bwfl_id, vch_lic_type, int_brand_id, int_pckg_id,vch_lic_no, " +
								" avl_bottl, avl_box, dispatchd_bottl, dispatchd_box, size, duty, addduty, "+
								" vch_gatepass_no,  batch_no , seq ) " +
								" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
						 
						ps5 = con.prepareStatement(insQr);

						ps5.setInt(1, act.getBwflId());
						ps5.setString(2, act.getVch_to());
						ps5.setInt(3, (dt.getInt_brand_id()));
						ps5.setInt(4, (dt.getInt_pckg_id()));
						ps5.setString(5, act.getVch_to_lic_no());						
						ps5.setInt(6, dt.getInt_bottle_avaliable());
						ps5.setInt(7, dt.getBoxAvailable());
						ps5.setInt(8, dt.getInt_dispatch());
						ps5.setInt(9, dt.getDispatchBoxes());
						ps5.setInt(10, dt.getSize());
						ps5.setDouble(11, dt.getCalculated_duty());
						ps5.setDouble(12, dt.getCalculated_add_duty());
						ps5.setString(13, gatepass);
						ps5.setString(14, dt.getBatchNo());
						ps5.setInt(15, seqDtl);						
						
						dispatch =dispatch + dt.getInt_dispatch();
						
						status = ps5.executeUpdate();
						seqDtl=seqDtl+1;
						
					
					}
					
					if(status > 0)
					{
						status = 0;
											
					
					updtQr = 	" INSERT INTO bwfl.fl36_imfl_old_stock_bwfl ( " +
								" bwfl_id, int_brand_id, int_pckg_id, vch_lic_nmbr, vch_lic_type, " +
								" int_stock_bottles, int_stock_boxes, int_dispatch_bottles, int_dispatch_boxes) " +
								" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) "+
								" ON CONFLICT ON CONSTRAINT fl36_imfl_old_stock_bwfl_pkey " +
								" DO UPDATE SET " +
								" int_stock_bottles = COALESCE(bwfl.fl36_imfl_old_stock_bwfl.int_stock_bottles,0)+'"+dt.getInt_dispatch()+"', " +
								" int_stock_boxes= COALESCE(bwfl.fl36_imfl_old_stock_bwfl.int_stock_boxes,0)+'"+dt.getDispatchBoxes()+"' " ;
								
		
								
								ps2 = con.prepareStatement(updtQr);
						
								ps2.setInt(1, act.getBwflId());
								ps2.setInt(2, (dt.getInt_brand_id()));
								ps2.setInt(3, (dt.getInt_pckg_id()));
								ps2.setString(4, act.getVch_to_lic_no());
								ps2.setString(5, act.getVch_to());
								ps2.setInt(6, dt.getInt_dispatch());
								ps2.setInt(7, dt.getDispatchBoxes());
								ps2.setInt(8, 0);
								ps2.setInt(9, 0);
				
								status = ps2.executeUpdate();
								
							
					}
					
					if(status > 0)
					{
						status = 0;
						
						updtQr1 = 	" UPDATE bwfl_license.fl11_bwfl_old_stock " +
									" dispatch_boxes=COALESCE(dispatch_boxes,0)+'"+ dt.getDispatchBoxes()+ "', " +
									" dispatch_bottels=COALESCE(dispatch_bottels,0)+'"+ dt.getInt_dispatch()+ "' " +
									" WHERE bwfl_id='"+ act.getBwflId()+"' " +
									" AND licence_number='"+ act.getVch_from_lic_no() +"' " +
									" AND brand_id='"+ dt.getInt_brand_id() +"' " +
									" AND pack_id='"+ dt.getInt_pckg_id() +"'";
						
						ps4 = con.prepareStatement(updtQr1);
						status = ps4.executeUpdate();
					}

				}
			}
			
			if(status > 0)
			{
				status = 0;
				
				sql3 = 	" INSERT INTO bwfl.duty_register( " +
						" int_id, int_brewery_id, date_crr_date, vch_duty_type, int_quantity, int_value, vch_description) " +
						" VALUES (?, ?, ?, ?, ?, ?, ?) ";

				ps3 = con.prepareStatement(sql3);

				ps3.setInt(1, getMaxIdDuty() + 1);
				ps3.setInt(2, act.getBwflId());
				ps3.setDate(3, new java.sql.Date(System.currentTimeMillis()));
				ps3.setString(4, "OLD-IMFL-STOCK-DUTY");
				ps3.setDouble(5, dispatch);
				ps3.setDouble(6, act.getDb_total_value());				
				ps3.setString(7, "Duty for IMFL Old Stock FL11 for Gatepass " + gatepass);
				
				status = ps3.executeUpdate();
				
				

				sql3 = 	" INSERT INTO bwfl.duty_register( " +
						" int_id, int_brewery_id, date_crr_date, vch_duty_type, int_quantity, int_value, vch_description) " +
						" VALUES (?, ?, ?, ?, ?, ?, ?)";

				ps3 = con.prepareStatement(sql3);

				ps3.setInt(1, getMaxIdDuty() + 2);
				ps3.setInt(2, act.getBwflId());
				ps3.setDate(3, new java.sql.Date(System.currentTimeMillis()));
				ps3.setString(4, "OLD-IMFL-STOCK-ADD-DUTY");

				ps3.setDouble(5, dispatch);
				ps3.setDouble(6, act.getDb_total_add_value());				
				ps3.setString(7, "Add-Duty for IMFL Old Stock FL11 for Gatepass " + gatepass);
				status = ps3.executeUpdate();
				
				
			}
			
			if (status > 0) {
				con.commit();
				act.clearAll();
				ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,Constants.SAVED_SUCESSFULLY);
				
			}

			else {
				con.rollback();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,Constants.NOT_SAVED);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally 
		{
			try 
			{
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (ps2 != null)
					ps2.close();
				if (ps5 != null)
					ps5.close();
				if (ps3 != null)
					ps3.close();
				if (ps4 != null)
					ps4.close();
				if (ps2D != null)
					ps2D.close();

			} catch (Exception e) 
			{
				e.printStackTrace();			}
		}

		return "";
	}
*/
	
	public String saveMethodImpl(ImflOldStockFL36 act) {

		Connection con = null;
		PreparedStatement ps = null, ps2 = null, ps2D = null, ps3 = null, ps4 = null, ps5 = null;
		ResultSet rs = null;
		String sql = "";
		String sql2 = "";
		String sql3 = "";
		int tok1 = 0, tok2 = 0, tok3 = 0, tok5 = 0;
		double duty = 0;
		double addduty = 0;
		String sql5 = "";
		int sequence=0;
		int seq = this.maxId();
		

		String gatepass = String.valueOf(act.getBwflId())+ "-2017-18-BWFL-"+ seq;
		try {
			int cases = 0;
			int totalBottles = 0;

			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);

			String insQr = " INSERT INTO bwfl_license.gatepass_to_districtwholesale_2017_18_bwfl_old_stock( "
					+ " int_bwfl_id, vch_gatepass_no, dt_date, vch_to, vch_to_lic_no, curr_date, licencee_id, "
					+ " vch_route_detail, vch_vehicle_no, vehicle_driver_name, vehicle_agency_name_adrs, "
					+ " licensee_name, licensee_adrs, district_1, district_2, district_3, valid_till, "
					+ " licence_district, seq, gross_weight, tier_weight, net_weight, vch_from) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

			ps = con.prepareStatement(insQr);

			ps.setInt(1, act.getBwflId());
			ps.setString(2, gatepass);
			ps.setDate(3, Utility.convertUtilDateToSQLDate(act.getDt_date()));
			ps.setString(4, act.getVch_to());
			if (act.getVch_to().equalsIgnoreCase("DW")) {
				ps.setString(5, act.getVch_to_lic_no());
			} else if (act.getVch_to().equalsIgnoreCase("BRC")) {
				ps.setString(5, act.getBrc_to_lic());
			}
			ps.setDate(6, Utility.convertUtilDateToSQLDate(new Date()));
			ps.setInt(7, 0);
			ps.setString(8, act.getRouteDtl());
			ps.setString(9, act.getVehicleNo());
			ps.setString(10, act.getVehicleDrvrName());
			ps.setString(11, act.getVehicleAgencyNmAdrs());
			ps.setString(12, act.getLicenseeName());
			ps.setString(13, act.getLicenseeAdrs());
			ps.setInt(14, act.getDistrict1());
			ps.setInt(15, act.getDistrict2());
			ps.setInt(16, act.getDistrict3());
			ps.setDate(17,Utility.convertUtilDateToSQLDate(act.getValidtill_date()));
			ps.setInt(18, act.getDistrictId());
			ps.setInt(19, seq);
			ps.setDouble(20, act.getGrossWeight());
			ps.setDouble(21, act.getTierWeight());
			ps.setDouble(22, act.getNetWeight());
			ps.setString(23, act.getVch_from());
			tok1 = ps.executeUpdate();

			System.out.println(" first token"+tok1);
			if (tok1 > 0) {

				for (int i = 0; i < act.displaylist.size(); i++) {
					ImflOldStockBwfl_DataTable dt = (ImflOldStockBwfl_DataTable) act.getDisplaylist().get(i);	
					if (dt.getDispatchbottls() > 0 && dt.getDispatchBoxes() > 0)
					{
						tok1 = 0;

						String insQr1 = " INSERT INTO bwfl_license.fl2_stock_trxn_bwfl_old_stock( "
								+ " int_bwfl_id, vch_lic_no, dt, int_brand_id, int_pckg_id, avl_bottl, avl_box, "
								+ " breakage, balance, vch_gatepass_no, size, vch_batch_no, dispatch_bottle, "
								+ " dispatch_box, seq_fl2, duty, add_duty, mst_seq) VALUES "
								+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, (select nextval('seq')))";

						ps2 = con.prepareStatement(insQr1);

						ps2.setInt(1, act.getBwflId());
						ps2.setString(2, act.getVch_to());
						ps2.setDate(3, Utility.convertUtilDateToSQLDate(act.getDt_date()));
						ps2.setInt(4, dt.getInt_brand_id());
						ps2.setInt(5, dt.getInt_pckg_id());
						ps2.setInt(6, dt.getInt_bottle_avaliable());
						ps2.setInt(7, dt.getBoxAvailable());
						ps2.setInt(8, dt.getBreakage());
						ps2.setDouble(9, dt.getBalance());
						ps2.setString(10, gatepass);
						ps2.setInt(11, dt.getSize());
						ps2.setString(12, dt.getBatchNo());
						ps2.setInt(13, dt.getDispatchbottls());
						ps2.setInt(14, dt.getDispatchBoxes());
						ps2.setInt(15, sequence);
						ps2.setDouble(16, dt.getCalculated_duty());
						ps2.setDouble(17, dt.getCalculated_add_duty());

						cases = cases + dt.getDispatchBoxes();
						totalBottles = totalBottles + dt.getDispatchbottls();
						tok1 = ps2.executeUpdate();
						sequence++;
						System.out.println(" second token"+tok1);
						if (tok1 > 0) 
						{
							tok1 = 0;
							String updtQr1 =" UPDATE bwfl_license.fl11_bwfl_old_stock set " +
									" dispatch_boxes=COALESCE(dispatch_boxes,0)+"+ dt.getDispatchBoxes()+ ", " +
									" dispatch_bottels=COALESCE(dispatch_bottels,0)+"+ (dt.getDispatchbottls()+ dt.getBreakage())+" " +
									" WHERE bwfl_id='"+ act.getBwflId()+"' " +
									" AND licence_number='"+ act.getBwfllicno()+"' " +
									" AND brand_id='"+ dt.getInt_brand_id() +"' " +
									" AND pack_id='"+ dt.getInt_pckg_id() +"'";
						
							
							ps3 = con.prepareStatement(updtQr1);

							System.out.println(updtQr1);

							tok1 = ps3.executeUpdate();
							System.out.println(" update token"+tok1);
							
						}

						

					}

				}

				/*if (tok1 > 0) {
					tok1 = 0;

					sql3 = "INSERT INTO distillery.duty_register( "
							+ "int_id, int_bwfl_id, date_crr_date, vch_duty_type, int_quantity, int_value, vch_description, int_distillery_id) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

					ps5 = con.prepareStatement(sql3);

					ps5.setInt(1, dutymaxId() + 1);
					ps5.setInt(2, act.getBwflId());
					ps5.setDate(3,
							new java.sql.Date(System.currentTimeMillis()));
					ps5.setString(4, "BWFL Export DUTY");
					ps5.setDouble(5, totalBottles);
					ps5.setDouble(6, act.getDb_total_value());
					ps5.setString(7, "Duty for BWFL Export DUTY for Gatepass "
							+ gatepass);
					ps5.setInt(8, 0);
					tok1 = ps5.executeUpdate();
					// System.out.println("fourth status-------insert-------"+tok1);

					sql3 = "INSERT INTO distillery.duty_register( "
							+ "int_id, int_bwfl_id, date_crr_date, vch_duty_type, int_quantity, int_value, vch_description, int_distillery_id) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

					ps5 = con.prepareStatement(sql3);

					ps5.setInt(1, dutymaxId() + 2);
					ps5.setInt(2, act.getBwflId());
					ps5.setDate(3,
							new java.sql.Date(System.currentTimeMillis()));
					ps5.setString(4, "BWFL Export ADD-DUTY");
					ps5.setDouble(5, totalBottles);
					ps5.setDouble(6, act.getDb_total_add_value());
					ps5.setString(7,
							"Add-Duty for BWFL Export Add-Duty for Gatepass "
									+ gatepass);
					ps5.setInt(8, 0);
					tok1 = ps5.executeUpdate();
					// System.out.println("fifth status-------insert-------"+tok1);

				}*/
			}

			if (tok1 > 0) {

				con.setAutoCommit(true);
				act.setListFlagForPrint(true);
				act.clearAll();
				ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,
						Constants.SAVED_SUCESSFULLY);

				/*String to = act.getVch_to();
				String Name = act.getVch_bwfl_name();

				String sub = "Registered Successfully";
				String msg = " " + gatepass + " For " + cases
						+ " Cases Containing " + totalBottles
						+ " Bottles Has Been Dispatched To Your District ";

				String from = CommonImpl.getEmailUser();
				String password = CommonImpl.getEmailUserPassword();
				CommonImpl.sendEmail(to, sub, msg, from, password);
				act.clearAll();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Mail Sent Successfully !!!",
								"Mail Sent Successfully !!!"));*/
			}

			else {
				con.rollback();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
						Constants.NOT_SAVED);

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (ps2 != null)
					ps2.close();
				if (ps5 != null)
					ps5.close();
				if (ps3 != null)
					ps3.close();
				if (ps4 != null)
					ps4.close();
				if (ps2D != null)
					ps2D.close();

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			}
		}

		return "";

	}
	public String getEmailDetails(ImflOldStockFL36 act) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			String queryList = " SELECT a.officer_email "
					+ " FROM public.district a, bwfl_license.gatepass_to_districtwholesale_2017_18_bwfl_old_stock b "
					+ " WHERE a.districtid=b.licence_district ";

			pstmt = con.prepareStatement(queryList);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				act.setOfficerEmail(rs.getString("officer_email"));
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

	public int dutymaxId() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = " SELECT max(int_id) as id FROM distillery.duty_register ";
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
		return maxid;

	}
	//================================get details in second datatable=========================
	
	public ArrayList getTable2List(ImflOldStockFL36 act) {

		ArrayList list = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String finalz = "";

		String query = " SELECT  dt_date, vch_to, vch_to_lic_no,  curr_date, vch_gatepass_no,  vch_finalize  "
				+ " FROM bwfl.gatepass_to_districtwholesale_2017_18_bwfl_old_stock WHERE " +
				" bwfl_id='"+ act.getBwflId()+"' order by dt_date  desc";

		
		try {
			list = new ArrayList();
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			int i = 1;
			while (rs.next()) {
				ImflOldStockBwfl_DataTable dt = new ImflOldStockBwfl_DataTable();

				dt.setSno(i);
				dt.setDt_date(rs.getDate("dt_date"));
				dt.setVch_to(rs.getString("vch_to"));
				dt.setVch_gatepass_no(rs.getString("vch_gatepass_no"));
				dt.setVch_to_lic_no(rs.getString("vch_to_lic_no"));
				
				finalz = rs.getString("vch_finalize");

				if (finalz != null) { // (finalz.equalsIgnoreCase("F"))
					dt.setFinalizePrint(true);
					dt.setPrintDraft(false);
				} else {
					dt.setFinalizePrint(false);
					dt.setPrintDraft(true);
				}

				list.add(dt);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	
	/*public ArrayList getWholeSaleManufactureList(ImflOldStockFL36 act)
	{

		ArrayList list = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

	
		
		String query=" SELECT distinct a.bwfl_id, a.vch_bwfl_name, a.vch_bwfl_address, "+
					 " a.vch_gatepass_no, c.code_generate_through, a.dt_date, a.vch_from, a.vch_to, "+
					 " a.vch_from_lic_no, a.vch_to_lic_no, a.vch_bond, a.curr_date, a.int_max_id, "+
					 " a.db_total_duty, a.db_total_additional_duty, b.dispatchd_bottl,"+ 
					 " COALESCE(a.vch_finalize,'N') as vch_finalize  "+
	
					 " FROM bwfl.gatepass_to_districtwholesale_2017_18_bwfl_old_stock a, 	 "+				
					 " bwfl.fl1_stock_trxn_old_stock_17_18_bwfl b, "+
					 " distillery.packaging_details c  "+
	

					" WHERE	a.bwfl_id=b.int_bwfl_id  "+
					" AND a.vch_gatepass_no=b.vch_gatepass_no  "+
					" AND  b.int_pckg_id=c.package_id "+
					" AND b.int_brand_id=c.brand_id_fk "+
					" AND a.bwfl_id='"+act.getBwflId()+"'  "+
					" ORDER BY a.dt_date desc ";

		
		
		try {
			list = new ArrayList();
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);

			//System.out.println("query-----second datatable---------"+query);
			rs = ps.executeQuery();
			int i = 1;
			while (rs.next()) {
				ImflOldStockBwfl_DataTable dt = new ImflOldStockBwfl_DataTable();
				dt.setSno(i);
				dt.setBwflId(rs.getInt("bwfl_id"));
				dt.setVch_bwfl_name(rs.getString("vch_bwfl_name"));
				dt.setVch_bwfl_address(rs.getString("vch_bwfl_address"));
				dt.setVch_gatepass_no(rs.getString("vch_gatepass_no"));
				dt.setDt_date(rs.getDate("dt_date"));
				dt.setVch_from(rs.getString("vch_from"));
				dt.setVch_to(rs.getString("vch_to"));
				dt.setVch_from_lic_no(rs.getString("vch_from_lic_no"));
				dt.setVch_to_lic_no(rs.getString("vch_to_lic_no"));				
				dt.setVch_bond(rs.getString("vch_bond"));
				dt.setCurr_date(rs.getDate("curr_date"));
				dt.setInt_max_id(rs.getInt("int_max_id"));
				dt.setDb_total_duty(rs.getDouble("db_total_duty"));
				dt.setDb_total_additional_duty(rs.getDouble("db_total_additional_duty"));
				dt.setFindispatchedbox(rs.getInt("dispatchd_box"));
				dt.setFindispatchedbttl(rs.getInt("dispatchd_bottl"));
				dt.setGtinno(rs.getString("code_generate_through"));

				 if (rs.getString("vch_finalize").equalsIgnoreCase("F")) {
					dt.setFinalizePrint(true);
					dt.setPrintDraft(false);
				} else {
					dt.setFinalizePrint(false);
					dt.setPrintDraft(true);
				}
				list.add(dt);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	
	}
	*/
	public ArrayList getWholeSaleManufactureList(ImflOldStockFL36 act)
	{
		ArrayList list = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String finalz = "";

		String query = " SELECT  dt_date, vch_to, vch_to_lic_no,  curr_date, vch_gatepass_no, seq , vch_finalize  "
				+ " FROM bwfl_license.gatepass_to_districtwholesale_2017_18_bwfl_old_stock WHERE int_bwfl_id='"
				+ act.getBwflId() + "' order by seq  desc";

		System.out
				.println(" show data second datatable===============" + query);
		try {
			list = new ArrayList();
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			int i = 1;
			while (rs.next()) {
				ImflOldStockBwfl_DataTable dt = new ImflOldStockBwfl_DataTable();

				dt.setSno(i);
				dt.setDt_date(rs.getDate("dt_date"));
				dt.setVch_to(rs.getString("vch_to"));
				dt.setVch_gatepass_no(rs.getString("vch_gatepass_no"));
				dt.setVch_to_lic_no(rs.getString("vch_to_lic_no"));
				

				finalz = rs.getString("vch_finalize");

				if (finalz != null) { // (finalz.equalsIgnoreCase("F"))
					dt.setFinalizePrint(true);
					dt.setPrintDraft(false);
				} else {
					dt.setFinalizePrint(false);
					dt.setPrintDraft(true);
				}

				list.add(dt);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;

	}
	//===========================print draft report==============================
	
	
	public boolean printDraftReport(ImflOldStockFL36 action) {

		String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
		String relativePath = mypath + File.separator + "ExciseUp"
				+ File.separator + "WholeSale" + File.separator + "jasper";
		String relativePathpdf = mypath + File.separator + "ExciseUp"
				+ File.separator + "WholeSale" + File.separator + "pdf";
		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		String reportQuery = null;
		boolean printFlag1 = false;

		try {
			con = ConnectionToDataBase.getConnection();

			reportQuery =

					" SELECT b.dt_date,a.vch_firm_name, b.vch_gatepass_no,b.vehicle_driver_name,a.vch_firm_add, "
							+ " b.vch_to, b.vch_to_lic_no,b.curr_date, b.licencee_id, b.vch_route_detail, b.vch_vehicle_no, "
							+ " b.valid_till,b.vehicle_agency_name_adrs, b.licensee_name,b.licensee_adrs  ,c.dispatch_box ,"
							+ " b.valid_till, c.int_brand_id, c.size as box_size, c.dispatch_bottle as no_bottl,d.brand_name,"
							+ " e.package_name,e.box_id,f.qnt_ml_detail as ml,d.strength, "
							+ " c.vch_batch_no, b.gross_weight, b.tier_weight, b.net_weight,"
							+ " (((c.dispatch_bottle)*f.qnt_ml_detail)/1000) as bl,"
							+ " ((((c.dispatch_bottle*f.qnt_ml_detail)/1000)*42.8)/100) as al "
							
							+ " FROM bwfl.registration_of_bwfl_lic_holder a,bwfl_license.gatepass_to_districtwholesale_2017_18_bwfl_old_stock b,"
							+ " bwfl_license.fl2_stock_trxn_bwfl_old_stock c, distillery.brand_registration d,"
							+ " distillery.packaging_details e, distillery.box_size_details f "
							
							+ " WHERE a.int_id=b.int_bwfl_id AND b.int_bwfl_id=c.int_bwfl_id "
							+ " AND b.vch_gatepass_no=c.vch_gatepass_no and b.dt_date=c.dt "
							+ " AND c.int_brand_id=d.brand_id AND d.brand_id=e.brand_id_fk AND c.int_pckg_id=e.package_id "
							+ " AND e.box_id=f.box_id AND f.qnt_ml_detail=e.quantity AND "
							+ " b.int_bwfl_id='"
							+ action.getBwflId()
							+ "' AND b.vch_gatepass_no='"
							+ action.getPrintGatePassNo()
							+ "' ";

			pst = con.prepareStatement(reportQuery);

		
			rs = pst.executeQuery();
			if (rs.next()) {

				rs = pst.executeQuery();

				Map parameters = new HashMap();
				parameters.put("REPORT_CONNECTION", con);
				parameters.put("SUBREPORT_DIR", relativePath + File.separator);
				parameters.put("image", relativePath + File.separator);

				JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);

				jasperReport = (JasperReport) JRLoader.loadObject(relativePath
						+ File.separator
						+ "GatepassDistrictWholesaleBWFL_Draft_old_stock.jasper");

				JasperPrint print = JasperFillManager.fillReport(jasperReport,
						parameters, jrRs);
				Random rand = new Random();
				int n = rand.nextInt(250) + 1;

				JasperExportManager.exportReportToPdfFile(print,
						relativePathpdf + File.separator
								+ "GatepassDistrictWholesaleBWFL_Draft_old_stock"
								+ action.getBwflId() + n + ".pdf");

				ImflOldStockBwfl_DataTable dt = new ImflOldStockBwfl_DataTable();
				dt.setDraftePdfName("GatepassDistrictWholesaleBWFL_Draft_old_stock"
						+ action.getBwflId() + n + ".pdf");

				action.setDraftpdfname("GatepassDistrictWholesaleBWFL_Draft_old_stock"
						+ action.getBwflId() + n + ".pdf");

				dt.setDraftprintFlag(true);
				printFlag1 = true;

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("No Data Found", "No Data Found"));
				ImflOldStockBwfl_DataTable dt1 = new ImflOldStockBwfl_DataTable();

				printFlag1 = false;
				dt1.setDraftprintFlag(false);

			}

		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return printFlag1;

	}
	
	
	//==========================print report===========================================
	
	
	public boolean printReport(ImflOldStockFL36 action) {

		String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
		String relativePath = mypath + File.separator + "ExciseUp"
				+ File.separator + "WholeSale" + File.separator + "jasper";
		String relativePathpdf = mypath + File.separator + "ExciseUp"
				+ File.separator + "WholeSale" + File.separator + "pdf";
		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		String reportQuery = null;
		boolean printFlag = false;

		try {
			con = ConnectionToDataBase.getConnection();

			reportQuery = " SELECT b.dt_date,a.vch_firm_name, b.vch_gatepass_no,b.vehicle_driver_name,a.vch_firm_add, "
					+ " b.vch_to, b.vch_to_lic_no,b.curr_date, b.licencee_id, b.vch_route_detail, b.vch_vehicle_no, "
					+ " b.valid_till,b.vehicle_agency_name_adrs, b.licensee_name,b.licensee_adrs  ,c.dispatch_box ,"
					+ " b.valid_till, c.int_brand_id, c.size as box_size, c.dispatch_bottle as no_bottl,d.brand_name,"
					+ " e.package_name,e.box_id,f.qnt_ml_detail as ml,d.strength, "
					+ " c.vch_batch_no, b.gross_weight, b.tier_weight, b.net_weight,"
					+ " (((c.dispatch_bottle)*f.qnt_ml_detail)/1000) as bl,"
					+ " ((((c.dispatch_bottle*f.qnt_ml_detail)/1000)*42.8)/100) as al "
					
					+ " FROM bwfl.registration_of_bwfl_lic_holder a," 
					+ " bwfl_license.gatepass_to_districtwholesale_2017_18_bwfl_old_stock b,"
					+ " bwfl_license.fl2_stock_trxn_bwfl_old_stock c, " 
					+ " distillery.brand_registration d,"
					+ " distillery.packaging_details e, " 
					+ " distillery.box_size_details f "
					
					+ " WHERE a.int_id=b.int_bwfl_id AND b.int_bwfl_id=c.int_bwfl_id "
					+ " AND b.vch_gatepass_no=c.vch_gatepass_no and b.dt_date=c.dt "
					+ " AND c.int_brand_id=d.brand_id AND d.brand_id=e.brand_id_fk AND c.int_pckg_id=e.package_id "
					+ " AND e.box_id=f.box_id AND f.qnt_ml_detail=e.quantity AND "
					+ " b.int_bwfl_id='"
					+ action.getBwflId()
					+ "' AND b.vch_gatepass_no='"
					+ action.getPrintGatePassNo()
					+ "' ";

			// System.out.println("reportQuery---------------------"+reportQuery);
			pst = con.prepareStatement(reportQuery);
			rs = pst.executeQuery();

			if (rs.next()) {
				rs = pst.executeQuery();

				Map parameters = new HashMap();
				parameters.put("REPORT_CONNECTION", con);
				// parameters.put("SUBREPORT_DIR", relativePath+File.separator);
				parameters.put("image", relativePath + File.separator);

				JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);

				jasperReport = (JasperReport) JRLoader.loadObject(relativePath+ File.separator+ "GatepassDistrictWholesaleBWF_old_stock.jasper");

				JasperPrint print = JasperFillManager.fillReport(jasperReport,parameters, jrRs);
				Random rand = new Random();
				int n = rand.nextInt(250) + 1;

				JasperExportManager.exportReportToPdfFile(print,relativePathpdf + File.separator+ "GatepassDistrictWholesaleBWF_old_stock"+ action.getPrintGatePassNo() + "-" + n+ ".pdf");

				ImflOldStockBwfl_DataTable dt = new ImflOldStockBwfl_DataTable();
				dt.setPdfName("GatepassDistrictWholesaleBWF_old_stock"+ action.getPrintGatePassNo() + "-" + n + ".pdf");
				action.setPdfName("GatepassDistrictWholesaleBWF_old_stock"+ action.getPrintGatePassNo() + "-" + n + ".pdf");
				dt.setPrintFlag(true);
				printFlag = true;
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("No Data Found", "No Data Found"));
				ImflOldStockBwfl_DataTable dt1 = new ImflOldStockBwfl_DataTable();
				printFlag = false;
				dt1.setPrintFlag(false);
			}
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return printFlag;
	}

	

	
	//=========================get etin fro same brand======================================
	
	
	
	public boolean  etin(String casecode,ImflOldStockFL36 act) {
		 
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		boolean s = false;
		try {
			con = ConnectionToDataBase.getConnection();
 
		
			String query =	" SELECT distinct b.code_generate_through FROM distillery.fl2_stock_trxn a," +
							" distillery.packaging_details b "+
							" WHERE b.package_id = a.int_pckg_id  and a.vch_gatepass_no='"+ act.getScanGatePassNo().trim()+ "' " +
							" and b.code_generate_through='"+casecode+"'";

			pstmt = con.prepareStatement(query);
			 
			rs = pstmt.executeQuery();
			if (rs.next()) 
			{
			 
			 
			}else{
				
				s=true;
			}

			 

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (ps2 != null)
					ps2.close();
				if (rs != null)
					rs.close();
				if (rs2 != null)
					rs2.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return s;

	}
	
	
	
	//==============================get casecode for matching======================
	
	
	public boolean getCascodeMatch(String casecode,String etin ,ImflOldStockFL36 act)
	{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean flag=false;
		String sql="";
		
		 	sql = "select * from bottling_unmapped.bwfl WHERE casecode=? and etin=? and fl36_date is null ";
			 
		
	try{
		conn=ConnectionToDataBase.getConnection3();
		pstmt=conn.prepareStatement(sql);
		 pstmt.setString(1,String.valueOf((Long.parseLong(casecode.trim()))));
		pstmt.setString(2,String.valueOf(Long.parseLong( etin.trim())));
		 
		rs=pstmt.executeQuery();
		if(rs.next())
		{
			flag=true;
		}
		
	}
	catch(Exception e)
	{
	e.printStackTrace();	
	}
	finally{
		try{
		if(rs!=null)rs.close();
		if(pstmt!=null)pstmt.close();
		if(conn!=null)conn.close();
		
		
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	return flag;
	}
	
	
	//===========================get excel data=============================
	
	
	
	/*public ArrayList getExcelData(ImflOldStockFL36 act)
	{

		ArrayList list = new ArrayList();

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
        int boxingCount=0;
        int listSize=0;
        
        
		String query = 	" SELECT vch_gatepass_no,vch_casecode " +
						" FROM distillery.fl11_dislery_casecode_imfl_old_stock " +
						" WHERE vch_gatepass_no='"+ act.getScanGatePassNo().trim() + "' ";

		try {
			con = ConnectionToDataBase.getConnection();
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				ImflOldStockBwfl_DataTable dt = new ImflOldStockBwfl_DataTable();

				act.setValFlag(true);
				dt.setGatepass(rs.getString("vch_gatepass_no"));
				dt.setCasecode(rs.getString("vch_casecode"));
				
				boolean flag=getCascodeMatch(rs.getString("vch_casecode").substring(29,
						rs.getString("vch_casecode").length()),rs.getString("vch_casecode").substring(2, 15),act);
				if(flag)
				{
					++listSize;
					//System.out.println("listSize"+listSize);
		        dt.setCascodeMatching("Casecode Accepted");
				}else{
					
					//System.out.println("boxingCount"+boxingCount);
					++boxingCount;
					dt.setCascodeMatching("Casecode Rejected");
				}


				list.add(dt);

			}
			
			//System.out.println("boxingCount"+boxingCount+"     listSize"+listSize);
			if(boxingCount!=0 || listSize<=0 )
			{
				//System.out.println("flag checkkkkkkkkkkkkkkk"+listSize);
				act.setBottlingNotDoneFlag(true);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	
	}
	*/
	
	//=========================select number of cases in excel================
	
	
	
	public int csvCases(ImflOldStockFL36 act)
	{

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			String query = 	" SELECT count(*) as dispatchd_box " +
							" FROM bwfl_license.bwfl_dispatch_casecode  " +
							" WHERE vch_gatepass_no='"+act.getScanGatePassNo().trim()+"'";

			pstmt = con.prepareStatement(query);
			
			//System.out.println("query-----excelCases------"+query);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				id = (rs.getInt("dispatchd_box"));

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (ps2 != null)
					ps2.close();
				if (rs != null)
					rs.close();
				if (rs2 != null)
					rs2.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return id;
		

	
	}
	
	
	//=========================select number of cases in detail table================

		public int recieveCases(ImflOldStockFL36 act)
		{

			int id = 0;
			Connection con = null;
			PreparedStatement pstmt = null, ps2 = null;
			ResultSet rs = null, rs2 = null;
			String s = "";
			try {
				con = ConnectionToDataBase.getConnection();

				String query = 	" SELECT SUM(dispatch_box) as dispatchd_box " +
								" FROM bwfl_license.fl2_stock_trxn_bwfl_old_stock " +
								" WHERE vch_gatepass_no='"+ act.getScanGatePassNo().trim() + "'  ";

				pstmt = con.prepareStatement(query);
				//System.out.println("query-----recieveCases------"+query);
				
				rs = pstmt.executeQuery();

				while (rs.next()) {

					id = (rs.getInt("dispatchd_box"));

				}

			} catch (SQLException se) {
				se.printStackTrace();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (ps2 != null)
						ps2.close();
					if (rs != null)
						rs.close();
					if (rs2 != null)
						rs2.close();
					if (con != null)
						con.close();

				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			return id;
			

		}
		
		
	//===========================update on final submit button=================
		
		
		public boolean updateFL3(ImflOldStockFL36 act)
		{
			
			int save = 0;
			int j[] = null;
			boolean val = false;
			PreparedStatement ps = null;
			Connection con = null;
			Connection con1 = null;
			String query = "";
			String query11 = "  UPDATE bottling_unmapped.bwfl SET fl36gatepass= ? , fl36_date= ? " +
				 		     "  WHERE casecode=? and fl11_date is not null  ";

			try {
				con = ConnectionToDataBase.getConnection();
				con1 = ConnectionToDataBase.getConnection3();
				con.setAutoCommit(false);
				con1.setAutoCommit(false);
				ps = con1.prepareStatement(query11);
				for (int i = 0; i < act.getGetVal().size(); i++) {
					ImflOldStockBwfl_DataTable dt = (ImflOldStockBwfl_DataTable) act.getGetVal().get(i);
					    ps.setString(1,dt.getGatepass().trim());
					    ps.setDate(2, Utility.convertUtilDateToSQLDate(new Date()));
					    ps.setString(3,dt.getCasecode().trim().substring(29,dt.getCasecode().trim().length()));
					
					ps.addBatch();
										
					System.out.println("come for finalizeee444444");

				} 
				j = ps.executeBatch();
				 
				save = j.length;
				System.out.println("come for finalizeee5555 save"+save);
				if (act.getGetVal().size() == save && save>0) {
					save = 0;
					String updtQr = " UPDATE bwfl_license.gatepass_to_districtwholesale_2017_18_bwfl_old_stock " +
									" SET vch_finalize='F' " +
									" WHERE vch_gatepass_no='"+ act.getScanGatePassNo().trim()+ "' ";

					ps = con.prepareStatement(updtQr);
					save = ps.executeUpdate();
					
					query = " DELETE FROM bwfl_license.bwfl_dispatch_casecode " +
							" WHERE vch_gatepass_no ='"+act.getScanGatePassNo().trim()+"' ";
					
					ps =con.prepareStatement(query);
					ps.executeUpdate();
					
					System.out.println("come for finalizeee6666 ");
				} else {
					save = 0;
					System.out.println("come for finalizeee777 save"+save);
				}
				if (save > 0) {
					val = true;
					System.out.println("come for finalizeee88888 save"+save);
					con.commit();
					con1.commit();
				} else {
					System.out.println("come for finalizeee999 ");
					val = false;
					con.rollback();
					con1.rollback();
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (ps != null)
						ps.close();

					if (con != null)
						con.close();
					con1.close();
				} catch (Exception ex1) {
					ex1.printStackTrace();
				}
			}
			return val;

		
		}
		
		
	//=======================delete excel data from temporary table=========================
	
	
	public void deleteData(ImflOldStockFL36 act)
	{
	
		Connection con = null;
		PreparedStatement stmt = null;
	
		String query = 	" DELETE FROM bwfl_license.bwfl_dispatch_casecode " +
						" WHERE vch_gatepass_no='"+ act.getScanGatePassNo().trim()+"'  ";
		int status = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			stmt = con.prepareStatement(query);
	
			status = stmt.executeUpdate();
			if (status > 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Cancelled Successfully ","Cancelled Successfully "));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Not Cancelled", "Not Cancelled"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	
	}
	
	
	public void saveCSV(ImflOldStockFL36 action) throws IOException 
	{
		System.out.println("reached");
		Connection con = null;
		PreparedStatement stmt = null;
		
		
		String query = " INSERT INTO bwfl_license.bwfl_dispatch_casecode(vch_gatepass_no, vch_casecode)VALUES (?, ?)";

		String gatepass = "";
		int status = 0, flag = 0;
		BufferedReader bReader = new BufferedReader(new FileReader(action.getCsvFilepath()));
		
		try {
			con = ConnectionToDataBase.getConnection();
			stmt = con.prepareStatement(query);
			

			String line = "";
			StringTokenizer st = null;
			int lineNumber = 0;
			int tokenNumber = 0;
			while ((line = bReader.readLine()) != null) {
				lineNumber++;
				String casecode = "";
				st = new StringTokenizer(line, " ");
				while (st.hasMoreTokens()) {
					String sd = st.nextToken() + "  ";

					if (sd != null) {
						if (lineNumber == 1) {							
							gatepass = sd.trim();
						}

						else// line number >1
						{
							
							if (gatepass.equalsIgnoreCase(action.getScanGatePassNo().trim())) 
							{								
								casecode = sd;
								System.out.println("enter in gatepass");
								/*if(this.etin(casecode.trim().substring(2, 15), action)==true)
								{
									FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
											" Casecode-"+casecode.trim()+" does not belongs to the brands for the selected gatepass!" ," Casecode-"+casecode.trim()+" does not belongs to the brands for the selected gatepass!"));	
								}
								else
								{ 
								*/
								stmt.setString(1, gatepass);
								stmt.setString(2, casecode);
								stmt.addBatch();
								//}
							 
							} 
							else 
							{
								flag = 1;
							}
						}

					}

					tokenNumber = 0;
				}
			}
					
			if (flag == 0) 
			{
				
				status=stmt.executeBatch().length;
				if (status > 0) 
				{
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"File Uploaded Successfully ","File Uploaded Successfully "));
				} 
				else 
				{
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"File Not Uploaded!!", "File Not Uploaded!!"));
				}
			} 
			else 
			{
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Kindly Enter Same Gatepass Number !! ","Kindly Enter Same Gatepass Number !! "));
			}

		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (stmt != null)
					stmt.close();

				if (con != null)
					con.close();

			} catch (Exception ex) 
			{
				ex.printStackTrace();
			}
		}

		

	}	
	public ArrayList getCsvData(ImflOldStockFL36 act)
	{
		
		ArrayList list = new ArrayList();

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
        int boxingCount=0;
        int listSize=0;
        
        
		String query = 	" SELECT vch_gatepass_no, vch_casecode FROM bwfl_license.bwfl_dispatch_casecode "+
						" WHERE vch_gatepass_no='"+act.getScanGatePassNo().trim()+"' ";

		try {
			con = ConnectionToDataBase.getConnection();
			stmt = con.prepareStatement(query);
		 
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				ImflOldStockBwfl_DataTable dt = new ImflOldStockBwfl_DataTable();

				act.setValFlag(true);
				dt.setGatepass(rs.getString("vch_gatepass_no"));
				dt.setCasecode(rs.getString("vch_casecode"));
				
				boolean flag=getCascodeMatch(rs.getString("vch_casecode").substring(29,
						rs.getString("vch_casecode").length()),rs.getString("vch_casecode").substring(2, 15),act);
				if(flag)
				{
					++listSize;
					
		        dt.setCascodeMatching("Casecode Accepted");
				}
				else
				{
					++boxingCount;
					dt.setCascodeMatching("Casecode Rejected");
				}
				list.add(dt);
			}
		 
		if(boxingCount!=0 || listSize<=0 )
			{ 
			
				act.setBottlingNotDoneFlag(true);
			}else{
				act.setBottlingNotDoneFlag(false);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	
	}
	
	public ArrayList getlicenseNmbr(ImflOldStockFL36 act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("");
		list.add(item);
		try {
			String query = " SELECT vch_licence_no, vch_license_type "
					+ " FROM licence.fl2_2b_2d WHERE vch_license_type='FL2' ORDER BY vch_licence_no ";

			String query1 = " SELECT vch_licence_no, vch_license_type "
					+ " FROM licence.fl2_2b_2d WHERE vch_license_type='FL2B' ORDER BY vch_licence_no ";

			String query2 = " SELECT vch_licence_no, vch_license_type "
					+ " FROM licence.fl2_2b_2d WHERE vch_license_type in ('FL2B','FL2') ORDER BY vch_licence_no ";

			conn = ConnectionToDataBase.getConnection();

			if (act.getLicenseTypeId() == 1
					|| act.getLicenseTypeId() == 3) {
				ps = conn.prepareStatement(query);
				

			} else if (act.getLicenseTypeId() == 2) {
				ps = conn.prepareStatement(query1);
				
			} else if (act.getLicenseTypeId() == 4) {
				ps = conn.prepareStatement(query1);
				

			}

			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();

				item.setValue(rs.getString("vch_licence_no"));
				item.setLabel(rs.getString("vch_licence_no"));

				list.add(item);

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

	// ========================get bar restaurant and club restaurant
	// number==========================

	public ArrayList getbrcLicenseNo(ImflOldStockFL36 act) 
	{
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("");
		list.add(item);
		try {

			String query = " SELECT license_number, finalize "
					+ " FROM hotel_bar_rest.registration_for_hotels_bars_restraunt "
					+ " WHERE finalize IS NOT NULL ORDER BY license_number ";

			conn = ConnectionToDataBase.getConnection();

			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();

				item.setValue(rs.getString("license_number"));
				item.setLabel(rs.getString("license_number"));

				list.add(item);

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


	public ArrayList getDistrictList(ImflOldStockFL36 act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue(0);
		list.add(item);
		try {
			String query = " SELECT districtid, description FROM public.district ORDER BY description ";

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				item = new SelectItem();

				item.setValue(rs.getString("districtid"));
				item.setLabel(rs.getString("description"));

				list.add(item);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		return list;

	}

	public String getlicenseeDetail(ImflOldStockFL36 act, String val) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		
		try {
			con = ConnectionToDataBase.getConnection();

			String selQr = " SELECT a.license_id, a.license_number, a.name_of_license, "
						 + " a.address, b.description, b.districtid "
				        	+ " FROM hotel_bar_rest.registration_for_hotels_bars_restraunt a, public.district b "
				        	+ " WHERE a.district=b.districtid::text  AND license_number='"
							+ val + "' ";
			 System.out.println("licensee-------no-----"+val+"====="+selQr);
			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();

			while (rs.next()) 
			{
				
				act.setLicenseeid(rs.getInt("license_id"));
				act.setLicenseeName(rs.getString("name_of_license"));
				act.setDistrictId(rs.getInt("districtid"));
				act.setLicenseeDistrict(rs.getString("description"));
				if (rs.getString("address") != null) 
				{
					act.setLicenseeAdrs(rs.getString("address"));
				}

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (ps2 != null)
					ps2.close();
				if (rs != null)
					rs.close();
				if (rs2 != null)
					rs2.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return "";

	}



	public String getwareLicenseeDetail(ImflOldStockFL36 act,
			String val) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;

		try {
			con = ConnectionToDataBase.getConnection();

			String selQr = " SELECT a.int_app_id, a.vch_applicant_name, a.vch_core_address, "
					+ " a.vch_licence_no, b.description, b.districtid "
					+ " FROM licence.fl2_2b_2d a, public.district b "
					+ " WHERE a.core_district_id=b.districtid AND vch_licence_no='"
					+ val + "' AND vch_license_type=? ";

			pstmt = con.prepareStatement(selQr);

			 System.out.println("selQr-----license -------"+val+"===="+selQr);
			// System.out.println("act.getBwflLicenseTypeId()------------"+act.getBwflLicenseTypeId());

			if (act.getLicenseTypeId() == 1
					|| act.getLicenseTypeId() == 3) {
				pstmt.setString(1, "FL2");
			} else if (act.getLicenseTypeId() == 2
					|| act.getLicenseTypeId() == 4) {
				pstmt.setString(1, "FL2B");
			}
			/*
			 * else if(act.getBwflLicenseTypeId() == 4) { pstmt.setString(1,
			 * "FL2B"); }
			 */
			rs = pstmt.executeQuery();

			while (rs.next()) {

				act.setLicenseeid(rs.getInt("int_app_id"));
				act.setLicenseeName(rs.getString("vch_applicant_name"));
				act.setDistrictId(rs.getInt("districtid"));
				act.setLicenseeDistrict(rs.getString("description"));
				if (rs.getString("vch_core_address") != null) {
					act.setLicenseeAdrs(rs.getString("vch_core_address"));
				}

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (ps2 != null)
					ps2.close();
				if (rs != null)
					rs.close();
				if (rs2 != null)
					rs2.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return "";

	}
	
}

