package com.mentor.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;


import com.mentor.action.RollOverStock_fl2d_NonRenewedBrandAction;
import com.mentor.action.Rollover_Stock_FL2D_18_19Action;
import com.mentor.datatable.RollOverStock_fl2d_NonRenewedBrandDT;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class RollOverStock_fl2d_NonRenewedBrandImpl {
	
	

	public String getDetails(RollOverStock_fl2d_NonRenewedBrandAction act) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList = " SELECT vch_licence_no,vch_mobile_no,int_app_id,vch_firm_name, vch_core_address,vch_license_type" +
					" FROM licence.fl2_2b_2d_19_20 WHERE vch_mobile_no = '"+ResourceUtil.getUserNameAllReq().trim() + "' ";
			
					/*" SELECT int_app_id, vch_licence_no, vch_firm_name, vch_license_type, vch_core_address, vch_mobile_no "
					+ " FROM licence.fl2_2b_2d_19_20 "
					+ " WHERE loginid='"
					+ ResourceUtil.getUserNameAllReq().trim() + "' ";*/

			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				act.setVch_licence_no(rs.getString("vch_licence_no"));
				act.setFl2_fl2bId(rs.getInt("int_app_id"));
				act.setFl2_fl2bName(rs.getString("vch_firm_name"));
				act.setFl2_fl2bAdrs(rs.getString("vch_core_address"));
				act.setFl2LicenseType(rs.getString("vch_license_type"));
			/*	if (rs.getString("vch_license_type").equalsIgnoreCase("FL2")) {
					act.setVch_from("FL2");
				} else if (rs.getString("vch_license_type").equalsIgnoreCase(
						"FL2B")) {
					act.setVch_from("FL2B");
				} else if (rs.getString("vch_license_type").equalsIgnoreCase(
						"CL2")) {
					act.setVch_from("CL2");
				}*/
			}

			// pstmt.executeUpdate();
		} catch (SQLException se) {
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
		return "";

	}

	
	// --------------------------------show detail

	public ArrayList displaylistImpl2(RollOverStock_fl2d_NonRenewedBrandAction act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;
		int i = 1;

		try {

			System.out.println("act.getFl2_fl2bId() "+act.getFl2_fl2bId());
			query = " SELECT distinct a.code_generate_through as code_generate_through, " +
					" c.seq,coalesce(m.deo_flg,'NA') as deo_flg,c.int_brand_id as brand_id,COALESCE(c.final_flag, 'N') as final_flag ,  " +
					" m.rollover_bottles as stock_bottles,m.rollover_boxes as stock_box,m.etin_new, c.seq ,c.licence_no , a.package_name,COALESCE(c.final_flag, 'NA')  AS final_flag, " +
					" a.package_id as pckg_id,a.permit, a.duty, a.adduty, c.vch_license_type,  c.int_brand_id,  b.brand_name,c.int_pack_id, " +
					" c.box_size ,c.int_boxes ,c.int_planned_bottles-coalesce(c.dispatch_36,0)  as avlbottle,   " +
					" ((c.int_planned_bottles-coalesce(c.dispatch_36,0))/(c.int_planned_bottles/c.int_boxes))   as dispatchd_box, " +
					" ((c.int_planned_bottles-coalesce(c.dispatch_36,0))/c.box_size) as avlboxes , " +
					" (c.int_planned_bottles/c.int_boxes) as export_box_size   " +
					" FROM distillery.packaging_details_19_20 a, distillery.brand_registration_19_20 b,  " +
					" fl2d.mst_stock_receive_19_20 c   left outer join fl2d.rollover_stock_for_fl2d_non_renew_brand  m on m.package_id=c.int_pack_id and " +
					" m.unit_id=c.int_fl2d_id and c.seq=m.seq WHERE a.brand_id_fk=b.brand_id AND  a.brand_id_fk=c.int_brand_id     " +
					" AND a.package_id=c.int_pack_id AND  b.brand_id=c.int_brand_id  AND c.int_fl2d_id='"+act.getFl2_fl2bId()+"'    " +
					" AND c.vch_license_type='FL2D' and a.vch_renewp is null " +
					" and c.finalized_flag='F' " +
					" AND  COALESCE(c.dispatch_36,0)<c.int_planned_bottles "; 

			/*"	SELECT  coalesce(m.deo_flg,'NA') as deo_flg, coalesce(c.renwal_flg,'NA') as renwal_flg, a.package_name as package_name,m.rollover_bottles as stock_bottles ,m.rollover_boxes as stock_box, b.brand_name as brand_name ,  a.code_generate_through as "
					+ "	  code_generate_through , c.brand_id as brand_id  ,c.id as id, c.type,case when COALESCE(c.recv_total_bottels,0)-COALESCE(c.dispatchbotl,0)<c.size then 1 else "
					+ "	round(((COALESCE(c.recv_total_bottels,0)-COALESCE(c.dispatchbotl,0))/c.size)+.4) end as   "
					+ "	 avlbottle,c.pckg_id as pckg_id,  c.size  as box_size  ,d.etin_unit_id  as etin_unit_id FROM distillery.packaging_details_19_20 a "
					+ "	   ,distillery.brand_registration_19_20 b,fl2d.fl2_2b_stock_19_20 c left outer join  fl2d.rollover_fl_stock_non_renew_brand   m on  "
					+ "	 m.brand_id=c.brand_id and  m.unit_id=c.id  and  m.package_id=c.pckg_id  , licence.fl2_2b_2d_20_21 d   WHERE c.id=d.int_app_id and  "
					+ "	 a.brand_id_fk=b.brand_id and a.vch_renewp is null AND a.brand_id_fk=c.brand_id     AND a.package_id=c.pckg_id AND b.brand_id=c.brand_id and "
					+ "	 c.recv_total_bottels-COALESCE(c.dispatchbotl,0)>0   AND c.id='"
					+ act.getFl2_fl2bId()
					+ "'  group by rollover_bottles, m.rollover_boxes ,c.brand_id, c.id,c.pckg_id, a.package_name, "
					+ "	 b.unit_name, c.type, d.etin_unit_id ,  b.brand_name,c.size  , "
					+ "	 m.deo_flg,  a.code_generate_through  order by  "
					+ "	  b.brand_name,a.package_name ";*/
			

			
			System.out.println("query "+query);
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();
			while (rs.next()) {
				RollOverStock_fl2d_NonRenewedBrandDT dt = new RollOverStock_fl2d_NonRenewedBrandDT();
				dt.setBrand_id(rs.getInt("brand_id"));
				dt.setBrand(rs.getString("brand_name"));
				dt.setPackage_id(rs.getInt("pckg_id"));
				dt.setSize(rs.getInt("box_size"));
				//dt.setStockbottle(rs.getInt("avlbottle")); 
				dt.setStockbottle(rs.getInt("avlboxes"));
				dt.setRolloverbox(rs.getInt("stock_box"));
				dt.setRolloverbottles(rs.getInt("stock_bottles"));
				dt.setOld_etin(rs.getString("code_generate_through"));
				 dt.setSeq(rs.getInt("seq"));
				
				if (rs.getString("final_flag") != null
						&& rs.getString("final_flag").length() > 0
						&& rs.getString("final_flag").equalsIgnoreCase("T")) {
					dt.setFinl_flg(true);
					dt.setBtflag(false);
					// act.setDataflg(true);
					// System.out.println("====="+act.getdat);
				} else {
					dt.setFinl_flg(false);
					dt.setBtflag(true);
					// act.setDataflg(false);
				}
				if (rs.getString("deo_flg").equalsIgnoreCase("T")) {

					// act.setCom_flg(true);
					dt.setCom_flg(true);
				}
				if(rs.getString("final_flag") != null
						&& rs.getString("final_flag").length() > 0
						&& rs.getString("final_flag").equalsIgnoreCase("T") ){
					 dt.setEtin_new(rs.getString("etin_new"));
					 System.out.println("====dt.getEtin_new()====="+dt.getEtin_new());
				}
				dt.setSlno(i);
				list.add(dt);

				i++;

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

	// ----------------------view details-----afer click scan and upload button
	// ---------------by arvind -----------------------------------
	public String viewdetail1(RollOverStock_fl2d_NonRenewedBrandAction act,
			RollOverStock_fl2d_NonRenewedBrandDT dt) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String query =" select distinct c.seq,a.package_id,c.int_fl2d_id,a.brand_id_fk,b.brand_name ,c.int_pack_id as pckg_id ," +
					      " a.brand_id_fk as brand_id, a.package_name as package_name, a.code_generate_through as code_generate_through," +
					      " b.etin_unit_id from  distillery.packaging_details_19_20 a,  distillery.brand_registration_19_20 b ," +
					      " fl2d.mst_stock_receive_19_20 c where a.brand_id_fk=b.brand_id and a.brand_id_fk=c.int_brand_id " +
					      " and b.brand_id=c.int_brand_id " +
					      " and a.brand_id_fk='"+dt.getBrand_id()+"' and  c.int_fl2d_id='"+act.getFl2_fl2bId()+"' and a.package_id='"+dt.getPackage_id()+"' " +
					      " and seq='"+dt.getSeq()+"' " +
					      "and a.package_id =c.int_pack_id   and c.int_planned_bottles-COALESCE(c.dispatch_36,0)>0 ";
					
					
					
					/* "select distinct b.brand_name ,c.pckg_id as pckg_id ,a.brand_id_fk as brand_id,c.id, a.package_name as package_name,a.code_generate_through as code_generate_through, b.etin_unit_id from  distillery.packaging_details_19_20 a, "
					+ " distillery.brand_registration_19_20 b ,fl2d.mst_stock_receive_19_20 c where a.brand_id_fk=b.brand_id and"
					+ " a.brand_id_fk=c.brand_id and b.brand_id=c.brand_id and a.brand_id_fk="
					+ "'"
					+ dt.getBrand_id()
					+ "'"
					+ " and  c.id='"
					+ act.getFl2_fl2bId()
					+ "'"
					+ " and a.package_id =c.pckg_id and a.package_id='"
					+ dt.getPackage_id()
					+ "'"
					+ " and c.recv_total_bottels-COALESCE(c.dispatchbotl,0)>0 ";*/

			// System.out.println("======remrk====" + query);
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				act.setBrand_nm(rs.getString("brand_name"));
				act.setEtn(rs.getString("code_generate_through"));
				act.setBrand_id(rs.getString("brand_id"));
				act.setPack_id(rs.getInt("pckg_id"));
				act.setNew_etin(dt.getEtin_new());
				act.setNew_brand_name(getNew_Brand_Name(dt.getEtin_new()));
				act.setSeq(rs.getInt("seq"));
			}

		} catch (SQLException se) {
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

	// -------------------------------------upload
	// csv-----------------------------------------------------------------------------------------
	private String csvFilename;
	private String csvFilepath;

	public String getCsvFilename() {
		return csvFilename;
	}

	public void setCsvFilename(String csvFilename) {
		this.csvFilename = csvFilename;
	}

	public String getCsvFilepath() {
		return csvFilepath;
	}

	public void setCsvFilepath(String csvFilepath) {
		this.csvFilepath = csvFilepath;
	}

	public void uploadCsv(UploadEvent event) {

		try {
			int size = 0;
			int counter = 0;
			UploadItem item = event.getUploadItem();

			String FullfileName = item.getFileName();

			String path = item.getFile().getPath();

			String fileName = FullfileName.substring(FullfileName
					.lastIndexOf("\\") + 1);

			ExternalContext con = FacesContext.getCurrentInstance()
					.getExternalContext();

			ServletContext sCon = (ServletContext) con.getContext();

			System.out.println("filename" + FullfileName
					+ "---------------filename" + fileName);

			size = item.getFileSize();
			this.setCsvFilename(FullfileName);
			this.setCsvFilepath(path);

		} catch (Exception ee) {
			// ee.printStackTrace();
			// System.out.println("exception in upload@");
		} finally {

		}

	}

	// ---------------------------------------------------------------------close
	// --------------------------------------------------

	// ===========================code for csv============================
	/*
	 * public void saveCSV(RollOverStock_fl2d_NonRenewedBrandAction action) throws
	 * IOException {
	 * 
	 * Connection con = null; PreparedStatement stmt = null;
	 * 
	 * String query =
	 * " INSERT INTO fl2d.rollover_stock(etin, casecode)VALUES (?, ?) "; String
	 * casecode = ""; String gatepass = ""; int status = 0, flag = 0;
	 * BufferedReader bReader = new BufferedReader(new FileReader(
	 * action.getCsvFilepath()));
	 * 
	 * try { con = ConnectionToDataBase.getConnection();
	 * con.setAutoCommit(false); stmt = con.prepareStatement(query);
	 * 
	 * int box_No = action.getRollerboxses(); String line = ""; StringTokenizer
	 * st = null; int lineNumber = 0; int tokenNumber = 0;
	 * 
	 * 
	 * while ((line = bReader.readLine()) != null) { lineNumber++;
	 * 
	 * st = new StringTokenizer(line, " ");
	 * 
	 * while (st.hasMoreTokens()) { String sd = st.nextToken() + "  ";
	 * 
	 * if (this.check(sd.trim().substring(26), action) == true) { if (sd !=
	 * null) {
	 * 
	 * casecode = sd;
	 * 
	 * 
	 * 
	 * 
	 * if (casecode.trim().substring(0, 12)
	 * .equalsIgnoreCase(action.getEtn().trim())) { stmt.setString(1,
	 * action.getEtn()); stmt.setString(2, casecode.trim()); stmt.addBatch();
	 * 
	 * } else { FacesContext .getCurrentInstance() .addMessage( null, new
	 * FacesMessage( FacesMessage.SEVERITY_ERROR, " Casecode-" + casecode.trim()
	 * + " does not belongs to the selected brand !", " Casecode-" +
	 * casecode.trim() + " does not belongs to the selected brand !"));
	 * 
	 * 
	 * }
	 * 
	 * }
	 * 
	 * tokenNumber = 0; } } }
	 * 
	 * int countExcel[] = stmt.executeBatch();
	 * 
	 * System.out.println(countExcel.length + ":length:" + box_No); if
	 * (countExcel.length == box_No) {
	 * 
	 * con.commit(); FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage(FacesMessage.SEVERITY_ERROR, "File Uploaded Successfully ",
	 * "File Uploaded Successfully ")); } else { FacesContext
	 * .getCurrentInstance() .addMessage( null, new FacesMessage(
	 * "Uploaded Cases in CSV and Boxes defined does not match!File Read Ended at case no -"
	 * + casecode.trim() + " ",
	 * "Uploaded Cases in CSV and Boxes defined  does not match!File Read Ended at case no - "
	 * + casecode.trim() + ""));
	 * 
	 * }
	 * 
	 * }
	 * 
	 * catch (Exception ex) { ex.getMessage(); } finally { try { if (stmt !=
	 * null) stmt.close();
	 * 
	 * if (con != null) con.close();
	 * 
	 * } catch (Exception ex) { ex.getMessage(); } }
	 * 
	 * }
	 */
	public void saveCSV(RollOverStock_fl2d_NonRenewedBrandAction action)
			throws IOException {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);
			String queryList = " delete FROM fl2d.rollover_stock_fl2d_cases_non_renew_brand  where  etin='"
					+ action.getEtn()
					+ "' and seq='"+action.getSeq()+"' and unit_id="
					+ action.getFl2_fl2bId();
			stmt = con.prepareStatement(queryList);
			stmt.executeUpdate();

			String query = " INSERT INTO fl2d.rollover_stock_fl2d_cases_non_renew_brand(etin,etin_new, casecode,unit_id,packg_id,seq)VALUES (?, ?,?,?,?, ?) ";
			String casecode = "";
			String gatepass = "";
			int status = 0, flag = 0;
			BufferedReader bReader = new BufferedReader(new FileReader(
					action.getCsvFilepath()));

			stmt = con.prepareStatement(query);

			int box_No = action.getRollerboxses();
			String line = "";
			StringTokenizer st = null;
			int lineNumber = 0;
			int tokenNumber = 0;
			while ((line = bReader.readLine()) != null) {
				lineNumber++;

				st = new StringTokenizer(line, " ");
				while (st.hasMoreTokens()) {
					String sd = st.nextToken() + "  ";

					if (sd != null) {

						casecode = sd;
						if (casecode.trim().substring(0, 12)
								.equalsIgnoreCase(action.getEtn().trim())) {
							stmt.setString(1, action.getEtn());
							stmt.setString(2, action.getNew_etin());
							stmt.setString(3, casecode.trim());
							stmt.setInt(4, action.getFl2_fl2bId());
							stmt.setInt(5, action.getPack_id());
							stmt.setInt(6, action.getSeq());
							stmt.addBatch();

						} else {
							FacesContext
									.getCurrentInstance()
									.addMessage(
											null,
											new FacesMessage(
													FacesMessage.SEVERITY_ERROR,
													" Casecode-"
															+ casecode.trim()
															+ " does not belongs to the selected brand !",
													" Casecode-"
															+ casecode.trim()
															+ " does not belongs to the selected brand !"));

						}

					}

					tokenNumber = 0;
				}
			}

			int countExcel[] = stmt.executeBatch();

			if (countExcel.length == box_No) {

				con.commit();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"File Uploaded Successfully ",
								"File Uploaded Successfully "));
			} else {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										"Uploaded Cases in CSV and Boxes defined does not match!File Read Ended at case no -"
												+ casecode.trim() + " ",
										"Uploaded Cases in CSV and Boxes defined  does not match!File Read Ended at case no - "
												+ casecode.trim() + ""));

			}

		}

		catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), e.getMessage()));

		} finally {
			try {
				if (stmt != null)
					stmt.close();

				if (con != null)
					con.close();

			} catch (Exception e) {

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, e
								.getMessage(), e.getMessage()));

			}
		}

	}

	public boolean etin(String casecode, RollOverStock_fl2d_NonRenewedBrandAction act) {

		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		boolean s = false;
		try {
			con = ConnectionToDataBase.getConnection();

			String query = " SELECT distinct b.code_generate_through FROM distillery.fl2_stock_trxn_19_20 a, distillery.packaging_details_19_20 b "
					+ " WHERE b.package_id = a.int_pckg_id   and b.code_generate_through='"
					+ casecode + "'";
			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();
			if (rs.next()) {

			} else {

				s = true;
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

	// ===============================show csv detail

	public ArrayList showcsvDetail(RollOverStock_fl2d_NonRenewedBrandAction act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		int check_count = 0;
		int i = 1;

		try {

			String selQr1 = "select etin ,etin_new,casecode from  fl2d.rollover_stock_fl2d_cases_non_renew_brand where etin='"
					+ act.getEtn() + "' and seq='"+act.getSeq()+"' and unit_id=" + act.getFl2_fl2bId();

			System.out.println("sql "+selQr1);
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(selQr1);

			rs = ps.executeQuery();
			while (rs.next()) {
				RollOverStock_fl2d_NonRenewedBrandDT dt2 = new RollOverStock_fl2d_NonRenewedBrandDT();
				dt2.setEtin_csv(rs.getString("etin"));
				dt2.setCasecose_csv(rs.getString("casecode"));
				dt2.setEtin_new(rs.getString("etin_new"));

				try {
					String check = checkCasecodeValid(rs.getString("casecode"));
				 //String check="VALID";
					dt2.setValidInvalid(check);
					if (check.equals("VALID")) {

					} else {
						check_count++;
					}

					if (check_count > 0) {
						act.setSave_disable(true);
					} else {
						act.setSave_disable(false);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				dt2.setSlno_csv(i);
				act.setSave_flg("F");
				System.out.println("===Save_flg==" + act.getSave_flg());
				list.add(dt2);

				i++;

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

	// -----------------------------------delete

	// ===================Rejected

	public void resetcsvcasecode(RollOverStock_fl2d_NonRenewedBrandAction action) {

		int saveStatus = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String queryList = "";

		try {

			queryList = " delete FROM fl2d.rollover_stock_fl2d_cases_non_renew_brand where unit_id="
					+ action.getFl2_fl2bId()
					+ " and seq='"+action.getSeq()+"' and etin='"
					+ action.getEtn()
					+ "'";

			// System.out.println("====Rejected========" + queryList);
			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			saveStatus = pstmt.executeUpdate();

			if (saveStatus > 0) {
				// action.setFinalflg(true);

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Deleted  Successfully !!! ",
								" Deleted Successfully !!!"));

			} else {
				// System.out.println("---------- UPDATE NOT ----------");
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Not Deleted  !!! ", "Not Deleted !!!"));

			}

		} catch (Exception se) {
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

	}

	public String save(RollOverStock_fl2d_NonRenewedBrandAction act,
			RollOverStock_fl2d_NonRenewedBrandDT dt) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = "";
		int tok1 = 0;
		int tok2 = 0;
		int seq = this.maxId();

		try {
			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);
			/*
			 * String query1 = "INSERT INTO fl2d.fl2_2b_stock_wholesale_20_21" +
			 * "(int_dist_id,  brand, package, stock_box, stock_bottles, dispatch_box, "
			 * + "dispatch_bottles, size,licence_no) VALUES ('" +
			 * act.getFl2_fl2bId() + "', '" + dt.getBrand_id() + "', '" +
			 * dt.getPackage_id() + "', '" + dt.getRolloverbox() + "', '" +
			 * dt.getRolloverbottles() + "', '0', '0', '" + dt.getSize() +
			 * "' ,'" + act.getVch_licence_no() + "')"; pst =
			 * con.prepareStatement(query1);
			 * 
			 * tok1 = pst.executeUpdate(); //
			 * System.out.println("========================tok1=================="
			 * +tok1); if (tok1 > 0)
			 */{

				query = "INSERT INTO fl2d.rollover_stock_for_fl2d_non_renew_brand(etin,etin_new, brand_id, package_id, "
						+ " rollover_bottles, rollover_boxes, finalize_dt,unit_id,unit_type,old_unit_id,seq,size)"
						+ " VALUES ('"
						+ act.getEtn()
						+ "', '"
						
						+ act.getNew_etin()
						+ "', '"
						+ act.getBrand_id()
						+ "', '"
						+ act.getPack_id()
						+ "',"
						+ "  '"
						+ act.getRollerbottles()
						+ "',  '"
						+ act.getRollerboxses()
						+ "',  '"
						+ Utility.convertUtilDateToSQLDate(new Date())
						+ "','"
						+ act.getFl2_fl2bId() + "','"+dt.getType_old()+"','"+dt.getUnit_id_old()+"','"+dt.getSeq()+"', '"+dt.getSize()+"' )";
				
System.out.println("---save data===="+query);
				pst = con.prepareStatement(query);
				tok2 = pst.executeUpdate();

			}
			if (tok2 > 0) {
				// /select size,pckg_id,brand_id,renwal_flg,id from
				// fl2d.fl2_2b_stock_19_20
				query = " update  fl2d.mst_stock_receive_19_20  set  final_flag='T' where int_fl2d_id='"
						+ act.getFl2_fl2bId()
						+ "'  "
						+ " and   int_brand_id='"
						+ act.getBrand_id()
						+ "'"
						+ " and int_pack_id='"
						+ act.getPack_id()
						+ "' and box_size='"
						+ act.getSize()
						+ "' and seq='"+dt.getSeq()+"'";
						
						
						/*"update  fl2d.fl2_2b_stock_19_20  set  renwal_flg='T' where id='"
						+ act.getFl2_fl2bId()
						+ "'  "
						+ " and   brand_id='"
						+ act.getBrand_id()
						+ "'"
						+ " and pckg_id='"
						+ act.getPack_id()
						+ "' and size='"
						+ act.getSize()
						+ "'";*/

				pst = con.prepareStatement(query);
				tok1 = pst.executeUpdate();
				con.commit();
				act.setViewflg("F");
				

				ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,
						Constants.SAVED_SUCESSFULLY);

			} else {
				 con.rollback();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
						Constants.NOT_SAVED);

			}

		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), e.getMessage()));

		}

		finally {
			try {
				if (con != null)
					con.close();

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, e
								.getMessage(), e.getMessage()));

			}
		}

		return "";

	}

	public int maxId() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(seq) as id FROM fl2d.gatepass_to_districtwholesale_fl2_fl2b_19_20 ";
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

	public String getDetails1(RollOverStock_fl2d_NonRenewedBrandAction ac,
			RollOverStock_fl2d_NonRenewedBrandDT dt) {

		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;

		try {
			con = ConnectionToDataBase.getConnection();

			String queryList = "select int_dist_id,brand from fl2d.fl2_stock_20_21 where int_dist_id="
					+ "(select id from fl2d.fl2_2b_stock_19_20 where id="
					+ "'"
					+ ac.getFl2_fl2bId()
					+ "' and pckg_id='"
					+ ac.getPack_id()
					+ "' and brand_id='" + ac.getBrand_id() + "')";

			pstmt = con.prepareStatement(queryList);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				ac.setBtflag(true);

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
		return "";

	}

	public String viewdetail11(RollOverStock_fl2d_NonRenewedBrandAction act,
			RollOverStock_fl2d_NonRenewedBrandDT dt) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String query = "select (mrp*2)/100 as mrpperbottle from  distillery.packaging_details_19_20"
					+ "  where package_id='" + dt.getPackage_id() + "' ";

			System.out.println("======setMrp====" + query);
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				act.setMrp(rs.getDouble("mrpperbottle")
						* dt.getRolloverbottles());

			}

		} catch (SQLException se) {
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
		return "";

	}

	public String viewdetail12(RollOverStock_fl2d_NonRenewedBrandAction act,
			RollOverStock_fl2d_NonRenewedBrandDT dt) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String query = "SELECT ((b.adduty-a.adduty) +(b.duty-a.duty)) as duty FROM distillery.packaging_details_19_20 a,"
					+ " distillery.packaging_details_20_21 b where a.package_id='"
					+ dt.getPackage_id() + "' and a.package_id=b.package_id ";

			System.out.println("======duty====" + query);
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				act.setDuty(rs.getDouble("duty") * dt.getRolloverbottles());

			}

		} catch (SQLException se) {
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
		return "";

	}

	public boolean validatechallan(RollOverStock_fl2d_NonRenewedBrandAction action,
			RollOverStock_fl2d_NonRenewedBrandDT dt) {
		boolean flag = false;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		double chalanamot = 0;

		/*
		 * "select  vch_total_amount from licence.mst_challan_master a,licence.challan_head_details b where a.vch_challan_id=? and  "
		 * +
		 * " b.vch_rajaswa_head='3900800050000'   and  b.g6_head='9'  and b.amount=a.vch_total_amount::numeric     and b.vch_challan_id=a.vch_challan_id "
		 * + "and b.vch_reference_id=a.vch_reference_id" +
		 * "  and  a.vch_trn_status='SUCCESS' and a.vch_challan_id not in  (select challan_no::character varying from  fl2d.rollover_fl_stock  ) "
		 * ;
		 */

		try {
			conn = ConnectionToDataBase.getConnection();

			if (action.getChallantable().size() > 0) {
				for (int i = 0; i < action.getChallantable().size(); i++) {

					RollOverStock_fl2d_NonRenewedBrandDT dt1 = (RollOverStock_fl2d_NonRenewedBrandDT) action
							.getChallantable().get(i);
					String sql = "select  a.vch_challan_id,vch_total_amount from licence.mst_challan_master a,licence.challan_head_details b where a.vch_challan_id='"
							+ dt1.getChallanname().trim()
							+ "' and "
							+ " b.vch_rajaswa_head='3900800050000'   and  b.g6_head='9'  and b.amount=a.vch_total_amount::numeric "
							+ "and b.vch_challan_id=a.vch_challan_id  and a.vch_challan_id='190009172'		"
							+ "and b.vch_reference_id=a.vch_reference_id "
							+ " and  a.vch_trn_status='SUCCESS' and a.vch_challan_id not in "
							+ " (select challan_no::character varying from  fl2d.rollover_fl_stock_challan_detail  )";
					System.out.println("====query----" + sql);
					pstmt = conn.prepareStatement(sql);

					// pstmt.setString(1, dt.getChallannamebr().trim());
					// pstmt.setString(1, dt1.getChallanname().trim());
					System.out.println("----getChallanname===="
							+ dt1.getChallanname());
					// /System.out.println("----getChallanname====" +
					// rs.getDouble("vch_total_amount"));
					rs = pstmt.executeQuery();
					if (rs.next()) {
						chalanamot = chalanamot
								+ rs.getDouble("vch_total_amount");
						System.out.println("----getChallanname===="
								+ rs.getDouble("vch_total_amount"));
					}

				}

			}
			System.out.println("===rs.next()chalanamot----" + chalanamot);
			System.out.println("===chalanamot----"
					+ (action.getMrp() + action.getDuty()));
			/*
			 * pstmt=conn.prepareStatement(sql);
			 * 
			 * //pstmt.setString(1, dt.getChallannamebr().trim());
			 * pstmt.setString(1, dt.getChallannamebr().trim());
			 * 
			 * rs=pstmt.executeQuery();
			 */
			// if(rs.next())
			if (chalanamot != 0) {

				if (chalanamot >= (action.getMrp() + action.getDuty())) {
					flag = true;
				} else {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(
									"Challan Amount Mismatch Found For Fee! ",
									"Challan Amount Mismatch Found For Fee!"));
				}
			} else {
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {
				conn.close();
				pstmt.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return flag;
	}

	public boolean updatechallandetail(RollOverStock_fl2d_NonRenewedBrandAction action,
			RollOverStock_fl2d_NonRenewedBrandDT dt) {
		ArrayList list = new ArrayList();
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		int i = 0;
		int ii = 0;
		int iii = 0;

		String sql = "UPDATE fl2d.rollover_fl_stock SET "
				+
				// " challan_no=?, challan_dt=?, " +
				" fees_mrp=?, fee_duty=? "
				+ " WHERE etin=? and  brand_id=? and  package_id=? ";
		String sql2 = "INSERT INTO fl2d.rollover_fl_stock_challan_detail( etin, brand_id, package_id,  challan_no, challan_dt,int_dist_id) VALUES (?, ?, ?, ?, ?, ?);";
		try {

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			// pstmt.setString(1, dt.getChallanname());
			// pstmt.setDate(2, Utility.convertUtilDateToSQLDate(dt.getDate()));
			pstmt.setDouble(1, action.getMrp());
			pstmt.setDouble(2, action.getDuty());
			pstmt.setString(3, action.getEtn());
			pstmt.setInt(4, Integer.parseInt(action.getBrand_id()));
			pstmt.setInt(5, action.getPack_id());
			i = pstmt.executeUpdate();

			if (i > 0) {
				if (action.getChallantable().size() > 0)

				{
					for (int k = 0; k < action.getChallantable().size(); k++) {

						RollOverStock_fl2d_NonRenewedBrandDT dt1 = (RollOverStock_fl2d_NonRenewedBrandDT) action
								.getChallantable().get(k);
						pstmt1 = conn.prepareStatement(sql2);

						pstmt1.setString(1, action.getEtn());
						pstmt1.setInt(2, Integer.parseInt(action.getBrand_id()));
						pstmt1.setInt(3, action.getPack_id());
						pstmt1.setInt(4, Integer.parseInt(dt1.getChallanname()));
						pstmt1.setDate(5,
								Utility.convertUtilDateToSQLDate(dt1.getDate()));
						pstmt1.setInt(6, action.getFl2_fl2bId());
						ii = pstmt1.executeUpdate();

					}
				}
			}
			if (ii > 0) {
				String query = "update  fl2d.fl2_2b_stock_19_20  set  finaliz_flg='T' where id='"
						+ action.getFl2_fl2bId()
						+ "'  "
						+ " and   brand_id='"
						+ dt.getBrand_id()
						+ "'"
						+ " and pckg_id='"
						+ dt.getPackage_id()
						+ "' and size='"
						+ dt.getSize()
						+ "'";

				System.out.println("update===========" + query);

				pstmt1 = conn.prepareStatement(query);
				iii = pstmt1.executeUpdate();
			}

			if (iii > 0) {
				flag = true;

				System.out.println("commit");
				conn.commit();
			} else {
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {
				conn.close();
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmt1 != null) {
					pstmt1.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return flag;
	}

	public int displaylist1implcount(RollOverStock_fl2d_NonRenewedBrandAction act) {

		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = "";
		int i = 1, cases = 1;

		try {

			query = " select distinct count(*) from  fl2d.rollover_stock_fl2d_cases_non_renew_brand where etin='"
					+ act.getEtn() + "' and seq='"+act.getSeq()+"' and unit_id=" + act.getFl2_fl2bId();

			con = ConnectionToDataBase.getConnection();
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();

			if (rs.next()) {
				i = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pst.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return i;
	}

	public String finalize(RollOverStock_fl2d_NonRenewedBrandAction act,
			RollOverStock_fl2d_NonRenewedBrandDT dt) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		// System.out.println("====RollOver_Stock_For_FL1_1A_20_21Action========");
		try {

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);

			String query = " update fl2d.rollover_stock_for_fl2d_non_renew_brand set deo_flg ='T' where "
					+ " brand_id='"
					+ dt.getBrand_id()
					+ "' and package_id='"
					+ dt.getPackage_id()
					+ "' "
					+ " and seq='"+dt.getSeq()+"'  and unit_id="
					+ act.getFl2_fl2bId();
			// "unit_id="+act.getFl2_fl2bId();
			System.out.println("=====update-----" + query);
			pstmt = conn.prepareStatement(query);
			saveStatus = pstmt.executeUpdate();

			if (saveStatus > 0) {

				conn.commit();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								" Sent To DEO Successfully.  !!! ",
								"  Sent To DEO Successfully.  !!!"));
				act.reset();
				// action.setFinalflg(true);

			} else {

				// action.reset();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
						Constants.NOT_SAVED);

			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							" RECORD NOT SAVED !!!!!!",
							"RECORD NOT SAVED !!!!!!"));
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
				// e.printStackTrace();
			}
		}
		return "";
	}

	/*
	 * @Author Date Purpose
	 * 
	 * Atul 20-05-2020 check in rollover process uploaded case is valid or not
	 */

	public String checkType(String casecode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String type = "";
		String sql =

		"select distinct  a.unit_type from distillery.brand_registration_19_20 a , distillery.packaging_details_19_20 b "
				+ " where a.brand_id=b.brand_id_fk and b.code_generate_through='"
				+ casecode.substring(0, 12) + "'";

		try {

			 System.out.println("=====sqllll ==="+sql);
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				type = rs.getString("unit_type");
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
		return type;
	}

	public String checkCasecodeValid(String casecode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String validNotValid = "";
		try {
			String type = checkType(casecode);
			System.out.println("type  fvghfghgf " + type);
			if (type.equals("B")) {
				validNotValid = getBrewaryCaseDetail_19_20(casecode, "BREWERY");
				System.out.println("brewary status" + validNotValid);
			} else if (type.equals("D")) {
				validNotValid = getDisteleryCaseDetail_19_20(casecode,
						"DISTILLERY");
				System.out.println("distillery status" + validNotValid);
			} else if (type.equals("BWFL")) {
				validNotValid = getBwflCaseDetail_19_20(casecode, "BWFL");
				System.out.println("bwfl" + validNotValid);
			}

			else if (type.equals("OtherUnit")) {
				validNotValid = getOther_19_20(casecode, "BWFL");
				System.out.println("other unit" + validNotValid);
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

		return validNotValid;

	}

	public String getBrewaryCaseDetail_19_20(String caseno, String type)
			throws SQLException {

		Connection conn = null;
		Connection conn1 = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String etin = caseno.substring(0, 12);
		String licence_type = etin.substring(3, 4);
		String casecode = caseno.substring(26, caseno.length());

		String sql = "";

		String gatepassno = "";
		String unitto_wholesale_dispatch_date = "";
		String unitname = "";
		String wholeseller_name = "";
		String shop_name = "";
		String wholesellergatpassno = "";
		String wholesale_to_shop_date = "";
		String wholeseller_id = "";
		String shop_id = "";
		String shop_type = "";
		String response = "";
		String plan_date = "";
		String validInvalid = "";

		try {

			SimpleDateFormat sdf5 = new SimpleDateFormat("yyMMdd");
			SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf5.parse(caseno.substring(16, 22));
			plan_date = sdf6.format(date);

		} catch (Exception e) {
			e.printStackTrace();
		}

		String brewary_fl3 = "SELECT plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,recv_id,ws_date,ws_gatepass,shop_id,shop_type, "
				+ " fl11_date, fl36gatepass, fl36_date, boxing_seq "
				+ " FROM bottling_unmapped.brewary_unmap_fl3 where etin='"
				+ etin
				+ "' and  casecode='"
				+ casecode
				+ "'  and date_plan='"
				+ plan_date
				+ "' union "
				+

				" SELECT plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,recv_id,ws_date,ws_gatepass,shop_id,shop_type, "
				+ " fl11_date, fl36gatepass, fl36_date, boxing_seq "
				+ " FROM bottling_unmapped.brewary_unmap_fl3_backup where etin='"
				+ etin
				+ "' and  casecode='"
				+ casecode
				+ "'  and date_plan='"
				+ plan_date + "' ";

		String brewary_fl3a = "SELECT plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,recv_id,ws_date,ws_gatepass,shop_id,shop_type, "
				+ " fl11_date, fl36gatepass, fl36_date, boxing_seq "
				+ " FROM bottling_unmapped.brewary_unmap_fl3a where etin='"
				+ etin
				+ "' and casecode='"
				+ casecode
				+ "'  and date_plan='"
				+ plan_date
				+ "' union  "
				+

				"  SELECT plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,recv_id,ws_date,ws_gatepass,shop_id,shop_type, "
				+ " fl11_date, fl36gatepass, fl36_date, boxing_seq "
				+ " FROM bottling_unmapped.brewary_unmap_fl3a_backup where etin='"
				+ etin
				+ "' and casecode='"
				+ casecode
				+ "'  and date_plan='"
				+ plan_date + "'";

		try {

			if ((type.equals("BREWERY"))
					&& (licence_type.equalsIgnoreCase("1"))) {
				sql = brewary_fl3;

			} else if ((type.equals("BREWERY"))
					&& (licence_type.equalsIgnoreCase("2"))) {
				sql = brewary_fl3a;
			}
			conn = ConnectionToDataBase.getConnection3();

			conn1 = ConnectionToDataBase.getConnection();

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				validInvalid = "VALID";
			} else {

				validInvalid = "INVALID";
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
				conn1.close();
			} catch (Exception e) {
				e.printStackTrace();

			}

		}
		return validInvalid;
	}

	public String getDisteleryCaseDetail_19_20(String caseno, String type) {

		Connection conn = null;
		Connection conn1 = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String etin = caseno.substring(0, 12);
		String licence_type = etin.substring(3, 4);
		String casecode = caseno.substring(26, caseno.length());

		String sql = "";
		String plan_date = "";
		String validInvalid = "";

		try {

			SimpleDateFormat sdf5 = new SimpleDateFormat("yyMMdd");
			SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf5.parse(caseno.substring(16, 22));
			plan_date = sdf6.format(date);

		} catch (Exception e) {

			e.printStackTrace();
		}

		String disliry_fl3 = "SELECT plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,recv_id,ws_date,ws_gatepass,shop_id,shop_type, "
				+ " fl11_date, fl36gatepass, fl36_date, boxing_seq "
				+ " FROM bottling_unmapped.disliry_unmap_fl3 where etin='"
				+ etin
				+ "' and casecode='"
				+ casecode
				+ "'  and date_plan='"
				+ plan_date
				+ "' union "
				+ "SELECT plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,recv_id,ws_date,ws_gatepass,shop_id,shop_type, "
				+ " fl11_date, fl36gatepass, fl36_date, boxing_seq "
				+ " FROM bottling_unmapped.disliry_unmap_fl3_backup where etin='"
				+ etin
				+ "' and casecode='"
				+ casecode
				+ "'  and date_plan='"
				+ plan_date + "'";

		String disliry_fl3a = "SELECT plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,recv_id,ws_date,ws_gatepass,shop_id,shop_type, "
				+ " fl11_date, fl36gatepass, fl36_date, boxing_seq "
				+ " FROM bottling_unmapped.disliry_unmap_fl3a where etin='"
				+ etin
				+ "' and casecode='"
				+ casecode
				+ "'  and date_plan='"
				+ plan_date
				+ "' union  "
				+

				" SELECT plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass,recv_id,ws_date,ws_gatepass,shop_id,shop_type, "
				+ " fl11_date, fl36gatepass, fl36_date, boxing_seq "
				+ " FROM bottling_unmapped.disliry_unmap_fl3a_backup where etin='"
				+ etin
				+ "' and casecode='"
				+ casecode
				+ "'  and date_plan='"
				+ plan_date + "'";

		String disliry_cl = "SELECT plan_id, fl11gatepass,recv_id,ws_date,ws_gatepass,shop_id,shop_type, "
				+ " fl11_date, fl36gatepass, fl36_date "
				+ " FROM bottling_unmapped.disliry_unmap_cl where etin='"
				+ etin
				+ "' and casecode='"
				+ casecode
				+ "'  and date_plan='"
				+ plan_date
				+ "' union  "
				+ "  SELECT plan_id, fl11gatepass,recv_id,ws_date,ws_gatepass,shop_id,shop_type, "
				+ " fl11_date, fl36gatepass, fl36_date "
				+ " FROM bottling_unmapped.disliry_unmap_cl_backup where etin='"
				+ etin
				+ "' and casecode='"
				+ casecode
				+ "'  and date_plan='"
				+ plan_date + "'";

		try {

			if ((type.equals("DISTILLERY")) && (licence_type.equals("1"))) {
				sql = disliry_fl3;

			} else if ((type.equals("DISTILLERY"))
					&& (licence_type.equals("2"))) {
				sql = disliry_fl3a;

			} else if ((type.equals("DISTILLERY"))
					&& (licence_type.equals("3"))) {
				sql = disliry_cl;

			}

			conn = ConnectionToDataBase.getConnection3();
			conn1 = ConnectionToDataBase.getConnection();

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				validInvalid = "VALID";
			} else {

				validInvalid = "INVALID";
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
				conn1.close();

			} catch (Exception e) {
				e.printStackTrace();

			}

		}
		return validInvalid;
	}

	public String getBwflCaseDetail_19_20(String caseno, String type) {

		Connection conn = null;
		Connection conn1 = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String etin = caseno.substring(0, 12);
		String licence_type = etin.substring(3, 4);
		String casecode = caseno.substring(26, caseno.length());

		String sql = "";
		String plan_date = "";
		String validInvalid = "";

		try {

			SimpleDateFormat sdf5 = new SimpleDateFormat("yyMMdd");
			SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf5.parse(caseno.substring(16, 22));
			plan_date = sdf6.format(date);

		} catch (Exception e) {

			e.printStackTrace();
		}
		String bwfl = " SELECT ws_date,recv_id,shop_type,shop_id, plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass, fl11_date, fl36gatepass, fl36_date, serial_no_start, serial_no_end "
				+ " FROM bottling_unmapped.bwfl where etin='"
				+ etin
				+ "'  and casecode='"
				+ casecode
				+ "'  and date_plan='"
				+ plan_date
				+ "' union "
				+ " SELECT ws_date,recv_id,shop_type,shop_id, plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass, fl11_date, fl36gatepass, fl36_date, serial_no_start, serial_no_end "
				+ " FROM bottling_unmapped.bwfl_backup where etin='"
				+ etin
				+ "'  and casecode='"
				+ casecode
				+ "'  and date_plan='"
				+ plan_date + "'";

		try {
			if (type.equals("BWFL")) {
				sql = bwfl;
			}

			conn = ConnectionToDataBase.getConnection3();
			conn1 = ConnectionToDataBase.getConnection();

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {

				validInvalid = "VALID";
			} else {

				validInvalid = "INVALID";
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
				conn1.close();

			} catch (Exception e) {
				e.printStackTrace();

			}

		}
		return validInvalid;

	}

	public String getOther_19_20(String caseno, String type) {

		Connection conn = null;
		Connection conn1 = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		String etin = caseno.substring(0, 12);
		String licence_type = etin.substring(3, 4);
		String casecode = caseno.substring(26, caseno.length());

		String sql = "";
		String plan_date = "";
		String validInvalid = "";

		try {

			SimpleDateFormat sdf5 = new SimpleDateFormat("yyMMdd");
			SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf5.parse(caseno.substring(16, 22));
			plan_date = sdf6.format(date);
System.out.println("----casecode====="+casecode);
		} catch (Exception e) {

			e.printStackTrace();
		}
		String bwfl = " SELECT ws_date,recv_id,shop_type,shop_id, plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass, fl11_date, fl36gatepass, fl36_date, serial_no_start, serial_no_end "
				+ " FROM bottling_unmapped.bwfl where etin='"
				+ etin
				+ "'  and casecode='"
				+ casecode
				+ "'  and date_plan='"
				+ plan_date
				+ "' union "
				+ " SELECT ws_date,recv_id,shop_type,shop_id, plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass, fl11_date, fl36gatepass, fl36_date, serial_no_start, serial_no_end "
				+ " FROM bottling_unmapped.bwfl_backup where etin='"
				+ etin
				+ "'  and casecode='"
				+ casecode
				+ "'  and date_plan='"
				+ plan_date + "'";

		String fl2d = " SELECT plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass, fl11_date, fl36gatepass,recv_id,ws_date,ws_gatepass,shop_id,shop_type, fl36_date, serial_no_start, serial_no_end "
				+ " FROM bottling_unmapped.fl2d where etin='"
				+ etin
				+ "'  and casecode= '"
				+ casecode
				+ "'  and date_plan='"
				+ plan_date
				+ "' union "
				+ " SELECT plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass, fl11_date, fl36gatepass,recv_id,ws_date,ws_gatepass,shop_id,shop_type, fl36_date, serial_no_start, serial_no_end "
				+ " FROM bottling_unmapped.fl2d_backup where etin='"
				+ etin
				+ "'  and casecode= '"
				+ casecode
				+ "'  and date_plan='"
				+ plan_date + "'";
System.out.println("====fl2d====="+fl2d);
		try {
			if (type.equals("BWFL")) {
				sql = bwfl;
			}

			conn = ConnectionToDataBase.getConnection3();
			conn1 = ConnectionToDataBase.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt1 = conn.prepareStatement(fl2d);
			rs1 = pstmt1.executeQuery();
			rs = pstmt.executeQuery();
			if (rs.next() || rs1.next()) {

				validInvalid = "VALID";
			} else {

				validInvalid = "INVALID";
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
				conn1.close();

			} catch (Exception e) {
				e.printStackTrace();

			}

		}
		return validInvalid;

	}
	
	public String getNew_Brand_Name(String etin)
	{
		String brand_name=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=
		   "select brand_name from distillery.brand_registration_20_21 a,distillery.packaging_details_20_21 b "+
		  "  where a.brand_id=b.brand_id_fk and b.code_generate_through='"+etin+"'";
		try{
			conn=ConnectionToDataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
					rs=pstmt.executeQuery();
					if(rs.next())
					{
						brand_name=rs.getString("brand_name")	;
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
		return brand_name;
	}
	
	public int getUnitId(String etin,String unitType)
	{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int id=0;
		String sql=
        " select unit_type,id from                                                                                               "+
		" (select distinct  a.unit_type,a.distillery_id as id                                                                    "+
		" from distillery.brand_registration_19_20 a ,                                                                           "+
		" distillery.packaging_details_19_20 b                                                                                   "+
		" where a.brand_id=b.brand_id_fk and b.code_generate_through='"+etin+"' and a.unit_type='"+unitType+"' "+
		" union                                                                                                                  "+
		" select distinct  a.unit_type,a.brewery_id as id                                                                        "+
		" from distillery.brand_registration_19_20 a ,                                                                           "+
		" distillery.packaging_details_19_20 b                                                                                   "+
		" where a.brand_id=b.brand_id_fk and b.code_generate_through='"+etin+"' and a.unit_type='"+unitType+"' "+
		" union                                                                                                                  "+
		" select distinct  a.unit_type,a.int_bwfl_id as id                                                                       "+
		" from distillery.brand_registration_19_20 a ,                                                                           "+
		" distillery.packaging_details_19_20 b                                                                                   "+
		" where a.brand_id=b.brand_id_fk and b.code_generate_through='"+etin+"' and a.unit_type='"+unitType+"' "+
		" union                                                                                                                  "+
		" select distinct  a.unit_type,a.int_fl2d_id as id                                                                       "+
		" from distillery.brand_registration_19_20 a ,                                                                           "+
		" distillery.packaging_details_19_20 b                                                                                   "+
		" where a.brand_id=b.brand_id_fk and b.code_generate_through='"+etin+"' and a.unit_type='"+unitType+"' "+")x where id!=0 ";
		
		System.out.println("===getUnitId==="+sql);
		
		
		
		
		try{
			conn=ConnectionToDataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				id=rs.getInt("id");
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
		return id;
	}
	
	public ArrayList getBrandName(RollOverStock_fl2d_NonRenewedBrandDT dt) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		RollOverStock_fl2d_NonRenewedBrandAction bof = (RollOverStock_fl2d_NonRenewedBrandAction) facesContext
				.getApplication().createValueBinding("#{rollOverStock_fl2d_NonRenewedBrandAction}")
				.getValue(facesContext);



		
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);
		String sql = "";
		
		String unit_type="OtherUnit";
		int id=0;
		try{
			///RollOverStock_fl2d_NonRenewedBrandDT dt= new RollOverStock_fl2d_NonRenewedBrandDT();
			
			unit_type=this.checkType(dt.getOld_etin());
			id=this.getUnitId(dt.getOld_etin(), unit_type);
			dt.setType_old(unit_type);
			///this.setType_old("IMPORTUNIT");
			dt.setUnit_id_old(String.valueOf(id));
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		 
		try {

		
				sql =  "	select distinct   a.unit_name,a.brand_name,b.code_generate_through,b.package_name          "+
						"	from distillery.brand_registration_20_21 a ,                                               "+
						"	distillery.packaging_details_20_21 b                                                       "+
						"	where a.brand_id=b.brand_id_fk and a.int_fl2d_id > 0 and  a.int_fl2d_id is not null and for_csd_civil='Civil' order by  brand_name        ";

				

			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			
				
				//ps.setInt(1, unit_id);
		//	item.setLabel(rs.getString("brand_name")+" "+rs.getString("package_name")+" "+rs.getString("code_generate_through"));
			//item.setValue(rs.getString("code_generate_through"));
		
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("brand_name")+" "+rs.getString("package_name")+" "+rs.getString("code_generate_through"));
				item.setValue(rs.getString("code_generate_through"));
				list.add(item);
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
		return list;
	}	
	
	

}
