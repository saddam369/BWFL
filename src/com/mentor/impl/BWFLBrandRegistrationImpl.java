package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import com.mentor.action.BWFLBrandRegistrationAction;
import com.mentor.datatable.BWFL_BrandDatatable;
import com.mentor.datatable.BWFL_BrandDetailDataTable;
import com.mentor.datatable.BWFL_Brand_ViewDatatable;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.sun.facelets.FaceletContext;

public class BWFLBrandRegistrationImpl {
	
	
	public void getHidden(BWFLBrandRegistrationAction action){
		Connection con = null;
		PreparedStatement ps = null, ps1=null,ps2=null;
		ResultSet rs = null,rs1=null,rs2=null;
		
		 String query3 = " SELECT * FROM bwfl.registration_of_bwfl_lic_holder where vch_approval='V' and " +
						" mobile_number='"+ResourceUtil.getUserNameAllReq().trim()+"' order by vch_firm_name";
		
		
		try{
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query3);
			rs = ps.executeQuery();
			 
					ps2 = con.prepareStatement(query3);
					rs2 = ps2.executeQuery();
					if(rs2.next()){
						action.setBwfl_id_logged_in(rs2.getInt("int_id"));
						action.setBwfl_firm_name_logged_in(rs2.getString("vch_firm_name"));
						action.setBwfl_license_number_logged_in(rs2.getString("vch_license_number"));
						action.setBwfl_flag(false);
						action.setBwfl_flag_bwfl_case(true);
						action.setBrewery_flag(true);
					 
				 
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(rs1!=null)rs1.close();
				if(ps1!=null)ps1.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public ArrayList getDis_Name(BWFLBrandRegistrationAction action){
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
				
		String query = " SELECT int_app_id_f, vch_pd1_pd2_lic_no, vch_undertaking_name FROM public.dis_mst_pd1_pd2_lic " +
					   " where vch_finalize='F' and vch_license_approval_flag='A' and vch_verify_flag='V' " +
					   " and vch_wrk_phon="+ResourceUtil.getUserNameAllReq().trim()+" order by vch_undertaking_name ";
		
		
		
		
		try{
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()){
				item = new SelectItem();
				item.setLabel(rs.getString("vch_undertaking_name"));
				item.setValue(rs.getString("vch_pd1_pd2_lic_no"));
				list.add(item);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return list;
	}
	public ArrayList getBre_Name(BWFLBrandRegistrationAction action){
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--Select--");
		item.setValue("");
		list.add(item);
		String query = " SELECT vch_app_id_f, vch_b1_lic_no, brewery_nm FROM public.bre_mst_b1_lic " +
					   " where   vch_verify_flag='V' order by brewery_nm ";
		try{
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				item = new SelectItem();
				item.setLabel(rs.getString("brewery_nm"));
				item.setValue(rs.getString("vch_b1_lic_no"));
				list.add(item);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return list;
	}
	public int getBoxSize(BWFL_BrandDatatable brandDatatable, int boxsize){			 
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id=0;		
		try{
			 
			String query = 	" SELECT box_id, box_size FROM distillery.box_size_details WHERE qnt_ml_detail="+boxsize+" " +
							" ORDER BY box_id ";					
			
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()){
				id=rs.getInt("box_id");			 
		}}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return id;
	}

	public ArrayList getBrand_Details(BWFLBrandRegistrationAction action) {
		ArrayList list = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String lic="";
		
		//FacesContext facesContext = FacesContext.getCurrentInstance();
		//BrandAction bof = (BrandAction)facesContext.getApplication().createValueBinding("#{brandAction}").getValue(facesContext);
		
		if(action.getLicenseing()!=null && action.getLicenseing().length()>0 &&  action.getLicenseing().equalsIgnoreCase("CL")){
			lic=action.getDis_license_no();
		}else if(action.getLicenseing()!=null && action.getLicenseing().length()>0 && !action.getLicenseing().equalsIgnoreCase("CL")){
			lic=action.getLicense_no();
			
		}
		
		String query = " SELECT brand_id, brand_name, description,liquor_category , year, licencee_dtl, distillery_id, " +
						" approval, approvaldt, strength, liquor_type, license_category, vch_license_type, license_number" +
						" FROM distillery.brand_registration a,distillery.liscence_category b where license_number='"+lic+"' " +
						" AND vch_license_type='"+action.getLicense_type()+"' and a.liquor_category = b.id order by brand_id ";
		
	 		
		try {
			list = new ArrayList();
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);		 
			rs = ps.executeQuery();
			 
			int i = 1;
			while (rs.next()) {
				BWFL_Brand_ViewDatatable dt = new BWFL_Brand_ViewDatatable();
				dt.setSrno(i);
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
				dt.setLiqCatDisplay(rs.getString("description"));
				
				
				
				/*if(rs.getInt("liquor_category")==1){
					 dt.setLiqCatDisplay("");
				 }else if(rs.getInt("liquor_category")==1){
					 dt.setLiqCatDisplay("Economy");
				 }
				 else if(rs.getInt("liquor_category")==2){
					 dt.setLiqCatDisplay("Regular");
				 }
				 else if(rs.getInt("liquor_category")==3){
					 dt.setLiqCatDisplay("Medium");
				 }
				 else if(rs.getInt("liquor_category")==4){
					 dt.setLiqCatDisplay("Scotch");
				 }*/
				/* else if(rs.getInt("liquor_category")==5){
					 dt.setLiqCatDisplay("Premium");
				 }
				 else if(rs.getInt("liquor_category")==6){
					 dt.setLiqCatDisplay("Semi Premium");
				 }*/
			/*	 else if(rs.getInt("liquor_category")==5){
					 dt.setLiqCatDisplay("Semi Premium");
				 }
				 else if(rs.getInt("liquor_category")==6){
					 dt.setLiqCatDisplay("Premium");
				 }
				 
				 if(rs.getInt("liquor_category")==11){
					 dt.setLiqCatDisplay("25% Spiced");
				 }
				 else if(rs.getInt("liquor_category")==12){
					 dt.setLiqCatDisplay("36%");
				 }
				 else if(rs.getInt("liquor_category")==13){
					 dt.setLiqCatDisplay("42.8%");
				 }
				 else if(rs.getInt("liquor_category")==10){
					 dt.setLiqCatDisplay("25% Plain");
				 }else if(rs.getInt("liquor_category")==8){
					 dt.setLiqCatDisplay("Mild");
				 }
				 else if(rs.getInt("liquor_category")==9){
					 dt.setLiqCatDisplay("Strong");
				 }*/
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

/*	public ArrayList getBrand_Details(BWFLBrandRegistrationAction action) {
		ArrayList list = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String lic="";
		
		 
		if(action.getLicenseing()!=null && action.getLicenseing().length()>0 &&  action.getLicenseing().equalsIgnoreCase("CL")){
			lic=action.getDis_license_no();
		}else if(action.getLicenseing()!=null && action.getLicenseing().length()>0 && !action.getLicenseing().equalsIgnoreCase("CL")){
			lic=action.getLicense_no();
			
		}
		
		String query = " SELECT brand_id, brand_name, liquor_category, year, licencee_dtl, distillery_id, " +
						" approval, approvaldt, strength, liquor_type, license_category, vch_license_type, license_number" +
						" FROM distillery.brand_registration  where license_number='"+lic+"' " +
						" AND vch_license_type='"+action.getLicense_type()+"' order by brand_id ";
		
 	try {
			list = new ArrayList();
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);		 
			rs = ps.executeQuery();
			int i = 1;
			while (rs.next()) {
				BWFL_Brand_ViewDatatable dt = new BWFL_Brand_ViewDatatable();
				dt.setSrno(i);
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
				 
			 if(rs.getInt("liquor_category")==1){
					 dt.setLiqCatDisplay("Economy");
				 }
				 else if(rs.getInt("liquor_category")==2){
					 dt.setLiqCatDisplay("Regular");
				 }
				 else if(rs.getInt("liquor_category")==3){
					 dt.setLiqCatDisplay("Medium");
				 }
				 else if(rs.getInt("liquor_category")==4){
					 dt.setLiqCatDisplay("Scotch");
				 }
				 
				 else if(rs.getInt("liquor_category")==5){
					 dt.setLiqCatDisplay("Semi Premium");
				 }
				 else if(rs.getInt("liquor_category")==6){
					 dt.setLiqCatDisplay("Premium");
				 }
				 
				 else if(rs.getInt("liquor_category")==11){
					 dt.setLiqCatDisplay("25% Spiced");
				 }
				 else if(rs.getInt("liquor_category")==12){
					 dt.setLiqCatDisplay("36%");
				 }
				 else if(rs.getInt("liquor_category")==13){
					 dt.setLiqCatDisplay("42.8%");
				 }
				 else if(rs.getInt("liquor_category")==10){
					 dt.setLiqCatDisplay("25% Plain");
				 }else if(rs.getInt("liquor_category")==8){
					 dt.setLiqCatDisplay("Mild");
				 }
				 else if(rs.getInt("liquor_category")==9){
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
	}*/

	public String get_Distillery_Details(BWFLBrandRegistrationAction action,String lic_no) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		String name = "";
		try {
			
	/*	String fl3_imfl =  " select a.int_distillery_id, b.vch_undertaking_name, a.int_lic_id, b.vch_unit_dist " +
						   " FROM licence.licence_entery_fl3_fl1 a, public.dis_mst_pd1_pd2_lic b "
						   + " where vch_licence_type='FL3' and a.vch_lic_unit_type='D' and " +
						   " a.int_distillery_id=b.int_app_id_f and a.vch_licence_no::text='"+lic_no.trim()+"'";
		
		
		String fl3_beer = " select  a.int_brewry_id, b.brewery_nm, a.int_lic_id, b.int_upkrm_district_id, " +
					" b.upkrm_district_name FROM licence.licence_entery_fl3_fl1 a, public.bre_mst_b1_lic b  " +
					" where vch_licence_type='FL3' and a.vch_lic_unit_type='B' and a.int_distillery_id=b.vch_app_id_f" +
					" and a.vch_licence_no::text='"+lic_no.trim()+"'";
		
		String fl3a_imfl = " SELECT distinct a.int_app_id, a.int_distillery_id, a.int_brewery_id, a.vch_unit_name, " +
						   " b.vch_unit_dist FROM licence.fl3a_fl1a a, public.dis_mst_pd1_pd2_lic b " +
						   " where a.vch_licence_type='FL3A' and a.vch_lic_unit_type='D' and " +
						   " b.int_app_id_f=a.int_distillery_id and  a.vch_license_fl3a::text='"+lic_no.trim()+"'";
					  
		String fl3a_beer =  " SELECT distinct a.int_app_id, a.int_distillery_id, a.int_brewery_id, a.vch_unit_name, " +
							" b.int_upkrm_district_id,b.upkrm_district_name FROM licence.fl3a_fl1a a, public.bre_mst_b1_lic b " +
							" where a.vch_licence_type='FL3A' and a.vch_lic_unit_type='B' and " +
							" b.vch_app_id_f=a.int_brewery_id and  a.vch_license_fl3a::text='"+lic_no.trim()+"'";
		
		String fl2d = " select int_app_id, vch_firm_name ,coalesce(core_district_id,00) as core_district_id  " +
					  " from licence.fl2_2b_2d where vch_license_type='FL2D' and vch_flag='O' " +
					  " and vch_licence_no::text='"+lic_no.trim()+"'";
		*/
		String bwfl = " select int_id, vch_firm_name, vch_firm_district_id FROM bwfl.registration_of_bwfl_lic_holder " +
					  " where vch_approval='V' and vch_license_number::text='"+lic_no.trim()+"' and vch_license_type=?";
		
		//String beer = " SELECT vch_app_id_f, vch_b1_lic_no, brewery_nm FROM public.bre_mst_b1_lic " +
		//		   	  " where   vch_b1_lic_no='"+lic_no.trim()+"' ";
		
		conn = ConnectionToDataBase.getConnection();
		
		/*if (action.license_type.equals("FL3") && action.getLicenseing().equals("IMFL")) {
				ps = conn.prepareStatement(fl3_imfl);
				rs = ps.executeQuery();
				if (rs.next()) {
					name = rs.getString("vch_undertaking_name");
					action.setDistillery_iddd(rs.getInt("int_distillery_id"));
					action.setUnitid(rs.getInt("int_lic_id"));
					action.setDistricid(rs.getInt("vch_unit_dist"));
				}
			}
		if (action.license_type.equals("FL3") && action.getLicenseing().equals("BEER")) {
				ps = conn.prepareStatement(fl3_beer);
				rs = ps.executeQuery();
				if (rs.next()) {
					name = rs.getString("brewery_nm");
					action.setDistillery_iddd(rs.getInt("int_brewry_id"));
					action.setBrewryid(String.valueOf(rs.getInt("int_brewry_id")));
					action.setUnitid(rs.getInt("int_lic_id"));
					
					if(rs.getString("int_upkrm_district_id")==null){
						action.setDistricid(rs.getInt("int_upkrm_district_id"));
					}else{
						action.setDistricid(rs.getInt("upkrm_district_name"));
					}					
				}
			}
			if (action.license_type.equals("FL3A") && action.getLicenseing().equals("IMFL")) {
				ps = conn.prepareStatement(fl3a_imfl);
				rs = ps.executeQuery();
				if (rs.next()) {
					name = rs.getString("vch_unit_name");
					if(rs.getInt("int_distillery_id")==0){
						action.setDistillery_iddd(rs.getInt("int_brewery_id"));
					}else{
						action.setDistillery_iddd(rs.getInt("int_distillery_id"));
					}	
					action.setUnitid(rs.getInt("int_app_id"));
					action.setDistricid(rs.getInt("vch_unit_dist"));
				}
			}
			if (action.license_type.equals("FL3A") && action.getLicenseing().equals("BEER")) {
				ps = conn.prepareStatement(fl3a_beer);
				rs = ps.executeQuery();
				if (rs.next()) {
					name = rs.getString("vch_unit_name");					
					action.setBrewryid(String.valueOf(rs.getInt("int_brewery_id")));
					
					action.setDistillery_iddd(rs.getInt("int_brewery_id"));					
					
					action.setUnitid(rs.getInt("int_app_id"));
					
					if(rs.getString("int_upkrm_district_id")!=null){
						action.setDistricid(rs.getInt("int_upkrm_district_id"));
					}else{
						action.setDistricid(rs.getInt("upkrm_district_name"));
					}					
				}
			}			
			
			if (action.license_type.equals("BWFL2A")||action.license_type.equals("BWFL2B")||action.license_type.equals("BWFL2C")) {
				ps = conn.prepareStatement(bwfl);
				if(action.license_type.equals("BWFL2A")){
					ps.setString(1, "1");
					}else if(action.license_type.equals("BWFL2B")){
						ps.setString(1, "2");
						}
					else if(action.license_type.equals("BWFL2C")){
						ps.setString(1, "3");
						}
					else if(action.license_type.equals("BWFL2D")){
						ps.setString(1, "4");
						}
				rs = ps.executeQuery();
				if (rs.next()) {
					name = rs.getString("vch_firm_name");
					action.setDistillery_iddd(rs.getInt("int_id"));
					action.setUnitid(rs.getInt("int_id"));
					action.setDistricid(rs.getInt("vch_firm_district_id"));
				}
			}if (action.license_type.equals("FL2D")) {
				ps = conn.prepareStatement(fl2d);
				rs = ps.executeQuery();
				if (rs.next()) {
					name = rs.getString("vch_firm_name");
					action.setDistillery_iddd(rs.getInt("int_app_id"));	
					action.setUnitid(rs.getInt("int_app_id"));
					action.setDistricid(rs.getInt("core_district_id"));}
			}
			if (action.getLicenseing().equals("BEER")) {
				ps = conn.prepareStatement(beer);
				rs = ps.executeQuery();
				if (rs.next()) {
					name = rs.getString("brewery_nm");
					action.setDistillery_iddd(rs.getInt("vch_app_id_f"));	
					action.setUnitid(rs.getInt("vch_app_id_f"));
					action.setDistricid(rs.getInt("vch_dist_id"));}
			}*/
		 		ps = conn.prepareStatement(bwfl);
			if(action.license_type.equals("BWFL2A")){
				ps.setString(1, "1");
				}else if(action.license_type.equals("BWFL2B")){
					ps.setString(1, "2");
					}
				else if(action.license_type.equals("BWFL2C")){
					ps.setString(1, "3");
					}
				else if(action.license_type.equals("BWFL2D")){
					ps.setString(1, "4");
					}
			rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString("vch_firm_name");
				action.setDistillery_iddd(rs.getInt("int_id"));
				action.setUnitid(rs.getInt("int_id"));
				action.setDistricid(rs.getInt("vch_firm_district_id"));
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
		return name;
	}

	public ArrayList yearListImpl(BWFLBrandRegistrationAction action) {
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

	public ArrayList unitListImpl(BWFLBrandRegistrationAction act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue(0);
		list.add(item);
		try {			
			String fl3 =  " select a.int_lic_id, b.vch_undertaking_name FROM licence.licence_entery_fl3_fl1 a, public.dis_mst_pd1_pd2_lic b "
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
			}if (act.license_type.equals("FL3A")) {
				ps = conn.prepareStatement(fl3a);
				rs = ps.executeQuery();
				while (rs.next()) {
					item = new SelectItem();
					item.setValue(rs.getInt("int_app_id"));
					item.setLabel(rs.getString("vch_unit_name"));
					list.add(item);
				}
			}if (act.license_type.equals("BWFL2")) {
				ps = conn.prepareStatement(bwfl);
				rs = ps.executeQuery();
				while (rs.next()) {
					item = new SelectItem();
					item.setValue(rs.getInt("int_id"));
					item.setLabel(rs.getString("vch_firm_name"));
					list.add(item);
				}
			}if (act.license_type.equals("FL2D")) {
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
	public ArrayList getLicenselist(BWFLBrandRegistrationAction act) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--select--");
		item.setValue("");
		list.add(item);
		try {			
			
			
			String fl3_1 = " SELECT int_lic_id, vch_licence_no FROM licence.licence_entery_fl3_fl1 " +
						   " WHERE vch_licence_type='FL3' AND int_brewry_id="+act.getBrewery_id_logged_in()+" " +
						   " AND vch_lic_unit_type='B'";
			
			String fl3_2 = " SELECT int_lic_id, vch_licence_no FROM licence.licence_entery_fl3_fl1 " +
						   " WHERE vch_licence_type='FL3' AND int_distillery_id="+act.getDistillery_id_logged_in()+" " +
						   " AND vch_lic_unit_type='D'";
			
			String fl3a_1 = " SELECT vch_license_fl3a FROM licence.fl3a_fl1a where vch_licence_type='FL3A' " +
							" and vch_lic_unit_type='B' and int_brewery_id="+act.getBrewery_id_logged_in()+"";
			
			String fl3a_2 = " SELECT distinct vch_license_fl3a FROM licence.fl3a_fl1a where vch_licence_type='FL3A' " +
							" and vch_lic_unit_type='D' and int_distillery_id="+act.getDistillery_id_logged_in()+"";
			
			String bwfl = 	" select vch_license_number FROM bwfl.registration_of_bwfl_lic_holder where vch_approval='V' and " +
							" vch_license_type=? AND mobile_number='"+ResourceUtil.getUserNameAllReq()+"' ";
			
			
			
			String fl2d = "select vch_licence_no from licence.fl2_2b_2d where vch_license_type='FL2D' and vch_flag='O'";
			
			conn = ConnectionToDataBase.getConnection();		
			
			if (act.license_type.equals("FL3") && act.getLicenseing().equals("BEER")) {
				ps = conn.prepareStatement(fl3_1);
				rs = ps.executeQuery();
				while (rs.next()) {
					item = new SelectItem();
					item.setValue(rs.getString("vch_licence_no"));
					item.setLabel(rs.getString("vch_licence_no"));
					list.add(item);
				}
			}if (act.license_type.equals("FL3") && act.getLicenseing().equals("IMFL")) {
				ps = conn.prepareStatement(fl3_2);
				rs = ps.executeQuery();
				while (rs.next()) {
					item = new SelectItem();
					item.setValue(rs.getString("vch_licence_no"));
					item.setLabel(rs.getString("vch_licence_no"));
					list.add(item);
				}
			}if (act.license_type.equals("FL3A") && act.getLicenseing().equals("BEER")) {
				ps = conn.prepareStatement(fl3a_1);
				rs = ps.executeQuery();
				while (rs.next()) {
					item = new SelectItem();					
					item.setValue(rs.getString("vch_license_fl3a"));
					item.setLabel(rs.getString("vch_license_fl3a"));									
					list.add(item);
				}
			}if (act.license_type.equals("FL3A") && act.getLicenseing().equals("IMFL")) {
				ps = conn.prepareStatement(fl3a_2);
				rs = ps.executeQuery();
				while (rs.next()) {
					item = new SelectItem();					
					item.setValue(rs.getString("vch_license_fl3a"));
					item.setLabel(rs.getString("vch_license_fl3a"));									
					list.add(item);
				}
			}if (act.license_type.equals("BWFL2A")||act.license_type.equals("BWFL2B")||act.license_type.equals("BWFL2C") ||act.license_type.equals("BWFL2D")) {
				ps = conn.prepareStatement(bwfl);
				if(act.license_type.equals("BWFL2A")){
				ps.setString(1, "1");
				}else if(act.license_type.equals("BWFL2B")){
					ps.setString(1, "2");
					}
				else if(act.license_type.equals("BWFL2C")){
					ps.setString(1, "3");
					}
				else if(act.license_type.equals("BWFL2D")){
					ps.setString(1, "4");
					}
				rs = ps.executeQuery();
				while (rs.next()) {
					item = new SelectItem();
					item.setValue(rs.getString("vch_license_number"));
					item.setLabel(rs.getString("vch_license_number"));
					list.add(item);
				}
			}if (act.license_type.equals("FL2D")) {
				ps = conn.prepareStatement(fl2d);
				rs = ps.executeQuery();
				while (rs.next()) {
					item = new SelectItem();
					item.setValue(rs.getString("vch_licence_no"));
					item.setLabel(rs.getString("vch_licence_no"));
					list.add(item);
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if(ps!=null)ps.close();
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		return list;
	}
	public ArrayList liqTypeListImpl(BWFL_BrandDetailDataTable act) {
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
	public String getImfl_type(String val){
		String imfl = "";
		int id = Integer.parseInt(val);
		Connection con =null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		String query = "select description from distillery.imfl_type where id=?";
		try{
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,id);
			rs = ps.executeQuery();
			if(rs.next()){
				imfl = rs.getString("description");
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(con != null)con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return imfl;
	}

	public ArrayList descListImpl(BWFLBrandRegistrationAction ac) {
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

	public ArrayList liqCatListImpl(BWFL_BrandDetailDataTable act) {
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
	
	public String getLicenseeDetails(String license_number){
		String detail = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM public.dis_mst_pd1_pd2_lic where vch_pd1_pd2_lic_no='"+license_number+"'";
		try{
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()){
				detail = rs.getString("vch_undertaking_name");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return detail;
	}
	
	public int getDistId(String license_number){
		int id = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM public.dis_mst_pd1_pd2_lic where vch_pd1_pd2_lic_no='"+license_number+"'";
		try{
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getInt("int_app_id_f");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return id;
	}
	public void saveMethod(BWFLBrandRegistrationAction ac) {
		PreparedStatement ps = null;
		Connection conn = null;
		int saveStatus = 0;
		
		String query =  " INSERT INTO distillery.brand_registration(brand_id, brand_name, liquor_category, year, " +
						" licencee_dtl, distillery_id, strength, liquor_type, license_category, vch_license_type," +
						"  license_number, brewery_id, int_bwfl_id) VALUES " +
						" (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";		
			
		try {
			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);			
			saveStatus = 0;
			int seq = this.maxId(this);
			for (int i = 0; i < ac.getAddRowBranding().size(); i++) {
				ps = conn.prepareStatement(query);
				BWFL_BrandDetailDataTable dt = (BWFL_BrandDetailDataTable) ac.getAddRowBranding().get(i);
				ps.setInt(1, seq);
				ps.setString(2, dt.getBrandName());
				
				ps.setDouble(3, Double.parseDouble(dt.getLiquorCategory()));
				
				ps.setInt(4, Integer.parseInt(ac.getVch_year()));				
				
				if(ac.isCl_flag2()){					
					ps.setString(5, this.getLicenseeDetails(ac.getDis_license_no()));
				}else{
					ps.setString(5, ac.getLicense_details());
				}
				
				/*if(ac.getDistillery_iddd()==0){
					ps.setInt(6,this.getDistId(ac.getDis_license_no()));
				}else{
					ps.setInt(6, ac.getDistillery_iddd());
				}*/
				//ps.setInt(6, ac.getDistillery_id_logged_in());
				
				ps.setInt(6,0);				
				
				ps.setDouble(7, Double.parseDouble(dt.getStrength_addrow()));

				if(ac.getLicenseing().equalsIgnoreCase("CL")){
					ps.setInt(8, 3);
				}else if(ac.getLicenseing().equalsIgnoreCase("IMFL")){
					ps.setInt(8, 1);
				}else if(ac.getLicenseing().equalsIgnoreCase("LAB")){
					ps.setInt(8, 7);
				}else if(ac.getLicenseing().equalsIgnoreCase("WINE")){
					ps.setInt(8, 6);
				}else if(ac.getLicenseing().equalsIgnoreCase("BEER")){
					ps.setInt(8, 2);
				}else{
					ps.setInt(8, 0);
				}
				
				ps.setString(9, ac.getLicenseing());
				
				if(ac.getLicenseing().equalsIgnoreCase("CL")){
					ps.setString(10, "CL");
				}else{
					ps.setString(10, ac.getLicense_type());
				}
				
				
				if (ac.isCl_flag()) {
					ps.setString(11, ac.getLicense_no());
				} else {
					ps.setString(11, ac.getDis_license_no());
				}
				
				ps.setInt(12,0);
				
				/*if(ac.getLicenseing().equalsIgnoreCase("BEER")){
					ps.setInt(12, Integer.parseInt(ac.getBrewryid()));
				}
				else{
					ps.setInt(12,0);
				}*/
				ps.setInt(13, ac.getBwfl_id_logged_in());
				saveStatus = ps.executeUpdate();
				seq=seq+1;
			}	
			if (saveStatus > 0) {
				ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,Constants.SAVED_SUCESSFULLY);	
				conn.commit();
				ac.reset1();
			} else {
				conn.rollback();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,Constants.NOT_SAVED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {				
				if (ps != null)ps.close();
				if(conn!=null)conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void saveMethod2(BWFLBrandRegistrationAction action) {
		Connection con = null;
		PreparedStatement ps = null;String  val="";
		int status = 0;boolean chk=false;

		String query = " INSERT INTO distillery.packaging_details(package_name, quantity, package_type, "
				+ " mrp, edp, duty, adduty, wsmargin, retmargin, sno, brand_id_fk, package_id, box_id, code_generate_through) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

		try {
			con = ConnectionToDataBase.getConnection();			
			int seqq = this.getId();
			for (int i = 0; i < action.getAddRowPackaging().size(); i++) {
				ps = con.prepareStatement(query);
				BWFL_BrandDatatable dt = (BWFL_BrandDatatable) action.getAddRowPackaging().get(i);
				if(dt.isDisableFlag()== false)
				{
				ps.setString(1, dt.getPackage_name());
				ps.setDouble(2, Double.parseDouble(dt.getQuantity()));
				ps.setString(3, dt.getPackage_type());
				ps.setDouble(4, dt.getMrp1());
				ps.setDouble(5, dt.getEdp1());
			
				ps.setDouble(8, dt.getWsMargin1());
				ps.setDouble(9, dt.getRetMargin1());
				ps.setInt(10, dt.getSno());
				ps.setInt(11, action.getBrand_idd());
				ps.setInt(12, seqq);
				ps.setInt(13, this.getBoxSize(dt,Integer.parseInt(dt.getQuantity())));
				
				String code="";int sumcode=0;
				 
				////////////////////district code-2 digit////////////////////
				if(String.valueOf(action.getDistricid()).length()==2){
					code=String.valueOf(action.getDistricid());
				}else if(String.valueOf(action.getDistricid()).length()==1){
					code="0"+String.valueOf(action.getDistricid());
				}else{
					chk=false;code="0";
				}
				 
				
				
				////////////////////license type code-2 digit////////////////////
				
				  if(action.getLicense_type().equalsIgnoreCase("BWFL2A")){
					code=code+"04";sumcode=sumcode+4;
				} 
				else if(action.getLicense_type().equalsIgnoreCase("BWFL2B")){
					code=code+"05";sumcode=sumcode+4;
				} 
				else if(action.getLicense_type().equalsIgnoreCase("BWFL2C")){
					code=code+"06";sumcode=sumcode+4;
				} else if(action.getLicense_type().equalsIgnoreCase("BWFL2D")){
					code=code+"07";sumcode=sumcode+4;
				} 
				else{
					chk=false;code="0";
				}
				 
				
				//////////////////// unit code-2 digit////////////////////
				
				if(action.getBwfl_id_logged_in()>0){
								
				if(String.valueOf(action.getBwfl_id_logged_in()).length()==3){
					code=code+String.valueOf(action.getBwfl_id_logged_in());
				}else if(String.valueOf(action.getBwfl_id_logged_in()).length()==2){
					code=code+"0"+String.valueOf(action.getBwfl_id_logged_in());
				}else if(String.valueOf(action.getBwfl_id_logged_in()).length()==1){
					code=code+"00"+String.valueOf(action.getBwfl_id_logged_in());
				}else{
					chk=false;code="0";
				}
				 
				}else{
					chk=false;code="0";
				}
				////////////////////Liqour Type-2 digit////////////////////
				
				   if(action.getLicenseing().equalsIgnoreCase("IMFL")){
					ps.setDouble(6, dt.getDuty1());
					ps.setDouble(7, dt.getAddDuty1());
					//code=code+"1"; 
				}else if(action.getLicenseing().equalsIgnoreCase("IMPORTEDFL")){
					ps.setDouble(6, dt.getDuty1());
					ps.setDouble(7, dt.getAddDuty1());
					//code=code+"4"; 
				}else if(action.getLicenseing().equalsIgnoreCase("WINE")){
					ps.setDouble(6, dt.getDuty1());
					ps.setDouble(7, dt.getAddDuty1());
				//	code=code+"6"; 
				}else if(action.getLicenseing().equalsIgnoreCase("BEER")){
					ps.setDouble(6, dt.getDuty1());
					ps.setDouble(7, dt.getAddDuty1());
					//code=code+"2"; 
				}else if(action.getLicenseing().equalsIgnoreCase("IMPORTEDBEER")){
					ps.setDouble(6, dt.getDuty1());
					ps.setDouble(7, dt.getAddDuty1());
					//code=code+"5"; 
				}
				 
				else if(action.getLicenseing().equalsIgnoreCase("IMPORTEDLAB")){
					ps.setDouble(6, dt.getDuty1());
					ps.setDouble(7, dt.getAddDuty1());
					 
					//code=code+"8"; 
				}
				else if(action.getLicenseing().equalsIgnoreCase("IMPORTEDWINE")){
					ps.setDouble(6, dt.getDuty1());
					ps.setDouble(7, dt.getAddDuty1());
					//code=code+"9"; 
				}
				else if(action.getLicenseing().equalsIgnoreCase("LAB")){
					ps.setDouble(6, dt.getDuty1());
					ps.setDouble(7, dt.getAddDuty1());
				//	code=code+"7"; 
				}else{
					chk=false;code="0";
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
						}
						else {
							chk=false;code="0";
						}
						}else {
							chk=false;code="0";
						}
				////////////////////packagetype code-1 digit////////////////////
				
			if(dt.getPackage_type()!=null && dt.getPackage_type().length()>0){
			if(dt.getPackage_type().length()==1){
				code=code+dt.getPackage_type();
				sumcode=sumcode+Integer.parseInt(dt.getPackage_type());
			} 
			else {
				chk=false;code="0";
			}
			}else {
				chk=false;code="0";
			}
		
			String val1= String.valueOf(this.getOddSum(code));
			   val=code+ val1;	 				 			 
				ps.setString(14,  val);
				status = ps.executeUpdate();
				seqq=seqq+1;
				}
		
			}			
			if (status > 0 && val.length()==13) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Record Saved","Record Saved"));
				action.reset2();
				con.commit();
			}else if (status > 0 && val.length()<13) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("ETIN Error-"+val+"!","ETIN Error-"+val+"!"));
				 
				con.rollback();
			} else if (status > 0 && val.length()>13) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("ETIN Error-"+val+"!","ETIN Error-"+val+"!"));
				 
				con.rollback();
			}  else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Record Not Saved","Record Not Saved"));
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
	

	public int maxId(BWFLBrandRegistrationImpl brandImpl) {
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
		return maxid+1;
	}

	public int getId() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = " select max(package_id) as idd from distillery.packaging_details";
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
		return maxid+1;
	}

	public int getDisId(String year) {
		int disId = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String yr = year;
		try {
			conn = ConnectionToDataBase.getConnection();
			String query = "select int_distillary from licence.fl3_licence where int_year = ? ";
			ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(yr));
			rs = ps.executeQuery();
			if (rs.next()) {
				disId = rs.getInt("int_distillary");
			} else {
				disId = 0;
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
		return disId;
	}

	// ===================================
	public String getdist_name(BWFLBrandRegistrationAction action) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name = "";
		String query = " select vch_undertaking_name from public.dis_mst_pd1_pd2_lic where vch_wrk_phon="
				+ ResourceUtil.getUserNameAllReq().trim();
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
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					ps.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return name;
	}
	public int getdistidd() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id = 0;
		String query = " select int_app_id_f from public.dis_mst_pd1_pd2_lic where vch_wrk_phon="+ ResourceUtil.getUserNameAllReq().trim();
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
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					ps.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return id;
	}
	public String getdist_add(BWFLBrandRegistrationAction action) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String add = "";
		String query = " select vch_wrk_add from public.dis_mst_pd1_pd2_lic where vch_wrk_phon="
				+ ResourceUtil.getUserNameAllReq().trim();
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				add = rs.getString("vch_wrk_add");
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
					ps.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return add;
	}
	
	//===============================showing details of saved packaging==================================	
	
		public ArrayList getPackagingDetails(BWFLBrandRegistrationAction action){
			ArrayList list = null;
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;	
			
			String query = 		" SELECT quantity, package_name, package_type, mrp, edp, duty, adduty, wsmargin, " +
								" retmargin, brand_id_fk, package_id, sno, code_generate_through, box_id " +
								" FROM distillery.packaging_details WHERE brand_id_fk='"+action.getBrand_idd()+"' ";
			
			try {
				list = new ArrayList();
				conn = ConnectionToDataBase.getConnection();
				ps = conn.prepareStatement(query);
				rs = ps.executeQuery();
				
				int i = 1;
				while (rs.next()) {					
					BWFL_BrandDatatable dt = new BWFL_BrandDatatable();					
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
					i++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)conn.close();
					if (ps != null)ps.close();
					if (rs != null)rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return list;		
		}
		
		//=====================get quantity details=========================		
		
		public ArrayList getQntyDetails(BWFL_BrandDatatable brandDatatable){
			ArrayList list = new ArrayList();
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			SelectItem item = new SelectItem();
			item.setLabel("--Select--");
			item.setValue("0");
			list.add(item);
			
			String query = 	"SELECT qnt_ml_id, qnt_ml_detail FROM distillery.box_size_details";			
			String query2 = "SELECT qnt_ml_id, qnt_ml_detail FROM distillery.box_size_details where type='B'";
			
			try{
				con = ConnectionToDataBase.getConnection();
				
				FacesContext facesContext = FacesContext.getCurrentInstance();
				BWFLBrandRegistrationAction ba = (BWFLBrandRegistrationAction)facesContext.getApplication().createValueBinding("#{bWFLBrandRegistrationAction}").getValue(facesContext);				
				String value = ba.getLic_cat();			
				
				if(value.equals("BEER")){
					ps = con.prepareStatement(query2);
					rs = ps.executeQuery();
				}else{
					ps = con.prepareStatement(query);
					rs = ps.executeQuery();
				}		
				while(rs.next()){
					item = new SelectItem();
					item.setLabel(rs.getString("qnt_ml_detail"));
					item.setValue(rs.getInt("qnt_ml_detail"));
					list.add(item);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(rs!=null)rs.close();
					if(ps!=null)ps.close();
					if(con!=null)con.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			return list;		
		}
	 
		        public int Check(String ccNumber)
		        {
		                int sum = 0;
		                boolean alternate = false;
		                for (int i = ccNumber.length() - 1; i >= 0; i--)
		                {
		                        int n = Integer.parseInt(ccNumber.substring(i, i + 1));
		                        if (alternate)
		                        { 
		                                n *= 2;
		                                if (n > 9)
		                                {
		                                        n = (n % 10) + 1;
		                                }
		                        }
		                        sum += n; 
		                        alternate = !alternate;
		                } sum=  sum % 10;
		               
		                return sum;
		        }
		        public ArrayList liqTypeListImpl() {
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
		        
		        public ArrayList liqCatListImpl() {
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
		        public static int getOddSum(String n)
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
		    			   System.out.println("evennnn"+ar[i]);
		    			   odd =odd+ Character.getNumericValue(ar[i]);
		    		   }else{
		    			   System.out.println("oddddddd"+ar[i]);
		    			   even=even+Character.getNumericValue(ar[i]);
		    		   }
		    		  
		    		}
		    		
		    		sum=(odd*3)+even;
		    		System.out.println("summm"+sum);
		    		System.out.println("even"+even);
		    		System.out.println("odd sum"+odd);
		    		if(sum%10!=0)
		    		{
		    			
		    			System.out.println("SUMM    "+sum);
		    			    checkDigit= (10 - sum % 10);
		    			    System.out.println("checkDigit  "+checkDigit);
		    		}
		    		
		    		
		    	return checkDigit;
		        }
}
