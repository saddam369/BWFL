package com.mentor.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


import com.mentor.action.Rollover_Stock_for_fl2dAction;
import com.mentor.datatable.Rollover_Stock_for_fl2dDataTable;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;

public class Rollover_Stock_for_fl2dImpl {
	
	

	public String getDetails(Rollover_Stock_for_fl2dAction action){
		 
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String query="";
		try {
			query= " SELECT vch_mobile_no,int_app_id,vch_firm_name, vch_core_address,vch_license_type" +
					" FROM licence.fl2_2b_2d_19_20 WHERE vch_mobile_no = '"+ResourceUtil.getUserNameAllReq().trim() + "' ";
			con=ConnectionToDataBase.getConnection();  
			pst=con.prepareStatement(query);
			rs=pst.executeQuery();
		while(rs.next()){
			
			action.setApp_id(rs.getInt("int_app_id"));
			action.setName(rs.getString("vch_firm_name"));
				/*action.setLic_no(rs.getString("vch_licence_no"));*/
			action.setLic_type_str(rs.getString("vch_license_type"));
				//act.setEtin_unit_id(rs.getString("etin_unit_id"));
			 //   act.setLoginid(rs.getInt("loginid"));
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
				pst.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
				}
			
		}
		return "";
	}



	public ArrayList displaylistimpl(Rollover_Stock_for_fl2dAction act ) {
		
        ArrayList list=new ArrayList();
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        String query="";
        int i=1,cases=1;
        
        try {
       
    			query=  " SELECT distinct c.seq,coalesce(m.deo_flg,'NA') as deo_flg,c.int_brand_id,COALESCE(c.final_flag, 'N') as final_flag ,  " +
    					" m.rollover_boxes, c.seq ,c.licence_no , a.package_name,COALESCE(c.final_flag, 'NA')  AS final_flag, " +
    					" a.package_id,a.permit, a.duty, a.adduty, c.vch_license_type,  c.int_brand_id,  b.brand_name,c.int_pack_id, " +
    					" c.box_size ,c.int_boxes ,c.int_planned_bottles-coalesce(c.dispatch_36,0)  as avlbottle,   " +
    					" ((c.int_planned_bottles-coalesce(c.dispatch_36,0))/(c.int_planned_bottles/c.int_boxes))   as dispatchd_box, " +
    					" ((c.int_planned_bottles-coalesce(c.dispatch_36,0))/c.box_size) as avlboxes , " +
    					" (c.int_planned_bottles/c.int_boxes) as export_box_size   " +
    					" FROM distillery.packaging_details_19_20 a, distillery.brand_registration_19_20 b,  " +
    					" fl2d.mst_stock_receive_19_20 c   left outer join fl2d.rollover_stock_for_fl2d  m on m.package_id=c.int_pack_id and " +
    					" m.unit_id=c.int_fl2d_id and c.seq=m.seq WHERE a.brand_id_fk=b.brand_id AND  a.brand_id_fk=c.int_brand_id     " +
    					" AND a.package_id=c.int_pack_id AND  b.brand_id=c.int_brand_id  AND c.int_fl2d_id='"+act.getApp_id()+"'    " +
    					" AND c.vch_license_type='FL2D' and a.vch_renewp='Y' " +
    					" and c.finalized_flag='F' " +
    					" AND  COALESCE(c.dispatch_36,0)<c.int_planned_bottles "; 


    					/*" SELECT DISTINCT coalesce(g.deo_flg,'NA') as deo_flg,c.int_brand_id,COALESCE(c.final_flag, 'N') as final_flag , b.brand_name,c.int_pack_id, a.package_name,"
    					+ " c.seq,c.licence_no,c.vch_license_type, c.int_recieved_bottles-coalesce(c.dispatch_36,0) as avlbottle , "
    					+ " ((c.int_planned_bottles-coalesce(c.dispatch_36,0))/c.box_size) as avlboxes ,a.duty, a.adduty,(select distinct g.rollover_bottles FROM bwfl_license.rollover_bwfl_stock g "
    					+ "where  g.package_id=c.int_pack_id and g.brand_id=c.int_brand_id and c.int_bwfl_id='"+act.getApp_id()+"') as rollover_bottles, "
    					+ " (select distinct g.rollover_boxes FROM bwfl_license.rollover_bwfl_stock g where  g.package_id=c.int_pack_id "
    					+ "and g.brand_id=c.int_brand_id and c.int_bwfl_id='"+act.getApp_id()+"') as rollover_boxes,  "
    					+ " ((c.int_planned_bottles-coalesce(c.dispatch_36,0))/c.box_size) as dispatchd_box, c.box_size "
    					+ "FROM distillery.packaging_details_19_20 a left outer join bwfl_license.rollover_bwfl_stock g on  g.package_id=a.package_id , distillery.brand_registration_19_20 b, bwfl_license.mst_receipt_19_20 c   "+ 
    			  " WHERE a.brand_id_fk=b.brand_id AND a.brand_id_fk=c.int_brand_id AND a.package_id=c.int_pack_id AND b.brand_id=c.int_brand_id  "
    			  + "AND c.int_bwfl_id='"+act.getApp_id()+"'  AND COALESCE(c.dispatch_36,0)<c.int_planned_bottles  and "
    			  		+ "c.int_recieved_bottles-coalesce(c.dispatch_36,0)>0    "     ;*/
    			 System.out.println("-----fl2d======"+query);
    			
    			con=ConnectionToDataBase.getConnection();  
 			pst=con.prepareStatement(query);
 			rs=pst.executeQuery();
        	   
             
               while(rs.next()){
            	   Rollover_Stock_for_fl2dDataTable dt=new Rollover_Stock_for_fl2dDataTable();
            	   dt.setSno(i);
            	   dt.setBrand_name(rs.getString("brand_name"));
            	   dt.setPackage_name(rs.getString("package_name"));
            	   dt.setAvl_bottle(rs.getInt("avlbottle"));
            	   dt.setSize(rs.getInt("box_size"));
            	    dt.setBrand_id(rs.getInt("int_brand_id"));
            	    dt.setPckg_id(rs.getInt("int_pack_id"));
            	  
            	    dt.setAvl_box(rs.getInt("avlboxes"));
            	    dt.setBox(rs.getInt("rollover_boxes"));
            	    dt.setSeq(rs.getInt("seq"));
            	    
            	    if(rs.getString("final_flag").equalsIgnoreCase("T")){
            	    	dt.setFinal_flag(true);
    				}else{
    					dt.setFinal_flag(false);
    				}if(rs.getString("deo_flg").equalsIgnoreCase("T")) {
   					 
    					//act.setCom_flg(true);
    					dt.setCom_flg(true);
    				} 
            	   cases++;
               list.add(dt);
   				i++;
               }
               
               
        }     
           catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
				pst.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
				}
			
		}
        
      
        return list;
}



	 
	public int maxId() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = " SELECT max(seq) as id FROM fl2d.gatepass_to_districtwholesale_fl2_fl2b_19_20 ";
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
	
	
	
	 
	public String scan(Rollover_Stock_for_fl2dAction action, int brand_id ,int pack_id) {
		

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query =" select distinct b.brand_name ,c.int_pack_id as pckg_id ,a.brand_id_fk as brand_id_fk, " +
				      " a.package_name as package_name,a.code_generate_through as code_generate_through, b.etin_unit_id " +
				      " from  distillery.packaging_details_19_20 a, distillery.brand_registration_19_20 b ,fl2d.mst_stock_receive_19_20 c where a.brand_id_fk=b.brand_id and" +
				      " a.brand_id_fk=c.int_brand_id and b.brand_id=c.int_brand_id and a.brand_id_fk='"+brand_id+"'" +
				      		" and  a.package_id =c.int_pack_id and a.package_id='"+pack_id+"'";
				
				/*"select distinct b.brand_name ,c.pckg_id as pckg_id ,a.brand_id_fk as brand_id_fk, a.package_name as package_name,a.code_generate_through as code_generate_through, b.etin_unit_id from  distillery.packaging_details_19_20 a, " +
				" distillery.brand_registration_19_20 b ,fl2d.mst_stock_receive_19_20 c where a.brand_id_fk=b.brand_id and" +
				" a.brand_id_fk=c.brand_id and b.brand_id=c.brand_id and a.brand_id_fk='"+brand_id+"' and  a.package_id =c.pckg_id and a.package_id='"+pack_id+"'";
				
				*/
				
		try {
			System.out.println("query=========="+query);
			System.out.println("brand id="+brand_id);
			System.out.println("pack_id===="+pack_id);
		
			con = ConnectionToDataBase.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
			   action.setBrand_name(rs.getString("brand_name"));
           	   action.setEtin_unit_id(rs.getString("code_generate_through"));
           	   action.setBrand_id(rs.getInt("brand_id_fk"));
           	   action.setPckg_id(rs.getInt("pckg_id"));
           	   action.setPackage_name(rs.getString("package_name"));
           	   
           	System.out.println("brand name"+action.getBrand_name());
			System.out.println("Package_name"+action.getPackage_name());
			System.out.println("Etin_unit_id"+action.getEtin_unit_id());
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
		return "";

	}
	
	
	
	
	public String getCascodeBrand(String etin)
	{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String brand_name="";
		String sql="";
		
		sql="select a.brand_name from distillery.brand_registration_19_20 a,distillery.packaging_details_19_20 b where " +
			"b.brand_id_fk=a.brand_id and code_generate_through='"+etin+"'";
		// //System.out.println("brand name casecode="+sql);
		try{
		conn=ConnectionToDataBase.getConnection();
		pstmt=conn.prepareStatement(sql);
		
	 
		//pstmt.setString(1, casecode);
		//pstmt.setString(2, etin);
		rs=pstmt.executeQuery();
		if(rs.next())
		{
			brand_name=rs.getString("brand_name");
		}else{
			brand_name="Invalid Brand";
		}
		
	}
	catch(Exception e)
	{FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
	e.printStackTrace();	
	}
	finally{
		try{
		if(rs!=null)rs.close();
		if(pstmt!=null)pstmt.close();
		if(conn!=null)conn.close();
		
		
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	return brand_name;
	}
	
	
	
	
	//===================save csv======
	

	public void saveCSV(Rollover_Stock_for_fl2dAction action) throws IOException {
		Connection con = null;
		PreparedStatement stmt = null;
		con = ConnectionToDataBase.getConnection();
		 
		try { 
		String queryList = " delete FROM fl2d.rollover_stock_for_fl2d_casecode  where  etin='"+action.getEtin_unit_id()+"' and seq='"+action.getSeq()+"' and unit_id="+action.getApp_id();
		 stmt = con.prepareStatement(queryList);
		  stmt.executeUpdate();
		  
		String query = " INSERT INTO fl2d.rollover_stock_for_fl2d_casecode(etin, casecode,unit_id,packg_id,seq)VALUES (?, ?,?, ?, ?)  ";
        
		String gatepass = "";
		int status = 0, flag = 0;
		BufferedReader bReader = new BufferedReader(new FileReader(action.getCsvFilepath()));
		
		
			

			 stmt = con.prepareStatement(query);
		
			String line = "";
			StringTokenizer st = null;
			int lineNumber = 0;
			int tokenNumber = 0;
			while ((line = bReader.readLine()) != null) {
				 
				lineNumber++;
			 
				String casecode = "";
				st = new StringTokenizer(line, " ");
				 
				while (st.hasMoreTokens()) {
					 
					String sd = st.nextToken() + "  ";      
					if (sd != null) { 									
					casecode = sd;
						 		
				         	if(casecode.trim().substring(0,12).equalsIgnoreCase(action.getEtin_unit_id().trim()))
							{
								stmt.setString(1, action.getEtin_unit_id());
								stmt.setString(2, casecode.trim());
								stmt.setInt(3, action.getApp_id());
								stmt.setInt(4, action.getPackage_id());
								stmt.setInt(5, action.getSeq());
								stmt.addBatch();
								
								}
								else{
								FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
											" Casecode-"+casecode+" does not belongs to the selected brand !" ," Casecode-"+casecode+" does not belongs to the selected brand !"));	
								
								}
							 }

					tokenNumber = 0;
				}
				
		
					
			
        
	
		
		
		
			}	
                  if (flag == 0) {
				
				status=stmt.executeBatch().length;
				
				if (status > 0) {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"File Uploaded Successfully ","File Uploaded Successfully "));
				} else {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"File Not Uploaded!!", "File Not Uploaded!!"));
				}
			 
                   }
           
			
			
		
			/*
			con = ConnectionToDataBase.getConnection();
			stmt = con.prepareStatement(query);
		
			String line = "";
			StringTokenizer st = null;
			int lineNumber = 0;
			int tokenNumber = 0;
			while ((line = bReader.readLine()) != null) {
				System.out.println("bReader.readLine()======="+line);
				lineNumber++;
			//	if(lineNumber == action.getRollerbox()){
					//System.out.println("====getRollerboxses===="+action.getRollerbox());
				System.out.println("========lineNumber============"+lineNumber);
				String casecode = "";
				st = new StringTokenizer(line, " ");
				System.out.println("StringTokenizer===="+st);
				while (st.hasMoreTokens()) {
					System.out.println("st.hasMoreTokens()==="+st.hasMoreTokens());
					String sd = st.nextToken() + "  ";
					System.out.println("st.nextToken())==="+sd);      
					if (sd != null) { 									
					casecode = sd;
						System.out.println("casecode====="+casecode);			
				         	if(casecode.trim().substring(0,12).equalsIgnoreCase(action.getEtin_unit_id().trim()))
							{
								stmt.setString(1, action.getEtin_unit_id());
								stmt.setString(2, casecode.trim());
								stmt.addBatch();
								
								}
								else{
								FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
											" Casecode-"+casecode+" does not belongs to the selected brand !" ," Casecode-"+casecode+" does not belongs to the selected brand !"));	
								
								}
							 }

					tokenNumber = 0;
				}
				
		
					
			
        
	
		
		
		
			}	
                  if (flag == 0) {
				
				status=stmt.executeBatch().length;
				
				if (status > 0) {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"File Uploaded Successfully ","File Uploaded Successfully "));
				} else {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"File Not Uploaded!!", "File Not Uploaded!!"));
				}
			 
                   }
           
			
			
		*/} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),
							e.getMessage()));
			
		
		} finally {
			try {
				if (stmt != null)
					stmt.close();

				if (con != null)
					con.close();

			} catch (Exception e) {

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),
								e.getMessage()));
				
			
			}
		}

		

	}
	
	
	

	
	/*public boolean  etin(String casecode,Rollover_Stock_for_fl2dAction act) {
		 
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		boolean s = false;
		try {
			con = ConnectionToDataBase.getConnection();
 
		
			String query = " SELECT distinct b.code_generate_through FROM fl2d.fl2d_stock_trxn_19_20 a," +
					" distillery.packaging_details_19_20 b "
					+ " WHERE b.package_id = a.int_pckg_id   and b.code_generate_through='"+casecode+"'";
			
			System.out.println("=etin=="+query);
	pstmt = con.prepareStatement(query);
			 
			rs = pstmt.executeQuery();
			if (rs.next()) {
			 
			 System.out.println("=======================---------------------=======if============================");
			}else{
				System.out.println("=======================------else---------------=======if============================");
				s=true;
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
		return s;

	}
	*/
	//=-====================================
	

	
public ArrayList displaylist1impl1(Rollover_Stock_for_fl2dAction act) {
		
        ArrayList list=new ArrayList();
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        String query="";
        int i=1,cases=1;
        
        try {
        	
        	
        	query= " select distinct etin ,casecode from  fl2d.rollover_stock_for_fl2d_casecode  where etin='"+act.getEtin_unit_id()+"' and seq='"+act.getSeq()+"' and unit_id="+act.getApp_id();
        
        	 con=ConnectionToDataBase.getConnection();  
 			pst=con.prepareStatement(query);
 			rs=pst.executeQuery();
        	   
        	  
              
               while(rs.next()){
            	   Rollover_Stock_for_fl2dDataTable dt1=new Rollover_Stock_for_fl2dDataTable();
            	   dt1.setSrno(i);
            	   dt1.setEtin(rs.getString("etin"));
            	   dt1.setCasecode(rs.getString("casecode"));
            	 
            	   cases++;
                //  System.out.println("======================"+cases);
            	   list.add(dt1);
   				i++;
               }
             
        }     
           catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
				pst.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
				}
			
		}
        
      
        return list;
}


public String saveData(Rollover_Stock_for_fl2dAction action) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 0;

	// System.out.println("====update========"+action.getUnit_id());
	try { 
		
		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
	
		String query =" INSERT INTO fl2d.rollover_stock_for_fl2d " +
			 		   " (unit_id,  brand_id, package_id, rollover_bottles,   size, rollover_boxes, etin ,seq) " +
			 		   " VALUES ('"+action.getApp_id()+"',  '"+action.getBrand_id()+"',  '"+action.getPackage_id()+"'," +
			 		   "  '"+(action.getRolloverbox()*action.getSize())+"',  '"+action.getSize()+"', '"+action.getRolloverbox()+"',  '"+action.getEtin_unit_id()+"', '"+action.getSeq()+"')";
						
 pstmt = conn.prepareStatement(query);
 
 saveStatus=pstmt.executeUpdate();
			if (saveStatus > 0) {saveStatus=0;
				  String query1 = "update fl2d.mst_stock_receive_19_20  set  final_flag='T' where int_fl2d_id='"+ action.getApp_id()+"'  " +
						  " and    int_brand_id='"+action.getBrand_id()+"'" +
						  " and int_pack_id='"+action.getPackage_id()+"' and box_size='"+action.getSize()+"' and seq='"+action.getSeq()+"'";
				  pstmt = conn.prepareStatement(query1);
				  saveStatus= pstmt.executeUpdate();
				 
			} 
			
			if (saveStatus > 0) {
				 conn.commit();action.back();
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
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),
							e.getMessage()));
			
		} finally {
		try {

			if (conn != null)
				conn.close();

			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();

		} catch (Exception e) {


			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),
							e.getMessage()));
			
		
		
		}
	}
	return "";
}
	
// reset code new impl
	
public void resetcsvcasecode(Rollover_Stock_for_fl2dAction action) {

	int saveStatus = 0;
	Connection con = null;
	PreparedStatement pstmt = null;
	String queryList = "";

	try {


	//	SELECT etin, casecode FROM bwfl.rollover_stock where  etin='"+act.getEtin_unit_id()+"'
			queryList = " delete FROM fl2d.rollover_stock_for_fl2d_casecode  where  etin='"+action.getEtin_unit_id()+"' and seq='"+action.getSeq()+"' and unit_id="+action.getApp_id();

		 
			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			saveStatus = pstmt.executeUpdate();

			if (saveStatus > 0) {
				// action.setFinalflg(true);
		
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Deleted  Successfully !!! ",
								" Deleted Successfully !!!"));

			} else {
				// System.out.println("---------- UPDATE NOT ----------");
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Not Deleted  !!! ", "Not Deleted !!!"));

			}
		
	} catch (Exception se) {
		se.printStackTrace();

	} finally {
		try {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

		} catch (Exception se) {
			se.printStackTrace();
		}

	}

}


public int displaylist1implcount(Rollover_Stock_for_fl2dAction act) {
		
        ArrayList list=new ArrayList();
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        String query="";
        int i=1,cases=1;
        
        try {
        	
        	
        	query= " select distinct count(*) from  fl2d.rollover_stock_for_fl2d_casecode where etin='"+act.getEtin_unit_id()+"' and seq='"+act.getSeq()+"' and unit_id="+act.getApp_id();
        
        	 con=ConnectionToDataBase.getConnection();  
 			pst=con.prepareStatement(query);
 			rs=pst.executeQuery();
        	   
        	  
              
               if(rs.next()){
            	 i=rs.getInt(1);  
               }
             
        }     
           catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
				pst.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
				}
			
		}
        
      
        return i;
}
public String finalize(Rollover_Stock_for_fl2dAction act,Rollover_Stock_for_fl2dDataTable dt) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 0;
	 //System.out.println("====RollOver_Stock_For_FL1_1A_20_21Action========");
	try {
            
	 
		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
		 

			String query = " update  fl2d.rollover_stock_for_fl2d set  deo_flg ='T' where " +
					       " brand_id='"+dt.getBrand_id()+"' and package_id='"+dt.getPckg_id()+"' " +
				           " and size='"+dt.getSize()+"' and seq='"+dt.getSeq()+"' and unit_id="+act.getApp_id();

					

	     	 pstmt = conn.prepareStatement(query); 
			saveStatus = pstmt.executeUpdate(); 
		 
		if (saveStatus > 0) {

			conn.commit();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							" Sent To DEO Successfully.  !!! ",
							"  Sent To DEO Successfully.  !!!"));
			 
			// action.setFinalflg(true);

		} else {

			// action.reset();
			ResourceUtil.addErrorMessage(Constants.NOT_SAVED,
					Constants.NOT_SAVED);

		}
	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR,
						" RECORD NOT SAVED !!!!!!",
						"RECORD NOT SAVED !!!!!!"));
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
			// e.printStackTrace();
		}
	}
	return "";
}
	

}
