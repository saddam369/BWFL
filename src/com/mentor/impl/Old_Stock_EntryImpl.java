package com.mentor.impl;

import java.io.FileOutputStream;
import java.io.FileWriter;
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

import com.arjuna.ats.arjuna.gandiva.Resource;


import com.mentor.action.BWFL_StockReceiveAction;
import com.mentor.action.Old_Stock_EntryAction;

import com.mentor.connectiondb.ConnectionToDataBase;
import com.mentor.datatable.BWFL_StockReceiveDataTable;
import com.mentor.datatable.Old_Stock_EntryDT;
import com.mentor.datatable.Fl2ScanningShowDataTable;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class Old_Stock_EntryImpl {

	/*
	 * public String getLicenceList1(Old_Stock_EntryAction ac, String val) { int
	 * id = 0; Connection con = null; PreparedStatement pstmt = null; ResultSet
	 * rs = null; String s = ""; try { con =
	 * ConnectionToDataBase.getConnection(); String queryList =
	 * " SELECT int_app_id, vch_applicant_name, vch_firm_name ," +
	 * " vch_mobile_no,vch_license_type, vch_licence_no " +
	 * " FROM licence.fl2_2b_2d WHERE loginid= ? ";
	 * 
	 * pstmt = con.prepareStatement(queryList); pstmt.setString(1,
	 * ResourceUtil.getUserNameAllReq().trim());
	 * 
	 * rs = pstmt.executeQuery(); System.out.println(queryList); while
	 * (rs.next()) {
	 * 
	 * 
	 * ac.setName(rs.getString("vch_firm_name"));
	 * ac.setDistillery_id(rs.getInt("int_app_id"));
	 * ac.setLicenceNo(rs.getString("vch_licence_no"));
	 * ac.setLoginType(rs.getString("vch_license_type"));
	 * 
	 * }
	 * 
	 * } catch (SQLException se) { se.printStackTrace(); } finally { try { if
	 * (pstmt != null) pstmt.close();
	 * 
	 * if (rs != null) rs.close();
	 * 
	 * if (con != null) con.close();
	 * 
	 * } catch (SQLException se) { se.printStackTrace(); } } return "";
	 * 
	 * }
	 */

	/*
	 * public ArrayList getLicenceList(Old_Stock_EntryAction ac, String val) {
	 * 
	 * 
	 * ArrayList list = new ArrayList(); Connection conn = null;
	 * PreparedStatement pstmt = null; ResultSet rs = null; String query = null;
	 * SelectItem item = new SelectItem(); item.setLabel("--select--");
	 * item.setValue(0); list.add(item);
	 * 
	 * try {
	 * 
	 * 
	 * 
	 * query = " SELECT int_app_id, vch_applicant_name, vch_firm_name ," +
	 * " vch_mobile_no,vch_license_type, vch_licence_no " +
	 * " FROM licence.fl2_2b_2d WHERE vch_license_type= '"+ac.getRadio()+"' ";
	 * 
	 * System.out.println("Q="+query); conn =
	 * ConnectionToDataBase.getConnection(); pstmt =
	 * conn.prepareStatement(query);
	 * 
	 * 
	 * rs = pstmt.executeQuery();
	 * 
	 * while (rs.next()) { item = new SelectItem();
	 * System.out.println("dadsadsda");
	 * item.setValue(rs.getString("int_app_id"));
	 * item.setLabel(rs.getString("vch_firm_name"));
	 * ac.setLicenceNo(rs.getString("vch_licence_no"));
	 * 
	 * list.add(item);
	 * 
	 * }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } finally { try {
	 * 
	 * if (conn != null) conn.close(); if (pstmt != null) pstmt.close(); if (rs
	 * != null) rs.close();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } } return list;
	 * 
	 * 
	 * }
	 */

	public ArrayList getDisListOup(Old_Stock_EntryAction ac) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("");
		list.add(item);
		try {

			String query = " SELECT int_app_id, vch_applicant_name, vch_firm_name ,"
					+ " vch_mobile_no,vch_license_type, vch_licence_no "
					+ " FROM licence.fl2_2b_2d ,public.district b  WHERE vch_license_type= '"
					+ ac.getRadio() + "' and b.districtid=core_district_id and   b.deo='"+ ResourceUtil.getUserNameAllReq().trim()+ "'  order by vch_applicant_name ";

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				item = new SelectItem();

				item.setValue(rs.getString("int_app_id"));
				item.setLabel(rs.getString("vch_firm_name") + " - "
						+ rs.getString("vch_licence_no"));
				 

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

	public ArrayList getAddRowData(Old_Stock_EntryAction ac, String lic_typ) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "	SELECT int_brewery_id, int_brand_id, int_pack_id, int_quantity,  "
				+ "	int_planned_bottles, int_boxes, int_liquor_type, "
				+ "	vch_license_type, plan_dt, licence_no, cr_date,seq "
				+ "		FROM bwfl.mst_bottling_plan where  int_brewery_id=? and vch_license_type=?  "
				+ "	    and  plan_dt = ? ";

		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(ac.getDistillery_id()));
			// ps.setString(2, ac.getLicenceType());
			ps.setString(2, lic_typ);

			ps.setDate(3,
					Utility.convertUtilDateToSQLDate(ac.getDateOfBottling()));
			rs = ps.executeQuery();
			while (rs.next()) {

				Old_Stock_EntryDT dt = new Old_Stock_EntryDT();

				dt.setBrandPackagingData_Brand(rs.getString("int_brand_id"));
				dt.setBrandPackagingData_Packaging(rs.getString("int_pack_id"));
				dt.setBrandPackagingData_Quantity(rs.getString("int_quantity"));
				dt.setBrandPackagingData_PlanNoOfBottling(rs
						.getInt("int_planned_bottles"));
				dt.setBrandPackagingData_NoOfBoxes(rs.getInt("int_boxes"));

				dt.setSeq(rs.getInt("seq"));

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

	// ----------------------------- get Brand
	// --------------------------------------------

	public ArrayList getBrandName() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		Old_Stock_EntryAction bof = (Old_Stock_EntryAction) facesContext
				.getApplication()
				.createValueBinding("#{old_Stock_EntryAction}")
				.getValue(facesContext);

		String lic = bof.getRadio();

		// String licNo=bof.getLicenceNoId();

		/*
		 * System.out.println("---------- 00 00 lic  00 -------------"+lic);
		 * 
		 * System.out.println("---------- brand mthd  lic id -------------"+licNo
		 * );
		 */
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
			/*
			 * if(lic.equalsIgnoreCase("FL2")) { sql=
			 * "select x.brand_id,x.brand_name from "+
			 * "	(SELECT brand_id, brand_name FROM distillery.brand_registration where license_category in('IMFL') "
			 * + "	union all "+
			 * "	SELECT brand_id, brand_name FROM distillery.brand_registration where int_bwfl_id not  in(0) "
			 * + "	union all "+
			 * "	SELECT brand_id, brand_name FROM distillery.brand_registration where int_fl2d_id not  in(0))x "
			 * + "	order by  brand_name";
			 * 
			 * } if(lic.equalsIgnoreCase("FL2B")) { sql=
			 * "	SELECT brand_id, brand_name FROM distillery.brand_registration "
			 * + "  where license_category='BEER' "+
			 * 
			 * "     order by  brand_name" ;
			 * 
			 * }if(lic.equalsIgnoreCase("CL2")) {
			 */
			// sql=
			// "	SELECT distinct  brand_id, brand_name FROM distillery.brand_registration "+
			// "  where license_category='CL' "+

			// "     order by  brand_name" ;

			// }
			sql = "select x.brand_id,x.brand_name from (             "
					+ " SELECT distinct a.brand_id,( a.brand_name||' - '||b.brewery_nm) as brand_name             "
					+ " FROM distillery.brand_registration a,public.bre_mst_b1_lic b                      "
					+ "  where a.license_category in ('BEER') and a.distillery_id=b.vch_app_id_f 	    and coalesce(a.int_bwfl_id,0)=0 	  "
					+ "  union                                                                           "
					+ "   SELECT distinct a.brand_id,( a.brand_name||' - '||b.vch_firm_name||', '||b.vch_firm_district_name) as brand_name   "
					+ " FROM distillery.brand_registration a,bwfl.registration_of_bwfl_lic_holder b    "
					+ "  where a.license_category in ('BEER','LAB') and a.int_bwfl_id=b.int_id  and a.distillery_id=0          "
					+ "   union                                                                          "
					+ "   SELECT distinct a.brand_id,( a.brand_name||' - '||b.vch_firm_name||', '||c.description) as brand_name        "
					+ " FROM distillery.brand_registration a,licence.fl2_2b_2d b  ,public.district c                       "
					+ "  where a.license_category in ('IMPORTEDBEER','LAB') and a.int_fl2d_id=b.int_app_id  and b.core_district_id =c.districtid   "
					+ " union                                                                          "
					+ "   SELECT distinct a.brand_id,( a.brand_name||' - '||b.vch_undertaking_name) as brand_name        "
					+ " FROM distillery.brand_registration a,public.dis_mst_pd1_pd2_lic b                        "
					+ "  where a.license_category in ('LAB') and a.distillery_id=b.int_app_id_f  )x  "
					+ " order by  x.brand_name ";
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("brand_name"));
				item.setValue(rs.getString("brand_id"));
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

	// ----------------------------- get Packaging Name
	// --------------------------------------------

	public ArrayList getPackagingName(String brand_id) {
		Old_Stock_EntryAction ac = new Old_Stock_EntryAction();
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);
		String SQl = "SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id,b.code_generate_through "
				+ "	from distillery.brand_registration a , "
				+ "	distillery.packaging_details b "
				+ "	where a.brand_id=b.brand_id_fk  " +
				// "	and a.distillery_id=? "+
				"	and brand_id =? ";
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
				+ "	from distillery.brand_registration a , "
				+ "	distillery.packaging_details b "
				+ "	where a.brand_id=b.brand_id_fk  " +
				// "	and a.distillery_id=?  "+
				"	and brand_id =?  and b.package_id=?";
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);
			// ps.setInt(1, this.getSugarmill_Id());
			ps.setInt(1, Integer.parseInt(brand_Id));
			ps.setInt(2, Integer.parseInt(packging_Id));
			rs = ps.executeQuery();
			while (rs.next()) {

				System.out.println("rs.getDoublequantity"
						+ rs.getDouble("quantity"));
				item = new SelectItem();
				item.setLabel(rs.getString("quantity"));
				item.setValue(rs.getDouble("quantity"));
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

			"SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id, case when coalesce(b.export_box_size,0) > 0  "
					+ " then b.export_box_size else c.box_size  end as box_size "
					+ "	from distillery.brand_registration a , "
					+ "	distillery.packaging_details b,distillery.box_size_details c "
					+ "	where a.brand_id=b.brand_id_fk  "
					+ "	and b.box_id=c.box_id  "
					+ "	and brand_id =?  and b.package_id=?";

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

	public int getsize(String brand_Id, String packging_Id) {
		int qty = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		FacesContext facesContext = FacesContext.getCurrentInstance();
		Old_Stock_EntryAction ac = (Old_Stock_EntryAction) facesContext
				.getApplication().getVariableResolver()
				.resolveVariable(facesContext, "old_Stock_EntryAction");

		try {

			String queryList =

			"SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id,b.code_generate_through, case when coalesce(b.export_box_size,0) > 0  "
					+ " then b.export_box_size else c.box_size  end as box_size "
					+ "	from distillery.brand_registration a , "
					+ "	distillery.packaging_details b,distillery.box_size_details c "
					+ "	where a.brand_id=b.brand_id_fk  "
					+ "	and b.box_id=c.box_id  "
					+ "	and brand_id =?  and b.package_id=?";

			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, Integer.parseInt(brand_Id));
			pstmt.setInt(2, Integer.parseInt(packging_Id));

			rs = pstmt.executeQuery();

			while (rs.next()) {

				qty = rs.getInt("box_size");
				ac.setEtin(rs.getString("code_generate_through"));

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

	public void save(Old_Stock_EntryAction action) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		int seq = maxId();
		int status = 0;

		String query = "";

		String sql = "	INSERT INTO fl2d.wholesale_30_02_2018_stock( "
				+ "			id, type, brand_id, pckg_id, recv_total_bottels)  "
				+ "			VALUES (?, ?, ?, ?, ? )  "
				+ "			ON CONFLICT ON CONSTRAINT fl2_2b_receiving_stock_master_manage_pkey1 "
				+ "do update set recv_total_bottels=  COALESCE(fl2d.wholesale_30_02_2018_stock.recv_total_bottels,0.0) + ?";

		String sql2 = "	INSERT INTO fl2d.wholesale_30_02_2018_stock_trxn ( "
				+ "	seq, brand_id, size_ml, recv_box, recv_bottels, breakage_bottels,  "
				+ "    total_recv_bottels, pckg_id, created_date, box_size, rcvdt,"
				+ "  fl2_2btype, fl2_2bid, loginusr, licence_no, etin,bottling_seq_frm,bottling_seq_to,case_seq_frm,case_seq_to)"
				+ "	VALUES (?,  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?) ";

		try {

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql2);
			pstmt1 = conn.prepareStatement(sql);

			for (int i = 0; i < action.getBrandPackagingDataList().size(); i++) {
				Old_Stock_EntryDT dt = (Old_Stock_EntryDT) action
						.getBrandPackagingDataList().get(i);

				long bottleCode = getOldStockBottleCode(dt
						.getBrandPackagingData_PlanNoOfBottling()
						+ dt.getBrandPackagingData_Breakage());
				long caseCode = getOldStockCaseNo(dt
						.getBrandPackagingData_NoOfBoxes());
				pstmt.setInt(1, ++seq);
				pstmt.setInt(2,
						Integer.parseInt(dt.getBrandPackagingData_Brand()));
				pstmt.setLong(3,
						new Double(dt.getBrandPackagingData_Quantity())
								.longValue());
				pstmt.setDouble(4, dt.getBrandPackagingData_NoOfBoxes());
				pstmt.setDouble(5, dt.getBrandPackagingData_PlanNoOfBottling());
				pstmt.setDouble(6, dt.getBrandPackagingData_Breakage());
				pstmt.setDouble(7, dt.getBrandPackagingData_PlanNoOfBottling());
				pstmt.setInt(8,
						Integer.parseInt(dt.getBrandPackagingData_Packaging()));
				pstmt.setDate(9, Utility.convertUtilDateToSQLDate(action
						.getDateOfBottling()));
				pstmt.setDouble(10, ((dt
						.getBrandPackagingData_PlanNoOfBottling() + dt
						.getBrandPackagingData_Breakage()) / dt
						.getBrandPackagingData_NoOfBoxes()));
				pstmt.setDate(11, Utility.convertUtilDateToSQLDate(new Date()));
				pstmt.setString(12, action.getRadio());
				pstmt.setInt(13, Integer.parseInt(action.getDistillery_id()));
				pstmt.setString(14, ResourceUtil.getUserNameAllReq().trim());
				pstmt.setString(16, action.getEtin());
				pstmt.setString(15, action.getLicenceNo());
				pstmt.setString(17, String.valueOf(bottleCode));
				pstmt.setString(18, String.valueOf(bottleCode
						+ (dt.getBrandPackagingData_PlanNoOfBottling() )));
				pstmt.setString(19, String.valueOf(caseCode));
				pstmt.setString(20, String.valueOf(caseCode
						+ (dt.getBrandPackagingData_NoOfBoxes()  )));

				System.out.println("etin=====>" + action.getEtin());

				status = pstmt.executeUpdate();

				/*if (status > 0) {
					status = 0;
					pstmt1.setInt(1, action.getDistillery_id());
					pstmt1.setString(2, action.getRadio());
					pstmt1.setInt(3,
							Integer.parseInt(dt.getBrandPackagingData_Brand()));
					pstmt1.setInt(4, Integer.parseInt(dt
							.getBrandPackagingData_Packaging()));
					pstmt1.setDouble(5,
							dt.getBrandPackagingData_PlanNoOfBottling());
					pstmt1.setDouble(6,
							dt.getBrandPackagingData_PlanNoOfBottling());
				 
					status = pstmt1.executeUpdate();
					 
				}*/

			}
			if (status > 0) {
				conn.setAutoCommit(true);
				ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,
						Constants.SAVED_SUCESSFULLY);
				action.getBrandPackagingDataList().clear();
			} else {
				conn.rollback();
				ResourceUtil.addMessage(Constants.NOT_SAVED,
						Constants.NOT_SAVED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public int maxId() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(seq) as id FROM fl2d.wholesale_30_02_2018_stock_trxn ";
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

	public ArrayList getShowDataTable(Old_Stock_EntryAction action) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int k = 0;
		String sql =

		"select a.seq,a.box_size,a.finalize_flag,a.recv_box,a.recv_bottels,a.created_date,b.brand_name,c.package_name,c.quantity ,a.etin,a.transfer ,a.brand_id ,a.pckg_id,a.fl2_2bid  "
				+ "from fl2d.wholesale_30_02_2018_stock_trxn a,                                                 "
				+ "distillery.brand_registration b,                                                         "
				+ "distillery.packaging_details c where a.pckg_id=c.package_id                              "
				+ "and a.brand_id=c.brand_id_fk                                                             "
				+ "and c.brand_id_fk=b.brand_id and    a.loginusr='"+ResourceUtil.getUserNameAllReq()+"' and  a.fl2_2btype=? order by a.seq desc ";
		try {
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(sql);
		 
			pstmt.setString(1, action.getRadio());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				Fl2ScanningShowDataTable flsd = new Fl2ScanningShowDataTable();
				flsd.setBrandName(rs.getString("brand_name"));
				flsd.setPackageName(rs.getString("package_name"));
				flsd.setPlan_seq(rs.getInt("seq"));
				flsd.setNo_of_box(rs.getInt("recv_box"));
				flsd.setNo_of_planned_bottle(rs.getInt("recv_bottels"));
				flsd.setQuantity(rs.getInt("quantity"));
				flsd.setPlan_date(Utility.convertSqlDateToUtilDate(rs
						.getDate("created_date")));
				flsd.setFinalizedFlag(rs.getString("finalize_flag"));
				flsd.setTransferFlag(rs.getString("transfer"));
				flsd.setBox_size(rs.getInt("box_size"));
				flsd.setEtin(rs.getString("etin"));
				if (rs.getDate("created_date") != null) {
				Date dat = Utility.convertSqlDateToUtilDate(rs
						.getDate("created_date"));
				DateFormat formatter = new SimpleDateFormat("yyMMdd");
				String date = formatter.format(dat);
				flsd.setFinalizedDateString(date);
				}
				flsd.setBranid(rs.getInt("brand_id"));
				flsd.setPckid(rs.getInt("pckg_id"));
				flsd.setFl2id(rs.getInt("fl2_2bid"));
				flsd.setSrNo(++k);
				list.add(flsd);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public synchronized long getOldStockCaseNo() {
		String sql = " select     nextval('public.fl3_3a_old_stock_case_code')";

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

	public synchronized long getOldStockCaseNo(long noOfSequenc) {
		String sql = " select     nextval('public.fl3_3a_old_stock_case_code')";
		String sqll = "ALTER SEQUENCE public.fl3_3a_old_stock_case_code RESTART WITH ? ";
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
						.prepareStatement("ALTER SEQUENCE public.fl3_3a_old_stock_case_code RESTART WITH "
								+ (noOfSequenc + serialNo));

				System.out
						.println("ALTER SEQUENCE public.fl3_3a_old_stock_case_code RESTART WITH "
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

	public synchronized long getOldStockBottleCode(long noOfSequenc) {
		String sql = " select     nextval('public.old_stock_bottle_code')";
		String sqll = "ALTER SEQUENCE public.old_stock_bottle_code RESTART WITH ? ";
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
						.prepareStatement("ALTER SEQUENCE public.old_stock_bottle_code RESTART WITH "
								+ (noOfSequenc + serialNo));

				System.out
						.println("ALTER SEQUENCE public.old_stock_bottle_code RESTART WITH "
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

	public void finalizeData(Old_Stock_EntryAction action,
			Fl2ScanningShowDataTable dt) {

		Connection conn1 = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";

		int status = 0;

		String bottle = "";

		System.out.println("come in impl");

		String update = "update  fl2d.wholesale_30_02_2018_stock_trxn set finalize_flag='F' where seq="
				+ dt.getPlan_seq()
				+ " and etin='"
				+ dt.getEtin()
				+ "' and created_date=?";

		String select = "SELECT seq, brand_id, size_ml, recv_box, recv_bottels, breakage_bottels, total_recv_bottels, pckg_id, created_date, box_size, "
				+ "rcvdt, fl2_2btype, fl2_2bid, loginusr, licence_no, etin, bottling_seq_frm, bottling_seq_to, case_seq_frm, case_seq_to, finalize_flag "
				+ "FROM fl2d.wholesale_30_02_2018_stock_trxn where seq=? and etin=? and created_date=?";

		if (action.getRadio().equalsIgnoreCase("FL2B")) {
			query = " INSERT INTO bottling_unmapped.fl2_2b_cl2_unmap  ( date_plan, etin, "
					+ " casecode,flid,type,bottle_code) VALUES (?,?,?,?,?,?) ";
		}

		else if (action.getRadio().equalsIgnoreCase("FL2")) {
			query = " INSERT INTO bottling_unmapped.fl2_unmap  ( date_plan, etin, "
					+ " casecode,flid,type,bottle_code) VALUES (?,?,?,?,?,?) ";
		}

		else if (action.getRadio().equalsIgnoreCase("CL2")) {
			query = " INSERT INTO bottling_unmapped.cl2_unmap  ( date_plan, etin, "
					+ " casecode,flid,type,bottle_code) VALUES (?,?,?,?,?,?) ";
		}

		try {

			conn = ConnectionToDataBase.getConnection();
			conn1 = ConnectionToDataBase.getConnection3();

			conn1.setAutoCommit(false);

			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(update);

			/*
			 * pstmt.setInt(1,dt.getPlan_seq()); pstmt.setString(2,
			 * dt.getEtin());
			 */
			pstmt.setDate(1,
					Utility.convertUtilDateToSQLDate(dt.getPlan_date()));

			status = pstmt.executeUpdate();
			System.out.println("come in impl status" + status);
			if (status > 0) {

				status = 0;

				pstmt = conn.prepareStatement(select);

				pstmt.setInt(1, dt.getPlan_seq());
				pstmt.setString(2, dt.getEtin());
				pstmt.setDate(3,
						Utility.convertUtilDateToSQLDate(dt.getPlan_date()));
				rs = pstmt.executeQuery();

				if (rs.next()) {

					long bootlecode = Long.parseLong(rs
							.getString("bottling_seq_frm"));
					long caseseq = Long.parseLong(rs.getString("case_seq_frm"));

					pstmt = conn1.prepareStatement(query);

					for (int i = 0; i < dt.getNo_of_box(); i++) {

						pstmt.setDate(1, Utility.convertUtilDateToSQLDate(dt
								.getPlan_date()));
						pstmt.setString(2, dt.getEtin());
						pstmt.setString(3, String.valueOf(caseseq + i));
						pstmt.setInt(4, dt.getPlan_seq());
						pstmt.setString(5, action.getRadio());

						for (long k = bootlecode; k < bootlecode
								+ (dt.getBox_size() - 1); k++) {
							System.out.println("come in impl rs ===" + k);

							bottle = bottle + "|" + k;

						}

						bootlecode = bootlecode + dt.getBox_size();
						pstmt.setString(6, bottle);
						status = pstmt.executeUpdate();
						System.out.println("come in impl rs status" + status);

					}
				}

								if (status > 0) {

					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("Finalized SuccessFully",
									"Finalized SuccessFully"));
					conn.commit();
					conn1.commit();
				} else {
					FacesContext.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage("Not Finalized ",
											"Not Finalized "));
				}

			}

		} catch (Exception e) {

			try {

				conn.rollback();
				conn1.rollback();

			} catch (Exception e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		} finally {

			try {

				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (conn1 != null)
					conn1.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
	public void transfer(Old_Stock_EntryAction action,
			Fl2ScanningShowDataTable dt) {

		Connection conn1 = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";

		int status = 0;

		String bottle = "";

		 

		String update = "update  fl2d.wholesale_30_02_2018_stock_trxn set transfer='F' where seq="
				+ dt.getPlan_seq()
				+ " and etin='"
				+ dt.getEtin()
				+ "' and created_date=?";

		String sql = "	INSERT INTO fl2d.wholesale_30_02_2018_stock( "
				+ "			id, type, brand_id, pckg_id, recv_total_bottels)  "
				+ "			VALUES (?, ?, ?, ?, ? )  "
				+ "			ON CONFLICT ON CONSTRAINT fl2_2b_receiving_stock_master_manage_pkey1 "
				+ "do update set recv_total_bottels=  COALESCE(fl2d.wholesale_30_02_2018_stock.recv_total_bottels,0.0) + ?";

		try {

			conn = ConnectionToDataBase.getConnection();
		 

		 
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(update);

			 
			pstmt.setDate(1,
					Utility.convertUtilDateToSQLDate(dt.getPlan_date()));

			status = pstmt.executeUpdate();
			if (status > 0) {
				status = 0;
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql=="+sql);
				pstmt.setInt(1,dt.getFl2id());
				pstmt.setString(2,"FL2B");
				pstmt.setInt(3,dt.getBranid());
				pstmt.setInt(4, dt.getPckid());
				pstmt.setDouble(6,dt.getNo_of_planned_bottle());
				pstmt.setDouble(5,dt.getNo_of_planned_bottle());
			 
				status = pstmt.executeUpdate();
				 
			}
								if (status > 0) {

					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("Transfered SuccessFully",
									"Transfered SuccessFully"));
					conn.commit();
					 
				} else {conn.rollback();
					FacesContext.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage("Not Finalized ",
											"Not Finalized "));
				}

			 

		} catch (Exception e) {

			try {

				conn.rollback();
				 

			} catch (Exception e1) {
				e1.printStackTrace();
			}

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
				e.printStackTrace();
			}

		}

	}
	public boolean write(Fl2ScanningShowDataTable dt,
			Old_Stock_EntryAction action) {

		 
		

		String fl3 =	"SELECT seq, brand_id, size_ml, recv_box, recv_bottels, breakage_bottels, total_recv_bottels, pckg_id, created_date, box_size, "
				+ "rcvdt, fl2_2btype, fl2_2bid, loginusr, licence_no, etin, bottling_seq_frm, bottling_seq_to, case_seq_frm, case_seq_to, finalize_flag "
				+ "FROM fl2d.wholesale_30_02_2018_stock_trxn where seq=? and etin=? and created_date=?";


		
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
		 Connection conn=null;long lastbottle=0;
		try {

			

		 

				conn=ConnectionToDataBase.getConnection();
				pstmt = conn.prepareStatement(fl3);
				pstmt.setInt(1, dt.getPlan_seq());
				pstmt.setString(2, dt.getEtin());
				pstmt.setDate(3,Utility.convertUtilDateToSQLDate(dt.getPlan_date()));
				
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
						.getDate("created_date"));

				DateFormat formatter;

				formatter = new SimpleDateFormat("yyMMdd");
				date = formatter.format(dat);
				
				long casecode_from=	Long.parseLong(rs.getString("case_seq_frm"));
				long casecode_to=Long.parseLong(rs.getString("case_seq_to"));
				
				long bottle_code=Long.parseLong(rs.getString("bottling_seq_frm"));
				
				
					for(long m=casecode_from;m<casecode_to;m++)
					{
					
					 
					
						for(long q=bottle_code;q<bottle_code+dt.getBox_size();q++)
						{
					
							 
				 
							lastbottle=q+1;
				 

				k++;
				XSSFRow row1 = worksheet.createRow((int) k);

				XSSFCell cellA1 = row1.createCell((int) 0);
				cellA1.setCellValue(k);

				XSSFCell cellB1 = row1.createCell((int) 1);
				cellB1.setCellValue(rs.getString("etin"));

				XSSFCell cellC1 = row1.createCell((int) 2);
				cellC1.setCellValue(date);
				// cellC1.setCellStyle(unlockcellStyle);

				XSSFCell cellD1 = row1.createCell((int) 3);
			 
				
				if (String.valueOf(dt.getBox_size()).length()==2) {
					noOfUnit = String.valueOf(dt.getBox_size());
				}else if (String.valueOf(dt.getBox_size()).length()==1) {
					noOfUnit = 0+""+String.valueOf(dt.getBox_size());
				}
				
				cellD1.setCellValue("01" + rs.getString("etin") + "13"
						+ date + "37" + noOfUnit + "21"
						+ m);

				XSSFCell cellE1 = row1.createCell((int) 4);
				cellE1.setCellValue("01" + rs.getString("etin") + "13"
						+ date + "21" + q);

			}
			

			bottle_code=lastbottle;

					}	}
			XSSFRow row1 = worksheet.createRow((int) k + 1);
			XSSFCell cellA1 = row1.createCell((int) 0);
			cellA1.setCellValue("End");
			fileOut = new FileOutputStream(relativePath + "//ExciseUp//Excel//"+dt.getPlan_seq()+""
					+ dt.getEtin() + "" + date + ".xls");
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			flag = true;

		} catch (Exception e) {

			System.out.println("xls2" + e.getMessage());
			e.printStackTrace();
		}
		finally {

			try {

				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	public boolean writeCsv(Fl2ScanningShowDataTable dt,
			Old_Stock_EntryAction action ) {
		FileWriter fw = null;
		Connection conn=null;
		String fl3 = /*
					 * "" +
					 * 
					 * "	select to_char(y.gs, 'fm000000000000')as GENERATE_SERIES ,y.distillery_id, y.dispatch_date, y.gtin_no,"
					 * +
					 * "	  y.serial_no_start, y.serial_no_end, y.licence_no, y.licence_type,"
					 * + "	to_char(y.case_no , 'fm0000000000')as case_no from( "
					 * +
					 * "	select  GENERATE_SERIES(x.serial_no_start ,x.serial_no_end ) as gs ,x.serial_no_start ,x.serial_no_end,x.licence_no, x.licence_type, "
					 * + "	x.case_no,x.dispatch_date,x.distillery_id,x.gtin_no "
					 * + "	from ( " +
					 * "	SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no "
					 * + "	FROM public.bottling_fl3 a " +
					 * "	where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=? and  seq="
					 * +dt.getPlanid()+")x)y";
					 */
				
				
				
				
				

		"SELECT seq, brand_id, size_ml, recv_box, recv_bottels, breakage_bottels, total_recv_bottels, pckg_id, created_date, box_size, "
				+ "rcvdt, fl2_2btype, fl2_2bid, loginusr, licence_no, etin, bottling_seq_frm, bottling_seq_to, case_seq_frm, case_seq_to, finalize_flag "
				+ "FROM fl2d.wholesale_30_02_2018_stock_trxn where seq=? and etin=? and created_date=?";

		String relativePath = Constants.JBOSS_SERVER_PATH
				+ Constants.JBOSS_LINX_PATH;
		FileOutputStream fileOut = null;
		 conn=ConnectionToDataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long start = 0;
		long end = 0;
		boolean flag = false;
		
		String noOfUnit = "";
		String date = null;
		long lastbottle=0;
		try {

			pstmt = conn.prepareStatement(fl3);
			pstmt.setInt(1, dt.getPlan_seq());
			pstmt.setString(2, dt.getEtin());
			pstmt.setDate(3,Utility.convertUtilDateToSQLDate(dt.getPlan_date()));
			
			rs = pstmt.executeQuery();

			DateFormat formatter1;

			
			
			
			
			
			
			formatter1 = new SimpleDateFormat("yyMMdd");
			String date1 = formatter1.format(dt.getPlan_date());

			fw = new FileWriter(relativePath + "//ExciseUp//Excel//"
					+ dt.getPlan_seq() + dt.getEtin() + "" + date1 + ".csv");

			int i = 0;
			
			
			
			
			

			while (rs.next()) {
				
				
				
			long casecode_from=	Long.parseLong(rs.getString("case_seq_frm"));
			long casecode_to=Long.parseLong(rs.getString("case_seq_to"));
			
			long bottle_code=Long.parseLong(rs.getString("bottling_seq_frm"));
			
			
				for(long m=casecode_from;m<casecode_to;m++)
				{
				
				 
				
					for(long q=bottle_code;q<bottle_code+dt.getBox_size();q++)
					{
				
						 
						
						
						lastbottle=q+1;
				Date dat = Utility.convertSqlDateToUtilDate(rs
						.getDate("created_date"));

				DateFormat formatter;

				formatter = new SimpleDateFormat("yyMMdd");
				date = formatter.format(dat);

				
				
				

				fw.append(rs.getString("etin"));

				fw.append(",");

				fw.append(date);
				fw.append(",");

				/*if (dt.getShowDataTable_Quntity().equals("750")) {
					noOfUnit = "12";
				} else if (dt.getShowDataTable_Quntity().equals("375")) {
					noOfUnit = "24";
				} else if (dt.getShowDataTable_Quntity().equals("180")) {
					noOfUnit = "48";
				} else if (dt.getShowDataTable_Quntity().equals("60")) {
					noOfUnit = "15";
				} else if (dt.getShowDataTable_Quntity().equals("50")) {
					noOfUnit = "19";
				} else if (dt.getShowDataTable_Quntity().equals("200")) {
					noOfUnit = "45";
				} else if (dt.getShowDataTable_Quntity().equals("90")) {
					noOfUnit = "96";
				} else if (dt.getShowDataTable_Quntity().equals("1000")) {
					noOfUnit = "09";
				} else if (dt.getShowDataTable_Quntity().equals("2000")) {
					noOfUnit = "04";
				} else if (dt.getShowDataTable_Quntity().equals("500")) {
					noOfUnit = "24";
				}*/
				
				if (String.valueOf(dt.getBox_size()).length()==2) {
					noOfUnit = String.valueOf(dt.getBox_size());
				}else if (String.valueOf(dt.getBox_size()).length()==1) {
					noOfUnit = 0+""+String.valueOf(dt.getBox_size());
				}
				
				
				

					fw.append("01" + rs.getString("etin") + "13" + date
							+ "37" + noOfUnit.substring(0, 2) + "21"
							+ m);
				
				fw.append(",");

				
				
				
				fw.append("01" + rs.getString("etin") + "13" + date + "21"
						+ q);
				fw.append("\n");

			}
					
					bottle_code=lastbottle;
					 
				}
			fw.flush();
			fw.close();
			flag = true;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}finally {

			try {

				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return flag;
	}

}
