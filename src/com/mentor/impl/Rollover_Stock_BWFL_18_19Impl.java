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
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




import com.mentor.action.Rollover_Stock_BWFL_18_19Action;
import com.mentor.datatable.Rollover_Stock_BWFL_18_19DT;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class Rollover_Stock_BWFL_18_19Impl {

	
	

	public String getDetails(Rollover_Stock_BWFL_18_19Action ac) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String queryList = "";
		try {
			con = ConnectionToDataBase.getConnection();
			
		
			
			queryList = 	" SELECT unit_id,vch_license_number,etin_unit_id,vch_license_type,mobile_number, vch_firm_name, vch_firm_add, int_id, vch_license_type "+
	                   " FROM bwfl.registration_of_bwfl_lic_holder_20_21 "+
	                   "  WHERE  mobile_number= '"+ResourceUtil.getUserNameAllReq().trim() + "' ";
			pstmt = con.prepareStatement(queryList);
			
			System.out.println("hidden query================"+queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {
		        ac.setUnit_id_brand(rs.getInt("unit_id"));
				ac.setUnit_name(rs.getString("vch_firm_name"));
				ac.setUnit_id(rs.getInt("int_id"));
				ac.setUnit_address(rs.getString("vch_firm_add"));
				///ac.setLicence_type(rs.getString("vch_licence_type"));
				ac.setLicence_no(rs.getString("vch_license_number"));
				ac.setEtin_unit_id(rs.getInt("etin_unit_id"));
				ac.setBwflLicenseTypeId(rs.getInt("vch_license_type"));
				
				if (rs.getString("vch_license_type").equals("1")) {
					
					ac.setLicence_type("BWFL2A");
				} else if (rs.getString("vch_license_type").equals("2")) {
					ac.setLicence_type("BWFL2B");
				} else if (rs.getString("vch_license_type").equals("3")) {
					ac.setLicence_type("BWFL2C");
				} else if (rs.getString("vch_license_type").equals("4")) {
					ac.setLicence_type("BWFL2D");
				}

			}

			} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (ps2 != null)
					ps2.close();
				if (rs != null)
					rs.close();
				if (rs2 != null)
					rs2.close();
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return "";

	}
	
	
	public ArrayList displaylistdata(Rollover_Stock_BWFL_18_19Action action) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		String query = "";
		int j = 1;
		try {

			


				query = " select  c.cowcess_fees,b.quantity,c.plandate,coalesce(c.planid,'0') as planid,coalesce(c.finaliz_flg,'NA') as finaliz_flg, "+
			            " coalesce(c.excel_flg,'NA') as excel_flg,c.date,b.code_generate_through," +
						"c.license_no,c.vch_licence_type,coalesce(c.final_flg,'NA') as final_flg ,a.brand_id,a.brand_name,b.package_type,b.package_name,c.package_id,  " +
						" case when b.package_type='1' then 'Glass Bottle' when b.package_type='2' then 'CAN' " +
						" when b.package_type='3' then 'Pet Bottle'   " +
						" when b.package_type='4' then 'Tetra Pack' when b.package_type='5' then 'Sachet'  " +
						" when b.package_type='6' then 'Keg' end as  package_type_name,  " +
						" c.size,c.box, c.mrp_18_19, c.rollover_fees, c.diif_duty " +
						" from distillery.brand_registration_20_21 a,   bwfl.rollover_bwfl_18_19 c ," +
						" distillery.packaging_details_20_21 b " +
						" where a.brand_id=c.brand_id and c.brand_id=b.brand_id_fk " +
						" and c.package_id=b.package_id" +
						" and c.int_bwfl_id='"+action.getUnit_id()+"' order by brand_name "; 

				//System.out.println("--------getShowData----------" + query);

				

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Rollover_Stock_BWFL_18_19DT dataTable = new Rollover_Stock_BWFL_18_19DT();
				dataTable.setS_no(j++);
				dataTable.setBrand_id(rs.getInt("brand_id"));
				dataTable.setBrand_name(rs.getString("brand_name"));
				dataTable.setPackg_id(rs.getInt("package_id"));
				//dataTable.setPackg_type(rs.getInt("package_type"));
				dataTable.setPackg(rs.getString("package_name")+"-"+rs.getString("package_type_name"));
				dataTable.setSize(rs.getString("size"));
				dataTable.setS_box(rs.getInt("box"));
				dataTable.setS_mrp(rs.getDouble("mrp_18_19"));
				dataTable.setS_rollover_fee(rs.getDouble("rollover_fees"));
				dataTable.setS_diff_duty(rs.getDouble("diif_duty"));
				dataTable.setButtn_flg(rs.getString("final_flg"));
				dataTable.setLicence_no(rs.getString("license_no"));
				dataTable.setLicence_type(rs.getString("vch_licence_type"));
				
				dataTable.setBottles(rs.getInt("box")*Integer.parseInt(rs.getString("size")));
				dataTable.setGtin_no(rs.getString("code_generate_through"));
				dataTable.setExcel_flg(rs.getString("finaliz_flg"));
				dataTable.setExcel_flg_dis(rs.getString("excel_flg"));
				if(rs.getInt("planid")>0)
				{
					dataTable.setPlanid(rs.getInt("planid"));
				}
				if (rs.getDate("plandate") != null) {
					Date dat = Utility.convertSqlDateToUtilDate(rs.getDate("plandate"));
					
					DateFormat formatter = new SimpleDateFormat("yyMMdd");
					String date = formatter.format(dat);
					dataTable.setFinalizedDateString(date);
					//System.out.println(date);
				}
				dataTable.setQuantity_ml(rs.getInt("quantity"));
				dataTable.setS_cowcess(rs.getDouble("cowcess_fees"));
				
				
					
				
				

				list.add(dataTable);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		return list;
	}
	
	

public String saveData(Rollover_Stock_BWFL_18_19Action action) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 0;


	try { 
		
		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
	
		String query =" INSERT INTO bwfl.rollover_bwfl_18_19(int_bwfl_id, brand_id, package_id," +
				      "  size, box, mrp_18_19, rollover_fees, diif_duty,date,license_no,vch_licence_type,cowcess_fees )" +
				      " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
				
		for (int i = 0; i < action.getDisplaylist().size(); i++) {
			Rollover_Stock_BWFL_18_19DT dt = new Rollover_Stock_BWFL_18_19DT();
			dt = (Rollover_Stock_BWFL_18_19DT) action.getDisplaylist().get(i);
			//System.out.println("===insert=");
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, action.getUnit_id());
            pstmt.setInt(2, Integer.parseInt(dt.getBrandPackagingData_Brand()));
            pstmt.setInt(3, Integer.parseInt(dt.getBrandPackagingData_Packaging()));
            
            pstmt.setInt(4, Integer.parseInt(dt.getBrandPackagingData_Quantity()));
            pstmt.setInt(5, dt.getBox());
            pstmt.setDouble(6, dt.getMrp());
            pstmt.setDouble(7, dt.getRollover_fee());
            pstmt.setDouble(8, 0);
        	pstmt.setDate(9,Utility.convertUtilDateToSQLDate(new Date()));
            pstmt.setString(10, action.getLicence_no());
            ///pstmt.setString(11, String.valueOf(action.getBwflLicenseTypeId()));
            pstmt.setString(11, action.getLicence_type());
            pstmt.setDouble(12, dt.getCowcess());
            
            saveStatus=pstmt.executeUpdate();
            
		}
		 
			
			if (saveStatus > 0) {
				 conn.commit();
				 action.reset();
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"   save successfully ",
									"  save successfully "));
			}
			else {

				conn.rollback();
				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
						Constants.NOT_SAVED);

			}
		}  catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Some of these Brands are alrady saved !!!",
							"Some of these Brands are alrady saved !!!"));
			} finally {
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
	return "";
}	
public String finalizdata(Rollover_Stock_BWFL_18_19Action action, Rollover_Stock_BWFL_18_19DT dt) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 0;


	try { 
		
		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
	
		String query =" Update bwfl.rollover_bwfl_18_19 set final_flg='T' " +
				      " where int_bwfl_id=? and  brand_id=? and  package_id=? and size=? " +
				      " and license_no=? and vch_licence_type=? ";
				
		
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, action.getUnit_id());
            pstmt.setInt(2, dt.getBrand_id());
            pstmt.setInt(3, dt.getPackg_id());
            pstmt.setInt(4, Integer.parseInt(dt.getSize()));
            pstmt.setString(5, dt.getLicence_no());
            pstmt.setString(6, dt.getLicence_type());
            saveStatus=pstmt.executeUpdate();
            
			if (saveStatus > 0) {
				 conn.commit();
				 action.reset();
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Data Finalize Successfully and send to DEO !!! ",
									"Data Finalize Successfully and send to DEO !!!"));
			}
			else {

				conn.rollback();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Data Not Finalize Successfully!!! ",
								"Data Not Finalize Successfully!!!"));

			}
		}  catch (Exception e) {
			e.printStackTrace();
			} finally {
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
	return "";
}	
	
	
public ArrayList getBrandName() {

	FacesContext facesContext = FacesContext.getCurrentInstance();
	Rollover_Stock_BWFL_18_19Action bof = (Rollover_Stock_BWFL_18_19Action) facesContext
			.getApplication().createValueBinding("#{rollover_Stock_BWFL_18_19Action}")
			.getValue(facesContext);

	///int unit_id = bof.getUnit_id();
	int unit_id = bof.getUnit_id_brand();
	
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

	
			sql =   "	SELECT distinct brand_id, brand_name FROM distillery.brand_registration_20_21 a,distillery.packaging_details_20_21 b"
					+ "  where  int_bwfl_id=? and a.brand_id=b.brand_id_fk and  (b.mrp+b.adduty+b.duty+b.permit+b.export) > 0   order by brand_id ";

			

		con = ConnectionToDataBase.getConnection();
		ps = con.prepareStatement(sql);
		
			
			ps.setInt(1, unit_id);
			
	
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


public ArrayList getPackagingName(String brand_id) {
	ArrayList list = new ArrayList();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	SelectItem item = new SelectItem();
	item.setLabel("--SELECT--");
	item.setValue("");
	list.add(item);
	String SQl = "SELECT a.brand_id, a.brand_name ,b.package_type,b.quantity, b.package_name ,b.package_id "
			+ "	from distillery.brand_registration_20_21 a , "
			+ "	distillery.packaging_details_20_21 b "
			+ "	where a.brand_id=b.brand_id_fk  " +
			"	and brand_id =? and (b.mrp+b.adduty+b.duty+b.permit+b.export) > 0  ";
	
	 
	try {
		con = ConnectionToDataBase.getConnection();
		ps = con.prepareStatement(SQl);
		
		ps.setInt(1, Integer.parseInt(brand_id));
		rs = ps.executeQuery();
		while (rs.next()) 
		{
			item = new SelectItem();
			
		if(rs.getInt("package_type")==1)
		{
				
			
			item.setLabel(rs.getString("package_name")+"-Glass Bottle");
			item.setValue(rs.getString("package_id"));
		}	
		else if(rs.getInt("package_type")==2)
		{
			item.setLabel(rs.getString("package_name")+"-CAN");
			item.setValue(rs.getString("package_id"));
		}
		else if(rs.getInt("package_type")==3)
		{
			item.setLabel(rs.getString("package_name")+"-Pet Bottle");
			item.setValue(rs.getString("package_id"));
		}
		else if(rs.getInt("package_type")==4)
		{
			item.setLabel(rs.getString("package_name")+"-Tetra Pack");
			item.setValue(rs.getString("package_id"));
		}
		else if(rs.getInt("package_type")==5)
		{
			item.setLabel(rs.getString("package_name")+"-Sache");
			item.setValue(rs.getString("package_id"));
		}
		else if(rs.getInt("package_type")==6)
		{
			item.setLabel(rs.getString("package_name")+"-Keg");
			item.setValue(rs.getString("package_id"));
		}
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


public String getqty(String brand_Id, String packging_Id) {
	String qty = "";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {

		String queryList =

		"SELECT d.box_size,a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id "
				+ "	from distillery.brand_registration_20_21 a , "
				+ "	distillery.packaging_details_20_21 b  ,distillery.box_size_details d"
				+ "	where a.brand_id=b.brand_id_fk  " +
				
				"	and brand_id =?  and b.package_id=? AND b.box_id=d.box_id AND b.quantity=d.qnt_ml_detail ";

		con = ConnectionToDataBase.getConnection();

		pstmt = con.prepareStatement(queryList);

		pstmt.setInt(1, Integer.parseInt(brand_Id));
		pstmt.setInt(2, Integer.parseInt(packging_Id));

		rs = pstmt.executeQuery();

		while (rs.next()) {

			qty = rs.getString("box_size");

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
	return qty;

}


public int maxIdgroup() {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt1 = null;
	ResultSet rs1 = null;

	String query = " SELECT max(group_id)+1 as id FROM bwfl_license.mst_bottling_plan_20_21 ";
			
	int maxid = 0;
	try {
		con = ConnectionToDataBase.getConnection();
		pstmt = con.prepareStatement(query);
		rs = pstmt.executeQuery();
		 System.out.println("query==" + query);
		if (rs.next()) {
			maxid = rs.getInt("id");
		} 
	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

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
System.out.println("maxid==" + maxid);
	return maxid;

}

public void dataFinalize(Rollover_Stock_BWFL_18_19Action action, Rollover_Stock_BWFL_18_19DT dt) {
	Connection conn = null;
	Connection conn1 = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	PreparedStatement pstmt3 = null;
	PreparedStatement pstmt4 = null;
	PreparedStatement pstmt5 = null;
	PreparedStatement pstmt6 = null;
	PreparedStatement pstmt7 = null;
	ResultSet rs = null;
	String gtinNo = "";
	long boxsize = 0;
	long caseno = 0;
	int unmaped_status = 0;
	int status5 =0;
	int status6 =0;
	int status7 =0;
	String bottlecode = "";
	int groupId = maxIdgroup();
	
	

	String sql = " INSERT INTO bottling_unmapped.bwfl(  "
			+ " date_plan, etin, serial_no_start, serial_no_end, casecode, plan_id, " + " unit_id)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
	int status = 0;
	long serialno = 0;

	String update = "UPDATE bwfl.rollover_bwfl_18_19 "
			+ " SET   finalized_flag='F' ,finalized_date=?"
			
			+ "WHERE int_bwfl_id=? and brand_id=? and package_id=? and plandate=? and planid=? ";


	try {
		gtinNo = dt.getGtin_no();

	

		serialno = getSerialNo(new Double(dt.getBottles()).longValue(), new Date());

		// System.out.println("gtinNo"+gtinNo);
		// System.out.println("serialno="+serialno);
		if (!gtinNo.equals("") && serialno != 0) {
			
			conn = ConnectionToDataBase.getConnection20_21();
			conn1 = ConnectionToDataBase.getConnection();
		    conn.setAutoCommit(false);
			conn1.setAutoCommit(false);
			
			
			
			String update1 = "UPDATE bwfl.rollover_bwfl_18_19 "
					+ " SET   seqfrom=?,seqto=?,caseseq=?, plandate=?, planid=? " 
					
					+ "WHERE int_bwfl_id='"+action.getUnit_id()+"' and brand_id='"+dt.getBrand_id()+"' and package_id='"+dt.getPackg_id()+"'  " +
						
									" and license_no='"+dt.getLicence_no()+"' and size='"+dt.getSize()+"' and box='"+dt.getS_box()+"' and vch_licence_type='"+dt.getLicence_type()+"' ";
			
			pstmt5 = conn1.prepareStatement(update1);

				 
				
				

			
			pstmt5.setLong(1, 0);
			pstmt5.setLong(2, 0);
			pstmt5.setLong(3,0);
			pstmt5.setDate(4, Utility.convertUtilDateToSQLDate(new Date()));
			pstmt5.setInt(5, groupId);

			status5 = pstmt5.executeUpdate();
		

			pstmt1 = conn1.prepareStatement(update);

	pstmt1.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			
			pstmt1.setInt(2, action.getUnit_id());
			pstmt1.setInt(3, dt.getBrand_id());
			pstmt1.setInt(4, dt.getPackg_id());
			pstmt1.setDate(5, Utility.convertUtilDateToSQLDate(new Date()));
			pstmt1.setInt(6, groupId);

			status = pstmt1.executeUpdate();
			// //System.out.println("statussssssssssssssss"+status);

			if (status > 0) {
				status = 0;
				for (int i = 0; i < dt.getS_box(); i++) {
					// caseno = dt.getCaseseq() + i;

					caseno = getcaseNo(new Date());

					pstmt2 = conn.prepareStatement(sql);
					// pstmt3=conn.prepareStatement(unmapped_sql);

					pstmt2.setDate(1, new java.sql.Date(System.currentTimeMillis()));
					pstmt2.setString(2, gtinNo);
					pstmt2.setString(3, StringUtils.leftPad(String.valueOf(serialno), 8, '0'));
					pstmt2.setString(4,
							StringUtils.leftPad(
									String.valueOf(
											serialno + (dt.getBottles()
													/ dt.getS_box()) - 1),
									8, '0'));
					Random rand1 = new Random();
					int n1 = rand1.nextInt((99 - 91) + 1) + 91;
					pstmt2.setString(5, n1 + "" + StringUtils.leftPad(String.valueOf(caseno), 6, '0')
							+ getCheckDigit(n1 + "" + StringUtils.leftPad(String.valueOf(caseno), 6, '0')));
					pstmt2.setInt(6, groupId);

					///pstmt2.setLong(7, getEtinUnitId(dt.getDistillery_id()));
					pstmt2.setLong(7, action.getUnit_id());
					serialno += (dt.getBottles()
							/ dt.getS_box());

					status = pstmt2.executeUpdate();
				}
			}

			if (status > 0) {
				status = 0;
				boolean flag = write(dt, action, conn, groupId);

				if (flag) {
					status = 1;
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Excel Not Generated", "Excel Not Generated"));
				}
			}
if (status > 0) {
				
				String update11 = " update bwfl.rollover_bwfl_18_19 "
						+ " SET   excel_flg='T' " 
						
						+ "WHERE int_bwfl_id='"+action.getUnit_id()+"' and brand_id='"+dt.getBrand_id()+"' and package_id='"+dt.getPackg_id()+"'  " +
							
										" and license_no='"+dt.getLicence_no()+"' and size='"+dt.getSize()+"' and box='"+dt.getS_box()+"' and vch_licence_type='"+dt.getLicence_type()+"' ";
				
					System.out.println("===update---last===="+update11);
				pstmt6 = conn1.prepareStatement(update11);
				status6 = pstmt6.executeUpdate();
				System.out.println("===status6---last===="+status6);
			}
				if (status6 > 0) {
				String update12 = " INSERT INTO bwfl_license.mst_bottling_plan_20_21( int_distillery_id, int_brand_id, int_pack_id, plan_dt, "+
						          "  group_id,finalized_flag,finalized_date,int_planned_bottles, int_boxes, int_liquor_type, vch_license_type,  int_quantity, "+
						          " licence_no, permitdt,permitno ) "+ 
						          " values('"+action.getUnit_id()+"','"+dt.getBrand_id()+"','"+dt.getPackg_id()+"','"+Utility.convertUtilDateToSQLDate(new Date())+"' ," +
								  " '"+groupId+"','F', '"+Utility.convertUtilDateToSQLDate(new Date())+"','"+dt.getBottles()+"','"+dt.getS_box()+"','1','"+dt.getLicence_type()+"','"+dt.getQuantity_ml()+"', " +
								  " '"+dt.getLicence_no()+"','"+Utility.convertUtilDateToSQLDate(new Date())+"', 'Rollover-bwfl.rollover_bwfl_18_19' )  ";
				
					System.out.println("----insert ======"+update12);
				pstmt7 = conn1.prepareStatement(update12);
				status7 = pstmt7.executeUpdate();
				System.out.println("===status7---last===="+status7);
				}
				
				if (status7 > 0) {
			//if (status > 0) {

				conn.commit();
				conn1.commit();

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Finalized SuccessFully", "Finalized SuccessFully"));
			} else {
				conn.rollback();
				conn1.rollback();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Not Finalized ", " Not Finalized "));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Gtin and Serial No Can Not Zero ", " Gtin and Serial No Can Not Zero"));
		}

	} catch (Exception e) {
		e.printStackTrace();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}
	}
}

public boolean write(Rollover_Stock_BWFL_18_19DT dt, Rollover_Stock_BWFL_18_19Action action, Connection conn, int groupId) {

	// //System.out.println("excel innn");
	String sql_bwfl_update = "update bottling_unmapped.bwfl set bottle_code=? where unit_id= ? and plan_id=? and date_plan=? and  etin=? and  casecode=?";
	String bwfl = "" +

			"	select to_char(y.gs, 'fm00000000')as GENERATE_SERIES , y.dispatch_date,y.gtin_no, y.unit_id ,               "
			+ "	y.serial_no_start, y.serial_no_end, "
			+ "	to_char(y.case_no::numeric , 'fm000000000')as case_no,y.plan_id from( "
			+ "	select  x.unit_id,GENERATE_SERIES(x.serial_no_start::numeric ,x.serial_no_end::numeric ) as gs ,x.serial_no_start ,x.serial_no_end, "
			+ "	x.case_no,x.dispatch_date,x.gtin_no,x.plan_id from ( "
			+ "	SELECT  plan_id,unit_id,date_plan as dispatch_date, etin as gtin_no, serial_no_start, serial_no_end, casecode as case_no "
			+ "	FROM bottling_unmapped.bwfl a where   date_plan=?   and etin=? and plan_id=?)x)y";

	String relativePath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
	FileOutputStream fileOut = null;

	int count = 1;
	String bottle_code = "";
	PreparedStatement pstmt = null;
	PreparedStatement pstmt1 = null;
	ResultSet rs = null;
	long start = 0;
	long end = 0;
	boolean flag = false;
	long k = 0;
	String noOfUnit = "";
	String date = null;

	try {

		pstmt = conn.prepareStatement(bwfl);

		pstmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));
		pstmt.setString(2, dt.getGtin_no());
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

			Date dat = Utility.convertSqlDateToUtilDate(rs.getDate("dispatch_date"));

			DateFormat formatter;

			formatter = new SimpleDateFormat("yyMMdd");
			date = formatter.format(dat);

			String lic = dt.getLicence_no().replaceAll("/", "");

			// //System.out.println("while in");serial_no_start, serial_no_end
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
			noOfUnit = StringUtils.leftPad(String.valueOf(dt.getBottles()
					/ dt.getS_box()), 3, '0');

			if (noOfUnit.length() == 2) {

				cellD1.setCellValue(rs.getString("gtin_no") + "" + 1 + ""
						+ StringUtils.leftPad(String.valueOf(rs.getString("unit_id")), 3, '0') + "" + date + ""
						+ "1" + "" + StringUtils.leftPad(String.valueOf(noOfUnit), 3, '0') + ""
						+ rs.getString("case_no"));

			} else if (noOfUnit.length() == 1) {
				cellD1.setCellValue("" + rs.getString("gtin_no") + "" + 1 + ""
						+ StringUtils.leftPad(String.valueOf(rs.getString("unit_id")), 3, '0') + "" + date + ""
						+ "1" + "" + StringUtils.leftPad(String.valueOf(noOfUnit), 3, '0') + ""
						+ rs.getString("case_no"));
			} else {

				cellD1.setCellValue(rs.getString("gtin_no") + "" + 1 + ""
						+ StringUtils.leftPad(String.valueOf(rs.getString("unit_id")), 3, '0') + "" + date + ""
						+ "1" + "" + StringUtils.leftPad(String.valueOf(noOfUnit.substring(0, 3)), 3, '0') + ""
						+ rs.getString("case_no"));

			}

			XSSFCell cellE1 = row1.createCell((int) 4);

			Random rand = new Random();
			int n = 10 + rand.nextInt(90);

			cellE1.setCellValue(rs.getString("gtin_no") + "" + date
					+ StringUtils.leftPad(String.valueOf(rs.getString("unit_id")), 3, '0') + "" + "" + n + ""
					+ rs.getString("GENERATE_SERIES") + "" + getCheckDigit(rs.getString("GENERATE_SERIES")));

			bottle_code = bottle_code + "|" + n + "" + rs.getString("GENERATE_SERIES") + ""
					+ getCheckDigit(rs.getString("GENERATE_SERIES"));

			pstmt1 = conn.prepareStatement(sql_bwfl_update);
			// System.out.println("bottle_code "+bottle_code);
			// System.out.println("unit id
			// "+String.valueOf(Integer.parseInt(rs.getString("unit_id"))));
			// System.out.println("unit id "+rs.getInt("plan_id"));
			// System.out.println("unit id
			// "+Utility.convertUtilDateToSQLDate(rs.getDate("dispatch_date") ));
			// System.out.println("unit id "+rs.getString("gtin_no") );
			// System.out.println("unit id "+rs.getString("case_no") );
			pstmt1.setString(1, bottle_code);
			pstmt1.setInt(2, Integer.parseInt(rs.getString("unit_id")));
			pstmt1.setInt(3, rs.getInt("plan_id"));
			pstmt1.setDate(4, Utility.convertUtilDateToSQLDate(rs.getDate("dispatch_date")));
			pstmt1.setString(5, rs.getString("gtin_no"));
			pstmt1.setString(6, rs.getString("case_no"));
			pstmt1.executeUpdate();

			if (count == (dt.getBottles()
					/ dt.getS_box()))

			{

				System.out.println("come inn nn time ");
				count = 0;
				bottle_code = "";
			}

			count++;

		}
		fileOut = new FileOutputStream(
				relativePath + "//ExciseUp//RolloverStock//Excel//" + dt.getGtin_no() + date + groupId + ".xls");

		XSSFRow row1 = worksheet.createRow((int) k + 1);

		XSSFCell cellA1 = row1.createCell((int) 0);
		cellA1.setCellValue("End");

		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
		flag = true;

	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

		// //System.out.println("xls2" + e.getMessage());
		e.printStackTrace();
	}

	return flag;
}




public static int getCheckDigit(String n) {
	// int i=0;
	int sum = 0;
	int even = 0;
	int odd = 0;
	int checkDigit = 2;
	/*char ar[] = n.toCharArray();
	int k = ar.length;
	for (int i = ar.length - 1; i >= 0; i--) {

		if (i % 2 != 0) {
			// //System.out.println("evennnn"+ar[i]);
			odd = odd + Character.getNumericValue(ar[i]);
		} else {
			// //System.out.println("oddddddd"+ar[i]);
			even = even + Character.getNumericValue(ar[i]);
		}

	}

	sum = (odd * 3) + even;
	// System.out.println("summm" + sum);
	// System.out.println("even" + even);
	// System.out.println("odd sum" + odd);
	if (sum % 10 != 0) {

		// //System.out.println("SUMM "+sum);
		checkDigit = (10 - sum % 10);
		// //System.out.println("checkDigit "+checkDigit);
	}*/

	return checkDigit;
}


public synchronized long getcaseNo(Date date) {
	// String sql = " select nextval('public.bwfl_manual_case_code')";

	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	long serialNo = 0;
	long currseq = 0;

	try {
		conn = ConnectionToDataBase.getConnection19_20();

		Date today = date;
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		String currentdate = sdf.format(today);
		pstmt2 = conn.prepareStatement("CREATE SEQUENCE IF NOT EXISTS public.bwfl_manual_case_code_" + currentdate);
		pstmt2.executeUpdate();

		pstmt = conn.prepareStatement(" select     nextval('public.bwfl_manual_case_code_" + currentdate + "')");
		rs = pstmt.executeQuery();
		if (rs.next()) {
			serialNo = rs.getLong(1);
			if (serialNo == 0) {
				serialNo = serialNo;

			}
			/*
			 * pstmt = conn .prepareStatement("ALTER SEQUENCE public.bwfl_manual_case_code_"
			 * +currentdate+" RESTART WITH " + (no + serialNo));
			 * 
			 * 
			 * pstmt.executeUpdate();
			 */
		}

	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}
	}

	return serialNo;
}

public synchronized long getSerialNo(long noOfSequenc, Date date) {

	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	long serialNo = 0;

	try {
		conn = ConnectionToDataBase.getConnection19_20();

		Date today = date;
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		String currentdate = sdf.format(today);
		pstmt2 = conn.prepareStatement("CREATE SEQUENCE IF NOT EXISTS public.serial_seq_" + currentdate);
		pstmt2.executeUpdate();

		pstmt = conn.prepareStatement("select     nextval('public.serial_seq_" + currentdate + "')");
		rs = pstmt.executeQuery();
		if (rs.next()) {
			serialNo = rs.getInt(1);
			if (serialNo == 0) {
				serialNo = serialNo + 1;
			}
			// //System.out.println("noOfSequenc " + noOfSequenc);

			pstmt1 = conn.prepareStatement("ALTER SEQUENCE public.serial_seq_" + currentdate + " RESTART WITH "
					+ (noOfSequenc + serialNo + 1));

			pstmt1.executeUpdate();

		}

	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));

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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}
	}

	return serialNo;
}



	
}
