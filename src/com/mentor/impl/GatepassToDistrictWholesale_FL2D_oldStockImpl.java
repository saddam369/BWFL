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
 
import com.mentor.action.GatepassToDistrictWholesale_FL2D_oldStockAction;
import com.mentor.action.ImflOldStockFL36;
import com.mentor.connectiondb.ConnectionToDataBase; 
import com.mentor.datatable.GatepassToDistrictWholesale_FL2D_oldStockDT;
import com.mentor.utility.Constants;
import com.mentor.utility.NewCommomImpl;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class GatepassToDistrictWholesale_FL2D_oldStockImpl {

	public boolean insertorupdateflg = false;

	public boolean isInsertorupdateflg() {
		return insertorupdateflg;
	}

	public void setInsertorupdateflg(boolean insertorupdateflg) {
		this.insertorupdateflg = insertorupdateflg;
	}

	public String fetchDataImpl(int i1, int i2, int i3, String s, int dis,
			double duty, double addduty, int bottle_avl, int box_avl,
			int dispatchbox) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		String query = "";

		try {
			con = ConnectionToDataBase.getConnection();
			sql = "SELECT int_package_ml FROM distillery.fl1_stock WHERE  int_package_ml="
					+ i1
					+ " AND  int_dist_id="
					+ i2
					+ "   AND  int_brand_id="
					+ i3 + "   AND vch_to_fl1_fl1a='" + s + "'";

			ps = con.prepareStatement(sql);
			//System.out.println("fetchDataImpl" + sql);
			rs = ps.executeQuery();
			if (rs.next()) {

				query = "UPDATE distillery.fl1_stock SET  int_dispatch=int_dispatch+"
						+ dis
						+ ", db_duty=db_duty+"
						+ duty
						+ ", db_add_duty=db_add_duty+"
						+ addduty
						+ ", int_bottle_avaliable="
						+ (bottle_avl - dis)
						+ ", no_boxes_avaliable="
						+ (box_avl - dispatchbox)
						+ " WHERE    int_package_ml="
						+ i1
						+ " AND  int_dist_id="
						+ i2
						+ "   AND  int_brand_id="
						+ i3 + "   AND vch_to_fl1_fl1a='" + s + "'";
				this.insertorupdateflg = true;
			//	System.out.println("update above============" + query);
			} else {
				query = " INSERT INTO distillery.fl1_stock(int_seq, int_dist_id, int_brand_id, vch_to_fl1_fl1a, "
						+ " int_package_ml, int_bottle_avaliable, int_dispatch, db_duty, db_add_duty, dt_date, "
						+ " box_size, no_boxes_avaliable, no_boxes_dispatch)VALUES "
						+ " ((select nextval('seq')),?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
				this.insertorupdateflg = false;
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

		return query;
	}

	public String saveMethodImpl(GatepassToDistrictWholesale_FL2D_oldStockAction act) {
		Connection con = null;
		PreparedStatement ps = null, ps2 = null, ps2D = null, ps3 = null, ps4 = null, ps5 = null;
		ResultSet rs = null;
		String sql = "";
		String sql2 = "";
		String sql3 = "";
		int tok1 = 0;
		int tok2 = 0;
		double duty = 0;
		double addduty = 0;
		String sql5 = "";

		int seq = this.maxId(this);
		String gatepass = String.valueOf(act.getDist_id()) + "-2017-18-FL36-"
				+ seq;

		int dutymaxId = this.getMaxIdDuty(this);

		try {
			int cases = 0;
			int totalBottles = 0;

			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);

		 	sql = " INSERT INTO fl2d.gatepass_to_districtwholesale_fl2d_oldStock("
					+ " int_fl2d_id,  dt_date, vch_from, vch_to, vch_from_lic_no, vch_to_lic_no, curr_date,"
					+ " int_max_id, licencee_id,vch_gatepass_no, vch_route_detail, vch_vehicle_no,vehicle_driver_name, "
					+ " vehicle_agency_name_adrs, licensee_name, licensee_adrs, district_1, district_2," +
					" district_3,valid_till,gross_weight, tier_weight, net_weight,licencee_district,tot_cal_duty )"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?, ?,?,?,?)";

		//	System.out.println("sql="+sql);
			ps = con.prepareStatement(sql);

			ps.setInt(1, act.getDist_id()); 
			ps.setDate(2, Utility.convertUtilDateToSQLDate(act.getDt_date()));
			ps.setString(3, act.getVch_from());
			ps.setString(4, act.getVch_to());
			ps.setString(5, act.getVch_from_lic_no());
			ps.setString(6, act.getVch_to_lic_no());
			ps.setDate(7, Utility.convertUtilDateToSQLDate(new Date()));
			ps.setInt(8, seq);
			ps.setDouble(9, act.getLicenceeid());
			ps.setString(10, gatepass);
			ps.setString(11, act.getRouteDtl());
			ps.setString(12, act.getVehicleNo());
			ps.setString(13, act.getVehicleDrvrName());
			ps.setString(14, act.getVehicleAgencyNmAdrs());
			ps.setString(15, act.getLicenceenm());
			ps.setString(16, act.getLicenceeadd());
			ps.setInt(17, act.getDistrict1());
			ps.setInt(18, act.getDistrict2());
			ps.setInt(19, act.getDistrict3());
			ps.setDate(20,
					Utility.convertUtilDateToSQLDate(act.getValidtilldt_date()));
			ps.setDouble(21, act.getGrossWeight());
			ps.setDouble(22, act.getTierWeight());
			ps.setDouble(23, act.getNetWeight());
			ps.setInt(24, act.getDistrictLic());

			ps.setDouble(25, act.getDb_total_value());
			// ps.setDouble(26, act.getDb_total_add_value());

			tok1 = ps.executeUpdate();

		//	System.out.println("first status----------" + tok1);

			if (tok1 > 0) {

				for (int i = 0; i < act.displaylist.size(); i++) {

					GatepassToDistrictWholesale_FL2D_oldStockDT dt = (GatepassToDistrictWholesale_FL2D_oldStockDT) act
							.getDisplaylist().get(i);

					if (dt.getDispatchbottls() > 0 && dt.getDispatchbox() > 0) {
						tok1 = 0;

						sql2 =  "INSERT INTO fl2d.fl2d_stock_trxn_oldStock( int_fl2d_id, vch_lic_no," +
								"dt, int_brand_id, int_pckg_id, avl_bottl, avl_box, breakage, " +
								"balance,vch_gatepass_no, size,vch_batch_no, mst_seq,dispatch_box," +
								"dispatch_bottle,seq_fl1,duty,cal_duty)	VALUES " +
								"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,(select nextval('seq')),?,?,?,?,?)";

						// , additional_duty, cal_additional_duty
						//System.out.println("sql2="+sql2);
						ps2 = con.prepareStatement(sql2);

						ps2.setInt(1, act.getDist_id());
						ps2.setString(2, act.getVch_to());
						ps2.setDate(3, Utility.convertUtilDateToSQLDate(act
								.getDt_date()));
						ps2.setInt(4, dt.getInt_brand_id());
						ps2.setInt(5, dt.getInt_pckg_id());
						ps2.setInt(6, dt.getInt_bottle_avaliable());
						ps2.setInt(7, dt.getBoxAvailable());
						ps2.setInt(8, dt.getBreakage());
						ps2.setDouble(9, dt.getBalance());
						ps2.setString(10, gatepass);
						// ps2.setInt(11, seq);
						ps2.setInt(11, dt.getSize());
						ps2.setString(12, dt.getBatchNo());
						ps2.setInt(13, dt.getDispatchbox());
						ps2.setInt(14, dt.getDispatchbottls());
						ps2.setInt(15, dt.getSeq());

						ps2.setDouble(16, dt.getDb_duty());
						ps2.setDouble(17, dt.getCalculated_duty());

						// ps2.setDouble(18, dt.getDb_add_duty());
						// ps2.setDouble(19, dt.getCalculated_add_duty());

						cases += dt.getDispatchbox();
						totalBottles += dt.getDispatchbottls();
						tok1 = ps2.executeUpdate();
					//	System.out.println("tok1="+tok1);
						if (tok1 > 0) {
							tok1 = 0;
					 		String updtQr = " UPDATE fl2d.fl11_fl2d_old_stock SET "
								 + " dispatch_bottels=COALESCE(dispatch_bottels ,0)+'" + (dt.getDispatchbottls()+dt.getBreakage())
									+ "',dispatch_boxes=COALESCE(dispatch_boxes ,0)+'" + dt.getDispatchbox()+ "' " 
									   + " "
									+ // , seq_fl1="+dt.getSeq()+" " +
									" WHERE fl2d_id='"
									+ act.getDist_id()
									+ "' AND "
									+ " brand_id='"
									+ dt.getInt_brand_id()
									+ "' AND pack_id='" + dt.getInt_pckg_id() + "'"; 
										//	"and  seq=" + dt.getSeq();
							ps3 = con.prepareStatement(updtQr);

							//System.out.println("update query--"+ updtQr);

							tok1 = ps3.executeUpdate();
						 }

					}
				}
			}

			sql3 = "INSERT INTO distillery.permit_register( "
					+ "int_id, int_distillery_id , date_crr_date, vch_duty_type, int_quantity, int_value, vch_description , int_fl2d_id) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			//System.out.println("sql3="+sql3);
			ps3 = con.prepareStatement(sql3);

			ps3.setInt(1, dutymaxId);
			ps3.setInt(2, 0);
			ps3.setDate(3, new java.sql.Date(System.currentTimeMillis()));
			ps3.setString(4, "FL2D EXP DUTY OLD STOCK");
			ps3.setDouble(5, totalBottles);
			ps3.setDouble(6, act.getDb_total_value());
			// pstmt.setInt(7, Integer.parseInt(action.getVatNo()));
			ps3.setString(7, "Duty for FL2D Old Stock Gatepass " + gatepass);
			ps3.setInt(8, act.getDist_id());

			tok2 = ps3.executeUpdate();
 
	 	if (tok1 > 0 && tok2 > 0) {
			 	con.setAutoCommit(true);

				act.setListFlagForPrint(true);
				ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,
						Constants.SAVED_SUCESSFULLY);

				 
				act.clearAll();
				//NewCommomImpl.sendEmail(to, sub, msg, from, password);
 
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

	/*
	 * public double fetchDuty(int int_brand_id, String vch_package_ml){
	 * Connection conn = null; PreparedStatement ps = null; ResultSet rs=null;
	 * String query = null; double d=0; try{
	 * 
	 * 
	 * query =
	 * "SELECT   db_duty FROM distillery.brand_registration WHERE brand_id="
	 * +int_brand_id+" AND  int_package_ml="+vch_package_ml;
	 * 
	 * 
	 * conn = ConnectionToDataBase.getConnection();
	 * ps=conn.prepareStatement(query);
	 * 
	 * System.out.println("666666666666"+query); rs=ps.executeQuery();
	 * while(rs.next()) { d=(rs.getDouble("db_duty")); } } catch(Exception e) {
	 * e.printStackTrace(); } finally { try {
	 * 
	 * if(conn!=null)conn.close(); if(ps!=null)ps.close();
	 * if(rs!=null)rs.close();
	 * 
	 * 
	 * } catch(Exception e) { e.printStackTrace(); } }
	 * 
	 * return d;
	 * 
	 * }
	 */

	public ArrayList displaylistImpl(GatepassToDistrictWholesale_FL2D_oldStockAction act, String val) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		// int i1=0,i2=0;  

		try {

			selQr = " SELECT distinct c.seq, a.package_name, a.package_id,a.permit, a.duty, " +
					" a.adduty, c.int_brand_id, b.brand_name, c.int_pack_id, " +
					" d.stock_bottels-coalesce(d.dispatch_bottels,0)  as avlbottle, " +
					" ((d.stock_boxes-coalesce(d.dispatch_boxes,0)) ) as " +
					" dispatchd_box, a.export_box_size FROM distillery.packaging_details a, " +
					" distillery.brand_registration b, fl2d.fl2d_old_stock c, fl2d.fl11_fl2d_old_stock d" +
					" WHERE c.int_fl2d_id=d.fl2d_id and c.int_brand_id=d.brand_id and " +
					" c.int_pack_id=d.pack_id and c.licence_no=d.licence_number and " +
					" a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.int_brand_id AND " +
					" a.package_id=c.int_pack_id AND b.brand_id=c.int_brand_id AND " +
					" c.int_fl2d_id='"+act.getDist_id()+"' AND c.vch_license_type='FL2D' AND" +
					" COALESCE(d.dispatch_bottels,0)<d.stock_bottels ";
			System.out.println("selQr="+selQr);
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(selQr);

			rs = ps.executeQuery();
			while (rs.next()) {
				GatepassToDistrictWholesale_FL2D_oldStockDT dt = new GatepassToDistrictWholesale_FL2D_oldStockDT();
				dt.setInt_brand_id(rs.getInt("int_brand_id"));
				dt.setInt_pckg_id(rs.getInt("package_id"));
				dt.setVch_brand(rs.getString("brand_name"));
				dt.setInt_bottle_avaliable(rs.getInt("avlbottle"));
				dt.setDesc(rs.getString("package_name"));
				dt.setSeq(rs.getInt("seq"));
				// dt.setSize(rs.getInt("box_size"));
				dt.setSize(rs.getInt("export_box_size"));

				dt.setBoxAvailable(rs.getInt("dispatchd_box"));

				// dt.setDb_duty(rs.getDouble("duty"));
				//dt.setDb_duty(rs.getDouble("permit"));

				//dt.setDb_add_duty(rs.getDouble("adduty"));

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

	// -----------list
	public ArrayList fromliclistImpl(GatepassToDistrictWholesale_FL2D_oldStockAction act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue(0);
		list.add(item);
		try {
			String query = "SELECT distinct vch_gatepass_no   from  "
					+ " distillery.gatepass_to_manufacturewholesale where   int_dist_id='"
					+ act.getDist_id() + "'  and vch_to= '" + act.getVch_from()
					+ "'";

			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);
			// ps.setDate(1,
			// Utility.convertUtilDateToSQLDate(act.getDt_date()));
			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();

				item.setValue(rs.getString("vch_gatepass_no"));
				item.setLabel(rs.getString("vch_gatepass_no"));

				list.add(item);

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

	public ArrayList toliclistImpl(GatepassToDistrictWholesale_FL2D_oldStockAction act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue(0);
		list.add(item);
		try {
			String query = "SELECT vch_licence_no FROM licence.licence_entery_fl3_fl1 WHERE int_distillery_id="
					+ act.getDist_id()
					+ " AND vch_licence_type='"
					+ act.getVch_to() + "' and vch_lic_unit_type='D'";
			;

			conn = ConnectionToDataBase.getConnection();

			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();

				// item.setValue(rs.getInt("int_state_id"));
				item.setValue(rs.getString("vch_licence_no"));
				item.setLabel(rs.getString("vch_licence_no"));

				list.add(item);

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

	public String licenceedet(GatepassToDistrictWholesale_FL2D_oldStockAction act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT distinct int_app_id,vch_firm_name,vch_applicant_name,vch_core_address" +
					" FROM licence.fl2_2b_2d WHERE vch_license_type='"+act.getVch_to()+"'  and vch_licence_no='"
					+ act.getVch_to_lic_no() + "'";
			;

			conn = ConnectionToDataBase.getConnection();

			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();
			 
			if (rs.next()) {
				act.setLicenceeid(rs.getInt("int_app_id"));
				act.setLicenceeadd(rs.getString("vch_core_address"));
				act.setLicenceenm(rs.getString("vch_firm_name"));
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
		return "";
	}

	public ArrayList toliclistImpl2a(GatepassToDistrictWholesale_FL2D_oldStockAction act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue(0);
		list.add(item);
		try {
			String query = " SELECT distinct vch_licence_no FROM licence.fl2_2b_2d WHERE vch_license_type='FL2'  "
					+ " AND vch_approval='A' and core_district_id="
					+ Integer.parseInt(act.getDistrictcode());

			conn = ConnectionToDataBase.getConnection();

			ps = conn.prepareStatement(query);

			//System.out.println("get license nmbr query===============" + query);
			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();

				// item.setValue(rs.getInt("int_state_id"));
				item.setValue(rs.getString("vch_licence_no"));
				item.setLabel(rs.getString("vch_licence_no"));

				list.add(item);

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

	public String getDetails(GatepassToDistrictWholesale_FL2D_oldStockAction ac) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			String queryList = " select  int_app_id, vch_firm_name  from licence.fl2_2b_2d where  vch_license_type='FL2D' and vch_mobile_no = '"
					+ ResourceUtil.getUserNameAllReq().trim() + "'  ";

			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ac.setName(rs.getString("vch_firm_name"));
				ac.setDist_id(rs.getInt("int_app_id"));
				// ac.setAddress(rs.getString("vch_firm_add"));

				ac.setVch_from(rs.getString("vch_firm_name"));

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

	// ===================select season id====================

	public ArrayList getSeasonDetails(GatepassToDistrictWholesale_FL2D_oldStockAction ac) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();

		String selQr = "";
		try {

			selQr = "SELECT * FROM public.mst_season where active='a'";
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(selQr);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setValue(rs.getString(1));
				item.setLabel(rs.getString(2) + "-" + rs.getString(3));
				ac.setSession(rs.getString(2) + "-" + rs.getString(3));
				list.add(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return list;
	}

	// =====================get max id sequence==============================

	public int maxId(GatepassToDistrictWholesale_FL2D_oldStockImpl impl) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(int_max_id) as id FROM fl2d.gatepass_to_districtwholesale_fl2d_oldStock ";
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

	/*
	 * public void printReport(GatepassToDistrictWholesale_FL2DAction action) {
	 * String mypath=Constants.JBOSS_SERVER_PATH+Constants.JBOSS_LINX_PATH;
	 * String
	 * relativePath=mypath+File.separator+"ExciseUp"+File.separator+"WholeSale"
	 * +File.separator+"jasper"; String
	 * relativePathpdf=mypath+File.separator+"ExciseUp"
	 * +File.separator+"WholeSale"+File.separator+"pdf"; JasperReport
	 * jasperReport = null; JasperPrint jasperPrint = null; PreparedStatement
	 * pst=null; Connection con=null; ResultSet rs=null; String
	 * reportQuery=null; try { con =ConnectionToDataBase.getConnection();
	 * reportQuery=
	 * 
	 * 
	 * "	SELECT a.vch_undertaking_name, a.vch_wrk_add, b.int_dist_id, b.vch_gatepass_no, b.dt_date, "
	 * +
	 * "	b.vch_route_detail, b.vch_vehicle_no, c.int_brand_id, c.size as box_size, c.balance as no_bottl,  "
	 * + "	d.brand_name, e.package_name,e.box_id,f.qnt_ml_detail as ml, "+
	 * "	g.vch_firm_name, g.vch_applicant_name, g.vch_core_address,c.vch_batch_no, "
	 * + "	(((c.balance)*f.qnt_ml_detail)/1000) as bl, "+
	 * "	(((((c.balance)*f.qnt_ml_detail)/1000)*48.2)/100) as al "+
	 * "	FROM public.dis_mst_pd1_pd2_lic a, distillery.gatepass_to_districtwholesale b,  "
	 * + "	distillery.fl2_stock_trxn c, distillery.brand_registration d,  "+
	 * "	distillery.packaging_details e, distillery.box_size_details f, licence.fl2_2b_2d g "
	 * +
	 * "	WHERE a.int_app_id_f=b.int_dist_id AND b.int_dist_id=c.int_dissleri_id AND b.vch_gatepass_no=c.vch_gatepass_no  "
	 * +
	 * "	and b.dt_date=c.dt AND c.int_brand_id=d.brand_id AND d.brand_id=e.brand_id_fk AND c.int_pckg_id=e.package_id "
	 * +
	 * "	AND e.box_id=f.box_id AND b.licencee_id=g.int_app_id AND b.int_dist_id=? AND dt_date=? "
	 * ;
	 * 
	 * System.out.println("report query-----------"+reportQuery);
	 * 
	 * 
	 * //
	 * System.out.println("--------- action date  ---------"+action.getPrintDate
	 * ());
	 * 
	 * 
	 * pst=con.prepareStatement(reportQuery); pst.setInt(1,
	 * action.getDist_id()); // pst.setDate(2,
	 * Utility.convertUtilDateToSQLDate(action.getDt_date())); pst.setDate(2,
	 * Utility.convertUtilDateToSQLDate(action.getPrintDate()));
	 * 
	 * 
	 * rs=pst.executeQuery(); if(rs.next()){ rs=pst.executeQuery();
	 * 
	 * Map parameters = new HashMap(); parameters.put("REPORT_CONNECTION", con);
	 * parameters.put("SUBREPORT_DIR", relativePath+File.separator);
	 * parameters.put("image", relativePath+File.separator);
	 * 
	 * JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);
	 * 
	 * jasperReport = (JasperReport)
	 * JRLoader.loadObject(relativePath+File.separator
	 * +"GatepassDistrictWholesale.jasper");
	 * 
	 * JasperPrint print = JasperFillManager.fillReport(jasperReport,parameters,
	 * jrRs); Random rand = new Random(); int n = rand.nextInt(250) + 1;
	 * 
	 * JasperExportManager.exportReportToPdfFile(print,relativePathpdf+File.
	 * separator+"GatepassDistrictWholesale"+n+".pdf");
	 * action.setPdfname("GatepassDistrictWholesale"+n+".pdf");
	 * action.setPrintFlag(true);
	 * 
	 * }else{FacesContext.getCurrentInstance().addMessage(null,new
	 * FacesMessage("No Data Found", "No Data Found"));
	 * action.setPrintFlag(false); }
	 * 
	 * 
	 * } catch (JRException e) { e.printStackTrace(); } catch (Exception e) {
	 * e.printStackTrace(); } finally { try { if(rs!=null)rs.close();
	 * if(con!=null)con.close(); } catch(SQLException e) { e.printStackTrace();
	 * } }
	 * 
	 * 
	 * }
	 */

	// ------------------------------ PRINT
	// ---------------------------------------

	public boolean printReport(GatepassToDistrictWholesale_FL2D_oldStockAction action) {
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

			/*
			 * if(dt.getVch_from().equalsIgnoreCase("FL1A")){ reportQuery=
			 * "	SELECT a.vch_undertaking_name,m.vch_unit_name as nm, b.vch_gatepass_no,b.vehicle_driver_name,a.vch_wrk_add,  b.vch_from, b.vch_to, b.vch_from_lic_no, b.vch_to_lic_no, "
			 * +
			 * "b.curr_date, b.licencee_id, b.vch_route_detail, b.vch_vehicle_no, b.valid_till,b.vehicle_agency_name_adrs,"
			 * + " b.licensee_name, b.licensee_adrs  ,c.dispatch_box , "+
			 * "	b.valid_till, c.int_brand_id, c.size as box_size, c.dispatch_bottle as no_bottl,  "
			 * +
			 * "	d.brand_name, e.package_name,e.box_id,f.qnt_ml_detail as ml, "+
			 * "	 b.vehicle_agency_name_adrs as vch_firm_name,  c.vch_batch_no,"
			 * + "	(((c.dispatch_bottle)*f.qnt_ml_detail)/1000) as bl, "+
			 * "	((((c.dispatch_bottle*f.qnt_ml_detail)/1000)*42.8)/100) as al "
			 * +
			 * "	FROM public.dis_mst_pd1_pd2_lic a, distillery.gatepass_to_districtwholesale b left outer join licence.fl3a_fl1a m  "
			 * +
			 * "                                 on  m.vch_licence_type='FL1A'  and  vch_license_fl1a=m.vch_to_lic_no  ,"
			 * +
			 * "	distillery.fl2_stock_trxn c, distillery.brand_registration d,  "
			 * +
			 * "	distillery.packaging_details e, distillery.box_size_details f "
			 * +
			 * "	WHERE a.int_app_id_f=b.int_dist_id AND b.int_dist_id=c.int_dissleri_id AND b.vch_gatepass_no=c.vch_gatepass_no  "
			 * +
			 * "	and b.dt_date=c.dt AND c.int_brand_id=d.brand_id AND d.brand_id=e.brand_id_fk AND c.int_pckg_id=e.package_id "
			 * +
			 * "	AND e.box_id=f.box_id  AND b.brewery_id=?  and f.qnt_ml_detail=e.quantity and b.vch_gatepass_no=?"
			 * ;
			 * 
			 * } else {
			 */
			reportQuery =

			/*
			 * "	SELECT a.vch_undertaking_name, b.vch_gatepass_no,b.vehicle_driver_name,a.vch_wrk_add,  b.vch_from, b.vch_to, b.vch_from_lic_no, b.vch_to_lic_no, "
			 * +
			 * "b.curr_date, b.licencee_id, b.vch_route_detail, b.vch_vehicle_no, b.valid_till,b.vehicle_agency_name_adrs,"
			 * +
			 * " b.licensee_name, b.licensee_adrs  ,c.dispatch_box ,b.gross_weight, b.tier_weight, b.net_weight, "
			 * +
			 * "	b.valid_till, c.int_brand_id, c.size as box_size, c.dispatch_bottle as no_bottl,  "
			 * +
			 * "	d.brand_name, e.package_name,e.box_id,f.qnt_ml_detail as ml, "+
			 * "	 b.vehicle_agency_name_adrs as vch_firm_name,  c.vch_batch_no,"
			 * + "	(((c.dispatch_bottle)*f.qnt_ml_detail)/1000) as bl, "+
			 * "	((((c.dispatch_bottle*f.qnt_ml_detail)/1000)*42.8)/100) as al "
			 * +
			 * "	FROM public.dis_mst_pd1_pd2_lic a, distillery.gatepass_to_districtwholesale b,  "
			 * +
			 * "	distillery.fl2_stock_trxn c, distillery.brand_registration d,  "
			 * +
			 * "	distillery.packaging_details e, distillery.box_size_details f "
			 * +
			 * "	WHERE a.int_app_id_f=b.int_dist_id AND b.int_dist_id=c.int_dissleri_id AND b.vch_gatepass_no=c.vch_gatepass_no  "
			 * +
			 * "	and b.dt_date=c.dt AND c.int_brand_id=d.brand_id AND d.brand_id=e.brand_id_fk AND c.int_pckg_id=e.package_id "
			 * +
			 * "	AND e.box_id=f.box_id  AND b.int_dist_id=?  and f.qnt_ml_detail=e.quantity and b.vch_gatepass_no=?"
			 * ;
			 */

			"	SELECT b.dt_date,a.vch_firm_name,b.tot_cal_duty, b.vch_gatepass_no,b.vehicle_driver_name,b.licensee_adrs as vch_firm_add,  b.vch_from, b.vch_to, b.vch_from_lic_no, b.vch_to_lic_no,  "
					+ "	b.curr_date, b.licencee_id, b.vch_route_detail, b.vch_vehicle_no, b.valid_till,b.vehicle_agency_name_adrs,  "
					+ "	 b.licensee_name, b.licensee_adrs  ,c.dispatch_box ,b.gross_weight, b.tier_weight, b.net_weight,   "
					+ "		b.valid_till, c.int_brand_id, d.strength,c.size as box_size, c.dispatch_bottle as no_bottl,   "
					+ "		d.brand_name, e.package_name,e.box_id,e.quantity as ml,   "
					+ "		 b.vehicle_agency_name_adrs as vch_firm_name,  c.vch_batch_no,  "
					+ "		(((c.dispatch_bottle)*e.quantity)/1000) as bl,   "
					+ "		((((c.dispatch_bottle*e.quantity)/1000)*42.8)/100) as al   "
					+ "		FROM licence.fl2_2b_2d  a,   "
					+ "        fl2d.gatepass_to_districtwholesale_fl2d_oldStock b,    "
					+ "		fl2d.fl2d_stock_trxn_oldStock c, distillery.brand_registration d,    "
					+ "		distillery.packaging_details e  "
					+ "		WHERE a.int_app_id=b.int_fl2d_id   "
					+ "        AND b.int_fl2d_id=c.int_fl2d_id   "
					+ "        AND b.vch_gatepass_no=c.vch_gatepass_no    "
					+ "		and b.dt_date=c.dt   "
					+ "        AND c.int_brand_id=d.brand_id   "
					+ "        AND d.brand_id=e.brand_id_fk   "
					+ "        AND c.int_pckg_id=e.package_id   "
					+ "		   "
					+ "        AND b.int_fl2d_id=?   "
					+ "        "
					+ "        and b.vch_gatepass_no=? ";

			// }

			pst = con.prepareStatement(reportQuery);

			pst.setInt(1, action.getDist_id());
			pst.setString(2, action.getPrintGatePassNo().trim());

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
						+ "GatepassDistrictWholesaleFL2D1718.jasper");

				JasperPrint print = JasperFillManager.fillReport(jasperReport,
						parameters, jrRs);
				Random rand = new Random();
				int n = rand.nextInt(250) + 1;

				JasperExportManager.exportReportToPdfFile(
						print,
						relativePathpdf + File.separator
								+ "GatepassDistrictWholesaleFL2D1718"
								+ action.getDist_id() + n + ".pdf");

				GatepassToDistrictWholesale_FL2D_oldStockDT dt = new GatepassToDistrictWholesale_FL2D_oldStockDT();
				dt.setPdfName("GatepassDistrictWholesaleFL2D1718"
						+ action.getDist_id() + n + ".pdf");
				action.setPdfname("GatepassDistrictWholesaleFL2D1718"
						+ action.getDist_id() + n + ".pdf");
				dt.setPrintFlag(true);
				printFlag = true;

				printFlag = true;
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("No Data Found", "No Data Found"));
				GatepassToDistrictWholesale_FL2D_oldStockDT dt1 = new GatepassToDistrictWholesale_FL2D_oldStockDT();
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

	// ---------------------------------------------------------------

	public ArrayList getGatePassWholeSaleListManufacture(
			GatepassToDistrictWholesale_FL2D_oldStockAction action) {
		ArrayList list = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = " SELECT int_fl2d_id,    "
				+ " dt_date, vch_from, vch_to, vch_from_lic_no, vch_to_lic_no,  curr_date, "
				+ " int_max_id, vch_gatepass_no, tot_cal_duty, COALESCE(vch_finalize,'N') as vch_finalize FROM "
				+ " fl2d.gatepass_to_districtwholesale_fl2d_oldStock where int_fl2d_id="
				+ action.getDist_id() + " order by dt_date desc";
		try {
			list = new ArrayList();
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			int i = 1;
			while (rs.next()) {
				GatepassToDistrictWholesale_FL2D_oldStockDT dt = new GatepassToDistrictWholesale_FL2D_oldStockDT();
				dt.setSno(i);
				dt.setInt_dist_id(rs.getInt("int_fl2d_id"));

				dt.setDt_date(rs.getDate("dt_date"));
				dt.setVch_from(rs.getString("vch_from"));
				dt.setVch_to(rs.getString("vch_to"));
				dt.setVch_from_lic_no(rs.getString("vch_from_lic_no"));
				dt.setVch_to_lic_no(rs.getString("vch_to_lic_no"));
				dt.setGatepassNo(rs.getString("vch_gatepass_no"));
				dt.setCurr_date(rs.getDate("curr_date"));
				dt.setTotalDuty(rs.getDouble("tot_cal_duty"));
				dt.setInt_max_id(rs.getInt("int_max_id"));
				if (rs.getString("vch_finalize").equalsIgnoreCase("F")) {
					dt.setFinalizePrint(true);
					dt.setPrintDraft(false);
				} else {
					dt.setFinalizePrint(false);
					dt.setPrintDraft(true);
				}

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

	public ArrayList getDistList() {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue(0);
		list.add(item);
		try {
			String query = "SELECT DistrictID, Description FROM district order by Description";

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);
			// pstmt.setInt(1,id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				item = new SelectItem();

				item.setValue(rs.getString("DistrictID"));
				item.setLabel(rs.getString("Description"));

				list.add(item);

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
		return list;
	}

	public String getEmailDetails(GatepassToDistrictWholesale_FL2D_oldStockAction ac) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String queryList = "";
		try {
			con = ConnectionToDataBase.getConnection();

			queryList = " SELECT a.officer_email "
					+ " FROM public.district a, fl2d.gatepass_to_districtwholesale_fl2d_oldStock b "
					+ " WHERE a.districtid=b.licencee_district and  b.licencee_district="
					+ ac.getDistrictLic() + " ";

			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ac.setOfficrEmail(rs.getString("officer_email"));
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

	// --------------------------------

	public int getMaxIdDuty(GatepassToDistrictWholesale_FL2D_oldStockImpl impl) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT max(int_id) as id from distillery.permit_register ";
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

	// ============================code for
	// uploader===============================

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

	public void saveExcelData(GatepassToDistrictWholesale_FL2D_oldStockAction action) {
		String gatepass = "";
		int status = 0, flag = 1, excelcase = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
	//	System.out.println("filename and path" + action.getExcelFilename() + "---------patj" + action.getExcelFilepath());
		FileInputStream fileInputStream = null;
		XSSFWorkbook workbook = null;
		try {
			String sql = "INSERT INTO fl2d.fl36_dispatch_casecode_fl2d(vch_gatepass_no, vch_casecode)VALUES (?, ?) ";

			conn = ConnectionToDataBase.getConnection();

			pstmt = conn.prepareStatement(sql);
			fileInputStream = new FileInputStream(action.getExcelFilepath());

			workbook = new XSSFWorkbook(fileInputStream);

			XSSFSheet worksheet = workbook.getSheet("Sheet1");
			int i = 0, j = 0;
			do {
				
			//	System.out.println("-----------gate pass no----------------" + action.getScanGatePassNo());

				String casecode = "";
				XSSFRow row1 = worksheet.getRow(j);

				XSSFCell cellA1 = row1.getCell((short) 0);

				String cellval = getCellValue(cellA1);

		//		System.out.println("break.............break" + cellval);
				if ((cellval == null) || (cellval.length() == 0)
						|| (cellval.equals("0.0"))) {
				//	System.out.println("break.............break");
					break;
				} else {

					if (j == 0) {
						cellA1 = row1.getCell((short) 0);
						gatepass = getCellValue(cellA1);
						if (action.getScanGatePassNo().trim()
								.equalsIgnoreCase(gatepass.trim()))

						{

					//		System.out.println("-----------gate pass no----------------" + gatepass);
							// pstmt.setString(1, gatepass);
							// pstmt.setString(2, casecode);
							// status= pstmt.executeUpdate();
							// excelcase++;
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
			System.out.println("errer" + e.getMessage());
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
								"File Upload successfully!!",
								"File Upload successfully!!"));

			} else {

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"File Not Upload!!", "File Not Upload!!"));
			}
		} else {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Kindly enter the same gatepass number!!",
							"Kindly enter the same gatepass number!!"));
		}

	}

	// ==========================get excel data from temporary
	// table============================

	public ArrayList getExcelData(GatepassToDistrictWholesale_FL2D_oldStockAction action) {

		ArrayList list = new ArrayList();

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
	     int boxingCount=0;
	        int listSize=0;
		String query = " SELECT vch_gatepass_no, vch_casecode FROM fl2d.fl36_dispatch_casecode_fl2d "
				+ " WHERE vch_gatepass_no='"
				+ action.getScanGatePassNo()
				+ "' ";

		try {
			con = ConnectionToDataBase.getConnection();
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				GatepassToDistrictWholesale_FL2D_oldStockDT dt = new GatepassToDistrictWholesale_FL2D_oldStockDT();

				dt.setGatepass(rs.getString("vch_gatepass_no"));
				dt.setCasecode(rs.getString("vch_casecode"));
				boolean flag=getCascodeMatch(rs.getString("vch_casecode").substring(29,
						rs.getString("vch_casecode").length()),rs.getString("vch_casecode").substring(2, 15),action);
				if(flag)
				{
					++listSize;
					
		        dt.setCascodeMatching("Casecode Accepted");
				}
				else
				{
					++boxingCount;
					dt.setCascodeMatching("Casecode Rejected");
				}
				list.add(dt);
				if(boxingCount!=0 || listSize<=0 )
				{ 
				
					action.setBottlingNotDoneFlag(true);
				}else{
					action.setBottlingNotDoneFlag(false);	
				}
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

		return list;

	}
	public boolean getCascodeMatch(String casecode,String etin ,GatepassToDistrictWholesale_FL2D_oldStockAction act)
	{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean flag=false;
		String sql="";
		
		 	sql = "select * from bottling_unmapped.fl2d WHERE casecode=? and etin=? and fl36_date is null ";
			 
		
	try{
		conn=ConnectionToDataBase.getConnection3();
		pstmt=conn.prepareStatement(sql);
		 pstmt.setString(1,casecode.trim());
		pstmt.setString(2, etin.trim());
		 
		rs=pstmt.executeQuery();
		if(rs.next())
		{
			flag=true;
		}
		
	}
	catch(Exception e)
	{
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
	// ---------------------count casecode in
	// excel------------------------------

	public int excelCases(GatepassToDistrictWholesale_FL2D_oldStockAction act) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			String query = " SELECT count(*) as dispatchd_box FROM fl2d.fl36_dispatch_casecode_fl2d "
					+ " WHERE vch_gatepass_no='"
					+ act.getScanGatePassNo().trim() + "'";
			//System.out.println("query-="+query);
			pstmt = con.prepareStatement(query);

			//System.out.println("query--------recieve------------" + query);

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

	public int recieveCases(GatepassToDistrictWholesale_FL2D_oldStockAction act) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			String query = " SELECT SUM(dispatch_box) as dispatchd_box FROM fl2d.fl2d_stock_trxn_oldStock "
					+ " WHERE vch_gatepass_no='"
					+ act.getScanGatePassNo().trim() + "'  ";

			pstmt = con.prepareStatement(query);
		//	System.out.println("query----------database--------" + query);
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

	// =======================update on finalize===========================

	public boolean updateDispatch(GatepassToDistrictWholesale_FL2D_oldStockAction act) {

		int save = 0;
		int j = 0;
		boolean val = false;
		PreparedStatement ps = null;
		Connection con = null;
		Connection con1 = null;
		String query = "";

		//query = " UPDATE bottling_unmapped.fl2d SET fl36gatepass=?, "
		//		+ " fl36_date=? WHERE casecode::numeric=?  ";

		try {
			con = ConnectionToDataBase.getConnection();
			con1 = ConnectionToDataBase.getConnection3();
			con.setAutoCommit(false);
			con1.setAutoCommit(false);
			for (int i = 0; i < act.getGetVal().size(); i++) {
				GatepassToDistrictWholesale_FL2D_oldStockDT dt = (GatepassToDistrictWholesale_FL2D_oldStockDT) act.getGetVal().get(i);
			/*	query ="insert into bottling_unmapped.fl2d ( " +
						" bottle_code,fl11gatepass,fl36gatepass , plan_id,date_plan,etin,fl36_date,casecode) values " +
						"('0','0','"+dt.getGatepass().trim()+"',0,'"+Utility.convertUtilDateToSQLDate(new Date())
						+"','"+dt.getCasecode().substring(2,15)+"','"+
						Utility.convertUtilDateToSQLDate(new Date())+"','"+
						dt.getCasecode().substring(29,dt.getCasecode().length())+"') " +
						" ON CONFLICT (casecode) DO UPDATE SET fl36gatepass='"+dt.getGatepass().trim()
						+"', fl36_date='"+Utility.convertUtilDateToSQLDate(new Date())+"'";*/
			
				 query = " UPDATE bottling_unmapped.fl2d SET fl36gatepass='"+ dt.getGatepass().trim()+ "', fl36_date='"+Utility.convertUtilDateToSQLDate(new Date())+"' " +
					 		" WHERE casecode='"+ Long.parseLong(dt.getCasecode().trim().substring(29,
									dt.getCasecode().trim().length()))+ "'    ";
				ps = con1.prepareStatement(query);
				 
				//System.out.println("query="+query);
				 
				j = ps.executeUpdate();
				if (j == 0) {
				//	System.out.println("1++");
					  
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(dt.getCasecode().substring(29,
									dt.getCasecode().length())
									+ " Casecode not found! ", dt.getCasecode()
									.substring(29, dt.getCasecode().length())
									+ " Casecode not found! "));

				}
				save += j;
				

			 

			} //System.out.println("save==="+save);
			if (act.getGetVal().size() == save) {
				save = 0;

				String updtQr = " UPDATE fl2d.gatepass_to_districtwholesale_fl2d_oldStock SET vch_finalize='F' "
						+ " WHERE vch_gatepass_no='"
						+ act.getScanGatePassNo().trim()
						+ "' ";
				//System.out.println("updtQr-="+updtQr);
				ps = con.prepareStatement(updtQr);
 
				save = ps.executeUpdate();
 

				String query1 = " DELETE FROM fl2d.fl36_dispatch_casecode_fl2d WHERE vch_gatepass_no='"
						+ act.getScanGatePassNo().trim() + "'  ";
				
				//System.out.println("query1-="+query1);
				ps=con.prepareStatement(query1);				
				ps.executeUpdate();
				act.clearAll();
				

			 
				

			} else {
				save = 0;
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

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();

				if (con != null)
					con.close();
				con1.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return val;

	}
	
	//===========================print draft report====================================
	
	
	public boolean printDraftReport(GatepassToDistrictWholesale_FL2D_oldStockAction action)
	{
	 
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
		boolean printFlag1 = false;

		try {
			con = ConnectionToDataBase.getConnection();

			
			reportQuery =			

			"	SELECT b.dt_date,a.vch_firm_name,b.tot_cal_duty, b.vch_gatepass_no,b.vehicle_driver_name,b.licensee_adrs as vch_firm_add,  b.vch_from, b.vch_to, b.vch_from_lic_no, b.vch_to_lic_no,  "
					+ "	b.curr_date, b.licencee_id, b.vch_route_detail, b.vch_vehicle_no, b.valid_till,b.vehicle_agency_name_adrs,  "
					+ "	 b.licensee_name, b.licensee_adrs  ,c.dispatch_box ,b.gross_weight, b.tier_weight, b.net_weight,   "
					+ "		b.valid_till, c.int_brand_id, d.strength,c.size as box_size, c.dispatch_bottle as no_bottl,   "
					+ "		d.brand_name, e.package_name,e.box_id,e.quantity as ml,   "
					+ "		 b.vehicle_agency_name_adrs as vch_firm_name,  c.vch_batch_no,  "
					+ "		(((c.dispatch_bottle)*e.quantity)/1000) as bl,   "
					+ "		((((c.dispatch_bottle*e.quantity)/1000)*42.8)/100) as al   "
					+ "		FROM licence.fl2_2b_2d  a,   "
					+ "        fl2d.gatepass_to_districtwholesale_fl2d_oldStock b,    "
					+ "		fl2d.fl2d_stock_trxn_oldStock c, distillery.brand_registration d,    "
					+ "		distillery.packaging_details e  "
					+ "		WHERE a.int_app_id=b.int_fl2d_id   "
					+ "        AND b.int_fl2d_id=c.int_fl2d_id   "
					+ "        AND b.vch_gatepass_no=c.vch_gatepass_no    "
					+ "		and b.dt_date=c.dt   "
					+ "        AND c.int_brand_id=d.brand_id   "
					+ "        AND d.brand_id=e.brand_id_fk   "
					+ "        AND c.int_pckg_id=e.package_id   "
					+ "		   "
					+ "        AND b.int_fl2d_id=?   "
					+ "        "
					+ "        and b.vch_gatepass_no=? ";
			

			pst = con.prepareStatement(reportQuery);
			//System.out.println("reportQuery="+reportQuery);
			pst.setInt(1, action.getDist_id());
			pst.setString(2, action.getPrintGatePassNo().trim());

			

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
						+ "GatepassDistrictWholesaleFL2D_Draft1718.jasper");

				JasperPrint print = JasperFillManager.fillReport(jasperReport,
						parameters, jrRs);
				Random rand = new Random();
				int n = rand.nextInt(250) + 1;

				JasperExportManager.exportReportToPdfFile(
						print,
						relativePathpdf + File.separator
								+ "GatepassDistrictWholesaleFL2D_Draft1718"
								+ action.getDist_id() + n + ".pdf");

				GatepassToDistrictWholesale_FL2D_oldStockDT dt = new GatepassToDistrictWholesale_FL2D_oldStockDT();
				dt.setDraftpdfName("GatepassDistrictWholesaleFL2D_Draft1718"
						+ action.getDist_id() + n + ".pdf");
				action.setDraftpdfname("GatepassDistrictWholesaleFL2D_Draft1718"
						+ action.getDist_id() + n + ".pdf");
				dt.setDraftprintFlag(true);
				printFlag1 = true;
				
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("No Data Found", "No Data Found"));
				GatepassToDistrictWholesale_FL2D_oldStockDT dt1 = new GatepassToDistrictWholesale_FL2D_oldStockDT();
				//action.setPrintFlag(false);
				printFlag1 = false;
				dt1.setDraftprintFlag(false);

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

		return printFlag1;
	
	}
	
	
	public void deleteData(GatepassToDistrictWholesale_FL2D_oldStockAction act)
	{

		Connection con = null;
		PreparedStatement stmt = null;

		String query = " DELETE FROM fl2d.fl36_dispatch_casecode_fl2d WHERE vch_gatepass_no='"
				+ act.getScanGatePassNo().trim() + "'  ";
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
	
	
	
	
	// ----------------------------code for
		// cancelgatepass------------------------------

		// =====================get max id sequence==============================

		public int seqCancel() {

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = " SELECT max(seq) as id FROM fl2d.duty_cancellation_fl2d ";
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

		// -------------------------cancel
		// gatepass----------------------------------

		public String cancelGatepassImpl(GatepassToDistrictWholesale_FL2D_oldStockAction act) {

			Connection con = null;
			PreparedStatement ps = null, ps2 = null, ps3 = null, ps4 = null, ps5 = null;
			PreparedStatement pstmt = null;

			ResultSet rs = null;
			ResultSet rs1 = null;
			String sql = "";
			String sql2 = "";
			String sql3 = "";
			String sql4 = "";
			int tok1 = 0;
			int duty = 0;int bot=0;

			int seq = this.seqCancel();

			try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

				con = ConnectionToDataBase.getConnection();
				con.setAutoCommit(false);

				String gatepass = "";
				gatepass = act.getPrintGatePassNo().trim();

				
				String selQr = 	" SELECT a.int_fl2d_id, a.dt_date, a.vch_gatepass_no, b.seq_fl1,a.tot_cal_duty, " +
								" b.int_brand_id, b.int_pckg_id, b.dispatch_bottle,b.dispatch_box   " +
								" FROM fl2d.gatepass_to_districtwholesale_fl2d_oldStock a, fl2d.fl2d_stock_trxn_oldStock b  " +
								" WHERE a.int_fl2d_id=b.int_fl2d_id AND a.vch_gatepass_no=b.vch_gatepass_no " +
								" AND a.dt_date=b.dt AND a.int_fl2d_id='"+act.getDist_id()+"' " +
								" AND  a.vch_gatepass_no='"+ gatepass.trim() +"' ";
							

				pstmt = con.prepareStatement(selQr);

				System.out.println("selQr------------" + selQr);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					//System.out.println("come into rssss------------");
					GatepassToDistrictWholesale_FL2D_oldStockDT dt = new GatepassToDistrictWholesale_FL2D_oldStockDT();

					dt.setSeqDt(rs.getInt("seq_fl1"));
					dt.setBrandIdDt(rs.getInt("int_brand_id"));
					dt.setPckgIdDt(rs.getInt("int_pckg_id"));
					dt.setDispatcBotlsDt(rs.getInt("dispatch_bottle"));
					duty=rs.getInt("tot_cal_duty");
					 bot= (dt.getDispatcBotlsDt()+dt.getBreakage());
					String updtQr = " UPDATE fl2d.fl11_fl2d_old_stock SET "
							 + " dispatch_bottels=COALESCE(dispatch_bottels ,0)-'" + (dt.getDispatcBotlsDt())
								+ "',dispatch_boxes=COALESCE(dispatch_boxes ,0)-'" + rs.getInt("dispatch_box")+ "' " 
								   + " "
								+ // , seq_fl1="+dt.getSeq()+" " +
								" WHERE fl2d_id='"
								+ act.getDist_id()
								+ "' AND "
								+ " brand_id='"
								+ dt.getBrandIdDt()
								+ "' AND pack_id='" + dt.getPckgIdDt()+ "'";
					
					ps5 = con.prepareStatement(updtQr);
				 
					tok1 = ps5.executeUpdate();

					 

				}
			 

				if (tok1 > 0) {
					tok1 = 0;

					sql2 = 	" DELETE FROM fl2d.gatepass_to_districtwholesale_fl2d_oldStock "
							+ " WHERE vch_gatepass_no='" + gatepass
							+ "' AND int_fl2d_id='" + act.getDist_id() + "' ";

					ps2 = con.prepareStatement(sql2);

					tok1 = ps2.executeUpdate();
					System.out.println("third status----------" + tok1);
				}

				if (tok1 > 0) {
					tok1 = 0;
					int dutymaxId = this.getMaxIdDuty(this);
					sql3 = 	" DELETE FROM fl2d.fl2d_stock_trxn_oldStock "
							+ " WHERE vch_gatepass_no='" + gatepass
							+ "' AND int_fl2d_id='" + act.getDist_id() + "' ";

					ps3 = con.prepareStatement(sql3);
					tok1 = ps3.executeUpdate();

					sql3 = "INSERT INTO distillery.permit_register( "
							+ "int_id, int_distillery_id , date_crr_date, vch_duty_type, int_quantity, int_value, vch_description , int_fl2d_id) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
					//System.out.println("sql3="+sql3);
					ps3 = con.prepareStatement(sql3);

					ps3.setInt(1, dutymaxId);
					ps3.setInt(2, 0);
					ps3.setDate(3, new java.sql.Date(System.currentTimeMillis()));
					ps3.setString(4, "FL2D EXP DUTY OLD STOCK CANCELLED");
					ps3.setDouble(5, bot);
					ps3.setDouble(6, -duty);
					// pstmt.setInt(7, Integer.parseInt(action.getVatNo()));
					ps3.setString(7, "Duty for FL2D Old Stock Gatepass " + gatepass);
					ps3.setInt(8, act.getDist_id());

					 ps3.executeUpdate();
				}
				
				if (tok1 > 0) {
					

					sql4 = 	" DELETE FROM fl2d.fl36_dispatch_casecode_fl2d WHERE vch_gatepass_no='"+gatepass+"' ";

					ps3 = con.prepareStatement(sql4);
					ps3.executeUpdate();

					System.out.println("fifth status----------" + tok1);
				}

				if (tok1 > 0) {
					con.setAutoCommit(true);
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Gatepass Cancelled Successfully !!! ",
									"Gatepass Cancelled Successfully !!!"));
					act.clearAll();
				}

				else {
					con.rollback();
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Gatepass Not Cancelled !!! ",
									"Gatepass Not Cancelled !!!"));

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

				} catch (SQLException ex) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(ex.getMessage(), ex.getMessage()));
					ex.printStackTrace();
				}
			}

			return "";

		}
		
		
		// ===========================code for csv============================

		public void saveCSV(GatepassToDistrictWholesale_FL2D_oldStockAction action) throws IOException {
			Connection con = null;
			PreparedStatement stmt = null;

			String query = " INSERT INTO fl2d.fl36_dispatch_casecode_fl2d(vch_gatepass_no, vch_casecode)VALUES (?, ?) ";

			String gatepass = "";
			int status = 0, flag = 0;
			BufferedReader bReader = new BufferedReader(new FileReader(
					action.getCsvFilepath()));
			// String line = "";
			try {
				con = ConnectionToDataBase.getConnection();
				stmt = con.prepareStatement(query);
				// ArrayList ar = new ArrayList();

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
								//System.err.println("-----gatepass" + sd);
								gatepass = sd;
							}

							else// line number >1
							{
								//System.out.println(gatepass);
								//System.out.println(action.getScanGatePassNo());
								if (gatepass.trim().equalsIgnoreCase(
										action.getScanGatePassNo().trim())) {

									//System.err.println("----casecode" + sd);
									casecode = sd;
									stmt.setString(1, gatepass.trim());
									stmt.setString(2, casecode.trim());

									status = stmt.executeUpdate();
								} else {
									flag = 1;
								}
							}

						}

						tokenNumber = 0;
					}
				}

				//System.out.println("----------------" + status);
				if (flag == 0) {
					if (status > 0) {
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_INFO,
										"File Uploaded Successfully",
										"File Uploaded Successfully"));
					} else {
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"File Not Uploaded !!",
										"File Not Uploaded !!"));
					}
				} else {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Kindly Enter Same Gatepass Number ",
									"Kindly Enter Same Gatepass Number "));
				}

			} catch (Exception ex) {
				ex.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, ex
								.getMessage(), ex.getMessage()));
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
}
