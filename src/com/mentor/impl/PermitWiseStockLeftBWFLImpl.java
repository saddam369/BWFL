package com.mentor.impl;

import java.io.BufferedReader;
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
import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.mentor.action.GatepassToDistrictBWFLAction;
import com.mentor.action.PermitWiseStockLeftBWFLAction;
import com.mentor.datatable.GatepassToDistrictBWFLDT;
import com.mentor.datatable.PermitWiseStockLeftBWFLDataTable;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class PermitWiseStockLeftBWFLImpl {
	
	
	
	// =====================get details of distillery ======================

		public int getId(PermitWiseStockLeftBWFLAction act) {

			int id = 0;
			Connection con = null;
			Connection con2 = null;
			PreparedStatement ps = null, ps1 = null;
			ResultSet rs = null, rs1 = null;
			String sql = "";

			int rolDist = 0;
			int rolBrewry = 0;

			try {

				con = ConnectionToDataBase.getConnection();

				sql = 	" SELECT int_id, vch_distillery_contact_number, vch_license_type, vch_firm_name, vch_firm_add "+
						" FROM bwfl.registration_of_bwfl_lic_holder "+
						" WHERE vch_approval='V' AND "+
						" mobile_number='" + ResourceUtil.getUserNameAllReq().trim() + "' ";
				
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();

				if (rs.next()) {

					act.setDistillery_id(rs.getInt("int_id"));
						act.setDistillery_nm(rs.getString("vch_firm_name"));
					act.setDistillery_adrs(rs.getString("vch_firm_add"));
					if (rs.getString("vch_license_type").equals("1")) {
						act.setLicenceType("BWFL2A");
					} else if (rs.getString("vch_license_type").equals("2")) {
						act.setLicenceType("BWFL2B");
					} else if (rs.getString("vch_license_type").equals("3")) {
						act.setLicenceType("BWFL2C");
					} else if (rs.getString("vch_license_type").equals("4")) {
						act.setLicenceType("BWFL2D");
					}
				}

			} catch (Exception se) {
				se.printStackTrace();
			} finally {
				try {
					if (ps != null)
						ps.close();
					if (con != null)
						con.close();
					if (con2 != null)
						con2.close();

				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			return id;

		}
		
	
		
		
		
		//================================= permit list ================================
		
		
		
		
	
		
					public ArrayList getpermitList(PermitWiseStockLeftBWFLAction action)
					{
						Connection conn = null;
						PreparedStatement pstmt = null;
						ResultSet rs=null;
						
						String query=null;
						
						
						
						ArrayList list=new ArrayList();
						SelectItem item=new SelectItem();
					
						item.setLabel("--Select--");
						item.setValue("0");
						list.add(item);
						try
						
						{
							
								/*query =  " select distinct  permitno  	" +
										" FROM bwfl_license.mst_bottling_plan where int_distillery_id='"+action.getDistillery_id()+"' and scan_upload_flag is null ";
						*/
							
						
							query=
									
						"	select distinct  permitno FROM bwfl_license.mst_bottling_plan  "+
						"   where int_distillery_id='"+action.getDistillery_id()+"' and scan_upload_flag is null  "+
						"   and permitno not in ((select distinct permit_no from  bwfl_license.permit_wise_stock_left_for_manual_permit where bwfl_id='"+action.getDistillery_id()+"'))  ";
							
							
							
								
						 System.out.println("----------- permit list    query"+query);
							
							
						conn = ConnectionToDataBase.getConnection();
						pstmt=conn.prepareStatement(query);
						rs=pstmt.executeQuery();
						while(rs.next())
						{
						item=new SelectItem();
						item.setValue(rs.getString("permitno"));
						item.setLabel(rs.getString("permitno"));
						list.add(item);
						}
						
						}catch(Exception e)
						{
							e.printStackTrace();
						}
						finally
						{
				      		try
				      		{	
				      			
				          		if(conn!=null)conn.close();
				          		if(pstmt!=null)pstmt.close();
				          		if(rs!=null)rs.close();
				          		
				          		
				      		}
				      		catch(Exception e)
				      		{
				      			e.printStackTrace();
				      		}
				      	}
					return list;	
					}
					
				
					
		
		
					//===================show data in first datatable========================
					
					
					
					public ArrayList getData(PermitWiseStockLeftBWFLAction act)
					{

						ArrayList list = new ArrayList();
						Connection con = null;
						PreparedStatement ps = null;
						ResultSet rs = null;
						int i =1;

						String sql = 
								
							"	SELECT distinct a.int_distillery_id, a.int_brand_id, a.int_pack_id, a.int_quantity, a.int_planned_bottles, a.int_boxes, "+ 
							"	a.int_liquor_type, a.vch_license_type , a.plan_dt, a.licence_no, a.cr_date, a.finalized_flag, a.finalized_date,  "+
							"	a.received_liqour, a.permitno, a.permitdt, a.recieved_bottles, a.recieved_boxes, a.seq, a.gatepass_no,  "+
							"	a.bottling_seq_frm, a.bottling_seq_to, a.scan_upload_flag, a.exp_order_nmbr, a.exp_order_dt, a.transprt_vehicle_nmbr, "+
							"	a.route_details, a.maped_unmaped_flag, a.breakage, a.maped_unmaped_type, a.etin, group_id, a.validity_date, "+
							"	a.permitno_entered, a.casecode_seq_frm, a.casecode_seq_to, "+
							"	concat(b.brand_name,'-' ,c.package_name ) as brand_pack_name , c.code_generate_through , d.box_size "+
							"	FROM bwfl_license.mst_bottling_plan a, distillery.brand_registration  b , distillery.packaging_details c , distillery.box_size_details d "+
							"	where a.int_distillery_id=b.int_bwfl_id and  a.int_brand_id=b.brand_id  "+
							"	and a.int_pack_id=c.package_id and b.brand_id = c.brand_id_fk  and c.quantity=d.qnt_ml_detail" +
							" and a.int_distillery_id='"+act.getDistillery_id()+"' and permitno='"+act.getPermit_Id()+"' ";
						
						
						System.out.println(act.getDistillery_id()+" sql--------------"+sql);

						try {
							con = ConnectionToDataBase.getConnection();
							ps = con.prepareStatement(sql);
						//	ps.setInt(1, act.getDistillery_id());
							rs = ps.executeQuery();
							while (rs.next()) {

								PermitWiseStockLeftBWFLDataTable  dt = new PermitWiseStockLeftBWFLDataTable();
							//	dt.setShowDataTable_Date(rs.getDate("plan_dt"));
								dt.setPer_Seq(i);
								dt.setPer_Brand_Id(rs.getInt("int_brand_id"));
								dt.setPer_Pckg_Id(rs.getInt("int_pack_id"));
								dt.setPer_Brand_Pack_Name(rs.getString("brand_pack_name"));
								dt.setPer_etin_No(rs.getString("code_generate_through"));
								dt.setPer_PermitNo(rs.getString("permitno"));
								dt.setPer_Size(rs.getInt("box_size"));
								
								dt.setPer_Pland_Date(rs.getDate("plan_dt"));
								dt.setPer_liquor_type(rs.getInt("int_liquor_type"));
								dt.setPer_LicenceNo(rs.getString("licence_no"));
								dt.setPer_Seq_mst_btl_pln(rs.getInt("seq"));
								dt.setPer_quantity(rs.getInt("int_quantity"));
								
								
								
								
								
								/*if (rs.getDate("finalized_date") != null) {
								Date dat = Utility.convertSqlDateToUtilDate(rs.getDate("plan_dt"));
								
								DateFormat formatter = new SimpleDateFormat("yyMMdd");
								String date = formatter.format(dat);
								dt.setFinalizedDateString(date);
									
								}*/
								
								list.add(dt);
								
								i++;
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
					
		
		
		//============================= save 1 ==========================================
					
					
					
					
					public String saveData_Permit(PermitWiseStockLeftBWFLAction action)
					{
						Connection conn = null;
						PreparedStatement pstmt = null, ps1=null,ps2=null,ps3=null,ps4=null;
						ResultSet rs=null;
						int saveStatus=0;
						
						try
						{
					    
					     String pak_queryDetail=	
					    		 
					    		" INSERT INTO bwfl_license.permit_wise_stock_left_for_manual_permit( "+
					    		" permit_no, brand_pack_name, brand_id, packg_id, size, box_cese,  left_qty, etin_no, bwfl_id, "+ 
					    		"   bwfl_type, created_date, created_by , plan_date, int_liquor_type, seq_mst_btl_pln, licence_no, int_quantity, finalze_Flag) "+
					    		"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, 'N') ";
					     
					     conn = ConnectionToDataBase.getConnection();
						conn.setAutoCommit(false);
						
						if(action.getPermitWiseBrandPackShow().size()>0)
						{
							for(int i=0;i<action.getPermitWiseBrandPackShow().size();i++)
							{
								
							PermitWiseStockLeftBWFLDataTable table=new PermitWiseStockLeftBWFLDataTable();
							table=(PermitWiseStockLeftBWFLDataTable)action.getPermitWiseBrandPackShow().get(i);
						
							
							
							String query = 	" INSERT INTO bwfl_license.mst_receipt(int_bwfl_id, int_brand_id, int_pack_id, " +
									" int_quantity, int_planned_bottles, int_planned_boxes, int_liquor_type, vch_license_type, " +
									" plan_dt, licence_no, cr_date, received_by, remarks, receiving_date, " +
									" received_liqour, received_from_usr, seq, dispatch_36, box_size, int_recieved_bottles, breakage,gatepass,permitno) VALUES " +
									" (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,'"+table.getPer_PermitNo()+"')";

					String query5 = " SELECT * FROM bwfl_license.receipt_stock where int_brand_id="+table.getPer_Brand_Id()+" " +
									" AND int_pckg_id="+table.getPer_Pckg_Id()+" AND int_bwfl_id="+action.getDistillery_id()+" " +
									" AND vch_lic_no='"+table.getPer_LicenceNo()+"' and permit='"+table.getPer_PermitNo()+"'";

					String query3 = " INSERT INTO bwfl_license.receipt_stock(int_bwfl_id, int_dispatched_botls, " +
									" int_dispatched_cases, int_stock_botls,   vch_lic_no, vch_lic_type, " +
									" int_brand_id, int_pckg_id,permit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )";

							
					String queryy = " UPDATE bwfl_license.receipt_stock SET int_stock_botls=?   " +
									" WHERE int_brand_id="+table.getPer_Brand_Id()+" AND int_pckg_id="+table.getPer_Pckg_Id()+" " +
									" AND int_bwfl_id="+action.getDistillery_id()+" AND vch_lic_no='"+table.getPer_LicenceNo()+"' and permit='"+table.getPer_PermitNo()+"'";


					String updtQr = " UPDATE bwfl_license.mst_bottling_plan " +
									" SET recieved_bottles=?  " +
									" WHERE int_brand_id="+table.getPer_Brand_Id()+" AND int_pack_id="+table.getPer_Pckg_Id()+" " +
									" AND int_distillery_id="+action.getDistillery_id()+" AND licence_no='"+table.getPer_LicenceNo()+"' " +
									" AND plan_dt='"+table.getPer_Pland_Date()+"' AND int_quantity='"+table.getPer_quantity()+"' and permitno='"+table.getPer_PermitNo()+"' ";
							
						
							pstmt=conn.prepareStatement(pak_queryDetail);
							pstmt.setString(1, action.getPermit_Id());
							pstmt.setString(2, table.getPer_Brand_Pack_Name());
							pstmt.setInt(3, table.getPer_Brand_Id());
							pstmt.setInt(4, table.getPer_Pckg_Id());
							
							pstmt.setInt(5, table.getPer_Size());
							pstmt.setInt(6, table.getPer_box_Case());
							
							pstmt.setInt(7, table.getPer_Left_Qty());
							pstmt.setString(8, table.getPer_etin_No());
							pstmt.setInt(9, action.getDistillery_id());
							pstmt.setString(10, action.getLicenceType());
							
							pstmt.setDate(11, Utility.convertUtilDateToSQLDate(new Date()));
							pstmt.setString(12, ResourceUtil.getUserNameAllReq()); 
							
							pstmt.setDate(13, Utility.convertUtilDateToSQLDate(table.getPer_Pland_Date()));
							pstmt.setInt(14, table.getPer_liquor_type());
							pstmt.setInt(15, table.getPer_Seq_mst_btl_pln());
							pstmt.setString(16, table.getPer_LicenceNo());
							pstmt.setInt(17, table.getPer_quantity());
							
							saveStatus=pstmt.executeUpdate();
							
							if(saveStatus>0)
							{
								saveStatus=0;
								System.out.println("------ 1111111111  -----");
								pstmt = conn.prepareStatement(query);
								
								pstmt.setInt(1, action.getDistillery_id());			
								pstmt.setInt(2, table.getPer_Brand_Id());
								pstmt.setInt(3, table.getPer_Pckg_Id());
								pstmt.setInt(4, table.getPer_quantity());
								pstmt.setInt(5, table.getPer_Left_Qty());
								pstmt.setInt(6, table.getPer_box_Case());
								pstmt.setInt(7, table.getPer_liquor_type());
								pstmt.setString(8, action.getLicenceType());
								pstmt.setDate(9, Utility.convertUtilDateToSQLDate(table.getPer_Pland_Date()));
								pstmt.setString(10, table.getPer_LicenceNo());
								pstmt.setDate(11, Utility.convertUtilDateToSQLDate(new Date()));
								pstmt.setString(12, "Manual");
								pstmt.setString(13, "Manual");			
								pstmt.setDate(14, Utility.convertUtilDateToSQLDate(new Date()));
								pstmt.setInt(15, 0);
								pstmt.setLong(16, 0000);
								pstmt.setInt(17, this.mst_receipt()+i);
								pstmt.setInt(18,0);
								pstmt.setInt(19,table.getPer_Size());
								pstmt.setInt(20, table.getPer_Left_Qty());
							//	pstmt.setInt(21, table.getPer_box_Case()); // breakage
								pstmt.setInt(21, 0);
								pstmt.setInt(22, 000);

								saveStatus = pstmt.executeUpdate();	
								
							}
							
							
							if(saveStatus>0){
								saveStatus=0;
								System.out.println("------ 2222222222  -----");
								ps2=conn.prepareStatement(query5);				
								rs = ps2.executeQuery();				
								if(rs.next()){
									System.out.println("------ 33333333333  -----");
									ps1 = conn.prepareStatement(queryy);
								//	ps1.setInt(1, dt.getInt_planned_bottles());
									ps1.setInt(1, table.getPer_Left_Qty());
								 		
									saveStatus = ps1.executeUpdate();
									
								}else{	
									System.out.println("------ 4444444444444  -----");
									
									ps3 = conn.prepareStatement(query3);
									ps3.setInt(1, action.getDistillery_id());
									ps3.setInt(2,0);					
									ps3.setInt(3,0);
								//	ps3.setInt(4,dt.getInt_planned_bottles());
									ps3.setInt(4, table.getPer_Left_Qty());
								 		
									ps3.setString(5,table.getPer_LicenceNo());
									ps3.setString(6,action.getLicenceType());
									ps3.setInt(7, table.getPer_Brand_Id());
									ps3.setInt(8,table.getPer_Pckg_Id());
									ps3.setString(9,table.getPer_PermitNo());
									saveStatus = ps3.executeUpdate();
									
								}
							}
							
							
							
							if(saveStatus>0)
							{System.out.println("------ 55555555555  -----");
								saveStatus=0;
								ps4 = conn.prepareStatement(updtQr);
							//	ps4.setInt(1, dt.getInt_planned_bottles());
								ps4.setInt(1, table.getPer_Left_Qty());
								saveStatus = ps4.executeUpdate();
								
							}
							
							
							
							
							
							
							}
						}
						
						
						
						if(saveStatus>0)
						{
							System.out.println("------ 66666666666666  -----");
							
							conn.commit();
							action.reset();
							ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY, Constants.SAVED_SUCESSFULLY);
							action.reset();

						}
						else
						{
							action.reset();
							conn.rollback();
							ResourceUtil.addErrorMessage(Constants.NOT_SAVED, Constants.NOT_SAVED);
					
						}
						}catch(Exception e)
						{
							e.printStackTrace();
							FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
						}
						finally
						{
				      		try
				      		{	
				      			
				          		if(conn!=null)conn.close();
				          		if(pstmt!=null)pstmt.close();
				          		if(rs!=null)rs.close();
				          		
				          		
				      		}
				      		catch(Exception e)
				      		{
				      			e.printStackTrace();
				      		}
				      	}
					return "";	
					}
					
					
					
					
					
				
					public ArrayList get_After_saveData_Show(PermitWiseStockLeftBWFLAction act)
					{

						ArrayList list = new ArrayList();
						Connection con = null;
						PreparedStatement ps = null;
						ResultSet rs = null;
						int i =1;

						String sql = 
								
							"	SELECT seq, permit_no, brand_pack_name, brand_id, packg_id, size, box_cese, left_qty, etin_no, bwfl_id, bwfl_type, created_date, created_by, finalze_Flag "+
							"	FROM bwfl_license.permit_wise_stock_left_for_manual_permit where bwfl_id ='"+act.getDistillery_id()+"' " ;
						
					//	System.out.println(  "---------  after save   sql--------------"+sql);

						try {
							con = ConnectionToDataBase.getConnection();
							ps = con.prepareStatement(sql);
						//	ps.setInt(1, act.getDistillery_id());
							rs = ps.executeQuery();
							while (rs.next()) {

								PermitWiseStockLeftBWFLDataTable  dt = new PermitWiseStockLeftBWFLDataTable();
							//	dt.setShowDataTable_Date(rs.getDate("plan_dt"));
								dt.setSav_Seq(i);
								dt.setSav_Created_Date(rs.getDate("created_date"));
								dt.setSav_Brand_Id(rs.getInt("brand_id"));
								dt.setSav_Pckg_Id(rs.getInt("packg_id"));
								dt.setSav_Brand_Pack_Name(rs.getString("brand_pack_name"));
								dt.setSav_etin_No(rs.getString("etin_no"));
								dt.setSav_PermitNo(rs.getString("permit_no"));
								dt.setSav_Left_Qty(rs.getInt("left_qty"));
								dt.setSav_Size(rs.getInt("size"));
								dt.setSav_box_Case(rs.getInt("box_cese"));
								dt.setSav_finlize_Flag(rs.getString("finalze_Flag"));
								
								
								
								/*if (rs.getDate("finalized_date") != null) {
								Date dat = Utility.convertSqlDateToUtilDate(rs.getDate("plan_dt"));
								
								DateFormat formatter = new SimpleDateFormat("yyMMdd");
								String date = formatter.format(dat);
								dt.setFinalizedDateString(date);
									
								}*/
								
								list.add(dt);
								
								i++;
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
					
					
					
					
					
					public int mst_receipt(){
						Connection con = null;
						PreparedStatement ps = null;
						ResultSet rs = null;
						String query = "Select max(seq) as id from bwfl_license.mst_receipt";
						int id=0;
						try{
							con = ConnectionToDataBase.getConnection();
							ps = con.prepareStatement(query);
							rs = ps.executeQuery();
							if(rs.next()){
								id = rs.getInt("id");
							}
						}catch (Exception e) {
							e.printStackTrace();
						} finally {
							try {
								if (ps != null)ps.close();
								if (rs != null)rs.close();
								if (con != null)con.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						return id+1;
					}		
		
		
	
					
					
					
					//=================================== csv upload code ================================
					
					
					
				

					public void saveCSV(PermitWiseStockLeftBWFLAction action)throws IOException {
							
						Connection con = null;
						PreparedStatement stmt = null;
						
						String query = " INSERT INTO bwfl_license.permit_wise_dispatch_casecode_bwfl(permit_no, permit_case_code, etin_no)VALUES (?, ?, ?) ";

						String permit = "";
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
											
											permit = sd.trim();
										}

										else// line number >1
										{
											
											if (permit.trim().equalsIgnoreCase(action.getScan_PermitNo().trim())) {

												System.err.println("----casecode" + sd);
												casecode = sd;
												
												
												if(this.etin(casecode.trim().substring(2, 15), action)==true){
												
												FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
															" Casecode-"+casecode.trim()+" does not belongs to the brands for the selected Permit!" ," Casecode-"+casecode.trim()+" does not belongs to the brands for the selected Permit!"));	
													
												}else{ 
												
												stmt.setString(1, permit.trim());
												stmt.setString(2, casecode.trim());
												stmt.setString(3, casecode.trim().substring(2, 15));
												stmt.addBatch();
												
												//status = stmt.executeUpdate();
												}
												
												
												System.out.println("------------------"+casecode.trim().length());
														
												

												/*
												if(casecode.trim().length()==39){
												
													stmt.setString(1, permit.trim());
													stmt.setString(2, casecode.trim());
													stmt.addBatch();
											
												}else{ 
													FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
															" Casecode-"+casecode.trim()+" does not belongs to the brands for the selected Permit!" ," Casecode-"+casecode.trim()+" does not belongs to the brands for the selected Permit!"));	
												}
												*/
												
												
											} else {
												flag = 1;
											}
										}

									}

									tokenNumber = 0;
								}
							}

							System.out.println("----------------" + status);
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
												"Kindly Enter Same Permit Number ","Kindly Enter Same Permit Number "));
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
					
					
				
					
					
					
					
					
					
					
					
					public boolean  etin(String casecode,PermitWiseStockLeftBWFLAction act) {
						 
						Connection con = null;
						PreparedStatement pstmt = null, ps2 = null;
						ResultSet rs = null, rs2 = null;
						boolean s = false;
						try {
							con = ConnectionToDataBase.getConnection();
				 
						
						/*	String query = " SELECT distinct b.code_generate_through FROM bwfl_license.fl2_stock_trxn_bwfl a, distillery.packaging_details b "
									+ " WHERE b.package_id = a.int_pckg_id  and a.vch_gatepass_no='"+ act.getScanGatePassNo().trim()+ "' and b.code_generate_through='"+casecode+"'";

						*/
							
							String query = " SELECT distinct b.code_generate_through FROM bwfl_license.mst_bottling_plan a, distillery.packaging_details b "
									+ " WHERE b.package_id = a.int_pack_id  and a.permitno='"+ act.getScan_PermitNo().trim()+ "' and b.code_generate_through='"+casecode+"'";

							
							
							
							
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
					
					
					
					
		//============================================
					
					
					
					
					// ==========================get excel data from temporary
					// table============================

					public ArrayList getExcelData(PermitWiseStockLeftBWFLAction action) {

						ArrayList list = new ArrayList();

						Connection con = null;
						PreparedStatement stmt = null;
						ResultSet rs = null;

						String query = " SELECT permit_no, permit_case_code FROM bwfl_license.permit_wise_dispatch_casecode_bwfl "
								+ " WHERE permit_no='"
								+ action.getScan_PermitNo()
								+ "' ";

						try {
							con = ConnectionToDataBase.getConnection();
							stmt = con.prepareStatement(query);
							rs = stmt.executeQuery();

							while (rs.next()) {
								PermitWiseStockLeftBWFLDataTable dt = new PermitWiseStockLeftBWFLDataTable();

								dt.setGatepass(rs.getString("permit_no"));
								dt.setCasecode(rs.getString("permit_case_code"));

								list.add(dt);

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

					// ---------------------count casecode in // excel------------------------------
					

					public int excelCases(PermitWiseStockLeftBWFLAction act) {

						int id = 0;
						Connection con = null;
						PreparedStatement pstmt = null, ps2 = null;
						ResultSet rs = null, rs2 = null;
						String s = "";
						try {
							con = ConnectionToDataBase.getConnection();

							String query = " SELECT count(*) as dispatchd_box FROM bwfl_license.permit_wise_dispatch_casecode_bwfl "
									+ " WHERE permit_no='"
									+ act.getScan_PermitNo().trim() + "'";

							pstmt = con.prepareStatement(query);

							System.out.println("query--------recieve------------" + query);

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

					public int recieveCases(PermitWiseStockLeftBWFLAction act) {

						int id = 0;
						Connection con = null;
						PreparedStatement pstmt = null, ps2 = null;
						ResultSet rs = null, rs2 = null;
						String s = "";
						try {
							con = ConnectionToDataBase.getConnection();

							String query = " SELECT SUM(box_cese) as dispatchd_box FROM bwfl_license.permit_wise_stock_left_for_manual_permit "
									+ " WHERE permit_no='"
									+ act.getScan_PermitNo().trim() + "'  ";

							pstmt = con.prepareStatement(query);
							System.out
									.println("query----------database   recieveCases  --------"
											+ query);
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
					
					
					
					
					
					//======================= Count  etin no =======================
					
					
					public int count_ETIN_No(PermitWiseStockLeftBWFLAction act) {

						int id = 0;
						Connection con = null;
						PreparedStatement pstmt = null, ps2 = null;
						ResultSet rs = null, rs2 = null;
						String s = "";
						try {
							con = ConnectionToDataBase.getConnection();

							String query = " SELECT count(etin_no) as count_etn FROM bwfl_license.permit_wise_stock_left_for_manual_permit "
									+ " WHERE permit_no='"
									+ act.getScan_PermitNo().trim() + "'  ";

							pstmt = con.prepareStatement(query);
							
							rs = pstmt.executeQuery();

							while (rs.next()) {

								id = (rs.getInt("count_etn"));

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
					
					
					
					
					
					
					public int temp_Count_ETIN(PermitWiseStockLeftBWFLAction act) {

						int id = 0;
						Connection con = null;
						PreparedStatement pstmt = null, ps2 = null;
						ResultSet rs = null, rs2 = null;
						String s = "";
						try {
							con = ConnectionToDataBase.getConnection();

							/*String query = " SELECT distinct count(SUBSTRING(permit_case_code, 2, 13)) as temp_count_etn  FROM bwfl_license.permit_wise_dispatch_casecode_bwfl "
									+ " WHERE permit_no='"
									+ act.getScan_PermitNo().trim() + "'";
							
							*/
							
							
							
							
							String query = " SELECT distinct etin_no  as temp_count_etn  FROM bwfl_license.permit_wise_dispatch_casecode_bwfl "
									+ " WHERE permit_no='"
									+ act.getScan_PermitNo().trim() + "'";
							
							

							pstmt = con.prepareStatement(query);

							System.out.println("query--------temp count etin------------" + query);

							rs = pstmt.executeQuery();

							while (rs.next()) {

							Long id_old = (rs.getLong("temp_count_etn")); //(rs.getInt("temp_count_etn"));
							id++;

							}
							
							System.out.println("--------- id  ---------------"+id);

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

					
					
					
					
					
					
					
					
					
					// ------------------- delete ---------------------

					public void deleteData(PermitWiseStockLeftBWFLAction act) {

						Connection con = null;
						PreparedStatement stmt = null;

						String query = " DELETE FROM bwfl_license.permit_wise_dispatch_casecode_bwfl WHERE permit_no='"
								+ act.getScan_PermitNo().trim() + "'  ";
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
					
					
					
					//========================================================
					
					
					public void deleteData_upload_CSv(PermitWiseStockLeftBWFLAction act) {

						Connection con = null;
						PreparedStatement stmt = null;

						String query = " DELETE FROM bwfl_license.permit_wise_dispatch_casecode_bwfl WHERE permit_no='"
								+ act.getScan_PermitNo().trim() + "'  ";
						int status = 0;
						try {
							con = ConnectionToDataBase.getConnection();
							stmt = con.prepareStatement(query);

							status = stmt.executeUpdate();
							if (status > 0) {
						//		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Cancelled Successfully ","Cancelled Successfully "));
										
							System.out.println("--------------- delete upload csv before  ---------");			
												
							} else {
						//		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Not Cancelled ", "Not Cancelled "));
										
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
					
					
					
					
					
					
					
					
					
					
					
					
					// =======================update on finalize===========================

					public boolean updateDispatch(PermitWiseStockLeftBWFLAction act) {

						int save = 0;
						//int j = 0;
						boolean val = false;
						PreparedStatement ps = null;
						Connection con = null;
						Connection con1 = null;
						String query = "";
						
						
						
						query = " INSERT INTO bottling_unmapped.bwfl (fl11gatepass, plan_id, date_plan,etin,fl11_date,casecode) " +
								" values (?,?,?,?,?,?) "+
								" ON CONFLICT (etin, casecode) DO UPDATE SET fl11gatepass=?, fl11_date=?  where bottling_unmapped.bwfl.fl11_date is null ";
						
						try {
							
							DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
							
							int j[] = null;
							con = ConnectionToDataBase.getConnection();
							con1 = ConnectionToDataBase.getConnection3();
							con.setAutoCommit(false);
							con1.setAutoCommit(false);
							ps = con1.prepareStatement(query);
							for (int i = 0; i < act.getGetVal().size(); i++) {
								PermitWiseStockLeftBWFLDataTable dt = (PermitWiseStockLeftBWFLDataTable) act.getGetVal().get(i);
												

								 ps.setString(1, dt.getGatepass().trim());
								 ps.setInt(2, 0);
								 ps.setDate(3, Utility.convertUtilDateToSQLDate(new Date()));
								 ps.setString(4,(dt.getCasecode().substring(2, 15)));
								 ps.setDate(5, Utility.convertUtilDateToSQLDate(new Date()));
								 ps.setString(6,dt.getCasecode().substring(29,dt.getCasecode().length()));
								 ps.setString(7, dt.getGatepass().trim());
								 ps.setDate(8, Utility.convertUtilDateToSQLDate(new Date()));

								ps.addBatch();
								
								System.out.println("save-------"+save +"dfgdfghd------------"+query);
								
							}
							j = ps.executeBatch();
							 
							save = j.length;
							
						
							if (act.getGetVal().size() == save && save>0) {
								save = 0;

								/*String updtQr = " UPDATE bwfl_license.gatepass_to_districtwholesale_bwfl SET vch_finalize='F', finalize_dt_time=? "
										+ " WHERE vch_gatepass_no='"
										+ act.getScan_PermitNo()
										+ "' ";
								*/
								
								
								
								String updtQr = " UPDATE bwfl_license.mst_bottling_plan SET scan_upload_flag='F'  " //, finalize_dt_time=? "
										+ " WHERE permitno='"+ act.getScan_PermitNo()+ "' "; 
										
										
								
								

								ps = con.prepareStatement(updtQr);
								//ps.setString(1, dateFormat.format(new Date()));

								 System.out.println("updtQr------------" + updtQr);
								save = ps.executeUpdate();

								 System.out.println("second status------------" + save);

								query = " DELETE FROM bwfl_license.permit_wise_dispatch_casecode_bwfl WHERE permit_no ='"
										+ act.getScan_PermitNo() + "' ";
								ps = con.prepareStatement(query);
								ps.executeUpdate();
								
								
								
								
							String	query_update = " update bwfl_license.permit_wise_stock_left_for_manual_permit set finalze_Flag='F' WHERE permit_no ='"
										+ act.getScan_PermitNo() + "' ";
							
								ps = con.prepareStatement(query_update);
								ps.executeUpdate();
								
								
								
								

								 System.out.println("----   delete query   ---------"+query);

							} else {
								save = 0;
							}
							if (save > 0) {
								val = true;
								act.setPermitWiseBrandPackShow_Flag(true);
								con.commit();
								con1.commit();
							} else {
								val = false;
								con.rollback();
								con1.rollback();
							}

						} catch (Exception ex) {
							ex.printStackTrace();
							FacesContext.getCurrentInstance().addMessage(null,
									new FacesMessage(ex.getMessage(), ex.getMessage()));
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
					
					
					
					
					

}
