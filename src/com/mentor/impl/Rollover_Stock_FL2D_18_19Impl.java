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
import com.mentor.action.Rollover_Stock_FL2D_18_19Action;
import com.mentor.datatable.Rollover_Stock_FL2D_18_19DT;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class Rollover_Stock_FL2D_18_19Impl {
	
	



	
	public String getDetails(Rollover_Stock_FL2D_18_19Action ac) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String queryList = "";
		try {
			con = ConnectionToDataBase.getConnection();
			
		
			
			queryList = 		" select vch_core_address,etin_unit_id,int_app_id,vch_licence_no, vch_firm_name ,vch_license_type " +
					" from licence.fl2_2b_2d_20_21 where  vch_license_type='FL2D' and  vch_mobile_no" +
					" = '"+ ResourceUtil.getUserNameAllReq().trim() + "' ";

			pstmt = con.prepareStatement(queryList);
			
		System.out.println("hidden query================"+queryList);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				
				ac.setUnit_name(rs.getString("vch_firm_name"));
				ac.setUnit_id(rs.getInt("int_app_id"));
				ac.setUnit_address(rs.getString("vch_core_address"));
				ac.setLicence_type(rs.getString("vch_license_type"));
				ac.setLicence_no(rs.getString("vch_licence_no"));
				ac.setEtin_unit_id(rs.getInt("etin_unit_id"));

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
	
	
	public ArrayList displaylistdata(Rollover_Stock_FL2D_18_19Action action) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		String query = "";
		int j = 1;
		try {

			/*" select b.quantity,c.plandate,coalesce(c.planid,'0') as planid,coalesce(c.finaliz_flg,'NA') as finaliz_flg,
			 * coalesce(c.excel_flg,'NA') as excel_flg,c.date,b.code_generate_through,coalesce(c.final_flg,'NA') as final_flg ,
			 * a.brand_id,a.brand_name,b.package_type,b.package_name,c.package_id,  " +
					" c.license_no,c.vch_licence_type,case when b.package_type='1' then 'Glass Bottle' when b.package_type='2' then 'CAN' " +
					" when b.package_type='3' then 'Pet Bottle'   " +
					" when b.package_type='4' then 'Tetra Pack' when b.package_type='5' then 'Sachet'  " +
					" when b.package_type='6' then 'Keg' end as  package_type_name,  " +
					" c.size,c.box, c.mrp_18_19, c.rollover_fees, c.diif_duty " +
					" from distillery.brand_registration_20_21 a,  "+filter+"  "+
					" distillery.packaging_details_20_21 b " +
					" where a.brand_id=c.brand_id and c.brand_id=b.brand_id_fk " +
					" and c.package_id=b.package_id  "+filter1+" order by brand_name "; */


				query = " select  c.cowcess_fees,b.quantity,c.plandate,coalesce(c.planid,'0') as planid,coalesce(c.finaliz_flg,'NA') as finaliz_flg, "+
			            " coalesce(c.excel_flg,'NA') as excel_flg,c.date,b.code_generate_through," +
						"c.license_no,c.vch_licence_type,coalesce(c.final_flg,'NA') as final_flg ,a.brand_id,a.brand_name,b.package_type,b.package_name,c.package_id,  " +
						" case when b.package_type='1' then 'Glass Bottle' when b.package_type='2' then 'CAN' " +
						" when b.package_type='3' then 'Pet Bottle'   " +
						" when b.package_type='4' then 'Tetra Pack' when b.package_type='5' then 'Sachet'  " +
						" when b.package_type='6' then 'Keg' end as  package_type_name,  " +
						" c.size,c.box, c.mrp_18_19, c.rollover_fees, c.diif_duty " +
						" from distillery.brand_registration_20_21 a,   fl2d.rollover_fl2d_18_19 c ," +
						" distillery.packaging_details_20_21 b " +
						" where a.brand_id=c.brand_id and c.brand_id=b.brand_id_fk " +
						" and c.package_id=b.package_id" +
						" and c.int_fl2d_id='"+action.getUnit_id()+"' order by brand_name "; 

				//System.out.println("--------getShowData----------" + query);

				

			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Rollover_Stock_FL2D_18_19DT dataTable = new Rollover_Stock_FL2D_18_19DT();
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
				
				System.out.println("========file name data select ====="+dataTable.getPlanid()+""+dataTable.getGtin_no()+""+dataTable.getFinalizedDateString());
					
				///{list.planid}#{list.gtin_no}#{list.finalizedDateString}
				

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
	
	

public String saveData(Rollover_Stock_FL2D_18_19Action action) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 0;


	try { 
		
		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
	
		String query =" INSERT INTO fl2d.rollover_fl2d_18_19(int_fl2d_id, brand_id, package_id," +
				      "  size, box, mrp_18_19, rollover_fees, diif_duty,date,license_no,vch_licence_type,cowcess_fees )" +
				      " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
				
		for (int i = 0; i < action.getDisplaylist().size(); i++) {
			Rollover_Stock_FL2D_18_19DT dt = new Rollover_Stock_FL2D_18_19DT();
			dt = (Rollover_Stock_FL2D_18_19DT) action.getDisplaylist().get(i);
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
public String finalizdata(Rollover_Stock_FL2D_18_19Action action, Rollover_Stock_FL2D_18_19DT dt) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 0;


	try { 
		
		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
	
		String query =" Update fl2d.rollover_fl2d_18_19 set final_flg='T' " +
				      " where int_fl2d_id=? and  brand_id=? and  package_id=? and size=? " +
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
	Rollover_Stock_FL2D_18_19Action bof = (Rollover_Stock_FL2D_18_19Action) facesContext
			.getApplication().createValueBinding("#{rollover_Stock_FL2D_18_19Action}")
			.getValue(facesContext);

	int unit_id = bof.getUnit_id();

	
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
					+ "  where    a.int_fl2d_id>0   and a.for_csd_civil not in ('CSD')	 "
					///" and a.brand_id=b.brand_id_fk and  (b.mrp+b.adduty+b.duty+b.permit+b.export) > 0   " +
					 + " order by brand_name ";

			

		con = ConnectionToDataBase.getConnection();
		ps = con.prepareStatement(sql);
		
			
			//ps.setInt(1, unit_id);
			
	
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
			+ "	where  brand_id ='"+Integer.parseInt(brand_id)+"' and a.brand_id=b.brand_id_fk  " +
				///	" and (b.mrp+b.adduty+b.duty+b.permit+b.export) > 0 " +
					"  ";
	
	 System.out.println("===package---"+SQl);
	try {
		con = ConnectionToDataBase.getConnection();
		ps = con.prepareStatement(SQl);
		
		//ps.setInt(1, Integer.parseInt(brand_id));
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

/*
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
				
				"	and brand_id ='"+Integer.parseInt(brand_Id)+"'  and b.package_id='"+Integer.parseInt(packging_Id)+"' AND b.box_id=d.box_id AND b.quantity=d.qnt_ml_detail ";
System.out.println("===size==="+queryList);
		con = ConnectionToDataBase.getConnection();

		pstmt = con.prepareStatement(queryList);

		//pstmt.setInt(1, Integer.parseInt(brand_Id));
		//pstmt.setInt(2, Integer.parseInt(packging_Id));

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

}*/

/*
public void dataFinalize(Rollover_Stock_FL2D_18_19Action action,
		Rollover_Stock_FL2D_18_19DT dt) {
	Connection conn = null;
	Connection conn1 = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	PreparedStatement pstmt3 = null;
	PreparedStatement pstmt4 = null;
	PreparedStatement pstmt5 = null;
	PreparedStatement pstmt6 = null;
	PreparedStatement pstmt7 = null;
	String gtinNo = "";
	long boxsize = 0;
	long caseno = 0;
	long serialno=0;
	int status = 0;
	int status11 = 0;
	int status5 = 0;
	int status6 = 0;
	int status7 = 0;

	int planid= maxId();
	try {
	conn = ConnectionToDataBase.getConnection20_21();
	conn1 = ConnectionToDataBase.getConnection();
	conn.setAutoCommit(false);
	conn1.setAutoCommit(false);
	
	
	String update1 = "UPDATE distillery.rollover_fl1_fl1a_18_19 "
			+ " SET   seqfrom=?,seqto=?,caseseq=?, plandate=?, planid=? " 
			
			+ "WHERE int_distillery_id='"+action.getUnit_id()+"' and brand_id='"+dt.getBrand_id()+"' and package_id='"+dt.getPackg_id()+"'  " +
				
							" and license_no='"+dt.getLicence_no()+"' and size='"+dt.getSize()+"' and box='"+dt.getS_box()+"' and vch_licence_type='"+dt.getLicence_type()+"' ";
	
	pstmt5 = conn1.prepareStatement(update1);
	 if(dt.getLicence_type().equals("FL1"))
	{
		 serialno = getSerialFl3Bottle(new Double(dt.getBottles()).longValue(),new Date());
		 
		 pstmt5.setLong(3,(getcaseNoFl3(dt.getS_box(),new Date())));
		
	}else if(dt.getLicence_type().equals("FL1A"))
	{
		 serialno = getSerialFl3Bottle(new Double(dt.getBottles()).longValue(),new Date());
		 pstmt5.setLong(3,(getcaseNoFl3a(dt.getS_box(),new Date())));
		
	}
	
	pstmt5.setLong(1, serialno);
	pstmt5.setLong(2, serialno+new Double(dt.getBottles()).longValue());
	pstmt5.setDate(4, Utility.convertUtilDateToSQLDate(new Date()));
	pstmt5.setInt(5, planid);
	
	
	
	status5 = pstmt5.executeUpdate();
	
	
	
	String sql = " INSERT INTO bottling_unmapped.disliry_unmap_fl3( "+
			    "  date_plan, etin, serial_no_start, serial_no_end, casecode,plan_id,unit_id) "+
			    ///" VALUES (?, ?, ?, ?, ?, "+dt.getPlanid()+",?)";;
			    " VALUES (?, ?, ?, ?, ?, "+planid+",?)";;


	
	String fl3a =     " INSERT INTO bottling_unmapped.disliry_unmap_fl3a(  "+
			          "   date_plan, etin,serial_no_start, serial_no_end, casecode,plan_id,unit_id) "+
			          "  VALUES (?, ?, ?, ?, ?, "+planid+",?)";;

	

	String cl=     " INSERT INTO bottling_unmapped.disliry_unmap_cl(  "+
	          "   date_plan, etin,serial_no_start, serial_no_end, casecode,plan_id,unit_id) "+
	          "  VALUES (?, ?, ?, ?, ?, "+dt.getPlanid()+",?)";;

	
	
	
	
	
	String update = "UPDATE distillery.rollover_fl1_fl1a_18_19 "
			+ " SET   finalized_flag='F' ,finalized_date=?"
			////+ "WHERE int_distillery_id=? and int_brand_id=? and int_pack_id=? and plan_dt=? and seq="+planid+"";
			+ "WHERE int_distillery_id=? and brand_id=? and package_id=? and plandate=? and planid=? ";


		//gtinNo = getBrandPackagingGtinNo(action, dt);
		gtinNo = dt.getGtin_no();
	  //serialno = dt.getSeqfrm();
		serialno = getSeqfrm(action, dt,conn1);
		
		 
		if (!gtinNo.equals("") && serialno != 0 && status5 > 0) {
		
			
			pstmt1 = conn1.prepareStatement(update);

			pstmt1.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			
			pstmt1.setInt(2, action.getUnit_id());
			pstmt1.setInt(3, dt.getBrand_id());
			pstmt1.setInt(4, dt.getPackg_id());
			pstmt1.setDate(5, Utility.convertUtilDateToSQLDate(new Date()));
			pstmt1.setInt(6, planid);
			status = pstmt1.executeUpdate();

			if (dt.getLicence_type().equals("FL1")
					&& status > 0) {
				status = 0;
				pstmt1 = conn.prepareStatement(sql);
				for (int i = 0; i < dt.getS_box(); i++) {
					///caseno = dt.getCaseseq()+i;
					caseno = getCaseseq(action, dt,conn1)+i;
					

					pstmt1.setDate(1, Utility.convertUtilDateToSQLDate(new Date()));
					pstmt1.setString(2, gtinNo);
					pstmt1.setString(3, StringUtils.leftPad(String.valueOf(serialno), 8, '0'));
					pstmt1.setInt(6, action.getEtin_unit_id());
					
					Random rand1 = new Random();
					//int n1 = 10+rand1.nextInt(90) ;
					int n1=		rand1.nextInt((40 - 31) + 1) + 31;
					pstmt1.setString(5,n1+""+StringUtils.leftPad(String.valueOf(caseno), 6, '0')+getCheckDigit(n1+""+StringUtils.leftPad(String.valueOf(caseno), 6, '0')) );
					
						pstmt1.setString(4,	 StringUtils.leftPad(String.valueOf(serialno+(dt.getBottles() / dt.getS_box()) - 1), 8, '0'));
						serialno += (dt.getBottles() / dt.getS_box());
					
					pstmt1.addBatch();
					// status=pstmt1.executeUpdate();
				}
			}
			if (dt.getLicence_type().equals("FL1A")
					&& status > 0) {
				status = 0;
				pstmt1 = conn.prepareStatement(fl3a);
				for (int i = 0; i < dt.getS_box(); i++) {

					//caseno = dt.getCaseseq()+i;   getSeqfrm(action, dt);
					caseno = getCaseseq(action, dt,conn1)+i;
					

					pstmt1.setDate(1, Utility.convertUtilDateToSQLDate(new Date()));
					pstmt1.setString(2, gtinNo);
					pstmt1.setString(3, StringUtils.leftPad(String.valueOf(serialno), 8, '0'));
					pstmt1.setInt(6, action.getEtin_unit_id());
					
					Random rand1 = new Random();
					//int n1 = 10+rand1.nextInt(90) ;
					int n1=		rand1.nextInt((60 - 51) + 1) + 51;
					pstmt1.setString(5,n1+""+StringUtils.leftPad(String.valueOf(caseno), 6, '0')+getCheckDigit(n1+""+StringUtils.leftPad(String.valueOf(caseno), 6, '0')) );
					
				
						pstmt1.setString(4,	 StringUtils.leftPad(String.valueOf(serialno+(dt.getBottles() / dt.getS_box()) - 1), 8, '0'));
						serialno += (dt.getBottles() / dt.getS_box());
					
					pstmt1.addBatch();
					// status=pstmt1.executeUpdate();}
				}
			}
			
			int[] status1 = pstmt1.executeBatch();
			if (status1.length > 0) {
				status = 0;
				boolean flag1 = write(dt, action, conn,planid);
				//boolean flag1 = writeCsv(dt, action, conn);
				
				if ( flag1) {
					status = 1;
				} else {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("Excel Not Generated",
									"Excel Not Generated"));
				}
			}
			if (status > 0) {
				
				String update11 = "UPDATE distillery.rollover_fl1_fl1a_18_19 "
						+ " SET   excel_flg='T' " 
						
						+ "WHERE int_distillery_id='"+action.getUnit_id()+"' and brand_id='"+dt.getBrand_id()+"' and package_id='"+dt.getPackg_id()+"'  " +
							
										" and license_no='"+dt.getLicence_no()+"' and size='"+dt.getSize()+"' and box='"+dt.getS_box()+"' and vch_licence_type='"+dt.getLicence_type()+"' ";
				
					
				pstmt6 = conn1.prepareStatement(update11);
				status6 = pstmt6.executeUpdate();
			}
				if (status6 > 0) {
				String update12 = " insert into distillery.mst_bottling_plan_20_21 (int_distillery_id, int_brand_id, int_pack_id, plan_dt, " +
						          " seq,finalized_flag ,  finalized_date,int_planned_bottles,int_boxes,int_liquor_type,vch_license_type,int_quantity,comment_) "
						+ " values('"+action.getUnit_id()+"','"+dt.getBrand_id()+"','"+dt.getPackg_id()+"','"+Utility.convertUtilDateToSQLDate(new Date())+"' ," +
								" '"+planid+"','F', '"+Utility.convertUtilDateToSQLDate(new Date())+"','"+dt.getBottles()+"','"+dt.getS_box()+"','1','"+dt.getLicence_type()+"','"+dt.getQuantity_ml()+"','Rollover-distillery.rollover_fl1_fl1a_18_19' )  ";
				
					
				pstmt7 = conn1.prepareStatement(update12);
				status7 = pstmt7.executeUpdate();
				}
				
				if (status7 > 0) {
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
	}

	catch (Exception e) {

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
	}}
public boolean write(Rollover_Stock_FL2D_18_19DT dt, Rollover_Stock_FL2D_18_19Action action, Connection conn,int planid) {
	String sql_fl3_update="update bottling_unmapped.disliry_unmap_fl3 set bottle_code=? where unit_id= ? and plan_id=? and date_plan=? and  etin=? and  casecode=?";
	String sql_fl3a_update="update bottling_unmapped.disliry_unmap_fl3a set bottle_code=? where unit_id =? and plan_id=? and date_plan=? and  etin=? and  casecode=?";
	
	

	String fl3 = 
			
			"	select to_char(y.gs, 'fm00000000')as GENERATE_SERIES ,y.date_plan as dispatch_date , y.etin as gtin_no,   to_char(y.unit_id,'fm000')as unit_id,                                                                                        "+
			"	to_char(y.serial_no_start::numeric, 'fm00000000') as serial_no_start,to_char(y.serial_no_end::numeric, 'fm00000000') as serial_no_end,                      "+
			"	to_char(y.casecode::numeric , 'fm000000000')as case_no,y.plan_id from(                                                                                                 "+
			"	select  GENERATE_SERIES(x.serial_no_start::numeric ,x.serial_no_end::numeric ) as gs ,x.serial_no_start ,x.serial_no_end,                                   "+
			"	x.casecode,x.etin ,x.date_plan  ,x.unit_id  ,x.plan_id                                                                                                                           "+
			"	from (                                                                                                                                                      "+
			"	SELECT unit_id,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass, fl11_date, fl36gatepass, fl36_date, boxing_seq, remark, serial_no_start, serial_no_end "+
			"	FROM bottling_unmapped.disliry_unmap_fl3 where etin=? and date_plan=? and plan_id="+planid+")x)y order by to_char(y.casecode::numeric , 'fm000000000')::numeric";                                                                                              
				
			
			String fl3a =
	
"	select to_char(y.gs, 'fm00000000')as GENERATE_SERIES ,y.date_plan as dispatch_date , y.etin as gtin_no,   to_char(y.unit_id,'fm000')as unit_id,                                                                                        "+
"	to_char(y.serial_no_start::numeric, 'fm00000000') as serial_no_start,to_char(y.serial_no_end::numeric, 'fm00000000') as serial_no_end,                      "+
"	to_char(y.casecode::numeric , 'fm000000000')as case_no,y.plan_id from(                                                                                                 "+
"	select  GENERATE_SERIES(x.serial_no_start::numeric ,x.serial_no_end::numeric ) as gs ,x.serial_no_start ,x.serial_no_end,                                   "+
"	x.casecode,x.etin ,x.date_plan  ,x.unit_id ,x.plan_id                                                                                                                            "+
"	from (                                                                                                                                                      "+
"	SELECT unit_id,plan_id, date_plan, etin, casecode, bottle_code, fl11gatepass, fl11_date, fl36gatepass, fl36_date, boxing_seq, remark, serial_no_start, serial_no_end "+
"	FROM bottling_unmapped.disliry_unmap_fl3a where etin=? and date_plan=? and plan_id="+planid+")x)y order by to_char(y.casecode::numeric , 'fm000000000')::numeric";                                                                                              

	

	String relativePath = Constants.JBOSS_SERVER_PATH
			+ Constants.JBOSS_LINX_PATH;
	FileOutputStream fileOut = null;

	PreparedStatement pstmt = null;
	PreparedStatement pstmt1 = null;
	String bottle_code="";
	int count=1;
	
	ResultSet rs = null;
	long start = 0;
	long end = 0;
	boolean flag = false;
	long k = 0;
	String noOfUnit = "";
	String date = null;

	try {

		

	
		if (dt.getLicence_type().equals("FL1")) {

			pstmt = conn.prepareStatement(fl3);

			
			
			pstmt.setString(1, dt.getGtin_no());
			pstmt.setDate(2, Utility.convertUtilDateToSQLDate(new Date()));
			rs = pstmt.executeQuery();
			System.out.println("excecute query fl1");

		}
		if (dt.getLicence_type().equals("FL1A")) {

			pstmt = conn.prepareStatement(fl3a);

			
			
			pstmt.setString(1, dt.getGtin_no());
			pstmt.setDate(2, Utility.convertUtilDateToSQLDate(new Date()));
			rs = pstmt.executeQuery();
			System.out.println("excecute query fl1a");
		}

		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet worksheet = workbook.createSheet("Barcode Report");
		
		CellStyle unlockedCellStyle = workbook.createCellStyle();
		unlockedCellStyle.setLocked(false);

		
		worksheet.protectSheet("agristat");
		worksheet.setColumnWidth(0, 3000);
		worksheet.setColumnWidth(1, 8000);
		worksheet.setColumnWidth(2, 8000);
		worksheet.setColumnWidth(3, 8000);
		worksheet.setColumnWidth(4, 6000);
		
		XSSFRow rowhead0 = worksheet.createRow((int) 0);
		
		XSSFCell cellhead0 = rowhead0.createCell((int) 0);
		cellhead0.setCellValue("Barcode Report");

		rowhead0.setHeight((short) 700);
		cellhead0.setCellStyle(unlockedCellStyle);
		XSSFCellStyle cellStyl = workbook.createCellStyle();
		

		cellStyl = workbook.createCellStyle();
		XSSFFont hSSFFont = workbook.createFont();
		hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
		hSSFFont.setFontHeightInPoints((short) 12);
		hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		hSSFFont.setColor(HSSFColor.GREEN.index);
		cellStyl.setFont(hSSFFont);
		cellhead0.setCellStyle(cellStyl);

		
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

			 System.out.println("i==========="+i++);
			Date dat = Utility.convertSqlDateToUtilDate(rs
					.getDate("dispatch_date"));

			DateFormat formatter;

			formatter = new SimpleDateFormat("yyMMdd");
			date = formatter.format(dat);

			String lic = dt.getLicence_no().replaceAll("/", "");

			// System.out.println("while in");serial_no_start, serial_no_end
			//start = rs.getLong("serial_no_start");
			//end = rs.getLong("serial_no_end");

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
			noOfUnit=StringUtils.leftPad(String.valueOf(dt.getBottles() / dt.getS_box()), 3, '0');
			

			int inside_ouside_brand=getInsideOutsideBrand(rs.getString("gtin_no"));
			if(noOfUnit.length()==2){
				
				
				
				cellD1.setCellValue( rs.getString("gtin_no")+""+inside_ouside_brand+""+ StringUtils.leftPad(String.valueOf(rs.getString("unit_id")), 3, '0')
						+""+ date +""+ "1" +""+ StringUtils.leftPad(String.valueOf(noOfUnit), 3, '0')+""+ rs.getString("case_no")); 
				
				}
				else if(noOfUnit.length()==1)
				{
					cellD1.setCellValue( ""+rs.getString("gtin_no")+""+inside_ouside_brand+""+  StringUtils.leftPad(String.valueOf(rs.getString("unit_id")), 3, '0')
							+""+ date +""+ "1" +""+ StringUtils.leftPad(String.valueOf(noOfUnit), 3, '0')+""+ rs.getString("case_no"));
				}else{
					
					cellD1.setCellValue( rs.getString("gtin_no")+""+inside_ouside_brand+""+ StringUtils.leftPad(String.valueOf(rs.getString("unit_id")), 3, '0')
							+""+ date +""+ "1" +""+ StringUtils.leftPad(String.valueOf(noOfUnit.substring(0,3)), 3, '0')+""+ rs.getString("case_no"));
					
				}
			 

			XSSFCell cellE1 = row1.createCell((int) 4);
			
			Random rand = new Random();
			int n = 10+rand.nextInt(90) ;
		
			 cellE1.setCellValue(rs.getString("gtin_no")
					+ ""+
					 date + StringUtils.leftPad(
								String.valueOf(rs.getString("unit_id")), 3, '0')
								+ "" + "" + n + ""
					+ rs.getString("GENERATE_SERIES") + ""
					+ getCheckDigit(rs.getString("GENERATE_SERIES")));
			 
			 
			 
			 
			 
			 
			 
			 
			 bottle_code=bottle_code+"|"+n + ""+ rs.getString("GENERATE_SERIES") + ""+ getCheckDigit(rs.getString("GENERATE_SERIES"));	
				
				if (dt.getLicence_type().equals("FL1")) {

					pstmt1 = conn.prepareStatement(sql_fl3_update);

					pstmt1.setString(1, bottle_code);
					pstmt1.setInt(2, Integer.parseInt(rs.getString("unit_id")));
					pstmt1.setInt(3,rs.getInt("plan_id") );
					pstmt1.setDate(4,rs.getDate("dispatch_date") );
					pstmt1.setString(5,rs.getString("gtin_no") );
					pstmt1.setString(6,rs.getString("case_no") );
					
					 pstmt1.executeUpdate();

				}
				if (dt.getLicence_type().equals("FL1A")) {

					pstmt1 = conn.prepareStatement(sql_fl3a_update);
 pstmt1.setString(1, bottle_code);
					pstmt1.setInt(2, Integer.parseInt(rs.getString("unit_id")));
					pstmt1.setInt(3,rs.getInt("plan_id") );
					pstmt1.setDate(4,Utility.convertUtilDateToSQLDate(rs.getDate("dispatch_date") ));
					pstmt1.setString(5,rs.getString("gtin_no") );
					pstmt1.setString(6,rs.getString("case_no") );
                    pstmt1.executeUpdate();


				}

				
if(count==(dt.getBottles() / dt.getS_box()))
						
						{
					
					 
					count=0;
					bottle_code="";
						}
				
				
				count++;
					
				 
}
		fileOut = new FileOutputStream(relativePath + "//ExciseUp//RolloverStock//Excel//"
				+ planid + dt.getGtin_no() + "" + date + ".xls");

		XSSFRow row1 = worksheet.createRow((int) k + 1);

		// APoolFinancialReportDataTable dt=(APoolFinancialReportDataTable)
		// list.get(i-2);

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
public int maxId()
{

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String query = " SELECT max(seq) as id FROM distillery.mst_bottling_plan_20_21 ";
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

public synchronized long getSerialFl3Bottle(long noOfSequenc, Date date) {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	long serialNo = 0;
	long currseq = 0;

	try {
		conn = ConnectionToDataBase.getConnection20_21();

		
		
		Date today = date;
		SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyy");
		String currentdate=	 sdf.format(today);
		pstmt2=conn.prepareStatement("CREATE SEQUENCE IF NOT EXISTS public.fl3_manual_bottle_code_"+currentdate);
		pstmt2.executeUpdate();
		
		
		
		
		
		
		
		
		
		pstmt = conn.prepareStatement("select     nextval('public.fl3_manual_bottle_code_"+currentdate+"')");
		rs = pstmt.executeQuery();
		if (rs.next()) {
			serialNo = rs.getLong(1);
			if (serialNo == 0) {
				serialNo = serialNo + 1;
			}
			//System.out.println("noOfSequenc " + noOfSequenc);

			pstmt1 = conn
					.prepareStatement("ALTER SEQUENCE public.fl3_manual_bottle_code_"+currentdate+" RESTART WITH "
							+ (noOfSequenc + serialNo+1));

			
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
public synchronized long getcaseNoFl3(int no,Date date) {
	

	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	long serialNo = 0;
	long currseq = 0;

	try {
		conn = ConnectionToDataBase.getConnection20_21();

		Date today = date;
		SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyy");
		String currentdate=	 sdf.format(today);
		pstmt2=conn.prepareStatement("CREATE SEQUENCE IF NOT EXISTS public.fl3_manual_case_code_"+currentdate);
		pstmt2.executeUpdate();
		
		
		
		
		
		
		
		
		
		pstmt = conn.prepareStatement("select     nextval('public.fl3_manual_case_code_"+currentdate+"')");
		rs = pstmt.executeQuery();
		if (rs.next()) {
			serialNo = rs.getLong(1);
			if (serialNo == 0) {
				serialNo = serialNo;
			}
			pstmt1 = conn
					.prepareStatement("ALTER SEQUENCE public.fl3_manual_case_code_"+currentdate+" RESTART WITH "
							+ (no + serialNo));

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


public synchronized long getcaseNoFl3a(int no,Date date) {
	

	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	long serialNo = 0;
	long currseq = 0;

	try {
		conn = ConnectionToDataBase.getConnection20_21();

		
		Date today = date;
		SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyy");
		String currentdate=	 sdf.format(today);
		pstmt2=conn.prepareStatement("CREATE SEQUENCE IF NOT EXISTS public.fl3a_manual_case_code_"+currentdate);
		pstmt2.executeUpdate();
		
		
		
		
		
		
		pstmt = conn.prepareStatement(" select     nextval('public.fl3a_manual_case_code_"+currentdate+"')");
		rs = pstmt.executeQuery();
		if (rs.next()) {
			serialNo = rs.getLong(1);
			if (serialNo == 0) {
				serialNo = serialNo;
			}
			pstmt1 = conn
					.prepareStatement("ALTER SEQUENCE public.fl3a_manual_case_code_"+currentdate+" RESTART WITH "
							+ (no + serialNo));

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
public long getSeqfrm(Rollover_Stock_FL2D_18_19Action action,
		Rollover_Stock_FL2D_18_19DT dt,Connection conn) {

	long Seqfrm = 0;
	String sql = "select seqfrom from   distillery.rollover_distillery_18_19  "
			+ "where   int_distillery_id=? and brand_id=? and package_id=? and vch_licence_type=? and license_no=? ";
	
	String sql = "select seqfrom from   distillery.rollover_fl1_fl1a_18_19  "
			+ "where   int_distillery_id='"+action.getUnit_id()+"' and brand_id='"+dt.getBrand_id()+"' and package_id='"+dt.getPackg_id()+"' and vch_licence_type='"+dt.getLicence_type()+"' and license_no='"+dt.getLicence_no()+"' ";
	System.out.println("====getSeqfrm=="+sql);
	//Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	System.out.println("brandId" + dt.getBrandId() + "  package_id"
			+ dt.getPackagingId() + " b.quantity "
			+ Integer.parseInt(dt.getShowDataTable_Quntity())
			+ " vch_license_type " + dt.getShowDataTable_LicenceType()
			+ " a.distillery_id " + action.getDistillery_id());

	try {

		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, action.getUnit_id());
		pstmt.setInt(2, dt.getBrand_id());
		pstmt.setInt(3, dt.getPackg_id());
		pstmt.setString(4, dt.getLic_type());
		pstmt.setString(5, dt.getLic_no());
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Seqfrm = rs.getLong("seqfrom");
			System.out.println("====getSeqfrm avlue=="+Seqfrm);
			
		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {

		try {
			if (rs != null)
				rs.close();

			if (pstmt != null)
				pstmt.close();

			

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}
	}

	return Seqfrm;

}

public static int getCheckDigit(String n)
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
		  // System.out.println("evennnn"+ar[i]);
		   odd =odd+ Character.getNumericValue(ar[i]);
	   }else{
		 //  System.out.println("oddddddd"+ar[i]);
		   even=even+Character.getNumericValue(ar[i]);
	   }
	  
	}
	
	sum=(odd*3)+even;
	 
	if(sum%10!=0)
	{
		
		//System.out.println("SUMM    "+sum);
		    checkDigit= (10 - sum % 10);
		 //   System.out.println("checkDigit  "+checkDigit);
	}
	
	
return checkDigit;
}


public int getInsideOutsideBrand(String etin)
{
	int i=0;
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
    String  sql=" select domain from distillery.brand_registration_20_21 a,distillery.packaging_details_20_21 b "+
                 " where b.brand_id_fk=a.brand_id and b.code_generate_through=?";
	
	
	try{
		conn=ConnectionToDataBase.getConnection();
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, etin);
		rs=pstmt.executeQuery();
		if(rs.next())
		{
			
			
			if(rs.getString("domain")!=null)
			{
			if(rs.getString("domain").equalsIgnoreCase("EXP"))
			{
				i=2;
			}
			}else {
				i=1;
			}
			
			
			
		}
		
	}catch(Exception e)
	{
		e.printStackTrace();
	}finally{
		
		
		try{
			if(conn!=null)conn.close();
			if(pstmt!=null)pstmt.close();
			if(rs!=null)rs.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	return i;
	
}
public long getCaseseq(Rollover_Stock_FL2D_18_19Action action,
		Rollover_Stock_FL2D_18_19DT dt,Connection conn) {

	long caseseq = 0;
	String sql = "select caseseq from   distillery.rollover_fl1_fl1a_18_19  "
			+ "where   int_distillery_id=? and brand_id=? and package_id=? and vch_licence_type=? and license_no=? ";

	///Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	System.out.println("brandId" + dt.getBrandId() + "  package_id"
			+ dt.getPackagingId() + " b.quantity "
			+ Integer.parseInt(dt.getShowDataTable_Quntity())
			+ " vch_license_type " + dt.getShowDataTable_LicenceType()
			+ " a.distillery_id " + action.getDistillery_id());

	try {
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, action.getUnit_id());
		pstmt.setInt(2, dt.getBrand_id());
		pstmt.setInt(3, dt.getPackg_id());
		pstmt.setString(4, dt.getLicence_type());
		pstmt.setString(5, dt.getLicence_no());
		rs = pstmt.executeQuery();
		while (rs.next()) {
			caseseq = rs.getLong("caseseq");
		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {

		try {
			if (rs != null)
				rs.close();

			if (pstmt != null)
				pstmt.close();

			

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}
	}

	return caseseq;

}
*/



public void dataFinalize(Rollover_Stock_FL2D_18_19Action action,
		Rollover_Stock_FL2D_18_19DT dt) {
	Connection conn = null;
	Connection conn1 = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	PreparedStatement pstmt3 = null;
	PreparedStatement pstmt4 = null;
	PreparedStatement pstmt5 = null;
	PreparedStatement pstmt6 = null;
	PreparedStatement pstmt7 = null;
	String gtinNo = "";
	long boxsize = 0;
	long caseno = 0;
	int status=0;
	int status5=0;
	int status6=0;
	int status7=0;
	int planid= this.getMax_id();
	


	String sql=
			
			
			
			        "INSERT INTO bottling_unmapped.fl2d( "+
					"date_plan,etin,serial_no_start, serial_no_end,  casecode,plan_id,unit_id) "+
					"VALUES (?, ?, ?, ?, ?, ?,?)";

	String update ="UPDATE fl2d.rollover_fl2d_18_19 "
			+ " SET   finalized_flag='F' ,finalized_date=?"
			////+ "WHERE int_distillery_id=? and int_brand_id=? and int_pack_id=? and plan_dt=? and seq="+planid+"";
			+ "WHERE int_fl2d_id=? and brand_id=? and package_id=? and plandate=? and planid=? ";

			
			
			/* "UPDATE fl2d.mst_stock_receive_19_20 "
			+ " SET   finalized_flag='F' ,finalized_date=? "
			+ "WHERE seq=? and permit_no=? and int_brand_id=? and int_pack_id=?";*/

	try {
	
		gtinNo=dt.getGtin_no();
		

		System.out
				.println("new Double(dt.getShowDataTable_PlanNoOfBottling()).longValue()"
						+ new Double(dt.getBottles())
								.longValue());
		
		
		// long	serialno = dt.getSeqfrm();
		
		long	serialno=getSerialNoFl2D(new Double(dt.getBottles()).longValue(),new Date());
		
		
		
		//////System.out.println("gtinNo" + gtinNo + "seqqqqqqqqqqqqqqqqqqqqqqqq"+ serialno);
		if (!gtinNo.equals("") && serialno != 0) {
			conn = ConnectionToDataBase.getConnection19_20();
			conn1 = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			conn1.setAutoCommit(false);
			
			String update1 = "UPDATE fl2d.rollover_fl2d_18_19 "
					+ " SET   seqfrom=?,seqto=?,caseseq=?, plandate=?, planid=? " 
					
					+ "WHERE int_fl2d_id='"+action.getUnit_id()+"' and brand_id='"+dt.getBrand_id()+"' and package_id='"+dt.getPackg_id()+"'  " +
						
									" and license_no='"+dt.getLicence_no()+"' and size='"+dt.getSize()+"' and box='"+dt.getS_box()+"' and vch_licence_type='"+dt.getLicence_type()+"' ";
			
			pstmt5 = conn1.prepareStatement(update1);
		    pstmt5.setLong(1, 0);
			pstmt5.setLong(2, 0);
		    pstmt5.setLong(3,0);
			pstmt5.setDate(4, Utility.convertUtilDateToSQLDate(new Date()));
			pstmt5.setInt(5, planid);
			status5 = pstmt5.executeUpdate();
			
			pstmt1 = conn1.prepareStatement(update);
			pstmt1.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			pstmt1.setInt(2, action.getUnit_id());
			pstmt1.setInt(3, dt.getBrand_id());
			pstmt1.setInt(4, dt.getPackg_id());
			pstmt1.setDate(5, new java.sql.Date(System.currentTimeMillis()));
			pstmt1.setInt(6, planid);
			
			status = pstmt1.executeUpdate();

			if (dt.getLicence_type().equals("FL2D")
					&& status > 0) {
				status = 0;
				
				 
				//////System.out.println("dt.getShowDataTable_Quntity()"+dt.getShowDataTable_Quntity()+"  dt.getShowDataTable_NoOfBoxes()"+dt.getShowDataTable_NoOfBoxes());
				pstmt1 = conn.prepareStatement(sql);
				//pstmt2 = conn.prepareStatement(unmapped_sql);
				///for (int i = 0; i < Long.parseLong(dt.getS_box()); i++) {
				for (int i = 0; i < dt.getS_box(); i++) {
					//caseno = dt.getCaseseq()+i;
                  
					caseno =getcaseNoFl2d(new Date());
                    
					int newsize=new Double((Long.parseLong(String.valueOf(dt.getBottles()))/Long.parseLong(String.valueOf(dt.getS_box())))).intValue();
					
                    pstmt1.setDate(1, new java.sql.Date(System.currentTimeMillis()));
					pstmt1.setString(2, gtinNo);
					pstmt1.setString(3, StringUtils.leftPad(String.valueOf(serialno), 8, '0'));
					 pstmt1.setString(4,StringUtils.leftPad(String.valueOf(serialno+(newsize-1)), 8, '0') );
					 Random rand1 = new Random();
					 int n1 = 10+rand1.nextInt(90) ;
                     pstmt1.setString(5, n1+""+StringUtils.leftPad(String.valueOf(caseno), 6, '0')+getCheckDigit(n1+""+StringUtils.leftPad(String.valueOf(caseno), 6, '0')));
					pstmt1.setInt(6, planid);
					pstmt1.setInt(7, action.getEtin_unit_id());
				   
					serialno+=newsize;
					pstmt1.addBatch();
					
				}
			}
			
			int[] status1=pstmt1.executeBatch();
			
			if (status1.length > 0) {
				status = 0;
				boolean flag = write(dt, action, conn,planid);

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
				
				String update11 = "UPDATE fl2d.rollover_fl2d_18_19 "
						+ " SET   excel_flg='T' " 
						
						+ "WHERE int_fl2d_id='"+action.getUnit_id()+"' and brand_id='"+dt.getBrand_id()+"' and package_id='"+dt.getPackg_id()+"'  " +
							
										" and license_no='"+dt.getLicence_no()+"' and size='"+dt.getSize()+"' and box='"+dt.getS_box()+"' and vch_licence_type='"+dt.getLicence_type()+"' ";
				
					
				pstmt6 = conn1.prepareStatement(update11);
				status6 = pstmt6.executeUpdate();
			}
				
				
				if (status6 > 0) {

			//if (status > 0) {

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
	}

	catch (Exception e) {

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
			if (pstmt5 != null)
				pstmt5.close();
			if (pstmt6 != null)
				pstmt6.close();
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

public int getMax_id() {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	int id = 0;
	//String query = "SELECT max(seq) as id FROM fl2d.mst_stock_receive_19_20";
	
	String query = "select nextval('fl2d.mst_stock_receive_sequence_19_20')as id";
	//////System.out.println("getMax_id"+query);
	try {
		con = ConnectionToDataBase.getConnection();
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		if (rs.next()) {
			id = rs.getInt("id");
		}
	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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
	//////System.out.println("id="+id);
	return id + 1;
}




public boolean write(Rollover_Stock_FL2D_18_19DT dt,
		Rollover_Stock_FL2D_18_19Action action, Connection conn,int planid) {

	//////System.out.println("excel innn");
	 String sql_fl3_update="update bottling_unmapped.fl2d set bottle_code=? where unit_id= ? and plan_id=? and date_plan=? and  etin=? and  casecode=?";
	

	String fl3 =
	
	"	select to_char(y.gs, 'fm00000000')as GENERATE_SERIES , y.dispatch_date, y.gtin_no,y.unit_id,"
	+ "	  y.serial_no_start, y.serial_no_end , "
	+ "	to_char(y.case_no::numeric , 'fm000000000')as case_no,y.plan_id from( "
	+ "	select x.unit_id, GENERATE_SERIES(x.serial_no_start::numeric ,x.serial_no_end::numeric ) as gs ,x.serial_no_start ,x.serial_no_end, "
	+ "	x.case_no,x.dispatch_date,x.gtin_no,x.plan_id "
	+ "	from ( "
	+ "	SELECT plan_id, unit_id,date_plan as dispatch_date, etin as gtin_no, serial_no_start, serial_no_end,  casecode as case_no "
	+ "	FROM bottling_unmapped.fl2d a "
	+ "	where  date_plan=?   and etin=? and plan_id=?)x)y";

	
	String relativePath = Constants.JBOSS_SERVER_PATH
			+ Constants.JBOSS_LINX_PATH;
	FileOutputStream fileOut = null;
String bottle_code="";
int count=1;
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

		

		if (dt.getLicence_type().equals("FL2D")) {

			pstmt = conn.prepareStatement(fl3);

			
			
			pstmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			
			pstmt.setString(2, dt.getGtin_no());
			pstmt.setInt(3, planid);
			rs = pstmt.executeQuery();
			//////System.out.println("excecute query fl3");

		}
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet worksheet = workbook.createSheet("Barcode Report");
	
		CellStyle unlockedCellStyle = workbook.createCellStyle();
		unlockedCellStyle.setLocked(false);

		// Sheet sheet = workbook.createSheet();
		worksheet.protectSheet("agristat");
		worksheet.setColumnWidth(0, 3000);
		worksheet.setColumnWidth(1, 8000);
		worksheet.setColumnWidth(2, 8000);
		worksheet.setColumnWidth(3, 8000);
		worksheet.setColumnWidth(4, 6000);
		

		XSSFRow rowhead0 = worksheet.createRow((int) 0);
		
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

			// ////System.out.println("i==========="+i++);
			Date dat = Utility.convertSqlDateToUtilDate(rs
					.getDate("dispatch_date"));

			DateFormat formatter;

			formatter = new SimpleDateFormat("yyMMdd");
			date = formatter.format(dat);

			String lic = dt.getLicence_no().replaceAll("/", "");

			// ////System.out.println("while in");serial_no_start, serial_no_end
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
			noOfUnit=StringUtils.leftPad(String.valueOf(dt.getBottles() / dt.getS_box()), 3, '0');
			
			if(noOfUnit.length()==2){
				/*cellD1.setCellValue("01" + rs.getString("gtin_no") + "13"
						+ date + "37" + noOfUnit + "21"
						+ rs.getString("case_no"));*/
				
				cellD1.setCellValue( rs.getString("gtin_no")+""+1+""+ StringUtils.leftPad(String.valueOf(rs.getString("unit_id")), 3, '0')
						+""+ date +""+ "1" +""+ StringUtils.leftPad(String.valueOf(noOfUnit), 3, '0')+""+ rs.getString("case_no")); 
				
				}
				else if(noOfUnit.length()==1)
				{
					cellD1.setCellValue( ""+rs.getString("gtin_no")+""+1+""+ StringUtils.leftPad(String.valueOf(rs.getString("unit_id")), 3, '0')
							+""+ date +""+ "1" +""+ StringUtils.leftPad(String.valueOf(noOfUnit), 3, '0')+""+ rs.getString("case_no"));
				}else{
					
					cellD1.setCellValue( rs.getString("gtin_no")+""+1+""+ StringUtils.leftPad(String.valueOf(rs.getString("unit_id")), 3, '0')
							+""+ date +""+ "1" +""+ StringUtils.leftPad(String.valueOf(noOfUnit.substring(0,3)), 3, '0')+""+ rs.getString("case_no"));
					
				}
			 

			XSSFCell cellE1 = row1.createCell((int) 4);
			
			Random rand = new Random();
			int n = 10+rand.nextInt(90) ;
		
			 cellE1.setCellValue(rs.getString("gtin_no")
						+ ""+
						 date + StringUtils.leftPad(
									String.valueOf(rs.getString("unit_id")), 3, '0')
									+ "" + "" + n + ""
						+ rs.getString("GENERATE_SERIES") + ""
						+ getCheckDigit(rs.getString("GENERATE_SERIES")));
			 
			 
			 
			 
			 
			 
			 bottle_code=bottle_code+"|"+n + ""+ rs.getString("GENERATE_SERIES") + ""+ getCheckDigit(rs.getString("GENERATE_SERIES"));	
				
				

					pstmt1 = conn.prepareStatement(sql_fl3_update);

					pstmt1.setString(1, bottle_code);
					pstmt1.setString(2, String.valueOf(Integer.parseInt(rs.getString("unit_id"))));
					pstmt1.setInt(3,rs.getInt("plan_id") );
					pstmt1.setDate(4,rs.getDate("dispatch_date") );
					pstmt1.setString(5,rs.getString("gtin_no") );
					pstmt1.setString(6,rs.getString("case_no") );
					
					 pstmt1.executeUpdate();

				
			 
			 
			 
				if(count==(dt.getBottles() / dt.getS_box()))
					
				{
			
			System.out.println("come inn nn time ");
			count=0;
			bottle_code="";
				}
		
		
		count++;
			 
			 
			 
			 
			 
			 
			

		}
		fileOut = new FileOutputStream(relativePath + "//ExciseUp//RolloverStock//Excel//"+planid+""
				+ dt.getGtin_no() + "" + date + ".xls");
System.out.println("=====file name===="+planid+""+ dt.getGtin_no() + "" + date + ".xls");
		XSSFRow row1 = worksheet.createRow((int) k + 1);

		// APoolFinancialReportDataTable dt=(APoolFinancialReportDataTable)
		// list.get(i-2);

		XSSFCell cellA1 = row1.createCell((int) 0);
		cellA1.setCellValue("End");

		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
		flag = true;

	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
		
		//////System.out.println("xls2" + e.getMessage());
		e.printStackTrace();
	}

	return flag;
}






public static int getCheckDigit(String n)
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
		  // ////System.out.println("evennnn"+ar[i]);
		   odd =odd+ Character.getNumericValue(ar[i]);
	   }else{
		 //  ////System.out.println("oddddddd"+ar[i]);
		   even=even+Character.getNumericValue(ar[i]);
	   }
	  
	}
	
	sum=(odd*3)+even;
	//////System.out.println("summm"+sum);
	//////System.out.println("even"+even);
	//////System.out.println("odd sum"+odd);
	if(sum%10!=0)
	{
		
		//////System.out.println("SUMM    "+sum);
		    checkDigit= (10 - sum % 10);
		 //   ////System.out.println("checkDigit  "+checkDigit);
	}
	
	
return checkDigit;
}







public synchronized long getcaseNoFl2d(Date date) {
	

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
		SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyy");
		String currentdate=	 sdf.format(today);
		pstmt2=conn.prepareStatement("CREATE SEQUENCE IF NOT EXISTS public.fl_2d_serial_seq_"+currentdate);
		pstmt2.executeUpdate();
		pstmt = conn.prepareStatement(" select     nextval('public.fl_2d_serial_seq_"+currentdate+"')");
		rs = pstmt.executeQuery();
		if (rs.next()) {
			serialNo = rs.getInt(1);
			if (serialNo == 0) {
				serialNo = serialNo;
			}
			
			/*pstmt=conn.prepareStatement("ALTER SEQUENCE public.fl_2d_serial_seq_"+currentdate+" RESTART WITH "+(no+serialNo));
			//////System.out.println("no="+no);
			//////System.out.println("serialNo="+serialNo);
			pstmt.executeUpdate();*/

		}

	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));				
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




public synchronized long getSerialNoFl2D(long noOfSequenc,Date date) {
	//////System.out.println("123");
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
		SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyy");
		String currentdate=	 sdf.format(today);
		pstmt2=conn.prepareStatement("CREATE SEQUENCE IF NOT EXISTS public.fl_2d_serial_seq_"+currentdate);
		//////System.out.println("currentdate="+currentdate);
		pstmt2.executeUpdate();
		pstmt = conn.prepareStatement(" select     nextval('public.fl_2d_serial_seq_"+currentdate+"')");
		rs = pstmt.executeQuery();
		if (rs.next()) {
			serialNo = rs.getInt(1);
			if (serialNo == 0) {
				serialNo = serialNo + 1;
			}
			//////System.out.println("noOfSequenc " + noOfSequenc);

			pstmt1 = conn.prepareStatement("ALTER SEQUENCE public.fl_2d_serial_seq_"+currentdate +" RESTART WITH "
							+ (noOfSequenc + serialNo+1));

			pstmt1.executeUpdate();

		}

	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));				

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



}
