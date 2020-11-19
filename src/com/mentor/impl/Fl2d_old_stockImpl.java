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
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




import com.mentor.action.BWFL_StockReceiveAction;
import com.mentor.action.Fl2d_old_stockAction;
import com.mentor.connectiondb.ConnectionToDataBase;
import com.mentor.datatable.BWFL_StockReceiveDataTable;
import com.mentor.datatable.Fl2d_old_stockDT;


import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;


public class Fl2d_old_stockImpl {
	

	public String getSugarmill(Fl2d_old_stockAction ac ) {
		int id=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		String queryList=" ";
		try{
			
		queryList=	"select int_app_id, vch_firm_name, vch_mobile_no, vch_core_address from licence.fl2_2b_2d " +
					"where vch_mobile_no='"+ResourceUtil.getUserNameAllReq()+"'";
			
					con=ConnectionToDataBase.getConnection() ;
					pstmt=con.prepareStatement(queryList);

					rs= pstmt.executeQuery();

			while(rs.next())
			{ 
				ac.setDistillery_nm(rs.getString("vch_firm_name"));
				ac.setDistillery_id(rs.getInt("int_app_id"));
				ac.setDistillery_adrs(rs.getString("vch_core_address"));
		
				 
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
		return "";

	}
	
	
	
	////////////// ---------------------------m didt id ----------------------
	
	
	public int getSugarmill_Id( ) {
	 
		int id=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		String queryList="";
		try{

	    queryList= "select int_app_id, vch_firm_name, vch_mobile_no, vch_core_address from licence.fl2_2b_2d " +
				   "where vch_mobile_no='"+ResourceUtil.getUserNameAllReq()+"'";
					
					
			
			con=ConnectionToDataBase.getConnection() ;
			pstmt=con.prepareStatement(queryList);

			rs= pstmt.executeQuery();

			while(rs.next())
			{ 
				id=rs.getInt("int_app_id");
			 
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
		return id;

	}
	
	
	
	
	
	//------------------------------------------------------------------------------
	
	
	//----------------------------- get Liqure Type --------------------------------------------

	public ArrayList getLiqureType()
	{
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);
		String SQl = "SELECT id, description FROM distillery.imfl_type WHERE id !=3 ORDER BY id ";
		try
		{
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(SQl);
			rs = ps.executeQuery();
			while(rs.next())
			{
				item = new SelectItem();
				item.setLabel(rs.getString("description"));
				item.setValue(rs.getString("id"));
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


	
	//----------------------------- get Licence NO. --------------------------------------------

		public ArrayList getLicenseNo( Fl2d_old_stockAction action ,String lice)
		{
			ArrayList list = new ArrayList();
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			SelectItem item = new SelectItem();
			item.setLabel("--SELECT--");
			item.setValue("");
			list.add(item);
			
			
	
			
			String SQl = "select distinct license_number FROM distillery.brand_registration" +
					     " where int_fl2d_id ='"+action.getDistillery_id()+"'  and vch_license_type='"+lice.trim()+"' " ;
			try
			{
				
			// System.out.println(SQl);
				
				con = ConnectionToDataBase.getConnection();
				ps = con.prepareStatement(SQl);
							
				rs = ps.executeQuery();
				while(rs.next())
				{
					action.setLicenseNoFlag(false);
					
					item = new SelectItem();
					item.setLabel(rs.getString("license_number"));
					item.setValue(rs.getString("license_number"));
					
			
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

	
	
	
	
	
	
	
	//----------------------------- get Brand  --------------------------------------------

		public ArrayList getBrandName( )
		{
			FacesContext facesContext = FacesContext.getCurrentInstance();
			Fl2d_old_stockAction bof
			    = (Fl2d_old_stockAction)facesContext.getApplication()
			      .createValueBinding("#{fl2d_old_stockAction}").getValue(facesContext);
			
			String lic=bof.getLicenceType();
			String liquor=bof.getLiqureTypeId();
			String licNo=bof.getLicenceNoId();
			ArrayList list = new ArrayList();
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			SelectItem item = new SelectItem();
			item.setLabel("--SELECT--");
			item.setValue("");
			list.add(item);
			String sql = "";
					
			 sql= "SELECT brand_id, brand_name FROM distillery.brand_registration "
					+ "  where vch_license_type='FL2D' "
					+ "   and int_fl2d_id='"+this.getSugarmill_Id()+"' and  license_number ='"+licNo.trim()+"' "
					+ "     order by brand_id ";	
					
				
			try
			{ 
				con = ConnectionToDataBase.getConnection();
				ps = con.prepareStatement(sql);
					
				rs = ps.executeQuery();
				
				
							/*System.out.println("iside impl-----FL2D---------"+sql);
				
							System.out.println("-------333333333333   ------"+liquor);*/


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
								
								"SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id "+
										"	from distillery.brand_registration a , "+ 
										"	distillery.packaging_details b "+
										"	where a.brand_id=b.brand_id_fk  "+
									//	"	and a.distillery_id=?  "+
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
				
				
				
				
				
				
				

	//---------------------------------------------- save --------------------------
				
				
				
				public int maxId()
				{

					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					String query = " SELECT max(seq) as id FROM  fl2d.fl2d_old_stock";
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
			
				
				
				
				
				
				
				
				//----------------------save----------------------
				
				
				public void save(Fl2d_old_stockAction action) {

					Connection conn = null;
					PreparedStatement pstmt = null, psmt=null;
					ResultSet rs = null;
					int saveStatus = 0;
					String insQr = null, query=null, query1=null, query2=null;
					int sequence = maxId();
					try {
						
						
						insQr = "			INSERT INTO fl2d.fl2d_old_stock( "+
					    		"			int_fl2d_id, int_brand_id, int_pack_id, int_quantity, int_planned_bottles, "+ 
					    		"		    int_boxes, int_liquor_type, vch_license_type, plan_dt, licence_no, cr_date,seq,int_pack_size) "+
					    		"		VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?,?,?) ";
						
						
						

						conn = ConnectionToDataBase.getConnection();
						conn.setAutoCommit(false);

						if (action.getBrandPackagingDataList().size() > 0) {

							for (int i = 0; i < action.getBrandPackagingDataList().size(); i++) {
								saveStatus = 0;
								pstmt = conn.prepareStatement(insQr);

								int j = 1;

								Fl2d_old_stockDT table = (Fl2d_old_stockDT) action.getBrandPackagingDataList().get(i);
							 

									pstmt.setInt(1, action.getDistillery_id());
									pstmt.setInt(2, Integer.parseInt(table.getBrandPackagingData_Brand()));
									pstmt.setInt(3, Integer.parseInt(table.getBrandPackagingData_Packaging()));
									pstmt.setDouble(4, Double.parseDouble(table.getBrandPackagingData_Quantity()));
									pstmt.setDouble(5, table.getBrandPackagingData_PlanNoOfBottling());
									pstmt.setDouble(6, table.getBrandPackagingData_NoOfBoxes());
									pstmt.setInt(7,Integer.parseInt(action.getLiqureTypeId()));
									pstmt.setString(8, action.getLicenceType());
									pstmt.setDate(9, Utility.convertUtilDateToSQLDate(action.getDateOfBottling()));
									pstmt.setString(10, action.getLicenceNoId());
									pstmt.setDate(11,Utility.convertUtilDateToSQLDate(new Date()));
									pstmt.setInt(12, sequence);
									pstmt.setInt(13, new Double(table.getBrandPackagingData_PlanNoOfBottling()/table.getBrandPackagingData_NoOfBoxes()).intValue());
									
									saveStatus = pstmt.executeUpdate();
									sequence = sequence + 1;
									
									//System.out.println("---savestus 1---"+saveStatus);
							 
								
								if(saveStatus > 0)
								{
									saveStatus = 0;
									
								
								    query1 = " SELECT fl2d_id, licence_number, brand_id, pack_id, stock_boxes, stock_bottels," +
									 		 " dispatch_boxes, dispatch_bottels, licence_type FROM  fl2d.fl11_fl2d_old_stock " +
									 		 " WHERE "
										   + " fl2d_id='"+action.getDistillery_id()+"' " +
												
											 " AND "
											
										   + " licence_number='"+action.getLicenceNoId()+"' " +
											
											 " AND "
											
										   + " brand_id='"+table.getBrandPackagingData_Brand()+"' " +
													
											 " AND"
											
										   + " pack_id='"+table.getBrandPackagingData_Packaging()+"' ";
									 
									 

									psmt = conn.prepareStatement(query1);
									
									
									rs = psmt.executeQuery();
									
									
									if(rs.next()){
										 query = " UPDATE  fl2d.fl11_fl2d_old_stock " +
												 " SET  stock_boxes=COALESCE(stock_boxes,0)+'"+table.getBrandPackagingData_NoOfBoxes()+"' , " +
												 " stock_bottels=COALESCE(stock_bottels,0)+'"+table.getBrandPackagingData_PlanNoOfBottling() + "' " + 
												 " WHERE " +
												 " fl2d_id='"+ action.getDistillery_id() + "' " + "AND" +
												 " licence_number='" + action.getLicenceNoId()+ "' " + "AND" +
												 " brand_id='"+ table.getBrandPackagingData_Brand() + "' " + "AND" +
												 " pack_id='"+ table.getBrandPackagingData_Packaging() + "' ";
										 psmt = conn.prepareStatement(query);
										 saveStatus = psmt.executeUpdate();
									}
									else
									{
																				 
										query2=" INSERT INTO  fl2d.fl11_fl2d_old_stock( fl2d_id, licence_number, brand_id, pack_id, " +
												"stock_boxes, stock_bottels, dispatch_boxes, dispatch_bottels, licence_type) " +
												"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
										 
										 
										 psmt = conn.prepareStatement(query2);
										
										 
										 if (table.isUpdateflg() == false) {
												psmt.setInt(1, action.getDistillery_id());
												psmt.setString(2, action.getLicenceNoId());
												psmt.setInt(3, Integer.parseInt(table.getBrandPackagingData_Brand()));
												psmt.setInt(4, Integer.parseInt(table.getBrandPackagingData_Packaging()));
												
												psmt.setDouble(5, table.getBrandPackagingData_NoOfBoxes());
												
									System.out.println("five...."+table.getBrandPackagingData_NoOfBoxes());
												
												psmt.setDouble(6, table.getBrandPackagingData_PlanNoOfBottling());
												psmt.setInt(7, 0);
												psmt.setInt(8, 0);
												psmt.setString(9, action.getLicenceType());

												saveStatus = psmt.executeUpdate();
												
												//System.out.println("=====saveStatus 3===="+saveStatus);
												//System.out.println("------------insert query----"+ query2);
											}
									}
									
								}
							}
						}
						
						

						if (saveStatus > 0) {
							conn.commit();
							ResourceUtil.addErrorMessage(Constants.SAVED_SUCESSFULLY,
									Constants.SAVED_SUCESSFULLY);
							action.reset();

						} else {
							// action.reset();
							conn.rollback();
							ResourceUtil.addErrorMessage(Constants.NOT_SAVED,Constants.NOT_SAVED);

						}

					}
					 catch(Exception e) { 
					 e.printStackTrace(); 
						 
						 FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(" Same Date Same Brand and Same Packaging  Not Allow For Botteling Plan ", "Same Date Same Brand and Same Packaging Not Allow For Botteling Plan")); //brand_Id,fl2d_ID and DAte PK
						 
						 System.out.println("duplicate key ------mohsin --------------");
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
							e.printStackTrace();
						}
					}

				}
				
				
				//---------------------------------------------
				
				
				/*public void save1( Fl2d_old_stockAction action)
				{
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs=null;
					int saveStatus=0;
		//	int challanId=getMaxChallanId()+1;
					try
					{
						
						
				     String query = 
				    		 
				    		 
				    		"			INSERT INTO fl2d.fl2d_old_stock( "+
				    		"			int_fl2d_id, int_brand_id, int_pack_id, int_quantity, int_planned_bottles, "+ 
				    		"		    int_boxes, int_liquor_type, vch_license_type, plan_dt, licence_no, cr_date) "+
				    		"		VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?) ";
				     
				     
				//     String delete=" delete from distillery.mst_bottling_plan where  int_distillery_id=? and vch_license_type =? and plan_dt =?";
				     
				     conn = ConnectionToDataBase.getConnection();
					
				      
					if(action.getBrandPackagingDataList().size()>0)
					{
						for(int i=0;i<action.getBrandPackagingDataList().size();i++)
						{
							saveStatus=0;
							pstmt=conn.prepareStatement(query);
							int j=1;
						
						Fl2d_old_stockDT 	table=(Fl2d_old_stockDT)action.getBrandPackagingDataList().get(i);
						if(table.isUpdateflg()==false)
						{
						pstmt.setInt(1,action.getDistillery_id());
						pstmt.setInt(2,Integer.parseInt(table.getBrandPackagingData_Brand()));
						pstmt.setInt(3,Integer.parseInt(table.getBrandPackagingData_Packaging()));
						
						pstmt.setDouble(4, Double.parseDouble(table.getBrandPackagingData_Quantity()));
						pstmt.setDouble(5, table.getBrandPackagingData_PlanNoOfBottling());
						pstmt.setDouble(6, table.getBrandPackagingData_NoOfBoxes());
						pstmt.setInt(7,Integer.parseInt(action.getLiqureTypeId()));
						pstmt.setString(8,action.getLicenceType());
						pstmt.setDate(9, Utility.convertUtilDateToSQLDate(action.getDateOfBottling()));
						pstmt.setString(10,action.getLicenceNoId());
						pstmt.setDate(11, Utility.convertUtilDateToSQLDate(new Date()));
						
						saveStatus=pstmt.executeUpdate();
						}}
					}
					
					if(saveStatus>0)
					{
						ResourceUtil.addErrorMessage(Constants.SAVED_SUCESSFULLY, Constants.SAVED_SUCESSFULLY);
						action.reset();

					}
					else
					{
					//	action.reset();
						ResourceUtil.addErrorMessage(Constants.NOT_SAVED, Constants.NOT_SAVED);
				
					}
					
					
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
					catch(SQLException e)
					{
						FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(" Same Date Same Brand and Same Packaging  Not Allow For Botteling Plan ", "Same Date Same Brand and Same Packaging Not Allow For Botteling Plan")); //brand_Id,Distillery_ID and DAte PK
					
						//brand_Id,Distillery_ID and DAte PK
					}
					
					
					finally
					{
			      		try
			      		{	
			      			
			          		if(conn!=null)conn.close();
			          		if(pstmt!=null)pstmt.close();
			          		if(rs!=null)rs.close();
			          		
			          		
			      		}
			      		catch(Exception e)
			      		{
			      			e.printStackTrace();
			      		}
			      	}
				
				}*/
				
				
				
				
			//---------------------- get add Row data -----------------------------
				
				public ArrayList getAddRowData(Fl2d_old_stockAction ac,String lic_typ)
				 {
					
					
					
					 ArrayList list = new ArrayList();
					 Connection con=null;
						PreparedStatement ps=null;
						ResultSet rs=null;
						
						String sql=
							"	SELECT int_fl2d_id, int_brand_id, int_pack_id, int_quantity,  "+
							"	int_planned_bottles, int_boxes, int_liquor_type, "+
							"	vch_license_type, plan_dt, licence_no, cr_date,seq "+
							"		FROM  fl2d.fl2d_old_stock where  int_fl2d_id=? and vch_license_type=?  "+
							"	    and  plan_dt = ?";
								
							
						
						
						
						
						System.out.println("brewery id:"+ac.getDistillery_id());
						System.out.println("vch_lic type"+lic_typ);
						System.out.println("plan date"+ Utility.convertUtilDateToSQLDate(ac.getDateOfBottling()));
						
						
						try{
							con=ConnectionToDataBase.getConnection();
							ps=con.prepareStatement(sql);
							
						ps.setInt(1, ac.getDistillery_id());
						//ps.setString(2, ac.getLicenceType());
						ps.setString(2, lic_typ);
						
						ps.setDate(3, Utility.convertUtilDateToSQLDate(ac.getDateOfBottling()));
						
						rs=ps.executeQuery();
						
						while(rs.next()){
						
							Fl2d_old_stockDT dt=new Fl2d_old_stockDT();
							
							
							dt.setBrandPackagingData_Brand(rs.getString("int_brand_id"));
							dt.setBrandPackagingData_Packaging(rs.getString("int_pack_id"));
							dt.setBrandPackagingData_Quantity(rs.getString("int_quantity"));
							dt.setBrandPackagingData_PlanNoOfBottling(rs.getDouble("int_planned_bottles"));
							dt.setBrandPackagingData_NoOfBoxes(rs.getDouble("int_boxes"));
							
							dt.setUpdateflg(true);
							ac.setLicenceNoId(rs.getString("licence_no").trim());
							ac.setCr_date(Utility.convertUtilDateToSQLDate(rs.getDate("cr_date")));
							
							ac.setCheckLicenceType(rs.getString("vch_license_type"));
							
							ac.setLicenseNoFlag(true);
							dt.setSeq(rs.getInt("seq"));
							
							
							list.add(dt);
							
							System.out.println("id=");
							
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
				 
				
				
				
				
				
				
				
				
	//------------------------------------ SHOW DATA TABLE-----------------------
				
				
				 public ArrayList getData(Fl2d_old_stockAction ac)
				 {
					 ArrayList list = new ArrayList();
					 Connection con=null;
						PreparedStatement ps=null;
						ResultSet rs=null;
						
						String sql=
								
								"	SELECT a.int_pack_size,a.seq,c.code_generate_through,coalesce(b.tracking_flg,'N') as tracking_flg,a.finalized_date,a.finalized_flag,a.licence_no,replace(a.licence_no,' ','')as licencenoo,a.int_fl2d_id, a.int_brand_id,b.brand_name, a.int_pack_id,c.package_name , a.int_quantity, "+
										"	a.int_planned_bottles, a.int_boxes, a.int_liquor_type,d.description, a.vch_license_type, a.plan_dt "+
										"		FROM fl2d.fl2d_old_stock a ,distillery.brand_registration b, "+
										"	    distillery.packaging_details c ,distillery.imfl_type d "+
										"	    where a.int_brand_id=b.brand_id  "+
										"	   and  b.brand_id=c.brand_id_fk "+
										"	  and   a.int_pack_id=c.package_id "+
										"	  and  a.int_liquor_type=d.id "+
										"	 and a.int_fl2d_id =?  ";
											
								//user_name ='"+ResourceUtil.getUserNameAllReq()+"'";
						
						System.out.println("----- get data in data table  ---"+sql);
						try{
							con=ConnectionToDataBase.getConnection();
						ps=con.prepareStatement(sql);
						ps.setInt(1, ac.getDistillery_id());
						rs=ps.executeQuery();
						while(rs.next()){
						
							Fl2d_old_stockDT dt=new Fl2d_old_stockDT();
							dt.setPack_size(rs.getInt("int_pack_size"));
							dt.setShowDataTable_Date(rs.getDate("plan_dt"));
							dt.setShowDataTable_LiqureType(rs.getString("description"));
							dt.setShowDataTable_LicenceType(rs.getString("vch_license_type"));
							dt.setShowDataTable_Brand(rs.getString("brand_name"));
							dt.setShowDataTable_Packging(rs.getString("package_name"));
							dt.setShowDataTable_Quntity(rs.getString("int_quantity"));
							dt.setShowDataTable_PlanNoOfBottling(rs.getString("int_planned_bottles"));
							dt.setShowDataTable_NoOfBoxes(rs.getString("int_boxes"));
							dt.setPackagingId(rs.getInt("int_pack_id"));
							dt.setLicenceNo(rs.getString("licence_no"));
							dt.setBrandId(rs.getInt("int_brand_id"));
							dt.setLicencenoo(rs.getString("licencenoo").replaceAll("/",""));
							dt.setFinalizedFlag(rs.getString("finalized_flag"));
							dt.setFinalize_Date(Utility.convertSqlDateToUtilDate(rs.getDate("finalized_date")));
							dt.setGtinno(rs.getString("code_generate_through"));
							
							dt.setPlanid(rs.getInt("seq"));
							
							Date dat=Utility.convertSqlDateToUtilDate(rs.getDate("plan_dt"));
							System.out.println("date finalize"+dat);
						          

						    
						     DateFormat   formatter = new SimpleDateFormat("yyMMdd");
						   String date = formatter.format(dat);
						   dt.setFinalizedDateString(date);
						   System.out.println(date);
						   if(!rs.getString("tracking_flg").equalsIgnoreCase("Y")){
							   dt.setFinalizedFlag("N");   
						   }
							
							
						
							list.add(dt);	
						}}catch(Exception e){e.printStackTrace();}
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
				 
				
				// --------------------------------- bool--------------------------------------
				 
				/* public boolean ckeck(Fl2d_old_stockAction ac ) {
					 
						boolean flag=false;
						Connection con=null;
						PreparedStatement pstmt=null;
						ResultSet rs =null;
						try{

							String queryList=
									
									
									"	SELECT int_distillery_id, int_brand_id, int_pack_id, int_quantity,  "+
											"	int_planned_bottles, int_boxes, int_liquor_type, "+
											"	vch_license_type, plan_dt, licence_no, cr_date "+
											"		FROM distillery.mst_bottling_plan_of_oldstock where  int_distillery_id=? and vch_license_type=?  "+
											"	    and  plan_dt = ? ";
							
							
							
							con=ConnectionToDataBase.getConnection() ;
							pstmt=con.prepareStatement(queryList);
							
							pstmt.setInt(1, ac.getDistillery_id());
							//ps.setString(2, ac.getLicenceType());
							pstmt.setString(2, ac.getLicenceType());
							
							pstmt.setDate(3, Utility.convertUtilDateToSQLDate(ac.getDateOfBottling()));

							rs= pstmt.executeQuery();

							if(rs.next())
							{ 
								
								 flag=true;
							}
							
							else{
								 flag=false;
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
						return flag;

					}*/
					
					
				 
				 
//////////////////////////////////////////////////////////////////////////





public void dataFinalize(Fl2d_old_stockAction action,Fl2d_old_stockDT dt) {
	Connection conn = null;
	Connection conn1 = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	PreparedStatement pstmt3 = null;
	PreparedStatement pstmt4 = null;
	String gtinNo = "";
	long boxsize = 0;
	long caseno = 0;
	int status=0;
	String bottlecode="";
	/*String sql = "INSERT INTO public.bottling_fl2d(  "
			+ " dispatch_date, gtin_no, serial_no_start, serial_no_end,case_no,request_id) "
			+ "	VALUES (?, ?, ?, ?, ?, ?)";*/

	String sql=
			
			
			
			        "INSERT INTO bottling_unmapped.fl2d( "+
					"date_plan,etin,serial_no_start, serial_no_end,  casecode,plan_id, bottle_code, fl11gatepass, fl11_date, fl36gatepass, fl36_date) "+
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
	
	String update="UPDATE  fl2d.fl2d_old_stock "+
			" SET   finalized_flag='F' ,finalized_date=? ,bottling_seq_frm=? ,bottling_seq_to=?"+
			"WHERE int_fl2d_id=? and int_brand_id=? and int_pack_id=? and plan_dt=? and seq="+dt.getPlanid()+"";


	try {
		//gtinNo = getBrandPackagingGtinNo(action, dt);
		gtinNo=dt.getGtinno();
		// boxsize=getBrandPackagingNoOfBottle(action,dt);

		// long noOfBottle=
		// (Long.parseLong(dt.getShowDataTable_PlanNoOfBottling())/boxsize);

		System.out
				.println("new Double(dt.getShowDataTable_PlanNoOfBottling()).longValue()"
						+ new Double(dt.getShowDataTable_PlanNoOfBottling())
								.longValue());
		
		
		 long	serialno = getSerialNoFl2D(new Double(
				dt.getShowDataTable_PlanNoOfBottling()).longValue());
		
		
		
		
		// long
		// i=(Long.parseLong(dt.getShowDataTable_PlanNoOfBottling())/(Long.parseLong(dt.getShowDataTable_NoOfBoxes())));
		System.out.println("gtinNo" + gtinNo + "seqqqqqqqqqqqqqqqqqqqqqqqq"
				+ serialno);
		if (!gtinNo.equals("") && serialno != 0) {
			conn = ConnectionToDataBase.getConnection3();
			conn1 = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			conn1.setAutoCommit(false);
			pstmt1=	conn1.prepareStatement(update);

			pstmt1.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			pstmt1.setLong(2, serialno);
			pstmt1.setLong(3, serialno+new Double(dt.getShowDataTable_PlanNoOfBottling()).longValue());
			
			pstmt1.setInt(4, action.getDistillery_id());
			pstmt1.setInt(5, dt.getBrandId());
			pstmt1.setInt(6,dt.getPackagingId());
			pstmt1.setDate(7,Utility.convertUtilDateToSQLDate(dt.getShowDataTable_Date()));
			status=pstmt1.executeUpdate();

			if (dt.getShowDataTable_LicenceType().equals("FL2D")
					&& status > 0) {
				status = 0;
				
				 
				System.out.println("dt.getShowDataTable_Quntity()"+dt.getShowDataTable_Quntity()+"  dt.getShowDataTable_NoOfBoxes()"+dt
						.getShowDataTable_NoOfBoxes());
				pstmt1 = conn.prepareStatement(sql);
				//pstmt2 = conn.prepareStatement(unmapped_sql);
				for (int i = 0; i < Long.parseLong(dt.getShowDataTable_NoOfBoxes()); i++) {
					caseno = getcaseNoFl2d();
                   bottlecode="";
			

					pstmt1.setDate(1, Utility.convertUtilDateToSQLDate(dt.getShowDataTable_Date()));
					pstmt1.setString(2, gtinNo);
					pstmt1.setLong(3, serialno);
					 pstmt1.setLong(4, serialno+(dt.getPack_size()-1));
				     
                     pstmt1.setString(5, String.valueOf(caseno));
					pstmt1.setInt(6, dt.getPlanid());
					pstmt1.setString(7,"NA");
					pstmt1.setString(8,"NA");
					pstmt1.setDate(9, null);
					pstmt1.setString(10, "NA");
					pstmt1.setDate(11, null);
				  
					serialno+=dt.getPack_size();
					pstmt1.addBatch();
					
				}
			}
			
			int[] status1=pstmt1.executeBatch();
			
			if (status1.length > 0) {
				status = 0;
				boolean flag = write(dt, action, conn);

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
	}
}



public boolean write(Fl2d_old_stockDT dt,
		Fl2d_old_stockAction  action, Connection conn) {

	System.out.println("excel innn");

	

	String fl3 =
	
	
	
	
	""
	+
	
	"	select to_char(y.gs, 'fm000000000000')as GENERATE_SERIES , y.dispatch_date, y.gtin_no,"
	+ "	  y.serial_no_start, y.serial_no_end , "
	+ "	to_char(y.case_no::numeric , 'fm0000000000')as case_no from( "
	+ "	select  GENERATE_SERIES(x.serial_no_start::numeric ,x.serial_no_end::numeric ) as gs ,x.serial_no_start ,x.serial_no_end, "
	+ "	x.case_no,x.dispatch_date,x.gtin_no "
	+ "	from ( "
	+ "	SELECT  date_plan as dispatch_date, etin as gtin_no, serial_no_start, serial_no_end,  casecode as case_no "
	+ "	FROM bottling_unmapped.fl2d a "
	+ "	where  date_plan=?   and etin=? and plan_id=?)x)y";

	
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

		

		if (dt.getShowDataTable_LicenceType().equals("FL2D")) {

			pstmt = conn.prepareStatement(fl3);

			
			
			pstmt.setDate(1, Utility.convertUtilDateToSQLDate(dt
					.getShowDataTable_Date()));
			
			pstmt.setString(2, dt.getGtinno());
			pstmt.setInt(3, dt.getPlanid());
			rs = pstmt.executeQuery();
			System.out.println("excecute query fl3");

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

			// System.out.println("i==========="+i++);
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
			/*if (dt.getShowDataTable_Quntity().equals("750")) {
				noOfUnit = "12";
			} else if (dt.getShowDataTable_Quntity().equals("375")) {
				noOfUnit = "24";
			} else if (dt.getShowDataTable_Quntity().equals("180")) {
				noOfUnit = "48";
			} else if (dt.getShowDataTable_Quntity().equals("60")) {
				noOfUnit = "150";
			} else if (dt.getShowDataTable_Quntity().equals("200")) {
				noOfUnit = "45";
			} else if (dt.getShowDataTable_Quntity().equals("90")) {
				noOfUnit = "96";
			} else if (dt.getShowDataTable_Quntity().equals("1000")) {
				noOfUnit = "9";
			} else if (dt.getShowDataTable_Quntity().equals("2000")) {
				noOfUnit = "4";
			}else if (dt.getShowDataTable_Quntity().equals("355")) {
				noOfUnit = "24";
			}else if (dt.getShowDataTable_Quntity().equals("330")) {
				noOfUnit = "24";
			}
			else if (dt.getShowDataTable_Quntity().equals("700")) {
				noOfUnit = "6";
			}*/
			
			noOfUnit=Integer.toString(dt.getPack_size());
			
			
			if(noOfUnit.length()>2)
			{
				noOfUnit=noOfUnit.substring(0, 2);
			}else if(noOfUnit.length()<2)
			{
				noOfUnit="0"+noOfUnit;
			}
			
			cellD1.setCellValue("01" + rs.getString("gtin_no") + "13"
					+ date + "37" + noOfUnit + "21"
					+ rs.getString("case_no"));

			XSSFCell cellE1 = row1.createCell((int) 4);
			cellE1.setCellValue("01" + rs.getString("gtin_no") + "13"
					+ date + "21" + rs.getString("GENERATE_SERIES"));

		}
		fileOut = new FileOutputStream(relativePath + "//ExciseUp//Excel//FL2D"+dt.getPlanid()+""
				+ dt.getGtinno() + "" + date + ".xls");

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

		System.out.println("xls2" + e.getMessage());
		e.printStackTrace();
	}

	return flag;
}









public String getBrandPackagingGtinNo(Fl2d_old_stockAction action,Fl2d_old_stockDT dt)	
{


String gtin="";
String sql="select b.code_generate_through from distillery.brand_registration a, distillery.packaging_details b "+
"where  a.brand_id=b.brand_id_fk and a.brand_id=? and b.package_id=? and b.quantity=? and vch_license_type=? and a.int_fl2d_id=?";


Connection con=null;
PreparedStatement pstmt=null;
ResultSet rs=null;

System.out.println("----------------------------------");

System.out.println("brandId"+dt.getBrandId()+"  package_id"+dt.getPackagingId()+" b.quantity "+Integer.parseInt(dt.getShowDataTable_Quntity()) +" vch_license_type "+dt.getShowDataTable_LicenceType() +" a.int_fl2d_id "+ action.getDistillery_id());

try{
con=ConnectionToDataBase.getConnection();
pstmt=con.prepareStatement(sql);
pstmt.setInt(1, dt.getBrandId());
pstmt.setInt(2,dt.getPackagingId());
pstmt.setInt(3, Integer.parseInt(dt.getShowDataTable_Quntity()));
pstmt.setString(4,dt.getShowDataTable_LicenceType());
pstmt.setInt(5, action.getDistillery_id());
rs=pstmt.executeQuery();
while(rs.next())
{
gtin=rs.getString("code_generate_through");
}




}catch(Exception e)
{
e.printStackTrace();
}finally{


try{
if(rs!=null)rs.close();

if(pstmt!=null)pstmt.close();

if(con!=null)con.close();


}catch(Exception e)
{
FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
e.printStackTrace();
}
}

System.out.println("gtin no------ >> "+gtin);
return gtin;



}		 








				 
public boolean checkData(Fl2d_old_stockAction  action)
{
	boolean flag=false;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	int j=0;
	  String sql=
			     
			   "  SELECT int_fl2d_id, int_brand_id, int_pack_id, int_quantity, int_planned_bottles, "+
			   "  int_boxes, int_liquor_type, vch_license_type, plan_dt, "+
			   "  licence_no, cr_date, finalized_flag, finalized_date "+
			   "  FROM fl2d.fl2d_old_stock "+
			  "   where int_fl2d_id=? and int_brand_id=? and int_pack_id=? and plan_dt=? ";
	try{
		  conn = ConnectionToDataBase.getConnection();
		
		if(action.getBrandPackagingDataList().size()>0)
		{
			
			pstmt=conn.prepareStatement(sql);
			for(int i=0;i<action.getBrandPackagingDataList().size();i++)
			{
				Fl2d_old_stockDT 	table=(Fl2d_old_stockDT)action.getBrandPackagingDataList().get(i);
				//pstmt.setInt(1,getMaxChallanIdDetail()+1);
				pstmt.setInt(1,action.getDistillery_id());
				pstmt.setInt(2,Integer.parseInt(table.getBrandPackagingData_Brand()));
				pstmt.setInt(3,Integer.parseInt(table.getBrandPackagingData_Packaging()));
				pstmt.setDate(4, Utility.convertUtilDateToSQLDate(action.getDateOfBottling()));
				rs=pstmt.executeQuery();
				while(rs.next())
				{
				j++;	
					
				}
				
				
			}
			if(j>0)
			{
				flag=false;	
			}else{
				flag=true;
			}
			
			
			
		}else{
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Add One Row", "Please Add One Row"));
		}
	}catch(Exception e)
	{
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
	}finally{
		
		try{
			if(conn!=null)conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	return flag;
}

				 








//====================================================================================================================
	
	
	
		public synchronized long getSerialClBottle(long noOfSequenc) {
			String sql = " select     nextval('public.cl_manual_bottle_code')";
			String sqll = "ALTER SEQUENCE public.cl_manual_bottle_code RESTART WITH ? ";
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
						serialNo = serialNo + 1;
					}
					//System.out.println("noOfSequenc " + noOfSequenc);

					pstmt1 = conn
							.prepareStatement("ALTER SEQUENCE public.cl_manual_bottle_code RESTART WITH "
									+ (noOfSequenc + serialNo));

					/*System.out
							.println("ALTER SEQUENCE public.serial_seq RESTART WITH "
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
		
		public synchronized long getSerialFl3Bottle(long noOfSequenc) {
			String sql = " select     nextval('public.fl3_manual_bottle_code')";
			String sqll = "ALTER SEQUENCE public.fl3_manual_bottle_code RESTART WITH ? ";
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
						serialNo = serialNo + 1;
					}
					//System.out.println("noOfSequenc " + noOfSequenc);

					pstmt1 = conn
							.prepareStatement("ALTER SEQUENCE public.fl3_manual_bottle_code RESTART WITH "
									+ (noOfSequenc + serialNo));

					/*System.out
							.println("ALTER SEQUENCE public.serial_seq RESTART WITH "
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
		
		
		public synchronized long getSerialFl3aBottle(long noOfSequenc) {
			String sql = " select     nextval('public.fl3a_manual_bottle_code')";
			String sqll = "ALTER SEQUENCE public.fl3a_manual_bottle_code RESTART WITH ? ";
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
						serialNo = serialNo + 1;
					}
					//System.out.println("noOfSequenc " + noOfSequenc);

					pstmt1 = conn
							.prepareStatement("ALTER SEQUENCE public.fl3a_manual_bottle_code RESTART WITH "
									+ (noOfSequenc + serialNo));

					/*System.out
							.println("ALTER SEQUENCE public.serial_seq RESTART WITH "
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
		public synchronized long getcaseNoCl() {
			String sql = " select     nextval('public.cl_manual_case_code')";

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
		
		
		
		
		public synchronized long getcaseNoFl3() {
			String sql = " select     nextval('public.fl3_manual_case_code')";

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
		
		
		public synchronized long getcaseNoFl3a() {
			String sql = " select     nextval('public.fl3a_manual_case_code')";

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
					
		public synchronized long getSerialBWFLBottle(long noOfSequenc) {
			String sql = " select     nextval('public.bwfl_manual_bottle_code')";
			//String sqll = "ALTER SEQUENCE public.bwfl_manual_bottle_code RESTART WITH ? ";
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
						serialNo = serialNo + 1;
					}
					//System.out.println("noOfSequenc " + noOfSequenc);

					pstmt1 = conn
							.prepareStatement("ALTER SEQUENCE public.bwfl_manual_bottle_code RESTART WITH "
									+ (noOfSequenc + serialNo));

					// System.out.println("ALTER SEQUENCE public.serial_seq RESTART WITH "+ (noOfSequenc + serialNo));
					
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
			System.out.println("serial no:-----------moh   ____"+serialNo);
			return serialNo;
		
			

			
		
		
		
		}
		
		
		public synchronized long getBwflcaseNo()
		{
		String sql=  " select  nextval('public.bwfl_manual_case_code')";

		Connection conn=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		long serialNo=0;
		long currseq=0;

		try{
		conn=ConnectionToDataBase.getConnection3();

		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		if(rs.next())
		{
		serialNo=rs.getLong(1);
		if(serialNo==0)
		{
		serialNo=serialNo;
		}


		}

		}
		catch(Exception e)
		{
		e.printStackTrace();
		}finally{


		try{
		if(rs!=null)rs.close();

		if(pstmt!=null)pstmt.close();

		if(conn!=null)conn.close();


		}catch(Exception e)
		{
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
		e.printStackTrace();
		}
		}
		System.out.println("serial No-------------moh-----"+serialNo);
		return serialNo;
		}

		public synchronized long getcaseNoFl2d() {
			String sql = " select     nextval('public.fl3_3a_old_stock_case_code')";

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
		
		public synchronized long getSerialNoFl2D(long noOfSequenc) {
			String sql = " select     nextval('public.fl_2d_serial_seq')";
			String sqll = "ALTER SEQUENCE public.fl_2d_serial_seq RESTART WITH ? ";
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
					System.out.println("noOfSequenc " + noOfSequenc);

					pstmt1 = conn
							.prepareStatement("ALTER SEQUENCE public.fl_2d_serial_seq RESTART WITH "
									+ (noOfSequenc + serialNo));

					System.out
							.println("ALTER SEQUENCE public.fl_2d_serial_seq RESTART WITH "
									+ (noOfSequenc + serialNo));
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
		
		
}
