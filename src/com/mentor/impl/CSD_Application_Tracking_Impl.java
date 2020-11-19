package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.mentor.action.CSD_Application_Tracking_Action;
import com.mentor.datatable.CSD_Application_Tracking_DT;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.ResourceUtil;

public class CSD_Application_Tracking_Impl {
	
	
	public void getDetail(CSD_Application_Tracking_Action act) {


		int id = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			
  String selQr ="SELECT int_app_id, vch_applicant_name, " +
  		        " vch_firm_name, vch_mobile_no, vch_email, vch_pan, vch_license_type, vch_licence_no, vch_flag, core_district_id " +
  		        "  FROM licence.fl2_2b_2d_fl2a_csd_20_21 where vch_mobile_no='"+ ResourceUtil.getUserNameAllReq().trim() + "'";
		  
		  
		  /*" select vch_licence_no  as vch_license_number,'99' as vch_license_type,vch_mobile_no as mobile_number,0 as stateid,  " +
				" null as vch_state_name,vch_firm_name, vch_core_address as vch_firm_add,core_district_id as vch_firm_district_id,description, " +
				" null as vch_indus_name,null as vch_reg_office_add,int_app_id as int_id, COALESCE (etin_unit_id,0) as unit_id, " +
				" null as  license_issue_date,null as approval_dt,null as application_date   " +
				" from licence.fl2_2b_2d_19_20 a, public.district b where a.core_district_id=b.districtid   " +
				" and vch_license_type='FL2D' and vch_mobile_no='"+ ResourceUtil.getUserNameAllReq().trim() + "' " ;
		
		*/
		
		//System.out.println("login dtl="+selQr);
			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();
			
			 
			while (rs.next()) {
				act.setInt_id(rs.getInt("int_app_id"));
				act.setDisttrict_id(rs.getInt("core_district_id"));
				act.setLic_no(rs.getString("vch_licence_no"));
				act.setName(rs.getString("vch_applicant_name"));
				act.setAddress(rs.getString("vch_firm_name"));
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
	public ArrayList getApplications(CSD_Application_Tracking_Action act) {

		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 1;
		String sql = "";
		try {
			
			
				sql = "SELECT id, bwfl_id, unit_id, bwfl_type,cr_date, COALESCE(vch_approved, 'NA') as vch_approved, digital_sign_date,app_id,COALESCE(vch_status,'NA') AS vch_status, " +
					  " COALESCE(permit_nmbr,'NA') AS permit_nmbr "+
	                  " FROM fl2d.application_csd_permit_mst_20_21 where bwfl_id="+act.getInt_id()+""; 
			
			//System.out.println(act.getUnit_id()+"-Brand list="+sql);
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CSD_Application_Tracking_DT dt = new CSD_Application_Tracking_DT();
				dt.setSrNo(i);
				dt.setApp_id(rs.getInt("app_id"));
				dt.setApp_date(rs.getDate("cr_date"));
				dt.setPermit_no(rs.getString("permit_nmbr"));
				if(rs.getString("vch_status").equalsIgnoreCase("NA")){
					dt.setStatus("Pending");
				}else{
				    dt.setStatus(rs.getString("vch_status"));
				}
				if(rs.getString("vch_approved").equalsIgnoreCase("APPROVED") && rs.getDate("digital_sign_date")!=null) 
				{
					dt.setFlag(true);
				}else{
					dt.setFlag(false);
				}
				list.add(dt);
				i++;
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
}
