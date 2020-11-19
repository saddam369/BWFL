package com.mentor.impl;

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

import org.jboss.util.platform.Java;

import com.mentor.action.GroupingOfUnmappedAction;
import com.mentor.datatable.GroupingOfUnmappedDT;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class GroupingOfUnmappedImpl {

	// =====================get details of distillery======================

	public int getId(GroupingOfUnmappedAction act) {

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

			sql = 	" SELECT unit_id, vch_distillery_contact_number, vch_license_type, vch_firm_name, vch_firm_add "+
					" FROM bwfl.registration_of_bwfl_lic_holder_19_20 "+
					" WHERE vch_approval='V' AND "+
					" vch_distillery_contact_number='" + ResourceUtil.getUserNameAllReq().trim() + "' ";
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {

				act.setDistillery_id(rs.getInt("unit_id"));
					act.setDistillery_nm(rs.getString("vch_firm_name"));
				act.setDistillery_adrs(rs.getString("vch_firm_add"));
				if (rs.getString("vch_license_type").equals("1")) {
					act.setLicenceType("BWFL2A");
				} else if (rs.getString("vch_license_type").equals("2")) {
					act.setLicenceType("BWFL2B");
				} else if (rs.getString("vch_license_type").equals("3")) {
					act.setLicenceType("BWFL2C");
				} else if (rs.getString("vch_license_type").equals("4")) {
					act.setLicenceType("BWFL2D");
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
	
	
	//===================show data in first datatable========================
	
	
	
	public ArrayList getData(GroupingOfUnmappedAction act)
	{

		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = 	" SELECT c.export_box_size,c.quantity,c.code_generate_through,coalesce(b.tracking_flg,'N') as tracking_flg, "+
				 		" a.finalized_date, a.finalized_flag, a.permitno, a.seq, "+
				 		" a.int_distillery_id,a.licence_no,replace(a.licence_no,' ','')as licencenoo , " +
						" a.int_brand_id,b.brand_name, a.int_pack_id,c.package_name , a.int_quantity, "+
						" a.int_planned_bottles, a.int_boxes, a.int_liquor_type,d.description, a.vch_license_type, a.plan_dt "+
						" FROM bwfl_license.mst_bottling_plan_19_20 a ,distillery.brand_registration_19_20 b, "+
				 		" distillery.packaging_details_19_20 c ,distillery.imfl_type d "+
						" where a.int_brand_id=b.brand_id  and  b.brand_id=c.brand_id_fk "+
						" and   a.int_pack_id=c.package_id and  a.int_liquor_type=d.id "+
						" and b.int_bwfl_id =? AND a.maped_unmaped_type='U' AND a.group_id IS NULL "+
				 		" ORDER BY a.plan_dt DESC  ";

		System.out.println(act.getDistillery_id()+" sql--------------"+sql);

		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, act.getDistillery_id());
			rs = ps.executeQuery();
			while (rs.next()) {

				GroupingOfUnmappedDT dt = new GroupingOfUnmappedDT();
				dt.setShowDataTable_Date(rs.getDate("plan_dt"));
				dt.setShowDataTable_LiqureType(rs.getString("description"));
				dt.setShowDataTable_LicenceType(rs.getString("vch_license_type"));
				dt.setShowDataTable_Brand(rs.getString("brand_name"));
				dt.setShowDataTable_Packging(rs.getString("package_name"));
				dt.setShowDataTable_Quntity(rs.getString("quantity"));
				dt.setShowDataTable_PlanNoOfBottling(rs.getString("int_planned_bottles"));
				dt.setShowDataTable_NoOfBoxes(rs.getString("int_boxes"));
				dt.setBrandId(rs.getInt("int_brand_id"));
				dt.setPackagingId(rs.getInt("int_pack_id"));
				dt.setNewml(rs.getInt("quantity"));
				dt.setNewsize(rs.getInt("export_box_size"));
				dt.setLicenceNo(rs.getString("licence_no"));
				dt.setPermitnoD(rs.getString("permitno"));
				dt.setLicencenoo(rs.getString("licencenoo").replaceAll("/", ""));
				dt.setFinalizedFlag(rs.getString("finalized_flag"));
				dt.setFinalize_Date(Utility.convertSqlDateToUtilDate(rs.getDate("finalized_date")));
				dt.setGtinno(rs.getString("code_generate_through"));
				dt.setSeq(rs.getInt("seq"));
				if (rs.getDate("finalized_date") != null) {
				Date dat = Utility.convertSqlDateToUtilDate(rs.getDate("plan_dt"));
					// System.out.println("date finalize" + dat);

				DateFormat formatter = new SimpleDateFormat("yyMMdd");
				String date = formatter.format(dat);
				dt.setFinalizedDateString(date);
					//System.out.println(date);
				}
				
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
	
	//=====================get max id of mst_bottling_plan===================
	
	
	public int maxId()
	{

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = " SELECT max(group_id) as id FROM bwfl_license.mst_bottling_plan_19_20 ";
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
	
	//==================update data on save=========================
	
	
	public void save(GroupingOfUnmappedAction act, ArrayList list)
	{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		String updtQr = null;
		int groupId = maxId();
		
		try{

			conn = ConnectionToDataBase.getConnection();

			if (list.size() > 0) {
				
				

				for (int i = 0; i < list.size(); i++) {

					
					GroupingOfUnmappedDT table = (GroupingOfUnmappedDT)list.get(i);
					
					updtQr = 	" UPDATE bwfl_license.mst_bottling_plan_19_20 SET " +
			 					" group_id='"+groupId+"',etin='"+table.getGtinno()+"'   "+
			 					" WHERE seq='"+table.getSeq()+"' " +
			 					" AND unit_id='"+act.getDistillery_id()+"'  " +
			 					" AND int_brand_id='"+table.getBrandId()+"' " +
			 					" AND int_pack_id='"+table.getPackagingId()+"'  " +
			 					" AND permitno='"+table.getPermitnoD()+"' ";
			 							
			 							
			 					
					
					System.out.println("updtQr-----------"+updtQr);
					
					pstmt = conn.prepareStatement(updtQr);

					saveStatus = pstmt.executeUpdate();
					
				}
			}

			if (saveStatus > 0) {

				ResourceUtil.addErrorMessage(Constants.SAVED_SUCESSFULLY,
						Constants.SAVED_SUCESSFULLY);
				act.reset();

			} else {

				ResourceUtil.addErrorMessage(Constants.NOT_SAVED,Constants.NOT_SAVED);

			}

		
		}
		catch(Exception e)
		{
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

			} catch (SQLException e) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
			}
		}
	}
	
	
	//===================show data in second datatable========================
	
	
	
	public ArrayList getSavedData(GroupingOfUnmappedAction act)
	{

		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		/*String sql = 	" SELECT a.finalized_flag,a.etin,a.int_distillery_id,c.code_generate_through,a.group_id, a.int_brand_id, a.int_pack_id, b.brand_name, c.package_name, " +
						" SUM(a.int_planned_bottles) as int_planned_bottles, SUM(a.int_boxes) as int_boxes, " +
						" a.plan_dt, a.int_liquor_type, a.vch_license_type, a.int_quantity, d.description " +
						" FROM bwfl_license.mst_bottling_plan a, distillery.brand_registration b, " +
						" distillery.packaging_details c, distillery.imfl_type d " +
						" WHERE a.int_brand_id=b.brand_id AND b.brand_id=c.brand_id_fk  " +
						" AND a.int_pack_id=c.package_id AND a.int_liquor_type=d.id  " +
						" AND a.maped_unmaped_type='U' AND b.int_bwfl_id =?  AND a.group_id IS NOT NULL " +
						" GROUP BY a.int_brand_id, a.int_pack_id, b.brand_name, c.package_name, a.group_id,  " +
						" a.plan_dt, a.int_liquor_type, a.vch_license_type, a.int_quantity, d.description,c.code_generate_through,a.etin, a.int_distillery_id ,a.finalized_flag";
*/
		

		String sql = 	" SELECT a.finalized_date,a.casecode_seq_frm,a.casecode_seq_to,a.bottling_seq_frm,a.bottling_seq_to,a.finalized_flag,a.etin,a.int_distillery_id,c.code_generate_through,a.group_id, a.int_brand_id, a.int_pack_id, b.brand_name, c.package_name, " +
						" SUM(a.int_planned_bottles) as int_planned_bottles, SUM(a.int_boxes) as int_boxes, " +
						" a.plan_dt, a.int_liquor_type, a.vch_license_type, a.int_quantity, d.description " +
						" FROM bwfl_license.mst_bottling_plan_19_20 a, distillery.brand_registration_19_20 b, " +
						" distillery.packaging_details_19_20 c, distillery.imfl_type d " +
						" WHERE a.int_brand_id=b.brand_id AND b.brand_id=c.brand_id_fk  " +
						" AND a.int_pack_id=c.package_id AND a.int_liquor_type=d.id  " +
						" AND a.maped_unmaped_type='U' AND b.int_bwfl_id =?  AND a.group_id IS NOT NULL " +
						" GROUP BY a.int_brand_id, a.int_pack_id, b.brand_name, c.package_name, a.group_id,  " +
						" a.plan_dt, a.int_liquor_type, a.vch_license_type, a.int_quantity, d.description,c.code_generate_through,a.etin, a.int_distillery_id ,a.finalized_flag,a.casecode_seq_frm,a.casecode_seq_to,a.bottling_seq_frm,a.bottling_seq_to,finalized_date";
		
		System.out.println("sql-----saved---------"+sql);

		try {
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, act.getDistillery_id());
			rs = ps.executeQuery();
			while (rs.next()) {

				GroupingOfUnmappedDT dt = new GroupingOfUnmappedDT();
				
				dt.setGroupIdDt(rs.getInt("group_id"));
				if(rs.getDate("finalized_date")!=null)
				{
					dt.setFinalize_Date(rs.getDate("finalized_date"));
					
				}else {}
				
				dt.setPlanDateDt(rs.getDate("plan_dt"));
				dt.setLiquorTypeDt(rs.getString("description"));
				dt.setLicenseTypeDt(rs.getString("vch_license_type"));
				dt.setBrandNmDt(rs.getString("brand_name"));
				dt.setPckgNmDt(rs.getString("package_name"));
				dt.setQuantityDt(rs.getString("int_quantity"));
				dt.setNmbrOfBottlesDt(rs.getInt("int_planned_bottles"));
				dt.setNmbrOfBoxesDt(rs.getInt("int_boxes"));
				dt.setGtinno(rs.getString("code_generate_through"));
				dt.setDisliery_id(rs.getInt("int_distillery_id"))	;
				dt.setGtinno(rs.getString("etin"));
				dt.setBrandId(rs.getInt("int_brand_id"));
				dt.setPackagingId(rs.getInt("int_pack_id"));
				dt.setFinalizedFlag(rs.getString("finalized_flag"));
				
				if(rs.getString("casecode_seq_frm")==null || rs.getString("casecode_seq_to")==null)
				{
					dt.setProvided_case_seq("NA");
				}else {
					dt.setProvided_case_seq("From "+rs.getString("casecode_seq_frm")+" To "+rs.getString("casecode_seq_To"));
					
				}if(rs.getString("bottling_seq_frm")==null || rs.getString("bottling_seq_to")==null)
				{
				dt.setProvided_bottling_seq("NA");
				}else{
					dt.setProvided_bottling_seq("From "+rs.getString("bottling_seq_frm")+" To "+rs.getString("bottling_seq_to"));
				}
				dt.setRecived_cases(checkUnmappedDataRecived(rs.getInt("group_id"),rs.getString("etin"),rs.getDate("finalized_date")));
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
	
	
	
	
	//Finalize of unmapped data
	
	
	
	public boolean finalizeData(GroupingOfUnmappedDT dt)
	{
		Connection conn=null;
		PreparedStatement pstmt=null;
		boolean flag=false;
		
		String sql=
		
		
		
			"	UPDATE bwfl_license.mst_bottling_plan_19_20 "+
			"	SET  finalized_flag='F', finalized_date=?  "+
			"	WHERE int_distillery_id=? and int_brand_id=?  "+
			"	and  int_pack_id=? and plan_dt=? and group_id=? and  etin=?";
		
		System.out.println("finalizeData="+sql);
		try{
			conn=ConnectionToDataBase.getConnection();
		pstmt=	conn.prepareStatement(sql);
		pstmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));
		pstmt.setInt(2, dt.getDisliery_id());
		pstmt.setInt(3, dt.getBrandId());
		pstmt.setInt(4, dt.getPackagingId());
		pstmt.setDate(5, Utility.convertUtilDateToSQLDate(dt.getPlanDateDt()));
		pstmt.setInt(6, dt.getGroupIdDt());
		pstmt.setString(7, dt.getGtinno());
		
	int i=	pstmt.executeUpdate();
	if(i>0)
	{
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Finalized Successful", "Finalized Successful"));	
	}else{
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Not Finalized", "Not Finalized"));
	}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			
			
			try{
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		return flag;
	}
	
	
	
	
	public String checkUnmappedDataRecived(int planid,String etin,Date plan_dt)
	{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(*) from bottling_unmapped.bwfl where plan_id=? and etin=? and date_plan=?";
		String count="";
		
	try{
		conn=ConnectionToDataBase.getConnection3();
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, planid);
		pstmt.setString(2, etin);
		pstmt.setDate(3,Utility.convertUtilDateToSQLDate(plan_dt));
		rs=pstmt.executeQuery();
		if(rs.next())
		{
			count=rs.getString(1);
		}else{
			count="Not Recived";
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



	return count;
	}
		
		
	
	
	
}
