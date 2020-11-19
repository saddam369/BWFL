package com.mentor.impl;

import java.io.FileOutputStream;
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
import javax.faces.model.SelectItem;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mentor.action.BarCodeForCSDAction;
import com.mentor.action.EnteryOfPermitAction;
import com.mentor.connectiondb.ConnectionToDataBase;
import com.mentor.datatable.BarCodeForCSDDataTable;
import com.mentor.datatable.EnteryOfPermitDataTable;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class EnteryOfPermitImpl {

	public String getSugarmill(EnteryOfPermitAction ac) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			// String
			// queryList="SELECT int_app_id_f,vch_undertaking_name,vch_wrk_add  from  dis_mst_pd1_pd2_lic where vch_wrk_phon="+ResourceUtil.getUserNameAllReq().trim();

			String queryList =

			" select int_app_id, vch_firm_name ,vch_core_address  from licence.fl2_2b_2d where vch_mobile_no = '"
					+ ResourceUtil.getUserNameAllReq().trim() + "' ";

			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ac.setDistillery_nm(rs.getString("vch_firm_name"));
				ac.setDistillery_id(rs.getInt("int_app_id"));
				ac.setDistillery_adrs(rs.getString("vch_core_address"));

				ac.setCdsName(rs.getString("vch_firm_name"));
				ac.setCdsAddress(rs.getString("vch_core_address"));

			}

			// pstmt.executeUpdate();
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

	public ArrayList distilleryList(EnteryOfPermitAction act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue(0);
		list.add(item);
		try {
			String query =

			"	 SELECT  int_app_id_f, vch_pd1_pd2_lic_no ,vch_undertaking_name  "
					+ "	    FROM  dis_mst_pd1_pd2_lic WHERE vch_verify_flag='V'  order by int_app_id_f ";

			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);
			// ps.setDate(1,
			// Utility.convertUtilDateToSQLDate(act.getDt_date()));
			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();

				item.setValue(rs.getString("int_app_id_f"));
				item.setLabel(rs.getString("vch_undertaking_name"));

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

	public ArrayList breweryList(EnteryOfPermitAction act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue(0);
		list.add(item);
		try {
			String query =

			"	select vch_app_id_f, vch_b1_lic_no ,brewery_nm   "
					+ "    FROM public.bre_mst_b1_lic where vch_verify_flag='V' order by  vch_app_id_f ";

			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();

				item.setValue(rs.getString("vch_app_id_f"));
				item.setLabel(rs.getString("brewery_nm"));

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

	// ---------------------------------- distillery name add etc --------------

	public String getdistellry(EnteryOfPermitAction ac, String app_id) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList = "SELECT int_app_id_f,vch_undertaking_name,vch_wrk_add ,vch_pd1_pd2_lic_no from  dis_mst_pd1_pd2_lic"
					+ " where int_app_id_f=? ";

			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(queryList);
			pstmt.setInt(1, Integer.parseInt(app_id));

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ac.setDistilleryBreweryAddress(rs.getString("vch_wrk_add"));
				ac.setLicenceNo(rs.getString("vch_pd1_pd2_lic_no"));

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

	// ---------------------------------- brewery name add etc --------------

	public String getbrewery(EnteryOfPermitAction ac, String app_id) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList =

			"	 select vch_app_id_f, vch_b1_lic_no ,brewery_nm , vch_reg_address  "
					+ "	    FROM public.bre_mst_b1_lic where vch_app_id_f= ? ";

			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(queryList);
			pstmt.setInt(1, Integer.parseInt(app_id));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ac.setDistilleryBreweryAddress(rs.getString("vch_reg_address"));
				ac.setLicenceNo(rs.getString("vch_b1_lic_no"));

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

	// ------------------------ add row --------------------

	public ArrayList getBrandName() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		EnteryOfPermitAction bof = (EnteryOfPermitAction) facesContext.getApplication().createValueBinding("#{enteryOfPermitAction}").getValue(facesContext);

		String radiobtn = bof.getRadioButton();

		String distllry_id = bof.getDistilleryId();

		String brewry_Id = bof.getBreweryId();

		int fl2a_id = bof.getDistillery_id();

		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);
		String sql = "";

		try {

		/*	if (radiobtn.equalsIgnoreCase("FUPD")) {
				sql =

				"	SELECT brand_id, brand_name FROM distillery.brand_registration_19_20  "
						+ "	  where license_category !='BEER'  "
						+ "	  and distillery_id=?  and  type='CSD' "
						+ "	     order by brand_name  ";

			} else if (radiobtn.equalsIgnoreCase("FUPB")) {
				sql =

				"	SELECT brand_id, brand_name FROM distillery.brand_registration_19_20  "
						+ "	  where license_category ='BEER'  "
						+ "	  and distillery_id=?  and  type='CSD' "
						+ "	     order by brand_name  ";

			}

			else if (radiobtn.equalsIgnoreCase("FOD")) {

				sql =

				"	SELECT brand_id, brand_name FROM distillery.brand_registration_19_20  "
						+ "	  where license_category !='BEER'  "
						+ "	 and  license_number='FL2A' and int_fl2a_id= ?"
						+ "	     order by brand_name  ";

			}

			else if (radiobtn.equalsIgnoreCase("FOB")) {

				sql =

				"	SELECT brand_id, brand_name FROM distillery.brand_registration_19_20  "
						+ "	  where license_category ='BEER'  "
						+ "	 and  license_number='FL2A' and int_fl2a_id= ? "
						+ "	     order by brand_name  ";

			}
			
			

			else {

			} */

			//System.out.println("-------btn---------"+radiobtn);
			
			sql = "SELECT brand_id, brand_name FROM distillery.brand_registration_19_20 where (vch_license_type in ('DCSD','BCSD','BUCSD','WCSD') or for_csd_civil='CSD') and approval='F';";
			
			
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
		/*	if ((radiobtn.equalsIgnoreCase("FUPD"))) {

				ps.setInt(1, Integer.parseInt(distllry_id));
				
			} else if ((radiobtn.equalsIgnoreCase("FUPB"))) {
				ps.setInt(1, Integer.parseInt(brewry_Id));

			}

			else if ((radiobtn.equalsIgnoreCase("FOD"))) {
				ps.setInt(1, fl2a_id);
			}

			else if ((radiobtn.equalsIgnoreCase("FOB"))) {
				ps.setInt(1, fl2a_id);
			}*/

			rs = ps.executeQuery();

			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("brand_name"));
				item.setValue(rs.getString("brand_id"));
				list.add(item);
			}
		} catch (Exception e) {
			// e.printStackTrace();
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

	// --------------------- capacti------------------

	public ArrayList getCapacity(String brand_id) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);
		String SQl = "SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id "
				+ "	from distillery.brand_registration_19_20 a , "
				+ "	distillery.packaging_details_19_20 b "
				+ "	where a.brand_id=b.brand_id_fk  " +
				// "	and a.distillery_id=? "+
				"	and brand_id =? and b.code_generate_through is not null  ";

		// System.out.println("-------    brand id----------"+brand_id+"------- sql -------"+SQl);

		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);
			// ps.setInt(1, this.getSugarmill_Id());
			ps.setInt(1, Integer.parseInt(brand_id));
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("package_name"));
				item.setValue(rs.getString("package_id"));
				list.add(item);
			}
		} catch (Exception e) {
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

	// ---------------------------- ml ---------------------

	// ----------------------------- get quantity
	// --------------------------------------------

	public ArrayList getquantity(String brand_Id, String packging_Id) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		/*
		 * item.setLabel("--SELECT--"); item.setValue(""); list.add(item);
		 */
		String SQl =

		"SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id "
				+ "	from distillery.brand_registration_19_20 a , "
				+ "	distillery.packaging_details_19_20 b "
				+ "	where a.brand_id=b.brand_id_fk  " +
				// "	and a.distillery_id=?  "+
				"	and brand_id =?  and b.package_id=?";

		/*System.out.println("--------------   -------------" + brand_Id
				+ "yyyyyyyyyyy" + packging_Id);
*/
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);
			// ps.setInt(1, this.getSugarmill_Id());
			ps.setInt(1, Integer.parseInt(brand_Id));
			ps.setInt(2, Integer.parseInt(packging_Id));
			rs = ps.executeQuery();
			while (rs.next()) {

				item = new SelectItem();
				item.setLabel(rs.getString("quantity"));
				item.setValue(rs.getString("quantity"));
				list.add(item);
			}
		} catch (Exception e) {
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

	// ----------------------------- 2nd -----------------

	public String getqty(String brand_Id, String packging_Id) {
		String qty = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList =

			"SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id "
					+ "	from distillery.brand_registration_19_20 a , "
					+ "	distillery.packaging_details_19_20 b "
					+ "	where a.brand_id=b.brand_id_fk  " +
					// "	and a.distillery_id=?  "+
					"	and brand_id =?  and b.package_id=?";

			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, Integer.parseInt(brand_Id));
			pstmt.setInt(2, Integer.parseInt(packging_Id));

			rs = pstmt.executeQuery();

			while (rs.next()) {

				qty = rs.getString("quantity");

			}

			// pstmt.executeUpdate();
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
		return qty;

	}

	// ----------------------------- get Packaging Name

	public ArrayList getPackagingName(String brand_id) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);
		String SQl =

		"	  select distinct qnt_ml_id , qnt_ml_detail  "
				+ "	  FROM distillery.box_size_details order by  qnt_ml_detail ";

		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);
			// ps.setInt(1, this.getSugarmill_Id());
			// ps.setInt(1, Integer.parseInt(brand_id));
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("qnt_ml_detail"));
				item.setValue(rs.getString("qnt_ml_detail"));
				list.add(item);
			}
		} catch (Exception e) {
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

	// ---------------------------------- save --------------------------

	public void save(EnteryOfPermitAction action) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		int saveStatus2 = 0;
		int max = getMax_id();
		int max_detail = getMax_id_detail();
		try {

			String queryMaster =

			"	INSERT INTO fl2d.entry_of_permit_master ( "
					+ "	seq, licence_id, radio_type, csd_name, csd_address, permit_no, issue_date,  "
					+ "   distillery_brewery_name, distillery_id, brewery_id, distillery_brewery_address, licence_no, contact_no, created_by , email_id ) "
					+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(queryMaster);

			pstmt.setInt(1, this.getMax_id());
			pstmt.setInt(2, action.getDistillery_id());
			pstmt.setString(3, action.getRadioButton());
			pstmt.setString(4, action.getCdsName());
			pstmt.setString(5, action.getCdsAddress());
			pstmt.setString(6, action.getPermitNo());
			pstmt.setDate(7,Utility.convertUtilDateToSQLDate(action.getIssueDate()));

			if (action.getRadioButton().equalsIgnoreCase("FUPD")) {
				pstmt.setString(8, null);
				pstmt.setInt(9, Integer.parseInt(action.getDistilleryId()));
				pstmt.setInt(10, 0);
			} else if (action.getRadioButton().equalsIgnoreCase("FUPB")) {
				pstmt.setString(8, null);
				pstmt.setInt(9, 0);
				pstmt.setInt(10, Integer.parseInt(action.getBreweryId()));
			} else {
				pstmt.setString(8, action.getDistilleryBreweryName());
				pstmt.setInt(9, 0);
				pstmt.setInt(10, 0);
			}
			pstmt.setString(11, action.getDistilleryBreweryAddress());
			//pstmt.setString(12, action.getLicenceNo());
			pstmt.setString(12, "NA");
			pstmt.setString(13, action.getContactNo());
			pstmt.setString(14, ResourceUtil.getUserNameAllReq().trim());
			pstmt.setString(15, action.getEmail_id());

			saveStatus = pstmt.executeUpdate();

			if (saveStatus > 0) {

				if (action.getBrandPackagingDataList().size() > 0) {

					for (int i = 0; i < action.getBrandPackagingDataList()
							.size(); i++) {
						saveStatus2 = 0;

						String sql = " INSERT INTO fl2d.entry_of_permit_master_detail("
								+ "  int_id, seq, brand_id, size, no_of_bottle,no_cases,cases, packaging_id) VALUES (?, ?, ?, ?, ?, ?,?,?)";

						pstmt = conn.prepareStatement(sql);
						int j = 1;
						EnteryOfPermitDataTable table = (EnteryOfPermitDataTable) action
								.getBrandPackagingDataList().get(i);
						// if (table.isUpdateflg() == false) {

						pstmt.setInt(1, ++max_detail);
						pstmt.setInt(2, max);
						pstmt.setInt(3, Integer.parseInt(table
								.getBrandPackagingData_Brand()));
						pstmt.setInt(4, Integer.parseInt(table
								.getBrandPackagingData_Packaging()));
						pstmt.setInt(5,
								table.getBrandPackagingData_PlanNoOfBottling());
						pstmt.setInt(6, table.getBrandPackagingData_NoOfBoxes());
						pstmt.setInt(7, table.getCases());
						pstmt.setInt(8, Integer.parseInt(table
								.getBrandPackagingData_Capacity_id()));

						saveStatus2 = pstmt.executeUpdate();
						// }
					}
				}

			}
			if (saveStatus > 0 && saveStatus2 > 0) {
				conn.commit();
				ResourceUtil.addErrorMessage(Constants.SAVED_SUCESSFULLY,
						Constants.SAVED_SUCESSFULLY);
				action.reset();
			} else {
				conn.rollback();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
						Constants.NOT_SAVED);
			}
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
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
	}

	// -----------------------------------

	public int getMax_id() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id = 0;
		String query = "SELECT max(seq) as id FROM fl2d.entry_of_permit_master ";
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (Exception e) {
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
		return id + 1;
	}

	// ------------------------------ detail

	public int getMax_id_detail() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id = 0;
		String query = "SELECT max(int_id) as id FROM fl2d.entry_of_permit_master_detail ";
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (Exception e) {
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
		return id;
	}

	// --------------------------------- show data table -------------------

	public ArrayList getData(EnteryOfPermitAction ac) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql =

		"	SELECT seq, licence_id, radio_type, csd_name, csd_address, permit_no, issue_date,  "
				+ "	distillery_brewery_name, distillery_id, brewery_id, distillery_brewery_address,distillery_brewery_name  "
				+ "	licence_no, contact_no, created_by, email_id "
				+ "	FROM fl2d.entry_of_permit_master where created_by=? ORDER BY issue_date DESC ";

		// user_name ='"+ResourceUtil.getUserNameAllReq()+"'";

		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, ResourceUtil.getUserNameAllReq());
			rs = ps.executeQuery();
			while (rs.next()) {

				EnteryOfPermitDataTable dt = new EnteryOfPermitDataTable();
				dt.setRequest_id(rs.getInt("seq"));
				dt.setShowDataTable_Date(rs.getDate("issue_date"));
				dt.setShowDataTable_Cds_Name(rs.getString("csd_name"));
				dt.setShowDataTable_Permit_No(rs.getString("permit_no"));
				dt.setShowDataTable_Licence_No(rs.getString("email_id"));
				dt.setShowDataTable_contact_NO(rs.getString("contact_no"));

				dt.setShowDataTable_radio(rs.getString("radio_type"));
				dt.setShowDataTable_distBrewNm(rs.getString("distillery_brewery_name"));
				dt.setShowDataTable_Distid(rs.getInt("distillery_id"));
				dt.setShowDataTable_brewId(rs.getString("brewery_id"));
				
				list.add(dt);
			}

		} catch (Exception e) {
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

	// -----------------------get details for update----------------------------

	public ArrayList getUpdtData(EnteryOfPermitAction ac, int id) {

		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = " SELECT a.int_id, a.seq, a.brand_id, a.size, a.no_of_bottle, a.cases, "
				+ " a.no_cases, a.packaging_id, a.finalize_flg, a.finalized_date, a.recieved_bottles,b.distillery_id,b.brewery_id," +
				" b.distillery_id, b.brewery_id, b.distillery_brewery_address,b.distillery_brewery_name  "
				+ " FROM fl2d.entry_of_permit_master_detail a,fl2d.entry_of_permit_master b WHERE a.seq='"
				+ id + "' and a.seq=b.seq  ";

		System.out.println("sql=----------" + sql);

		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {

				//System.out.println("sql=---next-------");

				EnteryOfPermitDataTable dt = new EnteryOfPermitDataTable();

				dt.setSeqDt(rs.getInt("int_id"));
				dt.setMstSeqDt(rs.getInt("seq"));
				dt.setBrandPackagingData_Brand(rs.getString("brand_id"));
				dt.setBrandPackagingData_Capacity_id(rs.getString("packaging_id"));
				dt.setBrandPackagingData_Packaging(rs.getString("size"));
				dt.setBrandPackagingData_NoOfBoxes(rs.getInt("no_cases"));
				dt.setCases(rs.getInt("cases"));
				dt.setBrandPackagingData_PlanNoOfBottling(rs.getInt("no_of_bottle"));
				ac.setDistilleryBreweryAddress(rs.getString("distillery_brewery_address"));
				
				if(rs.getString("distillery_brewery_name")!=null){
					System.out.println("-------1");
					ac.setDistilleryBreweryNameText_Flag(true);
					ac.setDistillerylist_Flag(false);
					ac.setBrewerylist_Flag(false);
				ac.setDistilleryBreweryName(rs.getString("distillery_brewery_name"));
				}else if(rs.getInt("distillery_id")!=0){
					System.out.println("-------2");
					ac.setDistilleryBreweryNameText_Flag(false);
					ac.setDistillerylist_Flag(true);
					ac.setBrewerylist_Flag(false);
				ac.setDistilleryId(rs.getString("distillery_id"));
				}else if(rs.getInt("brewery_id")!=0){
					System.out.println("-------3");
					ac.setDistilleryBreweryNameText_Flag(false);
					ac.setDistillerylist_Flag(false);
					ac.setBrewerylist_Flag(true);
				ac.setBreweryId(rs.getString("brewery_id"));
				}
				// dt.setBrandPackagingData_Brand("665");

				list.add(dt);
			}

		} catch (Exception e) {
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

	// ------------------------update data which are displaying in data
	// table--------------------

	public String updtDetailsImpl(EnteryOfPermitAction act) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		String selQr = "";
		//int saveStatus2 = 0;

		try {

			String queryMaster = " UPDATE fl2d.entry_of_permit_master SET  permit_no=?, "
					+ " issue_date=?, distillery_brewery_name=?, distillery_id=?, brewery_id=?, "
					+ " distillery_brewery_address=?, contact_no=?, email_id=? "
					+ " WHERE licence_id='" + act.getDistillery_id() + "' and  permit_no='"+act.getPermitNo()+"' ";

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(queryMaster);

			pstmt.setString(1, act.getPermitNo());
			pstmt.setDate(2,Utility.convertUtilDateToSQLDate(act.getIssueDate()));

			if (act.getRadioButton().equalsIgnoreCase("FUPD")) {
				pstmt.setString(3, null);
				pstmt.setInt(4, Integer.parseInt(act.getDistilleryId()));
				pstmt.setInt(5, 0);
			} else if (act.getRadioButton().equalsIgnoreCase("FUPB")) {
				pstmt.setString(3, null);
				pstmt.setInt(4, 0);
				pstmt.setInt(5, Integer.parseInt(act.getBreweryId()));
			} else {
				pstmt.setString(3, act.getDistilleryBreweryName());
				pstmt.setInt(4, 0);
				pstmt.setInt(5, 0);
			}
			pstmt.setString(6, act.getDistilleryBreweryAddress());
			pstmt.setString(7, act.getContactNo());
			pstmt.setString(8, act.getEmail_id());

			saveStatus = pstmt.executeUpdate();

			if (saveStatus > 0) {

				if (act.getBrandPackagingDataList().size() > 0) {

					for (int i = 0; i < act.getBrandPackagingDataList().size(); i++) {
						saveStatus = 0;

						String sql = " UPDATE fl2d.entry_of_permit_master_detail "+
									 " SET brand_id=?, packaging_id=?, size=?, no_of_bottle=?, cases=?, no_cases=? "+
									 " WHERE int_id=? AND seq=? ";

						pstmt = conn.prepareStatement(sql);
						
						//System.out.println("sql-----------"+sql);
						
						//int j = 1;
						EnteryOfPermitDataTable table = (EnteryOfPermitDataTable) act.getBrandPackagingDataList().get(i);

						pstmt.setInt(1, Integer.parseInt(table.getBrandPackagingData_Brand()));
						pstmt.setInt(2, Integer.parseInt(table.getBrandPackagingData_Capacity_id()));
						pstmt.setInt(3, Integer.parseInt(table.getBrandPackagingData_Packaging()));
						pstmt.setInt(4,table.getBrandPackagingData_PlanNoOfBottling());
						pstmt.setInt(5, table.getCases());
						pstmt.setInt(6, table.getBrandPackagingData_NoOfBoxes());
						pstmt.setInt(7, table.getSeqDt());
						pstmt.setInt(8, table.getMstSeqDt());

						saveStatus = pstmt.executeUpdate();

					}
				}

			}
			if (saveStatus > 0) {
				conn.commit();
				ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,Constants.SAVED_SUCESSFULLY);
				act.reset();
			} else {
				conn.rollback();
				//ResourceUtil.addErrorMessage(Constants.NOT_SAVED,Constants.NOT_SAVED);
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
				"Data Not Saved.........You Cannot Add New Row While Updating !!", "Data Not Saved.........You Cannot Add New Row While Updating !!")); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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
		return "";
	}

	
	
	
	
	
	
	//------------------------------------------
	
	
			public ArrayList getDataBrandDetail(EnteryOfPermitAction ac) {
				ArrayList list = new ArrayList();
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				
				/*FacesContext facesContext = FacesContext.getCurrentInstance();
				BarCodeForCSDAction bof = (BarCodeForCSDAction) facesContext
						.getApplication()
						.createValueBinding("#{barCodeForCSDAction}")
						.getValue(facesContext);

				String csd_id = bof.getCsd_Id();
*/
				

				String sql =
						
					"	SELECT distinct a.seq, d.code_generate_through,a.licence_id, a.radio_type, a.csd_name, a.csd_address, a.permit_no, a.issue_date,  "+  
					"	a.distillery_brewery_name, a.distillery_id, a.brewery_id, a.distillery_brewery_address,  "+
					"	a.licence_no, a.contact_no, a.created_by, b.finalize_flg,  "+
					"	b.int_id, b.seq, b.brand_id, b.size, b.no_of_bottle,b.cases,b.no_cases, "+
					"	c.brand_id, c.brand_name ,b.finalized_date,b.int_id  "+
					"	FROM fl2d.entry_of_permit_master a , fl2d.entry_of_permit_master_detail b , "+
					"	distillery.brand_registration_19_20 c,distillery.packaging_details_19_20 d "+
					"	where a.seq=b.seq  and d.brand_id_fk=b.brand_id  "+
					"	and b.brand_id=c.brand_id and b.packaging_id =d.package_id  and created_by=? order by  a.issue_date DESC ";
					//"	and a.licence_id=?  and a.permit_no ='"+ac.getPermit_No().trim()+"'";
						
						
						
				
				// user_name ='"+ResourceUtil.getUserNameAllReq()+"'";

				 
				try {
					con = ConnectionToDataBase.getConnection();
					ps = con.prepareStatement(sql);
					ps.setString(1, ResourceUtil.getUserNameAllReq());
					//ps.setInt(1, Integer.parseInt(csd_id));
					
					rs = ps.executeQuery();
					while (rs.next()) {

						BarCodeForCSDDataTable dt = new BarCodeForCSDDataTable();
						dt.setCsd(rs.getInt("licence_id"));
						dt.setShowDataTable_Quntity(rs.getString("size"));
						dt.setShowDataTable_PlanNoOfBottling(rs
								.getString("no_of_bottle"));
						dt.setShowDataTable_NoOfBoxes(rs.getString("no_cases"));
						dt.setBrandPackagingData_Brand_Name(rs.getString("brand_name"));
						dt.setBrandPackagingData_Brand_Size(rs.getString("size"));
						dt.setBrandPackagingData_Brand_No_OF_Bottels(rs.getString("no_of_bottle"));
						dt.setGtinno(rs.getString("code_generate_through"));
						dt.setRequest_id(rs.getInt("int_id"));
						if (rs.getDate("finalized_date") != null) {
							Date dat = Utility.convertSqlDateToUtilDate(rs
									.getDate("issue_date"));
							//System.out.println("date finalize" + dat);

							DateFormat formatter = new SimpleDateFormat("yyMMdd");
							String date = formatter.format(dat);
							dt.setFinalizedDateString(date);
							
						}
						dt.setShowDataTable_Date(rs.getDate("issue_date"));	
						dt.setNewsize(rs.getInt("cases"));
						dt.setLicencenoo(rs.getString("permit_no"));
						dt.setFinalizedFlag(rs.getString("finalize_flg"));
						list.add(dt);
					}

				} catch (Exception e) {
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

	
	
	
	
			public void dataFinalize(EnteryOfPermitAction action,
					BarCodeForCSDDataTable dt) {
				Connection conn = null;
				Connection conn1 = null;
				PreparedStatement pstmt1 = null;
				PreparedStatement pstmt2 = null;
				PreparedStatement pstmt3 = null;
				PreparedStatement pstmt4 = null;
				String gtinNo = "";
				long boxsize = 0;
				long caseno = 0;
				int status=0;
				
				String sql = "INSERT INTO public.bottling_fl2a(  "
						+ " dispatch_date, gtin_no, serial_no_start, serial_no_end,case_no,request_id,csd_id,licence_no,licence_type) "
						+ "	VALUES (?, ?, ?, ?, ?, ?,?,?,'FL2A')";

				

				String update = "UPDATE fl2d.entry_of_permit_master_detail "
						+ " SET   finalize_flg='F' ,finalized_date=? "
						+ "WHERE int_id=?";

				try {
					//gtinNo = getBrandPackagingGtinNo(action, dt);
					gtinNo=dt.getGtinno();
				 	 long	serialno = getSerialNoFl2D(new Double(
							dt.getShowDataTable_PlanNoOfBottling()).longValue());
					
					
					
					
					// long
					// i=(Long.parseLong(dt.getShowDataTable_PlanNoOfBottling())/(Long.parseLong(dt.getShowDataTable_NoOfBoxes())));
				 
					if (!gtinNo.equals("") && serialno != 0) {
						conn = ConnectionToDataBase.getConnection3();
						conn1 = ConnectionToDataBase.getConnection();
						conn.setAutoCommit(false);
						conn1.setAutoCommit(false);
						pstmt1 = conn1.prepareStatement(update);
						pstmt1.setDate(1, new java.sql.Date(System.currentTimeMillis()));
						pstmt1.setInt(2, dt.getRequest_id());
						status = pstmt1.executeUpdate();

					 
							status = 0;
						
							pstmt1 = conn.prepareStatement(sql);
							for (int i = 0; i < Long.parseLong(dt
									.getShowDataTable_NoOfBoxes()); i++) {
								caseno = getcaseNoFl2d();

							//	pstmt1.setInt(1, action.getDistillery_id());

								pstmt1.setDate(1, Utility.convertUtilDateToSQLDate(dt
										.getShowDataTable_Date()));
								pstmt1.setString(2, gtinNo);
								pstmt1.setLong(3, serialno);

								//pstmt1.setString(6, dt.getLicenceNo());
								//pstmt1.setString(7, dt.getShowDataTable_LicenceType());
								pstmt1.setLong(5, caseno);
								pstmt1.setInt(6, dt.getRequest_id());
								 
								pstmt1.setLong(4, serialno+(dt.getNewsize()-1));
								pstmt1.setInt(7, dt.getCsd());
								pstmt1.setString(8, dt.getLicencenoo());
								serialno+=dt.getNewsize();
								pstmt1.addBatch();
								
							 
						}
						
						int[] status1=pstmt1.executeBatch();
						
						if (status1.length > 0) {
							status = 0;
							boolean flag = write(dt, action, conn);

							if (flag) {
								status = 1;
							} else {
								FacesContext.getCurrentInstance().addMessage(
										null,
										new FacesMessage("Excel Not Generated",
												"Excel Not Generated"));
							}
						}
						if (status > 0) {

							conn.commit();
							conn1.commit();

							FacesContext.getCurrentInstance().addMessage(
									null,
									new FacesMessage("Finalized SuccessFully",
											"Finalized SuccessFully"));
						} else {
							conn.rollback();
							conn1.rollback();
							FacesContext.getCurrentInstance().addMessage(
									null,
									new FacesMessage("Not Finalized ",
											" Not Finalized "));
						}

					} else {
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage("Gtin and Serial No Can Not Zero ",
										" Gtin and Serial No Can Not Zero"));
					}
				}

				catch (Exception e) {

					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(e.getMessage(), e.getMessage()));
					try {
						conn.rollback();
						conn1.rollback();
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					e.printStackTrace();
				} finally {

					try {

						if (pstmt1 != null)
							pstmt1.close();
						if (pstmt2 != null)
							pstmt2.close();
						if (pstmt3 != null)
							pstmt3.close();
						if (pstmt4 != null)
							pstmt4.close();
						if (conn != null)
							conn.close();
						if (conn1 != null)
							conn1.close();

					} catch (Exception e) {
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(e.getMessage(), e.getMessage()));
						e.printStackTrace();
					}
				}
			}



						
					
					
			public synchronized long getSerialNoFl2D(long noOfSequenc) {
				String sql = " select     nextval('public.fl2a_serial_seq')";
				String sqll = "ALTER SEQUENCE public.fl2a_serial_seq RESTART WITH ? ";
				Connection conn = null;
				PreparedStatement pstmt = null;
				PreparedStatement pstmt1 = null;
				PreparedStatement pstmt2 = null;
				ResultSet rs = null;
				long serialNo = 0;
				long currseq = 0;

				try {
					conn = ConnectionToDataBase.getConnection3();

					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						serialNo = rs.getLong(1);
						if (serialNo == 0) {
							serialNo = serialNo + 1;
						}
						System.out.println("noOfSequenc " + noOfSequenc);

						pstmt1 = conn
								.prepareStatement("ALTER SEQUENCE public.fl2a_serial_seq RESTART WITH "
										+ (noOfSequenc + serialNo));

						System.out
								.println("ALTER SEQUENCE public.fl2a_serial_seq RESTART WITH "
										+ (noOfSequenc + serialNo));
						pstmt1.executeUpdate();

					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {

					try {
						if (rs != null)
							rs.close();

						if (pstmt != null)
							pstmt.close();

						if (conn != null)
							conn.close();

					} catch (Exception e) {
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(e.getMessage(), e.getMessage()));
						e.printStackTrace();
					}
				}

				return serialNo;
			}


			public synchronized long getcaseNoFl2d() {
				String sql = " select     nextval('public.fl2a_serial_seq')";

				Connection conn = null;
				PreparedStatement pstmt = null;
				PreparedStatement pstmt1 = null;
				PreparedStatement pstmt2 = null;
				ResultSet rs = null;
				long serialNo = 0;
				long currseq = 0;

				try {
					conn = ConnectionToDataBase.getConnection3();

					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						serialNo = rs.getLong(1);
						if (serialNo == 0) {
							serialNo = serialNo;
						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {

					try {
						if (rs != null)
							rs.close();

						if (pstmt != null)
							pstmt.close();

						if (conn != null)
							conn.close();

					} catch (Exception e) {
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(e.getMessage(), e.getMessage()));
						e.printStackTrace();
					}
				}

				return serialNo;
			}




			public boolean write(BarCodeForCSDDataTable dt,
					EnteryOfPermitAction action, Connection conn) {

				

				String fl3 = ""
						+
						
						"	select to_char(y.gs, 'fm000000000000')as GENERATE_SERIES , y.dispatch_date, y.gtin_no,"
						+ "	  y.serial_no_start, y.serial_no_end , "
						+ "	to_char(y.case_no , 'fm0000000000')as case_no from( "
						+ "	select  GENERATE_SERIES(x.serial_no_start ,x.serial_no_end ) as gs ,x.serial_no_start ,x.serial_no_end, "
						+ "	x.case_no,x.dispatch_date,x.gtin_no "
						+ "	from ( "
						+ "	SELECT  dispatch_date, gtin_no, serial_no_start, serial_no_end,  case_no "
						+ "	FROM public.bottling_fl2a a "
						+ "	where  dispatch_date=?   and gtin_no=? and request_id=?)x)y";

				
				String relativePath = Constants.JBOSS_SERVER_PATH
						+ Constants.JBOSS_LINX_PATH;
				FileOutputStream fileOut = null;

				PreparedStatement pstmt = null;
				ResultSet rs = null;
				long start = 0;
				long end = 0;
				boolean flag = false;
				long k = 0;
				String noOfUnit = "";
				String date = null;

				try {

					

				 
						pstmt = conn.prepareStatement(fl3);

						
						
						pstmt.setDate(1, Utility.convertUtilDateToSQLDate(dt
								.getShowDataTable_Date()));
						
						pstmt.setString(2, dt.getGtinno());
						pstmt.setInt(3, dt.getRequest_id());
						rs = pstmt.executeQuery();
						 
					
					XSSFWorkbook workbook = new XSSFWorkbook();
					XSSFSheet worksheet = workbook.createSheet("Barcode Report");
				
					CellStyle unlockedCellStyle = workbook.createCellStyle();
					unlockedCellStyle.setLocked(false);

					// Sheet sheet = workbook.createSheet();
					worksheet.protectSheet("agristat");
					worksheet.setColumnWidth(0, 3000);
					worksheet.setColumnWidth(1, 8000);
					worksheet.setColumnWidth(2, 8000);
					worksheet.setColumnWidth(3, 8000);
					worksheet.setColumnWidth(4, 6000);
					

					XSSFRow rowhead0 = worksheet.createRow((int) 0);
					
					XSSFCell cellhead0 = rowhead0.createCell((int) 0);
					cellhead0.setCellValue("Barcode Report");

					rowhead0.setHeight((short) 700);
					cellhead0.setCellStyle(unlockedCellStyle);
					XSSFCellStyle cellStyl = workbook.createCellStyle();
					// HSSFCellStyle cellStyl = workbook.createCellStyle();

					cellStyl = workbook.createCellStyle();
					XSSFFont hSSFFont = workbook.createFont();
					hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
					hSSFFont.setFontHeightInPoints((short) 12);
					hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
					hSSFFont.setColor(HSSFColor.GREEN.index);
					cellStyl.setFont(hSSFFont);
					cellhead0.setCellStyle(cellStyl);

					// HSSFCellStyle cellStyle = workbook.createCellStyle();
					XSSFCellStyle cellStyle = workbook.createCellStyle();
					cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
					cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

					// -----------------------------

					XSSFCellStyle unlockcellStyle = workbook.createCellStyle();
					unlockcellStyle.setLocked(false);

					// ----------------------------
					XSSFRow rowhead = worksheet.createRow((int) 1);

					XSSFCell cellhead1 = rowhead.createCell((int) 0);
					cellhead1.setCellValue("S.No.");

					cellhead1.setCellStyle(cellStyle);

					XSSFCell cellhead2 = rowhead.createCell((int) 1);
					cellhead2.setCellValue("Gtin");
					cellhead2.setCellStyle(cellStyle);

					XSSFCell cellhead3 = rowhead.createCell((int) 2);
					cellhead3.setCellValue("FinalizeDate");
					cellhead3.setCellStyle(cellStyle);

					XSSFCell cellhead4 = rowhead.createCell((int) 3);
					cellhead4.setCellValue("Case Etn");
					cellhead4.setCellStyle(cellStyle);

					XSSFCell cellhead5 = rowhead.createCell((int) 4);
					cellhead5.setCellValue("BottleBarcode");
					cellhead5.setCellStyle(cellStyle);
					

					int i = 0;
					while (rs.next()) {

						// System.out.println("i==========="+i++);
						Date dat = Utility.convertSqlDateToUtilDate(rs
								.getDate("dispatch_date"));

						DateFormat formatter;

						formatter = new SimpleDateFormat("yyMMdd");
						date = formatter.format(dat);

						String lic = dt.getLicencenoo().replaceAll("/", "");

						// System.out.println("while in");serial_no_start, serial_no_end
						start = rs.getLong("serial_no_start");
						end = rs.getLong("serial_no_end");

						k++;
						XSSFRow row1 = worksheet.createRow((int) k);

						XSSFCell cellA1 = row1.createCell((int) 0);
						cellA1.setCellValue(k);

						XSSFCell cellB1 = row1.createCell((int) 1);
						cellB1.setCellValue(rs.getString("gtin_no"));

						XSSFCell cellC1 = row1.createCell((int) 2);
						cellC1.setCellValue(date);
						// cellC1.setCellStyle(unlockcellStyle);

						XSSFCell cellD1 = row1.createCell((int) 3);
						 
						noOfUnit=Integer.toString(dt.getNewsize());
						if(noOfUnit.length()==2){
						cellD1.setCellValue("01" + rs.getString("gtin_no") + "13"
								+ date + "37" + noOfUnit + "21"
								+ rs.getString("case_no"));
						}
						else if(noOfUnit.length()==1)
						{
							cellD1.setCellValue("01" + rs.getString("gtin_no") + "13"
									+ date + "370" + noOfUnit + "21"
									+ rs.getString("case_no"));
						}else{
							cellD1.setCellValue("01" + rs.getString("gtin_no") + "13"
									+ date + "37" + noOfUnit.substring(0,2) + "21"
									+ rs.getString("case_no"));
						}
						XSSFCell cellE1 = row1.createCell((int) 4);
						cellE1.setCellValue("01" + rs.getString("gtin_no") + "13"
								+ date + "21" + rs.getString("GENERATE_SERIES"));

					}
					fileOut = new FileOutputStream(relativePath + "//ExciseUp//Excel//"+dt.getRequest_id()+""
							+ dt.getGtinno() + "" + date + ".xls");

					XSSFRow row1 = worksheet.createRow((int) k + 1);

					// APoolFinancialReportDataTable dt=(APoolFinancialReportDataTable)
					// list.get(i-2);

					XSSFCell cellA1 = row1.createCell((int) 0);
					cellA1.setCellValue("End");

					workbook.write(fileOut);
					fileOut.flush();
					fileOut.close();
					flag = true;

				} catch (Exception e) {

					System.out.println("xls2" + e.getMessage());
					e.printStackTrace();
				}

				return flag;
			}



			public synchronized long getcaseNo() {
				String sql = " select     nextval('public.case_seq')";

				Connection conn = null;
				PreparedStatement pstmt = null;
				PreparedStatement pstmt1 = null;
				PreparedStatement pstmt2 = null;
				ResultSet rs = null;
				long serialNo = 0;
				long currseq = 0;

				try {
					conn = ConnectionToDataBase.getConnection3();

					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						serialNo = rs.getInt(1);
						if (serialNo == 0) {
							serialNo = serialNo;
						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {

					try {
						if (rs != null)
							rs.close();

						if (pstmt != null)
							pstmt.close();

						if (conn != null)
							conn.close();

					} catch (Exception e) {
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(e.getMessage(), e.getMessage()));
						e.printStackTrace();
					}
				}

				return serialNo;
			}

	
	
	
	
	
}
