package com.mentor.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import com.mentor.action.Stock_ReportAction;
import com.mentor.connectiondb.ConnectionToDataBase;
import com.mentor.datatable.GatepassToDistrictBWFLDT;
import com.mentor.datatable.Stock_ReportDatatable;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;

public class Stock_ReportImpl {

	public ArrayList getDataList(Stock_ReportAction action) {
		ArrayList list = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 1;
		String query = "";

		try {
			if (action.getRadio().equals("D")) {
				
				query = " SELECT d.vch_undertaking_name as name, c.quantity, b.brand_name, c.package_name, " +
						" (COALESCE(a.int_stock,0)-COALESCE(a.int_dispatched,0)) as currentStock from " +
						" distillery.bottling_stock a, distillery.brand_registration b, distillery.packaging_details c, " +
						" public.dis_mst_pd1_pd2_lic d where a.int_brand_id=b.brand_id AND a.int_pckg_id=c.package_id " +
						" AND a.int_dissleri_id=b.distillery_id AND b.distillery_id=d.int_app_id_f order by b.brand_name";
				
			} else if (action.getRadio().equals("B")) {		
				
				query = " SELECT d.brewery_nm as name, c.quantity, b.brand_name, c.package_name, (COALESCE(a.int_stock,0)-COALESCE(a.int_dispatched,0)) " +
						" as currentStock from bwfl.bottling_stock a, distillery.brand_registration b, distillery.packaging_details c, " +
						" public.bre_mst_b1_lic d  where a.int_brand_id=b.brand_id AND a.int_pckg_id=c.package_id AND " +
						" a.int_brewery_id=b.brewery_id AND b.brewery_id=d.vch_app_id_f order by b.brand_name";	
				
			}

			list = new ArrayList();
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Stock_ReportDatatable dt = new Stock_ReportDatatable();
				dt.setSno(i);
				dt.setName(rs.getString("name"));
				dt.setBrand_name(rs.getString("brand_name"));
				dt.setPackage_name(rs.getString("package_name"));
				dt.setSize(rs.getInt("quantity"));
				dt.setCurrent_stock(rs.getInt("currentStock"));
				list.add(dt);
				i++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)rs.close();
				if (ps != null)ps.close();
				if (con != null)con.close();
			} catch (Exception exx) {
				exx.printStackTrace();
			}
		}
		return list;
	}

	public boolean printReport(Stock_ReportAction action) {

		String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
		String relativePath = mypath + File.separator + "ExciseUp" + File.separator + "Stock_Report" + File.separator + "jasper";
		String relativePathpdf = mypath + File.separator + "ExciseUp" + File.separator + "Stock_Report" + File.separator + "pdf";
		JasperReport jasperReport = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String reportQuery = "";
		boolean printFlagImpl = false;
		try {
			
			if (action.getRadio().equals("D")) {
				
				/*reportQuery = " SELECT d.vch_undertaking_name as name, c.quantity, b.brand_name, c.package_name, " +
						" (COALESCE(a.int_stock,0)-COALESCE(a.int_dispatched,0)) as currentStock from " +
						" distillery.bottling_stock a, distillery.brand_registration b, distillery.packaging_details c, " +
						" public.dis_mst_pd1_pd2_lic d where a.int_brand_id=b.brand_id AND a.int_pckg_id=c.package_id " +
						" AND a.int_dissleri_id=b.distillery_id AND b.distillery_id=d.int_app_id_f order by b.brand_name";*/
				
				
				reportQuery = " SELECT d.vch_undertaking_name as name, c.quantity, b.brand_name, c.package_name, " +
						" (COALESCE(a.int_stock,0)-COALESCE(a.int_dispatched,0)) as currentStock from distillery.bottling_stock a, " +
						" distillery.brand_registration b, distillery.packaging_details c, public.dis_mst_pd1_pd2_lic d where " +
						" a.int_brand_id=b.brand_id AND a.int_pckg_id=c.package_id AND a.int_dissleri_id=b.distillery_id AND " +
						" b.distillery_id=d.int_app_id_f group by name, c.quantity, b.brand_name, c.package_name, currentStock " +
						" order by b.brand_name";
				
				
			} else if (action.getRadio().equals("B")) {		
				/*
				reportQuery = " SELECT d.brewery_nm as name, c.quantity, b.brand_name, c.package_name, (COALESCE(a.int_stock,0)-COALESCE(a.int_dispatched,0)) " +
						" as currentStock from bwfl.bottling_stock a, distillery.brand_registration b, distillery.packaging_details c, " +
						" public.bre_mst_b1_lic d  where a.int_brand_id=b.brand_id AND a.int_pckg_id=c.package_id AND " +
						" a.int_brewery_id=b.brewery_id AND b.brewery_id=d.vch_app_id_f order by b.brand_name";	*/
				
				
				reportQuery = " SELECT d.brewery_nm as name, c.quantity, b.brand_name, c.package_name, " +
						" (COALESCE(a.int_stock,0)-COALESCE(a.int_dispatched,0)) as currentStock from bwfl.bottling_stock a, " +
						" distillery.brand_registration b, distillery.packaging_details c, public.bre_mst_b1_lic d where " +
						" a.int_brand_id=b.brand_id AND a.int_pckg_id=c.package_id AND a.int_brewery_id=b.brewery_id AND " +
						" b.brewery_id=d.vch_app_id_f group by name, c.quantity, b.brand_name, c.package_name, currentStock " +
						" order by b.brand_name";
				
				
			}
			
			
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(reportQuery);
			rs = ps.executeQuery();
			if (rs.next()) {
				Map parameters = new HashMap();
				parameters.put("REPORT_CONNECTION", con);
				parameters.put("image", relativePath + File.separator);
				JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);				
				jasperReport = (JasperReport) JRLoader.loadObject(relativePath + File.separator + "Stock_Report.jasper");				
				JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, jrRs);
				Random rand = new Random();
				int n = rand.nextInt(250) + 1;
				JasperExportManager.exportReportToPdfFile(print, relativePathpdf + File.separator + "Stock_Report_"+n+".pdf");
				action.setPdfname("Stock_Report_"+n+".pdf");
				action.setPrintFlag(true);
				printFlagImpl = true;
			} else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("No Data Found", "No Data Found"));				
				printFlagImpl = false;
				action.setPrintFlag(false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)rs.close();
				if (ps != null)ps.close();
				if (con != null)con.close();
			} catch (Exception exx) {
				exx.printStackTrace();
			}
		}
		return printFlagImpl;
	}
}
