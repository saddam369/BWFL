package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.mentor.action.AdvanceRegisterCSDAction;
import com.mentor.datatable.AdvanceRegisterCSDDT;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.ResourceUtil;

public class AdvanceRegisterCSDImpl {
	public void getDetails(AdvanceRegisterCSDAction act){


		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			
  String selQr ="SELECT int_app_id, vch_applicant_name,                                                          "+
             " vch_firm_name,core_district_id,'FL2A' as login_type                                                                     "+
             " FROM licence.fl2_2b_2d_fl2a_csd_20_21 where vch_mobile_no='"+ResourceUtil.getUserNameAllReq()+"'    "+
             " union                                                                                             "+
             " SELECT int_app_id_f,vch_indus_name,vch_reg_office_add,0,'UNIT' as login_type FROM public.other_unit_registration_20_21 "+
             " where login_id='"+ResourceUtil.getUserNameAllReq()+"' and loginusr like '%CSD%'    "+
             " union                                                                                             "+
             " select 0,'','',districtid,'DEO' as login_type from district where deo='"+ResourceUtil.getUserNameAllReq()+"'";
		  
		  
		//System.out.println("login dtl="+selQr);
			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();
			
			 
			if (rs.next()) {
			
				act.setName(rs.getString("vch_applicant_name"));
				act.setAddress(rs.getString("vch_firm_name"));
				act.setInt_id(rs.getInt("int_app_id"));
				act.setInt_district_id(rs.getInt("core_district_id"));
				act.setLogin_type(rs.getString("login_type"));
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
	}
	
	
	public ArrayList getUnitList() {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);

	 String sql=" select int_app_id_f,vch_indus_name from other_unit_registration_20_21 where loginusr like '%CSD%' order by vch_indus_name";
 		try {
 			//System.out.println("dis========================"+sql);
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("vch_indus_name"));
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
	
	
	public ArrayList getDepoList(AdvanceRegisterCSDAction act) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);

	 String sql="select int_app_id,coalesce(vch_firm_name,'NA')vch_firm_name from licence.fl2_2b_2d_fl2a_csd_20_21 where core_district_id="+act.getInt_district_id()+" order by vch_firm_name ";
 		try {
 			System.out.println("dis========================"+sql);
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("vch_firm_name"));
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
	
	public ArrayList getDepoList1(AdvanceRegisterCSDAction act) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);

	 String sql="select core_district_id ,int_app_id,coalesce(vch_firm_name,'NA') as vch_firm_name from licence.fl2_2b_2d_fl2a_csd_20_21 order by vch_firm_name ";
 		try {
 			System.out.println("dis==========1111=============="+sql);
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("vch_firm_name"));
				item.setValue(rs.getString("core_district_id")+"/"+rs.getString("int_app_id"));
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
	public ArrayList datalist(AdvanceRegisterCSDAction act){

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null, ps1=null;
		ResultSet rs = null,rs1=null;
		String selQr = null;
		 int int_id=act.getInt_id();
		 int i = 1;
		 String filter="";
		 String id="";
		 String filter1="";
		 double openning=0.0;
		 int count=0;
		 double tot_dep=0.0;
		 double tot_permit=0.0;
		 double tot_bal=0.0;
         int district_id=0;
        
		 
		 if(act.getRadio().equalsIgnoreCase("IF")){
			 filter1="IMPORT FEE";
		 }else if(act.getRadio().equalsIgnoreCase("S")){
			 filter1="SPECIAL FEE";
		 }else if(act.getRadio().equalsIgnoreCase("ED")){
			 filter1="EXCISE DUTY";
		 }else if(act.getRadio().equalsIgnoreCase("SF")){
			 filter1="SCANING FEE";
		 }else if(act.getRadio().equalsIgnoreCase("SCF")){
			 filter1="SPECIAL CONSIDERATION FEE";
		 }
		 
		 if(act.getLogin_type().equalsIgnoreCase("DEO")){
			 filter= String.valueOf(act.getInt_district_id())+" as core_district_id";
			 int_id=Integer.parseInt(act.getUnit_id());
			 district_id=act.getInt_district_id();
			 if(act.getUnitradio().equalsIgnoreCase("U")){
				 id=" deo_user='"+ResourceUtil.getUserNameAllReq()+"'  and unit_id='"+int_id+"' ";
			 }else if(act.getUnitradio().equalsIgnoreCase("D")){
				 id=" deo_user='"+ResourceUtil.getUserNameAllReq()+"'  and bwfl_id='"+int_id+"' ";
			 }
		 }else if(act.getLogin_type().equalsIgnoreCase("UNIT")){
			 String arr[] = act.getDepo_id().split("/");
	         String dipoid=arr[0];
			 String bwfl_id=arr[1];	
			 district_id=Integer.parseInt(dipoid);
			 filter=dipoid+" as core_district_id";
			 id=" unit_id='"+act.getInt_id()+"' and bwfl_id="+bwfl_id+" and deo_user=(Select deo from district where districtid="+dipoid+") ";
		 }else if(act.getLogin_type().equalsIgnoreCase("FL2A")){
			 district_id=act.getInt_district_id();
			 filter="core_district_id, int_app_id FROM licence.fl2_2b_2d_fl2a_csd_20_21 where vch_mobile_no='"+ResourceUtil.getUserNameAllReq()+"'";
			 id=" bwfl_id='"+act.getInt_id()+"' ";
		 }
		
		try { 
			
			
			
			
			
			String query=" SELECT sum(amount) as amount from fl2d.advance_opening_fl2a where  unit_id="+int_id+" and  fee_type='"+filter1+"' " +
					     " and district_id="+district_id+"";
			
			
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);
            
			rs = ps.executeQuery();
			if(rs.next()){
				openning=rs.getDouble("amount");
				
				AdvanceRegisterCSDDT dt = new AdvanceRegisterCSDDT();
				dt.setSrno(i);
				if(count==0){
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
					Date appdate = formatter.parse("01-04-2020");
					String date=formatter.format(appdate);
					dt.setDate(date);
					dt.setDescription("Current openning in this year");
					dt.setDeposite_amt(openning);
					dt.setPermit_amt(0.0);
					dt.setBalance(openning);
					tot_dep=openning;
					tot_permit=0.0;
					tot_bal=tot_dep-tot_permit;
					act.setTotal_deposite(tot_dep);
					act.setTotal_permit(tot_permit);
					act.setTotal_bal(tot_bal);
					count++;
					i++;
				}
				list.add(dt);
			}
			
			ps=null;
			rs=null;
			double bal=0.0;
			if(act.getRadio().equalsIgnoreCase("IF"))
			{
			  selQr = " select concat('Deposite For Challan no. ', a.vch_challan_id) as des,dat_created_date,sum(vch_total_amount::int) as deposite_amt,0.0 as  permit_amt from licence.mst_challan_master a,"+
                    " licence.challan_head_details b,(select x.*, a.divcode,  a.tcode from (SELECT "+filter+")x ,"+
                    " licence.treasury a where a.district_id=x.core_district_id)c where a.vch_division=c.divcode and a.vch_treasury=tcode  "+
                    " and a.vch_challan_id=b.vch_challan_id and                                                                                                "+
                    " vch_rajaswa_head in ('3900103030000','3900105040000') and g6_head in (2) and a.vch_trn_status='SUCCESS'                                      "+
                    " and vch_mill_type in ('CSD-DEPO') and a.vch_mill_name='"+int_id+"' and a.dat_created_date>'2020-03-31' and a.vch_register_flag='R' group by dat_created_date, a.vch_challan_id                                                      "+
                    " union                                                                                                                                                            "+
                    " select concat('For Permit no. ', permit_nmbr,'_20_21') as des ,print_permit_dt,0.0 as deposite_amt,sum(import_fee) as permit_amt from fl2d.application_csd_permit_mst_20_21                          "+
                    " where "+id+"                                                                                                                "+
                    " and vch_approved='APPROVED' and unit_type not in ('D','B') group by print_permit_dt ,permit_nmbr order by dat_created_date";
			  
			}else if(act.getRadio().equalsIgnoreCase("S")){
				
				selQr=
                      " select concat('Deposite For Challan no. ', a.vch_challan_id) as des,dat_created_date,sum(vch_total_amount::int) as deposite_amt,0.0 as  permit_amt from licence.mst_challan_master a,"+
                      " licence.challan_head_details b,(select x.*, a.divcode,  a.tcode from (SELECT "+filter+")x ,"+
                      " licence.treasury a where a.district_id=x.core_district_id)c where a.vch_division=c.divcode and a.vch_treasury=tcode  "+
                      " and a.vch_challan_id=b.vch_challan_id and                                                                                                 "+
                      " vch_rajaswa_head in ('3900105020000','3900103020000') and g6_head in (24,13) and a.vch_trn_status='SUCCESS'                                                                          "+
                      " and vch_mill_type in ('CSD') and a.vch_mill_name='"+int_id+"' and a.dat_created_date>'2020-03-31' and a.vch_register_flag='R' group by dat_created_date, a.vch_challan_id                                                             "+
                      " union                                                                                                                                                             "+
                      " select concat('For Permit no. ', permit_nmbr,'_20_21') as des ,print_permit_dt,0.0 as deposite_amt,sum(special_fee) as permit_amt from fl2d.application_csd_permit_mst_20_21                          "+
                      " where  "+id+"     "  +                                                                                                         
                      " and vch_approved='APPROVED' and unit_type not in ('D','B')  group by print_permit_dt ,permit_nmbr order by dat_created_date";
				
			}else if(act.getRadio().equalsIgnoreCase("ED")){
				
				selQr=
                     " select concat('Deposite For Challan no. ', a.vch_challan_id) as des,dat_created_date,sum(vch_total_amount::int) as deposite_amt,0.0 as  permit_amt from licence.mst_challan_master a,"+
                     " licence.challan_head_details b,(select x.*, a.divcode,  a.tcode from (SELECT "+filter+")x ,"+
                     " licence.treasury a where a.district_id=x.core_district_id)c where a.vch_division=c.divcode and a.vch_treasury=tcode  "+
                     " and a.vch_challan_id=b.vch_challan_id  and                                                                                              "+
                     " vch_rajaswa_head in('3900105010000','3900103010000','3900105040000') and g6_head in (2,3,4) and a.vch_trn_status='SUCCESS'                                                         "+
                     " and vch_mill_type in ('CSD') and a.vch_mill_name='"+int_id+"' and a.dat_created_date>'2020-03-31' and a.vch_register_flag='R' group by dat_created_date, a.vch_challan_id                                                             "+
                     " union                                                                                                                                                            "+
                     " select concat('For Permit no.', permit_nmbr,'_20_21') as des ,print_permit_dt,0.0 as deposite_amt,sum(duty) as permit_amt from fl2d.application_csd_permit_mst_20_21                                "+
                     " where "+id+"                                                                                                                "+
                     " and vch_approved='APPROVED' and unit_type not in ('D','B')  group by print_permit_dt ,permit_nmbr order by dat_created_date";     
				
			}else if(act.getRadio().equalsIgnoreCase("SF")){
				
				selQr=
                    " select concat('Deposite For Challan no. ', a.vch_challan_id) as des,dat_created_date,sum(vch_total_amount::int) as deposite_amt,0.0 as  permit_amt from licence.mst_challan_master a,"+
                    " licence.challan_head_details b,(select x.*, a.divcode,  a.tcode from (SELECT "+filter+")x ,"+
                    " licence.treasury a where a.district_id=x.core_district_id)c where a.vch_division=c.divcode and a.vch_treasury=tcode  "+
                    " and a.vch_challan_id=b.vch_challan_id  and                                                                                             "+
                    " vch_rajaswa_head in ('3900800060000') and g6_head in (6) and a.vch_trn_status='SUCCESS'                                                                          "+
                    " and vch_mill_type in ('CSD') and a.vch_mill_name='"+int_id+"' and a.dat_created_date>'2020-03-31' and a.vch_register_flag='R' group by dat_created_date, a.vch_challan_id                                                             "+
                    " union                                                                                                                                                            "+
                    " select concat('For Permit no. ', permit_nmbr) as des ,print_permit_dt,0.0 as deposite_amt,sum(tot_scanning_fee) as permit_amt from fl2d.application_csd_permit_mst_20_21                    "+
                    " where "+id+"                                                                                                                "+
                    " and vch_approved='APPROVED' and unit_type not in ('D','B')  group by print_permit_dt ,permit_nmbr order by dat_created_date";
				
			}else if(act.getRadio().equalsIgnoreCase("SCF")){
				
				 selQr=
                       " select concat('Deposite For Challan no. ', a.vch_challan_id) as des,dat_created_date,sum(vch_total_amount::int) as deposite_amt,0.0 as  permit_amt from licence.mst_challan_master a,  "+
                       " licence.challan_head_details b,(select x.*, a.divcode,  a.tcode from (SELECT "+filter+")x ,"+
                       " licence.treasury a where a.district_id=x.core_district_id)c where a.vch_division=c.divcode and a.vch_treasury=tcode  "+
                       " and a.vch_challan_id=b.vch_challan_id and                                                                                               "+
                       " vch_rajaswa_head in ('003900103010000','003900105010000') and g6_head in (09) and a.vch_trn_status='SUCCESS'                                                      "+
                       " and vch_mill_type='CSD'  and a.vch_mill_name='"+int_id+"' and a.dat_created_date>'2020-03-31' and a.vch_register_flag='R' group by dat_created_date, a.vch_challan_id                                                   "+
                       " union                                                                                                                                                              "+
                       " select concat('For Permit no. ', permit_nmbr,'_20_21') as des ,print_permit_dt,0.0 as deposite_amt,sum(total_corona_fee) as permit_amt from fl2d.application_csd_permit_mst_20_21                      "+
                       " where   "+id+"                                                                                                                  "+
                       " and vch_approved='APPROVED' and unit_type not in ('D','B')  group by print_permit_dt ,permit_nmbr order by dat_created_date";
			}

			System.out.println("datalist=" + selQr);
			ps = conn.prepareStatement(selQr);

			rs = ps.executeQuery();
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			
			while (rs.next()) {
				
				
				AdvanceRegisterCSDDT dt = new AdvanceRegisterCSDDT();
				dt.setSrno(i);
				/*if(count==0){
					dt.setDate(appdate1);
					dt.setDescription("Current openning in this year");
					dt.setDeposite_amt(openning);
					dt.setPermit_amt(0.0);
					dt.setBalance(openning);
					count++;
				}else{*/
					if(bal==0){
					  bal=openning;
					}
					String appdate = formatter.format(rs.getDate("dat_created_date"));
					//System.out.println("date==="+appdate);
					dt.setDate(appdate);
					dt.setDescription(rs.getString("des"));
					dt.setDeposite_amt(rs.getDouble("deposite_amt"));
					dt.setPermit_amt(rs.getDouble("permit_amt"));
					bal=bal+dt.getDeposite_amt()-dt.getPermit_amt();
					//System.out.println("balance=="+dt.getBalance());
					dt.setBalance(bal);					
				//}
				
				tot_dep=tot_dep+dt.getDeposite_amt();
				tot_permit=tot_permit+dt.getPermit_amt();
				tot_bal=tot_dep-tot_permit;
				act.setTotal_deposite(tot_dep);
				act.setTotal_permit(tot_permit);
				act.setTotal_bal(tot_bal);
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
}



