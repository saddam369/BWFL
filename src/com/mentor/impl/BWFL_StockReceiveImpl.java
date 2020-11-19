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
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mentor.action.BWFL_StockReceiveAction;
import com.mentor.datatable.BWFLImportDataTable;
import com.mentor.datatable.BWFL_StockReceiveDataTable;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class BWFL_StockReceiveImpl {

	public String getSugarmill(BWFL_StockReceiveAction ac) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// String
			// queryList="SELECT int_app_id_f,vch_undertaking_name,vch_wrk_add  from  dis_mst_pd1_pd2_lic where vch_wrk_phon="+ResourceUtil.getUserNameAllReq().trim();

			String queryList = 	" select etin_unit_id,int_app_id,vch_licence_no, vch_firm_name  " +
								" from licence.fl2_2b_2d_19_20 where  vch_license_type='FL2D' and  vch_mobile_no" +
								" = '"+ ResourceUtil.getUserNameAllReq().trim() + "' ";
			//////System.out.println("queryList="+queryList);
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ac.setDistillery_nm(rs.getString("vch_firm_name"));
				ac.setDistillery_id(rs.getInt("int_app_id"));
				//ac.setDistillery_adrs(rs.getString("vch_firm_add"));
				//ac.setLicenceNoId(rs.getString("vch_licence_no"));
				ac.setEtin_unit_id(rs.getInt("int_app_id"));

			}

			// pstmt.executeUpdate();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			 e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return "";

	}

	// //////////// ---------------------------m didt id ----------------------

	public int getSugarmill_Id() {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			// String
			// queryList="SELECT int_app_id_f,vch_undertaking_name,vch_wrk_add  from  dis_mst_pd1_pd2_lic where vch_wrk_phon="+ResourceUtil.getUserNameAllReq().trim();

			String queryList = " select int_app_id, vch_firm_name  from licence.fl2_2b_2d_19_20 where vch_license_type='FL2D' and vch_mobile_no = '"
					+ ResourceUtil.getUserNameAllReq().trim() + "' ";

			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				id = rs.getInt("int_app_id");

			}

		} catch (SQLException se) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));	
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return id;

	}

	// ------------------------------------------------------------------------------

	// ----------------------------- get Liqure Type
	// --------------------------------------------

	public ArrayList getLiqureType() {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);
		String SQl = "SELECT id, description FROM distillery.imfl_type WHERE id !=3 ORDER BY id ";
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("description"));
				item.setValue(rs.getString("id"));
				list.add(item);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			
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

	// ----------------------------- get Licence NO.
	// --------------------------------------------

	public ArrayList getLicenseNo(BWFL_StockReceiveAction action, String lice) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);

		String SQl = " select distinct license_number FROM distillery.brand_registration_19_20 where " +
				" int_fl2d_id in (select unit_id from fl2d.fl2d_unit_mapping where fl2d_id='"	+ action.getDistillery_id() +"')  and vch_license_type='IU' and license_number is not null";
		try {
//////System.out.println("get license="+SQl);
		 

			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);
			//ps.setString(1, lice.trim());
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("license_number"));
				item.setValue(rs.getString("license_number"));

				list.add(item);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			
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

	// ----------------------------- get Brand
	// --------------------------------------------

	public ArrayList getBrandName() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		BWFL_StockReceiveAction bof = (BWFL_StockReceiveAction) facesContext
				.getApplication()
				.createValueBinding("#{bWFL_StockReceiveAction}")
				.getValue(facesContext);

		String lic = bof.getLicenceType();

		String licNo = bof.getLicenceNoId();
		
		//distillery_id
		// ////System.out.println("---------- brand mthd  lic id -------------"+licNo);

		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);
		String sql = "";

		// sql=
		// "SELECT brand_id, brand_name FROM distillery.brand_registration_19_20 where distillery_id=? order by brand_id ";

		try {

			/*if (lic.equalsIgnoreCase("CL")) {
				sql = 
					 * "	SELECT brand_id, brand_name FROM distillery.brand_registration_19_20 "
					 * + "  where license_category='CL' "+
					 * "   and   license_number =(select vch_pd1_pd2_lic_no  from  dis_mst_pd1_pd2_lic where int_app_id_f=?) "
					 * + "     order by brand_id " ;
					 

				"	SELECT brand_id, brand_name FROM distillery.brand_registration_19_20 "
						+ "  where license_category='CL' "
						+ "   and distillery_id=? and  license_number = ? "
						+ "     order by brand_id ";

			} else if (lic.equalsIgnoreCase("FL3")) {
				sql = 
					 * " SELECT brand_id, brand_name FROM distillery.brand_registration_19_20  "
					 * +
					 * "   where license_category='IMFL' and vch_license_type='FL3' "
					 * +
					 * "   and   license_number =(select vch_licence_no  from  licence.licence_entery_fl3_fl1 where  "
					 * + "      int_distillery_id=(select int_app_id_f  "+
					 * "   from  dis_mst_pd1_pd2_lic where int_app_id_f=?)) "+
					 * "  order by brand_id " ;
					 

				"	SELECT brand_id, brand_name FROM distillery.brand_registration_19_20 "
						+ "  where license_category='IMFL' "
						+ "   and distillery_id=? and  license_number = ? "
						+ "     order by brand_id ";

			}

			else if (lic.equalsIgnoreCase("FL3A")) {

				sql =

				
				 * "	 SELECT brand_id, brand_name FROM distillery.brand_registration_19_20  "
				 * +
				 * "	  where license_category='IMFL' and vch_license_type='FL3A' "
				 * +
				 * "	  and   license_number =(select  vch_license_fl3a  from licence.fl3a_fl1a where  "
				 * + "	   int_distillery_id=(select int_app_id_f  "+
				 * "	   from  dis_mst_pd1_pd2_lic where int_app_id_f=?)) "+
				 * "	  order by brand_id  ";
				 

				"	SELECT brand_id, brand_name FROM distillery.brand_registration_19_20 "
						+ "  where license_category='IMFL' "
						+ "   and distillery_id=? and  license_number = ? "
						+ "     order by brand_id ";

			}

			else if (lic.equalsIgnoreCase("FL2D")) {

				String filter="";
				////System.out.println("id="+bof.getOther_unit_id()+"--");
				if(bof.getOther_unit_id().equals("") || 
						bof.getOther_unit_id()=="" || 
						bof.getOther_unit_id().length()<=0 ||
						bof.getOther_unit_id()==null){
					
				}else{
					filter=" and int_fl2d_id='"+bof.getOther_unit_id()+"' ";
				}

				sql = "	SELECT brand_id, brand_name FROM distillery.brand_registration_19_20 "
					+ " where vch_license_type='IU'  and int_fl2d_id>0 "+filter+" " +
					//" in (select unit_id from fl2d.fl2d_unit_mapping where fl2d_id=?)  and  license_number =? " +
					" order by brand_name ";

				sql="SELECT a.brand_id, a.brand_name FROM distillery.brand_registration_19_20 a,fl2d.fl2d_unit_mapping b" +
					" where a.int_fl2d_id=b.unit_id and a.vch_license_type='IU' and b.fl2d_id in ? and  a.license_number =? " +
			      " order by brand_name ";
				////System.out.println("get brands="+sql);
			
			}
			else {

			}*/
			
			
			
			

			String filter="";
			//////System.out.println("id="+bof.getOther_unit_id()+"--");
			if(bof.getOther_unit_id()==null ||
					bof.getOther_unit_id().equals("") || 
					bof.getOther_unit_id()=="" || 
					bof.getOther_unit_id().length()<=0 ){
				
			}else{
				filter=" and int_fl2d_id='"+bof.getOther_unit_id()+"' ";
			}

			sql = "	SELECT brand_id, brand_name FROM distillery.brand_registration_19_20 "
				+ " where vch_license_type='IU'  and int_fl2d_id>0 "+filter+" " +
				//" in (select unit_id from fl2d.fl2d_unit_mapping where fl2d_id=?)  and  license_number =? " +
				" order by brand_name ";

			/*sql="SELECT a.brand_id, a.brand_name FROM distillery.brand_registration_19_20 a,fl2d.fl2d_unit_mapping b" +
				" where a.int_fl2d_id=b.unit_id and a.vch_license_type='IU' and b.fl2d_id in ? and  a.license_number =? " +
		      " order by brand_name ";*/
			//////System.out.println("get brands="+sql);
		
			//////System.out.println("brand="+sql);
			//////System.out.println("licNo.trim()="+licNo.trim());
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
		/*	if ((lic.equalsIgnoreCase("CL")) || (lic.equalsIgnoreCase("FL3"))) {
				// ////System.out.println("------------if  brand 111111   -----");
				ps.setInt(1, bof.getDistillery_id());
				//ps.setInt(1, this.getSugarmill_Id());
				//ps.setInt(1, bof.getUnit_id1());
				ps.setString(2, licNo.trim());
			} else {
				

				//ps.setInt(1, bof.getDistillery_id());
				//ps.setInt(1, this.getSugarmill_Id());
				//ps.setString(2, licNo.trim());

			}*/
			//////System.out.println(licNo.trim()+"=|-|="+bof.getDistillery_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("brand_name"));
				item.setValue(rs.getString("brand_id"));
				list.add(item);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			
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

	// ----------------------------- get Packaging Name
	// --------------------------------------------

	public ArrayList getPackagingName(String brand_id) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);
		String SQl = "SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id "
				+ "	from distillery.brand_registration_19_20 a , "
				+ "	distillery.packaging_details_19_20 b "
				+ "	where a.brand_id=b.brand_id_fk  " +
				// "	and a.distillery_id=? "+
				"	and brand_id =? and b.code_generate_through is not null ";
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);
			// ps.setInt(1, this.getSugarmill_Id());
			ps.setInt(1, Integer.parseInt(brand_id));
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("package_name"));
				item.setValue(rs.getString("package_id"));
				list.add(item);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			
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

	// ----------------------------- get quantity
	// --------------------------------------------

	public ArrayList getquantity(String brand_Id, String packging_Id) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		/*
		 * item.setLabel("--SELECT--"); item.setValue(""); list.add(item);
		 */
		String SQl =

		"SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id "
				+ "	from distillery.brand_registration_19_20 a , "
				+ "	distillery.packaging_details_19_20 b "
				+ "	where a.brand_id=b.brand_id_fk  " +
				// "	and a.distillery_id=?  "+
				"	and brand_id =?  and b.package_id=?";
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);
			// ps.setInt(1, this.getSugarmill_Id());
			ps.setInt(1, Integer.parseInt(brand_Id));
			ps.setInt(2, Integer.parseInt(packging_Id));
			rs = ps.executeQuery();
			while (rs.next()) {

				//////System.out.println("rs.getDoublequantity"+ rs.getDouble("quantity"));
				item = new SelectItem();
				item.setLabel(rs.getString("quantity"));
				item.setValue(rs.getDouble("quantity"));
				list.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			
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

	// ----------------------------- 2nd -----------------

	public String getqty(String brand_Id, String packging_Id,BWFL_StockReceiveDataTable dt) {
		String qty = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList =

			"SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id ,b.mrp "
					+ "	from distillery.brand_registration_19_20 a , "
					+ "	distillery.packaging_details_19_20 b "
					+ "	where a.brand_id=b.brand_id_fk  " +
					// "	and a.distillery_id=?  "+
					"	and brand_id =?  and b.package_id=?";

			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, Integer.parseInt(brand_Id));
			pstmt.setInt(2, Integer.parseInt(packging_Id));

			rs = pstmt.executeQuery();

			while (rs.next()) {

				qty = rs.getString("quantity");
				/*if(rs.getDouble("mrp")>6000){
					 dt.setNoOfBottlesPerCase(1);
					 dt.setNoOfBottleFlg(true);
				}else{
				dt.setNoOfBottleFlg(false);

					}*/
			}

			// pstmt.executeUpdate();
		} catch (SQLException se) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));
			
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return qty;

	}

	// ---------------------------------------------- save
	// --------------------------

	public int getMax_id() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id = 0;
		//String query = "SELECT max(seq) as id FROM fl2d.mst_stock_receive_19_20";
		
		String query = "select nextval('fl2d.mst_stock_receive_sequence_19_20')as id";
		//////System.out.println("getMax_id"+query);
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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
		//////System.out.println("id="+id);
		return id + 1;
	}
	
	
	// =====================get max id sequence==============================

	public int maxDutyRegId() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(int_id) as id FROM distillery.duty_register_19_20 ";
		int maxid = 0;
		//////System.out.println("query="+query);
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
	

	public void save(BWFL_StockReceiveAction action) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		String insDutyQr = "";
		int dutyId = this.maxDutyRegId();
		double totalPermitFees=0.0;
		int totalBottles=0;
	
		try {

			String query =  " INSERT INTO fl2d.mst_stock_receive_19_20(int_fl2d_id, int_brand_id, " +
							" int_pack_id, int_quantity, int_planned_bottles, int_boxes, " +
							" int_liquor_type, vch_license_type, plan_dt, licence_no, cr_date, seq, permit_no,loginusr,district_id,seqfrom,seqto,caseseq, permit_fee,box_size) " +
							" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?,?,(select districtid from public.district where deo='"+ ResourceUtil.getUserNameAllReq().trim() + "'),?,?,?, ?,?) ";
			
			
			
			insDutyQr = " INSERT INTO distillery.duty_register_19_20( " +
						" int_id, int_distillery_id, date_crr_date, vch_duty_type, int_quantity, int_value, vch_description,permitno_fl2d) " +
						" VALUES (?, ?, ?, ?, ?, ?, ?,?)";

			

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
		

			if (action.getBrandPackagingDataList().size() > 0) {

				for (int i = 0; i < action.getBrandPackagingDataList().size(); i++) {
					saveStatus = 0;
					pstmt = conn.prepareStatement(query);
					int j = 1;
					BWFL_StockReceiveDataTable table = (BWFL_StockReceiveDataTable) action.getBrandPackagingDataList().get(i);
					if (table.isUpdateflg() == false) {
			
						pstmt.setInt(1, Integer.parseInt(action.getUnit_id().trim()));
						pstmt.setInt(2, Integer.parseInt(table.getBrandPackagingData_Brand()));
						pstmt.setInt(3, Integer.parseInt(table.getBrandPackagingData_Packaging()));
						pstmt.setDouble(4, Double.parseDouble(table.getBrandPackagingData_Quantity()));
						pstmt.setInt(5, table.getBrandPackagingData_PlanNoOfBottling());
						pstmt.setInt(6, table.getBrandPackagingData_NoOfBoxes());
						pstmt.setInt(7, Integer.parseInt(action.getLiqureTypeId()));
						pstmt.setString(8, action.getLicenceType());
						pstmt.setDate(9, Utility.convertUtilDateToSQLDate(action.getDateOfBottling()));
						pstmt.setString(10, action.getLicenceNoId());
						pstmt.setDate(11, Utility.convertUtilDateToSQLDate(new Date()));
						pstmt.setInt(12, this.getMax_id());
						pstmt.setString(13, action.getPermitNo());
						pstmt.setString(14, ResourceUtil.getUserNameAllReq().trim()); 
						/*long	serialno = getSerialNoFl2D(new Double(table.getBrandPackagingData_PlanNoOfBottling()).longValue(),action.getDateOfBottling());
						pstmt.setLong(15, serialno);
						pstmt.setLong(16,serialno+ new Double(table.getBrandPackagingData_PlanNoOfBottling()).longValue());
						pstmt.setLong(17,getcaseNoFl2d(new Double(table.getBrandPackagingData_NoOfBoxes()).longValue(),action.getDateOfBottling()));*/
						
						
						
						pstmt.setLong(15, 0);
						pstmt.setLong(16,0);
						pstmt.setLong(17,0);
						
						
						
						
						pstmt.setDouble(18,table.getCalPermitFee_dt());
						pstmt.setDouble(19,table.getNoOfBottlesPerCase());
						saveStatus = pstmt.executeUpdate();
						totalPermitFees+= table.getCalPermitFee_dt();
						totalBottles+=table.getBrandPackagingData_PlanNoOfBottling();
					}
				}
			}
			
		
			if(saveStatus > 0)
			{	//////System.out.println("3");
				saveStatus = 0;
				pstmt = conn.prepareStatement(insDutyQr);
				
				pstmt.setInt(1, dutyId);
				pstmt.setInt(2, Integer.parseInt(action.getUnit_id().trim()));
				pstmt.setDate(3, Utility.convertUtilDateToSQLDate(action.getDateOfBottling()));
				pstmt.setString(4, "FL2D_PERMIT_FEE");
				pstmt.setDouble(5, totalBottles);
				pstmt.setDouble(6, totalPermitFees);
				//pstmt.setString(7, action.getLicenceType()+"-"+action.getPermitNo());
				pstmt.setString(7,"Permit Fee for Overseas FL");
				pstmt.setString(8, action.getPermitNo());
				saveStatus = pstmt.executeUpdate();
				//////System.out.println("4");
			}
			
			if (saveStatus > 0) {
				conn.commit();
				ResourceUtil.addErrorMessage(Constants.SAVED_SUCESSFULLY,Constants.SAVED_SUCESSFULLY);
				action.reset();
			} else {
				conn.rollback();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,Constants.NOT_SAVED);
			}
		}catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage())); 
		}
		catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage())); 
		}finally {
			try {
				if (conn != null)conn.close();
				if (pstmt != null)pstmt.close();
				if (rs != null)rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// --------------------------------------------

	public void save1(BWFL_StockReceiveAction action) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		try {
			String query = " INSERT INTO distillery.mst_bottling_plan_19_20(int_distillery_id, " +
						   " int_brand_id, int_pack_id, int_quantity, int_planned_bottles, " +
						   " int_boxes, int_liquor_type, vch_license_type, plan_dt, licence_no, cr_date) " +
						   " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?) ";

			conn = ConnectionToDataBase.getConnection();
			if (action.getBrandPackagingDataList().size() > 0) {
				for (int i = 0; i < action.getBrandPackagingDataList().size(); i++) {
					saveStatus = 0;
					pstmt = conn.prepareStatement(query);
					int j = 1;
					BWFL_StockReceiveDataTable table = (BWFL_StockReceiveDataTable) action.getBrandPackagingDataList().get(i);
					pstmt.setInt(1, action.getDistillery_id());
					pstmt.setInt(2, Integer.parseInt(table.getBrandPackagingData_Brand()));
					pstmt.setInt(3, Integer.parseInt(table.getBrandPackagingData_Packaging()));
					pstmt.setDouble(4, Double.parseDouble(table.getBrandPackagingData_Quantity()));
					pstmt.setDouble(5, table.getBrandPackagingData_PlanNoOfBottling());
					pstmt.setDouble(6, table.getBrandPackagingData_NoOfBoxes());
					pstmt.setInt(7, Integer.parseInt(action.getLiqureTypeId()));
					pstmt.setString(8, action.getLicenceType());
					pstmt.setDate(9, Utility.convertUtilDateToSQLDate(action.getDateOfBottling()));
					pstmt.setString(10, action.getLicenceNoId());
					pstmt.setDate(11, Utility.convertUtilDateToSQLDate(new Date()));
					saveStatus = pstmt.executeUpdate();
				}
			}if (saveStatus > 0) {
				ResourceUtil.addErrorMessage(Constants.SAVED_SUCESSFULLY,Constants.SAVED_SUCESSFULLY);
				action.reset();
			} else {
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,Constants.NOT_SAVED);
			}
		}catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(" Same Date Same Brand and Same Packaging  Not Allow For Botteling Plan ","Same Date Same Brand and Same Packaging Not Allow For Botteling Plan"));						
		}finally {
			try {
				if (conn != null)conn.close();
				if (pstmt != null)pstmt.close();
				if (rs != null)rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// ---------------------- get add Row data -----------------------------

	public ArrayList getAddRowData(BWFL_StockReceiveAction ac, String lic_typ) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "	SELECT int_fl2d_id, int_brand_id, int_pack_id, int_quantity,  "
				+ "	int_planned_bottles, int_boxes, int_liquor_type, "
				+ "	vch_license_type, plan_dt, licence_no, cr_date "
				+ "		FROM fl2d.mst_stock_receive_19_20 where  int_fl2d_id=? and vch_license_type=?  "
				+ "	    and  plan_dt = ? and permit_no='"+ac.getPermitNo()+"' AND (finalized_flag is null or finalized_flag!='F')";

		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, ac.getDistillery_id());
			// ps.setString(2, ac.getLicenceType());
			ps.setString(2, lic_typ);

			ps.setDate(3,
					Utility.convertUtilDateToSQLDate(ac.getDateOfBottling()));
			rs = ps.executeQuery();
			while (rs.next()) {

				BWFL_StockReceiveDataTable dt = new BWFL_StockReceiveDataTable();
				dt.setUpdateflg(true);

				dt.setBrandPackagingData_Brand(rs.getString("int_brand_id"));
				dt.setBrandPackagingData_Packaging(rs.getString("int_pack_id"));
				dt.setBrandPackagingData_Quantity(rs.getString("int_quantity"));
				dt.setBrandPackagingData_PlanNoOfBottling(rs
						.getInt("int_planned_bottles"));
				dt.setBrandPackagingData_NoOfBoxes(rs.getInt("int_boxes"));

				ac.setLicenceNoId(rs.getString("licence_no").trim());
				ac.setCr_date(Utility.convertUtilDateToSQLDate(rs
						.getDate("cr_date")));

				ac.setCheckLicenceType(rs.getString("vch_license_type"));

				list.add(dt);
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			
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

	// ------------------------------------ SHOW DATA
	// TABLE-----------------------

	public ArrayList getData(BWFL_StockReceiveAction ac) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="";
		/* sql ="SELECT a.seq,c.code_generate_through,coalesce(b.tracking_flg,'N') as tracking_flg,a.finalized_date,a.finalized_flag,a.int_fl2d_id,a.licence_no,replace(a.licence_no,' ','')as licencenoo ,a.int_brand_id,b.brand_name, a.int_pack_id,c.package_name , a.int_quantity, "
				+ "	c.export_box_size,c.quantity,a.int_planned_bottles, a.int_boxes, a.int_liquor_type,d.description, a.vch_license_type, a.plan_dt ,a.permit_no  "
				+ "		FROM fl2d.mst_stock_receive_19_20 a ,distillery.brand_registration_19_20 b, "
				+ "	    distillery.packaging_details_19_20 c ,distillery.imfl_type d "
				+ "	    where a.int_brand_id=b.brand_id  "
				+ "	   and  b.brand_id=c.brand_id_fk "
				+ "	  and   a.int_pack_id=c.package_id "
				+ "	  and  a.int_liquor_type=d.id "
				+ "	 and a.int_fl2d_id =? ORDER BY a.plan_dt DESC  ";*/

		sql=" SELECT a.cancel_order_no,a.cancel_req_reason,a.cancel_req_flg,a.cancel_req_dt_time,a.seq,c.code_generate_through,coalesce(b.tracking_flg,'N') as tracking_flg,a.finalized_date,      				  "+
			" a.finalized_flag,a.int_fl2d_id,a.licence_no,replace(a.licence_no,' ','')as licencenoo ,a.int_brand_id,b.brand_name,     "+
			" a.int_pack_id,c.package_name , a.int_quantity, c.export_box_size,c.quantity,a.int_planned_bottles, a.int_boxes,         "+
			" a.int_liquor_type,d.description, a.vch_license_type, a.plan_dt ,a.permit_no                                             "+
/*@rvind*/	" ,e.vch_firm_name||' ('|| e.vch_mobile_no||')' as unit_name                                                            "+
			" FROM distillery.brand_registration_19_20 b,distillery.packaging_details_19_20 c , " +
			" distillery.imfl_type d,fl2d.mst_stock_receive_19_20 a    "+
/*@rvind*/	" left join licence.fl2_2b_2d_19_20 e on  e.vch_license_type='FL2D' and e.int_app_id=a.int_fl2d_id                                  "+
			" where a.int_brand_id=b.brand_id and b.brand_id=c.brand_id_fk and a.int_pack_id=c.package_id and a.int_liquor_type=d.id  "+
			" and loginusr='"+ ResourceUtil.getUserNameAllReq().trim() + "' ORDER BY a.plan_dt DESC  ";
		// user_name ='"+ResourceUtil.getUserNameAllReq()+"'";
//////System.out.println("data list="+sql);
		 
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
		//	ps.setString(1, ac.getUnit_id());
			rs = ps.executeQuery();
			while (rs.next()) {

				BWFL_StockReceiveDataTable dt = new BWFL_StockReceiveDataTable();
				
				
				if(rs.getString("cancel_req_reason")!=null)
				{
					dt.setCancel_reason(rs.getString("cancel_req_reason")+"   Cancel Request Date Time Is "+rs.getString("cancel_req_dt_time"));
				}else{
					dt.setCancel_reason("NA");
				}
				
				if(rs.getString("cancel_order_no")!=null)
				{
					dt.setCancel_order_flg(true);
				}else{
					dt.setCancel_order_flg(false);
				}
				
				
				
				dt.setCancel_flg(rs.getString("cancel_req_flg"));
               dt.setFinalize_Date(Utility.convertSqlDateToUtilDate(rs.getDate("finalized_date")));
                
               dt.setLicenceNo(rs.getString("licence_no"));
				dt.setLiqureTypeId(rs.getString("int_liquor_type"));
				dt.setRequest_id(rs.getInt("seq"));
				dt.setGtinno(rs.getString("code_generate_through"));
				dt.setShowDataTable_Date(rs.getDate("plan_dt"));
				dt.setShowDataTable_LiqureType(rs.getString("description"));
				dt.setShowDataTable_LicenceType(rs
						.getString("vch_license_type"));
				dt.setShowDataTable_Brand(rs.getString("brand_name"));
				dt.setShowDataTable_Packging(rs.getString("package_name"));
				dt.setNewml(rs.getInt("quantity"));
				dt.setNewsize(rs.getInt("export_box_size"));
				dt.setShowDataTable_Quntity(rs.getString("quantity"));
				dt.setShowDataTable_PlanNoOfBottling(rs
						.getString("int_planned_bottles"));
				dt.setShowDataTable_NoOfBoxes(rs.getString("int_boxes"));
				dt.setBrandId(rs.getInt("int_brand_id"));
				dt.setPackagingId(rs.getInt("int_pack_id"));
				//////System.out.println("dt.setLicenceNo="+dt.getLicenceNo());
				dt.setShowDataTable_PermitNo(rs.getString("permit_no"));
				dt.setLicencenoo(rs.getString("licencenoo").replaceAll("/", ""));
				dt.setFinalizedFlag(rs.getString("finalized_flag"));
				
				dt.setFinalize_Date(Utility.convertSqlDateToUtilDate(rs
						.getDate("finalized_date")));
				dt.setGtinno(rs.getString("code_generate_through"));

				if (rs.getDate("finalized_date") != null) {
					Date dat = Utility.convertSqlDateToUtilDate(rs
							.getDate("plan_dt"));
					////////System.out.println("date finalize" + dat);

					DateFormat formatter = new SimpleDateFormat("yyMMdd");
					String date = formatter.format(dat);
					dt.setFinalizedDateString(date);
					//////System.out.println(date);
				}
				/*if (!rs.getString("tracking_flg").equalsIgnoreCase("Y")) {
					dt.setFinalizedFlag("N");
				}*/
				dt.setUnit_id(rs.getInt("int_fl2d_id"));
				dt.setUnit_name(rs.getString("unit_name"));
				list.add(dt);
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			
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

	// ---------------------------------
	// bool--------------------------------------

	public boolean ckeck(BWFL_StockReceiveAction ac) {

		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList =

			"	SELECT int_distillery_id, int_brand_id, int_pack_id, int_quantity,  "
					+ "	int_planned_bottles, int_boxes, int_liquor_type, "
					+ "	vch_license_type, plan_dt, licence_no, cr_date "
					+ "		FROM distillery.mst_bottling_plan_19_20 where  int_distillery_id=? and vch_license_type=?  "
					+ "	    and  plan_dt = ? ";

			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, ac.getDistillery_id());
			// ps.setString(2, ac.getLicenceType());
			pstmt.setString(2, ac.getLicenceType());

			pstmt.setDate(3,
					Utility.convertUtilDateToSQLDate(ac.getDateOfBottling()));

			rs = pstmt.executeQuery();

			if (rs.next()) {

				flag = true;
			}

			else {
				flag = false;
			}

		} catch (SQLException se) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));
			
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return flag;

	}

	public synchronized long getSerialNo(long noOfSequenc) {
		String sql = " select     nextval('public.serial_seq')";
		String sqll = "ALTER SEQUENCE public.serial_seq RESTART WITH ? ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		long serialNo = 0;
		long currseq = 0;

		try {
			conn = ConnectionToDataBase.getConnection19_20();

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				serialNo = rs.getInt(1);
				if (serialNo == 0) {
					serialNo = serialNo + 1;
				}
				//////System.out.println("noOfSequenc " + noOfSequenc);

				pstmt1 = conn
						.prepareStatement("ALTER SEQUENCE public.serial_seq RESTART WITH "
								+ (noOfSequenc + serialNo));

				System.out
						.println("ALTER SEQUENCE public.serial_seq RESTART WITH "
								+ (noOfSequenc + serialNo));
				pstmt1.executeUpdate();

			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			
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

	public String getBrandPackagingGtinNo(BWFL_StockReceiveAction action,
			BWFL_StockReceiveDataTable dt) {

		String gtin = "";
		String sql = "select b.code_generate_through from distillery.brand_registration_19_20 a, distillery.packaging_details_19_20 b "
				+ "where  a.brand_id=b.brand_id_fk and a.brand_id=? and b.package_id=? and b.quantity=? and vch_license_type=? and a.distillery_id=?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		/*////System.out.println("brandId" + dt.getBrandId() + "  package_id"
				+ dt.getPackagingId() + " b.quantity "
				+ Integer.parseInt(dt.getShowDataTable_Quntity())
				+ " vch_license_type " + dt.getShowDataTable_LicenceType()
				+ " a.distillery_id " + action.getDistillery_id());*/

		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dt.getBrandId());
			pstmt.setInt(2, dt.getPackagingId());
			pstmt.setInt(3, Integer.parseInt(dt.getShowDataTable_Quntity()));
			pstmt.setString(4, dt.getShowDataTable_LicenceType());
			pstmt.setInt(5, action.getDistillery_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				gtin = rs.getString("code_generate_through");
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			
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

	public int getBrandPackagingNoOfBottle(BWFL_StockReceiveAction action,
			BWFL_StockReceiveDataTable dt) {

		long boxid = 0;
		int boxSize = 0;
		String sql = "select box_id from distillery.brand_registration_19_20 a, distillery.packaging_details_19_20 b "
				+ "where  a.brand_id=b.brand_id_fk and a.brand_id=? and b.package_id=? and b.quantity=? and vch_license_type=? and a.distillery_id=?";

		String sql1 = "SELECT  box_size  "
				+ " FROM distillery.box_size_details  where box_id=?";
		;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;

		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				boxid = Long.parseLong(rs.getString("box_id"));
			} else {

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Packaging Not Found",
								"Packaging Not Found"));

			}
			if (boxid > 0) {
				pstmt1 = con.prepareStatement(sql1);
				rs1 = pstmt1.executeQuery();
				if (rs.next()) {

					boxSize = rs.getInt(1);

				} else {

					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("Box Size Not Defined In Master",
									"Box Size Not Defined In Master"));

				}

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (rs1 != null)
					rs1.close();
				if (pstmt != null)
					pstmt.close();
				if (pstmt1 != null)
					pstmt1.close();
				if (con != null)
					con.close();

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			}

		}

		return boxSize;

	}

	public boolean write(BWFL_StockReceiveDataTable dt,
			BWFL_StockReceiveAction action, Connection conn) {

		//////System.out.println("excel innn");
		 String sql_fl3_update="update bottling_unmapped.fl2d set bottle_code=? where unit_id= ? and plan_id=? and date_plan=? and  etin=? and  casecode=?";
		

		String fl3 =
		
		"	select to_char(y.gs, 'fm00000000')as GENERATE_SERIES , y.dispatch_date, y.gtin_no,y.unit_id,"
		+ "	  y.serial_no_start, y.serial_no_end , "
		+ "	to_char(y.case_no::numeric , 'fm000000000')as case_no,y.plan_id from( "
		+ "	select x.unit_id, GENERATE_SERIES(x.serial_no_start::numeric ,x.serial_no_end::numeric ) as gs ,x.serial_no_start ,x.serial_no_end, "
		+ "	x.case_no,x.dispatch_date,x.gtin_no,x.plan_id "
		+ "	from ( "
		+ "	SELECT plan_id, unit_id,date_plan as dispatch_date, etin as gtin_no, serial_no_start, serial_no_end,  casecode as case_no "
		+ "	FROM bottling_unmapped.fl2d a "
		+ "	where  date_plan=?   and etin=? and plan_id=?)x)y";

		
		String relativePath = Constants.JBOSS_SERVER_PATH
				+ Constants.JBOSS_LINX_PATH;
		FileOutputStream fileOut = null;
String bottle_code="";
int count=1;
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

			

			if (dt.getShowDataTable_LicenceType().equals("FL2D")) {

				pstmt = conn.prepareStatement(fl3);

				
				
				pstmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));
				
				pstmt.setString(2, dt.getGtinno());
				pstmt.setInt(3, dt.getRequest_id());
				rs = pstmt.executeQuery();
				//////System.out.println("excecute query fl3");

			}
			
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet worksheet = workbook.createSheet("Barcode Report");
		
			CellStyle unlockedCellStyle = workbook.createCellStyle();
			unlockedCellStyle.setLocked(false);

			// Sheet sheet = workbook.createSheet();
			worksheet.protectSheet("agristat");
			worksheet.setColumnWidth(0, 3000);
			worksheet.setColumnWidth(1, 8000);
			worksheet.setColumnWidth(2, 8000);
			worksheet.setColumnWidth(3, 8000);
			worksheet.setColumnWidth(4, 6000);
			

			XSSFRow rowhead0 = worksheet.createRow((int) 0);
			
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

				// ////System.out.println("i==========="+i++);
				Date dat = Utility.convertSqlDateToUtilDate(rs
						.getDate("dispatch_date"));

				DateFormat formatter;

				formatter = new SimpleDateFormat("yyMMdd");
				date = formatter.format(dat);

				String lic = dt.getLicencenoo().replaceAll("/", "");

				// ////System.out.println("while in");serial_no_start, serial_no_end
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
				noOfUnit=StringUtils.leftPad(String.valueOf(Integer.parseInt(dt.getShowDataTable_PlanNoOfBottling()) / Integer.parseInt(dt.getShowDataTable_NoOfBoxes())), 3, '0');
				
				if(noOfUnit.length()==2){
					/*cellD1.setCellValue("01" + rs.getString("gtin_no") + "13"
							+ date + "37" + noOfUnit + "21"
							+ rs.getString("case_no"));*/
					
					cellD1.setCellValue( rs.getString("gtin_no")+""+1+""+ StringUtils.leftPad(String.valueOf(rs.getString("unit_id")), 3, '0')
							+""+ date +""+ "1" +""+ StringUtils.leftPad(String.valueOf(noOfUnit), 3, '0')+""+ rs.getString("case_no")); 
					
					}
					else if(noOfUnit.length()==1)
					{
						cellD1.setCellValue( ""+rs.getString("gtin_no")+""+1+""+ StringUtils.leftPad(String.valueOf(rs.getString("unit_id")), 3, '0')
								+""+ date +""+ "1" +""+ StringUtils.leftPad(String.valueOf(noOfUnit), 3, '0')+""+ rs.getString("case_no"));
					}else{
						
						cellD1.setCellValue( rs.getString("gtin_no")+""+1+""+ StringUtils.leftPad(String.valueOf(rs.getString("unit_id")), 3, '0')
								+""+ date +""+ "1" +""+ StringUtils.leftPad(String.valueOf(noOfUnit.substring(0,3)), 3, '0')+""+ rs.getString("case_no"));
						
					}
				 

				XSSFCell cellE1 = row1.createCell((int) 4);
				
				Random rand = new Random();
				int n = 10+rand.nextInt(90) ;
			
				 cellE1.setCellValue(rs.getString("gtin_no")
							+ ""+
							 date + StringUtils.leftPad(
										String.valueOf(rs.getString("unit_id")), 3, '0')
										+ "" + "" + n + ""
							+ rs.getString("GENERATE_SERIES") + ""
							+ getCheckDigit(rs.getString("GENERATE_SERIES")));
				 
				 
				 
				 
				 
				 
				 bottle_code=bottle_code+"|"+n + ""+ rs.getString("GENERATE_SERIES") + ""+ getCheckDigit(rs.getString("GENERATE_SERIES"));	
					
					

						pstmt1 = conn.prepareStatement(sql_fl3_update);

						pstmt1.setString(1, bottle_code);
						pstmt1.setString(2, String.valueOf(Integer.parseInt(rs.getString("unit_id"))));
						pstmt1.setInt(3,rs.getInt("plan_id") );
						pstmt1.setDate(4,rs.getDate("dispatch_date") );
						pstmt1.setString(5,rs.getString("gtin_no") );
						pstmt1.setString(6,rs.getString("case_no") );
						
						 pstmt1.executeUpdate();

					
				 
				 
				 
					if(count==(Integer.parseInt(dt.getShowDataTable_PlanNoOfBottling()) / Integer.parseInt(dt.getShowDataTable_NoOfBoxes())))
						
					{
				
				System.out.println("come inn nn time ");
				count=0;
				bottle_code="";
					}
			
			
			count++;
				 
				 
				 
				 
				 
				 
				

			}
			fileOut = new FileOutputStream(relativePath + "//ExciseUp//Excel//"+dt.getRequest_id()+""
					+ dt.getGtinno() + "" + date + ".xls");

			XSSFRow row1 = worksheet.createRow((int) k + 1);

			// APoolFinancialReportDataTable dt=(APoolFinancialReportDataTable)
			// list.get(i-2);

			XSSFCell cellA1 = row1.createCell((int) 0);
			cellA1.setCellValue("End");

			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			flag = true;

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			
			//////System.out.println("xls2" + e.getMessage());
			e.printStackTrace();
		}

		return flag;
	}

	public static int getCheckDigit(String n)
	{
		//int i=0;
		int sum=0;
		int even=0;
		int odd=0;
		int checkDigit=0;
		char ar[]= n.toCharArray();
		int k=ar.length;
		for (int i = ar.length-1; i >=0; i--)
		{
			
			
			
		   if(i%2!=0)
		   {
			  // ////System.out.println("evennnn"+ar[i]);
			   odd =odd+ Character.getNumericValue(ar[i]);
		   }else{
			 //  ////System.out.println("oddddddd"+ar[i]);
			   even=even+Character.getNumericValue(ar[i]);
		   }
		  
		}
		
		sum=(odd*3)+even;
		//////System.out.println("summm"+sum);
		//////System.out.println("even"+even);
		//////System.out.println("odd sum"+odd);
		if(sum%10!=0)
		{
			
			//////System.out.println("SUMM    "+sum);
			    checkDigit= (10 - sum % 10);
			 //   ////System.out.println("checkDigit  "+checkDigit);
		}
		
		
	return checkDigit;
    }
	

	public synchronized long getcaseNo() {
		String sql = " select     nextval('public.case_seq')";

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		long serialNo = 0;
		long currseq = 0;

		try {
			conn = ConnectionToDataBase.getConnection19_20();

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				serialNo = rs.getInt(1);
				if (serialNo == 0) {
					serialNo = serialNo;
				}

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			
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


	public void dataFinalize(BWFL_StockReceiveAction action,
			BWFL_StockReceiveDataTable dt) {
		Connection conn = null;
		Connection conn1 = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		String gtinNo = "";
		long boxsize = 0;
		long caseno = 0;
		int status=0;
		
	

		String sql=
				
				
				
				        "INSERT INTO bottling_unmapped.fl2d( "+
						"date_plan,etin,serial_no_start, serial_no_end,  casecode,plan_id,unit_id) "+
						"VALUES (?, ?, ?, ?, ?, ?,?)";

		String update = "UPDATE fl2d.mst_stock_receive_19_20 "
				+ " SET   finalized_flag='F' ,finalized_date=? "
				+ "WHERE seq=? and permit_no=? and int_brand_id=? and int_pack_id=?";

		try {
		
			gtinNo=dt.getGtinno();
			

			System.out
					.println("new Double(dt.getShowDataTable_PlanNoOfBottling()).longValue()"
							+ new Double(dt.getShowDataTable_PlanNoOfBottling())
									.longValue());
			
			
			// long	serialno = dt.getSeqfrm();
			
			long	serialno=getSerialNoFl2D(new Double(dt.getBrandPackagingData_PlanNoOfBottling()).longValue(),new Date());
			
			
			
			//////System.out.println("gtinNo" + gtinNo + "seqqqqqqqqqqqqqqqqqqqqqqqq"+ serialno);
			if (!gtinNo.equals("") && serialno != 0) {
				conn = ConnectionToDataBase.getConnection19_20();
				conn1 = ConnectionToDataBase.getConnection();
				conn.setAutoCommit(false);
				conn1.setAutoCommit(false);
				pstmt1 = conn1.prepareStatement(update);
				pstmt1.setDate(1, new java.sql.Date(System.currentTimeMillis()));
				pstmt1.setInt(2, dt.getRequest_id());
				pstmt1.setString(3, dt.getShowDataTable_PermitNo());
				pstmt1.setInt(4, dt.getBrandId());
				pstmt1.setInt(5, dt.getPackagingId());
				
				status = pstmt1.executeUpdate();

				if (dt.getShowDataTable_LicenceType().equals("FL2D")
						&& status > 0) {
					status = 0;
					
					 
					//////System.out.println("dt.getShowDataTable_Quntity()"+dt.getShowDataTable_Quntity()+"  dt.getShowDataTable_NoOfBoxes()"+dt.getShowDataTable_NoOfBoxes());
					pstmt1 = conn.prepareStatement(sql);
					//pstmt2 = conn.prepareStatement(unmapped_sql);
					for (int i = 0; i < Long.parseLong(dt.getShowDataTable_NoOfBoxes()); i++) {
						//caseno = dt.getCaseseq()+i;
                      
						caseno =getcaseNoFl2d(new Date());

						pstmt1.setDate(1, new java.sql.Date(System.currentTimeMillis()));
						pstmt1.setString(2, gtinNo);
						pstmt1.setString(3, StringUtils.leftPad(String.valueOf(serialno), 8, '0'));
						 pstmt1.setString(4,StringUtils.leftPad(String.valueOf(serialno+(dt.getNewsize()-1)), 8, '0') );
						 Random rand1 = new Random();
						 int n1 = 10+rand1.nextInt(90) ;
                         pstmt1.setString(5, n1+""+StringUtils.leftPad(String.valueOf(caseno), 6, '0')+getCheckDigit(n1+""+StringUtils.leftPad(String.valueOf(caseno), 6, '0')));
						pstmt1.setInt(6, dt.getRequest_id());
						pstmt1.setInt(7, action.getEtin_unit_id());
					   
						serialno+=dt.getNewsize();
						pstmt1.addBatch();
						
					}
				}
				
				int[] status1=pstmt1.executeBatch();
				
				if (status1.length > 0) {
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
		}

		catch (Exception e) {

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

	
	
	
	public void generateReport(BWFL_StockReceiveDataTable dt,
			BWFL_StockReceiveAction action) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String relativePath = Constants.JBOSS_SERVER_PATH
				+ Constants.JBOSS_LINX_PATH + "//ExciseUp//";
		JasperPrint print = null;

		String FL3 = ""
				+ " select to_char(GENERATE_SERIES, 'fm000000000000') as GENERATE_SERIES,distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type,  "
				+ " to_char(case_no , 'fm0000000000')as case_no from "
				+

				"(SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no,b.*   "
				+ "FROM public.bottling_fl3 a, (select * from GENERATE_SERIES(?,?)) b   "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?  )x";

		String FL3A = " select to_char(GENERATE_SERIES, 'fm000000000000') as GENERATE_SERIES,distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type,  "
				+ " to_char(case_no , 'fm0000000000')as case_no from "
				+

				"(SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no,b.*   "
				+ "FROM public.bottling_fl3a a, (select * from GENERATE_SERIES(?,?)) b   "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?)x";

		String cl = " select to_char(GENERATE_SERIES, 'fm000000000000') as GENERATE_SERIES,distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type,  "
				+ " to_char(case_no , 'fm0000000000')as case_no from "
				+

				"(SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no,b.*   "
				+ "FROM public.bottling_cl a, (select * from GENERATE_SERIES(?,?)) b   "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?)x";

		try {
			String minmax = getMinMaxSerialNo(dt, action);

			String ar[] = minmax.split(",");
			conn = ConnectionToDataBase.getConnection19_20();
			String noOfUnit = "";

			if (dt.getShowDataTable_LicenceType().equals("FL3")) {
				pstmt = conn.prepareStatement(FL3);
				pstmt.setLong(1, Long.parseLong(ar[0]));
				pstmt.setLong(2, Long.parseLong(ar[1]));
				pstmt.setInt(3, action.getDistillery_id());
				pstmt.setDate(4,Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()));
				pstmt.setString(5, dt.getLicenceNo());
				pstmt.setString(6, dt.getShowDataTable_LicenceType());
				pstmt.setLong(7, Long.parseLong(dt.getGtinno()));

				rs = pstmt.executeQuery();
				if (rs.next()) {

					HashMap map = new HashMap();
					map.put("disname", action.getDistillery_nm());
					map.put("dislryadd", action.getDistillery_adrs());
					map.put("quantity", dt.getShowDataTable_Quntity());
					map.put("brandName", dt.getShowDataTable_Brand());
					Date dat = Utility.convertSqlDateToUtilDate(rs
							.getDate("dispatch_date"));
					//////System.out.println("date finalize" + dat);
					DateFormat formatter;

					formatter = new SimpleDateFormat("yyMMdd");
					String date = formatter.format(dat);
					//////System.out.println(date);
					map.put("date", date);

					if (dt.getShowDataTable_Quntity().equals("750")) {
						noOfUnit = "12";
					} else if (dt.getShowDataTable_Quntity().equals("375")) {
						noOfUnit = "24";
					} else if (dt.getShowDataTable_Quntity().equals("180")) {
						noOfUnit = "48";
					} else if (dt.getShowDataTable_Quntity().equals("60")) {
						noOfUnit = "12";
					}
					map.put("barcode",
							"01" + rs.getLong("gtin_no") + "13" + date + "37"
									+ noOfUnit + "21" + rs.getString("case_no"));
					map.put("barcodetext",
							"(01)" + rs.getLong("gtin_no") + "(13)" + date
									+ "(37)" + noOfUnit + "(21)"
									+ rs.getString("case_no"));
					rs = pstmt.executeQuery();

					JRResultSetDataSource jrds = new JRResultSetDataSource(rs);
					print = JasperFillManager.fillReport(relativePath
							+ "bottlingjasper" + File.separator
							+ "BarcodeFl3.jasper", map, jrds);
					JasperExportManager.exportReportToPdfFile(
							print,
							relativePath + "bottling_pdf" + File.separator
									+ "Barcode" + dt.getGtinno()
									+ dt.getFinalizedDateString() + ".pdf");

					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("Print Report Successfully",
									"Print Report Successfully"));

				} else {

					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("No Record Found",
									"No Record Found"));
				}

			}
			if (dt.getShowDataTable_LicenceType().equals("CL")) {
				pstmt = conn.prepareStatement(cl);
				pstmt.setLong(1, Long.parseLong(ar[0]));
				pstmt.setLong(2, Long.parseLong(ar[1]));
				pstmt.setInt(3, action.getDistillery_id());
				pstmt.setDate(4,
						Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()));
				pstmt.setString(5, dt.getLicenceNo());
				pstmt.setString(6, dt.getShowDataTable_LicenceType());
				pstmt.setLong(7, Long.parseLong(dt.getGtinno()));

				rs = pstmt.executeQuery();
				if (rs.next()) {

					HashMap map = new HashMap();
					map.put("disname", action.getDistillery_nm());
					map.put("dislryadd", action.getDistillery_adrs());
					map.put("quantity", dt.getShowDataTable_Quntity());
					map.put("brandName", dt.getShowDataTable_Brand());
					Date dat = Utility.convertSqlDateToUtilDate(rs
							.getDate("dispatch_date"));
					//////System.out.println("date finalize" + dat);
					DateFormat formatter;

					formatter = new SimpleDateFormat("yyMMdd");
					String date = formatter.format(dat);
					//////System.out.println(date);
					map.put("date", date);
					if (dt.getShowDataTable_Quntity().equals("750")) {
						noOfUnit = "12";
					} else if (dt.getShowDataTable_Quntity().equals("375")) {
						noOfUnit = "24";
					} else if (dt.getShowDataTable_Quntity().equals("180")) {
						noOfUnit = "48";
					} else if (dt.getShowDataTable_Quntity().equals("60")) {
						noOfUnit = "92";
					} else if (dt.getShowDataTable_Quntity().equals("200")) {
						noOfUnit = "45";
					}
					map.put("barcode",
							"01" + rs.getLong("gtin_no") + "13" + date + "37"
									+ noOfUnit + "21" + rs.getString("case_no"));
					map.put("barcodetext",
							"(01)" + rs.getLong("gtin_no") + "(13)" + date
									+ "(37)" + noOfUnit + "(21)"
									+ rs.getString("case_no"));
					rs = pstmt.executeQuery();

					JRResultSetDataSource jrds = new JRResultSetDataSource(rs);
					print = JasperFillManager.fillReport(relativePath
							+ "bottlingjasper" + File.separator
							+ "BarcodeCL.jasper", map, jrds);
					JasperExportManager.exportReportToPdfFile(
							print,
							relativePath + "bottling_pdf" + File.separator
									+ "Barcode" + dt.getGtinno()
									+ dt.getFinalizedDateString() + ".pdf");

					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("Print Report Successfully",
									"Print Report Successfully"));

				} else {

					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("No Record Found",
									"No Record Found"));
				}
			}
			if (dt.getShowDataTable_LicenceType().equals("FL3A")) {

				//////System.out.println("Fl3 a Fl3Acome in");
				pstmt = conn.prepareStatement(FL3A);
				pstmt.setLong(1, Long.parseLong(ar[0]));
				pstmt.setLong(2, Long.parseLong(ar[1]));
				pstmt.setInt(3, action.getDistillery_id());
				pstmt.setDate(4,
						Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()));
				pstmt.setString(5, dt.getLicenceNo());
				pstmt.setString(6, dt.getShowDataTable_LicenceType());
				pstmt.setLong(7, Long.parseLong(dt.getGtinno()));

				rs = pstmt.executeQuery();
				if (rs.next()) {

					//////System.out.println("Fl3 a Fl3Acome in nextt");
					HashMap map = new HashMap();
					map.put("disname", action.getDistillery_nm());
					map.put("dislryadd", action.getDistillery_adrs());
					map.put("quantity", dt.getShowDataTable_Quntity());
					map.put("brandName", dt.getShowDataTable_Brand());
					Date dat = Utility.convertSqlDateToUtilDate(rs
							.getDate("dispatch_date"));
					//////System.out.println("date finalize" + dat);
					DateFormat formatter;

					formatter = new SimpleDateFormat("yyMMdd");
					String date = formatter.format(dat);
					//////System.out.println(date);
					map.put("date", date);
					if (dt.getShowDataTable_Quntity().equals("750")) {
						noOfUnit = "12";
					} else if (dt.getShowDataTable_Quntity().equals("375")) {
						noOfUnit = "24";
					} else if (dt.getShowDataTable_Quntity().equals("180")) {
						noOfUnit = "48";
					} else if (dt.getShowDataTable_Quntity().equals("60")) {
						noOfUnit = "92";
					} else if (dt.getShowDataTable_Quntity().equals("200")) {
						noOfUnit = "45";
					}
					map.put("barcode",
							"01" + rs.getLong("gtin_no") + "13" + date + "37"
									+ noOfUnit + "21" + rs.getString("case_no"));
					map.put("barcodetext",
							"(01)" + rs.getLong("gtin_no") + "(13)" + date
									+ "(37)" + noOfUnit + "(21)"
									+ rs.getString("case_no"));
					rs = pstmt.executeQuery();

					JRResultSetDataSource jrds = new JRResultSetDataSource(rs);
					print = JasperFillManager.fillReport(relativePath
							+ "bottlingjasper" + File.separator
							+ "BarcodeFl3A.jasper", map, jrds);
					JasperExportManager.exportReportToPdfFile(
							print,
							relativePath + "bottling_pdf" + File.separator
									+ "Barcode" + dt.getGtinno()
									+ dt.getFinalizedDateString() + ".pdf");

					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("Print Report Successfully",
									"Print Report Successfully"));

				} else {

					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("No Record Found",
									"No Record Found"));
				}

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			
			e.printStackTrace();
		}

	}

	public String getMinMaxSerialNo(BWFL_StockReceiveDataTable dt,
			BWFL_StockReceiveAction action) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String fl3 = "SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no   "
				+ "FROM public.bottling_fl3 a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  ";

		String fl3a = "SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no   "
				+ "FROM public.bottling_fl3a a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  ";

		String cl = "SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no   "
				+ "FROM public.bottling_cl a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  ";

		String minMax = "";

		try {
			conn = ConnectionToDataBase.getConnection19_20();

			if (dt.getShowDataTable_LicenceType().equals("FL3")) {

				
				pstmt = conn.prepareStatement(fl3);
				pstmt.setInt(1, action.getDistillery_id());
				pstmt.setDate(2,
						Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = rs.getString("serial_no_start") + ","
							+ rs.getString("serial_no_end");
					//////System.out.println("minMax" + minMax);
				}
			}

			if (dt.getShowDataTable_LicenceType().equals("FL3A")) {

				
				pstmt = conn.prepareStatement(fl3a);
				pstmt.setInt(1, action.getDistillery_id());
				pstmt.setDate(2,
						Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = rs.getString("serial_no_start") + ","
							+ rs.getString("serial_no_end");
					//////System.out.println("minMax" + minMax);
				}
			}
			if (dt.getShowDataTable_LicenceType().equals("CL")) {
				
				pstmt = conn.prepareStatement(cl);
				pstmt.setInt(1, action.getDistillery_id());
				pstmt.setDate(2,
						Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = rs.getString("serial_no_start") + ","
							+ rs.getString("serial_no_end");
					//////System.out.println("minMax" + minMax);
				}
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			
			e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		return minMax;
	}

	public String getMinMaxSerialNoForExcel(BWFL_StockReceiveDataTable dt,
			BWFL_StockReceiveAction action, Connection conn) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String fl3 = "SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no   "
				+ "FROM public.bottling_fl3 a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?";
		String fl3mx = "SELECT   serial_no_end, licence_no   "
				+ "FROM public.bottling_fl3 a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?";

		String fl3a = "SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no   "
				+ "FROM public.bottling_fl3a a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=? and gtin_no=? ";
		String fl3amx = "SELECT   serial_no_end    "
				+ "FROM public.bottling_fl3a a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=? and gtin_no=? ";

		String cl = "SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no   "
				+ "FROM public.bottling_cl a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?";

		String clmx = "SELECT   serial_no_end   "
				+ "FROM public.bottling_cl a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?";

		String minMax = "";

		try {

			if (dt.getShowDataTable_LicenceType().equals("FL3")) {

				/*////System.out.println("fl3 min max"
						+ action.getDistillery_id()
						+ " date"
						+ Utility.convertUtilDateToSQLDate(dt
								.getFinalize_Date()) + " licenceno"
						+ dt.getLicenceNo() + " licence type"
						+ dt.getShowDataTable_LicenceType() + dt.getGtinno());*/
				pstmt = conn.prepareStatement(fl3);
				pstmt.setInt(1, action.getDistillery_id());
				pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				pstmt.setLong(5, Long.parseLong(dt.getGtinno()));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = rs.getString("serial_no_start");
					//////System.out.println("minMax" + minMax);
				}
				pstmt = conn.prepareStatement(fl3mx);
				pstmt.setInt(1, action.getDistillery_id());
				pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				pstmt.setLong(5, Long.parseLong(dt.getGtinno()));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = minMax + "," + rs.getString("serial_no_end");
					//////System.out.println("minMax" + minMax);
				}
			}

			if (dt.getShowDataTable_LicenceType().equals("FL3A")) {

				/*////System.out.println("fl3a min max"
						+ action.getDistillery_id()
						+ " date"
						+ Utility.convertUtilDateToSQLDate(dt
								.getFinalize_Date()) + " licenceno"
						+ dt.getLicenceNo() + " licence type"
						+ dt.getShowDataTable_LicenceType() + ""
						+ dt.getGtinno());*/
				pstmt = conn.prepareStatement(fl3a);
				pstmt.setInt(1, action.getDistillery_id());
				pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				pstmt.setLong(5, Long.parseLong(dt.getGtinno()));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = rs.getString("serial_no_start");
					//////System.out.println("minMax" + minMax);
				}
				pstmt = conn.prepareStatement(fl3amx);
				pstmt.setInt(1, action.getDistillery_id());
				pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				pstmt.setLong(5, Long.parseLong(dt.getGtinno()));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = minMax + "," + rs.getString("serial_no_end");
					//////System.out.println("minMax" + minMax);
				}
			}
			if (dt.getShowDataTable_LicenceType().equals("CL")) {
				/*////System.out.println("cl min max"
						+ action.getDistillery_id()
						+ " date"
						+ Utility.convertUtilDateToSQLDate(dt
								.getFinalize_Date()) + " licenceno"
						+ dt.getLicenceNo() + " licence type"
						+ dt.getShowDataTable_LicenceType() + ""
						+ dt.getGtinno());*/
				pstmt = conn.prepareStatement(cl);
				pstmt.setInt(1, action.getDistillery_id());
				pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				pstmt.setLong(5, Long.parseLong(dt.getGtinno()));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = rs.getString("serial_no_start");
					//////System.out.println("minMax" + minMax);
				}
				pstmt = conn.prepareStatement(clmx);
				pstmt.setInt(1, action.getDistillery_id());
				pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				pstmt.setLong(5, Long.parseLong(dt.getGtinno()));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = minMax + "," + rs.getString("serial_no_end");
					//////System.out.println("minMax" + minMax);
				}
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			e.printStackTrace();
		}
		return minMax;
	}

	public boolean checkData(BWFL_StockReceiveAction action) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int j = 0;
		String sql =

		"  SELECT int_distillery_id, int_brand_id, int_pack_id, int_quantity, int_planned_bottles, "
				+ "  int_boxes, int_liquor_type, vch_license_type, plan_dt, "
				+ "  licence_no, cr_date, finalized_flag, finalized_date "
				+ "  FROM distillery.mst_bottling_plan_19_20 "
				+ "   where int_distillery_id=? and int_brand_id=? and int_pack_id=? and plan_dt=? ";
		try {
			conn = ConnectionToDataBase.getConnection();

			if (action.getBrandPackagingDataList().size() > 0) {

				pstmt = conn.prepareStatement(sql);
				for (int i = 0; i < action.getBrandPackagingDataList().size(); i++) {
					BWFL_StockReceiveDataTable table = (BWFL_StockReceiveDataTable) action
							.getBrandPackagingDataList().get(i);
					// pstmt.setInt(1,getMaxChallanIdDetail()+1);
					pstmt.setInt(1, action.getDistillery_id());
					pstmt.setInt(2, Integer.parseInt(table
							.getBrandPackagingData_Brand()));
					pstmt.setInt(3, Integer.parseInt(table
							.getBrandPackagingData_Packaging()));
					pstmt.setDate(4, Utility.convertUtilDateToSQLDate(action
							.getDateOfBottling()));
					rs = pstmt.executeQuery();
					if (rs.next()) {
						flag = false;

					} else {
						flag = true;
					}

				}

			} else {

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Please Add One Row",
								"Please Add One Row"));
			}
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
		} finally {

			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return flag;
	}

	
	
	
	
	public synchronized long getSerialNoFl2D(long noOfSequenc,Date date) {
		//////System.out.println("123");
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
			SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyy");
			String currentdate=	 sdf.format(today);
			pstmt2=conn.prepareStatement("CREATE SEQUENCE IF NOT EXISTS public.fl_2d_serial_seq_"+currentdate);
			//////System.out.println("currentdate="+currentdate);
			pstmt2.executeUpdate();
			pstmt = conn.prepareStatement(" select     nextval('public.fl_2d_serial_seq_"+currentdate+"')");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				serialNo = rs.getInt(1);
				if (serialNo == 0) {
					serialNo = serialNo + 1;
				}
				//////System.out.println("noOfSequenc " + noOfSequenc);

				pstmt1 = conn.prepareStatement("ALTER SEQUENCE public.fl_2d_serial_seq_"+currentdate +" RESTART WITH "
								+ (noOfSequenc + serialNo+1));

				pstmt1.executeUpdate();

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));				

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
	
	

	public synchronized long getcaseNoFl2d(Date date) {
		

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
			SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyy");
			String currentdate=	 sdf.format(today);
			pstmt2=conn.prepareStatement("CREATE SEQUENCE IF NOT EXISTS public.fl_2d_serial_seq_"+currentdate);
			pstmt2.executeUpdate();
			pstmt = conn.prepareStatement(" select     nextval('public.fl_2d_serial_seq_"+currentdate+"')");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				serialNo = rs.getInt(1);
				if (serialNo == 0) {
					serialNo = serialNo;
				}
				
				/*pstmt=conn.prepareStatement("ALTER SEQUENCE public.fl_2d_serial_seq_"+currentdate+" RESTART WITH "+(no+serialNo));
				//////System.out.println("no="+no);
				//////System.out.println("serialNo="+serialNo);
				pstmt.executeUpdate();*/

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));				
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
	
	
	public ArrayList getUnit() {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);

	 

		String SQl = " SELECT int_app_id, vch_applicant_name, vch_firm_name ,"
				+ " vch_mobile_no,vch_license_type, vch_licence_no "
				+ " FROM licence.fl2_2b_2d_19_20 ,public.district b  WHERE vch_license_type= 'FL2D' and " +
				" b.districtid=core_district_id and   b.deo='"+ ResourceUtil.getUserNameAllReq().trim()+ "'  order by vch_applicant_name ";
		//////System.out.println("SQl="+SQl);
		try {

			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);

		

			rs = ps.executeQuery();
			while (rs.next()) {

				item = new SelectItem();
				item.setLabel(rs.getString("vch_firm_name")+" ( "+rs.getString("vch_mobile_no")+" )");
				item.setValue(rs.getString("int_app_id"));

				list.add(item);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
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
	
	
	public void update(BWFL_StockReceiveAction ac) {
		
	PreparedStatement stmt=null;
	Connection con=null;
	int saveStatus = 0;
	double totalPermitFees=0.0;
	int totalBottles=0;
	// int upbond_location_id=upbondGodownLocationId()+1;
	String query=	" UPDATE fl2d.mst_stock_receive_19_20 SET "+
					" int_liquor_type=?,licence_no=?, int_quantity=?,int_boxes=?,int_planned_bottles=?, permit_fee=? where "+
					" int_fl2d_id=? and int_brand_id=? and int_pack_id=? and plan_dt=? and permit_no=? AND (finalized_flag!='F' or finalized_flag IS NULL) ";
	
	try { 
			con=ConnectionToDataBase.getConnection(); 
			con.setAutoCommit(false);
			stmt=con.prepareStatement(query);
			//if (action.getBrandPackagingDataList().size() > 0) {
			for (int i = 0; i < ac.getBrandPackagingDataList().size(); i++) {
			BWFL_StockReceiveDataTable table = (BWFL_StockReceiveDataTable) ac.getBrandPackagingDataList().get(i);
					
			stmt.setInt(1, Integer.parseInt(ac.getLiqureTypeId()));
			stmt.setString(2, ac.getLicenceNoId());
			stmt.setInt(3, Integer.parseInt(table.getBrandPackagingData_Quantity()));
			stmt.setInt(4, table.getBrandPackagingData_NoOfBoxes());
			stmt.setInt(5, table.getBrandPackagingData_PlanNoOfBottling());
			stmt.setDouble(6, table.getCalPermitFee_dt());
			
			stmt.setInt(7, Integer.parseInt(ac.getUnit_id()));
			stmt.setInt(8, Integer.parseInt(table.getBrandPackagingData_Brand()));
			stmt.setInt(9, Integer.parseInt(table.getBrandPackagingData_Packaging()));
			stmt.setDate(10, Utility.convertUtilDateToSQLDate(ac.getDateOfBottling()));
			stmt.setString(11, ac.getPermitNo());
			saveStatus=stmt.executeUpdate();
			totalPermitFees+= table.getCalPermitFee_dt();
			totalBottles+=table.getBrandPackagingData_PlanNoOfBottling();
			//////System.out.println("1="+ac.getPermitNo()+"2="+table.getBrandPackagingData_Packaging()+"3="+table.getBrandPackagingData_Packaging()+"4="+table.getBrandPackagingData_Brand()+"5="+ac.getUnit_id()+"6="+table.getBrandPackagingData_PlanNoOfBottling()+"7="+table.getCalPermitFee_dt());
			/*double value=0;
			double a=ac.getInt_value();
			////System.out.println("ac.getInt_value()="+ac.getInt_value());
			////System.out.println("ac.getInt_quantity()="+ac.getInt_quantity());
			//totalPermitFees+=ac.getInt_value()-table.getCalPermitFee_dt();
			if(table.getCalPermitFee_dt()>ac.getInt_value()){
				value=table.getCalPermitFee_dt()-ac.getInt_value();
				ac.setInt_value(a+value);
			}else if(table.getCalPermitFee_dt()<ac.getInt_value()){
				value=ac.getInt_value()-table.getCalPermitFee_dt();
				ac.setInt_value(a-value);
			}
			
			int bottle=0;
			int b=ac.getInt_quantity();
			//totalBottles+= ac.getInt_quantity()-table.getBrandPackagingData_PlanNoOfBottling();
			if(table.getBrandPackagingData_PlanNoOfBottling()>ac.getInt_quantity()){
				bottle=table.getBrandPackagingData_PlanNoOfBottling()-ac.getInt_quantity();
				ac.setInt_quantity(b+bottle);
			}else if(table.getBrandPackagingData_PlanNoOfBottling()<ac.getInt_quantity()){
				bottle=ac.getInt_quantity()-table.getBrandPackagingData_PlanNoOfBottling();
				ac.setInt_quantity(b-bottle);
			}*/
			
			
			}
			if(saveStatus>0){
				
				saveStatus=0;
				String sql="update distillery.duty_register_19_20 set int_quantity='"+totalBottles+"',int_value='"+totalPermitFees+"' where int_id='"+ac.getDutyRegisterId()+"'";
				//////System.out.println("updateInDutyReg="+sql);
				stmt = con.prepareStatement(sql);
				saveStatus = stmt.executeUpdate();
			} 
			if(saveStatus>0){
				con.commit();
				ac.setUpdateFlg(false);
				ac.reset();
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO," Update successfully "," Update successfully "));
			}else{
				con.rollback();
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR," Details Not Updated  "," Details Not Updated  "));
			}
	}catch(Exception e){
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
		
			e.printStackTrace();
		}
		finally{
			try{
				if(con!=null)
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
 }
	public ArrayList getData1(BWFL_StockReceiveAction ac) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="";
		
		
		sql = 	" SELECT a.cancel_order_no, a.cancel_req_reason, a.cancel_req_flg, COALESCE(a.cancel_req_dt_time,'0')cancel_req_dt_time, "+
				" a.seqfrom, a.caseseq, a.seq, c.code_generate_through, coalesce(b.tracking_flg,'N') as tracking_flg,                  "+
				" a.finalized_date, a.finalized_flag, a.int_fl2d_id, a.licence_no, replace(a.licence_no,' ','')as licencenoo,          "+
				" a.int_brand_id,b.brand_name, a.int_pack_id,c.package_name , a.int_quantity, c.export_box_size,                       "+
				" c.quantity, a.int_planned_bottles, a.int_boxes, a.int_liquor_type, b.liquor_type as description,                     "+
				" a.vch_license_type, a.plan_dt ,a.permit_no, e.vch_firm_name||' ('|| e.vch_mobile_no||')' as unit_name                "+                                            
				" FROM distillery.brand_registration_19_20 b,distillery.packaging_details_19_20 c ,fl2d.mst_stock_receive_19_20 a      "+
				" left join licence.fl2_2b_2d_19_20 e on  e.vch_license_type='FL2D' and e.int_app_id=a.int_fl2d_id                     "+             
				" where a.int_brand_id=b.brand_id AND a.esign_flg='D' and b.brand_id=c.brand_id_fk and a.int_pack_id=c.package_id                          "+
				" and a.int_fl2d_id =?                                                                           "+
				" ORDER BY a.plan_dt DESC ";
		

		/*sql=" SELECT a.cancel_order_no,a.cancel_req_reason,a.cancel_req_flg,COALESCE(a.cancel_req_dt_time,'0')cancel_req_dt_time,a.seqfrom,a.caseseq,a.seq,c.code_generate_through,coalesce(b.tracking_flg,'N') as tracking_flg,a.finalized_date,      				  "+
			" a.finalized_flag,a.int_fl2d_id,a.licence_no,replace(a.licence_no,' ','')as licencenoo ,a.int_brand_id,b.brand_name,     "+
			" a.int_pack_id,c.package_name , a.int_quantity, c.export_box_size,c.quantity,a.int_planned_bottles, a.int_boxes,         "+
			" a.int_liquor_type,d.description, a.vch_license_type, a.plan_dt ,a.permit_no                                             "+
@rvind	" ,e.vch_firm_name||' ('|| e.vch_mobile_no||')' as unit_name                                                            "+
			" FROM distillery.brand_registration_19_20 b,distillery.packaging_details_19_20 c ,distillery.imfl_type d,fl2d.mst_stock_receive_19_20 a    "+
@rvind	" left join licence.fl2_2b_2d_19_20 e on  e.vch_license_type='FL2D' and e.int_app_id=a.int_fl2d_id                                  "+
			" where a.int_brand_id=b.brand_id and b.brand_id=c.brand_id_fk and a.int_pack_id=c.package_id and a.int_liquor_type=d.id  "+
			" and a.int_fl2d_id =? ORDER BY a.plan_dt DESC  ";*/
		
		 
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
		 	ps.setInt(1, ac.getDistillery_id());
			rs = ps.executeQuery();
			while (rs.next()) {

				BWFL_StockReceiveDataTable dt = new BWFL_StockReceiveDataTable();
				
                
				
				
				if(rs.getString("cancel_req_reason")!=null)
				{
					dt.setCancel_reason(rs.getString("cancel_req_reason")+"   Cancel Request Date Time Is "+rs.getString("cancel_req_dt_time"));
				}else{
					dt.setCancel_reason("NA");
				}
				
				if(rs.getString("cancel_order_no")!=null)
				{
					dt.setCancel_order_flg(true);
				}else{
					dt.setCancel_order_flg(false);
				}
				dt.setCancel_req_dt_time(rs.getString("cancel_req_dt_time"));
				
				
				dt.setCancel_flg(rs.getString("cancel_req_flg"));
               dt.setFinalize_Date(Utility.convertSqlDateToUtilDate(rs.getDate("finalized_date")));
                
				dt.setCancel_req_dt_time(rs.getString("cancel_req_dt_time"));
				dt.setLiqureTypeId(rs.getString("int_liquor_type"));
				dt.setRequest_id(rs.getInt("seq"));
				dt.setGtinno(rs.getString("code_generate_through"));
				dt.setShowDataTable_Date(rs.getDate("plan_dt"));
				dt.setShowDataTable_LiqureType(rs.getString("description"));
				dt.setShowDataTable_LicenceType(rs
						.getString("vch_license_type"));
				dt.setShowDataTable_Brand(rs.getString("brand_name"));
				dt.setShowDataTable_Packging(rs.getString("package_name"));
				dt.setNewml(rs.getInt("quantity"));
				dt.setNewsize(new Double((Long.parseLong(rs.getString("int_planned_bottles"))/Long.parseLong(rs.getString("int_boxes")))).intValue());
				dt.setShowDataTable_Quntity(rs.getString("quantity"));
				dt.setBrandPackagingData_PlanNoOfBottling(rs.getInt("int_planned_bottles"));
				dt.setShowDataTable_PlanNoOfBottling(rs
						.getString("int_planned_bottles"));
				dt.setShowDataTable_NoOfBoxes(rs.getString("int_boxes"));
				dt.setBrandId(rs.getInt("int_brand_id"));
				dt.setPackagingId(rs.getInt("int_pack_id"));
				dt.setLicenceNo(rs.getString("licence_no"));
				////System.out.println("dt.setLicenceNo="+dt.getLicenceNo());
				dt.setShowDataTable_PermitNo(rs.getString("permit_no"));
				dt.setLicencenoo(rs.getString("licencenoo").replaceAll("/", ""));
				dt.setFinalizedFlag(rs.getString("finalized_flag"));
				
				dt.setFinalize_Date(Utility.convertSqlDateToUtilDate(rs
						.getDate("finalized_date")));
				dt.setGtinno(rs.getString("code_generate_through"));

				if (rs.getDate("finalized_date") != null) {
					Date dat = Utility.convertSqlDateToUtilDate(rs
							.getDate("finalized_date"));
					//////System.out.println("date finalize" + dat);

					DateFormat formatter = new SimpleDateFormat("yyMMdd");
					String date = formatter.format(dat);
					dt.setFinalizedDateString(date);
					//////System.out.println(date);
				}
				/*if (!rs.getString("tracking_flg").equalsIgnoreCase("Y")) {
					dt.setFinalizedFlag("N");
				}*/
				dt.setUnit_id(rs.getInt("int_fl2d_id"));
				dt.setUnit_name(rs.getString("unit_name"));
				dt.setSeqfrm(rs.getLong("seqfrom"));
				dt.setCaseseq(rs.getLong("caseseq"));
				list.add(dt);
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
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
	
	
	
	// =============================get permit fees============================

	public double getPermitFee(String brand_Id, String packging_Id) {

		double permitFee = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList = 	" SELECT a.brand_id, a.brand_name, a.vch_license_type, b.package_name, b.package_id, b.permit " +
								" FROM distillery.brand_registration_19_20 a , distillery.packaging_details_19_20 b  " +
								" WHERE a.brand_id=b.brand_id_fk  " +
								" AND brand_id =?  AND b.package_id=?";

			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, Integer.parseInt(brand_Id));
			pstmt.setInt(2, Integer.parseInt(packging_Id));
			
			
			////System.out.println("brand_Id------------"+brand_Id);
			
			////System.out.println("packging_Id------------"+packging_Id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				permitFee = rs.getDouble("permit");

			}

		} catch (SQLException se) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));
			
			 se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return permitFee;

	}
	
	
	
	
	
	public boolean checkpermit(String permitno_entered, BWFL_StockReceiveAction action)
	{
////System.out.println("55");
		boolean flag = false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String SQl = 	" SELECT  * FROM fl2d.mst_stock_receive_19_20  " +
						" WHERE permit_no='"+action.getPermitNo().trim()+"' and district_id=(select districtid from public.district where deo='"+ ResourceUtil.getUserNameAllReq().trim() + "')";
		
		
		////System.out.println("SQl-----------------"+SQl);

		try {

			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);

			rs = ps.executeQuery();
			if (rs.next()) {
				flag = true;

			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
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
		return flag;
	
	}
	
	/*public boolean fl2dUnitMappingId(String permitno_entered, BWFL_StockReceiveAction action)
	{
////System.out.println("55");
		boolean flag = false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String SQl = 	" SELECT  * FROM fl2d.mst_stock_receive_19_20  " +
						" WHERE permit_no='"+action.getPermitNo().trim()+"' ";
		
		
		////System.out.println("SQl-----------------"+SQl);

		try {

			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);

			rs = ps.executeQuery();
			if (rs.next()) {
				flag = true;

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
		return flag;
	
	}*/
	public int getDutyRegisterId(BWFL_StockReceiveAction ac, String DutyRegisterId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int dutyRegisterId=0;
		String SQl ="select int_id,int_quantity, int_value from distillery.duty_register_19_20 where permitno_fl2d='"+DutyRegisterId+"'";
		////System.out.println("DutyRegisterId="+SQl);
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);
			rs = ps.executeQuery();
			if (rs.next()) {
				dutyRegisterId=rs.getInt(1);
				ac.setInt_quantity(rs.getInt("int_quantity"));
				ac.setInt_value(rs.getDouble("int_value"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
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
		return dutyRegisterId;
	}
	public ArrayList getOtherUnitList() {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);

	 String sql="select int_app_id_f,coalesce(vch_indus_name,'')vch_indus_name,coalesce(vch_wrk_phon,0)vch_wrk_phon from public.other_unit_registration where vch_indus_type='IU'";

	/*	String SQl = " SELECT int_app_id, vch_applicant_name, vch_firm_name ,"
				+ " vch_mobile_no,vch_license_type, vch_licence_no "
				+ " FROM licence.fl2_2b_2d_19_20 ,public.district b  WHERE vch_license_type= 'FL2D' and " +
				" b.districtid=core_district_id and   b.deo='"+ ResourceUtil.getUserNameAllReq().trim()+ "'  order by vch_applicant_name ";
	*/	////System.out.println("get other unit list="+sql);
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				////System.out.println("1="+rs.getString("int_app_id_f")+" 2="+rs.getString("vch_indus_name"));
				item.setLabel(rs.getString("vch_indus_name")+" ( "+rs.getString("vch_wrk_phon")+" )");
				item.setValue(rs.getString("int_app_id_f"));
				list.add(item);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
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
	//================
	public String getLicNoUnitId(BWFL_StockReceiveAction ac, String id) {
		 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// String
			// queryList="SELECT int_app_id_f,vch_undertaking_name,vch_wrk_add  from  dis_mst_pd1_pd2_lic where vch_wrk_phon="+ResourceUtil.getUserNameAllReq().trim();

			String queryList = 	"select vch_licence_no from licence.fl2_2b_2d_19_20 where  vch_license_type='FL2D' and int_app_id='"+id+"'  ";
			 ////System.out.println("getLicNoUnitId="+queryList);
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			if (rs.next()) { 
				ac.setLicenceNoId(rs.getString("vch_licence_no")); 
			}

			// pstmt.executeUpdate();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			 e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return "";

	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public void cancelPermit(BWFL_StockReceiveDataTable dt,BWFL_StockReceiveAction action) {
		Connection conn1 = null;
		Connection conn = null;
		PreparedStatement pstmt1 = null; 
		PreparedStatement pstmt = null; 
		int status = 0;
		String update="UPDATE fl2d.mst_stock_receive_19_20 SET cancel_order_no=? ,cancel_order_date=?,cancel_authority_name=?,cancel_order_date_time=?,cancel_pdf_name=?" +
				" WHERE int_fl2d_id=? and int_brand_id=? and int_pack_id=? and plan_dt=? and permit_no=? ";
		
	String bottling2="UPDATE bottling_unmapped.fl2d  "+
		"SET  cancel_permit=? "+
		"WHERE plan_id=? and  date_plan=? and  etin=? ";
	
	
	String bottling="delete from  bottling_unmapped.fl2d  "+
			""+
			"WHERE plan_id=? and  date_plan=? and  etin=? ";
		
		System.out.println("update="+update);
	
		System.out.println("dt.getBrandId()="+ dt.getBrandId());
		System.out.println("dt.getPackagingId()="+dt.getPackagingId());
		System.out.println("dt.getPermitnoD()"+dt.getShowDataTable_PermitNo());
		System.out.println("dt.getSeq()="+dt.getUnit_id());	
		System.out.println("finalize date "+dt.getFinalize_Date());
		System.out.println("Group id "+dt.getRequest_id());
		System.out.println("Etin "+dt.getGtinno());
		
		try {
			conn=ConnectionToDataBase.getConnection();
			conn1=ConnectionToDataBase.getConnection3();
			conn.setAutoCommit(false);
			conn1.setAutoCommit(false);
			pstmt=conn.prepareStatement(update);
			pstmt.setString(1, action.getCancel_order_no());
			pstmt.setDate(2, Utility.convertUtilDateToSQLDate(action.getCancel_order_date()));
			pstmt.setString(3, action.getAuthority_name());
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String time = sdf.format(cal.getTime());
			String cr_date_time=Utility.convertUtilDateToSQLDate(new Date())+" "+time;
			pstmt.setString(4, cr_date_time);
			pstmt.setString(5,action.getUploadedPdfName());
			
			pstmt.setInt(6,dt.getUnit_id());
			pstmt.setInt(7,dt.getBrandId());
			pstmt.setInt(8,dt.getPackagingId());
			pstmt.setDate(9, Utility.convertUtilDateToSQLDate(dt.getShowDataTable_Date()));
			pstmt.setString(10, dt.getShowDataTable_PermitNo());
			
			status=pstmt.executeUpdate();
			
			
			
			
			System.out.println("statusssssss 1     "+status);
			if(status>0)
			{
				status=0;
				
				pstmt1=conn1.prepareStatement(bottling);
			//	pstmt1.setString(1, "C");
				pstmt1.setInt(1, dt.getRequest_id());
				pstmt1.setDate(2, Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()));
				pstmt1.setString(3, dt.getGtinno());
				status=pstmt1.executeUpdate();
				System.out.println("statusssssss 2     "+status);
				if(status>0)
				{
					conn.commit();
					conn1.commit();
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Permit Canceled SuccessFul","Permit Canceled SuccessFul"));
				  action.cancelOrderClose();
				
				}else{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Permit Can Not Canceled ","Permit Can Not Canceled "));
				}
				
				
				
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

				
				
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				
				
				
				
				
				if (pstmt1 != null)
					pstmt1.close();
				if (conn1 != null)
					conn1.close();

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			}
		}
	}
	
	
	
	
public long getUploadSequence()
{
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	long val=0;
	String sql=    "select nextval('bwfl_license.cancel_permit')";
	try{
		conn=ConnectionToDataBase.getConnection();
		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery()
				;
		if(rs.next())
		{
			val=rs.getLong(1);
		}
	}catch(Exception e)
	{
		
	}finally {

		try {

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
	
	return val;
}
public void dataCancel(BWFL_StockReceiveAction action) {
	Connection conn1 = null;
	PreparedStatement pstmt1 = null;
	int status = 0;
	String update = "UPDATE fl2d.mst_stock_receive_19_20 SET cancel_req_flg='C' ,cancel_req_dt_time=?,cancel_req_reason='"+action.getRemark()+"' "
			+ "WHERE seq=?";
	//System.out.println(action.getRequest_id()+"="+update);
	try {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());
		String cr_date_time=Utility.convertUtilDateToSQLDate(new Date())+" "+time;
		
		conn1 = ConnectionToDataBase.getConnection();
		pstmt1 = conn1.prepareStatement(update);
		pstmt1.setString(1, cr_date_time);
		pstmt1.setInt(2, action.getRequest_id());
		status = pstmt1.executeUpdate();
		if (status > 0) {
			action.setRequest_id(0);
			action.setRemark("");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Cancel Request Submitted",
							"Cancel Request Submitted"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Cancel Request Not Submitted",
							"Cancel Request Not Submitted"));
		}
	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(e.getMessage(), e.getMessage()));
		try {
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();
	} finally {
		try {
			if (pstmt1 != null)
				pstmt1.close();
			if (conn1 != null)
				conn1.close();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}
	}
}	







public int checkFl11GatepassBwfl(BWFL_StockReceiveDataTable dt)
{
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	int i=0;
	String sql="SELECT  count(*) FROM bottling_unmapped.fl2d where plan_id=? and  date_plan=? and  etin=? and fl11gatepass is null ";
	try{
		conn=ConnectionToDataBase.getConnection3();
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, dt.getRequest_id());
		pstmt.setDate(2, Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()));
		pstmt.setString(3, dt.getGtinno());
		rs=pstmt.executeQuery();
		if(rs.next())
		{
			i=rs.getInt(1);
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
	return i;
}



public int checkFl36GatepassBwfl(BWFL_StockReceiveDataTable dt)
{
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	int i=0;
	String sql="SELECT  count(*) FROM bottling_unmapped.fl2d where plan_id=? and  date_plan=? and  etin=? and fl11gatepass is null ";
	try{
		conn=ConnectionToDataBase.getConnection3();
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, dt.getRequest_id());
		pstmt.setDate(2, Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()));
		pstmt.setString(3, dt.getGtinno());
		rs=pstmt.executeQuery();
		if(rs.next())
		{
			i=rs.getInt(1);
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
	return i;
}










}
