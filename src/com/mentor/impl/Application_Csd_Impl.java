package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.jboss.security.AppPolicy;

import com.arjuna.ats.arjuna.gandiva.Resource;
import com.mentor.action.Application_Csd_Action;
import com.mentor.action.ImportPermitFL2DAction;

import com.mentor.datatable.Application_Csd_dt;
import com.mentor.datatable.ImportPermitFL2DDT;

import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;


public class Application_Csd_Impl {
	
	public void getDetails(Application_Csd_Action act){


		int id = 0;
		act.setA("N");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s = "";
		try {
			con = ConnectionToDataBase.getConnection();

			
  String selQr ="SELECT int_app_id, vch_applicant_name, " +
  		        " vch_firm_name, vch_mobile_no, vch_email, vch_pan, vch_license_type, vch_licence_no, vch_flag, core_district_id " +
  		        "  FROM licence.fl2_2b_2d_fl2a_csd_20_21 where vch_mobile_no='"+ ResourceUtil.getUserNameAllReq().trim() + "'";
		  
		  
		  /*" select vch_licence_no  as vch_license_number,'99' as vch_license_type,vch_mobile_no as mobile_number,0 as stateid,  " +
				" null as vch_state_name,vch_firm_name, vch_core_address as vch_firm_add,core_district_id as vch_firm_district_id,description, " +
				" null as vch_indus_name,null as vch_reg_office_add,int_app_id as int_id, COALESCE (etin_unit_id,0) as unit_id, " +
				" null as  license_issue_date,null as approval_dt,null as application_date   " +
				" from licence.fl2_2b_2d_19_20 a, public.district b where a.core_district_id=b.districtid   " +
				" and vch_license_type='FL2D' and vch_mobile_no='"+ ResourceUtil.getUserNameAllReq().trim() + "' " ;
		
		*/
		
		//System.out.println("login dtl="+selQr);
			pstmt = con.prepareStatement(selQr);

			rs = pstmt.executeQuery();
			
			 
			while (rs.next()) {
				act.setInt_id(rs.getInt("int_app_id"));
				act.setDisid(rs.getInt("core_district_id"));
				act.setLic_no(rs.getString("vch_licence_no"));
				act.setName(rs.getString("vch_applicant_name"));
				act.setAdd(rs.getString("vch_firm_name"));
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
	}
	public ArrayList getdisUnitList() {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);

	 String sql="select distinct a.int_app_id_f ,c.vch_state_name, a.vch_undertaking_name ,a.vch_reg_add from  " +
	 		    " public.dis_mst_pd1_pd2_lic a, public.state_ind c "+
				" ,distillery.brand_registration_20_21 b where a.int_app_id_f=b.distillery_id and liquor_type not in ('CL') and for_csd_civil='CSD' "+
				" and c.int_state_id=a.vch_unit_state::int "+
				" order by vch_undertaking_name";
 		try {
 			//System.out.println("dis========================"+sql);
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("vch_undertaking_name")+","+rs.getString("vch_state_name"));
				item.setValue(rs.getString("int_app_id_f"));
				list.add(item);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
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

	
	

	public ArrayList getbupUnitList() {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);

	 String sql=" select distinct  c.vch_state_name,a.vch_app_id_f,a.brewery_nm  from  public.bre_mst_b1_lic a, distillery.brand_registration_20_21 b , "+
				" public.state_ind c "+
				" where a.vch_app_id_f=b.brewery_id and a.int_reg_state_id=c.int_state_id and for_csd_civil='CSD' order by brewery_nm asc ";
 		try {
 		//	System.out.println("bre================================="+sql);
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("brewery_nm")+","+rs.getString("vch_state_name"));
				item.setValue(rs.getString("vch_app_id_f"));
				list.add(item);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
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

	

	public ArrayList getoupUnitList() {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);

	 String sql=" select a.int_app_id_f ,a.vch_indus_name, b.vch_state_name FROM public.other_unit_registration_20_21 a, public.state_ind b "+
				"	 where a.vch_indus_type like '%CSD%' and a.vch_reg_office_state::int=b.int_state_id order by "+
				"	a.vch_indus_name ";
 		try {
 			//System.out.println("outher======================="+sql);
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("vch_indus_name")+","+rs.getString("vch_state_name"));
				item.setValue(rs.getString("int_app_id_f"));
				list.add(item);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
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
	
	public ArrayList getIUList(){
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);

	 String sql="select a.int_app_id_f ,a.vch_indus_name, b.vch_state_name from other_unit_registration_20_21 a, public.state_ind b where "+
				"	vch_indus_type='IU' and int_app_id_f in (select distinct int_fl2d_id from distillery.brand_registration_20_21 "+
				"	where for_csd_civil='CSD') and a.vch_reg_office_state::int=b.int_state_id order by "+
				"	a.vch_indus_name ";
 		try {
 			//System.out.println("outher======================="+sql);
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("vch_indus_name")+","+rs.getString("vch_state_name"));
				item.setValue(rs.getString("int_app_id_f"));
				list.add(item);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
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

	public String getdist(Application_Csd_Action act , int value) {
	   //int a=Integer.parseInt(value);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s = "";
		try {
			
			con = ConnectionToDataBase.getConnection();
			String query="select distinct  a.vch_undertaking_name ,a.vch_reg_add from   public.dis_mst_pd1_pd2_lic a, "+
			" distillery.brand_registration_20_21 b where a.int_app_id_f=b.distillery_id "+
			" and a.int_app_id_f='"+value+"' and liquor_type not in ('CL') and for_csd_civil='CSD'";
			
			
	//		System.out.println("id========================"+a);
			//System.out.println("query dis======================="+query);
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			 	while (rs.next()) {
				
			 		//act.setName(rs.getString("vch_undertaking_name"));
			 	//	act.setAdd(rs.getString("vch_reg_add"));
				    
			}

		} catch ( Exception se) {
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
		return  "";
		
	}
	
	
	
public String getbup(Application_Csd_Action act ,int value) {
	
		
		

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s = "";
		try {
			
			con = ConnectionToDataBase.getConnection();
			String query=" select a.vch_app_id_f ,a.brewery_nm, a.vch_upkrm_address FROM public.bre_mst_b1_lic a  " +
					" where vch_app_id_f='"+value+"' ";
			
			//System.out.println("id========================"+act.getUnit_id());
			//System.out.println("query b======================="+query);
			
			
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			 	while (rs.next()) {
			 		
			 		act.setName(rs.getString("brewery_nm"));
			 		act.setAdd(rs.getString("vch_upkrm_address"));
				    
			}

		} catch ( Exception se) {
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
		return  "";
		
	}
	
public ArrayList getBrandName() {

	FacesContext facesContext = FacesContext.getCurrentInstance();
	Application_Csd_Action act = (Application_Csd_Action) facesContext.getApplication()
			.createValueBinding("#{application_Csd_Action}")
			.getValue(facesContext);

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
		if(act.getUnit_id().length()>0){
		if(act.getRadio().equalsIgnoreCase("D")){
			sql = "	SELECT brand_id, brand_name FROM distillery.brand_registration_20_21 a,public.dis_mst_pd1_pd2_lic b "+
              " where a.distillery_id=b.int_app_id_f and a.distillery_id="+act.getUnit_id()+" and liquor_type not in ('CL') and " +
              " for_csd_civil='CSD' and domain is null order by brand_name ";
		}
		else if(act.getRadio().equalsIgnoreCase("B")){
			sql="select brand_id, brand_name from  public.bre_mst_b1_lic a,  "+
			    " distillery.brand_registration_20_21 b where a.vch_app_id_f=b.brewery_id "+
			    " and for_csd_civil='CSD' and b.brewery_id="+act.getUnit_id()+" and domain is null order by brand_name asc";
		     }
		
		else if(act.getRadio().equalsIgnoreCase("O")){
			sql="select brand_id, brand_name FROM public.other_unit_registration_20_21 a,distillery.brand_registration_20_21 b "+
			    "where a.int_app_id_f=b.int_fl2a_id and a.int_app_id_f="+act.getUnit_id()+" and a.vch_indus_type like '%CSD%' and domain is null order by brand_name";
			
		}else if(act.getRadio().equalsIgnoreCase("IU")){
			
			sql="	SELECT brand_id, brand_name FROM distillery.brand_registration_20_21 "
					+ " where vch_license_type='IU'  and int_fl2d_id>0 and int_fl2d_id='"+act.getUnit_id()+"'  " +
					" order by brand_name ";
		}
		}
		
		System.out.println(act.getUnit_id()+"-Brand list="+sql);
		con = ConnectionToDataBase.getConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			item = new SelectItem();
			item.setLabel(rs.getString("brand_name"));
			item.setValue(rs.getString("brand_id"));
			list.add(item);
		}
	} catch (Exception e) {
		e.printStackTrace();
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
		
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

		" SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id "
				+ "	from distillery.brand_registration_20_21 a , "
				+ "	distillery.packaging_details_20_21 b "
				+ "	where a.brand_id=b.brand_id_fk  " +
				 "	and brand_id =?  and b.package_id=?";
       // System.out.println(brand_Id+","+packging_Id+", "+"qty="+queryList);
		con = ConnectionToDataBase.getConnection();

		pstmt = con.prepareStatement(queryList);

		pstmt.setInt(1, Integer.parseInt(brand_Id));
		pstmt.setInt(2, Integer.parseInt(packging_Id));

		rs = pstmt.executeQuery();

		while (rs.next()) {

			qty = rs.getString("quantity");

		}

		// pstmt.executeUpdate();
	} catch (Exception se) {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));
		
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


public double getPermitFee(String brand_Id, String packging_Id) {

	double permitFee = 0;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {

		String queryList = 	" SELECT distinct  a.brand_id, a.brand_name, a.vch_license_type, b.package_name, b.package_id, b.permit,b.cesh " +
							" FROM distillery.brand_registration_20_21 a , distillery.packaging_details_20_21 b  " +
							" WHERE a.brand_id=b.brand_id_fk  " +
							" AND a.brand_id =?  AND b.package_id=? ";
		System.out.println("import fee="+queryList);
		con = ConnectionToDataBase.getConnection();

		pstmt = con.prepareStatement(queryList);

		pstmt.setInt(1, Integer.parseInt(brand_Id));
		pstmt.setInt(2, Integer.parseInt(packging_Id));
		
		
		System.out.println("brand_Id------------"+brand_Id);
		
		System.out.println("packging_Id------------"+packging_Id);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			 permitFee = rs.getDouble("permit");
             }

	} catch ( Exception se) {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));	
		
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
	return permitFee;

}
public double getmrp(String brand_Id, String packging_Id){
	double mrp = 0.0;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {

		String queryList =

		          " SELECT b.mrp "
				+ "	from distillery.brand_registration_20_21 a , "
				+ "	distillery.packaging_details_20_21 b "
				+ "	where a.brand_id=b.brand_id_fk  " +
				  "	and brand_id ='"+brand_Id+"'  and b.package_id='"+packging_Id+"'";
       // System.out.println("mrp="+queryList);
		con = ConnectionToDataBase.getConnection();

		pstmt = con.prepareStatement(queryList);

		
		rs = pstmt.executeQuery();

		while (rs.next()) {

			mrp = rs.getDouble("mrp");

		}

		// pstmt.executeUpdate();
	} catch (Exception se) {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));
		
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
	return mrp;
	
	
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

	String SQl = "SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id "
			+ "	from distillery.brand_registration_20_21 a , "
			+ "	distillery.packaging_details_20_21 b "
			+ "	where a.brand_id=b.brand_id_fk  " +
		 	"	and brand_id =? ";
	
	try {
		con = ConnectionToDataBase.getConnection();
		ps = con.prepareStatement(SQl);
	
		ps.setInt(1, Integer.parseInt(brand_id));
		rs = ps.executeQuery();
		//System.out.println(brand_id+"pckg="+SQl);
		while (rs.next()) {
			item = new SelectItem();
			item.setLabel(rs.getString("package_name"));
			item.setValue(rs.getString("package_id"));
			list.add(item);
		}
	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
		
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
public String getoup(Application_Csd_Action act ,int value) {
	
	
	

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String s = "";
	try {
		
		con = ConnectionToDataBase.getConnection();
		String query=" select int_app_id_f ,vch_indus_name,vch_reg_office_add FROM public.other_unit_registration_20_21 where vch_indus_type " +
				"  like '%CSD%' and int_app_id_f='"+value+"'";
		
		//System.out.println("id========================"+act.getUnit_id());
		//System.out.println("query o======================="+query);
		
		pstmt = con.prepareStatement(query);
		rs = pstmt.executeQuery();
		 	while (rs.next()) {
			
		 		act.setName(rs.getString("vch_indus_name"));
		 		act.setAdd(rs.getString("vch_indus_name"));
			    
		}

	} catch ( Exception se) {
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
	return  "";
	
} 


public ArrayList hislistImpl(Application_Csd_Action act) {

	ArrayList list = new ArrayList();
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String selQr = null;
	//int i = 1, cases = 0;
 
	try {
	 selQr ="select consignor_nm_adrs,COALESCE(print_permit_pdf,'NUL')print_permit_pdf,COALESCE(digital_sign_pdf_name,'NUL')digital_sign_pdf_name,app_id,import_fee_challan_no,spcl_fee_challan_no,id,district_id,bwfl_id,unit_id,bwfl_type,import_fee,duty+add_duty as duty,special_fee,cr_date," +
	 		 " case when bottlng_type='M' then 'Mapped' else 'Unmapped' end as bottlng_type" +
	 		",COALESCE(vch_status,'Pending')as vch_status, vch_approved,valid_upto from bwfl_license.import_permit_20_21 where district_id='"
			 +act.getVch_firm_district_id()+"' and bwfl_id='"+act.getUnit_id()+"' ";
	
	 //System.out.println("query--- "+selQr);
	 conn = ConnectionToDataBase.getConnection();
		ps = conn.prepareStatement(selQr);
 
		rs = ps.executeQuery();
		while (rs.next()) {
		   Application_Csd_dt dt = new Application_Csd_dt();
		
			dt.setApp_id(rs.getInt("app_id"));
			dt.setDt_app_id(rs.getInt("id"));
			dt.setDt_import_fee(rs.getDouble("import_fee"));
			dt.setDt_duty(rs.getDouble("duty"));
			dt.setDt_special_fee(rs.getDouble("special_fee"));
		
			
			dt.setDt_district_id(rs.getInt("district_id"));
			
			list.add(dt);
			//i++;
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

public boolean save(Application_Csd_Action act){


	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 0;
	int max_id = maxId(act.getVch_firm_district_id());
	int app_id=this.app_id();
	String query = ""; 
	double import_fee=0;
	double duty=0;
	double add_duty=0;
	double special_fee=0;
	double corona_fee=0;
	
	boolean flg=false;
	 try {

		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
		 
		
			
		query="INSERT INTO fl2d.application_csd_permit_detail_20_21(fk_id, district_id, brand_id, pckg_id, etin, no_of_cases, no_of_bottle_per_case,  pland_no_of_bottles," +
				" import_fee, duty, add_duty, special_fee, cr_date, login_type, app_id, size, scanning_fee, corona_fee) VALUES " +
				"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				/*"INSERT INTO bwfl_license.import_permit_20_21_dtl(fk_id, district_id, brand_id, pckg_id, etin, no_of_cases, no_of_bottle_per_case," +
				" pland_no_of_bottles, import_fee, duty, add_duty, special_fee, cr_date,login_type,app_id) VALUES (? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";*/
	
		
		if(act.getDisplaylist().size()>0){
			for(int i=0;i<act.getDisplaylist().size();i++){
				Application_Csd_dt dt = (Application_Csd_dt) act.getDisplaylist().get(i);
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1,  max_id);
				pstmt.setInt(2, act.getDisid());
				pstmt.setInt(3, Integer.parseInt(dt.getBrand_id()));
				pstmt.setInt(4, Integer.parseInt(dt.getPckg_id()));
				pstmt.setString(5, dt.getEtin());
				pstmt.setInt(6, dt.getNo_of_cases());
				pstmt.setLong(7, dt.getNo_of_bottle_per_case());
				pstmt.setInt(8, dt.getPland_no_of_bottles());
				pstmt.setDouble(9, dt.getCal_importFee());
				pstmt.setDouble(10, dt.getCal_duty());
				pstmt.setDouble(11, dt.getCalculated_add_duty());
				pstmt.setDouble(12, dt.getSpecial_fee());
				pstmt.setDate(13,Utility.convertUtilDateToSQLDate(new Date())); 
				pstmt.setString(14, "FL2A");
				pstmt.setInt(15, app_id);
				pstmt.setInt(16, Integer.parseInt(dt.getQuantity()));
				pstmt.setDouble(17, dt.getCal_scanfee());
				pstmt.setDouble(18, dt.getCal_coronafee());
				
				import_fee=import_fee+ dt.getCal_importFee();
				duty=duty+ dt.getCalculated_duty();       
				add_duty=add_duty+ dt.getCalculated_add_duty(); 
				special_fee=special_fee+dt.getSpecial_fee(); 
				corona_fee=corona_fee+dt.getCal_coronafee();
				saveStatus = pstmt.executeUpdate();
			}
			
		}
		
		
		if(saveStatus>0){
			
		
					saveStatus = 0;
					
					
					
						
					query = " INSERT INTO fl2d.application_csd_permit_mst_20_21("+
							" id, district_id, bwfl_id, unit_id, import_fee, duty, add_duty, special_fee,  cr_date, " +  
							" login_type, import_fee_challan_no, spcl_fee_challan_no,app_id ,unit_type, deo_user, lic_no, bottlng_type, route_road_radio, route_detail, " +
							" tot_scanning_fee, consignor_nm_adrs, total_corona_fee" +
							")" +
								"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
														
							/* " INSERT INTO bwfl_license.import_permit_20_21( id, district_id, bwfl_id, unit_id, bwfl_type, import_fee," +
							" duty, add_duty, special_fee, cr_date,bottlng_type,route_road_radio,route_detail,lic_no, " +
							" login_type,spcl_fee_challan_no,import_fee_challan_no,app_id,consignor_nm_adrs) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,? ,?,?,?,?,?,?)";*/
				
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, max_id);
					pstmt.setInt(2, act.getDisid());
					pstmt.setInt(3, act.getInt_id());
					pstmt.setInt(4, Integer.parseInt(act.getUnit_id()));
					pstmt.setDouble(5, Double.parseDouble(act.getTotal_import_fees()));
					pstmt.setDouble(6, Double.parseDouble(act.getTotal_duty()));
					pstmt.setDouble(7, 0.0);
					pstmt.setDouble(8, Double.parseDouble(act.getTotal_special_fee()));
					pstmt.setDate(9,Utility.convertUtilDateToSQLDate(new Date()));
					pstmt.setString(10, "FL2A");
				    pstmt.setString(11, null);
					pstmt.setString(12, null);
					pstmt.setInt(13, app_id);
					pstmt.setString(14, act.getRadio());
					pstmt.setString(15, this.getDEO(act));
					pstmt.setString(16, act.getLic_no());
					pstmt.setString(17, "M");
					pstmt.setString(18, act.getRoute_road_radio());
					pstmt.setString(19, act.getRoute_detail());
					pstmt.setDouble(20, Double.parseDouble(act.getTotal_scanning_fee()));
					pstmt.setString(21, act.getName());
					pstmt.setDouble(22, corona_fee);
					saveStatus = pstmt.executeUpdate();
						
			}
		
		if (saveStatus > 0) {
			conn.setAutoCommit(true);
			flg=true;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("SAVED SUCCESSFULLY !", "SAVED SUCCESSFULLY !"));
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("YOUR APPLICATION ID IS :"+ app_id,"YOUR APPLICATION ID IS :"+ app_id));
			act.reset();

		} else {
			// action.reset();
			ResourceUtil.addErrorMessage(Constants.NOT_SAVED,Constants.NOT_SAVED);

		}

	}

	catch (SQLException e) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(e.getMessage(), e.getMessage()));
		e.printStackTrace();
	}catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(e.getMessage(), e.getMessage()));
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
return flg;

}

public String getEtinNmbr(String brand_Id, String packging_Id, Application_Csd_dt dt) {

	String etin = "";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String queryList="";
	try {
    
		 queryList = " SELECT a.brand_id, a.brand_name ,b.quantity, b.package_name ,b.package_id, b.code_generate_through,b.duty,b.adduty "
				+", case when license_category in('IMFL','IMPORTED FL','WINE','IMPORTED WINE') then 1.5 else 1.0 end as spcl_fee "
				+ " FROM distillery.brand_registration_20_21 a , distillery.packaging_details_20_21 b "
				+ " WHERE a.brand_id=b.brand_id_fk  "
				+ " AND brand_id =?  AND b.package_id=?";
		 
		 System.out.println("Etin impl==="+queryList);
              
System.out.println(brand_Id+" | "+packging_Id+" | "+queryList);
		con = ConnectionToDataBase.getConnection();

		pstmt = con.prepareStatement(queryList);

		pstmt.setInt(1, Integer.parseInt(brand_Id));
		pstmt.setInt(2, Integer.parseInt(packging_Id));

		rs = pstmt.executeQuery();

		if (rs.next()) {

			etin = rs.getString("code_generate_through");
			
			
		}

	} catch ( Exception se) {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));
		
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
	return etin;

}


public double getSpcl(String brand_Id, String packging_Id, Application_Csd_dt dt) {

	String etin = "";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String queryList="";
	double spcl_fee=0.0;
	try {
   // if(dt.getRadio().equalsIgnoreCase("O")){
		 queryList =  " SELECT a.brand_id, a.brand_name ,b.special_fee, b.quantity, b.package_name ,b.package_id, b.code_generate_through,b.duty,b.adduty "
					+ " FROM distillery.brand_registration_20_21 a , distillery.packaging_details_20_21 b "
					+ " WHERE a.brand_id=b.brand_id_fk  "
					+ " AND brand_id ="+Integer.parseInt(brand_Id)+"  AND b.package_id="+Integer.parseInt(packging_Id)+"";
		 
		 System.out.println("O impl==="+queryList);
            /* }
    
          else if (dt.getRadio().equalsIgnoreCase("IU")){
		
		 queryList = " SELECT a.brand_id, a.brand_name ,b.special_fee, b.quantity, b.package_name ,b.package_id, b.code_generate_through,b.duty,b.adduty "
				+", case when license_category in('IMFL','IMPORTED FL','WINE','IMPORTED WINE') then 15.0 else 10.0 end as spcl_fee "
				+ " FROM distillery.brand_registration_20_21 a , distillery.packaging_details_20_21 b "
				+ " WHERE a.brand_id=b.brand_id_fk "
				+ " AND brand_id =?  AND b.package_id=?";
		 System.out.println("IU impl==="+queryList);
              }*/
        System.out.println(brand_Id+" | "+packging_Id+" | "+queryList);
		con = ConnectionToDataBase.getConnection();

		pstmt = con.prepareStatement(queryList);

		//pstmt.setInt(1, Integer.parseInt(brand_Id));
		//pstmt.setInt(2, Integer.parseInt(packging_Id));

		rs = pstmt.executeQuery();

		if (rs.next()) {

			
			spcl_fee=rs.getDouble("special_fee");
			System.out.println("setSpcl_fe=="+rs.getDouble("special_fee"));
			
		}

	} catch ( Exception se) {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));
		
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
	return spcl_fee;

}
public int maxId(int district_id) {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String query = " SELECT coalesce(max(fk_id),0) as id FROM fl2d.application_csd_permit_detail_20_21  ";
	int maxid = 0;
	try {
		con = ConnectionToDataBase.getConnection();
		pstmt = con.prepareStatement(query);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			maxid = rs.getInt("id");
		}
	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
		
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
public int app_id() {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String query = " SELECT max(app_id) as app_id FROM fl2d.application_csd_permit_mst_20_21";
	int maxid = 0;
	try {
		con = ConnectionToDataBase.getConnection();
		pstmt = con.prepareStatement(query);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			maxid = rs.getInt("app_id");
		}
	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e
						.getMessage(), e.getMessage()));

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
public boolean getChallanBalance(Application_Csd_Action act) {
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	boolean flg=true;
	String sql="";
	sql = " select COALESCE(amount,0.0)import_amount " +
		" FROM bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no='"+act.getImport_fee_challan_no()+"' " 
		+ " and unit_id="+act.getInt_id()+" AND unit_type='FL2D'";
//System.out.println("getChallanImportFee--"+sql);
	try {
		con = ConnectionToDataBase.getConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			 if(Double.parseDouble(act.getTotal_import_fees())>rs.getDouble("import_amount")){
				 flg=false; 
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Import Fee for this permit is greater then your selected Challan"));
			 }else {
				 System.out.println(Double.parseDouble(act.getTotal_import_fees())+"-import fee is "+rs.getDouble("import_amount"));
			 }
		}
		ps=null;
		rs=null;
		sql = " select COALESCE(amount,0.0)spcl_amount " +
				" FROM bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no='"+act.getSpcl_fee_challan_no()+"' " 
				+ " and unit_id="+act.getInt_id()+" AND unit_type='FL2D'";
		//System.out.println("ChallanSpcl1="+sql);
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if (rs.next()) {
			 if(Double.parseDouble(act.getTotal_special_fee())>rs.getDouble("spcl_amount")){
				 flg=false; 
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Special Fee for this permit is greater then your selected Challan"));
			 }else {
				// System.out.println(Double.parseDouble(act.getTotal_special_fee())+"-import fee is "+rs.getDouble("spcl_amount"));
			 }
		}
		
	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e
						.getMessage(), e.getMessage()));
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
	return flg;
}




public String getmethod(Application_Csd_Action act ,Application_Csd_dt dt) {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String s = "";
	try {
		
		con = ConnectionToDataBase.getConnection();
		String query="SELECT package_name,permit, package_type, mrp, edp, adduty, retmargin, brand_id_fk, package_id, sno, box_id," +
				" quantity, duty FROM distillery.packaging_details_20_21 where brand_id_fk='"+dt.getBrand_id()+"'";
				
			
		//.out.println("id========================"+act.getBrand_id());
		//System.out.println("duty query======================="+query);
		pstmt = con.prepareStatement(query);
		rs = pstmt.executeQuery();
		
		 	while (rs.next()) {
			

			    act.setImportFee(rs.getDouble("duty"));
			    
		}

	} catch ( Exception se) {
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

  public String getDEO(Application_Csd_Action act){
	  String deo="";
	  
	  Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String s = "";
		try {
			
			con = ConnectionToDataBase.getConnection();
			String query="SELECT deo FROM public.district where districtid="+act.getDisid()+"";
					

			//System.out.println("query deo======================="+query);
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			 	if (rs.next()) {
				

				    deo=rs.getString("deo");
			}

		} catch ( Exception se) {
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
	  
	  return deo;
  }
  
  public double getDuty(String brand_Id, String packging_Id){


		double duty = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList = 	" SELECT distinct a.brand_id, a.brand_name, a.vch_license_type,b.duty, b.adduty, b.package_name, b.package_id, b.permit " +
								" FROM distillery.brand_registration_20_21 a , distillery.packaging_details_20_21 b  " +
								" WHERE a.brand_id=b.brand_id_fk  " +
								" AND a.brand_id =?  AND b.package_id=? ";
			//System.out.println("import fee="+queryList);
			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, Integer.parseInt(brand_Id));
			pstmt.setInt(2, Integer.parseInt(packging_Id));
			
			
			//System.out.println("brand_Id------------"+brand_Id);
			
			//System.out.println("packging_Id------------"+packging_Id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				duty = rs.getDouble("duty");

			}

		} catch ( Exception se) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));	
			
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
		return duty;


  }
  
  public ArrayList getImportUnitList(Application_Csd_Action act) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue("");
		list.add(item);

	 String sql="SELECT int_id as int_app_id_f ,coalesce(vch_firm_name,'')vch_indus_name,coalesce(vch_mobile, 'NA')vch_wrk_phon FROM custom_bonds.mst_regis_importing_unit where unit_for='CSD' ";
		try {
			//System.out.println("dis========================"+sql);
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("vch_indus_name"));
				item.setValue(rs.getInt("int_app_id_f"));
				list.add(item);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
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

	public ArrayList getBondUnitList(Application_Csd_Action act, String id) {
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SelectItem item = new SelectItem();
		item.setLabel("--SELECT--");
		item.setValue(0);
		list.add(item);

	 String sql="SELECT distinct a.int_id, a.int_bond_id, a.int_import_id,b.vch_applicant_name FROM custom_bonds.mst_custom_bond_importing_unit_maping a,custom_bonds.custom_bonds_master b where a.int_bond_id=b.int_id and a.int_import_id="+id+"";
		try {
			System.out.println("dis=======11================="+sql);
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new SelectItem();
				item.setLabel(rs.getString("vch_applicant_name"));
				item.setValue(rs.getInt("int_bond_id"));
				list.add(item);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
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
	
	public double getCeshFee(String brand_Id, String packging_Id) {

		double cesh = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String queryList = 	" SELECT distinct  b.cesh " +
								" FROM distillery.brand_registration_20_21 a , distillery.packaging_details_20_21 b  " +
								" WHERE a.brand_id=b.brand_id_fk  " +
								" AND a.brand_id =?  AND b.package_id=? ";
			System.out.println("import fee="+queryList);
			con = ConnectionToDataBase.getConnection();

			pstmt = con.prepareStatement(queryList);

			pstmt.setInt(1, Integer.parseInt(brand_Id));
			pstmt.setInt(2, Integer.parseInt(packging_Id));
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				cesh = rs.getDouble("cesh");
	             }

		} catch ( Exception se) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));	
			
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
		return cesh;

	}
	
	
	
	public void getBalance(Application_Csd_Action act) {

	  act.setBalance_corona_fee(0);
	  act.setBalance_duty_fee(0);
	  act.setBalance_import_fee(0);
	  act.setBalance_spcl_fee(0);
	  act.setBalance_scanning_fee(0);
	    
	    Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

		String queryList = 	" select sum(coalesce(z.vch_total_amount,0))+sum(coalesce(z.amount,0))-sum(coalesce(import_fee,0)) as balance from " +
				            " (select a.vch_mill_name::int,0 as import_fee , 0 as amount ,sum(a.vch_total_amount::int)vch_total_amount from licence.mst_challan_master a,licence.challan_head_details b,                "+
							"  (select x.*, a.divcode,  a.tcode from (SELECT core_district_id, int_app_id FROM licence.fl2_2b_2d_fl2a_csd_20_21 where vch_mobile_no='"+ResourceUtil.getUserNameAllReq()+"')x ,"+                                                           
							" licence.treasury a where a.district_id=x.core_district_id)c where a.vch_division=c.divcode and a.vch_treasury=tcode                                   "+                           
							" and a.vch_challan_id=b.vch_challan_id and                                                                                                             "+                           
							" vch_rajaswa_head in ('3900103030000',' 3900105040000') and g6_head in (2) and a.vch_trn_status='SUCCESS'                           "+
							" and a.vch_mill_name='"+act.getInt_id()+"'  and vch_mill_type='CSD-DEPO' and dat_created_date>'2020-03-31' and vch_register_flag='R' group by a.vch_mill_name       " +
							" union                                                            "+
							" select bwfl_id as vch_mill_name,sum(import_fee)import_fee,0 as amount,0 as vch_total_amount from fl2d.application_csd_permit_mst_20_21  where                                "+
							" unit_type not in ('D','B') and bwfl_id="+act.getInt_id()+" and district_id="+act.getDisid()+" and vch_approved='APPROVED' group by bwfl_id                                                                                                                                   "+
                            " union " +
							" select unit_id, 0 as import_fee, sum(amount) amount,0 as vch_total_amount from fl2d.advance_opening_fl2a where  unit_id="+act.getInt_id()+" and fee_type='IMPORT FEE' and unit_type='DEPO' "+
                            " and district_id="+act.getDisid()+" group by unit_id)z";        
			
			             System.out.println("import fee="+queryList);
			             con = ConnectionToDataBase.getConnection();
			             pstmt=con.prepareStatement(queryList);
                         rs = pstmt.executeQuery();

			if (rs.next()) {
				   act.setBalance_import_fee(rs.getDouble("balance"));
	             }
			
			pstmt=null;
			rs=null;
			
                            
			
	String queryList1 = 	" select sum(coalesce(z.vch_total_amount,0))+sum(coalesce(z.amount,0))-sum(coalesce(special_fee,0)) as balance from " +
			                " (select a.vch_mill_name::int,0 as special_fee , 0 as amount ,sum(a.vch_total_amount::int)vch_total_amount from licence.mst_challan_master a,licence.challan_head_details b,                 "+
							"  (select x.*, a.divcode,  a.tcode from (SELECT core_district_id, int_app_id FROM licence.fl2_2b_2d_fl2a_csd_20_21 where vch_mobile_no='"+ResourceUtil.getUserNameAllReq()+"')x , "+                                                          
							" licence.treasury a where a.district_id=x.core_district_id)c where a.vch_division=c.divcode and a.vch_treasury=tcode                                    "+                          
							" and a.vch_challan_id=b.vch_challan_id and                                                                                                              "+                          
							" vch_rajaswa_head in ('3900105020000','3900103020000') and g6_head in (24,13) and a.vch_trn_status='SUCCESS'                                                               "+
							"  and a.vch_mill_name='"+act.getUnit_id()+"'  and vch_mill_type='CSD' and dat_created_date>'2020-03-31' and vch_register_flag='R' group by a.vch_mill_name " +
							"  union                                                                     "+
							" select unit_id as vch_mill_name,sum(special_fee)special_fee,0 as amount,0 as vch_total_amount from fl2d.application_csd_permit_mst_20_21  where  unit_id="+act.getUnit_id()+"                              "+
							" and unit_type not in ('D','B') and bwfl_id="+act.getInt_id()+" and district_id="+act.getDisid()+" and vch_approved='APPROVED' group by unit_id                                                                                                                                    "+
							
							" union " +
							" select unit_id, 0 as special_fee, sum(amount) amount,0 as vch_total_amount from fl2d.advance_opening_fl2a where  unit_id="+act.getUnit_id()+" and fee_type='SPECIAL FEE' "+
                            " and district_id="+act.getDisid()+" group by unit_id)z";        
			
			             System.out.println("import fee="+queryList1);
			             
			             pstmt=con.prepareStatement(queryList1);
                         rs = pstmt.executeQuery();

			if (rs.next()) {
				   act.setBalance_spcl_fee(rs.getDouble("balance"));
	             }

			pstmt=null;
			rs=null;
			String queryList2="";
			if(act.getRadio().equalsIgnoreCase("IU")){
				
				queryList2 = " select sum(coalesce(z.vch_total_amount,0))+sum(coalesce(z.amount,0))-sum(coalesce(duty,0)) as balance from  " +
			            " (select a.vch_mill_name::int,0 as duty , 0 as amount ,sum(a.vch_total_amount::int)vch_total_amount from licence.mst_challan_master a,licence.challan_head_details b,                 "+
						" (select x.*, a.divcode,  a.tcode from (SELECT core_district_id, int_app_id FROM licence.fl2_2b_2d_fl2a_csd_20_21 where vch_mobile_no='"+ResourceUtil.getUserNameAllReq()+"')x , "+                                                          
						" licence.treasury a where a.district_id=x.core_district_id)c where a.vch_division=c.divcode and a.vch_treasury=tcode                                    "+                          
						" and a.vch_challan_id=b.vch_challan_id and                                                                                                              "+                          
						" vch_rajaswa_head in('3900105040000') and g6_head in (4) and a.vch_trn_status='SUCCESS'                                               "+
						" and a.vch_mill_name='"+act.getUnit_id()+"'  and vch_mill_type='CSD' and dat_created_date>'2020-03-31' and vch_register_flag='R' group by a.vch_mill_name " +
						" union                                                                     "+
						" select unit_id as vch_mill_name,sum(duty)duty,0 as amount,0 as vch_total_amount from fl2d.application_csd_permit_mst_20_21  where  unit_id="+act.getUnit_id()+"                                           "+
						"  and unit_type not in ('D','B') and bwfl_id="+act.getInt_id()+" and district_id="+act.getDisid()+" and vch_approved='APPROVED' group by unit_id                                                                                                                                    "+
						
						" union" +
						" select unit_id, 0 as duty, sum(amount) amount,0 as vch_total_amount from fl2d.advance_opening_fl2a where  unit_id="+act.getUnit_id()+" and fee_type='EXCISE DUTY' "+
                        " and district_id="+act.getDisid()+" group by unit_id)z"; 
			}
			else if(act.getRadio().equalsIgnoreCase("O")){
	       queryList2 = " select sum(coalesce(z.vch_total_amount,0))+sum(coalesce(z.amount,0))-sum(coalesce(duty,0)) as balance from  " +
			            " (select a.vch_mill_name::int,0 as duty , 0 as amount ,sum(a.vch_total_amount::int)vch_total_amount from licence.mst_challan_master a,licence.challan_head_details b,                 "+
						" (select x.*, a.divcode,  a.tcode from (SELECT core_district_id, int_app_id FROM licence.fl2_2b_2d_fl2a_csd_20_21 where vch_mobile_no='"+ResourceUtil.getUserNameAllReq()+"')x , "+                                                          
						" licence.treasury a where a.district_id=x.core_district_id)c where a.vch_division=c.divcode and a.vch_treasury=tcode                                    "+                          
						" and a.vch_challan_id=b.vch_challan_id and                                                                                                              "+                          
						" vch_rajaswa_head in('3900105010000','3900103010000') and g6_head in (2,3) and a.vch_trn_status='SUCCESS'                                               "+
						" and a.vch_mill_name='"+act.getUnit_id()+"'  and vch_mill_type='CSD' and dat_created_date>'2020-03-31' and vch_register_flag='R' group by a.vch_mill_name " +
						" union                                                                     "+
						" select unit_id as vch_mill_name,sum(duty)duty,0 as amount,0 as vch_total_amount from fl2d.application_csd_permit_mst_20_21  where  unit_id="+act.getUnit_id()+"                                           "+
						"  and unit_type not in ('D','B') and bwfl_id="+act.getInt_id()+" and district_id="+act.getDisid()+" and vch_approved='APPROVED' group by unit_id                                                                                                                                    "+
						
						" union" +
						" select unit_id, 0 as duty, sum(amount) amount,0 as vch_total_amount from fl2d.advance_opening_fl2a where  unit_id="+act.getUnit_id()+" and fee_type='EXCISE DUTY' "+
                        " and district_id="+act.getDisid()+" group by unit_id)z";        
			}
			             System.out.println("import fee="+queryList2);
			            
			             pstmt=con.prepareStatement(queryList2);
                         rs = pstmt.executeQuery();

			if (rs.next()) {
				   act.setBalance_duty_fee(rs.getDouble("balance"));
	             }
			
			pstmt=null;
			rs=null;
                                                                                                                                                                                             
	String queryList3 = " select sum(coalesce(z.vch_total_amount,0))+sum(coalesce(z.amount,0))-sum(coalesce(tot_scanning_fee,0)) as balance from " +
			            " (select a.vch_mill_name::int,0 as tot_scanning_fee , 0 as amount ,sum(a.vch_total_amount::int)vch_total_amount from licence.mst_challan_master a,licence.challan_head_details b,                "+
						" (select x.*, a.divcode,  a.tcode from (SELECT core_district_id, int_app_id FROM licence.fl2_2b_2d_fl2a_csd_20_21 where vch_mobile_no='"+ResourceUtil.getUserNameAllReq()+"')x ,"+                                                         
						" licence.treasury a where a.district_id=x.core_district_id)c where a.vch_division=c.divcode and a.vch_treasury=tcode                                   "+                         
						" and a.vch_challan_id=b.vch_challan_id and                                                                                                             "+                         
						" vch_rajaswa_head in ('3900800060000') and g6_head in (6) and a.vch_trn_status='SUCCESS'                                                               "+
						" and a.vch_mill_name='"+act.getUnit_id()+"'  and vch_mill_type='CSD' and dat_created_date>'2020-03-31' and vch_register_flag='R' group by a.vch_mill_name " +
						" union                                                                    "+
						" select unit_id as vch_mill_name,sum(tot_scanning_fee)tot_scanning_fee,0 as amount,0 as vch_total_amount from fl2d.application_csd_permit_mst_20_21  where  unit_id="+act.getUnit_id()+"                   "+
						" and unit_type not in ('D','B') and bwfl_id="+act.getInt_id()+" and district_id="+act.getDisid()+" and vch_approved='APPROVED' group by unit_id " +
						" union                                     " +
                        " select unit_id, 0 as tot_scanning_fee, sum(amount) amount,0 as vch_total_amount from fl2d.advance_opening_fl2a where  unit_id="+act.getUnit_id()+" and fee_type='SCANING FEE' "+
                        " and district_id="+act.getDisid()+" group by unit_id)z";        
			
			             System.out.println("import fee="+queryList3);
			            
			             pstmt=con.prepareStatement(queryList3);
                         rs = pstmt.executeQuery();

			if (rs.next()) {
				   act.setBalance_scanning_fee(rs.getDouble("balance"));
	             }
			
			pstmt=null;
			rs=null;
                                                                                                                                                                                             
	String queryList4= 	  " select sum(coalesce(z.vch_total_amount,0))+sum(coalesce(z.amount,0))-sum(coalesce(total_corona_fee,0)) as balance from " +
				          " (select a.vch_mill_name::int,0 as total_corona_fee , 0 as amount ,sum(a.vch_total_amount::int)vch_total_amount from licence.mst_challan_master a,licence.challan_head_details b,                "+
		                  "  (select x.*, a.divcode,  a.tcode from (SELECT core_district_id, int_app_id FROM licence.fl2_2b_2d_fl2a_csd_20_21 where vch_mobile_no='"+ResourceUtil.getUserNameAllReq()+"')x ,"+                                                          
		                  " licence.treasury a where a.district_id=x.core_district_id)c where a.vch_division=c.divcode and a.vch_treasury=tcode                                   "+                          
		                  " and a.vch_challan_id=b.vch_challan_id and                                                                                                             "+                          
		                  " vch_rajaswa_head in ('003900103010000', '003900105010000') and g6_head in (09) and a.vch_trn_status='SUCCESS'                                         "+
		                  " and a.vch_mill_name='"+act.getUnit_id()+"'  and vch_mill_type='CSD' and dat_created_date>'2020-03-31' and vch_register_flag='R' group by a.vch_mill_name " +
		                  " union    "+
		                  " select unit_id as vch_mill_name,sum(total_corona_fee)total_corona_fee,0 as amount,0 as vch_total_amount from fl2d.application_csd_permit_mst_20_21  where  unit_id="+act.getUnit_id()+"                   "+
		                  " and unit_type not in ('D','B') and bwfl_id="+act.getInt_id()+" and district_id="+act.getDisid()+" and vch_approved='APPROVED' group by unit_id                                                                                                                                    "+
		                  " union" +
		                  " select unit_id, 0 as total_corona_fee, sum(amount) amount,0 as vch_total_amount from fl2d.advance_opening_fl2a where  unit_id="+act.getUnit_id()+" and fee_type='SPECIAL CONSIDERATION FEE'  "+
		                  " and district_id="+act.getDisid()+" group by unit_id)z";        
			
			             System.out.println("import fee="+queryList4);
			            
			             pstmt=con.prepareStatement(queryList4);
                         rs = pstmt.executeQuery();

			if (rs.next()) {
				   act.setBalance_corona_fee(rs.getDouble("balance"));
	             }
		} catch ( Exception se) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, se.getMessage(), se.getMessage()));	
			
			se.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
				if(rs !=null){
					rs.close();
				}

			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		

	}
}
