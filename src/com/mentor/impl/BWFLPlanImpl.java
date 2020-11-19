package com.mentor.impl;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mentor.action.BWFLPlanAction;
import com.mentor.datatable.BWFLPlanDataTable;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class BWFLPlanImpl {
	

	public int getId(BWFLPlanAction action) { 
		int id = 0;
		Connection con = null;
		Connection con2 = null;
		PreparedStatement ps = null, ps1 = null;
		ResultSet rs = null, rs1 = null;
		String sql = "";

		int rolDist = 0;
		int rolBrewry = 0;

		try { 
			con = ConnectionToDataBase.getConnection();

			sql = 	" SELECT int_id, mobile_number, vch_license_type, vch_license_number, " +
					" vch_firm_name, vch_firm_add, vch_firm_district_name  " +
					" FROM bwfl.registration_of_bwfl_lic_holder  " +
					" WHERE vch_approval='V' AND vch_distillery_contact_number='"+ResourceUtil.getUserNameAllReq().trim()+"' ";
				
			ps = con.prepareStatement(sql);
			
			
			System.out.println("get hidden--------------------"+sql);
			
			
			rs = ps.executeQuery();

			if (rs.next()) {

				action.setDistillery_id(rs.getString("int_id"));
				action.setDistillery_nm(rs.getString("vch_firm_name"));
				action.setDistillery_adrs(rs.getString("vch_firm_add"));
				action.setLicenceTypeId(rs.getString("vch_license_type"));
				action.setLicenceNum(rs.getString("vch_license_number"));
				action.setUserDistrict(rs.getString("vch_firm_district_name"));
				
				if (rs.getString("vch_license_type").equals("1")) {
					action.setLicenceType("BWFL2A");
				} else if (rs.getString("vch_license_type").equals("2")) {
					action.setLicenceType("BWFL2B");
				} else if (rs.getString("vch_license_type").equals("3")) {
					action.setLicenceType("BWFL2C");
				} else if (rs.getString("vch_license_type").equals("4")) {
					action.setLicenceType("BWFL2D");
				}
			}

		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
				if (con2 != null)
					con2.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return id;
	}
	
	public int getIdMapped(BWFLPlanAction action) { 
		int id = 0;
		Connection con = null;
		Connection con2 = null;
		PreparedStatement ps = null, ps1 = null;
		ResultSet rs = null, rs1 = null;
		String sql = "";

		int rolDist = 0;
		int rolBrewry = 0;

		try { 
			con = ConnectionToDataBase.getConnection();

			sql = " SELECT int_id, vch_distillery_contact_number, vch_license_type, vch_firm_name, vch_firm_add "
					+ " FROM bwfl.registration_of_bwfl_lic_holder "
					+ " WHERE vch_approval='V' AND "
					+ " vch_distillery_contact_number='"
					+ ResourceUtil.getUserNameAllReq().trim() + "' ";
				System.out.println("mapped basic sql="+sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {

				action.setDistillery_id(rs.getString("int_id"));
				action.setDistillery_nm(rs.getString("vch_firm_name"));
				action.setDistillery_adrs(rs.getString("vch_firm_add"));
				if (rs.getString("vch_license_type").equals("1")) {
					action.setLicenceType("BWFL2A");
				} else if (rs.getString("vch_license_type").equals("2")) {
					action.setLicenceType("BWFL2B");
				} else if (rs.getString("vch_license_type").equals("3")) {
					action.setLicenceType("BWFL2C");
				} else if (rs.getString("vch_license_type").equals("4")) {
					action.setLicenceType("BWFL2D");
				}
			}

		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
				if (con2 != null)
					con2.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return id;
	}

	// //////////// ---------------------------m didt id ----------------------

	// ------------------------------------------------------------------------------

	// ----------------------------- get Liqure Type
	// --------------------------------------------

	public ArrayList getLiqureType() {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);
		String SQl = "select id, description from distillery.imfl_type   order by id ";
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("description"));
				item.setValue(rs.getString("id"));
				list.add(item);
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
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// ----------------------------- get Licence NO.
	// --------------------------------------------

	public String getLicenseNo(BWFLPlanAction action,String id) {
	 
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String list = "";
	 
		String val=null;
		if( action.getLicenceType().equalsIgnoreCase("1"))
			{val="BWFL2A";}
		if( action.getLicenceType().equalsIgnoreCase("2"))
		{val="BWFL2B";}
		if( action.getLicenceType().equalsIgnoreCase("3"))
		{val="BWFL2C";}
		if( action.getLicenceType().equalsIgnoreCase("4"))
		{val="BWFL2D";}

		String SQl = " SELECT DISTINCT COALESCE(license_number,'NA') as license_number FROM distillery.brand_registration WHERE int_bwfl_id ="
				+ id
				+ "  and vch_license_type= '"
				+ val + "'";

		try {

			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);

			 System.out.println("SQl---------license nmbr--------" + SQl);
			// ps.setString(1, lice.trim());

			rs = ps.executeQuery();
			if (rs.next()) {

				list=rs.getString("license_number") ;
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
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// ----------------------------- get Brand
	// --------------------------------------------

	public ArrayList getBrandName() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		BWFLPlanAction bof = (BWFLPlanAction) facesContext.getApplication().createValueBinding("#{bWFLPlanAction}").getValue(facesContext);

		String lic = bof.getLicenceTypeId();

		String licNo = bof.getLicenceNum();
		

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

			if (lic.equalsIgnoreCase("1")) {
				sql =

				"	SELECT brand_id, brand_name FROM distillery.brand_registration "
						+ "  where vch_license_type='BWFL2A' "
						+ "   and int_bwfl_id =? and  license_number = ? "
						+ "     order by brand_id ";

				  System.out.println("BWFL2A");

			} else if (lic.equalsIgnoreCase("2")) {
				sql =

				"	SELECT brand_id, brand_name FROM distillery.brand_registration "
						+ "  where vch_license_type='BWFL2B' "
						+ "   and int_bwfl_id =? and  license_number = ? "
						+ "     order by brand_id ";

				// System.out.println("BWFL2B");

			}

			else if (lic.equalsIgnoreCase("3")) {

				sql =

				"	SELECT brand_id, brand_name FROM distillery.brand_registration "
						+ "  where vch_license_type='BWFL2C' "
						+ "   and int_bwfl_id =? and  license_number = ? "
						+ "     order by brand_id ";

				// System.out.println("BWFL2C");

			} else if (lic.equalsIgnoreCase("4")) {

				sql =

				"	SELECT brand_id, brand_name FROM distillery.brand_registration "
						+ "  where vch_license_type='BWFL2D' "
						+ "   and int_bwfl_id =? and  license_number = ? "
						+ "     order by brand_id ";
				// System.out.println("BWFL2D");

			}

			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);

			ps.setInt(1, Integer.parseInt(bof.getDistillery_id()));
			ps.setString(2, (licNo.trim()));
			//
		 System.out.println("licNo -- " + licNo.trim());

			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();

				item.setLabel(rs.getString("brand_name"));
				item.setValue(rs.getString("brand_id"));
				list.add(item);
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
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// ----------------------------- get Packaging Name
	// --------------------------------------------

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
				+ "	from distillery.brand_registration a , "
				+ "	distillery.packaging_details b "
				+ "	where a.brand_id=b.brand_id_fk  " +
				// "	and a.distillery_id=? "+
				"	and brand_id =? ";
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

	// ----------------------------- get quantity
	// --------------------------------------------

	public ArrayList getquantity(String brand_Id, String packging_Id) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		/*
		 * item.setLabel("--SELECT--"); item.setValue(""); list.add(item);
		 */
		String SQl =

		"SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id "
				+ "	from distillery.brand_registration a , "
				+ "	distillery.packaging_details b "
				+ "	where a.brand_id=b.brand_id_fk  " +
				// "	and a.distillery_id=?  "+
				"	and brand_id =?  and b.package_id=?";
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);
			// ps.setInt(1, this.getSugarmill_Id());
			ps.setInt(1, Integer.parseInt(brand_Id));
			ps.setInt(2, Integer.parseInt(packging_Id));
			rs = ps.executeQuery();
			while (rs.next()) {

				//System.out.println("rs.getDoublequantity"+ rs.getDouble("quantity"));
				item = new SelectItem();
				item.setLabel(rs.getString("quantity"));
				item.setValue(rs.getDouble("quantity"));
				list.add(item);
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
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// ----------------------------- 2nd -----------------

	public String getqty(String brand_Id, String packging_Id) {
		String qty = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList =

			" SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id "
					+ "	from distillery.brand_registration a , "
					+ "	distillery.packaging_details b "
					+ "	where a.brand_id=b.brand_id_fk  " +
					// "	and a.distillery_id=?  "+
					"	and brand_id =?  and b.package_id=?";

			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, Integer.parseInt(brand_Id));
			pstmt.setInt(2, Integer.parseInt(packging_Id));

			rs = pstmt.executeQuery();

			while (rs.next()) {

				qty = rs.getString("quantity");

			}

			// pstmt.executeUpdate();
		} catch (SQLException se) {
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

	// ---------------------------------------------- save
	// --------------------------
	public int maxId() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(seq) as id FROM bwfl_license.mst_bottling_plan ";
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

	public void save(BWFLPlanAction action) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		int challanId = maxId();
		String query ="";
		try {

			conn = ConnectionToDataBase.getConnection();
			
			
			
				query= " INSERT INTO bwfl_license.mst_bottling_plan"
						+ " (int_distillery_id, int_brand_id, int_pack_id, int_quantity, int_planned_bottles, "
						+ " int_boxes, vch_license_type, plan_dt, licence_no, cr_date, int_liquor_type, "
						+ " received_liqour, permitno, permitdt, seq, gatepass_no, maped_unmaped_type, etin,validity_date,permitno_entered) "
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?," + challanId +", "+ challanId + ", ?, ?, ?, ?) ";
			
		
				 
		     	if (action.getBrandPackagingDataList().size() > 0) {

				for (int i = 0; i < action.getBrandPackagingDataList().size(); i++) {

					saveStatus = 0;
					pstmt = conn.prepareStatement(query);
					int j = 1;

					BWFLPlanDataTable table = (BWFLPlanDataTable) action.getBrandPackagingDataList().get(i);

					if (table.isUpdateflg() == false) {

						pstmt.setInt(1,Integer.parseInt( action.getDistillery_id()));
						pstmt.setInt(2, Integer.parseInt(table.getBrandPackagingData_Brand()));
						pstmt.setInt(3, Integer.parseInt(table.getBrandPackagingData_Packaging()));
						pstmt.setDouble(4, Double.parseDouble(table.getBrandPackagingData_Quantity()));
						pstmt.setInt(5,table.getBrandPackagingData_PlanNoOfBottling());
						pstmt.setInt(6, table.getBrandPackagingData_NoOfBoxes());
						if (action.getLicenceTypeId().equalsIgnoreCase("1"))
						{
							pstmt.setString(7,"BWFL2A");
						} else if (action.getLicenceTypeId().equalsIgnoreCase("2")) {
							pstmt.setString(7,"BWFL2B");
						} else if (action.getLicenceTypeId().equalsIgnoreCase("3")) {
							pstmt.setString(7,"BWFL2C");
						} else if (action.getLicenceTypeId().equalsIgnoreCase("4")) {
							pstmt.setString(7,"BWFL2D");
						}
						pstmt.setDate(8, Utility.convertUtilDateToSQLDate(new Date()));
						pstmt.setString(9, action.getLicenceNum());
						pstmt.setDate(10,Utility.convertUtilDateToSQLDate(new Date()));

						if (action.getLicenceTypeId().equalsIgnoreCase("1")) {
							pstmt.setInt(11, 1);
						} else if (action.getLicenceTypeId().equalsIgnoreCase("2")) {
							pstmt.setInt(11, 2);
						} else if (action.getLicenceTypeId().equalsIgnoreCase("3")) {
							pstmt.setInt(11, 3);
						} else if (action.getLicenceTypeId().equalsIgnoreCase("4")) {
							pstmt.setInt(11, 4);
						}
						pstmt.setInt(12, 0);
						pstmt.setString(13, action.getUserDistrict()+"-2018-19-"+action.getPermitNoEnterd()+"-"+challanId);
						pstmt.setDate(14, Utility.convertUtilDateToSQLDate(action.getPermitdt()));
						pstmt.setString(15, action.getBottlngType());
						pstmt.setString(16, table.getBrandPackagingData_etinNmbr());
                        pstmt.setDate(17, Utility.convertUtilDateToSQLDate(action.getValidityDate()));
                        pstmt.setString(18, action.getPermitNoEnterd());
                       
						saveStatus = pstmt.executeUpdate();
					}
				}
			}
			
			if (saveStatus > 0) {

				ResourceUtil.addErrorMessage(Constants.SAVED_SUCESSFULLY,Constants.SAVED_SUCESSFULLY);
				action.reset();

			} else {
				
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,Constants.NOT_SAVED);

			}

		}

		catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
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

	}

	
	
	
	
	public void update(BWFLPlanAction action) {
		
		
		System.out.println("hello------------------------fama");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		int challanId = maxId();
		String query ="";
		try {

			conn = ConnectionToDataBase.getConnection();
			
			
			
				
				/*query = " UPDATE bwfl_license.mst_bottling_plan SET " +
						 " int_quantity=?, int_planned_bottles=?, int_boxes=?,   licence_no=?,  "+
						 " cr_date=?,  permitdt=?, maped_unmaped_type=? ,validity_date=? "+
						 
			 " where permitno_entered='"+action.getPermitno_enteredUpdate()+"' and vch_license_type='"+action.getVch_license_typeUpdate()+"' and int_distillery_id='"+action.getInt_distillery_idUpdate()+"' and int_brand_id='"+action.getInt_brand_idUpdate()+"' "+
			" and int_pack_id='"+action.getInt_pack_idUpdate()+"' and seq='"+action.getSeqUpdate()+"' and licence_no='"+action.getLicence_noUpdate()+"'  and gatepass_no='"+action.getGatepass_noUpdate()+"'" +
			" and etin='"+action.getEtinUpdate()+"' and    permitdt='"+action.getPermitDatUpdate()+"'  " ;*/
			
			
			query = " UPDATE bwfl_license.mst_bottling_plan SET " +
					 " int_quantity=?, int_planned_bottles=?, int_boxes=?,   licence_no=?,  "+
					 " cr_date=?,  permitdt=?, maped_unmaped_type=? ,validity_date=? "+
					 
		 " where permitno_entered='"+action.getPermitno_enteredUpdate()+"' and vch_license_type='"+action.getVch_license_typeUpdate()+"' and int_distillery_id='"+action.getInt_distillery_idUpdate()+"' and int_brand_id=? "+
		" and int_pack_id=? and seq='"+action.getSeqUpdate()+"' and licence_no='"+action.getLicence_noUpdate()+"'  and gatepass_no='"+action.getGatepass_noUpdate()+"'" +
		" and     permitdt='"+action.getPermitDatUpdate()+"'  " ;
				
				System.out.println("==============2222222222222222222 222222"+query);
					for (int i = 0; i < action.getBrandPackagingDataList().size(); i++) 
					{
						pstmt = conn.prepareStatement(query);
					
						BWFLPlanDataTable table = (BWFLPlanDataTable) action.getBrandPackagingDataList().get(i);
						
						System.out.println("==============getBrandPackagingData_Quantity()====="+table.getBrandPackagingData_Quantity());									
							pstmt.setInt(1,Integer.parseInt(table.getBrandPackagingData_Quantity()));
							pstmt.setInt(2,table.getBrandPackagingData_PlanNoOfBottling());
							pstmt.setInt(3, table.getBrandPackagingData_NoOfBoxes());
							
							pstmt.setString(4, action.getLicenseNoNew());
							pstmt.setDate(5,Utility.convertUtilDateToSQLDate(new Date()));

							pstmt.setDate(6, Utility.convertUtilDateToSQLDate(action.getPermitdt()));
							pstmt.setString(7, action.getBottlngType());
						
	                        pstmt.setDate(8, Utility.convertUtilDateToSQLDate(action.getValidityDate()));
	                        
	                        
	                        pstmt.setInt(9,Integer.parseInt(table.getBrandPackagingData_Brand()));
	                        pstmt.setInt(10,Integer.parseInt( table.getBrandPackagingData_Packaging()));
	                      
	                        
	                        
	                        
	                        
	                        
	                        
	                        
	                        
	                        
	                        
	                        
							saveStatus = pstmt.executeUpdate();
							
					}
					if(saveStatus>0)
					{
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Updated Sucessfully", "Updated Sucessfully"));
					action.reset();
					}else {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Not Updated ", "Not Updated "));
					}
				
		}
			
		catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
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

	}

	
	
	
	
	
	
	
	
	
	
	// --------------------------------------------

	/*
	 * public void save1(BWFLPlanAction action) { Connection conn = null;
	 * PreparedStatement pstmt = null; ResultSet rs = null; int saveStatus = 0;
	 * // int challanId=getMaxChallanId()+1; try {
	 * 
	 * String query =
	 * 
	 * "			INSERT INTO distillery.mst_bottling_plan( " +
	 * "			int_distillery_id, int_brand_id, int_pack_id, int_quantity, int_planned_bottles, "
	 * +
	 * "		    int_boxes, int_liquor_type, vch_license_type, plan_dt, licence_no, cr_date) "
	 * + "		VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?) ";
	 * 
	 * // String // delete=
	 * " delete from distillery.mst_bottling_plan where  int_distillery_id=? and vch_license_type =? and plan_dt =?"
	 * ;
	 * 
	 * conn = ConnectionToDataBase.getConnection();
	 * 
	 * if (action.getBrandPackagingDataList().size() > 0) { for (int i = 0; i <
	 * action.getBrandPackagingDataList().size(); i++) { saveStatus = 0; pstmt =
	 * conn.prepareStatement(query); int j = 1;
	 * 
	 * BWFLPlanDataTable table = (BWFLPlanDataTable) action
	 * .getBrandPackagingDataList().get(i); //
	 * pstmt.setInt(1,getMaxChallanIdDetail()+1); pstmt.setInt(1,
	 * action.getDistillery_id()); pstmt.setInt(2, Integer.parseInt(table
	 * .getBrandPackagingData_Brand())); pstmt.setInt(3, Integer.parseInt(table
	 * .getBrandPackagingData_Packaging()));
	 * 
	 * pstmt.setDouble(4, Double.parseDouble(table
	 * .getBrandPackagingData_Quantity())); pstmt.setDouble(5,
	 * table.getBrandPackagingData_PlanNoOfBottling()); pstmt.setDouble(6,
	 * table.getBrandPackagingData_NoOfBoxes()); pstmt.setInt(7,
	 * Integer.parseInt(action.getLiqureTypeId())); pstmt.setString(8,
	 * action.getLicenceType()); pstmt.setDate(9,
	 * Utility.convertUtilDateToSQLDate(action .getDateOfBottling()));
	 * pstmt.setString(10, action.getLicenceNoId()); pstmt.setDate(11,
	 * Utility.convertUtilDateToSQLDate(new Date()));
	 * 
	 * saveStatus = pstmt.executeUpdate(); } }
	 * 
	 * if (saveStatus > 0) {
	 * ResourceUtil.addErrorMessage(Constants.SAVED_SUCESSFULLY,
	 * Constants.SAVED_SUCESSFULLY); action.reset();
	 * 
	 * } else { // action.reset();
	 * ResourceUtil.addErrorMessage(Constants.NOT_SAVED, Constants.NOT_SAVED);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * catch(Exception e) { e.printStackTrace(); }
	 * 
	 * 
	 * catch (SQLException e) { FacesContext .getCurrentInstance() .addMessage(
	 * null, new FacesMessage(
	 * " Same Date Same Brand and Same Packaging  Not Allow For Plan ",
	 * "Same Date Same Brand and Same Packaging Not Allow For Plan")); //
	 * brand_Id,Distillery_ID // and // DAte // PK
	 * 
	 * // brand_Id,Distillery_ID and DAte PK }
	 * 
	 * finally { try {
	 * 
	 * if (conn != null) conn.close(); if (pstmt != null) pstmt.close(); if (rs
	 * != null) rs.close();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 * 
	 * }
	 */

	// ---------------------- get add Row data -----------------------------

	public ArrayList getAddRowData(BWFLPlanAction ac, String lic_typ) {

		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "	SELECT int_distillery_id, int_brand_id, int_pack_id, int_quantity,  "
				+ "	int_planned_bottles, int_boxes, int_liquor_type, "
				+ "	vch_license_type, plan_dt, licence_no, cr_date "
				+ "	FROM bwfl_license.mst_bottling_plan where int_distillery_id='"+Integer.parseInt(ac.getDistillery_id())+"' "+
				 " and vch_license_type='"+ac.getLicenceType()+"' " +
				 " and plan_dt = '"+Utility.convertUtilDateToSQLDate(ac.getPermitdt())+ "'  " +
				 " and licence_no='"+ac.getLicenceNum()+"' and permitno='"+ac.getPermitno()+"' ";

		try {

			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			/*
			 * ps.setInt(1, ac.getDistillery_id()); // ps.setString(2,
			 * ac.getLicenceType()); ps.setString(2, lic_typ);
			 * 
			 * ps.setDate(3, );
			 */
			rs = ps.executeQuery();

			while (rs.next()) {

				BWFLPlanDataTable dt = new BWFLPlanDataTable();
				dt.setUpdateflg(true);

				dt.setBrandPackagingData_Brand(rs.getString("int_brand_id"));
				dt.setBrandPackagingData_Packaging(rs.getString("int_pack_id"));
				dt.setBrandPackagingData_Quantity(rs.getString("int_quantity"));
				dt.setBrandPackagingData_PlanNoOfBottling(rs.getInt("int_planned_bottles"));
				dt.setBrandPackagingData_NoOfBoxes(rs.getInt("int_boxes"));
                dt.setBrandPackagingData_Brand(rs.getString("int_brand_id"));
               // dt.setBrandPackagingData_Packaging(rs.getString(""));
			//	ac.setLicenceNoId(rs.getString("licence_no").trim());
				ac.setCr_date(Utility.convertUtilDateToSQLDate(rs.getDate("cr_date")));
				ac.setCheckLicenceType(rs.getString("vch_license_type"));

				list.add(dt);
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
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// ------------------------------------ SHOW DATA
	// TABLE-----------------------

	public ArrayList getData(BWFLPlanAction ac) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql =

		" SELECT c.export_box_size,c.quantity,c.code_generate_through,coalesce(b.tracking_flg,'N') as tracking_flg,a.group_id,"
		+ " a.finalized_date,a.finalized_flag,a.permitno,a.seq,"
		+ " a.int_distillery_id,a.licence_no,replace(a.licence_no,' ','')as licencenoo ,a.int_brand_id," 
		+ " b.brand_name, a.int_pack_id,c.package_name , a.int_quantity, "
		+ "	a.int_planned_bottles, a.int_boxes, a.int_liquor_type,d.description, a.vch_license_type, a.plan_dt "
		+ "	FROM bwfl_license.mst_bottling_plan a ,distillery.brand_registration b, distillery.packaging_details" 
		+ " c ,distillery.imfl_type d where a.int_brand_id=b.brand_id  and  b.brand_id=c.brand_id_fk and" 
		+ " a.int_pack_id=c.package_id and  a.int_liquor_type=d.id and b.int_bwfl_id  =? AND" 
		+ " a.maped_unmaped_type='M' ORDER BY a.plan_dt DESC  ";

		 
		// user_name ='"+ResourceUtil.getUserNameAllReq()+"'";
		//System.out.println("sql-----------------" + sql);
		/*System.out.println("ac.getDistillery_id()-----------------"
				+ ac.getDistillery_id());*/

		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
	 
			ps.setInt(1,Integer.parseInt(ac.getDistillery_id()));
			rs = ps.executeQuery();
			while (rs.next()) {

				BWFLPlanDataTable dt = new BWFLPlanDataTable();
				dt.setShowDataTable_Date(rs.getDate("plan_dt"));
				dt.setShowDataTable_LiqureType(rs.getString("description"));
				dt.setShowDataTable_LicenceType(rs
						.getString("vch_license_type"));
				dt.setShowDataTable_Brand(rs.getString("brand_name"));
				dt.setShowDataTable_Packging(rs.getString("package_name"));
				dt.setShowDataTable_Quntity(rs.getString("quantity"));
				dt.setShowDataTable_PlanNoOfBottling(rs
						.getString("int_planned_bottles"));
				dt.setShowDataTable_NoOfBoxes(rs.getString("int_boxes"));
				dt.setBrandId(rs.getInt("int_brand_id"));
				dt.setPackagingId(rs.getInt("int_pack_id"));
				dt.setNewml(rs.getInt("quantity"));
				dt.setNewsize(rs.getInt("export_box_size"));
				dt.setLicenceNo(rs.getString("licence_no"));
				dt.setPermitnoD(rs.getString("permitno"));
				dt.setLicencenoo(rs.getString("licencenoo").replaceAll("/", ""));
				dt.setFinalizedFlag(rs.getString("finalized_flag"));
				dt.setFinalize_Date(Utility.convertSqlDateToUtilDate(rs
						.getDate("finalized_date")));
				dt.setGtinno(rs.getString("code_generate_through"));
				dt.setSeq(rs.getInt("seq"));
				dt.setGroupseq(rs.getInt("group_id"));
				if (rs.getDate("finalized_date") != null) {
					Date dat = Utility.convertSqlDateToUtilDate(rs.getDate("plan_dt"));
					// System.out.println("date finalize" + dat);

					DateFormat formatter = new SimpleDateFormat("yyMMdd");
					String date = formatter.format(dat);
					dt.setFinalizedDateString(date);
					//System.out.println(date);
				}
				/*
				 * if (!rs.getString("tracking_flg").equalsIgnoreCase("Y")) {
				 * dt.setFinalizedFlag("N"); }
				 */
				list.add(dt);
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
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// ---------------------------------
	// bool--------------------------------------

	public boolean ckeck(BWFLPlanAction ac) {

		boolean flag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList =

			"	SELECT int_distillery_id, int_brand_id, int_pack_id, int_quantity,  "
					+ "	int_planned_bottles, int_boxes, int_liquor_type, "
					+ "	vch_license_type, plan_dt, licence_no, cr_date "
					+ "		FROM distillery.mst_bottling_plan where  int_distillery_id=? and vch_license_type=?  "
					+ "	    and  plan_dt = ? ";

			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, Integer.parseInt(ac.getDistillery_id()));
			// ps.setString(2, ac.getLicenceType());
			pstmt.setString(2, ac.getLicenceType());

			pstmt.setDate(3,
					Utility.convertUtilDateToSQLDate(ac.getDateOfBottling()));

			rs = pstmt.executeQuery();

			if (rs.next()) {

				flag = true;
			}

			else {
				flag = false;
			}

		} catch (SQLException se) {
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
		return flag;

	}

	public synchronized long getSerialNo(long noOfSequenc) {
		String sql = " select     nextval('public.serial_seq')";
		String sqll = "ALTER SEQUENCE public.serial_seq RESTART WITH ? ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		long serialNo = 0;
		long currseq = 0;

		try {
			conn = ConnectionToDataBase.getConnection3();

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				serialNo = rs.getInt(1);
				if (serialNo == 0) {
					serialNo = serialNo + 1;
				}
				//System.out.println("noOfSequenc " + noOfSequenc);

				pstmt1 = conn
						.prepareStatement("ALTER SEQUENCE public.serial_seq RESTART WITH "
								+ (noOfSequenc + serialNo));

				/*System.out.println("ALTER SEQUENCE public.serial_seq RESTART WITH "
								+ (noOfSequenc + serialNo));*/
				pstmt1.executeUpdate();

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();

				if (pstmt != null)
					pstmt.close();

				if (conn != null)
					conn.close();

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			}
		}

		return serialNo;
	}

	public String getBrandPackagingGtinNo(BWFLPlanAction action,
			BWFLPlanDataTable dt) {

		String gtin = "";
		String sql = "select b.code_generate_through from distillery.brand_registration a, distillery.packaging_details b "
				+ "where  a.brand_id=b.brand_id_fk and a.brand_id=? and b.package_id=? and b.quantity=? and vch_license_type=? and a.int_bwfl_id =?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		/*System.out.println("brandId" + dt.getBrandId() + "  package_id"
				+ dt.getPackagingId() + " b.quantity "
				+ Integer.parseInt(dt.getShowDataTable_Quntity())
				+ " vch_license_type " + dt.getShowDataTable_LicenceType()
				+ " a.distillery_id " + action.getDistillery_id());*/

		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dt.getBrandId());
			pstmt.setInt(2, dt.getPackagingId());
			pstmt.setInt(3, Integer.parseInt(dt.getShowDataTable_Quntity()));
			pstmt.setString(4, dt.getShowDataTable_LicenceType());
			pstmt.setInt(5,Integer.parseInt( action.getDistillery_id()));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				gtin = rs.getString("code_generate_through");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();

				if (pstmt != null)
					pstmt.close();

				if (con != null)
					con.close();

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			}
		}

		return gtin;

	}

	public int getBrandPackagingNoOfBottle(BWFLPlanAction action,
			BWFLPlanDataTable dt) {

		long boxid = 0;
		int boxSize = 0;
		String sql = "select box_id from distillery.brand_registration a, distillery.packaging_details b "
				+ "where  a.brand_id=b.brand_id_fk and a.brand_id=? and b.package_id=? and b.quantity=? and vch_license_type=? and a.int_bwfl_id =?";

		String sql1 = "SELECT  box_size  "
				+ " FROM distillery.box_size_details  where box_id=?";
		;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;

		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				boxid = Long.parseLong(rs.getString("box_id"));
			} else {

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Packaging Not Found",
								"Packaging Not Found"));

			}
			if (boxid > 0) {
				pstmt1 = con.prepareStatement(sql1);
				rs1 = pstmt1.executeQuery();
				if (rs.next()) {

					boxSize = rs.getInt(1);

				} else {

					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("Box Size Not Defined In Master",
									"Box Size Not Defined In Master"));

				}

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (rs1 != null)
					rs1.close();
				if (pstmt != null)
					pstmt.close();
				if (pstmt1 != null)
					pstmt1.close();
				if (con != null)
					con.close();

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			}

		}

		return boxSize;

	}

	/*
	 * public boolean write(BWFLPlanDataTable dt, BWFLPlanAction action,
	 * Connection conn) {
	 * 
	 * System.out.println("excel innn");
	 * 
	 * 
	 * String cl=
	 * " select to_char(GENERATE_SERIES, 'fm00000000000000000000') as GENERATE_SERIES,distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type,  "
	 * + " to_char(case_no , 'fm00000000000000000000')as case_no from " +
	 * "(SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no "
	 * +
	 * "FROM public.bottling_cl where distillery_id=? and dispatch_date=?  and licence_no=? and licence_type=? and gtin_no=?)x"
	 * ; String fl3=
	 * " select to_char(GENERATE_SERIES, 'fm00000000000000000000') as GENERATE_SERIES,distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type,  "
	 * + " to_char(case_no , 'fm00000000000000000000')as case_no from " +
	 * "(SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no "
	 * +
	 * "FROM public.bottling_fl3 where distillery_id=? and dispatch_date=?  and licence_no=? and licence_type=? and gtin_no=?)x"
	 * ; String fl3a=
	 * " select to_char(GENERATE_SERIES, 'fm00000000000000000000') as GENERATE_SERIES,distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type,  "
	 * + " to_char(case_no , 'fm00000000000000000000')as case_no from " +
	 * "(SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no "
	 * +
	 * "FROM public.bottling_fl3a where distillery_id=? and dispatch_date=?  and licence_no=? and licence_type=? and gtin_no=?)x"
	 * ;
	 * 
	 * 
	 * String fl3 = "" +
	 * 
	 * " select to_char(GENERATE_SERIES, 'fm000000000000') as GENERATE_SERIES,distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type,  "
	 * + " to_char(case_no , 'fm0000000000')as case_no from " +
	 * 
	 * "(SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no,b.*   "
	 * + "FROM public.bottling_fl3 a, (select * from GENERATE_SERIES(?,?)) b   "
	 * +
	 * "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?)x"
	 * ;
	 * 
	 * "	select to_char(y.gs, 'fm000000000000')as GENERATE_SERIES ,y.distillery_id, y.dispatch_date, y.gtin_no,"
	 * + "	  y.serial_no_start, y.serial_no_end, y.licence_no, y.licence_type,"
	 * + "	to_char(y.case_no , 'fm0000000000')as case_no from( " +
	 * "	select  GENERATE_SERIES(x.serial_no_start ,x.serial_no_end ) as gs ,x.serial_no_start ,x.serial_no_end,x.licence_no, x.licence_type, "
	 * + "	x.case_no,x.dispatch_date,x.distillery_id,x.gtin_no " + "	from ( " +
	 * "	SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no "
	 * + "	FROM public.bottling_fl3 a " +
	 * "	where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?)x)y"
	 * ;
	 * 
	 * String fl3a =
	 * 
	 * " select to_char(GENERATE_SERIES, 'fm000000000000') as GENERATE_SERIES,distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type,  "
	 * + " to_char(case_no ,'fm0000000000')as case_no from " +
	 * 
	 * "(SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no,b.*   "
	 * +
	 * "FROM public.bottling_fl3a a, (select * from GENERATE_SERIES(?,?)) b   "
	 * +
	 * "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?)x"
	 * ;
	 * 
	 * 
	 * "	select to_char(y.gs, 'fm000000000000')as GENERATE_SERIES ,y.distillery_id, y.dispatch_date, y.gtin_no,"
	 * + "	  y.serial_no_start, y.serial_no_end, y.licence_no, y.licence_type,"
	 * + "	to_char(y.case_no , 'fm0000000000')as case_no from( " +
	 * "	select  GENERATE_SERIES(x.serial_no_start ,x.serial_no_end ) as gs ,x.serial_no_start ,x.serial_no_end,x.licence_no, x.licence_type, "
	 * + "	x.case_no,x.dispatch_date,x.distillery_id,x.gtin_no " + "	from ( " +
	 * "	SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no "
	 * + "	FROM public.bottling_fl3a a " +
	 * "	where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?)x)y"
	 * ;
	 * 
	 * String cl =
	 * 
	 * " select to_char(GENERATE_SERIES, 'fm000000000000') as GENERATE_SERIES,distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type,  "
	 * + " to_char(case_no , 'fm0000000000')as case_no from " +
	 * 
	 * 
	 * "(SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no,b.*   "
	 * + "FROM public.bottling_cl a, (select * from GENERATE_SERIES(?,?)) b   "
	 * +
	 * "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?)x"
	 * ;
	 * 
	 * "	select to_char(y.gs, 'fm000000000000')as GENERATE_SERIES ,y.distillery_id, y.dispatch_date, y.gtin_no,"
	 * + "	  y.serial_no_start, y.serial_no_end, y.licence_no, y.licence_type,"
	 * + "	to_char(y.case_no , 'fm0000000000')as case_no from( " +
	 * "	select  GENERATE_SERIES(x.serial_no_start ,x.serial_no_end ) as gs ,x.serial_no_start ,x.serial_no_end,x.licence_no, x.licence_type, "
	 * + "	x.case_no,x.dispatch_date,x.distillery_id,x.gtin_no " + "	from ( " +
	 * "	SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no "
	 * + "	FROM public.bottling_cl a " +
	 * "	where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?)x)y"
	 * ;
	 * 
	 * String relativePath = Constants.JBOSS_SERVER_PATH +
	 * Constants.JBOSS_LINX_PATH; FileOutputStream fileOut = null;
	 * 
	 * PreparedStatement pstmt = null; ResultSet rs = null; long start = 0; long
	 * end = 0; boolean flag = false; long k = 0; String noOfUnit = ""; String
	 * date = null;
	 * 
	 * try {
	 * 
	 * // String minmax=getMinMaxSerialNoForExcel(dt, action, conn); // String
	 * ar[]=minmax.split(",");
	 * 
	 * if (dt.getShowDataTable_LicenceType().equals("CL")) {
	 * 
	 * pstmt = conn.prepareStatement(cl); // pstmt.setLong(1,
	 * Long.parseLong(ar[0])); // pstmt.setLong(2, Long.parseLong(ar[1]));
	 * pstmt.setInt(1, action.getDistillery_id()); pstmt.setDate(2,
	 * Utility.convertUtilDateToSQLDate(dt .getShowDataTable_Date()));
	 * pstmt.setString(3, dt.getLicenceNo()); pstmt.setString(4,
	 * dt.getShowDataTable_LicenceType()); pstmt.setString(5, dt.getGtinno());
	 * rs = pstmt.executeQuery(); System.out.println("excecute query cl");
	 * 
	 * } if (dt.getShowDataTable_LicenceType().equals("FL3")) {
	 * 
	 * pstmt = conn.prepareStatement(fl3);
	 * 
	 * // pstmt.setLong(1, Long.parseLong(ar[0])); // pstmt.setLong(2,
	 * Long.parseLong(ar[1])); pstmt.setInt(1, action.getDistillery_id());
	 * pstmt.setDate(2, Utility.convertUtilDateToSQLDate(dt
	 * .getShowDataTable_Date())); pstmt.setString(3, dt.getLicenceNo());
	 * pstmt.setString(4, dt.getShowDataTable_LicenceType()); pstmt.setString(5,
	 * dt.getGtinno()); rs = pstmt.executeQuery();
	 * System.out.println("excecute query fl3");
	 * 
	 * } if (dt.getShowDataTable_LicenceType().equals("FL3A")) {
	 * 
	 * pstmt = conn.prepareStatement(fl3a);
	 * 
	 * // pstmt.setLong(1, Long.parseLong(ar[0])); // pstmt.setLong(2,
	 * Long.parseLong(ar[1])); pstmt.setInt(1, action.getDistillery_id());
	 * pstmt.setDate(2, Utility.convertUtilDateToSQLDate(dt
	 * .getShowDataTable_Date())); pstmt.setString(3, dt.getLicenceNo());
	 * pstmt.setString(4, dt.getShowDataTable_LicenceType()); pstmt.setString(5,
	 * dt.getGtinno()); rs = pstmt.executeQuery(); //
	 * System.out.println("excecute query fl3a"
	 * +action.getDistillery_id()+"jhhjhjhj"+new //
	 * java.sql.Date(dt.getShowDataTable_Date
	 * ())+"  licenc"+dt.getLicenceNo()+"licence type"
	 * +dt.getShowDataTable_LicenceType());
	 * 
	 * }
	 * 
	 * 
	 * String relativePath=Constants.JBOSS_SERVER_PATH+Constants.JBOSS_LINX_PATH
	 * ; FileOutputStream fileOut = new
	 * FileOutputStream(relativePath+"//ExciseUp//Excel//"
	 * +dt.getShowDataTable_LicenceType ()+""+date+""+action.getDistillery_id
	 * ()+""+dt.getLicenceNo()+""+".xls");
	 * 
	 * 
	 * XSSFWorkbook workbook = new XSSFWorkbook(); XSSFSheet worksheet =
	 * workbook.createSheet("Barcode Report"); // HSSFWorkbook workbook = new
	 * HSSFWorkbook(); // HSSFSheet worksheet =
	 * workbook.createSheet("Barcode Report");
	 * 
	 * CellStyle unlockedCellStyle = workbook.createCellStyle();
	 * unlockedCellStyle.setLocked(false);
	 * 
	 * // Sheet sheet = workbook.createSheet();
	 * worksheet.protectSheet("agristat"); worksheet.setColumnWidth(0, 3000);
	 * worksheet.setColumnWidth(1, 8000); worksheet.setColumnWidth(2, 8000);
	 * worksheet.setColumnWidth(3, 8000); worksheet.setColumnWidth(4, 6000);
	 * 
	 * worksheet.setColumnWidth(4, 3000); worksheet.setColumnWidth(5, 6000);
	 * worksheet.setColumnWidth(6, 3000); worksheet.setColumnWidth(7, 6000);
	 * worksheet.setColumnWidth(8, 3000); worksheet.setColumnWidth(9, 6000);
	 * 
	 * 
	 * XSSFRow rowhead0 = worksheet.createRow((int) 0); // HSSFRow rowhead0 =
	 * worksheet.createRow((int) 0); // HSSFCell cellhead0 =
	 * rowhead0.createCell((int) 0); XSSFCell cellhead0 =
	 * rowhead0.createCell((int) 0); cellhead0.setCellValue("Barcode Report");
	 * 
	 * rowhead0.setHeight((short) 700);
	 * cellhead0.setCellStyle(unlockedCellStyle); XSSFCellStyle cellStyl =
	 * workbook.createCellStyle(); // HSSFCellStyle cellStyl =
	 * workbook.createCellStyle();
	 * 
	 * cellStyl = workbook.createCellStyle(); XSSFFont hSSFFont =
	 * workbook.createFont(); hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
	 * hSSFFont.setFontHeightInPoints((short) 12);
	 * hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	 * hSSFFont.setColor(HSSFColor.GREEN.index); cellStyl.setFont(hSSFFont);
	 * cellhead0.setCellStyle(cellStyl);
	 * 
	 * // HSSFCellStyle cellStyle = workbook.createCellStyle(); XSSFCellStyle
	 * cellStyle = workbook.createCellStyle();
	 * cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
	 * cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	 * 
	 * // -----------------------------
	 * 
	 * XSSFCellStyle unlockcellStyle = workbook.createCellStyle();
	 * unlockcellStyle.setLocked(false);
	 * 
	 * // ---------------------------- XSSFRow rowhead =
	 * worksheet.createRow((int) 1);
	 * 
	 * XSSFCell cellhead1 = rowhead.createCell((int) 0);
	 * cellhead1.setCellValue("S.No.");
	 * 
	 * cellhead1.setCellStyle(cellStyle);
	 * 
	 * XSSFCell cellhead2 = rowhead.createCell((int) 1);
	 * cellhead2.setCellValue("Gtin"); cellhead2.setCellStyle(cellStyle);
	 * 
	 * XSSFCell cellhead3 = rowhead.createCell((int) 2);
	 * cellhead3.setCellValue("FinalizeDate");
	 * cellhead3.setCellStyle(cellStyle);
	 * 
	 * XSSFCell cellhead4 = rowhead.createCell((int) 3);
	 * cellhead4.setCellValue("Case Etn"); cellhead4.setCellStyle(cellStyle);
	 * 
	 * XSSFCell cellhead5 = rowhead.createCell((int) 4);
	 * cellhead5.setCellValue("BottleBarcode");
	 * cellhead5.setCellStyle(cellStyle);
	 * 
	 * HSSFCell cellhead6 = rowhead.createCell((int) 5);
	 * cellhead6.setCellValue("Category"); cellhead6.setCellStyle(cellStyle);
	 * 
	 * 
	 * HSSFCell cellhead7 = rowhead.createCell((int) 6);
	 * cellhead7.setCellValue("Sub-Category");
	 * cellhead7.setCellStyle(cellStyle);
	 * 
	 * 
	 * HSSFCell cellhead8 = rowhead.createCell((int) 7);
	 * cellhead8.setCellValue("Property No.");
	 * cellhead8.setCellStyle(cellStyle);
	 * 
	 * HSSFCell cellhead9 = rowhead.createCell((int) 8);
	 * cellhead9.setCellValue("Reg.No."); cellhead9.setCellStyle(cellStyle);
	 * 
	 * 
	 * HSSFCell cellhead10 = rowhead.createCell((int) 9);
	 * cellhead10.setCellValue("Name"); cellhead10.setCellStyle(cellStyle);
	 * 
	 * HSSFCell cellhead11 = rowhead.createCell((int) 10);
	 * cellhead11.setCellValue("Status"); cellhead11.setCellStyle(cellStyle);
	 * 
	 * 
	 * int i = 0; while (rs.next()) {
	 * 
	 * // System.out.println("i==========="+i++); Date dat =
	 * Utility.convertSqlDateToUtilDate(rs .getDate("dispatch_date"));
	 * 
	 * DateFormat formatter;
	 * 
	 * formatter = new SimpleDateFormat("yyMMdd"); date = formatter.format(dat);
	 * 
	 * String lic = dt.getLicencenoo().replaceAll("/", "");
	 * 
	 * // System.out.println("while in");serial_no_start, serial_no_end start =
	 * rs.getLong("serial_no_start"); end = rs.getLong("serial_no_end");
	 * 
	 * k++; XSSFRow row1 = worksheet.createRow((int) k);
	 * 
	 * XSSFCell cellA1 = row1.createCell((int) 0); cellA1.setCellValue(k);
	 * 
	 * XSSFCell cellB1 = row1.createCell((int) 1);
	 * cellB1.setCellValue(rs.getString("gtin_no"));
	 * 
	 * XSSFCell cellC1 = row1.createCell((int) 2); cellC1.setCellValue(date); //
	 * cellC1.setCellStyle(unlockcellStyle);
	 * 
	 * XSSFCell cellD1 = row1.createCell((int) 3); if
	 * (dt.getShowDataTable_Quntity().equals("750")) { noOfUnit = "12"; } else
	 * if (dt.getShowDataTable_Quntity().equals("375")) { noOfUnit = "24"; }
	 * else if (dt.getShowDataTable_Quntity().equals("180")) { noOfUnit = "48";
	 * } else if (dt.getShowDataTable_Quntity().equals("60")) { noOfUnit = "92";
	 * } else if (dt.getShowDataTable_Quntity().equals("200")) { noOfUnit =
	 * "45"; } cellD1.setCellValue("01" + rs.getString("gtin_no") + "13" + date
	 * + "37" + noOfUnit + "21" + rs.getString("case_no"));
	 * 
	 * XSSFCell cellE1 = row1.createCell((int) 4); cellE1.setCellValue("01" +
	 * rs.getString("gtin_no") + "13" + date + "21" +
	 * rs.getString("GENERATE_SERIES"));
	 * 
	 * } fileOut = new FileOutputStream(relativePath + "//ExciseUp//Excel//" +
	 * dt.getGtinno() + "" + date + ".xls");
	 * 
	 * XSSFRow row1 = worksheet.createRow((int) k + 1);
	 * 
	 * // APoolFinancialReportDataTable dt=(APoolFinancialReportDataTable) //
	 * list.get(i-2);
	 * 
	 * XSSFCell cellA1 = row1.createCell((int) 0); cellA1.setCellValue("End");
	 * 
	 * workbook.write(fileOut); fileOut.flush(); fileOut.close(); flag = true;
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.out.println("xls2" + e.getMessage()); e.printStackTrace(); }
	 * 
	 * return flag; }
	 */
	public synchronized long getcaseNo() {
		String sql = " select     nextval('public.bwfl_manual_case_code')";

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		long serialNo = 0;
		long currseq = 0;

		try {
			conn = ConnectionToDataBase.getConnection3();

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				serialNo = rs.getLong(1);
				if (serialNo == 0) {
					serialNo = serialNo;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();

				if (pstmt != null)
					pstmt.close();

				if (conn != null)
					conn.close();

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			}
		}

		return serialNo;
	}

	/*
	 * public void dataFinalize(BWFLPlanAction action, BWFLPlanDataTable dt)
	 * { Connection conn = null; Connection conn1 = null; PreparedStatement
	 * pstmt1 = null; PreparedStatement pstmt2 = null; PreparedStatement pstmt3
	 * = null; PreparedStatement pstmt4 = null; String gtinNo = ""; long boxsize
	 * = 0; long caseno = 0; String sql = "INSERT INTO public.bottling_fl3(  " +
	 * "distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type,case_no) "
	 * + "	VALUES (?, ?, ?, ?, ?, ?, ?,?)";
	 * 
	 * int status = 0;
	 * 
	 * String fl3a = "INSERT INTO public.bottling_fl3a(  " +
	 * "distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type,case_no) "
	 * + "	VALUES (?, ?, ?, ?, ?, ?, ?,?)";
	 * 
	 * String cl = "INSERT INTO public.bottling_cl(  " +
	 * "distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type,case_no) "
	 * + "	VALUES (?, ?, ?, ?, ?, ?, ?,?)";
	 * 
	 * String update = "UPDATE distillery.mst_bottling_plan " +
	 * " SET   finalized_flag='F' ,finalized_date=? " +
	 * "WHERE int_distillery_id=? and int_brand_id=? and int_pack_id=? and plan_dt=?"
	 * ;
	 * 
	 * try { gtinNo = getBrandPackagingGtinNo(action, dt); //
	 * boxsize=getBrandPackagingNoOfBottle(action,dt);
	 * 
	 * // long noOfBottle= //
	 * (Long.parseLong(dt.getShowDataTable_PlanNoOfBottling())/boxsize);
	 * 
	 * System.out
	 * .println("new Double(dt.getShowDataTable_PlanNoOfBottling()).longValue()"
	 * + new Double(dt.getShowDataTable_PlanNoOfBottling()) .longValue()); long
	 * serialno = getSerialNo(new Double(
	 * dt.getShowDataTable_PlanNoOfBottling()).longValue());
	 * 
	 * // long //
	 * i=(Long.parseLong(dt.getShowDataTable_PlanNoOfBottling())/(Long
	 * .parseLong(dt.getShowDataTable_NoOfBoxes())));
	 * System.out.println("gtinNo" + gtinNo + "seqqqqqqqqqqqqqqqqqqqqqqqq" +
	 * serialno); if (!gtinNo.equals("") && serialno != 0) { conn =
	 * ConnectionToDataBase.getConnection3(); conn1 =
	 * ConnectionToDataBase.getConnection(); conn.setAutoCommit(false);
	 * conn1.setAutoCommit(false); pstmt1 = conn1.prepareStatement(update);
	 * 
	 * pstmt1.setDate(1, new java.sql.Date(System.currentTimeMillis()));
	 * pstmt1.setInt(2, action.getDistillery_id()); pstmt1.setInt(3,
	 * dt.getBrandId()); pstmt1.setInt(4, dt.getPackagingId());
	 * pstmt1.setDate(5, Utility.convertUtilDateToSQLDate(dt
	 * .getShowDataTable_Date())); status = pstmt1.executeUpdate();
	 * 
	 * if (dt.getShowDataTable_LicenceType().equals("FL3") && status > 0) {
	 * status = 0; pstmt1 = conn.prepareStatement(sql); for (int i = 0; i <
	 * Long.parseLong(dt .getShowDataTable_NoOfBoxes()); i++) { caseno =
	 * getcaseNo();
	 * 
	 * pstmt1.setInt(1, action.getDistillery_id());
	 * 
	 * pstmt1.setDate(2, Utility.convertUtilDateToSQLDate(dt
	 * .getShowDataTable_Date())); pstmt1.setString(3, gtinNo);
	 * pstmt1.setLong(4, serialno);
	 * 
	 * pstmt1.setString(6, dt.getLicenceNo()); pstmt1.setString(7,
	 * dt.getShowDataTable_LicenceType()); pstmt1.setLong(8, caseno); if
	 * (dt.getShowDataTable_Quntity().equals("750")) { pstmt1.setLong(5,
	 * serialno + 11);
	 * 
	 * serialno += 12; } else if (dt.getShowDataTable_Quntity().equals("375")) {
	 * pstmt1.setLong(5, serialno + 23); serialno += 24; } else if
	 * (dt.getShowDataTable_Quntity().equals("180")) { pstmt1.setLong(5,
	 * serialno + 47); serialno += 48; } else if
	 * (dt.getShowDataTable_Quntity().equals("60")) { pstmt1.setLong(5, serialno
	 * + 95); serialno += 96; } pstmt1.addBatch(); //
	 * status=pstmt1.executeUpdate(); } } if
	 * (dt.getShowDataTable_LicenceType().equals("FL3A") && status > 0) { status
	 * = 0; pstmt1 = conn.prepareStatement(fl3a); for (int i = 0; i <
	 * Long.parseLong(dt .getShowDataTable_NoOfBoxes()); i++) {
	 * 
	 * caseno = getcaseNo();
	 * 
	 * pstmt1.setInt(1, action.getDistillery_id());
	 * 
	 * pstmt1.setDate(2, Utility.convertUtilDateToSQLDate(dt
	 * .getShowDataTable_Date())); pstmt1.setString(3, gtinNo);
	 * pstmt1.setLong(4, serialno);
	 * 
	 * pstmt1.setString(6, dt.getLicenceNo()); pstmt1.setString(7,
	 * dt.getShowDataTable_LicenceType()); pstmt1.setLong(8, caseno); if
	 * (dt.getShowDataTable_Quntity().equals("750")) { pstmt1.setLong(5,
	 * serialno + 11);
	 * 
	 * serialno += 12; } else if (dt.getShowDataTable_Quntity().equals("375")) {
	 * pstmt1.setLong(5, serialno + 23); serialno += 24; } else if
	 * (dt.getShowDataTable_Quntity().equals("180")) { pstmt1.setLong(5,
	 * serialno + 47); serialno += 48; } else if
	 * (dt.getShowDataTable_Quntity().equals("60")) { pstmt1.setLong(5, serialno
	 * + 95); serialno += 96; } pstmt1.addBatch(); //
	 * status=pstmt1.executeUpdate();} } } if
	 * (dt.getShowDataTable_LicenceType().equals("CL") && status > 0) { status =
	 * 0; pstmt1 = conn.prepareStatement(cl); for (int i = 0; i <
	 * Long.parseLong(dt .getShowDataTable_NoOfBoxes()); i++) { caseno =
	 * getcaseNo();
	 * 
	 * pstmt1.setInt(1, action.getDistillery_id());
	 * 
	 * pstmt1.setDate(2, Utility.convertUtilDateToSQLDate(dt
	 * .getShowDataTable_Date())); pstmt1.setString(3, gtinNo);
	 * pstmt1.setLong(4, serialno);
	 * 
	 * pstmt1.setString(6, dt.getLicenceNo()); pstmt1.setString(7,
	 * dt.getShowDataTable_LicenceType()); pstmt1.setLong(8, caseno); if
	 * (dt.getShowDataTable_Quntity().equals("750")) { pstmt1.setLong(5,
	 * serialno + 11);
	 * 
	 * serialno += 12; } else if (dt.getShowDataTable_Quntity().equals("375")) {
	 * pstmt1.setLong(5, serialno + 23); serialno += 24; } else if
	 * (dt.getShowDataTable_Quntity().equals("180")) { pstmt1.setLong(5,
	 * serialno + 47); serialno += 48; } else if
	 * (dt.getShowDataTable_Quntity().equals("60")) { pstmt1.setLong(5, serialno
	 * + 95); serialno += 96; } else if
	 * (dt.getShowDataTable_Quntity().equals("200")) { pstmt1.setLong(5,
	 * serialno + 44); serialno += 96; } pstmt1.addBatch(); //
	 * status=pstmt1.executeUpdate(); } } int[] status1 = pstmt1.executeBatch();
	 * if (status1.length > 0) { status = 0; boolean flag = write(dt, action,
	 * conn);
	 * 
	 * if (flag) { status = 1; } else {
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("Excel Not Generated", "Excel Not Generated")); } } if
	 * (status > 0) {
	 * 
	 * conn.commit(); conn1.commit();
	 * 
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("Finalized SuccessFully", "Finalized SuccessFully")); } else
	 * { conn.rollback(); conn1.rollback();
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("Not Finalized ", " Not Finalized ")); }
	 * 
	 * } else { FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("Gtin and Serial No Can Not Zero ",
	 * " Gtin and Serial No Can Not Zero")); } }
	 * 
	 * catch (Exception e) {
	 * 
	 * FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage(e.getMessage(), e.getMessage())); try { conn.rollback();
	 * conn1.rollback(); } catch (Exception e1) { e1.printStackTrace(); }
	 * 
	 * e.printStackTrace(); } finally {
	 * 
	 * try {
	 * 
	 * if (pstmt1 != null) pstmt1.close(); if (pstmt2 != null) pstmt2.close();
	 * if (pstmt3 != null) pstmt3.close(); if (pstmt4 != null) pstmt4.close();
	 * if (conn != null) conn.close(); if (conn1 != null) conn1.close();
	 * 
	 * } catch (Exception e) {
	 * FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage(e.getMessage(), e.getMessage())); e.printStackTrace(); } } }
	 */
	/*
	 * public void generateReport(BottlingOfPlanDataTable dt,
	 * BottlingOfPlanAction action) { Connection conn = null; PreparedStatement
	 * pstmt = null; ResultSet rs = null; String relativePath =
	 * Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH + "//ExciseUp//";
	 * JasperPrint print = null;
	 * 
	 * String FL3 = "" +
	 * " select to_char(GENERATE_SERIES, 'fm000000000000') as GENERATE_SERIES,distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type,  "
	 * + " to_char(case_no , 'fm0000000000')as case_no from " +
	 * 
	 * "(SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no,b.*   "
	 * + "FROM public.bottling_fl3 a, (select * from GENERATE_SERIES(?,?)) b   "
	 * +
	 * "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?  )x"
	 * ;
	 * 
	 * String FL3A =
	 * " select to_char(GENERATE_SERIES, 'fm000000000000') as GENERATE_SERIES,distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type,  "
	 * + " to_char(case_no , 'fm0000000000')as case_no from " +
	 * 
	 * "(SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no,b.*   "
	 * +
	 * "FROM public.bottling_fl3a a, (select * from GENERATE_SERIES(?,?)) b   "
	 * +
	 * "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?)x"
	 * ;
	 * 
	 * String cl =
	 * " select to_char(GENERATE_SERIES, 'fm000000000000') as GENERATE_SERIES,distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type,  "
	 * + " to_char(case_no , 'fm0000000000')as case_no from " +
	 * 
	 * "(SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no,b.*   "
	 * + "FROM public.bottling_cl a, (select * from GENERATE_SERIES(?,?)) b   "
	 * +
	 * "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?)x"
	 * ;
	 * 
	 * try { String minmax = getMinMaxSerialNo(dt, action);
	 * 
	 * String ar[] = minmax.split(","); conn =
	 * ConnectionToDataBase.getConnection3(); String noOfUnit = "";
	 * 
	 * if (dt.getShowDataTable_LicenceType().equals("FL3")) { pstmt =
	 * conn.prepareStatement(FL3); pstmt.setLong(1, Long.parseLong(ar[0]));
	 * pstmt.setLong(2, Long.parseLong(ar[1])); pstmt.setInt(3,
	 * action.getDistillery_id()); pstmt.setDate(4,
	 * Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()));
	 * pstmt.setString(5, dt.getLicenceNo()); pstmt.setString(6,
	 * dt.getShowDataTable_LicenceType()); pstmt.setLong(7,
	 * Long.parseLong(dt.getGtinno()));
	 * 
	 * rs = pstmt.executeQuery(); if (rs.next()) {
	 * 
	 * HashMap map = new HashMap(); map.put("disname",
	 * action.getDistillery_nm()); map.put("dislryadd",
	 * action.getDistillery_adrs()); map.put("quantity",
	 * dt.getShowDataTable_Quntity()); map.put("brandName",
	 * dt.getShowDataTable_Brand()); Date dat =
	 * Utility.convertSqlDateToUtilDate(rs .getDate("dispatch_date"));
	 * System.out.println("date finalize" + dat); DateFormat formatter;
	 * 
	 * formatter = new SimpleDateFormat("yyMMdd"); String date =
	 * formatter.format(dat); System.out.println(date); map.put("date", date);
	 * 
	 * if (dt.getShowDataTable_Quntity().equals("750")) { noOfUnit = "12"; }
	 * else if (dt.getShowDataTable_Quntity().equals("375")) { noOfUnit = "24";
	 * } else if (dt.getShowDataTable_Quntity().equals("180")) { noOfUnit =
	 * "48"; } else if (dt.getShowDataTable_Quntity().equals("60")) { noOfUnit =
	 * "12"; } map.put("barcode", "01" + rs.getLong("gtin_no") + "13" + date +
	 * "37" + noOfUnit + "21" + rs.getString("case_no")); map.put("barcodetext",
	 * "(01)" + rs.getLong("gtin_no") + "(13)" + date + "(37)" + noOfUnit +
	 * "(21)" + rs.getString("case_no")); rs = pstmt.executeQuery();
	 * 
	 * JRResultSetDataSource jrds = new JRResultSetDataSource(rs); print =
	 * JasperFillManager.fillReport(relativePath + "bottlingjasper" +
	 * File.separator + "BarcodeFl3.jasper", map, jrds);
	 * JasperExportManager.exportReportToPdfFile( print, relativePath +
	 * "bottling_pdf" + File.separator + "Barcode" + dt.getGtinno() +
	 * dt.getFinalizedDateString() + ".pdf");
	 * 
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("Print Report Successfully", "Print Report Successfully"));
	 * 
	 * } else {
	 * 
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("No Record Found", "No Record Found")); }
	 * 
	 * } if (dt.getShowDataTable_LicenceType().equals("CL")) { pstmt =
	 * conn.prepareStatement(cl); pstmt.setLong(1, Long.parseLong(ar[0]));
	 * pstmt.setLong(2, Long.parseLong(ar[1])); pstmt.setInt(3,
	 * action.getDistillery_id()); pstmt.setDate(4,
	 * Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()));
	 * pstmt.setString(5, dt.getLicenceNo()); pstmt.setString(6,
	 * dt.getShowDataTable_LicenceType()); pstmt.setLong(7,
	 * Long.parseLong(dt.getGtinno()));
	 * 
	 * rs = pstmt.executeQuery(); if (rs.next()) {
	 * 
	 * HashMap map = new HashMap(); map.put("disname",
	 * action.getDistillery_nm()); map.put("dislryadd",
	 * action.getDistillery_adrs()); map.put("quantity",
	 * dt.getShowDataTable_Quntity()); map.put("brandName",
	 * dt.getShowDataTable_Brand()); Date dat =
	 * Utility.convertSqlDateToUtilDate(rs .getDate("dispatch_date"));
	 * System.out.println("date finalize" + dat); DateFormat formatter;
	 * 
	 * formatter = new SimpleDateFormat("yyMMdd"); String date =
	 * formatter.format(dat); System.out.println(date); map.put("date", date);
	 * if (dt.getShowDataTable_Quntity().equals("750")) { noOfUnit = "12"; }
	 * else if (dt.getShowDataTable_Quntity().equals("375")) { noOfUnit = "24";
	 * } else if (dt.getShowDataTable_Quntity().equals("180")) { noOfUnit =
	 * "48"; } else if (dt.getShowDataTable_Quntity().equals("60")) { noOfUnit =
	 * "92"; } else if (dt.getShowDataTable_Quntity().equals("200")) { noOfUnit
	 * = "45"; } map.put("barcode", "01" + rs.getLong("gtin_no") + "13" + date +
	 * "37" + noOfUnit + "21" + rs.getString("case_no")); map.put("barcodetext",
	 * "(01)" + rs.getLong("gtin_no") + "(13)" + date + "(37)" + noOfUnit +
	 * "(21)" + rs.getString("case_no")); rs = pstmt.executeQuery();
	 * 
	 * JRResultSetDataSource jrds = new JRResultSetDataSource(rs); print =
	 * JasperFillManager.fillReport(relativePath + "bottlingjasper" +
	 * File.separator + "BarcodeCL.jasper", map, jrds);
	 * JasperExportManager.exportReportToPdfFile( print, relativePath +
	 * "bottling_pdf" + File.separator + "Barcode" + dt.getGtinno() +
	 * dt.getFinalizedDateString() + ".pdf");
	 * 
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("Print Report Successfully", "Print Report Successfully"));
	 * 
	 * } else {
	 * 
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("No Record Found", "No Record Found")); } } if
	 * (dt.getShowDataTable_LicenceType().equals("FL3A")) {
	 * 
	 * System.out.println("Fl3 a Fl3Acome in"); pstmt =
	 * conn.prepareStatement(FL3A); pstmt.setLong(1, Long.parseLong(ar[0]));
	 * pstmt.setLong(2, Long.parseLong(ar[1])); pstmt.setInt(3,
	 * action.getDistillery_id()); pstmt.setDate(4,
	 * Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()));
	 * pstmt.setString(5, dt.getLicenceNo()); pstmt.setString(6,
	 * dt.getShowDataTable_LicenceType()); pstmt.setLong(7,
	 * Long.parseLong(dt.getGtinno()));
	 * 
	 * rs = pstmt.executeQuery(); if (rs.next()) {
	 * 
	 * System.out.println("Fl3 a Fl3Acome in nextt"); HashMap map = new
	 * HashMap(); map.put("disname", action.getDistillery_nm());
	 * map.put("dislryadd", action.getDistillery_adrs()); map.put("quantity",
	 * dt.getShowDataTable_Quntity()); map.put("brandName",
	 * dt.getShowDataTable_Brand()); Date dat =
	 * Utility.convertSqlDateToUtilDate(rs .getDate("dispatch_date"));
	 * System.out.println("date finalize" + dat); DateFormat formatter;
	 * 
	 * formatter = new SimpleDateFormat("yyMMdd"); String date =
	 * formatter.format(dat); System.out.println(date); map.put("date", date);
	 * if (dt.getShowDataTable_Quntity().equals("750")) { noOfUnit = "12"; }
	 * else if (dt.getShowDataTable_Quntity().equals("375")) { noOfUnit = "24";
	 * } else if (dt.getShowDataTable_Quntity().equals("180")) { noOfUnit =
	 * "48"; } else if (dt.getShowDataTable_Quntity().equals("60")) { noOfUnit =
	 * "92"; } else if (dt.getShowDataTable_Quntity().equals("200")) { noOfUnit
	 * = "45"; } map.put("barcode", "01" + rs.getLong("gtin_no") + "13" + date +
	 * "37" + noOfUnit + "21" + rs.getString("case_no")); map.put("barcodetext",
	 * "(01)" + rs.getLong("gtin_no") + "(13)" + date + "(37)" + noOfUnit +
	 * "(21)" + rs.getString("case_no")); rs = pstmt.executeQuery();
	 * 
	 * JRResultSetDataSource jrds = new JRResultSetDataSource(rs); print =
	 * JasperFillManager.fillReport(relativePath + "bottlingjasper" +
	 * File.separator + "BarcodeFl3A.jasper", map, jrds);
	 * JasperExportManager.exportReportToPdfFile( print, relativePath +
	 * "bottling_pdf" + File.separator + "Barcode" + dt.getGtinno() +
	 * dt.getFinalizedDateString() + ".pdf");
	 * 
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("Print Report Successfully", "Print Report Successfully"));
	 * 
	 * } else {
	 * 
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("No Record Found", "No Record Found")); }
	 * 
	 * }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	public String getMinMaxSerialNo(BWFLPlanDataTable dt,
			BWFLPlanAction action) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String fl3 = "SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no   "
				+ "FROM public.bottling_fl3 a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  ";

		String fl3a = "SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no   "
				+ "FROM public.bottling_fl3a a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  ";

		String cl = "SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no   "
				+ "FROM public.bottling_cl a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  ";

		String minMax = "";

		try {
			conn = ConnectionToDataBase.getConnection3();

			if (dt.getShowDataTable_LicenceType().equals("FL3")) {

				/*System.out.println("fl3 min max"+ action.getDistillery_id()+ " date"+ 
				Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()) + " licenceno"
						+ dt.getLicenceNo() + " licence type"
						+ dt.getShowDataTable_LicenceType());*/
				pstmt = conn.prepareStatement(fl3);
				pstmt.setInt(1, Integer.parseInt(action.getDistillery_id()));
				pstmt.setDate(2,
						Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = rs.getString("serial_no_start") + ","
							+ rs.getString("serial_no_end");
					//System.out.println("minMax" + minMax);
				}
			}

			if (dt.getShowDataTable_LicenceType().equals("FL3A")) {

				/*System.out.println("fl3a min max"
						+ action.getDistillery_id()
						+ " date"
						+ Utility.convertUtilDateToSQLDate(dt
								.getFinalize_Date()) + " licenceno"
						+ dt.getLicenceNo() + " licence type"
						+ dt.getShowDataTable_LicenceType());*/
				pstmt = conn.prepareStatement(fl3a);
				pstmt.setInt(1, Integer.parseInt(action.getDistillery_id()));
				pstmt.setDate(2,
						Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = rs.getString("serial_no_start") + ","
							+ rs.getString("serial_no_end");
					//System.out.println("minMax" + minMax);
				}
			}
			if (dt.getShowDataTable_LicenceType().equals("CL")) {
				/*System.out.println("cl min max"
						+ action.getDistillery_id()
						+ " date"
						+ Utility.convertUtilDateToSQLDate(dt
								.getFinalize_Date()) + " licenceno"
						+ dt.getLicenceNo() + " licence type"
						+ dt.getShowDataTable_LicenceType());*/
				pstmt = conn.prepareStatement(cl);
				pstmt.setInt(1, Integer.parseInt(action.getDistillery_id()));
				pstmt.setDate(2,
						Utility.convertUtilDateToSQLDate(dt.getFinalize_Date()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = rs.getString("serial_no_start") + ","
							+ rs.getString("serial_no_end");
					//System.out.println("minMax" + minMax);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (conn != null)
					conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		return minMax;
	}

	public String getMinMaxSerialNoForExcel(BWFLPlanDataTable dt,
			BWFLPlanAction action, Connection conn) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String fl3 = "SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no   "
				+ "FROM public.bottling_fl3 a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?";
		String fl3mx = "SELECT   serial_no_end, licence_no   "
				+ "FROM public.bottling_fl3 a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?";

		String fl3a = "SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no   "
				+ "FROM public.bottling_fl3a a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=? and gtin_no=? ";
		String fl3amx = "SELECT   serial_no_end    "
				+ "FROM public.bottling_fl3a a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=? and gtin_no=? ";

		String cl = "SELECT distillery_id, dispatch_date, gtin_no, serial_no_start, serial_no_end, licence_no, licence_type, case_no   "
				+ "FROM public.bottling_cl a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?";

		String clmx = "SELECT   serial_no_end   "
				+ "FROM public.bottling_cl a "
				+ "where distillery_id=?  and dispatch_date=? and licence_no=? and licence_type=?  and gtin_no=?";

		String minMax = "";

		try {

			if (dt.getShowDataTable_LicenceType().equals("FL3")) {

				/*System.out.println("fl3 min max"
						+ action.getDistillery_id()
						+ " date"
						+ Utility.convertUtilDateToSQLDate(dt
								.getFinalize_Date()) + " licenceno"
						+ dt.getLicenceNo() + " licence type"
						+ dt.getShowDataTable_LicenceType() + dt.getGtinno());*/
				pstmt = conn.prepareStatement(fl3);
				pstmt.setString(1, action.getDistillery_id());
				pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				pstmt.setLong(5, Long.parseLong(dt.getGtinno()));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = rs.getString("serial_no_start");
					//System.out.println("minMax" + minMax);
				}
				pstmt = conn.prepareStatement(fl3mx);
				pstmt.setInt(1, Integer.parseInt(action.getDistillery_id()));
				pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				pstmt.setLong(5, Long.parseLong(dt.getGtinno()));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = minMax + "," + rs.getString("serial_no_end");
					//System.out.println("minMax" + minMax);
				}
			}

			if (dt.getShowDataTable_LicenceType().equals("FL3A")) {

				/*System.out.println("fl3a min max"
						+ action.getDistillery_id()
						+ " date"
						+ Utility.convertUtilDateToSQLDate(dt
								.getFinalize_Date()) + " licenceno"
						+ dt.getLicenceNo() + " licence type"
						+ dt.getShowDataTable_LicenceType() + ""
						+ dt.getGtinno());*/
				pstmt = conn.prepareStatement(fl3a);
				pstmt.setInt(1, Integer.parseInt(action.getDistillery_id()));
				pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				pstmt.setLong(5, Long.parseLong(dt.getGtinno()));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = rs.getString("serial_no_start");
					//System.out.println("minMax" + minMax);
				}
				pstmt = conn.prepareStatement(fl3amx);
				pstmt.setInt(1, Integer.parseInt(action.getDistillery_id()));
				pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				pstmt.setLong(5, Long.parseLong(dt.getGtinno()));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = minMax + "," + rs.getString("serial_no_end");
					//System.out.println("minMax" + minMax);
				}
			}
			if (dt.getShowDataTable_LicenceType().equals("CL")) {
				/*System.out.println("cl min max"
						+ action.getDistillery_id()
						+ " date"
						+ Utility.convertUtilDateToSQLDate(dt
								.getFinalize_Date()) + " licenceno"
						+ dt.getLicenceNo() + " licence type"
						+ dt.getShowDataTable_LicenceType() + ""
						+ dt.getGtinno());*/
				pstmt = conn.prepareStatement(cl);
				pstmt.setInt(1, Integer.parseInt(action.getDistillery_id()));
				pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				pstmt.setLong(5, Long.parseLong(dt.getGtinno()));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = rs.getString("serial_no_start");
					//System.out.println("minMax" + minMax);
				}
				pstmt = conn.prepareStatement(clmx);
				pstmt.setInt(1, Integer.parseInt(action.getDistillery_id()));
				pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(3, dt.getLicenceNo());
				pstmt.setString(4, dt.getShowDataTable_LicenceType());
				pstmt.setLong(5, Long.parseLong(dt.getGtinno()));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					minMax = minMax + "," + rs.getString("serial_no_end");
					//System.out.println("minMax" + minMax);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return minMax;
	}

	/*
	 * public boolean checkData(BWFLPlanAction action) { boolean flag = false;
	 * Connection conn = null; PreparedStatement pstmt = null; ResultSet rs =
	 * null; int j = 0; String sql =
	 * 
	 * "  SELECT int_distillery_id, int_brand_id, int_pack_id, int_quantity, int_planned_bottles, "
	 * + "  int_boxes, int_liquor_type, vch_license_type, plan_dt, " +
	 * "  licence_no, cr_date, finalized_flag, finalized_date " +
	 * "  FROM distillery.mst_bottling_plan " +
	 * "   where int_distillery_id=? and int_brand_id=? and int_pack_id=? and plan_dt=? and permitno=?"
	 * ; try { conn = ConnectionToDataBase.getConnection();
	 * 
	 * if (action.getBrandPackagingDataList().size() > 0) {
	 * 
	 * pstmt = conn.prepareStatement(sql); for (int i = 0; i <
	 * action.getBrandPackagingDataList().size(); i++) { BWFLPlanDataTable
	 * table = (BWFLPlanDataTable) action .getBrandPackagingDataList().get(i);
	 * // pstmt.setInt(1,getMaxChallanIdDetail()+1); pstmt.setInt(1,
	 * action.getDistillery_id()); pstmt.setInt(2, Integer.parseInt(table
	 * .getBrandPackagingData_Brand())); pstmt.setInt(3, Integer.parseInt(table
	 * .getBrandPackagingData_Packaging())); pstmt.setDate(4,
	 * Utility.convertUtilDateToSQLDate(action .getDateOfBottling()));
	 * pstmt.setString(5, action.getPermitno()); rs = pstmt.executeQuery(); if
	 * (rs.next()) { flag = false;
	 * 
	 * } else { flag = true; }
	 * 
	 * }
	 * 
	 * } else {
	 * 
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage("Please Add One Row", "Please Add One Row")); } } catch
	 * (Exception e) {
	 * 
	 * FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage(e.getMessage(), e.getMessage())); } finally {
	 * 
	 * try { if (conn != null) conn.close(); } catch (Exception e) {
	 * e.printStackTrace(); } }
	 * 
	 * return flag; }
	 */
	public int maxIdgroup(int seq)
	{

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		
		String query = " SELECT distinct coalesce(group_id,0) as id FROM bwfl_license.mst_bottling_plan where coalesce(group_id,0)>0 and seq="+seq;
		int maxid = 0;
		try {
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			System.out.println("query=="+query);
			if (rs.next()) {
				maxid = rs.getInt("id");
			}else
			{
				 query = " SELECT max(group_id)+1 as id FROM bwfl_license.mst_bottling_plan ";
				 pstmt1 = con.prepareStatement(query);
					rs1 = pstmt1.executeQuery();
					System.out.println("query1=="+query);
					if (rs1.next()) {
						maxid = rs1.getInt("id");
					}
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
		}System.out.println("maxid=="+maxid);
		return maxid;

	
	}
	public void dataFinalize(BWFLPlanAction action, BWFLPlanDataTable dt) {
		Connection conn = null;
		Connection conn1 = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		String gtinNo = "";
		long boxsize = 0;
		long caseno = 0;
		int unmaped_status = 0;
		String bottlecode = "";int groupId = maxIdgroup(dt.getSeq());
		/*
		 * String sql= "INSERT INTO public.bottling_bwfl_import(  "+
		 * " dispatch_date, gtin_no, serial_no_start, serial_no_end, case_no,seq) "
		 * + "	VALUES (?, ?, ?, ?, ?,?)";
		 */

		String sql = " INSERT INTO bottling_unmapped.bwfl(  "
				+ " date_plan, etin, serial_no_start, serial_no_end, casecode, plan_id,  bottle_code, "
				+ " fl11gatepass, fl11_date, fl36gatepass, fl36_date)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
		int status = 0;

		/*
		 * String unmapped_sql=
		 * 
		 * 
		 * 
		 * "INSERT INTO bottling_unmapped.bwfl( "+
		 * "plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass, fl11_date, fl36gatepass, fl36_date) "
		 * + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 */
		
		String update = " UPDATE bwfl_license.mst_bottling_plan "
				+ " SET   finalized_flag='F' ,finalized_date=? , group_id="+groupId+" "
				+ " WHERE int_distillery_id=? and int_brand_id=? and int_pack_id=? and plan_dt=? and permitno=? and seq=? ";

		try {
			gtinNo = getBrandPackagingGtinNo(action, dt);

			long serialno = getSerialNo(new Double(
					dt.getShowDataTable_PlanNoOfBottling()).longValue());
			
			if (!gtinNo.equals("") && serialno != 0) {
				conn = ConnectionToDataBase.getConnection3();
				conn1 = ConnectionToDataBase.getConnection();
				conn.setAutoCommit(false);
				conn1.setAutoCommit(false);
				pstmt1 = conn1.prepareStatement(update);

				pstmt1.setDate(1, new java.sql.Date(System.currentTimeMillis()));
				pstmt1.setInt(2, Integer.parseInt(action.getDistillery_id()));
				pstmt1.setInt(3, dt.getBrandId());
				pstmt1.setInt(4, dt.getPackagingId());
				pstmt1.setDate(5, Utility.convertUtilDateToSQLDate(dt.getShowDataTable_Date()));
				pstmt1.setString(6, dt.getPermitnoD());
				pstmt1.setInt(7, dt.getSeq());

				status = pstmt1.executeUpdate();
				// System.out.println("statussssssssssssssss"+status);

				if (status > 0) {
					status = 0;
					for (int i = 0; i < Long.parseLong(dt
							.getShowDataTable_NoOfBoxes()); i++) {
						caseno = getcaseNo();
						pstmt2 = conn.prepareStatement(sql);
						// pstmt3=conn.prepareStatement(unmapped_sql);

						pstmt2.setDate(1, Utility.convertUtilDateToSQLDate(dt
								.getShowDataTable_Date()));
						pstmt2.setString(2, gtinNo);
						pstmt2.setLong(3, serialno);

						bottlecode = "";
						pstmt2.setString(5, String.valueOf(caseno));
						pstmt2.setInt(6, groupId);
						pstmt2.setString(7, "NA");
						pstmt2.setString(8, "NA");
						pstmt2.setDate(9, null);
						pstmt2.setString(10, "NA");
						pstmt2.setDate(11, null);
						if (dt.getShowDataTable_Quntity().equals("650")) {
							pstmt2.setLong(4, serialno + 11);
							/*
							 * for(long n=serialno;n<=serialno+12;serialno++) {
							 * bottlecode=
							 * bottlecode+(String.valueOf(serialno))+"|"; }
							 */
							serialno += 12;

						} else if (dt.getShowDataTable_Quntity().equals("330")) {
							pstmt2.setLong(4, serialno + 23);
							/*
							 * for(long n=serialno;n<=serialno+24;serialno++) {
							 * bottlecode=
							 * bottlecode+(String.valueOf(serialno))+"|"; }
							 */
							serialno += 24;
						} else if (dt.getShowDataTable_Quntity().equals("500")) {
							pstmt2.setLong(4, serialno + 23);

							/*
							 * for(long n=serialno;n<=serialno+24;serialno++) {
							 * bottlecode=
							 * bottlecode+(String.valueOf(serialno))+"|"; }
							 */
							serialno += 24;
						} else if (dt.getShowDataTable_Quntity().equals("750")) {
							pstmt2.setLong(4, serialno + 11);
							/*
							 * for(long n=serialno;n<=serialno+12;serialno++) {
							 * bottlecode=
							 * bottlecode+(String.valueOf(serialno))+"|"; }
							 */
							serialno += 12;
						}

						else if (dt.getShowDataTable_Quntity().equals("2000")) {
							pstmt2.setLong(4, serialno + 3);
							/*
							 * for(long n=serialno;n<=serialno+4;serialno++) {
							 * bottlecode=
							 * bottlecode+(String.valueOf(serialno))+"|"; }
							 */
							serialno += 4;
						} else if (dt.getShowDataTable_Quntity().equals("1000")) {
							pstmt2.setLong(4, serialno + 8);
							/*
							 * for(long n=serialno;n<=serialno+9;serialno++) {
							 * bottlecode=
							 * bottlecode+(String.valueOf(serialno))+"|"; }
							 */
							serialno += 9;
						} else if (dt.getShowDataTable_Quntity().equals("375")) {
							pstmt2.setLong(4, serialno + 23);
							/*
							 * for(long n=serialno;n<=serialno+24;serialno++) {
							 * bottlecode=
							 * bottlecode+(String.valueOf(serialno))+"|"; }
							 */
							serialno += 24;
						} else if (dt.getShowDataTable_Quntity().equals("180")) {
							pstmt2.setLong(4, serialno + 47);
							/*
							 * for(long n=serialno;n<=serialno+48;serialno++) {
							 * bottlecode=
							 * bottlecode+(String.valueOf(serialno))+"|"; }
							 */
							serialno += 48;
						} else if (dt.getShowDataTable_Quntity().equals("90")) {
							pstmt2.setLong(4, serialno + 95);
							/*
							 * for(long n=serialno;n<=serialno+96;serialno++) {
							 * bottlecode=
							 * bottlecode+(String.valueOf(serialno))+"|"; }
							 */
							serialno += 96;
						} else if (dt.getShowDataTable_Quntity().equals("60")) {
							pstmt2.setLong(4, serialno + 149);
							/*
							 * for(long n=serialno;n<=serialno+150;serialno++) {
							 * bottlecode=
							 * bottlecode+(String.valueOf(serialno))+"|"; }
							 */
							serialno += 150;
						} else if (dt.getShowDataTable_Quntity().equals("200")) {
							pstmt2.setLong(4, serialno + 44);
							/*
							 * for(long n=serialno;n<=serialno+200;serialno++) {
							 * bottlecode=
							 * bottlecode+(String.valueOf(serialno))+"|"; }
							 */
							serialno += 45;
						} else if (dt.getShowDataTable_Quntity().equals("275")) {
							/*
							 * for(long n=serialno;n<=serialno+275;serialno++) {
							 * bottlecode=
							 * bottlecode+(String.valueOf(serialno))+"|"; }
							 */
							pstmt2.setLong(4, serialno + 23);
							serialno += 24;
						}
						// pstmt2.setLong(4, serialno+(dt.getNewsize()-1));
						// serialno+=dt.getNewsize();
						else {
							pstmt2.setLong(
									4,
									serialno
											+ (Integer.parseInt(dt
													.getShowDataTable_PlanNoOfBottling()) / Integer.parseInt(dt
													.getShowDataTable_NoOfBoxes()))
											- 1);
							serialno += (Integer.parseInt(dt
									.getShowDataTable_PlanNoOfBottling()) / Integer
									.parseInt(dt.getShowDataTable_NoOfBoxes()));
						}

						/*
						 * pstmt3.setInt(1, dt.getSeq()); pstmt3.setDate(2,
						 * Utility
						 * .convertUtilDateToSQLDate(dt.getShowDataTable_Date
						 * ())); pstmt3.setString(3, gtinNo);
						 * pstmt3.setString(4, String.valueOf(caseno));
						 * pstmt3.setString(5,bottlecode);
						 * pstmt3.setString(6,"NA"); pstmt3.setDate(7, null);
						 * pstmt3.setString(8, "NA"); pstmt3.setDate(9, null);
						 * unmaped_status= pstmt3.executeUpdate();
						 */

						status = pstmt2.executeUpdate();
					}
				}

				if (status > 0) {
					status = 0;
					boolean flag = write(dt, action, conn,groupId);

					if (flag) {
						status = 1;
					} else {
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage("Excel Not Generated",
										"Excel Not Generated"));
					}
				}
				if (status > 0) {

					conn.commit();
					conn1.commit();

					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("Finalized SuccessFully",
									"Finalized SuccessFully"));
				} else {
					conn.rollback();
					conn1.rollback();
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("Not Finalized ",
									" Not Finalized "));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Gtin and Serial No Can Not Zero ",
								" Gtin and Serial No Can Not Zero"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			try {
				conn.rollback();
				conn1.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		} finally {

			try {

				if (pstmt1 != null)
					pstmt1.close();
				if (pstmt2 != null)
					pstmt2.close();
				if (pstmt3 != null)
					pstmt3.close();
				if (pstmt4 != null)
					pstmt4.close();
				if (conn != null)
					conn.close();
				if (conn1 != null)
					conn1.close();

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage(), e.getMessage()));
				e.printStackTrace();
			}
		}
	}

	public boolean write(BWFLPlanDataTable dt, BWFLPlanAction action,
			Connection conn,int groupId) {

		//System.out.println("excel innn");

		String bwfl = ""
				+

				/*
				 * "	select to_char(y.gs, 'fm000000000000')as GENERATE_SERIES , y.dispatch_date,y.gtin_no,"
				 * + "	  y.serial_no_start, y.serial_no_end, "+
				 * "	to_char(y.case_no , 'fm0000000000')as case_no from( "+
				 * "	select  GENERATE_SERIES(x.serial_no_start ,x.serial_no_end ) as gs ,x.serial_no_start ,x.serial_no_end, "
				 * + "	x.case_no,x.dispatch_date,x.gtin_no "+ "	from ( "+
				 * "	SELECT  dispatch_date, gtin_no, serial_no_start, serial_no_end, case_no "
				 * + "	FROM public.bottling_bwfl_import a "+
				 * "	where   dispatch_date=?   and gtin_no=? and seq=?)x)y";
				 */

				"	select to_char(y.gs, 'fm000000000000')as GENERATE_SERIES , y.dispatch_date,y.gtin_no,                 "
				+ "	y.serial_no_start, y.serial_no_end, "
				+ "	to_char(y.case_no::numeric , 'fm0000000000')as case_no from( "
				+ "	select  GENERATE_SERIES(x.serial_no_start::numeric ,x.serial_no_end::numeric ) as gs ,x.serial_no_start ,x.serial_no_end, "
				+ "	x.case_no,x.dispatch_date,x.gtin_no from ( "
				+ "	SELECT  date_plan as dispatch_date, etin as gtin_no, serial_no_start, serial_no_end, casecode as case_no "
				+ "	FROM bottling_unmapped.bwfl a where   date_plan=?   and etin=? and plan_id=?)x)y";

		String relativePath = Constants.JBOSS_SERVER_PATH
				+ Constants.JBOSS_LINX_PATH;
		FileOutputStream fileOut = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long start = 0;
		long end = 0;
		boolean flag = false;
		long k = 0;
		String noOfUnit = "";
		String date = null;

		try {

			pstmt = conn.prepareStatement(bwfl);

			pstmt.setDate(1, Utility.convertUtilDateToSQLDate(dt
					.getShowDataTable_Date()));
			pstmt.setString(2, dt.getGtinno());
			pstmt.setInt(3, groupId);
			rs = pstmt.executeQuery();

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet worksheet = workbook.createSheet("Barcode Report");

			XSSFCellStyle unlockedCellStyle = workbook.createCellStyle();
			unlockedCellStyle.setLocked(false);

			worksheet.protectSheet("agristat");
			worksheet.setColumnWidth(0, 3000);
			worksheet.setColumnWidth(1, 8000);
			worksheet.setColumnWidth(2, 8000);
			worksheet.setColumnWidth(3, 8000);
			worksheet.setColumnWidth(4, 6000);

			XSSFRow rowhead0 = worksheet.createRow((int) 0);
			// HSSFRow rowhead0 = worksheet.createRow((int) 0);
			// HSSFCell cellhead0 = rowhead0.createCell((int) 0);
			XSSFCell cellhead0 = rowhead0.createCell((int) 0);
			cellhead0.setCellValue("Barcode Report");

			rowhead0.setHeight((short) 700);
			cellhead0.setCellStyle(unlockedCellStyle);
			XSSFCellStyle cellStyl = workbook.createCellStyle();
			// HSSFCellStyle cellStyl = workbook.createCellStyle();

			cellStyl = workbook.createCellStyle();
			XSSFFont hSSFFont = workbook.createFont();
			hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
			hSSFFont.setFontHeightInPoints((short) 12);
			hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			hSSFFont.setColor(HSSFColor.GREEN.index);
			cellStyl.setFont(hSSFFont);
			cellhead0.setCellStyle(cellStyl);

			// HSSFCellStyle cellStyle = workbook.createCellStyle();
			XSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			// -----------------------------

			XSSFCellStyle unlockcellStyle = workbook.createCellStyle();
			unlockcellStyle.setLocked(false);

			// ----------------------------
			XSSFRow rowhead = worksheet.createRow((int) 1);

			XSSFCell cellhead1 = rowhead.createCell((int) 0);
			cellhead1.setCellValue("S.No.");

			cellhead1.setCellStyle(cellStyle);

			XSSFCell cellhead2 = rowhead.createCell((int) 1);
			cellhead2.setCellValue("Gtin");
			cellhead2.setCellStyle(cellStyle);

			XSSFCell cellhead3 = rowhead.createCell((int) 2);
			cellhead3.setCellValue("FinalizeDate");
			cellhead3.setCellStyle(cellStyle);

			XSSFCell cellhead4 = rowhead.createCell((int) 3);
			cellhead4.setCellValue("Case Etn");
			cellhead4.setCellStyle(cellStyle);

			XSSFCell cellhead5 = rowhead.createCell((int) 4);
			cellhead5.setCellValue("BottleBarcode");
			cellhead5.setCellStyle(cellStyle);

			int i = 0;
			while (rs.next()) {

				Date dat = Utility.convertSqlDateToUtilDate(rs
						.getDate("dispatch_date"));

				DateFormat formatter;

				formatter = new SimpleDateFormat("yyMMdd");
				date = formatter.format(dat);

				String lic = dt.getLicencenoo().replaceAll("/", "");

				// System.out.println("while in");serial_no_start, serial_no_end
				start = rs.getLong("serial_no_start");
				end = rs.getLong("serial_no_end");

				k++;
				XSSFRow row1 = worksheet.createRow((int) k);

				XSSFCell cellA1 = row1.createCell((int) 0);
				cellA1.setCellValue(k);

				XSSFCell cellB1 = row1.createCell((int) 1);
				cellB1.setCellValue(rs.getString("gtin_no"));

				XSSFCell cellC1 = row1.createCell((int) 2);
				cellC1.setCellValue(date);
				// cellC1.setCellStyle(unlockcellStyle);

				XSSFCell cellD1 = row1.createCell((int) 3);
				if (dt.getShowDataTable_Quntity().equals("180")) {
					noOfUnit = "48";
				} else if (dt.getShowDataTable_Quntity().equals("375")) {
					noOfUnit = "24";
				} else if (dt.getShowDataTable_Quntity().equals("650")) {
					noOfUnit = "12";
				} else if (dt.getShowDataTable_Quntity().equals("500")) {
					noOfUnit = "24";
				} else if (dt.getShowDataTable_Quntity().equals("60")) {
					noOfUnit = "150";
				} else if (dt.getShowDataTable_Quntity().equals("90")) {
					noOfUnit = "96";
				} else if (dt.getShowDataTable_Quntity().equals("2000")) {
					noOfUnit = "4";
				} else if (dt.getShowDataTable_Quntity().equals("1000")) {
					noOfUnit = "9";
				} else if (dt.getShowDataTable_Quntity().equals("200")) {
					noOfUnit = "45";
				} else if (dt.getShowDataTable_Quntity().equals("750")) {
					noOfUnit = "12";
				} else if (dt.getShowDataTable_Quntity().equals("275")) {
					noOfUnit = "24";
				} else {

					noOfUnit = Integer
							.toString(Integer.parseInt(dt
									.getShowDataTable_PlanNoOfBottling())
									/ Integer.parseInt(dt
											.getShowDataTable_NoOfBoxes()));
				}
				// noOfUnit=Integer.toString(dt.getNewsize());
				cellD1.setCellValue("01" + rs.getString("gtin_no") + "13"
						+ date + "37" + noOfUnit + "21"
						+ rs.getString("case_no"));

				XSSFCell cellE1 = row1.createCell((int) 4);
				cellE1.setCellValue("01" + rs.getString("gtin_no") + "13"
						+ date + "21" + rs.getString("GENERATE_SERIES"));

			}
			fileOut = new FileOutputStream(relativePath + "//ExciseUp//Excel//"
					+ dt.getGtinno() + date + groupId+ ".xls");

			XSSFRow row1 = worksheet.createRow((int) k + 1);

			XSSFCell cellA1 = row1.createCell((int) 0);
			cellA1.setCellValue("End");

			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			flag = true;

		} catch (Exception e) {

			//System.out.println("xls2" + e.getMessage());
			e.printStackTrace();
		}

		return flag;
	}
	
	
	//=============================get etin nmbr============================
	
	
	public String getEtinNmbr(String brand_Id, String packging_Id)
	{

		String etin = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList =	" SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id, b.code_generate_through "+
								" FROM distillery.brand_registration a , distillery.packaging_details b " +
								" WHERE a.brand_id=b.brand_id_fk  " +					
								" AND brand_id =?  AND b.package_id=?";

			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, Integer.parseInt(brand_Id));
			pstmt.setInt(2, Integer.parseInt(packging_Id));

			rs = pstmt.executeQuery();

			while (rs.next()) {

				etin = rs.getString("code_generate_through");

			}

			// pstmt.executeUpdate();
		} catch (SQLException se) {
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
	
	//--------------get data--------------------
	
	
	public ArrayList getDisplayList(BWFLPlanAction act)
	{


		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int j = 1;

		String selQr = 	" SELECT DISTINCT coalesce(finalized_flag,'K') as finalized_flag ,a.int_distillery_id, a.int_brand_id, a.int_pack_id, " +
						" a.int_quantity, a.int_planned_bottles, a.int_boxes, a.int_liquor_type, " +
						" a.vch_license_type, a.plan_dt, a.licence_no, a.cr_date, a.finalized_flag, " +
						" a.finalized_date, a.received_liqour, a.permitno, a.permitdt, " +
						" a.recieved_bottles, a.recieved_boxes, a.seq, a.gatepass_no, a.bottling_seq_frm, " +
						" a.bottling_seq_to, a.scan_upload_flag, a.exp_order_nmbr, a.exp_order_dt, " +
						" a.transprt_vehicle_nmbr, a.route_details, a.maped_unmaped_flag, a.breakage, " +
						" a.maped_unmaped_type, a.etin, a.validity_date, a.permitno_entered, a.group_id, " +
						" a.dispatch_date, b.vch_firm_name, b.vch_firm_district_id, " +
						" CASE WHEN a.maped_unmaped_type='M' THEN 'Mapped Data' "+
						" WHEN a.maped_unmaped_type='U' THEN 'UnMapped Data' end as type "+
						" FROM bwfl_license.mst_bottling_plan a, bwfl.registration_of_bwfl_lic_holder b  " +
						" WHERE a.int_distillery_id=b.int_id  " +
						" AND a.int_distillery_id='"+act.getDistillery_id()+"' " +
						" ORDER BY a.cr_date DESC ";
		 
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(selQr);
			 System.out.println("selQr------ not null---------"+selQr);
			rs = ps.executeQuery();
 
			while (rs.next()) {
				BWFLPlanDataTable dt = new BWFLPlanDataTable();
				 
				dt.setSnothrd(j);
				dt.setEtinUpdate(rs.getString("etin"));
				dt.setPermitno_enteredUpdate(rs.getString("permitno_entered"));
				dt.setInt_brand_idUpdate(rs.getString("int_brand_id"));
				dt.setInt_pack_idUpdate(rs.getString("int_pack_id"));
				dt.setSeqUpdate(rs.getInt("seq"));
				dt.setGatepass_noUpdate(rs.getString("gatepass_no"));
				
				if(rs.getString("finalized_flag").equalsIgnoreCase("F"))
				{
				dt.setUpdateDisabled(true);
				}
				else if(rs.getString("finalized_flag").equalsIgnoreCase("K"))

				{
					dt.setUpdateDisabled(false);
				}
				
				
				dt.setLicenseNmbr_dt(rs.getString("licence_no"));
				dt.setLicenseType_dt(rs.getString("vch_license_type"));
				dt.setUnitName_dt(rs.getString("vch_firm_name"));
				dt.setPermitNmbr_dt(rs.getString("permitno"));
				dt.setMappedType_dt(rs.getString("type"));
				dt.setPermitDt_dt(rs.getDate("permitdt"));
				dt.setPlan_dt(rs.getDate("plan_dt"));
				dt.setDispbot_dt(rs.getInt("int_planned_bottles"));
				dt.setDispbox_dt(rs.getInt("int_boxes"));
				dt.setDistillery_id(rs.getString("int_distillery_id"));
				
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
	//---------------------------------------------------------------------------------------------------------------
	
	public ArrayList getNewData(BWFLPlanAction ac)
	{
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int j = 1;

		/*String selQr = 	" SELECT licence_no,int_pack_id,int_brand_id,permitno_entered,validity_date,maped_unmaped_type,int_distillery_id,vch_license_type,int_planned_bottles, int_boxes FROM " +
						" bwfl_license.mst_bottling_plan  " +
						 " where permitno_entered='"+ac.getPermitno_enteredUpdate()+"' and vch_license_type='"+ac.getVch_license_typeUpdate()+"' and int_distillery_id='"+ac.getInt_distillery_idUpdate()+"' and int_brand_id='"+ac.getInt_brand_idUpdate()+"' "+
							" and int_pack_id='"+ac.getInt_pack_idUpdate()+"' and seq='"+ac.getSeqUpdate()+"' and licence_no='"+ac.getLicence_noUpdate()+"'  and gatepass_no='"+ac.getGatepass_noUpdate()+"' and permitdt='"+ac.getPermitDatUpdate()+"'  " ;
		*/				         
		String selQr = 	" SELECT int_quantity,licence_no,int_pack_id,int_brand_id,permitno_entered,validity_date,maped_unmaped_type,int_distillery_id,vch_license_type,int_planned_bottles, int_boxes FROM " +
				" bwfl_license.mst_bottling_plan  " +
				 " where permitno_entered='"+ac.getPermitno_enteredUpdate()+"' and vch_license_type='"+ac.getVch_license_typeUpdate()+"' and int_distillery_id='"+ac.getInt_distillery_idUpdate()+"' "+
					" and seq='"+ac.getSeqUpdate()+"' and licence_no='"+ac.getLicence_noUpdate()+"'  and gatepass_no='"+ac.getGatepass_noUpdate()+"' and permitdt='"+ac.getPermitDatUpdate()+"'  " ;
				         
 
		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(selQr);
			 
			rs = ps.executeQuery();

			while (rs.next()) 
			{
				BWFLPlanDataTable dt = new BWFLPlanDataTable();
				dt.setBrandPackagingData_Quantity(rs.getString("int_quantity"));
				dt.setBrandPackagingData_NoOfBoxes(rs.getInt("int_boxes"));
				dt.setBrandPackagingData_PlanNoOfBottling(rs.getInt("int_planned_bottles"));
				dt.setBrandPackagingData_Brand(rs.getString("int_brand_id"));
				dt.setBrandPackagingData_Packaging(rs.getString("int_pack_id"));
				if(rs.getString("vch_license_type").equalsIgnoreCase("BWFL2A"))
				{
						ac.setLicenceType("1");
				}
				else if(rs.getString("vch_license_type").equalsIgnoreCase("BWFL2B"))
				{
				
						ac.setLicenceType("2");
						
				}
				else if(rs.getString("vch_license_type").equalsIgnoreCase("BWFL2C"))
				{
						ac.setLicenceType("3");
				}
				else if(rs.getString("vch_license_type").equalsIgnoreCase("BWFL2D"))
				{
						ac.setLicenceType("4");
				}
				ac.setLicenceNum(rs.getString("licence_no"));
				ac.setPermitdt(ac.getPermitDatUpdate());
				ac.setBottlngType(rs.getString("maped_unmaped_type"));
				ac.setValidityDate(rs.getDate("validity_date"));
				ac.setPermitNoEnterd(rs.getString("permitno_entered"));
				
			//	ac.setFlagNew(true);
				ac.setDisabledFlag(true);
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
	
	
	
	
	/*public ArrayList getUnit_For_Update(String id ,String licenseNoNew, BWFLPlanAction ac) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);

	 

		String SQl = " SELECT distinct int_id, mobile_number, vch_license_type, vch_firm_name, vch_firm_add " +
				 " FROM bwfl.registration_of_bwfl_lic_holder  " +
				" WHERE vch_approval='V' AND " +
				 " vch_license_type= '"+id+"'  and vch_license_number='"+licenseNoNew+"' " ;
		
		 
		
		try {

			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);

		

			rs = ps.executeQuery();
			while (rs.next()) {

				item = new SelectItem();
				item.setLabel(rs.getString("vch_firm_name")+" ("+rs.getString("mobile_number")+" )");
				item.setValue(rs.getString("int_id"));

				list.add(item);
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
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}*/

	
	public boolean checkData(String permitno_enteredUpdate,String vch_license_typeUpdate, String int_distillery_idUpdate,  
    String int_brand_idUpdate,
     String int_pack_idUpdate,
     int seqUpdate,
     String licence_noUpdate,
     String gatepass_noUpdate,
     Date permitDatUpdate
	) 
	{
		boolean flag=false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	

	 

		String SQl = " select  * from bwfl_license.mst_bottling_plan  " +
					
					 " where permitno_entered='"+permitno_enteredUpdate+"' and vch_license_type='"+vch_license_typeUpdate+"'" +
					 " and int_distillery_id='"+int_distillery_idUpdate+"' and int_brand_id='"+int_brand_idUpdate+"' "+
					 " and int_pack_id='"+int_pack_idUpdate+"' and seq='"+seqUpdate+"' and " +
					 " licence_no='"+licence_noUpdate+"'  and gatepass_no='"+gatepass_noUpdate+"'" +
					 " and permitdt='"+permitDatUpdate+"'  " ;
		
		 
		
		try {

			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);

		

			rs = ps.executeQuery();
			if (rs.next()) 
			{
				flag=true;
				
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
					rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	
	
	public boolean checkpermit(String permitno_entered,BWFLPlanAction action) 
			{
				boolean flag=false;
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
			

			 

				String SQl = " select  * from bwfl_license.mst_bottling_plan  " +
						 " where permitno like '%"+action.getUserDistrict()+"-2018-19-"+action.getPermitNoEnterd()+"-%'  " ;
				
			 
				
				try {

					con = ConnectionToDataBase.getConnection();
					ps = con.prepareStatement(SQl);

				

					rs = ps.executeQuery();
					if (rs.next()) 
					{
						flag=true;
						
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
							rs.close();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return flag;
			}
	
	
	
	
	
	
	
public int delete(BWFLPlanAction action) {
	
	 	Connection conn = null;
		PreparedStatement pstmt = null;
		 int saveStatus = 0;
		 String query ="";
		try {

			conn = ConnectionToDataBase.getConnection();
			 query = " delete from  bwfl_license.mst_bottling_plan  where int_distillery_id="+
			action.getInt_distillery_idUpdate()+" and int_brand_id="+action.getInt_brand_idUpdate()
			+" and int_pack_id="+action.getInt_pack_idUpdate()+" and plan_dt='"+action.getPlan_dt()
			+"' and permitno='"+action.getPermitNoNew()+"'";
				pstmt = conn.prepareStatement(query);
				 saveStatus=pstmt.executeUpdate();
					if(saveStatus>0)
					{
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Delete Sucessfully", "Delete Sucessfully"));
					action.reset();
					action.getDisplayDataList();
					}else {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Not Delete ", "Not Delete "));
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



//-------------------------get data for valid till update--------------------------------------------------------------

public ArrayList getDisplayListExt(BWFLPlanAction act)
{


	ArrayList list = new ArrayList();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	int j = 1;

	String selQr = 	" SELECT coalesce(finalized_flag,'K') as finalized_flag ,a.int_distillery_id, a.int_brand_id, a.int_pack_id, " +
					" a.int_quantity, a.int_planned_bottles, a.int_boxes, a.int_liquor_type, " +
					" a.vch_license_type, a.plan_dt, a.licence_no, a.cr_date, a.finalized_flag, " +
					" a.finalized_date, a.received_liqour, a.permitno, a.permitdt, " +
					" a.recieved_bottles, a.recieved_boxes, a.seq, a.gatepass_no, a.bottling_seq_frm, " +
					" a.bottling_seq_to, a.scan_upload_flag, a.exp_order_nmbr, a.exp_order_dt, " +
					" a.transprt_vehicle_nmbr, a.route_details, a.maped_unmaped_flag, a.breakage, " +
					" a.maped_unmaped_type, a.etin, a.validity_date, a.permitno_entered, a.group_id, " +
					" a.dispatch_date, b.vch_firm_name, b.vch_firm_district_id, " +
					" CASE WHEN a.maped_unmaped_flag='M' THEN 'Mapped Data' "+
					" WHEN a.maped_unmaped_flag='U' THEN 'UnMapped Data' end as type "+
					" FROM bwfl_license.mst_bottling_plan a, bwfl.registration_of_bwfl_lic_holder b, public.district c  " +
					" WHERE a.int_distillery_id=b.int_id AND b.vch_firm_district_id=c.districtid::text " +
					" AND c.deo='"+ResourceUtil.getUserNameAllReq().trim()+"' and a.validity_date is not null " +
					" ORDER BY a.cr_date DESC ";
	 
	try {
		con = ConnectionToDataBase.getConnection();
		ps = con.prepareStatement(selQr);
		
		rs = ps.executeQuery();

		while (rs.next()) {
			BWFLPlanDataTable dt = new BWFLPlanDataTable();
			 
			dt.setSnothrd(j);
			dt.setEtinUpdate(rs.getString("etin"));
			dt.setPermitno_enteredUpdate(rs.getString("permitno_entered"));
			dt.setInt_brand_idUpdate(rs.getString("int_brand_id"));
			dt.setInt_pack_idUpdate(rs.getString("int_pack_id"));
			dt.setSeqUpdate(rs.getInt("seq"));
			dt.setGatepass_noUpdate(rs.getString("gatepass_no"));
			dt.setNewvalidityDate(rs.getDate("validity_date"));
			dt.setOldvalidityDate(rs.getDate("validity_date"));
			
			if(rs.getString("finalized_flag").equalsIgnoreCase("F"))
			{
			dt.setUpdateDisabled(true);
			}
			else if(rs.getString("finalized_flag").equalsIgnoreCase("K"))

			{
				dt.setUpdateDisabled(false);
			}
			
			
			dt.setLicenseNmbr_dt(rs.getString("licence_no"));
			dt.setLicenseType_dt(rs.getString("vch_license_type"));
			dt.setUnitName_dt(rs.getString("vch_firm_name"));
			dt.setPermitNmbr_dt(rs.getString("permitno"));
			dt.setMappedType_dt(rs.getString("type"));
			dt.setPermitDt_dt(rs.getDate("permitdt"));
			dt.setPlan_dt(rs.getDate("plan_dt"));
			dt.setDispbot_dt(rs.getInt("int_planned_bottles"));
			dt.setDispbox_dt(rs.getInt("int_boxes"));
			dt.setDistillery_id(rs.getString("int_distillery_id"));
			
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


public void updateValidTillExt(BWFLPlanAction action,Date date) {
	
	BWFLPlanDataTable table = new BWFLPlanDataTable();
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 0;
	int challanId = maxId();
	String query ="";
	try {

		conn = ConnectionToDataBase.getConnection();
		
		
		
		query = " UPDATE bwfl_license.mst_bottling_plan SET " +
				 " old_valid_till='"+Utility.convertUtilDateToSQLDate(action.getOld_valid_date())+"' , validity_date='"+ Utility.convertUtilDateToSQLDate(date)+"'," +
				 "extension_date='"+Utility.convertUtilDateToSQLDate(action.getNewValidDate())+"' "+
				 
	 " where vch_license_type='"+action.getVch_license_typeUpdate()+"' and int_distillery_id='"+action.getInt_distillery_idUpdate()+"'  "+
	" and seq='"+action.getSeqUpdate()+"' and licence_no='"+action.getLicence_noUpdate()+"'  and gatepass_no='"+action.getGatepass_noUpdate()+"'" +
	" and     permitdt='"+action.getPermitDatUpdate()+"'  " ;
			
			System.out.println("===========update-----faizal"+query);
				
			if(action.getOld_valid_date().before(date)){
					pstmt = conn.prepareStatement(query);
				
					
						saveStatus = pstmt.executeUpdate();
						
			
				if(saveStatus>0)
				{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Updated Sucessfully", "Updated Sucessfully"));
				action.reset();
				}else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Not Updated ", "Not Updated "));
				}
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Date should not be Less then old validity Date ", "Date should not be Less then old validity Date "));
			}
			
	}
		
	catch (SQLException e) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(e.getMessage(), e.getMessage()));
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

}









}
