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

import com.mentor.action.ImportChallanForPermitFL2D_20_21Action;
import com.mentor.action.ImportPermitAction;
import com.mentor.action.Import_Permit_FL2D_20_21Action;
import com.mentor.datatable.ImportChallanForPermitFL2D_20_21DT;
import com.mentor.datatable.ImportPermitDT;
import com.mentor.datatable.Import_Permit_FL2D_20_21DT;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class ImportChallanForPermitFL2D_20_21Impl {

	
	public boolean getDetails(ImportChallanForPermitFL2D_20_21Action act) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag=false;
		try {
			con = ConnectionToDataBase.getConnection();
		String selQr="select int_id as app_id,vch_firm_district_id::numeric as district_id,unit_id, 'BWFL' as login_type,vch_license_type from " +
					" bwfl.registration_of_bwfl_lic_holder_20_21 where mobile_number='"+ ResourceUtil.getUserNameAllReq().trim()+"' " +
					" union " +
					" select int_app_id as app_id,core_district_id as district_id,0 as unit_id,'FL2D' as login_type, '99' as vch_license_type from " +
					" licence.fl2_2b_2d_20_21 where vch_license_type='FL2D' and vch_mobile_no='"+ ResourceUtil.getUserNameAllReq().trim() + "' ";
		
			System.out.println("login_dtl=" + selQr);
			pstmt = con.prepareStatement(selQr);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				act.setInt_id(rs.getInt("app_id"));
				act.setUnit_id(rs.getInt("unit_id"));
				act.setDistrict_id(rs.getInt("district_id"));
				act.setLogin_type(rs.getString("login_type"));
				act.setVch_license_type(rs.getInt("vch_license_type"));
				flag=true;
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
		return flag;
	}
	public ArrayList datalist(ImportChallanForPermitFL2D_20_21Action act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		 int i = 1;
		String filter="";
		if(act.getLogin_type().equalsIgnoreCase("BWFL")){
			filter=" and unit_id='"+ act.getUnit_id()+ "' and  login_type='BWFL' ";
		}else{
			filter=" and login_type='FL2D' ";
		}

		try { 
			selQr = "select app_id,import_fee,special_fee " +
					//",COALESCE(vch_status,'Pending')as vch_status" +
					" from bwfl_license.import_permit_20_21 where district_id='"+ act.getDistrict_id()+ 
					"' and bwfl_id='"+ act.getInt_id()+"' "+filter+" and import_fee_challan_no is null and challan_flg is null " +
					" and COALESCE(vch_status,'Pending')='Pending' order by app_id desc";

			System.out.println("datalist=" + selQr);
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(selQr);

			rs = ps.executeQuery();
			while (rs.next()) {
				ImportChallanForPermitFL2D_20_21DT dt = new ImportChallanForPermitFL2D_20_21DT();
				dt.setSno(i);
				dt.setApp_id(rs.getInt("app_id")); 
				dt.setImport_fee(rs.getDouble("import_fee")); 
				dt.setSpecial_fee(rs.getDouble("special_fee"));
				//dt.setImport_fee_challan_no(rs.getString("import_fee_challan_no"));
				//dt.setVch_status(rs.getString("vch_status"));
				//if(rs.getString("vch_status").equalsIgnoreCase("Pending")&& !rs.getString("import_fee_challan_no").equalsIgnoreCase("NUL"))
				// cases++;
				list.add(dt);
				 i++;
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
	public void ChallanList(ImportChallanForPermitFL2D_20_21Action act) {
		System.out.println("-/-");
		ArrayList ImportChallanList = new ArrayList();
		ArrayList SpclChallanList = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SelectItem ImportChallan = new SelectItem();
		ImportChallan.setLabel("--SELECT--");
		ImportChallan.setValue("");
		ImportChallanList.add(ImportChallan);

		SelectItem SpclChallan = new SelectItem();
		SpclChallan.setLabel("--SELECT--");
		SpclChallan.setValue("");
		SpclChallanList.add(SpclChallan); 
		try {
			
			String selQr="";
			con = ConnectionToDataBase.getConnection();
			 selQr = " select chalan_no,chalan_date,amount from bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no in  ( SELECT DISTINCT "
					+ " CASE WHEN head_type like 'Import%' then chalan_no "
					+ " WHEN head_type like 'BWFL%' then chalan_no else '' end as import_fee_challan_no "
				//	+ " ,CASE WHEN head_type like 'Special%' then chalan_no else '' end as spcl_fee_challan_no "
					+ " FROM bwfl_license.chalan_deposit_bwfl_fl2d  "
					+ " WHERE unit_id="+act.getInt_id()+" AND unit_type= "
					+ " CASE WHEN 1="+act.getVch_license_type()+" THEN 'BWFL2A' WHEN 2="+act.getVch_license_type()+" THEN 'BWFL2B' WHEN 3="+act.getVch_license_type()+" THEN 'BWFL2C' WHEN 4="+act.getVch_license_type()+" THEN 'BWFL2D' END " +
					" EXCEPT  " +
					" select DISTINCT import_fee_challan_no" +
					" from bwfl_license.import_permit_20_21  " +
					" where bwfl_id='"+act.getInt_id()+"' and bwfl_type="+act.getVch_license_type()+" and district_id='"+act.getDistrict_id()+"') and  unit_id="+act.getInt_id()+" AND unit_type= "
					+ " CASE WHEN 1="+act.getVch_license_type()+" THEN 'BWFL2A' WHEN 2="+act.getVch_license_type()+" THEN 'BWFL2B' WHEN 3="+act.getVch_license_type()+" THEN 'BWFL2C' WHEN 4="+act.getVch_license_type()+" THEN 'BWFL2D' END " ;
				
			System.out.println("ChallanList=" + selQr);
			pstmt = con.prepareStatement(selQr);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("01="+rs.getString("chalan_no"));
				if(!rs.getString("chalan_no").trim().equalsIgnoreCase("")){
					System.out.println("1");
					ImportChallan = new SelectItem();
					ImportChallan.setLabel(rs.getString("chalan_no")+" (Rs."+rs.getString("amount")+") - "+rs.getString("chalan_date"));
					ImportChallan.setValue(rs.getString("chalan_no"));
					ImportChallanList.add(ImportChallan);
				}	
			}
			act.setImportChallanList(ImportChallanList);
			 selQr = " select chalan_no,chalan_date,amount from bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no in  ( SELECT DISTINCT "+
			 		" CASE WHEN head_type like 'Special%' then chalan_no else '' end as spcl_fee_challan_no "
						+ " FROM bwfl_license.chalan_deposit_bwfl_fl2d  "
						+ " WHERE unit_id="+act.getInt_id()+" AND unit_type= "
						+ " CASE WHEN 1="+act.getVch_license_type()+" THEN 'BWFL2A' WHEN 2="+act.getVch_license_type()+" THEN 'BWFL2B' WHEN 3="+act.getVch_license_type()+" THEN 'BWFL2C' WHEN 4="+act.getVch_license_type()+" THEN 'BWFL2D' END " +
						" EXCEPT  " +
						" select spcl_fee_challan_no" +
						" from bwfl_license.import_permit_20_21  " +
						" where bwfl_id='"+act.getInt_id()+"' and bwfl_type="+act.getVch_license_type()+" and district_id='"+act.getDistrict_id()+"' ) and  unit_id="+act.getInt_id()+" AND unit_type= "
						+ " CASE WHEN 1="+act.getVch_license_type()+" THEN 'BWFL2A' WHEN 2="+act.getVch_license_type()+" THEN 'BWFL2B' WHEN 3="+act.getVch_license_type()+" THEN 'BWFL2C' WHEN 4="+act.getVch_license_type()+" THEN 'BWFL2D' END " ;
				
             System.out.println("ChallanList1=" + selQr);
			 pstmt=null;
			 rs=null;
				pstmt = con.prepareStatement(selQr);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println("01="+rs.getString("chalan_no"));
					if(!rs.getString("chalan_no").trim().equalsIgnoreCase("")){
						System.out.println("2");
						SpclChallan = new SelectItem();
						SpclChallan.setLabel(rs.getString("chalan_no")+" (Rs."+rs.getString("amount")+") - "+rs.getString("chalan_date"));
						SpclChallan.setValue(rs.getString("chalan_no"));
						SpclChallanList.add(SpclChallan);
					}
					
				}
			act.setSpclChallanList(SpclChallanList);

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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
	public void ChallanList1(ImportChallanForPermitFL2D_20_21Action act) {
		System.out.println("-/-");
		ArrayList ImportChallanList = new ArrayList();
		ArrayList SpclChallanList = new ArrayList();
		ArrayList scanfeeChallanList = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SelectItem ImportChallan = new SelectItem();
		ImportChallan.setLabel("--SELECT--");
		ImportChallan.setValue("");
		ImportChallanList.add(ImportChallan);

		SelectItem SpclChallan = new SelectItem();
		SpclChallan.setLabel("--SELECT--");
		SpclChallan.setValue("");
		SpclChallanList.add(SpclChallan); 
		
		SelectItem scanfeeChallan = new SelectItem();
		scanfeeChallan.setLabel("--SELECT--");
		scanfeeChallan.setValue("");
		scanfeeChallanList.add(scanfeeChallan); 
		try {
			
			String selQr="";
			con = ConnectionToDataBase.getConnection();
			selQr =" select chalan_no,chalan_date,amount from bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no in  ( SELECT DISTINCT "+
					" CASE WHEN head_type like 'FL2D%' then chalan_no else '' end as import_fee_challan_no  " +
			" FROM bwfl_license.chalan_deposit_bwfl_fl2d   WHERE unit_id='"+act.getInt_id()+"' AND unit_type='FL2D' " +
			" EXCEPT   " +
			" select DISTINCT import_fee_challan_no from bwfl_license.import_permit_20_21    " +
			" where bwfl_id='"+act.getInt_id()+"' and bwfl_type=99 and district_id='"+act.getDistrict_id()+"' ) and  unit_id="+act.getInt_id()+" AND unit_type='FL2D'";
		
			System.out.println("ChallanList=" + selQr);
			pstmt = con.prepareStatement(selQr);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("01="+rs.getString("chalan_no"));
				if(!rs.getString("chalan_no").trim().equalsIgnoreCase("")){
					
					ImportChallan = new SelectItem();
					ImportChallan.setLabel(rs.getString("chalan_no")+" (Rs."+rs.getString("amount")+") - "+rs.getString("chalan_date"));
					ImportChallan.setValue(rs.getString("chalan_no"));
					ImportChallanList.add(ImportChallan);
				}	
			}
			act.setImportChallanList(ImportChallanList);
			selQr = " select chalan_no,chalan_date,amount from bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no in  ( SELECT DISTINCT "+
					" CASE WHEN head_type like 'Special%' then chalan_no else '' end as spcl_fee_challan_no  "+
			" FROM bwfl_license.chalan_deposit_bwfl_fl2d   WHERE unit_id='"+act.getInt_id()+"' AND unit_type='FL2D'  EXCEPT "+
			" select spcl_fee_challan_no from bwfl_license.import_permit_20_21 where bwfl_id='"+act.getInt_id()+"' and bwfl_type=99 and district_id='"+act.getDistrict_id()+"' ) and  unit_id="+act.getInt_id()+" AND unit_type='FL2D'";

			
			System.out.println("ChallanList1=" + selQr);
			 pstmt=null;
			 rs=null;
				pstmt = con.prepareStatement(selQr);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println("01="+rs.getString("chalan_no"));
					if(!rs.getString("chalan_no").trim().equalsIgnoreCase("")){
						System.out.println("2");
						SpclChallan = new SelectItem();
						SpclChallan.setLabel(rs.getString("chalan_no")+" (Rs."+rs.getString("amount")+") - "+rs.getString("chalan_date"));
						SpclChallan.setValue(rs.getString("chalan_no"));
						SpclChallanList.add(SpclChallan);
					}
					
				}
			act.setSpclChallanList(SpclChallanList);
			
		

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(e.getMessage(), e.getMessage()));
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
public boolean getChallanBalance(ImportChallanForPermitFL2D_20_21Action act,double import_fee,double special_fee) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flg1=false;
		boolean flg2=false;
		boolean flg=false;
		String sql="";
		double total_import_fee=0;
		double total_spcl_fee=0;
		
		try {
			
			for(int i=0;i<act.getImportChallanList().size();i++){
				ImportChallanForPermitFL2D_20_21DT dt1 = (ImportChallanForPermitFL2D_20_21DT) act.getImportChallanList().get(i);
				sql = " select COALESCE(amount,0.0)import_amount " +
					" FROM bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no='"+dt1.getImportfeeChallan()+"' " 
					+ " and unit_id="+act.getInt_id()+" AND unit_type='FL2D'";
		System.out.println("getChallanImportFee1--"+sql);
			con = ConnectionToDataBase.getConnection();
			//con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				total_import_fee=total_import_fee+rs.getDouble("import_amount");
			}
			
		}
			
				 if(import_fee>total_import_fee){
					 
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Import Fee for selected permits is greater then your selected Challan"));
				 }else {
					 flg1=true;
					 System.out.println(import_fee+"-import fee is "+rs.getDouble("import_amount"));
				 }
			
			ps=null;
			rs=null;
			
			for(int i=0;i<act.getSpclChallanList().size();i++){
				ImportChallanForPermitFL2D_20_21DT dt1 = (ImportChallanForPermitFL2D_20_21DT) act.getSpclChallanList().get(i);
			sql = " select COALESCE(amount,0.0)spcl_amount " +
					" FROM bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no='"+dt1.getSpclfeeChallan()+"' " 
					+ " and unit_id="+act.getInt_id()+" AND unit_type='FL2D'";
			System.out.println("ChallanSpcl1="+sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				
				total_spcl_fee=total_spcl_fee+rs.getDouble("spcl_amount");
				 
			}
			
			
			
			}
			
			if(special_fee>total_spcl_fee){
				 
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Special Fee for selected permits is greater then your selected Challan"));
				 }else {
					 flg2=true;
					 System.out.println(special_fee+"-import fee is "+rs.getDouble("spcl_amount"));
				 }
			if(flg1 && flg2){
				flg=true;
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
public boolean getChallanBalanceBWFL(ImportChallanForPermitFL2D_20_21Action act,double import_fee,double special_fee) {
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	boolean flg1=false;
	boolean flg2=false;
	boolean flg=false;
	String sql="";
	sql = " select COALESCE(amount,0.0)import_amount " +
		" FROM bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no='"+act.getImport_fee_challan_no()+"' " 
		+ " and unit_id="+act.getInt_id()+" AND unit_type= "
		+ " CASE WHEN 1="+act.getVch_license_type()+" THEN 'BWFL2A' WHEN 2="+act.getVch_license_type()+" THEN 'BWFL2B' WHEN 3="+act.getVch_license_type()+" THEN 'BWFL2C' WHEN 4="+act.getVch_license_type()+" THEN 'BWFL2D' END ";
System.out.println("getChallanImportFee-"+sql);
	try {
		con = ConnectionToDataBase.getConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			 if(import_fee>rs.getDouble("import_amount")){
				 
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Import Fee for selected permits is greater then your selected Challan"));
			 }else {
				 flg1=true;
				 System.out.println(import_fee+"-import fee is "+rs.getDouble("import_amount"));
			 }
		}
		ps=null;
		rs=null;
		sql = " select COALESCE(amount,0.0)spcl_amount " +
				" FROM bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no='"+act.getSpcl_fee_challan_no()+"' " 
				+ " and unit_id="+act.getInt_id()+" AND unit_type= "
				+ " CASE WHEN 1="+act.getVch_license_type()+" THEN 'BWFL2A' WHEN 2="+act.getVch_license_type()+" THEN 'BWFL2B' WHEN 3="+act.getVch_license_type()+" THEN 'BWFL2C' WHEN 4="+act.getVch_license_type()+" THEN 'BWFL2D' END ";
		System.out.println("ChallanSpcl="+sql);
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if (rs.next()) {
			 if(special_fee>rs.getDouble("spcl_amount")){
				 
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Special Fee for selected permits is greater then your selected Challan"));
			 }else {
				 flg2=true;
				 System.out.println(special_fee+"-import fee is "+rs.getDouble("spcl_amount"));
			 }
		}
		System.out.println("flg1="+flg1+" flg2="+flg2);
		if(flg1 && flg2){
			flg=true;
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
	public boolean update(ImportChallanForPermitFL2D_20_21Action act){


	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int saveStatus = 0;
	
	//int max_id = maxId(act.getVch_firm_district_id());
	//int app_id=this.app_id();
	String query = ""; 
	//double import_fee=0;
	//double duty=0;
	//double add_duty=0;
	//double special_fee=0;
	boolean flg=false;
	//String navigation="NA";
			try {
		 query="update bwfl_license.import_permit_20_21 set import_fee_challan_no='NA' ," +
		 		"spcl_fee_challan_no='NA', challan_flg='T' where app_id=?";
		 System.out.println("update="+query);
		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
		if(act.getDatalist().size()>0 && act.getImportChallanList().size()>0 && act.getSpclChallanList().size()>0){
			for(int i=0;i<act.getDatalist().size();i++){
				
			ImportChallanForPermitFL2D_20_21DT dt = (ImportChallanForPermitFL2D_20_21DT) act.getDatalist().get(i);
			System.out.println("dt.getApp_id()="+dt.getApp_id());
				if(dt.isCheckbox()){
					saveStatus=0;
					System.out.println("dt.isCheckbox()="+dt.isCheckbox());
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, dt.getApp_id());
				saveStatus = pstmt.executeUpdate();
				
				  if(saveStatus>0){
					
					for(int j=0;j<act.getImportChallanList().size();j++){
						saveStatus=0;
						ImportChallanForPermitFL2D_20_21DT dt1 = (ImportChallanForPermitFL2D_20_21DT) act.getImportChallanList().get(j);
					String query1="INSERT INTO fl2d.fl2d_bwfl_importfee_challan_detail(app_id,  amount, unit_type, import_fee_challan_no,unit_id)"+
	                               " VALUES (?, ?, ?, ?, ?);";
					System.out.println("dt1.getImportfeeChallan())=="+dt1.getImportfeeChallan());
					pstmt = conn.prepareStatement(query1);
					pstmt.setInt(1, dt.getApp_id());
					pstmt.setDouble(2, 0.0);
					pstmt.setString(3, "FL2D");
					pstmt.setString(4, dt1.getImportfeeChallan());
					pstmt.setInt(5, act.getInt_id());
					saveStatus = pstmt.executeUpdate();
					}
				  }
				
				
      if(saveStatus>0){
					
					for(int j=0;j<act.getSpclChallanList().size();j++){
						saveStatus=0;
						ImportChallanForPermitFL2D_20_21DT dt1 = (ImportChallanForPermitFL2D_20_21DT) act.getSpclChallanList().get(j);
					String query2="INSERT INTO fl2d.fl2d_bwfl_special_challan_detail(app_id, spcl_fee_challan_no, amount, unit_type, unit_id)"+
	                               " VALUES (?, ?, ?, ?, ?);";
					
					System.out.println("dt1.getSpclfeeChallan())=="+dt1.getSpclfeeChallan());
					pstmt = conn.prepareStatement(query2);
					pstmt.setInt(1, dt.getApp_id());
					pstmt.setString(2, dt1.getSpclfeeChallan());
					pstmt.setDouble(3, 0.0);
					pstmt.setString(4, "FL2D");
					pstmt.setInt(5, act.getInt_id());
					saveStatus = pstmt.executeUpdate();
					}
				}
				}
			 	
				
			}
		}
		
		
			
	
		
		if (saveStatus > 0) {
			conn.setAutoCommit(true);
			flg=true;
			act.setFlg(false);
			System.out.println("flg="+act.isFlg());
			act.setDatalist(this.datalist(act));
			
		//	navigation=act.getLogin_type();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Application Completed Successfully "));
			//ResourceUtil.addErrorMessage(Constants.SAVED_SUCESSFULLY,Constants.SAVED_SUCESSFULLY);
			//act.reset();

		} 
		
		else {
			flg=false;
			conn.rollback();
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
	
	
	public boolean getSpclChallanDetail(ImportChallanForPermitFL2D_20_21Action act, ImportChallanForPermitFL2D_20_21DT dt) throws SQLException{


		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		
		//int max_id = maxId(act.getVch_firm_district_id());
		//int app_id=this.app_id();
		String query = ""; 
		boolean flg=false;
		//String navigation="NA";
				try {
					query=" SELECT * from bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no='"+dt.getSpclfeeChallan()+"' and unit_id='"+act.getInt_id()+"' AND unit_type='FL2D'" +
					 	  " and chalan_no not in(select DISTINCT coalesce(spcl_fee_challan_no,'NA') from bwfl_license.import_permit_20_21)" +
					      " and  chalan_no not in (SELECT DISTINCT coalesce(spcl_fee_challan_no,'NA') from fl2d.fl2d_bwfl_special_challan_detail)" +
					      " and  chalan_no not in (SELECT DISTINCT coalesce(import_fee_challan_no,'NA') from fl2d.fl2d_bwfl_importfee_challan_detail)";
					
			 System.out.println("spcl fee chek="+query);
			conn = ConnectionToDataBase.getConnection();
			
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
		if(rs.next()){
			flg=true;
		}else{

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Challan NO. "+dt.getSpclfeeChallan()+" Not Found "));
			flg=false;
		}
		
		
			
			
			

		}

		catch (Exception e) {
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
	
	
	public boolean getImpoetChallanDetail(ImportChallanForPermitFL2D_20_21Action act, ImportChallanForPermitFL2D_20_21DT dt) throws SQLException{


		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saveStatus = 0;
		
		//int max_id = maxId(act.getVch_firm_district_id());
		//int app_id=this.app_id();
		String query = ""; 
		//double import_fee=0;
		//double duty=0;
		//double add_duty=0;
		//double special_fee=0;
		boolean flg=false;
		//String navigation="NA";
				try {
			query="SELECT * from bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no='"+dt.getImportfeeChallan()+"' and unit_id='"+act.getInt_id()+"' AND unit_type='FL2D'" +
			 	  " and chalan_no not in(select DISTINCT coalesce(import_fee_challan_no,'NA') from bwfl_license.import_permit_20_21)" +
			      " and  chalan_no not in (SELECT DISTINCT coalesce(import_fee_challan_no,'NA') from fl2d.fl2d_bwfl_importfee_challan_detail)" +
			      " and  chalan_no not in (SELECT DISTINCT coalesce(spcl_fee_challan_no,'NA') from fl2d.fl2d_bwfl_special_challan_detail)";
		
			 System.out.println("import feee chek ="+query);
			conn = ConnectionToDataBase.getConnection();
			
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
		if(rs.next()){
			flg=true;
		}else{
			flg=false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Challan NO. "+dt.getImportfeeChallan()+" Not Found "));
		}
		
			
			
			

		}

		catch (Exception e) {
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
}
