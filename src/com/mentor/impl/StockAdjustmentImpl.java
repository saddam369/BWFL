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
import javax.faces.model.SelectItem;

import com.mentor.action.Fl2ScanningAction;
import com.mentor.action.StockAdjustmentAction;
import com.mentor.connectiondb.ConnectionToDataBase;
import com.mentor.datatable.Fl2ScanningDataTable;
import com.mentor.datatable.Fl2ScanningShowDataTable;
import com.mentor.datatable.StockAdjustmentDT;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class StockAdjustmentImpl {

	// =================get login details========================

	public String getLoginDetails(StockAdjustmentAction ac) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String queryList = "";
		try {
			con = ConnectionToDataBase.getConnection();
			
		queryList =" select created_date from " +
				   " fl2d.fl2_2b_receiving_stock_trxn " +
				   " where loginusr='"+ResourceUtil.getUserNameAllReq().trim()+"' " +
				   " and created_date<='06/03/2019' " +
				   " ";
				
				/*" SELECT int_app_id, vch_applicant_name, vch_firm_name , vch_mobile_no, " +
					" vch_license_type, vch_licence_no, vch_core_address "+
				 	" FROM licence.fl2_2b_2d WHERE loginid='"+ ResourceUtil.getUserNameAllReq().trim() + "' ";*/
		System.out.println("queryList--------"+queryList);
		pstmt=con.prepareStatement(queryList);
		rs=pstmt.executeQuery();
	int y=0;
		while (rs.next()) 
		{
		y=1;	
		
		}
		
		
		if(y==1)
		{
			ac.setFlgtab(true);
			
			queryList = " SELECT int_app_id, vch_applicant_name, vch_firm_name , vch_mobile_no, " +
						" vch_license_type, vch_licence_no, vch_core_address "+
					 	" FROM licence.fl2_2b_2d WHERE loginid='"+ ResourceUtil.getUserNameAllReq().trim() + "' ";

			pstmt = con.prepareStatement(queryList);
			rs = pstmt.executeQuery();
			System.out.println("queryList------hello--");
			while (rs.next()) 
			{
				ac.setLoginId(rs.getInt("int_app_id"));
				ac.setLoginName(rs.getString("vch_firm_name"));
				ac.setLoginAdrs(rs.getString("vch_core_address"));
				ac.setLoginType(rs.getString("vch_license_type"));
				ac.setLoginLicenseNmbr(rs.getString("vch_licence_no"));
			}
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
	
	//=============================get brand name===========================

	public ArrayList getBrandName( )
	{
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		StockAdjustmentAction bof= (StockAdjustmentAction)facesContext.getApplication().createValueBinding("#{stockAdjustmentAction}").getValue(facesContext);
		
		String lic=bof.getLoginType();
		
		
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
			if(lic.equalsIgnoreCase("CL2"))
			{
				sql=	" SELECT distinct a.brand_id,( a.brand_name||' - '||b.vch_undertaking_name ) as brand_name   "+
						" FROM distillery.brand_registration a, public.dis_mst_pd1_pd2_lic b                         "+
						" WHERE a.license_category = 'CL' AND a.distillery_id=b.int_app_id_f AND a.int_bwfl_id=0 " +
						" and lower(a.brand_name) not like '%'||'exports'||'%'  "+
						" ORDER BY  brand_name" ;
					
		    }
			
		if(lic.equalsIgnoreCase("FL2B"))
		{	
		sql=	"select x.brand_id,x.brand_name from (             "+
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
		"  where a.license_category in ('LAB') and a.distillery_id=b.int_app_id_f  )x " +
		" where lower(x.brand_name) not like '%'||'exports'||'%'  "+
			" order by  x.brand_name " ;	
		}
			
		else if(lic.equalsIgnoreCase("FL2"))
		{
		sql="select distinct x.brand_id,x.brand_name from (             "+ 
			"  SELECT distinct a.brand_id,( a.brand_name||' - '||b.vch_firm_name||', '||b.vch_firm_district_name||'(BWFL)') as brand_name   "+
		" FROM distillery.brand_registration a,bwfl.registration_of_bwfl_lic_holder b    "+
		"  where a.license_category in ('IMFL','WINE','IMPORTEDFL','IMPORTEDWINE') and a.int_bwfl_id=b.int_id  and a.distillery_id=0          "+
		"   union                                                                          "+
		"   SELECT distinct a.brand_id,( a.brand_name||' - '||b.vch_firm_name||', '||c.description||'(FL2D)') as brand_name        "+
		" FROM distillery.brand_registration a,licence.fl2_2b_2d b  ,public.district c                       "+
		"  where a.license_category in ('IMFL','WINE','IMPORTEDFL','IMPORTEDWINE') and a.int_fl2d_id=b.int_app_id  and b.core_district_id =c.districtid  and a.int_bwfl_id=0 " +
		" union                                                                          "+
		"   SELECT distinct a.brand_id,( a.brand_name||' - '||b.vch_undertaking_name||'(Distillery)') as brand_name        "+
		" FROM distillery.brand_registration a,public.dis_mst_pd1_pd2_lic b                        "+
		"  where a.license_category in ('IMFL','WINE','IMPORTEDFL','IMPORTEDWINE') and a.distillery_id=b.int_app_id_f and a.int_bwfl_id=0  )x  " +
		"  where lower(x.brand_name) not like '%'||'exports'||'%' "+
			" order by  x.brand_name " ;
			
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
	
	
	//========================get package name===========================

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
		String sql = "";
		
		
		
		sql = 	" SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id "+
				" FROM distillery.brand_registration a, distillery.packaging_details b "+
				" WHERE a.brand_id=b.brand_id_fk AND brand_id =? ";
				
		try
		{
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
		
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
	
	
	//========================get quantity according to brand pckg===========================
	
		public String getqty(String brand_Id ,String packging_Id) {
			
			String qty="";
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs =null;
			try{

				String queryList= 	" SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id, " +
									" CASE WHEN COALESCE(b.export_box_size,0) > 0  " +
									" THEN b.export_box_size else c.box_size  end as box_size "+
									" FROM distillery.brand_registration a, distillery.packaging_details b,distillery.box_size_details c "+
									" WHERE a.brand_id=b.brand_id_fk AND b.box_id=c.box_id  "+
									" AND brand_id =? AND b.package_id=? ";	
						
						
				
				
				 
				con=ConnectionToDataBase.getConnection() ;
				
				pstmt=con.prepareStatement(queryList);

				pstmt.setInt(1, Integer.parseInt(brand_Id));
				pstmt.setInt(2, Integer.parseInt(packging_Id));
				
				 
				rs= pstmt.executeQuery();

				while(rs.next())
				{ 
					
					qty=rs.getString("quantity");
					 
				}

				
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
		
		//=======================get size of bottles==========================
		
		public int getsize(String brand_Id ,String packging_Id) {
			
			int qty=0;
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs =null;
			try{

				String queryList= 	" SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id, " +
									" CASE WHEN COALESCE(b.export_box_size,0) > 0  " +
									" then b.export_box_size else c.box_size  end as box_size "+
									" from distillery.brand_registration a, distillery.packaging_details b, distillery.box_size_details c "+
									" WHERE a.brand_id=b.brand_id_fk  AND b.box_id=c.box_id  "+
									" AND brand_id =?  AND b.package_id=? ";
						
							
				
				
				 
				con=ConnectionToDataBase.getConnection() ;
				
				pstmt=con.prepareStatement(queryList);

				pstmt.setInt(1, Integer.parseInt(brand_Id));
				pstmt.setInt(2, Integer.parseInt(packging_Id));
				
				rs= pstmt.executeQuery();

				while(rs.next())
				{ 
					
			qty=rs.getInt("box_size");
					 
				}

			
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
	
	//======================get quantity====================================

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
		String sql="";
		
		
		sql = 	" SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id  "+
				" FROM distillery.brand_registration a , distillery.packaging_details b  "+
				" WHERE a.brand_id=b.brand_id_fk AND brand_id =?  AND b.package_id=? "; 
				
		System.out.println("quantity query==============="+sql);
						
		try
		{
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, Integer.parseInt(brand_Id));
			ps.setInt(2, Integer.parseInt(packging_Id));
			
			
			
			
			
			rs = ps.executeQuery();
			while(rs.next())
			{
				
				System.out.println("rs.getDoublequantity--------------"+rs.getDouble("quantity"));
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
	
	
	// =====================get max id sequence fl2d.gatepass_to_districtwholesale_fl2_fl2b==============================

		public int maxId() {

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			
			String query = " SELECT max(seq) as id FROM fl2d.gatepass_to_districtwholesale_fl2_fl2b ";
			
			
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
	
	//==========================save method==================================
	
	
	public void saveDispatch(StockAdjustmentAction action)
	{
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
				
		int seq = this.maxId();
		int status=0;
		 
		String gatepass = "ADJUSTMENT_NEWSTOCK";
		
		String insQr = 	" INSERT INTO fl2d.gatepass_to_districtwholesale_fl2_fl2b( "+
						" seq, int_fl2_fl2b_id, vch_gatepass_no, dt_date, curr_date, licensee_name, licensee_adrs, " +
						" vch_to, vch_to_lic_no, licencee_id, vch_finalize, finalize_dt_time)  "+
						" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";	
				
		try{
			String sDate1="2019/03/07";  
			 SimpleDateFormat formatter1=new SimpleDateFormat("yyyy/MM/dd");  
			 Date date1=formatter1.parse(sDate1);	
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			
		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
		pstmt=conn.prepareStatement(insQr);
		
		pstmt.setInt(1, seq);
		pstmt.setInt(2, action.getLoginId());
		pstmt.setString(3, gatepass);
		pstmt.setDate(4, Utility.convertUtilDateToSQLDate(date1));
		pstmt.setDate(5, Utility.convertUtilDateToSQLDate(date1));
		pstmt.setString(6, action.getLoginName());
		pstmt.setString(7, action.getLoginAdrs());
		pstmt.setString(8, "NA");
		pstmt.setString(9, "NA");
		pstmt.setInt(10, 0);
		pstmt.setString(11, "F");
		pstmt.setString(12, dateFormat.format(new Date()));
		
		status=   pstmt.executeUpdate();
		
		 
		
		if(status > 0){
			
			for(int i=0;i<action.getBrandPackagingDataList().size();i++)
			{
				status = 0;
				
				StockAdjustmentDT dt=(StockAdjustmentDT)action.getBrandPackagingDataList().get(i);
				
				String insQr1 = " INSERT INTO fl2d.fl2_stock_trxn_fl2_fl2b(  "+
								" int_fl2_fl2b_id, vch_lic_type, dt, int_brand_id, int_pckg_id, avl_bottl, avl_box, "+
								" breakage, vch_gatepass_no, dispatch_bottle, dispatch_box, size, mst_seq)  "+
								" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, (select nextval('seq')))  ";
				
				pstmt = conn.prepareStatement(insQr1);
				
				pstmt.setInt(1, action.getLoginId());
				pstmt.setString(2, action.getLoginType());
				pstmt.setDate(3, Utility.convertUtilDateToSQLDate(date1));
				pstmt.setInt(4, Integer.parseInt(dt.getBrandPackagingData_Brand()));
				pstmt.setInt(5, Integer.parseInt(dt.getBrandPackagingData_Packaging()));
				pstmt.setInt(6, 0);
				pstmt.setInt(7, 0);
				pstmt.setInt(8, dt.getBrandPackagingData_Breakage());
				pstmt.setString(9, gatepass);
				pstmt.setDouble(10, dt.getBrandPackagingData_PlanNoOfBottling());
				pstmt.setDouble(11, dt.getBrandPackagingData_NoOfBoxes());
				pstmt.setDouble(12, (dt.getBrandPackagingData_PlanNoOfBottling()/dt.getBrandPackagingData_NoOfBoxes()));
				

			    status=   pstmt.executeUpdate();
			    
			  
			    
			    if(status>0)
			    {
			    	/*status=0;
			    	
			    	String updtQr = " UPDATE fl2d.fl2_2b_stock "+
									" SET dispatchbotl = COALESCE(dispatchbotl,0)+("+dt.getBrandPackagingData_PlanNoOfBottling()+" + "+ dt.getBrandPackagingData_Breakage()+ ") "+
									" WHERE id='"+ action.getLoginId()+ "' AND brand_id='"+ dt.getBrandPackagingData_Brand()+ "' "+
									" AND pckg_id='"+ dt.getBrandPackagingData_Packaging()+ "' AND type='"+ action.getLoginType()+ "' ";
					
					
			    	 

			    	pstmt = conn.prepareStatement(updtQr);
					status =   pstmt.executeUpdate();*/

			    	status=0;
			    	
			   String updtQr = /*" UPDATE fl2d.fl2_2b_stock "+
									" SET dispatchbotl = COALESCE(dispatchbotl,0)+("+dt.getBrandPackagingData_PlanNoOfBottling()+" + "+ dt.getBrandPackagingData_Breakage()+ ") "+
									" WHERE id='"+ action.getLoginId()+ "' AND brand_id='"+ dt.getBrandPackagingData_Brand()+ "' "+
									" AND pckg_id='"+ dt.getBrandPackagingData_Packaging()+ "' AND type='"+ action.getLoginType()+ "' ";
					*/
					
						"	INSERT INTO fl2d.fl2_2b_stock( "
						+ "			id, type, brand_id, pckg_id, dispatchbotl)  "
						+ "			VALUES (?, ?, ?, ?, ? )  "
						+ "			ON CONFLICT ON CONSTRAINT fl2_2b_receiving_stock_master_manage_pkey "
						+ "			do update " +
						" SET dispatchbotl = COALESCE(fl2d.fl2_2b_stock.dispatchbotl,0)+("+dt.getBrandPackagingData_PlanNoOfBottling()+") " ;
					
			    	 

			    	pstmt = conn.prepareStatement(updtQr);
			    	
			    	
			    	pstmt.setInt(1, action.getLoginId());

			    	pstmt.setString(2, action.getLoginType());
			    	pstmt.setInt(3, Integer.parseInt(dt.getBrandPackagingData_Brand()));
			    	pstmt.setInt(4, Integer.parseInt(dt.getBrandPackagingData_Packaging()));
			    	pstmt.setInt(5, dt.getBrandPackagingData_PlanNoOfBottling());
			    	
			    	
			    	
					status =   pstmt.executeUpdate();
					
				 
				 
			    } 
			
			}
		}
		
			if(status>0)
			{
				conn.setAutoCommit(true);
				ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,Constants.SAVED_SUCESSFULLY);
				action.getBrandPackagingDataList().clear();
				
				
			}else{
				conn.rollback();
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
				" Stock Not Found in Database "," Stock Not Found in Database "));
				//ResourceUtil.addMessage(Constants.NOT_SAVED,Constants.NOT_SAVED);
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
	
	
	//=======================show data in datatable============================
	
	
	public ArrayList getShowDataTable(StockAdjustmentAction action)
	{
		ArrayList list=new ArrayList(); 
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int k=0;
		String sql = "";
		
		if(action.getRadioType().equalsIgnoreCase("R")){
			
			sql = 	" select a.seq, a.brand_id, a.pckg_id, a.fl2_2bid ,a.recv_box as boxes, a.recv_bottels as bottles, " +
					" a.created_date as dt ,b.brand_name, c.package_name, c.quantity   "+
					" from fl2d.fl2_2b_receiving_stock_trxn a,                                                 "+
					" distillery.brand_registration b,                                                         "+
					" distillery.packaging_details c where a.pckg_id=c.package_id                              "+
					" AND a.brand_id=c.brand_id_fk AND c.brand_id_fk=b.brand_id    " +
					" AND a.gatepass_no='ADJUSTMENT_NEWSTOCK' " +
					" AND a.fl2_2bid='"+action.getLoginId()+"' AND a.fl2_2btype='"+action.getLoginType()+"' " +
					" order by a.seq desc ";
			
		}
		else if(action.getRadioType().equalsIgnoreCase("D")){
			
			sql= 	" SELECT a.vch_lic_type, a.dt, a.int_brand_id, a.int_pckg_id, a.avl_bottl, a.avl_box, " +
					" a.breakage, a.balance, a.vch_gatepass_no, a.mst_seq, a.size, a.vch_batch_no,  " +
					" a.dispatch_bottle as bottles, a.dispatch_box as boxes,  " +
					" a.int_fl2_fl2b_id, b.brand_name,c.package_name,c.quantity " +
					" FROM fl2d.fl2_stock_trxn_fl2_fl2b a, distillery.brand_registration b, distillery.packaging_details c " +
					" WHERE a.int_pckg_id=c.package_id AND a.int_brand_id=c.brand_id_fk AND c.brand_id_fk=b.brand_id  " +
					" AND a.vch_gatepass_no='ADJUSTMENT_NEWSTOCK'  " +
					" AND a.int_fl2_fl2b_id='"+action.getLoginId()+"' AND a.vch_lic_type='"+action.getLoginType()+"' " +
					" ORDER BY a.mst_seq desc";
		}
		else{
			sql = "";
		}
		 
		 
		
		try{
			conn=ConnectionToDataBase.getConnection();
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				StockAdjustmentDT flsd=new StockAdjustmentDT();
				
				flsd.setSrNo(++k);
				flsd.setPlan_date(Utility.convertSqlDateToUtilDate(rs.getDate("dt")));
				flsd.setBrandName(rs.getString("brand_name"));
				flsd.setPackageName(rs.getString("package_name"));
				flsd.setQuantity(rs.getInt("quantity"));
				flsd.setNo_of_box(rs.getInt("boxes"));
				flsd.setNo_of_planned_bottle(rs.getInt("bottles"));

				
				list.add(flsd);
				
				
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
		
		return list;
	}
	
	
	// --------------------mohsin code ----------------------------------------

	
		public int maxIdfl2D() {

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
			return maxid + 1;

		}
	
	
	
	
	//=======================save for recieve=====================
	
	
	public String saveRecieved(StockAdjustmentAction action) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;

		int seq = this.maxIdfl2D();
		int status = 0;

		String gatepass = "ADJUSTMENT_NEWSTOCK";

		String queryInsert = "	INSERT INTO fl2d.fl2_2b_receiving_stock_trxn ( "
				+ "	seq, gatepass_no, fl2_2btype, brand_id, size_ml,batch_no, recv_box,  "
				+ "    recv_bottels, breakage_bottels, total_recv_bottels, pckg_id, created_date, fl2_2bid, box_size ,"
				+ " recvdfromunittype, rcvdunitid, loginusr) "
				+ "	VALUES (?, 'ADJUSTMENT_NEWSTOCK', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

		try {

			conn = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(queryInsert);

			
			for (int i = 0; i < action.getBrandPackagingDataList().size(); i++) {
				StockAdjustmentDT dt = (StockAdjustmentDT) action
						.getBrandPackagingDataList().get(i);
				if (dt.getBrandPackagingData_PlanNoOfBottling() > 0) {
					pstmt.setInt(1, ++seq);
					pstmt.setString(2, action.getLoginType());
					pstmt.setInt(3,
							Integer.parseInt(dt.getBrandPackagingData_Brand()));
					pstmt.setLong(4,
							new Double(dt.getBrandPackagingData_Quantity())
									.longValue());
					pstmt.setString(5, "NA");
					pstmt.setDouble(6, dt.getBrandPackagingData_NoOfBoxes());
					pstmt.setDouble(7,
							dt.getBrandPackagingData_PlanNoOfBottling());
					pstmt.setDouble(8, dt.getBrandPackagingData_Breakage());

					pstmt.setDouble(9,
							dt.getBrandPackagingData_PlanNoOfBottling());
					pstmt.setInt(10, Integer.parseInt(dt
							.getBrandPackagingData_Packaging()));

					pstmt.setDate(11,
							Utility.convertUtilDateToSQLDate(new Date()));
					pstmt.setInt(12, action.getLoginId());

					pstmt.setDouble(13, (dt
							.getBrandPackagingData_PlanNoOfBottling() / dt
							.getBrandPackagingData_NoOfBoxes()));
					pstmt.setString(14, "NA");
					pstmt.setString(15, "NA");
					pstmt.setString(16, ResourceUtil.getUserNameAllReq());

					status = pstmt.executeUpdate();
					

				}
			}
			if (status > 0) {
				
				status = 0;

				for (int i = 0; i < action.getBrandPackagingDataList().size(); i++) {
					StockAdjustmentDT dt = (StockAdjustmentDT) action
							.getBrandPackagingDataList().get(i);

					String queryUpdateInsert = "	INSERT INTO fl2d.fl2_2b_stock( "
							+ "			id, type, brand_id, pckg_id, recv_total_bottels)  "
							+ "			VALUES (?, ?, ?, ?, ? )  "
							+ "			ON CONFLICT ON CONSTRAINT fl2_2b_receiving_stock_master_manage_pkey "
							+ "			do update set recv_total_bottels =  COALESCE(fl2d.fl2_2b_stock.recv_total_bottels,0.0) + "
							+ dt.getBrandPackagingData_PlanNoOfBottling() + " ";

					if (dt.getBrandPackagingData_PlanNoOfBottling() > 0) {

						pstmt = conn.prepareStatement(queryUpdateInsert);

						pstmt.setInt(1, action.getLoginId());
						pstmt.setString(2, action.getLoginType());

						pstmt.setInt(3, Integer.parseInt(dt
								.getBrandPackagingData_Brand()));
						pstmt.setInt(4, Integer.parseInt(dt
								.getBrandPackagingData_Packaging()));
						pstmt.setInt(5, dt.getBrandPackagingData_PlanNoOfBottling());

						status = pstmt.executeUpdate();
						
						 

					}
				}
			}

			if (status > 0) {
				conn.setAutoCommit(true);
				ResourceUtil.addMessage(Constants.SAVED_SUCESSFULLY,
						Constants.SAVED_SUCESSFULLY);
				action.getBrandPackagingDataList().clear();

			} else {
				conn.rollback();
				ResourceUtil.addMessage(Constants.NOT_SAVED,
						Constants.NOT_SAVED);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		return "";

	}
	
	public int getCurrentStock(StockAdjustmentDT dt){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String query = "";

		int stock = 0;
		
		try {
			

			FacesContext facesContext = FacesContext.getCurrentInstance();
			StockAdjustmentAction action= (StockAdjustmentAction)facesContext.getApplication().createValueBinding("#{stockAdjustmentAction}").getValue(facesContext);
			
			
			query =  "SELECT (recv_total_bottels-dispatchbotl) as stock FROM fl2d.fl2_2b_stock where id='"+action.getLoginId()+"'" +
					" and type='"+action.getLoginType()+"' and brand_id='"+Integer.parseInt(dt.getBrandPackagingData_Brand())+"'" +
					" and pckg_id='"+Integer.parseInt(dt.getBrandPackagingData_Packaging())+"'";
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				stock = rs.getInt("stock");
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
		return stock;
	}
	

}
