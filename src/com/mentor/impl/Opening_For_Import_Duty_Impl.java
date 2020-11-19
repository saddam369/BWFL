package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.mentor.action.Opening_For_Import_Duty_Action;
import com.mentor.datatable.Application_Csd_dt;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class Opening_For_Import_Duty_Impl {

	public ArrayList getdisUnitList() {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue(0);
		list.add(item);

	 String sql="select int_app_id, vch_applicant_name from licence.fl2_2b_2d_fl2a_csd where core_district_id=(SELECT districtid FROM district where deo='"+ResourceUtil.getUserNameAllReq().trim()+"')";
 		try {
 			//System.out.println("dis========================"+sql);
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("vch_applicant_name"));
				item.setValue(rs.getInt("int_app_id"));
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
	
	
	public boolean saveImpl(Opening_For_Import_Duty_Action act){



		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		
		String query = ""; 
	
		boolean flg=false;
		 try {

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			 
			
				
			query="INSERT INTO fl2d.csd_advance_register("+
				  "	int_id, unit_id, challan_no, date_challan_permit, deposit_amount, fn_year, challan_type)"+
				  "	VALUES (?, ?, ?, ?, ?, ?, ?);";
									
					/*"INSERT INTO bwfl_license.import_permit_dtl(fk_id, district_id, brand_id, pckg_id, etin, no_of_cases, no_of_bottle_per_case," +
					" pland_no_of_bottles, import_fee, duty, add_duty, special_fee, cr_date,login_type,app_id) VALUES (? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";*/
		
			
			
				
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1,  this.maxId());
					pstmt.setInt(2, act.getUnit_id());
					pstmt.setString(3, "Advance Register For Import Fee");
					pstmt.setDate(4, Utility.convertUtilDateToSQLDate(act.getDate()));
					pstmt.setDouble(5, act.getAmount());
					pstmt.setInt(6, 14);
					pstmt.setString(7, "Import Fee");
					
					saveStatus = pstmt.executeUpdate();
			
				
		
			
			
			
			
			if (saveStatus > 0) {
				conn.setAutoCommit(true);
				flg=true;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("SAVED SUCCESSFULLY !", "SAVED SUCCESSFULLY !"));
				

			} else {
				// action.reset();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,Constants.NOT_SAVED);

			}

		}

		catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}

		finally {
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
	return flg;


	}
	
	
	
	public int maxId() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(int_id) as id FROM fl2d.csd_advance_register  ";
		int maxid = 0;
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
}
