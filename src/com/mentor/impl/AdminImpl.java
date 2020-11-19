package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.faces.model.SelectItem;

import com.mentor.action.AdminAction;
import com.mentor.connectiondb.ConnectionToDataBase;
import com.mentor.datatable.AdminDataTable;

public class AdminImpl {

	public ArrayList getShowDataTable(AdminAction action, int id) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int k = 0;
		int opening = 0;
		int reciving = 0;
		int disp = 0;
		int bal = 0;
		
/*
		String sql =

		"select sum(z.opening) as opening,sum(z.reciving) as reciving,sum(z.dispatch)as dispatch ,sum(z.total) as total ,z.brand_name ,z.pckge_id, z.fl2_id  from	"
				+ "(select distinct x.brand_id,sum(x.opening) as opening,sum(x.reciving) as reciving,sum(x.dispatch) as dispatch,x.pckge_id, "
				+ "c.brand_name,d.package_name ,(opening+reciving-dispatch) as total  "
				+ "from ( "
				+ "	select a.brand_id as brand_id,fl2_2bid as fl2_id,sum(a.recv_bottels)::int as opening,"
				+ "	0 as reciving, 0 as dispatch , a.pckg_id as pckge_id"
				+ "    from fl2d.fl2_2b_receiving_stock_trxn a ,distillery.packaging_details b "
				+ "	  where a.brand_id=b.brand_id_fk and b.package_id=a.pckg_id and brand_id='"
				+ id
				+ "' and gatepass_no='OLDSTOCK' 	"
				+ "	group by a.brand_id,a.pckg_id "
				+ "   union"
				+ "  select a.brand_id as brand_id,fl2_2bid as fl2_id ,0 as opening, sum(a.recv_bottels) as reciving,"
				+ "	0 as dispatch,a.pckg_id as pckge_id"
				+ "    from fl2d.fl2_2b_receiving_stock_trxn a ,distillery.packaging_details b "
				+ "	  where a.brand_id=b.brand_id_fk and a.pckg_id=b.package_id  and brand_id='"
				+ id
				+ "' and gatepass_no not in ('OLDSTOCK') "
				+ "	group by a.brand_id ,a.pckg_id"
				+ "	union"
				+ "	  select a.int_brand_id as brand_id, int_fl2_fl2b_id as fl2_id ,0 as opening, 0 as reciving,"
				+ "	sum(a.dispatch_bottle) as dispatch , a.int_pckg_id as pckge_id"
				+ "    from fl2d.fl2_stock_trxn_fl2_fl2b a ,distillery.packaging_details b  "
				+ "	  where a.int_brand_id=b.brand_id_fk and a.int_pckg_id=b.package_id  and int_brand_id='"
				+ id
				+ "'"
				+ "	group by a.int_brand_id,a.int_pckg_id"
				+ "    "
				+ ")x ,distillery.brand_registration c,distillery.packaging_details d, licence.fl2_2b_2d b"
				+ "	  where x.pckge_id=d.package_id and x.brand_id=d.brand_id_fk and x.brand_id=c.brand_id and b.int_app_id=x.fl2_id"
				+ "	  group by x.brand_id,x.opening,x.reciving,x.dispatch,x.pckge_id,c.brand_name,d.package_name"
				+ "	  order by c.brand_name)z"
				+ "	  group by brand_id,pckge_id ,brand_name,pckge_id";*/

		
		String sql =

		"select sum(z.opening) as opening,sum(z.reciving) as reciving,sum(z.dispatch)as dispatch ,sum(z.total) as total ,z.brand_name , z.vch_applicant_name,z.package_name from	" +
		"	(select distinct x.brand_id,sum(x.opening) as opening,sum(x.reciving) as reciving,sum(x.dispatch) as dispatch,x.pckge_id, " +
		"		c.brand_name,d.package_name ,(opening+reciving-dispatch) as total ,x.fl2_id, b.vch_applicant_name " +
		"		from ( " +
		"			select a.brand_id as brand_id,fl2_2bid as fl2_id,sum(a.total_recv_bottels)::int as opening," +
		"			0 as reciving, 0 as dispatch , a.pckg_id as pckge_id" +
		"		    from fl2d.fl2_2b_receiving_stock_trxn a ,distillery.packaging_details b " +
		"			  where a.brand_id=b.brand_id_fk and b.package_id=a.pckg_id and brand_id='"+id+"' and gatepass_no='OLDSTOCK' 	" +
		"			group by a.brand_id,a.pckg_id ,fl2_2bid" +
		"		   union" +
		"		  select a.brand_id as brand_id,fl2_2bid as fl2_id ,0 as opening, sum(a.total_recv_bottels) as reciving," +
		"			0 as dispatch,a.pckg_id as pckge_id" +
		"		    from fl2d.fl2_2b_receiving_stock_trxn a ,distillery.packaging_details b " +
		"			  where a.brand_id=b.brand_id_fk and a.pckg_id=b.package_id  and brand_id='"+id+"' and gatepass_no not in ('OLDSTOCK') " +
		"			group by a.brand_id ,a.pckg_id,fl2_2bid" +
		"			union" +
		"			  select a.int_brand_id as brand_id, int_fl2_fl2b_id::text as fl2_id ,0 as opening, 0 as reciving," +
		"			sum(a.dispatch_bottle) as dispatch , a.int_pckg_id as pckge_id" +
		"		    from fl2d.fl2_stock_trxn_fl2_fl2b a ,distillery.packaging_details b  " +
		"			  where a.int_brand_id=b.brand_id_fk and a.int_pckg_id=b.package_id  and int_brand_id='"+id+"'" +
		"			group by a.int_brand_id,a.int_pckg_id,int_fl2_fl2b_id" +
		"		    " +
		"		)x ,distillery.brand_registration c,distillery.packaging_details d, licence.fl2_2b_2d b" +
		"			  where x.pckge_id=d.package_id and x.brand_id=d.brand_id_fk and x.brand_id=c.brand_id and b.int_app_id=x.fl2_id::int" +
		"			  group by x.brand_id,x.opening,x.reciving,x.dispatch,x.pckge_id,c.brand_name,d.package_name,x.fl2_id,b.vch_applicant_name " +
		"			  order by c.brand_name)z" +
		"			  group by brand_id,pckge_id ,brand_name,package_name,vch_applicant_name ";

		
		
		 
		try {
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(sql);
			/* pstmt.setString(1, String.valueOf(action.getDistillery_id())); */
			/* pstmt.setString(2, action.getLoginType()); */
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AdminDataTable flsd = new AdminDataTable();
				flsd.setBrandName(rs.getString("brand_name"));
				flsd.setRecive_bottel(rs.getInt("opening"));
				flsd.setRecive_bottel2(rs.getInt("reciving"));
				flsd.setDis_bottel(rs.getInt("dispatch"));
				flsd.setBalance(rs.getInt("total"));
				flsd.setPack_name(rs.getString("package_name"));
				flsd.setApplicant_name(rs.getString("vch_applicant_name"));
				opening += flsd.getRecive_bottel();
				reciving +=flsd.getRecive_bottel2();
				disp +=flsd.getDis_bottel();
				bal +=flsd.getBalance();
				flsd.setSrNo(++k);
				list.add(flsd);
				 
			}
			
			action.setOpen1(opening);
			action.setReci1(reciving);
			action.setDisp1(disp);
			action.setBale1(bal);

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

	public ArrayList brand_listImpl(AdminAction action) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;

		SelectItem item = new SelectItem();
		item.setLabel("-select-");
		item.setValue("0");
		list.add(item);

		try {
			query = "SELECT brand_id, brand_name FROM distillery.brand_registration order by brand_name";
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			System.out.println("sql query========" + query);
			while (rs.next()) {

				item = new SelectItem();

				// action.setBrandid(rs.getInt("brand_id"));
				// action.setBrand_name(rs.getString("brand_name"));
				item.setValue(String.valueOf(rs.getInt("brand_id")));
				item.setLabel(rs.getString("brand_name"));
				list.add(item);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
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
	
	public ArrayList whole_listImpl(AdminAction action) {
		ArrayList list1 = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;

		SelectItem item = new SelectItem();
		item.setLabel("-select-");
		item.setValue("0");
		list1.add(item);

		try {
			query = "SELECT  distinct int_app_id, (vch_applicant_name||'-' ||vch_license_type ) as vch_applicant_name FROM licence.fl2_2b_2d b  " +
					"where vch_license_type in ('FL2','FL2B','CL2') " +
					"order by vch_applicant_name ";
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			System.out.println("sql query========" + query);
			while (rs.next()) {

				item = new SelectItem();

				// action.setBrandid(rs.getInt("brand_id"));
				// action.setBrand_name(rs.getString("brand_name"));
				item.setValue(String.valueOf(rs.getInt("int_app_id")));
				item.setLabel(rs.getString("vch_applicant_name"));
				list1.add(item);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list1;
	}
	
	public ArrayList getShowDataTable1(AdminAction action, int id) {
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int k = 0;
		int opening = 0;
		int reciving = 0;
		int disp = 0;
		int bal = 0;
		
/*
		String sql =

		"select sum(z.opening) as opening,sum(z.reciving) as reciving,sum(z.dispatch)as dispatch ,sum(z.total) as total ,z.brand_name ,z.pckge_id, z.fl2_id  from	"
				+ "(select distinct x.brand_id,sum(x.opening) as opening,sum(x.reciving) as reciving,sum(x.dispatch) as dispatch,x.pckge_id, "
				+ "c.brand_name,d.package_name ,(opening+reciving-dispatch) as total  "
				+ "from ( "
				+ "	select a.brand_id as brand_id,fl2_2bid as fl2_id,sum(a.recv_bottels)::int as opening,"
				+ "	0 as reciving, 0 as dispatch , a.pckg_id as pckge_id"
				+ "    from fl2d.fl2_2b_receiving_stock_trxn a ,distillery.packaging_details b "
				+ "	  where a.brand_id=b.brand_id_fk and b.package_id=a.pckg_id and brand_id='"
				+ id
				+ "' and gatepass_no='OLDSTOCK' 	"
				+ "	group by a.brand_id,a.pckg_id "
				+ "   union"
				+ "  select a.brand_id as brand_id,fl2_2bid as fl2_id ,0 as opening, sum(a.recv_bottels) as reciving,"
				+ "	0 as dispatch,a.pckg_id as pckge_id"
				+ "    from fl2d.fl2_2b_receiving_stock_trxn a ,distillery.packaging_details b "
				+ "	  where a.brand_id=b.brand_id_fk and a.pckg_id=b.package_id  and brand_id='"
				+ id
				+ "' and gatepass_no not in ('OLDSTOCK') "
				+ "	group by a.brand_id ,a.pckg_id"
				+ "	union"
				+ "	  select a.int_brand_id as brand_id, int_fl2_fl2b_id as fl2_id ,0 as opening, 0 as reciving,"
				+ "	sum(a.dispatch_bottle) as dispatch , a.int_pckg_id as pckge_id"
				+ "    from fl2d.fl2_stock_trxn_fl2_fl2b a ,distillery.packaging_details b  "
				+ "	  where a.int_brand_id=b.brand_id_fk and a.int_pckg_id=b.package_id  and int_brand_id='"
				+ id
				+ "'"
				+ "	group by a.int_brand_id,a.int_pckg_id"
				+ "    "
				+ ")x ,distillery.brand_registration c,distillery.packaging_details d, licence.fl2_2b_2d b"
				+ "	  where x.pckge_id=d.package_id and x.brand_id=d.brand_id_fk and x.brand_id=c.brand_id and b.int_app_id=x.fl2_id"
				+ "	  group by x.brand_id,x.opening,x.reciving,x.dispatch,x.pckge_id,c.brand_name,d.package_name"
				+ "	  order by c.brand_name)z"
				+ "	  group by brand_id,pckge_id ,brand_name,pckge_id";*/

		
		String sql =

		"select sum(z.opening) as opening,sum(z.reciving) as reciving,sum(z.dispatch)as dispatch ,sum(z.total) as total ,z.brand_name , z.vch_applicant_name,z.package_name from	" +
		"	(select distinct x.brand_id,sum(x.opening) as opening,sum(x.reciving) as reciving,sum(x.dispatch) as dispatch,x.pckge_id, " +
		"		c.brand_name,d.package_name ,(opening+reciving-dispatch) as total ,x.fl2_id, b.vch_applicant_name " +
		"		from ( " +
		"			select a.brand_id as brand_id,fl2_2bid as fl2_id,sum(a.recv_bottels)::int as opening," +
		"			0 as reciving, 0 as dispatch , a.pckg_id as pckge_id" +
		"		    from fl2d.fl2_2b_receiving_stock_trxn a ,distillery.packaging_details b,licence.fl2_2b_2d c " +
		"			  where a.fl2_2bid=c.int_app_id::text and b.package_id=a.pckg_id and fl2_2bid='"+id+"' and gatepass_no='OLDSTOCK' 	" +
		"			group by a.brand_id,a.pckg_id ,fl2_2bid" +
		"		   union" +
		"		  select a.brand_id as brand_id,fl2_2bid as fl2_id ,0 as opening, sum(a.recv_bottels) as reciving," +
		"			0 as dispatch,a.pckg_id as pckge_id" +
		"		    from fl2d.fl2_2b_receiving_stock_trxn a ,distillery.packaging_details b,licence.fl2_2b_2d c " +
		"			  where a.fl2_2bid=c.int_app_id::text and a.pckg_id=b.package_id  and fl2_2bid='"+id+"' and gatepass_no not in ('OLDSTOCK') " +
		"			group by a.brand_id ,a.pckg_id,fl2_2bid" +
		"			union" +
		"			  select a.int_brand_id as brand_id, int_fl2_fl2b_id::text as fl2_id ,0 as opening, 0 as reciving," +
		"			sum(a.dispatch_bottle) as dispatch , a.int_pckg_id as pckge_id" +
		"		    from fl2d.fl2_stock_trxn_fl2_fl2b a ,distillery.packaging_details b ,licence.fl2_2b_2d c " +
		"			  where a.int_fl2_fl2b_id=c.int_app_id and a.int_pckg_id=b.package_id  and int_fl2_fl2b_id='"+id+"'" +
		"			group by a.int_brand_id,a.int_pckg_id,int_fl2_fl2b_id" +
		"		    " +
		"		)x ,distillery.brand_registration c,distillery.packaging_details d, licence.fl2_2b_2d b" +
		"			  where x.pckge_id=d.package_id and x.brand_id=d.brand_id_fk and x.brand_id=c.brand_id and b.int_app_id=x.fl2_id::int" +
		"			  group by x.brand_id,x.opening,x.reciving,x.dispatch,x.pckge_id,c.brand_name,d.package_name,x.fl2_id,b.vch_applicant_name " +
		"			  order by c.brand_name)z" +
		"			  group by brand_id,pckge_id ,brand_name,package_name,vch_applicant_name ";

		
		
		System.out.println("sql querrrrry" + sql);
		try {
			conn = ConnectionToDataBase.getConnection();
			pstmt = conn.prepareStatement(sql);
			/* pstmt.setString(1, String.valueOf(action.getDistillery_id())); */
			/* pstmt.setString(2, action.getLoginType()); */
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AdminDataTable flsd = new AdminDataTable();
				flsd.setBrandName(rs.getString("brand_name"));
				flsd.setRecive_bottel(rs.getInt("opening"));
				flsd.setRecive_bottel2(rs.getInt("reciving"));
				flsd.setDis_bottel(rs.getInt("dispatch"));
				flsd.setBalance(rs.getInt("total"));
				flsd.setPack_name(rs.getString("package_name"));
				flsd.setApplicant_name(rs.getString("vch_applicant_name"));
				opening += flsd.getRecive_bottel();
				reciving +=flsd.getRecive_bottel2();
				disp +=flsd.getDis_bottel();
				bal +=flsd.getBalance();
				flsd.setSrNo(++k);
				list.add(flsd);

			}
			action.setOpen(opening);
			action.setReci(reciving);
			action.setDisp(disp);
			action.setBale(bal);

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

	
	
}
