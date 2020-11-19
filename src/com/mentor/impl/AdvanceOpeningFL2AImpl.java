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
import com.mentor.action.AdvanceOpeningFL2AAction;
import com.mentor.datatable.AdvanceOpeningFL2ADt;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class AdvanceOpeningFL2AImpl {
	public ArrayList getdisUnitList(AdvanceOpeningFL2AAction act) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue(0);
		list.add(item);

		if (act.getRadio().equalsIgnoreCase("DEPO")) {
			sql = " select int_app_id, vch_applicant_name from licence.fl2_2b_2d_fl2a_csd where  "
					+ " core_district_id=(SELECT districtid FROM district where deo='"
					+ ResourceUtil.getUserNameAllReq().trim() + "') ";

		}

		else if (act.getRadio().equalsIgnoreCase("UNIT")) {
			sql = " SELECT int_app_id_f as int_app_id,vch_indus_name as vch_applicant_name           "
					+ " FROM public.other_unit_registration_20_21  where  login_id like '%CSD%'         ";

		}

		// "select int_app_id, vch_applicant_name from licence.fl2_2b_2d_fl2a_csd where core_district_id=(SELECT districtid FROM district where deo='"+ResourceUtil.getUserNameAllReq().trim()+"')";
		try {
			// System.out.println("dis========================" + sql);
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("vch_applicant_name"));
				item.setValue(rs.getString("int_app_id"));
				list.add(item);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), e.getMessage()));
			e.printStackTrace();
		} finally {
			try {

				if (con != null)
					con.close();
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

	// /----------------------------------------------------------------------------------------------------

	public String loginmethod1(AdvanceOpeningFL2AAction act) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String query = " SELECT int_app_id_f as int_app_id,vch_indus_name as vch_applicant_name ,login_id          "
					+ " FROM public.other_unit_registration_20_21  where  login_id like '%CSD%'         ";

			// System.out.println("======loginmethod1====" + query);
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				act.setLogin_id(rs.getString("login_id"));
				// act.setDepo_nm(rs.getString("vch_applicant_name"));
			}
		}

		catch (SQLException se) {
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

	public String Districtmethod1(AdvanceOpeningFL2AAction act) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String query = " select int_app_id, vch_applicant_name,core_district_id as login_id from licence.fl2_2b_2d_fl2a_csd where  "
					+ " core_district_id=(SELECT districtid FROM district where deo='"
					+ ResourceUtil.getUserNameAllReq().trim() + "') ";

			// System.out.println("======loginmethod1====" + query);
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				act.setDistict_id(rs.getInt("login_id"));
				act.setDepo_nm(rs.getString("vch_applicant_name"));
			}
		}

		catch (SQLException se) {
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

	// --------------------------------------------------------------------------------------------------------
	public String getlogonMethod(AdvanceOpeningFL2AAction ac, int unit_id) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList = " select int_app_id, vch_applicant_name,core_district_id::text as login_id from licence.fl2_2b_2d_fl2a_csd where  "
					+ " core_district_id=(SELECT districtid FROM district where deo='"
					+ ResourceUtil.getUserNameAllReq().trim()
					+ "') "
					+ " union                                                                                "
					+ " SELECT int_app_id_f as int_app_id,vch_indus_name as vch_applicant_name  ,login_id         "
					+ " FROM public.other_unit_registration_20_21  where  login_id  like '%CSD%'              ";

			// /System.out.println("getlogonMethod===============" + queryList);
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				ac.setLogin_id(rs.getString("login_id"));
			}

		} catch (SQLException se) {
			// se.printStackTrace();
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

	// -------------------------------------------------------------------------------------

	public boolean checksavedetail(AdvanceOpeningFL2AAction act) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean pass = false;
		String query = "";

		try {

			query = " SELECT int_id, unit_id, unit_type, fee_type, open_date, amount, login_id, deo, district_id, depo_nm "
					+ "	FROM fl2d.advance_opening_fl2a where  unit_id='"
					+ act.getUnit_id()
					+ "' and unit_type='"
					+ act.getRadio()
					+ "'  and fee_type='" + act.getUnit_type() + "'  ";
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			// System.out.println("=checksavedetail=="+query);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				// act.setCheckReg(rs.getInt("registration_id"));
				act.setOpen_int_id(rs.getInt("int_id"));
				pass = true;

			}

		} catch (Exception e) {

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
		return pass;

	}

	// --------------------------------------------------------------------------------------------------------
	public boolean saveImpl(AdvanceOpeningFL2AAction act) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;

		String query = "";

		boolean flg = false;
		try {
			String sDate1 = act.getOpen_date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date1 = formatter.parse(sDate1);

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);

			query = " INSERT INTO fl2d.advance_opening_fl2a(   "
					+ "	int_id, unit_id, unit_type,  open_date, amount,fee_type, login_id,deo,district_id,depo_nm) "
					+ "	VALUES (?, ?, ?, ?, ?, '" + act.getUnit_type()
					+ "', ?,?,?,?) ";

			// System.out.println("SimpleDateFormat===============" +query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, this.maxId());
			pstmt.setInt(2, Integer.parseInt(act.getUnit_id()));
			pstmt.setString(3, act.getRadio());

			pstmt.setDate(4, Utility.convertUtilDateToSQLDate(date1));
			pstmt.setDouble(5, act.getAmount());
			/*
			 * if (act.getUnit_type().equalsIgnoreCase("IF")) {
			 * pstmt.setString(6, "IMPORT FEE"); } else
			 */

			// pstmt.setString(6, act.getUnit_type());
			/*
			 * if (act.getUnit_type().equalsIgnoreCase("ED")) {
			 * pstmt.setString(6, "EXCISE DUTY"); } else if
			 * (act.getUnit_type().equalsIgnoreCase("SF")) { pstmt.setString(6,
			 * "SPECIAL FEE"); } else if
			 * (act.getUnit_type().equalsIgnoreCase("SC")) { pstmt.setString(6,
			 * "SCANING FEE"); } else if
			 * (act.getUnit_type().equalsIgnoreCase("SCF")) { pstmt.setString(6,
			 * "SPECIAL CONSIDERATION FEE"); } else if
			 * (act.getUnit_type().equalsIgnoreCase("IF")) { pstmt.setString(6,
			 * "IMPORT FEE"); }
			 */
			// pstmt.setString(7, act.getLogin_id());
			pstmt.setString(7, ResourceUtil.getUserNameAllReq().trim());
			System.out.println("===" + act.getDistict_id());
			if (act.getRadio().equalsIgnoreCase("DEPO")) {
				pstmt.setInt(8, act.getDistict_id());
				pstmt.setString(9, act.getDepo_nm());
				pstmt.setString(6, null);
			} else {
				pstmt.setInt(8, act.getDistict_id());
				pstmt.setString(9, null);
				pstmt.setString(6, act.getLogin_id());
			}

			/*
			 * if(act.getDistict_id()!= 0){ pstmt.setInt(8,
			 * act.getDistict_id()); pstmt.setString(9, act.getDepo_nm());
			 * pstmt.setString(6, null); } else { pstmt.setInt(8, 0);
			 * pstmt.setString(9, null); pstmt.setString(6, act.getLogin_id());
			 * }
			 */

			saveStatus = pstmt.executeUpdate();

			if (saveStatus > 0) {
				conn.setAutoCommit(true);
				// act.reset();
				flg = true;
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("SAVED SUCCESSFULLY !",
								"SAVED SUCCESSFULLY !"));

			} else {
				// action.reset();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
						Constants.NOT_SAVED);

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

	public int maxId() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(int_id) as id FROM fl2d.advance_opening_fl2a  ";
		int maxid = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxid = rs.getInt("id");
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), e.getMessage()));

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

	// ----------------------------------------------------------------------------

	public void updatedata(AdvanceOpeningFL2AAction act) {

		int saveStatus = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String queryList = "";

		try {

			queryList = " update  fl2d.advance_opening_fl2a set  amount='"
					+ act.getAmount() + "' where  unit_id='" + act.getUnit_id()
					+ "' and unit_type='" + act.getRadio() + "' and fee_type='"
					+ act.getUnit_type() + "' ";

			// System.out.println("====updated===11=====" + queryList);
			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			saveStatus = pstmt.executeUpdate();

			if (saveStatus > 0) {
				// action.setFinalflg(true);
				// act.reset();

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Update  Successfully !!! ",
								"Update Successfully !!!"));

			} else {
				// System.out.println("---------- UPDATE NOT ----------");
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Not Update  !!! ", "Not Update !!!"));

			}

		} catch (Exception se) {
			se.printStackTrace();

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (Exception se) {
				se.printStackTrace();
			}

		}

	}

	// ----------------------------------------------------------------------------------------------

	public ArrayList viewdetail(AdvanceOpeningFL2AAction act) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList al = new ArrayList();
		int i = 1;
		conn = ConnectionToDataBase.getConnection();

		String sql = " SELECT a.int_id, a.unit_id, a.unit_type,case when a.unit_type='DEPO' then (select vch_applicant_name from licence.fl2_2b_2d_fl2a_csd where int_app_id=a.unit_id) when  "
				+ " a.unit_type='UNIT' then (select vch_indus_name from public.other_unit_registration_20_21 where int_app_id_f=a.unit_id and login_id like '%CSD%') end as unit_nm, a.fee_type, a.open_date, a.amount "
				+ " FROM fl2d.advance_opening_fl2a a where deo='"
				+ ResourceUtil.getUserNameAllReq().trim()
				+ "'    order by   unit_nm  asc";

		/*
		 * " SELECT int_id, unit_id, unit_type, fee_type, open_date, amount  "+
		 * " FROM fl2d.advance_opening_fl2a  where deo='"
		 * +ResourceUtil.getUserNameAllReq().trim()+"' ";
		 */
		// System.out.println("===view data====" + sql);
		try {

			pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			// action.setFlag("T");
			while (rs.next()) {

				AdvanceOpeningFL2ADt dt1 = new AdvanceOpeningFL2ADt();
				dt1.setSr_no(i);
				dt1.setUnit_typ(rs.getString("unit_type"));
				dt1.setUnit_nm(rs.getString("unit_nm"));
				dt1.setFee_typ(rs.getString("fee_type"));
				dt1.setAmount(rs.getString("amount"));
				dt1.setOpn_dt(rs.getDate("open_date"));

				al.add(dt1);

				i++;

			}

			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();

			if (rs != null)
				conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return al;

	}

}
