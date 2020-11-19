package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import com.mentor.action.BrandRegistrationForOtherDistilleryForCSDAction;
import com.mentor.datatable.BrandRegistrationForOtherDistilleryForCSD_BrandDatatable;
import com.mentor.datatable.BrandRegistrationForOtherDistilleryForCSD_BrandDetailDatatable;
import com.mentor.datatable.BrandRegistrationForOtherDistilleryForCSD_Brand_ViewDatatable;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;

public class BrandRegistrationForOtherDistilleryForCSDImpl {
	
	public ArrayList license_list(BrandRegistrationForOtherDistilleryForCSDAction a){
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--Select--");
		item.setValue("");
		list.add(item);
		String query = " SELECT int_app_id, vch_applicant_name, vch_firm_name, vch_sex, int_age, vch_mobile_no, " +
					   " vch_email, vch_pan, vch_father_hus_name, vch_registration_no, vch_gstin_no, " +
					   " vch_licence_no, date_licence_issued_date, date_licence_valied_upto, vch_license_type, " +
					   " vch_flag, vch_approval, approval_date, core_state_id, core_district_id, " +
					   " vch_core_district_name, vch_core_address, per_state_id, per_district_id, " +
					   " vch_perdistrict_name, vch_per_address, vch_co_applicant_name, vch_co_sex, " +
					   " vch_co_father_hus, co_applicant_core_state_id, co_applicant_core_district_id, " +
					   " vch_co_applicant_core_district_name, vch_co_applicant_core_address, " +
					   " co_applicant_per_state_id, co_applicant_per_district_id, " +
					   " vch_co_applicant_per_district_name, vch_co_applicant_per_address, " +
					   " vch_premises_suitable_for_shop, vch_location_of_premises, vch_area, " +
					   " vch_east, vch_west, vch_south, vch_north, vch_complete_status, " +
					   " vch_photo_name, vch_sign_name, reg_dt FROM licence.fl2_2b_2d " +
					   " where vch_license_type='FL2A'";
		
		try{
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				item = new SelectItem();
				item.setLabel(rs.getString("vch_licence_no"));
				item.setValue(String.valueOf(rs.getInt("int_app_id")));
				list.add(item);
			}
		}catch (Exception e) {
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
	

	public ArrayList getBre_Name(BrandRegistrationForOtherDistilleryForCSDAction action) {
		
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--Select--");
		item.setValue("");
		list.add(item);
		String query = " SELECT vch_app_id_f, vch_b1_lic_no, brewery_nm FROM public.bre_mst_b1_lic "
				+ " where   vch_verify_flag='V' order by brewery_nm ";
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("brewery_nm"));
				item.setValue(rs.getString("vch_b1_lic_no"));
				list.add(item);
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

	public int getBoxSize(BrandRegistrationForOtherDistilleryForCSD_BrandDatatable brandDatatable,int boxsize) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id = 0;
		try {

			String query = " SELECT box_id, box_size FROM distillery.box_size_details " +
						   " WHERE qnt_ml_detail="+boxsize+" ORDER BY box_id ";

			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("box_id");
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
		return id;
	}

	public ArrayList getBrand_Details(
			BrandRegistrationForOtherDistilleryForCSDAction action) {
		ArrayList list = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String lic = "";

		/*
		 * if(action.getLicenseing()!=null && action.getLicenseing().length()>0
		 * && action.getLicenseing().equalsIgnoreCase("CL")){
		 * lic=action.getDis_license_no(); }else if(action.getLicenseing()!=null
		 * && action.getLicenseing().length()>0 &&
		 * !action.getLicenseing().equalsIgnoreCase("CL")){
		 * lic=action.getLicense_no(); }
		 */

		String query = " SELECT brand_id, brand_name, liquor_category, year, licencee_dtl, distillery_id, "
				+ " approval, approvaldt, strength, liquor_type, license_category, " +
				" vch_license_type, license_number, int_fl2a_id "
				+ " FROM distillery.brand_registration  where license_number='FL2A' order by brand_id desc";

		try {
			list = new ArrayList();
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			int i = 1;
			while (rs.next()) {
				BrandRegistrationForOtherDistilleryForCSD_Brand_ViewDatatable dt = new BrandRegistrationForOtherDistilleryForCSD_Brand_ViewDatatable();
				dt.setSrno(i);
				dt.setAppid_fl2a(rs.getInt("int_fl2a_id"));
				dt.setBrand_id(rs.getInt("brand_id"));
				dt.setBrand_name(rs.getString("brand_name"));
				dt.setLiquor_category(rs.getInt("liquor_category"));
				dt.setYear(rs.getInt("year"));
				dt.setLicensee_details(rs.getString("licencee_dtl"));
				dt.setDistillery_id(rs.getInt("distillery_id"));
				dt.setApprovalcode(rs.getString("approval"));
				dt.setApprovaldt(rs.getDate("approvaldt"));
				dt.setLiquor_type(rs.getString("liquor_type"));
				dt.setStrength(rs.getDouble("strength"));
				dt.setLicense_category(rs.getString("license_category"));
				dt.setLicense_number(rs.getString("license_number"));
				dt.setVch_license_type(rs.getString("vch_license_type"));
				if (rs.getInt("liquor_category") == 1) {
					dt.setLiqCatDisplay("Economy");
				} else if (rs.getInt("liquor_category") == 2) {
					dt.setLiqCatDisplay("Regular");
				} else if (rs.getInt("liquor_category") == 3) {
					dt.setLiqCatDisplay("Medium");
				} else if (rs.getInt("liquor_category") == 4) {
					dt.setLiqCatDisplay("Scotch");
				} else if (rs.getInt("liquor_category") == 5) {
					dt.setLiqCatDisplay("Semi Premium");
				} else if (rs.getInt("liquor_category") == 6) {
					dt.setLiqCatDisplay("Premium");
				} else if (rs.getInt("liquor_category") == 7) {
					dt.setLiqCatDisplay("Super Premium");
				} else if (rs.getInt("liquor_category") == 11) {
					dt.setLiqCatDisplay("25% Spiced");
				} else if (rs.getInt("liquor_category") == 12) {
					dt.setLiqCatDisplay("36%");
				} else if (rs.getInt("liquor_category") == 13) {
					dt.setLiqCatDisplay("42.8%");
				} else if (rs.getInt("liquor_category") == 10) {
					dt.setLiqCatDisplay("25% Plain");
				} else if (rs.getInt("liquor_category") == 8) {
					dt.setLiqCatDisplay("Mild");
				} else if (rs.getInt("liquor_category") == 9) {
					dt.setLiqCatDisplay("Strong");
				}
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

	/*
	 * public String
	 * get_Distillery_Details(BrandRegistrationForOtherDistilleryForCSDAction
	 * action,String lic_no) { Connection conn = null; PreparedStatement ps =
	 * null; ResultSet rs = null; String name = ""; try {
	 * 
	 * String fl3_imfl =
	 * " select a.int_distillery_id, b.vch_undertaking_name, a.int_lic_id, b.vch_unit_dist "
	 * + " FROM licence.licence_entery_fl3_fl1 a, public.dis_mst_pd1_pd2_lic b "
	 * + " where vch_licence_type='FL3' and a.vch_lic_unit_type='D' and " +
	 * " a.int_distillery_id=b.int_app_id_f and a.vch_licence_no::text='"
	 * +lic_no.trim()+"'";
	 * 
	 * 
	 * String fl3_beer =
	 * " select  a.int_brewry_id, b.brewery_nm, a.int_lic_id, b.int_upkrm_district_id, "
	 * +
	 * " b.upkrm_district_name FROM licence.licence_entery_fl3_fl1 a, public.bre_mst_b1_lic b  "
	 * +
	 * " where vch_licence_type='FL3' and a.vch_lic_unit_type='B' and a.int_distillery_id=b.vch_app_id_f"
	 * + " and a.vch_licence_no::text='"+lic_no.trim()+"'";
	 * 
	 * String fl3a_imfl =
	 * " SELECT distinct a.int_app_id, a.int_distillery_id, a.int_brewery_id, a.vch_unit_name, "
	 * +
	 * " b.vch_unit_dist FROM licence.fl3a_fl1a a, public.dis_mst_pd1_pd2_lic b "
	 * + " where a.vch_licence_type='FL3A' and a.vch_lic_unit_type='D' and " +
	 * " b.int_app_id_f=a.int_distillery_id and  a.vch_license_fl3a::text='"
	 * +lic_no.trim()+"'";
	 * 
	 * String fl3a_beer =
	 * " SELECT distinct a.int_app_id, a.int_distillery_id, a.int_brewery_id, a.vch_unit_name, "
	 * +
	 * " b.int_upkrm_district_id,b.upkrm_district_name FROM licence.fl3a_fl1a a, public.bre_mst_b1_lic b "
	 * + " where a.vch_licence_type='FL3A' and a.vch_lic_unit_type='B' and " +
	 * " b.vch_app_id_f=a.int_brewery_id and  a.vch_license_fl3a::text='"
	 * +lic_no.trim()+"'";
	 * 
	 * String fl2d =
	 * " select int_app_id, vch_firm_name ,coalesce(core_district_id,00) as core_district_id  "
	 * +
	 * " from licence.fl2_2b_2d where vch_license_type='FL2D' and vch_flag='O' "
	 * + " and vch_licence_no::text='"+lic_no.trim()+"'";
	 * 
	 * String bwfl =
	 * " select int_id, vch_firm_name, vch_firm_district_id FROM bwfl.registration_of_bwfl_lic_holder "
	 * +
	 * " where vch_approval='V' and vch_license_number::text='"+lic_no.trim()+"'"
	 * ;
	 * 
	 * String beer =
	 * " SELECT vch_app_id_f, vch_b1_lic_no, brewery_nm FROM public.bre_mst_b1_lic "
	 * + " where   vch_b1_lic_no='"+lic_no.trim()+"' ";
	 * 
	 * conn = ConnectionToDataBase.getConnection();
	 * 
	 * if (action.license_type.equals("FL3") &&
	 * action.getLicenseing().equals("IMFL")) { ps =
	 * conn.prepareStatement(fl3_imfl); rs = ps.executeQuery(); if (rs.next()) {
	 * name = rs.getString("vch_undertaking_name");
	 * action.setDistillery_iddd(rs.getInt("int_distillery_id"));
	 * action.setUnitid(rs.getInt("int_lic_id"));
	 * action.setDistricid(rs.getInt("vch_unit_dist")); } } if
	 * (action.license_type.equals("FL3") &&
	 * action.getLicenseing().equals("LAB")) { ps =
	 * conn.prepareStatement(fl3_imfl); rs = ps.executeQuery(); if (rs.next()) {
	 * name = rs.getString("vch_undertaking_name");
	 * action.setDistillery_iddd(rs.getInt("int_distillery_id"));
	 * action.setUnitid(rs.getInt("int_lic_id"));
	 * action.setDistricid(rs.getInt("vch_unit_dist")); } } if
	 * (action.license_type.equals("FL3") &&
	 * action.getLicenseing().equals("BEER")) { ps =
	 * conn.prepareStatement(fl3_beer); rs = ps.executeQuery(); if (rs.next()) {
	 * name = rs.getString("brewery_nm");
	 * action.setDistillery_iddd(rs.getInt("int_brewry_id"));
	 * action.setBrewryid(String.valueOf(rs.getInt("int_brewry_id")));
	 * action.setUnitid(rs.getInt("int_lic_id"));
	 * 
	 * if(rs.getString("int_upkrm_district_id")==null){
	 * action.setDistricid(rs.getInt("int_upkrm_district_id")); }else{
	 * action.setDistricid(rs.getInt("upkrm_district_name")); } } } if
	 * (action.license_type.equals("FL3A") &&
	 * action.getLicenseing().equals("IMFL")) { ps =
	 * conn.prepareStatement(fl3a_imfl); rs = ps.executeQuery(); if (rs.next())
	 * { name = rs.getString("vch_unit_name");
	 * if(rs.getInt("int_distillery_id")==0){
	 * action.setDistillery_iddd(rs.getInt("int_brewery_id")); }else{
	 * action.setDistillery_iddd(rs.getInt("int_distillery_id")); }
	 * action.setUnitid(rs.getInt("int_app_id"));
	 * action.setDistricid(rs.getInt("vch_unit_dist")); } } if
	 * (action.license_type.equals("FL3A") &&
	 * action.getLicenseing().equals("LAB")) { ps =
	 * conn.prepareStatement(fl3a_imfl); rs = ps.executeQuery(); if (rs.next())
	 * { name = rs.getString("vch_unit_name");
	 * if(rs.getInt("int_distillery_id")==0){
	 * action.setDistillery_iddd(rs.getInt("int_brewery_id")); }else{
	 * action.setDistillery_iddd(rs.getInt("int_distillery_id")); }
	 * action.setUnitid(rs.getInt("int_app_id"));
	 * action.setDistricid(rs.getInt("vch_unit_dist")); } } if
	 * (action.license_type.equals("FL3A") &&
	 * action.getLicenseing().equals("BEER")) { ps =
	 * conn.prepareStatement(fl3a_beer); rs = ps.executeQuery(); if (rs.next())
	 * { name = rs.getString("vch_unit_name");
	 * action.setBrewryid(String.valueOf(rs.getInt("int_brewery_id")));
	 * 
	 * action.setDistillery_iddd(rs.getInt("int_brewery_id"));
	 * 
	 * action.setUnitid(rs.getInt("int_app_id"));
	 * 
	 * if(rs.getString("int_upkrm_district_id")!=null){
	 * action.setDistricid(rs.getInt("int_upkrm_district_id")); }else{
	 * action.setDistricid(rs.getInt("upkrm_district_name")); } } }
	 * 
	 * if
	 * (action.license_type.equals("BWFL2")||action.license_type.equals("BWFL2B"
	 * )||action.license_type.equals("BWFL2C")) { ps =
	 * conn.prepareStatement(bwfl); rs = ps.executeQuery(); if (rs.next()) {
	 * name = rs.getString("vch_firm_name");
	 * action.setDistillery_iddd(rs.getInt("int_id"));
	 * action.setUnitid(rs.getInt("int_id"));
	 * action.setDistricid(rs.getInt("vch_firm_district_id")); } }if
	 * (action.license_type.equals("FL2D")) { ps = conn.prepareStatement(fl2d);
	 * rs = ps.executeQuery(); if (rs.next()) { name =
	 * rs.getString("vch_firm_name");
	 * action.setDistillery_iddd(rs.getInt("int_app_id"));
	 * action.setUnitid(rs.getInt("int_app_id"));
	 * action.setDistricid(rs.getInt("core_district_id"));} } if
	 * (action.getLicenseing().equals("BEER")) { ps =
	 * conn.prepareStatement(beer); rs = ps.executeQuery(); if (rs.next()) {
	 * name = rs.getString("brewery_nm");
	 * action.setDistillery_iddd(rs.getInt("vch_app_id_f"));
	 * action.setUnitid(rs.getInt("vch_app_id_f"));
	 * action.setDistricid(rs.getInt("vch_dist_id"));} } } catch (Exception e) {
	 * e.printStackTrace(); } finally { try { if (conn != null) conn.close(); if
	 * (ps != null) ps.close(); if (rs != null) rs.close(); } catch (Exception
	 * e) { e.printStackTrace(); } } return name; }
	 */
	public ArrayList yearListImpl(
			BrandRegistrationForOtherDistilleryForCSDAction action) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;
		try {
			query = "SELECT id, year FROM public.financial_year_master where flg::text='A'";
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				SelectItem item = new SelectItem();
				item.setValue(String.valueOf(rs.getInt("id")));
				item.setLabel(rs.getString("year"));
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

	public ArrayList unitListImpl(BrandRegistrationForOtherDistilleryForCSDAction act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("---Select---");
		item.setValue(0);
		list.add(item);
		try {
			String fl3 = " select a.int_lic_id, b.vch_undertaking_name FROM licence.licence_entery_fl3_fl1 a, public.dis_mst_pd1_pd2_lic b "
					+ " where vch_licence_type='FL3'  and a.int_distillery_id=b.int_app_id_f ";

			String fl3a = "SELECT int_app_id, vch_unit_name FROM licence.fl3a_fl1a where vch_licence_type='FL3A'";

			String fl2d = "select int_app_id, vch_firm_name from licence.fl2_2b_2d where vch_license_type='FL2D' and vch_flag='O'";

			String bwfl = "select vch_firm_name,int_id  FROM bwfl.registration_of_bwfl_lic_holder where vch_approval='A'";

			conn = ConnectionToDataBase.getConnection();
			if (act.license_type.equals("FL3")) {
				ps = conn.prepareStatement(fl3);
				rs = ps.executeQuery();
				while (rs.next()) {
					item = new SelectItem();
					item.setValue(rs.getInt("int_lic_id"));
					item.setLabel(rs.getString("vch_undertaking_name"));
					list.add(item);
				}
			}
			if (act.license_type.equals("FL3A")) {
				ps = conn.prepareStatement(fl3a);
				rs = ps.executeQuery();
				while (rs.next()) {
					item = new SelectItem();
					item.setValue(rs.getInt("int_app_id"));
					item.setLabel(rs.getString("vch_unit_name"));
					list.add(item);
				}
			}
			if (act.license_type.equals("BWFL2")) {
				ps = conn.prepareStatement(bwfl);
				rs = ps.executeQuery();
				while (rs.next()) {
					item = new SelectItem();
					item.setValue(rs.getInt("int_id"));
					item.setLabel(rs.getString("vch_firm_name"));
					list.add(item);
				}
			}
			if (act.license_type.equals("FL2D")) {
				ps = conn.prepareStatement(fl2d);
				rs = ps.executeQuery();
				while (rs.next()) {
					item = new SelectItem();
					item.setValue(rs.getInt("int_app_id"));
					item.setLabel(rs.getString("vch_firm_name"));
					list.add(item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/*
	 * public ArrayList
	 * getLicenselist(BrandRegistrationForOtherDistilleryForCSDAction act) {
	 * ArrayList list = new ArrayList(); Connection conn = null;
	 * PreparedStatement ps = null; ResultSet rs = null; SelectItem item = new
	 * SelectItem(); item.setLabel("--select--"); item.setValue("");
	 * list.add(item); try {
	 * 
	 * 
	 * String fl3_1 =
	 * " select distinct a.int_lic_id, a.vch_licence_no, b.brewery_nm FROM licence.licence_entery_fl3_fl1 a, public.bre_mst_b1_lic b "
	 * +
	 * " where a.vch_licence_type='FL3' and a.int_distillery_id=b.vch_app_id_f and a.vch_lic_unit_type='B'"
	 * ;
	 * 
	 * String fl3_1 =
	 * " SELECT int_lic_id, vch_licence_no FROM licence.licence_entery_fl3_fl1 "
	 * + " WHERE vch_licence_type='FL3' AND int_brewry_id="+act.
	 * getBrewery_id_logged_in()+" " + " AND vch_lic_unit_type='B'";
	 * 
	 * String fl3_2 =
	 * " select distinct a.int_lic_id, a.vch_licence_no, b.vch_undertaking_name FROM licence.licence_entery_fl3_fl1 a, public.dis_mst_pd1_pd2_lic b "
	 * +
	 * " where a.vch_licence_type='FL3' and a.int_distillery_id=b.int_app_id_f and a.vch_lic_unit_type='D'"
	 * ;
	 * 
	 * String fl3_2 =
	 * " SELECT int_lic_id, vch_licence_no FROM licence.licence_entery_fl3_fl1 "
	 * + " WHERE vch_licence_type='FL3' AND int_distillery_id="+act.
	 * getDistillery_id_logged_in()+" " + " AND vch_lic_unit_type='D'";
	 * 
	 * String fl3a_1 =
	 * " SELECT distinct int_app_id, vch_license_fl1a, vch_license_fl3a, vch_unit_name FROM licence.fl3a_fl1a where "
	 * + " vch_licence_type='FL3A' and vch_lic_unit_type='B'";
	 * 
	 * String fl3a_1 =
	 * " SELECT vch_license_fl3a FROM licence.fl3a_fl1a where vch_licence_type='FL3A' "
	 * +
	 * " and vch_lic_unit_type='B' and int_brewery_id="+act.getBrewery_id_logged_in
	 * ()+"";
	 * 
	 * String fl3a_2 =
	 * " SELECT distinct int_app_id, vch_license_fl1a, vch_license_fl3a, vch_unit_name FROM licence.fl3a_fl1a where "
	 * + " vch_licence_type='FL3A' and vch_lic_unit_type='D'";
	 * 
	 * String fl3a_2 =
	 * " SELECT distinct vch_license_fl3a FROM licence.fl3a_fl1a where vch_licence_type='FL3A' "
	 * + " and vch_lic_unit_type='D' and int_distillery_id="+act.
	 * getDistillery_id_logged_in()+"";
	 * 
	 * String bwfl =
	 * "select vch_license_number FROM bwfl.registration_of_bwfl_lic_holder where vch_approval='V'"
	 * ;
	 * 
	 * String fl2d =
	 * "select vch_licence_no from licence.fl2_2b_2d where vch_license_type='FL2D' and vch_flag='O'"
	 * ;
	 * 
	 * conn = ConnectionToDataBase.getConnection();
	 * 
	 * if (act.license_type.equals("FL3") && act.getLicenseing().equals("BEER"))
	 * { ps = conn.prepareStatement(fl3_1); rs = ps.executeQuery(); while
	 * (rs.next()) { item = new SelectItem();
	 * item.setValue(rs.getString("vch_licence_no"));
	 * item.setLabel(rs.getString("vch_licence_no")); list.add(item); } }if
	 * (act.license_type.equals("FL3") && act.getLicenseing().equals("IMFL")) {
	 * ps = conn.prepareStatement(fl3_2); rs = ps.executeQuery(); while
	 * (rs.next()) { item = new SelectItem();
	 * item.setValue(rs.getString("vch_licence_no"));
	 * item.setLabel(rs.getString("vch_licence_no")); list.add(item); } }
	 * 
	 * if (act.license_type.equals("FL3") && act.getLicenseing().equals("LAB"))
	 * { ps = conn.prepareStatement(fl3_2); rs = ps.executeQuery(); while
	 * (rs.next()) { item = new SelectItem();
	 * item.setValue(rs.getString("vch_licence_no"));
	 * item.setLabel(rs.getString("vch_licence_no")); list.add(item); } } if
	 * (act.license_type.equals("FL3A") && act.getLicenseing().equals("BEER")) {
	 * ps = conn.prepareStatement(fl3a_1); rs = ps.executeQuery(); while
	 * (rs.next()) { item = new SelectItem();
	 * item.setValue(rs.getString("vch_license_fl3a"));
	 * item.setLabel(rs.getString("vch_license_fl3a")); list.add(item); } }if
	 * (act.license_type.equals("FL3A") && act.getLicenseing().equals("IMFL")) {
	 * ps = conn.prepareStatement(fl3a_2); rs = ps.executeQuery(); while
	 * (rs.next()) { item = new SelectItem();
	 * item.setValue(rs.getString("vch_license_fl3a"));
	 * item.setLabel(rs.getString("vch_license_fl3a")); list.add(item); } }
	 * 
	 * if (act.license_type.equals("FL3A") && act.getLicenseing().equals("LAB"))
	 * { ps = conn.prepareStatement(fl3a_2); rs = ps.executeQuery(); while
	 * (rs.next()) { item = new SelectItem();
	 * item.setValue(rs.getString("vch_license_fl3a"));
	 * item.setLabel(rs.getString("vch_license_fl3a")); list.add(item); } } if
	 * (act
	 * .license_type.equals("BWFL2")||act.license_type.equals("BWFL2B")||act.
	 * license_type.equals("BWFL2C")) { ps = conn.prepareStatement(bwfl); rs =
	 * ps.executeQuery(); while (rs.next()) { item = new SelectItem();
	 * item.setValue(rs.getString("vch_license_number"));
	 * item.setLabel(rs.getString("vch_license_number")); list.add(item); } }if
	 * (act.license_type.equals("FL2D")) { ps = conn.prepareStatement(fl2d); rs
	 * = ps.executeQuery(); while (rs.next()) { item = new SelectItem();
	 * item.setValue(rs.getString("vch_licence_no"));
	 * item.setLabel(rs.getString("vch_licence_no")); list.add(item); } } }
	 * catch (Exception e) { e.printStackTrace(); } finally { try { if (conn !=
	 * null) conn.close(); if(ps!=null)ps.close(); } catch (Exception e) {
	 * e.printStackTrace(); } } return list; }
	 */

	public ArrayList liqTypeListImpl(
			BrandRegistrationForOtherDistilleryForCSD_BrandDetailDatatable act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;
		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("");
		list.add(item);

		try {
			query = "select id, description from distillery.imfl_type order by id";
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setValue(String.valueOf(rs.getInt("id")));
				item.setLabel(rs.getString("description"));
				list.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public String getImfl_type(String val) {
		String imfl = "";
		int id = Integer.parseInt(val);
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select description from distillery.imfl_type where id=?";
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				imfl = rs.getString("description");
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
		return imfl;
	}

	public ArrayList descListImpl(
			BrandRegistrationForOtherDistilleryForCSDAction ac) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = null;
		item = new SelectItem();
		item.setLabel("--select--");
		item.setValue(0);
		list.add(item);

		try {
			String query = "select * from distillery.bolttling_fee where liqour_type ='IMFL' order by id";
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setValue(rs.getString("id"));
				item.setLabel(rs.getString("desc"));
				list.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	public String hologram(int id) {
		String list = "";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;
		try {
			query = "select hologram from distillery.liscence_category where id="
					+ id;
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				list = rs.getString("hologram");
			} else {
				list = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList liqCatListImpl(
			BrandRegistrationForOtherDistilleryForCSD_BrandDetailDatatable act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;
		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("");
		list.add(item);
		try {
			query = "select * from distillery.liscence_category ";
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setValue(String.valueOf(rs.getInt("id")));
				item.setLabel(rs.getString("description"));
				list.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public String getLicenseeDetails(String license_number) {
		String detail = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM public.dis_mst_pd1_pd2_lic where vch_pd1_pd2_lic_no='"
				+ license_number + "'";
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				detail = rs.getString("vch_undertaking_name");
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
		return detail;
	}

	public int getDistId(String license_number) {
		int id = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM public.dis_mst_pd1_pd2_lic where vch_pd1_pd2_lic_no='"
				+ license_number + "'";
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("int_app_id_f");
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
		return id;
	}

	public void saveMethod(BrandRegistrationForOtherDistilleryForCSDAction ac) {
		PreparedStatement ps = null;
		Connection conn = null;
		int saveStatus = 0;

		String query = " INSERT INTO distillery.brand_registration(brand_id, brand_name, liquor_category, year, "
				+ " licencee_dtl, distillery_id, strength, liquor_type, license_category, vch_license_type,"
				+ "  license_number, brewery_id, int_fl2a_id) VALUES "
				+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			saveStatus = 0;
			int seq = this.maxId(this);
			for (int i = 0; i < ac.getAddRowBranding().size(); i++) {
				ps = conn.prepareStatement(query);
				BrandRegistrationForOtherDistilleryForCSD_BrandDetailDatatable dt = (BrandRegistrationForOtherDistilleryForCSD_BrandDetailDatatable) ac
						.getAddRowBranding().get(i);
				ps.setInt(1, seq);
				ps.setString(2, dt.getBrandName());
				ps.setDouble(3, Double.parseDouble(dt.getLiquorCategory()));
				ps.setInt(4, Integer.parseInt(ac.getVch_year()));
				ps.setString(5, "FL2A");
			//	ps.setInt(6, Integer.parseInt(ac.getLic_type_int_id()));
				ps.setInt(6, 0);
				ps.setDouble(7, Double.parseDouble(dt.getStrength_addrow()));

				if (ac.getLicenseing().equalsIgnoreCase("CL")) {
					ps.setInt(8, 3);
				} else if (ac.getLicenseing().equalsIgnoreCase("IMFL")) {
					ps.setInt(8, 1);
				} else if (ac.getLicenseing().equalsIgnoreCase("IMPORTEDFL")) {
					ps.setInt(8, 4);
				} else if (ac.getLicenseing().equalsIgnoreCase("WINE")) {
					ps.setInt(8, 6);
				} else if (ac.getLicenseing().equalsIgnoreCase("BEER")) {
					ps.setInt(8, 2);
				} else if (ac.getLicenseing().equalsIgnoreCase("LAB")) {
					ps.setInt(8, 7);
				} else if (ac.getLicenseing().equalsIgnoreCase("IMPORTEDBEER")) {
					ps.setInt(8, 5);
				} else if (ac.getLicenseing().equalsIgnoreCase("IMPORTEDLAB")) {
					ps.setInt(8, 8);
				} else if (ac.getLicenseing().equalsIgnoreCase("IMPORTEDWINE")) {
					ps.setInt(8, 9);
				} else {
					ps.setInt(8, 0);
				}

				ps.setString(9, ac.getLicenseing());

				 
					ps.setString(10, ac.getLicense_type());
				 

				ps.setString(11, "FL2A");
				ps.setInt(12, 0);
				ps.setInt(13, Integer.parseInt(ac.getLic_type_int_id()));

				saveStatus = ps.executeUpdate();
				seq = seq + 1;
			}
			if (saveStatus > 0) {
				ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,
						Constants.SAVED_SUCESSFULLY);
				conn.commit();
				ac.reset1();
			} else {
				conn.rollback();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
						Constants.NOT_SAVED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void saveMethod2(BrandRegistrationForOtherDistilleryForCSDAction action) {
			
		Connection con = null;
		PreparedStatement ps = null;
		int status = 0;String chk="";String val="";
		
		String  seqall="0";

		String query = " INSERT INTO distillery.packaging_details(package_name, quantity, package_type, "
				+ " mrp, edp, duty, adduty, wsmargin, retmargin, sno, brand_id_fk, package_id, box_id, code_generate_through) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

		try {
			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);
		//	int seqq = this.getId(); //---old -----
			for (int i = 0; i < action.getAddRowPackaging().size(); i++) {
				ps = con.prepareStatement(query);
				BrandRegistrationForOtherDistilleryForCSD_BrandDatatable dt = (BrandRegistrationForOtherDistilleryForCSD_BrandDatatable) action
						.getAddRowPackaging().get(i);

				if (dt.isDisableFlag() == false) {
					int seqq = this.getId(seqall);
					System.out.println("----- int seqq ---"+seqq);
					seqall=seqq+",";
					
					System.out.println("----- seqall ---"+seqall);
					
					System.out.println("inside loop ===="+i);
					ps.setString(1, dt.getPackage_name());
					ps.setDouble(2, Double.parseDouble(dt.getQuantity()));
					ps.setString(3, dt.getPackage_type());
					ps.setDouble(4, dt.getMrp1());
					ps.setDouble(5, dt.getEdp1());
					//ps.setDouble(6, dt.getDuty1());
					//ps.setDouble(7, dt.getAddDuty1());
					ps.setDouble(8, dt.getWsMargin1());
					ps.setDouble(9, dt.getRetMargin1());
					ps.setInt(10, dt.getSno());
					ps.setInt(11, action.getBrand_idd());
					ps.setInt(12, seqq);
					ps.setInt(13, this.getBoxSize(dt, Integer.parseInt(dt.getQuantity())));
					//ps.setInt(14, this.getdistrictid(action.getAppid()));
					
					
					
					String code="";
				int sumcode=0;
				
				////////////////////district code-2 digit////////////////////
				
				if(String.valueOf(this.getdistrictid(action.getAppid())).length()==2){
					code=String.valueOf(this.getdistrictid(action.getAppid()));
				}else if(String.valueOf(this.getdistrictid(action.getAppid())).length()==1){
					code="0"+String.valueOf(this.getdistrictid(action.getAppid()));
				}else{ 
				chk="District Code missing!";
				code="0";
				}
				 
				////////////////////license type code-2 digit////////////////////
				
				if(action.getLicenseing().equalsIgnoreCase("CL")){
					code=code+"08"; 
				}else if(action.getLicense_type().equalsIgnoreCase("FL3")){
					code=code+"01"; 
				}else if(action.getLicense_type().equalsIgnoreCase("FL3A")){
					code=code+"02"; 
				}else if(action.getLicense_type().equalsIgnoreCase("FL2D")){
					code=code+"03"; 
				}else if(action.getLicense_type().equalsIgnoreCase("BWFL2A")){
					code=code+"04"; 
				}else if(action.getLicense_type().equalsIgnoreCase("BWFL2B")){
					code=code+"05"; 
				}else if(action.getLicense_type().equalsIgnoreCase("BWFL2C")){
					code=code+"06"; 
				}else if(action.getLicense_type().equalsIgnoreCase("BWFL2D")){
					code=code+"07"; 
				}
				else if(action.getLicense_type().equalsIgnoreCase("FL2A")){
					code=code+"09"; 
				}
				else{					
					chk=chk+"License Type Missing!";code="0";
				} 
				 
				//////////////////// unit code-2 digit////////////////////
				
				if(action.getAppid()>0){										
						if(String.valueOf(action.getAppid()).length()==3){
							code=code+String.valueOf(action.getAppid());
						}else if(String.valueOf(action.getAppid()).length()==2){
							code=code+"0"+String.valueOf(action.getAppid());
						}else if(String.valueOf(action.getAppid()).length()==1){
							code=code+"00"+String.valueOf(action.getAppid());
						}else{
							chk=chk+"D-Unit Code Missing!";
							code="0";
						}				 
				} else{
					chk=chk+"DB-Unit Code Missing!";code="0";
				}
				
				////////////////////Liqour Type-2 digit////////////////////
				
				if(action.getLicenseing().equalsIgnoreCase("CL")){					
					if(action.getBrandcat().equalsIgnoreCase("10")){
						ps.setDouble(6, 30.834);
						ps.setDouble(7, 1.12);
					}else if(action.getBrandcat().equalsIgnoreCase("11")){
						ps.setDouble(6, 30.834);
						ps.setDouble(7, 1.08);
					} else if(action.getBrandcat().equalsIgnoreCase("12")){
						ps.setDouble(6, 44.40);
						ps.setDouble(7, 3.51);
					}else if(action.getBrandcat().equalsIgnoreCase("13")){
						ps.setDouble(6, 52.788);
						ps.setDouble(7, 2.63);
					}else{
						ps.setDouble(6, dt.getDuty1());
						ps.setDouble(7, dt.getAddDuty1());
					}					
				}else if(action.getLicenseing().equalsIgnoreCase("IMFL")){
					ps.setDouble(6, dt.getDuty1());
					ps.setDouble(7, dt.getAddDuty1());					
				}else if(action.getLicenseing().equalsIgnoreCase("IMPORTEDFL")){
					ps.setDouble(6, dt.getDuty1());					
				}else if(action.getLicenseing().equalsIgnoreCase("WINE")){
					ps.setDouble(6, dt.getDuty1());
					ps.setDouble(7, dt.getAddDuty1());					 
				}else if(action.getLicenseing().equalsIgnoreCase("BEER")){
					ps.setDouble(6, dt.getDuty1());
					ps.setDouble(7, dt.getAddDuty1());					
				}else if(action.getLicenseing().equalsIgnoreCase("IMPORTEDBEER")){
					ps.setDouble(6, dt.getDuty1());
					ps.setDouble(7, dt.getAddDuty1());
				}else if(action.getLicenseing().equalsIgnoreCase("IMPORTEDLAB")){
					ps.setDouble(6, dt.getDuty1());
					ps.setDouble(7, dt.getAddDuty1());
				}else if(action.getLicenseing().equalsIgnoreCase("IMPORTEDWINE")){
					ps.setDouble(6, dt.getDuty1());
					ps.setDouble(7, dt.getAddDuty1());
				}else if(action.getLicenseing().equalsIgnoreCase("LAB")){
					ps.setDouble(6, dt.getDuty1());
					ps.setDouble(7, dt.getAddDuty1());
				}else{
					chk=chk+"Liqour Code Missing!";code="0";
				}
				
				////////////////////brnd-size code-4 digit////////////////////
				
				if(seqq>0){
				if(seqq>999){
					code=code+seqq;
					sumcode=sumcode+Integer.parseInt(dt.getPackage_type());
				}else if(seqq>99){
					sumcode=sumcode+Integer.parseInt(dt.getPackage_type());
					code=code+"0"+seqq;
				}
				else if(seqq>9){
					sumcode=sumcode+Integer.parseInt(dt.getPackage_type());
					code=code+"00"+seqq;
				}else if(seqq<=9){
					sumcode=sumcode+Integer.parseInt(dt.getPackage_type());
					code=code+"000"+seqq;
				}else {
					chk=chk+"Package Id Missing!";code="0";
				}
				}else {
					chk=chk+"Package Seq Missing!";code="0";
				}
				
				////////////////////packagetype code-1 digit////////////////////
				
			if(dt.getPackage_type()!=null && dt.getPackage_type().length()>0){
				
			if(dt.getPackage_type().length()==1){
				code=code+dt.getPackage_type();
				sumcode=sumcode+Integer.parseInt(dt.getPackage_type());
			}else{
				chk=chk+"Package Type Missing!";code="0";
			}			
			}else {
				chk=chk+"Package Type Missing!";code="0";
			}
		
			String val1= String.valueOf(this.getOddSum(code));
			   val=code+ val1;	 				 			 
				ps.setString(14,  val);
				status = ps.executeUpdate();
			//	seqq=seqq+1;  //-----old ---
				System.out.println("-------- csd  seqq --------"+seqq);
			
				if(status>0)
				{	 
						 String brand_seq = " delete  FROM distillery.brand_seq where seq='"+seqq+"' ";
						 System.out.println("--------delete brand csd brand_seq --------"+brand_seq);
						 status=0;
						PreparedStatement pst = con.prepareStatement(brand_seq);
						status=pst.executeUpdate();
						pst.close();
				}
					
					
					
					
				}
			}
		
			if (status > 0 && val.length()==13) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Record Saved", "Record Saved"));
				action.reset2();
				con.commit();
			} else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Record Not Saved","Record Not Saved"));
				con.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage()));	
		} finally {
			try {
				if (ps != null)ps.close();
				if (con != null)con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getdistrictid(int id){
		int idd=0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT core_district_id, vch_core_district_name FROM licence.fl2_2b_2d where int_app_id=?";
		try{
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,id);
			rs = ps.executeQuery();
			if(rs.next()){
				
					idd = rs.getInt("core_district_id");
							
			}
		}catch (Exception e) {
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
		return idd;
	}
	
	
	

	public static int getOddSum(String n) {
		int sum = 0;
		int even = 0;
		int odd = 0;
		int checkDigit = 0;
		char ar[] = n.toCharArray();
		for (int i = ar.length - 1; i >= 0; i--) {
			if (i % 2 != 0) {
				System.out.println("evennnn" + ar[i]);
				odd = odd + Character.getNumericValue(ar[i]);
			} else {
				System.out.println("oddddddd" + ar[i]);
				even = even + Character.getNumericValue(ar[i]);
			}
		}
		sum = (odd * 3) + even;
		System.out.println("summm" + sum);
		System.out.println("even" + even);
		System.out.println("odd sum" + odd);
		if (sum % 10 != 0) {
			System.out.println("SUMM    " + sum);
			checkDigit = (10 - sum % 10);
			System.out.println("checkDigit  " + checkDigit);
		}
		return checkDigit;
	}

	public int maxId(BrandRegistrationForOtherDistilleryForCSDImpl brandImpl) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(brand_id) as id from distillery.brand_registration";
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

	public int getId(String seqall) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	//	String query = " select max(package_id) as idd from distillery.packaging_details";
		String query = " SELECT min(seq) as idd FROM distillery.brand_seq where seq not in ("+seqall+"0)";
		
		System.out.println("----- query ------"+query);
		
		int maxid = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				maxid = rs.getInt("idd");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	//	return maxid + 1;  //--------- old ------
		return maxid ;
	}

	// ===============================showing details of saved
	// packaging==================================

	public ArrayList getPackagingDetails(
			BrandRegistrationForOtherDistilleryForCSDAction action) {
		ArrayList list = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = " SELECT quantity, package_name, package_type, mrp, edp, duty, adduty, wsmargin, "
				+ " retmargin, brand_id_fk, package_id, sno, code_generate_through, box_id "
				+ " FROM distillery.packaging_details WHERE brand_id_fk='"
				+ action.getBrand_idd() + "' ";

		try {
			list = new ArrayList();
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				BrandRegistrationForOtherDistilleryForCSD_BrandDatatable dt = new BrandRegistrationForOtherDistilleryForCSD_BrandDatatable();
				dt.setSno(rs.getInt("sno"));
				dt.setPackage_name(rs.getString("package_name"));
				dt.setQuantity(rs.getString("quantity"));
				dt.setGct(rs.getString("code_generate_through"));
				dt.setBox_id(rs.getInt("box_id"));
				dt.setPackage_type(rs.getString("package_type"));
				dt.setMrp1(rs.getDouble("mrp"));
				dt.setEdp1(rs.getDouble("edp"));
				dt.setDuty1(rs.getDouble("duty"));
				dt.setAddDuty1(rs.getDouble("adduty"));
				dt.setWsMargin1(rs.getDouble("wsmargin"));
				dt.setRetMargin1(rs.getDouble("retmargin"));
				dt.setDisableFlag(true);
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

	// =====================get quantity details=========================

	public ArrayList getQntyDetails(
			BrandRegistrationForOtherDistilleryForCSD_BrandDatatable brandDatatable) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--Select--");
		item.setValue("0");
		list.add(item);

		String query = "SELECT qnt_ml_id, qnt_ml_detail FROM distillery.box_size_details";
		String query2 = "SELECT qnt_ml_id, qnt_ml_detail FROM distillery.box_size_details where type='B'";

		try {
			con = ConnectionToDataBase.getConnection();

			FacesContext facesContext = FacesContext.getCurrentInstance();
			BrandRegistrationForOtherDistilleryForCSDAction ba = (BrandRegistrationForOtherDistilleryForCSDAction) facesContext
					.getApplication()
					.createValueBinding(
							"#{brandRegistrationForOtherDistilleryForCSDAction}")
					.getValue(facesContext);
			String value = ba.getLic_cat();

			if (value.equals("BEER")) {
				ps = con.prepareStatement(query2);
				rs = ps.executeQuery();
			} else {
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();
			}
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("qnt_ml_detail"));
				item.setValue(rs.getInt("qnt_ml_detail"));
				list.add(item);
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

	public int Check(String ccNumber) {
		int sum = 0;
		boolean alternate = false;
		for (int i = ccNumber.length() - 1; i >= 0; i--) {
			int n = Integer.parseInt(ccNumber.substring(i, i + 1));
			if (alternate) {
				System.out.println("n====================================" + n);
				n *= 2;
				if (n > 9) {
					n = (n % 10) + 1;
				}
			}
			sum += n;
			System.out.println("sum====================================" + sum);
			alternate = !alternate;
		}
		sum = sum % 10;
		System.out.println("fin sum====================================" + sum);
		return sum;
	}

}
