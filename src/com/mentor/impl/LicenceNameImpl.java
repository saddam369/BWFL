package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.mentor.action.LicenceNameAction;
import com.mentor.connectiondb.ConnectionToDataBase;
import com.mentor.datatable.Fl2ScanningShowDataTable;
import com.mentor.datatable.LicenceNameDataTable;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class LicenceNameImpl {

	public String getDetails(LicenceNameAction ac) {
		int id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();
			String queryList = " SELECT int_app_id, vch_applicant_name, vch_firm_name ,"
					+ " vch_mobile_no,vch_license_type, vch_licence_no "
					+ " FROM licence.fl2_2b_2d WHERE loginid= ? ";

			pstmt = con.prepareStatement(queryList);
			pstmt.setString(1, ResourceUtil.getUserNameAllReq().trim());

			rs = pstmt.executeQuery();

			while (rs.next()) {

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

	public ArrayList getShowDataTable(LicenceNameAction action) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int k = 0;
		String selQr=null;
		

		/*String sql = 	" SELECT DISTINCT x.brand_id, x.pckge_id, x.brand_name, x.package_name,  " +
						" SUM(x.reciving) as reciving , SUM(x.dispatch) as dispatch " +
						" FROM " +
						" (SELECT a.created_date as dt, a.brand_id as brand_id, a.pckg_id as pckge_id, " +
						" c.brand_name, b.package_name, SUM(a.total_recv_bottels) as reciving, 0 as dispatch   " +
						" FROM fl2d.fl2_2b_receiving_stock_trxn a ,distillery.packaging_details b, distillery.brand_registration c " +
						" WHERE a.brand_id=b.brand_id_fk AND a.pckg_id=b.package_id AND a.brand_id=c.brand_id  " +
						" AND a.fl2_2bid='"+action.getDistillery_id()+"' AND a.gatepass_no NOT IN ('OLDSTOCK') " +
						" AND a.fl2_2btype='"+action.getLoginType()+"'  " +
						" AND a.created_date='"+ Utility.convertUtilDateToSQLDate(action.getDtDate())+ "' " +
						" GROUP BY a.brand_id ,a.pckg_id, a.created_date, c.brand_name, b.package_name " +
						" UNION " +
						" SELECT a.dt as dt, a.int_brand_id as brand_id, a.int_pckg_id as pckge_id, " +
						" c.brand_name, b.package_name, 0 as reciving, (SUM(a.dispatch_bottle)+a.breakage) as dispatch  " +
						" FROM fl2d.fl2_stock_trxn_fl2_fl2b a ,distillery.packaging_details b, distillery.brand_registration c " +
						" WHERE a.int_brand_id=b.brand_id_fk AND a.int_pckg_id=b.package_id AND a.int_brand_id=c.brand_id  " +
						" AND a.int_fl2_fl2b_id='"+action.getDistillery_id()+"' " +
						" AND a.dt='"+ Utility.convertUtilDateToSQLDate(action.getDtDate())+ "' " +
						" GROUP BY a.int_brand_id ,a.int_pckg_id, a.breakage, a.dt, c.brand_name, b.package_name)x " +
						" GROUP BY x.brand_name, x.package_name, x.brand_id, x.pckge_id " +
						" ORDER BY x.brand_name, x.package_name, x.brand_id, x.pckge_id";*/

		 
		selQr = 	" SELECT * FROM fl2d.getfl2_cl2_stocklist " +
					" ('"+ Utility.convertUtilDateToSQLDate(action.getDtDate())+ "', '"+action.getDistillery_id()+"', '"+action.getLoginType()+"') ";
		
		try {
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(selQr);
			
			//System.out.println("sql------------aaa----------"+selQr);
			
						
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LicenceNameDataTable flsd = new LicenceNameDataTable();
				flsd.setBrandId(rs.getInt("brand_id"));
				flsd.setPack_id(rs.getInt("pckge_id"));
				flsd.setBrandName(rs.getString("brand_name"));	
				flsd.setPcknm(rs.getString("package_name"));
				flsd.setOpening(rs.getInt("opening"));
				flsd.setRecive_bottel2(rs.getInt("reciving"));
				flsd.setDis_bottel(rs.getInt("dispatch"));
				flsd.setBalance(rs.getInt("balance"));
								
				//flsd.setRecive_bottel(this.fetchOpening(action, flsd.getBrandId(), flsd.getPack_id(), action.getDistillery_id(), action.getLoginType()));
				flsd.setSrNo(++k);
				list.add(flsd);

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

		return list;
	}

	
	// ----------------- fetch opening  -------------------------------------

		/*public int fetchOpening(LicenceNameAction action, int brand_id, int pckg_id, int dist_id, String type)
		{


			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int opening = 0;
			String query = "";
			int count = 0;

			try {

				query = " SELECT x.brand_id, x.pckge_id, SUM(x.opening+(x.reciving-x.dispatch)) as opening " +
						" FROM " +
						" (SELECT a.created_date as dt, a.brand_id as brand_id, a.pckg_id as pckge_id, " +
						" c.brand_name, b.package_name, SUM(a.total_recv_bottels)::int as opening, " +
						" 0 as reciving, 0 as dispatch   " +
						" FROM fl2d.fl2_2b_receiving_stock_trxn a, distillery.packaging_details b, distillery.brand_registration c " +
						" WHERE a.brand_id=b.brand_id_fk AND a.pckg_id=b.package_id AND a.brand_id=c.brand_id " +
						" AND a.fl2_2bid='"+dist_id+"' AND a.gatepass_no='OLDSTOCK'  " +
						" AND a.brand_id='"+brand_id+"' AND a.pckg_id='"+pckg_id+"' AND a.fl2_2btype='"+ type +"' " +
						" GROUP BY a.brand_id,a.pckg_id, a.created_date, c.brand_name, b.package_name " +
						" UNION " +
						" SELECT a.created_date as dt, a.brand_id as brand_id, a.pckg_id as pckge_id, " +
						" c.brand_name, b.package_name, 0 as opening, SUM(a.total_recv_bottels) as reciving, 0 as dispatch   " +
						" FROM fl2d.fl2_2b_receiving_stock_trxn a ,distillery.packaging_details b, distillery.brand_registration c " +
						" WHERE a.brand_id=b.brand_id_fk AND a.pckg_id=b.package_id AND a.brand_id=c.brand_id  " +
						" AND a.fl2_2bid='"+dist_id+"' AND a.gatepass_no NOT IN ('OLDSTOCK')  " +
						" AND a.brand_id='"+brand_id+"' AND a.pckg_id='"+pckg_id+"' AND a.fl2_2btype='"+ type +"' " +
						" AND a.created_date = (to_date('"+ Utility.convertUtilDateToSQLDate(action.getDtDate())+ "','YYYY-MM-DD')  - INTERVAL '1' DAY)  " +
						" GROUP BY a.brand_id, a.pckg_id, a.created_date, c.brand_name, b.package_name " +
						" UNION " +
						" SELECT a.dt as dt, a.int_brand_id as brand_id, a.int_pckg_id as pckge_id, " +
						" c.brand_name, b.package_name, 0 as opening, 0 as reciving, " +
						" (SUM(a.dispatch_bottle)+a.breakage) as dispatch     " +
						" FROM fl2d.fl2_stock_trxn_fl2_fl2b a ,distillery.packaging_details b, distillery.brand_registration c " +
						" WHERE a.int_brand_id=b.brand_id_fk AND a.int_pckg_id=b.package_id AND a.int_brand_id=c.brand_id  " +
						" AND a.int_fl2_fl2b_id='"+dist_id+"' AND a.int_brand_id='"+brand_id+"' AND a.int_pckg_id='"+pckg_id+"' " +
						" AND a.dt = (to_date('"+ Utility.convertUtilDateToSQLDate(action.getDtDate())+ "','YYYY-MM-DD')  - INTERVAL '1' DAY)  " +
						" GROUP BY a.int_brand_id ,a.int_pckg_id, a.breakage, a.dt, c.brand_name, b.package_name)x " +
						" GROUP BY x.brand_name, x.package_name, x.brand_id, x.pckge_id " +
						" ORDER BY x.brand_name, x.package_name ";

				conn = ConnectionToDataBase.getConnection();
				pstmt = conn.prepareStatement(query);
				
				System.out.println("query--------opening---------" + query);

				
				rs = pstmt.executeQuery();
				if (rs.next()) {
					
					opening = rs.getInt("opening");

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
			return opening;

		
		}*/
		
	
		
}
