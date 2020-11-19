package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.mentor.action.Import_Permit_Challan_CSD_Action;
import com.mentor.datatable.Import_Permit_Challan_CSD_DT;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;

public class Import_Permit_Challan_CSD_Impl {

	
	public boolean getDetails(Import_Permit_Challan_CSD_Action act) {
        
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag=false;
		try {
			con = ConnectionToDataBase.getConnection();
		String selQr="select x.*, a.divcode, a.tcode from  "+
					 "	(SELECT core_district_id, int_app_id FROM licence.fl2_2b_2d_fl2a_csd_20_21 where vch_mobile_no='"+ResourceUtil.getUserNameAllReq().trim()+"')x, "+
					 "	licence.treasury a    "+
					 "	where a.district_id=x.core_district_id";
		
			//System.out.println("login_dtl=" + selQr);
			pstmt = con.prepareStatement(selQr);
			rs = pstmt.executeQuery();
			if (rs.next()) {
			
				act.setDistrict_id(rs.getInt("core_district_id"));
				act.setInt_division_id(rs.getInt("divcode"));
				act.setInt_treasury_id(rs.getInt("tcode"));
				act.setInt_id(rs.getInt("int_app_id"));
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
	public ArrayList datalist(Import_Permit_Challan_CSD_Action act) {

		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selQr = null;
		 int i = 1;
		
		try { 
			selQr = "select app_id,import_fee,special_fee,duty,tot_scanning_fee,total_corona_fee  "+
					" from fl2d.application_csd_permit_mst_20_21 where district_id='"+act.getDistrict_id()+"' and bwfl_id='"+act.getInt_id()+"' and import_fee_challan_no is null  "+
					" and COALESCE(vch_status,'Pending')='Pending' and login_type='FL2A' and unit_type in ('O', 'IU') order by import_fee_challan_no,app_id desc";

			//System.out.println("datalist=" + selQr);
			conn = ConnectionToDataBase.getConnection();
			ps = conn.prepareStatement(selQr);

			rs = ps.executeQuery();
			while (rs.next()) {
				Import_Permit_Challan_CSD_DT dt = new Import_Permit_Challan_CSD_DT();
				dt.setSno(i);
				dt.setApp_id(rs.getInt("app_id")); 
				dt.setImport_fee(rs.getDouble("import_fee")); 
				dt.setSpecial_fee(rs.getDouble("special_fee"));
				dt.setExcise_duty(rs.getDouble("duty"));
				dt.setScannFee(rs.getDouble("tot_scanning_fee"));
				dt.setCoronaFee(rs.getDouble("total_corona_fee"));
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
	public void ChallanList(Import_Permit_Challan_CSD_Action act) {
		////System.out.println("-/-");
		ArrayList ImportChallanList = new ArrayList();
		ArrayList SpclChallanList = new ArrayList();
		ArrayList excisedutylist = new ArrayList();
		ArrayList scanFeelist = new ArrayList();
		ArrayList coronaFeelist = new ArrayList();
		
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
		
		SelectItem exciseduty = new SelectItem();
		exciseduty.setLabel("--SELECT--");
		exciseduty.setValue("");
		excisedutylist.add(exciseduty);
		
		SelectItem scanFee = new SelectItem();
		scanFee.setLabel("--SELECT--");
		scanFee.setValue("");
		scanFeelist.add(scanFee);
		
		SelectItem coronaFee = new SelectItem();
		coronaFee.setLabel("--SELECT--");
		coronaFee.setValue("");
		coronaFeelist.add(coronaFee);
		try {
			
			String selQr="";
			con = ConnectionToDataBase.getConnection();
			 selQr = " select distinct a.vch_challan_id,CAST(a.dat_created_date as character varying),a.vch_division,a.vch_treasury,a.vch_depositor_name                                                 "+
					"	from licence.mst_challan_master a,licence.challan_head_details b,(select x.*, a.divcode,                                                                                         "+
					"	a.tcode from (SELECT core_district_id, int_app_id FROM licence.fl2_2b_2d_fl2a_csd_20_21 where vch_mobile_no='"+ResourceUtil.getUserNameAllReq()+"')x ,                                                           "+
					"	licence.treasury a where a.district_id=x.core_district_id)c where a.vch_division=c.divcode and a.vch_treasury=tcode                                                              "+
					"	and a.vch_challan_id=b.vch_challan_id and                                                                                                                                        "+
					"	vch_rajaswa_head in ('3900103030000',' 3900105040000') and g6_head in (2) and a.vch_trn_status='SUCCESS' and dat_created_date>'2020-03-31' and  a.vch_challan_id not in          "+
					"	(select COALESCE(import_fee_challan_no,'0') from fl2d.application_csd_permit_mst_20_21)                                                                                                "+
					"	union                                                                                                                                                                            "+
					"	select distinct a.vch_challan_id,CAST(a.dat_created_date as character varying),a.vch_division,a.vch_treasury,a.vch_depositor_name                                                "+
					"	from licence.mst_challan_master a,licence.challan_head_details b,(select x.*, a.divcode,                                                                                         "+
					"	a.tcode from (SELECT core_district_id, int_app_id FROM licence.fl2_2b_2d_fl2a_csd_20_21 where vch_mobile_no='"+ResourceUtil.getUserNameAllReq()+"')x ,                                                           "+
					"	licence.treasury a where a.district_id=x.core_district_id)c where a.vch_division=c.divcode and a.vch_treasury=tcode                                                              "+
					"	and a.vch_challan_id=b.vch_challan_id and                                                                                                                                        "+
					"	vch_rajaswa_head in ('3900105020000') and g6_head in (24) and a.vch_trn_status='SUCCESS' and a.dat_created_date>'2020-03-31' and vch_register_flag='N' and  a.vch_challan_id not in"+
					"	(select COALESCE(import_fee_challan_no,'0') from fl2d.application_csd_permit_mst_20_21) " ;
				
			
			 
			 System.out.println("ChallanList=" + selQr);
			pstmt = con.prepareStatement(selQr);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				////System.out.println("01="+rs.getString("vch_challan_id"));
				if(!rs.getString("vch_challan_id").trim().equalsIgnoreCase("")){
					////System.out.println("1");
					ImportChallan = new SelectItem();
					String label= rs.getString("vch_challan_id")+"-"+rs.getString("dat_created_date")+"-"+rs.getString("vch_depositor_name");
					ImportChallan.setLabel(label);
					ImportChallan.setValue(rs.getString("vch_challan_id"));
					ImportChallanList.add(ImportChallan);
				}	
			}
			act.setImportChallanList(ImportChallanList);
			 selQr = " select distinct a.vch_challan_id,a.dat_created_date,a.vch_division,a.vch_treasury,a.vch_depositor_name                  "+
					"	from licence.mst_challan_master a,licence.challan_head_details b,(select x.*, a.divcode,                               "+
					"	a.tcode from (SELECT core_district_id, int_app_id FROM licence.fl2_2b_2d_fl2a_csd_20_21 where vch_mobile_no='"+ResourceUtil.getUserNameAllReq()+"')x , "+
					"	licence.treasury a where a.district_id=x.core_district_id)c where a.vch_register_flag='N' and a.vch_division=c.divcode and a.vch_treasury=tcode    "+
					"	and a.vch_challan_id=b.vch_challan_id and a.vch_trn_status='SUCCESS' and a.dat_created_date>'2020-03-31' and "+
					" vch_rajaswa_head in ('3900105020000') and g6_head in (24) and a.vch_challan_id not in " +
					"(select COALESCE(spcl_fee_challan_no,'0') from fl2d.application_csd_permit_mst_20_21)" ;
										

			 
			 
			System.out.println("ChallanList1=" + selQr);
			 pstmt=null;
			 rs=null;
				pstmt = con.prepareStatement(selQr);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					////System.out.println("01="+rs.getString("vch_challan_id"));
					if(!rs.getString("vch_challan_id").trim().equalsIgnoreCase("")){
						////System.out.println("2");
						SpclChallan = new SelectItem();
						SpclChallan.setLabel(rs.getString("vch_challan_id")+"-"+rs.getString("dat_created_date")+"-"+rs.getString("vch_depositor_name"));
						SpclChallan.setValue(rs.getString("vch_challan_id"));
						SpclChallanList.add(SpclChallan);
					}
					
				}
			act.setSpclChallanList(SpclChallanList);
			
			selQr = " select distinct a.vch_challan_id,a.dat_created_date,a.vch_division,a.vch_treasury,a.vch_depositor_name                  "+
					"	from licence.mst_challan_master a,licence.challan_head_details b,(select x.*, a.divcode,                               "+
					"	a.tcode from (SELECT core_district_id, int_app_id FROM licence.fl2_2b_2d_fl2a_csd_20_21 where vch_mobile_no='"+ResourceUtil.getUserNameAllReq()+"')x , "+
					"	licence.treasury a where a.district_id=x.core_district_id)c where a.vch_register_flag='N' and a.vch_division=c.divcode and a.vch_treasury=tcode    "+
					"	and a.vch_challan_id=b.vch_challan_id and a.vch_trn_status='SUCCESS' and a.dat_created_date>'2020-03-31' and "+
				    " vch_rajaswa_head in ('3900105010000','3900103010000') and g6_head in (2,3) and a.vch_challan_id  not in " +
				    "(select COALESCE(excise_duty_challan_id,'0') from fl2d.application_csd_permit_mst_20_21)" ;
									

		 
		 
		System.out.println("ChallanList2=" + selQr);
		 pstmt=null;
		 rs=null;
			pstmt = con.prepareStatement(selQr);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				////System.out.println("01="+rs.getString("vch_challan_id"));
				if(!rs.getString("vch_challan_id").trim().equalsIgnoreCase("")){
					////System.out.println("3");
					exciseduty = new SelectItem();
					exciseduty.setLabel(rs.getString("vch_challan_id")+"-"+rs.getString("dat_created_date")+"-"+rs.getString("vch_depositor_name"));
					exciseduty.setValue(rs.getString("vch_challan_id"));
					excisedutylist.add(exciseduty);
				}
				
			}
		act.setExcise_duty_list(excisedutylist);
		
		 selQr = " select distinct a.vch_challan_id,a.dat_created_date,a.vch_division,a.vch_treasury,a.vch_depositor_name                  "+
					"	from licence.mst_challan_master a,licence.challan_head_details b,(select x.*, a.divcode,                               "+
					"	a.tcode from (SELECT core_district_id, int_app_id FROM licence.fl2_2b_2d_fl2a_csd_20_21 where vch_mobile_no='"+ResourceUtil.getUserNameAllReq()+"')x , "+
					"	licence.treasury a where a.district_id=x.core_district_id)c where a.vch_register_flag='N' and a.vch_division=c.divcode and a.vch_treasury=tcode    "+
					"	and a.vch_challan_id=b.vch_challan_id and a.vch_trn_status='SUCCESS' and a.dat_created_date>'2020-03-31' and "+
					" vch_rajaswa_head in ('3900800060000') and g6_head in (6) and a.vch_challan_id not in " +
					" (select COALESCE(scanning_fee_challan_no,'0') from fl2d.application_csd_permit_mst_20_21)" ;
										

			 
			 
			System.out.println("ChallanList1=" + selQr);
			 pstmt=null;
			 rs=null;
				pstmt = con.prepareStatement(selQr);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					////System.out.println("01="+rs.getString("vch_challan_id"));
					if(!rs.getString("vch_challan_id").trim().equalsIgnoreCase("")){
						////System.out.println("2");
						scanFee = new SelectItem();
						scanFee.setLabel(rs.getString("vch_challan_id")+"-"+rs.getString("dat_created_date")+"-"+rs.getString("vch_depositor_name"));
						scanFee.setValue(rs.getString("vch_challan_id"));
						scanFeelist.add(scanFee);
					}
					
				}
			act.setScanning_fee_list(scanFeelist);
			
			 selQr = " select distinct a.vch_challan_id,a.dat_created_date,a.vch_division,a.vch_treasury,a.vch_depositor_name                  "+
						"	from licence.mst_challan_master a,licence.challan_head_details b,(select x.*, a.divcode,                               "+
						"	a.tcode from (SELECT core_district_id, int_app_id FROM licence.fl2_2b_2d_fl2a_csd_20_21 where vch_mobile_no='"+ResourceUtil.getUserNameAllReq()+"')x , "+
						"	licence.treasury a where a.district_id=x.core_district_id)c where a.vch_register_flag='N' and a.vch_division=c.divcode and a.vch_treasury=tcode    "+
						"	and a.vch_challan_id=b.vch_challan_id and a.vch_trn_status='SUCCESS' and a.dat_created_date>'2020-03-31' and "+
						" vch_rajaswa_head in ('003900103010000', '003900105010000') and g6_head in (09) and a.vch_challan_id not in " +
						"(select COALESCE(corona_fee_challan_no,'0') from fl2d.application_csd_permit_mst_20_21)" ;				

				 
				 
				System.out.println("ChallanList1=" + selQr);
				 pstmt=null;
				 rs=null;
					pstmt = con.prepareStatement(selQr);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						////System.out.println("01="+rs.getString("vch_challan_id"));
						if(!rs.getString("vch_challan_id").trim().equalsIgnoreCase("")){
							////System.out.println("2");
							coronaFee = new SelectItem();
							coronaFee.setLabel(rs.getString("vch_challan_id")+"-"+rs.getString("dat_created_date")+"-"+rs.getString("vch_depositor_name"));
							coronaFee.setValue(rs.getString("vch_challan_id"));
							coronaFeelist.add(coronaFee);
						}
						
					}
				act.setCorona_fee_list(coronaFeelist);
		act.setFlg(true);

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
	public void ChallanList1(Import_Permit_Challan_CSD_Action act) {
		////System.out.println("-/-");
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
			selQr =" select chalan_no,chalan_date,amount from bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no in  ( SELECT DISTINCT "+
					" CASE WHEN head_type like 'FL2D%' then chalan_no else '' end as import_fee_challan_no  " +
			" FROM bwfl_license.chalan_deposit_bwfl_fl2d   WHERE unit_id='"+act.getInt_id()+"' AND unit_type='FL2D' " +
			" EXCEPT   " +
			" select DISTINCT import_fee_challan_no from bwfl_license.import_permit    " +
			" where bwfl_id='"+act.getInt_id()+"' and bwfl_type=99 and district_id='"+act.getDistrict_id()+"' ) and  unit_id="+act.getInt_id()+" AND unit_type='FL2D'";
		
			////System.out.println("ChallanList=" + selQr);
			pstmt = con.prepareStatement(selQr);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				////System.out.println("01="+rs.getString("chalan_no"));
				if(!rs.getString("chalan_no").trim().equalsIgnoreCase("")){
					
					ImportChallan = new SelectItem();
					ImportChallan.setLabel(rs.getString("chalan_no")+"-"+rs.getString("amount")+"-"+rs.getString("chalan_date"));
					ImportChallan.setValue(rs.getString("chalan_no"));
					ImportChallanList.add(ImportChallan);
				}	
			}
			act.setImportChallanList(ImportChallanList);
			selQr = " select chalan_no,chalan_date,amount from bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no in  ( SELECT DISTINCT "+
					" CASE WHEN head_type like 'Special%' then chalan_no else '' end as spcl_fee_challan_no  "+
			" FROM bwfl_license.chalan_deposit_bwfl_fl2d   WHERE unit_id='"+act.getInt_id()+"' AND unit_type='FL2D'  EXCEPT "+
			" select spcl_fee_challan_no from bwfl_license.import_permit where bwfl_id='"+act.getInt_id()+"' and bwfl_type=99 and district_id='"+act.getDistrict_id()+"' ) and  unit_id="+act.getInt_id()+" AND unit_type='FL2D'";

			
			////System.out.println("ChallanList1=" + selQr);
			 pstmt=null;
			 rs=null;
				pstmt = con.prepareStatement(selQr);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					////System.out.println("01="+rs.getString("chalan_no"));
					if(!rs.getString("chalan_no").trim().equalsIgnoreCase("")){
						////System.out.println("2");
						SpclChallan = new SelectItem();
						SpclChallan.setLabel(rs.getString("chalan_no")+"-"+rs.getString("amount")+"-"+rs.getString("chalan_date"));
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
public boolean getChallanBalance(Import_Permit_Challan_CSD_Action act,double import_fee,double special_fee) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flg1=false;
		boolean flg2=false;
		boolean flg=false;
		String sql="";
		sql = " select COALESCE(amount,0.0)import_amount " +
			" FROM bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no='"+act.getImport_fee_challan_no()+"' " 
			+ " and unit_id="+act.getInt_id()+" AND unit_type='FL2D'";
////System.out.println("getChallanImportFee1--"+sql);
		try {
			con = ConnectionToDataBase.getConnection();
			//con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				 if(import_fee>rs.getDouble("import_amount")){
					 
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Import Fee for selected permits is greater then your selected Challan"));
				 }else {
					 flg1=true;
					 //System.out.println(import_fee+"-import fee is "+rs.getDouble("import_amount"));
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
				 if(special_fee>rs.getDouble("spcl_amount")){
					 
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Special Fee for selected permits is greater then your selected Challan"));
				 }else {
					 flg2=true;
					 //System.out.println(special_fee+"-import fee is "+rs.getDouble("spcl_amount"));
				 }
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
public boolean getChallanBalanceBWFL(Import_Permit_Challan_CSD_Action act,double import_fee,double special_fee) {
	
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
//System.out.println("getChallanImportFee-"+sql);
	try {
		con = ConnectionToDataBase.getConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			 if(import_fee>rs.getDouble("import_amount")){
				 
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Import Fee for selected permits is greater then your selected Challan"));
			 }else {
				 flg1=true;
				 //System.out.println(import_fee+"-import fee is "+rs.getDouble("import_amount"));
			 }
		}
		ps=null;
		rs=null;
		sql = " select COALESCE(amount,0.0)spcl_amount " +
				" FROM bwfl_license.chalan_deposit_bwfl_fl2d where chalan_no='"+act.getSpcl_fee_challan_no()+"' " 
				+ " and unit_id="+act.getInt_id()+" AND unit_type= "
				+ " CASE WHEN 1="+act.getVch_license_type()+" THEN 'BWFL2A' WHEN 2="+act.getVch_license_type()+" THEN 'BWFL2B' WHEN 3="+act.getVch_license_type()+" THEN 'BWFL2C' WHEN 4="+act.getVch_license_type()+" THEN 'BWFL2D' END ";
		//System.out.println("ChallanSpcl="+sql);
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if (rs.next()) {
			 if(special_fee>rs.getDouble("spcl_amount")){
				 
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Special Fee for selected permits is greater then your selected Challan"));
			 }else {
				 flg2=true;
				 //System.out.println(special_fee+"-import fee is "+rs.getDouble("spcl_amount"));
			 }
		}
		//System.out.println("flg1="+flg1+" flg2="+flg2);
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
	public boolean update(Import_Permit_Challan_CSD_Action act){


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
		 query="update fl2d.application_csd_permit_mst_20_21 set import_fee_challan_no='"+act.getImport_fee_challan_no()+
					"' ,spcl_fee_challan_no='"+act.getSpcl_fee_challan_no()+"', excise_duty_challan_id='"+act.getExcise_duty()+"', " +
					" scanning_fee_challan_no='"+act.getScanning_fee()+"', corona_fee_challan_no='"+act.getCorona_fee()+"' where app_id=?";
		 //System.out.println("update="+query);
		conn = ConnectionToDataBase.getConnection();
		conn.setAutoCommit(false);
		if(act.getDatalist().size()>0){
			for(int i=0;i<act.getDatalist().size();i++){
				
			Import_Permit_Challan_CSD_DT dt = (Import_Permit_Challan_CSD_DT) act.getDatalist().get(i);
			//System.out.println("dt.getApp_id()="+dt.getApp_id());
			//System.out.println("dt.isCheckbox()="+dt.isCheckbox());
				if(dt.isCheckbox()){
				saveStatus=0;
				//System.out.println("dt.isCheckbox()="+dt.isCheckbox());
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, dt.getApp_id());
				saveStatus = pstmt.executeUpdate();
				//System.out.println("status=="+saveStatus);
				}
			 	
			}
		}
	
	
		
		if (saveStatus > 0) {
			conn.setAutoCommit(true);
			flg=true;
			act.setFlg(false);
			//System.out.println("flg="+act.isFlg());
			act.setDatalist(this.datalist(act));
		//	navigation=act.getLogin_type();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Application Completed Successfully "));
			//ResourceUtil.addErrorMessage(Constants.SAVED_SUCESSFULLY,Constants.SAVED_SUCESSFULLY);
			//act.reset();

		} else {
			flg=false;
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
}
