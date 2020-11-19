package com.mentor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.mentor.action.BWFL_RecieptAction_20_21;
import com.mentor.connectiondb.ConnectionToDataBase;
import com.mentor.datatable.BWFL_RecieptDatatable;
import com.mentor.datatable.BWFL_Reciept_Details_Datatable;
import com.mentor.datatable.Reciept_List_Datatable;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;

public class BWFL_RecieptImpl_20_21 {
	public void getDetails(BWFL_RecieptAction_20_21 ac){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = 	" SELECT * FROM bwfl.registration_of_bwfl_lic_holder_20_21 WHERE " +
						" mobile_number='"+ResourceUtil.getUserNameAllReq().trim()+"' " +
						" AND vch_approval='V' ";
		try{
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			System.out.println("---------login---hidden----"+query);
			if(rs.next()){
				ac.setLicense_type(rs.getInt("vch_license_type"));
				ac.setBwfl_int_brewery_id(rs.getString("int_brewery_id"));
				ac.setBwfl_int_distillery_id(rs.getString("int_distillery_id"));
				ac.setBwfl_int_id_pk(rs.getInt("int_id"));			
				ac.setBwfl_vch_brewery_contact_number(rs.getString("vch_brewery_contact_number"));
				ac.setBwfl_vch_distillery_contact_number(rs.getString("vch_distillery_contact_number"));							
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)ps.close();
				if (rs != null)rs.close();
				if (con != null)con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public ArrayList getDetails_BWFL(BWFL_RecieptAction_20_21 ac){
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int j=1;
		

		
		String selQr = 	" SELECT DISTINCT int_distillery_id ,permitno ,permitdt,gatepass_no,licence_no " +
						" FROM bwfl_license.mst_bottling_plan_20_21 " +
						" WHERE  int_distillery_id="+ac.getBwfl_int_id_pk()+" and " +
						" int_planned_bottles>(recieved_bottles+COALESCE(breakage::numeric,0)) and scan_upload_flag='F' " +
						" ORDER BY permitdt, gatepass_no ";
		
		System.out.println("getDetails_BWFL="+selQr);
		try{ 
			con= ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(selQr);
			rs = ps.executeQuery();
			 
			while(rs.next()){
					BWFL_RecieptDatatable dt = new BWFL_RecieptDatatable();
					dt.setDate(rs.getDate("permitdt"));
					dt.setPassno(rs.getString("gatepass_no"));
					//dt.setSource(rs.getString("int_brewery_id")+"-"+rs.getString("int_distillery_id"));
					dt.setBrewery_id(rs.getString("int_distillery_id"));
					dt.setDistillery_id(rs.getString("int_distillery_id"));
					dt.setInt_id(rs.getInt("int_distillery_id"));
					dt.setSno(j);
					dt.setPermitno(rs.getString("permitno"));
					dt.setLicenseNmbr(rs.getString("licence_no"));
					
					
					j++;
					list.add(dt);				
			}
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)ps.close();
				if (rs != null)rs.close();
				if (con != null)con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList getFormDetails(BWFL_RecieptAction_20_21 ac, BWFL_RecieptDatatable dt){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		int j=1;
				
		
		String selQr = 	" SELECT DISTINCT a.int_distillery_id, a.int_brand_id, a.int_pack_id, a.int_quantity,a.permitno,c.brand_name," +
						" b.vch_brewery_contact_number, b.vch_distillery_contact_number , " +
						" a.int_planned_bottles-COALESCE(a.recieved_bottles,0) as bottles, " +
						" a.int_boxes-COALESCE(a.recieved_boxes,0) as boxes, a.int_liquor_type, a.vch_license_type,  " +
						" a.plan_dt, a.licence_no, a.cr_date, a.entry_no_of_bottle_per_case, a.gatepass_no, " +
						" a.finalized_date, a.finalized_flag, entry_no_of_bottle_per_case as box_size,pk.cesh" +
						//" d.box_size " +
						" FROM bwfl_license.mst_bottling_plan_20_21 a ,bwfl.registration_of_bwfl_lic_holder_20_21 b,distillery.brand_registration_20_21 c," +
						" distillery.packaging_details_20_21 pk" +
						//" /distillery.box_size_details d " +
						" WHERE a.int_distillery_id=? and  c.brand_id=a.int_brand_id and c.int_bwfl_id=a.unit_id and  " +
						"a.int_distillery_id=b.int_id " +
						//"AND a.int_quantity=d.qnt_ml_detail" +
						" and a.gatepass_no='"+dt.getPassno()+"' and pk.brand_id_fk=c.brand_id and pk.package_id=a.int_pack_id order by a.cr_date ";
		try{
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(selQr);
			ps.setInt(1, Integer.parseInt(dt.getDistillery_id()));
			 
			System.out.println("dt.getDistillery_id()------------------"+dt.getDistillery_id());
			
			System.out.println("selQr------------------"+selQr);
			
			
			
			rs = ps.executeQuery();
			while(rs.next()){
				BWFL_Reciept_Details_Datatable dt2 = new BWFL_Reciept_Details_Datatable();
				dt2.setBrewery_id(rs.getInt("int_distillery_id"));
				dt2.setInt_brand_id(rs.getInt("int_brand_id"));
				dt2.setInt_pack_id(rs.getInt("int_pack_id"));
				dt2.setInt_quantity(rs.getInt("int_quantity"));
				dt2.setInt_planned_bottles(rs.getInt("bottles"));
				dt2.setInt_boxes(rs.getInt("boxes"));
				dt2.setInt_liquor_type(rs.getInt("int_liquor_type"));
				dt2.setVch_license_type(rs.getString("vch_license_type"));
				dt2.setPlan_dt(rs.getDate("plan_dt"));
				dt2.setLicence_no(rs.getString("licence_no"));
				dt2.setCr_date(rs.getDate("cr_date"));
				dt2.setFinalized_date(rs.getDate("finalized_date"));
				dt2.setFinalized_flag(rs.getString("finalized_flag"));
				dt2.setSize(rs.getInt("box_size"));
				dt2.setReceived_from_usr(rs.getString("vch_distillery_contact_number"));
				dt2.setPermitno(rs.getString("permitno"));
				dt2.setNoOfBottle(rs.getInt("bottles"));
				dt2.setBrand(rs.getString("brand_name"));
				//dt2.setPassno(Integer.parseInt(dt.getPassno()));
				dt2.setEntryNmbrOfSize_dt(rs.getString("entry_no_of_bottle_per_case"));
				dt2.setGatepassNmbr_dt(rs.getString("gatepass_no"));
				dt2.setCesh(rs.getDouble("cesh"));
				/*if(rs.getString("vch_brewery_contact_number")!=null)
				{
				dt2.setReceived_from_usr(rs.getString("vch_brewery_contact_number"));
				}else if(rs.getString("vch_distillery_contact_number")!=null){
					dt2.setReceived_from_usr(rs.getString("vch_distillery_contact_number"));
				}*/
				dt2.setSlno(j);
				list.add(dt2);
				j++;
				/*
				if(rs.getString("vch_brewery_contact_number") == null){
					ac.setBreweryFlag(true);
					ac.setDistilleryFlag(false);
					ac.setBrewerynm(this.getBreweryName(rs.getInt("int_brewery_id")));
				}else{
					ac.setBreweryFlag(false);
					ac.setDistilleryFlag(true);
					ac.setDistillerynm(this.getDistilleryName(rs.getInt("int_distillery_id")));
				}*/
			}
		}catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
		} finally {
			try {
				if (ps != null)ps.close();
				if (rs != null)rs.close();
				if (con != null)con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public String getLicenseType(String id){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name="";
		String query="Select * from distillery.imfl_type where id='"+id+"'";
		try{
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()){
				name = rs.getString("description");
			}
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)ps.close();
				if (rs != null)rs.close();
				if (con != null)con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return name;
	}
	public String getDistilleryName(int id){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name="";
		String query="Select * from public.dis_mst_pd1_pd2_lic where int_app_id_f="+id;
		try{
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()){
				name = rs.getString("vch_undertaking_name");
			}
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)ps.close();
				if (rs != null)rs.close();
				if (con != null)con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return name;
	}
	public String getBreweryName(int idd){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name="";
		String query="Select * from public.bre_mst_b1_lic where vch_app_id_f="+idd;
		try{
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()){
				name = rs.getString("brewery_nm");
			}
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)ps.close();
				if (rs != null)rs.close();
				if (con != null)con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return name;
	}
	
	public void saveDetail(BWFL_RecieptAction_20_21 ac ){
		Connection con = null;
		PreparedStatement ps = null,ps1=null,ps2=null,ps3=null,ps4=null,ps5=null;
		ResultSet rs = null;
		int save=0;
		int maxid=dutymaxId()+1;
					
		try{
			con = ConnectionToDataBase.getConnection();
			con.setAutoCommit(false);
			
			
	for (int i = 0; i < ac.getView_formData().size(); i++) {
				
		BWFL_Reciept_Details_Datatable dt = (BWFL_Reciept_Details_Datatable) ac.getView_formData().get(i);
		
 		String query = 	" INSERT INTO bwfl_license.mst_receipt_20_21(int_bwfl_id, int_brand_id, int_pack_id, " +
				" int_quantity, int_planned_bottles, int_planned_boxes, int_liquor_type, vch_license_type, " +
				" plan_dt, licence_no, cr_date, received_by, remarks, receiving_date, " +
				" received_liqour, received_from_usr, seq, dispatch_36, box_size, int_recieved_bottles, breakage,gatepass,permitno) VALUES " +
				" (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,'"+dt.getPermitno()+"')";

		
  String query5 = " SELECT * FROM bwfl_license.receipt_stock_20_21 where int_brand_id="+dt.getInt_brand_id()+" " +
				" AND int_pckg_id="+dt.getInt_pack_id()+" AND int_bwfl_id="+ac.getBwfl_int_id_pk()+" " +
				" AND vch_lic_no='"+dt.getLicence_no()+"' and permit='"+dt.getPermitno()+"'";

   String query3 = " INSERT INTO bwfl_license.receipt_stock_20_21(int_bwfl_id, int_dispatched_botls, " +
				" int_dispatched_cases, int_stock_botls,   vch_lic_no, vch_lic_type, " +
				" int_brand_id, int_pckg_id,permit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )";

		
   String queryy = " UPDATE bwfl_license.receipt_stock_20_21 SET int_stock_botls=int_stock_botls+?   " +
				" WHERE int_brand_id="+dt.getInt_brand_id()+" AND int_pckg_id="+dt.getInt_pack_id()+" " +
				" AND int_bwfl_id="+ac.getBwfl_int_id_pk()+" AND vch_lic_no='"+dt.getLicence_no()+"' and permit='"+dt.getPermitno()+"'";


   String updtQr = " UPDATE bwfl_license.mst_bottling_plan_20_21 " +
				" SET recieved_bottles=COALESCE(recieved_bottles,0)+?  " +
				" WHERE int_brand_id="+dt.getInt_brand_id()+" AND int_pack_id="+dt.getInt_pack_id()+" " +
				" AND int_distillery_id="+ac.getBwfl_int_id_pk()+" AND licence_no='"+dt.getLicence_no()+"' " +
				" AND plan_dt='"+dt.getPlan_dt()+"' AND int_quantity='"+dt.getInt_quantity()+"' and permitno='"+dt.getPermitno()+"' ";


		//System.out.println("updtQr------------"+updtQr);
	
			ps = con.prepareStatement(query);
			System.out.println("query="+query);
			ps.setInt(1, ac.getBwfl_int_id_pk());			
			ps.setInt(2, dt.getInt_brand_id());
			ps.setInt(3, dt.getInt_pack_id());
			ps.setInt(4, dt.getInt_quantity());
			ps.setInt(5, dt.getInt_planned_bottles());
			ps.setInt(6, dt.getInt_boxes());
			ps.setInt(7, dt.getInt_liquor_type());
			ps.setString(8, dt.getVch_license_type());
			ps.setDate(9, Utility.convertUtilDateToSQLDate(dt.getPlan_dt()));
			ps.setString(10, dt.getLicence_no());
			ps.setDate(11, Utility.convertUtilDateToSQLDate(new Date()));
			ps.setString(12, ac.getRecievedby());
			ps.setString(13, ac.getRemarks());			
			ps.setDate(14, Utility.convertUtilDateToSQLDate(ac.getReceiving_date()));
			ps.setInt(15, 0);
			ps.setString(16, dt.getReceived_from_usr().trim());
			ps.setInt(17, this.mst_receipt_20_21()+i);
			ps.setInt(18,0);
			ps.setInt(19,Integer.parseInt(dt.getEntryNmbrOfSize_dt()));
			ps.setInt(20, (dt.getNoOfBottle()));
			ps.setInt(21, dt.getRecivecases());
			ps.setString(22, dt.getGatepassNmbr_dt());
			save = ps.executeUpdate();	
			
			System.out.println("first status-----------------"+save);
			
			if(save>0){
				save=0;
				System.out.println("query5="+query5);
				ps2=con.prepareStatement(query5);
				rs = ps2.executeQuery();				
				if(rs.next()){
					System.out.println("queryy="+queryy);
					ps1 = con.prepareStatement(queryy);
					ps1.setInt(1, dt.getInt_planned_bottles());
				 		
					save = ps1.executeUpdate();
					System.out.println("second status-------update condition----------"+save);
					
				}else{				
					System.out.println("query3="+query3);
					ps3 = con.prepareStatement(query3);
					ps3.setInt(1, ac.getBwfl_int_id_pk());
					ps3.setInt(2,0);					
					ps3.setInt(3,0);
					ps3.setInt(4,dt.getInt_planned_bottles());
				 		
					ps3.setString(5,dt.getLicence_no());
					ps3.setString(6,dt.getVch_license_type());
					ps3.setInt(7, dt.getInt_brand_id());
					ps3.setInt(8,dt.getInt_pack_id());
					ps3.setString(9,dt.getPermitno());
					save = ps3.executeUpdate();
					System.out.println("secont status--------insert condition---------"+save);
				}
			}
			if(save>0)
			{System.out.println("updtQr="+updtQr);
				save=0;
				ps4 = con.prepareStatement(updtQr);

				ps4.setInt(1, dt.getInt_planned_bottles());
			 	save = ps4.executeUpdate();
				System.out.println("fourth status-------update----------"+save);
				
			}
			
			if(save > 0 && dt.getRecivecases()>0){
				save = 0;
				String sql3 = "INSERT INTO distillery.duty_register_19_20( "
						+ "int_id, int_bwfl_id, date_crr_date, vch_duty_type, " +
						" int_quantity, int_value, vch_description, int_distillery_id,gatepass)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";

				ps5 = con.prepareStatement(sql3);
				ps5.setInt(1, maxid);
				ps5.setInt(2, ac.getBwfl_int_id_pk());
				ps5.setDate(3,new java.sql.Date(System.currentTimeMillis()));
				
				if(ac.getLicense_type()==1 || ac.getLicense_type()==3){
					ps5.setString(4, "BWFL2A_SOCIAL_DUTY");
				}
				else if(ac.getLicense_type()==2 || ac.getLicense_type()==4){
					ps5.setString(4, "BWFL2B_SOCIAL_DUTY");
				}
				
				ps5.setDouble(5, dt.getRecivecases());
				
				ps5.setDouble(6, dt.getRecivecases()*dt.getCesh());
				
				ps5.setString(7, "Consideration Fee For Reciept Breakage On Gatepass "+ dt.getGatepassNmbr_dt());
				ps5.setInt(8, 0);
				ps5.setString(9, dt.getGatepassNmbr_dt());
				save = ps5.executeUpdate();
			
				}
	        maxid++;
	}
			if(save>0){
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Record Saved","Record Saved"));
				 con.commit();
				ac.reset();
			}else{
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Not Saved","Not Saved"));
				con.rollback();
			}
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)ps.close();				
				if (con != null)con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public int mst_receipt_20_21(){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "Select max(seq) as id from bwfl_license.mst_receipt_20_21";
		int id=0;
		try{
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getInt("id");
			}
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)ps.close();
				if (rs != null)rs.close();
				if (con != null)con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return id+1;
	}
	public int receipt_stock_20_21(){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "Select max(int_id_pk) as id from bwfl_license.receipt_stock_20_21";
		int id=0;
		try{
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getInt("id");
			}
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)ps.close();
				if (rs != null)rs.close();
				if (con != null)con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return id+1;
	}
	
	public ArrayList getRecieptsList(BWFL_RecieptAction_20_21 ac){
		ArrayList list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=1;
		/*String query = 		" SELECT a.int_bwfl_id, int_brand_id, a.int_pack_id,b.brand_name, a.int_quantity, a.permitno,a.int_planned_bottles,a.gatepass, " +
							" a.int_planned_boxes, a.int_liquor_type, a.vch_license_type, a.plan_dt, a.licence_no, a.cr_date, a.received_by, " +
							" a.remarks, a.receiving_date, a.received_liqour, a.received_from_usr, a.seq, a.dispatch_36," +
							" a.int_recieved_bottles,a.int_recieved_boxes  " +
							" FROM bwfl_license.mst_receipt_20_21 a ,distillery.brand_registration_20_21 b, bwfl_license.mst_bottling_plan_20_21 aa" +
							" where " +
							//"a.int_bwfl_id="+ac.getBwfl_int_id_pk()+" and " +
									" b.brand_id=a.int_brand_id and a.int_pack_id=b.int_pack_id" +
									//" and b.int_bwfl_id=a.int_bwfl_id" +
									"";*/
   String query="select a.gatepass_no as gatepass,a.licence_no,a.permitno,a.int_brand_id,a.int_pack_id,a.int_planned_bottles," +
				"b.received_by,b.receiving_date,b.int_recieved_bottles,(select brand_name from distillery.brand_registration_20_21 c where c.brand_id=a.int_brand_id) as brand_name" +
				",(select package_name from distillery.packaging_details_20_21 ac where ac.package_id=a.int_pack_id) from " +
				"bwfl_license.mst_receipt_20_21 b,bwfl_license.mst_bottling_plan_20_21 a where int_distillery_id=int_bwfl_id and" +
				" a.int_brand_id=b.int_brand_id and a.int_pack_id=b.int_pack_id and b.permitno=b.permitno " + 
				" and a.gatepass_no=b.gatepass  and  a.int_distillery_id='"+ac.getBwfl_int_id_pk()+"' order by a.plan_dt desc";
		
		
System.out.println("query11="+query);
/*select gatepass_no,licence_no,permitno,int_brand_id,int_pack_id,int_planned_bottles,
received_by,receiving_date,int_recieved_bottles from 
bwfl_license.mst_receipt_20_21 b,bwfl_license.mst_bottling_plan_20_21 a where int_distillery_id=int_bwfl_id and
 int_brand_id=b.int_brand_id and a.int_pack_id=b.int_pack_id and b.permitno=permitno;

	*/	
		//System.out.println("last datatable------------------"+query);
		try{
			con = ConnectionToDataBase.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				Reciept_List_Datatable dt = new Reciept_List_Datatable();
				dt.setSno(i);
				dt.setLicno(rs.getString("licence_no"));
				dt.setRecievedby(rs.getString("received_by"));
				dt.setPassno(rs.getString("gatepass"));
				dt.setRecievingdate(rs.getDate("receiving_date"));
				dt.setPlannedBottlesdt(rs.getInt("int_planned_bottles"));
				dt.setRecievedBottlesdt(rs.getInt("int_recieved_bottles"));
				dt.setBrand(rs.getString("brand_name"));
				dt.setPermitnodisp(rs.getString("permitno"));
				list.add(dt);
				i++;
			}
		}catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));	
			
		} finally {
			try {
				if (ps != null)ps.close();
				if (rs != null)rs.close();
				if (con != null)con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	public int dutymaxId() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = " SELECT max(int_id) as id FROM distillery.duty_register_19_20 ";
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
	
}
