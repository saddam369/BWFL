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


import com.mentor.action.FL2D_Permit_validity_ExtAction;
import com.mentor.action.PendingPermitAction;

import com.mentor.datatable.FL2D_Permit_validity_ExtDataTable;
import com.mentor.datatable.PendingPermitDT;

import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class FL2D_Permit_validity_ExtImpl {
	
	public ArrayList getDisplayListExt(FL2D_Permit_validity_ExtAction act) {

		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int j = 1;

		String selQr ="select a.app_id,a.login_type,a.bwfl_id, a.deo_date,a.permit_nmbr,b.vch_approval,a.id, a.district_id,a.vch_approved,a.valid_upto," +
				      " b.int_app_id,b.vch_firm_name,b.vch_licence_no,b.vch_license_type,b.core_district_id" +
				      " FROM bwfl_license.import_permit_20_21 a, licence.fl2_2b_2d_20_21 b where a.vch_approved = 'APPROVED' " +
				      " and b.vch_license_type = 'FL2D' and a.lic_no=b.vch_licence_no  " +
				      " AND a.deo_user='"+ ResourceUtil.getUserNameAllReq().trim()+ "' "+
				      " order by b.vch_firm_name,valid_upto ";
		
		System.out.println("DEOOOOOOOOOOO"+selQr);
	

		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(selQr);

			rs = ps.executeQuery();

			while (rs.next()) {
				FL2D_Permit_validity_ExtDataTable dt = new FL2D_Permit_validity_ExtDataTable();

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
		
		
	public void update(FL2D_Permit_validity_ExtAction act,FL2D_Permit_validity_ExtDataTable dt) {
		Connection con = null;
		PreparedStatement pstm = null;
		int savestatus = 0;
		ResultSet rs = null;
		Connection conn = null;
		ResultSet rs1 = null;
		try {
			String sql = "UPDATE bwfl_license.import_permit_20_21 set" +
					     " valid_upto='"+Utility.convertUtilDateToSQLDate(dt.getNewvalidityDate())+"'  " +
					     " where permit_nmbr = '"+dt.getPermitNmbr_dt()+"' ";
			
					
			 
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
	public boolean printReportFL2D(FL2D_Permit_validity_ExtDataTable dt){


		String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
		String relativePath = mypath + File.separator + "ExciseUp"+ File.separator + "Bond" + File.separator + "jasper";
		String relativePathpdf = mypath + File.separator + "ExciseUp"+ File.separator + "Bond" + File.separator + "FL2Dpermit";
		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		String reportQuery = null;
		boolean printFlag = false;

		try {
			con = ConnectionToDataBase.getConnection();

			/*	                                                                                                                                           
			reportQuery = 	" SELECT DISTINCT a.import_fee_challan_no, a.spcl_fee_challan_no, "+
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
					" FROM bwfl_license.import_permit_20_21 a, licence.fl2_2b_2d_20_21 b,                                                            "+
					" public.district c, bwfl_license.import_permit_dtl_20_21 d,                                                                     "+
					" public.other_unit_registration f,                                                                                        "+
					" distillery.brand_registration_20_21 br, distillery.packaging_details_20_21 pk                                            "+
					" WHERE a.bwfl_id=b.int_app_id AND a.district_id=c.districtid                                                              "+
					" AND a.id=d.fk_id AND a.district_id=d.district_id AND a.login_type=d.login_type AND a.app_id=d.app_id " +
					" AND a.unit_id=f.int_app_id_f   and CASE WHEN a.bwfl_type=1 THEN vch_indus_type='OUPD'   "
					+ "WHEN a.bwfl_type=2  THEN vch_indus_type='OUPB'      WHEN a.bwfl_type=3  THEN vch_indus_type='OUPW'  "
					+ "WHEN a.bwfl_type=4  THEN vch_indus_type='OUPBU'      WHEN a.bwfl_type=99  THEN vch_indus_type='IU'  end    AND br.brand_id=d.brand_id and br.brand_id=pk.brand_id_fk and d.pckg_id=pk.package_id                                    "+
					" AND a.app_id="+dt.getDigital_sign_appId_dt()+" ";
*/
      	  reportQuery =" SELECT DISTINCT mst.vch_firm_name,a.import_fee_challan_no, a.spcl_fee_challan_no,   "+                                                        
    				"(select DISTINCT amount from bwfl_license.chalan_deposit_bwfl_fl2d     "+
    				" where chalan_no=a.import_fee_challan_no and unit_id=a.bwfl_id and      "+
    				" unit_type=CASE WHEN a.bwfl_type=1 then 'BWFL2A' WHEN a.bwfl_type=2 THEN 'BWFL2B'  "+
    				" WHEN a.bwfl_type=3 THEN 'BWFL2C' WHEN a.bwfl_type=4 THEN 'BWFL2D' WHEN a.bwfl_type=99     "+
    				" THEN 'FL2D' END) as import_chalan_amnt,   "+
    				" (select DISTINCT amount from bwfl_license.chalan_deposit_bwfl_fl2d       "+
    				"  where chalan_no=a.spcl_fee_challan_no and unit_id=a.bwfl_id and              "+
    				"  unit_type=CASE WHEN a.bwfl_type=1 then 'BWFL2A' WHEN a.bwfl_type=2 THEN 'BWFL2B' "+
    				"  WHEN a.bwfl_type=3 THEN 'BWFL2C' WHEN a.bwfl_type=4 THEN 'BWFL2D' WHEN a.bwfl_type=99 THEN    "+
    				"  'FL2D' END) as spcl_chalan_amnt,                                                                  "+
    				"  (select DISTINCT chalan_date from   bwfl_license.chalan_deposit_bwfl_fl2d              "+
    				"   where chalan_no=a.import_fee_challan_no and unit_id=a.bwfl_id and              "+
    				"   unit_type=CASE WHEN a.bwfl_type=1 then 'BWFL2A' WHEN a.bwfl_type=2 THEN 'BWFL2B'  "+
    				"   WHEN a.bwfl_type=3 THEN 'BWFL2C' WHEN a.bwfl_type=4 THEN 'BWFL2D' WHEN a.bwfl_type=99    "+
    				"   THEN 'FL2D' END) as import_chalan_date,                                                     "+
    				"   (select DISTINCT chalan_date from  bwfl_license.chalan_deposit_bwfl_fl2d                    "+
    				"	where chalan_no=a.spcl_fee_challan_no and unit_id=a.bwfl_id and                            "+
    				"	unit_type=CASE WHEN a.bwfl_type=1 then 'BWFL2A' WHEN a.bwfl_type=2 THEN 'BWFL2B'           "+
    				"	WHEN a.bwfl_type=3 THEN 'BWFL2C' WHEN a.bwfl_type=4 THEN 'BWFL2D' WHEN a.bwfl_type=99      "+
    				"	THEN 'FL2D' END) as spcl_chalan_date,    a.id, a.district_id, a.bwfl_id, a.unit_id,         "+
    				"	a.bwfl_type, a.lic_no, a.import_fee as tot_import_fee,                                      "+
    				"	(a.duty+a.add_duty)as tot_duty, a.special_fee as tot_special_fee, a.cr_date,                "+
    				"	a.vch_approved, a.vch_status, a.deo_time, a.deo_remark, a.deo_user, a.deo_date,             "+
    				"	a.bottlng_type, a.valid_upto, a.route_road_radio, a.route_detail,                           "+
    				"	CASE WHEN a.bwfl_type=1 THEN 'BWFL2A'                                                       "+
    				"	WHEN a.bwfl_type=2 THEN 'BWFL2B'                                                            "+
    				"	WHEN a.bwfl_type=3 THEN 'BWFL2C'                                                            "+
    				"	WHEN a.bwfl_type=4 THEN 'BWFL2D'                                                            "+
    				"	WHEN a.bwfl_type=99 THEN 'FL2D' end as type,                                               "+
    				"	CASE WHEN a.route_road_radio='route' THEN 'By Train'                                        "+
    				"	WHEN a.route_road_radio='road' THEN 'By Road' end as route_type,                            "+
    				"	b.vch_firm_name,  b.vch_bond_address as vch_core_address, c.description,                    "+
    				"	CONCAT(b.vch_firm_name, '(' ,b.vch_bond_address,')')as licensee_nm_adrs,                    "+
    				"	(SELECT DISTINCT d.vch_state_name FROM public.state_ind d WHERE                            "+
    				"	 f.int_state_id=d.int_state_id)as vch_state_name,                                           "+
    				"	 d.no_of_cases, d.no_of_bottle_per_case, d.pland_no_of_bottles, a.permit_nmbr,              "+
    				"	 d.import_fee,                               (d.duty+d.add_duty) as duty,                   "+
    				"	 d.special_fee, a.import_fee_challan_no, a.spcl_fee_challan_no,                             "+
    				"	 f.vch_applicant_name as vch_indus_name, f.vch_bond_address as vch_reg_office_add, " +
    				"   concat(lic.vch_firm_name,'-',lic.vch_core_address) as consignor_nm_adrs,  "+
    				"	 d.brand_id, d.pckg_id, br.brand_name,                      "+
    				"	 pk.package_name, (pk.permit) as duty_rate,   "+
    				"	 round(CAST(float8(((d.pland_no_of_bottles)*pk.quantity)/1000) as numeric), 2) as bl, "+
    				"	CASE WHEN pk.package_type='1' THEN 'Glass Bottle'                                                                             "  +
					"	WHEN pk.package_type='2' THEN 'CAN'                                                                                           "  +
					"	WHEN pk.package_type='3' THEN 'Pet Bottle'                                                                                    "  +
					"	WHEN pk.package_type='4' THEN 'Tetra Pack'                                                                                    "  +
					"	WHEN pk.package_type='5' THEN 'Sachet'                                                                                        "  +
					"	WHEN pk.package_type='6' THEN 'Keg' end as liqour_type                                                                        "  +
    				"	 FROM bwfl_license.import_permit_20_21 a, custom_bonds.custom_bonds_master b,             "+
    				"	 public.district c, bwfl_license.import_permit_dtl_20_21 d,                               "+
    				"	 custom_bonds.mst_regis_importing_unit f,                                                 "+
    				"	 distillery.brand_registration_20_21 br, distillery.packaging_details_20_21 pk , "+
    				"	 custom_bonds.mst_regis_importing_unit mst, licence.fl2_2b_2d_20_21 lic "+
    				"	 WHERE a.custom_id=b.int_id AND a.district_id=c.districtid                                "+
    				"	 AND a.id=d.fk_id AND a.district_id=d.district_id AND a.login_type=d.login_type           "+
    				"	 AND a.app_id=d.app_id  AND a.unit_id=f.int_id  and  a.custom_id=b.int_id                 "+
    				"	 AND br.brand_id=d.brand_id and br.brand_id=pk.brand_id_fk and d.pckg_id=pk.package_id "+
    				"	 AND a.app_id="+dt.getDigital_sign_appId_dt()+" and a.bwfl_id=lic.int_app_id and "+
    				"	 mst.int_id=a.int_import_id and lic.vch_license_type='FL2D' ";
    					
        
			
		//	System.out.println("reportQuery---------------------"+reportQuery);
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
