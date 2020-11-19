package com.mentor.impl;

import java.io.File;
import java.math.BigDecimal;
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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import com.mentor.action.FL2A_CSD_Permit_Approval_Action;
import com.mentor.datatable.FL2A_CSD_Permit_Approval_DT;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class FL2A_CSD_Permit_Approval_Impl {

	// ------------------display applications in datatable----------------------

	public ArrayList displayRegUsersImpl(FL2A_CSD_Permit_Approval_Action act) {

		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int j = 1;
		String selQr = null;
		String filter = "";

		try {
			con = ConnectionToDataBase.getConnection();

			if (ResourceUtil.getUserNameAllReq().trim().substring(0, 10).equalsIgnoreCase("Excise-DEO")) {
				if (act.getRadioType().equalsIgnoreCase("N")) 
				{
					filter = " AND a.deo_date IS NULL AND a.vch_approved IS NULL ";

				} else if (act.getRadioType().equalsIgnoreCase("A")) 
				{
					filter = " AND a.deo_date IS NOT NULL AND a.vch_approved='APPROVED'  ";

				} else if (act.getRadioType().equalsIgnoreCase("R"))
				{
					filter = " AND a.deo_date IS NOT NULL AND a.vch_approved='REJECTED' ";

				}

			}else{
				filter = " AND a.id=0 ";
			}

			selQr = "   SELECT DISTINCT a.id,a.district_id,COALESCE(a.excise_duty_challan_id, 'NA') as excise_duty_challan_id, a.bwfl_id, a.unit_id, a.bwfl_type, a.import_fee, a.duty, a.add_duty, a.login_type,                                             "+
					"	a.special_fee,a.tot_scanning_fee,a.total_corona_fee, a.cr_date, a.vch_approved, a.vch_status, a.deo_time, a.deo_remark, a.deo_user, a.deo_date,                                                 "+
					"	a.lic_no,  COALESCE(a.import_fee_challan_no, 'NA') as import_fee_challan_no, COALESCE(a.spcl_fee_challan_no, 'NA') as spcl_fee_challan_no," +
					"   COALESCE(a.scanning_fee_challan_no,'NA') as scanning_fee_challan_no,COALESCE(a.corona_fee_challan_no,'NA') as corona_fee_challan_no, a.app_id,  a.digital_sign_time, a.digital_sign_date,                                           "+
					"	a.print_permit_dt, COALESCE(a.permit_nmbr,'NA')permit_nmbr,                                                                                               "+
					"	COALESCE(a.digital_sign_pdf_name,'NA')digital_sign_pdf_name,COALESCE(a.scanning_fee_challan_no,'NA') as scanning_fee_challan_no,a.tot_scanning_fee," +
					"                                                      "+
					"	(SELECT DISTINCT d.vch_firm_name FROM licence.fl2_2b_2d_fl2a_csd_20_21 d WHERE a.bwfl_id=d.int_app_id)                                                                   "+
					"	as vch_firm_name, a.consignor_nm_adrs,                                                                                                                    "+
					"	c.description, a.bottlng_type, a.valid_upto, a.route_road_radio, a.route_detail,                                                                          "+
					"	CASE WHEN a.unit_type='D' THEN (SELECT DISTINCT vch_undertaking_name from public.dis_mst_pd1_pd2_lic where int_app_id_f=a.unit_id)                                 "+
					"	WHEN a.unit_type='B' THEN (SELECT DISTINCT brewery_nm from public.bre_mst_b1_lic where vch_app_id_f=a.unit_id)                                                     "+
					"	WHEN a.unit_type='O' THEN (SELECT DISTINCT vch_indus_name from public.other_unit_registration_20_21 where int_app_id_f=a.unit_id and vch_indus_type like '%CSD%') " +
					"   WHEN a.unit_type='IU' THEN (SELECT DISTINCT vch_indus_name from public.other_unit_registration_20_21 where int_app_id_f=a.unit_id and vch_indus_type='IU')end    "+
					"	as vch_indus_name,                                                                                                                                        "+
					"	CASE WHEN a.unit_type='D' THEN (SELECT DISTINCT vch_reg_add from public.dis_mst_pd1_pd2_lic where int_app_id_f=a.unit_id)                                          "+
					"	WHEN a.unit_type='B' THEN (SELECT DISTINCT vch_reg_address from public.bre_mst_b1_lic where vch_app_id_f=a.unit_id )                                               "+
					"	WHEN a.unit_type='O' THEN (SELECT DISTINCT vch_reg_office_add from public.other_unit_registration_20_21 where int_app_id_f=a.unit_id                                     "+
					"	and vch_indus_type like '%CSD%') " +
					"   WHEN a.unit_type='IU' THEN (SELECT DISTINCT vch_reg_office_add from public.other_unit_registration_20_21 where int_app_id_f=a.unit_id                                     "+
					"	and vch_indus_type='IU') end                                                                                                                      "+
					"	as vch_reg_office_add, " +
					"  CASE WHEN a.unit_type='D' Then 'Distillery' "+
					"	WHEN a.unit_type='B' THEN 'Brewery'                                                     "+
					"	WHEN a.unit_type='O' THEN 'Outside UP' WHEN a.unit_type='IU' THEN 'Imported Units' end    "+
					"	as unit_type,                                                                                                                                   "+
					"	CASE WHEN a.unit_type='D' THEN(SELECT DISTINCT d.vch_state_name FROM public.state_ind d,public.dis_mst_pd1_pd2_lic e WHERE                                         "+
					"	e.vch_reg_off_state=d.int_state_id::text and e.int_app_id_f=a.unit_id)                                                                                "+
					"	WHEN a.unit_type='B' THEN(SELECT DISTINCT d.vch_state_name FROM public.state_ind d,public.bre_mst_b1_lic e WHERE                                               "+
					"	e.int_reg_state_id=d.int_state_id and e.vch_app_id_f=a.unit_id)                                                                                      "+
					"	WHEN a.unit_type='O' THEN(SELECT DISTINCT d.vch_state_name FROM public.state_ind d,public.other_unit_registration_20_21 e WHERE                                      "+
					"	 e.vch_reg_office_state=d.int_state_id::text and e.vch_indus_type like '%CSD%' and  e.int_app_id_f=a.unit_id ) " +
					"   WHEN a.unit_type='IU' THEN(SELECT DISTINCT d.vch_state_name FROM public.state_ind d,public.other_unit_registration_20_21 e WHERE                                      "+
					"	 e.vch_reg_office_state=d.int_state_id::text and e.vch_indus_type='IU' and  e.int_app_id_f=a.unit_id ) end as vch_state_name,				"+		
					"	(SELECT DISTINCT SUM(COALESCE(dtl.no_of_cases,0)) FROM fl2d.application_csd_permit_detail_20_21 dtl                                                                      "+
					"	WHERE a.id=dtl.fk_id AND a.app_id=dtl.app_id AND a.login_type=dtl.login_type AND                                                                        "+
					"	a.district_id=dtl.district_id) as total_cases_detail                                                                                                    "+
					"	FROM fl2d.application_csd_permit_mst_20_21 a,                                                                                                                   "+
					"	  public.district c           	WHERE a.district_id=c.districtid                                                                                          "+
					"	  AND c.deo='"+ResourceUtil.getUserNameAllReq().trim()+"'  "+filter+"                                                               "+
					"	  and a.bwfl_id>0  " +
					"   AND case when a.unit_type='O' then (a.import_fee_challan_no is not null or a.import_fee_challan_no is  null) "+
					"   WHEN a.unit_type='D' then a.district_id=c.districtid "+
					"   WHEN  a.unit_type='B' then a.district_id=c.districtid   when a.unit_type='IU' then "+
                    " (a.import_fee_challan_no    is not null or a.import_fee_challan_no    is null)end "+        
					"	  ORDER BY a.app_id  ";

			/*selQr = " SELECT a.id, a.district_id, a.bwfl_id, a.unit_id, a.bwfl_type, a.import_fee, a.duty, a.add_duty, a.login_type,           "+
					" a.special_fee, a.cr_date, a.vch_approved, a.vch_status, a.deo_time, a.deo_remark, a.deo_user, a.deo_date,  "+
					" CASE WHEN a.bwfl_type=1 THEN 'BWFL2A' WHEN a.bwfl_type=2 THEN 'BWFL2B'                                     "+
					" WHEN a.bwfl_type=3 THEN 'BWFL2C' WHEN a.bwfl_type=4 THEN 'BWFL2D' end as type,                             "+
					" b.vch_firm_name, b.vch_license_number, b.vch_associated_unit_name, b.vch_firm_add,    " +
					" c.description, a.bottlng_type, a.valid_upto, a.route_road_radio, a.route_detail,  " +
					" e.vch_indus_name, e.vch_reg_office_add, a.print_permit_dt,COALESCE(a.permit_nmbr,'NA')permit_nmbr,                                                      "+
					" (SELECT d.vch_state_name FROM public.state_ind d WHERE e.vch_reg_office_state=d.int_state_id::text)as vch_state_name,  "+
					" (SELECT SUM(COALESCE(dtl.no_of_cases,0)) FROM bwfl_license.import_permit_dtl dtl                               "+
					" WHERE a.id=dtl.fk_id) as total_cases_detail                                                                  "+
					" FROM bwfl_license.import_permit a, bwfl.registration_of_bwfl_lic_holder_19_20 b,  " +
					" public.district c, public.other_unit_registration_20_21 e " +
					" WHERE a.bwfl_id=b.int_id AND a.district_id=c.districtid " +
					" AND b.unit_id=e.int_app_id_f AND a.unit_id=b.unit_id   " +
					" AND c.deo='"+ResourceUtil.getUserNameAllReq().trim()+"' "+filter+" " +
					" ORDER BY a.id ";*/

			System.out.println("select query=="+selQr);
			ps = con.prepareStatement(selQr);
			 
			rs = ps.executeQuery();

			while (rs.next()) {

				FL2A_CSD_Permit_Approval_DT dt = new FL2A_CSD_Permit_Approval_DT();

				dt.setSrNo(j);
				
				dt.setImport_fee(rs.getDouble("import_fee"));
				dt.setExcise_duty(rs.getDouble("duty")+rs.getDouble("add_duty"));
				dt.setSpecial_fee(rs.getDouble("special_fee"));
				dt.setScanning_fee(rs.getDouble("tot_scanning_fee"));
				dt.setConsideration_fee(rs.getDouble("total_corona_fee"));
				
				
				
				
				
				
				
				dt.setAppId_dt(rs.getInt("app_id"));
				dt.setRequestID_dt(rs.getInt("id"));
				dt.setBwflID_dt(rs.getInt("bwfl_id"));
				dt.setUnitID_dt(rs.getInt("unit_id"));
				dt.setLicenseType_dt("FL2A");
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
				dt.setPermitNmbr_dt(rs.getString("permit_nmbr").replaceAll("/", ""));
				dt.setLoginType_dt(rs.getString("login_type"));
				dt.setCrDate_dt(rs.getDate("cr_date"));
				dt.setApprovalDate_dt(rs.getDate("deo_date"));
				dt.setApprovalTym_dt(rs.getString("deo_time"));
				dt.setApprovalUser_dt(rs.getString("deo_user"));
				dt.setImportFeeChalanNo_dt(rs.getString("import_fee_challan_no").replaceAll("-", ""));
				dt.setSpclFeeChalanNo_dt(rs.getString("spcl_fee_challan_no"));
				dt.setDigitalSignPdfNm_dt(rs.getString("digital_sign_pdf_name"));
				dt.setDigitalSignPdfDate_dt(rs.getDate("digital_sign_date"));
				dt.setConsignorNmAdrs_dt(rs.getString("consignor_nm_adrs"));
				dt.setUnitName_dt(rs.getString("unit_type"));
				dt.setExcDutyChallanNo_dt(rs.getString("excise_duty_challan_id"));

				SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
				if (rs.getDate("cr_date") != null) {
					String appDate = date.format(Utility.convertSqlDateToUtilDate(rs.getDate("cr_date")));
					dt.setAppDate_dt(appDate);
				} else {
					dt.setAppDate_dt("");
				}
				
				if (rs.getDate("valid_upto") != null) {
					String validDate = date.format(Utility.convertSqlDateToUtilDate(rs.getDate("valid_upto")));
					dt.setValidUptoDt_dt(validDate);
				} else {
					dt.setValidUptoDt_dt("Not Filled");
				}

				if (rs.getString("vch_status") != null&& rs.getString("vch_status").length() > 0) {
					dt.setStatus_dt(rs.getString("vch_status"));
				} else {
					dt.setStatus_dt("Pending");
				}

				if (rs.getString("deo_remark") != null&& rs.getString("deo_remark").length() > 0) {
					dt.setDeoRemark_dt(rs.getString("deo_remark")+ " [ REMARK DATE : " + rs.getDate("deo_date")+ " ] ");
				} else {
					dt.setDeoRemark_dt("No Remark");
				}

		

				j++;
				list.add(dt);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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
	
	//======================get max id bwfl_license.import_permit_duty=============================
	

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
		
			String query = " SELECT max(seq) as id FROM bwfl_license.mst_bottling_plan_19_20 ";
			int maxid = 0;
			try {
				con = ConnectionToDataBase.getConnection();
				pstmt = con.prepareStatement(query);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					maxid = rs.getInt("id");
				}
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
				
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
		
			String query = " SELECT max(seq) as id FROM fl2d.mst_stock_receive_19_20 ";
			int maxid = 0;
			try {
				con = ConnectionToDataBase.getConnection();
				pstmt = con.prepareStatement(query);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					maxid = rs.getInt("id");
				}
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
				
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
		
		public int maxMstSeq(){


			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = null;
			
			
			query = " SELECT max(seq) as id  FROM fl2d.entry_of_permit_master_20_21 ";

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
	
		public int maxCSDSeq(){


			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = null;
			
			
			query = " SELECT max(int_id) as id  FROM fl2d.csd_advance_register ";

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
	
		
		public int maxIntId(){



			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = null;
			
			
			query = " SELECT max(int_id) as id  FROM fl2d.entry_of_permit_master_detail_20_21 ";

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

		public String approveApplicationImpl(FL2A_CSD_Permit_Approval_Action act){


			int saveStatus = 0;
			Connection con = null;
			PreparedStatement pstmt = null;			
			String queryList = "";
			String updtQr = "";
			String insQr = "";
			int seq = this.maxMstSeq();
			int bottlngPlanId = maxIdMstBotlngPlan();
			int maxStockRcv = maxIdMstStockRcv();
			String permitNmbr = act.getDistrictName().trim().replaceAll("\\s+","")+"_"+act.getAppId()+"_"+act.getLicenseNmbr().trim().replace("/","_")+"";

			
			try {
				con = ConnectionToDataBase.getConnection();
				con.setAutoCommit(false);
				Date date = new Date();
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				String time = sdf.format(cal.getTime());

					updtQr = 	" UPDATE fl2d.application_csd_permit_mst_20_21 "+
							 	" SET deo_time=?, deo_date=?, deo_remark=?,   "+
							 	" deo_user=?, vch_approved=?, vch_status=?, permit_nmbr=?, valid_upto=?  " +
							 	" WHERE id=? AND district_id=? AND login_type=? AND app_id=?  ";
										

					pstmt = con.prepareStatement(updtQr);
                    pstmt.setString(1, time);
					pstmt.setDate(2, Utility.convertUtilDateToSQLDate(new Date()));
					pstmt.setString(3, act.getFillRemrks());	
					pstmt.setString(4, ResourceUtil.getUserNameAllReq().trim());
					pstmt.setString(5, "APPROVED");
					pstmt.setString(6, "Approved By DEO");
					pstmt.setString(7, permitNmbr.trim());
					pstmt.setDate(8, Utility.convertUtilDateToSQLDate(act.getFillValidDt()));
					pstmt.setInt(9, act.getRequestID());
					pstmt.setInt(10, act.getDistrictId());
					pstmt.setString(11, act.getLoginType());
					pstmt.setInt(12, act.getAppId());

					saveStatus = pstmt.executeUpdate();
				  if (saveStatus > 0)
				{
					for (int i = 0; i < act.getDisplayBrandDetails().size(); i++) {

						FL2A_CSD_Permit_Approval_DT dt = (FL2A_CSD_Permit_Approval_DT) act.getDisplayBrandDetails().get(i);
				
							saveStatus = 0;
							
								insQr = " INSERT INTO fl2d.entry_of_permit_master_detail_20_21( "+
										" int_id, seq, brand_id, size, no_of_bottle, cases, no_cases, packaging_id, permit_no) "+
										" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
																															

							pstmt = con.prepareStatement(insQr);

                            pstmt.setInt(1, this.maxIntId()+i);
							pstmt.setInt(2, seq);
							pstmt.setInt(3, dt.getBrandId_dt());	
							pstmt.setInt(4, dt.getSize_ml_dt());
							pstmt.setInt(5, dt.getNmbrOfBtl_dt());
							pstmt.setInt(6, dt.getSize_dt());		
							pstmt.setInt(7, dt.getNmbrOfBox_dt());
							pstmt.setInt(8, dt.getPckgID_dt());
							pstmt.setString(9, permitNmbr);
						
							
							saveStatus = pstmt.executeUpdate();
								
							}
					}
				  
				  if(saveStatus>0){
					  
					  saveStatus=0;
					  
					  insQr = " INSERT INTO fl2d.entry_of_permit_master_20_21("+
	                          " seq, licence_id, radio_type, csd_name, csd_address, permit_no, issue_date, distillery_brewery_name, " +
	                          " distillery_id, brewery_id, distillery_brewery_address, licence_no, created_by)"+
	                          " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					  

						pstmt = con.prepareStatement(insQr);

                        pstmt.setInt(1, seq);
                        pstmt.setInt(2, act.getBwflId());
                        pstmt.setString(3, "FOD");
                        pstmt.setString(4, act.getCsd_name());
                        pstmt.setString(5, act.getCsd_address());
                        pstmt.setString(6, permitNmbr);
                        pstmt.setDate(7, Utility.convertUtilDateToSQLDate(new Date()));
                        pstmt.setString(8, act.getBrew_dist_name());
                        pstmt.setInt(9, 0);
                        pstmt.setInt(10, 0);
                        pstmt.setString(11, act.getBrew_dist_addres());
                        pstmt.setString(12, "NA");
                        pstmt.setString(13, ResourceUtil.getUserNameAllReq().trim());
						saveStatus = pstmt.executeUpdate();
				  }
				  
                if(saveStatus > 0 && act.getImportFeeChalanNo().trim().equalsIgnoreCase("Adjust From Advanced Register")){
					  
					  saveStatus=0;
					  
					  insQr = "INSERT INTO fl2d.csd_advance_register(int_id, unit_id, challan_no, date_challan_permit, "+
							  "	deposit_amount, permit_number, permit_amount, description, fn_year, challan_type)"+
							  "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					  

						pstmt = con.prepareStatement(insQr);

                        pstmt.setInt(1, this.maxCSDSeq());
                        pstmt.setInt(2, act.getBwflId());
                        pstmt.setString(3, null);
                        pstmt.setDate(4, Utility.convertUtilDateToSQLDate(new Date()));
                        pstmt.setDouble(5, 0.0);
                        pstmt.setString(6, permitNmbr);
                        pstmt.setDouble(7, act.getTotalImportFee());
                        pstmt.setString(8, "Application For Permit");
                        pstmt.setInt(9, 14);
                        pstmt.setString(10, "Import fee challan") ;
                       
						saveStatus = pstmt.executeUpdate();
				  }


				if (saveStatus > 0) {
					con.commit();
					act.setPopupPermitNmbr(act.getAppId()+" and Permit No. is "+permitNmbr);
					act.closeApplication();
					act.setShowMainPanel_flg(true);
					act.setRegisterFlag(false);
					/*FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
					" Application Approved Successfully. Your Application Id is "+ act.getAppId()+" and Permit No. is "+ permitNmbr+" !!! ",
					" Application Approved Successfully. Your Application Id is "+ act.getAppId()+" and Permit No. is "+ permitNmbr+" !!! "));*/
					

				} else {
					act.setShowMainPanel_flg(false);
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error !!! ", "Error!!!"));

					con.rollback();

				}
			} catch (Exception se) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(se.getMessage(), se.getMessage()));
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
		
		
		//================reject application====================
		
		
		public String rejectApplicationImpl(FL2A_CSD_Permit_Approval_Action act){


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

					updtQr = 	" UPDATE fl2d.application_csd_permit_mst_20_21 "+
							 	" SET deo_time=?, deo_date=?, deo_remark=?,   "+
							 	" deo_user=?, vch_approved=?, vch_status=?, import_fee_challan_no=?, spcl_fee_challan_no=?,excise_duty_challan_id=null  " +
							 	" WHERE id=? AND district_id=? AND login_type=? AND app_id=?  ";
										

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
					
					//System.out.println("status1------only----- "+saveStatus);
						


				if (saveStatus > 0) {
					con.commit();
					act.closeApplication();
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
					" Application Rejected !!! ","Application Rejected !!!"));
					

				} else {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error !!! ", "Error!!!"));

					con.rollback();

				}
			} catch (Exception se) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(se.getMessage(), se.getMessage()));
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
		
		// ====================reject application after approval=========================

		public String rejectApprovedApplication(FL2A_CSD_Permit_Approval_Action act){


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

					updtQr = 	" UPDATE fl2d.application_csd_permit_mst_20_21 "+
							 	" SET deo_time=?, deo_date=?, deo_remark=?,   "+
							 	" deo_user=?, vch_approved=?, vch_status=?, import_fee_challan_no=?, spcl_fee_challan_no=?  " +
							 	" WHERE id=? AND district_id=? AND login_type=? AND app_id=?  ";
										

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
					
					//System.out.println("status1------rejectttt----- "+saveStatus);
						
					/*if(saveStatus > 0){
					
						insQr = 	" INSERT INTO bwfl_license.rejected_permit_after_approval_20_21( " +
									" seq, district_id, bwfl_id, bwfl_type, cr_date, approval_time, approval_remark, " +
									" approval_user, approval_date, lic_no, permit_nmbr, login_type, reject_dt_time, reject_remark, app_id_fk) " +
									" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);  ";
									

					pstmt = con.prepareStatement(insQr);


					pstmt.setInt(1, seq);
					pstmt.setInt(2, act.getDistrictId());
					pstmt.setInt(3, act.getBwflId());
					pstmt.setString(4, act.getLicenseType());
					pstmt.setDate(5, Utility.convertUtilDateToSQLDate(new Date()));
					pstmt.setString(6, act.getApprovalTym());
					pstmt.setString(7, act.getDeoRemrks());
					pstmt.setString(8, act.getApprovalUser());
					pstmt.setDate(9, Utility.convertUtilDateToSQLDate(act.getApprovalDate()));
					pstmt.setString(10, act.getLicenseNmbr());	
					pstmt.setString(11, act.getPermitNmbr());
					pstmt.setString(12, act.getLoginType());
					pstmt.setString(13, dateFormat.format(new Date()));
					pstmt.setString(14, act.getFillRemrks());
					pstmt.setInt(15, act.getAppId());

					saveStatus = pstmt.executeUpdate();
					
					//System.out.println("status2------rejectttt----- "+saveStatus);
				}
					*/
					
					if(saveStatus > 0){
						

							saveStatus = 0;
							
								
							delQr = 	" DELETE FROM fl2d.entry_of_permit_master_detail_20_21  "+								 	
								 		" WHERE permit_no=?  and finalize_flg is null" ;
										

								pstmt = con.prepareStatement(delQr);
			                    pstmt.setString(1, act.getPermitNmbr());
							    saveStatus = pstmt.executeUpdate();
								
							  //  System.out.println("permit no=="+act.getPermitNmbr());
								//System.out.println("status2------rejectttt----- "+saveStatus);
	
					
					}

					if(saveStatus>0){


							saveStatus = 0;
							delQr = 	" DELETE FROM fl2d.entry_of_permit_master_20_21  "+								 	
								 		" WHERE permit_no=?" ;
										

								pstmt = con.prepareStatement(delQr);
			                    pstmt.setString(1, act.getPermitNmbr());
								
							    saveStatus = pstmt.executeUpdate();
								
							
							
								
							//	System.out.println("status3------rejectttt----- "+saveStatus);
	
						
					
					}

				if (saveStatus > 0) {
					con.commit();
					act.closeApplication();
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
					" Application Rejected   !!! ","Application Rejected !!!"));
					

				} else {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error !!! ", "Error!!!"));

					con.rollback();

				}
			} catch (Exception se) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(se.getMessage(), se.getMessage()));
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
		
		// ------------------display brand details in datatable----------------------

		public ArrayList displayBrandDetailsImpl(FL2A_CSD_Permit_Approval_Action act){


			ArrayList list = new ArrayList();
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			int j = 1;
			String selQr = null;
			String filter = "";


			try {
				con = ConnectionToDataBase.getConnection();

				                                                                                                                             
				selQr = " SELECT a.fk_id, a.district_id, a.brand_id, a.pckg_id, a.etin, a.no_of_cases, a.no_of_bottle_per_case,         "+
						" a.pland_no_of_bottles, a.import_fee, (a.duty+a.add_duty)as duty, a.add_duty, a.special_fee, a.cr_date, a.size,       "+
						" b.import_fee as tot_import_fee, (b.duty+b.add_duty) as tot_duty, " +
						" b.add_duty as tot_adduty, b.special_fee as tot_spcl_fee, " +
						" br.brand_name, br.liquor_category, pk.package_name, pk.quantity  "+
						" FROM fl2d.application_csd_permit_detail_20_21 a, fl2d.application_csd_permit_mst_20_21 b,                                          "+
						" distillery.brand_registration_20_21 br, distillery.packaging_details_20_21 pk                                 "+
						" WHERE a.fk_id=b.id AND a.district_id=b.district_id AND a.login_type=b.login_type AND a.app_id=b.app_id        "+
						" AND br.brand_id=a.brand_id and br.brand_id=pk.brand_id_fk and a.pckg_id=pk.package_id                         "+
						" AND a.app_id="+act.getAppId()+"   " +
						" ORDER BY a.fk_id ";

				ps = con.prepareStatement(selQr);
			//	System.out.println("brand query---------------" + selQr);
				rs = ps.executeQuery();

				while (rs.next()) {

					FL2A_CSD_Permit_Approval_DT dt = new FL2A_CSD_Permit_Approval_DT();

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
					dt.setSize_ml_dt(rs.getInt("size"));

					act.setTotalDuty(act.getTotalDuty()+rs.getDouble("duty"));
					act.setTotalAddDuty(act.getTotalAddDuty()+rs.getDouble("add_duty"));
					act.setTotalImportFee(act.getTotalImportFee()+rs.getDouble("import_fee"));
					act.setTotalSpecialFee(act.getTotalSpecialFee()+rs.getDouble("special_fee"));


					j++;
					list.add(dt);
				}
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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

		
		// =============================print report============================

		public boolean printReportBWFL(FL2A_CSD_Permit_Approval_Action act, FL2A_CSD_Permit_Approval_DT dt){


			String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
			String relativePath = mypath + File.separator + "ExciseUp"+ File.separator + "Bond" + File.separator + "jasper";
			String relativePathpdf = mypath + File.separator + "ExciseUp"+ File.separator + "Bond" + File.separator + "BWFLpermit";
			JasperReport jasperReport = null;
			JasperPrint jasperPrint = null;
			PreparedStatement pst = null;
			Connection con = null;
			ResultSet rs = null;
			String reportQuery = null;
			boolean printFlag = false;

			try {
				con = ConnectionToDataBase.getConnection();

					
				reportQuery = 	" SELECT a.id,a.district_id, import_fee_challan_no,a.spcl_fee_challan_no,a.excise_duty_challan_id,a.bwfl_id,dtl.no_of_cases,                                 "  +                           
								"	dtl.no_of_bottle_per_case,dtl.pland_no_of_bottles, 	dtl.import_fee,dtl.special_fee,dtl.import_fee,dtl.special_fee,                                      "  +
								"	br.brand_name,pk.package_name,pk.quantity,pk.permit as duty_rate,  	a.unit_id, a.bwfl_type, a.import_fee, a.duty,                                       "  +
								"	a.add_duty, a.login_type, 	a.special_fee, a.cr_date, a.vch_approved, a.vch_status, a.deo_time, a.deo_remark,                                           "  +
								"	a.deo_user, a.deo_date,a.print_permit_dt, a.permit_nmbr,a.digital_sign_date, COALESCE(a.digital_sign_time,'NA') as digital_sign_time ,                  "  +
								"  COALESCE(a.digital_sign_pdf_name,'NA') as digital_sign_pdf_name, a.lic_no,  a.import_fee_challan_no, a.spcl_fee_challan_no, a.app_id,                    "  +             
								"	(SELECT d.vch_firm_name FROM licence.fl2_2b_2d_fl2a_csd_20_21 d WHERE a.bwfl_id=d.int_app_id)                                                                 "  +
								"	as vch_firm_name, a.consignor_nm_adrs,                                                                                                                  "  +
								"	c.description, a.bottlng_type, a.valid_upto, concat('By ',a.route_road_radio) as route_road_radio, a.route_detail,                                      "  +                               
								"	CASE WHEN a.unit_type='D' THEN (SELECT vch_undertaking_name from public.dis_mst_pd1_pd2_lic where int_app_id_f=a.unit_id)                               "  +
								"	WHEN a.unit_type='B' THEN (SELECT brewery_nm from public.bre_mst_b1_lic where vch_app_id_f=a.unit_id)                                                   "  +
								"	WHEN a.unit_type='O' THEN (SELECT vch_indus_name from public.other_unit_registration_20_21 where int_app_id_f=a.unit_id and vch_indus_type like '%CSD%') end  "  +
								"	as vch_indus_name,                                                                                                                                      "  +
								"	CASE WHEN a.unit_type='D' THEN (SELECT vch_reg_add from public.dis_mst_pd1_pd2_lic where int_app_id_f=a.unit_id)                                        "  +
								"	WHEN a.unit_type='B' THEN (SELECT vch_reg_address from public.bre_mst_b1_lic where vch_app_id_f=a.unit_id )                                             "  +
								"	WHEN a.unit_type='O' THEN (SELECT vch_reg_office_add from public.other_unit_registration_20_21 where int_app_id_f=a.unit_id                                   "  +
								"	and vch_indus_type like '%CSD%') end                                                                                                                    "  +
								"	as vch_reg_office_add,                                                                                                                                  "  +
								"	CASE WHEN a.unit_type='D' THEN(SELECT d.vch_state_name FROM public.state_ind d,public.dis_mst_pd1_pd2_lic e WHERE                                       "  +
								"	    e.vch_reg_off_state=d.int_state_id::text and e.int_app_id_f=a.unit_id)                                                                              "  +
								"		WHEN a.unit_type='B' THEN(SELECT d.vch_state_name FROM public.state_ind d,public.bre_mst_b1_lic e WHERE                                             "  +
								"	     e.int_reg_state_id=d.int_state_id and e.vch_app_id_f=a.unit_id)                                                                                    "  +
								"		WHEN a.unit_type='O' THEN(SELECT d.vch_state_name FROM public.state_ind d,public.other_unit_registration_20_21 e WHERE                                    "  +
								"	     e.vch_reg_office_state=d.int_state_id::text and e.vch_indus_type like '%CSD%' and  e.int_app_id_f=a.unit_id ) end as vch_state_name,				"+	
								"	(SELECT SUM(COALESCE(dtl.no_of_cases,0)) FROM fl2d.application_csd_permit_detail_20_21 dtl                                                                    "  +
								"	  WHERE a.id=dtl.fk_id AND a.app_id=dtl.app_id AND a.login_type=dtl.login_type AND                                                                      "  +
								"	  a.district_id=dtl.district_id) as total_cases_detail,                                                                                                 "  +
								"	 round(CAST(float8(((dtl.pland_no_of_bottles)*pk.quantity)/1000) as numeric), 2) as bl                                                                  "  +
								"	 FROM fl2d.application_csd_permit_mst_20_21 a, public.district c,                                                                                             "  +
								"	 distillery.brand_registration_20_21 br, distillery.packaging_details_20_21 pk ,                                                                        "  +
								"	 fl2d.application_csd_permit_detail_20_21 dtl                                                                                                                 "  +
								"	 WHERE a.district_id=c.districtid                                                                                                                       "  +
								"	 AND c.deo='"+ResourceUtil.getUserNameAllReq()+"'  AND a.deo_date IS NOT NULL AND a.vch_approved='APPROVED'                                                              "  +
								"	 and a.bwfl_id>0  and  a.app_id='"+dt.getAppId_dt()+"' AND br.brand_id=dtl.brand_id                                                                                     "  +
								"	 and br.brand_id=pk.brand_id_fk       	 and dtl.pckg_id=pk.package_id   and a.app_id=dtl.app_id ; ";

				
					
			//	System.out.println("reportQuery---------------------"+reportQuery);
				pst = con.prepareStatement(reportQuery);
				rs = pst.executeQuery();

				if (rs.next()) {
					rs = pst.executeQuery();

					Map parameters = new HashMap();
					parameters.put("REPORT_CONNECTION", con);
					//parameters.put("SUBREPORT_DIR", relativePath+File.separator);
					parameters.put("image", relativePath + File.separator);					
					/*if(dt.getLoginType_dt().equalsIgnoreCase("BWFL")){
						parameters.put("balImportFee", act.getBalRgstrImportFee());
					}
					else if(dt.getLoginType_dt().equalsIgnoreCase("FL2D")){
						parameters.put("balImportFee", act.getBalFL2DImportFee());
					}*/
					

					JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);
					if(dt.getLicenseType_dt().equalsIgnoreCase("BWFL2B")){
						
						
						jasperReport = (JasperReport) JRLoader.loadObject(relativePath+ File.separator+ "BWFLImportPermitBwfl2B.jasper");
						 
						}
						else{
							jasperReport = (JasperReport) JRLoader.loadObject(relativePath+ File.separator+ "BWFLImportPermit.jasper");
						}
					JasperPrint print = JasperFillManager.fillReport(jasperReport,parameters, jrRs);
					
					Random rand = new Random();
					int n = rand.nextInt(250) + 1;

					JasperExportManager.exportReportToPdfFile(print,relativePathpdf + File.separator+ dt.getPermitNmbr_dt().trim().replaceAll("\\s+","")+".pdf");
					
					//dt.setPdfName(dt.getAppId_dt()+ "-"+dt.getBwflID_dt()+"_"+dt.getLoginType_dt()+"ImportPermit.pdf");	
					dt.setPdfName(dt.getPermitNmbr_dt()+".pdf");
					this.updatePermit(act, dt.getRequestID_dt(),dt.getDistrictId_dt(), dt.getLoginType_dt(), dt.getAppId_dt(),dt.getPdfName());
					printFlag = true;
				} else {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("No Data Found", "No Data Found"));				
					printFlag = false;

				}
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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

		public boolean printReportFL2D(FL2A_CSD_Permit_Approval_Action act, FL2A_CSD_Permit_Approval_DT dt){


			String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
			String relativePath = mypath + File.separator + "ExciseUp"+ File.separator + "Bond" + File.separator + "jasper";
			String relativePathpdf = mypath + File.separator + "ExciseUp"+ File.separator + "Bond" + File.separator + "FL2Apermit";
			JasperReport jasperReport = null;
			JasperPrint jasperPrint = null;
			PreparedStatement pst = null;
			Connection con = null;
			ResultSet rs = null;
			String reportQuery = null;
			boolean printFlag = false;

			try {
				con = ConnectionToDataBase.getConnection();

					                                                                                                                                           
		  reportQuery = "  SELECT DISTINCT a.tot_scanning_fee,a.total_corona_fee,a.duty,a.special_fee, a.import_fee as total_import_fee,a.id,a.district_id, COALESCE(import_fee_challan_no, 'Adjust From Advance Register') as import_fee_challan_no,COALESCE(a.spcl_fee_challan_no,'NA') as spcl_fee_challan_no," +
						" COALESCE(a.excise_duty_challan_id,'NA') as excise_duty_challan_id," +
						" COALESCE(a.scanning_fee_challan_no,'NA') as scanning_fee_challan_no,COALESCE(a.corona_fee_challan_no,'NA') as corona_fee_challan_no,a.bwfl_id,dtl.no_of_cases,                         "+                                     
						"	dtl.no_of_bottle_per_case,dtl.pland_no_of_bottles, 	dtl.import_fee,CASE WHEN a.unit_type='O' THEN dtl.special_fee::text ELSE '-' end as special_fee,dtl.import_fee,dtl.special_fee,                                  "+                                          
						"	br.brand_name,pk.package_name,pk.quantity,pk.duty as duty_rate,pk.permit,  	a.unit_id, a.bwfl_type, a.import_fee, a.duty,                                                                                            "+
						"	a.add_duty, a.login_type, 	a.special_fee, CASE WHEN a.unit_type='O' THEN (a.special_fee/dtl.pland_no_of_bottles)::text ELSE '-' end as spcl_fee, a.cr_date, a.vch_approved, a.vch_status, a.deo_time, a.deo_remark, "+                                                                                
						"	a.deo_user, a.deo_date,a.print_permit_dt, a.permit_nmbr,a.digital_sign_date, COALESCE(a.digital_sign_time,'NA') as digital_sign_time ,                                                     "+    
						"	COALESCE(a.digital_sign_pdf_name,'NA') as digital_sign_pdf_name, a.lic_no,  a.import_fee_challan_no, a.spcl_fee_challan_no, a.app_id,                                                      "+    
						"	(SELECT DISTINCT d.vch_firm_name FROM licence.fl2_2b_2d_fl2a_csd_20_21 d WHERE a.bwfl_id=d.int_app_id)                                                                                                    "+    
						"	as vch_firm_name, a.consignor_nm_adrs,COALESCE(a.scanning_fee_challan_no,'NA') as scanning_fee_challan_no,a.tot_scanning_fee," +
					    "   dtl. scanning_fee,                                                                                                                                                     "+    
						"	c.description, a.bottlng_type, a.valid_upto, concat('By ',a.route_road_radio) as route_road_radio, a.route_detail,                                                                         "+    
						"	CASE WHEN a.unit_type='D' THEN (SELECT DISTINCT vch_undertaking_name from public.dis_mst_pd1_pd2_lic where int_app_id_f=a.unit_id)                                                                  "+    
						"	WHEN a.unit_type='B' THEN (SELECT DISTINCT brewery_nm from public.bre_mst_b1_lic where vch_app_id_f=a.unit_id)                                                                                      "+    
						"	WHEN a.unit_type='O' THEN (SELECT DISTINCT vch_indus_name from public.other_unit_registration_20_21 where int_app_id_f=a.unit_id and vch_indus_type like '%CSD%')                                         "+    
						"	WHEN a.unit_type='IU' THEN (SELECT DISTINCT vch_indus_name from public.other_unit_registration_20_21 where int_app_id_f=a.unit_id and vch_indus_type='IU'                                                 "+    
						"	and int_app_id_f in (SELECT  distinct int_fl2d_id from distillery.brand_registration_20_21                                                                                                  "+    
						"	where for_csd_civil='CSD'))                                                                                                                                                                "+    
						"	end                                                                                                                                                                                        "+    
						"	as vch_indus_name,                                                                                                                                                                         "+    
						"	CASE WHEN a.unit_type='D' THEN (SELECT DISTINCT vch_reg_add from public.dis_mst_pd1_pd2_lic where int_app_id_f=a.unit_id)                                                                           "+    
						"	WHEN a.unit_type='B' THEN (SELECT DISTINCT vch_reg_address from public.bre_mst_b1_lic where vch_app_id_f=a.unit_id )                                                                                "+    
						"	WHEN a.unit_type='O' THEN (SELECT DISTINCT vch_reg_office_add from public.other_unit_registration_20_21 where int_app_id_f=a.unit_id                                                                      "+    
						"	and vch_indus_type like '%CSD%')                                                                                                                                                           "+    
						"	WHEN a.unit_type='IU' THEN (SELECT DISTINCT vch_reg_office_add from public.other_unit_registration_20_21 where int_app_id_f=a.unit_id                                                                     "+    
						"	and vch_indus_type='IU' and int_app_id_f in (select distinct int_fl2d_id from distillery.brand_registration_20_21                                                                          "+    
						"	where for_csd_civil='CSD'))end                                                                                                                                                             "+    
						"	as vch_reg_office_add,                                                                                                                                                                     "+    
						"	CASE WHEN a.unit_type='D' THEN(SELECT DISTINCT d.vch_state_name FROM public.state_ind d,public.dis_mst_pd1_pd2_lic e WHERE                                                                          "+    
						"	e.vch_reg_off_state=d.int_state_id::text and e.int_app_id_f=a.unit_id)                                                                                                                     "+    
						"	WHEN a.unit_type='B' THEN(SELECT DISTINCT d.vch_state_name FROM public.state_ind d,public.bre_mst_b1_lic e WHERE                                                                                    "+    
						"	e.int_reg_state_id=d.int_state_id and e.vch_app_id_f=a.unit_id)                                                                                                                            "+    
						"	WHEN a.unit_type='O' THEN(SELECT DISTINCT d.vch_state_name FROM public.state_ind d,public.other_unit_registration_20_21 e WHERE                                                                           "+    
						"	e.vch_reg_office_state=d.int_state_id::text and e.vch_indus_type like '%CSD%' and  e.int_app_id_f=a.unit_id )                                                                              "+    
						"	WHEN a.unit_type='IU' THEN(SELECT DISTINCT d.vch_state_name FROM public.state_ind d,public.other_unit_registration_20_21 e WHERE                                                                          "+    
						"	e.vch_reg_office_state=d.int_state_id::text and e.vch_indus_type='IU' and  e.int_app_id_f=a.unit_id                                                                                        "+    
						"	and int_app_id_f in (select distinct int_fl2d_id from distillery.brand_registration_20_21                                                                                                  "+    
						"	where for_csd_civil='CSD'))                                                                                                                                                                "+    
						"	end as vch_state_name,						                                                                                                                                               "+    
						"	(SELECT DISTINCT SUM(COALESCE(dtl.no_of_cases,0)) FROM fl2d.application_csd_permit_detail_20_21 dtl                                                                                                       "+    
						"	WHERE a.id=dtl.fk_id AND a.app_id=dtl.app_id AND a.login_type=dtl.login_type AND                                                                                                           "+    
						"	a.district_id=dtl.district_id) as total_cases_detail,                                                                                                                                      "+    
						"	round(CAST(float8(((dtl.pland_no_of_bottles)*pk.quantity)/1000) as numeric), 2) as bl                                                                                                      "+    
						"	FROM fl2d.application_csd_permit_mst_20_21 a, public.district c,                                                                                                                                 "+    
						"	distillery.brand_registration_20_21 br, distillery.packaging_details_20_21 pk ,                                                                                                            "+    
						"	fl2d.application_csd_permit_detail_20_21 dtl                                                                                                                                                     "+           
						"	WHERE a.district_id=c.districtid AND a.deo_date IS NOT NULL AND a.vch_approved='APPROVED' and br.brand_id=pk.brand_id_fk  " +
						"   and dtl.pckg_id=pk.package_id   and a.app_id=dtl.app_id   "+
						"	AND c.deo='"+ResourceUtil.getUserNameAllReq()+"' and a.bwfl_id>0  and  a.app_id='"+dt.getAppId_dt()+"' AND br.brand_id=dtl.brand_id order by a.app_id    ";                                                                                                                                   
										
				
			    System.out.println("reportQuery---------------------"+reportQuery);
				pst = con.prepareStatement(reportQuery);
				rs = pst.executeQuery();

				if (rs.next()) {
					rs = pst.executeQuery();

					Map parameters = new HashMap();
					parameters.put("REPORT_CONNECTION", con);
					//parameters.put("SUBREPORT_DIR", relativePath+File.separator);
					parameters.put("image", relativePath + File.separator);					
					parameters.put("condition_img", relativePath + File.separator);
				
					
					JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);
					JasperPrint print=null;
					if(dt.getUnitName_dt().equalsIgnoreCase("Imported Units")){
						jasperReport = (JasperReport) JRLoader.loadObject(relativePath+ File.separator+ "FL2AImportPermitIU.jasper");
						print = JasperFillManager.fillReport(jasperReport,parameters, jrRs);
					}else if (dt.getUnitName_dt().equalsIgnoreCase("Distillery") || dt.getUnitName_dt().equalsIgnoreCase("Brewery")){
					 jasperReport = (JasperReport) JRLoader.loadObject(relativePath+ File.separator+ "FL2AImportPermitDB.jasper");
					 print = JasperFillManager.fillReport(jasperReport,parameters, jrRs);
					}else{
						jasperReport = (JasperReport) JRLoader.loadObject(relativePath+ File.separator+ "FL2AImportPermit.jasper");
						 print = JasperFillManager.fillReport(jasperReport,parameters, jrRs);
					}
					
					Random rand = new Random();
					int n = rand.nextInt(250) + 1;

					JasperExportManager.exportReportToPdfFile(print,relativePathpdf + File.separator+ dt.getPermitNmbr_dt().trim().replaceAll("/","")+".pdf");
					
					//dt.setPdfName(dt.getAppId_dt()+ "-"+dt.getBwflID_dt()+"_"+dt.getLoginType_dt()+"ImportPermit.pdf");	
					dt.setPdfName(dt.getPermitNmbr_dt()+".pdf");
					this.updatePermit(act, dt.getRequestID_dt(),dt.getDistrictId_dt(), dt.getLoginType_dt(), dt.getAppId_dt(),dt.getPdfName());
					printFlag = true;
				} else {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("No Data Found", "No Data Found"));				
					printFlag = false;

				}
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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
		
		public String updatePermit(FL2A_CSD_Permit_Approval_Action act, int id, int districtId, String loginType, int appId, String pdfName){


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

					updtQr = 	" UPDATE fl2d.application_csd_permit_mst_20_21 "+
							 	" SET print_permit_time=?, print_permit_dt=?, print_permit_pdf=? "+
							 	" WHERE id="+id+" AND district_id="+districtId+"  " +
							 	" AND login_type='"+loginType+"' AND app_id="+appId+"  ";
										

					pstmt = con.prepareStatement(updtQr);

					//System.out.println("updtQr----------------"+updtQr);
					
					pstmt.setString(1, time);
					pstmt.setDate(2, Utility.convertUtilDateToSQLDate(new Date()));	
					pstmt.setString(3, pdfName);
					

					saveStatus = pstmt.executeUpdate();
						


				if (saveStatus > 0) {
					con.commit();
					//act.closeApplication();
					
					

				} else {
					con.rollback();

				}
			} catch (Exception se) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(se.getMessage(), se.getMessage()));
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
		
		
		// ------------------display challan details in datatable----------------------

		public ArrayList displayChalanDetailsImpl(FL2A_CSD_Permit_Approval_Action act,FL2A_CSD_Permit_Approval_DT dt){


			ArrayList list = new ArrayList();
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			int j = 1;
			String selQr = null;
			String filter = "";
            int count=0;
            String head="";
            
            if(dt.getUnitName_dt().equalsIgnoreCase("Imported Units")){
            	head=" and vch_rajaswa_head in ('3900105040000') and g6_head in (4) ";
            	
            }else if(dt.getUnitName_dt().equalsIgnoreCase("Outside UP")){
            	head=" and vch_rajaswa_head in ('3900105010000','3900103010000') and g6_head in (2,3) ";
            }else{
            	head=" and vch_rajaswa_head in ('3900105010000','3900103010000') and g6_head in (2,3) ";
            }
			try {
				con = ConnectionToDataBase.getConnection();
                                                                                                                                           
				selQr = /*" select distinct x.challan_nm, x.challan, a.vch_challan_id ,a.dat_created_date, a.vch_total_amount, a.vch_division, a.vch_treasury,  "+
						"	(select distinct tname from licence.treasury where tcode=a.vch_treasury)  as treasury,                             "+
						"	(SELECT distinct divname from licence.division where divcode=a.vch_division) as division                           "+
						"	from (SELECT distinct 'Import fee' as challan_nm ,  import_fee_challan_no as challan from fl2d.application_csd_permit_mst_20_21 where app_id='"+act.getAppId()+"'      "+
						"	union                                                                                                     "+
						"	SELECT distinct 'Special Fee' as challan_nm, spcl_fee_challan_no as challan from fl2d.application_csd_permit_mst_20_21 where app_id='"+act.getAppId()+"'              "+
						"	union                                                                                                     "+
						"	SELECT distinct 'Excise Duty' as challan_nm, excise_duty_challan_id as challan from fl2d.application_csd_permit_mst_20_21 where app_id='"+act.getAppId()+"'" +
						"	union "+
                        "	SELECT distinct 'Scanning Fee' as challan_nm, scanning_fee_challan_no as challan from fl2d.application_csd_permit_mst_20_21 where app_id='"+act.getAppId()+"'" +
						"	union                                                                                                     "+
						"	SELECT distinct 'Special Additional Consideration Fee' as challan_nm, corona_fee_challan_no as challan from fl2d.application_csd_permit_mst_20_21 where app_id='"+act.getAppId()+"') x        "+
						"	left outer join licence.mst_challan_master a on a.vch_challan_id=x.challan ";
													
						*/
					                                

					"	select sum(opening) as opening,sum(deposit) as deposit ,sum(permit) as permit ,((sum(opening)+sum(deposit))-sum(permit)) as balance,type   "+
					"	from(                                                                                                                                      "+
                    "                                                                                                                                              "+
					"	select sum(amount)as opening,0 as deposit,0 as permit, 'IMPORT FEE'  as type from fl2d.advance_opening_fl2a                                "+
					"	where fee_type='IMPORT FEE' and unit_type='DEPO' and district_id="+this.getDistrictId()+" and unit_id="+dt.getBwflID_dt()+"                                                        "+
					"	union                                                                                                                                      "+
					"	select 0 as opening ,coalesce(sum(vch_total_amount::numeric),0) as deposit,0 as permit,'IMPORT FEE'  as type                               "+
					"	from licence.mst_challan_master a,                                                                                                         "+
					"	licence.challan_head_details b,licence.division c,licence.treasury d                                                                       "+
					"	where a.vch_challan_id=b.vch_challan_id                                                                                                    "+
					"	and vch_rajaswa_head in ('3900103030000',' 3900105040000') and g6_head in (2)                                                              "+
					"	and a.vch_trn_status='SUCCESS' and dat_created_date>='2020-04-01'   and vch_register_flag='R'                                                                      "+
					"	and c.divcode::numeric=a.vch_division::numeric and c.divcode::numeric=d.divcode::numeric  and d.district_id="+this.getDistrictId()+"                                                 "+
					"	and a.vch_login_id=(select vch_mobile_no from  licence.fl2_2b_2d_fl2a_csd_20_21                                                            "+
					"	where int_app_id="+dt.getBwflID_dt()+" )                                                                                                                      "+
					"	union                                                                                                                                      "+
					"	select 0 as opening ,0 as deposit,sum(import_fee) as permit,'IMPORT FEE'  as type                                                          "+
					"	from fl2d.application_csd_permit_mst_20_21 where  bwfl_id="+dt.getBwflID_dt()+" and district_id="+this.getDistrictId()+"                                              "+
					"	and vch_approved='APPROVED' and unit_type not in('B','D')                                                                                  "+
					"	group by unit_id,bwfl_id,district_id                                                                                                       "+
					"	union                                                                                                                                      "+
					"	select sum(amount)as opening,0 as deposit,0 as permit, 'EXCISE DUTY'  as type from fl2d.advance_opening_fl2a                               "+
					"	where fee_type='EXCISE DUTY' and unit_type='UNIT' and district_id="+this.getDistrictId()+" and unit_id="+dt.getUnitID_dt()+"                                                        "+
					"	union                                                                                                                                      "+
					"	select 0 as opening ,coalesce(sum(vch_total_amount::numeric),0) as deposit,0 as permit,'EXCISE DUTY'  as type                              "+
					"	from licence.mst_challan_master a,                                                                                                         "+
					"	licence.challan_head_details b,licence.division c,licence.treasury d                                                                       "+
					"	where a.vch_challan_id=b.vch_challan_id                                                                                                    "+
					"	 "+head+"                                                             "+
					"	and a.vch_trn_status='SUCCESS' and dat_created_date>='2020-04-01'   and vch_register_flag='R'                                                                      "+
					"	and c.divcode::numeric=a.vch_division::numeric and c.divcode::numeric=d.divcode::numeric and d.district_id="+this.getDistrictId()+"                                                  "+
					"	and a.vch_login_id=(select login_id  from public.other_unit_registration_20_21                                                             "+
					"	where 'CSD'=substring(login_id from 1 for 3) and int_app_id_f="+dt.getUnitID_dt()+")                                                                          "+
					"	UNION                                                                                                                                      "+
					"	select 0 as opening ,0 as deposit,sum((coalesce(duty,0)+coalesce(add_duty,0))) as permit,'EXCISE DUTY'  as type                            "+
					"	from fl2d.application_csd_permit_mst_20_21 where unit_id="+dt.getUnitID_dt()+" and bwfl_id="+dt.getBwflID_dt()+" and district_id="+this.getDistrictId()+"                                              "+
					"	and vch_approved='APPROVED' and unit_type not in('B','D')                                                                                  "+
					"	group by unit_id,bwfl_id,district_id                                                                                                       "+
					"	union                                                                                                                                      "+
					"	select sum(amount)as opening,0 as deposit,0 as permit, 'SPECIAL FEE'  as type from fl2d.advance_opening_fl2a                               "+
					"	where fee_type='SPECIAL FEE' and unit_type='UNIT' and district_id="+this.getDistrictId()+" and unit_id="+dt.getUnitID_dt()+"                                                        "+
					"	union                                                                                                                                      "+
					"	select 0 as opening ,coalesce(sum(vch_total_amount::numeric),0) as deposit,0 as permit,'SPECIAL FEE'  as type                              "+
					"	from licence.mst_challan_master a,                                                                                                         "+
					"	licence.challan_head_details b,licence.division c,licence.treasury d                                                                       "+
					"	where a.vch_challan_id=b.vch_challan_id                                                                                                    "+
					"	and vch_rajaswa_head in ('3900105020000','3900103020000') and g6_head in (24,13)                                                                              "+
					"	and a.vch_trn_status='SUCCESS' and dat_created_date>='2020-04-01' and vch_register_flag='R'                                                                       "+
					"	and c.divcode::numeric=a.vch_division::numeric and c.divcode::numeric=d.divcode::numeric   and d.district_id="+this.getDistrictId()+"                                                "+
					"	and a.vch_login_id=(select login_id  from public.other_unit_registration_20_21                                                             "+
					"	where 'CSD'=substring(login_id from 1 for 3) and int_app_id_f="+dt.getUnitID_dt()+" )                                                                          "+
					"	UNION                                                                                                                                      "+
					"	select 0 as opening ,0 as deposit,sum(coalesce(special_fee,0)) as permit,'SPECIAL FEE'  as type                                            "+
					"	from fl2d.application_csd_permit_mst_20_21 where unit_id="+dt.getUnitID_dt()+" and bwfl_id="+dt.getBwflID_dt()+" and district_id="+this.getDistrictId()+"                                              "+
					"	and vch_approved='APPROVED' and unit_type not in('B','D')                                                                                  "+
					"	group by unit_id,bwfl_id,district_id                                                                                                       "+
					"	union                                                                                                                                      "+
					"	select coalesce(sum(amount),0)as opening,0 as deposit,0 as permit, 'SCANING FEE'  as type from fl2d.advance_opening_fl2a                   "+
					"	where fee_type='SCANING FEE' and unit_type='UNIT' and district_id="+this.getDistrictId()+" and unit_id="+dt.getUnitID_dt()+"                                                        "+
					"	union                                                                                                                                      "+
					"	select 0 as opening ,coalesce(sum(vch_total_amount::numeric),0) as deposit,0 as permit,'SCANING FEE'  as type                              "+
					"	from licence.mst_challan_master a,                                                                                                         "+
					"	licence.challan_head_details b,licence.division c,licence.treasury d                                                                       "+
					"	where a.vch_challan_id=b.vch_challan_id                                                                                                    "+
					"	and vch_rajaswa_head in ('3900800060000') and g6_head in (6)                                                                               "+
					"	and a.vch_trn_status='SUCCESS' and dat_created_date>='2020-04-01'  and vch_register_flag='R'                                                                       "+
					"	and c.divcode::numeric=a.vch_division::numeric and c.divcode::numeric=d.divcode::numeric  and d.district_id="+this.getDistrictId()+"                                                 "+
					"	and a.vch_login_id=(select login_id  from public.other_unit_registration_20_21                                                             "+
					"	where 'CSD'=substring(login_id from 1 for 3) and int_app_id_f="+dt.getUnitID_dt()+")                                                                          "+
					"	UNION                                                                                                                                      "+
					"	select 0 as opening ,0 as deposit,sum(coalesce(tot_scanning_fee,0)) as permit,'SCANING FEE'  as type                                       "+
					"	from fl2d.application_csd_permit_mst_20_21 where unit_id="+dt.getUnitID_dt()+" and bwfl_id="+dt.getBwflID_dt()+" and district_id="+this.getDistrictId()+"                                              "+
					"	and vch_approved='APPROVED' and unit_type not in('B','D')                                                                                  "+
					"	group by unit_id,bwfl_id,district_id                                                                                                       "+
					"	union                                                                                                                                      "+
					"	select coalesce(sum(amount),0)as opening,0 as deposit,0 as permit, 'SPECIAL CONSIDERATION FEE'  as type from fl2d.advance_opening_fl2a     "+
					"	where fee_type='SPECIAL CONSIDERATION FEE' and unit_type='UNIT' and district_id="+this.getDistrictId()+" and unit_id="+dt.getUnitID_dt()+"                                          "+
					"	union                                                                                                                                      "+
					"	select 0 as opening ,coalesce(sum(vch_total_amount::numeric),0) as deposit,0 as permit,'SPECIAL CONSIDERATION FEE'  as type                "+
					"	from licence.mst_challan_master a,                                                                                                         "+
					"	licence.challan_head_details b,licence.division c,licence.treasury d                                                                       "+
					"	where a.vch_challan_id=b.vch_challan_id                                                                                                    "+
					"	and vch_rajaswa_head in ('003900103010000', '003900105010000') and g6_head in (09)                                                         "+
					"	and a.vch_trn_status='SUCCESS' and dat_created_date>='2020-04-01'  and vch_register_flag='R'                                                                      "+
					"	and c.divcode::numeric=a.vch_division::numeric and c.divcode::numeric=d.divcode::numeric  and d.district_id="+this.getDistrictId()+"                                                 "+
					"	and a.vch_login_id=(select login_id  from public.other_unit_registration_20_21                                                             "+
					"	where 'CSD'=substring(login_id from 1 for 3) and int_app_id_f="+dt.getUnitID_dt()+")                                                                          "+
					"	UNION                                                                                                                                      "+
					"	select 0 as opening ,0 as deposit,sum(coalesce(total_corona_fee,0)) as permit,'SPECIAL CONSIDERATION FEE'  as type                         "+
					"	from fl2d.application_csd_permit_mst_20_21 where unit_id="+dt.getUnitID_dt()+" and bwfl_id="+dt.getBwflID_dt()+" and district_id="+this.getDistrictId()+"                                              "+
					"	and vch_approved='APPROVED' and unit_type not in('B','D')                                                                                  "+
					"	group by unit_id,bwfl_id,district_id)x group by type                                                                                       ";

			

				ps = con.prepareStatement(selQr);
				System.out.println("challan query---------------" + selQr);
				rs = ps.executeQuery();

				while (rs.next()) {

					FL2A_CSD_Permit_Approval_DT dt1 = new FL2A_CSD_Permit_Approval_DT();

					dt1.setSrNo(j);
					dt1.setChallanName_dt(rs.getString("type"));
					dt1.setBalance(rs.getDouble(("balance")));
					
					
					if(rs.getString("type").trim().equalsIgnoreCase("IMPORT FEE")&& (dt.getImport_fee()>rs.getDouble("balance")))
					{
						count++;
						dt1.setRequired_remark("Balance As On Date Of Import Is Less Than Permit Import Fee");
						dt1.setPermit_balance(dt.getImport_fee());
						
					}else if(rs.getString("type").trim().equalsIgnoreCase("IMPORT FEE")&& (dt.getImport_fee()<=rs.getDouble("balance")))
					{
						dt1.setPermit_balance(dt.getImport_fee());
					}
					if(rs.getString("type").trim().equalsIgnoreCase("EXCISE DUTY")&& (dt.getExcise_duty()>rs.getDouble("balance")))
					{
						
						dt1.setPermit_balance(dt.getExcise_duty());
						dt1.setRequired_remark("Balance As On Date Of EXCISE DUTY Is Less Than Permit EXCISE DUTY Fee");
						count++;
						
					}else if(rs.getString("type").trim().equalsIgnoreCase("EXCISE DUTY")&& (dt.getExcise_duty()<=rs.getDouble("balance")))
					{
						dt1.setPermit_balance(dt.getExcise_duty());
					}
					if(rs.getString("type").trim().equalsIgnoreCase("SPECIAL FEE")&& (dt.getSpecial_fee()>rs.getDouble("balance")))
					{
						dt1.setPermit_balance(dt.getSpecial_fee());
						dt1.setRequired_remark("Balance As On Date Of SPECIAL FEE Is Less Than Permit SPECIAL FEE Fee");
						count++;
						
					}else if(rs.getString("type").trim().equalsIgnoreCase("SPECIAL FEE")&& (dt.getSpecial_fee()<=rs.getDouble("balance")))
					{
						dt1.setPermit_balance(dt.getSpecial_fee());
					}
					if(rs.getString("type").trim().equalsIgnoreCase("SCANING FEE")&& (dt.getScanning_fee()>rs.getDouble("balance")))
					{
						dt1.setPermit_balance(dt.getScanning_fee());
						dt1.setRequired_remark("Balance As On Date Of SCANING Is Less Than Permit SCANING Fee");
						count++;
						
					}else if(rs.getString("type").trim().equalsIgnoreCase("SCANING FEE")&& (dt.getScanning_fee()<=rs.getDouble("balance")))
					{
						dt1.setPermit_balance(dt.getScanning_fee());
					}
					if(rs.getString("type").trim().equalsIgnoreCase("SPECIAL CONSIDERATION FEE")&& (dt.getConsideration_fee()>rs.getDouble("balance")))
					{
						dt1.setPermit_balance(dt.getConsideration_fee());
						dt1.setRequired_remark("Balance As On Date Of SPECIAL CONSIDERATION Is Less Than Permit SPECIAL CONSIDERATION Fee");
						count++;
						
					}else if(rs.getString("type").trim().equalsIgnoreCase("SPECIAL CONSIDERATION FEE")&& (dt.getConsideration_fee()<=rs.getDouble("balance")))
					{
						dt1.setPermit_balance(dt.getConsideration_fee());
					}
					/*dt.setChallanId_dt(rs.getInt("vch_challan_id"));
					dt.setChallanNo_dt(rs.getString("vch_challan_id"));
					dt.setChallanAmnt_dt(rs.getDouble("vch_total_amount"));
					dt.setDivName_dt(rs.getString("division"));
					dt.setTreasryNm_dt(rs.getString("treasury"));
					
					SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
					if (rs.getDate("dat_created_date") != null) {
						String chalanDate = date.format(Utility.convertSqlDateToUtilDate(rs.getDate("dat_created_date")));
						dt.setChallanDate_dt(chalanDate);
					} else {
						dt.setChallanDate_dt("");
					}*/


					j++;
					list.add(dt1);
				}
				
				
				if(!dt.getUnitName_dt().equals("Distillery")&&!dt.getUnitName_dt().equals("Brewery"))
				{
				if(count>0)
				{
					act.setApproveFlag(true);
				}else{
					act.setApproveFlag(false);
				}
				
				}
				
				
				
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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
		
		public String getAdvancRgstrBalance(FL2A_CSD_Permit_Approval_Action act){


			int id = 0;
			Connection con = null;
			PreparedStatement pstmt = null, ps2 = null;
			ResultSet rs = null, rs2 = null;
			String s = "";
			try {
				con = ConnectionToDataBase.getConnection();
				
				
			String selQr = 		" SELECT x.bwfl_id,                                                                                                    "+
								" SUM(x.bwfl_import_fee-(x.ap_import_fee+x.import_fee))as bwfl_import_fee_bal,                                         "+
								" SUM(x.bwfl_special_fee-(x.ap_special_fee+x.special_fee))as bwfl_special_fee_bal,                                     "+
								" SUM(x.fl2d_import_fee-(x.ap_import_fee+x.import_fee))as fl2d_import_fee_bal,                                         "+
								" SUM(x.fl2d_special_fee-(x.ap_special_fee+x.special_fee))as fl2d_special_fee_bal                                      "+
								" FROM                                                                                                                 "+
								" (SELECT a.int_distillery_id as bwfl_id, SUM(b.double_amt) as bwfl_import_fee, 0 as bwfl_special_fee,                 "+
								" 0 as fl2d_import_fee, 0 as fl2d_special_fee,                                                                         "+
								" 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee                                           "+
								" FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b                                                "+
								" WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type                                    "+
								" AND a.int_distillery_id='"+act.getBwflId()+"'                                                                        "+
								" AND b.vch_head_code in ('003900103030000','003900105040000','003900103030000') and b.vch_g6_head_code in ('01','03') "+
								" AND a.date_challan_date > '2019-06-16'                                                                               "+
								" group by bwfl_id                                                                                                     "+
								" UNION                                                                                                                "+
								" SELECT a.int_distillery_id as bwfl_id, 0 as bwfl_import_fee, SUM(b.double_amt) as bwfl_special_fee,                  "+
								" 0 as fl2d_import_fee, 0 as fl2d_special_fee,                                                                         "+
								" 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee                                           "+
								" FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b                                                "+
								" WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type                                    "+
								" AND a.int_distillery_id='"+act.getBwflId()+"'                                                                        "+
								" AND b.vch_head_code in ('003900105020000','003900103020000') and b.vch_g6_head_code in ('18','13')                   "+
								" AND a.date_challan_date > '2019-06-16'                                                                               "+
								" GROUP BY bwfl_id                                                                                                     "+
								" UNION                                                                                                                "+
								" SELECT a.int_distillery_id as bwfl_id, 0 as bwfl_import_fee, 0 as bwfl_special_fee,                                  "+
								" SUM(b.double_amt) as fl2d_import_fee, 0 as fl2d_special_fee,                                                         "+
								" 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee                                           "+
								" FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b                                                "+
								" WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type                                    "+
								" AND a.int_distillery_id='"+act.getBwflId()+"'                                                                        "+
								" AND b.vch_head_code in ('003900105040000','003900103030000','003900103030000') and b.vch_g6_head_code in ('04','05') "+
								" AND a.date_challan_date > '2019-06-16'                                                                               "+
								" GROUP BY bwfl_id                                                                                                     "+
								" UNION                                                                                                                "+
								" SELECT a.int_distillery_id as bwfl_id, 0 as bwfl_import_fee, 0 as bwfl_special_fee,                                  "+
								" 0 as fl2d_import_fee, SUM(b.double_amt) as fl2d_special_fee,                                                         "+
								" 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee                                           "+
								" FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b                                                "+
								" WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type                                    "+
								" AND a.int_distillery_id='"+act.getBwflId()+"'                                                                        "+
								" AND b.vch_head_code in ('003900105020000','003900103020000') and b.vch_g6_head_code in ('18','13')                   "+
								" AND a.date_challan_date > '2019-06-16'                                                                               "+
								" GROUP BY bwfl_id                                                                                                     "+
								" UNION                                                                                                                "+
								" SELECT a.bwfl_id, 0 as bwfl_import_fee, 0 as bwfl_special_fee,0 as fl2d_import_fee, 0 as fl2d_special_fee,           "+
								" SUM(a.import_fee) as ap_import_fee, SUM(COALESCE(a.special_fee,0)) as ap_special_fee,                                "+
								" 0 as import_fee, 0 as special_fee                                                                                    "+
								" FROM bwfl_license.import_permit a                                                                                    "+
								" WHERE a.bwfl_id='"+act.getBwflId()+"' AND a.vch_approved='APPROVED'                                                  "+
								" GROUP BY a.bwfl_id                                                                                                   "+
								" UNION                                                                                                                "+
								" SELECT a.bwfl_id, 0 as bwfl_import_fee, 0 as bwfl_special_fee,0 as fl2d_import_fee, 0 as fl2d_special_fee,           "+
								" 0 as ap_import_fee, 0 as ap_special_fee,                                                                             "+
								" SUM(a.import_fee) as import_fee, SUM(a.special_fee) as special_fee                                                   "+
								" FROM bwfl_license.import_permit a WHERE a.id='"+act.getRequestID()+"'                                                "+
								" GROUP BY a.bwfl_id)x GROUP BY x.bwfl_id  ";                                                                          
				
				
				
				/*String selQr = 	" SELECT x.bwfl_id, SUM(x.challan_duty-(x.ap_duty+x.duty))as bal_duty,                                 "+   
								" SUM(x.challan_import_fee-(x.ap_import_fee+x.import_fee))as bal_import_fees,                          "+     
								" SUM(x.challan_special_fee-(x.ap_special_fee+x.special_fee))as bal_special_fees                       "+     
								" FROM                                                                                                 "+ 
								" (SELECT a.int_distillery_id as bwfl_id, 0 as ap_import_fee, 0 as ap_duty,                            "+ 
								" 0 as ap_special_fee, 0 as import_fee, 0 as duty, 0 as special_fee,                                   "+
								" CASE                                                                                                 "+
								" WHEN b.vch_head_code='003900105010000' and 'BWFL2A'=a.vch_bwfl_shop_type THEN SUM(b.double_amt)      "+
								" WHEN b.vch_head_code='003900105010000' and 'BWFL2C'=a.vch_bwfl_shop_type THEN SUM(b.double_amt)      "+
								" WHEN b.vch_head_code='003900103010000' and 'BWFL2B'=a.vch_bwfl_shop_type THEN SUM(b.double_amt)      "+
								" WHEN b.vch_head_code='003900103010000' and 'BWFL2D'=a.vch_bwfl_shop_type                             "+
								" THEN SUM(b.double_amt) end as challan_duty,                                                          "+
								" CASE WHEN b.vch_head_code='003900105040000' and a.vch_bwfl_shop_type in ('BWFL2A','BWFL2C')          "+
								" THEN SUM(b.double_amt)                                                                               "+
								" WHEN b.vch_head_code='003900103030000' and a.vch_bwfl_shop_type in ('BWFL2B','BWFL2D')               "+
								" THEN SUM(b.double_amt) end as challan_import_fee,                                                    "+
								" CASE WHEN b.vch_head_code='003900102010000' and a.vch_bwfl_shop_type in ('BWFL2A','BWFL2C')          "+
								" THEN SUM(b.double_amt)                                                                               "+
								" WHEN b.vch_head_code='003900103030000' and a.vch_bwfl_shop_type in ('BWFL2B','BWFL2D')               "+
								" THEN SUM(b.double_amt) end as challan_special_fee                                                    "+
								" FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b                                "+ 
								" WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type                    "+
								" AND a.int_distillery_id='"+act.getBwflId()+"'                                                        "+                    
								" GROUP BY a.int_distillery_id, b.vch_head_code, a.vch_bwfl_shop_type                                  "+                       
								" UNION                                                                                                "+ 
								" SELECT a.bwfl_id, SUM(a.import_fee) as ap_import_fee , SUM(a.duty+a.add_duty) as ap_duty ,           "+ 
								" SUM(a.special_fee) as ap_special_fee, 0 as import_fee, 0 as duty, 0 as special_fee,                  "+ 
								" 0 as challan_duty, 0 as challan_import_fee, 0 as challan_special_fee                                 "+ 
								" FROM bwfl_license.import_permit a                                                                    "+
								" WHERE a.bwfl_id='"+act.getBwflId()+"' AND a.vch_approved='APPROVED'                                  "+
								" GROUP BY a.bwfl_id                                                                                   "+ 
								" UNION                                                                                                "+ 
								" SELECT a.bwfl_id, 0 as ap_import_fee, 0 as ap_duty, 0 as ap_special_fee,                             "+ 
								" SUM(a.import_fee) as import_fee , SUM(a.duty+a.add_duty) as duty, SUM(a.special_fee) as special_fee, "+ 
								" 0 as challan_duty, 0 as challan_import_fee, 0 as challan_special_fee                                 "+ 
								" FROM bwfl_license.import_permit a WHERE a.id='"+act.getRequestID()+"'                                "+                       
								" GROUP BY a.bwfl_id)x GROUP BY x.bwfl_id  ";*/

				

				pstmt = con.prepareStatement(selQr);

				rs = pstmt.executeQuery();
				
			//	System.out.println("==============bal recive=========="+selQr);

				while (rs.next()) {
					
					//act.setBalRgstrDuty(rs.getDouble("bal_duty"));
					act.setBalRgstrImportFee(rs.getDouble("bwfl_import_fee_bal"));
					act.setBalRgstrSpecialFee(rs.getDouble("bwfl_special_fee_bal"));
					act.setBalFL2DImportFee(rs.getDouble("fl2d_import_fee_bal"));
					act.setBalFL2DSpecialFee(rs.getDouble("fl2d_special_fee_bal"));
					
					//System.out.println("setBalRgstrImportFee================"+act.getBalRgstrImportFee());
				}

			} catch (SQLException se) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(se.getMessage(), se.getMessage()));
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
		
		
		public String getAdvancRgstrBalanceForPrint(FL2A_CSD_Permit_Approval_Action act){


			int id = 0;
			Connection con = null;
			PreparedStatement pstmt = null, ps2 = null;
			ResultSet rs = null, rs2 = null;
			String s = "";
			try {
				con = ConnectionToDataBase.getConnection();
				
				
			String selQr = 		" SELECT x.bwfl_id,                                                                                                    "+
								" SUM(x.bwfl_import_fee-(x.ap_import_fee+x.import_fee))as bwfl_import_fee_bal,                                         "+
								" SUM(x.bwfl_special_fee-(x.ap_special_fee+x.special_fee))as bwfl_special_fee_bal,                                     "+
								" SUM(x.fl2d_import_fee-(x.ap_import_fee+x.import_fee))as fl2d_import_fee_bal,                                         "+
								" SUM(x.fl2d_special_fee-(x.ap_special_fee+x.special_fee))as fl2d_special_fee_bal                                      "+
								" FROM                                                                                                                 "+
								" (SELECT a.int_distillery_id as bwfl_id, SUM(b.double_amt) as bwfl_import_fee, 0 as bwfl_special_fee,                 "+
								" 0 as fl2d_import_fee, 0 as fl2d_special_fee,                                                                         "+
								" 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee                                           "+
								" FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b                                                "+
								" WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type                                    "+
								" AND a.int_distillery_id='"+act.getBwflId()+"'                                                                        "+
								" AND b.vch_head_code in ('003900103030000','003900105040000','003900103030000') and b.vch_g6_head_code in ('01','03') "+
								" AND a.date_challan_date > '2019-06-16'                                                                               "+
								" group by bwfl_id                                                                                                     "+
								" UNION                                                                                                                "+
								" SELECT a.int_distillery_id as bwfl_id, 0 as bwfl_import_fee, SUM(b.double_amt) as bwfl_special_fee,                  "+
								" 0 as fl2d_import_fee, 0 as fl2d_special_fee,                                                                         "+
								" 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee                                           "+
								" FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b                                                "+
								" WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type                                    "+
								" AND a.int_distillery_id='"+act.getBwflId()+"'                                                                        "+
								" AND b.vch_head_code in ('003900105020000','003900103020000') and b.vch_g6_head_code in ('18','13')                   "+
								" AND a.date_challan_date > '2019-06-16'                                                                               "+
								" GROUP BY bwfl_id                                                                                                     "+
								" UNION                                                                                                                "+
								" SELECT a.int_distillery_id as bwfl_id, 0 as bwfl_import_fee, 0 as bwfl_special_fee,                                  "+
								" SUM(b.double_amt) as fl2d_import_fee, 0 as fl2d_special_fee,                                                         "+
								" 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee                                           "+
								" FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b                                                "+
								" WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type                                    "+
								" AND a.int_distillery_id='"+act.getBwflId()+"'                                                                        "+
								" AND b.vch_head_code in ('003900105040000','003900103030000','003900103030000') and b.vch_g6_head_code in ('04','05') "+
								" AND a.date_challan_date > '2019-06-16'                                                                               "+
								" GROUP BY bwfl_id                                                                                                     "+
								" UNION                                                                                                                "+
								" SELECT a.int_distillery_id as bwfl_id, 0 as bwfl_import_fee, 0 as bwfl_special_fee,                                  "+
								" 0 as fl2d_import_fee, SUM(b.double_amt) as fl2d_special_fee,                                                         "+
								" 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee                                           "+
								" FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b                                                "+
								" WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type                                    "+
								" AND a.int_distillery_id='"+act.getBwflId()+"'                                                                        "+
								" AND b.vch_head_code in ('003900105020000','003900103020000') and b.vch_g6_head_code in ('18','13')                   "+
								" AND a.date_challan_date > '2019-06-16'                                                                               "+
								" GROUP BY bwfl_id                                                                                                     "+
								" UNION                                                                                                                "+
								" SELECT a.bwfl_id, 0 as bwfl_import_fee, 0 as bwfl_special_fee,0 as fl2d_import_fee, 0 as fl2d_special_fee,           "+
								" SUM(a.import_fee) as ap_import_fee, SUM(COALESCE(a.special_fee,0)) as ap_special_fee,                                "+
								" 0 as import_fee, 0 as special_fee                                                                                    "+
								" FROM bwfl_license.import_permit a                                                                                    "+
								" WHERE a.bwfl_id='"+act.getBwflId()+"' AND a.vch_approved='APPROVED'                                                  "+		
								" GROUP BY a.bwfl_id)x GROUP BY x.bwfl_id  ";

				pstmt = con.prepareStatement(selQr);

				rs = pstmt.executeQuery();
				
				System.out.println("==============bal recive print=========="+selQr);

				while (rs.next()) {
					
					//act.setBalRgstrDuty(rs.getDouble("bal_duty"));
					act.setBalRgstrImportFee(rs.getDouble("bwfl_import_fee_bal"));
					act.setBalRgstrSpecialFee(rs.getDouble("bwfl_special_fee_bal"));
					act.setBalFL2DImportFee(rs.getDouble("fl2d_import_fee_bal"));
					act.setBalFL2DSpecialFee(rs.getDouble("fl2d_special_fee_bal"));
					
					System.out.println("setBalRgstrImportFee=======print========="+act.getBalRgstrImportFee());
				}

			} catch (SQLException se) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(se.getMessage(), se.getMessage()));
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
		
		public void getCsdDetail(FL2A_CSD_Permit_Approval_Action act){


			int id = 0;
			Connection con = null;
			PreparedStatement pstmt = null, ps2 = null;
			ResultSet rs = null, rs2 = null;
			String s = "";
			try {
				con = ConnectionToDataBase.getConnection();
				
				
			String selQr = 		" SELECT a.vch_applicant_name, a.vch_firm_name ,             "+                                    
								"	c.vch_undertaking_name as name, c.vch_reg_add  as address from licence.fl2_2b_2d_fl2a_csd_20_21 a,    "+
								"	fl2d.application_csd_permit_mst_20_21 b, dis_mst_pd1_pd2_lic c                                        "+
								"	where a.int_app_id=b.bwfl_id and c.int_app_id_f=b.unit_id and b.unit_id='"+act.getUnitID()+"' and b.unit_type='D'  "+                               
								"	union                                                                                           "+
								"	SELECT a.vch_applicant_name, a.vch_firm_name ,                                                  "+
								"	c.brewery_nm as name, c.vch_reg_address  as address from licence.fl2_2b_2d_fl2a_csd_20_21 a,          "+
								"	fl2d.application_csd_permit_mst_20_21 b, bre_mst_b1_lic c                                             "+
								"	where a.int_app_id=b.bwfl_id and c.vch_app_id_f=b.unit_id and  b.unit_id='"+act.getUnitID()+"' and b.unit_type='B' "+
								"	union                                                                                           "+
								"	SELECT a.vch_applicant_name, a.vch_firm_name ,                                                  "+
								"	c.vch_indus_name as name, c.vch_reg_office_add  as address from licence.fl2_2b_2d_fl2a_csd_20_21 a,   "+   
								"	fl2d.application_csd_permit_mst_20_21 b,public.other_unit_registration_20_21 c                              "+           
								"	where a.int_app_id=b.bwfl_id and c.int_app_id_f=b.unit_id and  b.unit_id='"+act.getUnitID()+"' and b.unit_type='O' "+
								"	union" +
								"   SELECT a.vch_applicant_name, a.vch_firm_name ,    "+
								"	c.vch_indus_name as name, c.vch_reg_office_add  as address from licence.fl2_2b_2d_fl2a_csd_20_21 a, "+
								"	fl2d.application_csd_permit_mst_20_21 b,public.other_unit_registration_20_21 c "+
								"	where a.int_app_id=b.bwfl_id and c.int_app_id_f=b.unit_id and  b.unit_id='"+act.getUnitID()+"' and c.vch_indus_type='IU'";                                 

				pstmt = con.prepareStatement(selQr);

				rs = pstmt.executeQuery();
				System.out.println("==============bal recive print=========="+selQr);

				if (rs.next()) {
				
					act.setCsd_name(rs.getString("vch_applicant_name"));
					act.setCsd_address(rs.getString("vch_firm_name"));
					act.setBrew_dist_name(rs.getString("name"));
					act.setBrew_dist_addres(rs.getString("address"));
					
				}

			} catch (SQLException se) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(se.getMessage(), se.getMessage()));
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
		

		
		}

		
		public void generateChallan(FL2A_CSD_Permit_Approval_Action act, FL2A_CSD_Permit_Approval_DT dt){


			boolean isValid = true;

			String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
			String relativePath = mypath + File.separator + "ExciseUp"
					+ File.separator + "HBR" + File.separator + "jasper";
			String relativePath1 = mypath + File.separator + "ExciseUp"+ File.separator + "HBR" + File.separator + "pdf";
			JasperReport jasperReport = null;
			JasperPrint jasperPrint = null;
			PreparedStatement ps = null;
			Connection con = null;
			ResultSet rs = null;
			String reportQuery = null;

			try {
				reportQuery = "select mst.vch_depositor_name,   mst.vch_reference_id, mst.vch_adderss, mst.vch_challan_id, mst.vch_assessment_year," +
						" mst.vch_deposite_period, mst.dat_created_date,mst.vch_remarks, mst.vch_trn_reference,"
						+ " hd.vch_rajaswa_head, hd.amount from licence.mst_challan_master mst, licence.challan_head_details hd " +
						" where mst.vch_reference_id = hd.vch_reference_id "
						+ "and mst.vch_challan_id = '"+dt.getChallanNo_dt()+"'";

				System.out.println("reprt query=="+reportQuery);
			 con = ConnectionToDataBase.getConnection();
				 

					 
					ps = con.prepareStatement(reportQuery);
					//ps.setString(1,dt.getChallanNo_dt());

					rs = ps.executeQuery();

					if (rs.next()) {

						isValid = true;
						rs = ps.executeQuery();
						JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);
						jasperReport = (JasperReport) JRLoader
								.loadObject(relativePath + File.separator
										+ "NewChallanReport.jasper");
						Map parameters = new HashMap();
						JasperPrint print = JasperFillManager.fillReport(
								jasperReport, parameters, jrRs);

						JasperExportManager.exportReportToPdfFile(print,
								relativePath1 + File.separator + "ExciseChallanReport"
										+ dt.getChallanNo_dt() + ".pdf");

						

						dt.setReportRender(true);

						

					}  else {
						dt.setReportRender(false);
						ResourceUtil.addErrorMessage(Constants.RECORD_NOT_AVAILABLE, Constants.RECORD_NOT_AVAILABLE);
					}

			} catch (Exception e) {
				isValid = false;
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

			
		
		}
		
		public ArrayList getRegisterDetail(FL2A_CSD_Permit_Approval_Action act, int unit_id){


			ArrayList list = new ArrayList();
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			int j = 1;
			String selQr = null;
			String filter = "";
            double total_depopsit = 0.0;
            double total_permit = 0.0;
            
			
			

			try {
				con = ConnectionToDataBase.getConnection();
                                                                                                                                           
				selQr = "SELECT int_id, unit_id, COALESCE(challan_no, 'NA') as challan_no,b.vch_applicant_name, date_challan_permit, deposit_amount, COALESCE(permit_number,'NA') as permit_number, "+
						"permit_amount, description, fn_year, challan_type "+
						"FROM fl2d.csd_advance_register, licence.fl2_2b_2d_fl2a_csd_20_21 b WHERE unit_id=b.int_app_id and unit_id="+unit_id+"";
						
						
						/*"  SELECT DISTINCT a.int_id, b.id, a.unit_id, a.unit_type, a.amount,                                                     "+
						"	 a.chalan_no, a.chalan_date, a.division, a.tres_id,                                                                  "+
						"	 CASE WHEN a.chalan_no=b.import_fee_challan_no THEN CONCAT('IMPORT FEE Challan Number- ',b.import_fee_challan_no)    "+
						"	 WHEN a.chalan_no=b.spcl_fee_challan_no THEN CONCAT('SPECIAL FEE Challan Number- ',b.spcl_fee_challan_no)            "+
						"	 END AS challan_nmbr, c.divname, d.tname                                                                             "+
						"	 FROM bwfl_license.chalan_deposit_bwfl_fl2d a, fl2d.application_csd_permit_mst_20_21 b,                                    "+  
						"	 licence.division c, licence.treasury d                                                                              "+
						"	 WHERE a.unit_type=b.login_type                                                                                      "+
						"	 AND a.division=c.divcode AND a.tres_id=d.tcode AND c.divcode=d.divcode                                          "+
						"    AND b.app_id="+act.getAppId()+"   " +
						"    AND a.chalan_no in ('"+act.getImportFeeChalanNo()+"', '"+act.getSpclFeeChalanNo()+"')  ";*/                                    

			

				ps = con.prepareStatement(selQr);
				System.out.println("register query---------------" + selQr);
				rs = ps.executeQuery();

				while (rs.next()) {

					FL2A_CSD_Permit_Approval_DT dt = new FL2A_CSD_Permit_Approval_DT();

					dt.setSrNo(j);
				    dt.setUnitName_dt(rs.getString("vch_applicant_name"));
				    dt.setChallan_permit_no(rs.getString("challan_no")+"/"+rs.getString("permit_number"));
				    dt.setPermitDate(rs.getDate("date_challan_permit"));
				    dt.setDeposite_amt(rs.getDouble("deposit_amount"));
				    dt.setPermit_amt(rs.getDouble("permit_amount"));
				    total_depopsit += rs.getDouble("deposit_amount");
				    total_permit += rs.getDouble("permit_amount");
			        j++;
					list.add(dt);
					
					
				}
				act.setTotal_deposit_amt(total_depopsit);
				act.setTotal_permit_amt(total_permit);
				act.setTotal_diff(total_depopsit - total_permit);
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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
		
		
		/*
		 * 
		 * This Method is Used to Fetch district Id
		 * Corresponding login Id
		 * 
		 */
		
		
		public int getDistrictId()
		{
			int districtId=0;
			
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql="select districtid,description from public.district where deo='"+ResourceUtil.getUserNameAllReq()+"'";
			try{
				conn=ConnectionToDataBase.getConnection();
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next())
				{
					districtId=rs.getInt("districtid");
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}finally{
				
				try{
					if(conn!=null)conn.close();
					if(pstmt!=null)pstmt.close();
					if(rs!=null)rs.close();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			
			return districtId;
			
		}
		
		
		
		
		
		
		
		
		
		
}
