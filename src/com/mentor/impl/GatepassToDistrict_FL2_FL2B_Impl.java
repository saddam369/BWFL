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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

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

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mentor.action.GatepassToDistrict_FL2_FL2B_Action;
import com.mentor.datatable.GatepassToDistrict_FL2_FL2B_DT;
import com.mentor.resource.ConnectionToDataBase;

import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;
//import com.itextpdf.text.log.SysoCounter;
//import com.mentor.datatable.GatePassFl2_Fl2BDataTablek;

public class GatepassToDistrict_FL2_FL2B_Impl {

	// =====================get fl2_fl2b
	// details=================================

	public String getDetails(GatepassToDistrict_FL2_FL2B_Action act) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";String selQr = "";
		try {
			con = ConnectionToDataBase.getConnection();
			   selQr = " SELECT int_app_id, vch_licence_no, vch_firm_name, vch_license_type, vch_core_address, vch_mobile_no "
					+ " FROM licence.fl2_2b_2d_19_20  "
					+ " WHERE loginid='"
					+ ResourceUtil.getUserNameAllReq().trim() + "' ";
			 
			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				act.setVch_licence_no(rs.getString("vch_licence_no"));
				act.setFl2_fl2bId(rs.getInt("int_app_id"));
				act.setFl2_fl2bName(rs.getString("vch_firm_name"));
				act.setFl2_fl2bAdrs(rs.getString("vch_core_address"));
				act.setFl2LicenseType(rs.getString("vch_license_type"));
				if (rs.getString("vch_license_type").equalsIgnoreCase("FL2")) {

					act.setVch_from("FL2");
				} else if (rs.getString("vch_license_type").equalsIgnoreCase(
						"FL2B")) {

					act.setVch_from("FL2B");
				} else if (rs.getString("vch_license_type").equalsIgnoreCase(
						"CL2")) {

					act.setVch_from("CL2");
				}
			}else {rs.close();pstmt.close();
				   selQr = " SELECT int_app_id, vch_licence_no, vch_firm_name, vch_license_type, vch_core_address, vch_mobile_no "
							+ " FROM licence.fl2_2b_2d_20_21  "
							+ " WHERE loginid='"
							+ ResourceUtil.getUserNameAllReq().trim() + "' ";
					 
					pstmt = con.prepareStatement(selQr);

					rs = pstmt.executeQuery();

					if (rs.next()) {
						act.setVch_licence_no(rs.getString("vch_licence_no"));
						act.setFl2_fl2bId(rs.getInt("int_app_id"));
						act.setFl2_fl2bName(rs.getString("vch_firm_name"));
						act.setFl2_fl2bAdrs(rs.getString("vch_core_address"));
						act.setFl2LicenseType(rs.getString("vch_license_type"));
						if (rs.getString("vch_license_type").equalsIgnoreCase("FL2")) {

							act.setVch_from("FL2");
						} else if (rs.getString("vch_license_type").equalsIgnoreCase(
								"FL2B")) {

							act.setVch_from("FL2B");
						} else if (rs.getString("vch_license_type").equalsIgnoreCase(
								"CL2")) {

							act.setVch_from("CL2");
						}
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

	// ========================get bar restaurant and club license
	// number==========================

	public ArrayList getbrcLicenseNo(GatepassToDistrict_FL2_FL2B_Action act) {

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
					+ " WHERE finalize IS NOT NULL ";

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

	// ===============================get shops license
	// number==========================================

	public ArrayList getlicenseNmbr(GatepassToDistrict_FL2_FL2B_Action act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("");
		list.add(item);
		try {

			String query = " SELECT vch_name_of_shop, serial_no "
					+ " FROM retail.retail_shop ORDER BY vch_name_of_shop ";

			// System.out.println("retailler query------------" + query);
			conn = ConnectionToDataBase.getConnection();

			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();

				item.setValue(rs.getString("serial_no"));
				item.setLabel(rs.getString("vch_name_of_shop"));

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

	// ===============================get district
	// list==================================

	public ArrayList getDistrictList(GatepassToDistrict_FL2_FL2B_Action act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		/*
		 * item.setLabel("--select--"); item.setValue(0); list.add(item);
		 */

		try {
			// String query =
			// " SELECT districtid, description FROM public.district ORDER BY description ";

			String query = " SELECT distinct coalesce(a.districtid,0) as districtid , a.description FROM public.district a ,licence.fl2_2b_2d_19_20 b  where  a.districtid=b.core_district_id  and b.loginid='" + ResourceUtil.getUserNameAllReq().trim() + "' union "
					+ " SELECT distinct coalesce(a.districtid,0) as districtid , a.description FROM public.district a ,licence.fl2_2b_2d_20_21 b  where  a.districtid=b.core_district_id  and b.loginid='" + ResourceUtil.getUserNameAllReq().trim() + "'  ";

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				item = new SelectItem();

				item.setValue(rs.getString("districtid"));
				item.setLabel(rs.getString("description"));

				act.setDistrictLic(Integer.parseInt(rs.getString("districtid")));
				act.setDistrict1(Integer.parseInt(rs.getString("districtid")));
				act.setDistrict2(Integer.parseInt(rs.getString("districtid")));
				act.setDistrict3(Integer.parseInt(rs.getString("districtid")));

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

	// ===========================get licensee
	// detail==============================

	public String getlicenseeDetail(GatepassToDistrict_FL2_FL2B_Action act,
			String val) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;

		try {
			con = ConnectionToDataBase.getConnection();

			String selQr = " SELECT fl2_2bid, license_number, name_of_license, address  "
					+ " FROM hotel_bar_rest.registration_for_hotels_bars_restraunt  "
					+ " WHERE license_number='" + val + "' ";

			// System.out.println("licensee------------"+selQr);
			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				act.setLicenseeId(rs.getInt("fl2_2bid"));
				act.setLicenseeName(rs.getString("name_of_license"));
				act.setLicenseeAdrs(rs.getString("address"));

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

	// ===========================get retailer licensee
	// detail==============================

	public String getretailLicenseeDetail(
			GatepassToDistrict_FL2_FL2B_Action act, String val) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;

		try {
			con = ConnectionToDataBase.getConnection();

			String selQr = " SELECT vch_name_of_licensee, vch_location_of_shop, serial_no "
					+ " FROM retail.retail_shop WHERE serial_no='" + val + "' ";

			// System.out.println("licensee---------retaillll---"+selQr);
			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				act.setLicenseeId(rs.getInt("serial_no"));
				act.setLicenseeName(rs.getString("vch_name_of_licensee"));
				act.setLicenseeAdrs(rs.getString("vch_location_of_shop"));

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

	// ================================get data of first
	// datatable======================

	public ArrayList displaylistImpl(GatepassToDistrict_FL2_FL2B_Action act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		int i = 1, cases = 0;

		try {
			if (act.getVch_from().equalsIgnoreCase("FL2")) {
				selQr = "  SELECT a.package_name, b.brand_name,b.unit_nm, "
						+ " c.brand_id, c.id, c.type,(c.recv_total_bottels-COALESCE(c.dispatchbotl,0)) as avlbottle,                        "
						+ "	    c.pckg_id,  c.size  as box_size                                                    "
						+ "	    FROM distillery.packaging_details_19_20 a   ,                                         "
						+ "	    distillery.brand_registration_19_20 b,fl2d.fl2_2b_stock_19_20   c                                                         "
						+ "	    WHERE a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.brand_id                                                   "
						+ "	    AND a.package_id=c.pckg_id AND b.brand_id=c.brand_id and c.recv_total_bottels-COALESCE(c.dispatchbotl,0)>0                                                         "
						+ "	    AND c.id='"
						+ act.getFl2_fl2bId()
						+ "'                                                                                             "
						+ "	    group by c.brand_id, c.id,c.pckg_id, a.package_name, b.unit_name, c.type,  "
						+ " b.brand_name,c.size , b.distillery_id, b.brewery_id, b.int_bwfl_id,b.int_fl2d_id,b.unit_nm      "
						+ "	order by   b.brand_name,a.package_name           ";
			} else {
				selQr = " SELECT a.package_name, b.brand_name, b.unit_nm, "
						+ " c.brand_id, c.id, c.type,(c.recv_total_bottels-COALESCE(c.dispatchbotl,0)) as avlbottle,                        "
						+ "	    c.pckg_id, c.size as box_size                                                    "
						+ "	    FROM distillery.packaging_details_19_20 a     ,                                         "
						+ "	    distillery.brand_registration_19_20 b,fl2d.fl2_2b_stock_19_20   c                                                         "
						+ "	    WHERE a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.brand_id                                                   "
						+ "	    AND a.package_id=c.pckg_id AND b.brand_id=c.brand_id and c.recv_total_bottels-COALESCE(c.dispatchbotl,0)>0                                                         "
						+ "	    AND c.id='"
						+ act.getFl2_fl2bId()
						+ "'                                                                                            "
						+ "	    group by c.brand_id, c.id,c.pckg_id, a.package_name,b.unit_name,  c.type,  "
						+ " b.brand_name,c.size, b.distillery_id, b.brewery_id, b.int_bwfl_id,b.int_fl2d_id,b.unit_nm      "
						+ "	order by   b.brand_name,a.package_name           ";
			}
			/*
			 * "select z.*from (                                                                 "
			 * +
			 * " SELECT a.package_name,  b.brand_name,   c.brand_id, c.fl2_2bid, c.fl2_2btype,                                                    "
			 * +
			 * " c.pckg_id, (sum(c.total_recv_bottels) -coalesce((select sum(coalesce(x.dispatch_bottle,0)) from fl2d.fl2_stock_trxn_fl2_fl2b_19_20 x"
			 * + " where  x.int_fl2_fl2b_id="+ act.getFl2_fl2bId() +
			 * "   and x.int_brand_id=c.brand_id::int and x.int_pckg_id=c.pckg_id::int  "
			 * +
			 * " and x.int_brand_id=c.brand_id::int and   x.int_pckg_id=c.pckg_id::int),0))     as avlbottle  ,           "
			 * +
			 * "  case when coalesce(a.export_box_size,0) > 0  then a.export_box_size else d.box_size end     as box_size        "
			 * +
			 * " FROM distillery.packaging_details_19_20 a                                                                               "
			 * +
			 * " left outer join distillery.box_size_details d on d.box_id=a.box_id  ,   distillery.brand_registration_19_20 b,          "
			 * +
			 * " fl2d.fl2_2b_receiving_stock_trxn c  WHERE a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.brand_id                                                       "
			 * +
			 * " AND a.package_id=c.pckg_id AND b.brand_id=c.brand_id                                                              "
			 * +" AND c.fl2_2bid='" + act.getFl2_fl2bId() +
			 * "'                                                                                              "
			 * +
			 * " group by c.brand_id, c.fl2_2bid,c.pckg_id, a.package_name,  c.fl2_2btype,  b.brand_name,a.export_box_size,d.box_size             "
			 * +
			 * "  order by   b.brand_name,a.package_name )z where z.avlbottle>0 "
			 * ;
			 */

			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(selQr);

			// System.out.println("stock query--------------"+selQr);

			rs = ps.executeQuery();
			while (rs.next()) {
				GatepassToDistrict_FL2_FL2B_DT dt = new GatepassToDistrict_FL2_FL2B_DT();
				dt.setInt_brand_id(rs.getInt("brand_id"));
				dt.setInt_pckg_id(rs.getInt("pckg_id"));
				dt.setVch_brand(rs.getString("brand_name"));
				dt.setBrandUnitName_dt(rs.getString("unit_nm"));
				dt.setInt_bottle_avaliable(rs.getInt("avlbottle"));
				dt.setPackageName(rs.getString("package_name"));
				// dt.setSeq(rs.getInt("seq"));
				dt.setSize(rs.getInt("box_size"));
				// dt.setBoxAvailable(rs.getInt("available_box"));
				dt.setBalance(rs.getInt("avlbottle"));
				// dt.setDb_duty(rs.getDouble("duty"));
				// dt.setDb_add_duty(rs.getDouble("adduty"));
				dt.setSlno(i);
				cases++;
				// act.setRecieveCases(act.getRecieveCases() + cases);

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
		String query = " SELECT max(seq) as id FROM fl2d.gatepass_to_districtwholesale_fl2_fl2b_19_20 ";
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

	// =================================save
	// data===================================

	public String saveMethodImpl(GatepassToDistrict_FL2_FL2B_Action act) {

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
		int seq = this.maxId();

		String gatepass = String.valueOf(act.getFl2_fl2bId()) + "-2019-20-"+ act.getFl2LicenseType().trim() + "-" + seq;
		
		
		try {
			int cases = 0;
			int totalBottles = 0;

			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);

			String insQr = " INSERT INTO fl2d.gatepass_to_districtwholesale_fl2_fl2b_19_20( "
					+ " int_fl2_fl2b_id, vch_gatepass_no, dt_date, vch_to, vch_to_lic_no, curr_date, licencee_id, "
					+ " vch_route_detail, vch_vehicle_no, vehicle_driver_name, vehicle_agency_name_adrs, "
					+ " licensee_name, licensee_adrs, district_1, district_2, district_3, valid_till, "
					+ " licence_district, seq, gross_weight, tier_weight, net_weight, vch_from,shop_nm,shop_id,challan_no,challan_date,challan_amount ) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?) ";

			ps = con.prepareStatement(insQr);

			ps.setInt(1, act.getFl2_fl2bId());
			ps.setString(2, gatepass);

			// System.out.println("------------gatepass---------"+gatepass);

			ps.setDate(3, Utility.convertUtilDateToSQLDate(act.getDt_date()));
			ps.setString(4, act.getVch_to());

			if (act.vch_to.equalsIgnoreCase("BRC")) {
				ps.setString(5, act.getVch_to_lic_noNew().trim());
			}

			else if (act.vch_to.equalsIgnoreCase("RT")) {
				ps.setString(5, act.getShopnoNew().trim());

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
			ps.setDate(17,
					Utility.convertUtilDateToSQLDate(act.getValidtilldt_date()));
			ps.setInt(18, act.getDistrictLic());
			ps.setInt(19, seq);
			ps.setDouble(20, act.getGrossWeight());
			ps.setDouble(21, act.getTierWeight());
			ps.setDouble(22, act.getNetWeight());
			ps.setString(23, act.getVch_from());

			if (act.vch_to.equalsIgnoreCase("RT")) {
				ps.setString(24, act.getShopName());
			} else {
				ps.setString(24, "");
			}

			if (act.vch_to.equalsIgnoreCase("RT")) {
				ps.setString(25, act.getShopnoNew().trim());

			} else {
				ps.setString(25, "0");
			}

			if (act.eco_flag) {
				ps.setString(26, act.getChallan_no());
				ps.setDate(27,
						Utility.convertUtilDateToSQLDate(act.getChallan_dt()));
				ps.setLong(28, Long.parseLong(act.getChallan_amt()));

			} else {
				ps.setString(26, "");
				ps.setDate(27, null);
				ps.setInt(28, 0);
			}

			tok1 = ps.executeUpdate();

			if (tok1 > 0) {

				for (int i = 0; i < act.getDisplaylist().size(); i++) {

					GatepassToDistrict_FL2_FL2B_DT dt = (GatepassToDistrict_FL2_FL2B_DT) act
							.getDisplaylist().get(i);
					if (dt.getDispatchbottls() > 0 && dt.getDispatchbox() > 0) {
						tok1 = 0;

						String insQr1 = " INSERT INTO fl2d.fl2_stock_trxn_fl2_fl2b_19_20( "
								+ " int_fl2_fl2b_id, vch_lic_type, dt, int_brand_id, int_pckg_id, avl_bottl, avl_box, "
								+ " breakage, balance, vch_gatepass_no, size, vch_batch_no, dispatch_bottle, "
								+ " dispatch_box, seq_fl2_fl2b, mst_seq) VALUES "
								+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, (select nextval('seq')))";

						ps2 = con.prepareStatement(insQr1);

						ps2.setInt(1, act.getFl2_fl2bId());
						ps2.setString(2, act.getVch_to());
						ps2.setDate(3, Utility.convertUtilDateToSQLDate(act
								.getDt_date()));
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
						ps2.setInt(14, dt.getDispatchbox());
						ps2.setInt(15, dt.getSeq());

						cases += dt.getDispatchbox();
						totalBottles += dt.getDispatchbottls();
						tok1 = ps2.executeUpdate();

						if (tok1 > 0) {

							tok1 = 0;

							String updtQr = " UPDATE fl2d.fl2_2b_stock_19_20 "
									+ " SET dispatchbotl = COALESCE(dispatchbotl,0)+("
									+ dt.getDispatchbottls() + ") " + " WHERE id='"
									+ act.getFl2_fl2bId() + "' AND brand_id='"
									+ dt.getInt_brand_id() + "'"
									+ " AND pckg_id='" + dt.getInt_pckg_id()
									+ "' AND type='" + act.getVch_from()
									+ "' and size=" + dt.getSize() + "";

							System.out.println(updtQr);

							ps3 = con.prepareStatement(updtQr);
							tok1 = ps3.executeUpdate();

						}

					}

				}

			}

			if (tok1 > 0) {

				con.commit();
				act.clearAll();
				act.setListFlagForPrint(true);

				ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,
						Constants.SAVED_SUCESSFULLY);

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

	// ============================get data on second data
	// table========================================

	public ArrayList getTable2List1(GatepassToDistrict_FL2_FL2B_Action act,
			Date ev) {
		ArrayList list = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String selqr = " SELECT int_fl2_fl2b_id, vch_gatepass_no, dt_date, vch_to, vch_to_lic_no, curr_date, "
				+ " seq, vch_from,coalesce(vch_finalize,'N') as vch_finalize "
				+ " FROM fl2d.gatepass_to_districtwholesale_fl2_fl2b_19_20 "
				+ " WHERE int_fl2_fl2b_id='"
				+ act.getFl2_fl2bId()
				+ "' and dt_date=? order by  seq desc";

		// System.out.println("second datatable==============="+query);
		try {
			list = new ArrayList();
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(selqr);
			ps.setDate(1, Utility.convertUtilDateToSQLDate(ev));
			rs = ps.executeQuery();
			int i = 1;
			while (rs.next()) {
				GatepassToDistrict_FL2_FL2B_DT dt = new GatepassToDistrict_FL2_FL2B_DT();

				dt.setSerialNo(i);
				dt.setType(rs.getString("vch_from"));
				dt.setCurrentDate(rs.getDate("dt_date"));
				dt.setVchTO(rs.getString("vch_to"));
				dt.setGatepassNo(rs.getString("vch_gatepass_no"));
				dt.setLicenseNo(rs.getString("vch_to_lic_no"));
				dt.setMaxId(rs.getInt("seq"));
				if (rs.getString("vch_finalize").equalsIgnoreCase("F")) {
					dt.setFinflg(true);
				} else {
					dt.setFinflg(false);
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

	public ArrayList getTable2List(GatepassToDistrict_FL2_FL2B_Action act) {
		ArrayList list = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String selqr = " SELECT int_fl2_fl2b_id, vch_gatepass_no, dt_date, vch_to, vch_to_lic_no, curr_date, "
				+ " seq, vch_from,coalesce(vch_finalize,'N') as vch_finalize "
				+ " FROM fl2d.gatepass_to_districtwholesale_fl2_fl2b_19_20 "
				+ " WHERE int_fl2_fl2b_id='"
				+ act.getFl2_fl2bId()
				+ "' and dt_date=? order by  seq desc";

		// System.out.println("second datatable==============="+query);
		try {
			list = new ArrayList();
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(selqr);
			ps.setDate(1, Utility.convertUtilDateToSQLDate(act.getTable_dt()));
			rs = ps.executeQuery();
			int i = 1;
			while (rs.next()) {
				GatepassToDistrict_FL2_FL2B_DT dt = new GatepassToDistrict_FL2_FL2B_DT();

				dt.setSerialNo(i);
				dt.setType(rs.getString("vch_from"));
				dt.setCurrentDate(rs.getDate("dt_date"));
				dt.setVchTO(rs.getString("vch_to"));
				dt.setGatepassNo(rs.getString("vch_gatepass_no"));
				dt.setLicenseNo(rs.getString("vch_to_lic_no"));
				dt.setMaxId(rs.getInt("seq"));
				if (rs.getString("vch_finalize").equalsIgnoreCase("F")) {
					dt.setFinflg(true);
				} else {
					dt.setFinflg(false);
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

	// ------------------------get email details-------------------------

	public String getEmailDetails(GatepassToDistrict_FL2_FL2B_Action act) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			String queryList = " SELECT a.officer_email "
					+ " FROM public.district a, fl2d.gatepass_to_districtwholesale_fl2_fl2b_19_20 b "
					+ " WHERE a.districtid=b.licence_district ";

			pstmt = con.prepareStatement(queryList);

			// System.out.println("email query-----------------"+queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				act.setOfficrEmail(rs.getString("officer_email"));
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

	// =============================print report============================

	/*
	 * public boolean printReport(GatepassToDistrict_FL2_FL2B_Action act,
	 * GatepassToDistrict_FL2_FL2B_DT dt) {
	 * 
	 * String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
	 * String relativePath = mypath + File.separator + "ExciseUp" +
	 * File.separator + "WholeSale" + File.separator + "jasper"; String
	 * relativePathpdf = mypath + File.separator + "ExciseUp" + File.separator +
	 * "WholeSale" + File.separator + "pdf"; JasperReport jasperReport = null;
	 * JasperPrint jasperPrint = null; PreparedStatement pst = null; Connection
	 * con = null; ResultSet rs = null; String reportQuery = null; boolean
	 * printFlag = false;
	 * 
	 * try { con = ConnectionToDataBase.getConnection();
	 * 
	 * reportQuery =
	 * " SELECT DISTINCT c.breakage,a.int_fl2_fl2b_id, a.vch_gatepass_no, a.dt_date, a.vch_to, "
	 * + " a.vch_to_lic_no, a.curr_date, a.vch_route_detail, a.vch_vehicle_no, "
	 * + " a.vehicle_driver_name, a.vehicle_agency_name_adrs, a.licensee_name, "
	 * +
	 * " a.licensee_adrs, a.valid_till, a.licence_district, a.gross_weight, a.tier_weight, "
	 * +
	 * " a.net_weight, a.vch_from,b.vch_firm_name, c.dispatch_box, c.int_brand_id, "
	 * + " c.size as box_size, c.dispatch_bottle as no_bottl, c.vch_batch_no, "
	 * +
	 * " d.brand_name, d.strength, e.package_name,e.box_id, e.quantity as ml, "
	 * + " (((c.dispatch_bottle-c.breakage)*e.quantity )/1000) as bl, " +
	 * " (((((c.dispatch_bottle-c.breakage)*e.quantity )/1000)*d.strength)/100) as al "
	 * +
	 * " FROM fl2d.gatepass_to_districtwholesale_fl2_fl2b_19_20 a, licence.fl2_2b_2d_19_20 b, "
	 * +
	 * " fl2d.fl2_stock_trxn_fl2_fl2b_19_20 c, distillery.brand_registration_19_20 d, "
	 * + " distillery.packaging_details_19_20 e " +
	 * " WHERE a.int_fl2_fl2b_id=b.int_app_id AND a.int_fl2_fl2b_id=c.int_fl2_fl2b_id "
	 * + " AND a.vch_gatepass_no=c.vch_gatepass_no and a.dt_date=c.dt " +
	 * " AND c.int_brand_id=d.brand_id AND d.brand_id=e.brand_id_fk " +
	 * " AND c.int_pckg_id=e.package_id   " + " AND a.int_fl2_fl2b_id=" +
	 * act.getFl2_fl2bId() + " AND a.vch_gatepass_no='" +
	 * act.getPrintGatePassNo() + "' ";
	 * 
	 * //System.out.println("reportQuery---------------------"+reportQuery); pst
	 * = con.prepareStatement(reportQuery); rs = pst.executeQuery();
	 * 
	 * if (rs.next()) { rs = pst.executeQuery();
	 * 
	 * Map parameters = new HashMap(); parameters.put("REPORT_CONNECTION", con);
	 * // parameters.put("SUBREPORT_DIR", relativePath+File.separator);
	 * parameters.put("image", relativePath + File.separator);
	 * 
	 * JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);
	 * 
	 * jasperReport = (JasperReport) JRLoader.loadObject(relativePath +
	 * File.separator + "GatePassToRetailerFL2_FL2BPrint.jasper");
	 * 
	 * JasperPrint print = JasperFillManager.fillReport(jasperReport,
	 * parameters, jrRs); Random rand = new Random(); int n = rand.nextInt(250)
	 * + 1;
	 * 
	 * // JasperExportManager.exportReportToPdfFile(print,relativePathpdf // +
	 * File.separator+ "GatePassToRetailerFL2_FL2BPrint"+ //
	 * act.getPrintGatePassNo() + "-" + n+ ".pdf");
	 * JasperExportManager.exportReportToPdfFile(print, relativePathpdf +
	 * File.separator + "FL2_FL2B"+act.getPrintGatePassNo() + n + ".pdf");
	 * 
	 * dt.setPdfName("FL2_FL2B"+act.getPrintGatePassNo() + n + ".pdf");
	 * act.setPdfname("FL2_FL2B"+act.getPrintGatePassNo() + n + ".pdf");
	 * dt.setPrintFlag(true); printFlag = true;
	 * 
	 * 
	 * dt.setPdfDraft("GatePassToRetailerFL2_FL2BDraft1"+ n + ".pdf");
	 * act.setPdfDraft("GatePassToRetailerFL2_FL2BDraft1"+ n + ".pdf");
	 * dt.setPrintDraftFlagDt(true); printFlag = true;
	 * 
	 * 
	 * } else { FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage("No Data Found", "No Data Found"));
	 * 
	 * printFlag = false; dt.setPrintFlag(false); } } catch (JRException e) {
	 * e.printStackTrace(); } catch (Exception e) { e.printStackTrace(); }
	 * finally { try { if (rs != null) rs.close(); if (con != null) con.close();
	 * } catch (SQLException e) { e.printStackTrace(); } } return printFlag;
	 * 
	 * }
	 * 
	 * // -------------------------print draft--------------------------
	 * 
	 * public boolean printDraftReport(GatepassToDistrict_FL2_FL2B_Action act,
	 * GatepassToDistrict_FL2_FL2B_DT dt) {
	 * 
	 * String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
	 * String relativePath = mypath + File.separator + "ExciseUp" +
	 * File.separator + "WholeSale" + File.separator + "jasper"; String
	 * relativePathpdf = mypath + File.separator + "ExciseUp" + File.separator +
	 * "WholeSale" + File.separator + "pdf"; JasperReport jasperReport = null;
	 * JasperPrint jasperPrint = null; PreparedStatement pst = null; Connection
	 * con = null; ResultSet rs = null; String reportQuery = null; boolean
	 * printDraftFlag = false;
	 * 
	 * try { con = ConnectionToDataBase.getConnection();
	 * 
	 * //
	 * System.out.println(" printdraft -gatepass------------"+act.getPrintGatePassNo
	 * ());
	 * 
	 * reportQuery =
	 * " SELECT DISTINCT c.breakage,a.int_fl2_fl2b_id, a.vch_gatepass_no, a.dt_date, a.vch_to, "
	 * + " a.vch_to_lic_no, a.curr_date, a.vch_route_detail, a.vch_vehicle_no, "
	 * + " a.vehicle_driver_name, a.vehicle_agency_name_adrs, a.licensee_name, "
	 * +
	 * " a.licensee_adrs, a.valid_till, a.licence_district, a.gross_weight, a.tier_weight, "
	 * +
	 * " a.net_weight, a.vch_from,b.vch_firm_name, c.dispatch_box, c.int_brand_id, "
	 * + " c.size as box_size, c.dispatch_bottle as no_bottl, c.vch_batch_no, "
	 * +
	 * " d.brand_name, d.strength, e.package_name,e.box_id, e.quantity  as ml, "
	 * + " (((c.dispatch_bottle-c.breakage)*e.quantity )/1000) as bl, " +
	 * " (((((c.dispatch_bottle-c.breakage)*e.quantity )/1000)*d.strength)/100) as al "
	 * +
	 * " FROM fl2d.gatepass_to_districtwholesale_fl2_fl2b_19_20 a, licence.fl2_2b_2d_19_20 b, "
	 * +
	 * " fl2d.fl2_stock_trxn_fl2_fl2b_19_20 c, distillery.brand_registration_19_20 d, "
	 * + " distillery.packaging_details_19_20 e, distillery.box_size_details f "
	 * +
	 * " WHERE a.int_fl2_fl2b_id=b.int_app_id AND a.int_fl2_fl2b_id=c.int_fl2_fl2b_id "
	 * + " AND a.vch_gatepass_no=c.vch_gatepass_no and a.dt_date=c.dt " +
	 * " AND c.int_brand_id=d.brand_id AND d.brand_id=e.brand_id_fk " +
	 * " AND c.int_pckg_id=e.package_id   " + " AND a.int_fl2_fl2b_id=" +
	 * act.getFl2_fl2bId() + " AND a.vch_gatepass_no='" +
	 * act.getPrintGatePassNo() + "' ";
	 * 
	 * // System.out.println("reportQuery---------------------"+reportQuery);
	 * pst = con.prepareStatement(reportQuery); rs = pst.executeQuery();
	 * 
	 * if (rs.next()) { rs = pst.executeQuery();
	 * 
	 * Map parameters = new HashMap(); parameters.put("REPORT_CONNECTION", con);
	 * // parameters.put("SUBREPORT_DIR", relativePath+File.separator);
	 * parameters.put("image", relativePath + File.separator);
	 * 
	 * JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);
	 * 
	 * jasperReport = (JasperReport) JRLoader.loadObject(relativePath +
	 * File.separator + "GatePassToRetailerFL2_FL2BDraft1.jasper");
	 * 
	 * JasperPrint print = JasperFillManager.fillReport(jasperReport,
	 * parameters, jrRs); Random rand = new Random(); int n = rand.nextInt(250)
	 * + 1;
	 * 
	 * // JasperExportManager.exportReportToPdfFile(print,relativePathpdf // +
	 * File.separator+ "GatePassToRetailerFL2_FL2BPrint"+ //
	 * act.getPrintGatePassNo() + "-" + n+ ".pdf");
	 * JasperExportManager.exportReportToPdfFile(print, relativePathpdf +
	 * File.separator + "FL2_FL2B"+act.getPrintGatePassNo() + n + "Draft.pdf");
	 * 
	 * dt.setDraftpdfname("FL2_FL2B"+act.getPrintGatePassNo() + n +
	 * "Draft.pdf"); act.setDraftpdfname("FL2_FL2B"+act.getPrintGatePassNo() + n
	 * + "Draft.pdf"); dt.setPrintDraftFlagDt(true); printDraftFlag = true;
	 * 
	 * 
	 * dt.setPdfDraft("GatePassToRetailerFL2_FL2BDraft1"+ n + ".pdf");
	 * act.setPdfDraft("GatePassToRetailerFL2_FL2BDraft1"+ n + ".pdf");
	 * dt.setPrintDraftFlagDt(true); printFlag = true;
	 * 
	 * 
	 * } else { FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage("No Data Found", "No Data Found"));
	 * 
	 * printDraftFlag = false;
	 * 
	 * dt.setPrintDraftFlagDt(false); } } catch (JRException e) {
	 * e.printStackTrace(); } catch (Exception e) { e.printStackTrace(); }
	 * finally { try { if (rs != null) rs.close(); if (con != null) con.close();
	 * } catch (SQLException e) { e.printStackTrace(); } } return
	 * printDraftFlag;
	 * 
	 * }
	 */
	/*
	 * public void saveExcelData(GatepassToDistrict_FL2_FL2B_Action action) {
	 * String gatepass = ""; int status = 0, flag = 1, excelcase = 0; Connection
	 * conn = null; PreparedStatement pstmt = null; //
	 * System.out.println("filename and path"
	 * +action.getExcelFilename()+"---------patj"+action.getExcelFilepath());
	 * FileInputStream fileInputStream = null; XSSFWorkbook workbook = null; try
	 * {
	 * 
	 * String query = "DELETE FROM fl2d.scan_temp where gatepass ='"+
	 * action.getScanGatePassNo().trim()+ "' ";
	 * 
	 * conn = ConnectionToDataBase.getConnection(); pstmt =
	 * conn.prepareStatement(query); pstmt.executeUpdate(); pstmt=null;
	 * 
	 * String sql =
	 * "INSERT INTO fl2d.scan_temp(gatepass, casecode) VALUES (?,?)";
	 * 
	 * 
	 * 
	 * pstmt = conn.prepareStatement(sql); fileInputStream = new
	 * FileInputStream(action.getExcelFilepath());
	 * 
	 * workbook = new XSSFWorkbook(fileInputStream);
	 * 
	 * XSSFSheet worksheet = workbook.getSheet("Sheet1"); int i = 0, j = 0; do {
	 * 
	 * String casecode = ""; XSSFRow row1 = worksheet.getRow(j); XSSFRow row2 =
	 * worksheet.getRow(j + 1);
	 * 
	 * XSSFCell cellA1 = row1.getCell((short) 0); XSSFCell cellA2 =
	 * row2.getCell((short) 0);
	 * 
	 * String cellval = getCellValue(cellA1); String cellval2 =
	 * getCellValue(cellA2);
	 * 
	 * 
	 * 
	 * if ((cellval == null && cellval2 == null) || (cellval.length() == 0 &&
	 * cellval2.length() == 0) || (cellval.equals("0.0") &&
	 * cellval2.equals("0.0"))) {
	 * 
	 * break; } else {
	 * 
	 * if (j == 0) {
	 * 
	 * 
	 * 
	 * cellA1 = row1.getCell((short) 0); gatepass = getCellValue(cellA1);
	 * 
	 * if (action.getScanGatePassNo().equalsIgnoreCase( gatepass)) { cellA2 =
	 * row2.getCell((short) 0); casecode = getCellValue(cellA2);
	 * if(this.etin(casecode.trim().substring(2, 15), action)==true){
	 * FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage(FacesMessage.SEVERITY_ERROR, " Casecode-"+casecode.trim()+
	 * " does not belongs to the brands for the selected gatepass!"
	 * ," Casecode-"
	 * +casecode.trim()+" does not belongs to the brands for the selected gatepass!"
	 * )); }else{ pstmt.setString(1, gatepass.trim()); pstmt.setString(2,
	 * casecode.trim()); status = pstmt.executeUpdate(); excelcase++; i = 1; //}
	 * } else {
	 * 
	 * flag = 0; } } else {
	 * 
	 * 
	 * cellA2 = row2.getCell((short) 0);
	 * 
	 * casecode = getCellValue(cellA2);
	 * 
	 * 
	 * if(this.etin(casecode.trim().substring(2, 15), action)==true){
	 * FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage(FacesMessage.SEVERITY_ERROR, " Casecode-"+casecode.trim()+
	 * " does not belongs to the brands for the selected gatepass!"
	 * ," Casecode-"
	 * +casecode.trim()+" does not belongs to the brands for the selected gatepass!"
	 * )); }else{ pstmt.setString(1, gatepass.trim()); pstmt.setString(2,
	 * casecode.trim());
	 * 
	 * status = pstmt.executeUpdate(); excelcase++;
	 * action.setExcelCases(excelcase); i = 1; } //} }
	 * 
	 * j++; } while (i == 1);
	 * 
	 * } catch (Exception e) { FacesContext.getCurrentInstance().addMessage(
	 * null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e .getMessage(),
	 * e.getMessage())); }
	 * 
	 * finally { try { if (pstmt != null) pstmt.close(); if (conn != null)
	 * conn.close(); } catch (Exception e) {
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage(FacesMessage.SEVERITY_ERROR, e .getMessage(),
	 * e.getMessage())); } } if (flag == 1) { if (status > 0) {
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage(FacesMessage.SEVERITY_ERROR, "Upload successfully!!",
	 * "Upload successfully!!"));
	 * 
	 * } else {
	 * 
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage(FacesMessage.SEVERITY_ERROR, "Uploading Failed!!",
	 * "Uploading Failed!!")); } } else { // action.setKidnlyUploadFlag(true);
	 * // action.setGatePassFlag(true);
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage(FacesMessage.SEVERITY_ERROR,
	 * "Kindly enter the same gatepass number!!",
	 * "Kindly enter the same gatepass number!!")); }
	 * 
	 * }
	 */
	// ----------------------------------excel
	// data------------------------------------------------------
	public void saveExcelData(GatepassToDistrict_FL2_FL2B_Action action) {
		String gatepass = "";
		int status = 0, flag = 1, excelcase = 0;
		int k[] = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		// System.out.println("filename and path"+action.getExcelFilename()+"---------patj"+action.getExcelFilepath());
		FileInputStream fileInputStream = null;
		XSSFWorkbook workbook = null;
		try {

			String query = "DELETE FROM bottling_unmapped.scan_temp where gatepass ='"
					+ action.getScanGatePassNo().trim() + "' ";

			conn = ConnectionToDataBase.getConnection19_20();
			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			pstmt = null;

			String sql = "INSERT INTO bottling_unmapped.scan_temp(gatepass, casecode) VALUES (?,?)";

			pstmt1 = conn.prepareStatement(sql);

			fileInputStream = new FileInputStream(action.getExcelFilepath());

			workbook = new XSSFWorkbook(fileInputStream);

			XSSFSheet worksheet = workbook.getSheet("Sheet1");
			int i = 0, j = 0;
			do {

				String casecode = "";
				XSSFRow row1 = worksheet.getRow(j);
				// XSSFRow row2 = worksheet.getRow(j + 1);

				XSSFCell cellA1 = row1.getCell((short) 0);
				// XSSFCell cellA2 = row2.getCell((short) 0);

				String cellval = getCellValue(cellA1);
				// String cellval2 = getCellValue(cellA2);

				if ((cellval == null) || (cellval.length() == 0)
						|| (cellval.equals("0.0"))) {

					break;
				}
				{

					if (j == 0) {

						cellA1 = row1.getCell((short) 0);
						gatepass = getCellValue(cellA1);

						if (action.getScanGatePassNo().equalsIgnoreCase(
								gatepass)) {
							/*
							 * cellA2 = row2.getCell((short) 0); casecode =
							 * getCellValue(cellA2);
							 * if(this.etin(casecode.trim().substring(2, 15),
							 * action)==true){
							 * FacesContext.getCurrentInstance().
							 * addMessage(null, new
							 * FacesMessage(FacesMessage.SEVERITY_ERROR,
							 * " Casecode-"+casecode.trim()+
							 * " does not belongs to the brands for the selected gatepass!"
							 * ," Casecode-"+casecode.trim()+
							 * " does not belongs to the brands for the selected gatepass!"
							 * )); }else{
							 * 
							 * pstmt1.setString(1, gatepass.trim());
							 * pstmt1.setString(2, casecode.trim());
							 * pstmt1.addBatch(); excelcase++;
							 */
							i = 1;

							// }
						} else {

							flag = 0;
						}
					} else {

						cellA1 = row1.getCell((short) 0);

						casecode = getCellValue(cellA1);

						/*
						 * if(this.etin(casecode.trim().substring(2, 15),
						 * action)==true){
						 * FacesContext.getCurrentInstance().addMessage(null,
						 * new FacesMessage(FacesMessage.SEVERITY_ERROR,
						 * " Casecode-"+casecode.trim()+
						 * " does not belongs to the brands for the selected gatepass!"
						 * ," Casecode-"+casecode.trim()+
						 * " does not belongs to the brands for the selected gatepass!"
						 * )); }else{
						 */

						pstmt1.setString(1, gatepass.trim());
						pstmt1.setString(2, casecode.trim());
						pstmt1.addBatch();
						// status = pstmt.executeUpdate();
						excelcase++;
						action.setExcelCases(excelcase);
						i = 1;
						// }
					}
				}

				j++;

			} while (i == 1);

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), e.getMessage()));
		}

		if (flag == 1) {
			try {
				k = pstmt1.executeBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, e
								.getMessage(), e.getMessage()));
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (pstmt1 != null)
						pstmt1.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, e
									.getMessage(), e.getMessage()));
				}
			}

			if (k != null && k.length > 0) {

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Upload successfully!!",
								"Upload successfully!!"));

			} else {

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Uploading Failed!!", "Uploading Failed!!"));
			}
		} else {
			// action.setKidnlyUploadFlag(true);
			// action.setGatePassFlag(true);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Kindly enter the same gatepass number!!",
							"Kindly enter the same gatepass number!!"));
		}

	}

	public void saveExcelDatafl(GatepassToDistrict_FL2_FL2B_Action action) {
		String gatepass = "";
		int status = 0, flag = 1, excelcase = 0;
		int k[] = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		// System.out.println("filename and path"+action.getExcelFilename()+"---------patj"+action.getExcelFilepath());
		FileInputStream fileInputStream = null;
		XSSFWorkbook workbook = null;
		try {

			String query = "DELETE FROM bottling_unmapped.scan_temp where gatepass ='"
					+ action.getScanGatePassNo().trim() + "' ";

			conn = ConnectionToDataBase.getConnection19_20();
			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			pstmt = null;

			String sql = "INSERT INTO bottling_unmapped.scan_temp(gatepass, casecode) VALUES (?,?)";

			pstmt1 = conn.prepareStatement(sql);

			fileInputStream = new FileInputStream(action.getExcelFilepath());

			workbook = new XSSFWorkbook(fileInputStream);

			XSSFSheet worksheet = workbook.getSheet("Sheet1");
			int i = 0, j = 0;
			do {

				String casecode = "";
				XSSFRow row1 = worksheet.getRow(j);
				// XSSFRow row2 = worksheet.getRow(j + 1);

				XSSFCell cellA1 = row1.getCell((short) 0);
				// XSSFCell cellA2 = row2.getCell((short) 0);

				String cellval = getCellValue(cellA1);
				// String cellval2 = getCellValue(cellA2);

				if ((cellval == null) || (cellval.length() == 0)
						|| (cellval.equals("0.0"))) {

					break;
				}
				{

					if (j == 0) {

						cellA1 = row1.getCell((short) 0);
						gatepass = getCellValue(cellA1);

						if (action.getScanGatePassNo().equalsIgnoreCase(
								gatepass)) {
							/*
							 * cellA2 = row2.getCell((short) 0); casecode =
							 * getCellValue(cellA2);
							 * if(this.etin(casecode.trim().substring(2, 15),
							 * action)==true){
							 * FacesContext.getCurrentInstance().
							 * addMessage(null, new
							 * FacesMessage(FacesMessage.SEVERITY_ERROR,
							 * " Casecode-"+casecode.trim()+
							 * " does not belongs to the brands for the selected gatepass!"
							 * ," Casecode-"+casecode.trim()+
							 * " does not belongs to the brands for the selected gatepass!"
							 * )); }else{
							 * 
							 * pstmt1.setString(1, gatepass.trim());
							 * pstmt1.setString(2, casecode.trim());
							 * pstmt1.addBatch(); excelcase++;
							 */
							i = 1;

							// }
						} else {

							flag = 0;
						}
					} else {

						cellA1 = row1.getCell((short) 0);

						casecode = getCellValue(cellA1);

						if (this.etin(casecode.trim().substring(0, 12), action) == true) {
							FacesContext
							.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_ERROR,
											" Casecode-"
													+ casecode.trim()
													+ " does not belongs to the brands for the selected gatepass!",
													" Casecode-"
															+ casecode.trim()
															+ " does not belongs to the brands for the selected gatepass!"));
						} else {

							pstmt1.setString(1, gatepass.trim());
							pstmt1.setString(2, casecode.trim());
							pstmt1.addBatch();
							// status = pstmt.executeUpdate();
							excelcase++;
							action.setExcelCases(excelcase);
							i = 1;
						}
					}
				}

				j++;

			} while (i == 1);

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), e.getMessage()));
		}

		if (flag == 1) {
			try {
				k = pstmt1.executeBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, e
								.getMessage(), e.getMessage()));
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (pstmt1 != null)
						pstmt1.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, e
									.getMessage(), e.getMessage()));
				}
			}

			if (k != null && k.length > 0) {

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Upload successfully!!",
								"Upload successfully!!"));

			} else {

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Uploading Failed!!", "Uploading Failed!!"));
			}
		} else {
			// action.setKidnlyUploadFlag(true);
			// action.setGatePassFlag(true);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Kindly enter the same gatepass number!!",
							"Kindly enter the same gatepass number!!"));
		}

	}

	public ArrayList getExcelData( String gt) {
		ArrayList list = new ArrayList();

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String query = "SELECT date_plan,ws_gatepass,casecode FROM bottling_unmapped.disliry_unmap_cl where ws_gatepass='"+ gt+ "'";

		try {
			con = ConnectionToDataBase.getConnection19_20();
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				GatepassToDistrict_FL2_FL2B_DT dt = new GatepassToDistrict_FL2_FL2B_DT();


				dt.setCasecode(rs.getString("casecode"));
				dt.setCasecodedt(rs.getDate("date_plan"));
				list.add(dt);

			}
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ex
							.getMessage(), ex.getMessage()));
		}

		finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception ex) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, ex
								.getMessage(), ex.getMessage()));
			}
		}

		return list;
	}
	public ArrayList getExcelDatafl2( String gt) {
		ArrayList list = new ArrayList();

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String query = "SELECT date_plan,casecode FROM bottling_unmapped.disliry_unmap_fl3 where ws_gatepass='"+ gt+ "'" +
				" union " +
				" SELECT date_plan,casecode FROM bottling_unmapped.disliry_unmap_fl3a where ws_gatepass='"+ gt+ "' "+
				" union " +
				" SELECT date_plan,casecode FROM bottling_unmapped.bwfl where ws_gatepass='"+ gt+ "' "+
				" union " +
				" SELECT date_plan,casecode FROM bottling_unmapped.fl2d where ws_gatepass='"+ gt+ "' ";

		try {
			con = ConnectionToDataBase.getConnection19_20();
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				GatepassToDistrict_FL2_FL2B_DT dt = new GatepassToDistrict_FL2_FL2B_DT();


				dt.setCasecode(rs.getString(2));
				dt.setCasecodedt(rs.getDate(1));
				list.add(dt);

			}
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ex
							.getMessage(), ex.getMessage()));
		}

		finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception ex) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, ex
								.getMessage(), ex.getMessage()));
			}
		}

		return list;
	}
	public ArrayList getExcelDatafl2b( String gt) {
		ArrayList list = new ArrayList();

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String query = "SELECT date_plan,casecode FROM bottling_unmapped.brewary_unmap_fl3 where ws_gatepass='"+ gt+ "'" +
				" union " +
				" SELECT date_plan,casecode FROM bottling_unmapped.brewary_unmap_fl3a where ws_gatepass='"+ gt+ "' "+
				" union " +
				" SELECT date_plan,casecode FROM bottling_unmapped.bwfl where ws_gatepass='"+ gt+ "' "+
				" union " +
				" SELECT date_plan,casecode FROM bottling_unmapped.fl2d where ws_gatepass='"+ gt+ "' "+
				" union " +
				" SELECT date_plan,casecode FROM bottling_unmapped.disliry_unmap_fl3a where ws_gatepass='"+ gt+ "' "+
				" union " +
				" SELECT date_plan,casecode FROM bottling_unmapped.disliry_unmap_fl3 where ws_gatepass='"+ gt+ "' ";

		try {
			con = ConnectionToDataBase.getConnection19_20();
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				GatepassToDistrict_FL2_FL2B_DT dt = new GatepassToDistrict_FL2_FL2B_DT();


				dt.setCasecode(rs.getString(2));
				dt.setCasecodedt(rs.getDate(1));
				list.add(dt);

			}
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ex
							.getMessage(), ex.getMessage()));
		}

		finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception ex) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, ex
								.getMessage(), ex.getMessage()));
			}
		}

		return list;
	}
	private String getCellValue(XSSFCell cell) {
		try {

			switch (cell.getCellType()) {
			case XSSFCell.CELL_TYPE_STRING:

				return cell.getStringCellValue().toString();

			case XSSFCell.CELL_TYPE_BOOLEAN:
				return Boolean.toString(cell.getBooleanCellValue());

			case XSSFCell.CELL_TYPE_NUMERIC:
				String val = Double.toString(cell.getNumericCellValue());
				val = val.substring(0, val.lastIndexOf("."));

				return val;

			case XSSFCell.CELL_TYPE_BLANK:

				return null;

			}

			return null;
		} catch (Exception e) {
			return null;
		}

	}

	// -----------------------------------------------delete
	// data---------------------------------------------------------

	public void deleteData(GatepassToDistrict_FL2_FL2B_Action action) {
		Connection con = null;
		PreparedStatement stmt = null;

		String query = "";String query1 = "";String query2 = "";String query3 = "";String query4 = "";String query5 = "";

		if (action.getFl2LicenseType().equalsIgnoreCase("FL2B")) {

			action.tempList.clear();
			query = "  update bottling_unmapped.brewary_unmap_fl3 set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
					" where  ws_gatepass='"+ action.getScanGatePassNo().toUpperCase().trim() + "' ";
			query1 = "  update bottling_unmapped.brewary_unmap_fl3a set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
					" where  ws_gatepass='"+ action.getScanGatePassNo().toUpperCase().trim() + "' ";
			query2= "  update bottling_unmapped.bwfl set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
					" where  ws_gatepass='"+ action.getScanGatePassNo().toUpperCase().trim() + "' ";
			query3 = "  update bottling_unmapped.fl2d set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
					" where  ws_gatepass='"+ action.getScanGatePassNo().toUpperCase().trim() + "' ";
			query4 = "  update bottling_unmapped.disliry_unmap_fl3 set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
					" where  ws_gatepass='"+ action.getScanGatePassNo().toUpperCase().trim() + "' ";
			query5 = "  update bottling_unmapped.disliry_unmap_fl3a set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
					" where  ws_gatepass='"+ action.getScanGatePassNo().toUpperCase().trim() + "' ";
		}

		else if (action.getFl2LicenseType().equalsIgnoreCase("FL2")) {

			action.tempList.clear();
			query = "  update bottling_unmapped.disliry_unmap_fl3 set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
					" where  ws_gatepass='"+ action.getScanGatePassNo().toUpperCase().trim() + "' ";
			query1 = "  update bottling_unmapped.disliry_unmap_fl3a set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
					" where  ws_gatepass='"+ action.getScanGatePassNo().toUpperCase().trim() + "' ";
			query2= "  update bottling_unmapped.bwfl set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
					" where  ws_gatepass='"+ action.getScanGatePassNo().toUpperCase().trim() + "' ";
			query3 = "  update bottling_unmapped.fl2d set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
					" where  ws_gatepass='"+ action.getScanGatePassNo().toUpperCase().trim() + "' ";
		}

		else if (action.getFl2LicenseType().equalsIgnoreCase("CL2"))

		{
			action.tempList.clear();
			query = "  update bottling_unmapped.disliry_unmap_cl set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
					" where  ws_gatepass='"+ action.getScanGatePassNo().toUpperCase().trim() + "' ";


		}

		int status = 0;
		try {
			con = ConnectionToDataBase.getConnection19_20();
			 if (action.getFl2LicenseType().equalsIgnoreCase("CL2")) {
			
			stmt = con.prepareStatement(query);
			status = stmt.executeUpdate();
			
			 }
			 else if (action.getFl2LicenseType().equalsIgnoreCase("FL2B")) {
				 stmt = con.prepareStatement(query);
					status += stmt.executeUpdate();
					stmt = con.prepareStatement(query1);
					status += stmt.executeUpdate();
					stmt = con.prepareStatement(query2);
					status += stmt.executeUpdate();
					stmt = con.prepareStatement(query3);
					status += stmt.executeUpdate(); 
					stmt = con.prepareStatement(query4);
					status += stmt.executeUpdate();
					stmt = con.prepareStatement(query5);
					status += stmt.executeUpdate(); 
			 }
			 else{
				 stmt = con.prepareStatement(query);
					status += stmt.executeUpdate();
					stmt = con.prepareStatement(query1);
					status += stmt.executeUpdate();
					stmt = con.prepareStatement(query2);
					status += stmt.executeUpdate();
					stmt = con.prepareStatement(query3);
					status += stmt.executeUpdate();
			 }
			if (status > 0) {

				FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Deleted Successfully",
								"Deleted Successfully"));

			} else {

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"No Old Data Found !", "No Old Data Found !"));

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

	// ----------------------recieve casees---------------------------

	public int recieveCases(GatepassToDistrict_FL2_FL2B_Action act) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			String query = " SELECT SUM(dispatch_box) as dispatchd_box FROM fl2d.fl2_stock_trxn_fl2_fl2b_19_20 "
					+ " WHERE vch_gatepass_no='"
					+ act.getScanGatePassNo()
					+ "'";
			pstmt = con.prepareStatement(query);

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
		// ------------------Excel cases--------------------
	}

	public int excelCases(GatepassToDistrict_FL2_FL2B_Action act) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";
		String query = "";
		try {
			con = ConnectionToDataBase.getConnection19_20();

			if (act.getFl2LicenseType().equalsIgnoreCase("FL2B")) {
				 query = " select sum(x.c) as dispatchd_box  from (" +
						 " SELECT count(*) as c FROM bottling_unmapped.disliry_unmap_fl3 where ws_gatepass='"+ act.getScanGatePassNo().toUpperCase().trim()+ "'" +
							" union all " +
							" SELECT count(*) as c FROM bottling_unmapped.disliry_unmap_fl3a where ws_gatepass='"+ act.getScanGatePassNo().toUpperCase().trim()+ "' "+
							" union all " +
				 		"SELECT count(*) as c FROM bottling_unmapped.brewary_unmap_fl3 where ws_gatepass='"+ act.getScanGatePassNo().toUpperCase().trim()+ "'" +
							" union all " +
							" SELECT count(*) as c FROM bottling_unmapped.brewary_unmap_fl3a where ws_gatepass='"+ act.getScanGatePassNo().toUpperCase().trim()+ "' "+
							" union all " +
							" SELECT count(*) as c FROM bottling_unmapped.bwfl where ws_gatepass='"+ act.getScanGatePassNo().toUpperCase().trim()+ "' "+
							" union all " +
							" SELECT count(*) as c FROM bottling_unmapped.fl2d where ws_gatepass='"+ act.getScanGatePassNo().toUpperCase().trim()+ "')x ";
					 

			}

			else if (act.getFl2LicenseType().equalsIgnoreCase("FL2")) {
				  query = " select sum(x.c) as dispatchd_box  from (" +
				  		" SELECT count(*) as c FROM bottling_unmapped.disliry_unmap_fl3 where ws_gatepass='"+ act.getScanGatePassNo().toUpperCase().trim()+ "'" +
						" union all " +
						" SELECT count(*) as c FROM bottling_unmapped.disliry_unmap_fl3a where ws_gatepass='"+ act.getScanGatePassNo().toUpperCase().trim()+ "' "+
						" union all " +
						" SELECT count(*) as c FROM bottling_unmapped.bwfl where ws_gatepass='"+ act.getScanGatePassNo().toUpperCase().trim()+ "' "+
						" union all " +
						" SELECT count(*) as c FROM bottling_unmapped.fl2d where ws_gatepass='"+ act.getScanGatePassNo().toUpperCase().trim()+ "')x ";
				 
			}

			else if (act.getFl2LicenseType().equalsIgnoreCase("CL2")) {
				query = " SELECT count(*) as dispatchd_box FROM bottling_unmapped.disliry_unmap_cl"
						+ " WHERE ws_gatepass='"+ act.getScanGatePassNo().toUpperCase().trim()+ "' ";
			} else {
				query = "";
			}

			pstmt = con.prepareStatement(query);

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
	public boolean labcheck(String casecode) {

		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		boolean s = false;
		try {
			con = ConnectionToDataBase.getConnection();

			String query = " SELECT distinct a.license_category FROM distillery.brand_registration_19_20 a, distillery.packaging_details_19_20 b "
					+ " WHERE b.brand_id_fk = a.brand_id   and b.code_generate_through='" + casecode + "'";

			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				if(rs.getString("license_category").equalsIgnoreCase("LAB")){
					s = true;	
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
		return s;

	}
	public boolean etin(String casecode, GatepassToDistrict_FL2_FL2B_Action act) {

		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		boolean s = false;
		try {
			con = ConnectionToDataBase.getConnection();

			String query = " SELECT distinct b.code_generate_through FROM fl2d.fl2_stock_trxn_fl2_fl2b_19_20 a, distillery.packaging_details_19_20 b "
					+ " WHERE b.package_id = a.int_pckg_id  and a.vch_gatepass_no='"
					+ act.getScanGatePassNo().trim()
					+ "' and b.code_generate_through='" + casecode + "'";

			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();
			if (rs.next()) {

			} else {

				s = true;
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

	// ----------------------------update
	public boolean updateFL3(GatepassToDistrict_FL2_FL2B_Action act) {
		int save = 0;
		boolean val = false;
		PreparedStatement ps = null;
		Connection con = null;
		String updtQr = " UPDATE fl2d.gatepass_to_districtwholesale_fl2_fl2b_19_20 SET  vch_finalize='F', finalize_dt_time=? "
				+ " WHERE vch_gatepass_no='" + act.getScanGatePassNo() + "'";
		int j = 0;

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(updtQr);
			ps.setString(1, dateFormat.format(new Date()));

			save = ps.executeUpdate();

			if (save > 0) {
				val = true;
				con.commit();
			} else {
				val = false;
				con.rollback();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ex
							.getMessage(), ex.getMessage()));
		} finally {
			try {
				if (ps != null)
					ps.close();

				if (con != null)
					con.close();
			} catch (Exception ex) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, ex
								.getMessage(), ex.getMessage()));
			}
		}

		return val;
	}

	// Fl3-------------------------------------------
	/*
	 * public boolean updateFL3(GatepassToDistrict_FL2_FL2B_Action act) { int
	 * save = 0; boolean val = false; PreparedStatement ps = null; Connection
	 * con = null; Connection con1 = null; String query = ""; int j= 0;
	 * /*if(!etin_check(act.getScanGatePassNo())){
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage(FacesMessage.SEVERITY_ERROR,
	 * "Number of SKUs in gatepass are not equal to the number of SKUs uploaded."
	 * ,
	 * "Number of SKUs in gatepass are not equal to the number of SKUs uploaded."
	 * )); return false; }
	 */
	/*
	 * if(act.getFl2LicenseType().equalsIgnoreCase("FL2B")) { query =
	 * " INSERT INTO bottling_unmapped.fl2_2b_cl2_unmap  (fl36gatepass , date_plan, etin, "
	 * +
	 * " fl36_date,casecode,flid,type) select '"+act.getScanGatePassNo().toUpperCase
	 * ().trim()+"','"+Utility.convertUtilDateToSQLDate(new Date())+"'," +
	 * " substring(casecode,1,12),'"+Utility.convertUtilDateToSQLDate(new
	 * Date())+"',substring(casecode,27)," +
	 * " '"+act.getFl2_fl2bId()+"','"+act.getFl2LicenseType()+"'" +
	 * " FROM bottling_unmapped.scan_temp where gatepass='"
	 * +act.getScanGatePassNo()+"'; ";
	 * 
	 * }
	 * 
	 * else if(act.getFl2LicenseType().equalsIgnoreCase("FL2")) { query =
	 * " INSERT INTO bottling_unmapped.fl2_unmap  (fl36gatepass , date_plan, etin, "
	 * +
	 * " fl36_date,casecode,flid,type) select '"+act.getScanGatePassNo().toUpperCase
	 * ().trim()+"','"+Utility.convertUtilDateToSQLDate(new Date())+"'," +
	 * " substring(casecode,1,12),'"+Utility.convertUtilDateToSQLDate(new
	 * Date())+"',substring(casecode,27)," +
	 * " '"+act.getFl2_fl2bId()+"','"+act.getFl2LicenseType()+"'" +
	 * " FROM bottling_unmapped.scan_temp where gatepass='"
	 * +act.getScanGatePassNo()+"'; "; }
	 * 
	 * else if(act.getFl2LicenseType().equalsIgnoreCase("CL2")) { query1 =
	 * " update  bottling_unmapped.cl2_unmap a set fl36gatepass ='"
	 * +act.getScanGatePassNo().toUpperCase().trim()+"' from (" +
	 * "  select * FROM bottling_unmapped.scan_temp where gatepass='"
	 * +act.getScanGatePassNo()+"' ) b where " +
	 * "  a.date_plan=to_date(concat('20', substring(b.casecode,17,6)),'YYYYMMDD') and  "
	 * +
	 * " a.casecode=substring(b.casecode,27) and  a.flid='"+act.getFl2_fl2bId()+
	 * "' and a.etin=substring(b.casecode,1,12) and to_date(concat('20', substring(b.casecode,17,6)),'YYYYMMDD')>='2019-04-19'"
	 * ;
	 * 
	 * } if(act.getFl2LicenseType().equalsIgnoreCase("FL2B")) { query =
	 * " INSERT INTO bottling_unmapped.fl2_2b_cl2_unmap  (fl36gatepass , date_plan, etin, "
	 * +
	 * " fl36_date,casecode,flid,type) select '"+act.getScanGatePassNo().toUpperCase
	 * ().trim()+"',to_date(concat('20', substring(casecode,17,6)),'YYYYMMDD'),"
	 * + " substring(casecode,1,12),'"+Utility.convertUtilDateToSQLDate(new
	 * Date())+"',substring(casecode,27)," +
	 * " '"+act.getFl2_fl2bId()+"','"+act.getFl2LicenseType()+"'" +
	 * " FROM bottling_unmapped.scan_temp where gatepass='"
	 * +act.getScanGatePassNo()+"'; ";
	 * 
	 * }
	 * 
	 * else if(act.getFl2LicenseType().equalsIgnoreCase("FL2")) { query =
	 * " INSERT INTO bottling_unmapped.fl2_unmap  (fl36gatepass , date_plan, etin, "
	 * +
	 * " fl36_date,casecode,flid,type) select '"+act.getScanGatePassNo().toUpperCase
	 * ().trim()+"',to_date(concat('20', substring(casecode,17,6)),'YYYYMMDD'),"
	 * + " substring(casecode,1,12),'"+Utility.convertUtilDateToSQLDate(new
	 * Date())+"',substring(casecode,27)," +
	 * " '"+act.getFl2_fl2bId()+"','"+act.getFl2LicenseType()+"'" +
	 * " FROM bottling_unmapped.scan_temp where gatepass='"
	 * +act.getScanGatePassNo()+"'; "; }
	 * 
	 * else if(act.getFl2LicenseType().equalsIgnoreCase("CL2")) { query =
	 * " INSERT INTO bottling_unmapped.cl2_unmap  (fl36gatepass , date_plan, etin, "
	 * +
	 * " fl36_date,casecode,flid,type) select '"+act.getScanGatePassNo().toUpperCase
	 * ().trim()+"',to_date(concat('20', substring(casecode,17,6)),'YYYYMMDD'),"
	 * + " substring(casecode,1,12),'"+Utility.convertUtilDateToSQLDate(new
	 * Date())+"',substring(casecode,27)," +
	 * " '"+act.getFl2_fl2bId()+"','"+act.getFl2LicenseType()+"'" +
	 * " FROM bottling_unmapped.scan_temp where gatepass='"
	 * +act.getScanGatePassNo()+"'; "; } else{ query = ""; }
	 * 
	 * try { DateFormat dateFormat = new
	 * SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	 * 
	 * con = ConnectionToDataBase.getConnection(); con1 =
	 * ConnectionToDataBase.getConnection19_20(); con.setAutoCommit(false);
	 * con1.setAutoCommit(false); ps = con1.prepareStatement(query); /* for (int
	 * i = 0; i < act.getGetVal().size(); i++) {
	 * 
	 * GatepassToDistrict_FL2_FL2B_DT dt = (GatepassToDistrict_FL2_FL2B_DT)
	 * act.getGetVal().get(i);
	 * 
	 * ps.setString(1, dt.getGatepass().toUpperCase().trim()); ps.setDate(2,
	 * Utility.convertUtilDateToSQLDate(new Date())); ps.setString(3,
	 * dt.getCasecode().substring(2, 15)); ps.setDate(4,
	 * Utility.convertUtilDateToSQLDate(new Date()));
	 * ps.setString(5,dt.getCasecode().substring(29,dt.getCasecode().length()));
	 * ps.setInt(6, act.getFl2_fl2bId()); ps.setString(7,
	 * act.getFl2LicenseType()); j = ps.executeUpdate(); if (j == 0) {
	 * FacesContext.getCurrentInstance().addMessage( null, new FacesMessage(
	 * " Casecode not inserted! ", " Casecode not inserted! ")); //
	 * System.out.println
	 * ("dt.getCasecode().substring(29,dt.getCasecode().length())==="
	 * +dt.getCasecode().substring(29,dt.getCasecode().length())); } // save +=
	 * j; // }
	 * 
	 * 
	 * if (j > 0) { save = 0; String updtQr =
	 * " UPDATE fl2d.gatepass_to_districtwholesale_fl2_fl2b_19_20 SET  vch_finalize='F', finalize_dt_time=? "
	 * + " WHERE vch_gatepass_no='"+ act.getScanGatePassNo() + "'";
	 * 
	 * ps = con.prepareStatement(updtQr); ps.setString(1, dateFormat.format(new
	 * Date()));
	 * 
	 * save = ps.executeUpdate();
	 * 
	 * 
	 * 
	 * query = "DELETE FROM  bottling_unmapped.scan_temp where gatepass  ='" +
	 * act.getScanGatePassNo().trim() + "' ";
	 * 
	 * ps = con1.prepareStatement(query); ps.executeUpdate(); } else { save = 0;
	 * } if (save > 0) { val = true; con.commit(); con1.commit(); } else { val =
	 * false; con.rollback(); con1.rollback(); }
	 * 
	 * } catch (Exception ex) { ex.printStackTrace();
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage(FacesMessage.SEVERITY_ERROR, ex .getMessage(),
	 * ex.getMessage())); } finally { try { if (ps != null) ps.close();
	 * 
	 * if (con != null) con.close(); con1.close(); } catch (Exception ex) {
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage(FacesMessage.SEVERITY_ERROR, ex .getMessage(),
	 * ex.getMessage())); } } return val;
	 * 
	 * }
	 */
	public boolean etin_check(String g) {

		Connection c1 = null;
		Connection c2 = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Map<String, String> m1 = new HashMap<String, String>();
		Map<String, String> m2 = new HashMap<String, String>();

		String q1 = "select count(a.etin), a.etin from"
				+ " (select substring(casecode,1,12) as etin from bottling_unmapped.scan_temp"
				+ " where gatepass='" + g
				+ "')a group by a.etin order by a.etin;";

		String q2 = "select b.f*count(b.code_generate_through) as count, b.code_generate_through as etin from"
				+ " (select a.f, (select code_generate_through from distillery.packaging_details_19_20 where package_id=a.p) from"
				+ " (select dispatch_box as f,int_pckg_id as p  from fl2d.fl2_stock_trxn_fl2_fl2b_19_20"
				+ " where vch_gatepass_no = '"
				+ g
				+ "') a)b group by b.code_generate_through, b.f order by etin";

		// System.out.println("second datatable==============="+query);
		try {
			c1 = ConnectionToDataBase.getConnection19_20();
			c2 = ConnectionToDataBase.getConnection();

			ps = c1.prepareStatement(q1);
			rs = ps.executeQuery();
			while (rs.next()) {
				m1.put(rs.getString("etin"), rs.getString("count"));
				// System.out.println(rs.getString("etin")+","+
				// rs.getString("count"));

			}

			ps = c2.prepareStatement(q2);
			rs = ps.executeQuery();
			while (rs.next()) {
				m2.put(rs.getString("etin"), rs.getString("count"));
				// System.out.println(rs.getString("etin")+","+
				// rs.getString("count"));

			}

			if (m1.equals(m2)) {
				// System.out.println("Result: "+ m1.equals(m2));
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (c1 != null)
					c1.close();
				c2.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;

	}

	/*
	 * public boolean updateFL3(GatepassToDistrict_FL2_FL2B_Action act) { int
	 * save = 0; boolean val = false; PreparedStatement ps = null; Connection
	 * con = null; Connection con1 = null; String query = ""; int j = 0; query =
	 * " insert into bottling_unmapped.fl2_2b_cl2_unmap  (  fl36gatepass , date_plan,etin ,"
	 * + " fl36_date,casecode,flid,type) values (?,?,?,?,?,?,?) ";
	 * 
	 * try { con = ConnectionToDataBase.getConnection(); con1 =
	 * ConnectionToDataBase.getConnection19_20(); con.setAutoCommit(false);
	 * con1.setAutoCommit(false); for (int i = 0; i < act.getGetVal().size();
	 * i++) { GatepassToDistrict_FL2_FL2B_DT dt =
	 * (GatepassToDistrict_FL2_FL2B_DT) act .getGetVal().get(i);
	 * 
	 * ps = con1.prepareStatement(query);
	 * 
	 * ps.setString(1, dt.getGatepass().toUpperCase().trim()); ps.setDate(2,
	 * Utility.convertUtilDateToSQLDate(new Date())); ps.setString(3,
	 * dt.getCasecode().substring(2, 15)); ps.setDate(4,
	 * Utility.convertUtilDateToSQLDate(new Date())); ps.setString( 5,
	 * dt.getCasecode().substring(29, dt.getCasecode().length())); ps.setInt(6,
	 * act.getFl2_fl2bId()); ps.setString(7, act.getFl2LicenseType()); j =
	 * ps.executeUpdate(); if (j == 0) {
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage(dt.getCasecode().substring(29, dt.getCasecode().length()) +
	 * " Casecode not inserted! ", dt .getCasecode().substring(29,
	 * dt.getCasecode().length()) + " Casecode not inserted! ")); //
	 * System.out.println
	 * ("dt.getCasecode().substring(29,dt.getCasecode().length())==="
	 * +dt.getCasecode().substring(29,dt.getCasecode().length())); } save += j;
	 * } if (act.getGetVal().size() == save) { save = 0; String updtQr =
	 * "UPDATE fl2d.gatepass_to_districtwholesale_fl2_fl2b_19_20 SET  vch_finalize='F' WHERE vch_gatepass_no='"
	 * + act.getScanGatePassNo() + "'";
	 * 
	 * ps = con.prepareStatement(updtQr); save = ps.executeUpdate(); query =
	 * "DELETE FROM   fl2d.scan_temp where gatepass  ='" +
	 * act.getScanGatePassNo().trim() + "' "; ps = con.prepareStatement(query);
	 * ps.executeUpdate(); } else { save = 0; } if (save > 0) { val = true;
	 * con.commit(); con1.commit(); } else { val = false; con.rollback();
	 * con1.rollback(); }
	 * 
	 * } catch (Exception ex) { FacesContext.getCurrentInstance().addMessage(
	 * null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex .getMessage(),
	 * ex.getMessage())); } finally { try { if (ps != null) ps.close();
	 * 
	 * if (con != null) con.close(); con1.close(); } catch (Exception ex) {
	 * ex.printStackTrace(); } } return val;
	 * 
	 * }
	 */

	// ----------------------------code for cancel
	// gatepass------------------------------

	// =====================get max id sequence==============================

	public int seqCancel() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(seq) as id FROM fl2d.duty_cancellation_fl2_fl2b_19_20 ";
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

	// -------------------------cancel
	// gatepass----------------------------------

	@SuppressWarnings("resource")
	public String cancelGatepassImpl(GatepassToDistrict_FL2_FL2B_Action act) {

		Connection con = null;
		Connection con1 = null;
		PreparedStatement ps = null, ps2 = null, ps3 = null, ps4 = null, ps5 = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		ResultSet rs1 = null;
		String sql = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "", sql41 = "", sql42 = "", sql43 = "",sql44 = "",sql45 = "";
		int tok1 = 0;
		int tok2 = 0;

		int seq = this.seqCancel();

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			con = ConnectionToDataBase.getConnection();
			con1 = ConnectionToDataBase.getConnection19_20();
			con.setAutoCommit(false);
			con1.setAutoCommit(false);

			String gatepass = "";
			gatepass = act.getPrintGatePassNo().trim();

			String selQr = " SELECT a.int_fl2_fl2b_id, a.dt_date, a.vch_gatepass_no, "
					+ " b.seq_fl2_fl2b, b.breakage, b.int_brand_id, b.int_pckg_id, b.dispatch_bottle  "
					+ " FROM fl2d.gatepass_to_districtwholesale_fl2_fl2b_19_20 a, fl2d.fl2_stock_trxn_fl2_fl2b_19_20 b   "
					+ " WHERE a.int_fl2_fl2b_id=b.int_fl2_fl2b_id AND a.vch_gatepass_no=b.vch_gatepass_no  "
					+ " AND a.dt_date=b.dt AND a.int_fl2_fl2b_id='"
					+ act.getFl2_fl2bId()
					+ "' AND  "
					+ " a.vch_gatepass_no='"
					+ gatepass.trim() + "'  ";

			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				GatepassToDistrict_FL2_FL2B_DT dt = new GatepassToDistrict_FL2_FL2B_DT();

				dt.setSeqDt(rs.getInt("seq_fl2_fl2b"));
				dt.setBrandIdDt(rs.getInt("int_brand_id"));
				dt.setPckgIdDt(rs.getInt("int_pckg_id"));
				dt.setDispatcBotlsDt(rs.getInt("dispatch_bottle"));
				dt.setBreakage(rs.getInt("breakage"));
				dt.setFl2_2bIdDt(rs.getInt("int_fl2_fl2b_id"));

				String updtQr = " UPDATE fl2d.fl2_2b_stock_19_20 SET "
						+ " dispatchbotl=COALESCE(dispatchbotl,0)-"
						+ (dt.getDispatcBotlsDt() ) + " "//+ dt.getBreakage()
						+ " WHERE id='" + dt.getFl2_2bIdDt() + "' AND  "
						+ " brand_id='" + dt.getBrandIdDt() + "' AND  "
						+ " pckg_id='" + dt.getPckgIdDt() + "' ";

				ps5 = con.prepareStatement(updtQr);

				// System.out.println("updtQr---------------"+updtQr);

				tok1 = ps5.executeUpdate();

				// System.out.println("first satatus---------------"+tok1);

			}

			if (tok1 > 0) {
				tok1 = 0;

				sql = " INSERT INTO fl2d.duty_cancellation_fl2_fl2b_19_20( "
						+ " seq, vch_gatepass_no, gatepass_dt, duty_cancellation_dt_tm, db_duty_amount, vch_type, db_add_duty_amount) "
						+ " VALUES (?, ?, ?, ?, ?, ?, ?) ";

				ps = con.prepareStatement(sql);

				ps.setInt(1, seq);
				ps.setString(2, act.getPrintGatePassNo());
				ps.setDate(3,
						Utility.convertUtilDateToSQLDate(act.getPrintDate()));
				ps.setString(4, dateFormat.format(new Date()));
				ps.setDouble(5, 0.0);
				ps.setString(6, "FL36-FL2_FL2B");
				ps.setDouble(7, 0.0);

				tok1 = ps.executeUpdate();

				// System.out.println("second status----------" + tok1);

			}

			if (tok1 > 0) {
				tok1 = 0;

				sql2 = " DELETE FROM fl2d.gatepass_to_districtwholesale_fl2_fl2b_19_20 "
						+ " WHERE  vch_finalize is NULL and vch_gatepass_no='"
						+ gatepass
						+ "' AND int_fl2_fl2b_id='"
						+ act.getFl2_fl2bId() + "' ";

				ps2 = con.prepareStatement(sql2);

				tok1 = ps2.executeUpdate();

				// System.out.println("third status----------" + tok1);

			}

			if (tok1 > 0) {
				tok1 = 0;

				sql3 = " DELETE FROM fl2d.fl2_stock_trxn_fl2_fl2b_19_20 "
						+ " WHERE vch_gatepass_no='" + gatepass
						+ "' AND int_fl2_fl2b_id='" + act.getFl2_fl2bId()
						+ "' ";

				ps3 = con.prepareStatement(sql3);
				tok1 = ps3.executeUpdate();

				// System.out.println("fourth status----------" + tok1);

			}
			/*
			 * if (tok1 > 0) { tok1 = 0;
			 * 
			 * 
			 * rs=null;ps=null;
			 * 
			 * 
			 * tok1=this.getDetail(act,gatepass);
			 * 
			 * }
			 */
			if (tok1 > 0) {

				/*sql4 = " DELETE FROM bottling_unmapped.scan_temp WHERE gatepass='"
						+ gatepass + "' ";*/


				if (act.getFl2LicenseType().equalsIgnoreCase("FL2B")) {

					sql4 = "  update bottling_unmapped.brewary_unmap_fl3 set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
							" where  ws_gatepass='"+gatepass.toUpperCase().trim()+ "' ";
					sql41 = "  update bottling_unmapped.brewary_unmap_fl3a set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
							" where  ws_gatepass='"+gatepass.toUpperCase().trim()+ "' ";
					sql42= "  update bottling_unmapped.bwfl set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
							" where  ws_gatepass='"+gatepass.toUpperCase().trim()+ "' ";
					sql43 = "  update bottling_unmapped.fl2d set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
							" where  ws_gatepass='"+gatepass.toUpperCase().trim()+ "' ";
					sql44 = "  update bottling_unmapped.disliry_unmap_fl3 set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
							" where  ws_gatepass='"+gatepass.toUpperCase().trim()+ "' ";
					sql45 = "  update bottling_unmapped.disliry_unmap_fl3a set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
							" where  ws_gatepass='"+gatepass.toUpperCase().trim()+ "' ";
					

				}

				else if (act.getFl2LicenseType().equalsIgnoreCase("FL2")) {

				 
					sql4 = "  update bottling_unmapped.disliry_unmap_fl3 set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
							" where  ws_gatepass='"+gatepass.toUpperCase().trim()+ "' ";
					sql41 = "  update bottling_unmapped.disliry_unmap_fl3a set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
							" where  ws_gatepass='"+gatepass.toUpperCase().trim()+ "' ";
					sql42= "  update bottling_unmapped.bwfl set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
							" where  ws_gatepass='"+gatepass.toUpperCase().trim()+ "' ";
					sql43 = "  update bottling_unmapped.fl2d set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
							" where  ws_gatepass='"+gatepass.toUpperCase().trim()+ "' ";
				}

				else if (act.getFl2LicenseType().equalsIgnoreCase("CL2"))

				{
					sql4 = "  update bottling_unmapped.disliry_unmap_cl set ws_date=null,ws_gatepass=null,shop_id=null,shop_type =null" +
							" where ws_gatepass='"+gatepass.toUpperCase().trim()+"' ";


				}else{
					tok1=0;
				}

				if (act.getFl2LicenseType().equalsIgnoreCase("CL2")) {
					
					ps3 = con1.prepareStatement(sql4);
					ps3.executeUpdate();
					 }else if (act.getFl2LicenseType().equalsIgnoreCase("FL2")) {
						 ps3 = con1.prepareStatement(sql4);
							ps3.executeUpdate();
							ps3 = con1.prepareStatement(sql41);
							ps3.executeUpdate();
							ps3 = con1.prepareStatement(sql42);
							ps3.executeUpdate();
							ps3 = con1.prepareStatement(sql43);
							ps3.executeUpdate();
					 }else if (act.getFl2LicenseType().equalsIgnoreCase("FL2B")) {
						 ps3 = con1.prepareStatement(sql4);
							ps3.executeUpdate();
							ps3 = con1.prepareStatement(sql41);
							ps3.executeUpdate();
							ps3 = con1.prepareStatement(sql42);
							ps3.executeUpdate();
							ps3 = con1.prepareStatement(sql43);
							ps3.executeUpdate();
							ps3 = con1.prepareStatement(sql44);
							ps3.executeUpdate();
							ps3 = con1.prepareStatement(sql45);
							ps3.executeUpdate();
					 }

			}

			if (tok1 > 0) {
				con.commit();
				con1.commit();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Gatepass Cancelled Successfully !!! ",
								"Gatepass Cancelled Successfully !!!"));
				act.clearAll();
				act.setListFlagForPrint(true);
			}

			else {
				con.rollback();
				con1.rollback();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Gatepass Not Cancelled !!! ",
								"Gatepass Not Cancelled !!!"));

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));

		} finally {
			try {
				if (con != null)
					con.close();
				if (con1 != null)
					con1.close();
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

			}
		}

		return "";

	}

	// ===========================code for CSV============================

	public void saveCSV(GatepassToDistrict_FL2_FL2B_Action action)
			throws IOException {
		Connection con = null;
		PreparedStatement stmt = null;
		action.getVal.clear();
		String query2 = "";String query3 = "";String query1 = "";
		String query = "";String casecode = "0000";
		/* if (action.getFl2LicenseType().equalsIgnoreCase("FL2B")) {

			query = " INSERT INTO bottling_unmapped.fl2_2b_cl2_unmap (fl36gatepass , "
					+ " fl36_date,casecode,flid,type) values(?,?,?,?,?)";

			query2 =  " DELETE FROM bottling_unmapped.fl2_2b_cl2_unmap where fl36gatepass='"+action.getScanGatePassNo().toUpperCase().trim()+"' ";

		}

		 else if (action.getFl2LicenseType().equalsIgnoreCase("FL2")) {

			 
											query = "  update bottling_unmapped.disliry_unmap_fl3 set ws_date=?,ws_gatepass=?,shop_id=?,shop_type =?" +
													" where  ws_date IS NULL and etin=? and casecode=?    AND date_plan=?  ";
										 query2 = "  update bottling_unmapped.disliry_unmap_fl3a set ws_date=?,ws_gatepass=?,shop_id=?,shop_type =?" +
													" where  ws_date IS NULL and etin=? and casecode=?  AND date_plan=?  ";
										  query3 = "  update bottling_unmapped.bwfl set ws_date=?,ws_gatepass=?,shop_id=?,shop_type =?" +
													" where  ws_date IS NULL and etin=? and casecode=?    AND date_plan=?  ";
										 	query1 = "  update bottling_unmapped.fl2d set ws_date=?,ws_gatepass=?,shop_id=?,shop_type =?" +
													" where  ws_date IS NULL and etin=? and casecode=?   AND date_plan=?  ";//and recv_id='"+action.getFl2_fl2bId()+"'
										 

		} else*/

		 if (action.getFl2LicenseType().equalsIgnoreCase("CL2")) {

			query = "  update bottling_unmapped.disliry_unmap_cl set ws_date=?,ws_gatepass=?,shop_id=?,shop_type =?" +
					" where  ws_date IS NULL and etin=? and casecode=?  and recv_id='"+action.getFl2_fl2bId()+"' AND date_plan=?  ";//

		} else {
			query = "";
		}

		// ///////////////////////////////////////////////////////////////////////////

		String gatepass = "";
		int status = 0, flag = 0;


		// String line = "";
		try {
			BufferedReader bReader = new BufferedReader(new FileReader(
					action.getCsvFilepath()));
			con = ConnectionToDataBase.getConnection19_20();
			con.setAutoCommit(false);
			
			if (action.getFl2LicenseType().equalsIgnoreCase("CL2")) { 
					 stmt = con.prepareStatement(query);
				 }
			String line = "";
			StringTokenizer st = null;
			int lineNumber = 0;
			int tokenNumber = 0;
			String datenew="";Date date1=null;
			
			while ((line = bReader.readLine()) != null) {
				lineNumber++;
				
				st = new StringTokenizer(line, " ");
				while (st.hasMoreTokens()) {
					String sd = st.nextToken() + "  ";

					if (sd != null) {
						if (lineNumber == 1) {
							gatepass = sd;
						}
						else// line number >1
						{
							if (gatepass.trim().equalsIgnoreCase(
									action.getScanGatePassNo().trim())) {
								casecode = sd.trim();
								//if (action.getFl2LicenseType().equalsIgnoreCase("CL2") || action.getFl2LicenseType().equalsIgnoreCase("FL2")) {
									if (this.etin(casecode.trim().substring(0, 12),	action) == true	|| casecode.trim().length() != 35) {
										FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
												" Casecode-"+ casecode.trim()+ " does not belongs to the brands for the selected gatepass!",
												" Casecode-"+ casecode.trim()+ " does not belongs to the brands for the selected gatepass!"));

										return;
									} 
									else 
									{    if (action.getFl2LicenseType().equalsIgnoreCase("FL2")) {

											
											  if(casecode.trim().charAt(3)=='1'  && casecode.substring(4,6).equalsIgnoreCase("49") && (casecode.substring(0, 12).equalsIgnoreCase("143149131805") || casecode.substring(0, 12).equalsIgnoreCase("143149131902"))){
												query = "  update bottling_unmapped.disliry_unmap_fl3a set ws_date=?,ws_gatepass=?,shop_id=?,shop_type =?" +
														" where  ws_date IS NULL and etin=? and casecode=?  and recv_id='"+action.getFl2_fl2bId()+"' AND date_plan=?  ";
											}else if(casecode.trim().charAt(3)=='1'  ){
												query = "  update bottling_unmapped.disliry_unmap_fl3 set ws_date=?,ws_gatepass=?,shop_id=?,shop_type =?" +
														" where  ws_date IS NULL and etin=? and casecode=?  and recv_id='"+action.getFl2_fl2bId()+"' AND date_plan=?  ";
											}
											else if(casecode.trim().charAt(3)=='2'){
																			query = "  update bottling_unmapped.disliry_unmap_fl3a set ws_date=?,ws_gatepass=?,shop_id=?,shop_type =?" +
																					" where  ws_date IS NULL and etin=? and casecode=?  and recv_id='"+action.getFl2_fl2bId()+"' AND date_plan=?  ";
																		}else if(casecode.trim().charAt(3)=='4' ||casecode.trim().charAt(3)=='5' || casecode.trim().charAt(3)=='6' ||casecode.trim().charAt(3)=='7'){
																			query = "  update bottling_unmapped.bwfl set ws_date=?,ws_gatepass=?,shop_id=?,shop_type =?" +
																					" where  ws_date IS NULL and etin=? and casecode=?  and recv_id='"+action.getFl2_fl2bId()+"' AND date_plan=?  ";
																		}else if(casecode.trim().charAt(3)=='8'){
																			query = "  update bottling_unmapped.fl2d set ws_date=?,ws_gatepass=?,shop_id=?,shop_type =?" +
																					" where  ws_date IS NULL and etin=? and casecode=?  and recv_id='"+action.getFl2_fl2bId()+"' AND date_plan=?  ";
																		}else {
																			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
																					" Casecode-"+ casecode.trim()+ " not found !",
																					" Casecode-"+ casecode.trim()+ "  not found !")); 
																		}
											stmt = con.prepareStatement(query);

										}else if (action.getFl2LicenseType().equalsIgnoreCase("FL2B")) {
											if (this.labcheck(casecode.trim().substring(0, 12)) == true	) {
												if(casecode.trim().charAt(3)=='1'  ){
													query = "  update bottling_unmapped.disliry_unmap_fl3 set ws_date=?,ws_gatepass=?,shop_id=?,shop_type =?" +
															" where  ws_date IS NULL and etin=? and casecode=?  and recv_id='"+action.getFl2_fl2bId()+"' AND date_plan=?  ";
												}
												else if(casecode.trim().charAt(3)=='2'){
																				query = "  update bottling_unmapped.disliry_unmap_fl3a set ws_date=?,ws_gatepass=?,shop_id=?,shop_type =?" +
																						" where  ws_date IS NULL and etin=? and casecode=?  and recv_id='"+action.getFl2_fl2bId()+"' AND date_plan=?  ";
																			}else if(casecode.trim().charAt(3)=='4' ||casecode.trim().charAt(3)=='5' || casecode.trim().charAt(3)=='6' ||casecode.trim().charAt(3)=='7'){
																				query = "  update bottling_unmapped.bwfl set ws_date=?,ws_gatepass=?,shop_id=?,shop_type =?" +
																						" where  ws_date IS NULL and etin=? and casecode=?  and recv_id='"+action.getFl2_fl2bId()+"' AND date_plan=?  ";
																			}else if(casecode.trim().charAt(3)=='8'){
																				query = "  update bottling_unmapped.fl2d set ws_date=?,ws_gatepass=?,shop_id=?,shop_type =?" +
																						" where  ws_date IS NULL and etin=? and casecode=?  and recv_id='"+action.getFl2_fl2bId()+"' AND date_plan=?  ";
																			}else {
																				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
																						" Casecode-"+ casecode.trim()+ " not found !",
																						" Casecode-"+ casecode.trim()+ "  not found !")); 
																			}
											} else{
											
											    if(casecode.trim().charAt(3)=='1'  ){
												query = "  update bottling_unmapped.brewary_unmap_fl3 set ws_date=?,ws_gatepass=?,shop_id=?,shop_type =?" +
														" where  ws_date IS NULL and etin=? and casecode=?  and recv_id='"+action.getFl2_fl2bId()+"' AND date_plan=?  ";
											}
											else if(casecode.trim().charAt(3)=='2'){
																			query = "  update bottling_unmapped.brewary_unmap_fl3a set ws_date=?,ws_gatepass=?,shop_id=?,shop_type =?" +
																					" where  ws_date IS NULL and etin=? and casecode=?  and recv_id='"+action.getFl2_fl2bId()+"' AND date_plan=?  ";
																		}else if(casecode.trim().charAt(3)=='4' ||casecode.trim().charAt(3)=='5' || casecode.trim().charAt(3)=='6' ||casecode.trim().charAt(3)=='7'){
																			query = "  update bottling_unmapped.bwfl set ws_date=?,ws_gatepass=?,shop_id=?,shop_type =?" +
																					" where  ws_date IS NULL and etin=? and casecode=?  and recv_id='"+action.getFl2_fl2bId()+"' AND date_plan=?  ";
																		}else if(casecode.trim().charAt(3)=='8'){
																			query = "  update bottling_unmapped.fl2d set ws_date=?,ws_gatepass=?,shop_id=?,shop_type =?" +
																					" where  ws_date IS NULL and etin=? and casecode=?  and recv_id='"+action.getFl2_fl2bId()+"' AND date_plan=?  ";
																		}else {
																			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
																					" Casecode-"+ casecode.trim()+ " not found !",
																					" Casecode-"+ casecode.trim()+ "  not found !")); 
																		}}
											stmt = con.prepareStatement(query);

										}
									
										stmt.setString(2, gatepass.toUpperCase().trim());
										stmt.setDate(1,Utility.convertUtilDateToSQLDate(new Date()));
										stmt.setString(6, casecode.substring(26, 35));
										stmt.setString(5, casecode.substring(0, 12));
										stmt.setString(3,action.getShopid());
										stmt.setString(4,action.getShoptype());
										datenew=casecode.substring(16,22).trim();
										datenew="20"+datenew;
										datenew	=datenew.substring(0,4)+"-"+datenew.substring(4,6)+"-"+datenew.substring(6);
										date1=new SimpleDateFormat("yyyy-MM-dd").parse(datenew);
										stmt.setDate(7,Utility.convertUtilDateToSQLDate(date1));
										if (!action.getFl2LicenseType().equalsIgnoreCase("CL2")) {
											status=+stmt.executeUpdate();
										}else{
										stmt.addBatch();
										}
										GatepassToDistrict_FL2_FL2B_DT dt = new GatepassToDistrict_FL2_FL2B_DT();
										dt.setCasecode(casecode.substring(26, 35));
										action.getTempList().add(casecode); 

									}/*}else{if (this.etin(casecode.trim().substring(0, 12),	action) == true	|| casecode.trim().length() != 35) {
										FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
												" Casecode-"+ casecode.trim()+ " does not belongs to the brands for the selected gatepass!",
												" Casecode-"+ casecode.trim()+ " does not belongs to the brands for the selected gatepass!"));

										return;
									} else {
										 
										stmt.setString(1, gatepass.trim());
										stmt.setDate(2,Utility.convertUtilDateToSQLDate(new Date()));
										stmt.setString(3, casecode);
										stmt.setInt(4, action.getFl2_fl2bId());
										stmt.setString(5,action.getFl2LicenseType());
										stmt.addBatch();
										GatepassToDistrict_FL2_FL2B_DT dt = new GatepassToDistrict_FL2_FL2B_DT();
										dt.setGatepass(gatepass);
										dt.setCasecode(casecode);
										action.getVal.add(dt);
										
									}}*/
							} else {
								flag = 1;
							}
						}

					}

					tokenNumber = 0;
				}
			}
			if (flag == 0) {

				if (!action.getFl2LicenseType().equalsIgnoreCase("FL2")) {
					
				 
				status += stmt.executeBatch().length;
				}
				if (status > 0) {
					con.commit();
					action.setValFlag(true);

					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"File Uploaded Successfully",
									"File Uploaded Successfully"));
				} else {
					con.rollback();
					action.getVal.clear();

					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"File Not Uploaded -No valid Casecode found!!",
									"File Not Uploaded -No valid Casecode found !!"));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Kindly Enter Same Gatepass Number ",
								"Kindly Enter Same Gatepass Number "));
			}

		} catch (Exception ex) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ex
							.getMessage(), ex.getMessage()));
		} finally {
			try {
				if (stmt != null)
					stmt.close();

				if (con != null)
					con.close();

			} catch (Exception ex) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, ex
								.getMessage(), ex.getMessage()));
			}
		}
	}

	public void saveCSVfl(GatepassToDistrict_FL2_FL2B_Action action)
			throws IOException {
		Connection con = null;
		PreparedStatement stmt = null;

		String query = " INSERT INTO bottling_unmapped.scan_temp(gatepass, casecode) VALUES (?,?) ";

		String gatepass = "";
		int status = 0, flag = 0;
		BufferedReader bReader = new BufferedReader(new FileReader(
				action.getCsvFilepath()));
		// String line = "";
		try {
			con = ConnectionToDataBase.getConnection19_20();
			stmt = con.prepareStatement(query);
			// ArrayList ar = new ArrayList();

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

							gatepass = sd;
						}

						else// line number >1
						{

							if (gatepass.trim().equalsIgnoreCase(
									action.getScanGatePassNo().trim())) {

								casecode = sd;

								if (this.etin(casecode.trim().substring(0, 12),
										action) == true) {
									FacesContext
									.getCurrentInstance()
									.addMessage(
											null,
											new FacesMessage(
													FacesMessage.SEVERITY_ERROR,
													" Casecode-"
															+ casecode
															.trim()
															+ " does not belongs to the brands for the selected gatepass!",
															" Casecode-"
																	+ casecode
																	.trim()
																	+ " does not belongs to the brands for the selected gatepass!"));
								} else {
									stmt.setString(1, gatepass.trim());
									stmt.setString(2, casecode.trim());
									stmt.addBatch();
									// status = stmt.executeUpdate();
								}
							} else {
								flag = 1;
							}
						}

					}

					tokenNumber = 0;
				}
			}

			if (flag == 0) {
				status = stmt.executeBatch().length;
				if (status > 0) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"File Uploaded Successfully",
									"File Uploaded Successfully"));
				} else {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"File Not Uploaded !!",
									"File Not Uploaded !!"));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Kindly Enter Same Gatepass Number ",
								"Kindly Enter Same Gatepass Number "));
			}

		} catch (Exception ex) {

			if(ex instanceof SQLException){

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Your file contains casecodes which are already dispatched or uploaded. Either you have uploaded them before on" +
								" some other gatepass or you are trying to upload new casecodes without cancelling your previous upload on same gatepass. " , "Your file contains casecodes which are already dispatched or uploaded. Either you have uploaded them before on" +
								" some other gatepass or you are trying to upload new casecodes without cancelling your previous upload on same gatepass."));
			}
			else{
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, ex
								.getMessage(), ex.getMessage()));

			}

		} finally {
			try {
				if (stmt != null)
					stmt.close();

				if (con != null)
					con.close();

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// --------------------- print case code ---------------------

	public boolean printReport_CaseCode(GatepassToDistrict_FL2_FL2B_Action act,
			GatepassToDistrict_FL2_FL2B_DT dt) {

		String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
		String relativePath = mypath + File.separator + "ExciseUp"
				+ File.separator + "WholeSale" + File.separator + "jasper";
		String relativePathpdf = mypath + File.separator + "ExciseUp"
				+ File.separator + "WholeSale" + File.separator + "pdf";
		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		PreparedStatement pst = null;
		// Connection con = null;
		Connection con3 = null;
		ResultSet rs = null;
		String reportQuery = null;
		boolean printFlag2 = false;

		try {
			// con = ConnectionToDataBase.getConnection();
			con3 = ConnectionToDataBase.getConnection19_20();

			reportQuery =

					"	SELECT fl36gatepass, array_agg(casecode) casecode "
							+ "	FROM bottling_unmapped.fl2_2b_cl2_unmap  "
							+ "	where fl36gatepass= '" + act.getPrintGatePassNo()
							+ "'    " + "	GROUP BY fl36gatepass ";

			// System.out.println("reportQuery---------------------"+reportQuery);
			pst = con3.prepareStatement(reportQuery);
			rs = pst.executeQuery();

			if (rs.next()) {
				rs = pst.executeQuery();

				Map parameters = new HashMap();
				// parameters.put("REPORT_CONNECTION", con);
				parameters.put("REPORT_CONNECTION", con3);
				parameters.put("SUBREPORT_DIR", relativePath + File.separator);
				parameters.put("image", relativePath + File.separator);

				JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);

				jasperReport = (JasperReport) JRLoader.loadObject(relativePath
						+ File.separator
						+ "GatePassToRetailerFL2_FL2BPrintCasecode.jasper");

				JasperPrint print = JasperFillManager.fillReport(jasperReport,
						parameters, jrRs);
				Random rand = new Random();
				int n = rand.nextInt(250) + 1;

				JasperExportManager.exportReportToPdfFile(print,
						relativePathpdf + File.separator
						+ "GatePassToRetailerFL2_FL2BPrintCasecode" + n
						+ ".pdf");

				// dt.setPdfName("GatePassToRetailerFL2_FL2BPrintCasecode" + n +
				// ".pdf");
				act.setPdfname_CaseCode("GatePassToRetailerFL2_FL2BPrintCasecode"
						+ n + ".pdf");
				dt.setPrintFlag_CaseCode(true);
				printFlag2 = true;

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("No Data Found_C", "No Data Found_C"));

				printFlag2 = false;
				dt.setPrintFlag_CaseCode(false);
			}
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (con3 != null)
					con3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return printFlag2;

	}

	public int getDetail(GatepassToDistrict_FL2_FL2B_Action act, String gatepass) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			String updtQr11 = " SELECT distinct b.int_brand_id,b.int_pckg_id,b.dispatch_bottle,b.breakage                         "
					+ " FROM fl2d.gatepass_to_districtwholesale_fl2_fl2b_19_20  a,fl2d.fl2_stock_trxn_fl2_fl2b_19_20 b     "
					+ " WHERE a.int_fl2_fl2b_id=b.int_fl2_fl2b_id and a.int_fl2_fl2b_id=?                      "
					+ " and   a.vch_gatepass_no=b.vch_gatepass_no and      a.vch_from =?                       "
					+ " and   a.vch_gatepass_no=?                                                              "
					+ " group by b.int_brand_id,b.int_pckg_id,b.dispatch_bottle,b.breakage  ";
			pstmt = con.prepareStatement(updtQr11);
			pstmt.setString(3, gatepass);
			pstmt.setString(2, act.getVch_from());
			pstmt.setInt(1, act.getFl2_fl2bId());
			rs = pstmt.executeQuery();

			while (rs.next()) {

				updtQr11 = " UPDATE fl2d.fl2_2b_stock_19_20 "
						+ " SET dispatchbotl = (dispatchbotl-"
						+ rs.getInt("dispatch_bottle") + "+"
						+ rs.getInt("breakage") + ") " + " WHERE id='"
						+ act.getFl2_fl2bId() + "' AND brand_id='"
						+ rs.getInt("int_brand_id") + "'" + " AND pckg_id='"
						+ rs.getInt("int_pckg_id") + "' AND type='"
						+ act.getVch_from() + "' ";
				pstmt = con.prepareStatement(updtQr11);
				System.out.println("updtQr11===" + updtQr11);
				id = pstmt.executeUpdate();

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

	// //faizal changes/////

	public boolean printReport(GatepassToDistrict_FL2_FL2B_Action act,
			GatepassToDistrict_FL2_FL2B_DT dt) {

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
			reportQuery = "select * from fl2d.fl2_2b_gatepass('"
					+ act.getFl2_fl2bId() + "', '" + act.getPrintGatePassNo()
					+ "')";

			pst = con.prepareStatement(reportQuery);

			rs = pst.executeQuery();

			if (rs.next()) {
				rs = pst.executeQuery();

				Map parameters = new HashMap();
				parameters.put("REPORT_CONNECTION", con);
				// parameters.put("SUBREPORT_DIR", relativePath+File.separator);
				parameters.put("image", relativePath + File.separator);
				parameters.put("licenseno", act.getVch_licence_no());
				JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);
				// jasperReport = (JasperReport)
				// JRLoader.loadObject(relativePath
				// + File.separator
				// + "GatePassToRetailerFL2_FL2BPrint.jasper");
				if (act.getVchToPrint().equalsIgnoreCase("RT")) {

					jasperReport = (JasperReport) JRLoader
							.loadObject(relativePath + File.separator
									+ "GatePassToRetailerFL2_FL2BPrint2.jasper");
				} else if (act.eco_flag) {
					jasperReport = (JasperReport) JRLoader
							.loadObject(relativePath + File.separator
									+ "GatePassToRetailerFL2_FL2BPrint3.jasper");

				} else {
					jasperReport = (JasperReport) JRLoader
							.loadObject(relativePath + File.separator
									+ "GatePassToRetailerFL2_FL2BPrint.jasper");
				}
				JasperPrint print = JasperFillManager.fillReport(jasperReport,
						parameters, jrRs);
				Random rand = new Random();
				int n = rand.nextInt(250) + 1;

				// JasperExportManager.exportReportToPdfFile(print,relativePathpdf
				// + File.separator+ "GatePassToRetailerFL2_FL2BPrint"+
				// act.getPrintGatePassNo() + "-" + n+ ".pdf");
				JasperExportManager.exportReportToPdfFile(
						print,
						relativePathpdf + File.separator + "FL2_FL2B"
								+ act.getPrintGatePassNo() + n + ".pdf");

				dt.setPdfName("FL2_FL2B" + act.getPrintGatePassNo() + n
						+ ".pdf");
				act.setPdfname("FL2_FL2B" + act.getPrintGatePassNo() + n
						+ ".pdf");
				dt.setPrintFlag(true);
				printFlag = true;

				/*
				 * dt.setPdfDraft("GatePassToRetailerFL2_FL2BDraft1"+ n +
				 * ".pdf"); act.setPdfDraft("GatePassToRetailerFL2_FL2BDraft1"+
				 * n + ".pdf"); dt.setPrintDraftFlagDt(true); printFlag = true;
				 */

			} else {
				
				reportQuery = "select * from fl2d.fl2_2b_gatepass_temp('"
						+ act.getFl2_fl2bId() + "', '" + act.getPrintGatePassNo()
						+ "')";

				pst = con.prepareStatement(reportQuery);

				rs = pst.executeQuery();

				if (rs.next()) {
					rs = pst.executeQuery();

					Map parameters = new HashMap();
					parameters.put("REPORT_CONNECTION", con);
					// parameters.put("SUBREPORT_DIR", relativePath+File.separator);
					parameters.put("image", relativePath + File.separator);
					parameters.put("licenseno", act.getVch_licence_no());
					JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);
					// jasperReport = (JasperReport)
					// JRLoader.loadObject(relativePath
					// + File.separator
					// + "GatePassToRetailerFL2_FL2BPrint.jasper");
					if (act.getVchToPrint().equalsIgnoreCase("RT")) {

						jasperReport = (JasperReport) JRLoader
								.loadObject(relativePath + File.separator
										+ "GatePassToRetailerFL2_FL2BPrint2.jasper");
					} else if (act.eco_flag) {
						jasperReport = (JasperReport) JRLoader
								.loadObject(relativePath + File.separator
										+ "GatePassToRetailerFL2_FL2BPrint3.jasper");

					} else {
						jasperReport = (JasperReport) JRLoader
								.loadObject(relativePath + File.separator
										+ "GatePassToRetailerFL2_FL2BPrint.jasper");
					}
					JasperPrint print = JasperFillManager.fillReport(jasperReport,
							parameters, jrRs);
					Random rand = new Random();
					int n = rand.nextInt(250) + 1;

					// JasperExportManager.exportReportToPdfFile(print,relativePathpdf
					// + File.separator+ "GatePassToRetailerFL2_FL2BPrint"+
					// act.getPrintGatePassNo() + "-" + n+ ".pdf");
					JasperExportManager.exportReportToPdfFile(
							print,
							relativePathpdf + File.separator + "FL2_FL2B"
									+ act.getPrintGatePassNo() + n + ".pdf");

					dt.setPdfName("FL2_FL2B" + act.getPrintGatePassNo() + n
							+ ".pdf");
					act.setPdfname("FL2_FL2B" + act.getPrintGatePassNo() + n
							+ ".pdf");
					dt.setPrintFlag(true);
					printFlag = true;

					/*
					 * dt.setPdfDraft("GatePassToRetailerFL2_FL2BDraft1"+ n +
					 * ".pdf"); act.setPdfDraft("GatePassToRetailerFL2_FL2BDraft1"+
					 * n + ".pdf"); dt.setPrintDraftFlagDt(true); printFlag = true;
					 */

				} else {
				
				
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("No Data Found", "No Data Found"));

				printFlag = false;
				dt.setPrintFlag(false);}
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

	// -------------------------print draft pro--------------------------

	public boolean printDraftReport(GatepassToDistrict_FL2_FL2B_Action act,
			GatepassToDistrict_FL2_FL2B_DT dt) {

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
		boolean printDraftFlag = false;

		try {
			con = ConnectionToDataBase.getConnection();
			reportQuery = "select * from fl2d.fl2_2b_gatepass('"
					+ act.getFl2_fl2bId() + "', '" + act.getPrintGatePassNo()
					+ "')";

			pst = con.prepareStatement(reportQuery);

			rs = pst.executeQuery();

			if (rs.next()) {
				rs = pst.executeQuery();

				Map parameters = new HashMap();
				parameters.put("REPORT_CONNECTION", con);
				// parameters.put("SUBREPORT_DIR", relativePath+File.separator);
				parameters.put("image", relativePath + File.separator);
				parameters.put("licenseno", act.getVch_licence_no());
				JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);
				// jasperReport = (JasperReport)
				// JRLoader.loadObject(relativePath+ File.separator+
				// "GatePassToRetailerFL2_FL2BDraft1.jasper");
				if (act.getVch_TO_Print().equalsIgnoreCase("RT")) {
					jasperReport = (JasperReport) JRLoader.loadObject(relativePath + File.separator+ "GatePassToRetailerFL2_FL2BDraft2.jasper");
				} else if (act.eco_flag) {
					jasperReport = (JasperReport) JRLoader.loadObject(relativePath+ File.separator+ "GatePassToRetailerFL2_FL2BDraft13.jasper");

				} else {
					jasperReport = (JasperReport) JRLoader.loadObject(relativePath + File.separator+ "GatePassToRetailerFL2_FL2BDraft1.jasper");

				}

				JasperPrint print = JasperFillManager.fillReport(jasperReport,parameters, jrRs);
				Random rand = new Random();
				int n = rand.nextInt(250) + 1;

				// JasperExportManager.exportReportToPdfFile(print,relativePathpdf
				// + File.separator+ "GatePassToRetailerFL2_FL2BPrint"+
				// act.getPrintGatePassNo() + "-" + n+ ".pdf");
				JasperExportManager.exportReportToPdfFile(
						print,
						relativePathpdf + File.separator + "FL2_FL2B"
								+ act.getPrintGatePassNo() + n + "Draft.pdf");

				dt.setDraftpdfname("FL2_FL2B" + act.getPrintGatePassNo() + n
						+ "Draft.pdf");

				act.setDraftpdfname("FL2_FL2B" + act.getPrintGatePassNo() + n
						+ "Draft.pdf");

				dt.setPrintDraftFlagDt(true);
				printDraftFlag = true;

				/*
				 * dt.setPdfDraft("GatePassToRetailerFL2_FL2BDraft1"+ n +
				 * ".pdf"); act.setPdfDraft("GatePassToRetailerFL2_FL2BDraft1"+
				 * n + ".pdf"); dt.setPrintDraftFlagDt(true); printFlag = true;
				 */

			} else {

				reportQuery = "select * from fl2d.fl2_2b_gatepass_temp('"
						+ act.getFl2_fl2bId() + "', '" + act.getPrintGatePassNo()
						+ "')";

				pst = con.prepareStatement(reportQuery);

				rs = pst.executeQuery();

				if (rs.next()) {
					rs = pst.executeQuery();

					Map parameters = new HashMap();
					parameters.put("REPORT_CONNECTION", con);
					// parameters.put("SUBREPORT_DIR", relativePath+File.separator);
					parameters.put("image", relativePath + File.separator);
					parameters.put("licenseno", act.getVch_licence_no());
					JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);
					// jasperReport = (JasperReport)
					// JRLoader.loadObject(relativePath+ File.separator+
					// "GatePassToRetailerFL2_FL2BDraft1.jasper");
					if (act.getVch_TO_Print().equalsIgnoreCase("RT")) {
						jasperReport = (JasperReport) JRLoader.loadObject(relativePath + File.separator+ "GatePassToRetailerFL2_FL2BDraft2.jasper");
					} else if (act.eco_flag) {
						jasperReport = (JasperReport) JRLoader.loadObject(relativePath+ File.separator+ "GatePassToRetailerFL2_FL2BDraft13.jasper");

					} else {
						jasperReport = (JasperReport) JRLoader.loadObject(relativePath + File.separator+ "GatePassToRetailerFL2_FL2BDraft1.jasper");

					}

					JasperPrint print = JasperFillManager.fillReport(jasperReport,parameters, jrRs);
					Random rand = new Random();
					int n = rand.nextInt(250) + 1;

					// JasperExportManager.exportReportToPdfFile(print,relativePathpdf
					// + File.separator+ "GatePassToRetailerFL2_FL2BPrint"+
					// act.getPrintGatePassNo() + "-" + n+ ".pdf");
					JasperExportManager.exportReportToPdfFile(
							print,
							relativePathpdf + File.separator + "FL2_FL2B"
									+ act.getPrintGatePassNo() + n + "Draft.pdf");

					dt.setDraftpdfname("FL2_FL2B" + act.getPrintGatePassNo() + n
							+ "Draft.pdf");

					act.setDraftpdfname("FL2_FL2B" + act.getPrintGatePassNo() + n
							+ "Draft.pdf");

					dt.setPrintDraftFlagDt(true);
					printDraftFlag = true;
 

				} else {
				
				
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("No Data Found", "No Data Found"));

				 
				printDraftFlag = false;

				dt.setPrintDraftFlagDt(false);
			}}
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
		return printDraftFlag;

	}

	public void getShopDetails(GatepassToDistrict_FL2_FL2B_Action ac) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			con = ConnectionToDataBase.getConnection();

			if (ac.vch_from.equalsIgnoreCase("CL2")) {
				/*sql = " SELECT district_id , vch_name_of_shop, "
						+ " vch_name_of_licensee, vch_location_of_shop "
						+ " FROM retail.retail_shop    " + " where serial_no='"
						+ ac.getShopno().trim() + "' and district_id='"
						+ ac.getDistrictLic() + "' "
						+ " and  vch_shop_type='Country Liquor'   ";*/
				
				sql = " SELECT district_id , vch_name_of_shop, "
						+ " vch_name_of_licensee, vch_location_of_shop "
						+ " FROM retail.retail_shop    " + " where serial_no='"
						+ ac.getShopno().trim() + "'   and dispatch_flag is true and district_id= " +
						" (select int_shop_dist_id from distillery.wholesale_toshop_dist_map a,retail.retail_shop b" +
						" where a.int_shop_dist_id=b.district_id and a.vch_shop_type='CL' and int_wholesale_id='"+ac.getFl2_fl2bId()+"' and serial_no='"+ ac.getShopno().trim() +"')" +
						" and  vch_shop_type='Country Liquor'   ";
				
				
			} else if (ac.vch_from.equalsIgnoreCase("FL2")) {
				sql = " SELECT district_id , vch_name_of_shop, "
						+ " vch_name_of_licensee, vch_location_of_shop "
						+ " FROM retail.retail_shop    "
						+ " where serial_no='"
						+ ac.getShopno().trim()
						+ "'  and dispatch_flag is true and district_id='"
						+ ac.getDistrictLic()
						+ "' "
						+ " and  vch_shop_type in ('Model Shop','Foreign Liquor')   ";
			} else if (ac.vch_from.equalsIgnoreCase("FL2B")) {
				sql = " SELECT district_id , vch_name_of_shop, "
						+ " vch_name_of_licensee, vch_location_of_shop "
						+ " FROM retail.retail_shop    " + " where serial_no='"
						+ ac.getShopno().trim() + "' and district_id='"
						+ ac.getDistrictLic() + "' "
						+ "  and dispatch_flag is true and  vch_shop_type in ('Model Shop','Beer')   ";
			}

			int i = 0;
			 
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				i = 1;

				ac.setShopName(rs.getString("vch_name_of_shop"));
				ac.setLicenseeName(rs.getString("vch_name_of_licensee"));
				ac.setLicenseeAdrs(rs.getString("vch_location_of_shop"));
				ac.setFlagshop(true);
				ac.setDisable_flag(true);

			}

			if (i == 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Dispatch To this Shop Is Currently Not Allowed.",
								"Dispatch To this Shop Is Currently Not Allowed."));
				ac.setShopName("");
				ac.setLicenseeName("");
				ac.setLicenseeAdrs("");

			}

		} catch (SQLException se) {
			se.printStackTrace();
		}

		finally {
			try

			{
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
	}

	public boolean check_economy(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionToDataBase.getConnection();

			String sql = "select * from brandlabel.brand_registration_19_20 where liquor_category='1' and brand_id_pk='"
					+ id + "' ";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try

			{
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

		return false;
	}

	public String checklic(GatepassToDistrict_FL2_FL2B_Action ac) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String f = "";
		String sql = "";
		try {
			con = ConnectionToDataBase.getConnection();

			sql = " SELECT * FROM hotel_bar_rest.registration_for_hotels_bars_restraunt_20_21 "
					+ " where district='"
					+ ac.getDistrictLic()
					+ "' and id='"
					+ ac.getVch_to_lic_no() + "' and vch_verify_flag='V'";

			System.out.println(sql);

			int i = 0;

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				i = 1;
				f = "F";
				ac.setLicenseeName(rs.getString("name_of_license"));
				ac.setLicenseeAdrs(rs.getString("address"));
				ac.setDisable_flag(true);

			}

			if (i == 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("No record found on this HBR ID",
								"No record found on this HBR ID"));
				ac.setShopName("");
				ac.setLicenseeName("");
				ac.setLicenseeAdrs("");

			}

		} catch (SQLException se) {
			se.printStackTrace();
		}

		finally {
			try

			{
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
		return f;
	}

	public String checkshop(GatepassToDistrict_FL2_FL2B_Action ac) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String f = "";
		String sql = "";
		try {
			con = ConnectionToDataBase.getConnection();

			sql = "  ";

			System.out.println(sql);

			int i = 0;

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				i = 1;
				f = "F";
				ac.setLicenseeName(rs.getString("name_of_license"));
				ac.setLicenseeAdrs(rs.getString("address"));
				ac.setDisable_flag(true);

			}

			if (i == 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("No record found on this HBR ID",
								"No record found on this HBR ID"));
				ac.setShopName("");
				ac.setLicenseeName("");
				ac.setLicenseeAdrs("");

			}

		} catch (SQLException se) {
			se.printStackTrace();
		}

		finally {
			try

			{
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
		return f;
	}

	
}
