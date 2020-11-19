package com.mentor.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.mentor.action.AdvanceDutyRegisterBWFL_FL2D_DEOAction;
import com.mentor.datatable.AdvanceDutyRegisterBWFL_FL2D_DEODT;
import com.mentor.datatable.AdvanceDutyRegisterDT;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class AdvanceDutyRegisterBWFL_FL2D_DEOImpl {

	private String login_type;

	public String getLogin_type() {
		return login_type;
	}

	public void setLogin_type(String login_type) {
		this.login_type = login_type;
	}

	public String getDetails(AdvanceDutyRegisterBWFL_FL2D_DEOAction ac) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		 
		try {
			String queryList="";
			con = ConnectionToDataBase.getConnection();
			//try {
				
					////System.out.println("2--"+ResourceUtil.getUserNameAllReq().trim()+"-");
				//if(Integer.parseInt(ResourceUtil.getUserNameAllReq().trim())>0){
					queryList = "select districtid from district where deo='"+ResourceUtil.getUserNameAllReq()+"'" ;
							   
				//}
			/*} catch (NumberFormatException e) {
				ac.setName(ResourceUtil.getUserNameAllReq().trim());
				ac.setDist_id(Integer.parseInt(ResourceUtil.getUserNameAllReq().trim().substring(10)));
				ac.setAddress("");
				this.setLogin_type(ResourceUtil.getUserNameAllReq().trim().substring(7,8));			
				e.printStackTrace();
			}*/
			System.out.println("Login="+queryList);
			pstmt = con.prepareStatement(queryList);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				//ac.setName(rs.getString("vch_applicant_name"));
				ac.setDist_id(rs.getInt("districtid"));
				//ac.setAddress(rs.getString("vch_firm_add"));
				
			} 
			
		
		} catch (SQLException se){
				se.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(se.getMessage(), se.getMessage()));
		} finally{
			try{
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

	public ArrayList getSeasonDetails(AdvanceDutyRegisterBWFL_FL2D_DEOAction ac) {
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

				ac.setSession_Name(rs.getString(2) + "-" + rs.getString(3));
				ac.setSessionId(rs.getInt(2));

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

	// --------------------------------- Data List ---------------------

	/*
	 * public static ArrayList getShowData_old(AdvanceDutyRegisterBWFL_FL2D_DEOAction ac)
	 * { ArrayList list = new ArrayList(); Connection conn = null;
	 * PreparedStatement pstmt = null; ResultSet rs = null; ResultSet rs2 =
	 * null; int i=1; double firstOpnig=0.0; int count=0;
	 * 
	 * 
	 * 
	 * try {
	 * 
	 * 
	 * conn = ConnectionToDataBase.getConnection();
	 * 
	 * 
	 * String Opning =
	 * 
	 * 
	 * "	select (sum(xx.challan_amt)-sum(xx.disptch_duty)) as opning from  "+
	 * "	( "+
	 * "	select x.date_challan_date , sum(x.challan_amt) as challan_amt , sum(x.disptch_duty) as disptch_duty  from "
	 * +
	 * "	(select a.date_challan_date, a.double_amt as challan_amt, 0 as disptch_duty  "
	 * + "	FROM public.challan_deposit a   "+
	 * "	where a.vch_assessment_year=(SELECT sesn_id FROM public.mst_financial_year where active='a')   "
	 * + "	and vch_challan_type='D' and a.challan_fee_type='"+ac.getType()+
	 * "' and a.int_distillery_id ='"
	 * +ac.getDist_id()+"'  and a.date_challan_date between '"
	 * +Utility.convertUtilDateToSQLDate
	 * (ac.getFromdate())+"' and '"+Utility.convertUtilDateToSQLDate
	 * (ac.getToOpningDate())+"' "+ "	union "+
	 * "	select b.date_crr_date, 0 as challan_amt, b.int_value as disptch_duty  "
	 * + "	FROM distillery.duty_register_19_20 b "+
	 * "	where b.financial_year:: text=(SELECT sesn_id FROM public.mst_financial_year where active='a')  "
	 * +
	 * "	and b.vch_duty_type='"+ac.getType()+"' and  b.int_distillery_id='"+ac.
	 * getDist_id
	 * ()+"' and b.date_crr_date between '"+Utility.convertUtilDateToSQLDate
	 * (ac.getFromdate
	 * ())+"' and '"+Utility.convertUtilDateToSQLDate(ac.getToOpningDate())+"' "
	 * +
	 * 
	 * "	union "+
	 * "	select c.date_crr_date, 0 as challan_amt, c.int_value as disptch_duty  "
	 * + "	FROM bwfl.duty_register_19_20 c "+
	 * "	where c.financial_year:: text=(SELECT sesn_id FROM public.mst_financial_year where active='a')  "
	 * +
	 * "	and c.vch_duty_type='"+ac.getType()+"' and  c.int_distillery_id='"+ac.
	 * getDist_id
	 * ()+"' and c.date_crr_date between '"+Utility.convertUtilDateToSQLDate
	 * (ac.getFromdate
	 * ())+"' and '"+Utility.convertUtilDateToSQLDate(ac.getToOpningDate())+"' "
	 * +
	 * 
	 * 
	 * ") x group by x.date_challan_date order by x.date_challan_date "+
	 * "	)xx ";
	 * 
	 * 
	 * 
	 * 
	 * //System.out.println("--------------- opning Query   Report ----------"+Opning
	 * );
	 * 
	 * 
	 * pstmt = conn.prepareStatement(Opning);
	 * 
	 * rs = pstmt.executeQuery();
	 * 
	 * if (rs.next()) {
	 * 
	 * firstOpnig=rs.getDouble("opning");
	 * 
	 * } rs.close();
	 * 
	 * 
	 * 
	 * 
	 * double bal=0.0;
	 * 
	 * String allData=
	 * 
	 * 
	 * 
	 * "		select x.date_challan_date as dt , sum(x.challan_amt) as challan_amt , sum(x.disptch_duty) as disptch_duty  from "
	 * +
	 * "		(select a.date_challan_date, a.double_amt as challan_amt, 0 as disptch_duty "
	 * + "		FROM public.challan_deposit a  "+
	 * "		where a.vch_assessment_year=(SELECT sesn_id FROM public.mst_financial_year where active='a') "
	 * + "		and vch_challan_type='D' and  a.challan_fee_type='"+ac.getType()+
	 * "' and  a.int_distillery_id ='"
	 * +ac.getDist_id()+"' and a.date_challan_date  between '"
	 * +Utility.convertUtilDateToSQLDate
	 * (ac.getFromdate())+"' and '"+Utility.convertUtilDateToSQLDate
	 * (ac.getTodate())+"'  "+ "		union "+
	 * "		select b.date_crr_date, 0 as challan_amt, b.int_value as disptch_duty  "
	 * + "		FROM distillery.duty_register_19_20 b  "+
	 * "		where b.financial_year:: text=(SELECT sesn_id FROM public.mst_financial_year where active='a')  "
	 * +
	 * "		and  b.vch_duty_type='"+ac.getType()+"' and b.int_distillery_id='"+ac
	 * .getDist_id
	 * ()+"' and b.date_crr_date between '"+Utility.convertUtilDateToSQLDate
	 * (ac.getFromdate
	 * ())+"' and '"+Utility.convertUtilDateToSQLDate(ac.getTodate())+"' " +
	 * 
	 * "		union "+
	 * "		select c.date_crr_date, 0 as challan_amt, c.int_value as disptch_duty  "
	 * + "		FROM bwfl.duty_register_19_20 c "+
	 * "		where c.financial_year:: text=(SELECT sesn_id FROM public.mst_financial_year where active='a')  "
	 * +
	 * "		and c.vch_duty_type='"+ac.getType()+"' and  c.int_distillery_id='"+ac
	 * .getDist_id
	 * ()+"' and c.date_crr_date between '"+Utility.convertUtilDateToSQLDate
	 * (ac.getFromdate
	 * ())+"' and '"+Utility.convertUtilDateToSQLDate(ac.getTodate())+"' " +
	 * 
	 * 
	 * ") x group by x.date_challan_date order by x.date_challan_date " ;
	 * 
	 * 
	 * 
	 * 
	 * //System.out.println("-----------------  All Data Query  Report -----------"
	 * +allData);
	 * 
	 * 
	 * 
	 * pstmt=conn.prepareStatement(allData); rs2=pstmt.executeQuery();
	 * 
	 * 
	 * 
	 * 
	 * while (rs2.next()) {
	 * 
	 * AdvanceDutyRegisterBWFL_FL2D_DEODT dt= new
	 * AdvanceDutyRegisterBWFL_FL2D_DEODT();
	 * 
	 * dt.setSrNo(i);
	 * 
	 * dt.setDate_Dt(rs2.getDate("dt"));
	 * 
	 * if(count==0) { //System.out.println("---------tttttt---------"+firstOpnig);
	 * 
	 * dt.setDate_Dt(Utility.convertUtilDateToSQLDate(ac.getFromdate()));
	 * 
	 * dt.setOpningBal(firstOpnig) ; dt.setProduceBal(0.0);
	 * dt.setDisptchBal(0.0); dt.setCancelBal(0.0); dt.setDeclarwstBal(0.0);
	 * dt.setBalanceTot(firstOpnig); count++; }else{
	 * 
	 * 
	 * //System.out.println("---------2222222222222---------"+bal); if(bal ==0){
	 * dt.setOpningBal(firstOpnig);
	 * 
	 * }else{ dt.setOpningBal(bal); }
	 * 
	 * 
	 * dt.setDate_Dt(rs2.getDate("dt"));
	 * dt.setProduceBal(rs2.getDouble("challan_amt"));
	 * dt.setDisptchBal(rs2.getDouble("disptch_duty")); //
	 * dt.setCancelBal(rs2.getDouble("cancel_qyy")); //
	 * dt.setDeclarwstBal(rs2.getDouble("declar_stock"));
	 * 
	 * 
	 * //
	 * dt.setBalanceTot(dt.getOpningBal()+dt.getProduceBal()-dt.getDisptchBal(
	 * )+dt.getDeclarwstBal()); //
	 * bal=dt.getOpningBal()+dt.getProduceBal()-dt.getDisptchBal
	 * ()+dt.getDeclarwstBal();
	 * 
	 * dt.setBalanceTot(dt.getOpningBal()+dt.getProduceBal()-dt.getDisptchBal());
	 * 
	 * bal=dt.getOpningBal()+dt.getProduceBal()-dt.getDisptchBal();
	 * 
	 * 
	 * }
	 * 
	 * i++;
	 * 
	 * list.add(dt);
	 * 
	 * }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } finally { try {
	 * 
	 * if (conn != null) conn.close(); if (pstmt != null) pstmt.close(); if (rs
	 * != null) rs.close();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } } return list; }
	 */

	// ------------------------- New ----------------------------

	public ArrayList getShowData(AdvanceDutyRegisterBWFL_FL2D_DEOAction ac) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		int i = 1;
		double firstOpnig = 0.0;
		//int count = 0;

		try {

			conn = ConnectionToDataBase.getConnection();
			
		
				firstOpnig = 0.0;
			
			

			double bal = 0.0;
			String allData ="";
			String filter1="";
			String filter2="";
			if(ac.getType().equalsIgnoreCase("FL2D_SOCIAL_DUTY")){
				filter1="BEER_SOCIAL_DUTY','FL_SOCIAL_DUTY','FL2D_SOCIAL_DUTY";
			}else{
				filter1=ac.getType();
			}
			 
		
			
			
			
			
			if(ac.getUnit_type().equalsIgnoreCase("FL2D")){	
				
				
				if(ac.getType().equalsIgnoreCase("FL2D_Permit")){
					
					filter2=" SELECT  b.cr_date, 0 as challan_amt, 'Import Fee on Permit ' || b.permit_nmbr as description, 'DT' as check_type,            "+  
							" SUM (b.import_fee) as disptch_duty FROM bwfl_license.import_permit_20_21 b WHERE b.bwfl_id='"+ac.getUnit_id()+"' and login_type='FL2D'  "+                                                                
							" AND b.cr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                                                                                     "+
							" AND '"+Utility.convertUtilDateToSQLDate(ac.getTodate())+"' and vch_approved='APPROVED'	                                                                         "+
							" GROUP BY  b.cr_date, description, check_type                                                                       "+
							" Union  " +
							" SELECT  b.cr_date, sum(amount) as challan_amt, 'Deposit Challan  - ' || b.chalan_no as description, 'CH' as check_type,  "+           
							" 0 as disptch_duty FROM bwfl_license.chalan_deposit_bwfl_fl2d b WHERE b.unit_id='"+ ac.getUnit_id()+ "' and unit_type='FL2D'                "+                                                 
							" and head_code in ('003900103030000','003900105040000') and g6_code in ('04','05') AND b.cr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'   "+
							" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' 	                                                "+
							" GROUP BY  b.cr_date, description, check_type            "          ;
						
					System.out.println("fl2d import ==="+filter2);
					}
					else if(ac.getType().equalsIgnoreCase("Special_fee_fl2D_permit_Beer")){
						
						filter2=" SELECT  b.cr_date, 0 as challan_amt, 'Special Fee on Permit ' || b.permit_nmbr as description, 'DT' as check_type,          "+    
								" SUM(b.special_fee) as disptch_duty FROM bwfl_license.import_permit_20_21 b WHERE b.bwfl_id='"+ac.getUnit_id()+"' and login_type='FL2D' "+                                                                 
								" AND b.cr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                                                                                     "+
								" AND '"+Utility.convertUtilDateToSQLDate(ac.getTodate())+"' and vch_approved='APPROVED'	                                                                       "+
								" GROUP BY  b.cr_date, description, check_type    " +
						        " UNION "+
								" SELECT  b.cr_date, sum(amount) as challan_amt, 'Deposit Challan  - ' || b.chalan_no as description, 'CH' as check_type,  "+           
								" 0 as disptch_duty FROM bwfl_license.chalan_deposit_bwfl_fl2d b WHERE b.unit_id='"+ ac.getUnit_id()+ "' and unit_type='FL2D'                "+                                                 
								" and head_code in ('003900103020000','003900105020000') and g6_code in ('15','22') AND b.cr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'   "+
								" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' 	                                                "+
								" GROUP BY  b.cr_date, description, check_type            "          ;
						
						System.out.println("fl2d special ==="+filter2);
					}
					else {
						filter2=
								" SELECT x.date_challan_date, sum(x.challan_amt::decimal) as challan_amt,x.vch_remarks as description, "+
								" x.check_type, sum(x.disptch_duty) as disptch_duty FROM      "+                                                                                                   
								" (SELECT a.vch_remarks, b.vch_rajaswa_head,b.g6_head,a.dat_created_date as date_challan_date, a.vch_total_amount as challan_amt,  "+             
								" 'CH'::text as check_type, 0 as disptch_duty    "+                                                                    
								" FROM licence.mst_challan_master a, licence.challan_head_details b  	"+                               
								" where a.vch_challan_id=b.vch_challan_id and a.vch_trn_status='SUCCESS' and a.vch_mill_name is not null and "+
								" a.vch_mill_type='Fl2d'  and   "+        
								" b.head_type in ('"+filter1+"') and "+                        
								" a.vch_mill_name='"+ ac.getUnit_id()+ "' and              "+
								" a.dat_created_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                       "+
								" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' )x    "+    
								" GROUP BY x.date_challan_date, x.check_type,x.vch_remarks "+
								" UNION  "+                                       
								" SELECT b.date_crr_date, 0 as challan_amt, b.vch_description as description, 'DT' as check_type,              "+
								" SUM(b.int_value) as disptch_duty FROM distillery.duty_register_19_20 b WHERE b.vch_duty_type='"+ac.getType()+"'     "+
								" AND b.int_fl2d_id='"+ac.getUnit_id()+"'                                                                   "+
								" AND b.date_crr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                       "+
								" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' 	                                               "+
								" GROUP BY  b.date_crr_date, description, check_type  "+	
								" UNION " +
								" SELECT b.date_crr_date, 0 as challan_amt, b.vch_description as description, 'DT' as check_type,              "+
								" SUM(b.int_value) as disptch_duty FROM distillery.permit_register_20_21 b WHERE b.vch_duty_type='"+ac.getType()+"'     "+
								" AND b.int_fl2d_id='"+ac.getUnit_id()+"'                                                                   "+
								" AND b.date_crr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                       "+
								" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' 	                                               "+
								" GROUP BY  b.date_crr_date, description, check_type  ";
					}
					
				
				
				allData =" SELECT x.date_challan_date as dt , sum(x.challan_amt) as challan_amt , sum(x.disptch_duty) as disptch_duty,  "+
						" x.description, x.check_type                                                                                  "+
						" FROM  	                                                                                                   "+
					    " (select '2020-04-01' as date_challan_date,case  when '"+ac.getType()+"' in ('BWFL2B_SOCIAL_DUTY','BWFL2A_SOCIAL_DUTY','FL2D_SOCIAL_DUTY')           "+
				        " then coalesce(sum(add_consideration_amount),0) when '"+ac.getType()+"' like ('Special%') then coalesce(sum(cowcesh_amt),0)                               "+
				        " when '"+ac.getType()+"' in ('FL2D_Permit') then coalesce(sum(duty),0) when '"+ac.getType()+"' in ('scanning_fee_cr_FL2D_ALL','scanning_fee_cr_BWFL_ALL')   "+
				        " then                                                                                                                                           "+
				        " coalesce(sum(scan_amount),0) end as challan_amt, 'Current Year opening' as description ,                                                       "+
				        " 'OP' as check_type, 0 as disptch_duty from fl2d.opening_fees_for_fl2d_bwfl_20_21 where unit_id='"+ ac.getUnit_id()+ "' and unit_type='FL2D'         "+
                        " union "+
                        filter2 +
				        " UNION  "+
						" SELECT p.cr_date, 0 as challan_amt,                                                              "+
						" CONCAT('Penality Deduction for Penality No.- ', p.deduction_code) as description,                 "+
						" 'DT' as check_type, SUM(p.db_amount) as disptch_duty                                             "+
						" FROM licence.mst_penalty_deduction p                                                             "+
						" WHERE   p.duty_type='"+ac.getType()+"' AND p.unit_id='"+ ac.getUnit_id()+ "'                     "+
						" AND p.unit_type='"+this.getLogin_type()+"'                                                       "+
						" AND p.cr_date BETWEEN '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                 "+
						" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "'                                     "+
						" GROUP BY p.cr_date, description, check_type) x                            "+
						" GROUP BY x.date_challan_date , x.description ,x.check_type order by x.date_challan_date , check_type ";
				
				
				}
				else{
					
					
					if(ac.getType().equalsIgnoreCase("BWFL_Import")){
						
						filter2=" SELECT  b.cr_date, 0 as challan_amt, 'Import Fee on Permit ' || b.permit_nmbr as description, 'DT' as check_type,            "+  
								" SUM (b.import_fee) as disptch_duty FROM bwfl_license.import_permit_20_21 b WHERE b.bwfl_id='"+ac.getUnit_id()+"' and login_type='BWFL'  "+                                                                
								" AND b.cr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                                                                                     "+
								" AND '"+Utility.convertUtilDateToSQLDate(ac.getTodate())+"' and vch_approved='APPROVED'	                                                                         "+
								" GROUP BY  b.cr_date, description, check_type                                                                       "+
								" Union  " +
								" SELECT  b.cr_date, sum(amount) as challan_amt, 'Deposit Challan  - ' || b.chalan_no as description, 'CH' as check_type,  "+           
								" 0 as disptch_duty FROM bwfl_license.chalan_deposit_bwfl_fl2d b WHERE b.unit_id='"+ ac.getUnit_id()+ "' and unit_type like '%BWFL%'               "+                                                 
								" and head_code in ('003900103030000','003900105040000') and g6_code in ('03','01') AND b.cr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'   "+
								" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' 	                                                "+
								" GROUP BY  b.cr_date, description, check_type            "          ;
						
						System.out.println("import ==="+filter2);
							
						}
					   else if(ac.getType().equalsIgnoreCase("BWFL_Duty")){
							
							filter2=" SELECT b.date_crr_date, 0 as challan_amt, b.vch_description as description, 'DT' as check_type,              "+
									" SUM(b.int_value) as disptch_duty FROM distillery.duty_register_19_20 b WHERE b.vch_duty_type like 'DUTY_BWFL%'     "+
									" AND b.int_bwfl_id='"+ac.getUnit_id()+"'                                                                   "+
									" AND b.date_crr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                       "+
									" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' 	                                               "+
									" GROUP BY  b.date_crr_date, description, check_type  "+                                                                     
									" Union  " +
									" SELECT  b.cr_date, sum(amount) as challan_amt, 'Deposit Challan  - ' || b.chalan_no as description, 'CH' as check_type,  "+           
									" 0 as disptch_duty FROM bwfl_license.chalan_deposit_bwfl_fl2d b WHERE b.unit_id='"+ ac.getUnit_id()+ "'    and unit_type like '%BWFL%'             "+                                                 
									" and head_code in ('003900103010000','003900105010000') and g6_code in ('06','07')  AND b.cr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'   "+
									" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' 	                                                "+
									" GROUP BY  b.cr_date, description, check_type            "          ;
							
							System.out.println("duty ==="+filter2);
								
							}
						else if(ac.getType().equalsIgnoreCase("BWFL_Special")){
							
							filter2=" SELECT  b.cr_date, 0 as challan_amt, 'Special Fee on Permit ' || b.permit_nmbr as description, 'DT' as check_type,          "+    
									" SUM(b.special_fee) as disptch_duty FROM bwfl_license.import_permit_20_21 b WHERE b.bwfl_id='"+ac.getUnit_id()+"' and login_type='BWFL' "+                                                                 
									" AND b.cr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                                                                                     "+
									" AND '"+Utility.convertUtilDateToSQLDate(ac.getTodate())+"' and vch_approved='APPROVED'	                                                                       "+
									" GROUP BY  b.cr_date, description, check_type    " +
							        " UNION "+
							        " SELECT  b.cr_date, sum(amount) as challan_amt, 'Deposit Challan  - ' || b.chalan_no as description, 'CH' as check_type,  "+           
									" 0 as disptch_duty FROM bwfl_license.chalan_deposit_bwfl_fl2d b WHERE b.unit_id='"+ ac.getUnit_id()+ "' and unit_type like '%BWFL%'                "+                                                 
									" and head_code in ('003900103020000','003900105020000') and g6_code in ('13','18') AND b.cr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'   "+
									" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' 	                                                "+
									" GROUP BY  b.cr_date, description, check_type            "          ;
							
							System.out.println("special ==="+filter2);
						}
						else {
							            filter2=
									        " SELECT x.date_challan_date, sum(x.challan_amt::decimal) as challan_amt,x.vch_remarks as description, "+
											" x.check_type, sum(x.disptch_duty) as disptch_duty FROM      "+                                                                                                   
											" (SELECT a.vch_remarks, b.vch_rajaswa_head,b.g6_head,a.dat_created_date as date_challan_date, a.vch_total_amount as challan_amt,  "+             
											" 'CH'::text as check_type, 0 as disptch_duty    "+                                                                    
											" FROM licence.mst_challan_master a, licence.challan_head_details b  	"+                               
											" where a.vch_challan_id=b.vch_challan_id and a.vch_trn_status='SUCCESS' and a.vch_mill_name is not null and "+
											" a.vch_mill_type='Bwfl'                  "+                                                   
											" and  b.head_type='"+ac.getType()+"' and "+                        
											" a.vch_mill_name='"+ ac.getUnit_id()+ "' and              "+
											" a.dat_created_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                       "+
											" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' )x    "+    
											" GROUP BY x.date_challan_date, x.check_type,x.vch_remarks "+
											" UNION  "+
											" SELECT a.gatepass_dt, 0 as challan_amt, CONCAT('Duty Cancellation For GATEPASS',vch_gatepass_no) as description, "+
											" 'CANCEL' as check_type , 0 as disptch_duty  "+
											" FROM bwfl.duty_cancellation_19_20 a  "+
											" WHERE  a.vch_duty_type='"+ac.getType()+"' AND  a.int_bwfl_id ='"+ ac.getUnit_id()+ "'  "+
											" AND a.gatepass_dt BETWEEN '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                  "+
											" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "'  "+
											" UNION                                                                                                        "+
											" SELECT b.date_crr_date, 0 as challan_amt, b.vch_description as description, 'DT' as check_type,              "+
											" SUM(b.int_value) as disptch_duty FROM distillery.duty_register_19_20 b WHERE b.vch_duty_type='"+ac.getType()+"'     "+
											" AND b.int_bwfl_id='"+ac.getUnit_id()+"'                                                                   "+
											" AND b.date_crr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                       "+
											" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' 	                                               "+
											" GROUP BY  b.date_crr_date, description, check_type  ";
						}
						
					allData = 	" SELECT x.date_challan_date as dt , sum(x.challan_amt) as challan_amt , sum(x.disptch_duty) as disptch_duty,  "+
							" x.description, x.check_type                                                                                  "+
							" FROM  	                                                                                                   "+
						    " (select '2020-04-01' as date_challan_date,case  when '"+ac.getType()+"' in ('BWFL2B_SOCIAL_DUTY','BWFL2A_SOCIAL_DUTY','FL2D_SOCIAL_DUTY')           "+
					        " then coalesce(sum(add_consideration_amount),0) when '"+ac.getType()+"' in ('BWFL_Special') then coalesce(sum(cowcesh_amt),0)                               "+
					        " when '"+ac.getType()+"' in ('BWFL_Duty') then coalesce(sum(duty),0) when '"+ac.getType()+"' in ('scanning_fee_cr_FL2D_ALL','scanning_fee_cr_BWFL_ALL')   "+
					        " then                                                                                                                                           "+
					        " coalesce(sum(scan_amount),0)  when '"+ac.getType()+"' in ('BWFL_Import') then coalesce(sum(import_amount),0) end as challan_amt, 'Current Year opening' as description ,                                                       "+
					        " 'OP' as check_type, 0 as disptch_duty from fl2d.opening_fees_for_fl2d_bwfl_20_21 where unit_id='"+ ac.getUnit_id()+ "' and unit_type='BWFL'         "+
					        " union" +
							 filter2 +
                            " union" +
						    " SELECT p.cr_date, 0 as challan_amt,                                                              "+
							" CONCAT('Penality Deduction for Penality No.- ', p.deduction_code) as description,                 "+
							" 'DT' as check_type, SUM(p.db_amount) as disptch_duty                                             "+
							" FROM licence.mst_penalty_deduction p                                                             "+
							" WHERE   p.duty_type='"+ac.getType()+"' AND p.unit_id='"+ ac.getUnit_id()+ "'                     "+
							" AND p.unit_type='BWFL'                                                       "+
							" AND p.cr_date BETWEEN '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                 "+
							" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "'                                     "+
							" GROUP BY p.cr_date, description, check_type) x                            "+
							" GROUP BY x.date_challan_date , x.description ,x.check_type order by x.date_challan_date , check_type ";
				}
			
			
			

			 System.out.println("register query------------- "+allData);

			pstmt = conn.prepareStatement(allData);
			rs2 = pstmt.executeQuery();

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
			while (rs2.next()) {

				String date = formatter.format(rs2.getDate("dt"));
				
				AdvanceDutyRegisterDT dt = new AdvanceDutyRegisterDT();

				dt.setSrNo(i);

				dt.setDate_Dt(date);

				/*if (count == 0) {
					//System.out.println("---------tttttt---------" + firstOpnig);

					dt.setDate_Dt(Utility.convertUtilDateToSQLDate(ac.getFromdate()));

					dt.setOpningBal(firstOpnig);
					dt.setProduceBal(0.0);
					dt.setDisptchBal(0.0);
					dt.setCancelBal(0.0);
					dt.setDeclarwstBal(0.0);
					dt.setBalanceTot(firstOpnig);
					dt.setDescription("Current Year Opning Balance");
					count++;
				}

				else {*/

					 //System.out.println("---------2222222222222---------"+bal);
					if (bal == 0) {
						dt.setOpningBal(firstOpnig);

					} else {
						dt.setOpningBal(bal);
					}
					dt.setDate_Dt(date);
					dt.setProduceBal(rs2.getDouble("challan_amt"));
					dt.setDisptchBal(rs2.getDouble("disptch_duty"));
					dt.setDescription(rs2.getString("description"));

					// dt.setBalanceTot(dt.getOpningBal()+dt.getProduceBal()-dt.getDisptchBal());
					if (rs2.getString("check_type").equalsIgnoreCase("CH") || rs2.getString("check_type").equalsIgnoreCase("OP")) {
						dt.setBalanceTot(dt.getOpningBal() + dt.getProduceBal());

					} else {
						dt.setBalanceTot(dt.getOpningBal() - dt.getDisptchBal());
					}

					bal = dt.getOpningBal() + dt.getProduceBal()- dt.getDisptchBal();

				

				i++;

				list.add(dt);

			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
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

	public ArrayList getTypeList(AdvanceDutyRegisterBWFL_FL2D_DEOAction ac) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("");
		list.add(item);
		try {
			String query = ""; 
			
					if(ac.getUnit_type().equalsIgnoreCase("FL2D")){
					    query = "select  display_head_name,challan_head_type from licence.distillery_g6head where display_head_name is not null" +
							" and challan_head_type in ('scanning_fee_cr_FL2D_ALL','FL2D_SOCIAL_DUTY') " +
							" union select  'FL2D Special Fee' as display_head_name, 'Special_fee_fl2D_permit_Beer' as challan_head_type    "+
		                    " union select  'FL2D Import Fee' as display_head_name, 'FL2D_Permit' as challan_head_type        "+
		                    "  order by display_head_name ";
					}else{
						 query = "select  display_head_name,challan_head_type from licence.distillery_g6head where display_head_name is not null" +
								" and challan_head_type in ('BWFL2B_SOCIAL_DUTY','BWFL2A_SOCIAL_DUTY','scanning_fee_cr_BWFL_ALL') " +
			                    " union select  'BWFL Special Fee' as display_head_name, 'BWFL_Special' as challan_head_type    "+
			                    " union select  'BWFL Import Fee' as display_head_name, 'BWFL_Import' as challan_head_type    "+
			                    " union select  'BWFL Duty' as display_head_name, 'BWFL_Duty' as challan_head_type order by display_head_name ";
					}
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString(1));
				item.setValue(rs.getString(2));
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
	
	
	public void print(AdvanceDutyRegisterBWFL_FL2D_DEOAction ac) {
		String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
		String relativePath = mypath + File.separator + "ExciseUp" + File.separator + "WholeSale" + File.separator + "jasper";
		String relativePathpdf = mypath + File.separator + "ExciseUp" + File.separator + "WholeSale" + File.separator + "pdf";
		JasperReport jasperReport = null;
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null; 
		double firstOpnig = 0.0;
		int count = 0;
		
		try {
			conn = ConnectionToDataBase.getConnection();
			
			String filter = "";
			String Opning="";
			//System.out.println("worng Login Type=" + this.getLogin_type()+"-");
			
			////System.out.println("filter=" + filter);
			
			////System.out.println("Opning="+Opning);

			

			double bal = 0.0;
			
			String allData ="";
			String filter1="";
			String filter2="";
			if(ac.getType().equalsIgnoreCase("FL2D_SOCIAL_DUTY")){
				filter1="BEER_SOCIAL_DUTY','FL_SOCIAL_DUTY','FL2D_SOCIAL_DUTY";
			}else{
				filter1=ac.getType();
			}
			 
			
			
			
			
			if(ac.getUnit_type().equalsIgnoreCase("FL2D")){	
				
				
           if(ac.getType().equalsIgnoreCase("FL2D_Permit")){
					
					filter2=" SELECT  b.cr_date, 0 as challan_amt, 'Import Fee on Permit ' || b.permit_nmbr as description, 'DT' as check_type,            "+  
							" SUM (b.import_fee) as disptch_duty FROM bwfl_license.import_permit_20_21 b WHERE b.bwfl_id='"+ac.getUnit_id()+"' and login_type='FL2D'  "+                                                                
							" AND b.cr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                                                                                     "+
							" AND '"+Utility.convertUtilDateToSQLDate(ac.getTodate())+"' and vch_approved='APPROVED'	                                                                         "+
							" GROUP BY  b.cr_date, description, check_type                                                                       "+
							" Union  " +
							" SELECT  b.cr_date, sum(amount) as challan_amt, 'Deposit Challan  - ' || b.chalan_no as description, 'CH' as check_type,  "+           
							" 0 as disptch_duty FROM bwfl_license.chalan_deposit_bwfl_fl2d b WHERE b.unit_id='"+ ac.getUnit_id()+ "' and unit_type='FL2D'                "+                                                 
							" and head_code in ('003900103030000','003900105040000') and g6_code in ('04','05') AND b.cr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'   "+
							" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' 	                                                "+
							" GROUP BY  b.cr_date, description, check_type            "          ;
						
					System.out.println("fl2d import ==="+filter2);
					   }
					else if(ac.getType().equalsIgnoreCase("Special_fee_fl2D_permit_Beer")){
						
						filter2=" SELECT  b.cr_date, 0 as challan_amt, 'Special Fee on Permit ' || b.permit_nmbr as description, 'DT' as check_type,          "+    
								" SUM(b.special_fee) as disptch_duty FROM bwfl_license.import_permit_20_21 b WHERE b.bwfl_id='"+ac.getUnit_id()+"' and login_type='FL2D' "+                                                                 
								" AND b.cr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                                                                                     "+
								" AND '"+Utility.convertUtilDateToSQLDate(ac.getTodate())+"' and vch_approved='APPROVED'	                                                                       "+
								" GROUP BY  b.cr_date, description, check_type    " +
						        " UNION "+
								" SELECT  b.cr_date, sum(amount) as challan_amt, 'Deposit Challan  - ' || b.chalan_no as description, 'CH' as check_type,  "+           
								" 0 as disptch_duty FROM bwfl_license.chalan_deposit_bwfl_fl2d b WHERE b.unit_id='"+ ac.getUnit_id()+ "' and unit_type='FL2D'                "+                                                 
								" and head_code in ('003900103020000','003900105020000') and g6_code in ('15','22') AND b.cr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'   "+
								" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' 	                                                "+
								" GROUP BY  b.cr_date, description, check_type            "          ;
						
						System.out.println("fl2d special ==="+filter2);
					}
					else {
						filter2=
								" SELECT x.date_challan_date, sum(x.challan_amt::decimal) as challan_amt,x.vch_remarks as description, "+
								" x.check_type, sum(x.disptch_duty) as disptch_duty FROM      "+                                                                                                   
								" (SELECT a.vch_remarks, b.vch_rajaswa_head,b.g6_head,a.dat_created_date as date_challan_date, a.vch_total_amount as challan_amt,  "+             
								" 'CH'::text as check_type, 0 as disptch_duty    "+                                                                    
								" FROM licence.mst_challan_master a, licence.challan_head_details b  	"+                               
								" where a.vch_challan_id=b.vch_challan_id and a.vch_trn_status='SUCCESS' and a.vch_mill_name is not null and "+
								" a.vch_mill_type='Fl2d'  and   "+        
								" b.head_type in ('"+filter1+"') and "+                        
								" a.vch_mill_name='"+ ac.getUnit_id()+ "' and              "+
								" a.dat_created_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                       "+
								" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' )x    "+    
								" GROUP BY x.date_challan_date, x.check_type,x.vch_remarks "+
								" UNION  "+                                       
								" SELECT b.date_crr_date, 0 as challan_amt, b.vch_description as description, 'DT' as check_type,              "+
								" SUM(b.int_value) as disptch_duty FROM distillery.duty_register_19_20 b WHERE b.vch_duty_type='"+ac.getType()+"'     "+
								" AND b.int_fl2d_id='"+ac.getUnit_id()+"'                                                                   "+
								" AND b.date_crr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                       "+
								" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' 	                                               "+
								" GROUP BY  b.date_crr_date, description, check_type  "+	
								" UNION " +
								" SELECT b.date_crr_date, 0 as challan_amt, b.vch_description as description, 'DT' as check_type,              "+
								" SUM(b.int_value) as disptch_duty FROM distillery.permit_register_20_21 b WHERE b.vch_duty_type='"+ac.getType()+"'     "+
								" AND b.int_fl2d_id='"+ac.getUnit_id()+"'                                                                   "+
								" AND b.date_crr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                       "+
								" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' 	                                               "+
								" GROUP BY  b.date_crr_date, description, check_type  ";
					}
					
				
				
				allData =" SELECT x.date_challan_date as dt , sum(x.challan_amt) as challan_amt , sum(x.disptch_duty) as disptch_duty,  "+
						" x.description, x.check_type                                                                                  "+
						" FROM  	                                                                                                   "+
					    " (select '2020-04-01' as date_challan_date,case  when '"+ac.getType()+"' in ('BWFL2B_SOCIAL_DUTY','BWFL2A_SOCIAL_DUTY','FL2D_SOCIAL_DUTY')           "+
				        " then coalesce(sum(add_consideration_amount),0) when '"+ac.getType()+"' like ('Special%') then coalesce(sum(cowcesh_amt),0)                               "+
				        " when '"+ac.getType()+"' in ('FL2D_Permit') then coalesce(sum(duty),0) when '"+ac.getType()+"' in ('scanning_fee_cr_FL2D_ALL','scanning_fee_cr_BWFL_ALL')   "+
				        " then                                                                                                                                           "+
				        " coalesce(sum(scan_amount),0) end as challan_amt, 'Current Year opening' as description ,                                                       "+
				        " 'OP' as check_type, 0 as disptch_duty from fl2d.opening_fees_for_fl2d_bwfl_20_21 where unit_id='"+ ac.getUnit_id()+ "' and unit_type='FL2D'         "+
                        " union "+
                        filter2 +
				        " UNION  "+
						" SELECT p.cr_date, 0 as challan_amt,                                                              "+
						" CONCAT('Penality Deduction for Penality No.- ', p.deduction_code) as description,                 "+
						" 'DT' as check_type, SUM(p.db_amount) as disptch_duty                                             "+
						" FROM licence.mst_penalty_deduction p                                                             "+
						" WHERE   p.duty_type='"+ac.getType()+"' AND p.unit_id='"+ ac.getUnit_id()+ "'                     "+
						" AND p.unit_type='"+this.getLogin_type()+"'                                                       "+
						" AND p.cr_date BETWEEN '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                 "+
						" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "'                                     "+
						" GROUP BY p.cr_date, description, check_type) x                            "+
						" GROUP BY x.date_challan_date , x.description ,x.check_type order by x.date_challan_date , check_type ";
				
				
				}
				else{
					
					
					if(ac.getType().equalsIgnoreCase("BWFL_Import")){
						
						filter2=" SELECT  b.cr_date, 0 as challan_amt, 'Import Fee on Permit ' || b.permit_nmbr as description, 'DT' as check_type,            "+  
								" SUM (b.import_fee) as disptch_duty FROM bwfl_license.import_permit_20_21 b WHERE b.bwfl_id='"+ac.getUnit_id()+"' and login_type='BWFL'  "+                                                                
								" AND b.cr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                                                                                     "+
								" AND '"+Utility.convertUtilDateToSQLDate(ac.getTodate())+"' and vch_approved='APPROVED'	                                                                         "+
								" GROUP BY  b.cr_date, description, check_type                                                                       "+
								" Union  " +
								" SELECT  b.cr_date, sum(amount) as challan_amt, 'Deposit Challan  - ' || b.chalan_no as description, 'CH' as check_type,  "+           
								" 0 as disptch_duty FROM bwfl_license.chalan_deposit_bwfl_fl2d b WHERE b.unit_id='"+ ac.getUnit_id()+ "' and unit_type like '%BWFL%'                "+                                                 
								" and head_code in ('003900103030000','003900105040000') and g6_code in ('03','01') AND b.cr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'   "+
								" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' 	                                                "+
								" GROUP BY  b.cr_date, description, check_type            "          ;
						
						System.out.println("import ==="+filter2);
							
						}
					   else if(ac.getType().equalsIgnoreCase("BWFL_Duty")){
							
							filter2=" SELECT b.date_crr_date, 0 as challan_amt, b.vch_description as description, 'DT' as check_type,              "+
									" SUM(b.int_value) as disptch_duty FROM distillery.duty_register_19_20 b WHERE b.vch_duty_type like 'DUTY_BWFL%'     "+
									" AND b.int_bwfl_id='"+ac.getUnit_id()+"'                                                                   "+
									" AND b.date_crr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                       "+
									" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' 	                                               "+
									" GROUP BY  b.date_crr_date, description, check_type  "+                                                                     
									" Union  " +
									" SELECT  b.cr_date, sum(amount) as challan_amt, 'Deposit Challan  - ' || b.chalan_no as description, 'CH' as check_type,  "+           
									" 0 as disptch_duty FROM bwfl_license.chalan_deposit_bwfl_fl2d b WHERE b.unit_id='"+ ac.getUnit_id()+ "'   and unit_type like '%BWFL%'              "+                                                 
									" and head_code in ('003900103010000','003900105010000') and g6_code in ('06','07')  AND b.cr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'   "+
									" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' 	                                                "+
									" GROUP BY  b.cr_date, description, check_type            "          ;
							
							System.out.println("duty ==="+filter2);
								
							}
						else if(ac.getType().equalsIgnoreCase("BWFL_Special")){
							
							filter2=" SELECT  b.cr_date, 0 as challan_amt, 'Special Fee on Permit ' || b.permit_nmbr as description, 'DT' as check_type,          "+    
									" SUM(b.special_fee) as disptch_duty FROM bwfl_license.import_permit_20_21 b WHERE b.bwfl_id='"+ac.getUnit_id()+"' and login_type='BWFL' "+                                                                 
									" AND b.cr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                                                                                     "+
									" AND '"+Utility.convertUtilDateToSQLDate(ac.getTodate())+"' and vch_approved='APPROVED'	                                                                       "+
									" GROUP BY  b.cr_date, description, check_type    " +
							        " UNION "+
							        " SELECT  b.cr_date, sum(amount) as challan_amt, 'Deposit Challan  - ' || b.chalan_no as description, 'CH' as check_type,  "+           
									" 0 as disptch_duty FROM bwfl_license.chalan_deposit_bwfl_fl2d b WHERE b.unit_id='"+ ac.getUnit_id()+ "' and unit_type like '%BWFL%'                "+                                                 
									" and head_code in ('003900103020000','003900105020000') and g6_code in ('13','18') AND b.cr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'   "+
									" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' 	                                                "+
									" GROUP BY  b.cr_date, description, check_type            "          ;
							
							System.out.println("special ==="+filter2);
						}
						else {
							            filter2=
									        " SELECT x.date_challan_date, sum(x.challan_amt::decimal) as challan_amt,x.vch_remarks as description, "+
											" x.check_type, sum(x.disptch_duty) as disptch_duty FROM      "+                                                                                                   
											" (SELECT a.vch_remarks, b.vch_rajaswa_head,b.g6_head,a.dat_created_date as date_challan_date, a.vch_total_amount as challan_amt,  "+             
											" 'CH'::text as check_type, 0 as disptch_duty    "+                                                                    
											" FROM licence.mst_challan_master a, licence.challan_head_details b  	"+                               
											" where a.vch_challan_id=b.vch_challan_id and a.vch_trn_status='SUCCESS' and a.vch_mill_name is not null and "+
											" a.vch_mill_type='Bwfl'                  "+                                                   
											" and  b.head_type='"+ac.getType()+"' and "+                        
											" a.vch_mill_name='"+ ac.getUnit_id()+ "' and              "+
											" a.dat_created_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                       "+
											" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' )x    "+    
											" GROUP BY x.date_challan_date, x.check_type,x.vch_remarks "+
											" UNION  "+
											" SELECT a.gatepass_dt, 0 as challan_amt, CONCAT('Duty Cancellation For GATEPASS',vch_gatepass_no) as description, "+
											" 'CANCEL' as check_type , 0 as disptch_duty  "+
											" FROM bwfl.duty_cancellation_19_20 a  "+
											" WHERE  a.vch_duty_type='"+ac.getType()+"' AND  a.int_bwfl_id ='"+ ac.getUnit_id()+ "'  "+
											" AND a.gatepass_dt BETWEEN '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                  "+
											" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "'  "+
											" UNION                                                                                                        "+
											" SELECT b.date_crr_date, 0 as challan_amt, b.vch_description as description, 'DT' as check_type,              "+
											" SUM(b.int_value) as disptch_duty FROM distillery.duty_register_19_20 b WHERE b.vch_duty_type='"+ac.getType()+"'     "+
											" AND b.int_bwfl_id='"+ac.getUnit_id()+"'                                                                   "+
											" AND b.date_crr_date between '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                       "+
											" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "' 	                                               "+
											" GROUP BY  b.date_crr_date, description, check_type  ";
						}
						
					allData = 	" SELECT x.date_challan_date as dt , sum(x.challan_amt) as challan_amt , sum(x.disptch_duty) as disptch_duty,  "+
							" x.description, x.check_type                                                                                  "+
							" FROM  	                                                                                                   "+
						    " (select '2020-04-01' as date_challan_date,case  when '"+ac.getType()+"' in ('BWFL2B_SOCIAL_DUTY','BWFL2A_SOCIAL_DUTY','FL2D_SOCIAL_DUTY')           "+
					        " then coalesce(sum(add_consideration_amount),0) when '"+ac.getType()+"' in ('BWFL_Special') then coalesce(sum(cowcesh_amt),0)                               "+
					        " when '"+ac.getType()+"' in ('BWFL_Duty') then coalesce(sum(duty),0) when '"+ac.getType()+"' in ('scanning_fee_cr_FL2D_ALL','scanning_fee_cr_BWFL_ALL')   "+
					        " then                                                                                                                                           "+
					        " coalesce(sum(scan_amount),0)  when '"+ac.getType()+"' in ('BWFL_Import') then coalesce(sum(import_amount),0) end as challan_amt, 'Current Year opening' as description ,                                                       "+
					        " 'OP' as check_type, 0 as disptch_duty from fl2d.opening_fees_for_fl2d_bwfl_20_21 where unit_id='"+ ac.getUnit_id()+ "' and unit_type='BWFL'         "+
					        " union" +
							 filter2 +
                            " union" +
						    " SELECT p.cr_date, 0 as challan_amt,                                                              "+
							" CONCAT('Penality Deduction for Penality No.- ', p.deduction_code) as description,                 "+
							" 'DT' as check_type, SUM(p.db_amount) as disptch_duty                                             "+
							" FROM licence.mst_penalty_deduction p                                                             "+
							" WHERE   p.duty_type='"+ac.getType()+"' AND p.unit_id='"+ ac.getUnit_id()+ "'                     "+
							" AND p.unit_type='BWFL'                                                       "+
							" AND p.cr_date BETWEEN '"+ Utility.convertUtilDateToSQLDate(ac.getFromdate())+ "'                 "+
							" AND '"+ Utility.convertUtilDateToSQLDate(ac.getTodate())+ "'                                     "+
							" GROUP BY p.cr_date, description, check_type) x                            "+
							" GROUP BY x.date_challan_date , x.description ,x.check_type order by x.date_challan_date , check_type ";
				}
			
			
			
			
			
			

			pst = conn.prepareStatement(allData);
			
		
		
			////System.out.println("====22222======"+action.getBwfl_FL2d_Id()+"==="+reportQuery);
			rs = pst.executeQuery();
			if (rs.next()) {
				rs = pst.executeQuery();
				Map parameters = new HashMap();
				parameters.put("REPORT_CONNECTION", conn);
				parameters.put("SUBREPORT_DIR", relativePath + File.separator);
				parameters.put("image", relativePath + File.separator);
				parameters.put("opening",ac.getBal());
				parameters.put("fromDate",Utility.convertUtilDateToSQLDate(ac.getFromdate()));
				parameters.put("toDate",Utility.convertUtilDateToSQLDate(ac.getTodate()));
				parameters.put("user",ac.getName());
				parameters.put("type",this.getLogin_type());
				
				
				JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);
				jasperReport = (JasperReport) JRLoader.loadObject(relativePath + File.separator + "AdvanceDutyRegister.jasper");
				JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, jrRs);
				Random rand = new Random();
				int n = rand.nextInt(250) + 1;
				JasperExportManager.exportReportToPdfFile(print, relativePathpdf + File.separator + "AdvanceDutyRegister" + n + ".pdf");
				ac.setPdfname("AdvanceDutyRegister" + n + ".pdf");
				ac.setPrintFlag(true);
			} else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("No Data Found", "No Data Found"));
				ac.setPrintFlag(false);
			}
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)rs.close();
				if (conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
public void getopening(AdvanceDutyRegisterBWFL_FL2D_DEOAction ac) throws Exception {
		
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(ac.getFromdate());
	    calendar.add(Calendar.DATE, -1);
	    Date yesterday = calendar.getTime();
	    //System.out.println("yesterday===if not equal fix date=="+yesterday);
	    
	    
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String Opning="";
	
		if (this.getLogin_type().trim().equalsIgnoreCase("D")) {
			 Opning ="select sum(b.opning_balance) as opning,(select  a.display_head_name from licence.distillery_g6head a where a.challan_head_type='"+ac.getType()+"' and display_head_name is not null) as type" +
			 		" FROM distillery.distillery_challan_opening_balance_19_20 b "
						+ "    where challan_type='"
						+ ac.getType()
						+ "' and unit_id='" + ac.getDist_id() + "' ";
		
			
		}else if (this.getLogin_type().trim().equalsIgnoreCase("B")) {
			 Opning ="select sum(b.opning_balance) as opning,(select  a.display_head_name from licence.distillery_g6head a where a.challan_head_type='"+ac.getType()+"' and display_head_name is not null) as type" +
			 		" FROM distillery.brewery_challan_opening_balance_19_20 b "
						+ "    where challan_type='"
						+ ac.getType()
						+ "' and unit_id='" + ac.getDist_id() + "' ";
			
			

		} else {
			
			//System.out.println("worng Login Type=" + this.getLogin_type()+"-");
		}	
				
				
		
		try {
			//System.out.println("check query===>"+Opning);
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(Opning);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				
				ac.setBal(rs.getInt("opning"));
				ac.setTypep(rs.getString("type"));
				

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
	}
	
public ArrayList getUniitList(AdvanceDutyRegisterBWFL_FL2D_DEOAction act) {

	ArrayList list = new ArrayList();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	SelectItem item = new SelectItem();
	item.setLabel("--select--");
	item.setValue("");
	list.add(item);
	String query="";
	try {
		if(act.getUnit_type().equalsIgnoreCase("BWFL")){
		   query = "select int_id as int_app_id ,concat(vch_applicant_name,' - ',vch_license_number) as liccense from bwfl.registration_of_bwfl_lic_holder_20_21 where vch_firm_district_id='"+act.getDist_id()+"' order by liccense";
		}
		else{
			query="select int_app_id,concat(vch_firm_name,' - ',vch_licence_no) as liccense from licence.fl2_2b_2d_20_21 where vch_license_type='FL2D' and core_district_id='"+act.getDist_id()+"' order by liccense";
		}
		conn = ConnectionToDataBase.getConnection();
		pstmt = conn.prepareStatement(query);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			item = new SelectItem();
			item.setLabel(rs.getString("liccense"));
			item.setValue(rs.getString("int_app_id"));
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



}
