package com.mentor.impl;


	

	import java.io.BufferedReader;
import java.io.File;
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

import com.mentor.action.BWFLGatepass36AccidentalAction;

import com.mentor.datatable.BWFLGatepass36Accidentaldt;

import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

	public class BWFLGatepass36AccidentalImpl {

		// =====================get bwfl details=================================

		public String getDetails(BWFLGatepass36AccidentalAction act) {

			int id = 0;
			Connection con = null;
			PreparedStatement pstmt = null, ps2 = null;
			ResultSet rs = null, rs2 = null;
			String s = "";
			try {
				con = ConnectionToDataBase.getConnection();

				String selQr = " SELECT mobile_number, vch_firm_name, vch_firm_add, int_id, vch_license_type "
						+ " FROM bwfl.registration_of_bwfl_lic_holder_20_21 "
						+ " WHERE  mobile_number='"
						+ ResourceUtil.getUserNameAllReq().trim() + "' AND vch_approval='V' ";

				pstmt = con.prepareStatement(selQr);

				rs = pstmt.executeQuery();
				
				 

				while (rs.next()) {
					act.setBwflId(rs.getInt("int_id"));
					act.setBwflName(rs.getString("vch_firm_name"));
					act.setBwflAdrs(rs.getString("vch_firm_add"));
					act.setBwflLicenseTypeId(rs.getInt("vch_license_type"));
					if (rs.getString("vch_license_type").equals("1")) {
						act.setVch_from("BWFL2A");
					} else if (rs.getString("vch_license_type").equals("2")) {
						act.setVch_from("BWFL2B");
					} else if (rs.getString("vch_license_type").equals("3")) {
						act.setVch_from("BWFL2C");
					} else if (rs.getString("vch_license_type").equals("4")) {
						act.setVch_from("BWFL2D");
					}

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

		// ===============================get district
		// list==================================

		public ArrayList getDistrictList(BWFLGatepass36AccidentalAction act) {

			ArrayList list = new ArrayList();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			SelectItem item = new SelectItem();
			item.setLabel("--select--");
			item.setValue(0);
			list.add(item);
			try {
				String query = " SELECT districtid, description FROM public.district ORDER BY description ";

				conn = ConnectionToDataBase.getConnection();
				pstmt = conn.prepareStatement(query);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					item = new SelectItem();
					item.setValue(rs.getString("districtid"));
					if(rs.getString("description")==null){
						item.setLabel(rs.getString("districtid"));
					 }else{
						item.setLabel(rs.getString("description"));
					}
					
					
					

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

		// ================================get data of first
		// datatable======================

		public ArrayList displaylistImpl(BWFLGatepass36AccidentalAction act)
	
		{

			ArrayList list = new ArrayList();
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String selQr = null;
			int i = 1;

			try {

				selQr = "SELECT d.int_brand_id,b.brand_name,  d.size ,d.int_pack_id  ," +
						"(d.prcced_dispatch_bottle -COALESCE (d.breakage,0)- COALESCE( d.dispatch_36,0 )) as avlbottle," +
						"((d.prcced_dispatch_bottle)/d.size)-COALESCE(dispatch_36,0)/d.size as dispatchd_box," +
						"d.seq,c.code_generate_through,coalesce(b.tracking_flg,'N') " +
						"as tracking_flg, b.liquor_type ,  d.receive_flg, d.int_bwfl_id,d.vch_lic_no as licence_no , " +
						"receive_flg FROM   distillery.brand_registration_20_21 b, " +
						"distillery.packaging_details_20_21 c ,bwfl_license.liquor_accidental_case d     " +
						"where  b.brand_id=c.brand_id_fk  and   d.int_pack_id=c.package_id and" +
						" d.int_brand_id = b.brand_id  and d.int_pack_id = c.package_id and " +
						" d.int_bwfl_id =d.int_bwfl_id   and d.int_bwfl_id ='"+act.getBwflId()+"' " +
						" AND (d.prcced_dispatch_bottle -COALESCE (d.breakage,0)- COALESCE( d.dispatch_36,0 ))>0 and (c.edp+c.mrp+c.permit)>0  " +
						" and d.prcced_dispatch_box-coalesce(d.breakage,0)>0 order by seq desc";
				
						System.out.println("======ankur====="+selQr);
				
				
		/*" SELECT DISTINCT c.int_brand_id, a.cesh,b.brand_name,c.int_pack_id, a.package_name, c.seq,c.licence_no,  "
						+ " c.int_recieved_bottles-coalesce(c.dispatch_36,0) as avlbottle ,a.duty, a.adduty, "
						+ " ((c.int_recieved_bottles-coalesce(c.dispatch_36,0))/c.box_size) as dispatchd_box, c.box_size  "
						+ " FROM distillery.packaging_details_20_21 a, distillery.brand_registration_20_21 b, "
						+ " bwfl_license.mst_receipt_20_21 c " 
						
						+ " WHERE a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.int_brand_id  "
						+ " AND a.package_id=c.int_pack_id AND b.brand_id=c.int_brand_id  AND c.int_bwfl_id='"
						+ act.getBwflId()
						+ "' "
						+ " AND COALESCE(c.dispatch_36,0)<c.int_planned_bottles and (a.edp+a.mrp+a.permit)>0  and c.int_recieved_bottles-coalesce(c.dispatch_36,0)>0 "
						
						+"";*/
				
				
				 
				

				conn = ConnectionToDataBase.getConnection();
				ps = conn.prepareStatement(selQr);
				rs = ps.executeQuery();
				while (rs.next()) {
					BWFLGatepass36Accidentaldt dt = new BWFLGatepass36Accidentaldt();
					dt.setInt_brand_id(rs.getInt("int_brand_id"));
					dt.setInt_pckg_id(rs.getInt("int_pack_id"));
					dt.setVch_brand(rs.getString("brand_name"));
					dt.setInt_bottle_avaliable(rs.getInt("avlbottle"));
					//dt.setPackageName(rs.getString("package_name"));
					dt.setSeq(rs.getInt("seq"));
					dt.setSize(rs.getInt("size"));
					dt.setBoxAvailable(rs.getInt("dispatchd_box"));
					dt.setBalance(rs.getInt("avlbottle"));
					dt.setLicenseNodt(rs.getString("licence_no"));
				
				
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

		// =====================get max id sequence==============================

		public int maxId(BWFLGatepass36AccidentalImpl bwflGatepass36AccidentalImpl) {

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = " SELECT max(seq) as id FROM bwfl_license.gatepass_to_districtwholesale_bwfl_20_21 ";
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
		public boolean  etin(String casecode,String size,BWFLGatepass36AccidentalAction action) {
			 
			Connection con = null;
			PreparedStatement pstmt = null, ps2 = null;
			ResultSet rs = null, rs2 = null;
			boolean s = false;
			try {
				con = ConnectionToDataBase.getConnection();
	 
			
				String query = " SELECT distinct b.code_generate_through FROM bwfl_license.fl2_stock_trxn_bwfl_20_21 a, distillery.packaging_details_20_21 b "
						+ " WHERE b.package_id = a.int_pckg_id  and a.vch_gatepass_no='"+ action.getScanGatePassNo().trim()+ "' and b.code_generate_through='"+casecode+"' and a.size::numeric="+size+"";
				//////System.out.println("get etin flag="+query);
				pstmt = con.prepareStatement(query);
				 
				rs = pstmt.executeQuery();
				if (rs.next()) {
				 ////////System.out.println("false");
				 
				}else{
					//////System.out.println("true");
					s=true;
				}

				 

			} catch (SQLException se) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));			
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
		// =================================save
		// data===================================

		public String saveMethodImpl(BWFLGatepass36AccidentalAction act) {

			Connection con = null;
			PreparedStatement ps = null, ps2 = null, ps2D = null, ps3 = null, ps4 = null, ps5 = null;
			ResultSet rs = null;
			String sql = "";
			String sql2 = "";
			String sql3 = "";
			int tok1 = 0, tok2 = 0, tok3 = 0, tok5 = 0;
			double duty = 0;
			double addduty = 0;
			String sql5 = "";
			int seq = this.maxId(this);
			// int dutyid= this.dutymaxId(this);

			String gatepass = String.valueOf(act.getBwflId()) + "-2020-21-FL36-"+ seq;
			try {
				
				int cases = 0;
				int totalBottles = 0;

				con = ConnectionToDataBase.getConnection();
				con.setAutoCommit(false);
				
			

				String insQr = " INSERT INTO bwfl_license.gatepass_to_districtwholesale_bwfl_20_21( "
						+ " int_bwfl_id, vch_gatepass_no, dt_date, vch_to, vch_to_lic_no, curr_date, licencee_id, "
						+ " vch_route_detail, vch_vehicle_no, vehicle_driver_name, vehicle_agency_name_adrs, "
						+ " licensee_name, licensee_adrs, district_1, district_2, district_3, valid_till, "
						+ " licence_district, seq, gross_weight, tier_weight, net_weight, vch_from ," +
						" challanno, challandate, challanamount) "
						+ " VALUES ('"+act.getBwflId()+"', '"+gatepass+"', '"+Utility.convertUtilDateToSQLDate(act.getDt_date())+"'," +
						" '"+act.getVch_to()+"', ?, '"+Utility.convertUtilDateToSQLDate(new Date())+"'," +
						" ?, '"+act.getRouteDtl()+"', '"+act.getVehicleNo()+"', '"+act.getVehicleDrvrName()+"', " +
						"  '"+act.getVehicleAgencyNmAdrs()+"',  '"+act.getLicenseeName()+"', '"+act.getLicenseeAdrs()+"'," +
						" '"+ act.getDistrict1()+"', '"+ act.getDistrict2()+"', '"+ act.getDistrict3()+"', " +
						" '"+ Utility.convertUtilDateToSQLDate(act.getValidtilldt_date())+"', '"+ act.getDistrictLic()+"', " +
						" '"+ seq+"', '"+ act.getGrossWeight()+"', '"+ act.getTierWeight()+"', '"+ act.getNetWeight()+"', " +
						" '"+ act.getVch_from()+"',?,?,?  ) ";
				
				
				
						System.out.println("===ankuer234"+insQr);
				ps = con.prepareStatement(insQr);

			/*	ps.setInt(1, act.getBwflId());
				
				ps.setString(2, gatepass);
			
				ps.setDate(3, Utility.convertUtilDateToSQLDate(act.getDt_date()));
				ps.setString(4, act.getVch_to());*/
			
				if (act.getVch_to().equalsIgnoreCase("DW")) 
				{
					ps.setString(1, act.getVch_to_lic_no());
					ps.setString(3, null);
					ps.setDate(4, null);
				ps.setDouble(5, 0.0);
				} 
				
				else if (act.getVch_to().equalsIgnoreCase("BRC")) 
				{
					ps.setString(1, act.getVch_to_lic_noNew());
					ps.setString(2, act.getChallanNo());
					ps.setDate(3,Utility.convertUtilDateToSQLDate(act.getChallanDate()));	
					ps.setDouble(4, act.getChallanAmount());
				}
				//ps.setDate(6, Utility.convertUtilDateToSQLDate(new Date()));
		ps.setInt(2, 0);
			/*	ps.setString(8, act.getRouteDtl());
			
				ps.setString(9, act.getVehicleNo());
				
				ps.setString(10, act.getVehicleDrvrName());
				
				ps.setString(11, act.getVehicleAgencyNmAdrs());
			
				ps.setString(12, act.getLicenseeName());
			
				ps.setString(13, act.getLicenseeAdrs());
			
				ps.setInt(14, act.getDistrict1());
			
				ps.setInt(15, act.getDistrict2());
			
				ps.setInt(16, act.getDistrict3());
			
				ps.setDate(17,
						Utility.convertUtilDateToSQLDate(act.getValidtilldt_date()));
				ps.setInt(18, act.getDistrictLic());
				
				ps.setInt(19, seq);
			
				ps.setDouble(20, act.getGrossWeight());
				
				ps.setDouble(21, act.getTierWeight());
				
				ps.setDouble(22, act.getNetWeight());
			
				ps.setString(23, act.getVch_from());*/
			
				
			
	/*		  ps.setString(2, act.getChallanNo());
			  if(act.getChallanDate()!=null)
				{
				}else{
					
				}
			  ps.setDate(3,Utility.convertUtilDateToSQLDate(act.getChallanDate()));	
				
		      ps.setDouble(4, act.getChallanAmount());*/
				
				
				tok1 = ps.executeUpdate();

				 
				if (tok1 > 0) 
				{
					for (int i = 0; i < act.getDisplaylist().size(); i++) 
					{
						BWFLGatepass36Accidentaldt dt = (BWFLGatepass36Accidentaldt) act.getDisplaylist().get(i);
						if (dt.getDispatchbottls() > 0 && dt.getDispatchbox() > 0) 
						{
							tok1 = 0;
							String insQr1 = " INSERT INTO bwfl_license.fl2_stock_trxn_bwfl_20_21( "
									+ " int_bwfl_id, vch_lic_no, dt, int_brand_id, int_pckg_id, avl_bottl, avl_box, "
									+ " breakage, balance, vch_gatepass_no, size, vch_batch_no, dispatch_bottle, "
									+ " dispatch_box, seq_fl2, mst_seq) VALUES "
									+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, (select nextval('seq')))";

							
							System.out.println("===ankuer6700004"+insQr);
							ps2 = con.prepareStatement(insQr1);
							
							ps2.setInt(1, act.getBwflId());
						
							ps2.setString(2, act.getVch_to());
				
							ps2.setDate(3, Utility.convertUtilDateToSQLDate(act.getDt_date()));
							ps2.setInt(4, dt.getInt_brand_id());
						
							ps2.setInt(5, dt.getInt_pckg_id());
						
							ps2.setInt(6, dt.getInt_bottle_avaliable());
							
							ps2.setInt(7, dt.getBoxAvailable());
						
					
							ps2.setInt(8, dt.getBreakage());
						
							ps2.setDouble(9, dt.getBalance());
							

							
							ps2.setString(10, gatepass);
							
							ps2.setInt(11, dt.getSize());
					
							ps2.setString(12, dt.getBatchNo());
						
							ps2.setInt(13, dt.getDispatchbottls());
						
							ps2.setInt(14, dt.getDispatchbox());
						
							ps2.setInt(15, dt.getSeq());
						
						
							cases += dt.getDispatchbox();
						
							totalBottles += dt.getDispatchbottls();
					
							tok1 = ps2.executeUpdate();
							
							
							System.out.println("===ankuer6700004"+tok1);
							
							if (tok1 > 0) 
							{
								tok1 = 0;
								String updtQr = " UPDATE bwfl_license.liquor_accidental_case SET "
										+ " dispatch_36 = COALESCE(dispatch_36,0)+("
										+ dt.getDispatchbottls()
										+ "+"
										+ dt.getBreakage()
										+ ") "
										+ " WHERE int_bwfl_id='"
										+ act.getBwflId()
										+ "' AND "
										+ " int_brand_id='"
										+ dt.getInt_brand_id()
										+ "' "
										+ " AND int_pack_id='"
										+ dt.getInt_pckg_id()
										+ "' AND seq='"
										+ dt.getSeq()
										+ "'"
										+ " AND vch_lic_no='"+dt.getLicenseNodt()+"' ";
								
								System.out.println("===ankupdate"+updtQr);
								ps3 = con.prepareStatement(updtQr);
								tok1 = ps3.executeUpdate();
								System.out.println("===ankupdate"+tok1);
								 
							}
							
							

		/*					if (tok1 > 0) 
							{
								//tok1 = 0;

								String updtQr1 = " UPDATE bwfl_license.receipt_stock_20_21 SET "
										+ " int_dispatched_botls = COALESCE(int_dispatched_botls,0)+("
										+ dt.getDispatchbottls()
										+ "+"
										+ dt.getBreakage()
										+ "), "
										+ " int_dispatched_cases = COALESCE(int_dispatched_cases,0)+'"
										+ dt.getDispatchbox()
										+ "' "
										+ " WHERE int_bwfl_id='"
										+ act.getBwflId()
										+ "' AND "
										+ " int_brand_id='"
										+ dt.getInt_brand_id()
										+ "' "
										+ " AND int_pckg_id='"
										+ dt.getInt_pckg_id() + "' ";
								System.out.println("===ankupdate3450000"+updtQr1);
								ps4 = con.prepareStatement(updtQr1);
								 ps4.executeUpdate();
					
									System.out.println("===ankupdate3450000"+updtQr1);
								 
							}*/
							
							if (tok1 > 0 && act.getVch_to().equalsIgnoreCase("DW")) 
							{
								tok1 = 0;


								String qury="UPDATE fl2d.indent_for_wholesale_trxn "+
									" SET  finalize_indent= coalesce(finalize_indent,0)+"+dt.getDispatchbox()+" "+
									" where  int_pckg_id="+dt.getInt_pckg_id()+" and  int_brand_id="+dt.getInt_brand_id()+" and "+
									" indent_no='"+dt.getIndentNumber().trim()+"'  ";

								System.out.println("===an77777777777777"+qury);
							ps2 = con.prepareStatement(qury);

							tok1 = ps2.executeUpdate();
							 
						 
							qury = " INSERT INTO bwfl_license.wholesale_indent_gatepass( " +
									"  int_distillery_id, vch_gatepass, " +
									" gatepass_dt, indent_no, brand_id, package_id,boxes) " +
									" VALUES ("+act.getBwflId()+ ", '"+gatepass+"', '"+Utility.convertUtilDateToSQLDate(act.getDt_date())+"', '"+dt.getIndentNumber().trim()+"' , "+dt.getInt_brand_id()+", "+dt.getInt_pckg_id()+","+dt.getDispatchbox()+") ";

							System.out.println("===an77777777777333333333777"+qury);
							ps4 = con.prepareStatement(qury);
							 
							tok1 = ps4.executeUpdate();
							
							System.out.println("===an77777777777333333333777"+tok1);
							}

						}

					}
			
					String type="";String type1="";
					if(act.getVch_from().equalsIgnoreCase("BWFL2A"))
					{
						type="DUTY_BWFL2A";type1="BWFL2A_Special Additional_DUTY";
					}else if(act.getVch_from().equalsIgnoreCase("BWFL2B"))
					{
						type="DUTY_BWFL2B";type1="BWFL2B_Special Additional_DUTY";
					}
					
					else if(act.getVch_from().equalsIgnoreCase("BWFL2C"))
					{
						type="DUTY_BWFL2C";type1="BWFL2C_Special Additional_DUTY";
					}
					else if(act.getVch_from().equalsIgnoreCase("BWFL2D"))
					{
						type="DUTY_BWFL2D";type1="BWFL2A_Special Additional_DUTY";
					}
				
					
					
				/*	if (tok1 > 0) 
					{
						tok1 = 0;
						sql3 = "INSERT INTO distillery.duty_register_19_20( "
								+ "int_id, int_bwfl_id, date_crr_date, vch_duty_type, " +
								" int_quantity, int_value, vch_description, int_distillery_id,gatepass) "
								+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
						System.out.println("===an7777777711111111111777777"+sql3);
						ps5 = con.prepareStatement(sql3);
						ps5.setInt(1, dutymaxId() + 1);
						ps5.setInt(2, act.getBwflId());
						ps5.setDate(3,new java.sql.Date(System.currentTimeMillis()));
						
						
						ps5.setString(4, type1);
						
						ps5.setDouble(5, totalBottles);
						
						ps5.setDouble(6, act.getCeshsum());
						
						ps5.setString(7, "Special Additional Duty from "+act.getVch_from()+" for "+totalBottles+" Bottles for Gatepass "+ gatepass);
						ps5.setInt(8, 0);
						ps5.setString(9, gatepass);
						tok1 = ps5.executeUpdate();
						
						System.out.println("===an7777777711111111111777777"+tok1);
						}else {
							tok1=0;
						}*/
					
					
					
					/*if (tok1 > 0) 
					{
						tok1 = 0;333333333333333333333333333333
						sql3 = "INSERT INTO distillery.duty_register_19_20( "
								+ "int_id, int_bwfl_id, date_crr_date, vch_duty_type, " +
								" int_quantity, int_value, vch_description, int_distillery_id,gatepass) "
								+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
						System.out.println("===an777777ankur77777777"+sql3);
						ps5 = con.prepareStatement(sql3);
						ps5.setInt(1, dutymaxId() + 1);
						ps5.setInt(2, act.getBwflId());
						ps5.setDate(3,new java.sql.Date(System.currentTimeMillis()));
						
						
						ps5.setString(4, type);
						
						ps5.setDouble(5, totalBottles);
						
						ps5.setDouble(6, act.getDb_total_value()+act.getDb_total_add_value());
						
						ps5.setString(7, "Duty for BWFL Export DUTY for Gatepass "+ gatepass);
						ps5.setInt(8, 0);
						ps5.setString(9, gatepass);
						tok1 = ps5.executeUpdate();
						System.out.println("===an777777ankur77777777"+tok1);

						if(act.getVch_to().equalsIgnoreCase("BRC"))
						{
							
							tok1 = 0;
							
						sql3 = "INSERT INTO distillery.duty_register_19_20( "
								+ "int_id, int_bwfl_id, date_crr_date, vch_duty_type, int_quantity," +
								" int_value, vch_description, int_distillery_id,gatepass) "
								+ "VALUES (?, ?, ?, ?, ?, ?, ?, ? ,?)";

						ps5 = con.prepareStatement(sql3);

						ps5.setInt(1, dutymaxId() + 2);
						ps5.setInt(2, act.getBwflId());
						ps5.setDate(3,new java.sql.Date(System.currentTimeMillis()));
						ps5.setString(4, "COWCESS_BWFL");
						ps5.setDouble(5, totalBottles);
						ps5.setDouble(6, act.getChallanAmount());
						ps5.setString(7,act.getChallanNo()+"-"+Utility.convertUtilDateToSQLDate(act.getChallanDate())+"-"+act.getChallanAmount());
						ps5.setInt(8,0);
						ps5.setString(9, gatepass);
						tok1 = ps5.executeUpdate();
						
						
						 
						}
					}*/
				}

				if (tok1 > 0) 
				{
					con.commit();
					act.setListFlagForPrint(true);

					ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,
							Constants.SAVED_SUCESSFULLY);

					act.clearAll();
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

		public String getEmailDetails(BWFLGatepass36AccidentalAction act) {

			int id = 0;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String s = "";
			try {
				con = ConnectionToDataBase.getConnection();

				String queryList = " SELECT a.officer_email "
						+ " FROM public.district a, bwfl_license.gatepass_to_districtwholesale_bwfl_20_21 b "
						+ " WHERE a.districtid=b.licence_district ";

				pstmt = con.prepareStatement(queryList);

				// ////System.out.println("email query-----------------"+queryList);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					act.setOfficrEmail(rs.getString("officer_email"));
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

		// ============================get data on second data
		// table========================================

		public ArrayList getTable2List(BWFLGatepass36AccidentalAction act) {

			ArrayList list = null;
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String finalz = "";

			String query = " SELECT  dt_date, vch_to,vch_from, vch_to_lic_no,  curr_date, vch_gatepass_no, seq , vch_finalize  "
					+ " FROM bwfl_license.gatepass_to_districtwholesale_bwfl_20_21 WHERE int_bwfl_id='"
					+ act.getBwflId() + "' order by seq  desc";

	 
			try {
				list = new ArrayList();
				con = ConnectionToDataBase.getConnection();
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
				int i = 1;
				while (rs.next()) {
					BWFLGatepass36Accidentaldt dt = new BWFLGatepass36Accidentaldt();

					dt.setSerialNo(i);
					dt.setCurrentDate(rs.getDate("dt_date"));
					dt.setVchTO(rs.getString("vch_to"));
					dt.setGatepassNo(rs.getString("vch_gatepass_no"));
					dt.setLicenseNo(rs.getString("vch_to_lic_no"));
					dt.setMaxId(rs.getInt("seq"));
					dt.setVch_from(rs.getString("vch_from"));
					finalz = rs.getString("vch_finalize");

					if (finalz != null) { // (finalz.equalsIgnoreCase("F"))
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

		// =============================print report============================

		public boolean printReport(BWFLGatepass36AccidentalAction action) {

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

				/*reportQuery = " SELECT b.dt_date,a.vch_firm_name, b.vch_gatepass_no,b.vehicle_driver_name,a.vch_firm_add, "
						+ " b.vch_to, b.vch_to_lic_no,b.curr_date, b.licencee_id, b.vch_route_detail, b.vch_vehicle_no, "
						+ " b.valid_till,b.vehicle_agency_name_adrs, b.licensee_name,b.licensee_adrs  ,c.dispatch_box ,"
						+ " b.valid_till, c.int_brand_id, c.size as box_size, c.dispatch_bottle as no_bottl,d.brand_name,"
						+ " e.package_name,e.box_id,f.qnt_ml_detail as ml,d.strength, "
						+ " c.vch_batch_no, b.gross_weight, b.tier_weight, b.net_weight,"
						+ " (((c.dispatch_bottle)*f.qnt_ml_detail)/1000) as bl,"
						+ " ((((c.dispatch_bottle*f.qnt_ml_detail)/1000)*42.8)/100) as al "
						+ " FROM bwfl.registration_of_bwfl_lic_holder a, bwfl_license.gatepass_to_districtwholesale_bwfl b,"
						+ " bwfl_license.fl2_stock_trxn_bwfl c, distillery.brand_registration d,"
						+ " distillery.packaging_details e, distillery.box_size_details f "
						+ " WHERE a.int_id=b.int_bwfl_id AND b.int_bwfl_id=c.int_bwfl_id "
						+ " AND b.vch_gatepass_no=c.vch_gatepass_no and b.dt_date=c.dt "
						+ " AND c.int_brand_id=d.brand_id AND d.brand_id=e.brand_id_fk AND c.int_pckg_id=e.package_id "
						+ " AND e.box_id=f.box_id AND f.qnt_ml_detail=e.quantity AND "
						+ " b.int_bwfl_id='"
						+ action.getBwflId()
						+ "' AND b.vch_gatepass_no='"
						+ action.getPrintGatePassNo()
						+ "' ";*/
				reportQuery = "SELECT * FROM bwfl_license.func_20_21("+action.getBwflId()+",'"+action.getPrintGatePassNo()+"')";
				// ////System.out.println("reportQuery---------------------"+reportQuery);
				pst = con.prepareStatement(reportQuery);
				rs = pst.executeQuery();

				if (rs.next()) {
					rs = pst.executeQuery();

					Map parameters = new HashMap();
					parameters.put("REPORT_CONNECTION", con);
					// parameters.put("SUBREPORT_DIR", relativePath+File.separator);
					parameters.put("image", relativePath + File.separator);

					JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);

					jasperReport = (JasperReport) JRLoader.loadObject(relativePath
							+ File.separator
							+ "GatepassDistrictWholesaleBWFL.jasper");

					JasperPrint print = JasperFillManager.fillReport(jasperReport,
							parameters, jrRs);
					Random rand = new Random();
					int n = rand.nextInt(250) + 1;

					JasperExportManager.exportReportToPdfFile(
							print,
							relativePathpdf + File.separator
									+ "GatepassDistrictWholesaleBWFL_"
									+ action.getPrintGatePassNo() + "-" + n
									+ ".pdf");

					BWFLGatepass36Accidentaldt dt = new BWFLGatepass36Accidentaldt();
					dt.setPdfName("GatepassDistrictWholesaleBWFL_"
							+ action.getPrintGatePassNo() + "-" + n + ".pdf");
					action.setPdfname("GatepassDistrictWholesaleBWFL_"
							+ action.getPrintGatePassNo() + "-" + n + ".pdf");
					dt.setPrintFlag(true);
					printFlag = true;
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("No Data Found", "No Data Found"));
					BWFLGatepass36Accidentaldt dt1 = new BWFLGatepass36Accidentaldt();
					printFlag = false;
					dt1.setPrintFlag(false);
				}
			} catch (JRException e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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

		// ===============================get license
		// number==========================================

		public ArrayList getlicenseNmbr(BWFLGatepass36AccidentalAction act) {
	//////System.out.println("getlicenseNmbr");
			ArrayList list = new ArrayList();
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			SelectItem item = new SelectItem();
			item.setLabel("--select--");
			item.setValue("");
			list.add(item);
			try {
				String query = " SELECT vch_licence_no, vch_license_type "
						+ " FROM licence.fl2_2b_2d_20_21 WHERE vch_license_type='FL2' ORDER BY vch_licence_no ";

				String query1 = " SELECT vch_licence_no, vch_license_type "
						+ " FROM licence.fl2_2b_2d_20_21 WHERE vch_license_type='FL2B' ORDER BY vch_licence_no ";

				String query2 = " SELECT vch_licence_no, vch_license_type "
						+ " FROM licence.fl2_2b_2d_20_21 WHERE vch_license_type in ('FL2B','FL2') ORDER BY vch_licence_no ";

				conn = ConnectionToDataBase.getConnection();

				if (act.getBwflLicenseTypeId() == 1
						|| act.getBwflLicenseTypeId() == 3) {
					ps = conn.prepareStatement(query);
					// ////System.out.println("query-----license11111--------"+query);

				} else if (act.getBwflLicenseTypeId() == 2) {
					ps = conn.prepareStatement(query1);
					// ////System.out.println("query1-----license222222--------"+query1);

				} else if (act.getBwflLicenseTypeId() == 4) {
					ps = conn.prepareStatement(query1);
					// ////System.out.println("query1-----license444444--------"+query1);

				}

				rs = ps.executeQuery();

				while (rs.next()) {

					item = new SelectItem();
	/*if(rs.getString("vch_licence_no")==null){
		////System.out.println("oo");
		item.setValue("0");
		item.setLabel("0");
	}else{*/
		//////System.out.println("pp");
		item.setValue(rs.getString("vch_licence_no"));
		item.setLabel(rs.getString("vch_licence_no"));
	//}
					

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

		// ========================get bar restaurant and club restaurant
		// number==========================

		public ArrayList getbrcLicenseNo(BWFLGatepass36AccidentalAction act) {
	//////System.out.println("getbrcLicenseNo");
			ArrayList list = new ArrayList();
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			SelectItem item = new SelectItem();
			item.setLabel("--select--");
			item.setValue("");
			list.add(item);
			try {

				String query = " SELECT license_number, finalize "
						+ " FROM hotel_bar_rest.registration_for_hotels_bars_restraunt_20_21 "
						+ " WHERE finalize IS NOT NULL ORDER BY license_number ";

				conn = ConnectionToDataBase.getConnection();

				ps = conn.prepareStatement(query);
				rs = ps.executeQuery();

				while (rs.next()) {

					item = new SelectItem();

					item.setValue(rs.getString("license_number"));
					item.setLabel(rs.getString("license_number"));

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

		// ===========================get licensee
		// detail==============================

		public String getlicenseeDetail(BWFLGatepass36AccidentalAction act, String val) {

			int id = 0;
			Connection con = null;
			PreparedStatement pstmt = null, ps2 = null;
			ResultSet rs = null, rs2 = null;

			try {
				con = ConnectionToDataBase.getConnection();

				String selQr = " SELECT a.license_id, a.license_number, a.name_of_license, "
						+ " a.address, b.description, b.districtid "
						+ " FROM hotel_bar_rest.registration_for_hotels_bars_restraunt a, public.district b "
						+ " WHERE a.district=b.districtid::text  AND license_number='"
						+ val + "' ";
				 //////System.out.println("licensee------------"+selQr);
				pstmt = con.prepareStatement(selQr);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					act.setLicenseeId(rs.getInt("license_id"));
					act.setLicenseeName(rs.getString("name_of_license"));
					act.setDistrictLic(rs.getInt("districtid"));
					act.setLicenseeDistrict(rs.getString("description"));
					if (rs.getString("address") != null) {
						act.setLicenseeAdrs(rs.getString("address"));
					}

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

		// ===========================get licensee
		// detail==============================

		public String getwareLicenseeDetail(BWFLGatepass36AccidentalAction act,
				String val) {

			int id = 0;
			Connection con = null;
			PreparedStatement pstmt = null, ps2 = null;
			ResultSet rs = null, rs2 = null;

			try {
				con = ConnectionToDataBase.getConnection();

				String selQr = " SELECT a.int_app_id, a.vch_applicant_name, a.vch_core_address, "
						+ " a.vch_licence_no, b.description, b.districtid "
						+ " FROM licence.fl2_2b_2d_20_21 a, public.district b "
						+ " WHERE a.core_district_id=b.districtid AND vch_licence_no='"
						+ val + "' AND vch_license_type=? ";

				pstmt = con.prepareStatement(selQr);

				// ////System.out.println("selQr------------"+selQr);
				// ////System.out.println("act.getBwflLicenseTypeId()------------"+act.getBwflLicenseTypeId());

				if (act.getBwflLicenseTypeId() == 1
						|| act.getBwflLicenseTypeId() == 3) {
					pstmt.setString(1, "FL2");
				} else if (act.getBwflLicenseTypeId() == 2
						|| act.getBwflLicenseTypeId() == 4) {
					pstmt.setString(1, "FL2B");
				}
				/*
				 * else if(act.getBwflLicenseTypeId() == 4) { pstmt.setString(1,
				 * "FL2B"); }
				 */
				rs = pstmt.executeQuery();

				while (rs.next()) {

					act.setLicenseeId(rs.getInt("int_app_id"));
					act.setLicenseeName(rs.getString("vch_applicant_name"));
					act.setDistrictLic(rs.getInt("districtid"));
					act.setLicenseeDistrict(rs.getString("description"));
					if (rs.getString("vch_core_address") != null) {
						act.setLicenseeAdrs(rs.getString("vch_core_address"));
					}
					act.setFlagLic(true);
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

		// =====================get max id sequence==============================

		public int dutymaxId() {

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			String query = " SELECT max(int_id) as id FROM distillery.duty_register_19_20 ";
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

		public void saveExcelData(BWFLGatepass36AccidentalAction action) {/*
			String gatepass = "";
			int status = 0, flag = 1, excelcase = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			 FileInputStream fileInputStream = null;
			XSSFWorkbook workbook = null;
			try {
				String sql = " INSERT INTO bwfl_license.fl36_dispatch_casecode_fl2d_20_21(vch_gatepass_no, vch_casecode)VALUES (?, ?) ";

				conn = ConnectionToDataBase.getConnection();

				pstmt = conn.prepareStatement(sql);
				fileInputStream = new FileInputStream(action.getExcelFilepath());

				workbook = new XSSFWorkbook(fileInputStream);

				XSSFSheet worksheet = workbook.getSheet("Sheet1");
				int i = 0, j = 0;
				do {
					 
					String casecode = "";
					XSSFRow row1 = worksheet.getRow(j);

					XSSFCell cellA1 = row1.getCell((short) 0);

					String cellval = getCellValue(cellA1);

				 
					if ((cellval == null) || (cellval.length() == 0)
							|| (cellval.equals("0.0"))) {
						 
						break;
					} else {

						if (j == 0) {
							cellA1 = row1.getCell((short) 0);
							gatepass = getCellValue(cellA1);
							if (action.getScanGatePassNo().trim()
									.equalsIgnoreCase(gatepass.trim()))

							{
	 
								i = 1;
							} else {

								flag = 0;
							}
						} else {

							cellA1 = row1.getCell((short) 0);
							casecode = getCellValue(cellA1);
							if(this.etin(casecode.trim().substring(2, 15), action)==true){
								FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
										" Casecode-"+casecode.trim()+" does not belongs to the brands for the selected gatepass!" ," Casecode-"+casecode.trim()+" does not belongs to the brands for the selected gatepass!"));	
							}else{
							pstmt.setString(1, gatepass.trim());
							pstmt.setString(2, casecode.trim());

							status = pstmt.executeUpdate();
							excelcase++;
							action.setExcelCases(excelcase);
							i = 1;
						}}
					}

					j++;
				} while (i == 1);

			} catch (Exception e) {
				////System.out.println("errer" + e.getMessage());
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

		*/}

		// ==========================get excel data from temporary
		// table============================

		public ArrayList getExcelData(BWFLGatepass36AccidentalAction action) {

			ArrayList list = new ArrayList();

			Connection con = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			int boxingCount=0;
			int listSize=0;
			String query = " SELECT vch_gatepass_no, vch_casecode FROM bwfl_license.fl36_dispatch_casecode_fl2d_20_21 "
					+ " WHERE vch_gatepass_no='"
					+ action.getScanGatePassNo()
					+ "' ";

			try {
				con = ConnectionToDataBase.getConnection();
				stmt = con.prepareStatement(query);
				rs = stmt.executeQuery();

				while (rs.next()) {
					BWFLGatepass36Accidentaldt dt = new BWFLGatepass36Accidentaldt();

					dt.setGatepass(rs.getString("vch_gatepass_no"));
					dt.setCasecode(rs.getString("vch_casecode"));
					String datePlan=rs.getString("vch_casecode").substring(16,22).trim();
					String datePlan1="";
					datePlan1="20"+datePlan.substring(0, 2)+"/"+datePlan.substring(2, 4)+"/"+datePlan.substring(4,6);
					
					Date date_plan=new SimpleDateFormat("yyyy/MM/dd").parse(datePlan1);
					
					date_plan=Utility.convertUtilDateToSQLDate(date_plan);
					////System.out.println(datePlan1+"\t"+date_plan);
					boolean flag=getCascodeMatch(rs.getString("vch_casecode").substring(26,rs.getString("vch_casecode").length()),rs.getString("vch_casecode").substring(0, 12),date_plan,action);
					String brand_name=getCascodeBrand(rs.getString("vch_casecode").substring(0, 12));
					dt.setBrand_name(brand_name);
					//////System.out.println("brand name="+dt.getBrand_name());
					dt.setCasecoseBrandSize(Integer.parseInt(rs.getString("vch_casecode").substring(23, 26))); 
					dt.setDate_plan(date_plan);
					
						if(flag){
							//////System.out.println("getCascodeMatch");
							++listSize;
						dt.setCascodeMatching("Valid");
						}else{
							++boxingCount;
							 dt.setCascodeMatching("Invalid");
							 //////System.out.println("boxingCount"+boxingCount);
						}
					list.add(dt);
					
				}
				//////System.out.println("finalize flg ="+action.isBottlingNotDoneFlag());
				if(boxingCount!=0 || listSize<=0 )
				{ //////System.out.println("finalize flg 2="+action.isBottlingNotDoneFlag());
					action.setBottlingNotDoneFlag(true);
					//////System.out.println("finalize flg 3="+action.isBottlingNotDoneFlag());
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

		// ---------------------count casecode in
		// excel------------------------------

		public int excelCases(BWFLGatepass36AccidentalAction act) {

			int id = 0;
			Connection con = null;
			PreparedStatement pstmt = null, ps2 = null;
			ResultSet rs = null, rs2 = null;
			String s = "";
			try {
				con = ConnectionToDataBase.getConnection();

				String query = " SELECT count(*) as dispatchd_box FROM bwfl_license.fl36_dispatch_casecode_fl2d_20_21 "
						+ " WHERE vch_gatepass_no='"
						+ act.getScanGatePassNo().trim() + "'";

				pstmt = con.prepareStatement(query);

				//////System.out.println("query--------recieve------------" + query);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					id = (rs.getInt("dispatchd_box"));

				}

			} catch (SQLException se) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(se.getMessage(), se.getMessage()));
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

		public int recieveCases(BWFLGatepass36AccidentalAction act) {

			int id = 0;
			Connection con = null;
			PreparedStatement pstmt = null, ps2 = null;
			ResultSet rs = null, rs2 = null;
			String s = "";
			try {
				con = ConnectionToDataBase.getConnection();

				String query = " SELECT SUM(dispatch_box) as dispatchd_box FROM bwfl_license.fl2_stock_trxn_bwfl_20_21 "
						+ " WHERE vch_gatepass_no='"
						+ act.getScanGatePassNo().trim() + "'  ";

				pstmt = con.prepareStatement(query);
				System.out
						.println("query----------database   recieveCases  --------"
								+ query);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					id = (rs.getInt("dispatchd_box"));

				}

			} catch (SQLException se) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(se.getMessage(), se.getMessage()));
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

		public boolean updateDispatch(BWFLGatepass36AccidentalAction act) {

			int save = 0;
			//int j = 0;
			boolean val = false;
			PreparedStatement ps = null;
			Connection con = null;
			Connection con1 = null;
			String query = "";		
			/*query = " INSERT INTO bottling_unmapped.bwfl (fl36gatepass, plan_id, date_plan,etin,fl36_date,casecode) " +
					" values (?,?,?,?,?,?) "+
					" ON CONFLICT (etin, casecode) DO UPDATE SET fl36gatepass=?, fl36_date=?  where bottling_unmapped.bwfl.fl36_date is null ";
		*/	
			query = " UPDATE bottling_unmapped.bwfl SET  fl36gatepass=?, "
					+ " fl36_date=? WHERE casecode=? and fl36_date is null and fl11_date is not   null and etin=? " +
					"and  date_plan=?";
			Date date1=null;
			try {	
				 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");			
				int j[] = null;
				con = ConnectionToDataBase.getConnection();
				con1 = ConnectionToDataBase.getConnection20_21();
				con.setAutoCommit(false);
				con1.setAutoCommit(false);
				ps = con1.prepareStatement(query);
				for (int i = 0; i < act.getGetVal().size(); i++) {
					BWFLGatepass36Accidentaldt dt = (BWFLGatepass36Accidentaldt) act.getGetVal().get(i);
					
					String datePlan=dt.getCasecode().trim().substring(16,22).trim();
				 datePlan="20"+datePlan;
						datePlan=datePlan.substring(0,4)+"-"+datePlan.substring(4,6)+"-"+datePlan.substring(6);
						 date1=new SimpleDateFormat("yyyy-MM-dd").parse(datePlan);
						//////System.out.println(datePlan+"="+date_plan);
						
						ps.setString(1, dt.getGatepass());
						ps.setDate(2, Utility.convertUtilDateToSQLDate(new Date()));
						ps.setString(3,dt.getCasecode().trim().substring(26,dt.getCasecode().length()));
					
						ps.setString(4,dt.getCasecode().trim().substring(0,12));
						ps.setDate(5,Utility.convertUtilDateToSQLDate(date1));
					
					/* ps.setString(1, dt.getGatepass().trim());
					 ps.setInt(2, 0);
					 ps.setDate(3, Utility.convertUtilDateToSQLDate(new Date()));
					 ps.setString(4,(dt.getCasecode().substring(0, 12)));
					 ps.setDate(5, Utility.convertUtilDateToSQLDate(new Date()));
					 ps.setString(6,dt.getCasecode().substring(26,dt.getCasecode().length()));
					 ps.setString(7, dt.getGatepass().trim());
					 ps.setDate(8, Utility.convertUtilDateToSQLDate(new Date()));*/

					ps.addBatch();
					
					//////System.out.println("save-------"+save +"dfgdfghd------------"+query);
					
				}
				j = ps.executeBatch();
				 
				save = j.length;
				
			
				if (act.getGetVal().size() == save && save>0) {
					save = 0;

					String updtQr = " UPDATE bwfl_license.gatepass_to_districtwholesale_bwfl_20_21 SET vch_finalize='F', finalize_dt_time=? "
							+ " WHERE vch_gatepass_no='"
							+ act.getScanGatePassNo()
							+ "' ";

					ps = con.prepareStatement(updtQr);
					ps.setString(1, dateFormat.format(new Date()));

					 //////System.out.println("updtQr------------" + updtQr);
					save = ps.executeUpdate();

					 //////System.out.println("second status------------" + save);

					query = " DELETE FROM bwfl_license.fl36_dispatch_casecode_fl2d_20_21 WHERE vch_gatepass_no ='"
							+ act.getScanGatePassNo() + "' ";
					ps = con.prepareStatement(query);
					ps.executeUpdate();

					 //////System.out.println("----   delete query   ---------"+query);

				} else {
					save = 0;
				}
				if (save > 0) {
					val = true;
					act.setListFlagForPrint(true);
					con.commit();
					con1.commit();
				} else {
					val = false;
					con.rollback();
					con1.rollback();
				}

			} catch (Exception ex) {
				ex.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
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

		// ===========================print draft
		// report====================================

		public boolean printDraftReport(BWFLGatepass36AccidentalAction action) {

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

			 
				reportQuery = "SELECT * FROM bwfl_license.func_20_21("+action.getBwflId()+",'"+action.getPrintGatePassNo()+"')";
				pst = con.prepareStatement(reportQuery);

				// ////System.out.println("------------ print draft   ------"+reportQuery);
				// pst.setString(2, action.getPrintGatePassNo().trim());

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
							+ "GatepassDistrictWholesaleBWFL_Draft.jasper");

					JasperPrint print = JasperFillManager.fillReport(jasperReport,
							parameters, jrRs);
					Random rand = new Random();
					int n = rand.nextInt(250) + 1;

					JasperExportManager.exportReportToPdfFile(print,
							relativePathpdf + File.separator
									+ "GatepassDistrictWholesaleBWFL_Draft"
									+ action.getBwflId() + n + ".pdf");

					BWFLGatepass36Accidentaldt dt = new BWFLGatepass36Accidentaldt();
					dt.setDraftpdfName("GatepassDistrictWholesaleBWFL_Draft"
							+ action.getBwflId() + n + ".pdf");

					action.setDraftpdfname("GatepassDistrictWholesaleBWFL_Draft"
							+ action.getBwflId() + n + ".pdf");

					dt.setDraftprintFlag(true);
					printFlag1 = true;

				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("No Data Found", "No Data Found"));
					BWFLGatepass36Accidentaldt dt1 = new BWFLGatepass36Accidentaldt();
					// action.setPrintFlag(false);
					printFlag1 = false;
					dt1.setDraftprintFlag(false);

				}

			} catch (JRException e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));			
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

		// ------------------- delete

		public void deleteData(BWFLGatepass36AccidentalAction act) {

			Connection con = null;
			PreparedStatement stmt = null;

			String query = " DELETE FROM bwfl_license.fl36_dispatch_casecode_fl2d_20_21 WHERE vch_gatepass_no='"
					+ act.getScanGatePassNo().trim() + "'  ";
			int status = 0;
			try {
				con = ConnectionToDataBase.getConnection();
				stmt = con.prepareStatement(query);

				status = stmt.executeUpdate();
				if (status > 0) {
					act.getGetVal().clear();
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("Cancelled Successfully ",
									"Cancelled Successfully "));
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Not Cancelled ", "Not Cancelled "));
				}
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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

		/*** ---- ravi code deleting & updating via cancel button ---- ****/

		public int maxSeq() {

			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			int id = 0;

			try {

				String sql = "select max(seq) from bwfl_license.duty_cancellation_bwfl_19_20";
				con = ConnectionToDataBase.getConnection();
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();

				if (rs.next()) {

					id = rs.getInt(1);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				try {
					con.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

			return id;

		}

	/*	public String cancelGatepassImpl(GatepassToDistrictBWFLAction act,
				GatepassToDistrictBWFLDT dt) 
		{
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
			int tok2 = 0;
			double duty = 0;
			double addDuty = 0;

			int seq = this.maxSeq() + 1;
			String   updtQr ="";
			//////System.out.println("seq -- " + seq);

			try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

				con = ConnectionToDataBase.getConnection();
				con.setAutoCommit(false);

				String gatepass = "";
				gatepass = dt.getGatepassNo().trim();

				String selQr = "select a.int_bwfl_id, a.vch_to,a.dt_date, a.vch_to_lic_no, a.vch_gatepass_no, " +
						" b.seq_fl2, b.int_brand_id, b.int_pckg_id,                "
						+ "b.dispatch_bottle, b.vch_lic_no, b.dispatch_box, b.duty, b.add_duty " +
						" from bwfl_license.gatepass_to_districtwholesale_bwfl_20_21 a, " +
						" bwfl_license.fl2_stock_trxn_bwfl_20_21 b "
						+ "where a.int_bwfl_id=b.int_bwfl_id and a.vch_gatepass_no=b.vch_gatepass_no and                                "
						+ "a.dt_date=b.dt and a.int_bwfl_id="
						+ act.getBwflId()
						+ " and a.vch_gatepass_no='" + gatepass + "'";

				pstmt = con.prepareStatement(selQr);

				//////System.out.println("selQr------------" + selQr);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					//////System.out.println("come into rssss------------");

					duty = duty + rs.getInt("duty");
					addDuty = addDuty + rs.getInt("add_duty");

					 
					dt.setSeq((rs.getInt("seq_fl2")));
					dt.setInt_brand_id(rs.getInt("int_brand_id"));
					dt.setInt_pckg_id(rs.getInt("int_pckg_id"));
					dt.setDispatchbottls(rs.getInt("dispatch_bottle"));

					 updtQr = " UPDATE bwfl_license.mst_receipt_20_21 SET "
								+ " dispatch_36 = COALESCE(dispatch_36,0)-("
								+ rs.getInt("dispatch_bottle") + ") "
								+ " WHERE int_bwfl_id='" + act.getBwflId() + "' AND "
								+ " int_brand_id='" + rs.getInt("int_brand_id") + "' "
								+ " AND int_pack_id='" + rs.getInt("int_pckg_id")
								+ "' AND seq='" + rs.getInt("seq_fl2") + "'";
						

					ps5 = con.prepareStatement(updtQr);
				
					tok1 = ps5.executeUpdate();
				
					
					if (tok1 > 0 && rs.getString("vch_to").equalsIgnoreCase("DW")) 
					{
						//tok1=0;
						String indentNo=checkIndentData(gatepass.trim(),dt.getInt_pckg_id(),dt.getInt_brand_id(),act);
						
						
				
						  updtQr = "   update fl2d.indent_for_wholesale_trxn  "+

						"  set finalize_indent=coalesce(finalize_indent,0)-"+rs.getInt("dispatch_box")+"  " +
						"  WHERE indent_no='"+indentNo+"'  " +
						" and int_pckg_id="+dt.getInt_pckg_id()+"" +
						" and int_brand_id="+dt.getInt_brand_id()+"     ";  
					 
						  
						  
						  
						   ps5 = con.prepareStatement(updtQr);
						   
						   tok1+= ps5.executeUpdate();
						 
						   //tok1=0;
						
						   updtQr = " delete from  bwfl_license.wholesale_indent_gatepass    " +
								" WHERE   vch_gatepass ='"+gatepass.trim()+"' "; 
									//	"and  int_distillery_id="+act.getBwflId()+"  " +
									//	"  and package_id="+dt.getInt_pckg_id()+" and  brand_id="+ dt.getInt_brand_id()+" ";
						   
			 
						   ps5 = con.prepareStatement(updtQr);
						   tok1+= ps5.executeUpdate();
						
					
					}
				

				}
			
				
				
				
				
				if (tok1 > 0) {

					tok1 = 0;
					sql = " INSERT INTO bwfl_license.duty_cancellation_bwfl_19_20( "
							+ " seq, vch_gatepass_no, gatepass_dt, duty_cancellation_dt_tm, db_duty_amount, vch_type, db_add_duty_amount) "
							+ " VALUES (?, ?, ?, ?, ?, ?, ?) ";

					ps = con.prepareStatement(sql);

					ps.setInt(1, seq);
					ps.setString(2, gatepass.trim());
					ps.setDate(3,
							Utility.convertUtilDateToSQLDate(dt.getCurrentDate()));
					ps.setString(4, dateFormat.format(new Date()));
					ps.setDouble(5, duty);
					ps.setString(6, "FL36-BWFL");
					ps.setDouble(7, addDuty);

					tok1 = ps.executeUpdate();

					//////System.out.println("second status----------" + tok2);

				}

				if (tok1 > 0) {
					tok1 = 0;

					sql2 = "delete from bwfl_license.gatepass_to_districtwholesale_bwfl_20_21 where int_bwfl_id = "
							+ act.getBwflId()
							+ " and vch_gatepass_no = '"
							+ gatepass + "'";

					
					 * sql2 =
					 * " DELETE FROM distillery.gatepass_to_districtwholesale " +
					 * " WHERE vch_gatepass_no='" + gatepass + "' AND int_dist_id='"
					 * + act.getDist_id() + "' ";
					 

					ps2 = con.prepareStatement(sql2);

					tok1 = ps2.executeUpdate();
					//////System.out.println("third status----------" + tok2);
				}

				if (tok1 > 0) {
					tok1 = 0;

					sql3 = "delete from bwfl_license.fl2_stock_trxn_bwfl_20_21 where int_bwfl_id = "
							+ act.getBwflId()
							+ " and vch_gatepass_no = '"
							+ gatepass + "'";

					// sql3 = " delete from bwfl_license.fl2_stock_trxn_bwfl "
					// + " WHERE vch_gatepass_no='" + gatepass
					// + "' AND int_dissleri_id='" + act.getDist_id() + "' ";

					ps3 = con.prepareStatement(sql3);
					tok1 = ps3.executeUpdate();

					//////System.out.println("fourth status----------" + tok2);
				}

				if (tok1 > 0) {

					sql4 = " DELETE FROM bwfl_license.fl36_dispatch_casecode_fl2d_20_21 WHERE vch_gatepass_no='"
							+ gatepass + "' ";

					ps3 = con.prepareStatement(sql4);
					ps3.executeUpdate();

					//////System.out.println("fifth status----------" + tok1);
				}

				if (tok1 > 0) {
					con.commit();
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

		}*/
		public String cancelGatepassImpl(BWFLGatepass36AccidentalAction act,
				BWFLGatepass36Accidentaldt dt) 
		{
			Connection con = null;
			PreparedStatement ps = null, ps2 = null, ps3 = null, ps4 = null, ps5 = null, ps6 = null;
			PreparedStatement pstmt = null;

			ResultSet rs = null;
			ResultSet rs1 = null;
			String sql = "";
			String sql2 = "";
			String sql3 = "";
			String sql4 = "";
			String sql5 = "";
			int tok1 = 0;
			int tok2 = 0;
			double duty = 0;
			double addDuty = 0;

			int seq = this.maxSeq() + 1;
			String   updtQr ="";
			////System.out.println("seq -- " + seq);

			try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

				con = ConnectionToDataBase.getConnection();
				con.setAutoCommit(false);

				String gatepass = "";
				gatepass = dt.getGatepassNo().trim();

				String selQr = "select a.int_bwfl_id, a.vch_to,a.dt_date, a.vch_to_lic_no, a.vch_gatepass_no, " +
						" b.seq_fl2, b.int_brand_id, b.int_pckg_id,                "
						+ "b.dispatch_bottle, b.vch_lic_no, b.dispatch_box, b.duty, b.add_duty " +
						" from bwfl_license.gatepass_to_districtwholesale_bwfl_20_21 a, " +
						" bwfl_license.fl2_stock_trxn_bwfl_20_21 b "
						+ "where a.int_bwfl_id=b.int_bwfl_id and a.vch_gatepass_no=b.vch_gatepass_no and      "
						+ "a.dt_date=b.dt and a.int_bwfl_id='"+ act.getBwflId()+"' "
						+ " and a.vch_gatepass_no='" + gatepass + "'";

				pstmt = con.prepareStatement(selQr);

			System.out.println("selQr------------" + selQr);

				rs = pstmt.executeQuery();

				while (rs.next()) {

			System.out.println("come into rssss------------");

					//duty = duty + rs.getInt("duty");
					//addDuty = addDuty + rs.getInt("add_duty");

					 
					dt.setSeq((rs.getInt("seq_fl2")));
					dt.setInt_brand_id(rs.getInt("int_brand_id"));
					dt.setInt_pckg_id(rs.getInt("int_pckg_id"));
					dt.setDispatchbottls(rs.getInt("dispatch_bottle"));
				

					 updtQr = " UPDATE bwfl_license.liquor_accidental_case SET" +
								"  dispatch_36=COALESCE(dispatch_36 ,0)- ("
								+ rs.getInt("dispatch_bottle") + ") " +
								" WHERE int_bwfl_id="+act.getBwflId()+" "+ 
								" AND int_pack_id="+ dt.getInt_pckg_id()+" AND int_brand_id="+dt.getInt_brand_id()+" ";
					 
												/*" UPDATE bwfl_license.mst_receipt_20_21 SET "
								+ " dispatch_36 = COALESCE(dispatch_36,0)-("
								+ rs.getInt("dispatch_bottle") + ") "
								+ " WHERE int_bwfl_id='" + act.getBwflId() + "' AND "
								+ " int_brand_id='" + rs.getInt("int_brand_id") + "' "
								+ " AND int_pack_id='" + rs.getInt("int_pckg_id")
								+ "' AND seq='" + rs.getInt("seq_fl2") + "'";*/
						System.out.println("update ------------" + updtQr);


					ps5 = con.prepareStatement(updtQr);
				
					tok1 = ps5.executeUpdate();
				
					
			System.out.println("First Status-----"+tok1);
					
					if (rs.getString("vch_to").equalsIgnoreCase("DW")) 
					{
						//tok1=0;
						String indentNo=checkIndentData(gatepass.trim(),dt.getInt_pckg_id(),dt.getInt_brand_id(),act);
						
						
				
						  updtQr = "   update fl2d.indent_for_wholesale_trxn  "+

						"  set finalize_indent=coalesce(finalize_indent,0)-"+rs.getInt("dispatch_box")+"  " +
						"  WHERE indent_no='"+indentNo+"'  " +
						" and int_pckg_id="+dt.getInt_pckg_id()+"" +
						" and int_brand_id="+dt.getInt_brand_id()+"     ";  
					 
						  System.out.println("update ------------" + updtQr);
						  
						  
						   ps5 = con.prepareStatement(updtQr);
						   
						   tok1 = ps5.executeUpdate();
						 
						   //tok1=0;
						
						   updtQr = " delete from  bwfl_license.wholesale_indent_gatepass    " +
								" WHERE   vch_gatepass ='"+gatepass.trim()+"' and  int_distillery_id="+act.getBwflId()+"  " +
										"  and package_id="+dt.getInt_pckg_id()+" and  brand_id="+ dt.getInt_brand_id()+" ";
						   
							System.out.println("delete ------------" + updtQr);
						   ps5 = con.prepareStatement(updtQr);
						   tok1+= ps5.executeUpdate();
						
					
					}
				

				}
			
	                
					String type="";
					if(dt.getVch_from().equalsIgnoreCase("BWFL2A"))
					{
						type="DUTY_BWFL2A";
					}else if(dt.getVch_from().equalsIgnoreCase("BWFL2B"))
					{
						type="DUTY_BWFL2B";
					}
					
					else if(dt.getVch_from().equalsIgnoreCase("BWFL2C"))
					{
						type="DUTY_BWFL2C";
					}
					else if(dt.getVch_from().equalsIgnoreCase("BWFL2D"))
					{
						type="DUTY_BWFL2D";
					}
				   ////System.out.println("Type --------"+type);
		/*			if (tok1 > 0) {
					tok1 = 0;
					sql = " INSERT INTO bwfl_license.duty_cancellation_bwfl_19_20( "
							+ " seq, vch_gatepass_no, gatepass_dt, duty_cancellation_dt_tm," +
							"  db_duty_amount, vch_type, db_add_duty_amount, vch_duty_type, int_bwfl_id) "
							+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
				System.out.println("=====ak"+sql);
					ps = con.prepareStatement(sql);
					

					ps.setInt(1, this.maxSeq() + 1);
					ps.setString(2, gatepass.trim());
					ps.setDate(3,Utility.convertUtilDateToSQLDate(dt.getCurrentDate()));
					ps.setString(4, dateFormat.format(new Date()));
					ps.setDouble(5, duty);
					ps.setString(6, "FL36-BWFL");
					ps.setDouble(7, addDuty);
					ps.setString(8, type);
					ps.setInt(9, act.getBwflId());
					 
					tok1 = ps.executeUpdate();

					 
					if(dt.getVchTO().equalsIgnoreCase("BRC")){
						
						tok1 = 0;
						sql = " INSERT INTO bwfl_license.duty_cancellation_bwfl_19_20( "
								+ " seq, vch_gatepass_no, gatepass_dt, duty_cancellation_dt_tm," +
								"  db_duty_amount, vch_type, db_add_duty_amount, vch_duty_type, int_bwfl_id) "
								+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
						
						System.out.println("=====ak"+sql);
						ps = con.prepareStatement(sql);

						ps.setInt(1, this.maxSeq() + 2);
						ps.setString(2, gatepass.trim());
						ps.setDate(3,Utility.convertUtilDateToSQLDate(dt.getCurrentDate()));
						ps.setString(4, dateFormat.format(new Date()));
						ps.setDouble(5, duty);
						ps.setString(6, "FL36-BWFL");
						ps.setDouble(7, addDuty);
						ps.setString(8, "COWCESS_BWFL");
						ps.setInt(9, act.getBwflId());

						tok1 = ps.executeUpdate(); 
					}
				
				}*/

				if (tok1 > 0) {
					tok1 = 0;

					sql2 = "delete from bwfl_license.gatepass_to_districtwholesale_bwfl_20_21 where int_bwfl_id = "
							+ act.getBwflId()
							+ " and vch_gatepass_no = '"
							+ gatepass + "'";
					System.out.println("=====ak"+sql2);
					/*
					 * sql2 =
					 * " DELETE FROM distillery.gatepass_to_districtwholesale " +
					 * " WHERE vch_gatepass_no='" + gatepass + "' AND int_dist_id='"
					 * + act.getDist_id() + "' ";
					 */

					ps2 = con.prepareStatement(sql2);

					tok1 = ps2.executeUpdate(); 
				}

				if (tok1 > 0) {
					tok1 = 0;

					sql3 = "delete from bwfl_license.fl2_stock_trxn_bwfl_20_21 where int_bwfl_id = "
							+ act.getBwflId()
							+ " and vch_gatepass_no = '"
							+ gatepass + "'";
					System.out.println("=4444====ak"+sql3);
					// sql3 = " delete from bwfl_license.fl2_stock_trxn_bwfl "
					// + " WHERE vch_gatepass_no='" + gatepass
					// + "' AND int_dissleri_id='" + act.getDist_id() + "' ";

					ps3 = con.prepareStatement(sql3);
					tok1 = ps3.executeUpdate();

					 
				}

				if (tok1 > 0) {

					sql4 = " DELETE FROM bwfl_license.fl36_dispatch_casecode_fl2d_20_21 WHERE vch_gatepass_no='"
							+ gatepass + "' ";
					System.out.println("==555===ak"+sql4);
					ps3 = con.prepareStatement(sql4);
					ps3.executeUpdate();

					 
				}
				
			/*	if(tok1 > 0){
					
					  tok1 =0 ;
						
						 sql5 = " DELETE FROM distillery.duty_register_19_20 " +
						 		" WHERE gatepass = '"+gatepass+"'" ;
							System.out.println("==55665===ak"+sql5);
						 ps6 = con.prepareStatement(sql5);

							tok1 = ps6.executeUpdate();
							
							 
					}
				*/
				

				if (tok1 > 0) {
					con.commit();
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

		public void saveCSV(BWFLGatepass36AccidentalAction action)
				throws IOException {
			Connection con = null;
			PreparedStatement stmt = null;
			
			String query = " INSERT INTO bwfl_license.fl36_dispatch_casecode_fl2d_20_21(vch_gatepass_no, vch_casecode)VALUES (?, ?) ";

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
								
								gatepass = sd.trim();
							}

							else// line number >1
							{
								
								if (gatepass.trim().equalsIgnoreCase(action.getScanGatePassNo().trim())) {
									//////System.out.println("get csv gatepass="+gatepass.trim());
									System.err.println("----casecode" + sd);
									if(sd.trim().length()==34)
									{
										casecode =	sd.trim().substring(0, 12)+"1"+sd.trim().substring(12, sd.trim().length());
										
										
									}else{
									casecode = sd.trim();
									}
									
									//if(this.etin(casecode.trim().substring(0, 12), action)==true){
										if(this.etin(casecode.trim().substring(0, 12),casecode.trim().substring(23, 26), action)==true){
										FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
												" Casecode-"+casecode.trim()+" does not belongs to the brands for the selected gatepass!" ," Casecode-"+casecode.trim()+" does not belongs to the brands for the selected gatepass!"));	
									}else{ 
									//////System.out.println("lineNumber="+lineNumber);
									stmt.setString(1, gatepass.trim());
									stmt.setString(2, casecode.trim());
									stmt.addBatch();
									}
									//status = stmt.executeUpdate();
								} else {
									//////System.out.println("flag=1");
									flag = 1;
								}
							}

						}

						tokenNumber = 0;
					}
				}

				//////System.out.println("----------------" + status);
				if (flag == 0) {
					status=stmt.executeBatch().length;
					if (status > 0) {
						FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
										"File Uploaded Successfully","File Uploaded Successfully"));
					} else {
						FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"File Not Uploaded !!","File Not Uploaded !!"));
					}
				} else {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Kindly Enter Same Gatepass Number ","Kindly Enter Same Gatepass Number "));
				}

			} catch (Exception ex) {
				ex.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
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
		public boolean getCascodeMatch(String casecode,String etin,Date date_plan,BWFLGatepass36AccidentalAction action)
		{
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			boolean flag=false;
			String sql=""; 
			sql="select * from bottling_unmapped.bwfl WHERE casecode='"+casecode+"' and etin='"+etin+"' and fl11_date is not null and " +
					" date_plan='"+Utility.convertUtilDateToSQLDate(date_plan)+"' " +
							//"and boxing_seq is not null" +
							" and fl36_date is  null ";
			//////System.out.println("select="+sql);
			try{
			conn=ConnectionToDataBase.getConnection20_21();
			pstmt=conn.prepareStatement(sql);
			//pstmt.setString(1, casecode);
			//pstmt.setString(2, etin);
			rs=pstmt.executeQuery();
			if(rs.next())
			{//////System.out.println("unmap flag true");
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
		public String getCascodeBrand(String etin)
		{
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String brand_name="";
			String sql="";
			
			sql="select a.brand_name from distillery.brand_registration_20_21 a,distillery.packaging_details_20_21 b where " +
				"b.brand_id_fk=a.brand_id and code_generate_through='"+etin+"'";
			//////System.out.println("brand name casecode="+sql);
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
		return brand_name;
		}
		

		//-------------------------------------------------------------------------
		public ArrayList indentDetail(BWFLGatepass36AccidentalAction act, String val) {
			ArrayList list = new ArrayList();
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				String query = " SELECT   b.user_id,d.brand_name, c.package_name, b.indent_no, b.cr_date, b.int_brand_id,  " +
							   " b.int_pckg_id,(b.no_of_box-coalesce(b.finalize_indent,0)) as no_of_box   "+
							   " from  fl2d.indent_for_wholesale a,fl2d.indent_for_wholesale_trxn b,"+
	                           " distillery.packaging_details_20_21  c,distillery.brand_registration_20_21 d "+
	                           " where a.indent_no=b.indent_no and "+
							   " c.package_id=b.int_pckg_id and a.vch_licence_no= '"+val+"'and a.vch_action_taken='A' and"+
							   " d.brand_id=b.int_brand_id and a.unit_id='"+act.getBwflId()+"'  and  b.no_of_box-coalesce(b.finalize_indent,0) >0 ";

				conn = ConnectionToDataBase.getConnection();

				ps = conn.prepareStatement(query);

				rs = ps.executeQuery();
				
		System.out.println("query=indent=="+query);
				
			
				int i=1;
				while (rs.next()) 
				{
					BWFLGatepass36Accidentaldt dt = new BWFLGatepass36Accidentaldt();
					dt.setSnoIndent(i);
					dt.setIndentNo(rs.getString("indent_no"));
					dt.setIndentDate(rs.getString("cr_date"));
					dt.setBrandName(rs.getString("brand_name"));
					dt.setPackageSize(rs.getString("package_name"));
					dt.setDispatchboxIndent(rs.getInt("no_of_box"));
					dt.setBrandIdIndent(rs.getInt("int_brand_id"));
					dt.setPackageIdIndent(rs.getInt("int_pckg_id"));
				
					
				
					 
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
		public ArrayList displaylistImpl2( BWFLGatepass36AccidentalAction act) {
			ArrayList list = new ArrayList();
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String selQr = null;
			String brand = "";
			String packag = "";
			String indentno = "";
			int i = 1;

			try 
			{ 

				int indentedBox=0;	
				boolean flag=false;
						
							 
							conn = ConnectionToDataBase.getConnection();
							
							
							int j=0;

							 
							
			/*selQr = " SELECT distinct  a.package_name, a.package_id,  c.brand, b.brand_name,"
					+ " c.package, c.stock_bottles-coalesce(c.dispatch_bottles,0)  as avlbottle," +
					"(c.stock_box-c.dispatch_box) as dispatchd_box, d.box_size ,("+indentno+" )x) as  indentno  "
					
					+ " FROM distillery.packaging_details_20_21 a, distillery.brand_registration_20_21 b, "
					+ " bwfl.fl2_stock_20_21 c, bwfl.box_size_details d "
					+ " WHERE a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.brand "
					+ " AND a.package_id=c.package AND b.brand_id=c.brand "
					+ " AND c.int_dist_id='"
					+ act.getBreId()
					+ "' "
					+ "   AND c.stock_bottles-COALESCE(c.dispatch_bottles,0)>0 "
					+ " AND a.box_id=d.box_id AND a.quantity=d.qnt_ml_detail  "
					+ " and     b.brand_id  in ("+brand +"0)  and a.package_id in ("+packag +"0) "+
					"group by   a.package_name, "+
						" b.brand_name, a.package_id ,c.brand,c.package, c.stock_bottles,c.dispatch_bottles, "+
						" c.stock_box,c.dispatch_box, d.box_size ,c.int_dist_id  ";*/
							
							selQr = " SELECT DISTINCT c.int_brand_id, b.brand_name,c.int_pack_id, a.package_name, c.seq,c.licence_no,  "
									+ " c.int_recieved_bottles-coalesce(c.dispatch_36,0) as avlbottle ,a.duty, a.adduty, "
									+ " ((c.int_planned_bottles-coalesce(c.dispatch_36,0))/c.box_size) as dispatchd_box, c.box_size  "
									+ " FROM distillery.packaging_details_20_21 a, distillery.brand_registration_20_21 b, "
									+ " bwfl_license.mst_receipt_20_21 c " 
									
									+ " WHERE a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.int_brand_id  "
									+ " AND a.package_id=c.int_pack_id AND b.brand_id=c.int_brand_id  AND c.int_bwfl_id='"
									+ act.getBwflId()
									+ "' "
									+ " AND COALESCE(c.dispatch_36,0)<c.int_planned_bottles "+
									" and c.int_recieved_bottles-coalesce(c.dispatch_36,0)>0  " ;
							
			
					 ps = conn.prepareStatement(selQr);

					rs = ps.executeQuery();
					while (rs.next()) {
						BWFLGatepass36Accidentaldt dt = new BWFLGatepass36Accidentaldt();
						
						dt.setInt_brand_id(rs.getInt("int_brand_id"));
						dt.setInt_pckg_id(rs.getInt("int_pack_id"));
						dt.setVch_brand(rs.getString("brand_name"));
						dt.setInt_bottle_avaliable(rs.getInt("avlbottle"));
						dt.setPackageName(rs.getString("package_name"));
						dt.setSeq(rs.getInt("seq"));
						dt.setSize(rs.getInt("box_size"));
						dt.setBoxAvailable(rs.getInt("dispatchd_box"));
						dt.setBalance(rs.getInt("avlbottle"));
						dt.setLicenseNodt(rs.getString("licence_no"));
						dt.setDb_duty(rs.getDouble("duty"));
						dt.setDb_add_duty(rs.getDouble("adduty"));
						dt.setSlno(i);
					 
						
										
						list.add(dt);
						i++ ;
					}
			
			} catch (Exception e) 
			{
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
		public boolean getIndentData(String indentNo, int bId, int pId, int disptachBox) {

			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			boolean b=false;
			try {
				String query =  " SELECT  indent_no,int_brand_id, int_pckg_id, "+
								" no_of_box FROM fl2d.indent_for_wholesale_trxn "+
								" where indent_no='"+indentNo+"' and int_brand_id='"+bId+"' and  int_pckg_id='"+pId+"' " +
										" and no_of_box >='"+disptachBox+"'  ";

				conn = ConnectionToDataBase.getConnection();

				ps = conn.prepareStatement(query);

				rs = ps.executeQuery();
				
			System.out.println("query=indent=="+query);
				
			
				if (rs.next()) 
				{
					b=true;
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
			return b;
		}

		
		
		
		public String checklic(BWFLGatepass36AccidentalAction ac) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String f = "";
			String sql = "";
			try {
				con = ConnectionToDataBase.getConnection();

				sql =  "  SELECT license_id,district,name_of_license,address,  "+
						" (select description from public.district where districtid=district::int) as districtname "+
						"  FROM hotel_bar_rest.registration_for_hotels_bars_restraunt_20_21  "+
						"  where   id='"+ac.getBrc_to_lic()+"' and vch_verify_flag='V' ";
						
						/*
						" SELECT * FROM hotel_bar_rest.registration_for_hotels_bars_restraunt "
						+ " where district='"
						+ ac.getDistrictLic()
						+ "' and id='"+ac.getBrc_to_lic()+"' and vch_verify_flag='V'";*/

				 

				int i = 0;

				pstmt = con.prepareStatement(sql);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					i = 1;
					f = "F";
					
					ac.setLicenseeId(rs.getInt("license_id"));
					ac.setLicenseeName(rs.getString("name_of_license"));
					ac.setDistrictLic(rs.getInt("district"));
					
				//	ac.setLicenseeAdrs(rs.getString("address"));
					ac.setLicenseeDistrict(rs.getString("districtname"));
					
				
					
				/*	ac.setLicenseeId(rs.getInt("license_id"));
					ac.setLicenseeName(rs.getString("name_of_license"));
					ac.setDistrictLic(rs.getInt("districtid"));
					ac.setLicenseeDistrict(rs.getString("district"));*/
					
					if (rs.getString("address") != null)
					{
						ac.setLicenseeAdrs(rs.getString("address"));
					}

				}

				
				ac.setFlagLic(true);
				
				
				if (i == 0) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("No record found on this HBR ID",
									"No record found on this HBR ID"));
					//ac.setShopName("");
					ac.setLicenseeName("");
					ac.setLicenseeAdrs("");

				}

			} catch (SQLException se) {
				se.printStackTrace();
			}

			finally {
				try

				{
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
			return f;
		}
		public String checkIndentData(String gatepass,int pkg,int bid,BWFLGatepass36AccidentalAction ac) 
		{
			String b="xyz";
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String query =" select  a.indent_no " +
						"   from bwfl_license.wholesale_indent_gatepass b ,fl2d.indent_for_wholesale_trxn a  " +
						" WHERE b.vch_gatepass ='"+gatepass.trim()+"' and  " +
						" a.indent_no=b.indent_no and a.int_brand_id=b.brand_id and " +
						" a.int_pckg_id=b.package_id  and b.package_id="+pkg+" " +
								"and b.brand_id="+bid+" and b.int_distillery_id="+ac.getBwflId()+" ";
				
				conn = ConnectionToDataBase.getConnection();

				ps = conn.prepareStatement(query);

				rs = ps.executeQuery();
				
				 
				
				while (rs.next()) 
				{
					b=rs.getString("indent_no");
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
			return b;
		}
	}


