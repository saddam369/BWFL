package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.model.SelectItem;

import com.mentor.action.Hbr_Advance_Duty_RegisterAction;
import com.mentor.datatable.Hbr_Advance_Duty_RegisterDataTable;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class Hbr_Advance_Duty_RegisterImpl 

{
	
	public ArrayList getHotelBarRest(Hbr_Advance_Duty_RegisterAction act) {

		ArrayList list = new ArrayList();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();

		item.setLabel("-Select-");
		item.setValue("Select");
		list.add(item);
		
		try {

			String q = " SELECT id, district , name_of_hbr FROM hotel_bar_rest.registration_for_hotels_bars_restraunt_20_21" +
				       " where district ='"+act.getDis_id() +"' and vch_verify_flag='V'  order by id ";

				
					                                                                        
					
					
			c = ConnectionToDataBase.getConnection();
			ps = c.prepareStatement(q);

			rs = ps.executeQuery();

			while (rs.next()) {

				item = new SelectItem();
               
				item.setLabel(rs.getString("id")+"-"+rs.getString("name_of_hbr").toUpperCase());
				item.setValue(String.valueOf(rs.getInt("id")));

				list.add(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {

			try {
				c.close();
			} catch (Exception e2) {

			}
		}

		return list;
	}
	
	
	
	
	
	
	public ArrayList getAdvanceDutyRegister(Hbr_Advance_Duty_RegisterAction action)
	{
		double opening=0.0;
		double balance=0.0;
		Date openingDate;
		Connection conn=null;
		PreparedStatement pstmt=null,pstmt1=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		ArrayList list=new ArrayList();
		int count =1;
		Hbr_Advance_Duty_RegisterDataTable dt=new Hbr_Advance_Duty_RegisterDataTable();
		
		String opening_sql= " SELECT int_id, hbr_id, open_dt, beer_amt, fl_amt, district_id "+
				            " FROM hotel_bar_rest.opening_amt_for_hbr_20_21 where hbr_id=?" ;
		
		String fl_sql=  "  select  challan_amount ,dispatch_duty,dt_date,des from                                                                                                              " +
			            " (select 'Gatepass ' || a.vch_gatepass_no as des,                                                                                                                     " +
			            "  case when license_category in('IMFL','WINE','IMPORTED WINE','IMPORTED FL') then (dispatch_bottle*10)                                                                " +    
			            "  when license_category in('BEER','LAB','IMPORTED BEER') then (dispatch_bottle*5) end as dispatch_duty ,                                                              " +
			            "  0 as challan_amount,dt_date                                                                                                                                         " +
			            "  from fl2d.gatepass_to_districtwholesale_fl2_fl2b_20_21 a,fl2d.fl2_stock_trxn_fl2_fl2b_20_21 b,                                                                      " +
			            "  distillery.brand_registration_20_21 c,distillery.packaging_details_20_21 d                                                                                          " +
			            "  where vch_to='BRC' and a.vch_gatepass_no=b.vch_gatepass_no and vch_to_lic_no=? and dt_date between ? and ?                                " +
			            "  and c.brand_id=d.brand_id_fk and d.package_id=b.int_pckg_id and d.brand_id_fk=b.int_brand_id                                                                        " +
			            "  and license_category in ('IMFL','WINE','IMPORTED WINE','IMPORTED FL')                                                                                               " +  
			            "  union                                                                                                                                                               " +
			            " select 'Gatepass ' || a.vch_gatepass_no as des,                                                                                                                      " +
			            " case when license_category in ('IMFL','WINE','IMPORTED WINE','IMPORTED FL')  then (dispatch_bottle*10)                                                               " +     
			            " when license_category in('BEER','LAB','IMPORTED BEER') then (dispatch_bottle*5) end as dispatch_duty ,                                                               " +
			            "  0 as challan_amount,dt_date                                                                                                                                         " +
			            " from fl2d.gatepass_to_districtwholesale_fl2d_20_21 a,fl2d.fl2d_stock_trxn_20_21 b,                                                                                   " +
			            " distillery.brand_registration_20_21 c,distillery.packaging_details_20_21 d                                                                                           " +
			            " where vch_to='HBR' and a.vch_gatepass_no=b.vch_gatepass_no and vch_to_lic_no=?                                                                                   " +
			            " and dt_date between ? and ?                                                                                                                    " +
			            " and c.brand_id=d.brand_id_fk and d.package_id=b.int_pckg_id and d.brand_id_fk=b.int_brand_id                                                                         " +
			            " and license_category in ('IMFL','WINE','IMPORTED WINE','IMPORTED FL')                                                                                                " + 
			            " union                                                                                                                                                                " +
			            " select 'Challan No - ' || a.vch_challan_id as des ,0 as duty, coalesce(sum(vch_total_amount::numeric),0) as challan_amount ,dat_created_date   from licence.mst_challan_master a,       " +    
			            " licence.challan_head_details b where a.vch_challan_id=b.vch_challan_id                                                                                               " +
			            " and a.vch_mill_type='HotelBarRest' and a.vch_trn_status='SUCCESS' and a.vch_register_flag='R' and vch_mill_name=?                                                                               " +
			            " and dat_created_date between ? and ?                                                                                                          " +
			            " and b.vch_rajaswa_head=3900105020000 and g6_head=19 group by dat_created_date,a.vch_challan_id)x  ";                                                                   
				
				
				
	//System.out.println("============fl_sql==================="+fl_sql);	
		
		
		String beer_sql= "  select  challan_amount ,dispatch_duty,dt_date,des from                                                                                                          "+
			            " (select 'Gatepass ' || a.vch_gatepass_no as des,                                                                                                                  "+
			            " case when license_category in('IMFL','WINE','IMPORTED WINE','IMPORTED FL') then (dispatch_bottle*10)                                                              "+       
			            " when license_category in('BEER','LAB','IMPORTED BEER') then (dispatch_bottle*5) end as dispatch_duty, 0 as challan_amount,dt_date                                 "+                    
			            " from fl2d.gatepass_to_districtwholesale_fl2_fl2b_20_21 a,fl2d.fl2_stock_trxn_fl2_fl2b_20_21 b,                                                                    "+
			            " distillery.brand_registration_20_21 c,distillery.packaging_details_20_21 d                                                                                        "+
			            " where vch_to='BRC' and a.vch_gatepass_no=b.vch_gatepass_no and vch_to_lic_no=? and dt_date between ? and ?                              "+
			            " and c.brand_id=d.brand_id_fk and d.package_id=b.int_pckg_id and d.brand_id_fk=b.int_brand_id                                                                      "+
			            " and license_category in('BEER','LAB','IMPORTED BEER')                                                                                                             "+
			            " union                                                                                                                                                             "+
			            " select 'Gatepass ' || a.vch_gatepass_no as des,                                                                                                                   "+
			            " case when license_category in('IMFL','WINE','IMPORTED WINE','IMPORTED FL') then (dispatch_bottle*10)                                                              "+       
			            " when license_category in('BEER','LAB','IMPORTED BEER') then (dispatch_bottle*5) end as dispatch_duty,0 as challan_amt , dt_date                                   "+                
			            " from fl2d.gatepass_to_districtwholesale_fl2d_20_21 a,fl2d.fl2d_stock_trxn_20_21 b,                                                                                "+
			            " distillery.brand_registration_20_21 c,distillery.packaging_details_20_21 d                                                                                        "+
			            " where vch_to='HBR' and a.vch_gatepass_no=b.vch_gatepass_no and vch_to_lic_no=?                                                                                "+
			            " and dt_date between ? and ?                                                                                                                 "+
			            " and c.brand_id=d.brand_id_fk and d.package_id=b.int_pckg_id and d.brand_id_fk=b.int_brand_id                                                                      "+
			            " and license_category in('BEER','LAB','IMPORTED BEER')                                                                                                             "+                                                                                                                              
			            " union                                                                                                                                                             "+
			            " select 'Challan No - ' || a.vch_challan_id as des ,0 as duty, coalesce(sum(vch_total_amount::numeric),0) as challan_amount ,dat_created_date   from licence.mst_challan_master a,    "+        
			            " licence.challan_head_details b where a.vch_challan_id=b.vch_challan_id                                                                                            "+
			            " and a.vch_mill_type='HotelBarRest' and a.vch_trn_status='SUCCESS' and a.vch_register_flag='R' and vch_mill_name=?                                                                            "+
			            " and dat_created_date between ? and ?                                                                                                        "+
			            " and b.vch_rajaswa_head=3900103020000 and g6_head=14 group by dat_created_date,a.vch_challan_id)x " ;                                                               
				                                                                                                                                                                            
				
				
		//System.out.println("====beer_sql====="+beer_sql);
		
		
		
		try{
			
			conn=ConnectionToDataBase.getConnection();
		pstmt=	conn.prepareStatement(opening_sql);
		pstmt.setInt(1, Integer.parseInt(action.getHbr_id()));
		rs=pstmt.executeQuery();
		//System.out.println("query execute opening");
		
		if(rs.next())
		{
			if(action.getRadio().equalsIgnoreCase("F"))
			{
				opening=rs.getDouble("fl_amt");
				//System.out.println("openingg "+opening);
			}else{
				opening=rs.getDouble("beer_amt");
				//System.out.println("openingg "+opening);
			}
			
			openingDate=Utility.convertSqlDateToUtilDate(rs.getDate("open_dt"));
			
			dt.setDate(openingDate);
			dt.setBalance(opening);
			dt.setChallan_amount(0.0);
			dt.setDiscription("Current Year Opening");
			dt.setDispatch_duty(0.0);
			dt.setSrNo(count);
			list.add(dt);
			count++;
			
		}
		else
		{
                if(action.getRadio().equalsIgnoreCase("F"))
			{
				opening=0.0;
				//System.out.println("openingg "+opening);
			}else{
				opening=0.0;
				//System.out.println("openingg "+opening);
			}
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            
            openingDate = formatter.parse("01-04-2020");
            
		    dt.setDate(openingDate);
			dt.setBalance(0.0);
			dt.setChallan_amount(0.0);
			dt.setDiscription("Current Year Opening");
			dt.setDispatch_duty(0.0);
			dt.setSrNo(count);
			list.add(dt);
			count++;
		}
			
		if(action.getRadio().equalsIgnoreCase("F"))
		{
		pstmt=conn.prepareStatement(fl_sql);
		
		pstmt.setString(1,action.getHbr_id());
		pstmt.setDate(2, Utility.convertUtilDateToSQLDate(action.getFromdate()));
		pstmt.setDate(3, Utility.convertUtilDateToSQLDate(action.getTodate()));
		
		
		pstmt.setString(4,action.getHbr_id());
		pstmt.setDate(5, Utility.convertUtilDateToSQLDate(action.getFromdate()));
		pstmt.setDate(6, Utility.convertUtilDateToSQLDate(action.getTodate()));
		
		
		
		pstmt.setString(7,action.getHbr_id());
		pstmt.setDate(8, Utility.convertUtilDateToSQLDate(action.getFromdate()));
		pstmt.setDate(9, Utility.convertUtilDateToSQLDate(action.getTodate()));
		
		rs1=pstmt.executeQuery();
		
		
		}
		else
		{
			pstmt1=conn.prepareStatement(beer_sql);
		
			pstmt1.setString(1,action.getHbr_id());
			pstmt1.setDate(2, Utility.convertUtilDateToSQLDate(action.getFromdate()));
			pstmt1.setDate(3, Utility.convertUtilDateToSQLDate(action.getTodate()));
		
		
			pstmt1.setString(4,action.getHbr_id());
			pstmt1.setDate(5, Utility.convertUtilDateToSQLDate(action.getFromdate()));
			pstmt1.setDate(6, Utility.convertUtilDateToSQLDate(action.getTodate()));
		
		
		
			pstmt1.setString(7,action.getHbr_id());
			pstmt1.setDate(8, Utility.convertUtilDateToSQLDate(action.getFromdate()));
			pstmt1.setDate(9, Utility.convertUtilDateToSQLDate(action.getTodate()));
		
		rs1=pstmt1.executeQuery();
		
		
		}
		
		while(rs1.next())
		{
			
			
			dt=new Hbr_Advance_Duty_RegisterDataTable();
			if(count==2)
			{
			balance=(opening+rs1.getDouble("challan_amount"))-rs1.getDouble("dispatch_duty");
			}
			else
			{
				balance=(balance+rs1.getDouble("challan_amount"))-rs1.getDouble("dispatch_duty");
			}
			
			//System.out.println("come in"+balance);
			
			dt.setSrNo(count);
			dt.setBalance(balance);
			dt.setChallan_amount(rs1.getDouble("challan_amount"));
			dt.setDiscription(rs1.getString("des"));
			dt.setDispatch_duty(rs1.getDouble("dispatch_duty"));
			dt.setDate(Utility.convertUtilDateToSQLDate(rs1.getDate("dt_date")));
			list.add(dt);
			count++;
			
		
		}
		
		
		
		
		
			
		}catch(Exception e)
		{
		e.printStackTrace();	
		}finally{
			
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(pstmt1!=null)pstmt1.close();
				if(conn!=null)conn.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return list;
		
	}
	
//====================
	
	public String getDetails(Hbr_Advance_Duty_RegisterAction ac) {

		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null, ps2 = null;
		ResultSet rs = null, rs2 = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();
			
			String queryList =  " select districtid, description FROM public.district where deo = '" + ResourceUtil.getUserNameAllReq() + "'";

			pstmt = con.prepareStatement(queryList);

		    //System.err.println("get details query----------------"+queryList);
		    
			rs = pstmt.executeQuery();

			while (rs.next()) {
			  
				ac.setDis_id(rs.getInt("districtid"));

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
	
}
