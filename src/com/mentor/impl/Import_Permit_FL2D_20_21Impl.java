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
import com.mentor.action.Import_Permit_FL2D_20_21Action; 
import com.mentor.datatable.Import_Permit_FL2D_20_21DT;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class Import_Permit_FL2D_20_21Impl {
	
	public void getDetails(Import_Permit_FL2D_20_21Action act){


		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			
  String selQr =" select vch_licence_no  as vch_license_number,'99' as vch_license_type,vch_mobile_no as mobile_number,0 as stateid,  " +
				" null as vch_state_name,vch_firm_name, vch_core_address as vch_firm_add,core_district_id as vch_firm_district_id,description, " +
				" null as vch_indus_name,null as vch_reg_office_add,int_app_id as int_id, COALESCE (etin_unit_id,0) as unit_id, " +
				" null as  license_issue_date,null as approval_dt,null as application_date   " +
				" from licence.fl2_2b_2d_20_21 a, public.district b where a.core_district_id=b.districtid   " +
				" and vch_license_type='FL2D' and vch_mobile_no='"+ ResourceUtil.getUserNameAllReq().trim() + "' " ;
		
		
		
		    /////System.out.println("login dtl="+selQr);
			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();
			
			 
			while (rs.next()) {
				act.setInt_id(rs.getInt("int_id"));
				act.setUnit_id(rs.getInt("unit_id"));
				act.setVch_license_number(rs.getString("vch_license_number"));
				act.setVch_license_type(rs.getInt("vch_license_type"));
				act.setStateid(rs.getInt("stateid"));
				act.setState_name(rs.getString("vch_state_name"));
				act.setVch_firm_name(rs.getString("vch_firm_name"));
				act.setVch_firm_add(rs.getString("vch_firm_add"));
				act.setVch_firm_district_id(rs.getInt("vch_firm_district_id"));
				act.setVch_firm_district_name(rs.getString("description"));
				act.setVch_associated_unit_name(rs.getString("vch_indus_name"));
				act.setApplication_date(rs.getDate("application_date"));
				act.setVch_reg_office_add(rs.getString("vch_reg_office_add"));
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
	public ArrayList getBrandName() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		Import_Permit_FL2D_20_21Action act = (Import_Permit_FL2D_20_21Action) facesContext.getApplication()
				.createValueBinding("#{import_Permit_FL2D_20_21Action}")
				.getValue(facesContext);

		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);
		String sql = "";
		try {
				sql = /*"	SELECT brand_id, brand_name FROM distillery.brand_registration_20_21 "
						+ " where vch_license_type='IU'  and int_fl2d_id>0   " +
						" order by brand_name ";*/
						
						" SELECT brand_id, brand_name FROM distillery.brand_registration_20_21 where vch_license_type='IU'  and "+
						" int_fl2d_id in (SELECT brand_iu_id from custom_bonds.iu_mapping_with_iu where mapped_iu_id="+act.getImporter_unit_id()+"" +
					" union select "+act.getImporter_unit_id()+")  and int_fl2d_id >0  and (for_csd_civil in ('Civil','civil') or for_csd_civil is null) "+  
						" order by brand_name ";
		 
				/////System.out.println("brand query=="+sql);
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("brand_name"));
				item.setValue(rs.getString("brand_id"));
				list.add(item);
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
	public ArrayList getPackagingName(String brand_id) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);

		String SQl = "SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id "
				+ "	from distillery.brand_registration_20_21 a , "
				+ "	distillery.packaging_details_20_21 b "
				+ "	where a.brand_id=b.brand_id_fk  " +
			 	"	and brand_id =? ";
		/////System.out.println(brand_id+"pckg="+SQl);
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);
			// ps.setInt(1, this.getSugarmill_Id());
			ps.setInt(1, Integer.parseInt(brand_id));
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("package_name"));
				item.setValue(rs.getString("package_id"));
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
	public String getqty(String brand_Id, String packging_Id) {
		String qty = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList =

			" SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id "
					+ "	from distillery.brand_registration_20_21 a , "
					+ "	distillery.packaging_details_20_21 b "
					+ "	where a.brand_id=b.brand_id_fk  " +
					 "	and brand_id =?  and b.package_id=?";
            /////System.out.println(brand_Id+","+packging_Id+", "+"qty="+queryList);
			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, Integer.parseInt(brand_Id));
			pstmt.setInt(2, Integer.parseInt(packging_Id));

			rs = pstmt.executeQuery();

			while (rs.next()) {

				qty = rs.getString("quantity");

			}

			// pstmt.executeUpdate();
		} catch (Exception se) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));
			
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
		return qty;

	}
	public double getmrp(String brand_Id, String packging_Id){
		double mrp = 0.0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			String queryList =

			          " SELECT b.mrp "
					+ "	from distillery.brand_registration_20_21 a , "
					+ "	distillery.packaging_details_20_21 b "
					+ "	where a.brand_id=b.brand_id_fk  " +
					  "	and brand_id ='"+brand_Id+"'  and b.package_id='"+packging_Id+"'";
            /////System.out.println("mrp="+queryList);
			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			/*pstmt.setInt(1, Integer.parseInt(brand_Id));
			pstmt.setInt(2, Integer.parseInt(packging_Id));*/

			rs = pstmt.executeQuery();

			while (rs.next()) {

				mrp = rs.getDouble("mrp");

			}

			// pstmt.executeUpdate();
		} catch (Exception se) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));
			
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
		return mrp;
		
		
	}
	public String getEtinNmbr(String brand_Id, String packging_Id, Import_Permit_FL2D_20_21DT dt) {

		String etin = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList = " SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id, b.code_generate_through,b.duty,b.adduty "
					+", case when license_category in('IMFL','IMPORTED FL','WINE','IMPORTED WINE') then 20.0 else 10.0 end as spcl_fee "
					+ " FROM distillery.brand_registration_20_21 a , distillery.packaging_details_20_21 b "
					+ " WHERE a.brand_id=b.brand_id_fk  "
					+ " AND brand_id =?  AND b.package_id=?";
/////System.out.println(brand_Id+" | "+packging_Id+" | "+queryList);
			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, Integer.parseInt(brand_Id));
			pstmt.setInt(2, Integer.parseInt(packging_Id));

			rs = pstmt.executeQuery();

			if (rs.next()) {

				etin = rs.getString("code_generate_through");
				dt.setSpcl_fee(rs.getDouble("spcl_fee"));
				//dt.setDuty(rs.getDouble("duty"));
				//dt.setAdduty(rs.getDouble("adduty"));
			}
 
		} catch ( Exception se) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));
			
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
		return etin;

	}
	public double getPermitFee(String brand_Id, String packging_Id) {

		double permitFee = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList = 	" SELECT a.brand_id, a.brand_name, a.vch_license_type, b.package_name, b.package_id, b.permit " +
								" FROM distillery.brand_registration_20_21 a , distillery.packaging_details_20_21 b  " +
								" WHERE a.brand_id=b.brand_id_fk  " +
								" AND a.brand_id =?  AND b.package_id=? ";
			/////System.out.println("import fee="+queryList);
			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, Integer.parseInt(brand_Id));
			pstmt.setInt(2, Integer.parseInt(packging_Id));
			
			
		/////System.out.println("brand_Id------------"+brand_Id);
			
			/////System.out.println("packging_Id------------"+packging_Id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				permitFee = rs.getDouble("permit");

			}

		} catch ( Exception se) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));	
			
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
		return permitFee;

	}
	
	public int getCurrentstock(String brand_Id, String packging_Id, String Size) {

		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Import_Permit_FL2D_20_21Action act = (Import_Permit_FL2D_20_21Action) facesContext.getApplication()
				.createValueBinding("#{import_Permit_FL2D_20_21Action}").getValue(facesContext);
		
		
		int stock = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList = 	" SELECT (no_of_bottel::int-coalesce(dispatch_bottle,0)) as current_stock FROM custom_bonds.custom_bond_stock_2020 WHERE pack_id=? " +
					            " and brand_id=? and bott_size=? and int_id=(SELECT int_id from custom_bonds.mst_custom_bond_importing_unit_maping where int_import_id=?  and int_bond_id='"+act.getCustom_unit_id()+"')";
			/////System.out.println("stock fee="+queryList);
			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			
			pstmt.setInt(1, Integer.parseInt(packging_Id));
			pstmt.setInt(2, Integer.parseInt(brand_Id));
			pstmt.setInt(3, Integer.parseInt(Size));
			pstmt.setInt(4, act.getImporter_unit_id());
			
			
		/////System.out.println("brand_Id------------"+brand_Id+"===Size==="+Size+"----act.getImporter_unit_id()======"+act.getImporter_unit_id()+"===getCustom_unit_id==="+act.getCustom_unit_id());
			
			/////System.out.println("packging_Id------------"+packging_Id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				stock = rs.getInt("current_stock");

			}

		} catch ( Exception se) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));	
			
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
		return stock;

	}
	
	public int maxId(int district_id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(id) as id FROM bwfl_license.import_permit_20_21 where district_id='"+district_id+"' and login_type='FL2D' ";
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
	public boolean save(Import_Permit_FL2D_20_21Action act){


		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		int max_id = maxId(act.getVch_firm_district_id());
		int app_id=this.app_id();
		String query = ""; 
		double import_fee=0;
		double duty=0;
		double add_duty=0;
		double special_fee=0;
		double scanning_fee=0;
		boolean flg=false;
		 try {

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			 
			query="INSERT INTO bwfl_license.import_permit_dtl_20_21(fk_id, district_id, brand_id, pckg_id, etin, no_of_cases, no_of_bottle_per_case," +
					" pland_no_of_bottles, import_fee, duty, add_duty, special_fee,cr_date,login_type,app_id) VALUES (?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			if(act.getDisplaylist().size()>0){
				for(int i=0;i<act.getDisplaylist().size();i++){
					Import_Permit_FL2D_20_21DT dt = (Import_Permit_FL2D_20_21DT) act.getDisplaylist().get(i);
					
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, max_id);
					pstmt.setInt(2, act.getVch_firm_district_id());
					pstmt.setInt(3, Integer.parseInt(dt.getBrand_id()));
					pstmt.setInt(4, Integer.parseInt(dt.getPckg_id()));
					pstmt.setString(5, dt.getEtin());
					pstmt.setInt(6, dt.getNo_of_cases());
					pstmt.setLong(7, dt.getNo_of_bottle_per_case());
					pstmt.setInt(8, dt.getPland_no_of_bottles());
					pstmt.setDouble(9, dt.getCal_importFee());
					pstmt.setDouble(10, dt.getCalculated_duty());
					pstmt.setDouble(11, dt.getCalculated_add_duty());
					pstmt.setDouble(12, dt.getSpecial_fee());
					pstmt.setDate(13,Utility.convertUtilDateToSQLDate(new Date())); 
					pstmt.setString(14, "FL2D");
					pstmt.setInt(15, app_id);
					
					
					import_fee=import_fee+ dt.getCal_importFee();
					duty=duty+ dt.getCalculated_duty();       
					add_duty=add_duty+ dt.getCalculated_add_duty(); 
					special_fee=special_fee+dt.getSpecial_fee(); 
					scanning_fee += dt.getCal_scanfee(); 
					saveStatus = pstmt.executeUpdate();
				 	
				}
			}
		
			if(saveStatus>0){
				query = " INSERT INTO bwfl_license.import_permit_20_21( id, district_id, bwfl_id, unit_id, bwfl_type, import_fee," +
						" duty, add_duty, special_fee, cr_date,bottlng_type,route_road_radio,route_detail,lic_no, " +
						" login_type,spcl_fee_challan_no,import_fee_challan_no,app_id,consignor_nm_adrs,custom_id, int_import_id,challan_flg)" +
						" VALUES (?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?,'T')";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, max_id);
				pstmt.setInt(2, act.getVch_firm_district_id());
				pstmt.setInt(3, act.getInt_id());
				pstmt.setInt(4, act.getImporter_unit_id());
				pstmt.setInt(5, act.getVch_license_type());
				pstmt.setDouble(6, import_fee);
				pstmt.setDouble(7, duty);
				pstmt.setDouble(8, add_duty);
				pstmt.setDouble(9, special_fee);
				pstmt.setDate(10,Utility.convertUtilDateToSQLDate(new Date()));
				pstmt.setString(11, act.getBottlngType()); 
				pstmt.setString(12, act.getRoute_road_radio());
				pstmt.setString(13, act.getRoute_detail());
				pstmt.setString(14, act.getVch_license_number());
				pstmt.setString(15, "FL2D");
				pstmt.setString(16, "spcl_fee_challan_Submited" );
				pstmt.setString(17, "import_fee_challan_Submited" );
				pstmt.setInt(18, app_id);
				pstmt.setString(19, act.getConsignor_nm_adrs());
				pstmt.setInt(20, Integer.parseInt(act.getCustom_unit_id()));
				pstmt.setInt(21, act.getImporter_unit_id());
				
				saveStatus = pstmt.executeUpdate();
				
			}
		
			if (saveStatus > 0) {
				conn.setAutoCommit(true);
				flg=true;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Your Request id is "+app_id));
				//ResourceUtil.addErrorMessage(Constants.SAVED_SUCESSFULLY,Constants.SAVED_SUCESSFULLY);
				act.reset();

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
	public ArrayList hislistImpl(Import_Permit_FL2D_20_21Action act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		//int i = 1, cases = 0;
	 
		try {
		 /*selQr =" select consignor_nm_adrs,COALESCE(print_permit_pdf,'NUL')print_permit_pdf,COALESCE(digital_sign_pdf_name,'NUL')digital_sign_pdf_name," +
		 		" app_id,import_fee_challan_no,spcl_fee_challan_no,id,district_id,bwfl_id,unit_id,bwfl_type,import_fee,duty+add_duty as duty," +
		 		" special_fee,cr_date," +
		 		" case when bottlng_type='M' then 'Mapped' else 'Unmapped' end as bottlng_type" +
		 		",COALESCE(vch_status,'Pending')as vch_status, vch_approved,valid_upto from bwfl_license.import_permit_20_21 where district_id='"
				 +act.getVch_firm_district_id()+"' and bwfl_id='"+act.getInt_id()+"' and login_type='FL2D' order by id desc";*/
		 
		selQr=	"  select  x.*,                                                                                                                 "+
				" case when x.cr_date>'05-06-2020' then y.spcl_fee_challan_no1  else x.spcl_fee_challan_no end as spcl_fee_challan_no2,         "+
				" case when x.cr_date>'05-06-2020' then import_fee_challan_no1 else x.import_fee_challan_no end as import_fee_challan_no2 from  "+
				" (select app_id,consignor_nm_adrs,COALESCE(print_permit_pdf,'NUL')print_permit_pdf,                                            "+
				" COALESCE(digital_sign_pdf_name,'NUL')digital_sign_pdf_name,                                                                   "+
				" import_fee_challan_no,                                                                                                        "+
				" spcl_fee_challan_no,id,district_id,bwfl_id,unit_id,bwfl_type,import_fee,duty+add_duty as duty,                                "+
				" special_fee,cr_date, case when bottlng_type='M' then 'Mapped' else 'Unmapped' end as bottlng_type,                            "+
				" COALESCE(vch_status,'Pending')as vch_status, vch_approved,valid_upto from bwfl_license.import_permit_20_21                    "+
				" where district_id='"+act.getVch_firm_district_id()+"' and bwfl_id='"+act.getInt_id()+"' and login_type='FL2D')x                                                              "+
				" left outer join                                                                                                               "+
				" (select a.app_id,string_agg(distinct b.spcl_fee_challan_no, ', ') as spcl_fee_challan_no1 ,                                   "+
				" string_agg(distinct a.import_fee_challan_no, ', ') as import_fee_challan_no1 from fl2d.fl2d_bwfl_importfee_challan_detail a,  "+
				" fl2d.fl2d_bwfl_special_challan_detail  b where a.app_id=b.app_id group by  a.app_id)y on y.app_id=x.app_id  order by id desc";
		 
		 /////System.out.println("query--- "+selQr);
		 conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(selQr);
	 
			rs = ps.executeQuery();
			while (rs.next()) {
				Import_Permit_FL2D_20_21DT dt = new Import_Permit_FL2D_20_21DT();
				dt.setConsignor_nm_adrs(rs.getString("consignor_nm_adrs"));
				dt.setApp_id(rs.getInt("app_id"));
				dt.setDt_app_id(rs.getInt("id"));
				dt.setDt_import_fee(rs.getDouble("import_fee"));
				dt.setDt_duty(rs.getDouble("duty"));
				//dt.setDt_add_duty(rs.getDouble("add_duty"));
				dt.setDt_special_fee(rs.getDouble("special_fee"));
				dt.setDt_bottlng_type(rs.getString("bottlng_type"));
				/////System.out.println("p-"+rs.getString("print_permit_pdf")+"-A-"+rs.getString("digital_sign_pdf_name")+"-*");
				if(!rs.getString("print_permit_pdf").equalsIgnoreCase("NUL") && rs.getString("digital_sign_pdf_name").equalsIgnoreCase("NUL")){
					dt.setDt_vch_status(rs.getString("vch_status")+" and Pending for Digital Sign");
				}else {
					dt.setDt_vch_status(rs.getString("vch_status"));
				}
				dt.setDigital_sign_pdf_name(rs.getString("digital_sign_pdf_name"));
				//dt.setDt_vch_status(rs.getString("vch_status"));
				dt.setDt_vch_approved(rs.getString("vch_approved"));
				dt.setDt_district_id(rs.getInt("district_id"));
				dt.setValidUpto(rs.getDate("valid_upto"));
				dt.setImport_fee_challan_no(rs.getString("import_fee_challan_no2"));
				dt.setSpcl_fee_challan_no(rs.getString("spcl_fee_challan_no2"));
				dt.setApp_date(rs.getDate("cr_date"));
				
				//cases++;
				list.add(dt);
				//i++;
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
	 
	public ArrayList viewdetailImpl(Import_Permit_FL2D_20_21Action act, int app_id, int district_id){
		 ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		//int i = 1, cases = 0;
	 
		try {
			selQr="select a.brand_id,b.brand_name,a.pckg_id,c.package_name,a.etin,a.no_of_cases,a.no_of_bottle_per_case, "+
					"a.pland_no_of_bottles,a.import_fee, a.duty, a.add_duty,a.special_fee "+
					" FROM bwfl_license.import_permit_dtl_20_21 a,distillery.brand_registration_20_21 b,distillery.packaging_details_20_21 c" +
					" where b.brand_id=c.brand_id_fk and b.brand_id =a.brand_id  AND c.package_id=a.pckg_id and a.app_id='"+app_id+"' and district_id='"+district_id+"' and login_type='FL2D'" ;
					
			/////System.out.println("query--- "+selQr);
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(selQr);
	 
			rs = ps.executeQuery();
			while (rs.next()) {
				Import_Permit_FL2D_20_21DT dt = new Import_Permit_FL2D_20_21DT();
				
				dt.setBrand_name(rs.getString("brand_name"));
				dt.setPackageName(rs.getString("package_name"));
				dt.setEtin(rs.getString("etin"));
				dt.setNo_of_cases(rs.getInt("no_of_cases"));
				dt.setNo_of_bottle_per_case(rs.getInt("no_of_bottle_per_case"));
				dt.setPland_no_of_bottles(rs.getInt("pland_no_of_bottles"));
				dt.setImportFee(rs.getDouble("import_fee"));
				dt.setDuty(rs.getDouble("duty"));
				dt.setAdduty(rs.getDouble("add_duty"));
		        dt.setDt_special_fee(rs.getDouble("special_fee"));
				dt.setDt_duty(rs.getDouble("duty"));
				dt.setDt_add_duty(rs.getDouble("add_duty"));
				list.add(dt);
				//i++;
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
	public void getBalance(Import_Permit_FL2D_20_21Action act){

 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s = "";
		try {
			
			con = ConnectionToDataBase.getConnection();
			String selQr="";
			selQr =" SELECT x.bwfl_id,                                      " +
					" SUM(x.bwfl_import_fee-(x.ap_import_fee+x.import_fee))as bwfl_import_fee_bal,                                " +
					" SUM(x.bwfl_special_fee-(x.ap_special_fee+x.special_fee))as bwfl_special_fee_bal, " +
					" SUM(x.fl2d_import_fee-(x.ap_import_fee+x.import_fee))as fl2d_import_fee_bal,                                " +
					" SUM(x.fl2d_special_fee-(x.ap_special_fee+x.special_fee))as fl2d_special_fee_bal " +
					" FROM " +
					" (SELECT a.int_distillery_id as bwfl_id, SUM(b.double_amt) as bwfl_import_fee, 0 as bwfl_special_fee, " +
					" 0 as fl2d_import_fee, 0 as fl2d_special_fee, " +
					" 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee " +
					" FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b " +
					" WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type AND a.int_distillery_id='"+act.getInt_id()+"'   " +
					" AND b.vch_head_code in ('003900103030000','003900105040000','003900103030000') and b.vch_g6_head_code in ('01','03') " +
					" AND a.date_challan_date > '2019-06-16' " +
					" group by bwfl_id " +
					" UNION " +
					" SELECT a.int_distillery_id as bwfl_id, 0 as bwfl_import_fee, SUM(b.double_amt) as bwfl_special_fee, " +
					" 0 as fl2d_import_fee, 0 as fl2d_special_fee, " +
					" 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee " +
					" FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b " +
					" WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type AND a.int_distillery_id='"+act.getInt_id()+"'   " +
					" AND b.vch_head_code in ('003900105020000','003900103020000') and b.vch_g6_head_code in ('18','13')  " +
					" AND a.date_challan_date > '2019-06-16' " +
					" GROUP BY bwfl_id " +
					" UNION " +
					" SELECT a.int_distillery_id as bwfl_id, 0 as bwfl_import_fee, 0 as bwfl_special_fee, " +
					" SUM(b.double_amt) as fl2d_import_fee, 0 as fl2d_special_fee, " +
					" 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee " +
					" FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b " +
					" WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type AND a.int_distillery_id='"+act.getInt_id()+"' " +
					" AND b.vch_head_code in ('003900105040000','003900103030000','003900103030000') and b.vch_g6_head_code in ('04','05') " +
					" AND a.date_challan_date > '2019-06-16' " +
					" GROUP BY bwfl_id " +
					" UNION " +
					" SELECT a.int_distillery_id as bwfl_id, 0 as bwfl_import_fee, 0 as bwfl_special_fee, " +
					" 0 as fl2d_import_fee, SUM(b.double_amt) as fl2d_special_fee, " +
					" 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee " +
					" FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b " +
					" WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type AND a.int_distillery_id='"+act.getInt_id()+"'  " +
					" AND b.vch_head_code in ('003900105020000','003900103020000') and b.vch_g6_head_code in ('18','13') " +
					" AND a.date_challan_date > '2019-06-16' " +
					" GROUP BY bwfl_id " +
					" UNION                                                                                                      " +
					" SELECT a.bwfl_id, 0 as bwfl_import_fee, 0 as bwfl_special_fee,0 as fl2d_import_fee, 0 as fl2d_special_fee,  " +
					" SUM(a.import_fee) as ap_import_fee, SUM(COALESCE(a.special_fee,0)) as ap_special_fee,    " +
					" 0 as import_fee, 0 as special_fee " +
					" FROM bwfl_license.import_permit_20_21 a                                                                          " +
					" WHERE a.bwfl_id='"+act.getInt_id()+"' AND a.vch_approved='APPROVED'  and   login_type='FL2D'                                       " +
					" GROUP BY a.bwfl_id " +
					" )x GROUP BY x.bwfl_id";
			/////System.out.println("balance ="+selQr);
			pstmt = con.prepareStatement(selQr);
			rs = pstmt.executeQuery();
			 	while (rs.next()) {
				//act.setDuty_balance(rs.getDouble("bal_duty"));
				act.setImportFee_balance(rs.getDouble("fl2d_import_fee_bal"));
				act.setSpecialFee_balance(rs.getDouble("fl2d_special_fee_bal"));	
			}

		} catch ( Exception se) {
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
	
	public int delete(Import_Permit_FL2D_20_21Action action, int id, int dist, Date app_date, String challan) throws ParseException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int saveStatus = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		//Date appdate = formatter.format(app_date);
		Date appdate1 = formatter.parse("2020-06-05");
		String sql="";
		
		/////System.out.println("appdate==="+app_date);
		sql="delete from bwfl_license.import_permit_20_21  where app_id='"+id+"' and district_id='"+dist+"' and login_type='FL2D'";

		try {

			conn = ConnectionToDataBase.getConnection(); 
			conn.setAutoCommit(false);
		 	/////System.out.println(saveStatus+"="+sql);
			pstmt = conn.prepareStatement(sql);
			saveStatus = pstmt.executeUpdate();
			if(saveStatus>0){
				saveStatus=0;
				sql="delete from bwfl_license.import_permit_dtl_20_21 where app_id='"+id+"' and district_id='"+dist+"' and login_type='FL2D'";
				pstmt = conn.prepareStatement(sql);
				saveStatus = pstmt.executeUpdate();
			}
			
			if(app_date.after(appdate1) && challan!=null){
				if(saveStatus>0){
					saveStatus = 0;
					String update1="DELETE FROM fl2d.fl2d_bwfl_importfee_challan_detail where app_id="+id+"";
					pstmt = conn.prepareStatement(update1);
					saveStatus = pstmt.executeUpdate();
					/////System.out.println("status2------only----- "+saveStatus);
				}
				
				if(saveStatus>0){
					saveStatus = 0;
					String update2="DELETE FROM fl2d.fl2d_bwfl_special_challan_detail where app_id="+id+"";
					pstmt = conn.prepareStatement(update2);
					saveStatus = pstmt.executeUpdate();
					/////System.out.println("status3------only----- "+saveStatus);
				}
					
				}
			
			if (saveStatus > 0) {
				conn.commit();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Permit Deleted", "Permit Deleted"));
				action.setHislist(hislistImpl(action));
				action.setFlag("F");
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Error", "Error"));
				conn.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
		}
		finally {
			try {

				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return saveStatus;
	}
	public ArrayList getOtherUnitList() {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue(0);
		list.add(item);

	 String sql=//"select distinct b.vch_firm_name,  mapped_iu_id from custom_bonds.iu_mapping_with_iu a, custom_bonds.mst_regis_importing_unit b where a.mapped_iu_id=b.int_id";
			 
			 "SELECT int_id as int_app_id_f ,coalesce(vch_firm_name,'')vch_firm_name, coalesce(vch_applicant_name,'')vch_indus_name," +
			 "coalesce(vch_mobile, 'NA')vch_wrk_phon FROM custom_bonds.mst_regis_importing_unit where vch_username is not null and unit_for='CIVIL' " +
			 "order by vch_firm_name";
			 
 		try {
 			/////System.out.println("importer list="+sql);
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("vch_firm_name"));
				item.setValue(rs.getInt("int_app_id_f"));
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
	public void ChallanList(Import_Permit_FL2D_20_21Action act) {
		/////System.out.println("-/-");
		ArrayList ImportChallanList = new ArrayList();
		ArrayList SpclChallanList = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SelectItem ImportChallan = new SelectItem();
		ImportChallan.setLabel("--SELECT--");
		ImportChallan.setValue("");
		ImportChallanList.add(ImportChallan);

		SelectItem SpclChallan = new SelectItem();
		SpclChallan.setLabel("--SELECT--");
		SpclChallan.setValue("");
		SpclChallanList.add(SpclChallan); 
		try {
			
			String selQr="";
			con = ConnectionToDataBase.getConnection();
			selQr =" select chalan_no,chalan_date,amount from bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no in  ( SELECT DISTINCT "+
					" CASE WHEN head_type like 'FL2D%' then chalan_no else '' end as import_fee_challan_no  " +
			" FROM bwfl_license.chalan_deposit_bwfl_fl2d   WHERE unit_id='"+act.getInt_id()+"' AND unit_type='FL2D' " +
			" EXCEPT   " +
			" select DISTINCT import_fee_challan_no from bwfl_license.import_permit_20_21    " +
			" where bwfl_id='"+act.getInt_id()+"' and bwfl_type=99 and district_id='"+act.getVch_firm_district_id()+"' ) and  unit_id="+act.getInt_id()+" AND unit_type='FL2D'";
		
			/////System.out.println("ChallanList=" + selQr);
			pstmt = con.prepareStatement(selQr);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				///////System.out.println("01="+rs.getString("chalan_no"));
				if(!rs.getString("chalan_no").trim().equalsIgnoreCase("")){
					
					ImportChallan = new SelectItem();
					ImportChallan.setLabel(rs.getString("chalan_no")+" (Rs."+rs.getString("amount")+") - "+rs.getString("chalan_date"));
					ImportChallan.setValue(rs.getString("chalan_no"));
					ImportChallanList.add(ImportChallan);
				}	
			}
			act.setImportChallanList(ImportChallanList);
			selQr = " select chalan_no,chalan_date,amount from bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no in  ( SELECT DISTINCT "+
					" CASE WHEN head_type like 'Special%' then chalan_no else '' end as spcl_fee_challan_no  "+
			" FROM bwfl_license.chalan_deposit_bwfl_fl2d   WHERE unit_id='"+act.getInt_id()+"' AND unit_type='FL2D'  EXCEPT "+
			" select spcl_fee_challan_no from bwfl_license.import_permit_20_21 where bwfl_id='"+act.getInt_id()+"' and bwfl_type=99 and district_id='"+act.getVch_firm_district_id()+"' ) and  unit_id="+act.getInt_id()+" AND unit_type='FL2D'";

			
			/////System.out.println("ChallanList1=" + selQr);
			 pstmt=null;
			 rs=null;
				pstmt = con.prepareStatement(selQr);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					///////System.out.println("01="+rs.getString("chalan_no"));
					if(!rs.getString("chalan_no").trim().equalsIgnoreCase("")){
						///////System.out.println("2");
						SpclChallan = new SelectItem();
						SpclChallan.setLabel(rs.getString("chalan_no")+" (Rs."+rs.getString("amount")+") - "+rs.getString("chalan_date"));
						SpclChallan.setValue(rs.getString("chalan_no"));
						SpclChallanList.add(SpclChallan);
					}
					
				}
			act.setSpclChallanList(SpclChallanList);

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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
	public boolean getChallanBalance(Import_Permit_FL2D_20_21Action act) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flg=true;
		String sql="";
		sql = " select COALESCE(amount,0.0)import_amount " +
			" FROM bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no='"+act.getImport_fee_challan_no()+"' " 
			+ " and unit_id="+act.getInt_id()+" AND unit_type='FL2D'";
/////System.out.println("getChallanImportFee--"+sql);
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				 if(Double.parseDouble(act.getTotal_import_fees())>rs.getDouble("import_amount")){
					 flg=false; 
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Import Fee for this permit is greater then your selected Challan"));
				 }else {
					 /////System.out.println(Double.parseDouble(act.getTotal_import_fees())+"-import fee is "+rs.getDouble("import_amount"));
				 }
			}
			ps=null;
			rs=null;
			sql = " select COALESCE(amount,0.0)spcl_amount " +
					" FROM bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no='"+act.getSpcl_fee_challan_no()+"' " 
					+ " and unit_id="+act.getInt_id()+" AND unit_type='FL2D'";
			/////System.out.println("ChallanSpcl1="+sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				 if(Double.parseDouble(act.getTotal_special_fee())>rs.getDouble("spcl_amount")){
					 flg=false; 
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Special Fee for this permit is greater then your selected Challan"));
				 }else {
					 /////System.out.println(Double.parseDouble(act.getTotal_special_fee())+"-import fee is "+rs.getDouble("spcl_amount"));
				 }
			}
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), e.getMessage()));
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
		return flg;
	}
	public int app_id() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(app_id) as app_id FROM bwfl_license.import_permit_20_21";
		int maxid = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxid = rs.getInt("app_id");
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), e.getMessage()));

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
	
	
///==================================
	public ArrayList getcustomlist(int id) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue(0);
		list.add(item);

	 String sql="SELECT a.int_id,b.vch_firm_name, a.int_bond_id, a.int_import_id,b.vch_applicant_name,b.vch_bond_address FROM custom_bonds.mst_custom_bond_importing_unit_maping a,custom_bonds.custom_bonds_master b where a.int_bond_id=b.int_id and a.int_import_id="+id+"";
			 //"select int_app_id_f,coalesce(vch_indus_name,'')vch_indus_name,coalesce(vch_wrk_phon,0)vch_wrk_phon from public.other_unit_registration where vch_indus_type='IU'";
 		try {
 			/////System.out.println("importer list="+sql);
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("vch_firm_name")+"-"+rs.getString("vch_bond_address"));
				item.setValue(rs.getString("int_bond_id"));
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
	
	
	public double getst_total_advance_import_fees(Import_Permit_FL2D_20_21Action action) {
		double db_advance_import_fees =0.0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList =" SELECT  (sum(x.challan_amt) -sum(x.disptch_duty)) as fl2d_import_fees                                " +
			           " FROM  (select '2020-04-01' as date_challan_date,coalesce(sum(import_amount),0)  as challan_amt, 'Current Year opening' as description , " +
			           " 'OP' as check_type, 0 as disptch_duty from fl2d.opening_fees_for_fl2d_bwfl_20_21 where unit_id='"+action.getInt_id()+"' and unit_type='FL2D' " +
			           " union  " +
			           " SELECT  b.cr_date, 0 as challan_amt, 'Import Fee on Permit ' || b.permit_nmbr as description, 'DT' as check_type,             " +
			           " SUM (b.import_fee) as disptch_duty FROM bwfl_license.import_permit_20_21 b WHERE b.bwfl_id='"+action.getInt_id()+"' and login_type='FL2D'   AND b.cr_date between '2020-04-01' AND '"+Utility.convertUtilDateToSQLDate(new Date())+"' " +
			           " and vch_approved='APPROVED'	GROUP BY  b.cr_date, description, check_type                            " +
			           " Union   " +
			           " SELECT  b.cr_date, sum(amount) as  challan_amt, 'Deposit Challan  - ' || b.chalan_no as description, " +
			           " 'CH' as check_type, 0 as disptch_duty FROM bwfl_license.chalan_deposit_bwfl_fl2d b WHERE b.unit_id='"+action.getInt_id()+"' " +
			           " and unit_type='FL2D' and head_code in ('003900103030000','003900105040000') and g6_code in ('04','05') " +
			           " AND b.cr_date between '2020-04-01' AND '"+Utility.convertUtilDateToSQLDate(new Date())+"' GROUP BY  b.cr_date, description, check_type         " +
			           " UNION   " +
			           " SELECT p.cr_date, 0 as challan_amt, CONCAT('Penality Deduction for Penality No.- ', p.deduction_code) as description,          " +
			           " 'DT' as check_type, SUM(p.db_amount) as disptch_duty    FROM licence.mst_penalty_deduction p                                                      " +
			           "  WHERE   p.duty_type='FL2D_Permit' AND p.unit_id='"+action.getInt_id()+"'                      AND p.unit_type='FL2D' " +
			           " AND p.cr_date BETWEEN '2020-04-01'                  AND '"+Utility.convertUtilDateToSQLDate(new Date())+"'                              " +
			           " GROUP BY p.cr_date, description, check_type) x   ";
					
					/*" SELECT  (sum(x.challan_amt) -sum(x.disptch_duty)) as fl2d_import_fees                                " +
					           " FROM  (select '2020-04-01' as date_challan_date,case  when 'FL2D_Permit' in ('BWFL2B_SOCIAL_DUTY','BWFL2A_SOCIAL_DUTY','FL2D_SOCIAL_DUTY')       " +
					           " then coalesce(sum(add_consideration_amount),0) when 'FL2D_Permit' in ('Special%') then coalesce(sum(cowcesh_amt),0) " +
					           " when 'FL2D_Permit' in ('FL2D_Permit') then coalesce(sum(duty),0) when 'FL2D_Permit' in " +
					           " ('scanning_fee_cr_FL2D_ALL','scanning_fee_cr_BWFL_ALL')    then          " +
					           " coalesce(sum(scan_amount),0) end as challan_amt, 'Current Year opening' as description , " +
					           " 'OP' as check_type, 0 as disptch_duty from fl2d.opening_fees_for_fl2d_bwfl_20_21 where unit_id='"+action.getInt_id()+"' and unit_type='FL2D' " +
					           " union  " +
					           " SELECT  b.cr_date, 0 as challan_amt, 'Import Fee on Permit ' || b.permit_nmbr as description, 'DT' as check_type,             " +
					           " SUM (b.import_fee) as disptch_duty FROM bwfl_license.import_permit_20_21 b WHERE b.bwfl_id='"+action.getInt_id()+"' and login_type='FL2D'   AND b.cr_date between '2020-04-01' AND '"+Utility.convertUtilDateToSQLDate(new Date())+"' " +
					           " and vch_approved='APPROVED'	GROUP BY  b.cr_date, description, check_type                            " +
					           " Union   " +
					           " SELECT  b.cr_date, sum(amount) as  challan_amt, 'Deposit Challan  - ' || b.chalan_no as description, " +
					           " 'CH' as check_type, 0 as disptch_duty FROM bwfl_license.chalan_deposit_bwfl_fl2d b WHERE b.unit_id='"+action.getInt_id()+"' " +
					           " and unit_type='FL2D' and head_code in ('003900103030000','003900105040000') and g6_code in ('04','05') " +
					           " AND b.cr_date between '2020-04-01' AND '"+Utility.convertUtilDateToSQLDate(new Date())+"' GROUP BY  b.cr_date, description, check_type         " +
					           " UNION   " +
					           " SELECT p.cr_date, 0 as challan_amt, CONCAT('Penality Deduction for Penality No.- ', p.deduction_code) as description,          " +
					           " 'DT' as check_type, SUM(p.db_amount) as disptch_duty    FROM licence.mst_penalty_deduction p                                                      " +
					           "  WHERE   p.duty_type='FL2D_Permit' AND p.unit_id='"+action.getInt_id()+"'                      AND p.unit_type='FL2D' " +
					           " AND p.cr_date BETWEEN '2020-04-01'                  AND '"+Utility.convertUtilDateToSQLDate(new Date())+"'                              " +
					           " GROUP BY p.cr_date, description, check_type) x   ";*/
			
			
            System.out.println(  "====db_advance_import_fees==rrrr==="+queryList);
			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			 

			rs = pstmt.executeQuery();

			while (rs.next()) {

				db_advance_import_fees = rs.getDouble("fl2d_import_fees");

			}

			// pstmt.executeUpdate();
		} catch (Exception se) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));
			
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
		return db_advance_import_fees;

	}	
	public double getst_total_advance_special_fees(Import_Permit_FL2D_20_21Action action) {
		double db_advance_special_fees =0.0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList =" SELECT (sum(x.challan_amt) -sum(x.disptch_duty)) as advance_special_fees_disptch_duty FROM  	                                                                                                " +
			           " (select '2020-04-01' date_challan_date, coalesce(sum(cowcesh_amt),0)  as challan_amt, 'Current Year opening' as description ,      " +
			           " 'OP' as check_type, 0 as disptch_duty from fl2d.opening_fees_for_fl2d_bwfl_20_21 where unit_id='"+action.getInt_id()+"' and unit_type='FL2D'    " +
			           " union  " +
			           " SELECT  b.cr_date, 0 as challan_amt, 'Special Fee on Permit ' || b.permit_nmbr as description, 'DT' as check_type,    " +
			           " SUM(b.special_fee) as disptch_duty FROM bwfl_license.import_permit_20_21 b WHERE b.bwfl_id='"+action.getInt_id()+"' and login_type='FL2D' " +
			           " AND b.cr_date between '2020-04-01'  AND '"+Utility.convertUtilDateToSQLDate(new Date())+"' and vch_approved='APPROVED'	                                                " +
			           " GROUP BY  b.cr_date, description, check_type    " +
			           " UNION " +
			           " SELECT  b.cr_date, sum(amount) as challan_amt, 'Deposit Challan  - ' || b.chalan_no as description, 'CH' as check_type, " +
			           " 0 as disptch_duty FROM bwfl_license.chalan_deposit_bwfl_fl2d b WHERE b.unit_id='"+action.getInt_id()+"' and unit_type='FL2D'            " +
			           " and head_code in ('003900103020000','003900105020000') and g6_code in ('15','22') AND b.cr_date between '2020-04-01' " +
			           " AND '"+Utility.convertUtilDateToSQLDate(new Date())+"' 	                                  " +
			           " GROUP BY  b.cr_date, description, check_type       " +
			           " UNION   SELECT p.cr_date, 0 as challan_amt, CONCAT('Penality Deduction for Penality No.- ', p.deduction_code) as description,              " +
			           " 'DT' as check_type, SUM(p.db_amount) as disptch_duty  FROM licence.mst_penalty_deduction p                                 " +
			           " WHERE   p.duty_type='Special_fee_fl2D_permit_Beer' AND p.unit_id='"+action.getInt_id()+"' AND p.unit_type='FL2D'    " +
			           " AND p.cr_date BETWEEN '2020-04-01'   AND '"+Utility.convertUtilDateToSQLDate(new Date())+"' " +
			           " GROUP BY p.cr_date, description, check_type) x   ";
					
					/*" SELECT (sum(x.challan_amt) -sum(x.disptch_duty)) as advance_special_fees_disptch_duty FROM  	                                                                                                " +
					           " (select '2020-04-01' as date_challan_date,case  when 'Special_fee_fl2D_permit_Beer' in " +
					           " ('BWFL2B_SOCIAL_DUTY','BWFL2A_SOCIAL_DUTY','FL2D_SOCIAL_DUTY')        " +
					           "  then coalesce(sum(add_consideration_amount),0) when 'Special_fee_fl2D_permit_Beer' in " +
					           " ('Special%') then coalesce(sum(cowcesh_amt),0) when 'Special_fee_fl2D_permit_Beer' in ('FL2D_Permit') " +
					           " then coalesce(sum(duty),0) when 'Special_fee_fl2D_permit_Beer' in ('scanning_fee_cr_FL2D_ALL','scanning_fee_cr_BWFL_ALL')   " +
					           " then  coalesce(sum(scan_amount),0) end as challan_amt, 'Current Year opening' as description ,      " +
					           " 'OP' as check_type, 0 as disptch_duty from fl2d.opening_fees_for_fl2d_bwfl_20_21 where unit_id='"+action.getInt_id()+"' and unit_type='FL2D'    " +
					           " union  " +
					           " SELECT  b.cr_date, 0 as challan_amt, 'Special Fee on Permit ' || b.permit_nmbr as description, 'DT' as check_type,    " +
					           " SUM(b.special_fee) as disptch_duty FROM bwfl_license.import_permit_20_21 b WHERE b.bwfl_id='"+action.getInt_id()+"' and login_type='FL2D' " +
					           " AND b.cr_date between '2020-04-01'  AND '"+Utility.convertUtilDateToSQLDate(new Date())+"' and vch_approved='APPROVED'	                                                " +
					           " GROUP BY  b.cr_date, description, check_type    " +
					           " UNION " +
					           " SELECT  b.cr_date, sum(amount) as challan_amt, 'Deposit Challan  - ' || b.chalan_no as description, 'CH' as check_type, " +
					           " 0 as disptch_duty FROM bwfl_license.chalan_deposit_bwfl_fl2d b WHERE b.unit_id='"+action.getInt_id()+"' and unit_type='FL2D'            " +
					           " and head_code in ('003900103020000','003900105020000') and g6_code in ('15','22') AND b.cr_date between '2020-04-01' " +
					           " AND '"+Utility.convertUtilDateToSQLDate(new Date())+"' 	                                  " +
					           " GROUP BY  b.cr_date, description, check_type       " +
					           " UNION   SELECT p.cr_date, 0 as challan_amt, CONCAT('Penality Deduction for Penality No.- ', p.deduction_code) as description,              " +
					           " 'DT' as check_type, SUM(p.db_amount) as disptch_duty  FROM licence.mst_penalty_deduction p                                 " +
					           " WHERE   p.duty_type='Special_fee_fl2D_permit_Beer' AND p.unit_id='"+action.getInt_id()+"' AND p.unit_type='FL2D'    " +
					           " AND p.cr_date BETWEEN '2020-04-01'   AND '"+Utility.convertUtilDateToSQLDate(new Date())+"' " +
					           " GROUP BY p.cr_date, description, check_type) x   ";*/
 
			
			
			
            System.out.println( "===getst_total_advance_special_fees===rrr=="+queryList);
			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			 

			rs = pstmt.executeQuery();

			while (rs.next()) {

				db_advance_special_fees = rs.getDouble("advance_special_fees_disptch_duty");

			}

			// pstmt.executeUpdate();
		} catch (Exception se) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));
			
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
		return db_advance_special_fees;

	}	
	
	
	
	
	
	
	
	
	
	
}
