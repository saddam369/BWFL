package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.model.SelectItem;

import com.mentor.action.BWFLRegistrationDetailsAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class BWFLRegistrationDetailsImpl {

	public void getData(BWFLRegistrationDetailsAction action) {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT  b.description, a.int_distillery_id  as int_distillery_id, "
					+ " a.int_brewery_id as int_brewery_id, a.* FROM bwfl.registration_of_bwfl_lic_holder a, public.district b "
					+ "where a.vch_firm_district_id::numeric=b.districtid and a.mobile_number='"
					+ ResourceUtil.getUserNameAllReq().trim() + "'";

			System.out.println("sql - " + sql);
			c = ConnectionToDataBase.getConnection();
			ps = c.prepareStatement(sql);

			rs = ps.executeQuery();

			if (rs.next()) {

				action.setId(rs.getInt("int_id"));
				action.setLicenseNumber(rs.getString("vch_license_number"));
				action.setLicenseType(rs.getString("vch_license_type"));
				action.setLicenseeName(rs.getString("vch_applicant_name"));
				action.setDistrictName(rs.getString("description"));
				action.setFatherName(rs.getString("vch_father_or_husband_name"));
				action.setMobile(rs.getString("mobile_number"));
				action.setEmail(rs.getString("vch_email"));
				action.setFirmName(rs.getString("vch_firm_name"));
				action.setAge(rs.getString("age"));

				if (rs.getString("vch_gender") != null) {

					action.setSex(rs.getString("vch_gender"));

				} else {

					action.setSex("NA");
				}

				action.setGstin(rs.getString("vch_firm_gstin"));
				action.setPan(rs.getString("vch_firm_pan"));
				action.setAdhar(rs.getString("aadhar"));
				action.setUnitName(rs.getString("vch_associated_unit_name"));
				action.setIssueDate(Utility.convertSqlDateToUtilDate(rs
						.getDate("license_issue_date")));
				action.setValidUpto(Utility.convertSqlDateToUtilDate(rs
						.getDate("license_valid_upto")));
				action.setAddress(rs.getString("vch_firm_add"));

				 

					action.setDistilleryId(rs.getString("int_distillery_id"));

				 

					action.setBreweryId(rs.getString("int_brewery_id"));
 

				 
				if (rs.getString("vch_distillery_contact_number") == null
						|| rs.getString("vch_distillery_contact_number")
								.equalsIgnoreCase("null")) {

					action.setDistContactFlag(false);

				} else {

					action.setDistContactFlag(true);
					action.setContactDistillery(rs
							.getString("vch_distillery_contact_number"));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {
				c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	/*** -- getmax id -- ***/

	public int maxid() {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		int id = 0;

		try {

			String query = "select max(jbp_uid) as id from jbp_users";

			con = ConnectionToDataBase.getConnection2();

			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				id = rs.getInt(1);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (con != null)
					con.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	public void updateMethod(BWFLRegistrationDetailsAction action) {

		Connection c = null;
		PreparedStatement ps = null;
		int status = 0;
		Connection c2 = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;

		try {

			String sql = "update bwfl.registration_of_bwfl_lic_holder set vch_father_or_husband_name='"
					+ action.getFatherName()
					+ "'"
					+ ", vch_email='"
					+ action.getEmail()
					+ "', vch_firm_name='"
					+ action.getFirmName()
					+ "', age='"
					+ action.getAge()
					+ "', vch_gender='"
					+ action.getSex()
					+ "', vch_firm_gstin='"
					+ action.getGstin()
					+ "', vch_firm_pan='"
					+ action.getPan()
					+ "', aadhar='"
					+ action.getAdhar()
					+ "', vch_associated_unit_name='"
					+ action.getUnitName()
					+ "', license_issue_date='"
					+ Utility.convertUtilDateToSQLDate(action.getIssueDate())
					+ "', license_valid_upto='"
					+ Utility.convertUtilDateToSQLDate(action.getValidUpto())
					+ "', vch_firm_add='"
					+ action.getAddress()	+ "',  vch_distillery_contact_number='"
					+ action.getContactDistillery()
					+ "', int_distillery_id ='"
					+ action.getDistilleryId()
					+ "', int_brewery_id='"
					+ action.getRadio() +"' ,db_address='"+action.getDbAddress()+"'  where mobile_number='"
					+ ResourceUtil.getUserNameAllReq().trim()
					+ "' and int_id="
					+ action.getId();

			 
			c = ConnectionToDataBase.getConnection();
			ps = c.prepareStatement(sql);
			c.setAutoCommit(false);
			status = ps.executeUpdate();

			if (status > 0) {

				status = 0;
				ps = null;
				String query1 = "insert into jbp_users "
						+ " (jbp_uid, jbp_uname, jbp_password, jbp_regdate,  "
						+ " jbp_viewrealemail,jbp_enabled) "
						+ " values(?,?,?,?,?,?)";

				c2 = ConnectionToDataBase.getConnection2();
				c2.setAutoCommit(false);
				ps = c2.prepareStatement(query1);

				int max = this.maxid() + 1;
 
				ps.setInt(1, max);

				/*if (action.isBreweryFlag() == true) {

					ps.setString(2, action.getContactBrewery());
					String pass = String.valueOf(ResourceUtil.encryptMD5(action
							.getContactBrewery()));
					ps.setString(3, pass);
					ps.setDate(4, Utility.convertUtilDateToSQLDate(new Date()));
					ps.setBoolean(5, true);
					ps.setBoolean(6, true);

				} else if (action.isDistilleryFlag() == true) {
*/
					ps.setString(2, action.getContactDistillery());
					String pass = String.valueOf(ResourceUtil.encryptMD5(action
							.getContactDistillery()));
					ps.setString(3, pass);
					ps.setDate(4, Utility.convertUtilDateToSQLDate(new Date()));
					ps.setBoolean(5, true);
					ps.setBoolean(6, true);
				//}

				status = ps.executeUpdate();

				if (status > 0) {

					status = 0;

					/*if (action.isBreweryFlag() == true) {

						System.out.println("in brewery flag -- "
								+ action.getContactBrewery());

						String queryRole = " insert into jbp_role_membership "
								+ " (jbp_uid, jbp_rid) VALUES(?,?) ";

						ps = c2.prepareStatement(queryRole);
						ps.setInt(1, max);
						ps.setInt(2, 214);

						status = ps.executeUpdate();

						if (status > 0) {

							c.commit();
							c2.commit();
							action.setErrorMsg("");
							action.setSuccessMsg("Updated !");
							action.setFlag(true);

						} else {

							c.rollback();
							action.setErrorMsg("Not updated !");
							action.setSuccessMsg("");
							action.setFlag(false);

						}

					} else if (action.isDistilleryFlag() == true) {*/

					 

						String queryRole = " insert into jbp_role_membership "
								+ " (jbp_uid, jbp_rid) VALUES(?,?) ";

						ps1 = c2.prepareStatement(queryRole);
						 
						ps1.setInt(1, max);
						ps1.setInt(2, 213);

						status = ps1.executeUpdate();

						if (status > 0) {

							c.commit();
							c2.commit();
							action.setErrorMsg("");
							action.setSuccessMsg("Updated !");
							action.setFlag(true);

						} else {

							c.rollback();
							action.setErrorMsg("Not updated !");
							action.setSuccessMsg("");
							action.setFlag(false);

						}

					//}

				}
			}

		} catch (Exception e) {

			try {
				c.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			action.setErrorMsg("Something went wrong, try again !");
			action.setSuccessMsg("");
			action.setFlag(false);
			e.printStackTrace();

		} finally {

			try {
				c.close();
				c2.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	/*** --- validation --- ***/

	public boolean isEmail(BWFLRegistrationDetailsAction action) {

		boolean isValid = false;
		String email_regx = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

		if (action.getEmail() != null || action.getEmail().trim().length() != 0) {

			if (action.getEmail().matches(email_regx) == false) {

				action.errorMsg = "Email is invalid !";
				action.successMsg = "";
				isValid = false;

			} else {

				action.errorMsg = "";
				action.successMsg = "";
				isValid = true;

			}

		} else {

			action.errorMsg = "";
			action.successMsg = "";
			isValid = true;

		}

		return isValid;
	}

	public boolean isAge(BWFLRegistrationDetailsAction action) {

		boolean isValid = false;

		if (action.getAge() != null || action.getAge().length() != 0) {

			if (action.getAge().matches("\\d+") == false) {

				action.errorMsg = "Enter digits in Age !";
				action.successMsg = "";
				isValid = false;

			} else {

				action.errorMsg = "";
				action.successMsg = "";
				isValid = true;

			}

		} else {

			action.errorMsg = "";
			action.successMsg = "";
			isValid = true;

		}

		return isValid;
	}

	public boolean isAdhar(BWFLRegistrationDetailsAction action) {

		boolean isValid = false;

		if (action.getAdhar() != null || action.getAdhar().length() != 0) {

			if (action.getAdhar().matches("\\d+") == false) {

				action.errorMsg = "Enter digits in Adhar No !";
				action.successMsg = "";
				isValid = false;

			} else {

				action.errorMsg = "";
				action.successMsg = "";
				isValid = true;

			}

		} else {

			action.errorMsg = "";
			action.successMsg = "";
			isValid = true;

		}

		return isValid;
	}

	/*** --- getBreweryList --- ***/

	public ArrayList getBreweryList() {

		ArrayList list = new ArrayList();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("-select-");
		item.setValue(0);
		list.add(item);

		try {
			String sql = "SELECT vch_app_id_f, brewery_nm FROM public.bre_mst_b1_lic";

			c = ConnectionToDataBase.getConnection();
			ps = c.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();
				item.setLabel(rs.getString("brewery_nm"));
				item.setValue(rs.getInt("vch_app_id_f"));

				list.add(item);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return list;
	}

	/*** --- getDistilleryList --- ***/

	public ArrayList getDistilleryList() {

		ArrayList list = new ArrayList();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		SelectItem item = new SelectItem();
		item.setLabel("-select-");
		item.setValue(0);
		list.add(item);

		try {
			String sql = "SELECT int_app_id_f, vch_undertaking_name FROM public.dis_mst_pd1_pd2_lic";

			c = ConnectionToDataBase.getConnection();
			ps = c.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();
				item.setLabel(rs.getString("vch_undertaking_name"));
				item.setValue(rs.getInt("int_app_id_f"));

				list.add(item);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return list;
	}

	public boolean isContactAlreadyExist(BWFLRegistrationDetailsAction action) {

		boolean isValid = false;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = ConnectionToDataBase.getConnection();
		 

				String sql = "select vch_distillery_contact_number from bwfl.registration_of_bwfl_lic_holder where mobile_number='"
						+ ResourceUtil.getUserNameAllReq().trim() + "'";

				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
                rs.next();
				if ( rs.getString("vch_distillery_contact_number")!=null) {

					action.setErrorMsg("Unit contact number already exist !");
					action.setSuccessMsg("");
					isValid = false;

				} else {

					action.setErrorMsg("");
					action.setSuccessMsg("");
					isValid = true;

				}
			 

		} catch (Exception e) {

			isValid = false;
			e.printStackTrace();
		}finally{
			
			try{
				if(con!=null)con.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}

		return isValid;
	}
}
