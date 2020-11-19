package com.mentor.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;


import com.mentor.action.Fl2ScanningAction;
import com.mentor.connectiondb.ConnectionToDataBase;
import com.mentor.datatable.Fl2ScanningDataTable;
import com.mentor.datatable.Fl2ScanningShowDataTable;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class Fl2ScanningImpl {
	
	

	public String getDetails(Fl2ScanningAction ac) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s = "";
		try { 
			con = ConnectionToDataBase.getConnection();
			String queryList = 	" SELECT int_app_id, vch_applicant_name, vch_firm_name ," +
								" vch_mobile_no,vch_license_type, vch_licence_no " +
								" FROM licence.fl2_2b_2d WHERE loginid='"+ResourceUtil.getUserNameAllReq().trim()+"' ";
			
			pstmt = con.prepareStatement(queryList);
			rs = pstmt.executeQuery();

			while (rs.next()) 
			{
				ac.setName(rs.getString("vch_firm_name"));
				ac.setDistillery_id(rs.getInt("int_app_id"));
				ac.setLoginType(rs.getString("vch_license_type"));
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
		return "";

	}
	
	
	
	
	

	public ArrayList getAddRowData(Fl2ScanningAction ac,String lic_typ)
	 {
		 ArrayList list = new ArrayList();
		 Connection con=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			String sql=
				"	SELECT int_brewery_id, int_brand_id, int_pack_id, int_quantity,  "+
				"	int_planned_bottles, int_boxes, int_liquor_type, "+
				"	vch_license_type, plan_dt, licence_no, cr_date,seq "+
				"		FROM bwfl.mst_bottling_plan where  int_brewery_id=? and vch_license_type=?  "+
				"	    and  plan_dt = ? ";
					
				
			
			try{
				con=ConnectionToDataBase.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, ac.getDistillery_id());
			//ps.setString(2, ac.getLicenceType());
			ps.setString(2, lic_typ);
			
			ps.setDate(3, Utility.convertUtilDateToSQLDate(ac.getDateOfBottling()));
			rs=ps.executeQuery();
			while(rs.next()){
			
				Fl2ScanningDataTable dt=new Fl2ScanningDataTable();
				
				
				dt.setBrandPackagingData_Brand(rs.getString("int_brand_id"));
				dt.setBrandPackagingData_Packaging(rs.getString("int_pack_id"));
				dt.setBrandPackagingData_Quantity(rs.getString("int_quantity"));
				dt.setBrandPackagingData_PlanNoOfBottling(rs.getInt("int_planned_bottles"));
				dt.setBrandPackagingData_NoOfBoxes(rs.getInt("int_boxes"));
				
				
				dt.setSeq(rs.getInt("seq"));
				
				
				list.add(dt);
			}
				
			}catch(Exception e){e.printStackTrace();}
			finally{
				try{
					if(con!=null)
					con.close();
					if (ps != null)
						ps.close();
					if (rs != null)
						rs.close();
					
				}catch(Exception e){e.printStackTrace();}
			}
		 return list;
	 }
	//----------------------------- get Brand  --------------------------------------------

	public ArrayList getBrandName( )
	{
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Fl2ScanningAction bof
		    = (Fl2ScanningAction)facesContext.getApplication()
		      .createValueBinding("#{fl2ScanningAction}").getValue(facesContext);
		
		String lic=bof.getLoginType();
		
		//String licNo=bof.getLicenceNoId();
		
/*	System.out.println("---------- 00 00 lic  00 -------------"+lic);
		
		System.out.println("---------- brand mthd  lic id -------------"+licNo);
*/
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);
		String sql = "";
				
				
				
			
		try
		{
			/*if(lic.equalsIgnoreCase("FL2"))
			{
				sql=	"select x.brand_id,x.brand_name from "+
					"	(SELECT brand_id, brand_name FROM distillery.brand_registration where license_category in('IMFL') "+
					"	union all "+
					"	SELECT brand_id, brand_name FROM distillery.brand_registration where int_bwfl_id not  in(0) "+
					"	union all "+
					"	SELECT brand_id, brand_name FROM distillery.brand_registration where int_fl2d_id not  in(0))x "+
					"	order by  brand_name";
					
			}
			if(lic.equalsIgnoreCase("FL2B"))
			{
				sql=	"	SELECT brand_id, brand_name FROM distillery.brand_registration "+
						 "  where license_category='BEER' "+
						 
						 "     order by  brand_name" ;
					
			}
			if(lic.equalsIgnoreCase("CL2"))
			{*/
				//sql=	"	SELECT distinct  brand_id, brand_name FROM distillery.brand_registration "+
						// "  where license_category='CL' "+
						 
					//	 "     order by  brand_name" ;
					
			//}
			
			if(lic.equalsIgnoreCase("CL2"))
			{
				sql=	"	SELECT distinct a.brand_id,( a.brand_name||' - '||b.vch_undertaking_name ) as brand_name        "+
		" FROM distillery.brand_registration a,public.dis_mst_pd1_pd2_lic b                        "+
		"  where a.license_category in ('CL') and a.distillery_id=b.int_app_id_f and a.int_bwfl_id=0 and lower(a.brand_name) not like '%' || 'export' || '%'   order by  brand_name" ;
					
			}
			if(lic.equalsIgnoreCase("FL2B"))
			{	sql=	"select x.brand_id,x.brand_name from (             "+
		" SELECT distinct a.brand_id,( a.brand_name||' - '||b.brewery_nm) as brand_name             "+
		" FROM distillery.brand_registration a,public.bre_mst_b1_lic b                      "+
		"  where a.license_category in ('BEER') and a.distillery_id=b.vch_app_id_f 	    and coalesce(a.int_bwfl_id,0)=0 	  "+
		"  union                                                                           "+
		"   SELECT distinct a.brand_id,( a.brand_name||' - '||b.vch_firm_name||', '||b.vch_firm_district_name) as brand_name   "+
		" FROM distillery.brand_registration a,bwfl.registration_of_bwfl_lic_holder b    "+
		"  where a.license_category in ('BEER','LAB') and a.int_bwfl_id=b.int_id  and a.distillery_id=0          "+
		"   union                                                                          "+
		"   SELECT distinct a.brand_id,( a.brand_name||' - '||b.vch_firm_name||', '||c.description) as brand_name        "+
		" FROM distillery.brand_registration a,licence.fl2_2b_2d b  ,public.district c                       "+
		"  where a.license_category in ('IMPORTEDBEER','LAB') and a.int_fl2d_id=b.int_app_id  and b.core_district_id =c.districtid   " +
		" union                                                                          "+
		"   SELECT distinct a.brand_id,( a.brand_name||' - '||b.vch_undertaking_name) as brand_name        "+
		" FROM distillery.brand_registration a,public.dis_mst_pd1_pd2_lic b                        "+
		"  where a.license_category in ('LAB') and a.distillery_id=b.int_app_id_f  )x  "+
			" where lower(x.brand_name) not like '%' || 'export' || '%' order by  x.brand_name " ;}
			else
				if(lic.equalsIgnoreCase("FL2"))
			{
				sql=	"select distinct x.brand_id,x.brand_name from (             "+ 
				"   SELECT distinct a.brand_id,( a.brand_name||' - '||b.vch_firm_name||', '||b.vch_firm_district_name||'(BWFL)') as brand_name   "+
		" FROM distillery.brand_registration a,bwfl.registration_of_bwfl_lic_holder b    "+
		"  where a.license_category in ('IMFL','WINE','IMPORTEDFL','IMPORTEDWINE') and a.int_bwfl_id=b.int_id  and a.distillery_id=0          "+
		"   union                                                                          "+
		"   SELECT distinct a.brand_id,( a.brand_name||' - '||b.vch_firm_name||', '||c.description||'(FL2D)') as brand_name        "+
		" FROM distillery.brand_registration a,licence.fl2_2b_2d b  ,public.district c                       "+
		"  where a.license_category in ('IMFL','WINE','IMPORTEDFL','IMPORTEDWINE') and a.int_fl2d_id=b.int_app_id  and b.core_district_id =c.districtid  and a.int_bwfl_id=0 " +
		" union                                                                          "+
		"   SELECT distinct a.brand_id,( a.brand_name||' - '||b.vch_undertaking_name||'(Distillery)') as brand_name        "+
		" FROM distillery.brand_registration a,public.dis_mst_pd1_pd2_lic b                        "+
		"  where a.license_category in ('IMFL','WINE','IMPORTEDFL','IMPORTEDWINE') and a.distillery_id=b.int_app_id_f and a.int_bwfl_id=0  )x  "+
			" where lower(x.brand_name) not like '%' || 'export' || '%' order by  x.brand_name " ;
			}
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
		
			
			while(rs.next())
			{
				item = new SelectItem();
				item.setLabel(rs.getString("brand_name"));
				item.setValue(rs.getString("brand_id"));
				list.add(item);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{	
				
	    		if(con!=null)con.close();
	    		if(ps!=null)ps.close();
	    		if(rs!=null)rs.close();
	    		
	    		
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return list;
	}

			//----------------------------- get Packaging Name  --------------------------------------------

			public ArrayList getPackagingName(String brand_id)
			{
				ArrayList list = new ArrayList();
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				SelectItem item = new SelectItem();
				item.setLabel("--SELECT--");
				item.setValue("");
				list.add(item);
				String SQl = 
						"SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id "+
						"	from distillery.brand_registration a , "+ 
						"	distillery.packaging_details b "+
						"	where a.brand_id=b.brand_id_fk  "+
						//"	and a.distillery_id=? "+
						"	and brand_id =? ";
				try
				{
					con = ConnectionToDataBase.getConnection();
					ps = con.prepareStatement(SQl);
				//	ps.setInt(1, this.getSugarmill_Id());
					ps.setInt(1, Integer.parseInt(brand_id));
					rs = ps.executeQuery();
					while(rs.next())
					{
						item = new SelectItem();
						item.setLabel(rs.getString("package_name"));
						item.setValue(rs.getString("package_id"));
						list.add(item);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					try
					{	
						
			    		if(con!=null)con.close();
			    		if(ps!=null)ps.close();
			    		if(rs!=null)rs.close();
			    		
			    		
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				return list;
			}

			//----------------------------- get quantity  --------------------------------------------

			public ArrayList getquantity( String brand_Id,String packging_Id)
			{
				ArrayList list = new ArrayList();
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				SelectItem item = new SelectItem();
				/*item.setLabel("--SELECT--");
				item.setValue("");
				list.add(item);*/
				String SQl = 
						
						"SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id "+
						"	from distillery.brand_registration a , "+ 
						"	distillery.packaging_details b "+
						"	where a.brand_id=b.brand_id_fk  "+
					//	"	and a.distillery_id=?  "+
						"	and brand_id =?  and b.package_id=?";		
				try
				{
					con = ConnectionToDataBase.getConnection();
					ps = con.prepareStatement(SQl);
				//	ps.setInt(1, this.getSugarmill_Id());
					ps.setInt(1, Integer.parseInt(brand_Id));
					ps.setInt(2, Integer.parseInt(packging_Id));
					rs = ps.executeQuery();
					while(rs.next())
					{
						
						
						System.out.println("rs.getDoublequantity"+rs.getDouble("quantity"));
						item = new SelectItem();
						item.setLabel(rs.getString("quantity"));
						item.setValue(rs.getDouble("quantity"));
						list.add(item);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					try
					{	
						
			    		if(con!=null)con.close();
			    		if(ps!=null)ps.close();
			    		if(rs!=null)rs.close();
			    		
			    		
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				return list;
			}

			
			
			
//----------------------------- 2nd -----------------
			
			public String getqty(String brand_Id ,String packging_Id) {
				String qty="";
				Connection con=null;
				PreparedStatement pstmt=null;
				ResultSet rs =null;
				try{

					String queryList=
							
							"SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id, case when coalesce(b.export_box_size,0) > 0  " +
					" then b.export_box_size else c.box_size  end as box_size "+
									"	from distillery.brand_registration a , "+ 
									"	distillery.packaging_details b,distillery.box_size_details c "+
									"	where a.brand_id=b.brand_id_fk  "+
								 	"	and b.box_id=c.box_id  "+
									"	and brand_id =?  and b.package_id=?";	
					
					
					 
					con=ConnectionToDataBase.getConnection() ;
					
					pstmt=con.prepareStatement(queryList);

					pstmt.setInt(1, Integer.parseInt(brand_Id));
					pstmt.setInt(2, Integer.parseInt(packging_Id));
					
					rs= pstmt.executeQuery();

					while(rs.next())
					{ 
						
				qty=rs.getString("quantity");
						 
					}

					//	pstmt.executeUpdate();
				}catch(SQLException se){
					se.printStackTrace();
				}
				finally{try{
					if(pstmt!=null)pstmt.close();
					if(con!=null) con.close();	

				}catch(SQLException se){
					se.printStackTrace();
				}
				}
				return qty;

			}
			public int getsize(String brand_Id ,String packging_Id) {
				int qty=0;
				Connection con=null;
				PreparedStatement pstmt=null;
				ResultSet rs =null;
				try{

					String queryList=
							
							"SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id, case when coalesce(b.export_box_size,0) > 0  " +
					" then b.export_box_size else c.box_size  end as box_size "+
									"	from distillery.brand_registration a , "+ 
									"	distillery.packaging_details b,distillery.box_size_details c "+
									"	where a.brand_id=b.brand_id_fk  "+
								 	"	and b.box_id=c.box_id  "+
									"	and brand_id =?  and b.package_id=?";	
					
					
					 
					con=ConnectionToDataBase.getConnection() ;
					
					pstmt=con.prepareStatement(queryList);

					pstmt.setInt(1, Integer.parseInt(brand_Id));
					pstmt.setInt(2, Integer.parseInt(packging_Id));
					
					rs= pstmt.executeQuery();

					while(rs.next())
					{ 
						
				qty=rs.getInt("box_size");
						 
					}

					//	pstmt.executeUpdate();
				}catch(SQLException se){
					se.printStackTrace();
				}
				finally{try{
					if(pstmt!=null)pstmt.close();
					if(con!=null) con.close();	

				}catch(SQLException se){
					se.printStackTrace();
				}
				}
				return qty;

			}
		public void save(Fl2ScanningAction action)
		{
			
			Connection conn=null;
			PreparedStatement pstmt=null;
			PreparedStatement pstmt1=null;
			int seq=maxId();
			int status=0;
			
					
					
					
				String	sql2 = "	INSERT INTO fl2d.fl2_2b_receiving_stock_trxn ( "
				+ "	seq, gatepass_no, fl2_2btype, brand_id, size_ml, batch_no, recv_box,  "
				+ "    recv_bottels, breakage_bottels, total_recv_bottels, pckg_id, created_date, fl2_2bid, box_size ," +
				" recvdfromunittype,rcvdunitid,loginusr) "
				+ "	VALUES (?, 'OLDSTOCK', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			
			try{
				
			conn=	ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(sql2);
		//	pstmt1=conn.prepareStatement(sql);
			
			
			for(int i=0;i<action.getBrandPackagingDataList().size();i++)
			{
				Fl2ScanningDataTable dt=(Fl2ScanningDataTable)action.getBrandPackagingDataList().get(i);
				
				pstmt.setInt(1, ++seq);
				pstmt.setString(2, action.getLoginType());
				pstmt.setInt(3, Integer.parseInt(dt.getBrandPackagingData_Brand()));
				pstmt.setLong(4,new Double(dt.getBrandPackagingData_Quantity()).longValue());
				pstmt.setString(5, "NA");
				pstmt.setDouble(6, dt.getBrandPackagingData_NoOfBoxes());
				pstmt.setDouble(7, dt.getBrandPackagingData_PlanNoOfBottling());
				pstmt.setDouble(8, 0.0);
				pstmt.setDouble(9, dt.getBrandPackagingData_PlanNoOfBottling());
				pstmt.setInt(10, Integer.parseInt(dt.getBrandPackagingData_Packaging()));
				pstmt.setDate(11, Utility.convertUtilDateToSQLDate(action.getDateOfBottling()));
				pstmt.setInt(12, action.getDistillery_id());
				pstmt.setDouble(13, (dt.getBrandPackagingData_PlanNoOfBottling()/dt.getBrandPackagingData_NoOfBoxes()));
			    pstmt.setString(14, "NA");
			    pstmt.setString(15, "NA");
			    pstmt.setString(16, ResourceUtil.getUserNameAllReq());
			   
			    status=   pstmt.executeUpdate();
			    
			    /* if(status>0)
			    {
			    	status=0;
			    	pstmt1.setInt(1, action.getDistillery_id());
			    	pstmt1.setString(2, action.getLoginType());
			    	pstmt1.setInt(3, Integer.parseInt(dt.getBrandPackagingData_Brand()));
			    	pstmt1.setInt(4, Integer.parseInt(dt.getBrandPackagingData_Packaging()));
			    	pstmt1.setDouble(5, dt.getBrandPackagingData_PlanNoOfBottling());
			    	pstmt1.setDouble(6, dt.getBrandPackagingData_PlanNoOfBottling());
			    	status=pstmt1.executeUpdate();
			    } */
			
			}
				if(status>0)
				{
					conn.commit();
					ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,
							Constants.SAVED_SUCESSFULLY);
					action.getBrandPackagingDataList().clear();
					
					
				}else{
					conn.rollback();
					ResourceUtil.addMessage(Constants.NOT_SAVED,
							Constants.NOT_SAVED);
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}finally{
				
				try{
					if(conn!=null)conn.close();
					if(pstmt!=null)pstmt.close();
					
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
		}
			
			
		public int maxId() {

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = " SELECT max(seq) as id FROM fl2d.fl2_2b_receiving_stock_trxn ";
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
			return maxid;

		}	
		
		
		public ArrayList getShowDataTable(Fl2ScanningAction action)
		{
			ArrayList list=new ArrayList(); 
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			int k=0;
			String sql=
			
			
			" select a.finalize, a.seq,a.brand_id, a.pckg_id, a.fl2_2bid ,a.recv_box,a.recv_bottels,a.created_date,b.brand_name,c.package_name,c.quantity   "+
			" from fl2d.fl2_2b_receiving_stock_trxn a,                                                 "+
			" distillery.brand_registration b,                                                         "+
			" distillery.packaging_details c where a.pckg_id=c.package_id                              "+
			" and a.brand_id=c.brand_id_fk                                                             "+
			" and c.brand_id_fk=b.brand_id and fl2_2bid=? and fl2_2btype=? order by a.seq desc ";
			System.out.println("sql="+sql);
			try{
				System.out.println("String.valueOf(action.getDistillery_id())="+String.valueOf(action.getDistillery_id()));
				System.out.println("action.getLoginType()="+action.getLoginType());
				conn=ConnectionToDataBase.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, String.valueOf(action.getDistillery_id()));
				pstmt.setString(2, action.getLoginType());
				rs=pstmt.executeQuery();
				while(rs.next())
				{
					Fl2ScanningShowDataTable flsd=new Fl2ScanningShowDataTable();
					flsd.setBrandName(rs.getString("brand_name"));
					flsd.setPackageName(rs.getString("package_name"));
					
					flsd.setBranidF(rs.getInt("brand_id"));
					flsd.setPckidF(rs.getInt("pckg_id"));
					flsd.setFl2idF(rs.getInt("fl2_2bid"));
					flsd.setSeqF(rs.getInt("seq"));
					flsd.setFinalizeFlagLast(rs.getString("finalize"));
					if(rs.getString("finalize")!=null && rs.getString("finalize").length()>0){
					action.setFinalizedLast(rs.getString("finalize"));
					}
					flsd.setNo_of_box(rs.getInt("recv_box"));
				//	flsd.setNo_of_box_old(rs.getInt("recv_box"));
					
					flsd.setNo_of_planned_bottle(rs.getInt("recv_bottels"));
				//	flsd.setNo_of_planned_bottle_Old(rs.getInt("recv_bottels"));
					
					flsd.setQuantity(rs.getInt("quantity"));
					flsd.setPlan_date(Utility.convertSqlDateToUtilDate(rs.getDate("created_date")));
					flsd.setSrNo(++k);
					list.add(flsd);
					
					
				}
				
			}catch(Exception e)
			{
				//e.printStackTrace();
			}finally{
				
				try{
					if(conn!=null)conn.close();
					if(pstmt!=null)pstmt.close();
					
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			return list;
		}
		
		public void updateBottLe(Fl2ScanningAction ac)
		{
			Connection con=null;
			PreparedStatement stmt=null;
			
			
			String sql= " UPDATE fl2d.fl2_2b_receiving_stock_trxn "+
						" SET   recv_box='"+ac.getNo_of_box_New()+"', " +
						"      total_recv_bottels='"+ac.getNo_of_planned_bottle_New()+"' ,  " +
						"      recv_bottels='"+ac.getNo_of_planned_bottle_New()+"'           " +
								
						
						" WHERE brand_id='"+ac.getBranidF()+"' and pckg_id='"+ac.getPckidF()+"'" +
						" and fl2_2bid='"+ac.getFl2idF()+"' and seq='"+ac.getSeqF()+"' ";
			
			
			System.out.println("update before finalize"+sql);
			int status=0;
			try
			{
				con=ConnectionToDataBase.getConnection();
				stmt=con.prepareStatement(sql);
				status=stmt.executeUpdate();
				
				if(status>0)
				{
					ResourceUtil.addMessage(Constants.UPDATED_SUCESSFULLY,
							Constants.UPDATED_SUCESSFULLY);	
					ac.brandPackagingShowDataList=getShowDataTable(ac);
				}
				else
				{
					ResourceUtil.addMessage(Constants.NOT_UPDATE,
							Constants.NOT_UPDATE);	
				}
				
			
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(con!=null)
						con.close();
					if(stmt!=null)
						stmt.close();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			
		}
		public void finalize(Fl2ScanningAction ac)
		{
			Connection con=null;
			PreparedStatement stmt=null;
			PreparedStatement stmt1=null;
			
			String sql="	INSERT INTO fl2d.fl2_2b_stock( "
					+ "		id, type, brand_id, pckg_id, recv_total_bottels)  "
					+ "		VALUES (?, ?, ?, ?, ? )  "
					+ "	ON CONFLICT ON CONSTRAINT fl2_2b_receiving_stock_master_manage_pkey "
					+ "	do update set recv_total_bottels=  COALESCE(fl2d.fl2_2b_stock.recv_total_bottels,0.0) + ?";
		
			String sqlFinalize=	" UPDATE fl2d.fl2_2b_receiving_stock_trxn "+
								" SET finalize='F' "+
								" WHERE brand_id=? and pckg_id=? " +
								" and fl2_2bid=?  and seq=? ";
					
			int status=0;
			try
			{
				con=ConnectionToDataBase.getConnection();
				con.setAutoCommit(false);
				stmt=con.prepareStatement(sql);
				stmt1=con.prepareStatement(sqlFinalize);	
				
		for(int i=0;i<ac.brandPackagingShowDataList.size();i++)
		{
			Fl2ScanningShowDataTable dt=(Fl2ScanningShowDataTable)ac.brandPackagingShowDataList.get(i);
				
				stmt.setInt(1, ac.getDistillery_id());
				stmt.setString(2, ac.getLoginType());
				stmt.setInt(3, dt.getBranidF());
				stmt.setInt(4, dt.getPckidF());
				stmt.setInt(5,dt.getNo_of_planned_bottle());
				stmt.setInt(6, dt.getNo_of_planned_bottle());
				
				status=stmt.executeUpdate();
				
				if(status>0)
				{
					status=0;
					
					
					stmt1.setInt(1, dt.getBranidF());
					stmt1.setInt(2, dt.getPckidF());
					stmt1.setString(3, dt.getFl2idF()+"");
					stmt1.setInt(4, dt.getSeqF());
					status=stmt1.executeUpdate();
					
				
					
					
				}
		}
				if(status>0)
				{
					con.commit();
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Data Finalised Successfully",
									"Data Finalised Successfully"));
					 
					
				}
				else
				{
					ResourceUtil.addMessage(Constants.NOT_UPDATE,
							Constants.NOT_UPDATE);	
					 
				}
	
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(con!=null)
						con.close();
					if(stmt!=null)
						stmt.close();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			
		}
		
		// =======================print report=================================

		public void printReport(Fl2ScanningAction action) {
			String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
			String relativePath = mypath + File.separator + "ExciseUp"
					+ File.separator + "MIS" + File.separator + "jasper";
			String relativePathpdf = mypath + File.separator + "ExciseUp"
					+ File.separator + "MIS" + File.separator + "pdf";
			JasperReport jasperReport = null;
			JasperPrint jasperPrint = null;
			PreparedStatement pst=null;
			Connection con=null;
			ResultSet rs=null;
			String reportQuery=null;
			String reportQuery1=null;
			try {
				con =ConnectionToDataBase.getConnection();
				
		 
				
				reportQuery="select a.recv_box,a.recv_bottels,a.created_date,b.brand_name,c.package_name,c.quantity, " +
						"   (CASE WHEN finalize='F' THEN 'Data Finalize'  ELSE  'Data Not finalize'  END) as finalize " +
						"from fl2d.fl2_2b_receiving_stock_trxn a,    " +
						"   distillery.brand_registration b,       " +
						" distillery.packaging_details c where a.pckg_id=c.package_id " +
						"   and a.brand_id=c.brand_id_fk                 " +
						"    and c.brand_id_fk=b.brand_id and fl2_2bid='"+action.getDistillery_id()+"' and fl2_2btype='"+action.getLoginType()+"' order by a.created_date desc ;";
			
				
					pst=con.prepareStatement(reportQuery);
					
				System.out.println("==>>"+reportQuery);
				rs=pst.executeQuery();
				if(rs.next())
				{
					rs=pst.executeQuery();
					Map parameters = new HashMap();
					parameters.put("REPORT_CONNECTION", con);
					parameters.put("SUBREPORT_DIR", relativePath+File.separator);
					parameters.put("image", relativePath+File.separator);
					 
					JRResultSetDataSource jrRs = new JRResultSetDataSource(rs);

					jasperReport = (JasperReport) JRLoader.loadObject(relativePath+File.separator+"OpeningStockReport.jasper");

					JasperPrint print = JasperFillManager.fillReport(jasperReport,parameters, jrRs);	
					  Random rand = new Random();
		        	  int  n = rand.nextInt(250) + 1;

					/*JasperExportManager.exportReportToPdfFile(print,relativePath+File.separator+"OpeningStockReport"+n+".pdf");
					action.setPdfname("OpeningStockReport"+n+".pdf");
					action.setPrintFlag(true);*/
					
					
					JasperExportManager.exportReportToPdfFile(print,
							relativePathpdf + File.separator + "OpeningStockReport" + "-" + n + ".pdf");
					action.setPdfname("OpeningStockReport" + "-" + n
							+ ".pdf");
					action.setPrintFlag(true);
				}
				else
				{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No Data Found!!", "No Data Found!!"));
					action.setPrintFlag(false);
				}
				 
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(rs!=null)rs.close();
					if(con!=null)con.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}


		}

		public boolean panelFlg() {
			//int id = 0;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		//	String s = "";
			boolean flg=false;
			try { 
			
				con = ConnectionToDataBase.getConnection();
				String query = 	"select created_date from fl2d.fl2_2b_receiving_stock_trxn " +
						"where fl2_2btype='CL2' AND loginusr='"+ResourceUtil.getUserNameAllReq().trim()+"' and created_date<='2019-03-06'";
						/*" SELECT int_app_id, vch_applicant_name, vch_firm_name ," +
									" vch_mobile_no,vch_license_type, vch_licence_no " +
									" FROM licence.fl2_2b_2d WHERE loginid='"+ResourceUtil.getUserNameAllReq().trim()+"' ";*/
				
				pstmt = con.prepareStatement(query);
				rs = pstmt.executeQuery();

				if (rs.next()) 
				{
					flg=true;
				}

			} catch (SQLException se) {
				se.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(se.getMessage(), se.getMessage()));
			}
			catch (Exception e) {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
			}finally {
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
			return flg;

		}

}
