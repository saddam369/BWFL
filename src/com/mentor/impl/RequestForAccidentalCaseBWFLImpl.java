package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.mentor.action.RequestForAccidentalCaseBWFLAction;
import com.mentor.connectiondb.ConnectionToDataBase;
import com.mentor.datatable.RequestForAccidentalCaseBWFLDt;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class RequestForAccidentalCaseBWFLImpl {

	// ---------------------------------------------------------

	public String getDetails(RequestForAccidentalCaseBWFLAction act) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			String selQr = " SELECT mobile_number, vch_firm_name, vch_firm_add, int_id, vch_license_type "
					+ " FROM bwfl.registration_of_bwfl_lic_holder_20_21 "
					+ " WHERE  mobile_number='"
					+ ResourceUtil.getUserNameAllReq().trim()
					+ "' AND vch_approval='V' ";

			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				act.setBwflId(rs.getInt("int_id"));
				act.setBwflName(rs.getString("vch_firm_name"));
				act.setBwflAdrs(rs.getString("vch_firm_add"));
				act.setBwflLicenseTypeId(rs.getInt("vch_license_type"));
				/*
				 * if (rs.getString("vch_license_type").equals("1")) {
				 * act.setVch_from("BWFL2A"); } else if
				 * (rs.getString("vch_license_type").equals("2")) {
				 * act.setVch_from("BWFL2B"); } else if
				 * (rs.getString("vch_license_type").equals("3")) {
				 * act.setVch_from("BWFL2C"); } else if
				 * (rs.getString("vch_license_type").equals("4")) {
				 * act.setVch_from("BWFL2D"); }
				 */
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

	// ---------------------------

	public ArrayList getDistrictList(RequestForAccidentalCaseBWFLAction act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("");
		list.add(item);
		try {

			String query = "select description , districtid from public.district order by description";

			System.out.println(" --getdistrict_list--- " + query);

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

	public boolean SaveImpl(RequestForAccidentalCaseBWFLAction act) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 1;
		String query = "";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());

		boolean flg = false;
		try {

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);

			query = " INSERT INTO bwfl_license.request_for_accidental_case "
					+ " (req_id, gatepass_type, gatepass_no, gatepass_date, accident_address, accident_district, accident_date,"
					+ " bwfl_id, created_date) "
					+ " VALUES((SELECT coalesce(max(req_id),0)+1 from bwfl_license.request_for_accidental_case), ?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, act.getGatepass_type());
			pstmt.setString(2, act.getGatepass_no());
			pstmt.setDate(3,
					Utility.convertUtilDateToSQLDate(act.getGatepass_date()));
			pstmt.setString(4, act.getAccident_address());
			pstmt.setInt(5, Integer.parseInt(act.getAccident_district_id()));
			pstmt.setDate(6,
					Utility.convertUtilDateToSQLDate(act.getAccident_date()));
			pstmt.setInt(7, act.getBwflId());
			pstmt.setDate(8, Utility.convertUtilDateToSQLDate(new Date()));
			// pstmt.setString(9, act.getVch_type());
			// /pstmt.setString(10, act.getVch_licNo());

			saveStatus = pstmt.executeUpdate();

			System.out.println("====save status= pi details=1" + saveStatus);

			if (saveStatus > 0) {
				conn.setAutoCommit(true);
				flg = true;
				act.reset();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("SAVED SUCCESSFULLY !",
								"SAVED SUCCESSFULLY !"));
				act.reset();
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("SOMETHING WENT WRONG !",
								"SOMETHING WENT WRONG !"));
				conn.rollback();

			}
		}

		catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		} catch (Exception e) {
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

	// ////----------------------------------------------------display----------------------------------------

	public String displayImpl(RequestForAccidentalCaseBWFLAction act) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String query = " select vch_from ,vch_to_lic_no,* from bwfl_license.gatepass_to_districtwholesale_bwfl_20_21 "
					+ "where  vch_gatepass_no='" + act.getGatepass_no() + "' ";

			System.out.println("======details====" + query);
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				act.setVch_type(rs.getString("vch_from"));
				act.setVch_licNo(rs.getString("vch_to_lic_no"));

			} else {
				act.setVch_type("");
				act.setVch_licNo("");
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
		return "";

	}

	public ArrayList display_detail(RequestForAccidentalCaseBWFLAction act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int sr = 1;
		;

		try {

			String query =

			" SELECT req_id, gatepass_type, gatepass_no, gatepass_date, accident_address, accident_district, accident_date, created_date, "
					+ " (select description from public.district where districtid=accident_district) as dist_name FROM bwfl_license.request_for_accidental_case where bwfl_id ="
					+ act.getBwflId() + "";

			System.out.println("-- brand details ====" + query);
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				RequestForAccidentalCaseBWFLDt dt = new RequestForAccidentalCaseBWFLDt();
				dt.setReq_id(rs.getInt("req_id"));

				dt.setSrno(sr);
				dt.setGatepass_type(rs.getString("gatepass_type"));
				dt.setGatepass_no(rs.getString("gatepass_no"));
				dt.setGatepass_date(rs.getDate("gatepass_date"));
				dt.setAccident_address(rs.getString("accident_address"));
				dt.setAccident_district_name(rs.getString("dist_name"));
				dt.setAccident_date(rs.getDate("accident_date"));
				dt.setReq_dt(rs.getDate("created_date"));
				sr++;

				list.add(dt);

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

}
