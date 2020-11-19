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
import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import com.mentor.action.ReportOnDispatcherFL1FL1AAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.Utility;

public class ReportOnDispatcherFL1FL1AImpl {

	public ArrayList getdistricts(ReportOnDispatcherFL1FL1AAction action) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("---Select---");
		item.setValue("");
		list.add(item);

		String query = " SELECT zoneid, chargeid, districtid, description, district_user, deo, "
				+ " license_district_id, officer_email FROM public.district";
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("description"));
				item.setValue(String.valueOf(rs.getInt("districtid")));
				list.add(item);
			}
		} catch (Exception e) {
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

	/*** --- for Brewery --- ***/

	public void printForAllDistrictForBrewery(
			ReportOnDispatcherFL1FL1AAction action) {

		String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;

		String relativePath = mypath + File.separator + "ExciseUp"
				+ File.separator + "Dispatch_Report_FL1_FL1A";

		String relativePathpdf = mypath + File.separator + "ExciseUp"
				+ File.separator + "Dispatch_Report_FL1_FL1A";

		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		String reportQuery = null;
		try {
			con = ConnectionToDataBase.getConnection();

			reportQuery = "select distinct  a.brewery_id, a.vch_route_detail, a.vch_gatepass_no, a.dt_date ,                "
					+ "b.vch_gatepass_no, b.dispatch_box,(((b.size*b.dispatch_bottle)*e.quantity)/1000) as bl,          "
					+ "b.dt, c.vch_app_id_f, c.brewery_nm, d.brand_name FROM bwfl.gatepass_to_districtwholesale a,      "
					+ "bwfl.fl2_stock_trxn b, public.bre_mst_b1_lic c, distillery.brand_registration d,                 "
					+ "distillery.packaging_details e,distillery.box_size_details f where a.brewery_id=b.brewery_id     "
					+ "and a.vch_gatepass_no=b.vch_gatepass_no and a.dt_date=b.dt and a.brewery_id=c.vch_app_id_f and   "
					+ "	  d.brand_id=b.int_brand_id and f.box_id=e.box_id  and a.dt_date between '"
					+ Utility.convertUtilDateToSQLDate(action.getFromdate())
					+ "' and '"
					+ Utility.convertUtilDateToSQLDate(action.getTodate())
					+ "'  order by a.dt_date";

			pst = con.prepareStatement(reportQuery);

			rs = pst.executeQuery();
			if (rs.next()) {
				rs = pst.executeQuery();
				Map parameters = new HashMap();
				parameters.put("REPORT_CONNECTION", con);
				parameters.put("SUBREPORT_DIR", relativePath + File.separator);
				parameters.put("image", relativePath + File.separator);

				JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);

				jasperReport = (JasperReport) JRLoader.loadObject(relativePath
						+ File.separator
						+ "Dispatch_Report_FL1_FL1A_Brewery.jasper");

				JasperPrint print = JasperFillManager.fillReport(jasperReport,
						parameters, jrRs);
				Random rand = new Random();
				int n = rand.nextInt(250) + 1;

				JasperExportManager.exportReportToPdfFile(print,
						relativePathpdf + File.separator
								+ "Dispatch_Report_FL1_FL1A_Brewery" + n
								+ ".pdf");
				action.setPdfname("Dispatch_Report_FL1_FL1A_Brewery" + n
						+ ".pdf");
				action.setPrintFlag(true);
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("No Data Found", "No Data Found"));
				action.setPrintFlag(false);
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
	}

	/*** --- for Distillery --- ***/

	public void print_for_All_District(ReportOnDispatcherFL1FL1AAction action) {

		String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;

		String relativePath = mypath + File.separator + "ExciseUp"
				+ File.separator + "Dispatch_Report_FL1_FL1A";

		String relativePathpdf = mypath + File.separator + "ExciseUp"
				+ File.separator + "Dispatch_Report_FL1_FL1A";

		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		String reportQuery = null;
		try {
			con = ConnectionToDataBase.getConnection();

			reportQuery = "select distinct  a.int_dist_id, a.vch_route_detail, a.vch_gatepass_no, a.dt_date , "
					+ " b.vch_gatepass_no, b.dispatch_box,(((b.size*b.dispatch_bottle)*e.quantity)/1000) as bl, "
					+ " b.dt, c.int_app_id_f, c.vch_undertaking_name, d.brand_name FROM distillery.gatepass_to_districtwholesale a, "
					+ " distillery.fl2_stock_trxn b, public.dis_mst_pd1_pd2_lic c, distillery.brand_registration d, "
					+ " distillery.packaging_details e,distillery.box_size_details f where a.int_dist_id=b.int_dissleri_id "
					+ " and a.vch_gatepass_no=b.vch_gatepass_no and a.dt_date=b.dt and a.int_dist_id=c.int_app_id_f and "
					+ " d.brand_id=b.int_brand_id and a.dt_date between '"
					+ Utility.convertUtilDateToSQLDate(action.getFromdate())
					+ "' and '"
					+ Utility.convertUtilDateToSQLDate(action.getTodate())
					+ "' and f.box_id=e.box_id order by a.dt_date";

			pst = con.prepareStatement(reportQuery);

			rs = pst.executeQuery();
			if (rs.next()) {
				rs = pst.executeQuery();
				Map parameters = new HashMap();
				parameters.put("REPORT_CONNECTION", con);
				parameters.put("SUBREPORT_DIR", relativePath + File.separator);
				parameters.put("image", relativePath + File.separator);

				JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);

				jasperReport = (JasperReport) JRLoader.loadObject(relativePath
						+ File.separator + "DispatchReportOf_FL1_FL1A.jasper");

				JasperPrint print = JasperFillManager.fillReport(jasperReport,
						parameters, jrRs);
				Random rand = new Random();
				int n = rand.nextInt(250) + 1;

				JasperExportManager.exportReportToPdfFile(print,
						relativePathpdf + File.separator
								+ "DispatchReportOf_FL1_FL1A" + n + ".pdf");
				action.setPdfname("DispatchReportOf_FL1_FL1A" + n + ".pdf");
				action.setPrintFlag(true);
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("No Data Found", "No Data Found"));
				action.setPrintFlag(false);
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
	}

	/*** --- for CL --- ***/

	public void printForCL(ReportOnDispatcherFL1FL1AAction action) {

		String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;

		String relativePath = mypath + File.separator + "ExciseUp"
				+ File.separator + "Dispatch_Report_FL1_FL1A";

		String relativePathpdf = mypath + File.separator + "ExciseUp"
				+ File.separator + "Dispatch_Report_FL1_FL1A";

		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		String reportQuery = null;
		try {
			con = ConnectionToDataBase.getConnection();

			reportQuery = "select distinct  a.int_dist_id, a.vch_root_details, a.vch_gatepass_no, a.dt_date ,                "
					+ "	b.vch_gatepass_no, b.dispatchd_box,(((b.size::NUMERIC*b.dispatchd_bottl)*e.quantity)/1000) as bl,    "
					+ "	b.dt_date, c.int_app_id_f, c.vch_undertaking_name, d.brand_name                                      "
					+ "	FROM distillery.gatepass_to_manufacturewholesale_cl a,                                               "
					+ "	distillery.cl2_stock_trxn b, public.dis_mst_pd1_pd2_lic c, distillery.brand_registration d,          "
					+ "	distillery.packaging_details e, distillery.box_size_details f where a.int_dist_id=b.int_dissleri_id  "
					+ "	and a.vch_gatepass_no=b.vch_gatepass_no and a.dt_date=b.dt_date and a.int_dist_id=c.int_app_id_f and "
					+ "	d.brand_id=b.int_brand_id and f.box_id=e.box_id and a.dt_date between '"
					+ Utility.convertUtilDateToSQLDate(action.getFromdate())
					+ "' and '"
					+ Utility.convertUtilDateToSQLDate(action.getTodate())
					+ "'";

			pst = con.prepareStatement(reportQuery);

			rs = pst.executeQuery();
			if (rs.next()) {
				rs = pst.executeQuery();
				Map parameters = new HashMap();
				parameters.put("REPORT_CONNECTION", con);
				parameters.put("SUBREPORT_DIR", relativePath + File.separator);
				parameters.put("image", relativePath + File.separator);

				JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);

				jasperReport = (JasperReport) JRLoader.loadObject(relativePath
						+ File.separator
						+ "DispatchReportOf_FL1_FL1A_CL.jasper");

				JasperPrint print = JasperFillManager.fillReport(jasperReport,
						parameters, jrRs);
				Random rand = new Random();
				int n = rand.nextInt(250) + 1;

				JasperExportManager.exportReportToPdfFile(print,
						relativePathpdf + File.separator
								+ "DispatchReportOf_FL1_FL1A_CL" + n + ".pdf");
				action.setPdfname("DispatchReportOf_FL1_FL1A_CL" + n + ".pdf");
				action.setPrintFlag(true);
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("No Data Found", "No Data Found"));
				action.setPrintFlag(false);
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
	}

	// ---------------------------- print_for_Selected_District ---------------

	public void print_for_Selected_District(
			ReportOnDispatcherFL1FL1AAction action) {
		String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;

		String relativePath = mypath + File.separator + "ExciseUp"
				+ File.separator + "Dispatch_Report_FL1_FL1A" + File.separator
				+ "selected_district" + File.separator + "jasper";
		String relativePathpdf = mypath + File.separator + "ExciseUp"
				+ File.separator + "Dispatch_Report_FL1_FL1A" + File.separator
				+ "selected_district" + File.separator + "pdf";

		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		String reportQuery = null;
		try {
			con = ConnectionToDataBase.getConnection();

			reportQuery = " SELECT distinct a.int_dist_id, a.vch_distillary_name, a.vch_distillary_address, "
					+ " a.vch_gatepass_no, a.dt_date,a.licence_district,c.description, b.avl_bottl, "
					+ " b.avl_box, b.dispatchd_bottl, b.dispatchd_box, b.dt_date FROM "
					+ " distillery.gatepass_to_manufacturewholesale_cl a , distillery.cl2_stock_trxn b, "
					+ " public.district c where a.int_dist_id=b.int_dissleri_id and "
					+ " a.vch_gatepass_no=b.vch_gatepass_no and a.dt_date=b.dt_date and "
					+ " a.licence_district=c.districtid and c.districtid="
					+ action.getDistrictid()
					+ " "
					+ " and a.dt_date between '"
					+ Utility.convertUtilDateToSQLDate(action.getFromdate())
					+ "' "
					+ " and '"
					+ Utility.convertUtilDateToSQLDate(action.getTodate())
					+ "' order by a.dt_date, c.description";

			pst = con.prepareStatement(reportQuery);
			rs = pst.executeQuery();
			if (rs.next()) {
				rs = pst.executeQuery();
				Map parameters = new HashMap();
				parameters.put("REPORT_CONNECTION", con);
				parameters.put("SUBREPORT_DIR", relativePath + File.separator);
				parameters.put("image", relativePath + File.separator);

				JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);

				jasperReport = (JasperReport) JRLoader.loadObject(relativePath
						+ File.separator
						+ "DispatchReportOf_FL1_FL1A_SelectedDistrict.jasper");

				JasperPrint print = JasperFillManager.fillReport(jasperReport,
						parameters, jrRs);
				Random rand = new Random();
				int n = rand.nextInt(250) + 1;

				JasperExportManager.exportReportToPdfFile(print,
						relativePathpdf + File.separator
								+ "DispatchReportOf_FL1_FL1A_SelectedDistrict"
								+ n + ".pdf");
				action.setPdfname("DispatchReportOf_FL1_FL1A_SelectedDistrict"
						+ n + ".pdf");
				action.setPrintFlag(true);

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("No Data Found", "No Data Found"));
				action.setPrintFlag(false);
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

	}

	/*** --- validation --- ***/

	public boolean isCheckRadio(ReportOnDispatcherFL1FL1AAction action) {

		boolean isValid = false;

		if (action.getRadio() == null) {

			action.setSuccessMsg("");
			action.setErrorMsg("Select one radio button !");
			isValid = false;

		} else {

			action.setSuccessMsg("");
			action.setErrorMsg("");
			isValid = true;
		}

		return isValid;
	}

	public boolean isCheckFromDate(ReportOnDispatcherFL1FL1AAction action) {

		boolean isValid = false;

		if (action.getFromdate() == null) {

			action.setSuccessMsg("");
			action.setErrorMsg("Select From date !");
			isValid = false;

		} else {

			action.setSuccessMsg("");
			action.setErrorMsg("");
			isValid = true;
		}

		return isValid;
	}

	public boolean isChecktoDate(ReportOnDispatcherFL1FL1AAction action) {

		boolean isValid = false;

		if (action.getTodate() == null) {

			action.setSuccessMsg("");
			action.setErrorMsg("Select to date !");
			isValid = false;

		} else {

			action.setSuccessMsg("");
			action.setErrorMsg("");
			isValid = true;
		}

		return isValid;
	}

}
