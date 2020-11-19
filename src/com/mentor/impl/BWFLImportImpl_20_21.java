package com.mentor.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mentor.action.BWFLImportAction_20_21;
import com.mentor.datatable.BWFLImportDataTable_20_21;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class BWFLImportImpl_20_21 {
	public int getId(BWFLImportAction_20_21 action) {
		int id = 0;
		Connection con = null;
		Connection con2 = null;
		PreparedStatement ps = null, ps1 = null;
		ResultSet rs = null, rs1 = null;
		String sql = "";

		int rolDist = 0;
		int rolBrewry = 0;

		try {
			con = ConnectionToDataBase.getConnection();

			sql = " SELECT etin_unit_id,unit_id,int_id, mobile_number, vch_license_type, vch_firm_name, vch_firm_add "
					+ " FROM bwfl.registration_of_bwfl_lic_holder_20_21 " + " WHERE vch_approval='V' AND "
					+ " vch_distillery_contact_number='" + ResourceUtil.getUserNameAllReq().trim() + "' ";
			System.out.println("permit basic sql=" + sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {

				action.setDistillery_id(rs.getString("int_id"));
				action.setDistillery_nm(rs.getString("vch_firm_name"));
				action.setDistillery_adrs(rs.getString("vch_firm_add"));
				action.setEtin_unit_id(rs.getInt("unit_id"));
				if (rs.getString("vch_license_type").equals("1")) {
					action.setLicenceType("BWFL2A");
				} else if (rs.getString("vch_license_type").equals("2")) {
					action.setLicenceType("BWFL2B");
				} else if (rs.getString("vch_license_type").equals("3")) {
					action.setLicenceType("BWFL2C");
				} else if (rs.getString("vch_license_type").equals("4")) {
					action.setLicenceType("BWFL2D");
				}
			}

		} catch (Exception se) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));

			se.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
				if (con2 != null)
					con2.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return id;
	}

	public ArrayList getLicense() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);
		String sql = "SELECT vch_license_number,int_id,int_distillery_id FROM bwfl.registration_of_bwfl_lic_holder_20_21 "
				+ "WHERE vch_distillery_contact_number='" + ResourceUtil.getUserNameAllReq().trim() + "' "
				+ "ORDER BY vch_distillery_contact_number ASC ";
		try {
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("vch_license_number"));
				item.setValue(rs.getInt("int_id"));
				list.add(item);
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return list;
	}

	public String getIddeo(BWFLImportAction_20_21 action) {
		int id = 0;
		Connection con = null;
		Connection con2 = null;
		PreparedStatement ps = null, ps1 = null;
		ResultSet rs = null, rs1 = null;
		String sql = "";

		int rolDist = 0;
		String rolBrewry = null;

		try {
			con = ConnectionToDataBase.getConnection();

			sql = " SELECT  b.description,b.districtid "
					+ " FROM bwfl.registration_of_bwfl_lic_holder_20_21 ,public.district b"
					+ " WHERE vch_approval='V' AND " + " int_id=" + Integer.parseInt(action.getDistillery_id())
					+ " and  vch_firm_district_id::int=b.districtid and b.deo='" + ResourceUtil.getUserNameAllReq()
					+ "'";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {

				rolBrewry = (rs.getString("description"));
			}

		} catch (Exception se) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));

			se.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
				if (con2 != null)
					con2.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return rolBrewry;
	}

	public int getIdMapped(BWFLImportAction_20_21 action) {
		int id = 0;
		Connection con = null;
		Connection con2 = null;
		PreparedStatement ps = null, ps1 = null;
		ResultSet rs = null, rs1 = null;
		String sql = "";

		int rolDist = 0;
		int rolBrewry = 0;

		try {
			con = ConnectionToDataBase.getConnection();

			sql = " SELECT int_id, vch_distillery_contact_number, vch_license_type, vch_firm_name, vch_firm_add "
					+ " FROM bwfl.registration_of_bwfl_lic_holder_20_21 " + " WHERE vch_approval='V' AND "
					+ " vch_distillery_contact_number='" + ResourceUtil.getUserNameAllReq().trim() + "' ";
			// System.out.println("mapped basic sql=" + sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {

				action.setDistillery_id(rs.getString("int_id"));
				action.setDistillery_nm(rs.getString("vch_firm_name"));
				action.setDistillery_adrs(rs.getString("vch_firm_add"));
				if (rs.getString("vch_license_type").equals("1")) {
					action.setLicenceType("BWFL2A");
				} else if (rs.getString("vch_license_type").equals("2")) {
					action.setLicenceType("BWFL2B");
				} else if (rs.getString("vch_license_type").equals("3")) {
					action.setLicenceType("BWFL2C");
				} else if (rs.getString("vch_license_type").equals("4")) {
					action.setLicenceType("BWFL2D");
				}
			}

		} catch (Exception se) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));

			se.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
				if (con2 != null)
					con2.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return id;
	}

	// //////////// ---------------------------m didt id ----------------------

	// ------------------------------------------------------------------------------

	// ----------------------------- get Liqure Type
	// --------------------------------------------

	public ArrayList getLiqureType() {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);
		String SQl = "select id, description from distillery.imfl_type   order by id ";
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("description"));
				item.setValue(rs.getString("id"));
				list.add(item);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

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

	// ----------------------------- get Licence NO.
	// --------------------------------------------

	public String getLicenseNo(BWFLImportAction_20_21 action, String id) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String list = "";

		String val = null;
		if (action.getLicenceType().equalsIgnoreCase("1")) {
			val = "BWFL2A";
		}
		if (action.getLicenceType().equalsIgnoreCase("2")) {
			val = "BWFL2B";
		}
		if (action.getLicenceType().equalsIgnoreCase("3")) {
			val = "BWFL2C";
		}
		if (action.getLicenceType().equalsIgnoreCase("4")) {
			val = "BWFL2D";
		}
		// query pahele se thi maine bs distiliry id se unit change kiya h
		String SQl = " SELECT DISTINCT COALESCE(license_number,'NA') as license_number FROM distillery.brand_registration_20_21 "
				+ " WHERE int_bwfl_id =" + action.getUnit_id() + "  and vch_license_type= '" + val + "'";

		try {

			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);

			// System.out.println("SQl---------license nmbr--------" + SQl);
			// ps.setString(1, lice.trim());

			rs = ps.executeQuery();
			if (rs.next()) {

				list = rs.getString("license_number");
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

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

	public ArrayList getUnit(String id) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);

		String SQl = " SELECT int_id, mobile_number, vch_license_type, vch_firm_name, vch_firm_add  "
				+ " FROM bwfl.registration_of_bwfl_lic_holder_20_21, public.district b  "
				+ " WHERE vch_approval='V' AND " + " vch_license_type= '" + id
				+ "'  and  vch_firm_district_id::int=b.districtid and b.deo='" + ResourceUtil.getUserNameAllReq() + "'";

		try {

			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);

			rs = ps.executeQuery();
			while (rs.next()) {

				item = new SelectItem();
				item.setLabel(rs.getString("vch_firm_name") + " ( " + rs.getString("mobile_number") + " )");
				item.setValue(rs.getString("int_id"));

				list.add(item);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

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
		BWFLImportAction_20_21 bof = (BWFLImportAction_20_21) facesContext.getApplication()
				.createValueBinding("#{bWFLImportAction_20_21}").getValue(facesContext);

		String lic = bof.getLicenceType();

		String licNo = bof.getLicenceNum();
		// int unit_id=bof.getUnit_id();
		/*
		 * //System.out.println("---------- brand mthd  lic id -------------" + licNo);
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

			if (lic.equalsIgnoreCase("1")) {
				sql =

						"	SELECT brand_id, brand_name FROM distillery.brand_registration_20_21 "
								+ "  where vch_license_type='BWFL2A' " + "   and int_bwfl_id =?  "
								+ "     order by brand_id ";

				System.out.println("BWFL2A" + bof.getUnit_id());

			} else if (lic.equalsIgnoreCase("2")) {
				sql =

						"	SELECT brand_id, brand_name FROM distillery.brand_registration_20_21 "
								+ "  where vch_license_type='BWFL2B' " + "   and int_bwfl_id =?   "
								+ "     order by brand_id ";

				System.out.println("BWFL2B");

			}

			else if (lic.equalsIgnoreCase("3")) {

				sql =

						"	SELECT brand_id, brand_name FROM distillery.brand_registration_20_21 "
								+ "  where vch_license_type='BWFL2C' " + "   and int_bwfl_id =?   "
								+ "     order by brand_id ";

				System.out.println("BWFL2C");

			} else if (lic.equalsIgnoreCase("4")) {

				sql =

						"	SELECT brand_id, brand_name FROM distillery.brand_registration_20_21 "
								+ "  where vch_license_type='BWFL2D' " + "   and int_bwfl_id =?   "
								+ "     order by brand_id ";
				System.out.println("BWFL2D");

			}
			System.out.println("sql=" + sql);
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			// ps.setInt(1, Integer.parseInt(bof.getDistillery_id()));
			// System.out.println("unit id dt="+bof.getUnit_id());
			// System.out.println("licNo.trim()="+licNo.trim());
			ps.setInt(1, bof.getUnit_id());
			// ps.setString(2, (licNo.trim()));
			// System.out.println("licNo -- " + licNo.trim());
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("brand_name"));
				item.setValue(rs.getString("brand_id"));
				list.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

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
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);

		String SQl = "SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id "
				+ "	from distillery.brand_registration_20_21 a , " + "	distillery.packaging_details_20_21 b "
				+ "	where a.brand_id=b.brand_id_fk  " +
				 " and b.mrp >0  "+
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
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

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
						+ "	from distillery.brand_registration_20_21 a , " + "	distillery.packaging_details_20_21 b "
						+ "	where a.brand_id=b.brand_id_fk and b.mrp >0  " +
						// " and a.distillery_id=? "+
						"	and brand_id =?  and b.package_id=?";
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);
			// ps.setInt(1, this.getSugarmill_Id());
			ps.setInt(1, Integer.parseInt(brand_Id));
			ps.setInt(2, Integer.parseInt(packging_Id));
			rs = ps.executeQuery();
			while (rs.next()) {

				// //System.out.println("rs.getDoublequantity"+
				// rs.getDouble("quantity"));
				item = new SelectItem();
				item.setLabel(rs.getString("quantity"));
				item.setValue(rs.getDouble("quantity"));
				list.add(item);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

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

	public String getqty(String brand_Id, String packging_Id, BWFLImportDataTable_20_21 dt) {
		String qty = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList =

					" SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id ,b.mrp "
							+ "	from distillery.brand_registration_20_21 a , "
							+ "	distillery.packaging_details_20_21 b " + "	where a.brand_id=b.brand_id_fk  and b.mrp >0  " +
							// " and a.distillery_id=? "+
							"	and brand_id =?  and b.package_id=?";

			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, Integer.parseInt(brand_Id));
			pstmt.setInt(2, Integer.parseInt(packging_Id));

			rs = pstmt.executeQuery();

			while (rs.next()) {

				qty = rs.getString("quantity");
				/*
				 * if(rs.getDouble("mrp")>6000){ dt.setEntry_no_of_bottle_per_case(1);
				 * dt.setNoOfBottleFlg(true); }else{ dt.setNoOfBottleFlg(false); }
				 */
			}

			// pstmt.executeUpdate();
		} catch (SQLException se) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));

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

	// ---------------------------------------------- save
	// --------------------------
	public int maxId() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(seq) as id FROM bwfl_license.mst_bottling_plan_20_21 ";
		int maxid = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxid = rs.getInt("id");
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

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

	// =====================get max id sequence==============================

	public int maxDutyRegId() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(int_id) as id FROM distillery.duty_register_19_20 ";
		int maxid = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxid = rs.getInt("id");
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

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

	public void save(BWFLImportAction_20_21 action) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		int challanId = maxId();
		String query = "";
		String insDutyQr = "";
		double totalPermitFees = 0.0;
		int totalBottles = 0;

		int dutyId = this.maxDutyRegId();

		try {

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			/*
			 * if(this.checkData(action.getPermitno_enteredUpdate(),
			 * action.getVch_license_typeUpdate(), action.getInt_distillery_idUpdate(),
			 * action.getInt_brand_idUpdate(), action.getInt_pack_idUpdate(),
			 * action.getSeqUpdate(), action.getLicence_noUpdate(),
			 * action.getGatepass_noUpdate(), action.getPermitDatUpdate())==true) {
			 * 
			 * query = " UPDATE bwfl_license.mst_bottling_plan_20_21 SET " +
			 * " int_quantity=?, int_planned_bottles=?, int_boxes=?,   licence_no=?,  " +
			 * " cr_date=?,  permitdt=?, maped_unmaped_flag=? ,validity_date=? " +
			 * 
			 * " where permitno_entered='786' and vch_license_type='BWFL2B' and int_distillery_id='95' and int_brand_id='2015' "
			 * +
			 * " and int_pack_id='9623' and seq='6236' and licence_no='29/2018-19'  and gatepass_no='6236' and permitdt='2018-11-17'  "
			 * ;
			 * 
			 * //System.out.println("==============update wala"+query); for (int i = 0; i <
			 * action.getBrandPackagingDataList().size(); i++) { pstmt =
			 * conn.prepareStatement(query);
			 * 
			 * BWFLImportDataTable_20_21 table = (BWFLImportDataTable_20_21)
			 * action.getBrandPackagingDataList().get(i);
			 * 
			 * 
			 * pstmt.setDouble(1,
			 * Double.parseDouble(table.getBrandPackagingData_Quantity()));
			 * pstmt.setInt(2,table.getBrandPackagingData_PlanNoOfBottling());
			 * pstmt.setInt(3, table.getBrandPackagingData_NoOfBoxes());
			 * 
			 * 
			 * pstmt.setString(4, action.getLicenceNum());
			 * pstmt.setDate(5,Utility.convertUtilDateToSQLDate(new Date()));
			 * 
			 * 
			 * 
			 * pstmt.setDate(6, Utility.convertUtilDateToSQLDate(action.getPermitdt()));
			 * pstmt.setString(7, action.getBottlngType());
			 * 
			 * pstmt.setDate(8, Utility.convertUtilDateToSQLDate(action.getValidityDate()));
			 * 
			 * saveStatus = pstmt.executeUpdate(); }
			 * 
			 * 
			 * 
			 * }else {
			 */
			query = " INSERT INTO bwfl_license.mst_bottling_plan_20_21"
					+ " (int_distillery_id, int_brand_id, int_pack_id, int_quantity, int_planned_bottles, "
					+ " int_boxes, vch_license_type, plan_dt, licence_no, cr_date, int_liquor_type, "
					+ " received_liqour, permitno, permitdt, seq, gatepass_no, maped_unmaped_type, etin, "
					+ " validity_date, permitno_entered, seqfrom, seqto, caseseq, entry_no_of_bottle_per_case, districtid, permit_fee,unit_id) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?,"// 14
					+ challanId + ", " + challanId + ", ?, ?, ?, ?,?,?,?,?," + action.getDistrict() + ", ?,?) ";

			insDutyQr = " INSERT INTO distillery.duty_register_19_20( "
					+ " int_id, int_distillery_id, date_crr_date, vch_duty_type, int_quantity, int_value, vch_description,permitno_bwfl) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?,?)";

			if (action.getBrandPackagingDataList().size() > 0) {

				for (int i = 0; i < action.getBrandPackagingDataList().size(); i++) {

					saveStatus = 0;
					pstmt = conn.prepareStatement(query);
					int j = 1;

					BWFLImportDataTable_20_21 table = (BWFLImportDataTable_20_21) action.getBrandPackagingDataList().get(i);

					if (table.isUpdateflg() == false) {

						pstmt.setInt(1, Integer.parseInt(action.getDistillery_id()));
						pstmt.setInt(2, Integer.parseInt(table.getBrandPackagingData_Brand()));
						pstmt.setInt(3, Integer.parseInt(table.getBrandPackagingData_Packaging()));
						pstmt.setDouble(4, Double.parseDouble(table.getBrandPackagingData_Quantity()));
						pstmt.setInt(5, table.getBrandPackagingData_PlanNoOfBottling());
						pstmt.setInt(6, table.getBrandPackagingData_NoOfBoxes());
						if (action.getLicenceType().equalsIgnoreCase("1")) {
							pstmt.setString(7, "BWFL2A");
						} else if (action.getLicenceType().equalsIgnoreCase("2")) {
							pstmt.setString(7, "BWFL2B");
						} else if (action.getLicenceType().equalsIgnoreCase("3")) {
							pstmt.setString(7, "BWFL2C");
						} else if (action.getLicenceType().equalsIgnoreCase("4")) {
							pstmt.setString(7, "BWFL2D");
						}
						action.setPlan_dt(new Date());
						pstmt.setDate(8, Utility.convertUtilDateToSQLDate(new Date()));
						pstmt.setString(9, action.getLicenceNum());
						pstmt.setDate(10, Utility.convertUtilDateToSQLDate(new Date()));

						if (action.getLicenceType().equalsIgnoreCase("1")) {
							pstmt.setInt(11, 1);
						} else if (action.getLicenceType().equalsIgnoreCase("2")) {
							pstmt.setInt(11, 2);
						} else if (action.getLicenceType().equalsIgnoreCase("3")) {
							pstmt.setInt(11, 3);
						} else if (action.getLicenceType().equalsIgnoreCase("4")) {
							pstmt.setInt(11, 4);
						}
						pstmt.setInt(12, 0);
						pstmt.setString(13,
								this.getIddeo(action) + "-2019-20-" + action.getPermitNoEnterd() + "-" + challanId);
						pstmt.setDate(14, Utility.convertUtilDateToSQLDate(action.getPermitdt()));
						pstmt.setString(15, action.getBottlngType());
						pstmt.setString(16, table.getBrandPackagingData_etinNmbr());
						pstmt.setDate(17, Utility.convertUtilDateToSQLDate(action.getValidityDate()));
						pstmt.setString(18, action.getPermitNoEnterd());

						/*
						 * long serialno = getSerialNo(new Double(
						 * table.getBrandPackagingData_PlanNoOfBottling())
						 * .longValue(),action.getPlan_dt());
						 * 
						 * pstmt.setLong(19, serialno); pstmt.setLong(20,serialno+ new
						 * Double(table.getBrandPackagingData_PlanNoOfBottling()).longValue());
						 * pstmt.setLong(21,getcaseNo(new
						 * Double(table.getBrandPackagingData_NoOfBoxes()).longValue(),action.getPlan_dt
						 * ()));
						 */

						/*
						 * long serialno = getSerialNo(new Double(
						 * table.getBrandPackagingData_PlanNoOfBottling())
						 * .longValue(),action.getPlan_dt());
						 */

						pstmt.setLong(19, 0);
						pstmt.setLong(20, 0);
						pstmt.setLong(21, 0);

						pstmt.setLong(22, table.getEntry_no_of_bottle_per_case());

						pstmt.setDouble(23, table.getCalPermitFee_dt());
						pstmt.setDouble(24, action.getUnit_id());
						saveStatus = pstmt.executeUpdate();

						totalPermitFees += table.getCalPermitFee_dt();
						totalBottles += table.getBrandPackagingData_PlanNoOfBottling();
					}
				}
			}

			if (saveStatus > 0) {
				saveStatus = 0;
				pstmt = conn.prepareStatement(insDutyQr);

				pstmt.setInt(1, dutyId);
				pstmt.setInt(2, Integer.parseInt(action.getDistillery_id()));
				pstmt.setDate(3, Utility.convertUtilDateToSQLDate(action.getPermitdt()));
				// pstmt.setString(4, "BWFL_PERMIT_FEE");
				pstmt.setDouble(5, totalBottles);
				pstmt.setDouble(6, totalPermitFees);

				if (action.getLicenceType().equalsIgnoreCase("1")) {
					pstmt.setString(4, "BWFL2A_PERMIT_FEE_IMFL");
					pstmt.setString(7, "Import Fee on FL");
					// pstmt.setString(7, "BWFL2A-"+action.getPermitNoEnterd());
				} else if (action.getLicenceType().equalsIgnoreCase("2")) {
					pstmt.setString(4, "BWFL2B_PERMIT_FEE_IMFL");
					pstmt.setString(7, "Import Fee on beer");
					// pstmt.setString(7, "BWFL2B-"+action.getPermitNoEnterd());
				} else if (action.getLicenceType().equalsIgnoreCase("3")) {
					pstmt.setString(4, "BWFL2C_PERMIT_FEE_IMFL");
					pstmt.setString(7, "Import Fee on wine");
					// pstmt.setString(7, "BWFL2C-"+action.getPermitNoEnterd());
				} else if (action.getLicenceType().equalsIgnoreCase("4")) {
					pstmt.setString(4, "BWFL2D_PERMIT_FEE_IMFL");
					pstmt.setString(7, "Import Fee on lab");
					// pstmt.setString(7, "BWFL2D-"+action.getPermitNoEnterd());
				}
				// pstmt.setDouble(8,action.getUnit_id());
				pstmt.setString(8, this.getIddeo(action) + "-2019-20-" + action.getPermitNoEnterd() + "-" + challanId);

				saveStatus = pstmt.executeUpdate();
			}

			// }
			if (saveStatus > 0) {
				conn.commit();
				ResourceUtil.addErrorMessage(Constants.SAVED_SUCESSFULLY, Constants.SAVED_SUCESSFULLY);
				action.reset();

			} else {
				conn.rollback();
				// action.reset();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED, Constants.NOT_SAVED);

			}

		}

		catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
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

	}

	public synchronized long getcaseNo(Date date) {
		// String sql = " select nextval('public.bwfl_manual_case_code')";

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		long serialNo = 0;
		long currseq = 0;

		try {
			conn = ConnectionToDataBase.getConnection19_20();

			Date today = date;
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
			String currentdate = sdf.format(today);
			pstmt2 = conn.prepareStatement("CREATE SEQUENCE IF NOT EXISTS public.bwfl_manual_case_code_" + currentdate);
			pstmt2.executeUpdate();

			pstmt = conn.prepareStatement(" select     nextval('public.bwfl_manual_case_code_" + currentdate + "')");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				serialNo = rs.getLong(1);
				if (serialNo == 0) {
					serialNo = serialNo;

				}
				/*
				 * pstmt = conn .prepareStatement("ALTER SEQUENCE public.bwfl_manual_case_code_"
				 * +currentdate+" RESTART WITH " + (no + serialNo));
				 * 
				 * 
				 * pstmt.executeUpdate();
				 */
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

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
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			}
		}

		return serialNo;
	}

	public void update(BWFLImportAction_20_21 action) {

		// System.out.println("hello------------------------fama");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		// int challanId = maxId();
		String query = "";
		double totalPermitFees = 0.0;
		int totalBottles = 0;
		try {

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			/*
			 * query = " UPDATE bwfl_license.mst_bottling_plan_20_21 SET " +
			 * " int_quantity=?, int_planned_bottles=?, int_boxes=?,   licence_no=?,  " +
			 * " cr_date=?,  permitdt=?, maped_unmaped_type=? ,validity_date=? " +
			 * 
			 * " where permitno_entered='"+action.getPermitno_enteredUpdate()+
			 * "' and vch_license_type='"
			 * +action.getVch_license_typeUpdate()+"' and int_distillery_id='" +action
			 * .getInt_distillery_idUpdate()+"' and int_brand_id='"+action
			 * .getInt_brand_idUpdate()+"' "+
			 * " and int_pack_id='"+action.getInt_pack_idUpdate ()+"' and seq='"+action
			 * .getSeqUpdate()+"' and licence_no='"+action .getLicence_noUpdate()+
			 * "'  and gatepass_no='"+action.getGatepass_noUpdate()+"'" + " and etin='"
			 * +action.getEtinUpdate()+"' and    permitdt='"+action.
			 * getPermitDatUpdate()+"'  " ;
			 */

			query = " UPDATE bwfl_license.mst_bottling_plan_20_21 SET "
					+ " int_quantity=?, int_planned_bottles=?, int_boxes=?,   licence_no=?,  "
					+ " cr_date=?,  permitdt=?, maped_unmaped_type=? ,validity_date=?,entry_no_of_bottle_per_case=?, permit_fee=? "
					+

					" where permitno_entered='" + action.getPermitno_enteredUpdate() + "' and vch_license_type='"
					+ action.getVch_license_typeUpdate() + "' and int_distillery_id='"
					+ action.getInt_distillery_idUpdate() + "' and int_brand_id=? " + " and int_pack_id=? and seq='"
					+ action.getSeqUpdate() + "' and licence_no='" + action.getLicence_noUpdate()
					+ "'  and gatepass_no='" + action.getGatepass_noUpdate() + "'" + " and     permitdt='"
					+ action.getPermitDatUpdate() + "' AND (finalized_flag!='F' or finalized_flag IS NULL)  ";

			// System.out.println("==============update------------"+ query);
			for (int i = 0; i < action.getBrandPackagingDataList().size(); i++) {
				pstmt = conn.prepareStatement(query);

				BWFLImportDataTable_20_21 table = (BWFLImportDataTable_20_21) action.getBrandPackagingDataList().get(i);

				// System.out.println("==============getBrandPackagingData_Quantity()====="+
				// table.getBrandPackagingData_Quantity());
				pstmt.setInt(1, Integer.parseInt(table.getBrandPackagingData_Quantity()));
				pstmt.setInt(2, table.getBrandPackagingData_PlanNoOfBottling());
				pstmt.setInt(3, table.getBrandPackagingData_NoOfBoxes());

				pstmt.setString(4, action.getLicenseNoNew());
				pstmt.setDate(5, Utility.convertUtilDateToSQLDate(new Date()));

				pstmt.setDate(6, Utility.convertUtilDateToSQLDate(action.getPermitdt()));
				pstmt.setString(7, action.getBottlngType());
				pstmt.setDate(8, Utility.convertUtilDateToSQLDate(action.getValidityDate()));
				pstmt.setLong(9, table.getEntry_no_of_bottle_per_case());
				pstmt.setDouble(10, table.getCalPermitFee_dt());

				pstmt.setInt(11, Integer.parseInt(table.getBrandPackagingData_Brand()));
				pstmt.setInt(12, Integer.parseInt(table.getBrandPackagingData_Packaging()));

				saveStatus = pstmt.executeUpdate();
				totalPermitFees += table.getCalPermitFee_dt();
				totalBottles += table.getBrandPackagingData_PlanNoOfBottling();

			}
			if (saveStatus > 0) {
				saveStatus = 0;
				String sql = "update distillery.duty_register_19_20 set int_quantity='" + totalBottles + "',int_value='"
						+ totalPermitFees + "' where int_id='" + action.getDutyRegisterId() + "'";
				// System.out.println("updateInDutyReg="+sql);
				pstmt = conn.prepareStatement(sql);
				saveStatus = pstmt.executeUpdate();
			}
			if (saveStatus > 0) {
				conn.commit();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Updated Sucessfully", "Updated Sucessfully"));
				action.reset();
			} else {
				conn.rollback();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Not Updated ", "Not Updated "));
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
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

	}

	// --------------------------------------------

	/*
	 * public void save1(BWFLImportAction_20_21 action) { Connection conn = null;
	 * PreparedStatement pstmt = null; ResultSet rs = null; int saveStatus = 0; //
	 * int challanId=getMaxChallanId()+1; try {
	 * 
	 * String query =
	 * 
	 * "			INSERT INTO distillery.mst_bottling_plan_20_21( " +
	 * "			int_distillery_id, int_brand_id, int_pack_id, int_quantity, int_planned_bottles, "
	 * +
	 * "		    int_boxes, int_liquor_type, vch_license_type, plan_dt, licence_no, cr_date) "
	 * + "		VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?) ";
	 * 
	 * // String // delete=
	 * " delete from distillery.mst_bottling_plan_20_21 where  int_distillery_id=? and vch_license_type =? and plan_dt =?"
	 * ;
	 * 
	 * conn = ConnectionToDataBase.getConnection();
	 * 
	 * if (action.getBrandPackagingDataList().size() > 0) { for (int i = 0; i <
	 * action.getBrandPackagingDataList().size(); i++) { saveStatus = 0; pstmt =
	 * conn.prepareStatement(query); int j = 1;
	 * 
	 * BWFLImportDataTable_20_21 table = (BWFLImportDataTable_20_21) action
	 * .getBrandPackagingDataList().get(i); //
	 * pstmt.setInt(1,getMaxChallanIdDetail()+1); pstmt.setInt(1,
	 * action.getDistillery_id()); pstmt.setInt(2, Integer.parseInt(table
	 * .getBrandPackagingData_Brand())); pstmt.setInt(3, Integer.parseInt(table
	 * .getBrandPackagingData_Packaging()));
	 * 
	 * pstmt.setDouble(4, Double.parseDouble(table
	 * .getBrandPackagingData_Quantity())); pstmt.setDouble(5,
	 * table.getBrandPackagingData_PlanNoOfBottling()); pstmt.setDouble(6,
	 * table.getBrandPackagingData_NoOfBoxes()); pstmt.setInt(7,
	 * Integer.parseInt(action.getLiqureTypeId())); pstmt.setString(8,
	 * action.getLicenceType()); pstmt.setDate(9,
	 * Utility.convertUtilDateToSQLDate(action .getDateOfBottling()));
	 * pstmt.setString(10, action.getLicenceNoId()); pstmt.setDate(11,
	 * Utility.convertUtilDateToSQLDate(new Date()));
	 * 
	 * saveStatus = pstmt.executeUpdate(); } }
	 * 
	 * if (saveStatus > 0) {
	 * ResourceUtil.addErrorMessage(Constants.SAVED_SUCESSFULLY,
	 * Constants.SAVED_SUCESSFULLY); action.reset();
	 * 
	 * } else { // action.reset(); ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
	 * Constants.NOT_SAVED);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * catch(Exception e) { e.printStackTrace(); }
	 * 
	 * 
	 * catch (SQLException e) { FacesContext .getCurrentInstance() .addMessage(
	 * null, new FacesMessage(
	 * " Same Date Same Brand and Same Packaging  Not Allow For Plan ",
	 * "Same Date Same Brand and Same Packaging Not Allow For Plan")); //
	 * brand_Id,Distillery_ID // and // DAte // PK
	 * 
	 * // brand_Id,Distillery_ID and DAte PK }
	 * 
	 * finally { try {
	 * 
	 * if (conn != null) conn.close(); if (pstmt != null) pstmt.close(); if (rs !=
	 * null) rs.close();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 * 
	 * }
	 */

	// ---------------------- get add Row data -----------------------------

	public ArrayList getAddRowData(BWFLImportAction_20_21 ac, String lic_typ) {
		if (ac.getDistillery_id() == null) {
			ac.setDistillery_id("0");
		}
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// System.out.println("distillery_id="+ac.getDistillery_id());
		String sql = "	SELECT int_distillery_id, int_brand_id, int_pack_id, int_quantity,  "
				+ "	int_planned_bottles, int_boxes, int_liquor_type, "
				+ "	vch_license_type, plan_dt, licence_no, cr_date "
				+ "	FROM bwfl_license.mst_bottling_plan_20_21 where int_distillery_id='"
				+ Integer.parseInt(ac.getDistillery_id()) + "' " + " and vch_license_type='" + ac.getLicenceType()
				+ "' " + " and plan_dt = '" + Utility.convertUtilDateToSQLDate(ac.getPermitdt()) + "'  "
				+ " and licence_no='" + ac.getLicenceNum() + "' and permitno='" + ac.getPermitno() + "' ";
		//// System.out.println("getAddRowData="+sql);
		try {

			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			/*
			 * ps.setInt(1, ac.getDistillery_id()); // ps.setString(2, ac.getLicenceType());
			 * ps.setString(2, lic_typ);
			 * 
			 * ps.setDate(3, );
			 */
			rs = ps.executeQuery();

			while (rs.next()) {

				BWFLImportDataTable_20_21 dt = new BWFLImportDataTable_20_21();
				dt.setUpdateflg(true);

				dt.setBrandPackagingData_Brand(rs.getString("int_brand_id"));
				dt.setBrandPackagingData_Packaging(rs.getString("int_pack_id"));
				dt.setBrandPackagingData_Quantity(rs.getString("int_quantity"));
				dt.setBrandPackagingData_PlanNoOfBottling(rs.getInt("int_planned_bottles"));
				dt.setBrandPackagingData_NoOfBoxes(rs.getInt("int_boxes"));
				dt.setBrandPackagingData_Brand(rs.getString("int_brand_id"));
				// dt.setBrandPackagingData_Packaging(rs.getString(""));
				// ac.setLicenceNoId(rs.getString("licence_no").trim());
				ac.setCr_date(Utility.convertUtilDateToSQLDate(rs.getDate("cr_date")));

				ac.setCheckLicenceType(rs.getString("vch_license_type"));

				list.add(dt);
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

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

	// ------------------------------------ SHOW DATA
	// TABLE-----------------------

	public ArrayList getData(BWFLImportAction_20_21 ac) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		String licno = "";
		if (ac.getSelect_lic_no() == null) {
			licno = "0";
		} else {
			licno = ac.getSelect_lic_no();
		}

		sql = " SELECT COALESCE(a.cancel_req_dt_time,'0')cancel_req_dt_time, a.cr_date, a.caseseq, a.seqfrom,                     "
				+ " c.export_box_size, c.quantity, c.code_generate_through, coalesce(b.tracking_flg,'N') as tracking_flg, a.group_id, "
				+ " a.finalized_date, a.finalized_flag, a.permitno, a.seq, a.int_distillery_id, a.licence_no,                         "
				+ " replace(a.licence_no,' ','')as licencenoo, a.int_brand_id, b.brand_name, a.int_pack_id,c.package_name,            "
				+ " a.int_quantity, a.int_planned_bottles, a.int_boxes, a.int_liquor_type,                                            "
				+ " d.description, a.vch_license_type, a.plan_dt                                                                      "
				+ " FROM bwfl_license.mst_bottling_plan_20_21 a ,distillery.brand_registration_20_21 b,                               "
				+ " distillery.packaging_details_20_21 c, distillery.imfl_type d                                                      "
				+ " where a.int_brand_id=b.brand_id  and  b.brand_id=c.brand_id_fk and                                                "
				+ " a.int_pack_id=c.package_id and  a.int_liquor_type=d.id                                                            "
				+ " and b.int_bwfl_id =(select unit_id from bwfl.registration_of_bwfl_lic_holder_20_21 where int_id=?) AND            "
				+ " a.maped_unmaped_type='M' AND a.esign_flg='D' and  a.int_distillery_id='" + licno
				+ "'                                 " + " ORDER BY a.plan_dt DESC, a.seq  ";
		

		try {
			System.out.println("ac.getSelect_lic_no()"+ac.getSelect_lic_no());
			System.out.println("SQL"+sql);
			
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);

			try {
				ps.setInt(1, Integer.parseInt(ac.getSelect_lic_no()));
			} catch (Exception e) {
				ps.setInt(1, 0);
			}
			rs = ps.executeQuery();
			while (rs.next()) {

				BWFLImportDataTable_20_21 dt = new BWFLImportDataTable_20_21();
				dt.setCancel_req_dt_time(rs.getString("cancel_req_dt_time"));
				dt.setShowDataTable_Date(rs.getDate("plan_dt"));
				dt.setShowDataTable_LiqureType(rs.getString("description"));
				dt.setShowDataTable_LicenceType(rs.getString("vch_license_type"));
				dt.setShowDataTable_Brand(rs.getString("brand_name"));
				dt.setShowDataTable_Packging(rs.getString("package_name"));
				dt.setShowDataTable_Quntity(rs.getString("quantity"));
				dt.setBrandPackagingData_PlanNoOfBottling(rs.getInt("int_planned_bottles"));
				dt.setShowDataTable_PlanNoOfBottling(rs.getString("int_planned_bottles"));
				dt.setShowDataTable_NoOfBoxes(rs.getString("int_boxes"));
				dt.setBrandId(rs.getInt("int_brand_id"));
				dt.setPackagingId(rs.getInt("int_pack_id"));
				dt.setNewml(rs.getInt("quantity"));
				dt.setNewsize(rs.getInt("export_box_size"));
				dt.setLicenceNo(rs.getString("licence_no"));
				dt.setPermitnoD(rs.getString("permitno"));
				dt.setLicencenoo(rs.getString("licencenoo").replaceAll("/", ""));
				dt.setFinalizedFlag(rs.getString("finalized_flag"));
				dt.setFinalize_Date(Utility.convertSqlDateToUtilDate(rs.getDate("finalized_date")));
				dt.setGtinno(rs.getString("code_generate_through"));
				dt.setDistillery_id(rs.getString("int_distillery_id"));
				dt.setSeq(rs.getInt("seq"));
				// System.out.println("seq="+dt.getSeq());
				dt.setGroupseq(rs.getInt("group_id"));
				dt.setCr_date(Utility.convertSqlDateToUtilDate(rs.getDate("cr_date")));
				dt.setSeqfrm(rs.getLong("seqfrom"));
				dt.setCaseseq(rs.getLong("caseseq"));

				if (rs.getDate("finalized_date") != null) {
					Date dat = Utility.convertSqlDateToUtilDate(rs.getDate("finalized_date"));
					// //System.out.println("date finalize" + dat);

					DateFormat formatter = new SimpleDateFormat("yyMMdd");
					String date = formatter.format(dat);
					dt.setFinalizedDateString(date);
					// //System.out.println(date);
				}
				/*
				 * if (!rs.getString("tracking_flg").equalsIgnoreCase("Y")) {
				 * dt.setFinalizedFlag("N"); }
				 */
				list.add(dt);
			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

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

	// ---------------------------------
	// bool--------------------------------------

	public boolean ckeck(BWFLImportAction_20_21 ac) {

		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList =

					"	SELECT int_distillery_id, int_brand_id, int_pack_id, int_quantity,  "
							+ "	int_planned_bottles, int_boxes, int_liquor_type, "
							+ "	vch_license_type, plan_dt, licence_no, cr_date "
							+ "		FROM distillery.mst_bottling_plan_20_21 where  int_distillery_id=? and vch_license_type=?  "
							+ "	    and  plan_dt = ? ";

			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, Integer.parseInt(ac.getDistillery_id()));
			// ps.setString(2, ac.getLicenceType());
			pstmt.setString(2, ac.getLicenceType());

			pstmt.setDate(3, Utility.convertUtilDateToSQLDate(ac.getDateOfBottling()));

			rs = pstmt.executeQuery();

			if (rs.next()) {

				flag = true;
			}

			else {
				flag = false;
			}

		} catch (SQLException se) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));

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
		return flag;

	}

	public synchronized long getSerialNo(long noOfSequenc, Date date) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		long serialNo = 0;

		try {
			conn = ConnectionToDataBase.getConnection19_20();

			Date today = date;
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
			String currentdate = sdf.format(today);
			pstmt2 = conn.prepareStatement("CREATE SEQUENCE IF NOT EXISTS public.serial_seq_" + currentdate);
			pstmt2.executeUpdate();

			pstmt = conn.prepareStatement("select     nextval('public.serial_seq_" + currentdate + "')");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				serialNo = rs.getInt(1);
				if (serialNo == 0) {
					serialNo = serialNo + 1;
				}
				// //System.out.println("noOfSequenc " + noOfSequenc);

				pstmt1 = conn.prepareStatement("ALTER SEQUENCE public.serial_seq_" + currentdate + " RESTART WITH "
						+ (noOfSequenc + serialNo + 1));

				pstmt1.executeUpdate();

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

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
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			}
		}

		return serialNo;
	}

	public String getBrandPackagingGtinNo(BWFLImportAction_20_21 action, BWFLImportDataTable_20_21 dt) {

		String gtin = "";
		String sql = "select b.code_generate_through from distillery.brand_registration_20_21 a, distillery.packaging_details_20_21 b "
				+ "where  a.brand_id=b.brand_id_fk and a.brand_id=? and b.package_id=? and b.quantity=? and vch_license_type=? and a.int_bwfl_id"
				+
				// @rvind //" =?";
				// " in (select int_app_id_f from public.other_unit_registration_20_21 where
				// unit_id=?) ";
				"=(select unit_id from   bwfl.registration_of_bwfl_lic_holder_20_21 where int_id=?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dt.getBrandId());
			pstmt.setInt(2, dt.getPackagingId());
			pstmt.setInt(3, Integer.parseInt(dt.getShowDataTable_Quntity()));
			pstmt.setString(4, dt.getShowDataTable_LicenceType());
			pstmt.setInt(5, Integer.parseInt(dt.getDistillery_id()));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				gtin = rs.getString("code_generate_through");
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();

				if (pstmt != null)
					pstmt.close();

				if (con != null)
					con.close();

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			}
		}

		return gtin;

	}

	public int getBrandPackagingNoOfBottle(BWFLImportAction_20_21 action, BWFLImportDataTable_20_21 dt) {

		long boxid = 0;
		int boxSize = 0;
		String sql = "select box_id from distillery.brand_registration_20_21 a, distillery.packaging_details_20_21 b "
				+ "where  a.brand_id=b.brand_id_fk and b.mrp >0  and a.brand_id=? and b.package_id=? and b.quantity=? and vch_license_type=? and a.int_bwfl_id =?";

		String sql1 = "SELECT  box_size  " + " FROM distillery.box_size_details  where box_id=?";
		;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;

		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				boxid = Long.parseLong(rs.getString("box_id"));
			} else {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Packaging Not Found", "Packaging Not Found"));

			}
			if (boxid > 0) {
				pstmt1 = con.prepareStatement(sql1);
				rs1 = pstmt1.executeQuery();
				if (rs.next()) {

					boxSize = rs.getInt(1);

				} else {

					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Box Size Not Defined In Master", "Box Size Not Defined In Master"));

				}

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (rs1 != null)
					rs1.close();
				if (pstmt != null)
					pstmt.close();
				if (pstmt1 != null)
					pstmt1.close();
				if (con != null)
					con.close();

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			}

		}

		return boxSize;

	}

	
	public synchronized long getcaseNo() {
		String sql = " select     nextval('public.bwfl_manual_case_code')";

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		long serialNo = 0;
		long currseq = 0;

		try {
			conn = ConnectionToDataBase.getConnection19_20();

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				serialNo = rs.getLong(1);
				if (serialNo == 0) {
					serialNo = serialNo;
				}

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

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
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			}
		}

		return serialNo;
	}

	/*
	 * public void dataFinalize(BWFLImportAction_20_21 action, BWFLImportDataTable_20_21 dt) {
	 * Connection conn = null; Connection conn1 = null; PreparedStatement pstmt1 =
	 * null; PreparedStatement pstmt2 = null; PreparedStatement pstmt3 = null;
	 * PreparedStatement pstmt4 = null; String gtinNo = ""; long boxsize = 0; long
	 * caseno = 0; String sql = "INSERT INTO public.bottling_fl3(  " +
	 * "distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type,case_no) "
	 * + "	VALUES (?, ?, ?, ?, ?, ?, ?,?)";
	 * 
	 * int status = 0;
	 * 
	 * String fl3a = "INSERT INTO public.bottling_fl3a(  " +
	 * "distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type,case_no) "
	 * + "	VALUES (?, ?, ?, ?, ?, ?, ?,?)";
	 * 
	 * String cl = "INSERT INTO public.bottling_cl(  " +
	 * "distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type,case_no) "
	 * + "	VALUES (?, ?, ?, ?, ?, ?, ?,?)";
	 * 
	 * String update = "UPDATE distillery.mst_bottling_plan_20_21 " +
	 * " SET   finalized_flag='F' ,finalized_date=? " +
	 * "WHERE int_distillery_id=? and int_brand_id=? and int_pack_id=? and plan_dt=?"
	 * ;
	 * 
	 * try { gtinNo = getBrandPackagingGtinNo(action, dt); //
	 * boxsize=getBrandPackagingNoOfBottle(action,dt);
	 * 
	 * // long noOfBottle= //
	 * (Long.parseLong(dt.getShowDataTable_PlanNoOfBottling())/boxsize);
	 * 
	 * System.out
	 * .println("new Double(dt.getShowDataTable_PlanNoOfBottling()).longValue()" +
	 * new Double(dt.getShowDataTable_PlanNoOfBottling()) .longValue()); long
	 * serialno = getSerialNo(new Double(
	 * dt.getShowDataTable_PlanNoOfBottling()).longValue());
	 * 
	 * // long // i=(Long.parseLong(dt.getShowDataTable_PlanNoOfBottling())/(Long
	 * .parseLong(dt.getShowDataTable_NoOfBoxes()))); //System.out.println("gtinNo"
	 * + gtinNo + "seqqqqqqqqqqqqqqqqqqqqqqqq" + serialno); if (!gtinNo.equals("")
	 * && serialno != 0) { conn = ConnectionToDataBase.getConnection19_20(); conn1 =
	 * ConnectionToDataBase.getConnection(); conn.setAutoCommit(false);
	 * conn1.setAutoCommit(false); pstmt1 = conn1.prepareStatement(update);
	 * 
	 * pstmt1.setDate(1, new java.sql.Date(System.currentTimeMillis()));
	 * pstmt1.setInt(2, action.getDistillery_id()); pstmt1.setInt(3,
	 * dt.getBrandId()); pstmt1.setInt(4, dt.getPackagingId()); pstmt1.setDate(5,
	 * Utility.convertUtilDateToSQLDate(dt .getShowDataTable_Date())); status =
	 * pstmt1.executeUpdate();
	 * 
	 * if (dt.getShowDataTable_LicenceType().equals("FL3") && status > 0) { status =
	 * 0; pstmt1 = conn.prepareStatement(sql); for (int i = 0; i < Long.parseLong(dt
	 * .getShowDataTable_NoOfBoxes()); i++) { caseno = getcaseNo();
	 * 
	 * pstmt1.setInt(1, action.getDistillery_id());
	 * 
	 * pstmt1.setDate(2, Utility.convertUtilDateToSQLDate(dt
	 * .getShowDataTable_Date())); pstmt1.setString(3, gtinNo); pstmt1.setLong(4,
	 * serialno);
	 * 
	 * pstmt1.setString(6, dt.getLicenceNo()); pstmt1.setString(7,
	 * dt.getShowDataTable_LicenceType()); pstmt1.setLong(8, caseno); if
	 * (dt.getShowDataTable_Quntity().equals("750")) { pstmt1.setLong(5, serialno +
	 * 11);
	 * 
	 * serialno += 12; } else if (dt.getShowDataTable_Quntity().equals("375")) {
	 * pstmt1.setLong(5, serialno + 23); serialno += 24; } else if
	 * (dt.getShowDataTable_Quntity().equals("180")) { pstmt1.setLong(5, serialno +
	 * 47); serialno += 48; } else if (dt.getShowDataTable_Quntity().equals("60")) {
	 * pstmt1.setLong(5, serialno + 95); serialno += 96; } pstmt1.addBatch(); //
	 * status=pstmt1.executeUpdate(); } } if
	 * (dt.getShowDataTable_LicenceType().equals("FL3A") && status > 0) { status =
	 * 0; pstmt1 = conn.prepareStatement(fl3a); for (int i = 0; i <
	 * Long.parseLong(dt .getShowDataTable_NoOfBoxes()); i++) {
	 * 
	 * caseno = getcaseNo();
	 * 
	 * pstmt1.setInt(1, action.getDistillery_id());
	 * 
	 * pstmt1.setDate(2, Utility.convertUtilDateToSQLDate(dt
	 * .getShowDataTable_Date())); pstmt1.setString(3, gtinNo); pstmt1.setLong(4,
	 * serialno);
	 * 
	 * pstmt1.setString(6, dt.getLicenceNo()); pstmt1.setString(7,
	 * dt.getShowDataTable_LicenceType()); pstmt1.setLong(8, caseno); if
	 * (dt.getShowDataTable_Quntity().equals("750")) { pstmt1.setLong(5, serialno +
	 * 11);
	 * 
	 * serialno += 12; } else if (dt.getShowDataTable_Quntity().equals("375")) {
	 * pstmt1.setLong(5, serialno + 23); serialno += 24; } else if
	 * (dt.getShowDataTable_Quntity().equals("180")) { pstmt1.setLong(5, serialno +
	 * 47); serialno += 48; } else if (dt.getShowDataTable_Quntity().equals("60")) {
	 * pstmt1.setLong(5, serialno + 95); serialno += 96; } pstmt1.addBatch(); //
	 * status=pstmt1.executeUpdate();} } } if
	 * (dt.getShowDataTable_LicenceType().equals("CL") && status > 0) { status = 0;
	 * pstmt1 = conn.prepareStatement(cl); for (int i = 0; i < Long.parseLong(dt
	 * .getShowDataTable_NoOfBoxes()); i++) { caseno = getcaseNo();
	 * 
	 * pstmt1.setInt(1, action.getDistillery_id());
	 * 
	 * pstmt1.setDate(2, Utility.convertUtilDateToSQLDate(dt
	 * .getShowDataTable_Date())); pstmt1.setString(3, gtinNo); pstmt1.setLong(4,
	 * serialno);
	 * 
	 * pstmt1.setString(6, dt.getLicenceNo()); pstmt1.setString(7,
	 * dt.getShowDataTable_LicenceType()); pstmt1.setLong(8, caseno); if
	 * (dt.getShowDataTable_Quntity().equals("750")) { pstmt1.setLong(5, serialno +
	 * 11);
	 * 
	 * serialno += 12; } else if (dt.getShowDataTable_Quntity().equals("375")) {
	 * pstmt1.setLong(5, serialno + 23); serialno += 24; } else if
	 * (dt.getShowDataTable_Quntity().equals("180")) { pstmt1.setLong(5, serialno +
	 * 47); serialno += 48; } else if (dt.getShowDataTable_Quntity().equals("60")) {
	 * pstmt1.setLong(5, serialno + 95); serialno += 96; } else if
	 * (dt.getShowDataTable_Quntity().equals("200")) { pstmt1.setLong(5, serialno +
	 * 44); serialno += 96; } pstmt1.addBatch(); // status=pstmt1.executeUpdate(); }
	 * } int[] status1 = pstmt1.executeBatch(); if (status1.length > 0) { status =
	 * 0; boolean flag = write(dt, action, conn);
	 * 
	 * if (flag) { status = 1; } else {
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("Excel Not Generated", "Excel Not Generated")); } } if (status >
	 * 0) {
	 * 
	 * conn.commit(); conn1.commit();
	 * 
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("Finalized SuccessFully", "Finalized SuccessFully")); } else {
	 * conn.rollback(); conn1.rollback();
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("Not Finalized ", " Not Finalized ")); }
	 * 
	 * } else { FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("Gtin and Serial No Can Not Zero ",
	 * " Gtin and Serial No Can Not Zero")); } }
	 * 
	 * catch (Exception e) {
	 * 
	 * FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage(e.getMessage(), e.getMessage())); try { conn.rollback();
	 * conn1.rollback(); } catch (Exception e1) { e1.printStackTrace(); }
	 * 
	 * e.printStackTrace(); } finally {
	 * 
	 * try {
	 * 
	 * if (pstmt1 != null) pstmt1.close(); if (pstmt2 != null) pstmt2.close(); if
	 * (pstmt3 != null) pstmt3.close(); if (pstmt4 != null) pstmt4.close(); if (conn
	 * != null) conn.close(); if (conn1 != null) conn1.close();
	 * 
	 * } catch (Exception e) { FacesContext.getCurrentInstance().addMessage(null,
	 * new FacesMessage(e.getMessage(), e.getMessage())); e.printStackTrace(); } } }
	 */
	/*
	 * public void generateReport(BottlingOfPlanDataTable dt, BottlingOfPlanAction
	 * action) { Connection conn = null; PreparedStatement pstmt = null; ResultSet
	 * rs = null; String relativePath = Constants.JBOSS_SERVER_PATH +
	 * Constants.JBOSS_LINX_PATH + "//ExciseUp//"; JasperPrint print = null;
	 * 
	 * String FL3 = "" +
	 * " select to_char(GENERATE_SERIES, 'fm000000000000') as GENERATE_SERIES,distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type,  "
	 * + " to_char(case_no , 'fm0000000000')as case_no from " +
	 * 
	 * "(SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no,b.*   "
	 * + "FROM public.bottling_fl3 a, (select * from GENERATE_SERIES(?,?)) b   " +
	 * "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?  )x"
	 * ;
	 * 
	 * String FL3A =
	 * " select to_char(GENERATE_SERIES, 'fm000000000000') as GENERATE_SERIES,distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type,  "
	 * + " to_char(case_no , 'fm0000000000')as case_no from " +
	 * 
	 * "(SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no,b.*   "
	 * + "FROM public.bottling_fl3a a, (select * from GENERATE_SERIES(?,?)) b   " +
	 * "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?)x"
	 * ;
	 * 
	 * String cl =
	 * " select to_char(GENERATE_SERIES, 'fm000000000000') as GENERATE_SERIES,distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type,  "
	 * + " to_char(case_no , 'fm0000000000')as case_no from " +
	 * 
	 * "(SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no,b.*   "
	 * + "FROM public.bottling_cl a, (select * from GENERATE_SERIES(?,?)) b   " +
	 * "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?)x"
	 * ;
	 * 
	 * try { String minmax = getMinMaxSerialNo(dt, action);
	 * 
	 * String ar[] = minmax.split(","); conn =
	 * ConnectionToDataBase.getConnection19_20(); String noOfUnit = "";
	 * 
	 * if (dt.getShowDataTable_LicenceType().equals("FL3")) { pstmt =
	 * conn.prepareStatement(FL3); pstmt.setLong(1, Long.parseLong(ar[0]));
	 * pstmt.setLong(2, Long.parseLong(ar[1])); pstmt.setInt(3,
	 * action.getDistillery_id()); pstmt.setDate(4,
	 * Utility.convertUtilDateToSQLDate(dt.getFinalize_Date())); pstmt.setString(5,
	 * dt.getLicenceNo()); pstmt.setString(6, dt.getShowDataTable_LicenceType());
	 * pstmt.setLong(7, Long.parseLong(dt.getGtinno()));
	 * 
	 * rs = pstmt.executeQuery(); if (rs.next()) {
	 * 
	 * HashMap map = new HashMap(); map.put("disname", action.getDistillery_nm());
	 * map.put("dislryadd", action.getDistillery_adrs()); map.put("quantity",
	 * dt.getShowDataTable_Quntity()); map.put("brandName",
	 * dt.getShowDataTable_Brand()); Date dat = Utility.convertSqlDateToUtilDate(rs
	 * .getDate("dispatch_date")); //System.out.println("date finalize" + dat);
	 * DateFormat formatter;
	 * 
	 * formatter = new SimpleDateFormat("yyMMdd"); String date =
	 * formatter.format(dat); //System.out.println(date); map.put("date", date);
	 * 
	 * if (dt.getShowDataTable_Quntity().equals("750")) { noOfUnit = "12"; } else if
	 * (dt.getShowDataTable_Quntity().equals("375")) { noOfUnit = "24"; } else if
	 * (dt.getShowDataTable_Quntity().equals("180")) { noOfUnit = "48"; } else if
	 * (dt.getShowDataTable_Quntity().equals("60")) { noOfUnit = "12"; }
	 * map.put("barcode", "01" + rs.getLong("gtin_no") + "13" + date + "37" +
	 * noOfUnit + "21" + rs.getString("case_no")); map.put("barcodetext", "(01)" +
	 * rs.getLong("gtin_no") + "(13)" + date + "(37)" + noOfUnit + "(21)" +
	 * rs.getString("case_no")); rs = pstmt.executeQuery();
	 * 
	 * JRResultSetDataSource jrds = new JRResultSetDataSource(rs); print =
	 * JasperFillManager.fillReport(relativePath + "bottlingjasper" + File.separator
	 * + "BarcodeFl3.jasper", map, jrds); JasperExportManager.exportReportToPdfFile(
	 * print, relativePath + "bottling_pdf" + File.separator + "Barcode" +
	 * dt.getGtinno() + dt.getFinalizedDateString() + ".pdf");
	 * 
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("Print Report Successfully", "Print Report Successfully"));
	 * 
	 * } else {
	 * 
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("No Record Found", "No Record Found")); }
	 * 
	 * } if (dt.getShowDataTable_LicenceType().equals("CL")) { pstmt =
	 * conn.prepareStatement(cl); pstmt.setLong(1, Long.parseLong(ar[0]));
	 * pstmt.setLong(2, Long.parseLong(ar[1])); pstmt.setInt(3,
	 * action.getDistillery_id()); pstmt.setDate(4,
	 * Utility.convertUtilDateToSQLDate(dt.getFinalize_Date())); pstmt.setString(5,
	 * dt.getLicenceNo()); pstmt.setString(6, dt.getShowDataTable_LicenceType());
	 * pstmt.setLong(7, Long.parseLong(dt.getGtinno()));
	 * 
	 * rs = pstmt.executeQuery(); if (rs.next()) {
	 * 
	 * HashMap map = new HashMap(); map.put("disname", action.getDistillery_nm());
	 * map.put("dislryadd", action.getDistillery_adrs()); map.put("quantity",
	 * dt.getShowDataTable_Quntity()); map.put("brandName",
	 * dt.getShowDataTable_Brand()); Date dat = Utility.convertSqlDateToUtilDate(rs
	 * .getDate("dispatch_date")); //System.out.println("date finalize" + dat);
	 * DateFormat formatter;
	 * 
	 * formatter = new SimpleDateFormat("yyMMdd"); String date =
	 * formatter.format(dat); //System.out.println(date); map.put("date", date); if
	 * (dt.getShowDataTable_Quntity().equals("750")) { noOfUnit = "12"; } else if
	 * (dt.getShowDataTable_Quntity().equals("375")) { noOfUnit = "24"; } else if
	 * (dt.getShowDataTable_Quntity().equals("180")) { noOfUnit = "48"; } else if
	 * (dt.getShowDataTable_Quntity().equals("60")) { noOfUnit = "92"; } else if
	 * (dt.getShowDataTable_Quntity().equals("200")) { noOfUnit = "45"; }
	 * map.put("barcode", "01" + rs.getLong("gtin_no") + "13" + date + "37" +
	 * noOfUnit + "21" + rs.getString("case_no")); map.put("barcodetext", "(01)" +
	 * rs.getLong("gtin_no") + "(13)" + date + "(37)" + noOfUnit + "(21)" +
	 * rs.getString("case_no")); rs = pstmt.executeQuery();
	 * 
	 * JRResultSetDataSource jrds = new JRResultSetDataSource(rs); print =
	 * JasperFillManager.fillReport(relativePath + "bottlingjasper" + File.separator
	 * + "BarcodeCL.jasper", map, jrds); JasperExportManager.exportReportToPdfFile(
	 * print, relativePath + "bottling_pdf" + File.separator + "Barcode" +
	 * dt.getGtinno() + dt.getFinalizedDateString() + ".pdf");
	 * 
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("Print Report Successfully", "Print Report Successfully"));
	 * 
	 * } else {
	 * 
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("No Record Found", "No Record Found")); } } if
	 * (dt.getShowDataTable_LicenceType().equals("FL3A")) {
	 * 
	 * //System.out.println("Fl3 a Fl3Acome in"); pstmt =
	 * conn.prepareStatement(FL3A); pstmt.setLong(1, Long.parseLong(ar[0]));
	 * pstmt.setLong(2, Long.parseLong(ar[1])); pstmt.setInt(3,
	 * action.getDistillery_id()); pstmt.setDate(4,
	 * Utility.convertUtilDateToSQLDate(dt.getFinalize_Date())); pstmt.setString(5,
	 * dt.getLicenceNo()); pstmt.setString(6, dt.getShowDataTable_LicenceType());
	 * pstmt.setLong(7, Long.parseLong(dt.getGtinno()));
	 * 
	 * rs = pstmt.executeQuery(); if (rs.next()) {
	 * 
	 * //System.out.println("Fl3 a Fl3Acome in nextt"); HashMap map = new HashMap();
	 * map.put("disname", action.getDistillery_nm()); map.put("dislryadd",
	 * action.getDistillery_adrs()); map.put("quantity",
	 * dt.getShowDataTable_Quntity()); map.put("brandName",
	 * dt.getShowDataTable_Brand()); Date dat = Utility.convertSqlDateToUtilDate(rs
	 * .getDate("dispatch_date")); //System.out.println("date finalize" + dat);
	 * DateFormat formatter;
	 * 
	 * formatter = new SimpleDateFormat("yyMMdd"); String date =
	 * formatter.format(dat); //System.out.println(date); map.put("date", date); if
	 * (dt.getShowDataTable_Quntity().equals("750")) { noOfUnit = "12"; } else if
	 * (dt.getShowDataTable_Quntity().equals("375")) { noOfUnit = "24"; } else if
	 * (dt.getShowDataTable_Quntity().equals("180")) { noOfUnit = "48"; } else if
	 * (dt.getShowDataTable_Quntity().equals("60")) { noOfUnit = "92"; } else if
	 * (dt.getShowDataTable_Quntity().equals("200")) { noOfUnit = "45"; }
	 * map.put("barcode", "01" + rs.getLong("gtin_no") + "13" + date + "37" +
	 * noOfUnit + "21" + rs.getString("case_no")); map.put("barcodetext", "(01)" +
	 * rs.getLong("gtin_no") + "(13)" + date + "(37)" + noOfUnit + "(21)" +
	 * rs.getString("case_no")); rs = pstmt.executeQuery();
	 * 
	 * JRResultSetDataSource jrds = new JRResultSetDataSource(rs); print =
	 * JasperFillManager.fillReport(relativePath + "bottlingjasper" + File.separator
	 * + "BarcodeFl3A.jasper", map, jrds);
	 * JasperExportManager.exportReportToPdfFile( print, relativePath +
	 * "bottling_pdf" + File.separator + "Barcode" + dt.getGtinno() +
	 * dt.getFinalizedDateString() + ".pdf");
	 * 
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("Print Report Successfully", "Print Report Successfully"));
	 * 
	 * } else {
	 * 
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("No Record Found", "No Record Found")); }
	 * 
	 * }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	public String getMinMaxSerialNo(BWFLImportDataTable_20_21 dt, BWFLImportAction_20_21 action) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String fl3 = "SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no   "
				+ "FROM public.bottling_fl3 a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  ";

		String fl3a = "SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no   "
				+ "FROM public.bottling_fl3a a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  ";

		String cl = "SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no   "
				+ "FROM public.bottling_cl a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  ";

		String minMax = "";

		try {
			conn = ConnectionToDataBase.getConnection19_20();

			if (dt.getShowDataTable_LicenceType().equals("FL3")) {

				/*
				 * //System.out.println("fl3 min max"+ action.getDistillery_id()+ " date"+
				 * Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()) + " licenceno" +
				 * dt.getLicenceNo() + " licence type" + dt.getShowDataTable_LicenceType());
				 */
				pstmt = conn.prepareStatement(fl3);
				pstmt.setInt(1, Integer.parseInt(action.getDistillery_id()));
				pstmt.setDate(2, Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = rs.getString("serial_no_start") + "," + rs.getString("serial_no_end");
					// //System.out.println("minMax" + minMax);
				}
			}

			if (dt.getShowDataTable_LicenceType().equals("FL3A")) {

				/*
				 * //System.out.println("fl3a min max" + action.getDistillery_id() + " date" +
				 * Utility.convertUtilDateToSQLDate(dt .getFinalize_Date()) + " licenceno" +
				 * dt.getLicenceNo() + " licence type" + dt.getShowDataTable_LicenceType());
				 */
				pstmt = conn.prepareStatement(fl3a);
				pstmt.setInt(1, Integer.parseInt(action.getDistillery_id()));
				pstmt.setDate(2, Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = rs.getString("serial_no_start") + "," + rs.getString("serial_no_end");
					// //System.out.println("minMax" + minMax);
				}
			}
			if (dt.getShowDataTable_LicenceType().equals("CL")) {
				/*
				 * //System.out.println("cl min max" + action.getDistillery_id() + " date" +
				 * Utility.convertUtilDateToSQLDate(dt .getFinalize_Date()) + " licenceno" +
				 * dt.getLicenceNo() + " licence type" + dt.getShowDataTable_LicenceType());
				 */
				pstmt = conn.prepareStatement(cl);
				pstmt.setInt(1, Integer.parseInt(action.getDistillery_id()));
				pstmt.setDate(2, Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = rs.getString("serial_no_start") + "," + rs.getString("serial_no_end");
					// //System.out.println("minMax" + minMax);
				}
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

			e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		return minMax;
	}

	public String getMinMaxSerialNoForExcel(BWFLImportDataTable_20_21 dt, BWFLImportAction_20_21 action, Connection conn) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String fl3 = "SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no   "
				+ "FROM public.bottling_fl3 a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?";
		String fl3mx = "SELECT   serial_no_end, licence_no   " + "FROM public.bottling_fl3 a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?";

		String fl3a = "SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no   "
				+ "FROM public.bottling_fl3a a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=? and gtin_no=? ";
		String fl3amx = "SELECT   serial_no_end    " + "FROM public.bottling_fl3a a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=? and gtin_no=? ";

		String cl = "SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no   "
				+ "FROM public.bottling_cl a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?";

		String clmx = "SELECT   serial_no_end   " + "FROM public.bottling_cl a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?";

		String minMax = "";

		try {

			if (dt.getShowDataTable_LicenceType().equals("FL3")) {

				/*
				 * //System.out.println("fl3 min max" + action.getDistillery_id() + " date" +
				 * Utility.convertUtilDateToSQLDate(dt .getFinalize_Date()) + " licenceno" +
				 * dt.getLicenceNo() + " licence type" + dt.getShowDataTable_LicenceType() +
				 * dt.getGtinno());
				 */
				pstmt = conn.prepareStatement(fl3);
				pstmt.setString(1, action.getDistillery_id());
				pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				pstmt.setLong(5, Long.parseLong(dt.getGtinno()));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = rs.getString("serial_no_start");
					// //System.out.println("minMax" + minMax);
				}
				pstmt = conn.prepareStatement(fl3mx);
				pstmt.setInt(1, Integer.parseInt(action.getDistillery_id()));
				pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				pstmt.setLong(5, Long.parseLong(dt.getGtinno()));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = minMax + "," + rs.getString("serial_no_end");
					// //System.out.println("minMax" + minMax);
				}
			}

			if (dt.getShowDataTable_LicenceType().equals("FL3A")) {

				/*
				 * //System.out.println("fl3a min max" + action.getDistillery_id() + " date" +
				 * Utility.convertUtilDateToSQLDate(dt .getFinalize_Date()) + " licenceno" +
				 * dt.getLicenceNo() + " licence type" + dt.getShowDataTable_LicenceType() + ""
				 * + dt.getGtinno());
				 */
				pstmt = conn.prepareStatement(fl3a);
				pstmt.setInt(1, Integer.parseInt(action.getDistillery_id()));
				pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				pstmt.setLong(5, Long.parseLong(dt.getGtinno()));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = rs.getString("serial_no_start");
					// //System.out.println("minMax" + minMax);
				}
				pstmt = conn.prepareStatement(fl3amx);
				pstmt.setInt(1, Integer.parseInt(action.getDistillery_id()));
				pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				pstmt.setLong(5, Long.parseLong(dt.getGtinno()));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = minMax + "," + rs.getString("serial_no_end");
					// //System.out.println("minMax" + minMax);
				}
			}
			if (dt.getShowDataTable_LicenceType().equals("CL")) {
				/*
				 * //System.out.println("cl min max" + action.getDistillery_id() + " date" +
				 * Utility.convertUtilDateToSQLDate(dt .getFinalize_Date()) + " licenceno" +
				 * dt.getLicenceNo() + " licence type" + dt.getShowDataTable_LicenceType() + ""
				 * + dt.getGtinno());
				 */
				pstmt = conn.prepareStatement(cl);
				pstmt.setInt(1, Integer.parseInt(action.getDistillery_id()));
				pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				pstmt.setLong(5, Long.parseLong(dt.getGtinno()));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = rs.getString("serial_no_start");
					// //System.out.println("minMax" + minMax);
				}
				pstmt = conn.prepareStatement(clmx);
				pstmt.setInt(1, Integer.parseInt(action.getDistillery_id()));
				pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				pstmt.setLong(5, Long.parseLong(dt.getGtinno()));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = minMax + "," + rs.getString("serial_no_end");
					// //System.out.println("minMax" + minMax);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

		}
		return minMax;
	}

	/*
	 * public boolean checkData(BWFLImportAction_20_21 action) { boolean flag = false;
	 * Connection conn = null; PreparedStatement pstmt = null; ResultSet rs = null;
	 * int j = 0; String sql =
	 * 
	 * "  SELECT int_distillery_id, int_brand_id, int_pack_id, int_quantity, int_planned_bottles, "
	 * + "  int_boxes, int_liquor_type, vch_license_type, plan_dt, " +
	 * "  licence_no, cr_date, finalized_flag, finalized_date " +
	 * "  FROM distillery.mst_bottling_plan_20_21 " +
	 * "   where int_distillery_id=? and int_brand_id=? and int_pack_id=? and plan_dt=? and permitno=?"
	 * ; try { conn = ConnectionToDataBase.getConnection();
	 * 
	 * if (action.getBrandPackagingDataList().size() > 0) {
	 * 
	 * pstmt = conn.prepareStatement(sql); for (int i = 0; i <
	 * action.getBrandPackagingDataList().size(); i++) { BWFLImportDataTable_20_21 table =
	 * (BWFLImportDataTable_20_21) action .getBrandPackagingDataList().get(i); //
	 * pstmt.setInt(1,getMaxChallanIdDetail()+1); pstmt.setInt(1,
	 * action.getDistillery_id()); pstmt.setInt(2, Integer.parseInt(table
	 * .getBrandPackagingData_Brand())); pstmt.setInt(3, Integer.parseInt(table
	 * .getBrandPackagingData_Packaging())); pstmt.setDate(4,
	 * Utility.convertUtilDateToSQLDate(action .getDateOfBottling()));
	 * pstmt.setString(5, action.getPermitno()); rs = pstmt.executeQuery(); if
	 * (rs.next()) { flag = false;
	 * 
	 * } else { flag = true; }
	 * 
	 * }
	 * 
	 * } else {
	 * 
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("Please Add One Row", "Please Add One Row")); } } catch
	 * (Exception e) {
	 * 
	 * FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage(e.getMessage(), e.getMessage())); } finally {
	 * 
	 * try { if (conn != null) conn.close(); } catch (Exception e) {
	 * e.printStackTrace(); } }
	 * 
	 * return flag; }
	 */
	public int maxIdgroup(int seq) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;

		String query = " SELECT distinct coalesce(group_id,0) as id FROM bwfl_license.mst_bottling_plan_20_21 where coalesce(group_id,0)>0 and seq="
				+ seq;
		int maxid = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			// System.out.println("query==" + query);
			if (rs.next()) {
				maxid = rs.getInt("id");
			} else {
				query = " SELECT max(group_id)+1 as id FROM bwfl_license.mst_bottling_plan_20_21 ";
				pstmt1 = con.prepareStatement(query);
				rs1 = pstmt1.executeQuery();
				// System.out.println("query1==" + query);
				if (rs1.next()) {
					maxid = rs1.getInt("id");
				}
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

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
		// System.out.println("maxid==" + maxid);
		return maxid;

	}

	public void dataFinalize(BWFLImportAction_20_21 action, BWFLImportDataTable_20_21 dt) {
		Connection conn = null;
		Connection conn1 = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs = null;
		String gtinNo = "";
		long boxsize = 0;
		long caseno = 0;
		int unmaped_status = 0;
		String bottlecode = "";
		int groupId = maxIdgroup(dt.getSeq());

		String sql = " INSERT INTO bottling_unmapped.bwfl(  "
				+ " date_plan, etin, serial_no_start, serial_no_end, casecode, plan_id, " + " unit_id)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		int status = 0;
		long serialno = 0;

		String update = " UPDATE bwfl_license.mst_bottling_plan_20_21 "
				+ " SET   finalized_flag='F' ,finalized_date=? , group_id=" + groupId + " "
				+ " WHERE int_distillery_id=? and int_brand_id=? and int_pack_id=? and plan_dt=? and permitno=? and seq=? ";

		try {
			gtinNo = getBrandPackagingGtinNo(action, dt);

			/*
			 * long serialno = getSerialNo(new Double(
			 * dt.getShowDataTable_PlanNoOfBottling()).longValue());
			 */
			// serialno = dt.getSeqfrm();

			serialno = getSerialNo(new Double(dt.getBrandPackagingData_PlanNoOfBottling()).longValue(), new Date());

			// System.out.println("gtinNo"+gtinNo);
			// System.out.println("serialno="+serialno);
			if (!gtinNo.equals("") && serialno != 0) {
				conn = ConnectionToDataBase.getConnection20_21();
				conn1 = ConnectionToDataBase.getConnection();
				conn.setAutoCommit(false);
				conn1.setAutoCommit(false);

				pstmt1 = conn1.prepareStatement(update);

				pstmt1.setDate(1, new java.sql.Date(System.currentTimeMillis()));
				pstmt1.setInt(2, Integer.parseInt(dt.getDistillery_id()));
				pstmt1.setInt(3, dt.getBrandId());
				pstmt1.setInt(4, dt.getPackagingId());
				pstmt1.setDate(5, Utility.convertUtilDateToSQLDate(dt.getShowDataTable_Date()));
				pstmt1.setString(6, dt.getPermitnoD());
				pstmt1.setInt(7, dt.getSeq());

				status = pstmt1.executeUpdate();
				// //System.out.println("statussssssssssssssss"+status);

				if (status > 0) {
					status = 0;
					for (int i = 0; i < Long.parseLong(dt.getShowDataTable_NoOfBoxes()); i++) {
						// caseno = dt.getCaseseq() + i;

						caseno = getcaseNo(new Date());

						pstmt2 = conn.prepareStatement(sql);
						// pstmt3=conn.prepareStatement(unmapped_sql);

						pstmt2.setDate(1, new java.sql.Date(System.currentTimeMillis()));
						pstmt2.setString(2, gtinNo);
						pstmt2.setString(3, StringUtils.leftPad(String.valueOf(serialno), 8, '0'));
						pstmt2.setString(4,
								StringUtils.leftPad(
										String.valueOf(
												serialno + (Integer.parseInt(dt.getShowDataTable_PlanNoOfBottling())
														/ Integer.parseInt(dt.getShowDataTable_NoOfBoxes())) - 1),
										8, '0'));
						Random rand1 = new Random();
						int n1 = rand1.nextInt((99 - 91) + 1) + 91;
						pstmt2.setString(5, n1 + "" + StringUtils.leftPad(String.valueOf(caseno), 6, '0')
								+ getCheckDigit(n1 + "" + StringUtils.leftPad(String.valueOf(caseno), 6, '0')));
						pstmt2.setInt(6, groupId);

						pstmt2.setLong(7, getEtinUnitId(dt.getDistillery_id()));

						serialno += (Integer.parseInt(dt.getShowDataTable_PlanNoOfBottling())
								/ Integer.parseInt(dt.getShowDataTable_NoOfBoxes()));

						status = pstmt2.executeUpdate();
					}
				}

				if (status > 0) {
					status = 0;
					boolean flag = write(dt, action, conn, groupId);

					if (flag) {
						status = 1;
					} else {
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage("Excel Not Generated", "Excel Not Generated"));
					}
				}
				if (status > 0) {

					conn.commit();
					conn1.commit();

					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Finalized SuccessFully", "Finalized SuccessFully"));
				} else {
					conn.rollback();
					conn1.rollback();
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Not Finalized ", " Not Finalized "));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Gtin and Serial No Can Not Zero ", " Gtin and Serial No Can Not Zero"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
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
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			}
		}
	}

	public int getEtinUnitId(String distilleryunitid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int etintunitid = 0;
		String sql =

				"	 SELECT etin_unit_id,unit_id,int_id, mobile_number, vch_license_type, vch_firm_name, vch_firm_add   "
						+ "	 FROM bwfl.registration_of_bwfl_lic_holder_20_21                                                    "
						+ "	  WHERE vch_approval='V' AND   int_id=" + distilleryunitid;

		try {
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				etintunitid = rs.getInt("int_id");
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
		return etintunitid;
	}

	public boolean write(BWFLImportDataTable_20_21 dt, BWFLImportAction_20_21 action, Connection conn, int groupId) {

		// //System.out.println("excel innn");
		String sql_bwfl_update = "update bottling_unmapped.bwfl set bottle_code=? where unit_id= ? and plan_id=? and date_plan=? and  etin=? and  casecode=?";
		String bwfl = "" +

				"	select to_char(y.gs, 'fm00000000')as GENERATE_SERIES , y.dispatch_date,y.gtin_no, y.unit_id ,               "
				+ "	y.serial_no_start, y.serial_no_end, "
				+ "	to_char(y.case_no::numeric , 'fm000000000')as case_no,y.plan_id from( "
				+ "	select  x.unit_id,GENERATE_SERIES(x.serial_no_start::numeric ,x.serial_no_end::numeric ) as gs ,x.serial_no_start ,x.serial_no_end, "
				+ "	x.case_no,x.dispatch_date,x.gtin_no,x.plan_id from ( "
				+ "	SELECT  plan_id,unit_id,date_plan as dispatch_date, etin as gtin_no, serial_no_start, serial_no_end, casecode as case_no "
				+ "	FROM bottling_unmapped.bwfl a where   date_plan=?   and etin=? and plan_id=?)x)y";

		String relativePath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
		FileOutputStream fileOut = null;

		int count = 1;
		String bottle_code = "";
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		long start = 0;
		long end = 0;
		boolean flag = false;
		long k = 0;
		String noOfUnit = "";
		String date = null;

		try {

			pstmt = conn.prepareStatement(bwfl);

			pstmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			pstmt.setString(2, dt.getGtinno());
			pstmt.setInt(3, groupId);
			rs = pstmt.executeQuery();

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet worksheet = workbook.createSheet("Barcode Report");

			XSSFCellStyle unlockedCellStyle = workbook.createCellStyle();
			unlockedCellStyle.setLocked(false);

			worksheet.protectSheet("agristat");
			worksheet.setColumnWidth(0, 3000);
			worksheet.setColumnWidth(1, 8000);
			worksheet.setColumnWidth(2, 8000);
			worksheet.setColumnWidth(3, 8000);
			worksheet.setColumnWidth(4, 6000);

			XSSFRow rowhead0 = worksheet.createRow((int) 0);
			// HSSFRow rowhead0 = worksheet.createRow((int) 0);
			// HSSFCell cellhead0 = rowhead0.createCell((int) 0);
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

				Date dat = Utility.convertSqlDateToUtilDate(rs.getDate("dispatch_date"));

				DateFormat formatter;

				formatter = new SimpleDateFormat("yyMMdd");
				date = formatter.format(dat);

				String lic = dt.getLicencenoo().replaceAll("/", "");

				// //System.out.println("while in");serial_no_start, serial_no_end
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
				noOfUnit = StringUtils.leftPad(String.valueOf(Integer.parseInt(dt.getShowDataTable_PlanNoOfBottling())
						/ Integer.parseInt(dt.getShowDataTable_NoOfBoxes())), 3, '0');

				if (noOfUnit.length() == 2) {

					cellD1.setCellValue(rs.getString("gtin_no") + "" + 1 + ""
							+ StringUtils.leftPad(String.valueOf(rs.getString("unit_id")), 3, '0') + "" + date + ""
							+ "1" + "" + StringUtils.leftPad(String.valueOf(noOfUnit), 3, '0') + ""
							+ rs.getString("case_no"));

				} else if (noOfUnit.length() == 1) {
					cellD1.setCellValue("" + rs.getString("gtin_no") + "" + 1 + ""
							+ StringUtils.leftPad(String.valueOf(rs.getString("unit_id")), 3, '0') + "" + date + ""
							+ "1" + "" + StringUtils.leftPad(String.valueOf(noOfUnit), 3, '0') + ""
							+ rs.getString("case_no"));
				} else {

					cellD1.setCellValue(rs.getString("gtin_no") + "" + 1 + ""
							+ StringUtils.leftPad(String.valueOf(rs.getString("unit_id")), 3, '0') + "" + date + ""
							+ "1" + "" + StringUtils.leftPad(String.valueOf(noOfUnit.substring(0, 3)), 3, '0') + ""
							+ rs.getString("case_no"));

				}

				XSSFCell cellE1 = row1.createCell((int) 4);

				Random rand = new Random();
				int n = 10 + rand.nextInt(90);

				cellE1.setCellValue(rs.getString("gtin_no") + "" + date
						+ StringUtils.leftPad(String.valueOf(rs.getString("unit_id")), 3, '0') + "" + "" + n + ""
						+ rs.getString("GENERATE_SERIES") + "" + getCheckDigit(rs.getString("GENERATE_SERIES")));

				bottle_code = bottle_code + "|" + n + "" + rs.getString("GENERATE_SERIES") + ""
						+ getCheckDigit(rs.getString("GENERATE_SERIES"));

				pstmt1 = conn.prepareStatement(sql_bwfl_update);
				// System.out.println("bottle_code "+bottle_code);
				// System.out.println("unit id
				// "+String.valueOf(Integer.parseInt(rs.getString("unit_id"))));
				// System.out.println("unit id "+rs.getInt("plan_id"));
				// System.out.println("unit id
				// "+Utility.convertUtilDateToSQLDate(rs.getDate("dispatch_date") ));
				// System.out.println("unit id "+rs.getString("gtin_no") );
				// System.out.println("unit id "+rs.getString("case_no") );
				pstmt1.setString(1, bottle_code);
				pstmt1.setInt(2, Integer.parseInt(rs.getString("unit_id")));
				pstmt1.setInt(3, rs.getInt("plan_id"));
				pstmt1.setDate(4, Utility.convertUtilDateToSQLDate(rs.getDate("dispatch_date")));
				pstmt1.setString(5, rs.getString("gtin_no"));
				pstmt1.setString(6, rs.getString("case_no"));
				pstmt1.executeUpdate();

				if (count == (Integer.parseInt(dt.getShowDataTable_PlanNoOfBottling())
						/ Integer.parseInt(dt.getShowDataTable_NoOfBoxes())))

				{

					System.out.println("come inn nn time ");
					count = 0;
					bottle_code = "";
				}

				count++;

			}
			fileOut = new FileOutputStream(
					relativePath + "//ExciseUp//Excel//" + dt.getGtinno() + date + groupId + ".xls");

			XSSFRow row1 = worksheet.createRow((int) k + 1);

			XSSFCell cellA1 = row1.createCell((int) 0);
			cellA1.setCellValue("End");

			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			flag = true;

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

			// //System.out.println("xls2" + e.getMessage());
			e.printStackTrace();
		}

		return flag;
	}

	public static int getCheckDigit(String n) {
		// int i=0;
		int sum = 0;
		int even = 0;
		int odd = 0;
		int checkDigit = 2;
		/*char ar[] = n.toCharArray();
		int k = ar.length;
		for (int i = ar.length - 1; i >= 0; i--) {

			if (i % 2 != 0) {
				// //System.out.println("evennnn"+ar[i]);
				odd = odd + Character.getNumericValue(ar[i]);
			} else {
				// //System.out.println("oddddddd"+ar[i]);
				even = even + Character.getNumericValue(ar[i]);
			}

		}

		sum = (odd * 3) + even;
		// System.out.println("summm" + sum);
		// System.out.println("even" + even);
		// System.out.println("odd sum" + odd);
		if (sum % 10 != 0) {

			// //System.out.println("SUMM "+sum);
			checkDigit = (10 - sum % 10);
			// //System.out.println("checkDigit "+checkDigit);
		}*/

		return checkDigit;
	}
	// =============================get etin nmbr============================

	public String getEtinNmbr(String brand_Id, String packging_Id) {

		String etin = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList = " SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id, b.code_generate_through "
					+ " FROM distillery.brand_registration_20_21 a , distillery.packaging_details_20_21 b "
					+ " WHERE a.brand_id=b.brand_id_fk  and b.mrp >0  " + " AND brand_id =?  AND b.package_id=?";

			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, Integer.parseInt(brand_Id));
			pstmt.setInt(2, Integer.parseInt(packging_Id));

			rs = pstmt.executeQuery();

			while (rs.next()) {

				etin = rs.getString("code_generate_through");

			}

			// pstmt.executeUpdate();
		} catch (SQLException se) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));

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
		return etin;

	}

	// --------------get data--------------------

	public ArrayList getDisplayList(BWFLImportAction_20_21 act) {

		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int j = 1;

		String selQr = " SELECT  a.cancel_order_no,a.cancel_req_reason,a.cancel_req_flg,a.cancel_req_dt_time,a.entry_no_of_bottle_per_case,coalesce(finalized_flag,'K') as finalized_flag ,a.int_distillery_id, a.int_brand_id, a.int_pack_id, "
				+ " a.int_quantity, a.int_planned_bottles, a.int_boxes, a.int_liquor_type, "
				+ " a.vch_license_type, a.plan_dt, a.licence_no, a.cr_date, a.finalized_flag, "
				+ " a.finalized_date, a.received_liqour, a.permitno, a.permitdt, "
				+ " a.recieved_bottles, a.recieved_boxes, a.seq, a.gatepass_no, a.bottling_seq_frm, "
				+ " a.bottling_seq_to, a.scan_upload_flag, a.exp_order_nmbr, a.exp_order_dt, "
				+ " a.transprt_vehicle_nmbr, a.route_details, a.maped_unmaped_flag, a.breakage, "
				+ " a.maped_unmaped_type, a.etin, a.validity_date, a.permitno_entered, a.group_id, "
				+ " a.dispatch_date, b.vch_firm_name, b.vch_firm_district_id, "
				+ " CASE WHEN a.maped_unmaped_flag='M' THEN 'Mapped Data' "
				+ " WHEN a.maped_unmaped_flag='U' THEN 'UnMapped Data' end as type "
				+ " FROM bwfl_license.mst_bottling_plan_20_21 a, bwfl.registration_of_bwfl_lic_holder_20_21 b, public.district c  "
				+ " WHERE a.int_distillery_id=b.int_id AND b.vch_firm_district_id=c.districtid::text " + " AND c.deo='"
				+ ResourceUtil.getUserNameAllReq().trim() + "' " + " ORDER BY a.cr_date DESC ";

		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(selQr);
			// System.out.println("selQr------ not null---------" + selQr);
			rs = ps.executeQuery();

			while (rs.next()) {
				BWFLImportDataTable_20_21 dt = new BWFLImportDataTable_20_21();

				dt.setSnothrd(j);

				if (rs.getString("cancel_req_reason") != null) {
					dt.setCancel_reason(rs.getString("cancel_req_reason") + "   Cancel Request Date Time Is "
							+ rs.getString("cancel_req_dt_time"));
				} else {
					dt.setCancel_reason("NA");
				}

				if (rs.getString("cancel_order_no") != null) {
					dt.setCancel_order_flg(true);
				} else {
					dt.setCancel_order_flg(false);
				}

				dt.setCancel_flg(rs.getString("cancel_req_flg"));

				dt.setEtinUpdate(rs.getString("etin"));
				dt.setFinalize_Date(Utility.convertSqlDateToUtilDate(rs.getDate("finalized_date")));
				dt.setGroupId(rs.getString("group_id"));

				dt.setEtinUpdate(rs.getString("etin"));
				dt.setPermitno_enteredUpdate(rs.getString("permitno_entered"));
				dt.setInt_brand_idUpdate(rs.getString("int_brand_id"));
				dt.setInt_pack_idUpdate(rs.getString("int_pack_id"));
				dt.setSeqUpdate(rs.getInt("seq"));
				dt.setGatepass_noUpdate(rs.getString("gatepass_no"));

				if (rs.getString("finalized_flag").equalsIgnoreCase("F")) {
					dt.setUpdateDisabled(true);
				} else if (rs.getString("finalized_flag").equalsIgnoreCase("K"))

				{
					dt.setUpdateDisabled(false);
				}

				dt.setLicenseNmbr_dt(rs.getString("licence_no"));
				dt.setLicenseType_dt(rs.getString("vch_license_type"));
				dt.setUnitName_dt(rs.getString("vch_firm_name"));
				dt.setPermitNmbr_dt(rs.getString("permitno"));
				dt.setMappedType_dt(rs.getString("type"));
				dt.setPermitDt_dt(rs.getDate("permitdt"));
				dt.setPlan_dt(rs.getDate("plan_dt"));
				dt.setDispbot_dt(rs.getInt("int_planned_bottles"));
				dt.setDispbox_dt(rs.getInt("int_boxes"));
				dt.setDistillery_id(rs.getString("int_distillery_id"));
				dt.setEntry_no_of_bottle_per_case(rs.getLong("entry_no_of_bottle_per_case"));
				j++;
				list.add(dt);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	// ---------------------------------------------------------------------------------------------------------------

	public ArrayList getNewData(BWFLImportAction_20_21 ac) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int j = 1;

		String selQr = " SELECT entry_no_of_bottle_per_case,int_quantity,licence_no,int_pack_id,int_brand_id, "
				+ " permitno_entered,validity_date,maped_unmaped_type,int_distillery_id,vch_license_type,int_planned_bottles, int_boxes  "
				+ " FROM bwfl_license.mst_bottling_plan_20_21  " + " where permitno_entered='"
				+ ac.getPermitno_enteredUpdate() + "' and vch_license_type='" + ac.getVch_license_typeUpdate()
				+ "' and int_distillery_id='" + ac.getInt_distillery_idUpdate() + "' " + " and seq='"
				+ ac.getSeqUpdate() + "' and licence_no='" + ac.getLicence_noUpdate() + "'  and gatepass_no='"
				+ ac.getGatepass_noUpdate() + "' and permitdt='" + ac.getPermitDatUpdate()
				+ "' AND (finalized_flag is null or finalized_flag!='F') ";

		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(selQr);

			rs = ps.executeQuery();

			while (rs.next()) {
				BWFLImportDataTable_20_21 dt = new BWFLImportDataTable_20_21();
				dt.setBrandPackagingData_Quantity(rs.getString("int_quantity"));
				dt.setBrandPackagingData_NoOfBoxes(rs.getInt("int_boxes"));
				dt.setBrandPackagingData_PlanNoOfBottling(rs.getInt("int_planned_bottles"));
				dt.setBrandPackagingData_Brand(rs.getString("int_brand_id"));
				dt.setBrandPackagingData_Packaging(rs.getString("int_pack_id"));
				dt.setEntry_no_of_bottle_per_case(rs.getLong("entry_no_of_bottle_per_case"));
				if (rs.getString("vch_license_type").equalsIgnoreCase("BWFL2A")) {
					ac.setLicenceType("1");
				} else if (rs.getString("vch_license_type").equalsIgnoreCase("BWFL2B")) {

					ac.setLicenceType("2");

				} else if (rs.getString("vch_license_type").equalsIgnoreCase("BWFL2C")) {
					ac.setLicenceType("3");
				} else if (rs.getString("vch_license_type").equalsIgnoreCase("BWFL2D")) {
					ac.setLicenceType("4");
				}
				ac.setLicenceNum(rs.getString("licence_no"));
				ac.setPermitdt(ac.getPermitDatUpdate());
				ac.setBottlngType(rs.getString("maped_unmaped_type"));
				ac.setValidityDate(rs.getDate("validity_date"));
				ac.setPermitNoEnterd(rs.getString("permitno_entered"));

				// ac.setFlagNew(true);
				ac.setDisabledFlag(true);
				list.add(dt);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList getUnit_For_Update(String id, String licenseNoNew, BWFLImportAction_20_21 ac) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);

		String SQl = " SELECT distinct int_id, mobile_number, vch_license_type, vch_firm_name, vch_firm_add "
				+ " FROM bwfl.registration_of_bwfl_lic_holder_20_21  " + " WHERE vch_approval='V' AND "
				+ " vch_license_type= '" + id + "'  and vch_license_number='" + licenseNoNew + "' ";

		try {

			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);

			rs = ps.executeQuery();
			while (rs.next()) {

				item = new SelectItem();
				item.setLabel(rs.getString("vch_firm_name") + " (" + rs.getString("mobile_number") + " )");
				item.setValue(rs.getString("int_id"));

				list.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

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

	public boolean checkData(String permitno_enteredUpdate, String vch_license_typeUpdate,
			String int_distillery_idUpdate, String int_brand_idUpdate, String int_pack_idUpdate, int seqUpdate,
			String licence_noUpdate, String gatepass_noUpdate, Date permitDatUpdate) {
		boolean flag = false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String SQl = " select  * from bwfl_license.mst_bottling_plan_20_21  " +

				" where permitno_entered='" + permitno_enteredUpdate + "' and vch_license_type='"
				+ vch_license_typeUpdate + "'" + " and int_distillery_id='" + int_distillery_idUpdate
				+ "' and int_brand_id='" + int_brand_idUpdate + "' " + " and int_pack_id='" + int_pack_idUpdate
				+ "' and seq='" + seqUpdate + "' and " + " licence_no='" + licence_noUpdate + "'  and gatepass_no='"
				+ gatepass_noUpdate + "'" + " and permitdt='" + permitDatUpdate + "'  ";

		try {

			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);

			rs = ps.executeQuery();
			if (rs.next()) {
				flag = true;

			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

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
		return flag;
	}

	public boolean checkpermit(String permitno_entered, BWFLImportAction_20_21 action) {
		boolean flag = false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String SQl = " select  * from bwfl_license.mst_bottling_plan_20_21  " + " where permitno like '%"
				+ this.getIddeo(action) + "-2019-20-" + action.getPermitNoEnterd() + "-%'  ";

		// System.out.println("SQl-----------------"+SQl);

		try {

			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);

			rs = ps.executeQuery();
			if (rs.next()) {
				flag = true;

			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

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
		return flag;
	}

	public int delete(BWFLImportAction_20_21 action) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int saveStatus = 0;
		String query = "";
		String sql = "";
		// sql="delete from distillery.duty_register_19_20 where
		// int_id='"+action.getDutyRegisterId()+"'";
		sql = " INSERT INTO distillery.duty_register_19_20( "
				+ " int_id, int_distillery_id, date_crr_date, vch_duty_type, int_quantity, int_value, vch_description,permitno_bwfl)  "
				+ " select (select max(int_id)+1 from distillery.duty_register_19_20), int_distillery_id, date_crr_date, vch_duty_type, -int_quantity, -int_value,'Cancellation For '||vch_description,permitno_bwfl "
				+ " from distillery.duty_register_19_20  where int_id='" + action.getDutyRegisterId() + "' ";

		try {

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			query = " delete from  bwfl_license.mst_bottling_plan_20_21  where int_distillery_id="
					+ action.getInt_distillery_idUpdate() + " and int_brand_id=" + action.getInt_brand_idUpdate()
					+ " and int_pack_id=" + action.getInt_pack_idUpdate() + " and plan_dt='" + action.getPlan_dt()
					+ "' and permitno='" + action.getPermitNoNew()
					+ "' AND (finalized_flag!='F' or finalized_flag IS NULL) " + "and seq='" + action.getSeqUpdate()
					+ "' and licence_no='" + action.getLicence_noUpdate() + "'  and gatepass_no='"
					+ action.getGatepass_noUpdate() + "'" + " and     permitdt='" + action.getPermitDatUpdate() + "'";

			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			saveStatus = pstmt.executeUpdate();
			if (saveStatus > 0) {

				pstmt = conn.prepareStatement(sql);
				System.out.println(sql);

				saveStatus = pstmt.executeUpdate();

				if (saveStatus > 0) {
					conn.commit();
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Delete Sucessfully", "Delete Sucessfully"));
					action.reset();
					action.getDisplayDataList();
					// System.out.println("----");
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Not Delete ", "Not Delete "));
					conn.rollback();
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Not Delete ", "Not Delete "));
				conn.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
		}

		finally {
			try {

				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return saveStatus;
	}

	// -------------------------get data for valid till
	// update--------------------------------------------------------------

	public ArrayList getDisplayListExt(BWFLImportAction_20_21 act) {

		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int j = 1;

		String selQr = " SELECT coalesce(finalized_flag,'K') as finalized_flag ,a.int_distillery_id, a.int_brand_id, a.int_pack_id, "
				+ " a.int_quantity, a.int_planned_bottles, a.int_boxes, a.int_liquor_type, "
				+ " a.vch_license_type, a.plan_dt, a.licence_no, a.cr_date, a.finalized_flag, "
				+ " a.finalized_date, a.received_liqour, a.permitno, a.permitdt, "
				+ " a.recieved_bottles, a.recieved_boxes, a.seq, a.gatepass_no, a.bottling_seq_frm, "
				+ " a.bottling_seq_to, a.scan_upload_flag, a.exp_order_nmbr, a.exp_order_dt, "
				+ " a.transprt_vehicle_nmbr, a.route_details, a.maped_unmaped_flag, a.breakage, "
				+ " a.maped_unmaped_type, a.etin, a.validity_date, a.permitno_entered, a.group_id, "
				+ " a.dispatch_date, b.vch_firm_name, b.vch_firm_district_id, "
				+ " CASE WHEN a.maped_unmaped_flag='M' THEN 'Mapped Data' "
				+ " WHEN a.maped_unmaped_flag='U' THEN 'UnMapped Data' end as type "
				+ " FROM bwfl_license.mst_bottling_plan_20_21 a, bwfl.registration_of_bwfl_lic_holder_20_21 b, public.district c  "
				+ " WHERE a.int_distillery_id=b.int_id AND b.vch_firm_district_id=c.districtid::text " + " AND c.deo='"
				+ ResourceUtil.getUserNameAllReq().trim() + "' and a.validity_date is not null "
				+ " ORDER BY a.cr_date DESC ";

		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(selQr);

			rs = ps.executeQuery();

			while (rs.next()) {
				BWFLImportDataTable_20_21 dt = new BWFLImportDataTable_20_21();

				dt.setSnothrd(j);
				dt.setEtinUpdate(rs.getString("etin"));
				dt.setPermitno_enteredUpdate(rs.getString("permitno_entered"));
				dt.setInt_brand_idUpdate(rs.getString("int_brand_id"));
				dt.setInt_pack_idUpdate(rs.getString("int_pack_id"));
				dt.setSeqUpdate(rs.getInt("seq"));
				dt.setGatepass_noUpdate(rs.getString("gatepass_no"));
				dt.setNewvalidityDate(rs.getDate("validity_date"));
				dt.setOldvalidityDate(rs.getDate("validity_date"));

				if (rs.getString("finalized_flag").equalsIgnoreCase("F")) {
					dt.setUpdateDisabled(true);
				} else if (rs.getString("finalized_flag").equalsIgnoreCase("K"))

				{
					dt.setUpdateDisabled(false);
				}

				dt.setLicenseNmbr_dt(rs.getString("licence_no"));
				dt.setLicenseType_dt(rs.getString("vch_license_type"));
				dt.setUnitName_dt(rs.getString("vch_firm_name"));
				dt.setPermitNmbr_dt(rs.getString("permitno"));
				dt.setMappedType_dt(rs.getString("type"));
				dt.setPermitDt_dt(rs.getDate("permitdt"));
				dt.setPlan_dt(rs.getDate("plan_dt"));
				dt.setDispbot_dt(rs.getInt("int_planned_bottles"));
				dt.setDispbox_dt(rs.getInt("int_boxes"));
				dt.setDistillery_id(rs.getString("int_distillery_id"));

				j++;
				list.add(dt);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	public void updateValidTillExt(BWFLImportAction_20_21 action, Date date) {

		BWFLImportDataTable_20_21 table = new BWFLImportDataTable_20_21();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		int challanId = maxId();
		String query = "";
		try {

			conn = ConnectionToDataBase.getConnection();

			query = " UPDATE bwfl_license.mst_bottling_plan_20_21 SET " + " old_valid_till='"
					+ Utility.convertUtilDateToSQLDate(action.getOld_valid_date()) + "' , validity_date='"
					+ Utility.convertUtilDateToSQLDate(date) + "'," + "extension_date='"
					+ Utility.convertUtilDateToSQLDate(action.getNewValidDate()) + "' " +

					" where vch_license_type='" + action.getVch_license_typeUpdate() + "' and int_distillery_id='"
					+ action.getInt_distillery_idUpdate() + "'  " + " and seq='" + action.getSeqUpdate()
					+ "' and licence_no='" + action.getLicence_noUpdate() + "'  and gatepass_no='"
					+ action.getGatepass_noUpdate() + "'" + " and     permitdt='" + action.getPermitDatUpdate() + "'  ";
			
			String bwfl_permit = "update bwfl_license.import_permit_20_21 set valid_upto=? where permit_nmbr=?";

			if (action.getOld_valid_date().before(date)) {
				pstmt = conn.prepareStatement(query);

				saveStatus = pstmt.executeUpdate();
				if(saveStatus>0)
				{
					saveStatus=0;	
					pstmt = conn.prepareStatement(bwfl_permit);
					pstmt.setDate(1,  Utility.convertUtilDateToSQLDate(date));
					pstmt.setString(2, action.getPermitNoNew());

					saveStatus = pstmt.executeUpdate();
				}
				
				

				if (saveStatus > 0) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Updated Sucessfully", "Updated Sucessfully"));
					action.reset();
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Not Updated ", "Not Updated "));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Date should not be Less then old validity Date ",
								"Date should not be Less then old validity Date "));
			}

		}

		catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
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

	}

	// =============================get permit fees============================

	public double getPermitFee(String brand_Id, String packging_Id) {

		double permitFee = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList = " SELECT a.brand_id, a.brand_name, a.vch_license_type, b.package_name, b.package_id, b.permit "
					+ " FROM distillery.brand_registration_20_21 a , distillery.packaging_details_20_21 b  "
					+ " WHERE a.brand_id=b.brand_id_fk and b.mrp >0  " + " AND a.brand_id =?  AND b.package_id=? ";

			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, Integer.parseInt(brand_Id));
			pstmt.setInt(2, Integer.parseInt(packging_Id));

			// System.out.println("brand_Id------------"+brand_Id);

			// System.out.println("packging_Id------------"+packging_Id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				permitFee = rs.getDouble("permit");

			}

		} catch (SQLException se) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));

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
		return permitFee;

	}

	public void getLicNoUnitId(BWFLImportAction_20_21 action, String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		/*
		 * SELECT int_id, mobile_number, vch_license_type, vch_firm_name, vch_firm_add "
		 * + " FROM bwfl.registration_of_bwfl_lic_holder_20_21, public.district b  " +
		 * " WHERE vch_approval='V' AND " + " vch_license_type" +
		 */

		String SQl = "select vch_license_number,unit_id FROM bwfl.registration_of_bwfl_lic_holder_20_21 WHERE vch_approval='V' AND    int_id='"
				+ id + "'";
		// System.out.println("SQl="+SQl);
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);
			rs = ps.executeQuery();
			if (rs.next()) {

				action.setUnit_id(rs.getInt("unit_id"));
				action.setLicenceNum(rs.getString("vch_license_number"));

				// System.out.println("reg holder unit id="+action.getUnit_id());
				// System.out.println("lic no="+action.getLicenceNum());
			}
		} catch (Exception e) {
			// no need
			// FacesContext.getCurrentInstance().addMessage(null,new
			// FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
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
	}

	public int getDutyRegisterId(String DutyRegisterId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int dutyRegisterId = 0;
		String SQl = "select int_id from distillery.duty_register_19_20 where permitno_bwfl='" + DutyRegisterId + "'";
		// System.out.println("DutyRegisterId="+SQl);
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);
			rs = ps.executeQuery();
			if (rs.next()) {
				dutyRegisterId = rs.getInt(1);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
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
		return dutyRegisterId;
	}

	public void dataCancel(BWFLImportAction_20_21 action) {
		Connection conn1 = null;
		PreparedStatement pstmt1 = null;
		int status = 0;
		String update = "UPDATE bwfl_license.mst_bottling_plan_20_21 SET cancel_req_flg='C' ,cancel_req_dt_time=?,cancel_req_reason='"
				+ action.getRemark() + "' "
				+ " WHERE int_distillery_id=? and int_brand_id=? and int_pack_id=? and plan_dt=? and permitno=? and seq=? ";

		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String time = sdf.format(cal.getTime());
			String cr_date_time = Utility.convertUtilDateToSQLDate(new Date()) + " " + time;
			// System.out.println("time="+time);
			// System.out.println("cr_date_time="+cr_date_time);
			conn1 = ConnectionToDataBase.getConnection();
			// conn1.setAutoCommit(false);
			pstmt1 = conn1.prepareStatement(update);
			pstmt1.setString(1, cr_date_time);
			pstmt1.setInt(2, Integer.parseInt(action.getInt_distillery_id()));
			pstmt1.setInt(3, action.getInt_brand_id());
			pstmt1.setInt(4, action.getInt_pack_id());
			pstmt1.setDate(5, Utility.convertUtilDateToSQLDate(action.getPlan_dt_popup()));
			pstmt1.setString(6, action.getPermitno_popup());
			pstmt1.setInt(7, action.getSeq());
			// pstmt1.setString(8, action.getRemark());
			status = pstmt1.executeUpdate();
			if (status > 0) {

				// conn1.commit();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Cancel Request Submitted", "Cancel Request Submitted"));
				action.setInt_distillery_id(null);
				action.setInt_brand_id(0);
				action.setInt_pack_id(0);
				action.setPlan_dt_popup(null);
				action.setPermitno_popup(null);
				action.setSeq(0);
				action.setRemark("");
				// System.out.println("remark="+action.getRemark());
			} else {
				// System.out.println("2");
				// conn1.rollback();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Cancel Request Not Submitted", "Cancel Request Not Submitted"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
			try {
				// conn1.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {

			try {

				if (pstmt1 != null)
					pstmt1.close();
				if (conn1 != null)
					conn1.close();

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			}
		}
	}

	public void cancelPermit(BWFLImportDataTable_20_21 dt, BWFLImportAction_20_21 action) {
		Connection conn1 = null;
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt = null;
		int status = 0;
		String update = "UPDATE bwfl_license.mst_bottling_plan_20_21 SET cancel_order_no=? ,cancel_order_date=?,cancel_authority_name=?,cancel_order_date_time=?,cancel_pdf_name=?"
				+ " WHERE int_distillery_id=? and int_brand_id=? and int_pack_id=? and plan_dt=? and permitno=? and seq=? ";

		String bottling5 = "UPDATE bottling_unmapped.bwfl  " + "SET  cancel_permit=? "
				+ "WHERE plan_id=? and  date_plan=? and  etin=? ";
		String bottling = "delete from  bottling_unmapped.bwfl  " +

				"WHERE plan_id=? and  date_plan=? and  etin=? ";

		System.out.println("update=" + update);
		System.out.println("dt.getDistillery_id=" + Integer.parseInt(dt.getDistillery_id()));
		System.out.println("dt.getBrandId()=" + dt.getInt_brand_idUpdate());
		System.out.println("dt.getPackagingId()=" + dt.getInt_pack_idUpdate());
		System.out.println("dt.getPermitnoD()" + dt.getPermitNmbr_dt());
		System.out.println("dt.getSeq()=" + dt.getSeqUpdate());
		System.out.println("finalize date " + dt.getFinalize_Date());
		System.out.println("Group id " + dt.getGroupId());
		System.out.println("Etin " + dt.getEtinUpdate());

		try {
			conn = ConnectionToDataBase.getConnection();
			conn1 = ConnectionToDataBase.getConnection20_21();
			conn.setAutoCommit(false);
			conn1.setAutoCommit(false);
			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, action.getCancel_order_no());
			pstmt.setDate(2, Utility.convertUtilDateToSQLDate(action.getCancel_order_date()));
			pstmt.setString(3, action.getAuthority_name());
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String time = sdf.format(cal.getTime());
			String cr_date_time = Utility.convertUtilDateToSQLDate(new Date()) + " " + time;
			pstmt.setString(4, cr_date_time);
			pstmt.setString(5, action.getUploadedPdfName());

			pstmt.setInt(6, Integer.parseInt(dt.getDistillery_id()));
			pstmt.setInt(7, Integer.parseInt(dt.getInt_brand_idUpdate()));
			pstmt.setInt(8, Integer.parseInt(dt.getInt_pack_idUpdate()));
			pstmt.setDate(9, Utility.convertUtilDateToSQLDate(dt.getPlan_dt()));
			pstmt.setString(10, dt.getPermitNmbr_dt());
			pstmt.setInt(11, dt.getSeqUpdate());
			status = pstmt.executeUpdate();

			System.out.println("statusssssss 1     " + status);
			if (status > 0) {
				status = 0;

				pstmt1 = conn1.prepareStatement(bottling);

				pstmt1.setInt(1, Integer.parseInt(dt.getGroupId()));
				pstmt1.setDate(2, Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()));
				pstmt1.setString(3, dt.getEtinUpdate());
				status = pstmt1.executeUpdate();
				System.out.println("statusssssss 2     " + status);
				if (status > 0) {
					conn.commit();
					conn1.commit();
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Permit Canceled SuccessFul", "Permit Canceled SuccessFul"));
					action.cancelOrderClose();

				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Permit Can Not Canceled ", "Permit Can Not Canceled "));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
			try {
				conn.rollback();
				conn1.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {

			try {

				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

				if (pstmt1 != null)
					pstmt1.close();
				if (conn1 != null)
					conn1.close();

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			}
		}
	}

	public long getUploadSequence() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long val = 0;
		String sql = "select nextval('bwfl_license.cancel_permit')";
		try {
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				val = rs.getLong(1);
			}
		} catch (Exception e) {

		} finally {

			try {

				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			}
		}

		return val;
	}

	public int checkFl11GatepassBwfl(BWFLImportDataTable_20_21 dt) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String sql = "SELECT  count(*) FROM bottling_unmapped.bwfl where plan_id=? and  date_plan=? and  etin=? and fl11gatepass is null ";
		try {
			conn = ConnectionToDataBase.getConnection20_21();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dt.getGroupseq());
			pstmt.setDate(2, Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()));
			pstmt.setString(3, dt.getGtinno());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				i = rs.getInt(1);
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
		return i;
	}

	public int checkFl36GatepassBwfl(BWFLImportDataTable_20_21 dt) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String sql = "SELECT  count(*) FROM bottling_unmapped.bwfl where plan_id=? and  date_plan=? and  etin=? and fl11gatepass is null ";
		try {
			conn = ConnectionToDataBase.getConnection20_21();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dt.getGroupseq());
			pstmt.setDate(2, Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()));
			pstmt.setString(3, dt.getGtinno());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				i = rs.getInt(1);
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
		return i;
	}

	public double getBondPermitFee() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		BWFLImportAction_20_21 ac = (BWFLImportAction_20_21) facesContext.getApplication().createValueBinding("#{bWFLImportAction_20_21}")
				.getValue(facesContext);

		String lic = ac.getLicenceType();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "";
		String lic_type = "";
		double permit_fee = 0.0;
		try {
			if (lic.equalsIgnoreCase("1")) {
				lic_type = "BWFL2A";
			} else if (lic.equalsIgnoreCase("2")) {
				lic_type = "BWFL2B";
			} else if (lic.equalsIgnoreCase("3")) {
				lic_type = "BWFL2C";
			} else if (lic.equalsIgnoreCase("4")) {
				lic_type = "BWFL2D";
			}
			sql = "SELECT lic_type,fee FROM licence.bond_permit_fee WHERE lic_type='" + lic_type + "'";

			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				permit_fee = rs.getDouble("fee");
			} else {
				permit_fee = 0.0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

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
		return permit_fee;
	}

	// =============================print report============================

	public boolean printReportBWFL(BWFLImportDataTable_20_21 dt) {

		String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
		String relativePath = mypath + File.separator + "ExciseUp" + File.separator + "Bond" + File.separator
				+ "jasper";
		String relativePathpdf = mypath + File.separator + "ExciseUp" + File.separator + "Bond" + File.separator
				+ "BWFLpermit";
		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		String reportQuery = null;
		boolean printFlag = false;

		try {
			con = ConnectionToDataBase.getConnection();

			reportQuery = " SELECT DISTINCT a.import_fee_challan_no,case when br.strength>5 then 'Strong' else 'Lager' end as strength, "
					+ " a.spcl_fee_challan_no, "
					+ " (select amount from bwfl_license.chalan_deposit_bwfl_fl2d                                                                     "
					+ " where chalan_no=a.import_fee_challan_no and unit_id=a.bwfl_id and                                                             "
					+ " unit_type=CASE WHEN a.bwfl_type=1 then 'BWFL2A' WHEN a.bwfl_type=2 THEN 'BWFL2B'                                              "
					+ " WHEN a.bwfl_type=3 THEN 'BWFL2C' WHEN a.bwfl_type=4 THEN 'BWFL2D' WHEN a.bwfl_type=99 THEN 'FL2D' END) as import_chalan_amnt, "
					+ " (select amount from bwfl_license.chalan_deposit_bwfl_fl2d                                                                     "
					+ " where chalan_no=a.spcl_fee_challan_no and unit_id=a.bwfl_id and                                                               "
					+ " unit_type=CASE WHEN a.bwfl_type=1 then 'BWFL2A' WHEN a.bwfl_type=2 THEN 'BWFL2B'                                              "
					+ " WHEN a.bwfl_type=3 THEN 'BWFL2C' WHEN a.bwfl_type=4 THEN 'BWFL2D' WHEN a.bwfl_type=99 THEN 'FL2D' END) as spcl_chalan_amnt,   "
					+ " (select chalan_date from bwfl_license.chalan_deposit_bwfl_fl2d                                                                "
					+ " where chalan_no=a.import_fee_challan_no and unit_id=a.bwfl_id and                                                             "
					+ " unit_type=CASE WHEN a.bwfl_type=1 then 'BWFL2A' WHEN a.bwfl_type=2 THEN 'BWFL2B'                                              "
					+ " WHEN a.bwfl_type=3 THEN 'BWFL2C' WHEN a.bwfl_type=4 THEN 'BWFL2D' WHEN a.bwfl_type=99 THEN 'FL2D' END) as import_chalan_date, "
					+ " (select chalan_date from bwfl_license.chalan_deposit_bwfl_fl2d                                                                "
					+ " where chalan_no=a.spcl_fee_challan_no and unit_id=a.bwfl_id and                                                               "
					+ " unit_type=CASE WHEN a.bwfl_type=1 then 'BWFL2A' WHEN a.bwfl_type=2 THEN 'BWFL2B'                                              "
					+ " WHEN a.bwfl_type=3 THEN 'BWFL2C' WHEN a.bwfl_type=4 THEN 'BWFL2D' WHEN a.bwfl_type=99 THEN 'FL2D' END) as spcl_chalan_date,   "
					+ " a.id, a.district_id, a.bwfl_id, a.unit_id, a.bwfl_type,                                                "
					+ " a.import_fee as tot_import_fee,                                                                                        "
					+ " (a.duty+a.add_duty)as tot_duty, a.special_fee as tot_special_fee, a.cr_date,                                           "
					+ " a.vch_approved, a.vch_status, a.deo_time, a.deo_remark, a.deo_user, a.deo_date,                                        "
					+ " a.bottlng_type, a.valid_upto, a.route_road_radio, a.route_detail,                                                      "
					+ " CASE WHEN a.bwfl_type=1 THEN 'BWFL2A' WHEN a.bwfl_type=2 THEN 'BWFL2B'                                                 "
					+ " WHEN a.bwfl_type=3 THEN 'BWFL2C' WHEN a.bwfl_type=4 THEN 'BWFL2D' end as type,                                         "
					+ " CASE WHEN a.route_road_radio='route' THEN 'By Train' "
					+ " WHEN a.route_road_radio='road' THEN 'By Road' end as route_type,  "
					+ " b.vch_firm_name, b.vch_license_number, b.vch_firm_add, c.description,                                                  "
					+ " CONCAT(b.vch_firm_name, '(' ,b.vch_firm_add,')')as consignee_nm_adrs,                                                  "
					+ " (SELECT d.vch_state_name FROM public.state_ind d WHERE f.vch_reg_office_state=d.int_state_id::text)as vch_state_name,  "
					+ " d.no_of_cases, d.no_of_bottle_per_case, d.pland_no_of_bottles, a.permit_nmbr, d.import_fee,                            "
					+ " (d.duty+d.add_duty) as duty, d.special_fee, a.import_fee_challan_no, a.spcl_fee_challan_no,                            "
					+ " f.vch_indus_name, f.vch_reg_office_add,                                                                                "
					+ " CONCAT(f.vch_indus_name, '(' ,f.vch_reg_office_add,')')as consignor_nm_adrs,                                           "
					+ " br.brand_name, pk.package_name, (pk.duty+pk.adduty) as duty_rate,                                                                                        "
					+ " round(CAST(float8(((d.pland_no_of_bottles)*pk.quantity)/1000) as numeric), 2) as bl                                    "
					+ " FROM bwfl_license.import_permit_20_21 a, bwfl.registration_of_bwfl_lic_holder_20_21 b,                                       "
					+ " public.district c, bwfl_license.import_permit_dtl_20_21 d,                                                                   "
					+ " public.other_unit_registration_20_21 f,                                                                                      "
					+ " distillery.brand_registration_20_21 br, distillery.packaging_details_20_21 pk                                          "
					+ " WHERE a.bwfl_id=b.int_id AND a.district_id=c.districtid  "
					+ " AND a.id=d.fk_id AND a.district_id=d.district_id AND a.login_type=d.login_type AND a.app_id=d.app_id              "
					+ " AND b.unit_id=f.int_app_id_f AND a.unit_id=b.unit_id   and CASE WHEN a.bwfl_type=1 THEN vch_indus_type='OUPD'  "
					+ "WHEN a.bwfl_type=2  THEN vch_indus_type='OUPB'      WHEN a.bwfl_type=3  THEN vch_indus_type='OUPW'  "
					+ "WHEN a.bwfl_type=4  THEN vch_indus_type='OUPBU'      WHEN a.bwfl_type=99  THEN vch_indus_type='IU'  end  "
					+ " AND br.brand_id=d.brand_id and br.brand_id=pk.brand_id_fk and d.pckg_id=pk.package_id                                  "
					+ " AND a.permit_nmbr='" + dt.getPermitNmbr_dt() + "' ";

			// System.out.println("reportQuery---------------------"+reportQuery);
			pst = con.prepareStatement(reportQuery);
			rs = pst.executeQuery();

			if (rs.next()) {
				rs = pst.executeQuery();

				Map parameters = new HashMap();
				parameters.put("REPORT_CONNECTION", con);
				// parameters.put("SUBREPORT_DIR", relativePath+File.separator);
				parameters.put("image", relativePath + File.separator);
				/*
				 * if(dt.getLoginType_dt().equalsIgnoreCase("BWFL")){
				 * parameters.put("balImportFee", act.getBalRgstrImportFee()); } else
				 * if(dt.getLoginType_dt().equalsIgnoreCase("FL2D")){
				 * parameters.put("balImportFee", act.getBalFL2DImportFee()); }
				 */

				JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);
				if (dt.getLicenseType_dt().equalsIgnoreCase("BWFL2B")) {

					jasperReport = (JasperReport) JRLoader
							.loadObject(relativePath + File.separator + "BWFLImportPermitBwfl2B.jasper");

				} else {
					jasperReport = (JasperReport) JRLoader
							.loadObject(relativePath + File.separator + "BWFLImportPermit.jasper");
				}
				JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, jrRs);

				Random rand = new Random();
				int n = rand.nextInt(250) + 1;

				JasperExportManager.exportReportToPdfFile(print, relativePathpdf + File.separator
						+ dt.getPermitNmbr_dt().trim().replaceAll("\\s+", "") + ".pdf");

				// dt.setPdfName(dt.getAppId_dt()+
				// "-"+dt.getBwflID_dt()+"_"+dt.getLoginType_dt()+"ImportPermit.pdf");
				getDataForDigitalSign(dt);

				printFlag = true;
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No Data Found", "No Data Found"));
				printFlag = false;

			}
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
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

	public void getDataForDigitalSign(BWFLImportDataTable_20_21 dt) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select login_type,bwfl_id,app_id,COALESCE(permit_nmbr,'NA') as permit_nmbr from bwfl_license.import_permit_20_21 where permit_nmbr='"
				+ dt.getPermitNmbr_dt() + "'";
		try {
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dt.setDigital_sign_appId_dt(rs.getString("app_id"));
				dt.setDigital_sign_bwflID_dt(rs.getString("bwfl_id"));
				dt.setDigital_sign_Flag(true);
				dt.setDigital_sign_loginType_dt(rs.getString("login_type"));
				dt.setDigital_sign_permitNmbr_dt(rs.getString("permit_nmbr"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("No Record Found", "No Record Found"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
