package com.mentor.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mentor.action.FL2_2B_StockAction19_20;
import com.mentor.datatable.FL2_2B_StockDataTable19_20;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;
import com.mentor.xml.GATEPASS_DETAIL;
import com.mentor.xml.GATEPASS_DETAILS;

public class FL2_2B_StockImpl19_20 {

	public String getDetails(FL2_2B_StockAction19_20 ac) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s = "";
		try { 
			con = ConnectionToDataBase.getConnection();
			String queryList = 	" SELECT int_app_id, vch_applicant_name, vch_firm_name ," +
					" vch_mobile_no,vch_license_type, vch_licence_no " +
					" FROM licence.fl2_2b_2d_20_21 WHERE loginid= ? ";

			pstmt = con.prepareStatement(queryList);
			pstmt.setString(1, ResourceUtil.getUserNameAllReq().trim());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ac.setName(rs.getString("vch_firm_name"));
				ac.setDist_id(rs.getInt("int_app_id"));
				ac.setLoginType(rs.getString("vch_license_type"));
				ac.setLicenseNmbr(rs.getString("vch_licence_no"));
				if(rs.getString("vch_license_type").equalsIgnoreCase("CL2"))
				{
					 
					ac.setLicenseing("D");
				}


			}else {rs.close();pstmt.close();
				  queryList = 	" SELECT int_app_id, vch_applicant_name, vch_firm_name ," +
						" vch_mobile_no,vch_license_type, vch_licence_no " +
						" FROM licence.fl2_2b_2d_19_20 WHERE loginid= ? ";

				pstmt = con.prepareStatement(queryList);
				pstmt.setString(1, ResourceUtil.getUserNameAllReq().trim());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					ac.setName(rs.getString("vch_firm_name"));
					ac.setDist_id(rs.getInt("int_app_id"));
					ac.setLoginType(rs.getString("vch_license_type"));
					ac.setLicenseNmbr(rs.getString("vch_licence_no"));
					if(rs.getString("vch_license_type").equalsIgnoreCase("CL2"))
					{
						 
						ac.setLicenseing("D");
					}


				}
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

	// ----------------- check gatePass -------------------------------------

	public boolean checkgatePass(FL2_2B_StockAction19_20 action) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean pass = false;
		String query = "";

		try {
			if (action.getLoginType().equalsIgnoreCase("FL2") ) {

				query = "	 SELECT a.vch_gatepass_no from distillery.gatepass_to_districtwholesale_19_20 a , distillery.fl2_stock_trxn_19_20 b  "
						+ "	 where a.vch_gatepass_no=b.vch_gatepass_no "
						+ "	 and a.vch_gatepass_no= ? AND a.dt_date=? ";

			} else if (action.getLoginType().equalsIgnoreCase("FL2B")) {
				query = "	SELECT a.vch_gatepass_no FROM bwfl.gatepass_to_districtwholesale_19_20 a, bwfl.fl2_stock_trxn_19_20 b "
						+ "	WHERE a.vch_gatepass_no=b.vch_gatepass_no "
						+ "	AND a.vch_gatepass_no=? AND a.dt_date=?  ";

			}
			else if (action.getLoginType().equalsIgnoreCase("CL2")) {
				query = "	SELECT a.vch_gatepass_no FROM distillery.gatepass_to_manufacturewholesale_cl_19_20 a, distillery.cl2_stock_trxn_19_20 b "
						+ "	WHERE a.vch_gatepass_no=b.vch_gatepass_no "
						+ "	AND a.vch_gatepass_no=? AND a.dt_date=?  ";

			}else {
				query = "";
			}
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, action.getGatePassNo());
			//pstmt.setString(2, action.getLicenseNmbr());
			pstmt.setDate(2, Utility.convertUtilDateToSQLDate(action.getCreatedDate()));

			rs = pstmt.executeQuery();
			if (rs.next()) {

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
				// e.printStackTrace();
			}
		}
		return pass;
	}

	// -----------------------------------------------------------------
	public ArrayList gatePassDetail(FL2_2B_StockAction19_20 action) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		int i = 1,cases=0;

		try {
			 SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			  


			 if(action.getOldnew().equalsIgnoreCase("O"))
			 {
				 if (action.getLicenseing().equalsIgnoreCase("D") && !action.getLoginType().equalsIgnoreCase("CL2")) {

						selQr =

								"	SELECT  a.int_app_id_f as unitid,a.vch_undertaking_name, b.vch_gatepass_no,b.vehicle_driver_name,a.vch_wrk_add, "
										+ "	b.vch_from, b.vch_to, b.vch_from,b.vch_from_lic_no, b.vch_to_lic_no,  b.curr_date, b.licencee_id,  "
										+ "	b.vch_route_detail, b.vch_vehicle_no, b.valid_till,b.vehicle_agency_name_adrs,  "
										+ "	b.licensee_name, b.licensee_adrs  ,c.dispatch_box as dispatchd_box,b.gross_weight, b.tier_weight, b.net_weight,  "
										+ "	b.valid_till, c.int_brand_id, c.size as size, c.dispatch_bottle as dispatch_bottle, c.vch_batch_no , c.int_pckg_id,  "
										+ "	d.brand_name, d.strength, e.package_name,e.box_id,f.qnt_ml_detail as qnt_ml_detail, 	"
										+ "	b.vehicle_agency_name_adrs as vch_firm_name,  c.vch_batch_no,b.dt_date,	  "
										+ "	(((c.dispatch_bottle)*f.qnt_ml_detail)/1000) as bl, 	"
										+ "	((((c.dispatch_bottle*f.qnt_ml_detail)/1000)*d.strength)/100) as al 	 "
										+ "	FROM public.dis_mst_pd1_pd2_lic a, distillery.gatepass_to_districtwholesale_19_20 b,   "
										+ "	distillery.fl2_stock_trxn_19_20 c, distillery.brand_registration_19_20 d,  	 "
										+ "	distillery.packaging_details_19_20 e, distillery.box_size_details f 	 "
										+ "	WHERE a.int_app_id_f=b.int_dist_id AND b.int_dist_id=c.int_dissleri_id AND b.vch_gatepass_no=c.vch_gatepass_no  "
										+ "	and b.dt_date=c.dt AND c.int_brand_id=d.brand_id AND d.brand_id=e.brand_id_fk AND c.int_pckg_id=e.package_id 	 "
										+ "	AND e.box_id=f.box_id    "
										+ 
										"	AND f.qnt_ml_detail=e.quantity AND c.dispatch_bottle>0 " +
										" AND b.vch_gatepass_no='"+action.getGatePassNo()+"' and b.rcvdt is null AND b.vch_to='"+action.getLoginType()+"'  "
												+ " and trim(b.vch_to_lic_no) in (SELECT  vch_licence_no   FROM licence.fl2_2b_2d_19_20 WHERE loginid= '"+ ResourceUtil.getUserNameAllReq().trim()+"') ";
					}
					else if (action.getLicenseing().equalsIgnoreCase("D") && action.getLoginType().equalsIgnoreCase("CL2")) {

						selQr =

								"	SELECT  a.int_app_id_f as unitid,a.vch_undertaking_name, b.vch_gatepass_no,b.vehicle_driver_name,a.vch_wrk_add, "
										+ "	   b.curr_date, b.vch_from as vch_from,  "
										+ "	 c.dispatchd_box  , c.int_brand_id, c.size as  size, c.dispatchd_bottl as dispatch_bottle,   c.int_pckg_id,f.qnt_ml_detail ,  "
										+ "	d.brand_name, d.strength, e.package_name,e.box_id,f.qnt_ml_detail as qnt_ml_detail, 	"
										+ "	   b.dt_date,'' as 	vch_batch_no,  "
										+ "	(((c.dispatchd_bottl)*f.qnt_ml_detail)/1000) as bl, 	"
										+ "	((((c.dispatchd_bottl*f.qnt_ml_detail)/1000)*d.strength)/100) as al 	 "
										+ "	FROM public.dis_mst_pd1_pd2_lic a, distillery.gatepass_to_manufacturewholesale_cl_19_20 b,   "
										+ "	distillery.cl2_stock_trxn_19_20 c, distillery.brand_registration_19_20 d,  	 "
										+ "	distillery.packaging_details_19_20 e, distillery.box_size_details f 	 "
										+ "	WHERE a.int_app_id_f=b.int_dist_id AND b.int_dist_id=c.int_dissleri_id AND b.vch_gatepass_no=c.vch_gatepass_no  "
										+ "	and b.dt_date=c.dt_date AND c.int_brand_id=d.brand_id AND d.brand_id=e.brand_id_fk AND c.int_pckg_id=e.package_id 	 "
										+ "	AND e.box_id=f.box_id   "
										+ 
										"	AND f.qnt_ml_detail=e.quantity AND c.dispatchd_bottl>0 AND c.dispatchd_box>0 " +
										" AND b.vch_gatepass_no='"+action.getGatePassNo()+"' and b.rcvdt is null AND b.vch_to='"+action.getLoginType()+"' "
												+ " AND trim(b.vch_to_lic_no) in (SELECT  vch_licence_no   FROM licence.fl2_2b_2d_19_20 WHERE loginid= '"+ ResourceUtil.getUserNameAllReq().trim()+"') ";

					}

					else if (action.getLicenseing().equalsIgnoreCase("B")) {

						selQr =


								" SELECT DISTINCT a.vch_app_id_f as unitid,a.brewery_nm, a.vch_reg_address,b.vch_gatepass_no,b.vehicle_driver_name, " +
										" d.strength,  b.vch_from, b.vch_to, b.vch_from_lic_no, b.vch_to_lic_no,b.dt_date, b.curr_date, " +
										" b.licencee_id, b.vch_route_detail, b.vch_vehicle_no, b.valid_till,b.vehicle_agency_name_adrs,  " +
										" b.licensee_name, b.licensee_adrs, c.dispatch_box as dispatchd_box, b.valid_till, c.int_brand_id, " +
										" c.size as size, c.dispatch_bottle as dispatch_bottle, c.vch_batch_no, c.int_pckg_id , " +
										" d.brand_name, e.package_name,e.box_id,f.qnt_ml_detail as qnt_ml_detail, " +
										" b.vehicle_agency_name_adrs as vch_firm_name, c.vch_batch_no, " +
										" (((c.dispatch_bottle)*f.qnt_ml_detail)/1000) as bl, " +
										" ((((c.dispatch_bottle*f.qnt_ml_detail)/1000)*42.8)/100) as al " +
										" FROM public.bre_mst_b1_lic  a, bwfl.gatepass_to_districtwholesale_19_20 b, " +
										" bwfl.fl2_stock_trxn_19_20 c, distillery.brand_registration_19_20 d, " +
										" distillery.packaging_details_19_20 e, distillery.box_size_details f " +
										" WHERE a.vch_app_id_f=b.brewery_id AND   b.brewery_id=c.brewery_id " +
										" AND b.vch_gatepass_no=c.vch_gatepass_no AND b.dt_date=c.dt AND c.int_brand_id=d.brand_id " +
										" AND d.brand_id=e.brand_id_fk AND c.int_pckg_id=e.package_id  and b.vch_finalize='F' " +//
										" AND e.box_id=f.box_id and f.qnt_ml_detail=e.quantity AND c.dispatch_bottle>0 AND c.dispatch_box>0 " +
										" AND b.vch_gatepass_no='"+action.getGatePassNo()+"' and b.rcvdt is null AND b.vch_to='FL2B' AND trim(b.vch_to_lic_no) in (SELECT  vch_licence_no   FROM licence.fl2_2b_2d_19_20 WHERE loginid= '"+ ResourceUtil.getUserNameAllReq().trim()+"') ";


					} else if(action.getLicenseing().equalsIgnoreCase("FL2D")) {
						selQr = " SELECT	'FL2D' as vch_from, a.int_fl2d_id as unitid, a.vch_lic_no, a.dt, a.int_brand_id,b.vch_from, a.int_pckg_id,a.size, a.vch_batch_no,       "+
								" a.dispatch_bottle as dispatch_bottle, a.dispatch_box as dispatchd_box,c.brand_name,d.package_name,d.quantity as qnt_ml_detail  "+
								" FROM fl2d.fl2d_stock_trxn_19_20 a, fl2d.gatepass_to_districtwholesale_fl2d_19_20 b,                                                        "+
								" distillery.brand_registration_19_20 c,distillery.packaging_details_19_20 d                                                                 "+
								" where c.brand_id=a.int_brand_id AND a.vch_gatepass_no=b.vch_gatepass_no                                                        "+
								" AND a.int_fl2d_id=b.int_fl2d_id                                                                                                "+
								" AND  b.dt_date=a.dt AND a.int_pckg_id=d.package_id                                                                             "+
								"  AND  b.vch_gatepass_no='"+action.getGatePassNo()+"' and b.rcvdt is null AND b.vch_to='"+action.getLoginType()+"' "
										+ "and b.vch_finalize='F' AND trim(b.vch_to_lic_no) in (SELECT  vch_licence_no   FROM licence.fl2_2b_2d_19_20 WHERE loginid= '"+ ResourceUtil.getUserNameAllReq().trim()+"')" ;  



					}

					else if(action.getLicenseing().equalsIgnoreCase("BWFL")) {
						selQr = "SELECT	'BWFL' as vch_from, a.int_bwfl_id as unitid, a.vch_lic_no, a.dt, a.int_brand_id,b.vch_from, a.int_pckg_id,a.size, a.vch_batch_no," +
								" a.dispatch_bottle as dispatch_bottle, a.dispatch_box as dispatchd_box,c.brand_name,d.package_name,d.quantity as qnt_ml_detail " +
								"FROM bwfl_license.fl2_stock_trxn_bwfl_19_20 a, bwfl_license.gatepass_to_districtwholesale_bwfl_19_20 b," +
								"distillery.brand_registration_19_20 c,distillery.packaging_details_19_20 d where c.brand_id=a.int_brand_id AND a.vch_gatepass_no=b.vch_gatepass_no "+
								" AND a.int_bwfl_id=b.int_bwfl_id and b.vch_finalize='F' "+//
								" AND  b.dt_date=a.dt AND a.int_pckg_id=d.package_id  "+
								"  and b.rcvdt is null and b.vch_gatepass_no='"+action.getGatePassNo().trim()+"'  "
										+ " AND trim(b.vch_to_lic_no) in (SELECT  vch_licence_no   FROM licence.fl2_2b_2d_19_20 WHERE loginid= '"+ ResourceUtil.getUserNameAllReq().trim()+"')";//AND b.vch_to='"+action.getLoginType()+"'
					}
			 }else{
				 if (action.getLicenseing().equalsIgnoreCase("D") && !action.getLoginType().equalsIgnoreCase("CL2")) {

						selQr =

								"	SELECT  a.int_app_id_f as unitid,a.vch_undertaking_name, b.vch_gatepass_no,b.vehicle_driver_name,a.vch_wrk_add, "
										+ "	b.vch_from, b.vch_to, b.vch_from,b.vch_from_lic_no, b.vch_to_lic_no,  b.curr_date, b.licencee_id,  "
										+ "	b.vch_route_detail, b.vch_vehicle_no, b.valid_till,b.vehicle_agency_name_adrs,  "
										+ "	b.licensee_name, b.licensee_adrs  ,c.dispatch_box as dispatchd_box,b.gross_weight, b.tier_weight, b.net_weight,  "
										+ "	b.valid_till, c.int_brand_id, c.size as size, c.dispatch_bottle as dispatch_bottle, c.vch_batch_no , c.int_pckg_id,  "
										+ "	d.brand_name, d.strength, e.package_name,e.box_id,f.qnt_ml_detail as qnt_ml_detail, 	"
										+ "	b.vehicle_agency_name_adrs as vch_firm_name,  c.vch_batch_no,b.dt_date,	  "
										+ "	(((c.dispatch_bottle)*f.qnt_ml_detail)/1000) as bl, 	"
										+ "	((((c.dispatch_bottle*f.qnt_ml_detail)/1000)*d.strength)/100) as al 	 "
										+ "	FROM public.dis_mst_pd1_pd2_lic a, distillery.gatepass_to_districtwholesale_19_20 b,   "
										+ "	distillery.fl2_stock_trxn_19_20 c, distillery.brand_registration_19_20 d,  	 "
										+ "	distillery.packaging_details_19_20 e, distillery.box_size_details f 	 "
										+ "	WHERE a.int_app_id_f=b.int_dist_id AND b.int_dist_id=c.int_dissleri_id AND b.vch_gatepass_no=c.vch_gatepass_no  "
										+ "	and b.dt_date=c.dt AND c.int_brand_id=d.brand_id AND d.brand_id=e.brand_id_fk AND c.int_pckg_id=e.package_id 	 "
										+ "	AND e.box_id=f.box_id    "
										+ 
										"	AND f.qnt_ml_detail=e.quantity AND c.dispatch_bottle>0 " +
										" AND b.vch_gatepass_no='"+action.getGatePassNo()+"' and b.rcvdt is null AND b.vch_to='"+action.getLoginType()+"'   and trim(b.vch_to_lic_no)='"+action.getLicenseNmbr().trim()+"' ";
					}
					else if (action.getLicenseing().equalsIgnoreCase("D") && action.getLoginType().equalsIgnoreCase("CL2")) {

						selQr =

								"	SELECT  a.int_app_id_f as unitid,a.vch_undertaking_name, b.vch_gatepass_no,b.vehicle_driver_name,a.vch_wrk_add, "
										+ "	   b.curr_date, b.vch_from as vch_from,  "
										+ "	 c.dispatchd_box  , c.int_brand_id, c.size as  size, c.dispatchd_bottl as dispatch_bottle,   c.int_pckg_id,f.qnt_ml_detail ,  "
										+ "	d.brand_name, d.strength, e.package_name,e.box_id,f.qnt_ml_detail as qnt_ml_detail, 	"
										+ "	   b.dt_date,'' as 	vch_batch_no,  "
										+ "	(((c.dispatchd_bottl)*f.qnt_ml_detail)/1000) as bl, 	"
										+ "	((((c.dispatchd_bottl*f.qnt_ml_detail)/1000)*d.strength)/100) as al 	 "
										+ "	FROM public.dis_mst_pd1_pd2_lic a, distillery.gatepass_to_manufacturewholesale_cl_19_20 b,   "
										+ "	distillery.cl2_stock_trxn_19_20 c, distillery.brand_registration_19_20 d,  	 "
										+ "	distillery.packaging_details_19_20 e, distillery.box_size_details f 	 "
										+ "	WHERE a.int_app_id_f=b.int_dist_id AND b.int_dist_id=c.int_dissleri_id AND b.vch_gatepass_no=c.vch_gatepass_no  "
										+ "	and b.dt_date=c.dt_date AND c.int_brand_id=d.brand_id AND d.brand_id=e.brand_id_fk AND c.int_pckg_id=e.package_id 	 "
										+ "	AND e.box_id=f.box_id   "
										+ 
										"	AND f.qnt_ml_detail=e.quantity AND c.dispatchd_bottl>0 AND c.dispatchd_box>0 " +
										" AND b.vch_gatepass_no='"+action.getGatePassNo()+"' and b.rcvdt is null AND b.vch_to='"+action.getLoginType()+"' AND trim(b.vch_to_lic_no)='"+action.getLicenseNmbr().trim()+"' ";

					}

					else if (action.getLicenseing().equalsIgnoreCase("B")) {

						selQr =


								" SELECT DISTINCT a.vch_app_id_f as unitid,a.brewery_nm, a.vch_reg_address,b.vch_gatepass_no,b.vehicle_driver_name, " +
										" d.strength,  b.vch_from, b.vch_to, b.vch_from_lic_no, b.vch_to_lic_no,b.dt_date, b.curr_date, " +
										" b.licencee_id, b.vch_route_detail, b.vch_vehicle_no, b.valid_till,b.vehicle_agency_name_adrs,  " +
										" b.licensee_name, b.licensee_adrs, c.dispatch_box as dispatchd_box, b.valid_till, c.int_brand_id, " +
										" c.size as size, c.dispatch_bottle as dispatch_bottle, c.vch_batch_no, c.int_pckg_id , " +
										" d.brand_name, e.package_name,e.box_id,f.qnt_ml_detail as qnt_ml_detail, " +
										" b.vehicle_agency_name_adrs as vch_firm_name, c.vch_batch_no, " +
										" (((c.dispatch_bottle)*f.qnt_ml_detail)/1000) as bl, " +
										" ((((c.dispatch_bottle*f.qnt_ml_detail)/1000)*42.8)/100) as al " +
										" FROM public.bre_mst_b1_lic  a, bwfl.gatepass_to_districtwholesale_19_20 b, " +
										" bwfl.fl2_stock_trxn_19_20 c, distillery.brand_registration_19_20 d, " +
										" distillery.packaging_details_19_20 e, distillery.box_size_details f " +
										" WHERE a.vch_app_id_f=b.brewery_id AND   b.brewery_id=c.brewery_id " +
										" AND b.vch_gatepass_no=c.vch_gatepass_no AND b.dt_date=c.dt AND c.int_brand_id=d.brand_id " +
										" AND d.brand_id=e.brand_id_fk AND c.int_pckg_id=e.package_id  and b.vch_finalize='F' " +//
										" AND e.box_id=f.box_id and f.qnt_ml_detail=e.quantity AND c.dispatch_bottle>0 AND c.dispatch_box>0 " +
										" AND b.vch_gatepass_no='"+action.getGatePassNo()+"' and b.rcvdt is null AND b.vch_to='FL2B' AND trim(b.vch_to_lic_no)='"+action.getLicenseNmbr().trim()+"' ";


					} else if(action.getLicenseing().equalsIgnoreCase("FL2D")) {
						selQr = " SELECT	'FL2D' as vch_from, a.int_fl2d_id as unitid, a.vch_lic_no, a.dt, a.int_brand_id,b.vch_from, a.int_pckg_id,a.size, a.vch_batch_no,       "+
								" a.dispatch_bottle as dispatch_bottle, a.dispatch_box as dispatchd_box,c.brand_name,d.package_name,d.quantity as qnt_ml_detail  "+
								" FROM fl2d.fl2d_stock_trxn_19_20 a, fl2d.gatepass_to_districtwholesale_fl2d_19_20 b,                                                        "+
								" distillery.brand_registration_19_20 c,distillery.packaging_details_19_20 d                                                                 "+
								" where c.brand_id=a.int_brand_id AND a.vch_gatepass_no=b.vch_gatepass_no                                                        "+
								" AND a.int_fl2d_id=b.int_fl2d_id                                                                                                "+
								" AND  b.dt_date=a.dt AND a.int_pckg_id=d.package_id                                                                             "+
								"  AND  b.vch_gatepass_no='"+action.getGatePassNo()+"' and b.rcvdt is null AND b.vch_to='"+action.getLoginType()+"' and b.vch_finalize='F' AND trim(b.vch_to_lic_no)='"+action.getLicenseNmbr().trim()+"'" ;  



					}

					else if(action.getLicenseing().equalsIgnoreCase("BWFL")) {
						selQr = "SELECT	'BWFL' as vch_from, a.int_bwfl_id as unitid, a.vch_lic_no, a.dt, a.int_brand_id,b.vch_from, a.int_pckg_id,a.size, a.vch_batch_no," +
								" a.dispatch_bottle as dispatch_bottle, a.dispatch_box as dispatchd_box,c.brand_name,d.package_name,d.quantity as qnt_ml_detail " +
								"FROM bwfl_license.fl2_stock_trxn_bwfl_19_20 a, bwfl_license.gatepass_to_districtwholesale_bwfl_19_20 b," +
								"distillery.brand_registration_19_20 c,distillery.packaging_details_19_20 d where c.brand_id=a.int_brand_id AND a.vch_gatepass_no=b.vch_gatepass_no "+
								" AND a.int_bwfl_id=b.int_bwfl_id and b.vch_finalize='F' "+//
								" AND  b.dt_date=a.dt AND a.int_pckg_id=d.package_id  "+
								"  and b.rcvdt is null and b.vch_gatepass_no='"+action.getGatePassNo().trim()+"'  AND trim(b.vch_to_lic_no)='"+action.getLicenseNmbr().trim()+"'";//AND b.vch_to='"+action.getLoginType()+"'
					}
			 }

			conn = ConnectionToDataBase.getConnection();

			ps = conn.prepareStatement(selQr);

			rs = ps.executeQuery();
			while (rs.next()) {
				FL2_2B_StockDataTable19_20 dt = new FL2_2B_StockDataTable19_20();
				action.setVchFrom(rs.getString("vch_from"));
				dt.setInt_brand_id(rs.getInt("int_brand_id"));
				dt.setInt_pckg_id(rs.getInt("int_pckg_id"));
				dt.setVch_brand(rs.getString("brand_name"));
				dt.setVch_pakg_Name(rs.getString("package_name")); // dispatch_box
				dt.setBoxreciv(rs.getInt("dispatchd_box"));
				dt.setInt_package_ml(rs.getInt("qnt_ml_detail"));
				dt.setBatchNo(rs.getString("vch_batch_no"));
				dt.setInt_bottle_reciv(rs.getInt("dispatch_bottle"));
				dt.setRecivTotalBottel(rs.getInt("dispatch_bottle"));
				dt.setSize(rs.getInt("size"));
				action.setUnitid(rs.getString("unitid"));
				action.setUnittype(rs.getString("vch_from"));
				dt.setSlno(i);
				cases++;
				i++;
				list.add(dt);



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
	/*public ArrayList gatePassDetail(FL2_2B_StockAction19_20 action) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		int i = 1,cases=0;

		try {

			if (action.getLicenseing().equalsIgnoreCase("D") && !action.getLoginType().equalsIgnoreCase("CL2")) {

				selQr =

						"	SELECT  a.int_app_id_f as unitid,a.vch_undertaking_name, b.vch_gatepass_no,b.vehicle_driver_name,a.vch_wrk_add, "
						+ "	b.vch_from, b.vch_to, b.vch_from_lic_no, b.vch_to_lic_no,  b.curr_date, b.licencee_id,  "
						+ "	b.vch_route_detail, b.vch_vehicle_no, b.valid_till,b.vehicle_agency_name_adrs,  "
						+ "	b.licensee_name, b.licensee_adrs  ,c.dispatch_box as dispatchd_box,b.gross_weight, b.tier_weight, b.net_weight,  "
						+ "	b.valid_till, c.int_brand_id, c.size as size, c.dispatch_bottle as dispatch_bottle, c.vch_batch_no , c.int_pckg_id,  "
						+ "	d.brand_name, d.strength, e.package_name,e.box_id,f.qnt_ml_detail as qnt_ml_detail, 	"
						+ "	b.vehicle_agency_name_adrs as vch_firm_name,  c.vch_batch_no,b.dt_date,	  "
						+ "	(((c.dispatch_bottle)*f.qnt_ml_detail)/1000) as bl, 	"
						+ "	((((c.dispatch_bottle*f.qnt_ml_detail)/1000)*d.strength)/100) as al 	 "
						+ "	FROM public.dis_mst_pd1_pd2_lic a, distillery.gatepass_to_districtwholesale b,   "
						+ "	distillery.fl2_stock_trxn c, distillery.brand_registration_19_20 d,  	 "
						+ "	distillery.packaging_details_19_20 e, distillery.box_size_details f 	 "
						+ "	WHERE a.int_app_id_f=b.int_dist_id AND b.int_dist_id=c.int_dissleri_id AND b.vch_gatepass_no=c.vch_gatepass_no  "
						+ "	and b.dt_date=c.dt AND c.int_brand_id=d.brand_id AND d.brand_id=e.brand_id_fk AND c.int_pckg_id=e.package_id 	 "
						+ "	AND e.box_id=f.box_id    "
						+ 
						"	AND f.qnt_ml_detail=e.quantity AND c.dispatch_bottle>0 " +
						" AND b.vch_gatepass_no='"+action.getGatePassNo()+"' and b.rcvdt is null  ";
			}
			else if (action.getLicenseing().equalsIgnoreCase("D") && action.getLoginType().equalsIgnoreCase("CL2")) {

				selQr =

						"	SELECT  a.int_app_id_f as unitid,a.vch_undertaking_name, b.vch_gatepass_no,b.vehicle_driver_name,a.vch_wrk_add, "
						+ "	   b.curr_date, b.vch_from as vch_from,  "
						+ "	 c.dispatchd_box  , c.int_brand_id, c.size as  size, c.dispatchd_bottl as dispatch_bottle,   c.int_pckg_id,f.qnt_ml_detail ,  "
						+ "	d.brand_name, d.strength, e.package_name,e.box_id,f.qnt_ml_detail as qnt_ml_detail, 	"
						+ "	   b.dt_date,'' as 	vch_batch_no,  "
						+ "	(((c.dispatchd_bottl)*f.qnt_ml_detail)/1000) as bl, 	"
						+ "	((((c.dispatchd_bottl*f.qnt_ml_detail)/1000)*d.strength)/100) as al 	 "
						+ "	FROM public.dis_mst_pd1_pd2_lic a, distillery.gatepass_to_manufacturewholesale_cl_19_20 b,   "
						+ "	distillery.cl2_stock_trxn_19_20 c, distillery.brand_registration_19_20 d,  	 "
						+ "	distillery.packaging_details_19_20 e, distillery.box_size_details f 	 "
						+ "	WHERE a.int_app_id_f=b.int_dist_id AND b.int_dist_id=c.int_dissleri_id AND b.vch_gatepass_no=c.vch_gatepass_no  "
						+ "	and b.dt_date=c.dt_date AND c.int_brand_id=d.brand_id AND d.brand_id=e.brand_id_fk AND c.int_pckg_id=e.package_id 	 "
						+ "	AND e.box_id=f.box_id   "
						+ 
						"	AND f.qnt_ml_detail=e.quantity AND c.dispatchd_bottl>0 AND c.dispatchd_box>0 " +
						" AND b.vch_gatepass_no='"+action.getGatePassNo()+"' and b.rcvdt is null ";

			}

			else if (action.getLicenseing().equalsIgnoreCase("B")) {

				selQr =


					" SELECT DISTINCT a.vch_app_id_f as unitid,a.brewery_nm, a.vch_reg_address,b.vch_gatepass_no,b.vehicle_driver_name, " +
					" d.strength,  b.vch_from, b.vch_to, b.vch_from_lic_no, b.vch_to_lic_no,b.dt_date, b.curr_date, " +
					" b.licencee_id, b.vch_route_detail, b.vch_vehicle_no, b.valid_till,b.vehicle_agency_name_adrs,  " +
					" b.licensee_name, b.licensee_adrs, c.dispatch_box as dispatchd_box, b.valid_till, c.int_brand_id, " +
					" c.size as size, c.dispatch_bottle as dispatch_bottle, c.vch_batch_no, c.int_pckg_id , " +
					" d.brand_name, e.package_name,e.box_id,f.qnt_ml_detail as qnt_ml_detail, " +
					" b.vehicle_agency_name_adrs as vch_firm_name, c.vch_batch_no, " +
					" (((c.dispatch_bottle)*f.qnt_ml_detail)/1000) as bl, " +
					" ((((c.dispatch_bottle*f.qnt_ml_detail)/1000)*42.8)/100) as al " +
					" FROM public.bre_mst_b1_lic  a, bwfl.gatepass_to_districtwholesale b, " +
					" bwfl.fl2_stock_trxn c, distillery.brand_registration_19_20 d, " +
					" distillery.packaging_details_19_20 e, distillery.box_size_details f " +
					" WHERE a.vch_app_id_f=b.brewery_id AND   b.brewery_id=c.brewery_id " +
					" AND b.vch_gatepass_no=c.vch_gatepass_no AND b.dt_date=c.dt AND c.int_brand_id=d.brand_id " +
					" AND d.brand_id=e.brand_id_fk AND c.int_pckg_id=e.package_id  " +//and b.vch_finalize='F'
					" AND e.box_id=f.box_id and f.qnt_ml_detail=e.quantity AND c.dispatch_bottle>0 AND c.dispatch_box>0 " +
					" AND b.vch_gatepass_no='"+action.getGatePassNo()+"' and b.rcvdt is null AND b.vch_to_lic_no='"+action.getLicenseNmbr()+"' ";


			} else if(action.getLicenseing().equalsIgnoreCase("FL2D")) {
				selQr = " SELECT	 a.int_fl2d_id as unitid, a.vch_lic_no, a.dt, a.int_brand_id,b.vch_from, a.int_pckg_id,a.size, a.vch_batch_no,       "+
						" a.dispatch_bottle as dispatch_bottle, a.dispatch_box as dispatchd_box,c.brand_name,d.package_name,d.quantity as qnt_ml_detail  "+
						" FROM fl2d.fl2d_stock_trxn a, fl2d.gatepass_to_districtwholesale_fl2d b,                                                        "+
						" distillery.brand_registration_19_20 c,distillery.packaging_details_19_20 d                                                                 "+
						" where c.brand_id=a.int_brand_id AND a.vch_gatepass_no=b.vch_gatepass_no                                                        "+
						" AND a.int_fl2d_id=b.int_fl2d_id                                                                                                "+
						" AND  b.dt_date=a.dt AND a.int_pckg_id=d.package_id                                                                             "+
						"  AND  b.vch_gatepass_no='"+action.getGatePassNo()+"' and b.rcvdt is null and b.vch_finalize='F'" ;  



			}

			else if(action.getLicenseing().equalsIgnoreCase("BWFL")) {
				selQr = "SELECT	 a.int_bwfl_id as unitid, a.vch_lic_no, a.dt, a.int_brand_id,b.vch_from, a.int_pckg_id,a.size, a.vch_batch_no," +
						" a.dispatch_bottle as dispatch_bottle, a.dispatch_box as dispatchd_box,c.brand_name,d.package_name,d.quantity as qnt_ml_detail " +
						"FROM bwfl_license.fl2_stock_trxn_bwfl_19_20 a, bwfl_license.gatepass_to_districtwholesale_bwfl_19_20 b," +
						"distillery.brand_registration_19_20 c,distillery.packaging_details_19_20 d where c.brand_id=a.int_brand_id AND a.vch_gatepass_no=b.vch_gatepass_no "+
						" AND a.int_bwfl_id=b.int_bwfl_id and b.vch_finalize='F' "+//
                        " AND  b.dt_date=a.dt AND a.int_pckg_id=d.package_id  "+
                        "  and b.rcvdt is null and b.vch_gatepass_no='"+action.getGatePassNo().trim()+"' ";
			}


		//	conn = ConnectionToDataBase.getConnection();

			//ps = conn.prepareStatement(selQr);
              Date d=action.getCreatedDate();
              SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyy");
           String date=   sdf.format(d);


		List l=	getDetailFromService(action.getGatePassNo().trim(),date,action.getLicenseing().trim(),action.getLoginType(),action.getLicenseNmbr());

			for(int p=0;p<l.size();p++)
			{

				GATEPASS_DETAIL gd=		(GATEPASS_DETAIL)l.get(p);



				FL2_2B_StockDataTable dt = new FL2_2B_StockDataTable();

				dt.setInt_brand_id(Integer.parseInt(gd.getBRAND_ID()));
				dt.setInt_pckg_id(Integer.parseInt(gd.getPACKAGE_ID()));
				dt.setVch_brand(gd.getBRAND_NAME());
				dt.setVch_pakg_Name(gd.getPACKAGE_NAME()); // dispatch_box
				dt.setBoxreciv(Integer.parseInt(gd.getBOX_RECIVE()));
				dt.setInt_package_ml(Integer.parseInt(gd.getPACKAGE_ML()));
				dt.setBatchNo(gd.getBATCH_NO());
				dt.setInt_bottle_reciv(Integer.parseInt(gd.getBOTTLE_RECV()));
				dt.setRecivTotalBottel(Integer.parseInt(gd.getBOTTLE_RECV()));
				dt.setSize(Integer.parseInt(gd.getSIZE()));
				action.setUnitid(gd.getUNIT_ID());
				action.setUnittype(gd.getUNIT_TYPE());
				dt.setSlno(i);
				cases++;
				i++;
				list.add(dt);
			 rs = ps.executeQuery();
			while (rs.next()) {
				FL2_2B_StockDataTable dt = new FL2_2B_StockDataTable();

				dt.setInt_brand_id(rs.getInt("int_brand_id"));
				dt.setInt_pckg_id(rs.getInt("int_pckg_id"));
				dt.setVch_brand(rs.getString("brand_name"));
				dt.setVch_pakg_Name(rs.getString("package_name")); // dispatch_box
				dt.setBoxreciv(rs.getInt("dispatchd_box"));
				dt.setInt_package_ml(rs.getInt("qnt_ml_detail"));
				dt.setBatchNo(rs.getString("vch_batch_no"));
				dt.setInt_bottle_reciv(rs.getInt("dispatch_bottle"));
				dt.setRecivTotalBottel(rs.getInt("dispatch_bottle"));
				dt.setSize(rs.getInt("size"));
				action.setUnitid(rs.getString("unitid"));
				action.setUnittype(rs.getString("vch_from"));
				dt.setSlno(i);
				cases++;
				i++;
				list.add(dt);

				action.setExcelFlag(true);
				action.setUploadFlag(true);
				action.setTableFlag(true);
				action.setSubmitFlag(true);
				action.setCancelFlag(true);
				action.setKindlyUploadFlag(true);
				action.setGatePassFlag(true);
				action.setReceiveCases(action.getReceiveCases()+rs.getInt(cases));

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
	 */

	// ----------------- save data ----------------------

	/*public String saveMethodImpl(FL2_2B_StockAction19_20 act) {
		Connection con = null;
		PreparedStatement ps = null, ps2 = null, ps2D = null, ps3 = null, ps4 = null, ps5 = null;
		ResultSet rs = null;
		String sql = "";
		String sql2 = "";
		String sql3 = "";
		int tok1 = 0;
		double duty = 0;
		double addduty = 0;
		String sql5 = "";
		int seq = this.maxId(this);
		int seqsecoend = this.maxIdSecoend(this);

		try {
			int cases = 0;
			int totalBottles = 0;

			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);
			// receive


			if (act.getLicenseing().equalsIgnoreCase("D") && !act.getLoginType().equalsIgnoreCase("CL2")) {
 	      sql2 = "update  distillery.gatepass_to_districtwholesale  set recieveflg='R',  rcvdbyid='"+act.getDist_id()+"' , rcvdbytype='"+act.getLoginType()+"',rcvdt='"+Utility.convertUtilDateToSQLDate(new Date())+"'" +
						"where vch_gatepass_no='"+act.getGatePassNo()+"'  and dt_date='"+Utility.convertUtilDateToSQLDate(act.getCreatedDate())+"'";

			}
			else if (act.getLicenseing().equalsIgnoreCase("D") && act.getLoginType().equalsIgnoreCase("CL2")) {
        sql2 = "update  distillery.gatepass_to_manufacturewholesale_cl_19_20  set recieveflg='R',  rcvdbyid='"+act.getDist_id()+"' , rcvdbytype='"+act.getLoginType()+"',rcvdt='"+Utility.convertUtilDateToSQLDate(new Date())+"'" +
						"where vch_gatepass_no='"+act.getGatePassNo()+"'  and dt_date='"+Utility.convertUtilDateToSQLDate(act.getCreatedDate())+"'";

			}

			else if (act.getLicenseing().equalsIgnoreCase("B")) {

				sql2 = "update  bwfl.gatepass_to_districtwholesale set recieveflg='R',  rcvdbyid='"+act.getDist_id()+"' , rcvdbytype='"+act.getLoginType()+"',rcvdt='"+Utility.convertUtilDateToSQLDate(new Date())+"'" +
						"where vch_gatepass_no='"+act.getGatePassNo()+"'   and dt_date='"+Utility.convertUtilDateToSQLDate(act.getCreatedDate())+"'";


			} else if(act.getLicenseing().equalsIgnoreCase("FL2D")) {
				sql2 = "update  fl2d.gatepass_to_districtwholesale_fl2d set recieveflg='R',  rcvdbyid='"+act.getDist_id()+"' , rcvdbytype='"+act.getLoginType()+"',rcvdt='"+Utility.convertUtilDateToSQLDate(new Date())+"'" +
						"where vch_gatepass_no='"+act.getGatePassNo()+"'  and dt_date='"+Utility.convertUtilDateToSQLDate(act.getCreatedDate())+"'";
			}

			else if(act.getLicenseing().equalsIgnoreCase("BWFL")) {

				sql2 = "update   bwfl_license.gatepass_to_districtwholesale_bwfl_19_20 set recieveflg='R',  rcvdbyid='"+act.getDist_id()+"' , rcvdbytype='"+act.getLoginType()+"',rcvdt='"+Utility.convertUtilDateToSQLDate(new Date())+"'" +
						"where vch_gatepass_no='"+act.getGatePassNo()+"'  ";// and dt_date='"+Utility.convertUtilDateToSQLDate(act.getCreatedDate())+"'
							}


			ps2 = con.prepareStatement(sql2);
			tok1 = ps2.executeUpdate();

			SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyy");
			String d=sdf.format(act.getCreatedDate());

			String s=updateGatepass(act.getGatePassNo(), d, act.getLicenseing(), act.getLoginType(), act.getLicenseNmbr(), String.valueOf(act.getDist_id()),String.valueOf( act.getDist_id()));
			if (s!=null&& !s.equals("")&s.trim().equals("true")) {



				for (int i = 0; i < act.gatePssDisplaylist.size(); i++) {

					FL2_2B_StockDataTable dt = (FL2_2B_StockDataTable) act
							.getGatePssDisplaylist().get(i);
					if (dt.getRecivTotalBottel() > 0) {
						tok1 = 0;

						sql2 = "	INSERT INTO fl2d.fl2_2b_receiving_stock_trxn_19_20 ( "
								+ "	seq, gatepass_no, fl2_2btype, brand_id, size_ml, batch_no, recv_box,  "
								+ "    recv_bottels, breakage_bottels, total_recv_bottels, pckg_id, created_date, fl2_2bid, box_size ," +
								" recvdfromunittype,rcvdunitid,loginusr, permit_no) "
								+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?) ";

						ps2 = con.prepareStatement(sql2);
						System.out.println(act.getPermitNo());
						ps2.setInt(1, ++seq);
						ps2.setString(2, act.getGatePassNo());
						ps2.setString(3, act.getLoginType());
						ps2.setInt(4, dt.getInt_brand_id());
						ps2.setInt(5, dt.getInt_package_ml());
						ps2.setString(6, dt.getBatchNo());
						ps2.setInt(7, dt.getBoxreciv());
						ps2.setInt(8, dt.getInt_bottle_reciv());
						ps2.setInt(9, dt.getBreakage());
						ps2.setInt(10, dt.getRecivTotalBottel());
						ps2.setInt(11, dt.getInt_pckg_id());
						ps2.setDate(12, Utility.convertUtilDateToSQLDate(new Date()));
						ps2.setInt(13, act.getDist_id());
						ps2.setInt(14, dt.getSize());
						ps2.setString(15, act.getUnittype());
						ps2.setString(16, act.getUnitid());
						ps2.setString(17, ResourceUtil.getUserNameAllReq().trim());
						ps2.setString(18, act.getPermitNo());
						cases += dt.getBoxreciv();
						totalBottles += dt.getInt_bottle_reciv();
						tok1 = ps2.executeUpdate();

						if (tok1 > 0) {
							tok1 = 0;

							String updtQr =

							"	INSERT INTO fl2d.fl2_2b_stock_19_20( "
									+ "			id, type, brand_id, pckg_id, recv_total_bottels)  "
									+ "			VALUES (?, ?, ?, ?, ? )  "
									+ "			ON CONFLICT ON CONSTRAINT fl2_2b_receiving_stock_master_manage_pkey "
									+ "			do update set recv_total_bottels=  COALESCE(fl2d.fl2_2b_stock_19_20.recv_total_bottels,0.0) + "
									+ dt.getRecivTotalBottel() + " ";

							ps3 = con.prepareStatement(updtQr);

							ps3.setInt(1, act.getDist_id());

							ps3.setString(2, act.getLoginType());
							ps3.setInt(3, dt.getInt_brand_id());
							ps3.setInt(4, dt.getInt_pckg_id());
							ps3.setInt(5, dt.getRecivTotalBottel());

							tok1 = ps3.executeUpdate();

						}

					}// if(dt.getRecivTotalBottel()>0)
				}
			}	


			if (tok1 > 0) {
				con.setAutoCommit(true);
				ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,
						Constants.SAVED_SUCESSFULLY);

				act.reset();
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

	 */


	public String saveMethodImpl(FL2_2B_StockAction19_20 act) {
		Connection con = null;Connection con1 = null;
		PreparedStatement ps = null, ps2 = null, ps2D = null, ps3 = null, ps4 = null, ps5 = null;
		ResultSet rs = null;
		String sql = "";
		String sql2 = "";
		String sql3 = "";
		int tok1 = 0;int tok2=0;
		double duty = 0;
		double addduty = 0;
		String sql5 = ""; 
		
		int seqsecoend = this.maxIdSecoend(this);
		String val="";
		try {
			 

			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);
			 con1 = ConnectionToDataBase.getConnection19_20();
			 
			 con1.setAutoCommit(false);
			 
			 SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			 Date d2=sdf.parse("24/06/2019");
			 Date d3=sdf.parse("29/06/2019");

			 Date d4=(act.getCreatedDate());



			 if(act.getOldnew().equalsIgnoreCase("O"))
			 {
			 
			 	if (act.getLicenseing().equalsIgnoreCase("D") && !act.getLoginType().equalsIgnoreCase("CL2")) {
					sql2 = "update  distillery.gatepass_to_districtwholesale_19_20  set recieveflg='R',  rcvdbyid='"+act.getDist_id()+"' , rcvdbytype='"+act.getLoginType()+"',rcvdt='"+Utility.convertUtilDateToSQLDate(new Date())+"'" +
							"  where vch_gatepass_no='"+act.getGatePassNo()+"'   and recieveflg is null";// and trim(vch_to_lic_no)='"+act.getLicenseNmbr().trim()+"' 

				}
				else if (act.getLicenseing().equalsIgnoreCase("D") && act.getLoginType().equalsIgnoreCase("CL2")) {
					sql2 = "update  distillery.gatepass_to_manufacturewholesale_cl_19_20  set recieveflg='R',  rcvdbyid='"+act.getDist_id()+"' , rcvdbytype='"+act.getLoginType()+"',rcvdt='"+Utility.convertUtilDateToSQLDate(new Date())+"'" +
							"  where vch_gatepass_no='"+act.getGatePassNo()+"'  and recieveflg is null";//  and trim(vch_to_lic_no)='"+act.getLicenseNmbr().trim()+"' 

				}

				else if (act.getLicenseing().equalsIgnoreCase("B")) {

					sql2 = "update  bwfl.gatepass_to_districtwholesale_19_20 set recieveflg='R',  rcvdbyid='"+act.getDist_id()+"' , rcvdbytype='"+act.getLoginType()+"',rcvdt='"+Utility.convertUtilDateToSQLDate(new Date())+"'" +
							"  where vch_gatepass_no='"+act.getGatePassNo()+"'    and recieveflg is null";//  and trim(vch_to_lic_no)='"+act.getLicenseNmbr().trim()+"' 


				} else if(act.getLicenseing().equalsIgnoreCase("FL2D")) {
					sql2 = "update  fl2d.gatepass_to_districtwholesale_fl2d_19_20 set recieveflg='R',  rcvdbyid='"+act.getDist_id()+"' , rcvdbytype='"+act.getLoginType()+"',rcvdt='"+Utility.convertUtilDateToSQLDate(new Date())+"'" +
							"  where vch_gatepass_no='"+act.getGatePassNo()+"'  and dt_date='"+Utility.convertUtilDateToSQLDate(act.getCreatedDate())+"'  and recieveflg is null";
				}

				else if(act.getLicenseing().equalsIgnoreCase("BWFL")) {

					sql2 = "update   bwfl_license.gatepass_to_districtwholesale_bwfl_19_20 set recieveflg='R',  rcvdbyid='"+act.getDist_id()+"' , rcvdbytype='"+act.getLoginType()+"',rcvdt='"+Utility.convertUtilDateToSQLDate(new Date())+"'" +
							"  where vch_gatepass_no='"+act.getGatePassNo()+"'  and recieveflg is null";//  and trim(vch_to_lic_no)='"+act.getLicenseNmbr().trim()+"' 
				}

			 }else{
				 if (act.getLicenseing().equalsIgnoreCase("D") && !act.getLoginType().equalsIgnoreCase("CL2")) {
						sql2 = "update  distillery.gatepass_to_districtwholesale_19_20  set recieveflg='R',  rcvdbyid='"+act.getDist_id()+"' , rcvdbytype='"+act.getLoginType()+"',rcvdt='"+Utility.convertUtilDateToSQLDate(new Date())+"'" +
								"where vch_gatepass_no='"+act.getGatePassNo()+"'   and trim(vch_to_lic_no)='"+act.getLicenseNmbr().trim()+"'  and recieveflg is null";//and dt_date='"+Utility.convertUtilDateToSQLDate(act.getCreatedDate())+"'

					}
					else if (act.getLicenseing().equalsIgnoreCase("D") && act.getLoginType().equalsIgnoreCase("CL2")) {
						sql2 = "update  distillery.gatepass_to_manufacturewholesale_cl_19_20  set recieveflg='R',  rcvdbyid='"+act.getDist_id()+"' , rcvdbytype='"+act.getLoginType()+"',rcvdt='"+Utility.convertUtilDateToSQLDate(new Date())+"'" +
								"where vch_gatepass_no='"+act.getGatePassNo()+"'  and trim(vch_to_lic_no)='"+act.getLicenseNmbr().trim()+"'  and recieveflg is null";// dt_date='"+Utility.convertUtilDateToSQLDate(act.getCreatedDate())+"'

					}

					else if (act.getLicenseing().equalsIgnoreCase("B")) {

						sql2 = "update  bwfl.gatepass_to_districtwholesale_19_20 set recieveflg='R',  rcvdbyid='"+act.getDist_id()+"' , rcvdbytype='"+act.getLoginType()+"',rcvdt='"+Utility.convertUtilDateToSQLDate(new Date())+"'" +
								"where vch_gatepass_no='"+act.getGatePassNo()+"'     and trim(vch_to_lic_no)='"+act.getLicenseNmbr().trim()+"'  and recieveflg is null";//dt_date='"+Utility.convertUtilDateToSQLDate(act.getCreatedDate())+"'


					} else if(act.getLicenseing().equalsIgnoreCase("FL2D")) {
						sql2 = "update  fl2d.gatepass_to_districtwholesale_fl2d_19_20 set recieveflg='R',  rcvdbyid='"+act.getDist_id()+"' , rcvdbytype='"+act.getLoginType()+"',rcvdt='"+Utility.convertUtilDateToSQLDate(new Date())+"'" +
								"where vch_gatepass_no='"+act.getGatePassNo()+"'  and dt_date='"+Utility.convertUtilDateToSQLDate(act.getCreatedDate())+"'  and recieveflg is null";
					}

					else if(act.getLicenseing().equalsIgnoreCase("BWFL")) {

						sql2 = "update   bwfl_license.gatepass_to_districtwholesale_bwfl_19_20 set recieveflg='R',  rcvdbyid='"+act.getDist_id()+"' , rcvdbytype='"+act.getLoginType()+"',rcvdt='"+Utility.convertUtilDateToSQLDate(new Date())+"'" +
								"where vch_gatepass_no='"+act.getGatePassNo()+"' and trim(vch_to_lic_no)='"+act.getLicenseNmbr().trim()+"'  and recieveflg is null";//  dt_date='"+Utility.convertUtilDateToSQLDate(act.getCreatedDate())+"' 
					}
			 }
			ps2 = con.prepareStatement(sql2);
			tok1 = ps2.executeUpdate();


			
			if (tok1 > 0) {
				tok1 = 0;
				for (int i = 0; i < act.gatePssDisplaylist.size(); i++) {

					FL2_2B_StockDataTable19_20 dt = (FL2_2B_StockDataTable19_20) act
							.getGatePssDisplaylist().get(i);
					if (dt.getRecivTotalBottel() > 0) {
						tok1 = 0;
						String updtQr =

								"	INSERT INTO fl2d.fl2_2b_stock_19_20( "
										+ "			id, type, brand_id, pckg_id, recv_total_bottels,size)  "
										+ "			VALUES (?, ?, ?, ?, ? ,"+dt.getSize()+")  "
										+ "			ON CONFLICT ON CONSTRAINT fl2_2b_receiving_stock_master_manage_pkey_19_20 "
										+ "			do update set recv_total_bottels=  COALESCE(fl2d.fl2_2b_stock_19_20.recv_total_bottels,0.0) + "
										+ dt.getRecivTotalBottel() + " ";

						ps3 = con.prepareStatement(updtQr);

						ps3.setInt(1, act.getDist_id());

						ps3.setString(2, act.getLoginType());
						ps3.setInt(3, dt.getInt_brand_id());
						ps3.setInt(4, dt.getInt_pckg_id());
						ps3.setInt(5, dt.getRecivTotalBottel());

						tok1 = ps3.executeUpdate();


					}	}} 

			if (tok1 > 0 && act.getLoginType().equalsIgnoreCase("CL2")) {
				String query="";
				 query = "  update bottling_unmapped.disliry_unmap_cl set recv_id='"+act.getDist_id()+"'" +
								" where fl36gatepass='"+act.getGatePassNo()+"' and recv_id is null and ws_date is null ";

				 
				ps = con1.prepareStatement(query);
				tok2 = ps.executeUpdate();
				 
			}
			else if (tok1 > 0 && act.getLoginType().equalsIgnoreCase("FL2")) {
				String query="";
				if (act.getLicenseing().equalsIgnoreCase("D") && !act.getLoginType().equalsIgnoreCase("CL2")) {
					if(act.getVchFrom().equalsIgnoreCase("FL1")){
						val="bottling_unmapped.disliry_unmap_fl3";
					}else if(act.getVchFrom().equalsIgnoreCase("FL1A")){
						val="bottling_unmapped.disliry_unmap_fl3a";
					}
				}
				 
				else if(act.getLicenseing().equalsIgnoreCase("FL2D")) {
					val="bottling_unmapped.fl2d";
				}

				else if(act.getLicenseing().equalsIgnoreCase("BWFL")) {
					val="bottling_unmapped.bwfl";
				}
				 
				query = "  update "+val+" set recv_id='"+act.getDist_id()+"'" +
						" where fl36gatepass='"+act.getGatePassNo()+"' and recv_id is null and ws_date is null ";
				ps = con1.prepareStatement(query);
				tok2 = ps.executeUpdate();
			}
			else if (tok1 > 0 && act.getLoginType().equalsIgnoreCase("FL2B")) {
				String query="";
				  if (act.getLicenseing().equalsIgnoreCase("B")) {
					if(act.getVchFrom().equalsIgnoreCase("FL1")){
						val="bottling_unmapped.brewary_unmap_fl3";
					}else if(act.getVchFrom().equalsIgnoreCase("FL1A")){
						val="bottling_unmapped.brewary_unmap_fl3a";
					}
				} 
				  else if (act.getLicenseing().equalsIgnoreCase("D") && !act.getLoginType().equalsIgnoreCase("CL2")) {
						if(act.getVchFrom().equalsIgnoreCase("FL1")){
							val="bottling_unmapped.disliry_unmap_fl3";
						}else if(act.getVchFrom().equalsIgnoreCase("FL1A")){
							val="bottling_unmapped.disliry_unmap_fl3a";
						}
					}
				  else if(act.getLicenseing().equalsIgnoreCase("FL2D")) {
						val="bottling_unmapped.fl2d";
					} 
				else if(act.getLicenseing().equalsIgnoreCase("BWFL")) {
					val="bottling_unmapped.bwfl";
				}
				
				query = "  update "+val+" set recv_id='"+act.getDist_id()+"'" +
						" where fl36gatepass='"+act.getGatePassNo()+"' and recv_id is null and ws_date is null ";
				ps = con1.prepareStatement(query);
				tok2 = ps.executeUpdate();
			}
			else{
				tok2=0;
			}
			
			
			if (tok1 > 0) {

				tok1 = 0;
				int seq = this.maxId(this);
				for (int i = 0; i < act.gatePssDisplaylist.size(); i++) {

					FL2_2B_StockDataTable19_20 dt = (FL2_2B_StockDataTable19_20) act
							.getGatePssDisplaylist().get(i);
					if (dt.getRecivTotalBottel() > 0) {
						tok1 = 0;

						sql2 = "	INSERT INTO fl2d.fl2_2b_receiving_stock_trxn_19_20 ( "
								+ "	seq, gatepass_no, fl2_2btype, brand_id, size_ml, batch_no, recv_box,  "
								+ "    recv_bottels, breakage_bottels, total_recv_bottels, pckg_id, created_date, fl2_2bid, box_size ," +
								" recvdfromunittype,rcvdunitid,loginusr, permit_no) "
								+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?) ";

						ps2 = con.prepareStatement(sql2);

						ps2.setInt(1, ++seq);
						ps2.setString(2, act.getGatePassNo());
						ps2.setString(3, act.getLoginType());
						ps2.setInt(4, dt.getInt_brand_id());
						ps2.setInt(5, dt.getInt_package_ml());
						ps2.setString(6, dt.getBatchNo());
						ps2.setInt(7, dt.getBoxreciv());
						ps2.setInt(8, dt.getInt_bottle_reciv());
						ps2.setInt(9, dt.getBreakage());
						ps2.setInt(10, dt.getRecivTotalBottel());
						ps2.setInt(11, dt.getInt_pckg_id());
						ps2.setDate(12, Utility.convertUtilDateToSQLDate(new Date()));
						ps2.setInt(13, act.getDist_id());
						ps2.setInt(14, dt.getSize());
						ps2.setString(15, act.getUnittype());
						ps2.setString(16, act.getUnitid());
						ps2.setString(17, ResourceUtil.getUserNameAllReq().trim());
						ps2.setString(18, act.getPermitNo());
						 
						tok1 = ps2.executeUpdate();



					} 
				}
			}	
			if (tok1 >0 && tok2>0) {//
				con.commit();	 con1.commit();
				ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,
						Constants.SAVED_SUCESSFULLY);

				act.reset();
			}
			else if (tok1 >0 && tok2<=0) {
				con.rollback();	 con1.rollback();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Barcodes on this gatepass are already dispatched by wholesaler ! ", "Barcodes on this gatepass are already dispatched by wholesaler ! "));
				 
			}
			else if (tok1 <0) {
				con.rollback();	 con1.rollback();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Data Not Saved !! ", "Data Not Saved !!"));

			}
			else   {
				con.rollback();	 con1.rollback();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Data Not Saved !! ", "Data Not Saved !!"));

			}

		} catch (Exception e) {e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));

		} finally {
			try {
				if (con != null)
					con.close();if (con1 != null)
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
	// ----------------------- max-1-------------------

	public int maxId(FL2_2B_StockImpl19_20 impl) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(seq) as id FROM fl2d.fl2_2b_receiving_stock_trxn_19_20 ";
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

	// ----------------------------------------------------------

	// ----------------------- max-2nd-------------------

	public int maxIdSecoend(FL2_2B_StockImpl19_20 impl) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(id) as id FROM fl2d.fl2_2b_stock_19_20 ";
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

	// ------------------ get data -------------------

	public ArrayList getDataTable(FL2_2B_StockAction19_20 action ) {
		ArrayList list = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;

		if(action.getLoginType().equalsIgnoreCase("CL2"))
		{
			query = " SELECT a.seq, a.gatepass_no, a.fl2_2btype, a.brand_id, a.size_ml, a.batch_no,  " +
					" a.recv_box, 	a.recv_bottels, a.breakage_bottels, a.total_recv_bottels, a.pckg_id,  " +
					" a.created_date, a.fl2_2bid, a.dispatch_36, a.box_size, b.brand_name  " +
					" FROM fl2d.fl2_2b_receiving_stock_trxn_19_20 a , distillery.brand_registration_19_20 b  " +
					" WHERE a.brand_id=b.brand_id AND a.gatepass_no!='OLDSTOCK' AND a.fl2_2bid='"+ action.getDist_id()+"'  " +
					" ORDER BY a.seq DESC ";
		}
		else{
			query = " SELECT a.seq, a.gatepass_no, a.fl2_2btype, a.brand_id, a.size_ml, a.batch_no, a.recv_box, "
					+ "	a.recv_bottels, a.breakage_bottels, a.total_recv_bottels, a.pckg_id,  "
					+ "	a.created_date, a.fl2_2bid, a.dispatch_36, a.box_size, "
					+ "	b.brand_name "
					+ "		FROM fl2d.fl2_2b_receiving_stock_trxn_19_20 a , distillery.brand_registration_19_20 b "
					+ "	    where a.brand_id=b.brand_id   "
					+ "		and fl2_2bid='" + action.getDist_id() + "' order by a.seq desc";
		}







		try {
			list = new ArrayList();
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);

			 

			//ps.setDate(1, Utility.convertUtilDateToSQLDate(val));
			rs = ps.executeQuery();
			int i = 1;
			while (rs.next()) 
			{
				FL2_2B_StockDataTable19_20 dt = new FL2_2B_StockDataTable19_20();
				dt.setSno_data_table(i);
				dt.setCreatDate_data_table(rs.getDate("created_date"));
				dt.setVch_brand_data_table(rs.getString("brand_name"));
				dt.setInt_bottle_reciv_data_table(rs.getInt("recv_bottels"));
				dt.setBreakage_data_table(rs.getInt("breakage_bottels"));
				dt.setRecivTotalBottel_data_table(rs.getInt("total_recv_bottels"));
				dt.setGatepasss(rs.getString("gatepass_no"));
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
	public void saveExcelData(FL2_2B_StockAction19_20 action)
	{
		String gatepass="";
		int status=0,flag=1,excelCases=0;
		Connection conn=null;	
		PreparedStatement pstmt=null;
		System.out.println("filename and path"+action.getExcelFilename()+"---------patj"+action.getExcelFilepath());
		FileInputStream fileInputStream=null;
		XSSFWorkbook workbook=null;
		try{
			String sql= 
					"INSERT INTO bwfl.gatepass_casecode(gatepass,casecode)VALUES (?,?)";


			conn=ConnectionToDataBase.getConnection();


			pstmt=conn.prepareStatement(sql);
			fileInputStream = new FileInputStream(action.getExcelFilepath());

			workbook = new XSSFWorkbook(fileInputStream);


			XSSFSheet worksheet = workbook.getSheet("Sheet1");
			int i=0,j=1;


			do
			{

				String casecode="";
				XSSFRow row1 = worksheet.getRow(j);
				XSSFRow row2 = worksheet.getRow(j+1);

				XSSFCell cellA1 = row1.getCell((short) 0);
				XSSFCell cellA2 = row2.getCell((short) 0);

				String cellval=getCellValue(cellA1);
				String cellval2=getCellValue(cellA2);

				System.out.println("break.............break"+cellval);
				if((cellval==null && cellval2==null) || (cellval.length()==0 && cellval2.length()==0)
						|| (cellval.equals("0.0") && cellval2.equals("0.0")))
				{
					System.out.println("break.............break");
					break;
				}else{

					if(j==1)
					{
						cellA1 = row1.getCell((short) 0);
						gatepass=getCellValue(cellA1);
						if(action.getScanGatePassNo().equalsIgnoreCase(gatepass))
						{
							cellA2 = row2.getCell((short) 0);
							casecode=getCellValue(cellA2);

							pstmt.setString(1, gatepass);
							pstmt.setString(2, casecode);

							status= pstmt.executeUpdate();
							excelCases++;

							i=1;
						}else
						{

							flag=0;
						}
					}
					else
					{

						cellA2=row2.getCell((short) 0);
						casecode=getCellValue(cellA2);

						pstmt.setString(1, gatepass);
						pstmt.setString(2, casecode);
						excelCases++;

						status= pstmt.executeUpdate();
						action.setExcelCases(excelCases);
						i=1;
					}
				}

				j++;	
			}while(i==1);







		}
		catch(Exception e)
		{
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							e.getMessage(),
							e.getMessage()));
		}

		finally
		{
			try
			{	
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();    }  		
			catch(Exception e)
			{
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								e.getMessage(),
								e.getMessage()));
			}
		}

		if(flag==1)
		{
			if(status>0){

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Record Upload!!",
								"Record Upload!!"));
			}
			else{

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Duplicate CaseCode!!",
								"Duplicate CaseCode!!"));
			}
		}else
		{
			action.setKindlyUploadFlag(false);
			action.setGatePassFlag(false);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Kindly enter the same gatepass number!!",
							"Kindly enter the same gatepass number!!"));
		}


	}	
	private String getCellValue(XSSFCell cell) {
		try{
			System.out.println("get cell value type----------------------------------"+cell.getCellType());
			switch (cell.getCellType()) {
			case XSSFCell.CELL_TYPE_STRING:

				return cell.getStringCellValue().toString();

			case XSSFCell.CELL_TYPE_BOOLEAN:
				return Boolean.toString(cell.getBooleanCellValue());

			case XSSFCell.CELL_TYPE_NUMERIC:
				String val = Double.toString(cell.getNumericCellValue());
				val=val.substring(0, val.lastIndexOf("."));

				return val;

			case XSSFCell.CELL_TYPE_BLANK:

				return null;


			}

			return null;
		}
		catch(Exception e)
		{
			return null;
		}

	}
	public void deleteData(FL2_2B_StockAction19_20 action) 
	{

		Connection con=null;
		PreparedStatement stmt=null;


		String query="DELETE FROM bwfl.gatepass_casecode where gatespass ='" + action.getScanGatePassNo() + "' ";
		int status=0;
		try
		{
			con=ConnectionToDataBase.getConnection();
			stmt=con.prepareStatement(query);
			status=stmt.executeUpdate();
			if(status>0)
			{
				System.out.println("delete table successfully");
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Record delete successfully ", "Record delete successfully "));
			}
			else
			{
				System.out.println("not delete table successfully");
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Record not delete ", "Record not delete "));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		finally
		{
			try
			{	
				if(stmt!=null)stmt.close();
				if(con!=null)con.close();    }  		
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}


	}
	public ArrayList getExcelData(FL2_2B_StockAction19_20 action)
	{
		ArrayList list=new ArrayList();

		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;

		String query="SELECT gatepass,casecode FROM bwfl.gatepass_casecode";

		try
		{
			con=ConnectionToDataBase.getConnection();
			stmt=con.prepareStatement(query);
			rs=stmt.executeQuery();


			while(rs.next())
			{

				FL2_2B_StockDataTable19_20 dt=new FL2_2B_StockDataTable19_20();

				dt.setGatepass(rs.getString("gatepass"));
				dt.setCasecode(rs.getString("casecode"));

				list.add(dt);

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		finally
		{
			try
			{	
				if(stmt!=null)stmt.close();
				if(con!=null)con.close();    }  		
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		return list;
	}





	//get detail from service

	public List getDetailFromService(String gatepassno,String date,String industrytype,String login_type,String licNo) throws JAXBException
	{
		GATEPASS_DETAILS gd = null;

		try {
			String response="";
			//String POST_PARAMS = getdata();
			System.out.println("Start sending " + "" + " request");
			URL url = new URL( "https://www.upexciseonline.in/BarcodeQrcode/rest/gatepass/detail?gatepass_no="+gatepassno+"&date="+date+"&industry_type="+industrytype+"&login_type="+login_type+"&licence_no="+licNo );
			HttpURLConnection rc = (HttpURLConnection)url.openConnection();
			System.out.println("Connection opened " + rc );



			rc.setRequestMethod("POST");
			rc.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36");


			rc.setDoOutput( true );
			rc.setRequestProperty( "Content-Type", "text/xml; charset=utf-8" );


			rc.connect();    

			OutputStream out = rc.getOutputStream();
			//out.write(POST_PARAMS.getBytes());

			out.flush();
			out.close();

			int responseCode = rc.getResponseCode();
			System.out.println("Request sent, reading response ");
			Charset charset = Charset.forName("UTF8");
			InputStreamReader read = new InputStreamReader( rc.getInputStream() , charset);
			StringBuilder sb = new StringBuilder();   
			int ch = read.read();
			while( ch != -1 ){
				sb.append((char)ch);
				ch = read.read();
			}
			response = sb.toString(); 

			JAXBContext jb=	JAXBContext.newInstance(GATEPASS_DETAILS.class);
			Unmarshaller um=	    jb.createUnmarshaller();

			gd=    (GATEPASS_DETAILS)um.unmarshal(new StringReader(response));
			//System.out.println("atull"+response);

			// System.out.println("response"+response+ "       "+gd.getGATEPASS_DETAIL()+"  size"+gd.getGATEPASS_DETAIL().size());
			read.close();
			rc.disconnect();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return  gd.getGATEPASS_DETAIL();
	}






	/*
	 * 
	 * @author atul 
	 * this method call webservice only for updating gatepass flag on save event
	 */


	public String  updateGatepass(String gatepassno,String date,String industrytype,String login_type,String licNo,String distId,String recvById) throws JAXBException
	{

		String response="";
		try {

			//String POST_PARAMS = getdata();
			System.out.println("Start sending " + "" + " request");
			URL url = new URL( "https://www.upexciseonline.in/BarcodeQrcode/rest/gatepass/updategatepass?gatepassNo="+gatepassno+"&cretaedDate="+date+"&industryType="+industrytype+"&loginType="+login_type+"&licence_no="+licNo+"&distId="+distId+"&recvById="+recvById );
			HttpURLConnection rc = (HttpURLConnection)url.openConnection();
			//System.out.println("Connection opened " + rc );



			rc.setRequestMethod("POST");
			rc.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36");


			rc.setDoOutput( true );
			rc.setRequestProperty( "Content-Type", "text/xml; charset=utf-8" );


			rc.connect();    

			OutputStream out = rc.getOutputStream();
			//out.write(POST_PARAMS.getBytes());

			out.flush();
			out.close();

			int responseCode = rc.getResponseCode();
			System.out.println("Request sent, reading response ");
			Charset charset = Charset.forName("UTF8");
			InputStreamReader read = new InputStreamReader( rc.getInputStream() , charset);
			StringBuilder sb = new StringBuilder();   
			int ch = read.read();
			while( ch != -1 ){
				sb.append((char)ch);
				ch = read.read();
			}
			response = sb.toString(); 
			System.out.println("responseeeeeeeeeeeeeeeee"+response);

			read.close();
			rc.disconnect();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return  response;
	}






}
