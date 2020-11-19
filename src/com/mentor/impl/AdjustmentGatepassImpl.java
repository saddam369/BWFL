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

import com.mentor.action.AdjustmentGatepassAction;
import com.mentor.action.GatepassToDistrictWholesale_FL2DAction;
import com.mentor.connectiondb.ConnectionToDataBase;
import com.mentor.datatable.AdjustmentGatepassDT;
import com.mentor.datatable.GatepassToDistrictWholesale_FL2DDataTable;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class AdjustmentGatepassImpl {

	public String getDetails(AdjustmentGatepassAction ac) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			String queryList = " select  int_app_id, vch_licence_no,vch_firm_name  from licence.fl2_2b_2d where  vch_license_type='FL2D' and vch_mobile_no = '"
					+ ResourceUtil.getUserNameAllReq().trim() + "'  ";

			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ac.setName(rs.getString("vch_firm_name"));
				ac.setDist_id(rs.getInt("int_app_id"));
				// ac.setAddress(rs.getString("vch_firm_add"));
				ac.setVch_licence_no(rs.getString("vch_licence_no"));

				ac.setVch_from(rs.getString("vch_firm_name"));

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

	public ArrayList displaylistImpl(AdjustmentGatepassAction act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		act.setFlag("F");
		

		try {

			selQr =  "SELECT distinct c.seq,c.plan_dt,c.permit_no, a.package_name, a.package_id,a.permit, a.duty, a.adduty, c.int_brand_id, b.brand_name, "
					+ "	 c.int_pack_id, c.int_planned_bottles-coalesce(c.dispatch_36,0)  as avlbottle, "
					+ "    ((c.int_planned_bottles-coalesce(c.dispatch_36,0))/a.export_box_size) as dispatchd_box, a.export_box_size   "
					+ "	 FROM distillery.packaging_details a, distillery.brand_registration b,  "
					+ "	 fl2d.mst_stock_receive c  "
					+ "	 WHERE a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.int_brand_id  "
					+ "	 AND a.package_id=c.int_pack_id AND b.brand_id=c.int_brand_id  "
					+ "	 AND c.int_fl2d_id='"
					+ act.getDist_id()
					+ "'  "
					+ "	 AND c.vch_license_type='FL2D' "
					+ "    AND COALESCE(c.dispatch_36,0)<c.int_planned_bottles  "
					+ "	 and c.update_flag is null order by c.plan_dt desc ";

			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(selQr);
			

			rs = ps.executeQuery();
			while (rs.next()) {
				AdjustmentGatepassDT dt = new AdjustmentGatepassDT();
				dt.setInt_brand_id(rs.getInt("int_brand_id"));
				dt.setInt_pckg_id(rs.getInt("package_id"));
				dt.setVch_brand(rs.getString("brand_name"));
				dt.setInt_bottle_avaliable(rs.getInt("avlbottle"));
				dt.setDesc(rs.getString("package_name"));
				dt.setSeq(rs.getInt("seq"));
				// dt.setSize(rs.getInt("box_size"));
				dt.setSize(rs.getInt("export_box_size"));

				dt.setBoxAvailable(rs.getInt("dispatchd_box"));

				// dt.setDb_duty(rs.getDouble("duty"));
				dt.setDb_duty(rs.getDouble("permit"));

				dt.setDb_add_duty(rs.getDouble("adduty"));
				dt.setPermit(rs.getString("permit_no"));
				dt.setPlan_dt(rs.getDate("plan_dt"));

				list.add(dt);

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
	
	public ArrayList getupdatelist(AdjustmentGatepassAction action) {
		ArrayList list = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = " SELECT a.export_box_size, b.brand_name, packg_id, fl2d_id, size, permit_no, c.brand_id, act_box, act_bottle, cr_date, br_bottle, plan_dt" +
				"	FROM distillery.packaging_details a, distillery.brand_registration b, fl2d.adjustment_offline_fl2d c" +
				" where fl2d_id='"+action.getDist_id()+"' and a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.brand_id" +
						" AND a.package_id=c.packg_id AND b.brand_id=c.brand_id ";
		
			
		try {
			list = new ArrayList();
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			int i = 1;
			while (rs.next()) {
				AdjustmentGatepassDT dt = new AdjustmentGatepassDT();
				dt.setSno(i);
				dt.setUpbottle(rs.getInt("act_bottle"));
				dt.setUpbox(rs.getInt("act_box"));
				dt.setUpdate(rs.getDate("cr_date"));
				dt.setUpsize(rs.getInt("export_box_size"));
				dt.setBrandname(rs.getString("brand_name"));
				dt.setBrbottle(rs.getInt("br_bottle"));
				dt.setUppermit(rs.getString("permit_no"));

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

	public String update(AdjustmentGatepassAction act,int brandid,int packid ,
			int DispatcBotls, int size ,String permitno,int DispatcBox,int br_bottle,Date plan_dt, int seq) {

		Connection con = null;
		PreparedStatement ps = null, ps2 = null, ps3 = null, ps4 = null, ps5 = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		ResultSet rs1 = null;
		String sql = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		int tok1 = 0;
		int tok2 = 0;

		

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);

			String gatepass = "";
			

			
			String updtQr = " UPDATE fl2d.mst_stock_receive SET "+
			 		" dispatch_36=COALESCE(int_planned_bottles,0)-'"+ DispatcBotls+ "'  "+
			 		" , update_flag='F' WHERE int_fl2d_id='"+ act.getDist_id()+ "' AND "+
			 		" int_brand_id='"+ brandid+ "' AND " +
			 		" int_pack_id='"+ packid+ "' and plan_dt='"+plan_dt+"' and seq='"+seq+"'";

	
	ps5 = con.prepareStatement(updtQr);
	
	tok1 = ps5.executeUpdate();
	
	if (tok1 > 0) {

		tok1 = 0;
		sql = 	" INSERT INTO fl2d.adjustment_offline_fl2d(" +
				"	seq, packg_id, fl2d_id, size, permit_no, brand_id, act_box, act_bottle, cr_date,br_bottle,plan_dt)" +
				"	VALUES ((select nextval('seq')), '"+ packid+ "', '"+ act.dist_id+ "', '"+ size+ "'," +
				" '"+ permitno+ "', '"+ brandid+ "', '"+ DispatcBox+ "', '"+ DispatcBotls+ "'," +
						" '"+ Utility.convertUtilDateToSQLDate(act.getNewdate())+ "','"+ br_bottle+ "','"+ Utility.convertUtilDateToSQLDate(plan_dt)+ "') ";

		ps = con.prepareStatement(sql);
		
		tok2 = ps.executeUpdate();

		

	

		if (tok2 > 0) {
				con.setAutoCommit(true);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Data Update Successfully !!! ",
								"Data Update Successfully !!!"));
				act.setFlag("T");
			//	act.clearAll();
			}

			else {
				con.rollback();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Data Update Not Successfully !!! ",
								"Data Update Not Successfully !!!"));

			}}

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

			} catch (SQLException ex) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(ex.getMessage(), ex.getMessage()));
				ex.printStackTrace();
			}
		}

		return "";

	}
	
	
}
