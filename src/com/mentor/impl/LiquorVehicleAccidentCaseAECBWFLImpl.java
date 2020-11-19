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
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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

import com.mentor.action.LiquorVehicleAccidentCaseAECBWFLAction;
import com.mentor.connectiondb.ConnectionToDataBase;
import com.mentor.datatable.LiquorVehicleAccidentCaseAECBWFLDt;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class LiquorVehicleAccidentCaseAECBWFLImpl {

	public void getDetails(LiquorVehicleAccidentCaseAECBWFLAction act) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " select districtid, description from district d where deo='"
				+ ResourceUtil.getUserNameAllReq() + "' ";
		int maxid = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				act.setDistrict_id(rs.getInt("districtid"));
				act.setAccident_district_name(rs.getString("description"));
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

	}

	/*
	 * public boolean SaveImpl(LiquorVehicleAccidentCaseAECBreweryAction act){
	 * 
	 * Connection conn = null; PreparedStatement pstmt = null; ResultSet rs =
	 * null; int saveStatus = 1; String query = ""; Calendar cal =
	 * Calendar.getInstance(); SimpleDateFormat sdf = new
	 * SimpleDateFormat("HH:mm:ss"); String time = sdf.format(cal.getTime());
	 * int max = this.maxId(); boolean flg=false; try {
	 * 
	 * conn = ConnectionToDataBase.getConnection(); conn.setAutoCommit(false);
	 * 
	 * for (int i = 0; i < act.getBrand_list().size(); i++) {
	 * 
	 * LiquorVehicleAccidentCaseAECBreweryDt dt =
	 * (LiquorVehicleAccidentCaseAECBreweryDt) act .getBrand_list().get(i);
	 * 
	 * query = " INSERT INTO bwfl_license.liquor_accidental_case "+
	 * " (int_id, req_id, distillery_id, vch_gatepass_no, curr_dt, district_id, int_brand_id, int_pack_id, damage_box, damage_bottle, "
	 * +
	 * " return_box, return_bottle, prcced_dispatch_box, prcced_dispatch_bottle) "
	 * + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
	 * 
	 * 
	 * 
	 * 
	 * pstmt = conn.prepareStatement(query);
	 * 
	 * pstmt.setInt(1, max); pstmt.setInt(2, act.getReq_id()); pstmt.setInt(3,
	 * act.getDistillery_id()); pstmt.setString(4,act.getGatepass_no());
	 * pstmt.setDate(5,Utility.convertUtilDateToSQLDate(new Date()));
	 * pstmt.setInt(6, act.getDistrict_id()); pstmt.setInt(7,
	 * dt.getInt_brand_id()); pstmt.setInt(8, dt.getInt_pack_id());
	 * pstmt.setInt(9, dt.getDamage_box()); pstmt.setInt(10,
	 * dt.getDamage_bottle()); pstmt.setInt(11, dt.getReturn_box());
	 * pstmt.setInt(12, dt.getReturn_bottle()); pstmt.setInt(13,
	 * dt.getPrcced_dispatch_box()); pstmt.setInt(14,
	 * dt.getPrcced_dispatch_bottle());
	 * 
	 * saveStatus = pstmt.executeUpdate(); max++;
	 * System.out.println("====save status= pi details=1"+saveStatus);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * if (saveStatus > 0) { conn.setAutoCommit(true); flg=true;
	 * act.setViewflag(true); FacesContext.getCurrentInstance().addMessage(null,
	 * new FacesMessage("SAVED SUCCESSFULLY !", "SAVED SUCCESSFULLY !"));
	 * 
	 * } else { act.setViewflag(false);
	 * FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage("SOMETHING WENT WRONG !", "SOMETHING WENT WRONG !"));
	 * conn.rollback();
	 * 
	 * } }
	 * 
	 * 
	 * catch (SQLException e) {
	 * FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage(e.getMessage(), e.getMessage())); e.printStackTrace();
	 * }catch (Exception e) { FacesContext.getCurrentInstance().addMessage(null,
	 * new FacesMessage(e.getMessage(), e.getMessage())); e.printStackTrace(); }
	 * 
	 * finally { try {
	 * 
	 * if (conn != null) conn.close(); if (pstmt != null) pstmt.close(); if (rs
	 * != null) rs.close();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } } return flg;
	 * 
	 * }
	 */

	public ArrayList display_detail(LiquorVehicleAccidentCaseAECBWFLAction act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int sr = 1;
		;
		String filter = "";

		if (act.getRadioType().equalsIgnoreCase("N")) {
			filter = " and verify_flg is  null and verify_dt is null ";
		} else if (act.getRadioType().equalsIgnoreCase("A")) {
			filter = " and verify_flg is  not null and verify_dt is  not null ";
			System.out.println("if show data=====================");
		}

		try {

			String query =

			" select   coalesce(a.status,'NA') as status,a.valid_up_to,a.int_id,a.vch_gatepass_no ,a.int_bwfl_id,b.gatepass_type,(select description from district d where      "
					+ " d.districtid=accident_district) as distrctnm,                                                                                                                     "
					+ " a.vch_to_lic_no, a.licensee_name,a.licensee_adrs, a.vch_root_details, a.vch_vehicle_no, a.vehicle_driver_name, a.vehicle_agency_name_adrs,                        "
					+ " b.gatepass_no, b.gatepass_date, b.accident_address, b.accident_district, b.accident_date, b.created_date,                                                         "
					+ " (select vch_firm_name  from bwfl.registration_of_bwfl_lic_holder_20_21 where int_id = b.bwfl_id) as dis_name ,                                                              "
					+ " case when b.gatepass_type='FL36' then (select vch_from from bwfl_license.gatepass_to_districtwholesale_bwfl_20_21 where vch_gatepass_no=a.vch_gatepass_no)         "
					+ " else 'NA' end as license_type                                                                                                                                     "
					+ " FROM  bwfl_license.liquor_accidental_case_mst a ,bwfl_license.request_for_accidental_case  b  ,bwfl.registration_of_bwfl_lic_holder_20_21 c                       "
					+ " where a.int_bwfl_id=b.bwfl_id and a.vch_gatepass_no=b.gatepass_no and a.int_bwfl_id=c.int_id and   c.vch_firm_district_id='"
					+ act.getDistrict_id()
					+ "'     "
					+ filter
					+ "                                      ";

			// -------------------------
			/*
			 * " select   coalesce(a.status,'NA') as status,a.valid_up_to,a.int_id,a.vch_gatepass_no ,a.distillery_id,b.gatepass_type,(select description from district d where d.districtid=accident_district) as distrctnm,     "
			 * +
			 * "  a.vch_to_lic_no, a.licensee_name,a.licensee_adrs, a.vch_root_details, a.vch_vehicle_no, a.vehicle_driver_name, a.vehicle_agency_name_adrs,  "
			 * +
			 * " b.gatepass_no, b.gatepass_date, b.accident_address, b.accident_district, b.accident_date, b.created_date,                                        "
			 * +
			 * " (select vch_undertaking_name  from dis_mst_pd1_pd2_lic where int_app_id_f = b.distillery_id) as dis_name ,"
			 * +
			 * " case when b.gatepass_type='FL3' then (select vch_from  from distillery.gatepass_to_manufacturewholesale_20_21 where vch_gatepass_no=a.vch_gatepass_no) "
			 * +
			 * " when b.gatepass_type='FL1' then (select vch_from  from distillery.gatepass_to_districtwholesale_20_21 where vch_gatepass_no=a.vch_gatepass_no)"
			 * + " else 'CL' end as license_type "+
			 * " FROM  bwfl_license.liquor_accidental_case_mst a ,bwfl_license.request_for_accidental_case  b                                                         "
			 * +
			 * " where a.distillery_id=b.distillery_id and a.vch_gatepass_no=b.gatepass_no and a.distillery_id='"
			 * +act.getDist_id()+"'   "+filter+
			 * "                                           ";
			 */
			System.out.println("-- dell details ====" + query);
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				LiquorVehicleAccidentCaseAECBWFLDt dt = new LiquorVehicleAccidentCaseAECBWFLDt();
				dt.setReq_id(rs.getInt("int_id"));

				dt.setGatepass_type(rs.getString("gatepass_type"));

				dt.setSrno(sr);
				dt.setGatepass_no(rs.getString("vch_gatepass_no"));
				dt.setGatepass_date(rs.getDate("gatepass_date"));
				dt.setAccident_address(rs.getString("accident_address"));
				dt.setAccident_date(rs.getDate("accident_date"));
				dt.setReq_dt(rs.getDate("created_date"));
				dt.setDistillery_name(rs.getString("dis_name"));
				dt.setDistillery_id(rs.getInt("int_bwfl_id"));
				dt.setAccident_district_name(rs.getString("distrctnm"));
				if (act.getRadioType().equalsIgnoreCase("A")) {
					dt.setStatus(rs.getString("status"));
				}
				dt.setVehcleNo(rs.getString("vch_vehicle_no"));
				dt.setLicenseNo(rs.getString("vch_to_lic_no"));
				dt.setLicenseNm(rs.getString("licensee_name"));
				dt.setLicenseAddrss(rs.getString("licensee_adrs"));
				dt.setLicenserootDetails(rs.getString("vch_root_details"));
				dt.setDriverNm(rs.getString("vehicle_driver_name"));
				dt.setVehicaleagecyAdd(rs.getString("vehicle_agency_name_adrs"));
				dt.setVch_from(rs.getString("license_type"));
				dt.setValid_up_to(rs.getDate("valid_up_to"));

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

	// --------------------------------GATAPASS LIST

	public String gatapassList(LiquorVehicleAccidentCaseAECBWFLAction act) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String queryList = "";

		try {
			con = ConnectionToDataBase.getConnection();

			queryList = "select vch_gatepass_no ,curr_date ,vch_to , vch_to_lic_no from bwfl_license.gatepass_to_districtwholesale_bwfl_20_21 where vch_gatepass_no='"
					+ act.getGatepass_no() + "'";

			System.out.println("=getdetails===" + queryList);

			pstmt = con.prepareStatement(queryList);
			rs = pstmt.executeQuery();

			// System.out.println("==========================querylist   ================"+queryList);
			while (rs.next()) {
				act.setDate_list(rs.getDate("curr_date"));
				act.setLicenseno_list(rs.getString("vch_to_lic_no"));
				act.setGatapassNo_list(rs.getString("vch_gatepass_no"));
				act.setLicensetype_list(rs.getString("vch_to"));

			}

		} catch (Exception se) {
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

	public ArrayList gatepass_detail(LiquorVehicleAccidentCaseAECBWFLAction act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int sr = 1;
		;
		String query = "";

		try {

			query = "select vch_gatepass_no ,curr_date ,vch_to , vch_to_lic_no from bwfl_license.gatepass_to_districtwholesale_bwfl_20_21 where vch_gatepass_no='"
					+ act.getGatepass_no() + "'";

			System.out.println("-- brand details ====" + query);
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				LiquorVehicleAccidentCaseAECBWFLDt dt = new LiquorVehicleAccidentCaseAECBWFLDt();

				dt.setSr1(sr);
				dt.setGatepass_no(rs.getString("vch_gatepass_no"));
				dt.setGatepass_date(rs.getDate("curr_date"));
				dt.setLicense_type(rs.getString("vch_to"));
				dt.setLicense_no(rs.getString("vch_to_lic_no"));
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

	public ArrayList brand_detail(LiquorVehicleAccidentCaseAECBWFLAction act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int sr = 1;
		String query = "";

		try {

			query = " select distinct br.brand_name , pk.package_name , a.size, a.dispatch_box,a.dispatch_bottle, a.int_brand_id ,                         "
					+ " a.int_pckg_id  ,  pk.quantity,   br.maped_unmaped_flag,                                                                              "
					+ " c.damage_box, c.damage_bottle, c.return_box, c.return_bottle, c.prcced_dispatch_box, c.prcced_dispatch_bottle                        "
					+ " from bwfl_license.fl2_stock_trxn_bwfl_20_21 a, distillery.brand_registration_20_21 br,  distillery.packaging_details_20_21 pk        "
					+ " ,bwfl_license.liquor_accidental_case c where a.int_brand_id=br.brand_id and pk.brand_id_fk = br.brand_id and                         "
					+ " a.int_pckg_id = pk.package_id and a.vch_gatepass_no=c.vch_gatepass_no                                                                "
					+ " and pk.package_id=c.int_pack_id and pk.brand_id_fk=c.int_brand_id                                                                    "
					+ " and a.vch_gatepass_no='"
					+ act.getGatepass_no()
					+ "'                                                                             ";

			System.out.println("-- brand details ====" + query);
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				LiquorVehicleAccidentCaseAECBWFLDt dt = new LiquorVehicleAccidentCaseAECBWFLDt();
				dt.setSr2(sr);
				dt.setBrand_name(rs.getString("brand_name"));
				dt.setSize(rs.getString("package_name"));
				dt.setBox_size(rs.getInt("size"));
				dt.setDispatch_box(rs.getInt("dispatch_box"));
				dt.setDispatch_bottle(rs.getInt("dispatch_bottle"));
				dt.setInt_brand_id(rs.getInt("int_brand_id"));
				dt.setInt_pack_id(rs.getInt("int_pckg_id"));
				dt.setDamage_box(rs.getInt("damage_box"));
				dt.setDamage_bottle(rs.getInt("damage_bottle"));
				dt.setReturn_box(rs.getInt("return_box"));
				dt.setReturn_bottle(rs.getInt("return_bottle"));
				dt.setPrcced_dispatch_box(rs.getInt("prcced_dispatch_box"));
				dt.setPrcced_dispatch_bottle(rs
						.getInt("prcced_dispatch_bottle"));
				dt.setQuantity(rs.getDouble("quantity"));
				dt.setMaped_unmaped_flag(rs.getString("maped_unmaped_flag"));

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

	public int maxId() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT coalesce(max(int_id),0) as id FROM bwfl_license.liquor_accidental_case ";
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

	// =======================================

	public void approvedupdate(LiquorVehicleAccidentCaseAECBWFLAction action) {

		int saveStatus = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String queryList = "";

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");	

			queryList = " update  bwfl_license.liquor_accidental_case_mst set verify_flg='V' ,status='VERIFIED'  ,verify_dt='"
					+ Utility.convertUtilDateToSQLDate(new Date())
					+ "' "
					+ " where  int_id='"
					+ action.getReq_id()
					+ "' and vch_gatepass_no='"
					+ action.getGatepass_no()
					+ "' and  int_bwfl_id='"
					+ action.getDistillery_id()
					+ "'  ";

			System.out.println("====approvedupdate========" + queryList);
			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(queryList);

			saveStatus = pstmt.executeUpdate();
			System.out.println("savestatus==1====" + saveStatus);
			if (saveStatus > 0) {

				saveStatus = 0;
				saveStatus = this.save(action, con);
			}

			// if(action.getGatepass_type().equalsIgnoreCase("FL36")){
			if (saveStatus > 0) {
				saveStatus = 0;
				 	
				 
				String insQr = " INSERT INTO bwfl_license.gatepass_to_districtwholesale_bwfl_20_21( "
						+ " int_bwfl_id, vch_gatepass_no, dt_date, vch_from ,vch_to, vch_to_lic_no, curr_date, licencee_id, "
						+ " vch_route_detail, vch_vehicle_no, vehicle_driver_name, vehicle_agency_name_adrs, "
						+ " licensee_name, licensee_adrs,valid_till,vch_finalize,seq,finalize_dt_time) "
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,'F',(SELECT max(seq)+1 from bwfl_license.gatepass_to_districtwholesale_bwfl_20_21)) ";

				/*
				 * " INSERT INTO distillery.gatepass_to_manufacturewholesale_cl_20_21( "
				 * +
				 * " int_dist_id, vch_distillary_name, vch_distillary_address, vch_gatepass_no, dt_date, "
				 * +
				 * " vch_from, vch_to, vch_to_lic_no, curr_date, int_max_id, db_total_duty, "
				 * +
				 * " db_total_additional_duty, vch_root_details, vch_vehicle_no, "
				 * +
				 * " vehicle_driver_name, vehicle_agency_name_adrs, licensee_name, licensee_adrs,validtill,"
				 * + " tnt,vch_finalize) " +
				 * " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, 'F') "
				 * ;
				 */

				pstmt = con.prepareStatement(insQr);
                 
				pstmt.setInt(1, action.getDistillery_id());
				// pstmt.setString(2, action.getName());
				// pstmt.setString(3,null);
				pstmt.setString(2, action.getGatepass_no() + "-Accidental");
				pstmt.setDate(3, Utility.convertUtilDateToSQLDate(action
						.getGatepass_date()));
				pstmt.setString(4, action.getVch_from());
				pstmt.setString(5, "DW-BRC");
				pstmt.setString(6, action.getLicenseNo());
				pstmt.setDate(7, Utility.convertUtilDateToSQLDate(new Date()));
				pstmt.setInt(8, 0);
				// pstmt.setDouble(11, 0);
				// pstmt.setDouble(12, 0);
				pstmt.setString(9, action.getLicenserootDetails());
				pstmt.setString(10, action.getVehcleNo());
				pstmt.setString(11, action.getDriverNm());
				pstmt.setString(12, action.getVehicaleagecyAdd());
				pstmt.setString(13, action.getLicenseNm());
				pstmt.setString(14, action.getLicenseAddrss());
				// pstmt.setInt(19, action.getDistrict1());
				// pstmt.setInt(20, action.getDistrict2());
				// pstmt.setInt(21, action.getDistrict3());
				// pstmt.setInt(22, action.getDistrictLic());
				pstmt.setDate(15, Utility.convertUtilDateToSQLDate(action
						.getValidity_date()));
				pstmt.setString(16, dateFormat.format(new Date()));
				// pstmt.setDouble(24, action.getGrossWeight());
				// pstmt.setDouble(25, action.getTierWeight());
				// pstmt.setDouble(26, action.getNetWeight());
				// pstmt.setString(20, "X");
				

				saveStatus = pstmt.executeUpdate();

				System.out.println("first status----------------" + saveStatus);
			}
			if (saveStatus > 0) {
				saveStatus = 0;
				for (int i = 0; i < action.getBrand_list().size(); i++) {
					LiquorVehicleAccidentCaseAECBWFLDt dt = (LiquorVehicleAccidentCaseAECBWFLDt) action
							.getBrand_list().get(i);

					String insQr1 = " INSERT INTO bwfl_license.fl2_stock_trxn_bwfl_20_21( "
							+ " int_bwfl_id, vch_lic_no, int_brand_id, int_pckg_id, avl_bottl, avl_box, "
							+ " dispatch_bottle,dispatch_box, size,duty, add_duty,vch_gatepass_no,  dt,  mst_seq, vch_batch_no  "
							+ "  ) VALUES "
							+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, (select nextval('seq')),?)";

					/*
					 * " INSERT INTO distillery.cl2_stock_trxn_20_21( " +
					 * " int_dissleri_id, vch_lic_no, vch_lic_type, int_brand_id, int_pckg_id, avl_bottl, "
					 * +
					 * " avl_box, dispatchd_bottl, dispatchd_box, size, duty, addduty,"
					 * + "  vch_gatepass_no, dt_date,mst_seq,vch_batch_no) " +
					 * " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, (select nextval('seq')),?)"
					 * ;
					 */

					pstmt = con.prepareStatement(insQr1);

					pstmt.setInt(1, action.getDistillery_id());
					pstmt.setString(2, action.getLicenseNo());
					// pstmt.setString(3, "CL2");
					pstmt.setInt(3, dt.getInt_brand_id());
					pstmt.setInt(4, dt.getInt_pack_id());
					pstmt.setInt(5, dt.getPrcced_dispatch_bottle());
					pstmt.setInt(6, dt.getPrcced_dispatch_box());
					pstmt.setInt(7, dt.getPrcced_dispatch_bottle());
					pstmt.setDouble(8, dt.getPrcced_dispatch_box());
					pstmt.setInt(9, dt.getBox_size());
					pstmt.setDouble(10, 0);
					pstmt.setDouble(11, 0);
					pstmt.setString(12, action.getGatepass_no() + "-Accidental");
					pstmt.setDate(13,
							Utility.convertUtilDateToSQLDate(new Date()));
					pstmt.setString(14, null);

					saveStatus = pstmt.executeUpdate();

				}
			}

			if (saveStatus > 0) {
				con.commit();
				action.back();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								" Application No." + action.getGatepass_no()
										+ " Verified !! ", " Application No."
										+ action.getGatepass_no()
										+ " Verified !! "));

			} else {
				con.rollback();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Not Verified  !!! ", "Not Verified !!!"));

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

	// =======================================

	public void rejectdata(LiquorVehicleAccidentCaseAECBWFLAction action) {

		int saveStatus = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String queryList = "";

		try {

			queryList =
					 " update   bwfl_license.request_for_accidental_case set save_flag=null where "
					
					+ " gatepass_no='" + action.getGatepass_no() + "'  ";
					/* " update  bwfl_license.liquor_accidental_case_mst set verify_flg='R' ,status='REJECTED'  ,verify_dt='"
					+ Utility.convertUtilDateToSQLDate(new Date())
					+ "' "
					+ " where  int_id='"
					+ action.getReq_id()
					+ "' and vch_gatepass_no='"
					+ action.getGatepass_no()
					+ "' and int_bwfl_id='" + action.getDist_id() + "'  ";*/

			// System.out.println("====rejectdata========" + queryList);
			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(queryList);

			saveStatus = pstmt.executeUpdate();
			if (saveStatus > 0) {
				saveStatus = 0;
				String insQr = " delete from   bwfl_license.liquor_accidental_case_mst where  vch_gatepass_no='" + action.getGatepass_no() + "' ";
				pstmt = con.prepareStatement(insQr);
				saveStatus = pstmt.executeUpdate();

				// System.out.println("first status----------------"+tok1);
			}
			if (saveStatus > 0) {
				saveStatus = 0;

				String insQr1 = " delete from   bwfl_license.liquor_accidental_case where  vch_gatepass_no='" + action.getGatepass_no() + "' ";

				pstmt = con.prepareStatement(insQr1);

				saveStatus = pstmt.executeUpdate();

			}

			if (saveStatus > 0) {
				// action.setFinalflg(true);
				con.commit();
				action.back();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								" Application No." + action.getGatepass_no()
										+ " Rejected !! ", " Application No."
										+ action.getGatepass_no()
										+ " Rejected !! "));

			} else {
				// System.out.println("---------- UPDATE NOT ----------");
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Not Rejected  !!! ", "Not Rejected !!!"));

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

	public int save(LiquorVehicleAccidentCaseAECBWFLAction action,
			Connection conn) {
		// Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		int sequence = maxID();
		int max = sequence;
		int group_id = groupID();
		try {

			String query =

			"			INSERT INTO bwfl_license.mst_bottling_plan_20_21( "
					+ "			int_distillery_id, int_brand_id, int_pack_id, int_quantity, int_planned_bottles, "
					+ "		    int_boxes, int_liquor_type, vch_license_type, plan_dt, licence_no, cr_date, seq,"
					+ "    maped_unmaped_flag,seqfrom,seqto,caseseq,entry_no_of_bottle_per_case, permitno,permitdt,group_id) "
					+ "		VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?,?, ?,?,?,?,?,?,?) ";

			// conn = ConnectionToDataBase.getConnection();
			// conn.setAutoCommit(false);

			if (action.getBrand_list().size() > 0) {

				for (int i = 0; i < action.getBrand_list().size(); i++) {
					saveStatus = 0;
					pstmt = conn.prepareStatement(query);

					int j = 1;
					long serialno = 0;

					LiquorVehicleAccidentCaseAECBWFLDt table = (LiquorVehicleAccidentCaseAECBWFLDt) action
							.getBrand_list().get(i);

					pstmt.setInt(1, action.getDistillery_id());
					pstmt.setInt(2, table.getInt_brand_id());

					pstmt.setInt(3, table.getInt_pack_id());

					pstmt.setDouble(4, table.getQuantity());
					pstmt.setInt(5, table.getReturn_bottle());
					pstmt.setInt(6, table.getReturn_box());
					pstmt.setInt(7, table.getInt_liquor_type());
					pstmt.setString(8, action.getVch_from());
					pstmt.setDate(9,
							Utility.convertUtilDateToSQLDate(new Date()));
					pstmt.setString(10, "");
					pstmt.setDate(11,
							Utility.convertUtilDateToSQLDate(new Date()));
					pstmt.setInt(12, sequence);
					pstmt.setString(13, table.getMaped_unmaped_flag());

					serialno = getSerialClBottle(
							new Double(table.getReturn_bottle()).longValue(),
							new Date());
					pstmt.setLong(16,
							(getcaseNoCl(table.getReturn_box(), new Date())));

					pstmt.setLong(14, serialno);
					pstmt.setLong(
							15,
							serialno
									+ new Double(table.getReturn_bottle())
											.longValue());
					pstmt.setLong(17, table.getReturn_bottle());
					pstmt.setString(18, action.getGatepass_no() + i
							+ "-Accidental");
					pstmt.setDate(19,
							Utility.convertUtilDateToSQLDate(new Date()));
					pstmt.setInt(20, group_id);

					if (serialno > 0) {
						saveStatus = pstmt.executeUpdate();
						sequence = sequence + 1;
						group_id = group_id + 1;
					} else {
						saveStatus = 0;
					}

				}
				System.out.println("savestatus==2====" + saveStatus);
			}

			if (saveStatus > 0) {
				saveStatus = 0;

				String Update = "Update bwfl_license.liquor_accidental_case set seq=? where int_brand_id=? and  int_pack_id=? and vch_gatepass_no=?";
				for (int i = 0; i < action.getBrand_list().size(); i++) {
					LiquorVehicleAccidentCaseAECBWFLDt dt = (LiquorVehicleAccidentCaseAECBWFLDt) action
							.getBrand_list().get(i);
					pstmt = conn.prepareStatement(Update);

					pstmt.setInt(1, max);
					pstmt.setInt(2, dt.getInt_brand_id());
					pstmt.setInt(3, dt.getInt_pack_id());
					pstmt.setString(4, action.getGatepass_no());
					saveStatus = pstmt.executeUpdate();
					max++;
				}
				System.out.println("savestatus==3====" + saveStatus);
			}

			if (saveStatus > 0) {

			} else {
				// action.reset();

			}

		}

		/*
		 * catch(Exception e) { e.printStackTrace(); }
		 */

		catch (SQLException e) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage())); // brand_Id,Distillery_ID
																		// and
																		// DAte
																		// PK

			// brand_Id,Distillery_ID and DAte PK
		}

		finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return saveStatus;

	}

	// ====================Serial No. and casecode==================

	public synchronized long getSerialClBottle(long noOfSequenc, Date date) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		long serialNo = 0;
		long currseq = 0;

		try {
			conn = ConnectionToDataBase.getConnection20_21();
			Date today = date;
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
			String currentdate = sdf.format(today);
			pstmt2 = conn
					.prepareStatement("CREATE SEQUENCE IF NOT EXISTS public.cl_manual_bottle_code_"
							+ currentdate);
			pstmt2.executeUpdate();

			pstmt = conn
					.prepareStatement("select     nextval('public.cl_manual_bottle_code_"
							+ currentdate + "')");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				serialNo = rs.getLong(1);
				if (serialNo == 0) {
					serialNo = serialNo + 1;
				}
				pstmt1 = conn
						.prepareStatement("ALTER SEQUENCE public.cl_manual_bottle_code_"
								+ currentdate
								+ " RESTART WITH "
								+ (noOfSequenc + serialNo + 1));

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

	public synchronized long getSerialFl3Bottle(long noOfSequenc, Date date) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		long serialNo = 0;
		long currseq = 0;

		try {
			conn = ConnectionToDataBase.getConnection20_21();

			Date today = date;
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
			String currentdate = sdf.format(today);
			pstmt2 = conn
					.prepareStatement("CREATE SEQUENCE IF NOT EXISTS public.fl3_manual_bottle_code_"
							+ currentdate);
			pstmt2.executeUpdate();

			pstmt = conn
					.prepareStatement("select     nextval('public.fl3_manual_bottle_code_"
							+ currentdate + "')");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				serialNo = rs.getLong(1);
				if (serialNo == 0) {
					serialNo = serialNo + 1;
				}
				// System.out.println("noOfSequenc " + noOfSequenc);

				pstmt1 = conn
						.prepareStatement("ALTER SEQUENCE public.fl3_manual_bottle_code_"
								+ currentdate
								+ " RESTART WITH "
								+ (noOfSequenc + serialNo + 1));

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

	public synchronized long getSerialFl3aBottle(long noOfSequenc, Date date) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		long serialNo = 0;
		long currseq = 0;

		try {
			conn = ConnectionToDataBase.getConnection20_21();

			Date today = date;
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
			String currentdate = sdf.format(today);
			pstmt2 = conn
					.prepareStatement("CREATE SEQUENCE IF NOT EXISTS public.fl3a_manual_bottle_code_"
							+ currentdate);
			pstmt2.executeUpdate();

			pstmt = conn
					.prepareStatement(" select     nextval('public.fl3a_manual_bottle_code_"
							+ currentdate + "')");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				serialNo = rs.getLong(1);
				if (serialNo == 0) {
					serialNo = serialNo + 1;
				}
				// System.out.println("noOfSequenc " + noOfSequenc);

				pstmt1 = conn
						.prepareStatement("ALTER SEQUENCE public.fl3a_manual_bottle_code_"
								+ currentdate
								+ " RESTART WITH "
								+ (noOfSequenc + serialNo + 1));

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

	public synchronized long getcaseNoCl(int no, Date date) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		long serialNo = 0;
		long currseq = 0;

		try {
			conn = ConnectionToDataBase.getConnection20_21();
			Date today = date;
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
			String currentdate = sdf.format(today);
			pstmt2 = conn
					.prepareStatement("CREATE SEQUENCE IF NOT EXISTS public.cl_manual_case_code_"
							+ currentdate);
			pstmt2.executeUpdate();
			pstmt = conn
					.prepareStatement("select     nextval('public.cl_manual_case_code_"
							+ currentdate + "')");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				serialNo = rs.getLong(1);
				if (serialNo == 0) {
					serialNo = serialNo;
				}
				pstmt1 = conn
						.prepareStatement("ALTER SEQUENCE public.cl_manual_case_code_"
								+ currentdate
								+ " RESTART WITH "
								+ (no + serialNo));

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

	public synchronized long getcaseNoFl3(int no, Date date) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		long serialNo = 0;
		long currseq = 0;

		try {
			conn = ConnectionToDataBase.getConnection20_21();

			Date today = date;
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
			String currentdate = sdf.format(today);
			pstmt2 = conn
					.prepareStatement("CREATE SEQUENCE IF NOT EXISTS public.fl3_manual_case_code_"
							+ currentdate);
			pstmt2.executeUpdate();

			pstmt = conn
					.prepareStatement("select     nextval('public.fl3_manual_case_code_"
							+ currentdate + "')");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				serialNo = rs.getLong(1);
				if (serialNo == 0) {
					serialNo = serialNo;
				}
				pstmt1 = conn
						.prepareStatement("ALTER SEQUENCE public.fl3_manual_case_code_"
								+ currentdate
								+ " RESTART WITH "
								+ (no + serialNo));

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

	public synchronized long getcaseNoFl3a(int no, Date date) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		long serialNo = 0;
		long currseq = 0;

		try {
			conn = ConnectionToDataBase.getConnection20_21();

			Date today = date;
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
			String currentdate = sdf.format(today);
			pstmt2 = conn
					.prepareStatement("CREATE SEQUENCE IF NOT EXISTS public.fl3a_manual_case_code_"
							+ currentdate);
			pstmt2.executeUpdate();

			pstmt = conn
					.prepareStatement(" select     nextval('public.fl3a_manual_case_code_"
							+ currentdate + "')");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				serialNo = rs.getLong(1);
				if (serialNo == 0) {
					serialNo = serialNo;
				}
				pstmt1 = conn
						.prepareStatement("ALTER SEQUENCE public.fl3a_manual_case_code_"
								+ currentdate
								+ " RESTART WITH "
								+ (no + serialNo));

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

	public int maxID() {

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

	public int groupID() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(group_id) as id FROM bwfl_license.mst_bottling_plan_20_21 ";
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

	public ArrayList getDatacl(LiquorVehicleAccidentCaseAECBWFLAction ac) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String filter = "";

		String sql =

		"	SELECT d.dispatch_bottle,a.group_id,a.maped_unmaped_flag,a.seq,c.code_generate_through,coalesce(b.tracking_flg,'N') as tracking_flg, b.liquor_type ,               "
				+ "  a.finalized_date,a.finalized_flag,a.int_distillery_id,a.licence_no,replace(a.licence_no,' ','')as licencenoo ,                         "
				+ "  a.int_brand_id,b.brand_name, a.int_pack_id,c.package_name , a.int_quantity, d.size ,                                                           "
				+ " a.int_planned_bottles, a.int_boxes, a.int_liquor_type, a.vch_license_type, a.plan_dt ,a.seqfrom,a.seqto,a.caseseq ,d.breakage,d.receive_date "
				+ " ,coalesce(d.receive_flg,'N') as 	receive_flg FROM bwfl_license.mst_bottling_plan_20_21 a ,distillery.brand_registration_20_21 b,                                                     "
				+ "     distillery.packaging_details_20_21 c ,bwfl_license.liquor_accidental_case d                                                           "
				+ "     where a.int_brand_id=b.brand_id                                                                                                     "
				+ "    and  b.brand_id=c.brand_id_fk                                                                                                        "
				+ "   and   a.int_pack_id=c.package_id                                                                                                      "
				+ "  and d.int_brand_id = b.brand_id and d.int_pack_id = c.package_id and a.int_brand_id = d.int_brand_id and a.int_pack_id = d.int_pack_id "
				+ "  and a.int_distillery_id =d.int_bwfl_id and a.seq = d.seq                                                                             "
				+ "  and a.int_distillery_id =?  and d.vch_gatepass_no=? order by seq desc  ";

		// user_name ='"+ResourceUtil.getUserNameAllReq()+"'";

		System.out.println("getFinalised Brand==" + sql);
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, ac.getDistillery_id());
			ps.setString(2, ac.getGatepass_no());
			rs = ps.executeQuery();
			while (rs.next()) {

				LiquorVehicleAccidentCaseAECBWFLDt dt = new LiquorVehicleAccidentCaseAECBWFLDt();
				dt.setMaped_unmaped_flag(rs.getString("maped_unmaped_flag"));
				dt.setShowDataTable_Date(rs.getDate("plan_dt"));
				if (rs.getString("liquor_type").equalsIgnoreCase("FL")) {
					dt.setShowDataTable_LiqureType("Foriegn Liquor");
				} else if (rs.getString("liquor_type").equalsIgnoreCase("WINE")) {
					dt.setShowDataTable_LiqureType("WINE");
				} else if (rs.getString("liquor_type").equalsIgnoreCase("BEER")) {
					dt.setShowDataTable_LiqureType("BEER");
				}
				dt.setShowDataTable_LicenceType(rs
						.getString("vch_license_type"));
				dt.setShowDataTable_Brand(rs.getString("brand_name"));
				dt.setShowDataTable_Packging(rs.getString("package_name"));
				dt.setShowDataTable_Quntity(rs.getString("int_quantity"));
				dt.setShowDataTable_PlanNoOfBottling(rs
						.getString("int_planned_bottles"));
				dt.setShowDataTable_NoOfBoxes(rs.getString("int_boxes"));
				dt.setInt_brand_id(rs.getInt("int_brand_id"));
				dt.setInt_pack_id(rs.getInt("int_pack_id"));

				dt.setFinalized_falg(rs.getString("finalized_flag"));
				dt.setFinalised_date(Utility.convertSqlDateToUtilDate(rs
						.getDate("finalized_date")));
				dt.setCode_generate_through(rs
						.getString("code_generate_through"));
				dt.setPlan_id(rs.getInt("seq"));
				dt.setSeqform(rs.getString("seqfrom"));
				dt.setSeqto(rs.getString("seqto"));
				dt.setCaseseq(rs.getString("caseseq"));
				if (rs.getDate("finalized_date") != null) {
					Date dat = Utility.convertSqlDateToUtilDate(rs
							.getDate("plan_dt"));
					// System.out.println("date finalize" + dat);

					DateFormat formatter = new SimpleDateFormat("yyMMdd");
					String date = formatter.format(dat);
					dt.setFinalizedDateString(date);
					// System.out.println(date);
				}
				if (!rs.getString("tracking_flg").equalsIgnoreCase("Y")) {
					dt.setFinalized_falg("N");
				}

				dt.setShowDataTable_breakgae(rs.getInt("breakage"));
				dt.setReceive_date(rs.getDate("receive_date"));
				dt.setReceive_flg(rs.getString("receive_flg"));
				dt.setBox_size(rs.getInt("size"));
				dt.setGroup_id(rs.getInt("group_id"));
				dt.setShowDataTabl_barcode_bottle(rs.getInt("dispatch_bottle"));
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

	/*
	 * public void dataFinalize(LiquorVehicleAccidentCaseAECBreweryAction
	 * action, LiquorVehicleAccidentCaseAECBreweryDt dt) { Connection conn =
	 * null; Connection conn1 = null; PreparedStatement pstmt1 = null;
	 * PreparedStatement pstmt2 = null; PreparedStatement pstmt3 = null;
	 * PreparedStatement pstmt4 = null; String gtinNo = ""; long boxsize = 0;
	 * long caseno = 0; long serialno=0; int status = 0;
	 * 
	 * String sql = " INSERT INTO bottling_unmapped.bwfl( "+
	 * "  date_plan, etin, serial_no_start, serial_no_end, casecode,plan_id,unit_id,fl11gatepass,fl11_date) "
	 * + " VALUES (?, ?, ?, ?, ?, "+dt.getPlan_id()+",?,?,?)";;
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * String update = "UPDATE bwfl_license.mst_bottling_plan_20_21 " +
	 * " SET   finalized_flag='F' ,finalized_date=?" +
	 * "WHERE int_distillery_id=? and int_brand_id=? and int_pack_id=? and plan_dt=? and seq="
	 * +dt.getPlan_id()+"";
	 * 
	 * try { gtinNo = getBrandPackagingGtinNo(action, dt); serialno =
	 * Long.parseLong(dt.getSeqform());
	 * 
	 * System.out.println("ggtin=="+gtinNo);
	 * System.out.println("serial=="+serialno);
	 * 
	 * 
	 * if (!gtinNo.equals("") && serialno != 0) { conn =
	 * ConnectionToDataBase.getConnection20_21(); conn1 =
	 * ConnectionToDataBase.getConnection(); conn.setAutoCommit(false);
	 * conn1.setAutoCommit(false); pstmt1 = conn1.prepareStatement(update);
	 * 
	 * pstmt1.setDate(1, new java.sql.Date(System.currentTimeMillis()));
	 * 
	 * pstmt1.setInt(2, action.getDistillery_id()); pstmt1.setInt(3,
	 * dt.getInt_brand_id()); pstmt1.setInt(4, dt.getInt_pack_id());
	 * pstmt1.setDate(5, Utility.convertUtilDateToSQLDate(dt
	 * .getShowDataTable_Date())); status = pstmt1.executeUpdate();
	 * 
	 * 
	 * 
	 * if (status > 0) { status = 0; pstmt1 = conn.prepareStatement(sql); for
	 * (int i = 0; i < Long.parseLong(dt .getShowDataTable_NoOfBoxes()); i++) {
	 * caseno =Long.parseLong(dt.getCaseseq())+i;
	 * 
	 * 
	 * 
	 * pstmt1.setDate(1, Utility.convertUtilDateToSQLDate(dt
	 * .getShowDataTable_Date())); pstmt1.setString(2, gtinNo);
	 * pstmt1.setString(3, StringUtils.leftPad(String.valueOf(serialno), 8,
	 * '0')); pstmt1.setInt(6, action.getDistillery_id());
	 * 
	 * Random rand1 = new Random(); //int n1 = 10+rand1.nextInt(90) ; int n1=
	 * rand1.nextInt((20 - 10) + 1) + 10;
	 * pstmt1.setString(5,n1+""+StringUtils.leftPad(String.valueOf(caseno), 6,
	 * '0')+getCheckDigit(n1+""+StringUtils.leftPad(String.valueOf(caseno), 6,
	 * '0')) );
	 * 
	 * 
	 * pstmt1.setString(4,StringUtils.leftPad(String.valueOf(serialno+dt.getBox_size
	 * () - 1), 8, '0')); serialno += dt.getBox_size();
	 * 
	 * pstmt1.addBatch(); // status=pstmt1.executeUpdate(); } } int[] status1 =
	 * pstmt1.executeBatch(); if (status1.length > 0) { status = 0; boolean
	 * flag1 = write(dt, action, conn); //boolean flag1 = writeCsv(dt, action,
	 * conn);
	 * 
	 * if ( flag1) { status = 1; } else {
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("Excel Not Generated", "Excel Not Generated")); } } if
	 * (status > 0) {
	 * 
	 * conn.commit(); conn1.commit();
	 * 
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("Finalized SuccessFully", "Finalized SuccessFully")); } else
	 * { conn.rollback(); conn1.rollback();
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
	 * if (pstmt1 != null) pstmt1.close(); if (pstmt2 != null) pstmt2.close();
	 * if (pstmt3 != null) pstmt3.close(); if (pstmt4 != null) pstmt4.close();
	 * if (conn != null) conn.close(); if (conn1 != null) conn1.close();
	 * 
	 * } catch (Exception e) {
	 * FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage(e.getMessage(), e.getMessage())); e.printStackTrace(); } } }
	 * 
	 * public String
	 * getBrandPackagingGtinNo(LiquorVehicleAccidentCaseAECBreweryAction action,
	 * LiquorVehicleAccidentCaseAECBreweryDt dt) {
	 * 
	 * String gtin = ""; String sql =
	 * "select b.code_generate_through from distillery.brand_registration_20_21 a, distillery.packaging_details_20_21 b "
	 * +
	 * "where  a.brand_id=b.brand_id_fk and a.brand_id=? and b.package_id=? and b.quantity=? and vch_license_type=? and a.distillery_id=?"
	 * ;
	 * 
	 * Connection con = null; PreparedStatement pstmt = null; ResultSet rs =
	 * null; System.out.println("brandId" + dt.getInt_brand_id() +
	 * "  package_id" + dt.getInt_pack_id() + " b.quantity " +
	 * Integer.parseInt(dt.getShowDataTable_Quntity()) + " vch_license_type " +
	 * dt.getShowDataTable_LicenceType() + " a.distillery_id " +
	 * action.getDistillery_id());
	 * 
	 * try { con = ConnectionToDataBase.getConnection(); pstmt =
	 * con.prepareStatement(sql); pstmt.setInt(1, dt.getInt_brand_id());
	 * pstmt.setInt(2, dt.getInt_pack_id()); pstmt.setInt(3,
	 * Integer.parseInt(dt.getShowDataTable_Quntity())); pstmt.setString(4,
	 * dt.getShowDataTable_LicenceType()); pstmt.setInt(5,
	 * action.getDistillery_id()); rs = pstmt.executeQuery(); while (rs.next())
	 * { gtin = rs.getString("code_generate_through"); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } finally {
	 * 
	 * try { if (rs != null) rs.close();
	 * 
	 * if (pstmt != null) pstmt.close();
	 * 
	 * if (con != null) con.close();
	 * 
	 * } catch (Exception e) {
	 * FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage(e.getMessage(), e.getMessage())); e.printStackTrace(); } }
	 * 
	 * return gtin;
	 * 
	 * }
	 * 
	 * public static int getCheckDigit(String n) { //int i=0; int sum=0; int
	 * even=0; int odd=0; int checkDigit=0; char ar[]= n.toCharArray(); int
	 * k=ar.length; for (int i = ar.length-1; i >=0; i--) {
	 * 
	 * 
	 * 
	 * if(i%2!=0) { // System.out.println("evennnn"+ar[i]); odd =odd+
	 * Character.getNumericValue(ar[i]); }else{ //
	 * System.out.println("oddddddd"+ar[i]);
	 * even=even+Character.getNumericValue(ar[i]); }
	 * 
	 * }
	 * 
	 * sum=(odd*3)+even;
	 * 
	 * if(sum%10!=0) {
	 * 
	 * //System.out.println("SUMM    "+sum); checkDigit= (10 - sum % 10); //
	 * System.out.println("checkDigit  "+checkDigit); }
	 * 
	 * 
	 * return checkDigit; }
	 * 
	 * 
	 * public boolean write(LiquorVehicleAccidentCaseAECBreweryDt dt,
	 * LiquorVehicleAccidentCaseAECBreweryAction action, Connection conn) {
	 * String sql_fl3_update=
	 * "update bottling_unmapped.bwfl set bottle_code=? where unit_id= ? and plan_id=? and date_plan=? and  etin=? and  casecode=?"
	 * ;
	 * 
	 * String fl3 =
	 * 
	 * "	select to_char(y.gs, 'fm00000000')as GENERATE_SERIES ,y.date_plan as dispatch_date , y.etin as gtin_no,   to_char(y.unit_id,'fm000')as unit_id,                                                                                        "
	 * +
	 * "	to_char(y.serial_no_start::numeric, 'fm00000000') as serial_no_start,to_char(y.serial_no_end::numeric, 'fm00000000') as serial_no_end,                      "
	 * +
	 * "	to_char(y.casecode::numeric , 'fm000000000')as case_no,y.plan_id from(                                                                                                 "
	 * +
	 * "	select  GENERATE_SERIES(x.serial_no_start::numeric ,x.serial_no_end::numeric ) as gs ,x.serial_no_start ,x.serial_no_end,                                   "
	 * +
	 * "	x.casecode,x.etin ,x.date_plan  ,x.unit_id  ,x.plan_id                                                                                                                           "
	 * +
	 * "	from (                                                                                                                                                      "
	 * +
	 * "	SELECT unit_id,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass, fl11_date, fl36gatepass, fl36_date, boxing_seq, remark, serial_no_start, serial_no_end "
	 * +
	 * "	FROM bottling_unmapped.bwfl_20_21 where etin=? and date_plan=? and plan_id="
	 * +dt.getPlan_id()+
	 * ")x)y order by to_char(y.casecode::numeric , 'fm000000000')::numeric";
	 * 
	 * 
	 * 
	 * 
	 * String relativePath = Constants.JBOSS_SERVER_PATH +
	 * Constants.JBOSS_LINX_PATH; FileOutputStream fileOut = null;
	 * 
	 * PreparedStatement pstmt = null; PreparedStatement pstmt1 = null; String
	 * bottle_code=""; int count=1;
	 * 
	 * ResultSet rs = null; long start = 0; long end = 0; boolean flag = false;
	 * long k = 0; String noOfUnit = ""; String date = null;
	 * 
	 * try {
	 * 
	 * 
	 * 
	 * 
	 * System.out.println("excel queryyy==FL3="+fl3); pstmt =
	 * conn.prepareStatement(fl3);
	 * 
	 * 
	 * 
	 * pstmt.setString(1, dt.getCode_generate_through()); pstmt.setDate(2,
	 * Utility.convertUtilDateToSQLDate(dt.getShowDataTable_Date())); rs =
	 * pstmt.executeQuery(); //System.out.println("excecute query fl3");
	 * 
	 * 
	 * 
	 * XSSFWorkbook workbook = new XSSFWorkbook(); XSSFSheet worksheet =
	 * workbook.createSheet("Barcode Report");
	 * 
	 * CellStyle unlockedCellStyle = workbook.createCellStyle();
	 * unlockedCellStyle.setLocked(false);
	 * 
	 * 
	 * worksheet.protectSheet("agristat"); worksheet.setColumnWidth(0, 3000);
	 * worksheet.setColumnWidth(1, 8000); worksheet.setColumnWidth(2, 8000);
	 * worksheet.setColumnWidth(3, 8000); worksheet.setColumnWidth(4, 6000);
	 * 
	 * XSSFRow rowhead0 = worksheet.createRow((int) 0);
	 * 
	 * XSSFCell cellhead0 = rowhead0.createCell((int) 0);
	 * cellhead0.setCellValue("Barcode Report");
	 * 
	 * rowhead0.setHeight((short) 700);
	 * cellhead0.setCellStyle(unlockedCellStyle); XSSFCellStyle cellStyl =
	 * workbook.createCellStyle();
	 * 
	 * 
	 * cellStyl = workbook.createCellStyle(); XSSFFont hSSFFont =
	 * workbook.createFont(); hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
	 * hSSFFont.setFontHeightInPoints((short) 12);
	 * hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	 * hSSFFont.setColor(HSSFColor.GREEN.index); cellStyl.setFont(hSSFFont);
	 * cellhead0.setCellStyle(cellStyl);
	 * 
	 * 
	 * XSSFCellStyle cellStyle = workbook.createCellStyle();
	 * cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
	 * cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	 * 
	 * // -----------------------------
	 * 
	 * XSSFCellStyle unlockcellStyle = workbook.createCellStyle();
	 * unlockcellStyle.setLocked(false);
	 * 
	 * // ---------------------------- XSSFRow rowhead =
	 * worksheet.createRow((int) 1);
	 * 
	 * XSSFCell cellhead1 = rowhead.createCell((int) 0);
	 * cellhead1.setCellValue("S.No.");
	 * 
	 * cellhead1.setCellStyle(cellStyle);
	 * 
	 * XSSFCell cellhead2 = rowhead.createCell((int) 1);
	 * cellhead2.setCellValue("Gtin"); cellhead2.setCellStyle(cellStyle);
	 * 
	 * XSSFCell cellhead3 = rowhead.createCell((int) 2);
	 * cellhead3.setCellValue("FinalizeDate");
	 * cellhead3.setCellStyle(cellStyle);
	 * 
	 * XSSFCell cellhead4 = rowhead.createCell((int) 3);
	 * cellhead4.setCellValue("Case Etn"); cellhead4.setCellStyle(cellStyle);
	 * 
	 * XSSFCell cellhead5 = rowhead.createCell((int) 4);
	 * cellhead5.setCellValue("BottleBarcode");
	 * cellhead5.setCellStyle(cellStyle);
	 * 
	 * 
	 * int i = 0; while (rs.next()) {
	 * 
	 * // System.out.println("i==========="+i++); Date dat =
	 * Utility.convertSqlDateToUtilDate(rs .getDate("dispatch_date"));
	 * 
	 * DateFormat formatter;
	 * 
	 * formatter = new SimpleDateFormat("yyMMdd"); date = formatter.format(dat);
	 * 
	 * //String lic = dt.getLicencenoo().replaceAll("/", "");
	 * 
	 * // System.out.println("while in");serial_no_start, serial_no_end //start
	 * = rs.getLong("serial_no_start"); //end = rs.getLong("serial_no_end");
	 * 
	 * k++; XSSFRow row1 = worksheet.createRow((int) k);
	 * 
	 * XSSFCell cellA1 = row1.createCell((int) 0); cellA1.setCellValue(k);
	 * 
	 * XSSFCell cellB1 = row1.createCell((int) 1);
	 * cellB1.setCellValue(rs.getString("gtin_no"));
	 * 
	 * XSSFCell cellC1 = row1.createCell((int) 2); cellC1.setCellValue(date); //
	 * cellC1.setCellStyle(unlockcellStyle);
	 * 
	 * XSSFCell cellD1 = row1.createCell((int) 3);
	 * noOfUnit=StringUtils.leftPad(String
	 * .valueOf(Integer.parseInt(dt.getShowDataTable_PlanNoOfBottling
	 * ())-dt.getShowDataTable_breakgae() /
	 * Integer.parseInt(dt.getShowDataTable_NoOfBoxes())), 3, '0');
	 * 
	 * 
	 * int inside_ouside_brand=getInsideOutsideBrand(rs.getString("gtin_no"));
	 * if(noOfUnit.length()==2){
	 * 
	 * 
	 * 
	 * cellD1.setCellValue( rs.getString("gtin_no")+""+inside_ouside_brand+""+
	 * StringUtils.leftPad(String.valueOf(rs.getString("unit_id")), 3, '0') +""+
	 * date +""+ "1" +""+ StringUtils.leftPad(String.valueOf(noOfUnit), 3,
	 * '0')+""+ rs.getString("case_no"));
	 * 
	 * } else if(noOfUnit.length()==1) { cellD1.setCellValue(
	 * ""+rs.getString("gtin_no")+""+inside_ouside_brand+""+
	 * StringUtils.leftPad(String.valueOf(rs.getString("unit_id")), 3, '0') +""+
	 * date +""+ "1" +""+ StringUtils.leftPad(String.valueOf(noOfUnit), 3,
	 * '0')+""+ rs.getString("case_no")); }else{
	 * 
	 * cellD1.setCellValue( rs.getString("gtin_no")+""+inside_ouside_brand+""+
	 * StringUtils.leftPad(String.valueOf(rs.getString("unit_id")), 3, '0') +""+
	 * date +""+ "1" +""+
	 * StringUtils.leftPad(String.valueOf(noOfUnit.substring(0,3)), 3, '0')+""+
	 * rs.getString("case_no"));
	 * 
	 * }
	 * 
	 * 
	 * XSSFCell cellE1 = row1.createCell((int) 4);
	 * 
	 * Random rand = new Random(); int n = 10+rand.nextInt(90) ;
	 * 
	 * cellE1.setCellValue(rs.getString("gtin_no") + ""+ date +
	 * StringUtils.leftPad( String.valueOf(rs.getString("unit_id")), 3, '0') +
	 * "" + "" + n + "" + rs.getString("GENERATE_SERIES") + "" +
	 * getCheckDigit(rs.getString("GENERATE_SERIES")));
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * bottle_code=bottle_code+"|"+n + ""+ rs.getString("GENERATE_SERIES") + ""+
	 * getCheckDigit(rs.getString("GENERATE_SERIES"));
	 * 
	 * if (dt.getShowDataTable_LicenceType().equals("FL3")) {
	 * 
	 * pstmt1 = conn.prepareStatement(sql_fl3_update);
	 * 
	 * pstmt1.setString(1, bottle_code); pstmt1.setInt(2,
	 * Integer.parseInt(rs.getString("unit_id")));
	 * pstmt1.setInt(3,rs.getInt("plan_id") );
	 * pstmt1.setDate(4,rs.getDate("dispatch_date") );
	 * pstmt1.setString(5,rs.getString("gtin_no") );
	 * pstmt1.setString(6,rs.getString("case_no") );
	 * 
	 * pstmt1.executeUpdate();
	 * 
	 * }
	 * 
	 * 
	 * 
	 * if(count==(Integer.parseInt(dt.getShowDataTable_PlanNoOfBottling())-dt.
	 * getShowDataTable_breakgae() /
	 * Integer.parseInt(dt.getShowDataTable_NoOfBoxes())))
	 * 
	 * {
	 * 
	 * 
	 * count=0; bottle_code=""; }
	 * 
	 * 
	 * count++;
	 * 
	 * 
	 * } fileOut = new FileOutputStream(relativePath + "//ExciseUp//Excel//"+
	 * dt.getPlan_id() + dt.getCode_generate_through() + "" + date + ".xls");
	 * 
	 * XSSFRow row1 = worksheet.createRow((int) k + 1);
	 * 
	 * // APoolFinancialReportDataTable dt=(APoolFinancialReportDataTable) //
	 * list.get(i-2);
	 * 
	 * XSSFCell cellA1 = row1.createCell((int) 0); cellA1.setCellValue("End");
	 * 
	 * workbook.write(fileOut); fileOut.flush(); fileOut.close(); flag = true;
	 * 
	 * } catch (Exception e) {
	 * 
	 * //System.out.println("xls2" + e.getMessage()); e.printStackTrace(); }
	 * 
	 * return flag; }
	 * 
	 * public int getInsideOutsideBrand(String etin) { int i=0;
	 * 
	 * Connection conn=null; PreparedStatement pstmt=null; ResultSet rs=null;
	 * String sql=
	 * " select domain from distillery.brand_registration_20_21 a,distillery.packaging_details_20_21 b "
	 * + " where b.brand_id_fk=a.brand_id and b.code_generate_through=?";
	 * 
	 * 
	 * try{ conn=ConnectionToDataBase.getConnection();
	 * pstmt=conn.prepareStatement(sql); pstmt.setString(1, etin);
	 * rs=pstmt.executeQuery(); if(rs.next()) {
	 * 
	 * 
	 * if(rs.getString("domain")!=null) {
	 * if(rs.getString("domain").equalsIgnoreCase("EXP")) { i=2; } }else { i=1;
	 * }
	 * 
	 * 
	 * 
	 * }
	 * 
	 * }catch(Exception e) { e.printStackTrace(); }finally{
	 * 
	 * 
	 * try{ if(conn!=null)conn.close(); if(pstmt!=null)pstmt.close();
	 * if(rs!=null)rs.close();
	 * 
	 * }catch(Exception e) { e.printStackTrace(); }
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * return i;
	 * 
	 * }
	 */

	public void finalizedUnmappedData(
			LiquorVehicleAccidentCaseAECBWFLAction action,
			LiquorVehicleAccidentCaseAECBWFLDt dt) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		String update = "UPDATE bwfl_license.mst_bottling_plan_20_21 "
				+ " SET   finalized_flag='F' ,finalized_date=? "
				+ "WHERE int_distillery_id=? and int_brand_id=? and int_pack_id=? and plan_dt=? and seq="
				+ dt.getPlan_id() + "";

		try {

			conn = ConnectionToDataBase.getConnection();

			conn.setAutoCommit(false);

			pstmt1 = conn.prepareStatement(update);

			pstmt1.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			pstmt1.setInt(2, action.getDistillery_id());
			pstmt1.setInt(3, dt.getInt_brand_id());
			pstmt1.setInt(4, dt.getInt_pack_id());
			pstmt1.setDate(5, Utility.convertUtilDateToSQLDate(dt
					.getShowDataTable_Date()));
			int i = pstmt1.executeUpdate();
			if (i > 0) {
				conn.commit();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Finalized Successfully",
								"Finalized Successfully"));
			} else {
				conn.rollback();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Not Finalized", "Not Finalized"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
				if (pstmt1 != null)
					pstmt1.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public boolean recieveUpdate(LiquorVehicleAccidentCaseAECBWFLAction action,
			LiquorVehicleAccidentCaseAECBWFLDt dt) {

		int saveStatus = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String queryList = "";
		boolean flag = false;
		try {

			queryList = " update  bwfl_license.liquor_accidental_case set breakage="
					+ dt.getShowDataTable_breakgae()
					+ "  ,receive_date='"
					+ Utility.convertUtilDateToSQLDate(dt.getReceive_date())
					+ "', "
					+ " receive_flg='F' where  int_brand_id="
					+ dt.getInt_brand_id()
					+ " and int_pack_id="
					+ dt.getInt_pack_id()
					+ " and vch_gatepass_no='"
					+ action.getGatepass_no()
					+ "' and int_bwfl_id='"
					+ action.getDistillery_id() + "'  ";

			System.out.println("====approvedupdate========" + queryList);
			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			saveStatus = pstmt.executeUpdate();

			if (saveStatus > 0) {
				flag = true;

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								" Recieved Successfully !",
								" Recieved Successfully ! "));

			} else {
				// System.out.println("---------- UPDATE NOT ----------");
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Error !!! ", "Error!!!"));

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
		return flag;

	}

	public int maxId(LiquorVehicleAccidentCaseAECBWFLImpl impl) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(seq) as id FROM bwfl_license.gatepass_to_districtwholesale_bwfl_20_21  ";
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
					new FacesMessage(e.getMessage(), e.getMessage()));
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
			}
		}
		return maxid + 1;

	}

	// -------------------------------------------

	public void dataFinalize(LiquorVehicleAccidentCaseAECBWFLAction action,
			LiquorVehicleAccidentCaseAECBWFLDt dt) {
		Connection conn = null;
		Connection conn1 = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		PreparedStatement pstmt7 = null;
		ResultSet rs = null;
		String gtinNo = "";
		long boxsize = 0;
		long caseno = 0;
		int unmaped_status = 0;
		int status5 = 0;
		int status6 = 0;
		int status7 = 0;
		String bottlecode = "";
		int groupId = maxIdgroup();

		String sql = " INSERT INTO bottling_unmapped.bwfl(  "
				+ " date_plan, etin, serial_no_start, serial_no_end, casecode, plan_id, "
				+ " unit_id)" + " VALUES (?, ?, ?, ?, ?, ?, ?)";
		int status = 0;
		long serialno = 0;

		String update = "UPDATE bwfl_license.mst_bottling_plan_20_21 "
				+ " SET   finalized_flag='F' ,finalized_date=?"
				+ "WHERE int_distillery_id=? and int_brand_id=? and int_pack_id=? and plan_dt=? and seq="
				+ dt.getPlan_id() + " and group_id=? ";
		
		String update1=" Update bwfl_license.liquor_accidental_case set dispatch_bottle="+dt.getShowDataTabl_barcode_bottle()+" where " +
			       " int_brand_id=? and int_pack_id=? and vch_gatepass_no='"+action.getGatepass_no()+"' and int_bwfl_id=?";

		/*
		 * "UPDATE bwfl.rollover_bwfl_18_19 " +
		 * " SET   finalized_flag='F' ,finalized_date=?"
		 * 
		 * +
		 * "WHERE int_bwfl_id=? and brand_id=? and package_id=? and plandate=? and planid=? "
		 * ;
		 */
		System.out.println("statussssssupdate" + update);

		try {
			gtinNo = getBrandPackagingGtinNo(action, dt);
			// gtinNo = dt.getGtin_no();

			serialno = Long.parseLong(dt.getSeqform());
			// serialno = getSerialNo(new Double(dt.getBottles()).longValue(),
			// new Date());

			System.out.println("gtinNo" + gtinNo);
			System.out.println("serialno=" + serialno);
			if (!gtinNo.equals("") && serialno != 0) {

				conn = ConnectionToDataBase.getConnection20_21();
				conn1 = ConnectionToDataBase.getConnection();
				conn.setAutoCommit(false);
				conn1.setAutoCommit(false);

				/*
				 * String update1 = "UPDATE bwfl.rollover_bwfl_18_19 " +
				 * " SET   seqfrom=?,seqto=?,caseseq=?, plandate=?, planid=? "
				 * 
				 * +
				 * "WHERE int_bwfl_id='"+action.getUnit_id()+"' and brand_id='"
				 * +dt.getBrand_id()+"' and package_id='"+dt.getPackg_id()+"'  "
				 * +
				 * 
				 * " and license_no='"+dt.getLicence_no()+"' and size='"+dt.getSize
				 * ()+"' and box='"+dt.getS_box()+"' and vch_licence_type='"+dt.
				 * getLicence_type()+"' ";
				 * 
				 * pstmt5 = conn1.prepareStatement(update1);
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * pstmt5.setLong(1, 0); pstmt5.setLong(2, 0);
				 * pstmt5.setLong(3,0); pstmt5.setDate(4,
				 * Utility.convertUtilDateToSQLDate(new Date()));
				 * pstmt5.setInt(5, groupId);
				 * 
				 * status5 = pstmt5.executeUpdate();
				 */
				System.out.println("finalizeDate"
								+ new java.sql.Date(System.currentTimeMillis())
								+ "brandId"
								+ dt.getInt_brand_id()
								+ "  getDistillery_id"
								+ action.getDistillery_id()
								+ " b.getInt_brand_id "
								+ dt.getInt_brand_id()
								+ " getInt_pack_id "
								+ dt.getInt_pack_id()
								+ " a.getShowDataTable_Date "
								+ Utility.convertUtilDateToSQLDate(dt
										.getShowDataTable_Date()) + "groupId"
								+ dt.getGroup_id());

				pstmt1 = conn1.prepareStatement(update);
				pstmt1.setDate(1, new java.sql.Date(System.currentTimeMillis()));

				pstmt1.setInt(2, action.getDistillery_id());
				pstmt1.setInt(3, dt.getInt_brand_id());
				pstmt1.setInt(4, dt.getInt_pack_id());
				pstmt1.setDate(5, Utility.convertUtilDateToSQLDate(dt
						.getShowDataTable_Date()));
				pstmt1.setInt(6, dt.getGroup_id());
				status = pstmt1.executeUpdate();
				/*
				 * pstmt1.setDate(1, new
				 * java.sql.Date(System.currentTimeMillis()));
				 * 
				 * pstmt1.setInt(2, action.getUnit_id()); pstmt1.setInt(3,
				 * dt.getBrand_id()); pstmt1.setInt(4, dt.getPackg_id());
				 * pstmt1.setDate(5, Utility.convertUtilDateToSQLDate(new
				 * Date())); pstmt1.setInt(6, groupId);
				 * 
				 * status = pstmt1.executeUpdate();
				 */
				System.out.println("statussssssssssssssss" + status);
				if(status>0){
					status=0;
					pstmt1 = conn1.prepareStatement(update1);
					
					pstmt1.setInt(1, dt.getInt_brand_id());
					pstmt1.setInt(2, dt.getInt_pack_id());
					pstmt1.setInt(3, action.getDistillery_id());
					status = pstmt1.executeUpdate();
				}

				if (status > 0) {

					status = 0;
					pstmt1 = conn.prepareStatement(sql);
				//	for (int i = 0; i < Long.parseLong(String.valueOf((dt.getShowDataTabl_barcode_bottle()/dt.getShowDataTable_NoOfBoxes()))); i++) 
			//for (int i = 0; i < Long.parseLong(dt.getShowDataTable_NoOfBoxes()); i++) {
				for (int i = 0; i < Long.parseLong(String.valueOf((dt.getShowDataTabl_barcode_bottle()/dt.getBox_size()))); i++){
						caseno = Long.parseLong(dt.getCaseseq()) + i;

						pstmt1.setDate(1, Utility.convertUtilDateToSQLDate(dt
								.getShowDataTable_Date()));
						pstmt1.setString(2, gtinNo);
						pstmt1.setString(3, StringUtils.leftPad(
								String.valueOf(serialno), 8, '0'));
						pstmt1.setInt(6, dt.getGroup_id());
						pstmt1.setInt(7, action.getDistillery_id());

						Random rand1 = new Random();
						// int n1 = 10+rand1.nextInt(90) ;
						int n1 = rand1.nextInt((20 - 10) + 1) + 10;
						pstmt1.setString(
								5,
								n1
										+ ""
										+ StringUtils.leftPad(
												String.valueOf(caseno), 6, '0')
										+ getCheckDigit(n1
												+ ""
												+ StringUtils.leftPad(
														String.valueOf(caseno),
														6, '0')));
					//	pstmt1.setString(4,	 StringUtils.leftPad(String.valueOf(serialno+dt.getBox_size()- 1), 8, '0'));
						/*pstmt1.setString(
								4,
								StringUtils.leftPad(
										String.valueOf(serialno+ (dt.getShowDataTabl_barcode_bottle()/Integer.parseInt(dt.getShowDataTable_NoOfBoxes())) - 1), 8, '0'));
					*/
						pstmt1.setString(4,StringUtils.leftPad(String.valueOf(serialno+dt.getBox_size() - 1), 8, '0'));
						serialno += dt.getBox_size();
						//serialno += (dt.getShowDataTabl_barcode_bottle()/Integer.parseInt(dt.getShowDataTable_NoOfBoxes()));

						pstmt1.addBatch();
					 status=pstmt1.executeUpdate();
					 System.out.println("st Long.parseLong(dt" + status);
					}

					// -----------------------

				}

				if (status > 0) {
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

				/*
				 * if (status6 > 0) { String update12 =
				 * " INSERT INTO bwfl_license.mst_bottling_plan_20_21( int_distillery_id, int_brand_id, int_pack_id, plan_dt, "
				 * +
				 * "  group_id,finalized_flag,finalized_date,int_planned_bottles, int_boxes, int_liquor_type, vch_license_type,  int_quantity, "
				 * + " licence_no, permitdt,permitno ) "+
				 * " values('"+action.getUnit_id
				 * ()+"','"+dt.getBrand_id()+"','"+dt
				 * .getPackg_id()+"','"+Utility.convertUtilDateToSQLDate(new
				 * Date())+"' ," +
				 * " '"+groupId+"','F', '"+Utility.convertUtilDateToSQLDate(new
				 * Date
				 * ())+"','"+dt.getBottles()+"','"+dt.getS_box()+"','1','"+dt
				 * .getLicence_type()+"','"+dt.getQuantity_ml()+"', " +
				 * " '"+dt.getLicence_no
				 * ()+"','"+Utility.convertUtilDateToSQLDate(new
				 * Date())+"', 'Rollover-bwfl.rollover_bwfl_18_19' )  ";
				 * 
				 * System.out.println("----insert ======"+update12); pstmt7 =
				 * conn1.prepareStatement(update12); status7 =
				 * pstmt7.executeUpdate();
				 * System.out.println("===status7---last===="+status7); }
				 */

				// if (status7 > 0) {
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

		} catch (Exception e) {
			e.printStackTrace();
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

	public boolean write(LiquorVehicleAccidentCaseAECBWFLDt dt,
			LiquorVehicleAccidentCaseAECBWFLAction action, Connection conn
			) {

		// //System.out.println("excel innn");
		String sql_bwfl_update = "update bottling_unmapped.bwfl set bottle_code=? where unit_id= ? and plan_id=? and date_plan=? and  etin=? and  casecode=?";
		String bwfl = ""
				+

				"	select to_char(y.gs, 'fm00000000')as GENERATE_SERIES , y.dispatch_date,y.gtin_no, y.unit_id ,               "
				+ "	y.serial_no_start, y.serial_no_end, "
				+ "	to_char(y.case_no::numeric , 'fm000000000')as case_no,y.plan_id from( "
				+ "	select  x.unit_id,GENERATE_SERIES(x.serial_no_start::numeric ,x.serial_no_end::numeric ) as gs ,x.serial_no_start ,x.serial_no_end, "
				+ "	x.case_no,x.dispatch_date,x.gtin_no,x.plan_id from ( "
				+ "	SELECT  plan_id,unit_id,date_plan as dispatch_date, etin as gtin_no, serial_no_start, serial_no_end, casecode as case_no "
				+ "	FROM bottling_unmapped.bwfl a where   date_plan=?   and etin=? and plan_id=?)x)y";

		String relativePath = Constants.JBOSS_SERVER_PATH
				+ Constants.JBOSS_LINX_PATH;
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
			pstmt.setDate(1, Utility.convertUtilDateToSQLDate(dt
					.getShowDataTable_Date()));
			pstmt.setString(2, dt.getCode_generate_through());

			/*
			 * pstmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			 * pstmt.setString(2, dt.getGtin_no());
			 */
			pstmt.setInt(3, dt.getGroup_id());
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

				Date dat = Utility.convertSqlDateToUtilDate(rs
						.getDate("dispatch_date"));

				DateFormat formatter;

				formatter = new SimpleDateFormat("yyMMdd");
				date = formatter.format(dat);

				// String lic = dt.getLicence_no().replaceAll("/", "");

				// //System.out.println("while in");serial_no_start,
				// serial_no_end
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
				noOfUnit = StringUtils.leftPad(String.valueOf(Integer
						.parseInt(dt.getShowDataTable_PlanNoOfBottling())
						- dt.getShowDataTable_breakgae()
						/ Integer.parseInt(dt.getShowDataTable_NoOfBoxes())),
						3, '0');
				// noOfUnit =
				// StringUtils.leftPad(String.valueOf(dt.getBottles()/
				// dt.getS_box()), 3, '0');

				if (noOfUnit.length() == 2) {

					cellD1.setCellValue(rs.getString("gtin_no")
							+ ""
							+ 1
							+ ""
							+ StringUtils.leftPad(
									String.valueOf(rs.getString("unit_id")), 3,
									'0')
							+ ""
							+ date
							+ ""
							+ "1"
							+ ""
							+ StringUtils.leftPad(String.valueOf(noOfUnit), 3,
									'0') + "" + rs.getString("case_no"));

				} else if (noOfUnit.length() == 1) {
					cellD1.setCellValue(""
							+ rs.getString("gtin_no")
							+ ""
							+ 1
							+ ""
							+ StringUtils.leftPad(
									String.valueOf(rs.getString("unit_id")), 3,
									'0')
							+ ""
							+ date
							+ ""
							+ "1"
							+ ""
							+ StringUtils.leftPad(String.valueOf(noOfUnit), 3,
									'0') + "" + rs.getString("case_no"));
				} else {

					cellD1.setCellValue(rs.getString("gtin_no")
							+ ""
							+ 1
							+ ""
							+ StringUtils.leftPad(
									String.valueOf(rs.getString("unit_id")), 3,
									'0')
							+ ""
							+ date
							+ ""
							+ "1"
							+ ""
							+ StringUtils.leftPad(
									String.valueOf(noOfUnit.substring(0, 3)),
									3, '0') + "" + rs.getString("case_no"));

				}

				XSSFCell cellE1 = row1.createCell((int) 4);

				Random rand = new Random();
				int n = 10 + rand.nextInt(90);

				cellE1.setCellValue(rs.getString("gtin_no")
						+ ""
						+ date
						+ StringUtils.leftPad(
								String.valueOf(rs.getString("unit_id")), 3, '0')
						+ "" + "" + n + "" + rs.getString("GENERATE_SERIES")
						+ "" + getCheckDigit(rs.getString("GENERATE_SERIES")));

				bottle_code = bottle_code + "|" + n + ""
						+ rs.getString("GENERATE_SERIES") + ""
						+ getCheckDigit(rs.getString("GENERATE_SERIES"));

				pstmt1 = conn.prepareStatement(sql_bwfl_update);
				// System.out.println("bottle_code "+bottle_code);
				// System.out.println("unit id
				// "+String.valueOf(Integer.parseInt(rs.getString("unit_id"))));
				// System.out.println("unit id "+rs.getInt("plan_id"));
				// System.out.println("unit id
				// "+Utility.convertUtilDateToSQLDate(rs.getDate("dispatch_date")
				// ));
				// System.out.println("unit id "+rs.getString("gtin_no") );
				// System.out.println("unit id "+rs.getString("case_no") );
				pstmt1.setString(1, bottle_code);
				pstmt1.setInt(2, Integer.parseInt(rs.getString("unit_id")));
				pstmt1.setInt(3, rs.getInt("plan_id"));
				pstmt1.setDate(4, Utility.convertUtilDateToSQLDate(rs
						.getDate("dispatch_date")));
				pstmt1.setString(5, rs.getString("gtin_no"));
				pstmt1.setString(6, rs.getString("case_no"));
				pstmt1.executeUpdate();

				// if (count == (dt.getBottles()/ dt.getS_box()))
				if (count == (Integer.parseInt(dt
						.getShowDataTable_PlanNoOfBottling()) - dt
						.getShowDataTable_breakgae()
						/ Integer.parseInt(dt.getShowDataTable_NoOfBoxes())))

				{

					System.out.println("come inn nn time ");
					count = 0;
					bottle_code = "";
				}

				count++;

			}
			fileOut = new FileOutputStream(relativePath + "//ExciseUp//Excel//"
					+ dt.getPlan_id() + dt.getCode_generate_through() + ""
					+ date + ".xls");
			// fileOut = new FileOutputStream(relativePath +
			// "//ExciseUp//RolloverStock//Excel//" + dt.getGtin_no() + date +
			// groupId + ".xls");

			XSSFRow row1 = worksheet.createRow((int) k + 1);

			XSSFCell cellA1 = row1.createCell((int) 0);
			cellA1.setCellValue("End");

			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			flag = true;

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), e.getMessage()));

			// //System.out.println("xls2" + e.getMessage());
			e.printStackTrace();
		}

		return flag;
	}

	/*
	 * public boolean updateExcelPath(LiquorVehicleAccidentCaseAECBWFLDt
	 * dt,String path,Connection conn1)
	 * 
	 * { boolean flag=false; //Connection conn=null; PreparedStatement
	 * pstmt=null; String sql=
	 * 
	 * 
	 * "UPDATE fl2d.rollover_stock_for_fl2d_non_renew_brand                      "+
	 * "SET   excel_path=?                                                 "+
	 * "WHERE etin=? and  etin_new=? and brand_id=? and package_id=?       "+
	 * "and rollover_bottles=? and rollover_boxes=? and unit_id=?          ";
	 * 
	 * try{ //conn=ConnectionToDataBase.getConnection();
	 * pstmt=conn1.prepareStatement(sql); pstmt.setString(1, path);
	 * pstmt.setString(2, dt.getOld_etin()); pstmt.setString(3,
	 * dt.getNew_etin()); pstmt.setInt(4,dt.getBrand_id());
	 * pstmt.setInt(5,dt.getPackage_id());
	 * pstmt.setInt(6,dt.getRolloverbottles()); pstmt.setInt(7,
	 * dt.getRolloverbox()); pstmt.setInt(8, Integer.parseInt(dt.getUnit_id()));
	 * int i=pstmt.executeUpdate(); System.out.println("UPDATE EXCEL PATH");
	 * if(i>0) { System.out.println("update patttt"); flag=true; }
	 * System.out.println("UPDATE EXCEL PATH1111"); }catch(Exception e) {
	 * e.printStackTrace(); }finally{
	 * 
	 * try{ //if(conn!=null)conn.close(); if(pstmt!=null)pstmt.close();
	 * 
	 * 
	 * }catch(Exception e) { e.printStackTrace(); } } return flag;
	 * 
	 * }
	 */

	public static int getCheckDigit(String n) {
		// int i=0;
		int sum = 0;
		int even = 0;
		int odd = 0;
		int checkDigit = 2;
		char ar[] = n.toCharArray();
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
		}

		return checkDigit;
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
			pstmt2 = conn
					.prepareStatement("CREATE SEQUENCE IF NOT EXISTS public.bwfl_manual_case_code_"
							+ currentdate);
			pstmt2.executeUpdate();

			pstmt = conn
					.prepareStatement(" select     nextval('public.bwfl_manual_case_code_"
							+ currentdate + "')");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				serialNo = rs.getLong(1);
				if (serialNo == 0) {
					serialNo = serialNo;

				}
				/*
				 * pstmt = conn
				 * .prepareStatement("ALTER SEQUENCE public.bwfl_manual_case_code_"
				 * +currentdate+" RESTART WITH " + (no + serialNo));
				 * 
				 * 
				 * pstmt.executeUpdate();
				 */
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), e.getMessage()));

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
			pstmt2 = conn
					.prepareStatement("CREATE SEQUENCE IF NOT EXISTS public.serial_seq_"
							+ currentdate);
			pstmt2.executeUpdate();

			pstmt = conn
					.prepareStatement("select     nextval('public.serial_seq_"
							+ currentdate + "')");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				serialNo = rs.getInt(1);
				if (serialNo == 0) {
					serialNo = serialNo + 1;
				}
				// //System.out.println("noOfSequenc " + noOfSequenc);

				pstmt1 = conn
						.prepareStatement("ALTER SEQUENCE public.serial_seq_"
								+ currentdate + " RESTART WITH "
								+ (noOfSequenc + serialNo + 1));

				pstmt1.executeUpdate();

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), e.getMessage()));

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

	public int maxIdgroup() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;

		String query = " SELECT max(group_id)+1 as id FROM bwfl_license.mst_bottling_plan_20_21 ";

		int maxid = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			System.out.println("query==" + query);
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
		System.out.println("maxid==" + maxid);
		return maxid;

	}

	public String getBrandPackagingGtinNo(
			LiquorVehicleAccidentCaseAECBWFLAction action,
			LiquorVehicleAccidentCaseAECBWFLDt dt) {

		String gtin = "";
		String sql = "select b.code_generate_through from distillery.brand_registration_20_21 a, distillery.packaging_details_20_21 b "
				+ "where  a.brand_id=b.brand_id_fk and a.brand_id=? and b.package_id=? and b.quantity=? and vch_license_type=? and a.distillery_id=?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("brandId" + dt.getInt_brand_id() + "  package_id"
				+ dt.getInt_pack_id() + " b.quantity "
				+ Integer.parseInt(dt.getShowDataTable_Quntity())
				+ " vch_license_type " + dt.getShowDataTable_LicenceType()
				+ " a.distillery_id " + action.getDistillery_id());

		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dt.getInt_brand_id());
			pstmt.setInt(2, dt.getInt_pack_id());
			pstmt.setInt(3, Integer.parseInt(dt.getShowDataTable_Quntity()));
			pstmt.setString(4, dt.getShowDataTable_LicenceType());
			pstmt.setInt(5, action.getDistillery_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				gtin = rs.getString("code_generate_through");
			}

		} catch (Exception e) {
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
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			}
		}

		return gtin;

	}

	// -----------------------------------------

}
