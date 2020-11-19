package com.mentor.impl;



	import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

import com.mentor.action.CSD_Permit_validity_ExtAction;

import com.mentor.datatable.CSD_Permit_validity_ExtDT;

import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

	public class CSD_Permit_validity_ExtImpl {
		
		public ArrayList getDisplayListExt(CSD_Permit_validity_ExtAction act) {

			ArrayList list = new ArrayList();
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			int j = 1;

			String selQr ="select a.app_id,a.login_type,a.bwfl_id, a.deo_date,a.permit_nmbr,b.vch_approval,a.id, a.district_id,a.vch_approved,a.valid_upto," +
					      "  a.digital_sign_date, b.int_app_id,b.vch_firm_name,b.vch_licence_no,b.vch_license_type,b.core_district_id" +
					      " FROM fl2d.application_csd_permit_mst_20_21 a,  licence.fl2_2b_2d_20_21 b where a.vch_approved = 'APPROVED' " +
					      " and b.vch_license_type = 'FL2A' and a.lic_no=b.vch_licence_no  " +
					      " AND a.deo_user='"+ ResourceUtil.getUserNameAllReq().trim()+ "' "+
					      " order by b.vch_firm_name,valid_upto ";
			
			System.out.println("DEOOOOOOOOOOO"+selQr);
		

			try {
				con = ConnectionToDataBase.getConnection();
				ps = con.prepareStatement(selQr);

				rs = ps.executeQuery();

				while (rs.next()) {
					CSD_Permit_validity_ExtDT dt = new CSD_Permit_validity_ExtDT();

					dt.setSnothrd(j);
					dt.setNewvalidityDate(rs.getDate("valid_upto"));
					dt.setOldvalidityDate(rs.getDate("valid_upto"));
					dt.setLicenseNmbr_dt(rs.getString("vch_licence_no"));
					dt.setLicenseType_dt(rs.getString("vch_firm_name"));
					dt.setPermitNmbr_dt(rs.getString("permit_nmbr"));
				    dt.setPermitDt_dt(rs.getDate("deo_date"));
				    dt.setDigital_sign_permitNmbr_dt(rs.getString("permit_nmbr"));
					dt.setDigital_sign_appId_dt(rs.getString("app_id"));
					dt.setDigital_sign_loginType_dt(rs.getString("login_type"));
					dt.setDigital_sign_fl2d_dt(rs.getString("bwfl_id"));
					dt.setDigital_sign_date(rs.getDate("digital_sign_date"));
					
					
					j++;
					list.add(dt);
				}
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
				
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
			
			
		public void update(CSD_Permit_validity_ExtAction act,CSD_Permit_validity_ExtDT dt) {
			Connection con = null;
			PreparedStatement pstm = null;
			int savestatus = 0;
			ResultSet rs = null;
			Connection conn = null;
			ResultSet rs1 = null;
			try {
				String sql = "UPDATE fl2d.application_csd_permit_mst_20_21 set" +
						     " valid_upto='"+Utility.convertUtilDateToSQLDate(dt.getNewvalidityDate())+"'  " +
						     " where permit_nmbr = '"+dt.getPermitNmbr_dt()+"' ";
				
						
				System.out.println("=====update ====="+sql);
				
				con = ConnectionToDataBase.getConnection();
				pstm = con.prepareStatement(sql);
				savestatus = pstm.executeUpdate();

				if (savestatus > 0) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Data Updated", "Data Updated"));
					
				} else {
					FacesContext.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage("Data Not Updated",
											"Data Not Updated"));
				

				}

			} catch (Exception e) {
				e.printStackTrace();

			} finally {
				try {
					if (pstm != null)
						pstm.close();
					if (con != null)
						con.close();
					if (rs != null)
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		public boolean printReportFL2D(CSD_Permit_validity_ExtDT dt){


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

					                                                                                                                                           
				reportQuery =  "  SELECT DISTINCT a.tot_scanning_fee,a.total_corona_fee,a.duty,a.special_fee, a.import_fee as total_import_fee,a.id,a.district_id, COALESCE(import_fee_challan_no, 'Adjust From Advance Register') as import_fee_challan_no,COALESCE(a.spcl_fee_challan_no,'NA') as spcl_fee_challan_no," +
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
							"	AND c.deo='"+ResourceUtil.getUserNameAllReq()+"' and a.bwfl_id>0  and  a.app_id='"+dt.getDigital_sign_appId_dt()+"' AND br.brand_id=dtl.brand_id order by a.app_id    ";                                                                                                                                   
											
						
						/*" SELECT DISTINCT a.import_fee_challan_no, a.spcl_fee_challan_no, "+
						" (select amount from bwfl_license.chalan_deposit_bwfl_fl2d                                                                     "+
						" where chalan_no=a.import_fee_challan_no and unit_id=a.bwfl_id and                                                             "+
						" unit_type=CASE WHEN a.bwfl_type=1 then 'BWFL2A' WHEN a.bwfl_type=2 THEN 'BWFL2B'                                              "+
						" WHEN a.bwfl_type=3 THEN 'BWFL2C' WHEN a.bwfl_type=4 THEN 'BWFL2D' WHEN a.bwfl_type=99 THEN 'FL2D' END) as import_chalan_amnt, "+
						" (select amount from bwfl_license.chalan_deposit_bwfl_fl2d                                                                     "+
						" where chalan_no=a.spcl_fee_challan_no and unit_id=a.bwfl_id and                                                               "+
						" unit_type=CASE WHEN a.bwfl_type=1 then 'BWFL2A' WHEN a.bwfl_type=2 THEN 'BWFL2B'                                              "+
						" WHEN a.bwfl_type=3 THEN 'BWFL2C' WHEN a.bwfl_type=4 THEN 'BWFL2D' WHEN a.bwfl_type=99 THEN 'FL2D' END) as spcl_chalan_amnt,   "+
						" (select chalan_date from bwfl_license.chalan_deposit_bwfl_fl2d                                                                "+
						" where chalan_no=a.import_fee_challan_no and unit_id=a.bwfl_id and                                                             "+
						" unit_type=CASE WHEN a.bwfl_type=1 then 'BWFL2A' WHEN a.bwfl_type=2 THEN 'BWFL2B'                                              "+
						" WHEN a.bwfl_type=3 THEN 'BWFL2C' WHEN a.bwfl_type=4 THEN 'BWFL2D' WHEN a.bwfl_type=99 THEN 'FL2D' END) as import_chalan_date, "+ 
						" (select chalan_date from bwfl_license.chalan_deposit_bwfl_fl2d                                                                "+
						" where chalan_no=a.spcl_fee_challan_no and unit_id=a.bwfl_id and                                                               "+
						" unit_type=CASE WHEN a.bwfl_type=1 then 'BWFL2A' WHEN a.bwfl_type=2 THEN 'BWFL2B'                                              "+
						" WHEN a.bwfl_type=3 THEN 'BWFL2C' WHEN a.bwfl_type=4 THEN 'BWFL2D' WHEN a.bwfl_type=99 THEN 'FL2D' END) as spcl_chalan_date,   "+
						" a.id, a.district_id, a.bwfl_id, a.unit_id, a.bwfl_type, a.lic_no,                                        "+       
						" a.import_fee as tot_import_fee,                                                                                          "+
						" (a.duty+a.add_duty)as tot_duty, a.special_fee as tot_special_fee, a.cr_date,                                             "+
						" a.vch_approved, a.vch_status, a.deo_time, a.deo_remark, a.deo_user, a.deo_date,                                          "+
						" a.bottlng_type, a.valid_upto, a.route_road_radio, a.route_detail,                                                        "+
						" CASE WHEN a.bwfl_type=1 THEN 'BWFL2A'                                                                                    "+
						" WHEN a.bwfl_type=2 THEN 'BWFL2B'                                                                                         "+
						" WHEN a.bwfl_type=3 THEN 'BWFL2C'                                                                                         "+
						" WHEN a.bwfl_type=4 THEN 'BWFL2D'                                                                                         "+
						" WHEN a.bwfl_type=99 THEN 'FL2D' end as type,                                                                             "+
						" CASE WHEN a.route_road_radio='route' THEN 'By Train' "+
						" WHEN a.route_road_radio='road' THEN 'By Road' end as route_type,  "+
						" b.vch_firm_name,  b.vch_core_address, c.description,                                                                     "+
						" CONCAT(b.vch_firm_name, '(' ,b.vch_core_address,')')as licensee_nm_adrs,                                                 "+ 
						" (SELECT d.vch_state_name FROM public.state_ind d WHERE f.vch_reg_office_state=d.int_state_id::text)as vch_state_name,    "+
						" (SELECT d.vch_country_name FROM public.country_mst d WHERE f.vch_wrk_office_country=d.int_country_id::text)as country_name,"+
						" d.no_of_cases, d.no_of_bottle_per_case, d.pland_no_of_bottles, a.permit_nmbr, d.import_fee,                              "+
						" (d.duty+d.add_duty) as duty, d.special_fee, a.import_fee_challan_no, a.spcl_fee_challan_no,                              "+
						" f.vch_indus_name, f.vch_reg_office_add, a.consignor_nm_adrs,                                                             "+  
						" d.brand_id, d.pckg_id, concat(br.brand_name,', Country-',CASE WHEN br.fl2d_country_code IS NULL THEN  "+
						" (SELECT d.vch_country_name FROM public.country_mst d WHERE d.int_country_id::text=f.vch_wrk_office_country)  "+
						" else (SELECT d.vch_country_name FROM public.country_mst d WHERE d.int_country_id=br.fl2d_country_code) end) as brand_name, "+
						" pk.package_name, (pk.permit) as duty_rate,                                                                                           "+
						" round(CAST(float8(((d.pland_no_of_bottles)*pk.quantity)/1000) as numeric), 2) as bl                                      "+
						" FROM bwfl_license.import_permit a, licence.fl2_2b_2d_19_20 b,                                                            "+
						" public.district c, bwfl_license.import_permit_dtl d,                                                                     "+
						" public.other_unit_registration f,                                                                                        "+
						" distillery.brand_registration_19_20 br, distillery.packaging_details_19_20 pk                                            "+
						" WHERE a.bwfl_id=b.int_app_id AND a.district_id=c.districtid                                                              "+
						" AND a.id=d.fk_id AND a.district_id=d.district_id AND a.login_type=d.login_type AND a.app_id=d.app_id " +
						" AND a.unit_id=f.int_app_id_f   and CASE WHEN a.bwfl_type=1 THEN vch_indus_type='OUPD'   "
						+ "WHEN a.bwfl_type=2  THEN vch_indus_type='OUPB'      WHEN a.bwfl_type=3  THEN vch_indus_type='OUPW'  "
						+ "WHEN a.bwfl_type=4  THEN vch_indus_type='OUPBU'      WHEN a.bwfl_type=99  THEN vch_indus_type='IU'  end    AND br.brand_id=d.brand_id and br.brand_id=pk.brand_id_fk and d.pckg_id=pk.package_id                                    "+
						" AND a.app_id="+dt.getDigital_sign_appId_dt()+" ";
					*/
				
		System.out.println("reportQuery---------------------"+reportQuery);
				pst = con.prepareStatement(reportQuery);
				rs = pst.executeQuery();

				if (rs.next()) {
					rs = pst.executeQuery();

					Map parameters = new HashMap();
					parameters.put("REPORT_CONNECTION", con);
					//parameters.put("SUBREPORT_DIR", relativePath+File.separator);
					parameters.put("image", relativePath + File.separator);					

					JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);
					
					jasperReport = (JasperReport) JRLoader.loadObject(relativePath+ File.separator+ "FL2DImportPermit.jasper");
					JasperPrint print = JasperFillManager.fillReport(jasperReport,parameters, jrRs);
					
					Random rand = new Random();
					int n = rand.nextInt(250) + 1;

					JasperExportManager.exportReportToPdfFile(print,relativePathpdf + File.separator+ dt.getPermitNmbr_dt().trim().replaceAll("\\s+","")+".pdf");
					
					//dt.setPdfName(dt.getAppId_dt()+ "-"+dt.getBwflID_dt()+"_"+dt.getLoginType_dt()+"ImportPermit.pdf");	
					//dt.setPdfName(dt.getPermitNmbr_dt()+".pdf");
					//this.updatePermit(act, dt.getRequestID_dt(),dt.getDistrictId_dt(), dt.getLoginType_dt(), dt.getAppId_dt(),dt.getPdfName());
					
				
		
			dt.setDigital_sign_Flag(true);
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
	}

