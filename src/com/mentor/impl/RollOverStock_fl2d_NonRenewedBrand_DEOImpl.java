package com.mentor.impl;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

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

import com.mentor.action.RollOverStock_fl2d_NonRenewedBrand_DEOAction;
import com.mentor.datatable.RollOverStock_fl2d_NonRenewedBrand_DEODT;
import com.mentor.datatable.Rollover_Stock_20_21_fl2_DEODT;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class RollOverStock_fl2d_NonRenewedBrand_DEOImpl {
	
	

	public String getDetails(RollOverStock_fl2d_NonRenewedBrand_DEOAction action) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList = " SELECT districtid,description FROM public.district where " +
					" deo='"+ ResourceUtil.getUserNameAllReq().trim() + "' ";

			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				action.setDistrict_id(rs.getString("districtid"));
				action.setDistrict_name(rs.getString("description"));
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
	
	// ======================get unit list============================

		public ArrayList getUnitListImpl(RollOverStock_fl2d_NonRenewedBrand_DEOAction action) {

			ArrayList list = new ArrayList();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String selQr = "";
			SelectItem item = new SelectItem();
			
			 item.setLabel("--select--"); 
			 item.setValue(""); 
			 list.add(item);
			 

			try {
				System.out.println("====radioo==="+action.getRadio_type());
				

					selQr = "SELECT distinct int_app_id,vch_firm_name,vch_license_type  FROM licence.fl2_2b_2d_19_20 a,fl2d.rollover_stock_for_fl2d_non_renew_brand b " +
							" where a.int_app_id=b.unit_id and  b.deo_flg='T' and vch_license_type  in ('FL2D')" +
							"  and  core_district_id='"+action.getDistrict_id()+"' ORDER BY vch_firm_name ";
				

				

				System.out.println("---fgg==unit name---------------- " + selQr);

				conn = ConnectionToDataBase.getConnection();
				pstmt = conn.prepareStatement(selQr);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					item = new SelectItem();

					item.setValue(rs.getString("int_app_id"));
					item.setLabel(rs.getString("vch_firm_name")+"-"+rs.getString("vch_license_type"));


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
	
		
		public ArrayList displaylist(RollOverStock_fl2d_NonRenewedBrand_DEOAction action) {
			ArrayList list = new ArrayList();
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String query = null;
		double 	total_duty1=0;
		double mrp1=0;
			int i = 1;

			try {
				
				query = /*" select a.seq,b.brand_name, a.rollover_boxes,a.rollover_bottles,c.package_name," +
						
						" 0 as size, " +  
						" c.duty as old_duty,c.adduty as old_adduty,d.duty as new_duty,d.adduty as new_adduty," +
						" (a.rollover_bottles*((d.adduty-c.adduty)+(d.duty-c.duty))) as diff_duty,(a.rollover_bottles*((c.mrp*2)/100)) as mrp,a.finaliz_flg, " +
						" a.unit_id,  a.brand_id, a.package_id    " +
						"  from 	fl2d.rollover_fl_stock_non_renew_brand a," +
						" distillery.brand_registration_19_20 b,  distillery.packaging_details_19_20 c," +
						" distillery.packaging_details_20_21 d where " +
						" a.brand_id=b.brand_id and b.brand_id=c.brand_id_fk and c.package_id=a.package_id " +
						" and a.etin_new=d.code_generate_through and a.deo_flg='T' and a.unit_id='"+action.getUnitID()+"'";*/
						
						
						"select a.excel_flg,a.excel_path,a.unit_type,a.unit_id,a.old_unit_id,a.seq,e.brand_id as new_brand_id,d.package_id as new_packg_id ,b.brand_name,(e.brand_name||d.package_name) as newbrand," +
						" a.rollover_boxes,a.rollover_bottles,c.package_name, a.size,d.code_generate_through as new_etin,c.code_generate_through as old_etin  ,                                                               "+
						"c.permit as old_duty,c.adduty as old_adduty,d.permit as new_duty,d.adduty as new_adduty,                                            "+
						//"(a.rollover_bottles*((d.adduty-c.adduty)+(d.duty-c.duty))) as diff_duty,                                                        "+
						"(a.rollover_bottles*(d.permit-c.permit)) as diff_duty,                                                        "+
						"(a.rollover_bottles*((c.mrp*2)/100)) as mrp,a.finaliz_flg,  a.unit_id,  a.brand_id, a.package_id                                "+
						"from 	fl2d.rollover_stock_for_fl2d_non_renew_brand a, distillery.brand_registration_19_20 b,                                         "+
						"distillery.packaging_details_19_20 c, distillery.packaging_details_20_21 d ,distillery.brand_registration_20_21 e               "+
						"where  a.brand_id=b.brand_id and b.brand_id=c.brand_id_fk and c.package_id=a.package_id                                         "+
						"and a.etin_new=d.code_generate_through and e.brand_id=d.brand_id_fk     and a.unit_id='"+action.getUnitID()+"'";	
						
						
						
						

				
				System.out.println("ATUL QUERY "+query);
				 conn = ConnectionToDataBase.getConnection();
				ps = conn.prepareStatement(query);

				rs = ps.executeQuery();
				
				
				while (rs.next()) {
					RollOverStock_fl2d_NonRenewedBrand_DEODT dt = new RollOverStock_fl2d_NonRenewedBrand_DEODT();
					
					dt.setSrno(i);
					dt.setExcelFlag(rs.getString("excel_flg"));
					dt.setExcelPath(rs.getString("excel_path"));
					dt.setUnit_type(rs.getString("unit_type"));
					dt.setUnit_id(rs.getString("unit_id"));
					dt.setOld_unit_id(rs.getString("old_unit_id"));
					dt.setOld_etin(rs.getString("old_etin"));
					dt.setNew_etin(rs.getString("new_etin"));
					dt.setBrand_id(rs.getInt("brand_id"));
					dt.setBrand_name(rs.getString("brand_name"));
					dt.setNew_brand_name(rs.getString("newbrand"));
					dt.setNew_brand_id(rs.getInt("new_brand_id"));
					dt.setNew_package_id(rs.getInt("new_packg_id"));
					dt.setPackage_id(rs.getInt("package_id"));
					dt.setSize(rs.getInt("size"));
					dt.setRolloverbox(rs.getInt("rollover_boxes"));
					dt.setRolloverbottles(rs.getInt("rollover_bottles"));
					dt.setOld_duty(rs.getDouble("old_duty"));
					dt.setOld_adduty(rs.getDouble("old_adduty"));
					dt.setNew_duty(rs.getDouble("new_duty"));
					dt.setNew_adduty(rs.getDouble("new_adduty"));
					dt.setMrp(rs.getDouble("mrp"));
					/*mrp1+=rs.getDouble("mrp");
					action.setTotal_mrp(mrp1);
					total_duty1+=dt.getDiff_duty();
					action.setTotal_duty(total_duty1);*/
					if(rs.getDouble("diff_duty") > 0){
						dt.setDiff_duty(rs.getDouble("diff_duty"));
					}else{
					dt.setDiff_duty(0);
					}
					if(rs.getString("finaliz_flg")!=null && rs.getString("finaliz_flg").length() >0 && rs.getString("finaliz_flg").equalsIgnoreCase("T")){
						//action.setSubmit_flg("T");
						dt.setCheckbox_flg("T");
					}else{
						///action.setSubmit_flg("F");
						dt.setCheckbox_flg("F");
					}
					
					list.add(dt);

					i++;
					//action.setViewflg("T");
					dt.setSeq(rs.getInt("seq"));
					/*mrp1+=dt.getMrp();
					action.setTotal_mrp(mrp1);
					total_duty1+=dt.getDiff_duty();
					action.setTotal_duty(total_duty1);*/

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
		public ArrayList displaychallanlist(RollOverStock_fl2d_NonRenewedBrand_DEOAction action) {
			ArrayList list = new ArrayList();
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String query = null;
		double 	total_duty1=0;
		double mrp1=0;
			int i = 1;

			try {

				query = " select challan_no, challan_dt from fl2d.rollover_fl_stock_challan_detail where  unit_id='"+action.getUnitID()+"' and" +
						" unit_type='"+action.getRadio_type()+"'";

				System.out.println("selQr1============get data==========" + query);
				conn = ConnectionToDataBase.getConnection();
				ps = conn.prepareStatement(query);

				rs = ps.executeQuery();
				while (rs.next()) {
					RollOverStock_fl2d_NonRenewedBrand_DEODT dt = new RollOverStock_fl2d_NonRenewedBrand_DEODT();
					
					dt.setSrNo_challan(i);
					dt.setChallanname(rs.getString("challan_no"));
					dt.setChallan_date(rs.getDate("challan_dt"));
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
	
		
		public boolean validatechallan(RollOverStock_fl2d_NonRenewedBrand_DEOAction action,
				RollOverStock_fl2d_NonRenewedBrand_DEODT dt) {
			boolean flag = false;
			Connection conn = null;
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			double chalanamot = 0;

			

			try {
				conn = ConnectionToDataBase.getConnection();

				if (action.getChallantable().size() > 0) {
					for (int i = 0; i < action.getChallantable().size(); i++) {

						RollOverStock_fl2d_NonRenewedBrand_DEODT dt1 = (RollOverStock_fl2d_NonRenewedBrand_DEODT) action
								.getChallantable().get(i);
						String sql = "select  a.vch_challan_id,vch_total_amount from licence.mst_challan_master a,licence.challan_head_details b where a.vch_challan_id='"
								+ dt1.getChallanname().trim()
								+ "' and "+
							
								" b.amount=a.vch_total_amount::numeric "
								+ "and b.vch_challan_id=a.vch_challan_id  	"
								+ "and b.vch_reference_id=a.vch_reference_id "
								+ " and  a.vch_trn_status='SUCCESS' and a.vch_challan_id not in "
								+ " (select challan_no::character varying from  fl2d.rollover_fl_stock_challan_detail  )";
						 
						pstmt = conn.prepareStatement(sql);
 
						rs = pstmt.executeQuery();
						if (rs.next()) {
							chalanamot = chalanamot + rs.getDouble("vch_total_amount");
							 
						}

					}

				}
			 if (chalanamot != 0) {

				 if(action.getRadio_type().equalsIgnoreCase("BWFL")){
                     if (chalanamot >= action.getTotal_mrp()) {
       flag = true;
       } else {
       FacesContext.getCurrentInstance().addMessage(
       null,
       new FacesMessage(
       "Challan Amount Mismatch Found For Fee! ",
       "Challan Amount Mismatch Found For Fee!"));
       }
                      }else{
if (chalanamot >= (action.getTotal_mrp() + action.getTotal_duty())) {
flag = true;
} else {
FacesContext.getCurrentInstance().addMessage(
null,
new FacesMessage(
"Challan Amount Mismatch Found For Fee! ",
"Challan Amount Mismatch Found For Fee!"));
}
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

		public boolean updatechallandetail(RollOverStock_fl2d_NonRenewedBrand_DEOAction action,
				RollOverStock_fl2d_NonRenewedBrand_DEODT dt) {
			ArrayList list = new ArrayList();
			boolean flag = false;
			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt1 = null;
			PreparedStatement pstmt2 = null;
			int i = 0;
			int ii = 0;
			int iii = 0;

	
					
			String sql2 = "INSERT INTO fl2d.rollover_fl_stock_challan_detail(challan_no, challan_dt,unit_id,unit_type,pckg_id) VALUES (?, ?, ?, ?, ?);";
			try {

				conn = ConnectionToDataBase.getConnection();
				conn.setAutoCommit(false);

				for (int l = 0; l < action.getDisplaylist().size(); l++) {

					RollOverStock_fl2d_NonRenewedBrand_DEODT dt2 = (RollOverStock_fl2d_NonRenewedBrand_DEODT) action
							.getDisplaylist().get(l);
					
					if(dt2.isCheckbox()){
						
					if (action.getChallantable().size() > 0)

					{
						for (int k = 0; k < action.getChallantable().size(); k++) {

							RollOverStock_fl2d_NonRenewedBrand_DEODT dt1 = (RollOverStock_fl2d_NonRenewedBrand_DEODT) action
									.getChallantable().get(k);
							
								
							pstmt1 = conn.prepareStatement(sql2);

							
							
							pstmt1.setInt(1, Integer.parseInt(dt1.getChallanname()));
							pstmt1.setDate(2, Utility.convertUtilDateToSQLDate(dt1.getChallan_date()));
							pstmt1.setInt(3, Integer.parseInt(action.getUnitID()));
							pstmt1.setString(4, action.getRadio_type());
							
							pstmt1.setInt(5, dt2.getSeq());
							
							
							ii = pstmt1.executeUpdate();

						System.out.println("iiiiiii "+ii);
							
						}
					}
					}
				}
			
				if(ii>0){
					String query=null;
			
					
					
						
					
						for (int l = 0; l < action.getDisplaylist().size(); l++) {

							RollOverStock_fl2d_NonRenewedBrand_DEODT dt2 = (RollOverStock_fl2d_NonRenewedBrand_DEODT) action
									.getDisplaylist().get(l);
							
							if(dt2.isCheckbox()){
								System.out.println("--fl2d---update===finaliz_flg=====");
					 query = "update  fl2d.rollover_stock_for_fl2d_non_renew_brand  set  finaliz_flg='T' where unit_id='"
							+ action.getUnitID()
							+ "'  "
							+ " and   brand_id='"
							+ dt2.getBrand_id()
							+ "'"
							+ " and package_id='"
							+ dt2.getPackage_id()
							+ "' and seq='"+dt2.getSeq()+"'    ";

						pstmt1 = conn.prepareStatement(query);
						iii = pstmt1.executeUpdate();
						System.out.println("iiiiiii1111111111111111 "+iii);
						}
						}
					
					
				
					}
				

				if (iii > 0) {
					boolean flag2=	finalize(action, dt);
					if(flag2==true){
					flag = true;
					 
					conn.commit();
					}
					else{conn.rollback();
						flag = false;
						 
					}
					
				} else {conn.rollback();
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
		

		
		public boolean finalize(RollOverStock_fl2d_NonRenewedBrand_DEOAction action,
				RollOverStock_fl2d_NonRenewedBrand_DEODT dt) {
			ArrayList list = new ArrayList();
		
			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt1 = null;
			ResultSet rs = null;
			ResultSet rs1=null;
			boolean flag1 = false;
			int i = 0;
		

	
					
			
			try {
				
				
				conn = ConnectionToDataBase.getConnection();
				conn.setAutoCommit(false);
			
				
					for (int l = 0; l < action.getDisplaylist().size(); l++) {

						RollOverStock_fl2d_NonRenewedBrand_DEODT dt2 = (RollOverStock_fl2d_NonRenewedBrand_DEODT) action.getDisplaylist().get(l);
						if(dt2.isCheckbox()){
							
				String queryDetail = " select unit_id,unit_type,brand_id,package_id,rollover_bottles,rollover_boxes,"+
				                     " size,seq from fl2d.rollover_stock_for_fl2d_non_renew_brand " +
				                     " where unit_id='"+action.getUnitID()+"' and  brand_id='"+dt2.getBrand_id()+"' and package_id='"+dt2.getPackage_id()+"' and size='"+dt2.getSize()+"' " +
				                     " and seq='"+dt2.getSeq()+"' ";
						
						/*"select id as unit_id, type as unit_type, brand_id as unit_brand_id, pckg_id as unit_pckg_id, " +
						" recv_total_bottels as unit_recv_total_bottels, dispatchbotl as unit_dispatchbotl, size as unity_size, " +
						" finaliz_flg, renwal_flg from " +
						" fl2d.fl2_2b_stock_19_20 b where id='"+action.getUnitID()+"' and brand_id='"+dt2.getBrand_id()+"' and " +
								" pckg_id='"+dt2.getPackage_id()+"' " +
										///" and  size='"+dt2.getSize()+"' " +
								
										" and renwal_flg='T'  ";*/
						
						
				System.out.println("-----select ==fl2d======"+queryDetail);
				
				System.out.println("NEW BRAND_ID"+dt2.getNew_brand_id()+"  package "+dt2.getNew_package_id());
				pstmt = conn.prepareStatement(queryDetail);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					String query="select int_fl2d_id, int_brand_id, int_pckg_id, size, avl_bottl, avl_box from fl2d.fl2d_stock_20_21 where  " +
							" int_fl2d_id='"+rs.getInt("unit_id")+"' and int_pckg_id='"+dt2.getNew_package_id()+"' " +
							" and size='"+rs.getInt("size")+"' and int_brand_id='"+dt2.getNew_brand_id()+"'  ";
							/*"select int_fl2d_id, int_brand_id, int_pckg_id, size, avl_bottl, avl_box from fl2d.fl2d_stock_20_21 where  " +
							" int_fl2d_id='"+rs.getInt("unit_id")+"' and int_pckg_id='"+rs.getInt("package_id")+"' " +
							" and size='"+rs.getInt("size")+"' and int_brand_id='"+rs.getInt("brand_id")+"'  ";*/
					
					System.out.println("-----check==FL2D======"+query);
					 pstmt = conn.prepareStatement(query);
					rs1 = pstmt.executeQuery();
				
				
				if (rs1.next()) {
					
					String whilequery=" update fl2d.fl2d_stock_20_21  set " +
							         " avl_bottl=fl2d.fl2d_stock_20_21.avl_bottl+'"+rs.getInt("rollover_bottles")+"',avl_box=fl2d.fl2d_stock_20_21.avl_box+'"+rs.getInt("rollover_boxes")+"'" +
									" where int_fl2d_id='"+rs1.getInt("int_fl2d_id")+"' and int_pckg_id='"+rs1.getInt("int_pckg_id")+"'" +
									"  and size='"+rs1.getInt("size")+"' and int_brand_id='"+rs1.getInt("int_brand_id")+"'";
					
					System.out.println("-----update==FL2D======"+whilequery);
					pstmt = conn.prepareStatement(whilequery);


					i = pstmt.executeUpdate();	
				}else{ 
					
					String whilequery="INSERT INTO fl2d.fl2d_stock_20_21 (int_fl2d_id, int_brand_id, int_pckg_id, size, avl_bottl, avl_box, dispatch_bottle, dispatch_box)" +
							" values ('"+rs.getInt("unit_id")+"','"+dt2.getNew_brand_id()+"','"+dt2.getNew_package_id()+"','"+rs.getInt("size")+"'," +
							" '"+rs.getInt("rollover_bottles")+"',  '"+rs.getInt("rollover_boxes")+"',0,0) " ;
							
							
							/*"INSERT INTO fl2d.fl2d_stock_20_21 (int_fl2d_id, int_brand_id, int_pckg_id, size, avl_bottl, avl_box, dispatch_bottle, dispatch_box)" +
							" values ('"+rs.getInt("unit_id")+"','"+rs.getString("brand_id")+"','"+rs.getInt("package_id")+"','"+rs.getInt("size")+"'," +
							" '"+rs.getInt("rollover_bottles")+"',  '"+rs.getInt("rollover_boxes")+"',0,0) " ;*/

			
					System.out.println("-----insert==FL2D======"+whilequery);		
				
					
					pstmt1 = conn.prepareStatement(whilequery);
					i = pstmt1.executeUpdate();	
				}
					
					
					
					
				
					/*String q="INSERT INTO fl2d.fl2d_stock_20_21 (int_fl2d_id, int_brand_id, int_pckg_id, size, avl_bottl, avl_box, dispatch_bottle, dispatch_box,remark)" +
							" values ('"+rs.getInt("unit_id")+"','"+rs.getString("brand_id")+"','"+rs.getInt("package_id")+"','"+rs.getInt("size")+"'," +
							" '"+rs.getInt("rollover_bottles")+"',  '"+rs.getInt("rollover_boxes")+"',0,0,'Rollover Stock Non-Renewed Brand FL2D ') " ;
							
							"INSERT INTO fl2d.fl2_2b_stock_20_21 ( id, type, brand_id, pckg_id, recv_total_bottels, dispatchbotl, size,stock_box)" +
									" values ('"+rs.getInt("unit_id")+"','"+rs.getString("unit_type")+"','"+dt2.getNew_brand_id()+"','"+dt2.getNew_package_id()+"'," +
									" '"+dt2.getRolloverbottles()+"', 0, '"+rs.getInt("unity_size")+"' , '"+dt2.getRolloverbox()+"') ON CONFLICT  on CONSTRAINT" +
									" fl2_2b_receiving_stock_master_manage_pkey_20_21 " +
									" do update set recv_total_bottels=fl2d.fl2_2b_stock_20_21.recv_total_bottels+'"+dt2.getRolloverbottles()+"'," +
									" stock_box=fl2d.fl2_2b_stock_20_21.stock_box+'"+dt2.getRolloverbox()+"' ";
					
					pstmt = conn.prepareStatement(q);
					
					System.out.println("-----insert and update ==fl2d====== "+q);

					i = pstmt.executeUpdate();	*/	   
						
				}
						}
				}
					

				System.out.println("======fl2d======");
				
				if (i > 0) {
					flag1=true;

					System.out.println("------commit======");
					conn.commit();
				} else {
					flag1=false;
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Some thing went wrong!!!!!", "Some thing went wrong!!!!!"));
				}

			} catch (Exception e) {
				e.printStackTrace();

			} finally {

				try {
					conn.close();
					if (pstmt != null) {
						pstmt.close();
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

			return flag1;
		}
		
		public String dfinalizedata(RollOverStock_fl2d_NonRenewedBrand_DEOAction action ) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int saveStatus = 0;
			String query =null;
			

			// System.out.println("====update========"+action.getUnit_id());
			try { 
				
				conn = ConnectionToDataBase.getConnection();
				conn.setAutoCommit(false);
				
		
				 query =" delete from  fl2d.rollover_stock_for_fl2d_non_renew_brand where "+
				 		   " unit_id='"+action.getUnitID()+"'  ";
		
			System.out.println("----delete 11111======"+query);
				 pstmt = conn.prepareStatement(query);
				 
				 saveStatus=pstmt.executeUpdate();
							
					
						
					
					if (saveStatus > 0) {
						saveStatus=0;
						String query1=null;
						
					
						 query1 = "update  fl2d.mst_stock_receive_19_20  set  final_flag=null where int_fl2d_id= '" +action.getUnitID()+"' " ;
						 
				
					System.out.println("====delete22222==="+query1);
					pstmt = conn.prepareStatement(query1);
					  saveStatus= pstmt.executeUpdate(); 
					} 
					if (saveStatus > 0) {
						saveStatus=0;
						String query2 =null;
						
						
					
						query2 = "	delete FROM fl2d.rollover_stock_fl2d_cases_non_renew_brand  where  unit_id="+action.getUnitID();
					
					System.out.println("=====delete333===="+query2);
					  pstmt = conn.prepareStatement(query2);
					  saveStatus= pstmt.executeUpdate();
						 
					} 
					
					
					if (saveStatus > 0) {
						 conn.commit();
						 action.reset();
							FacesContext.getCurrentInstance().addMessage(
									null,
									new FacesMessage(FacesMessage.SEVERITY_ERROR,
											"Rejected successfully ",
											"Rejected successfully "));
					}
					else {

						conn.rollback();
						/*ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
								Constants.NOT_SAVED);
							*/
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"Not Rejected !!!!! ",
										"Not Rejected !!!!! "));
					}
				}  catch (Exception e) {e.printStackTrace();} finally {
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
		
	
public void dataFinalize(RollOverStock_fl2d_NonRenewedBrand_DEOAction action,
		RollOverStock_fl2d_NonRenewedBrand_DEODT dt) {
	Connection conn = null;
	Connection conn1 = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	PreparedStatement pstmt3 = null;
	PreparedStatement pstmt4 = null;
	PreparedStatement pstmt5 = null;
	PreparedStatement pstmt6 = null;
	PreparedStatement pstmt7 = null;
	String gtinNo = "";
	long boxsize = 0;
	long caseno = 0;
	int status=0;
	int status5=0;
	int status6=0;
	int status7=0;
	int planid= this.getMax_id();
	


	String sql=
			
			
			
			        "INSERT INTO bottling_unmapped.fl2d( "+
					"date_plan,etin,serial_no_start, serial_no_end,  casecode,plan_id,unit_id,fl11gatepass,fl11_date) "+
					"VALUES (?, ?, ?, ?, ?, "+planid+",?,'RolloverSctock Not Register for FL2D','"+Utility.convertUtilDateToSQLDate(new Date())+"')";
	
	String update ="UPDATE fl2d.rollover_stock_for_fl2d_non_renew_brand "
			+ " SET   finalized_flag='F' ,finalized_date=?"
			
			+ "WHERE unit_id=? and brand_id=? and package_id=? and plandate=? and planid=? ";

			
			
			/* "UPDATE fl2d.mst_stock_receive_19_20 "
			+ " SET   finalized_flag='F' ,finalized_date=? "
			+ "WHERE seq=? and permit_no=? and int_brand_id=? and int_pack_id=?";*/

	try {
	
		gtinNo=dt.getNew_etin();
		

		
		
		// long	serialno = dt.getSeqfrm();
	long	serialno = getSerialNoFl2D(new Double(dt.getRolloverbox()*dt.getRolloverbottles()).longValue(), new Date());
		///long	serialno=getSerialNoFl2D(new Double(dt.getBottles()).longValue(),new Date());
		
		
		
		//////System.out.println("gtinNo" + gtinNo + "seqqqqqqqqqqqqqqqqqqqqqqqq"+ serialno);
		if (!gtinNo.equals("") && serialno != 0) {
			conn = ConnectionToDataBase.getConnection20_21();
			conn1 = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			conn1.setAutoCommit(false);
			
			String update1 = "UPDATE fl2d.rollover_stock_for_fl2d_non_renew_brand "
					+ " SET   seqfrom=?,seqto=?,caseseq=?, plandate=?, planid=? " 
					
					+ "WHERE unit_id='"+dt.getUnit_id()+"' and brand_id='"+dt.getBrand_id()+"' and package_id='"+dt.getPackage_id()+"'  " +
						
									" and seq='"+dt.getSeq()+"' and size='"+dt.getSize()+"' and rollover_boxes='"+dt.getRolloverbox()+"' ";
			
			pstmt5 = conn1.prepareStatement(update1);
		    pstmt5.setLong(1, 0);
			pstmt5.setLong(2, 0);
		    pstmt5.setLong(3,0);
			pstmt5.setDate(4, Utility.convertUtilDateToSQLDate(new Date()));
			pstmt5.setInt(5, planid);
			status5 = pstmt5.executeUpdate();
			
			pstmt1 = conn1.prepareStatement(update);
			pstmt1.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			pstmt1.setInt(2, Integer.parseInt(dt.getUnit_id()));
			pstmt1.setInt(3, dt.getBrand_id());
			pstmt1.setInt(4, dt.getPackage_id());
			pstmt1.setDate(5, new java.sql.Date(System.currentTimeMillis()));
			pstmt1.setInt(6, planid);
			
			status = pstmt1.executeUpdate();

			/*if (dt.getLicence_type().equals("FL2D")
					&& status > 0) {*/
			if (status > 0) {
				status = 0;
				
				 
				//////System.out.println("dt.getShowDataTable_Quntity()"+dt.getShowDataTable_Quntity()+"  dt.getShowDataTable_NoOfBoxes()"+dt.getShowDataTable_NoOfBoxes());
				pstmt1 = conn.prepareStatement(sql);
				//pstmt2 = conn.prepareStatement(unmapped_sql);
				///for (int i = 0; i < Long.parseLong(dt.getS_box()); i++) {
				for (int i = 0; i < dt.getRolloverbox(); i++) {
					//caseno = dt.getCaseseq()+i;
                  
					caseno =getcaseNoFl2d(new Date());
					

					

					///pstmt1.setDate(1, Utility.convertUtilDateToSQLDate(new Date()));
					pstmt1.setDate(1, Utility.convertUtilDateToSQLDate(new Date()));
					pstmt1.setString(2, gtinNo);
					pstmt1.setString(3, StringUtils.leftPad(String.valueOf(serialno), 8, '0'));
					pstmt1.setInt(6, Integer.parseInt(action.getUnitID()));
					
					Random rand1 = new Random();
					//int n1 = 10+rand1.nextInt(90) ;
					int n1=		rand1.nextInt((60 - 51) + 1) + 51;
					pstmt1.setString(5,n1+""+StringUtils.leftPad(String.valueOf(caseno), 6, '0')+getCheckDigit(n1+""+StringUtils.leftPad(String.valueOf(caseno), 6, '0')) );
					
				
						pstmt1.setString(4,	 StringUtils.leftPad(String.valueOf(serialno+(dt.getRolloverbottles() / dt.getRolloverbox()) - 1), 8, '0'));
						serialno += (dt.getRolloverbottles() / dt.getRolloverbox());
					
					pstmt1.addBatch();
					/*int newsize=new Double((Long.parseLong(String.valueOf(dt.getBottles()))/Long.parseLong(String.valueOf(dt.getS_box())))).intValue();
					
                    pstmt1.setDate(1, new java.sql.Date(System.currentTimeMillis()));
					pstmt1.setString(2, gtinNo);
					pstmt1.setString(3, StringUtils.leftPad(String.valueOf(serialno), 8, '0'));
					
					 pstmt1.setString(4,StringUtils.leftPad(String.valueOf(serialno+(newsize-1)), 8, '0') );
					 Random rand1 = new Random();
					 int n1 = 10+rand1.nextInt(90) ;
                     pstmt1.setString(5, n1+""+StringUtils.leftPad(String.valueOf(caseno), 6, '0')+getCheckDigit(n1+""+StringUtils.leftPad(String.valueOf(caseno), 6, '0')));
					pstmt1.setInt(6, planid);
					pstmt1.setInt(7, Integer.parseInt(dt.getOld_unit_id()));
				   
					serialno+=newsize;
					pstmt1.addBatch();*/
					
					
				}
			}
			
			int[] status1=pstmt1.executeBatch();
			
			if (status1.length > 0) {
				status = 0;
				boolean flag = write(dt, action, conn,planid,conn1);

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
				
				String update11 = "UPDATE fl2d.rollover_stock_for_fl2d_non_renew_brand "
						+ " SET   excel_flg='T' " 
						
						+ "WHERE unit_id='"+dt.getUnit_id()+"' and brand_id='"+dt.getBrand_id()+"' and package_id='"+dt.getPackage_id()+"'  " +
							
										" and seq='"+dt.getSeq()+"' and size='"+dt.getSize()+"' and rollover_boxes='"+dt.getRolloverbox()+"'  ";
				
					
				pstmt6 = conn1.prepareStatement(update11);
				status6 = pstmt6.executeUpdate();
			}
				
				
				if (status6 > 0) {

			//if (status > 0) {

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
			if (pstmt5 != null)
				pstmt5.close();
			if (pstmt6 != null)
				pstmt6.close();
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




public boolean write(RollOverStock_fl2d_NonRenewedBrand_DEODT dt,
		RollOverStock_fl2d_NonRenewedBrand_DEOAction action, Connection conn,int planid,Connection conn1) {

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

		

		//if (dt.getLicence_type().equals("FL2D")) {

			pstmt = conn.prepareStatement(fl3);

			
			
			pstmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			
			pstmt.setString(2, dt.getNew_etin());
			pstmt.setInt(3, planid);
			rs = pstmt.executeQuery();
			//////System.out.println("excecute query fl3");

		//}
		
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

			//String lic = dt.getLicence_no().replaceAll("/", "");

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
			noOfUnit=StringUtils.leftPad(String.valueOf(dt.getRolloverbottles() / dt.getRolloverbox()), 3, '0');
			
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

				
			 
			 
			 
				if(count==(dt.getRolloverbottles() / dt.getRolloverbox()))
					
				{
			
			System.out.println("come inn nn time ");
			count=0;
			bottle_code="";
				}
		
		
		count++;
			 
			 
			 
			 
			 
			 
			

		}
		String path =relativePath + "//ExciseUp//RolloverStock//Excel//"+planid+""
				+ dt.getNew_etin() + "" + date + ".xls";
		/*fileOut = new FileOutputStream(relativePath + "//ExciseUp//RolloverStock//Excel//"+planid+""
				+ dt.getNew_etin() + "" + date + ".xls");*/
		fileOut = new FileOutputStream(path);
System.out.println("=====file name===="+planid+""+ dt.getNew_etin() + "" + date + ".xls");
		XSSFRow row1 = worksheet.createRow((int) k + 1);

		// APoolFinancialReportDataTable dt=(APoolFinancialReportDataTable)
		// list.get(i-2);

		XSSFCell cellA1 = row1.createCell((int) 0);
		cellA1.setCellValue("End");

		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
		///flag = true;
		if(updateExcelPath( dt,path,conn1))
		{
	System.out.println("update patttt1111");
flag = true;
		}


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







public synchronized long getcaseNoFl2d(Date date) {
	

	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	long serialNo = 0;
	long currseq = 0;

	try {
		conn = ConnectionToDataBase.getConnection20_21();
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
		conn = ConnectionToDataBase.getConnection20_21();
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
	
public boolean updateExcelPath(RollOverStock_fl2d_NonRenewedBrand_DEODT dt,String path,Connection conn1)

{
	boolean flag=false;
	//Connection conn=null;
	PreparedStatement pstmt=null;
	String sql=
	
	
	"UPDATE fl2d.rollover_stock_for_fl2d_non_renew_brand                      "+
	"SET   excel_path=?                                                 "+
	"WHERE etin=? and  etin_new=? and brand_id=? and package_id=?       "+
	"and rollover_bottles=? and rollover_boxes=? and unit_id=?          ";
	                                              
	try{
		//conn=ConnectionToDataBase.getConnection();
		pstmt=conn1.prepareStatement(sql);
		pstmt.setString(1, path);
		pstmt.setString(2, dt.getOld_etin());
		pstmt.setString(3, dt.getNew_etin());
		pstmt.setInt(4,dt.getBrand_id());
		pstmt.setInt(5,dt.getPackage_id());
		pstmt.setInt(6,dt.getRolloverbottles());
		pstmt.setInt(7, dt.getRolloverbox());
		pstmt.setInt(8, Integer.parseInt(dt.getUnit_id()));
		int i=pstmt.executeUpdate();
		System.out.println("UPDATE EXCEL PATH");
		if(i>0)
		{
			System.out.println("update patttt");
			flag=true;
		}
		System.out.println("UPDATE EXCEL PATH1111");
	}catch(Exception e)
	{
		e.printStackTrace();
	}finally{
		
		try{
			//if(conn!=null)conn.close();
			if(pstmt!=null)pstmt.close();
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	return flag;
	
}	
	
	
	
	
	

}
