package com.mentor.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

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

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mentor.action.BWFL_ScanUploadAction_20_21;
import com.mentor.connectiondb.ConnectionToDataBase;
import com.mentor.datatable.BWFL_ScanUploadDT;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class BWFL_ScanUploadImpl_20_21 {
	// -----------------------bwfl details---------------------

		public void getDetails(BWFL_ScanUploadAction_20_21 act) {

			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			String query = 	" SELECT int_id,int_distillery_id,vch_license_type,vch_license_number,vch_applicant_name, " +
							" vch_firm_add, vch_distillery_contact_number FROM bwfl.registration_of_bwfl_lic_holder_20_21 " +
							" WHERE vch_distillery_contact_number='"+ ResourceUtil.getUserNameAllReq().trim()+ "' " +
							" AND vch_approval='V' ";
			try {
				con = ConnectionToDataBase.getConnection();
				ps = con.prepareStatement(query);

				//System.out.println("---------login---hidden----"+query);
				rs = ps.executeQuery();

				if (rs.next()) {
					act.setBwflDistId(rs.getInt("int_id"));
					act.setBwflDistMoblie(rs.getString("vch_distillery_contact_number"));
					act.setDistillery_name(rs.getString("vch_distillery_contact_number"));

					/*if (rs.getString("int_distillery_id")!="0") {
						act.setDistillery_name(rs.getString("int_distillery_id"));
					} else {
						act.setDistillery_name(rs.getString("vch_distillery_contact_number"));
					}*/
					act.setLicense_type(rs.getString("vch_license_type"));
					act.setLicense_number(rs.getString("vch_license_number"));
					act.setLicensee_name(rs.getString("vch_applicant_name"));
					act.setFirm_address(rs.getString("vch_firm_add"));

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

		}

		// -----------------------get data on first data
		// table-------------------------

		public ArrayList getDetails_BWFL(BWFL_ScanUploadAction_20_21 act) {

			ArrayList list = new ArrayList();
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			int j = 1;

			String selQr = " SELECT DISTINCT int_distillery_id ,permitno ,permitdt,gatepass_no,licence_no "
					+ " FROM bwfl_license.mst_bottling_plan_20_21 "
					+ " WHERE  int_distillery_id="
					+ act.getBwflDistId()
					+ " and int_planned_bottles>recieved_bottles AND scan_upload_flag IS NULL "
					+ " ORDER BY gatepass_no ";

			try {
				con = ConnectionToDataBase.getConnection();
				ps = con.prepareStatement(selQr);
				////System.out.println("getDetails_BWFL sql-------==" + selQr);
				rs = ps.executeQuery();

				while (rs.next()) {
					BWFL_ScanUploadDT dt = new BWFL_ScanUploadDT();
					dt.setDatefrst(rs.getDate("permitdt"));
					dt.setPassnofrst(rs.getString("gatepass_no"));
					dt.setDistilleryIdfrst(rs.getInt("int_distillery_id"));
					dt.setSnofrst(j);
					dt.setPermitnofrst(rs.getString("permitno"));
					dt.setLicenseNmbrfrst(rs.getString("licence_no"));

					j++;
					list.add(dt);
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

		// -----------------------show data on view details---------------------

		public ArrayList getFormDetails(BWFL_ScanUploadAction_20_21 act, BWFL_ScanUploadDT dt) {

			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			ArrayList list = new ArrayList();
			int j = 1;
			String selQr="";
			
			selQr = " SELECT DISTINCT (a.int_planned_bottles*pk.cesh) as add_fee,(a.int_planned_bottles*0.35) as scanning_fee , " +
					" a.unit_id,c.int_bwfl_id,a.int_distillery_id, a.int_brand_id, a.int_pack_id, a.int_quantity,       "+
					" a.permitno, a.permitdt,a.gatepass_no,c.brand_name,b.vch_brewery_contact_number,b.vch_distillery_contact_number,   "+
					" a.int_planned_bottles as bottles,a.int_boxes as boxes,a.int_liquor_type,a.vch_license_type,a.plan_dt,             "+
					" a.licence_no,a.cr_date,a.finalized_date,a.finalized_flag,a.seq,                                                   "+
					" entry_no_of_bottle_per_case as box_size, a.maped_unmaped_type                                                     "+
					" FROM bwfl_license.mst_bottling_plan_20_21 a ,bwfl.registration_of_bwfl_lic_holder_20_21 b,                        "+
					" distillery.brand_registration_20_21 c, distillery.packaging_details_20_21 pk  WHERE c.brand_id=a.int_brand_id                                             "+
					" AND a.int_distillery_id=b.int_id AND a.int_planned_bottles>a.recieved_bottles                                     "+
					" AND a.scan_upload_flag IS NULL and a.finalized_flag='F' and  a.cancel_req_flg is null                             "+
					" and a.cancel_req_dt_time is null and a.int_distillery_id='"+act.getSelect_lic_no()+"' and c.int_bwfl_id=a.unit_id " +
					" and pk.brand_id_fk=c.brand_id and a.int_pack_id=pk.package_id "+
					" ORDER BY a.cr_date DESC ";

			
		/*	String selQr = " SELECT DISTINCT a.int_distillery_id, a.int_brand_id, a.int_pack_id,"
					+ " a.int_quantity,a.permitno, a.permitdt,a.gatepass_no,c.brand_name,"
					+ " b.vch_brewery_contact_number, b.vch_distillery_contact_number , "
					+ " a.int_planned_bottles as bottles, "
					+ " a.int_boxes as boxes, a.int_liquor_type, a.vch_license_type,  "
					+ " a.plan_dt, a.licence_no, a.cr_date, "
					+ " a.finalized_date, a.finalized_flag, d.box_size,a.seq "
					+ " FROM bwfl_license.mst_bottling_plan_20_21 a ,bwfl.registration_of_bwfl_lic_holder_20_21 b,  " +
					" distillery.brand_registration_20_21 c, "
					+ " distillery.box_size_details d "
					+ " WHERE a.int_distillery_id='"
					+ act.getBwflDistId()
					// + dt.getDistilleryIdfrst()
					+ "' AND  c.brand_id=a.int_brand_id "
					+ " AND c.int_bwfl_id=a.int_distillery_id AND  "
					+ " a.int_distillery_id=b.int_id AND a.int_quantity=d.qnt_ml_detail and"
					
					+ " int_planned_bottles>recieved_bottles AND scan_upload_flag IS NULL and finalized_flag='F' " 
					// + " and a.gatepass_no='"+ dt.getPassnofrst() + "' "
					+ " ORDER BY a.cr_date desc ";*/
			try {
				con = ConnectionToDataBase.getConnection();
				ps = con.prepareStatement(selQr);

				System.out.println("datalist query------------------"+selQr);
				 
				rs = ps.executeQuery();
				while (rs.next()) {

					BWFL_ScanUploadDT dt2 = new BWFL_ScanUploadDT();
					dt2.setDistilleryIdfrst(rs.getInt("int_distillery_id"));
					dt2.setBrewery_id(rs.getInt("int_distillery_id"));
					dt2.setInt_brand_id(rs.getInt("int_brand_id"));
					dt2.setInt_pack_id(rs.getInt("int_pack_id"));
					dt2.setInt_quantity(rs.getInt("int_quantity"));
					dt2.setInt_planned_bottles(rs.getInt("bottles"));
					dt2.setInt_boxes(rs.getInt("boxes"));
					dt2.setInt_liquor_type(rs.getInt("int_liquor_type"));
					dt2.setVch_license_type(rs.getString("vch_license_type"));
					dt2.setPlan_dt(rs.getDate("plan_dt"));
					dt2.setLicence_no(rs.getString("licence_no"));
					dt2.setCr_date(rs.getDate("cr_date"));
					dt2.setFinalized_date(rs.getDate("finalized_date"));
					dt2.setFinalized_flag(rs.getString("finalized_flag"));
					dt2.setSize(rs.getInt("box_size"));
					// dt2.setReceived_from_usr(rs.getString("vch_distillery_contact_number"));

					dt2.setPermitno(rs.getString("permitno"));
					dt2.setBrand(rs.getString("brand_name"));

					dt2.setDatefrst(rs.getDate("permitdt"));
					dt2.setPassnofrst(rs.getString("gatepass_no"));
					// dt.setPassnofrst(rs.getString("gatepass_no"));
					//dt2.setPassno(Integer.parseInt(rs.getString("gatepass_no")));
					dt2.setSno(rs.getInt("seq"));
					dt2.setScanning_fee(rs.getDouble("scanning_fee"));
					dt2.setAdd_fee(rs.getDouble("add_fee"));
					
					
					dt2.setSlno(j);
					list.add(dt2);
					j++;

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

		// --------------------get cell value------------
		private String getCellValue(XSSFCell cell) {
			try {
				System.out
						.println("get cell value type----------------------------------"
								+ cell.getCellType());
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:

					return cell.getStringCellValue().toString();

				case XSSFCell.CELL_TYPE_BOOLEAN:
					return Boolean.toString(cell.getBooleanCellValue());

				case XSSFCell.CELL_TYPE_NUMERIC:
					String val = Double.toString(cell.getNumericCellValue());
					val = val.substring(0, val.lastIndexOf("."));

					return val;

				case XSSFCell.CELL_TYPE_BLANK:

					return null;

				}

				return null;
			} catch (Exception e) {
				return null;
			}

		}

		// -----------------------------save excel data--------------------

		public void saveExcelData(BWFL_ScanUploadAction_20_21 action) {

			String gatepass = "";
			int status = 0, flag = 1, excelcase = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;

			FileInputStream fileInputStream = null;
			XSSFWorkbook workbook = null;
			try {
				String sql = " INSERT INTO bwfl_license.bwfl_dispatch_casecode_20_21(vch_gatepass_no, vch_casecode)VALUES (?, ?) ";

				conn = ConnectionToDataBase.getConnection();
				String query = "DELETE FROM bwfl_license.bwfl_dispatch_casecode_20_21 where vch_gatepass_no ='"
						+ action.getScanGatePassNo().trim() + "' ";

				pstmt = conn.prepareStatement(query);
				pstmt.executeUpdate();
				pstmt = null;
				pstmt = conn.prepareStatement(sql);
				fileInputStream = new FileInputStream(action.getExcelFilepath());

				workbook = new XSSFWorkbook(fileInputStream);

				XSSFSheet worksheet = workbook.getSheet("Sheet1");
				int i = 0, j = 0;
				do {

					String casecode = "";
					XSSFRow row1 = worksheet.getRow(j);
					// XSSFRow row2 = worksheet.getRow(j+1);

					XSSFCell cellA1 = row1.getCell((short) 0);
					// XSSFCell cellA2 = row2.getCell((short) 0);

					String cellval = getCellValue(cellA1);
					// String cellval2=getCellValue(cellA2);

					if ((cellval == null /* && cellval2==null */)
							|| (cellval.length() == 0 /* && cellval2.length()==0 */)
							|| (cellval.equals("0.0")) /*
														 * &&
														 * cellval2.equals("0.0"))
														 */) {

						break;
					} else {

						if (j == 0) {
							cellA1 = row1.getCell((short) 0);
							gatepass = getCellValue(cellA1);
							if (action.getScanGatePassNo().trim()
									.equalsIgnoreCase(gatepass.trim())) {

								i = 1;
							} else {

								flag = 0;
							}
						} else {

							cellA1 = row1.getCell((short) 0);
							casecode = getCellValue(cellA1);

							pstmt.setString(1, gatepass.trim());
							pstmt.setString(2, casecode.trim());

							status = pstmt.executeUpdate();
							excelcase++;
							action.setExcelCases(excelcase);
							i = 1;
						}
					}

					j++;
				} while (i == 1);

			} catch (Exception e) {

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, e
								.getMessage(), e.getMessage()));
			}

			finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (flag == 1) {
				if (status > 0) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Upload successfully!!",
									"Upload successfully!!"));

				} else {

					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Invalid excel format!!",
									"Invalid excel format!!"));
				}
			} else {
				// action.setKidnlyUploadFlag(true);
				// action.setGatePassFlag(true);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Kindly enter the same gatepass number!!",
								"Kindly enter the same gatepass number!!"));
			}

		}

		// ----------------------------get excel data------------------------------

		public ArrayList getExcelData(BWFL_ScanUploadAction_20_21 act) {

			ArrayList list = new ArrayList();

			Connection con = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			 int boxingCount=0;
	         int listSize=0;
			String query = 	" SELECT vch_gatepass_no, vch_casecode FROM bwfl_license.bwfl_dispatch_casecode_20_21 " +
							" WHERE vch_gatepass_no='"+ act.getGatepassForCsv().trim()+ "'";
	////System.out.println("getExcelData"+query);
			try {
				con = ConnectionToDataBase.getConnection();
				stmt = con.prepareStatement(query);
				rs = stmt.executeQuery();

				while (rs.next()) {
					BWFL_ScanUploadDT dt = new BWFL_ScanUploadDT();
					
					String datenew=rs.getString("vch_casecode").substring(16,22).trim();
					
					datenew="20"+datenew;
					datenew	=datenew.substring(0,4)+"-"+datenew.substring(4,6)+"-"+datenew.substring(6);
					
					Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(datenew);

					dt.setGatepass(rs.getString("vch_gatepass_no"));
					dt.setCasecode(rs.getString("vch_casecode"));
								
					
					boolean flag=getCascodeMatch(date1,rs.getString("vch_casecode").substring(26),rs.getString("vch_casecode").substring(0, 12),act);
					String brand_name=getCascodeBrand(rs.getString("vch_casecode").substring(0, 12));
					dt.setBrand_name(brand_name);
					////System.out.println("brand name="+dt.getBrand_name());
					dt.setCasecoseBrandSize(Integer.parseInt(rs.getString("vch_casecode").substring(23, 26))); 
					dt.setDate_plan(Utility.convertUtilDateToSQLDate(date1));
					if(flag)
					{
						++listSize;
			        dt.setCascodeMatching("Valid");
					}else{act.setBottlingNotDoneFlag(true);
						++boxingCount;act.setFinflg(true);
						 dt.setCascodeMatching("Not Valid");
					}
					list.add(dt);

				}
				if(boxingCount!=0 || listSize<=0 )
				{
					act.setBottlingNotDoneFlag(true);
				}else
				{
					act.setBottlingNotDoneFlag(false);
				}
				 
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));		
				e.printStackTrace();
			}

			finally {
				try {
					if (stmt != null)
						stmt.close();
					if (con != null)
						con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			return list;

		}
		
		public String getUnmappedGroupId(BWFL_ScanUploadAction_20_21 act) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String groupId = "";

			try {
				String query = 	" SELECT array_to_string(array_agg(group_id), ',') as group_id FROM bwfl_license.mst_bottling_plan_20_21  " +
								" WHERE gatepass_no='"+act.getGatepassForCsv().trim()+"' ";

				//System.out.println("get group id==============" + query);

				conn = ConnectionToDataBase.getConnection();
				pstmt = conn.prepareStatement(query);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					groupId = rs.getString("group_id");

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
			return groupId;
		}
		
		
		public boolean getCascodeMatch(Date date1, String casecode,String etin ,BWFL_ScanUploadAction_20_21 act)
		{
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			boolean flag=false;
			String sql="";

			
			 	//sql = "select * from bottling_unmapped.bwfl WHERE casecode=? and etin=? and plan_id=?";
			/*if(act.getMappedUnmapedType().equalsIgnoreCase("M")){
				
				//System.out.println("come into  mapped---------------"+act.getMappedUnmapedType());
				
				sql = 	" SELECT * from bottling_unmapped.bwfl WHERE casecode='"+String.valueOf((Long.parseLong(casecode.trim())))+"'  " +
			 			" AND etin='"+String.valueOf(Long.parseLong(etin.trim()))+"' AND plan_id='"+act.getGroupid()+"' " +
			 			" AND date_plan='"+Utility.convertUtilDateToSQLDate(date1)+"' AND fl11_date IS NULL AND fl11gatepass IS NULL";
				
			}if(act.getMappedUnmapedType().equalsIgnoreCase("U")){
				
				//System.out.println("come into  unmapped---------------"+act.getMappedUnmapedType());*/
				
				sql = 	" SELECT * from bottling_unmapped.bwfl WHERE casecode='"+String.valueOf((Long.parseLong(casecode.trim())))+"'  " +
			 			" AND etin='"+String.valueOf(Long.parseLong(etin.trim()))+"' AND plan_id in ("+getUnmappedGroupId(act)+") " +
			 			" AND date_plan='"+Utility.convertUtilDateToSQLDate(date1)+"' AND fl11_date IS NULL AND fl11gatepass IS NULL";
			//}
			
			 	
			 	
			  
				 
			
		try{
			conn=ConnectionToDataBase.getConnection20_21();
			
			pstmt=conn.prepareStatement(sql);
			
			//System.out.println("valid not valid---------------"+sql);
			//pstmt.setString(1,String.valueOf((Long.parseLong(casecode.trim()))));
			//pstmt.setString(2,String.valueOf(Long.parseLong( etin.trim())));
			//pstmt.setInt(3, act.getGroupid());;
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				flag=true;
			} 
			
		}
		catch(Exception e)
		{FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));		
		e.printStackTrace();	
		}
		finally{
			try{
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
			
			
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return flag;
		}
		
		public void deleteData(BWFL_ScanUploadAction_20_21 act) {

			Connection con = null;
			PreparedStatement stmt = null;

			String query = 	" DELETE FROM bwfl_license.bwfl_dispatch_casecode_20_21 " +
							" WHERE vch_gatepass_no='"+ act.getGatepassForCsv().trim() + "'  ";
			int status = 0;
			try {
				con = ConnectionToDataBase.getConnection();
				stmt = con.prepareStatement(query);

				status = stmt.executeUpdate();
				if (status > 0) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("Cancelled Successfully ",
									"Cancelled Successfully "));
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Not Cancelled ", "Not Cancelled "));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			finally {
				try {
					if (stmt != null)
						stmt.close();
					if (con != null)
						con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		// ---------------------count casecode in
		// excel------------------------------

		public int excelCases(BWFL_ScanUploadAction_20_21 act) {

			int id = 0;
			Connection con = null;
			PreparedStatement pstmt = null, ps2 = null;
			ResultSet rs = null, rs2 = null;
			String s = "";
			try {
				con = ConnectionToDataBase.getConnection();

				String query = 	" SELECT count(*) as dispatchd_box FROM bwfl_license.bwfl_dispatch_casecode_20_21 " +
								"  WHERE vch_gatepass_no='"+ act.getGatepassForCsv().trim() + "'";
				//System.out.println("excelCases="+query);
				pstmt = con.prepareStatement(query);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					id = (rs.getInt("dispatchd_box"));

				}

			} catch (SQLException se) {
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
			return id;

		}

		// =============count cases from databases===================

		public int recieveCases(BWFL_ScanUploadAction_20_21 act) {

			int id = 0;
			Connection con = null;
			PreparedStatement pstmt = null, ps2 = null;
			ResultSet rs = null, rs2 = null;
			String s = "";
			try {
				con = ConnectionToDataBase.getConnection();

				String query = " SELECT SUM(int_boxes) as dispatchd_box FROM bwfl_license.mst_bottling_plan_20_21 "
						+ " WHERE cancel_req_flg is null and cancel_req_dt_time is null and gatepass_no='" + act.getGatepassForCsv().trim() + "' and  dispatch_date='"+Utility.convertUtilDateToSQLDate(act.getDispatch_date())+"' ";
	 
				pstmt = con.prepareStatement(query);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					id = (rs.getInt("dispatchd_box"));

				}

			} catch (SQLException se) {

				FacesContext.getCurrentInstance()
						.addMessage(null,new FacesMessage(se.getMessage(),se.getMessage()));
			
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
					FacesContext.getCurrentInstance()
					.addMessage(null,new FacesMessage(se.getMessage(),se.getMessage()));
				}
			}
			return id;

		}
		
		
		
		public boolean updateDispatch(BWFL_ScanUploadAction_20_21 act) {
			int save = 0;
			int save1 = 0;
			boolean val = false;
			PreparedStatement ps = null;
			Connection con = null;
			Connection con1 = null;
			String query = "";
			String datenew="";
			String delQr = "";
			Date date1=null;

			query = " UPDATE bottling_unmapped.bwfl SET  fl11gatepass=?, fl11_date=?  WHERE casecode=? " +
					" AND etin=? AND date_plan=? AND plan_id in ("+getUnmappedGroupId(act)+") " +
					" AND fl11gatepass IS NULL AND fl11_date IS NULL  ";
			
			//System.out.println("finalize------------"+query);
	 
			try {
				con = ConnectionToDataBase.getConnection();
				con1 = ConnectionToDataBase.getConnection20_21();
				con.setAutoCommit(false);
				con1.setAutoCommit(false);
				int j[] = null;
				ps = con1.prepareStatement(query);
				
				
				
				for (int i = 0; i < act.getGetVal().size(); i++) {
					BWFL_ScanUploadDT dt = (BWFL_ScanUploadDT) act.getGetVal().get(i);

					datenew=dt.getCasecode().substring(16,22).trim();
					
					datenew="20"+datenew;
					datenew	=datenew.substring(0,4)+"-"+datenew.substring(4,6)+"-"+datenew.substring(6);
					
					date1=new SimpleDateFormat("yyyy-MM-dd").parse(datenew);
					//System.out.println("gatepass=============="+dt.getGatepass());
					//System.out.println("casecode============"+dt.getCasecode().trim().substring(26,dt.getCasecode().trim().length()));
					//System.out.println("etin=========="+dt.getCasecode().trim().substring(0,12));
					//System.out.println("casecode trim date==========="+Utility.convertUtilDateToSQLDate(date1));
					//System.out.println("group id========="+getUnmappedGroupId(act));
					
					ps.setString(1, dt.getGatepass());
					ps.setDate(2, Utility.convertUtilDateToSQLDate(new Date()));
					ps.setString(3,dt.getCasecode().trim().substring(26,dt.getCasecode().trim().length()));				
					ps.setString(4, dt.getCasecode().trim().substring(0,12));
					ps.setDate(5, Utility.convertUtilDateToSQLDate(date1));
					//ps.setInt(6, Integer.parseInt(getUnmappedGroupId(act)));
				 
					ps.addBatch();
					 

				}
				
				 
				j = ps.executeBatch();
				save = j.length;
				if (act.getGetVal().size() == save && save>0) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					
					String updtQr = " UPDATE bwfl_license.mst_bottling_plan_20_21 SET scan_upload_flag='F'  " +
									" WHERE cancel_req_flg is null and cancel_req_dt_time is null and gatepass_no='"+ act.getGatepassForCsv().trim() + "' ";
					
					 
					ps = con.prepareStatement(updtQr);
					
					save1 = ps.executeUpdate();
					
					delQr = " DELETE FROM bwfl_license.bwfl_dispatch_casecode_20_21 " +
							" WHERE vch_gatepass_no ='"+ act.getGatepassForCsv().trim() + "' ";
					
					
				 
					
					ps = con.prepareStatement(delQr);
					ps.executeUpdate();
				}
				if (save1 > 0) {
					val = true;
					con.commit();
					con1.commit();
				} else {
					val = false;
					con.rollback();
					con1.rollback();
				}

			} catch (Exception ex) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(ex.getMessage(),ex.getMessage()));
				ex.printStackTrace();
			} finally {
				try {
					if (ps != null)
						ps.close();

					if (con != null)
						con.close();
					con1.close();
				} catch (Exception ex) {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(ex.getMessage(),ex.getMessage()));
					ex.printStackTrace();
				}
			}
			return val;

		}

		/*public boolean updateDispatch_old(BWFL_ScanUploadAction_20_21 act) {

			int save = 0;
			int j = 0;
			boolean val = false;
			PreparedStatement ps = null;
			Connection con = null;
			Connection con1 = null;
			String query = "";
			String casecd = "";
			
			try {
				con = ConnectionToDataBase.getConnection();
				con1 = ConnectionToDataBase.getConnection20_21();
				con.setAutoCommit(false);
				con1.setAutoCommit(false);
				for (int i = 0; i < act.getGetVal().size(); i++) {
					BWFL_ScanUploadDT dt = (BWFL_ScanUploadDT) act.getGetVal().get(i);

					
					 
						query ="insert into bottling_unmapped.bwfl (  fl11gatepass , plan_id,date_plan,etin,fl11_date,casecode) values ('"+dt.getGatepass().trim()+"',0,'"+Utility.convertUtilDateToSQLDate(new Date())+"','"+dt.getCasecode().trim().substring(2,15)+"','"+Utility.convertUtilDateToSQLDate(new Date())+"','"+Long.parseLong(dt.getCasecode().trim().substring(29,dt.getCasecode().trim().length()))+"') " +
								" ON CONFLICT (etin, casecode) DO UPDATE SET fl11gatepass='"+dt.getGatepass().trim()+"', " +
										"fl11_date='"+Utility.convertUtilDateToSQLDate(new Date())+"' where bottling_unmapped.bwfl.fl11_date is null ";
				
					 
					 ps = con1.prepareStatement(query);
	 
					j = ps.executeUpdate();
					if (j == 0) {
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(dt.getCasecode() + " Casecode not found! ", dt.getCasecode() + " Casecode not found! "));

					}
					save += j;
					casecd=dt.getCasecode();
				}  
				if (act.getGetVal().size() == save) {
					save = 0;

					String updtQr = " UPDATE bwfl_license.mst_bottling_plan_20_21 SET scan_upload_flag='F'  "
							+ " WHERE gatepass_no='"
							+ act.getGatepassForCsv().trim() + "' ";

					ps = con.prepareStatement(updtQr);
				 save = ps.executeUpdate(); 
					String query1 = " DELETE FROM bwfl_license.bwfl_dispatch_casecode_20_21 WHERE vch_gatepass_no ='"
							+ act.getGatepassForCsv().trim() + "' ";

					ps = con.prepareStatement(query1);
					ps.executeUpdate();

				} else {
					save = 0;val = false;
				}
				if (save > 0) {
					val = true;
					con.commit();
					con1.commit();
				} else {
					val = false;
					con.rollback();
					con1.rollback();
				}

			} catch (Exception se) {
				se.printStackTrace();
				FacesContext.getCurrentInstance()
				.addMessage(null,new FacesMessage(se.getMessage()+" near "+casecd,se.getMessage()+" near "+casecd));
			} finally {
				try {
					if (ps != null)
						ps.close();

					if (con != null)
						con.close();
					con1.close();
				} catch (Exception se) {
					FacesContext.getCurrentInstance()
					.addMessage(null,new FacesMessage(se.getMessage(),se.getMessage()));
				}
			}
			return val;

		}*/

		// -----------------------get data on last data
		// table-------------------------

		public ArrayList getRecieptsList(BWFL_ScanUploadAction_20_21 act) {

			ArrayList list = new ArrayList();
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String selQr = null;
			int j = 1;

			/*String selQr = " SELECT DISTINCT int_distillery_id, int_brand_id, int_pack_id, int_quantity, int_planned_bottles, int_boxes, int_liquor_type, vch_license_type, plan_dt,"
					+ " licence_no, cr_date, finalized_flag, finalized_date, received_liqour, permitno, permitdt, recieved_bottles, recieved_boxes, seq, gatepass_no, "
			+ " bottling_seq_frm, bottling_seq_to, scan_upload_flag, exp_order_nmbr, exp_order_dt, transprt_vehicle_nmbr, route_details, maped_unmaped_type, etin, "
			+ " group_id, breakage, dispatch_date "
					+ " FROM bwfl_license.mst_bottling_plan_20_21 "
					+ " WHERE  int_distillery_id="+ act.getBwflDistId()
					+ "  AND scan_upload_flag IS NOT NULL "
					+ " ORDER BY  seq desc ";*/
			
			
			selQr = " SELECT DISTINCT a.int_distillery_id, a.int_brand_id, a.int_pack_id, a.int_quantity, a.int_planned_bottles, "+
					" a.int_boxes, a.int_liquor_type, a.vch_license_type, a.plan_dt, a.licence_no, a.cr_date, a.finalized_flag,  "+
					" a.finalized_date, a.received_liqour, a.permitno, a.permitdt, a.recieved_bottles, a.recieved_boxes,         "+
					" a.seq, a.gatepass_no, a.bottling_seq_frm, a.bottling_seq_to, a.scan_upload_flag, a.exp_order_nmbr,         "+
					" a.exp_order_dt, a.transprt_vehicle_nmbr, a.route_details, a.maped_unmaped_type, a.etin,                    "+
					" a.group_id, a.breakage, a.dispatch_date, b.brand_name, c.package_name                                      "+
					" FROM bwfl_license.mst_bottling_plan_20_21 a, distillery.brand_registration_20_21 b, distillery.packaging_details_20_21 c     "+
					" WHERE a.int_pack_id=c.package_id AND a.int_brand_id=c.brand_id_fk AND b.brand_id=c.brand_id_fk             "+
					" AND a.cancel_req_flg is null and a.cancel_req_dt_time is null and a.int_distillery_id="+
					act.getSelect_lic_no()
					+"  AND a.scan_upload_flag IS NOT NULL  ORDER BY a.seq DESC ";
			 
			
			//System.out.println("second dt query-----------------"+selQr);
			
			try {
				con = ConnectionToDataBase.getConnection();
				ps = con.prepareStatement(selQr);
				 
				rs = ps.executeQuery();

				while (rs.next()) {
					BWFL_ScanUploadDT dt = new BWFL_ScanUploadDT();
					dt.setPassnothrd(rs.getString("gatepass_no"));
					dt.setDispatch_date(rs.getDate("dispatch_date"));
					 dt.setPermitno1(rs.getString("permitno")+" - "+rs.getDate("permitdt"));
				 if(rs.getString("scan_upload_flag").equalsIgnoreCase("S")){ 
					 dt.setPrintBtnFlag(true);
				 }else if(rs.getString("scan_upload_flag").equalsIgnoreCase("F")){ 
					 dt.setPrintBtnFlag(false);
				 } 
				 dt.setDispbox(rs.getInt("int_boxes"));
				 dt.setDispbot(rs.getInt("int_planned_bottles")-rs.getInt("breakage"));
				 			 
				 dt.setPermitNmbr_dt(rs.getString("permitno"));
				 dt.setBrandNm_dt(rs.getString("brand_name"));
				 dt.setPckgNm_dt(rs.getString("package_name"));
				 dt.setGroupid(rs.getInt("group_id"));
				 dt.setMappedUnmapedType_dt(rs.getString("maped_unmaped_type"));
				 
					dt.setSnothrd(j);
					
					/*
					 * dt.setDatethrd(rs.getDate("permitdt"));
					 * dt.setDistilleryIdthrd(rs.getInt("int_distillery_id"));
					 * 
					 * dt.setPermitnothrd(rs.getString("permitno"));
					 * dt.setLicenseNmbrthrd(rs.getString("licence_no"));
					 */

					j++;
					list.add(dt);
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

		public boolean updateData(BWFL_ScanUploadAction_20_21 act,BWFL_ScanUploadDT dt) {
			////System.out.println("updateData impl");

			int save = 0;
			int j = 0;
			boolean val = false;
			PreparedStatement ps = null;
			Connection con = null;
			int max=this.getMaxId();
			//Connection con1 = null;
			//String query = "";
			//String newGatepass = String.valueOf(this.getNewGatepass());

			try {
				con = ConnectionToDataBase.getConnection();
				con.setAutoCommit(false);

				String expOrderNo = act.getExpOrderNo();
				Date expOrderDt = act.getExpOrderDt();
				String transVehicleNo = act.getTransVehicleNo();
				String routeDetails = act.getRouteDetails();
				String gatepassNmbr = dt.getVch_license_type()+"_GATEPASS-"+dt.getSno();
				 
					if (dt.getBreakage() != "") {

						/*String updtQr = " UPDATE bwfl_license.mst_bottling_plan_20_21 SET scan_upload_flag='S', "
								+ " exp_order_nmbr=?, exp_order_dt=?, transprt_vehicle_nmbr=?, route_details=?, "
								+ " breakage=?, dispatch_date=?  "
								+ " WHERE permitno='"
								+ dt.getPermitno().trim()
								+ "' and seq='" + dt.getSno() + "'";*/
						
						String updtQr = " UPDATE bwfl_license.mst_bottling_plan_20_21 SET scan_upload_flag='S', "+
										" exp_order_nmbr=?, exp_order_dt=?, transprt_vehicle_nmbr=?, route_details=?, "+
										" breakage=?, dispatch_date=?, gatepass_no=?  "+
										" WHERE permitno='"+ dt.getPermitno().trim()+ "' AND seq='" + dt.getSno() + "'  "+
										" AND int_brand_id="+dt.getInt_brand_id()+" AND int_pack_id="+dt.getInt_pack_id()+" ";
	 
						 
						ps = con.prepareStatement(updtQr);
						ps.setString(1, expOrderNo);
						ps.setDate(2, Utility.convertUtilDateToSQLDate(expOrderDt));
						ps.setString(3, transVehicleNo);
						ps.setString(4, routeDetails);
						ps.setString(5, dt.getBreakage());
						ps.setDate(6, Utility.convertUtilDateToSQLDate(new Date()));
						ps.setString(7, gatepassNmbr.trim());
						save = ps.executeUpdate();
						
						if(save>0){
							save=0;
							ps=null;
							
							String bwfl_type="";
							if(act.getLicense_type().equalsIgnoreCase("1")){
								bwfl_type= "BWFL2A";
							}else if(act.getLicense_type().equalsIgnoreCase("2")){
								bwfl_type= "BWFL2B";
							}else if(act.getLicense_type().equalsIgnoreCase("3")){
								bwfl_type= "BWFL2C";
							}else if(act.getLicense_type().equalsIgnoreCase("4")){
								bwfl_type= "BWFL2D";
							}
							
							 // for(int i=1; i<=2; i++){
								  
							String updtQr2 = " INSERT INTO distillery.duty_register_19_20( "+
	                                         " int_id, date_crr_date, vch_duty_type,  int_value," +
	                                         " vch_description, int_bwfl_id, permitno_bwfl, gatepass, int_distillery_id) VALUES" +
	                                         "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	                                         
							ps = con.prepareStatement(updtQr2);
							ps.setInt(1, max);
							ps.setDate(2, Utility.convertUtilDateToSQLDate(new Date()));
                           
							
							//if(i==1){
								ps.setString(3, "scanning_fee_cr_BWFL_ALL");
							    ps.setDouble(4, dt.getScanning_fee());
							    ps.setString(5, "Scanning Fee For "+bwfl_type);
							
							/*//}else if (i==2){
								
								 if(act.getLicense_type().equalsIgnoreCase("2") || act.getLicense_type().equalsIgnoreCase("4")){
		                            	ps.setString(3, "BWFL2B_SOCIAL_DUTY");
									}else  if(act.getLicense_type().equalsIgnoreCase("1") || act.getLicense_type().equalsIgnoreCase("3")){
										ps.setString(3, "BWFL2A_SOCIAL_DUTY");
									}
								ps.setDouble(4, dt.getAdd_fee());
								ps.setString(5, "Special Addtional Consideration Fee For "+bwfl_type);
						//	}
*/							
							ps.setInt(6, Integer.parseInt(act.getSelect_lic_no()));
							ps.setString(7, dt.getPermitno());
							ps.setString(8, gatepassNmbr.trim());
							ps.setInt(9, 0);
							save = ps.executeUpdate();
							max++;
						 //}
						}
						
					

					}else{
						FacesContext.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"Breakage cannot be null !",
										"Breakage cannot be null !"));
					}	 
				act.reset();
				act.getDatalist();

				if (save > 0) {
					con.commit();
					act.setDataListFlag(true);
					act.setListFlagForPrint(true);
					val = true;
				}else{
					con.rollback();
				}

			} catch (Exception ex) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));		
				ex.printStackTrace();
			} finally {
				try {
					if (ps != null)
						ps.close();

					if (con != null)
						con.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			return val;
		}

		public int getNewGatepass() {
			int newgatepass = 0;

			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			String query = "SELECT MAX( CAST (gatepass_no AS INTEGER)) as gatepass_no FROM bwfl_license.mst_bottling_plan_20_21;";
			////System.out.println("query=" + query);
			try {
				con = ConnectionToDataBase.getConnection();
				ps = con.prepareStatement(query);

				rs = ps.executeQuery();

				if (rs.next()) {
				 newgatepass = rs.getInt("gatepass_no");
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

			return newgatepass + 1;
		}

		public boolean printReport(BWFL_ScanUploadAction_20_21 action) {
			String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
			String relativePath = mypath + File.separator + "ExciseUp"
					+ File.separator + "WholeSale" + File.separator + "jasper";
			String relativePathpdf = mypath + File.separator + "ExciseUp"
					+ File.separator + "WholeSale" + File.separator + "pdf";
			JasperReport jasperReport = null;
			JasperPrint jasperPrint = null;
			PreparedStatement pst = null;
			Connection con = null;
			ResultSet rs = null;
			String reportQuery = null;
			boolean printFlag = false;

			try {
				con = ConnectionToDataBase.getConnection();
				
				
				reportQuery = "SELECT (a.int_planned_bottles*pk.cesh) as add_fee,(a.int_planned_bottles*0.35) as scanning_fee ," +
						" b.int_distillery_id,a.dispatch_date, a.gatepass_no,a.licence_no," +
						"a.route_details, a.transprt_vehicle_nmbr, b.vch_applicant_name, b.vch_firm_add,a.int_boxes," +
						" c.brand_name,(a.int_planned_bottles - CAST(coalesce(a.breakage, '0') as integer))  as dispatched_bottles," +
						" a.int_quantity, CAST(((a.int_quantity * (a.int_planned_bottles - CAST(coalesce(a.breakage, '0') as integer)))/1000) as float) as litres, a.permitno, " +
						"   CASE WHEN pk.package_type='1' THEN 'Glass Bottle' "+
						"   WHEN pk.package_type='2' THEN 'CAN' "+
						"   WHEN pk.package_type='3' THEN 'Pet Bottle' "+
						"   WHEN pk.package_type='4' THEN 'Tetra Pack' "+
						"   WHEN pk.package_type='5' THEN 'Sachet' "+
						"   WHEN pk.package_type='6' THEN 'Keg' end as liqour_type "+
						" FROM bwfl_license.mst_bottling_plan_20_21 a, bwfl.registration_of_bwfl_lic_holder_20_21" +
						" b, distillery.brand_registration_20_21 c, distillery.packaging_details_20_21 pk where a.gatepass_no='"+action.getGatepass()+"' and " +
						" a.int_distillery_id = b.int_id AND c.brand_id=a.int_brand_id AND c.int_bwfl_id=a.unit_id" +
						" and pk.brand_id_fk=c.brand_id and a.int_pack_id=pk.package_id ";


				// }
	////System.out.println("reportQuery="+reportQuery);
				pst = con.prepareStatement(reportQuery);

		//		pst.setString(1, action.getGatepass());
				//pst.setDate(2, Utility.convertUtilDateToSQLDate(action.getDispatch_date()));

				// pst.setDate(2,
				// Utility.convertUtilDateToSQLDate(action.getDt_date()));
				// pst.setDate(2,
				// Utility.convertUtilDateToSQLDate(action.getPrintDate()));

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
							+ "BWFL_ScanUpload_reciept.jasper");

					JasperPrint print = JasperFillManager.fillReport(jasperReport,
							parameters, jrRs);
					Random rand = new Random();
					int n = rand.nextInt(250) + 1;

					JasperExportManager.exportReportToPdfFile(
							print,
							relativePathpdf + File.separator
									+ "BWFL_ScanUpload_reciept"
									+  n + ".pdf");

					BWFL_ScanUploadDT dt = new BWFL_ScanUploadDT();
					dt.setPdfName("BWFL_ScanUpload_reciept"
							+  n + ".pdf");
					action.setPdfName("BWFL_ScanUpload_reciept" +  n + ".pdf");
					dt.setPrintFlag(true);
					printFlag = true;

				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("No Data Found", "No Data Found"));
					BWFL_ScanUploadDT dt1 = new BWFL_ScanUploadDT();
					// action.setPrintFlag(false);
					printFlag = false;
					dt1.setPrintFlag(false);

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

			return printFlag;
		}
		
		
		// ===========================code for csv============================

		public void saveCSV(BWFL_ScanUploadAction_20_21 action) throws IOException {
			Connection con = null;
			PreparedStatement stmt = null;
		 
			 
			String query = " INSERT INTO bwfl_license.bwfl_dispatch_casecode_20_21(vch_gatepass_no, vch_casecode)VALUES (?, ?) ";
	 
		 	 con = ConnectionToDataBase.getConnection();
			  
			String gatepass = "";
			int status = 0, flag = 0;
			BufferedReader bReader = new BufferedReader(new FileReader(action.getCsvFilepath()));
			
			try {
				stmt = con.prepareStatement(query);

				String line = "";
				StringTokenizer st = null;
				int lineNumber = 0;
				int tokenNumber = 0;
				while ((line = bReader.readLine()) != null) {
					lineNumber++;
					String casecode = "";
					st = new StringTokenizer(line, " ");
					while (st.hasMoreTokens()) {
						String sd = st.nextToken() + "  ";

						if (sd != null) {
							if (lineNumber == 1) {							
								gatepass = sd.trim();
							}
							else// line number >1
							{
								if (gatepass.equalsIgnoreCase(action.getGatepassForCsv().trim())) {	
									
									if(sd.trim().length()==34)
									{
										casecode =sd.trim().substring(0, 12)+"1"+sd.trim().substring(12, sd.trim().length());
										
										
									}else{
									casecode = sd.trim();
									}
								 	if(this.etin(casecode.trim().substring(0, 12), action)==true){
										FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
												" Casecode-"+casecode.trim()+" does not belongs to the brands for the selected gatepass!" ," Casecode-"+casecode.trim()+" does not belongs to the brands for the selected gatepass!"));	
									}else{ 
									stmt.setString(1, gatepass);
									//stmt.setString(2, casecode);
									
									if(casecode.trim().length()==35)
									{
										stmt.setString(2, casecode.trim());
									}else
									{
										FacesContext.getCurrentInstance().addMessage(null,
										new FacesMessage("Invalid Length Of Casecode"+casecode.trim(), "Invalid Length Of Casecode"+casecode.trim()));
										break;
									
									}
									
									stmt.addBatch();
									}
									//status = stmt.executeUpdate();
								} else {
									flag = 1;
								}
							}

						}

						tokenNumber = 0;
					}
				}
						
				if (flag == 0) {
					
					status=stmt.executeBatch().length;
					if (status > 0) {
						action.setGatePassFlag(false);
						FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"File Uploaded Successfully ","File Uploaded Successfully "));
				 
						 
					} else {
						FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"File Not Uploaded!!", "File Not Uploaded!!"));
					}
				} else {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Kindly Enter Same Gatepass Number !! ","Kindly Enter Same Gatepass Number !! "));
				}

			} catch (Exception ex) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));	
				ex.printStackTrace();
			} finally {
				try {
					if (stmt != null)
						stmt.close();

					if (con != null)
						con.close();

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			

		}
		
		public boolean finalSubmit1(BWFL_ScanUploadAction_20_21 act) {
			////System.out.println("finalSubmit1 impl");

			int save = 0;
			int j = 0;
			boolean val = false;
			PreparedStatement ps = null;
			Connection con = null;
			Connection con1 = null;
			String query = "";
		 
			try {
				con = ConnectionToDataBase.getConnection();
				// con.setAutoCommit(false);
				String updtQr = " UPDATE bwfl_license.mst_bottling_plan_20_21 SET scan_upload_flag='F' "
							 + " WHERE gatepass_no='"+act.getGatepassForCsv()+"'";

						////System.out.println("updtQr=" + updtQr);
						ps = con.prepareStatement(updtQr);
			 					save = ps.executeUpdate();

					 
				

				if (save > 0) {
					val = true;
					
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (ps != null)
						ps.close();

					if (con != null)
						con.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			return val;
		}
		
		
		
		//--------------------etin check--------------------------
		
		
			public boolean  etin(String casecode, BWFL_ScanUploadAction_20_21 act) {
				 
				Connection con = null;
				PreparedStatement pstmt = null, ps2 = null;
				ResultSet rs = null, rs2 = null;
				boolean s = false;
				try {
					con = ConnectionToDataBase.getConnection();
		 
				
					String query =	" SELECT distinct b.code_generate_through FROM bwfl_license.mst_bottling_plan_20_21 a, "+
							" distillery.packaging_details_20_21 b   "+
							" WHERE b.package_id = a.int_pack_id  and a.gatepass_no='"+ act.getGatepassForCsv().trim()+ "' "+
							" and b.code_generate_through='"+casecode+"'  ";
					
					
					
					//System.out.println("query---------etin------"+query);

					pstmt = con.prepareStatement(query);
					 
					rs = pstmt.executeQuery();
					if (rs.next()) {
					 
					 
					}else{
						
						s=true;
					}

					 

				} catch (SQLException se) {
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
				return s;

			}
			
			public ArrayList getLicense()
			{
				
				Connection conn=null;
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				ArrayList list=new ArrayList();
				SelectItem item = new SelectItem();
				item.setLabel("--SELECT--");
				item.setValue("");
				list.add(item);
				String sql=
						"SELECT vch_license_number,int_id,int_distillery_id FROM bwfl.registration_of_bwfl_lic_holder_20_21 "+
						"WHERE vch_distillery_contact_number='"+ ResourceUtil.getUserNameAllReq().trim() + "' "+
						"ORDER BY vch_distillery_contact_number ASC ";
				try{
					conn=ConnectionToDataBase.getConnection();
					pstmt=conn.prepareStatement(sql);
					rs=pstmt.executeQuery();
					
					while(rs.next())
					{
						item=new SelectItem();
						item.setLabel(rs.getString("vch_license_number"));
						item.setValue(rs.getInt("int_id"));
						list.add(item);
					}
					
				}catch(Exception e)
				{FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
				
					e.printStackTrace();
				}finally {
					try {
						if (pstmt != null)
							pstmt.close();
						if (conn != null)
							conn.close();
						

					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
				
				
				return list;
			}
			public String getCascodeBrand(String etin)
			{
				Connection conn=null;
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				String brand_name="";
				String sql="";
				
				sql="select a.brand_name from distillery.brand_registration_20_21 a,distillery.packaging_details_20_21 b where " +
					"b.brand_id_fk=a.brand_id and code_generate_through='"+etin+"'";
				// //System.out.println("brand name casecode="+sql);
				try{
				conn=ConnectionToDataBase.getConnection();
				pstmt=conn.prepareStatement(sql);
				
			 
				//pstmt.setString(1, casecode);
				//pstmt.setString(2, etin);
				rs=pstmt.executeQuery();
				if(rs.next())
				{
					brand_name=rs.getString("brand_name");
				}else{
					brand_name="Invalid Brand";
				}
				
			}
			catch(Exception e)
			{FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();	
			}
			finally{
				try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
				
				
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			return brand_name;
			}
			
			public void getLicAddress(BWFL_ScanUploadAction_20_21 act) {
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				if(act.getSelect_lic_no()==null){
					act.setSelect_lic_no("0");
				}
		 			String query = 	"select vch_firm_add from bwfl.registration_of_bwfl_lic_holder_20_21 where int_id='"+act.getSelect_lic_no()+"'";
				try {
					con = ConnectionToDataBase.getConnection();
					ps = con.prepareStatement(query);
					rs = ps.executeQuery();
					if (rs.next()) { 
						act.setFirm_address(rs.getString("vch_firm_add"));
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

			}
			
			
			//----------------check for all etin------------------
			
			
			public boolean checkEtinNmbr(BWFL_ScanUploadAction_20_21 act)
			{

				
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				boolean pass = true;
				String query = "";

				try {
					
					query = 	" SELECT DISTINCT etin, SUM(int_boxes) as box FROM bwfl_license.mst_bottling_plan_20_21   "+
								" WHERE gatepass_no='"+act.getGatepassForCsv().trim()+"' GROUP BY etin  " +
								" EXCEPT   "+
								" SELECT DISTINCT left(vch_casecode,12) as etin, count(*) as box FROM bwfl_license.bwfl_dispatch_casecode_20_21  "+
								" WHERE vch_gatepass_no='"+act.getGatepassForCsv().trim()+"' GROUP BY etin ";

					conn = ConnectionToDataBase.getConnection();
					pstmt = conn.prepareStatement(query);
					
					//System.out.println("query-----"+query);

					rs = pstmt.executeQuery();
					if (rs.next()) {								
						pass = false;
						
						//System.out.println("come into if--------"+pass);

					}else{
		
						pass = true;
						
						//System.out.println("come into else--------"+pass);
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
				return pass;
			
			}
			
			
		// =============count number of cases on the particular permit ===================

		public int totalCasesOnPermit(BWFL_ScanUploadAction_20_21 act) {

			int boxes = 0;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ConnectionToDataBase.getConnection();

				String query = 	" SELECT SUM(int_boxes) as total_boxes FROM bwfl_license.mst_bottling_plan_20_21 " +
								" WHERE permitno='"+act.getPermitNmbr()+"' ";
	 
				pstmt = con.prepareStatement(query);
				
				//System.out.println("query 111111111111------------"+query);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					boxes = (rs.getInt("total_boxes"));
					
					//System.out.println("boxes 111111111111------------"+boxes);

				}

			} catch (SQLException se) {

				FacesContext.getCurrentInstance()
						.addMessage(null,new FacesMessage(se.getMessage(),se.getMessage()));
			
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();				
					if (rs != null)
						rs.close();				
					if (con != null)
						con.close();

				} catch (SQLException se) {
					FacesContext.getCurrentInstance()
					.addMessage(null,new FacesMessage(se.getMessage(),se.getMessage()));
				}
			}
			return boxes;

		}
		
		
		// =============count number of cases dispatched on the particular permit ===================

			public int totalCasesDispatchedOnPermit(BWFL_ScanUploadAction_20_21 act) {

				int boxes = 0;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {
					con = ConnectionToDataBase.getConnection();

					String query = 	" SELECT SUM(int_boxes) as total_boxes FROM bwfl_license.mst_bottling_plan_20_21 " +
									" WHERE permitno='"+act.getPermitNmbr()+"' AND dispatch_date IS NOT NULL ";
		 
					pstmt = con.prepareStatement(query);
					
					//System.out.println("query 222222222222------------"+query);

					rs = pstmt.executeQuery();

					while (rs.next()) {

						boxes = (rs.getInt("total_boxes"));
						
						//System.out.println("boxes 222222222222------------"+boxes);

					}

				} catch (SQLException se) {

					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(se.getMessage(),se.getMessage()));
				
				} finally {
					try {
						if (pstmt != null)
							pstmt.close();					
						if (rs != null)
							rs.close();					
						if (con != null)
							con.close();

					} catch (SQLException se) {
						FacesContext.getCurrentInstance()
						.addMessage(null,new FacesMessage(se.getMessage(),se.getMessage()));
					}
				}
				return boxes;

			}
			
			
			public int getMaxId() {
				int max = 0;

				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;

				String query = "SELECT MAX(int_id) as max FROM distillery.duty_register_19_20";
				////System.out.println("query=" + query);
				try {
					con = ConnectionToDataBase.getConnection();
					ps = con.prepareStatement(query);

					rs = ps.executeQuery();

					if (rs.next()) {
						max = rs.getInt("max");
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

				return max + 1;
			}	
}
