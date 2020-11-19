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

import com.mentor.action.ImportPermitAction_20_21;
import com.mentor.datatable.ImportPermitDT_20_21;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class ImportPermitImpl_20_21 {
	public void getDetails(ImportPermitAction_20_21 act) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			String selQr = "select vch_license_number,vch_license_type,mobile_number,stateid,b.vch_state_name,vch_firm_name, "
					+ " vch_firm_add,vch_firm_district_id::numeric,c.description,d.vch_indus_name,d.vch_reg_office_add,int_id,a.unit_id, "
					+ " license_issue_date,approval_dt,application_date from bwfl.registration_of_bwfl_lic_holder_20_21 a,public.state_ind b,public.district c,public.other_unit_registration_20_21 d "
					+ " WHERE a.vch_firm_district_id::numeric=c.districtid and d.vch_reg_office_state::int=b.int_state_id "
					+ " and a.unit_id=d.int_app_id_f and  d.vch_indus_type in ('OUPD','OUPB','OUPBU','OUPW')  and  mobile_number='"
					+ ResourceUtil.getUserNameAllReq().trim()
					+ "' AND vch_approval='V' ";

			 
			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				act.setInt_id(rs.getInt("int_id"));
				// System.out.println("typelogin="+rs.getInt("vch_license_type")+"+");

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
		ImportPermitAction_20_21 act = (ImportPermitAction_20_21) facesContext
				.getApplication().createValueBinding("#{importPermitAction_20_21}")
				.getValue(facesContext);

		int lic = act.getVch_license_type();
		// String licNo = bof.getLicenceNum();

		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);
		String sql = "";
		System.out.println(" act.getUnit_id() "+ act.getUnit_id());
		try {

			if (lic == 1) {
				sql = "	SELECT brand_id, brand_name FROM distillery.brand_registration_20_21 "
						+ "  where vch_license_type='BWFL2A' "
						+ "   and int_bwfl_id =?  " + "     order by brand_id ";
			} else if (lic == 2) {
				sql =

				"	SELECT brand_id, brand_name FROM distillery.brand_registration_20_21 "
						+ "  where vch_license_type='BWFL2B' "
						+ "   and int_bwfl_id =?   "
						+ "     order by brand_id ";
			}

			else if (lic == 3) {

				sql =

				"	SELECT brand_id, brand_name FROM distillery.brand_registration_20_21 "
						+ "  where vch_license_type='BWFL2C' "
						+ "   and int_bwfl_id =?   "
						+ "     order by brand_id ";

			} else if (lic == 4) {

				sql =

				"	SELECT brand_id, brand_name FROM distillery.brand_registration_20_21 "
						+ "  where vch_license_type='BWFL2D' "
						+ "   and int_bwfl_id =?   "
						+ "     order by brand_id ";
			}
			 
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			if (lic != 99) {
				ps.setInt(1, act.getUnit_id());
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("brand_name"));
				item.setValue(rs.getString("brand_id"));
				list.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), e.getMessage()));

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
				+ "	where a.brand_id=b.brand_id_fk  and b.mrp >0 " + "	and brand_id =? ";
		 
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
					+ "	where a.brand_id=b.brand_id_fk  "
					+ "	and brand_id =? and b.mrp >0 and b.package_id=?";
		 
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
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, se
							.getMessage(), se.getMessage()));

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

	public String getEtinNmbr(String brand_Id, String packging_Id,
			ImportPermitDT_20_21 dt) {

		String etin = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList = " SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id, b.code_generate_through,b.duty,b.adduty "
					+ " FROM distillery.brand_registration_20_21 a , distillery.packaging_details_20_21 b "
					+ " WHERE a.brand_id=b.brand_id_fk  "
					+ " AND brand_id =?  and b.mrp >0 AND b.package_id=?";
		 
			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, Integer.parseInt(brand_Id));
			pstmt.setInt(2, Integer.parseInt(packging_Id));

			rs = pstmt.executeQuery();

			if (rs.next()) {

				etin = rs.getString("code_generate_through");
				dt.setDuty(rs.getDouble("duty"));
				dt.setAdduty(rs.getDouble("adduty"));
			}

		} catch (Exception se) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, se
							.getMessage(), se.getMessage()));

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

			String queryList = " SELECT a.brand_id, a.brand_name, a.vch_license_type, b.package_name, b.package_id, b.permit "
					+ " FROM distillery.brand_registration_20_21 a , distillery.packaging_details_20_21 b  "
					+ " WHERE a.brand_id=b.brand_id_fk  "
					+ " AND a.brand_id =? and b.mrp >0 AND b.package_id=? ";
		 
			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, Integer.parseInt(brand_Id));
			pstmt.setInt(2, Integer.parseInt(packging_Id));

			// System.out.println("brand_Id------------"+brand_Id);

			// System.out.println("packging_Id------------"+packging_Id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				permitFee = rs.getDouble("permit");

			}

		} catch (Exception se) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, se
							.getMessage(), se.getMessage()));

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

	public int maxId(int district_id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(id) as id FROM bwfl_license.import_permit_20_21 where district_id='"
				+ district_id + "' and login_type='BWFL' ";
		int maxid = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxid = rs.getInt("id");
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

	public boolean save(ImportPermitAction_20_21 act) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		int max_id = maxId(act.getVch_firm_district_id());
		int app_id=this.app_id();
		String query = "";
		double import_fee = 0;
		double duty = 0;
		double add_duty = 0;
		double special_fee = 0;
		boolean flg=false;
		try {

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);

			query = "INSERT INTO bwfl_license.import_permit_dtl_20_21(fk_id, district_id, brand_id, pckg_id, etin, no_of_cases, no_of_bottle_per_case,"
					+ " pland_no_of_bottles, import_fee, duty, add_duty, special_fee, cr_date,login_type,app_id) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
			if (act.getDisplaylist().size() > 0) {
				for (int i = 0; i < act.getDisplaylist().size(); i++) {
					ImportPermitDT_20_21 dt = (ImportPermitDT_20_21) act.getDisplaylist()
							.get(i);

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
					pstmt.setDate(13,
							Utility.convertUtilDateToSQLDate(new Date()));
					pstmt.setString(14, "BWFL");
					pstmt.setInt(15, app_id);

					import_fee = import_fee + dt.getCal_importFee();
					duty = duty + dt.getCalculated_duty();
					add_duty = add_duty + dt.getCalculated_add_duty();
					special_fee = special_fee + dt.getSpecial_fee();
					saveStatus = pstmt.executeUpdate();

				}
			}

			if (saveStatus > 0) {
				query = "INSERT INTO bwfl_license.import_permit_20_21( id, district_id, bwfl_id, unit_id, bwfl_type, import_fee,"
						+ " duty, add_duty, special_fee, cr_date,bottlng_type,route_road_radio,route_detail,lic_no, " +
						" login_type,spcl_fee_challan_no,import_fee_challan_no,app_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,? ,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, max_id);
				pstmt.setInt(2, act.getVch_firm_district_id());
				pstmt.setInt(3, act.getInt_id());
				pstmt.setInt(4, act.getUnit_id());
				pstmt.setInt(5, act.getVch_license_type());
				pstmt.setDouble(6, import_fee);
				pstmt.setDouble(7, duty);
				pstmt.setDouble(8, add_duty);
				pstmt.setDouble(9, special_fee);
				pstmt.setDate(10, Utility.convertUtilDateToSQLDate(new Date()));
				pstmt.setString(11, act.getBottlngType());
				pstmt.setString(12, act.getRoute_road_radio());
				pstmt.setString(13, act.getRoute_detail());
				pstmt.setString(14, act.getVch_license_number());
				pstmt.setString(15, "BWFL");
				pstmt.setString(16, "ADVANCE");
				pstmt.setString(17, "ADVANCE");
				pstmt.setInt(18, app_id);
				
				saveStatus = pstmt.executeUpdate();

			}

			if (saveStatus > 0) {
				 
				conn.setAutoCommit(true);
				flg=true;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Your Request id is " + app_id));
				// ResourceUtil.addErrorMessage(Constants.SAVED_SUCESSFULLY,Constants.SAVED_SUCESSFULLY);
				act.reset();
				

			} else {
				// action.reset();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
						Constants.NOT_SAVED);

			}
		}

		catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		} catch (Exception e) {
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

	public ArrayList hislistImpl(ImportPermitAction_20_21 act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		// int i = 1, cases = 0;

		try {
			selQr = "select COALESCE(print_permit_pdf,'NUL')print_permit_pdf,COALESCE(digital_sign_pdf_name,'NUL')digital_sign_pdf_name,app_id,import_fee_challan_no,spcl_fee_challan_no,id,district_id,bwfl_id,unit_id,bwfl_type,import_fee,duty+add_duty as duty,special_fee,cr_date,"
					+ " case when bottlng_type='M' then 'Mapped' else 'Unmapped' end as bottlng_type"
					+ ",COALESCE(vch_status,'Pending')as vch_status, vch_approved,valid_upto from bwfl_license.import_permit_20_21 where district_id='"
					+ act.getVch_firm_district_id()
					+ "' and bwfl_id='"
					+ act.getInt_id()
					+ "' and unit_id='"
					+ act.getUnit_id()
					+ "' and login_type='BWFL' order by id desc";

			 
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(selQr);

			rs = ps.executeQuery();
			while (rs.next()) {
				ImportPermitDT_20_21 dt = new ImportPermitDT_20_21();
				dt.setApp_id(rs.getInt("app_id"));
				dt.setDt_app_id(rs.getInt("id"));
				dt.setDt_import_fee(rs.getDouble("import_fee"));
				dt.setDt_duty(rs.getDouble("duty"));
				// dt.setDt_add_duty(rs.getDouble("add_duty"));
				dt.setDt_special_fee(rs.getDouble("special_fee"));
				dt.setDt_bottlng_type(rs.getString("bottlng_type"));
				 
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
				dt.setImport_fee_challan_no(rs.getString("import_fee_challan_no"));
				dt.setSpcl_fee_challan_no(rs.getString("spcl_fee_challan_no"));
				// cases++;
				list.add(dt);
				// i++;
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

	public ArrayList viewdetailImpl(ImportPermitAction_20_21 act, int app_id,
			int district_id) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		// int i = 1, cases = 0;

		try {
			selQr = "select a.brand_id,b.brand_name,a.pckg_id,c.package_name,a.etin,a.no_of_cases,a.no_of_bottle_per_case, "
					+ "a.pland_no_of_bottles,a.import_fee,a.duty,a.add_duty,a.special_fee "
					+ "FROM bwfl_license.import_permit_dtl_20_21 a,distillery.brand_registration_20_21 b,distillery.packaging_details_20_21 c "
					+ "where b.brand_id=c.brand_id_fk and b.brand_id =a.brand_id  AND c.package_id=a.pckg_id and fk_id='"
					+ app_id
					+ "' and district_id='"
					+ district_id
					+ "' and login_type='BWFL'";
		 
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(selQr);

			rs = ps.executeQuery();
			while (rs.next()) {
				ImportPermitDT_20_21 dt = new ImportPermitDT_20_21();

				dt.setBrand_name(rs.getString("brand_name"));
				dt.setPackageName(rs.getString("package_name"));
				dt.setEtin(rs.getString("etin"));
				dt.setNo_of_cases(rs.getInt("no_of_cases"));
				dt.setNo_of_bottle_per_case(rs.getInt("no_of_bottle_per_case"));
				dt.setPland_no_of_bottles(rs.getInt("pland_no_of_bottles"));
				dt.setImportFee(rs.getDouble("import_fee"));
				dt.setDuty(rs.getDouble("duty"));
				dt.setAdduty(rs.getDouble("add_duty"));

				dt.setDt_duty(rs.getDouble("duty"));
				dt.setDt_add_duty(rs.getDouble("add_duty"));
				dt.setDt_special_fee(rs.getDouble("special_fee"));
				
				list.add(dt);
				// i++;
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

	public void getBalance(ImportPermitAction_20_21 act) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s = "";
		try {

			con = ConnectionToDataBase.getConnection();
			String selQr = "";
		
			selQr = " SELECT x.bwfl_id,                                      "
					+ " SUM(x.bwfl_import_fee-(x.ap_import_fee+x.import_fee))as bwfl_import_fee_bal,                                "
					+ " SUM(x.bwfl_special_fee-(x.ap_special_fee+x.special_fee))as bwfl_special_fee_bal, "
					+ " SUM(x.fl2d_import_fee-(x.ap_import_fee+x.import_fee))as fl2d_import_fee_bal,                                "
					+ " SUM(x.fl2d_special_fee-(x.ap_special_fee+x.special_fee))as fl2d_special_fee_bal "
					+ " FROM "
					+ " (SELECT a.int_distillery_id as bwfl_id, SUM(b.double_amt) as bwfl_import_fee, 0 as bwfl_special_fee, "
					+ " 0 as fl2d_import_fee, 0 as fl2d_special_fee, "
					+ " 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee "
					+ " FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b "
					+ " WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type AND a.int_distillery_id='"
					+ act.getInt_id()
					+ "'   "
					+ " AND b.vch_head_code in ('003900103030000','003900105040000','003900103030000') and b.vch_g6_head_code in ('01','03') "
					+ " AND a.date_challan_date > '2019-06-16' "
					+ " group by bwfl_id "
					+ " UNION "
					+ " SELECT a.int_distillery_id as bwfl_id, 0 as bwfl_import_fee, SUM(b.double_amt) as bwfl_special_fee, "
					+ " 0 as fl2d_import_fee, 0 as fl2d_special_fee, "
					+ " 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee "
					+ " FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b "
					+ " WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type AND a.int_distillery_id='"
					+ act.getInt_id()
					+ "'   "
					+ " AND b.vch_head_code in ('003900105020000','003900103020000') and b.vch_g6_head_code in ('18','13')  "
					+ " AND a.date_challan_date > '2019-06-16' "
					+ " GROUP BY bwfl_id "
					+ " UNION "
					+ " SELECT a.int_distillery_id as bwfl_id, 0 as bwfl_import_fee, 0 as bwfl_special_fee, "
					+ " SUM(b.double_amt) as fl2d_import_fee, 0 as fl2d_special_fee, "
					+ " 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee "
					+ " FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b "
					+ " WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type AND a.int_distillery_id='"
					+ act.getInt_id()
					+ "' "
					+ " AND b.vch_head_code in ('003900105040000','003900103030000','003900103030000') and b.vch_g6_head_code in ('04','05') "
					+ " AND a.date_challan_date > '2019-06-16' "
					+ " GROUP BY bwfl_id "
					+ " UNION "
					+ " SELECT a.int_distillery_id as bwfl_id, 0 as bwfl_import_fee, 0 as bwfl_special_fee, "
					+ " 0 as fl2d_import_fee, SUM(b.double_amt) as fl2d_special_fee, "
					+ " 0 as ap_import_fee, 0 as ap_special_fee, 0 as import_fee, 0 as special_fee "
					+ " FROM revenue.g6_challan_deposit a, revenue.g6_challn_deposit_detail b "
					+ " WHERE a.int_challan_id=b.int_challan_id AND a.vch_challan_type=b.vch_challan_type AND a.int_distillery_id='"
					+ act.getInt_id()
					+ "'  "
					+ " AND b.vch_head_code in ('003900105020000','003900103020000') and b.vch_g6_head_code in ('18','13') "
					+ " AND a.date_challan_date > '2019-06-16' "
					+ " GROUP BY bwfl_id "
					+ " UNION                                                                                                      "
					+ " SELECT a.bwfl_id, 0 as bwfl_import_fee, 0 as bwfl_special_fee,0 as fl2d_import_fee, 0 as fl2d_special_fee,  "
					+ " SUM(a.import_fee) as ap_import_fee, SUM(COALESCE(a.special_fee,0)) as ap_special_fee,    "
					+ " 0 as import_fee, 0 as special_fee "
					+ " FROM bwfl_license.import_permit_20_21 a                                                                          "
					+ " WHERE a.bwfl_id='"
					+ act.getInt_id()
					+ "' AND a.vch_approved='APPROVED'  and   login_type='BWFL'                                       "
					+ " GROUP BY a.bwfl_id " + " )x GROUP BY x.bwfl_id";

			
			pstmt = con.prepareStatement(selQr);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// act.setDuty_balance(rs.getDouble("bal_duty"));
				act.setImportFee_balance(rs.getDouble("bwfl_import_fee_bal"));
				act.setSpecialFee_balance(rs.getDouble("bwfl_special_fee_bal"));
			}

		} catch (Exception se) {
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

	public int delete(ImportPermitAction_20_21 action, int id, int dist) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int saveStatus = 0;

		String sql = "";

		sql = "delete from bwfl_license.import_permit_20_21  where id='" + id
				+ "' and district_id='" + dist + "' and login_type='BWFL'";

		try {

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			 
			pstmt = conn.prepareStatement(sql);
			saveStatus = pstmt.executeUpdate();
			if (saveStatus > 0) {
				saveStatus = 0;
				sql = "delete from bwfl_license.import_permit_dtl_20_21 where fk_id='"
						+ id
						+ "' and district_id='"
						+ dist
						+ "' and login_type='BWFL'";
				pstmt = conn.prepareStatement(sql);
				saveStatus = pstmt.executeUpdate();
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
		} finally {
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

		String sql = "select int_app_id_f,coalesce(vch_indus_name,'')vch_indus_name,coalesce(vch_wrk_phon,0)vch_wrk_phon from public.other_unit_registration_20_21 where vch_indus_type='IU'";
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("vch_indus_name") + " ( "
						+ rs.getString("vch_wrk_phon") + " )");
				item.setValue(rs.getInt("int_app_id_f"));
				list.add(item);
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
		return list;
	}

	public double getBondPermitSpecialFee(ImportPermitDT_20_21 dt) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ImportPermitAction_20_21 ac = (ImportPermitAction_20_21) facesContext
				.getApplication().createValueBinding("#{importPermitAction_20_21}")
				.getValue(facesContext);

		String lic = String.valueOf(ac.getVch_license_type());

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "";
		String lic_type = "";
		double permit_fee = 0.0;
		try {
			if (lic.equalsIgnoreCase("1")) {
				lic_type = "BWFL2A";
			} else if (lic.equalsIgnoreCase("2")) {
				lic_type = "BWFL2B";
			} else if (lic.equalsIgnoreCase("3")) {
				lic_type = "BWFL2C";
			} else if (lic.equalsIgnoreCase("4")) {
				lic_type = "BWFL2D";
			}
			sql = "SELECT lic_type,fee,special_fee FROM licence.bond_permit_fee WHERE lic_type='"
					+ lic_type + "'";
			System.out.println("spcl and permit fee=" + sql);
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {

				permit_fee = rs.getDouble("fee");
				dt.setSpcl_fee(rs.getDouble("special_fee"));
			} else {
				permit_fee = 0.0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e
							.getMessage(), e.getMessage()));

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
		return permit_fee;
	}

	public void ChallanList(ImportPermitAction_20_21 act) {
		 
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
			 selQr = " select chalan_no,chalan_date,amount from bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no in  ( SELECT DISTINCT "
					+ " CASE WHEN head_type like 'Import%' then chalan_no "
					+ " WHEN head_type like 'BWFL%' then chalan_no else '' end as import_fee_challan_no "
				//	+ " ,CASE WHEN head_type like 'Special%' then chalan_no else '' end as spcl_fee_challan_no "
					+ " FROM bwfl_license.chalan_deposit_bwfl_fl2d  "
					+ " WHERE unit_id="+act.getInt_id()+" AND unit_type= "
					+ " CASE WHEN 1="+act.getVch_license_type()+" THEN 'BWFL2A' WHEN 2="+act.getVch_license_type()+" THEN 'BWFL2B' WHEN 3="+act.getVch_license_type()+" THEN 'BWFL2C' WHEN 4="+act.getVch_license_type()+" THEN 'BWFL2D' END " +
					" EXCEPT  " +
					" select DISTINCT import_fee_challan_no" +
					" from bwfl_license.import_permit_20_21  " +
					" where bwfl_id='"+act.getInt_id()+"' and bwfl_type="+act.getVch_license_type()+" and district_id='"+act.getVch_firm_district_id()+"') and  unit_id="+act.getInt_id()+" AND unit_type= "
					+ " CASE WHEN 1="+act.getVch_license_type()+" THEN 'BWFL2A' WHEN 2="+act.getVch_license_type()+" THEN 'BWFL2B' WHEN 3="+act.getVch_license_type()+" THEN 'BWFL2C' WHEN 4="+act.getVch_license_type()+" THEN 'BWFL2D' END " ;
				

			pstmt = con.prepareStatement(selQr);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				//System.out.println("01="+rs.getString("chalan_no"));
				if(!rs.getString("chalan_no").trim().equalsIgnoreCase("")){
					 
					ImportChallan = new SelectItem();
					ImportChallan.setLabel(rs.getString("chalan_no")+" (Rs."+rs.getString("amount")+") - "+rs.getString("chalan_date"));
					ImportChallan.setValue(rs.getString("chalan_no"));
					ImportChallanList.add(ImportChallan);
				}	
			}
			act.setImportChallanList(ImportChallanList);
			 selQr = " select chalan_no,chalan_date,amount from bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no in  ( SELECT DISTINCT "+
			 		" CASE WHEN head_type like 'Special%' then chalan_no else '' end as spcl_fee_challan_no "
						+ " FROM bwfl_license.chalan_deposit_bwfl_fl2d  "
						+ " WHERE unit_id="+act.getInt_id()+" AND unit_type= "
						+ " CASE WHEN 1="+act.getVch_license_type()+" THEN 'BWFL2A' WHEN 2="+act.getVch_license_type()+" THEN 'BWFL2B' WHEN 3="+act.getVch_license_type()+" THEN 'BWFL2C' WHEN 4="+act.getVch_license_type()+" THEN 'BWFL2D' END " +
						" EXCEPT  " +
						" select spcl_fee_challan_no" +
						" from bwfl_license.import_permit_20_21  " +
						" where bwfl_id='"+act.getInt_id()+"' and bwfl_type="+act.getVch_license_type()+" and district_id='"+act.getVch_firm_district_id()+"' ) and  unit_id="+act.getInt_id()+" AND unit_type= "
						+ " CASE WHEN 1="+act.getVch_license_type()+" THEN 'BWFL2A' WHEN 2="+act.getVch_license_type()+" THEN 'BWFL2B' WHEN 3="+act.getVch_license_type()+" THEN 'BWFL2C' WHEN 4="+act.getVch_license_type()+" THEN 'BWFL2D' END " ;
				

			 
			 
			// System.out.println("ChallanList1=" + selQr);
			 pstmt=null;
			 rs=null;
				pstmt = con.prepareStatement(selQr);
				rs = pstmt.executeQuery();
				while (rs.next()) {
				//	System.out.println("01="+rs.getString("chalan_no"));
					if(!rs.getString("chalan_no").trim().equalsIgnoreCase("")){
						 
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
	public boolean getChallanBalance(ImportPermitAction_20_21 act) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flg=true;
		String sql="";
		sql = " select COALESCE(amount,0.0)import_amount " +
			" FROM bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no='"+act.getImport_fee_challan_no()+"' " 
			+ " and unit_id="+act.getInt_id()+" AND unit_type= "
			+ " CASE WHEN 1="+act.getVch_license_type()+" THEN 'BWFL2A' WHEN 2="+act.getVch_license_type()+" THEN 'BWFL2B' WHEN 3="+act.getVch_license_type()+" THEN 'BWFL2C' WHEN 4="+act.getVch_license_type()+" THEN 'BWFL2D' END ";
//System.out.println("getChallanImportFee-"+sql);
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				 if(Double.parseDouble(act.getTotal_import_fees())>rs.getDouble("import_amount")){
					 flg=false; 
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Import Fee for this permit is greater then your selected Challan"));
				 }else {
					 //System.out.println(Double.parseDouble(act.getTotal_import_fees())+"-import fee is "+rs.getDouble("import_amount"));
				 }
			}
			ps=null;
			rs=null;
			sql = " select COALESCE(amount,0.0)spcl_amount " +
					" FROM bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no='"+act.getSpcl_fee_challan_no()+"' " 
					+ " and unit_id="+act.getInt_id()+" AND unit_type= "
					+ " CASE WHEN 1="+act.getVch_license_type()+" THEN 'BWFL2A' WHEN 2="+act.getVch_license_type()+" THEN 'BWFL2B' WHEN 3="+act.getVch_license_type()+" THEN 'BWFL2C' WHEN 4="+act.getVch_license_type()+" THEN 'BWFL2D' END ";
			 
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				 if(Double.parseDouble(act.getTotal_special_fee())>rs.getDouble("spcl_amount")){
					 flg=false; 
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Special Fee for this permit is greater then your selected Challan"));
				 }else {
					 
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
	
	//=============prasoooooooooooooooooooooooooooooooooooooooon====================================
	
	
	public void getimport_special_fee(ImportPermitAction_20_21 act) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			String selQr =
					"     select (select (select coalesce(sum(import_amount),0)  from fl2d.opening_fees_for_fl2d_bwfl_20_21 where unit_id= '"+act.getInt_id()+"' and unit_type like '%BWFL%' )    "+
					"	+(SELECT  coalesce(sum(amount),0)  as challan_amt  FROM bwfl_license.chalan_deposit_bwfl_fl2d b                                                   "+
					"	WHERE b.unit_id='"+act.getInt_id()+"'  and unit_type like '%BWFL%'                                                                                                 "+
					"	and head_code in ('003900103030000','003900105040000') and g6_code in ('03','01') AND                                                             "+
					"	b.cr_date between '2020-04-01'  AND '"+Utility.convertUtilDateToSQLDate(new Date())+"')-                                                                                                "+
					"	(SELECT   coalesce(sum(b.import_fee),0)  as disptch_duty FROM bwfl_license.import_permit_20_21 b WHERE b.bwfl_id= '"+act.getInt_id()+"' and                          "+
					"	login_type='BWFL'  AND b.cr_date between '2020-04-01'  AND '"+Utility.convertUtilDateToSQLDate(new Date())+"' and vch_approved='APPROVED')	as importfee                              "+
					"	),                                                                                                                                                "+
					"	(select (select coalesce(sum(cowcesh_amt),0)  from fl2d.opening_fees_for_fl2d_bwfl_20_21 where unit_id= '"+act.getInt_id()+"' and unit_type like '%BWFL%' )            "+
					"	+(SELECT  coalesce(sum(amount),0)  as challan_amt  FROM bwfl_license.chalan_deposit_bwfl_fl2d b                                                   "+
					"	WHERE b.unit_id='"+act.getInt_id()+"'  and unit_type like '%BWFL%'                                                                                                 "+
					"	and head_code in ('003900103020000','003900105020000') and g6_code in ('13','18') AND                                                             "+
					"	b.cr_date between '2020-04-01'  AND '"+Utility.convertUtilDateToSQLDate(new Date())+"')-                                                                                                "+
					"	(SELECT   coalesce(sum(b.special_fee),0)  as disptch_duty FROM bwfl_license.import_permit_20_21 b WHERE b.bwfl_id= '"+act.getInt_id()+"' and                         "+
					"	login_type='BWFL'  AND b.cr_date between '2020-04-01'  AND '"+Utility.convertUtilDateToSQLDate(new Date())+"' and vch_approved='APPROVED')as specialfee)                                " ;
														 
					
			 
			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				act.setTotal_advance_importFee(rs.getString("importfee"));
				act.setTotal_advance_specialFee(rs.getString("specialfee"));

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
}
