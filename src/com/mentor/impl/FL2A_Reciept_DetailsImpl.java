package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.mentor.action.FL2A_Reciept_DetailsAction;
import com.mentor.connectiondb.ConnectionToDataBase;
import com.mentor.datatable.FL2A_Reciept_DetailsDatatable;
import com.mentor.datatable.FL2A_Reciept_DetailsDatatable2;
import com.mentor.datatable.FL2A_Reciept_List_Datatabe;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class FL2A_Reciept_DetailsImpl {

	public void getDetails(FL2A_Reciept_DetailsAction ac) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		/*
		 * String query =
		 * " SELECT * FROM bwfl.registration_of_bwfl_lic_holder WHERE " +
		 * " mobile_number='"+ResourceUtil.getUserNameAllReq().trim()+"'";
		 */

		String query = " SELECT int_app_id, vch_applicant_name, vch_firm_name, vch_sex, int_age, vch_mobile_no, "
				+ " vch_email, vch_pan, vch_father_hus_name, vch_registration_no, vch_gstin_no, "
				+ " vch_licence_no, date_licence_issued_date, date_licence_valied_upto, vch_license_type, "
				+ " vch_flag, vch_approval, approval_date, core_state_id, core_district_id, "
				+ " vch_core_district_name, vch_core_address, per_state_id, per_district_id, "
				+ " vch_perdistrict_name, vch_per_address, vch_co_applicant_name, vch_co_sex, "
				+ " vch_co_father_hus, co_applicant_core_state_id, co_applicant_core_district_id, "
				+ " vch_co_applicant_core_district_name, vch_co_applicant_core_address, "
				+ " co_applicant_per_state_id, co_applicant_per_district_id, "
				+ " vch_co_applicant_per_district_name, vch_co_applicant_per_address, "
				+ " vch_premises_suitable_for_shop, vch_location_of_premises, vch_area, vch_east, vch_west, "
				+ " vch_south, vch_north, vch_complete_status, vch_photo_name, vch_sign_name, reg_dt "
				+ " FROM licence.fl2_2b_2d where vch_license_type='FL2A' and "
				+ " vch_mobile_no='"
				+ ResourceUtil.getUserNameAllReq().trim()
				+ "'";
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				// ac.setBwfl_int_brewery_id(rs.getString("int_brewery_id"));
				// ac.setBwfl_int_distillery_id(rs.getString("int_app_id"));
				ac.setBwfl_int_id_pk(rs.getInt("int_app_id"));
				// ac.setBwfl_vch_brewery_contact_number(rs.getString("vch_brewery_contact_number"));
				// ac.setBwfl_vch_distillery_contact_number(rs.getString("vch_distillery_contact_number"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList getDetails_BWFL(FL2A_Reciept_DetailsAction ac) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int j = 1;

		String selQr = " SELECT seq, licence_id, radio_type, csd_name, csd_address, permit_no, "
				+ " issue_date, distillery_brewery_name, distillery_id, brewery_id, "
				+ " distillery_brewery_address, licence_no, contact_no, created_by, "
				+ " email_id FROM fl2d.entry_of_permit_master where "
				+ " created_by='"
				+ ResourceUtil.getUserNameAllReq().trim()
				+ "'";

		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(selQr);
			rs = ps.executeQuery();

			while (rs.next()) {
				FL2A_Reciept_DetailsDatatable dt = new FL2A_Reciept_DetailsDatatable();
				dt.setDate(rs.getDate("issue_date"));
				dt.setPassno(rs.getString("csd_name"));
				dt.setBrewery_id(String.valueOf(rs.getInt("seq")));
				dt.setDistillery_id(String.valueOf(rs.getInt("seq")));
				dt.setInt_id(rs.getInt("seq"));
				dt.setSno(j);
				dt.setPermitno(rs.getString("permit_no"));
				dt.setLicenseNmbr(String.valueOf(rs.getInt("licence_id")));
				j++;
				list.add(dt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList getFormDetails(FL2A_Reciept_DetailsAction ac,
			FL2A_Reciept_DetailsDatatable dt) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		int j = 1;

		String selQr = " SELECT a.int_id, a.seq as id, a.brand_id, a.size, a.no_of_bottle, a.cases, a.no_cases, "
					 + " a.packaging_id, b.brand_name, c.permit_no FROM fl2d.entry_of_permit_master_detail a, " +
					 " distillery.brand_registration b, fl2d.entry_of_permit_master c " +
					 " where a.seq=c.seq and a.brand_id=b.brand_id and c.seq=? ";

		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(selQr);
			ps.setInt(1, Integer.parseInt(dt.getDistillery_id()));
			rs = ps.executeQuery();

			while (rs.next()) {
				FL2A_Reciept_DetailsDatatable2 dt2 = new FL2A_Reciept_DetailsDatatable2();

				dt2.setBrewery_id(rs.getInt("id"));
				dt2.setInt_brand_id(rs.getInt("brand_id"));
				dt2.setInt_pack_id(rs.getInt("packaging_id"));
				dt2.setInt_quantity(rs.getInt("size"));
				dt2.setInt_planned_bottles(rs.getInt("no_of_bottle"));
				dt2.setInt_boxes(rs.getInt("no_cases"));

				dt2.setInt_liquor_type(0);
				dt2.setVch_license_type("");
				dt2.setPlan_dt(dt.getDate());
				dt2.setLicence_no("");
				dt2.setCr_date(null);
				dt2.setFinalized_date(null);
				dt2.setFinalized_flag("");
				dt2.setSize(0);
				dt2.setReceived_from_usr("");
				dt2.setPermitno(rs.getString("permit_no"));
				dt2.setNoOfBottle(0);
				dt2.setBrand(rs.getString("brand_name"));
				dt2.setPassno(0);
				dt2.setSlno(j);
				list.add(dt2);
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public String getLicenseType(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name = "";
		String query = "Select * from distillery.imfl_type where id='" + id
				+ "'";
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString("description");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return name;
	}

	public String getDistilleryName(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name = "";
		String query = "Select * from public.dis_mst_pd1_pd2_lic where int_app_id_f="
				+ id;
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString("vch_undertaking_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return name;
	}

	public String getBreweryName(int idd) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name = "";
		String query = "Select * from public.bre_mst_b1_lic where vch_app_id_f="
				+ idd;
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString("brewery_nm");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return name;
	}

	public void saveDetail(FL2A_Reciept_DetailsAction ac) {
		Connection con = null;
		PreparedStatement ps = null, ps1 = null, ps2 = null, ps3 = null, ps4 = null;
		ResultSet rs = null;
		int save = 0;

		try {
			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);
			for (int i = 0; i < ac.getView_formData().size(); i++) {
				FL2A_Reciept_DetailsDatatable2 dt = (FL2A_Reciept_DetailsDatatable2) ac.getView_formData().get(i);
				
				String query = " INSERT INTO fl2d.mst_receipt_fl2a(int_fl2a_id, int_brand_id, int_pack_id, " +
							    " int_quantity, int_planned_bottles, int_planned_boxes, int_liquor_type, " +
							    " vch_license_type, plan_dt, licence_no, cr_date, received_by, remarks, " +
							    " receiving_date, received_liqour, received_from_usr, seq, dispatch_36, box_size, " +
							    " int_recieved_bottles, int_recieved_boxes, breakage, gatepass, permitno) VALUES " +
							    " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";				
				
				String query5 = " SELECT * FROM fl2d.receipt_stock_fl2a where int_brand_id="+dt.getInt_brand_id()+" " +
								" and int_pckg_id="+dt.getInt_pack_id()+" and permit='"+dt.getPermitno().trim()+"'";								
				
				String query3 = " INSERT INTO fl2d.receipt_stock_fl2a(int_fl2a_id, int_dispatched_botls, " +
								" int_dispatched_cases, int_stock_botls, int_stock_cases, " +
								" vch_lic_type, int_brand_id, int_pckg_id, permit) VALUES " +
								" (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
				
				String queryy = " UPDATE fl2d.receipt_stock_fl2a SET int_stock_botls=int_stock_botls+? WHERE " +
								" int_brand_id="+dt.getInt_brand_id()+" and int_pckg_id="+dt.getInt_pack_id()+" " +
								" and permit='"+dt.getPermitno().trim()+"'";
				
				String update = " UPDATE fl2d.entry_of_permit_master_detail SET recieved_bottles=recieved_bottles+? " +
								" WHERE brand_id="+dt.getInt_brand_id()+" and packaging_id="+dt.getInt_pack_id()+"" +
								" and seq="+dt.getBrewery_id()+"";
				

				ps = con.prepareStatement(query);
				ps.setInt(1, ac.getBwfl_int_id_pk());
				ps.setInt(2, dt.getInt_brand_id());
				ps.setInt(3, dt.getInt_pack_id());
				ps.setInt(4, dt.getInt_quantity());
				ps.setInt(5, dt.getInt_planned_bottles());
				ps.setInt(6, dt.getInt_boxes());
				ps.setInt(7, dt.getInt_liquor_type());
				ps.setString(8, "FL2A");
				ps.setDate(9, Utility.convertUtilDateToSQLDate(dt.getPlan_dt()));
				ps.setString(10, dt.getLicence_no());
				ps.setDate(11, Utility.convertUtilDateToSQLDate(new Date()));
				ps.setString(12, ac.getRecievedby());
				ps.setString(13, ac.getRemarks());
				ps.setDate(14, Utility.convertUtilDateToSQLDate(ac.getReceiving_date()));
				ps.setInt(15, 0);
				ps.setLong(16, 0);
				ps.setInt(17, this.mst_receipt() + i);
				ps.setInt(18, 0);
				ps.setInt(19, dt.getSize());
				ps.setInt(20, (dt.getNoOfBottle()));
				ps.setInt(21, (dt.getNoOfBottle()));
				ps.setInt(22, dt.getRecivecases());
				ps.setInt(23,0);
				ps.setString(24,dt.getPermitno());
				save = ps.executeUpdate();
				if (save > 0) {
					save = 0;
					ps2 = con.prepareStatement(query5);
					rs = ps2.executeQuery();
					if (rs.next()) {
						ps1 = con.prepareStatement(queryy);
						ps1.setInt(1, dt.getInt_planned_bottles());
						save = ps1.executeUpdate();
					} else {
						ps3 = con.prepareStatement(query3);
						ps3.setInt(1, ac.getBwfl_int_id_pk());
						ps3.setInt(2, 0);
						ps3.setInt(3, 0);
						ps3.setInt(4, dt.getInt_planned_bottles());
						ps3.setInt(5, dt.getInt_planned_bottles());
						ps3.setString(6, "FL2A");
						ps3.setInt(7, dt.getInt_brand_id());
						ps3.setInt(8, dt.getInt_pack_id());						
						ps3.setString(9, dt.getPermitno());
						save = ps3.executeUpdate();
					}
				}				
				if (save > 0) {
					save = 0;
					ps4 = con.prepareStatement(update);
					ps4.setInt(1, dt.getInt_planned_bottles());
					System.out.println("====SQL=ps4==>"+update);
					System.out.println("====SQL=ps4==>"+dt.getInt_planned_bottles());
					save = ps4.executeUpdate();
				}
			}
			if (save > 0) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Record Saved", "Record Saved"));
				con.commit();
				ac.reset();
			} else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Not Saved", "Not Saved"));
				con.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int mst_receipt() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "Select max(seq) as id from fl2d.mst_receipt_fl2a";
		int id = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return id + 1;
	}

	public int receipt_stock() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "Select max(int_id_pk) as id from bwfl_license.receipt_stock";
		int id = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return id + 1;
	}

	public ArrayList getRecieptsList(FL2A_Reciept_DetailsAction ac) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 1;
		/*String query = " SELECT a.int_bwfl_id, int_brand_id, a.int_pack_id,b.brand_name, a.int_quantity, a.permitno,a.int_planned_bottles,a.gatepass, "
				+ " a.int_planned_boxes, a.int_liquor_type, a.vch_license_type, a.plan_dt, a.licence_no, a.cr_date, a.received_by, "
				+ " a.remarks, a.receiving_date, a.received_liqour, a.received_from_usr, a.seq, a.dispatch_36,"
				+ " a.int_recieved_bottles,a.int_recieved_boxes  "
				+ " FROM bwfl_license.mst_receipt a ,distillery.brand_registration b where a.int_bwfl_id="
				+ ac.getBwfl_int_id_pk()
				+ " and b.brand_id=a.int_brand_id "
				+ " and b.int_bwfl_id=a.int_bwfl_id";*/
		
		String query = " SELECT a.int_fl2a_id, b.brand_name, a.int_brand_id, a.int_pack_id, a.int_quantity, a.int_planned_bottles, " +
					   " a.int_planned_boxes, a.int_liquor_type, a.vch_license_type, a.plan_dt, a.licence_no, " +
					   " a.cr_date, a.received_by, a.remarks, a.receiving_date, a.received_liqour, a.received_from_usr, " +
					   " a.seq, a.dispatch_36, a.box_size, a.int_recieved_bottles, a.int_recieved_boxes, " +
					   " a.breakage, a.gatepass, a.permitno FROM fl2d.mst_receipt_fl2a a, distillery.brand_registration b " +
					   " where a.int_fl2a_id="+ac.getBwfl_int_id_pk()+" and b.brand_id=a.int_brand_id";
					   /*" and " +
					   " a.int_fl2a_id=b.int_fl2d_id";*/

		 System.out.println("last datatable------------------"+query);
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				FL2A_Reciept_List_Datatabe dt = new FL2A_Reciept_List_Datatabe();
				dt.setSno(i);
				dt.setLicno(rs.getString("licence_no"));
				dt.setRecievedby(rs.getString("received_by"));
				dt.setPassno("PASS-" + rs.getInt("gatepass"));
				dt.setRecievingdate(rs.getDate("receiving_date"));
				dt.setPlannedBottlesdt(rs.getInt("int_planned_bottles"));
				dt.setRecievedBottlesdt(rs.getInt("int_recieved_bottles"));
				dt.setBrand(rs.getString("brand_name"));
				dt.setPermitnodisp(rs.getString("permitno"));
				list.add(dt);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}
