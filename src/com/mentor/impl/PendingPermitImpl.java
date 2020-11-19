package com.mentor.impl;

import java.io.File;
import java.io.StringReader;
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
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParserFactory;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.mentor.action.PendingPermitAction;
import com.mentor.datatable.PendingPermitDT;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class PendingPermitImpl {

	// ------------------display applications in datatable----------------------

	public ArrayList displayRegUsersImpl(PendingPermitAction act) {

		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int j = 1;
		String selQr = null;
		String filter = "";

		try {
			con = ConnectionToDataBase.getConnection();

			if (ResourceUtil.getUserNameAllReq().trim().substring(0, 10)
					.equalsIgnoreCase("Excise-DEO")) {
				if (act.getRadioType().equalsIgnoreCase("N")) {
					filter = " AND a.deo_date IS NULL AND a.vch_approved IS NULL ";

				} else if (act.getRadioType().equalsIgnoreCase("A")) {
					filter = " AND a.deo_date IS NOT NULL AND a.vch_approved='APPROVED'  ";

				} else if (act.getRadioType().equalsIgnoreCase("R")) {
					filter = " AND a.deo_date IS NOT NULL AND a.vch_approved='REJECTED' ";

				}

			} else {
				filter = " AND a.id=0 ";
			}

			selQr = " SELECT coalesce(a.control_id,'NA') as control_id,coalesce(a.service_id,'NA') as service_id,coalesce(a.request_id,'NA') as request_id ,coalesce(a.unit_id_niv,'NA') as unit_id_niv,a.id, a.district_id, a.bwfl_id, a.unit_id, a.bwfl_type, a.import_fee, a.duty, a.add_duty, a.login_type,         "
					+ " a.special_fee, a.cr_date, a.vch_approved, a.vch_status, a.deo_time, a.deo_remark, a.deo_user, a.deo_date,              "
					+ " a.lic_no,  a.import_fee_challan_no, a.spcl_fee_challan_no, a.app_id,    "
					+ " CASE WHEN a.bwfl_type=1 THEN 'BWFL2A'                                                                                  "
					+ " WHEN a.bwfl_type=2 THEN 'BWFL2B'                                                                                       "
					+ " WHEN a.bwfl_type=3 THEN 'BWFL2C'                                                                                       "
					+ " WHEN a.bwfl_type=4 THEN 'BWFL2D'                                                                                       "
					+ " WHEN a.bwfl_type=99 THEN 'FL2D' end as type,                                                                           "
					+ " CASE WHEN a.login_type='FL2D' THEN                                                                                     "
					+ " (SELECT d.vch_firm_name FROM licence.fl2_2b_2d_20_21 d WHERE a.bwfl_id=d.int_app_id)                                   "
					+ " WHEN a.login_type='BWFL' THEN                                                                                          "
					+ " (SELECT d.vch_firm_name FROM bwfl.registration_of_bwfl_lic_holder_20_21 d WHERE a.bwfl_id=d.int_id)                    "
					+ " end as vch_firm_name, a.consignor_nm_adrs,                                                                                                 "
					+ " c.description, a.bottlng_type, a.valid_upto, a.route_road_radio, a.route_detail,                                       "
					+ " e.vch_indus_name, e.vch_reg_office_add, a.digital_sign_time, a.digital_sign_date,                                                                               "
					+ " a.print_permit_dt, COALESCE(a.permit_nmbr,'NA')permit_nmbr, COALESCE(a.digital_sign_pdf_name,'NA')digital_sign_pdf_name,                                                           "
					+ " (SELECT d.vch_state_name FROM public.state_ind d WHERE e.vch_reg_office_state=d.int_state_id::text)as vch_state_name,  "
					+ " (SELECT SUM(COALESCE(dtl.no_of_cases,0)) FROM bwfl_license.import_permit_dtl_20_21 dtl                                       "
					+ " WHERE a.id=dtl.fk_id AND a.app_id=dtl.app_id AND a.login_type=dtl.login_type AND a.district_id=dtl.district_id) as total_cases_detail                                                                            "
					+ " FROM bwfl_license.import_permit_20_21 a, public.district c, public.other_unit_registration_20_21 e                                 "
					+ " WHERE a.district_id=c.districtid AND a.unit_id=e.int_app_id_f  and CASE WHEN a.bwfl_type=1 THEN vch_indus_type='OUPD' "
					+ "  WHEN a.bwfl_type=2  THEN vch_indus_type='OUPB'                                                                                      \r\n"
					+ " WHEN a.bwfl_type=3  THEN vch_indus_type='OUPW'                                                                                      \r\n"
					+ "  WHEN a.bwfl_type=4  THEN vch_indus_type='OUPBU'                                                                                       \r\n"
					+ " WHEN a.bwfl_type=99  THEN vch_indus_type='IU'  end                                                        "
					+ " AND c.deo='"
					+ ResourceUtil.getUserNameAllReq().trim()
					+ "' "
					+ filter
					+ "  and a.login_type='BWFL'                                                   "
					+ " ORDER BY a.app_id  ";

			ps = con.prepareStatement(selQr);

			rs = ps.executeQuery();

			while (rs.next()) {

				PendingPermitDT dt = new PendingPermitDT();

				dt.setSrNo(j);
				dt.setControl_id(rs.getString("control_id"));
				dt.setUnit_id(rs.getString("unit_id_niv"));
				dt.setService_id(rs.getString("service_id"));
				dt.setRequest_id(rs.getString("request_id"));
				dt.setAppId_dt(rs.getInt("app_id"));
				dt.setRequestID_dt(rs.getInt("id"));
				dt.setBwflID_dt(rs.getInt("bwfl_id"));
				dt.setUnitID_dt(rs.getInt("unit_id"));
				dt.setLicenseType_dt(rs.getString("type"));
				dt.setLicenseNmbr_dt(rs.getString("lic_no"));
				dt.setDistrictId_dt(rs.getInt("district_id"));
				dt.setDistrictName_dt(rs.getString("description"));
				dt.setBwflName_dt(rs.getString("vch_firm_name"));
				dt.setParentUnitNm_dt(rs.getString("vch_indus_name"));
				dt.setParentUnitAdrs_dt(rs.getString("vch_reg_office_add"));
				dt.setParentUnitState_dt(rs.getString("vch_state_name"));
				dt.setTotalBoxes_dt(rs.getInt("total_cases_detail"));
				dt.setMapped_unmapped_dt(rs.getString("bottlng_type"));
				dt.setByRoad_byRoute_dt(rs.getString("route_road_radio"));
				dt.setRouteDetail_dt(rs.getString("route_detail"));
				dt.setPermitDate_dt(rs.getDate("print_permit_dt"));
				dt.setPermitNmbr_dt(rs.getString("permit_nmbr"));
				dt.setLoginType_dt(rs.getString("login_type"));
				dt.setCrDate_dt(rs.getDate("cr_date"));
				dt.setApprovalDate_dt(rs.getDate("deo_date"));
				dt.setApprovalTym_dt(rs.getString("deo_time"));
				dt.setApprovalUser_dt(rs.getString("deo_user"));
				dt.setImportFeeChalanNo_dt(rs
						.getString("import_fee_challan_no"));
				dt.setSpclFeeChalanNo_dt(rs.getString("spcl_fee_challan_no"));
				dt.setDigitalSignPdfNm_dt(rs.getString("digital_sign_pdf_name"));
				dt.setDigitalSignPdfDate_dt(rs.getDate("digital_sign_date"));
				dt.setConsignorNmAdrs_dt(rs.getString("consignor_nm_adrs"));

				SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
				if (rs.getDate("cr_date") != null) {
					String appDate = date.format(Utility
							.convertSqlDateToUtilDate(rs.getDate("cr_date")));
					dt.setAppDate_dt(appDate);
				} else {
					dt.setAppDate_dt("");
				}

				if (rs.getDate("valid_upto") != null) {
					String validDate = date
							.format(Utility.convertSqlDateToUtilDate(rs
									.getDate("valid_upto")));
					dt.setValidUptoDt_dt(validDate);
				} else {
					dt.setValidUptoDt_dt("Not Filled");
				}

if (rs.getDate("valid_upto") != null) {
					
					SimpleDateFormat date1 = new SimpleDateFormat("dd/MM/yyyy");
					String validDate = date1.format(Utility.convertSqlDateToUtilDate(rs.getDate("valid_upto")));
					dt.setExpiry_date(validDate);
				} else {
					dt.setExpiry_date("");
				}
				
				
				
				if (rs.getString("vch_status") != null
						&& rs.getString("vch_status").length() > 0) {
					dt.setStatus_dt(rs.getString("vch_status"));
				} else {
					dt.setStatus_dt("Pending");
				}

				if (rs.getString("deo_remark") != null
						&& rs.getString("deo_remark").length() > 0) {
					dt.setDeoRemark_dt(rs.getString("deo_remark")
							+ " [ REMARK DATE : " + rs.getDate("deo_date")
							+ " ] ");
				} else {
					dt.setDeoRemark_dt("No Remark");
				}

				j++;
				list.add(dt);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
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

	// ======================get max id
	// bwfl_license.import_permit_duty=============================

	public int getMaxIdPermit() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;

		query = " SELECT max(seq) as id  FROM bwfl_license.import_permit_duty ";

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

	public int maxIdMstBotlngPlan() {

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

	public int maxIdMstStockRcv() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = " SELECT max(seq) as id FROM fl2d.mst_stock_receive_20_21 ";
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

	public int getRejctedSeq() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;

		query = " SELECT max(seq) as id  FROM bwfl_license.rejected_permit_after_approval_20_21 ";

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

	// ====================approve application=========================

	public String approveApplicationImpl(PendingPermitAction act) {

		int saveStatus = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String queryList = "";
		String updtQr = "";
		String insQr = "";
		int seq = this.getMaxIdPermit();
		int bottlngPlanId = maxIdMstBotlngPlan();
		int maxStockRcv = maxIdMstStockRcv();
		String permitNmbr = act.getDistrictName().trim().replaceAll("\\s+", "")
				+ "_" + act.getAppId() + "_" + act.getLicenseNmbr();

		try {
			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String time = sdf.format(cal.getTime());

			queryList = " INSERT INTO bwfl_license.import_permit_duty(seq, permit_nmbr, bwfl_id, duty, type) "
					+ " VALUES (?, ?, ?, ?, ?) ";

			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, seq);
			pstmt.setString(2, permitNmbr.trim());
			pstmt.setInt(3, act.getBwflId());
			pstmt.setDouble(4, act.getTotalDuty() + act.getTotalAddDuty());
			pstmt.setString(5, act.getLoginType());

			saveStatus = pstmt.executeUpdate();

			System.out.println("saveStatus==" + saveStatus);

			if (saveStatus > 0) {
				saveStatus = 0;

				updtQr = " UPDATE bwfl_license.import_permit_20_21 "
						+ " SET deo_time=?, deo_date=?, deo_remark=?,   "
						+ " deo_user=?, vch_approved=?, vch_status=?, permit_nmbr=?, valid_upto=?  "
						+ " WHERE id=? AND district_id=? AND login_type=? AND app_id=?  ";

				pstmt = con.prepareStatement(updtQr);

				pstmt.setString(1, time);
				pstmt.setDate(2, Utility.convertUtilDateToSQLDate(new Date()));
				pstmt.setString(3, act.getFillRemrks());
				pstmt.setString(4, ResourceUtil.getUserNameAllReq().trim());
				pstmt.setString(5, "APPROVED");
				pstmt.setString(6, "Approved By DEO");
				pstmt.setString(7, permitNmbr.trim());
				pstmt.setDate(8,
						Utility.convertUtilDateToSQLDate(act.getFillValidDt()));
				pstmt.setInt(9, act.getRequestID());
				pstmt.setInt(10, act.getDistrictId());
				pstmt.setString(11, act.getLoginType());
				pstmt.setInt(12, act.getAppId());

				saveStatus = pstmt.executeUpdate();
				System.out.println("saveStatus==1111==" + saveStatus);

			}

			if (saveStatus > 0) {
				for (int i = 0; i < act.getDisplayBrandDetails().size(); i++) {

					PendingPermitDT dt = (PendingPermitDT) act
							.getDisplayBrandDetails().get(i);

					saveStatus = 0;

					if (act.getLoginType().equalsIgnoreCase("BWFL")) {

						insQr = " INSERT INTO bwfl_license.mst_bottling_plan_20_21( "
								+ " int_distillery_id, int_brand_id, int_pack_id, int_quantity, int_planned_bottles, int_boxes,  "
								+ " int_liquor_type, vch_license_type, plan_dt, licence_no, cr_date,  received_liqour, permitno, permitdt, "
								+ " seq, gatepass_no, maped_unmaped_type, etin,  validity_date, permitno_entered,  entry_no_of_bottle_per_case, "
								+ " seqfrom, seqto, caseseq, districtid, permit_fee, unit_id) "
								+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

						pstmt = con.prepareStatement(insQr);

						pstmt.setInt(1, act.getBwflId());
						pstmt.setInt(2, dt.getBrandId_dt());
						pstmt.setInt(3, dt.getPckgID_dt());
						pstmt.setInt(4, dt.getQuantity_dt());
						pstmt.setInt(5, dt.getNmbrOfBtl_dt());
						pstmt.setInt(6, dt.getNmbrOfBox_dt());
						if (act.getLicenseType().equalsIgnoreCase("BWFL2A")) {
							pstmt.setInt(7, 1);
						} else if (act.getLicenseType().equalsIgnoreCase(
								"BWFL2B")) {
							pstmt.setInt(7, 2);
						} else if (act.getLicenseType().equalsIgnoreCase(
								"BWFL2C")) {
							pstmt.setInt(7, 3);
						} else if (act.getLicenseType().equalsIgnoreCase(
								"BWFL2D")) {
							pstmt.setInt(7, 4);
						}

						pstmt.setString(8, act.getLicenseType());
						pstmt.setDate(9,
								Utility.convertUtilDateToSQLDate(new Date()));
						pstmt.setString(10, act.getLicenseNmbr());
						pstmt.setDate(11,
								Utility.convertUtilDateToSQLDate(new Date()));
						pstmt.setInt(12, 0);
						pstmt.setString(13, permitNmbr.trim());
						pstmt.setDate(14,
								Utility.convertUtilDateToSQLDate(new Date()));
						pstmt.setInt(15, bottlngPlanId);
						pstmt.setString(16, String.valueOf(bottlngPlanId));
						pstmt.setString(17, act.getMapped_unmapped());
						pstmt.setString(18, dt.getEtinNo_dt());
						pstmt.setDate(19, Utility.convertUtilDateToSQLDate(act
								.getFillValidDt()));
						pstmt.setString(20, permitNmbr.trim());
						pstmt.setString(21, String.valueOf(dt.getSize_dt()));
						pstmt.setInt(22, 0);
						pstmt.setInt(23, 0);
						pstmt.setInt(24, 0);
						pstmt.setInt(25, act.getDistrictId());
						pstmt.setDouble(26, dt.getImportFees_dt());
						pstmt.setInt(27, act.getUnitID());

						saveStatus = pstmt.executeUpdate();

						System.out.println("saveStatus==22====" + saveStatus);

					}

					else if (act.getLoginType().equalsIgnoreCase("FL2D")) {

						insQr = " INSERT INTO fl2d.mst_stock_receive_20_21( int_fl2d_id, int_brand_id, "
								+ " int_pack_id, int_quantity, int_planned_bottles, int_boxes, "
								+ " int_liquor_type, vch_license_type, plan_dt, licence_no, cr_date, seq, permit_no, "
								+ " loginusr, district_id, seqfrom, seqto, caseseq, permit_fee, box_size,cbw_id) "
								+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

						pstmt = con.prepareStatement(insQr);

						pstmt.setInt(1, act.getBwflId());
						pstmt.setInt(2, dt.getBrandId_dt());
						pstmt.setInt(3, dt.getPckgID_dt());
						pstmt.setInt(4, dt.getQuantity_dt());
						pstmt.setInt(5, dt.getNmbrOfBtl_dt());
						pstmt.setInt(6, dt.getNmbrOfBox_dt());
						pstmt.setInt(7, dt.getLiquorCatgry_dt());
						pstmt.setString(8, act.getLicenseType());
						pstmt.setDate(9,
								Utility.convertUtilDateToSQLDate(new Date()));
						pstmt.setString(10, act.getLicenseNmbr());
						pstmt.setDate(11,
								Utility.convertUtilDateToSQLDate(new Date()));
						pstmt.setInt(12, maxStockRcv);
						pstmt.setString(13, permitNmbr.trim());
						pstmt.setString(14, ResourceUtil.getUserNameAllReq()
								.trim());
						pstmt.setInt(15, act.getDistrictId());
						pstmt.setInt(16, 0);
						pstmt.setInt(17, 0);
						pstmt.setInt(18, 0);
						pstmt.setDouble(19, dt.getImportFees_dt());
						pstmt.setInt(20, dt.getSize_dt());
						pstmt.setInt(21, act.getCustom_id());

						saveStatus = pstmt.executeUpdate();
						System.out.println("saveStatus==33=====" + saveStatus);
					}

				}

			}

			if (saveStatus > 0) {

				if (!act.getControl_id().equals("NA")
						&& act.getControl_id() != null) {
					String status = returnParameter(
							act.getControl_id(),
							act.getUnit_id(),
							act.getService_id(),
							act.getAppId() + "",
							act.getAppId() + "",
							"06",
							"  Approved By : "
									+ ResourceUtil.getUserNameAllReq()
									+ " Remarks Is " + act.getDeoRemrks(),
							act.getRequest_id(), "Pending For Certificate/NOC Issued at "
									+ ResourceUtil.getUserNameAllReq() ,
							act.getObjectionRejectionCode());

					if (status.equals("SUCCESS")) {

						con.commit();
						act.setPopupPermitNmbr(act.getAppId()
								+ " and Permit No. is " + permitNmbr);
						act.closeApplication();
						act.setShowMainPanel_flg(true);
					} else {
						FacesContext
								.getCurrentInstance()
								.addMessage(
										null,
										new FacesMessage(
												FacesMessage.SEVERITY_INFO,
												" NiveshMitra Status Return Failed   !!! ",
												"NiveshMitra Status Return Failed !!!"));
					}

				} else {

					con.commit();
					act.setPopupPermitNmbr(act.getAppId()
							+ " and Permit No. is " + permitNmbr);
					act.closeApplication();
					act.setShowMainPanel_flg(true);

				}
				/*
				 * FacesContext.getCurrentInstance().addMessage(null,new
				 * FacesMessage(FacesMessage.SEVERITY_INFO,
				 * " Application Approved Successfully. Your Application Id is "
				 * + act.getAppId()+" and Permit No. is "+ permitNmbr+" !!! ",
				 * " Application Approved Successfully. Your Application Id is "
				 * + act.getAppId()+" and Permit No. is "+ permitNmbr+" !!! "));
				 */

			} else {
				act.setShowMainPanel_flg(false);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Error !!! ", "Error!!!"));

				con.rollback();

			}
		} catch (Exception se) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(se.getMessage(), se.getMessage()));
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
		return "";

	}

	// ================reject application====================

	public String rejectApplicationImpl(PendingPermitAction act) {

		int saveStatus = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String updtQr = "";

		try {
			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String time = sdf.format(cal.getTime());

			updtQr = " UPDATE bwfl_license.import_permit_20_21 "
					+ " SET deo_time=?, deo_date=?, deo_remark=?,   "
					+ " deo_user=?, vch_approved=?, vch_status=?, import_fee_challan_no=?, spcl_fee_challan_no=?  "
					+ " WHERE id=? AND district_id=? AND login_type=? AND app_id=?  ";

			pstmt = con.prepareStatement(updtQr);

			pstmt.setString(1, time);
			pstmt.setDate(2, Utility.convertUtilDateToSQLDate(new Date()));
			pstmt.setString(3, act.getFillRemrks());
			pstmt.setString(4, ResourceUtil.getUserNameAllReq().trim());
			pstmt.setString(5, "REJECTED");
			pstmt.setString(6, "Rejected By DEO");
			pstmt.setString(7, null);
			pstmt.setString(8, null);
			pstmt.setInt(9, act.getRequestID());
			pstmt.setInt(10, act.getDistrictId());
			pstmt.setString(11, act.getLoginType());
			pstmt.setInt(12, act.getAppId());

			saveStatus = pstmt.executeUpdate();

			System.out.println("status1------only----- " + saveStatus);

			if (saveStatus > 0) {

				if (!act.getControl_id().equals("NA")
						&& act.getControl_id() != null) {
					String status = this.returnParameter(
							act.getControl_id(),
							act.getUnit_id(),
							act.getService_id(),
							act.getAppId() + "",
							act.getAppId() + "",
							"07",
							"  Rejected By : "
									+ ResourceUtil.getUserNameAllReq()
									+ " || Reason is: "
									+ this.getreasion(act
											.getObjectionRejectionCode())
									+ "|| Remarks is: " + act.getFillRemrks(),
							act.getRequest_id(), "No Pendency Because Of Application Rejected ", act
									.getObjectionRejectionCode());

					if (status.equals("SUCCESS")) {
						con.commit();
						act.closeApplication();
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_INFO,
										" Application Rejected   !!! ",
										"Application Rejected !!!"));

					} else {
						FacesContext
								.getCurrentInstance()
								.addMessage(
										null,
										new FacesMessage(
												FacesMessage.SEVERITY_INFO,
												" NiveshMitra Status Return Failed   !!! ",
												"NiveshMitra Status Return Failed !!!"));
					}
				} else {
					con.commit();
					act.closeApplication();
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									" Application Rejected   !!! ",
									"Application Rejected !!!"));
				}

			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Error !!! ", "Error!!!"));

				con.rollback();

			}
		} catch (Exception se) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(se.getMessage(), se.getMessage()));
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
		return "";

	}

	// ====================reject application after
	// approval=========================

	public String rejectApprovedApplication(PendingPermitAction act) {

		int saveStatus = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String updtQr = "";
		String delQr = "";
		String insQr = "";
		int seq = getRejctedSeq();

		try {
			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String time = sdf.format(cal.getTime());
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			updtQr = " UPDATE bwfl_license.import_permit_20_21 "
					+ " SET deo_time=?, deo_date=?, deo_remark=?,   "
					+ " deo_user=?, vch_approved=?, vch_status=?, import_fee_challan_no=?, spcl_fee_challan_no=?  "
					+ " WHERE id=? AND district_id=? AND login_type=? AND app_id=?  ";

			pstmt = con.prepareStatement(updtQr);

			pstmt.setString(1, time);
			pstmt.setDate(2, Utility.convertUtilDateToSQLDate(new Date()));
			pstmt.setString(3, act.getFillRemrks());
			pstmt.setString(4, ResourceUtil.getUserNameAllReq().trim());
			pstmt.setString(5, "REJECTED");
			pstmt.setString(6, "Rejected By DEO after Approval");
			pstmt.setString(7, null);
			pstmt.setString(8, null);
			pstmt.setInt(9, act.getRequestID());
			pstmt.setInt(10, act.getDistrictId());
			pstmt.setString(11, act.getLoginType());
			pstmt.setInt(12, act.getAppId());

			saveStatus = pstmt.executeUpdate();

			System.out.println("status1------rejectttt----- " + saveStatus);

			if (saveStatus > 0) {

				insQr = " INSERT INTO bwfl_license.rejected_permit_after_approval_20_21( "
						+ " seq, district_id, bwfl_id, bwfl_type, cr_date, approval_time, approval_remark, "
						+ " approval_user, approval_date, lic_no, permit_nmbr, login_type, reject_dt_time, reject_remark, app_id_fk) "
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);  ";

				pstmt = con.prepareStatement(insQr);

				pstmt.setInt(1, seq);
				pstmt.setInt(2, act.getDistrictId());
				pstmt.setInt(3, act.getBwflId());
				pstmt.setString(4, act.getLicenseType());
				pstmt.setDate(5, Utility.convertUtilDateToSQLDate(new Date()));
				pstmt.setString(6, act.getApprovalTym());
				pstmt.setString(7, act.getDeoRemrks());
				pstmt.setString(8, act.getApprovalUser());
				pstmt.setDate(9,
						Utility.convertUtilDateToSQLDate(act.getApprovalDate()));
				pstmt.setString(10, act.getLicenseNmbr());
				pstmt.setString(11, act.getPermitNmbr());
				pstmt.setString(12, act.getLoginType());
				pstmt.setString(13, dateFormat.format(new Date()));
				pstmt.setString(14, act.getFillRemrks());
				pstmt.setInt(15, act.getAppId());

				saveStatus = pstmt.executeUpdate();

				System.out.println("status2------rejectttt----- " + saveStatus);
			}

			if (saveStatus > 0) {
				for (int i = 0; i < act.getDisplayBrandDetails().size(); i++) {

					PendingPermitDT dt = (PendingPermitDT) act
							.getDisplayBrandDetails().get(i);

					saveStatus = 0;

					if (act.getLoginType().equalsIgnoreCase("BWFL")) {

						delQr = " DELETE FROM bwfl_license.mst_bottling_plan_20_21  "
								+ " WHERE int_distillery_id=? AND int_brand_id=? AND int_pack_id=? AND plan_dt=? "
								+ " AND permitno=?  AND (finalized_flag!='F' or finalized_flag IS NULL)  ";

						pstmt = con.prepareStatement(delQr);

						pstmt.setInt(1, act.getBwflId());
						pstmt.setInt(2, dt.getBrandId_dt());
						pstmt.setInt(3, dt.getPckgID_dt());
						pstmt.setDate(4, Utility.convertUtilDateToSQLDate(act
								.getApprovalDate()));
						pstmt.setString(5, act.getPermitNmbr());

						saveStatus = pstmt.executeUpdate();

					} else if (act.getLoginType().equalsIgnoreCase("FL2D")) {

						delQr = " DELETE FROM fl2d.mst_stock_receive_20_21  "
								+ " WHERE int_fl2d_id=? AND int_brand_id=? AND int_pack_id=? AND plan_dt=? "
								+ " AND permit_no=?  AND (finalized_flag!='F' or finalized_flag IS NULL)  ";

						pstmt = con.prepareStatement(delQr);

						pstmt.setInt(1, act.getBwflId());
						pstmt.setInt(2, dt.getBrandId_dt());
						pstmt.setInt(3, dt.getPckgID_dt());
						pstmt.setDate(4, Utility.convertUtilDateToSQLDate(act
								.getApprovalDate()));
						pstmt.setString(5, act.getPermitNmbr());

						saveStatus = pstmt.executeUpdate();

					}

					System.out.println("status3------rejectttt----- "
							+ saveStatus);

				}
			}

			if (saveStatus > 0) {

				if (!act.getControl_id().equals("NA")) {
					String status = this.returnParameter(
							act.getControl_id(),
							act.getUnit_id(),
							act.getService_id(),
							act.getAppId() + "",
							act.getAppId() + "",
							"07",
							"  Rejected By : "
									+ ResourceUtil.getUserNameAllReq()
									+ " || Reason is: "
									+ this.getreasion(act
											.getObjectionRejectionCode())
									+ "|| Remarks is: " + act.getDeoRemrks(),
							act.getRequest_id(), "No Pendency Because Of Application Rejected ", act
									.getObjectionRejectionCode());
					if (status.equals("SUCCESS")) {

						con.commit();
						act.closeApplication();
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_INFO,
										" Application Rejected   !!! ",
										"Application Rejected !!!"));
					} else {
						FacesContext
								.getCurrentInstance()
								.addMessage(
										null,
										new FacesMessage(
												FacesMessage.SEVERITY_INFO,
												" Nivesh Mitra Apllication  Status Submit Failed   !!! ",
												"Nivesh Mitra Apllication  Status Submit Failed !!!"));
					}
				} else {
					con.commit();
					act.closeApplication();
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									" Application Rejected   !!! ",
									"Application Rejected !!!"));
				}

			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Error !!! ", "Error!!!"));

				con.rollback();

			}
		} catch (Exception se) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(se.getMessage(), se.getMessage()));
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
		return "";

	}

	// ------------------display brand details in
	// datatable----------------------

	public ArrayList displayBrandDetailsImpl(PendingPermitAction act) {

		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int j = 1;
		String selQr = null;
		String filter = "";

		try {
			con = ConnectionToDataBase.getConnection();

			selQr = " SELECT distinct a.fk_id, a.district_id, a.brand_id, a.pckg_id, a.etin,a.scanning_fee, a.no_of_cases, a.no_of_bottle_per_case,         "
					+ " a.pland_no_of_bottles, a.import_fee, (a.duty+a.add_duty)as duty, a.add_duty, a.special_fee, a.cr_date,        "
					+ " b.import_fee as tot_import_fee, (b.duty+b.add_duty) as tot_duty, "
					+ " b.add_duty as tot_adduty, b.special_fee as tot_spcl_fee, "
					+ " br.brand_name, br.liquor_category, pk.package_name, pk.quantity  "
					+ " FROM bwfl_license.import_permit_dtl_20_21 a, bwfl_license.import_permit_20_21 b,                                          "
					+ " distillery.brand_registration_20_21 br, distillery.packaging_details_20_21 pk                                 "
					+ " WHERE a.fk_id=b.id AND a.district_id=b.district_id AND a.login_type=b.login_type AND a.app_id=b.app_id        "
					+ " AND br.brand_id=a.brand_id and br.brand_id=pk.brand_id_fk and a.pckg_id=pk.package_id                         "
					+ " AND a.app_id="
					+ act.getAppId()
					+ "   "
					+ " ORDER BY a.fk_id ";

			ps = con.prepareStatement(selQr);
			System.out.println("brand query---------------" + selQr);
			rs = ps.executeQuery();

			while (rs.next()) {

				PendingPermitDT dt = new PendingPermitDT();

				dt.setSrNo(j);

				dt.setBrandId_dt(rs.getInt("brand_id"));
				dt.setBrandName_dt(rs.getString("brand_name"));
				dt.setSize_dt(rs.getInt("no_of_bottle_per_case"));
				dt.setEtinNo_dt(rs.getString("etin"));
				dt.setPckgID_dt(rs.getInt("pckg_id"));
				dt.setPckgType_dt(rs.getString("package_name"));
				dt.setNmbrOfBox_dt(rs.getInt("no_of_cases"));
				dt.setNmbrOfBtl_dt(rs.getInt("pland_no_of_bottles"));
				dt.setDuty_dt(rs.getDouble("duty"));
				dt.setAddDuty_dt(rs.getDouble("add_duty"));
				dt.setImportFees_dt(rs.getDouble("import_fee"));
				dt.setSpecialfees_dt(rs.getDouble("special_fee"));
				dt.setQuantity_dt(rs.getInt("quantity"));
				dt.setLiquorCatgry_dt(rs.getInt("liquor_category"));
				dt.setScanning_fee(rs.getDouble("scanning_fee"));

				act.setTotalDuty(act.getTotalDuty() + rs.getDouble("tot_duty"));
				act.setTotalAddDuty(act.getTotalAddDuty()
						+ rs.getDouble("tot_adduty"));
				act.setTotalImportFee(act.getTotalImportFee()
						+ rs.getDouble("import_fee"));
				act.setTotalSpecialFee(rs.getDouble("tot_spcl_fee"));
				act.setTotal_scanning_fee(act.getTotal_scanning_fee()
						+ rs.getDouble("scanning_fee"));

				j++;
				list.add(dt);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
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

	public boolean printReportBWFL(PendingPermitAction act, PendingPermitDT dt) {

		String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
		String relativePath = mypath + File.separator + "ExciseUp"
				+ File.separator + "Bond" + File.separator + "jasper";
		String relativePathpdf = mypath + File.separator + "ExciseUp"
				+ File.separator + "Bond" + File.separator + "BWFLpermit";
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
					+ " round(CAST(float8(((d.pland_no_of_bottles)*pk.quantity)/1000) as numeric), 2) as bl ,                                   "
					+ " CASE WHEN pk.package_type='1' THEN 'Glass Bottle' "
					+ " WHEN pk.package_type='2' THEN 'CAN' "
					+ " WHEN pk.package_type='3' THEN 'Pet Bottle' "
					+ " WHEN pk.package_type='4' THEN 'Tetra Pack' "
					+ " WHEN pk.package_type='5' THEN 'Sachet' "
					+ " WHEN pk.package_type='6' THEN 'Keg' end as liqour_type "
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
					+ " AND a.app_id=" + dt.getAppId_dt() + " ";

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
				 * parameters.put("balImportFee", act.getBalRgstrImportFee()); }
				 * else if(dt.getLoginType_dt().equalsIgnoreCase("FL2D")){
				 * parameters.put("balImportFee", act.getBalFL2DImportFee()); }
				 */

				JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);
				if (dt.getLicenseType_dt().equalsIgnoreCase("BWFL2B")) {

					jasperReport = (JasperReport) JRLoader
							.loadObject(relativePath + File.separator
									+ "BWFLImportPermitBwfl2B.jasper");

				} else {
					jasperReport = (JasperReport) JRLoader
							.loadObject(relativePath + File.separator
									+ "BWFLImportPermit.jasper");
				}
				JasperPrint print = JasperFillManager.fillReport(jasperReport,
						parameters, jrRs);

				Random rand = new Random();
				int n = rand.nextInt(250) + 1;

				JasperExportManager.exportReportToPdfFile(
						print,
						relativePathpdf
								+ File.separator
								+ dt.getPermitNmbr_dt().trim()
										.replaceAll("\\s+", "") + ".pdf");

				// dt.setPdfName(dt.getAppId_dt()+
				// "-"+dt.getBwflID_dt()+"_"+dt.getLoginType_dt()+"ImportPermit.pdf");
				dt.setPdfName(dt.getPermitNmbr_dt() + ".pdf");
				this.updatePermit(act, dt.getRequestID_dt(),
						dt.getDistrictId_dt(), dt.getLoginType_dt(),
						dt.getAppId_dt(), dt.getPdfName());
				printFlag = true;
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("No Data Found", "No Data Found"));
				printFlag = false;

			}
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
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

	// =============================print report============================

	public boolean printReportFL2D(PendingPermitAction act, PendingPermitDT dt) {

		String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
		String relativePath = mypath + File.separator + "ExciseUp"
				+ File.separator + "Bond" + File.separator + "jasper";
		String relativePathpdf = mypath + File.separator + "ExciseUp"
				+ File.separator + "Bond" + File.separator + "FL2Dpermit";
		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		String reportQuery = null;
		boolean printFlag = false;

		try {
			con = ConnectionToDataBase.getConnection();

			reportQuery = " SELECT DISTINCT a.import_fee_challan_no, a.spcl_fee_challan_no,                                          "
					+ "(select DISTINCT amount from bwfl_license.chalan_deposit_bwfl_fl2d                                            "
					+ " where chalan_no=a.import_fee_challan_no and unit_id=a.bwfl_id and                                            "
					+ " unit_type=CASE WHEN a.bwfl_type=1 then 'BWFL2A' WHEN a.bwfl_type=2 THEN 'BWFL2B'                             "
					+ " WHEN a.bwfl_type=3 THEN 'BWFL2C' WHEN a.bwfl_type=4 THEN 'BWFL2D' WHEN a.bwfl_type=99                        "
					+ " THEN 'FL2D' END) as import_chalan_amnt,                                                                      "
					+ " (select DISTINCT amount from bwfl_license.chalan_deposit_bwfl_fl2d                                           "
					+ "  where chalan_no=a.spcl_fee_challan_no and unit_id=a.bwfl_id and                                             "
					+ "  unit_type=CASE WHEN a.bwfl_type=1 then 'BWFL2A' WHEN a.bwfl_type=2 THEN 'BWFL2B'                            "
					+ "  WHEN a.bwfl_type=3 THEN 'BWFL2C' WHEN a.bwfl_type=4 THEN 'BWFL2D' WHEN a.bwfl_type=99 THEN                  "
					+ "  'FL2D' END) as spcl_chalan_amnt,                                                                            "
					+ "  (select DISTINCT chalan_date from   bwfl_license.chalan_deposit_bwfl_fl2d                                   "
					+ "   where chalan_no=a.import_fee_challan_no and unit_id=a.bwfl_id and                                          "
					+ "   unit_type=CASE WHEN a.bwfl_type=1 then 'BWFL2A' WHEN a.bwfl_type=2 THEN 'BWFL2B'                           "
					+ "   WHEN a.bwfl_type=3 THEN 'BWFL2C' WHEN a.bwfl_type=4 THEN 'BWFL2D' WHEN a.bwfl_type=99                      "
					+ "   THEN 'FL2D' END) as import_chalan_date,                                                                    "
					+ "   (select DISTINCT chalan_date from  bwfl_license.chalan_deposit_bwfl_fl2d                                   "
					+ "	where chalan_no=a.spcl_fee_challan_no and unit_id=a.bwfl_id and                                            "
					+ "	unit_type=CASE WHEN a.bwfl_type=1 then 'BWFL2A' WHEN a.bwfl_type=2 THEN 'BWFL2B'                           "
					+ "	WHEN a.bwfl_type=3 THEN 'BWFL2C' WHEN a.bwfl_type=4 THEN 'BWFL2D' WHEN a.bwfl_type=99                      "
					+ "	THEN 'FL2D' END) as spcl_chalan_date,    a.id, a.district_id, a.bwfl_id, a.unit_id,                        "
					+ "	a.bwfl_type, a.lic_no, a.import_fee as tot_import_fee,                                                     "
					+ "	(a.duty+a.add_duty)as tot_duty, a.special_fee as tot_special_fee, a.cr_date,                               "
					+ "	a.vch_approved, a.vch_status, a.deo_time, a.deo_remark, a.deo_user, a.deo_date,                            "
					+ "	a.bottlng_type, a.valid_upto, a.route_road_radio, a.route_detail,                                          "
					+ "	CASE WHEN a.bwfl_type=1 THEN 'BWFL2A'                                                                      "
					+ "	WHEN a.bwfl_type=2 THEN 'BWFL2B'                                                                           "
					+ "	WHEN a.bwfl_type=3 THEN 'BWFL2C'                                                                           "
					+ "	WHEN a.bwfl_type=4 THEN 'BWFL2D'                                                                           "
					+ "	WHEN a.bwfl_type=99 THEN 'FL2D' end as type,                                                               "
					+ "	CASE WHEN a.route_road_radio='route' THEN 'By Train'                                                       "
					+ "	WHEN a.route_road_radio='road' THEN 'By Road' end as route_type,                                           "
					+ "	b.vch_firm_name,  b.vch_bond_address as vch_core_address, c.description,                                   "
					+ "	CONCAT(b.vch_firm_name, '(' ,b.vch_bond_address,')')as licensee_nm_adrs,                                   "
					+ "	(SELECT DISTINCT d.vch_state_name FROM public.state_ind d WHERE                                            "
					+ "	 f.int_state_id=d.int_state_id)as vch_state_name,                                                          "
					+ "	                                                                                                           "
					+ "	  d.no_of_cases, d.no_of_bottle_per_case, d.pland_no_of_bottles, a.permit_nmbr,                            "
					+ "	  d.import_fee,                               (d.duty+d.add_duty) as duty,                                 "
					+ "	  d.special_fee, a.import_fee_challan_no, a.spcl_fee_challan_no,                                           "
					+ "	  f.vch_applicant_name as vch_indus_name, f.vch_bond_address as vch_reg_office_add, a.consignor_nm_adrs,   "
					+ "	  d.brand_id, d.pckg_id, br.brand_name,                                                                    "
					+ "	  pk.package_name, (pk.permit) as duty_rate,                                                               "
					+ "	  round(CAST(float8(((d.pland_no_of_bottles)*pk.quantity)/1000) as numeric), 2) as bl                      "
					+ "	  FROM bwfl_license.import_permit_20_21 a, custom_bonds.custom_bonds_master b,                             "
					+ "	  public.district c, bwfl_license.import_permit_dtl_20_21 d,                                               "
					+ "	  custom_bonds.mst_regis_importing_unit f,                                                                 "
					+ "	  distillery.brand_registration_20_21 br, distillery.packaging_details_20_21 pk                            "
					+ "	  WHERE a.custom_id=b.int_id AND a.district_id=c.districtid                                                "
					+ "	  AND a.id=d.fk_id AND a.district_id=d.district_id AND a.login_type=d.login_type                           "
					+ "	  AND a.app_id=d.app_id  AND a.unit_id=f.int_id  and  a.custom_id=b.int_id                                 "
					+ "	  AND br.brand_id=d.brand_id and br.brand_id=pk.brand_id_fk and d.pckg_id=pk.package_id                    "
					+ "	  AND a.app_id=" + dt.getAppId_dt() + " ";

			System.out
					.println("reportQuery---------------------" + reportQuery);
			pst = con.prepareStatement(reportQuery);
			rs = pst.executeQuery();

			if (rs.next()) {
				rs = pst.executeQuery();

				Map parameters = new HashMap();
				parameters.put("REPORT_CONNECTION", con);
				// parameters.put("SUBREPORT_DIR", relativePath+File.separator);
				parameters.put("image", relativePath + File.separator);

				JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);

				jasperReport = (JasperReport) JRLoader.loadObject(relativePath
						+ File.separator + "FL2DImportPermit.jasper");
				JasperPrint print = JasperFillManager.fillReport(jasperReport,
						parameters, jrRs);

				Random rand = new Random();
				int n = rand.nextInt(250) + 1;

				JasperExportManager.exportReportToPdfFile(
						print,
						relativePathpdf
								+ File.separator
								+ dt.getPermitNmbr_dt().trim()
										.replaceAll("\\s+", "") + ".pdf");

				// dt.setPdfName(dt.getAppId_dt()+
				// "-"+dt.getBwflID_dt()+"_"+dt.getLoginType_dt()+"ImportPermit.pdf");
				dt.setPdfName(dt.getPermitNmbr_dt() + ".pdf");
				this.updatePermit(act, dt.getRequestID_dt(),
						dt.getDistrictId_dt(), dt.getLoginType_dt(),
						dt.getAppId_dt(), dt.getPdfName());
				printFlag = true;
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("No Data Found", "No Data Found"));
				printFlag = false;

			}
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
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

	public String updatePermit(PendingPermitAction act, int id, int districtId,
			String loginType, int appId, String pdfName) {

		int saveStatus = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String updtQr = "";

		try {
			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String time = sdf.format(cal.getTime());

			updtQr = " UPDATE bwfl_license.import_permit_20_21 "
					+ " SET print_permit_time=?, print_permit_dt=?, print_permit_pdf=? "
					+ " WHERE id=" + id + " AND district_id=" + districtId
					+ "  " + " AND login_type='" + loginType + "' AND app_id="
					+ appId + "  ";

			pstmt = con.prepareStatement(updtQr);

			// System.out.println("updtQr----------------"+updtQr);

			pstmt.setString(1, time);
			pstmt.setDate(2, Utility.convertUtilDateToSQLDate(new Date()));
			pstmt.setString(3, pdfName);

			saveStatus = pstmt.executeUpdate();

			if (saveStatus > 0) {
				con.commit();
				// act.closeApplication();

			} else {
				con.rollback();

			}
		} catch (Exception se) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(se.getMessage(), se.getMessage()));
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
		return "";

	}

	// ------------------display challan details in
	// datatable----------------------

	public ArrayList displayChalanDetailsImpl(PendingPermitAction act) {

		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int j = 1;
		String selQr = null;
		String filter = "";

		try {
			con = ConnectionToDataBase.getConnection();

			selQr = " SELECT DISTINCT a.int_id, b.id, a.unit_id, a.unit_type, a.amount,                                                "
					+ " a.chalan_no, a.chalan_date, a.division, a.tres_id,                                                               "
					+ " CASE WHEN a.chalan_no=b.import_fee_challan_no THEN CONCAT('IMPORT FEE Challan Number- ',b.import_fee_challan_no)  "
					+ " WHEN a.chalan_no=b.spcl_fee_challan_no THEN CONCAT('SPECIAL FEE Challan Number- ',b.spcl_fee_challan_no) "
					+
					// "WHEN a.chalan_no=b.scanning_fee_challan_no THEN CONCAT('Scanning FEE Challan Number- ',b.scanning_fee_challan_no)         "+
					" END AS challan_nmbr, c.divname, d.tname                                                                          "
					+ " FROM bwfl_license.chalan_deposit_bwfl_fl2d a, bwfl_license.import_permit_20_21 b,                                      "
					+ " licence.division c, licence.treasury d                                                                           "
					+ " WHERE a.unit_id=b.bwfl_id AND a.unit_type=                                                                       "
					+ " CASE WHEN b.bwfl_type=1 then 'BWFL2A' WHEN b.bwfl_type=2 THEN 'BWFL2B'                                           "
					+ " WHEN b.bwfl_type=3 THEN 'BWFL2C' WHEN b.bwfl_type=4 THEN 'BWFL2D' WHEN b.bwfl_type=99 THEN 'FL2D' END            "
					+ " AND a.division=c.divcode AND a.tres_id=d.tcode AND c.divcode=d.divcode                                           "
					+ " AND b.app_id="
					+ act.getAppId()
					+ "   "
					+ " AND a.chalan_no in ('"
					+ act.getImportFeeChalanNo()
					+ "', '" + act.getSpclFeeChalanNo() + "')  ";

			ps = con.prepareStatement(selQr);
			System.out.println("challan query---------------" + selQr);
			rs = ps.executeQuery();

			while (rs.next()) {

				PendingPermitDT dt = new PendingPermitDT();

				dt.setSrNo(j);
				dt.setChallanId_dt(rs.getInt("int_id"));
				dt.setChallanNo_dt(rs.getString("challan_nmbr"));
				dt.setChallanAmnt_dt(rs.getDouble("amount"));
				dt.setDivName_dt(rs.getString("divname"));
				dt.setTreasryNm_dt(rs.getString("tname"));

				SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
				if (rs.getDate("chalan_date") != null) {
					String chalanDate = date
							.format(Utility.convertSqlDateToUtilDate(rs
									.getDate("chalan_date")));
					dt.setChallanDate_dt(chalanDate);
				} else {
					dt.setChallanDate_dt("");
				}

				j++;
				list.add(dt);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
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

	public String getAdvancRgstrBalance(PendingPermitAction act) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			String selQr = " SELECT x.bwfl_id,                                                                                                    "
					+ " SUM(x.bwfl_import_fee-(x.ap_import_fee+x.import_fee))as bwfl_import_fee_bal,                                         "
					+ " SUM(x.bwfl_special_fee-(x.ap_special_fee+x.special_fee))as bwfl_special_fee_bal,                                     "
					+ " SUM(x.fl2d_import_fee-(x.ap_import_fee+x.import_fee))as fl2d_import_fee_bal,                                         "
					+ " SUM(x.fl2d_special_fee-(x.ap_special_fee+x.special_fee))as fl2d_special_fee_bal                                      "
					+ " FROM                                                                                                                 "
					+ " (SELECT a.int_distillery_id as bwfl_id, SUM(b.double_amt) as bwfl_import_fee, 0 as bwfl_special_fee,                 "
					+ " 0 as fl2d_import_fee, 0 as fl2d_special_fee,                                                                         "
					+ " 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee                                           "
					+ " FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b                                                "
					+ " WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type                                    "
					+ " AND a.int_distillery_id='"
					+ act.getBwflId()
					+ "'                                                                        "
					+ " AND b.vch_head_code in ('003900103030000','003900105040000','003900103030000') and b.vch_g6_head_code in ('01','03') "
					+ " AND a.date_challan_date > '2019-06-16'                                                                               "
					+ " group by bwfl_id                                                                                                     "
					+ " UNION                                                                                                                "
					+ " SELECT a.int_distillery_id as bwfl_id, 0 as bwfl_import_fee, SUM(b.double_amt) as bwfl_special_fee,                  "
					+ " 0 as fl2d_import_fee, 0 as fl2d_special_fee,                                                                         "
					+ " 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee                                           "
					+ " FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b                                                "
					+ " WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type                                    "
					+ " AND a.int_distillery_id='"
					+ act.getBwflId()
					+ "'                                                                        "
					+ " AND b.vch_head_code in ('003900105020000','003900103020000') and b.vch_g6_head_code in ('18','13')                   "
					+ " AND a.date_challan_date > '2019-06-16'                                                                               "
					+ " GROUP BY bwfl_id                                                                                                     "
					+ " UNION                                                                                                                "
					+ " SELECT a.int_distillery_id as bwfl_id, 0 as bwfl_import_fee, 0 as bwfl_special_fee,                                  "
					+ " SUM(b.double_amt) as fl2d_import_fee, 0 as fl2d_special_fee,                                                         "
					+ " 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee                                           "
					+ " FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b                                                "
					+ " WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type                                    "
					+ " AND a.int_distillery_id='"
					+ act.getBwflId()
					+ "'                                                                        "
					+ " AND b.vch_head_code in ('003900105040000','003900103030000','003900103030000') and b.vch_g6_head_code in ('04','05') "
					+ " AND a.date_challan_date > '2019-06-16'                                                                               "
					+ " GROUP BY bwfl_id                                                                                                     "
					+ " UNION                                                                                                                "
					+ " SELECT a.int_distillery_id as bwfl_id, 0 as bwfl_import_fee, 0 as bwfl_special_fee,                                  "
					+ " 0 as fl2d_import_fee, SUM(b.double_amt) as fl2d_special_fee,                                                         "
					+ " 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee                                           "
					+ " FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b                                                "
					+ " WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type                                    "
					+ " AND a.int_distillery_id='"
					+ act.getBwflId()
					+ "'                                                                        "
					+ " AND b.vch_head_code in ('003900105020000','003900103020000') and b.vch_g6_head_code in ('18','13')                   "
					+ " AND a.date_challan_date > '2019-06-16'                                                                               "
					+ " GROUP BY bwfl_id                                                                                                     "
					+ " UNION                                                                                                                "
					+ " SELECT a.bwfl_id, 0 as bwfl_import_fee, 0 as bwfl_special_fee,0 as fl2d_import_fee, 0 as fl2d_special_fee,           "
					+ " SUM(a.import_fee) as ap_import_fee, SUM(COALESCE(a.special_fee,0)) as ap_special_fee,                                "
					+ " 0 as import_fee, 0 as special_fee                                                                                    "
					+ " FROM bwfl_license.import_permit_20_21 a                                                                                    "
					+ " WHERE a.bwfl_id='"
					+ act.getBwflId()
					+ "' AND a.vch_approved='APPROVED'                                                  "
					+ " GROUP BY a.bwfl_id                                                                                                   "
					+ " UNION                                                                                                                "
					+ " SELECT a.bwfl_id, 0 as bwfl_import_fee, 0 as bwfl_special_fee,0 as fl2d_import_fee, 0 as fl2d_special_fee,           "
					+ " 0 as ap_import_fee, 0 as ap_special_fee,                                                                             "
					+ " SUM(a.import_fee) as import_fee, SUM(a.special_fee) as special_fee                                                   "
					+ " FROM bwfl_license.import_permit_20_21 a WHERE a.id='"
					+ act.getRequestID()
					+ "'                                                "
					+ " GROUP BY a.bwfl_id)x GROUP BY x.bwfl_id  ";

			/*
			 * String selQr =
			 * " SELECT x.bwfl_id, SUM(x.challan_duty-(x.ap_duty+x.duty))as bal_duty,                                 "
			 * +
			 * " SUM(x.challan_import_fee-(x.ap_import_fee+x.import_fee))as bal_import_fees,                          "
			 * +
			 * " SUM(x.challan_special_fee-(x.ap_special_fee+x.special_fee))as bal_special_fees                       "
			 * +
			 * " FROM                                                                                                 "
			 * +
			 * " (SELECT a.int_distillery_id as bwfl_id, 0 as ap_import_fee, 0 as ap_duty,                            "
			 * +
			 * " 0 as ap_special_fee, 0 as import_fee, 0 as duty, 0 as special_fee,                                   "
			 * +
			 * " CASE                                                                                                 "
			 * +
			 * " WHEN b.vch_head_code='003900105010000' and 'BWFL2A'=a.vch_bwfl_shop_type THEN SUM(b.double_amt)      "
			 * +
			 * " WHEN b.vch_head_code='003900105010000' and 'BWFL2C'=a.vch_bwfl_shop_type THEN SUM(b.double_amt)      "
			 * +
			 * " WHEN b.vch_head_code='003900103010000' and 'BWFL2B'=a.vch_bwfl_shop_type THEN SUM(b.double_amt)      "
			 * +
			 * " WHEN b.vch_head_code='003900103010000' and 'BWFL2D'=a.vch_bwfl_shop_type                             "
			 * +
			 * " THEN SUM(b.double_amt) end as challan_duty,                                                          "
			 * +
			 * " CASE WHEN b.vch_head_code='003900105040000' and a.vch_bwfl_shop_type in ('BWFL2A','BWFL2C')          "
			 * +
			 * " THEN SUM(b.double_amt)                                                                               "
			 * +
			 * " WHEN b.vch_head_code='003900103030000' and a.vch_bwfl_shop_type in ('BWFL2B','BWFL2D')               "
			 * +
			 * " THEN SUM(b.double_amt) end as challan_import_fee,                                                    "
			 * +
			 * " CASE WHEN b.vch_head_code='003900102010000' and a.vch_bwfl_shop_type in ('BWFL2A','BWFL2C')          "
			 * +
			 * " THEN SUM(b.double_amt)                                                                               "
			 * +
			 * " WHEN b.vch_head_code='003900103030000' and a.vch_bwfl_shop_type in ('BWFL2B','BWFL2D')               "
			 * +
			 * " THEN SUM(b.double_amt) end as challan_special_fee                                                    "
			 * +
			 * " FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b                                "
			 * +
			 * " WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type                    "
			 * + " AND a.int_distillery_id='"+act.getBwflId()+
			 * "'                                                        "+
			 * " GROUP BY a.int_distillery_id, b.vch_head_code, a.vch_bwfl_shop_type                                  "
			 * +
			 * " UNION                                                                                                "
			 * +
			 * " SELECT a.bwfl_id, SUM(a.import_fee) as ap_import_fee , SUM(a.duty+a.add_duty) as ap_duty ,           "
			 * +
			 * " SUM(a.special_fee) as ap_special_fee, 0 as import_fee, 0 as duty, 0 as special_fee,                  "
			 * +
			 * " 0 as challan_duty, 0 as challan_import_fee, 0 as challan_special_fee                                 "
			 * +
			 * " FROM bwfl_license.import_permit a                                                                    "
			 * + " WHERE a.bwfl_id='"+act.getBwflId()+
			 * "' AND a.vch_approved='APPROVED'                                  "
			 * +
			 * " GROUP BY a.bwfl_id                                                                                   "
			 * +
			 * " UNION                                                                                                "
			 * +
			 * " SELECT a.bwfl_id, 0 as ap_import_fee, 0 as ap_duty, 0 as ap_special_fee,                             "
			 * +
			 * " SUM(a.import_fee) as import_fee , SUM(a.duty+a.add_duty) as duty, SUM(a.special_fee) as special_fee, "
			 * +
			 * " 0 as challan_duty, 0 as challan_import_fee, 0 as challan_special_fee                                 "
			 * +
			 * " FROM bwfl_license.import_permit a WHERE a.id='"+act.getRequestID
			 * ()+"'                                "+
			 * " GROUP BY a.bwfl_id)x GROUP BY x.bwfl_id  ";
			 */

			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();

			System.out.println("==============bal recive==========" + selQr);

			while (rs.next()) {

				// act.setBalRgstrDuty(rs.getDouble("bal_duty"));
				act.setBalRgstrImportFee(rs.getDouble("bwfl_import_fee_bal"));
				act.setBalRgstrSpecialFee(rs.getDouble("bwfl_special_fee_bal"));
				act.setBalFL2DImportFee(rs.getDouble("fl2d_import_fee_bal"));
				act.setBalFL2DSpecialFee(rs.getDouble("fl2d_special_fee_bal"));

				System.out.println("setBalRgstrImportFee================"
						+ act.getBalRgstrImportFee());
			}

		} catch (SQLException se) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(se.getMessage(), se.getMessage()));
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

	public String getAdvancRgstrBalanceForPrint(PendingPermitAction act) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			String selQr = " SELECT x.bwfl_id,                                                                                                    "
					+ " SUM(x.bwfl_import_fee-(x.ap_import_fee+x.import_fee))as bwfl_import_fee_bal,                                         "
					+ " SUM(x.bwfl_special_fee-(x.ap_special_fee+x.special_fee))as bwfl_special_fee_bal,                                     "
					+ " SUM(x.fl2d_import_fee-(x.ap_import_fee+x.import_fee))as fl2d_import_fee_bal,                                         "
					+ " SUM(x.fl2d_special_fee-(x.ap_special_fee+x.special_fee))as fl2d_special_fee_bal                                      "
					+ " FROM                                                                                                                 "
					+ " (SELECT a.int_distillery_id as bwfl_id, SUM(b.double_amt) as bwfl_import_fee, 0 as bwfl_special_fee,                 "
					+ " 0 as fl2d_import_fee, 0 as fl2d_special_fee,                                                                         "
					+ " 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee                                           "
					+ " FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b                                                "
					+ " WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type                                    "
					+ " AND a.int_distillery_id='"
					+ act.getBwflId()
					+ "'                                                                        "
					+ " AND b.vch_head_code in ('003900103030000','003900105040000','003900103030000') and b.vch_g6_head_code in ('01','03') "
					+ " AND a.date_challan_date > '2019-06-16'                                                                               "
					+ " group by bwfl_id                                                                                                     "
					+ " UNION                                                                                                                "
					+ " SELECT a.int_distillery_id as bwfl_id, 0 as bwfl_import_fee, SUM(b.double_amt) as bwfl_special_fee,                  "
					+ " 0 as fl2d_import_fee, 0 as fl2d_special_fee,                                                                         "
					+ " 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee                                           "
					+ " FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b                                                "
					+ " WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type                                    "
					+ " AND a.int_distillery_id='"
					+ act.getBwflId()
					+ "'                                                                        "
					+ " AND b.vch_head_code in ('003900105020000','003900103020000') and b.vch_g6_head_code in ('18','13')                   "
					+ " AND a.date_challan_date > '2019-06-16'                                                                               "
					+ " GROUP BY bwfl_id                                                                                                     "
					+ " UNION                                                                                                                "
					+ " SELECT a.int_distillery_id as bwfl_id, 0 as bwfl_import_fee, 0 as bwfl_special_fee,                                  "
					+ " SUM(b.double_amt) as fl2d_import_fee, 0 as fl2d_special_fee,                                                         "
					+ " 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee                                           "
					+ " FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b                                                "
					+ " WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type                                    "
					+ " AND a.int_distillery_id='"
					+ act.getBwflId()
					+ "'                                                                        "
					+ " AND b.vch_head_code in ('003900105040000','003900103030000','003900103030000') and b.vch_g6_head_code in ('04','05') "
					+ " AND a.date_challan_date > '2019-06-16'                                                                               "
					+ " GROUP BY bwfl_id                                                                                                     "
					+ " UNION                                                                                                                "
					+ " SELECT a.int_distillery_id as bwfl_id, 0 as bwfl_import_fee, 0 as bwfl_special_fee,                                  "
					+ " 0 as fl2d_import_fee, SUM(b.double_amt) as fl2d_special_fee,                                                         "
					+ " 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee                                           "
					+ " FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b                                                "
					+ " WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type                                    "
					+ " AND a.int_distillery_id='"
					+ act.getBwflId()
					+ "'                                                                        "
					+ " AND b.vch_head_code in ('003900105020000','003900103020000') and b.vch_g6_head_code in ('18','13')                   "
					+ " AND a.date_challan_date > '2019-06-16'                                                                               "
					+ " GROUP BY bwfl_id                                                                                                     "
					+ " UNION                                                                                                                "
					+ " SELECT a.bwfl_id, 0 as bwfl_import_fee, 0 as bwfl_special_fee,0 as fl2d_import_fee, 0 as fl2d_special_fee,           "
					+ " SUM(a.import_fee) as ap_import_fee, SUM(COALESCE(a.special_fee,0)) as ap_special_fee,                                "
					+ " 0 as import_fee, 0 as special_fee                                                                                    "
					+ " FROM bwfl_license.import_permit_20_21 a                                                                                    "
					+ " WHERE a.bwfl_id='"
					+ act.getBwflId()
					+ "' AND a.vch_approved='APPROVED'                                                  "
					+ " GROUP BY a.bwfl_id)x GROUP BY x.bwfl_id  ";

			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();

			System.out.println("==============bal recive print=========="
					+ selQr);

			while (rs.next()) {

				// act.setBalRgstrDuty(rs.getDouble("bal_duty"));
				act.setBalRgstrImportFee(rs.getDouble("bwfl_import_fee_bal"));
				act.setBalRgstrSpecialFee(rs.getDouble("bwfl_special_fee_bal"));
				act.setBalFL2DImportFee(rs.getDouble("fl2d_import_fee_bal"));
				act.setBalFL2DSpecialFee(rs.getDouble("fl2d_special_fee_bal"));

				System.out.println("setBalRgstrImportFee=======print========="
						+ act.getBalRgstrImportFee());
			}

		} catch (SQLException se) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(se.getMessage(), se.getMessage()));
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

	public String returnParameter(String ControlID, String UnitID,
			String ServiceID, String ProcessIndustryID, String ApplicationID,
			String Status_Code, String REMARKS, String reqId,
			String pendencylevel, String objectionRejectionCode) {

		String apiOutput = "";
		try {
			org.apache.http.client.HttpClient client = new DefaultHttpClient();

			HttpPost post = new HttpPost(Constants.URL
					+ "/WReturn_CUSID_STATUS");

			final List nameValuePairs = new ArrayList(28);
			nameValuePairs.add(new BasicNameValuePair("ControlID", ControlID));
			nameValuePairs.add(new BasicNameValuePair("UnitID", UnitID));
			nameValuePairs.add(new BasicNameValuePair("ServiceID", ServiceID));
			nameValuePairs.add(new BasicNameValuePair("ProcessIndustryID",
					ProcessIndustryID));
			nameValuePairs.add(new BasicNameValuePair("ApplicationID",
					ApplicationID));
			nameValuePairs.add(new BasicNameValuePair("Status_Code",
					Status_Code));
			nameValuePairs.add(new BasicNameValuePair("Remarks", REMARKS));
			nameValuePairs.add(new BasicNameValuePair("Fee_Amount", ""));
			nameValuePairs.add(new BasicNameValuePair("Fee_Status", ""));
			nameValuePairs.add(new BasicNameValuePair("Transaction_ID", ""));
			nameValuePairs.add(new BasicNameValuePair("Transaction_Date", ""));
			nameValuePairs.add(new BasicNameValuePair("Transaction_Date_Time",
					""));
			nameValuePairs.add(new BasicNameValuePair("NOC_Certificate_Number",
					""));
			nameValuePairs.add(new BasicNameValuePair("NOC_URL", ""));
			nameValuePairs.add(new BasicNameValuePair("ISNOC_URL_ActiveYesNO",
					""));
			nameValuePairs.add(new BasicNameValuePair("passsalt",
					Constants.PASS_KEY));

			nameValuePairs.add(new BasicNameValuePair("Request_ID", reqId));

			nameValuePairs.add(new BasicNameValuePair(
					"Objection_Rejection_Code", objectionRejectionCode));
			nameValuePairs.add(new BasicNameValuePair(
					"Is_Certificate_Valid_Life_Time", ""));
			nameValuePairs.add(new BasicNameValuePair(
					"Certificate_EXP_Date_DDMMYYYY", ""));
			nameValuePairs.add(new BasicNameValuePair("Pendancy_level",
					pendencylevel));
			nameValuePairs.add(new BasicNameValuePair("D1", ""));
			nameValuePairs.add(new BasicNameValuePair("D2", ""));
			nameValuePairs.add(new BasicNameValuePair("D3", ""));
			nameValuePairs.add(new BasicNameValuePair("D4", ""));
			nameValuePairs.add(new BasicNameValuePair("D5", ""));
			nameValuePairs.add(new BasicNameValuePair("D6", ""));
			nameValuePairs.add(new BasicNameValuePair("D7", ""));

			System.out.println("nameValuePairs " + nameValuePairs);
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			System.out.println("entiry" + post.getEntity() + "uri"
					+ post.getURI());
			org.apache.http.HttpResponse respons = client.execute(post);
			System.out.println("Response offff" + respons);

			String xml = EntityUtils.toString(respons.getEntity());
			System.out.println("xml" + xml);

			XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser()
					.getXMLReader();
			InputSource source = new InputSource(new StringReader(xml));
			// -- create handlers

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder;
			InputSource is;

			builder = factory.newDocumentBuilder();
			is = new InputSource(new StringReader(xml));
			System.out.println("issss" + is.toString());
			Document doc = builder.parse(is);
			String xml1 = doc.getDocumentElement().getTextContent();
			System.out.println("status send to nic" + xml1);

			NodeList nodes = doc.getElementsByTagName("string");
			apiOutput = nodes.item(0).getTextContent();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return apiOutput;
	}

	public ArrayList objectionRejectionList(String serviceId) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("Select Rejection Reason");
		item.setValue("0");
		list.add(item);
		String sql = "select reson_id,reson_detail from industry.objection_rejection_mst where service_id='"
				+ serviceId + "'";
		try {
			System.out.println("QUERYYYYYYYYYYYYYYY " + sql);

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setValue(rs.getString("reson_id"));
				item.setLabel(rs.getString("reson_detail"));
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
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return list;
	}

	public String getreasion(String reasionid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs;
		String seq = null;

		String query = "select reson_detail from industry.objection_rejection_mst where reson_id='"
				+ reasionid + "'";

		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);

			rs = ps.executeQuery();

			if (rs.next()) {
				seq = rs.getString("reson_detail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return seq;
	}
	
	//=======================prasoooooooooooooooooooooooooooooooooooooooooooooooooooooooon 28-09-2020=====================
	
	//======================================import special fee challan lisst =========================
	
	public void getimport_special_fee(PendingPermitAction act) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			String selQr =
					"     select (select (select coalesce(sum(import_amount),0)  from fl2d.opening_fees_for_fl2d_bwfl_20_21 where unit_id= '"+act.getBwflId()+"' and unit_type like '%BWFL%' )    "+
					"	+(SELECT  coalesce(sum(amount),0)  as challan_amt  FROM bwfl_license.chalan_deposit_bwfl_fl2d b                                                   "+
					"	WHERE b.unit_id='"+act.getBwflId()+"'  and unit_type like '%BWFL%'                                                                                                 "+
					"	and head_code in ('003900103030000','003900105040000') and g6_code in ('03','01') AND                                                             "+
					"	b.cr_date between '2020-04-01'  AND '"+Utility.convertUtilDateToSQLDate(new Date())+"')-                                                                                                "+
					"	(SELECT   coalesce(sum(b.import_fee),0)  as disptch_duty FROM bwfl_license.import_permit_20_21 b WHERE b.bwfl_id= '"+act.getBwflId()+"' and                          "+
					"	login_type='BWFL'  AND b.cr_date between '2020-04-01'  AND '"+Utility.convertUtilDateToSQLDate(new Date())+"' and vch_approved='APPROVED')	as importfee                              "+
					"	),                                                                                                                                                "+
					"	(select (select coalesce(sum(cowcesh_amt),0)  from fl2d.opening_fees_for_fl2d_bwfl_20_21 where unit_id= '"+act.getBwflId()+"' and unit_type like '%BWFL%' )            "+
					"	+(SELECT  coalesce(sum(amount),0)  as challan_amt  FROM bwfl_license.chalan_deposit_bwfl_fl2d b                                                   "+
					"	WHERE b.unit_id='"+act.getBwflId()+"'  and unit_type like '%BWFL%'                                                                                                 "+
					"	and head_code in ('003900103020000','003900105020000') and g6_code in ('13','18') AND                                                             "+
					"	b.cr_date between '2020-04-01'  AND '"+Utility.convertUtilDateToSQLDate(new Date())+"')-                                                                                                "+
					"	(SELECT   coalesce(sum(b.special_fee),0)  as disptch_duty FROM bwfl_license.import_permit_20_21 b WHERE b.bwfl_id= '"+act.getBwflId()+"' and                         "+
					"	login_type='BWFL'  AND b.cr_date between '2020-04-01'  AND '"+Utility.convertUtilDateToSQLDate(new Date())+"' and vch_approved='APPROVED')as specialfee)                                " ;
														 
			 System.out.println("==import feeeeee s[ecial feees="+selQr);		
			 
			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				act.setTotal_advance_importFee(rs.getString("importfee"));
				act.setTotal_advance_specialFee(rs.getString("specialfee"));

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
	}

//========================================check boolean message  =====================================
	
	
	public boolean check_specialimport(PendingPermitAction act) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s = "";
		
		boolean flag = false;
		
		try {
			con = ConnectionToDataBase.getConnection();
			
   String selQuery = "select spcl_fee_challan_no, import_fee_challan_no from  "+
 "  bwfl_license.import_permit_20_21  where app_id = '"+act.getAppId()+"'             "+
 " and spcl_fee_challan_no = 'ADVANCE' and import_fee_challan_no = 'ADVANCE' ";
		
   
   System.out.println("===selQuery="+selQuery);
			pstmt = con.prepareStatement(selQuery);

			rs = pstmt.executeQuery();

		if(rs.next()) {
			
			 flag = true;

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
		return flag;
	}

}
