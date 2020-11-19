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


import com.mentor.action.ListOfApprovedBrandsAction;
import com.mentor.datatable.ListOfApprovedBrandsDatatable;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;

public class ListOfApprovedBrandsImpl {
	public ArrayList getDataList(ListOfApprovedBrandsAction action)
	{
		ArrayList list = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		String query = "";
		
		if(action.getLicenseing().equalsIgnoreCase("BEER")){
			
			query = " SELECT  c.brewery_nm as name, a.distillery_id, a.brand_name, b.package_name, b.quantity, b.code_generate_through, b.mrp  "
					 + " from  distillery.brand_registration a, distillery.packaging_details b, public.bre_mst_b1_lic c  "
					 + " where a.brewery_id=c.vch_app_id_f and a.brand_id=b.brand_id_fk and " +
					 " a.license_category=? ";
			
			
			
		}else {
			query = " SELECT  c.vch_undertaking_name as name, a.distillery_id, a.brand_name, b.package_name, b.quantity, b.code_generate_through, b.mrp  "
					 + " from  distillery.brand_registration a, distillery.packaging_details b, public.dis_mst_pd1_pd2_lic c  "
					 + " where a.distillery_id=c.int_app_id_f and a.brand_id=b.brand_id_fk and " +
					 " a.license_category=? ";
		}
		
		try{
			list = new ArrayList();
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,action.getLicenseing());
			rs = ps.executeQuery();
			int i=1;
			while(rs.next()){
				
				ListOfApprovedBrandsDatatable dt = new ListOfApprovedBrandsDatatable();
				dt.setSno(i);
				dt.setBrand(rs.getString("brand_name"));
				dt.setEtin(rs.getString("code_generate_through"));
				dt.setMrp(rs.getDouble("mrp"));
				dt.setPackage1(rs.getString("package_name"));
				
				dt.setRegistring(rs.getString("name"));
				
				dt.setQuantity(rs.getInt("quantity"));
				list.add(dt);
				i++;
				}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}		
		return list;
	}
	public void print(ListOfApprovedBrandsAction action) {
		String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
		String relativePath = mypath + File.separator + "ExciseUp"
				+ File.separator + "reports";

		String relativePathpdf = mypath + File.separator + "ExciseUp"
				+ File.separator + "reports" + File.separator + "pdf";

		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		String reportQuery = null;
		try {
			con = ConnectionToDataBase.getConnection();
			if (action.getLicenseing().equalsIgnoreCase("BEER")) {

				reportQuery = " SELECT  c.brewery_nm as name, a.distillery_id, a.brand_name, b.package_name, b.quantity, b.code_generate_through, b.mrp  "
						+ " from  distillery.brand_registration a, distillery.packaging_details b, public.bre_mst_b1_lic c  "
						+ " where a.brewery_id=c.vch_app_id_f and a.brand_id=b.brand_id_fk and "
						+ " a.license_category=? order by a.brand_name";

			} else {
				reportQuery = " SELECT  c.vch_undertaking_name as name, a.distillery_id, a.brand_name, b.package_name, b.quantity, b.code_generate_through, b.mrp  "
						+ " from  distillery.brand_registration a, distillery.packaging_details b, public.dis_mst_pd1_pd2_lic c  "
						+ " where a.distillery_id=c.int_app_id_f and a.brand_id=b.brand_id_fk and "
						+ " a.license_category=? order by a.brand_name";
			}
			

			
		
			 


			pst = con.prepareStatement(reportQuery);
			pst.setString(1, action.getLicenseing());
			

			rs = pst.executeQuery();
			if (rs.next()) {

				Map parameters = new HashMap();
				parameters.put("REPORT_CONNECTION", con);
				parameters.put("SUBREPORT_DIR", relativePath + File.separator);
				parameters.put("image", relativePath + File.separator);
				
				parameters.put("license", action.getLicenseing());
				
				System.out.println("------------------------"+action.getLicenseing());

				JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);

				jasperReport = (JasperReport) JRLoader.loadObject(relativePath
						+ File.separator + "listofbrands.jasper");

				JasperPrint print = JasperFillManager.fillReport(jasperReport,
						parameters, jrRs);
				Random rand = new Random();
				int n = rand.nextInt(250) + 1;

				JasperExportManager.exportReportToPdfFile(print,
						relativePathpdf + File.separator + "listofbrands" + n
								+ ".pdf");
				action.setPdfname("listofbrands" + n + ".pdf");
				action.setPrintFlag(true);
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("No Data Found", "No Data Found"));
				action.setPrintFlag(false);
			}

		} 
			  catch (JRException e) {
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

	}
	
}
