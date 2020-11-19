package com.mentor.datatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.model.SelectItem;

import com.mentor.impl.RollOverOfNonRenewalBrandImpl;
import com.mentor.resource.ConnectionToDataBase;

public class rollover_Stock_20_21_fl2_dt {
	//----------------------------------Table1 List variable--------------------------by arvind verma ------------------------------------------------ 
	private int slno;
	private String brand;
	private int 	size;
	private int stockbox;
	private int stockbottle;
	private int rolloverbox;
	private int  rolloverbottles;
	private String validInvalid;
	private String etin_new;
	private String old_etin;
	private String type_old;
	public String getType_old() {
		return type_old;
	}
	public void setType_old(String type_old) {
		this.type_old = type_old;
	}
	public String getUnit_id_old() {
		return unit_id_old;
	}
	public void setUnit_id_old(String unit_id_old) {
		this.unit_id_old = unit_id_old;
	}

	private String unit_id_old;
	public String getOld_etin() {
		return old_etin;
	}
	public void setOld_etin(String old_etin) {
		this.old_etin = old_etin;
	}

	public ArrayList list=new ArrayList();
	
	public ArrayList getList() {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		SelectItem item=new SelectItem();
		item.setLabel("select");
		item.setValue("0");
		String unit_type="";
		int id=0;
		
		
		
		try{
			RollOverOfNonRenewalBrandImpl impl= new RollOverOfNonRenewalBrandImpl();
			unit_type=impl.checkType(this.getOld_etin());
			id=impl.getUnitId(this.getOld_etin(), unit_type);
			this.setType_old(unit_type);
			this.setUnit_id_old(String.valueOf(id));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String sql=
		
		
		                                                                            
	"	select  unit_name,brand_name,code_generate_through,package_name from                       "+
	"	(select distinct  a.unit_name,a.brand_name,b.code_generate_through,b.package_name          "+
	"	from distillery.brand_registration_20_21 a ,                                               "+
	"	distillery.packaging_details_20_21 b                                                       "+
	"	where a.brand_id=b.brand_id_fk and a.distillery_id="+id+" and a.unit_type='"+unit_type+"'         "+
	"	union                                                                                      "+
	"	select distinct   a.unit_name,a.brand_name,b.code_generate_through,b.package_name          "+
	"	from distillery.brand_registration_20_21 a ,                                               "+
	"	distillery.packaging_details_20_21 b                                                       "+
	"	where a.brand_id=b.brand_id_fk and a.brewery_id="+id+" and a.unit_type='"+unit_type+"'         "+
	"	union                                                                                      "+
	"	select distinct  a.unit_name,a.brand_name,b.code_generate_through,b.package_name           "+
	"	from distillery.brand_registration_20_21 a ,                                               "+
	"	distillery.packaging_details_20_21 b                                                       "+
	"	where a.brand_id=b.brand_id_fk and a.int_bwfl_id="+id+" and a.unit_type='"+unit_type+"'         "+
	"	union                                                                                      "+
	"	select distinct   a.unit_name,a.brand_name,b.code_generate_through,b.package_name          "+
	"	from distillery.brand_registration_20_21 a ,                                               "+
	"	distillery.packaging_details_20_21 b                                                       "+
	"	where a.brand_id=b.brand_id_fk and a.int_fl2d_id="+id+" and a.unit_type='"+unit_type+"'         "+")x         ";
		
		
		String sqlfl2d="select distinct   a.unit_name,a.brand_name,b.code_generate_through,b.package_name          "+
				"	from distillery.brand_registration_20_21 a ,                                               "+
				"	distillery.packaging_details_20_21 b                                                       "+
				"	where a.brand_id=b.brand_id_fk and a.int_fl2d_id >0  order by brand_name ";	
					
					
					try{
						conn=ConnectionToDataBase.getConnection();
						
						if(unit_type.equalsIgnoreCase("OtherUnit")){
						pstmt=conn.prepareStatement(sqlfl2d);
						}
						else{
							pstmt=conn.prepareStatement(sql);
						}
		
		/*try{
			conn=ConnectionToDataBase.getConnection();
			pstmt=conn.prepareStatement(sql);*/
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				item=new SelectItem();
				item.setLabel(rs.getString("brand_name")+" "+rs.getString("package_name")+" "+rs.getString("code_generate_through"));
				item.setValue(rs.getString("code_generate_through"));
				list.add(item);
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			
			try{
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
				if(rs!=null)rs.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public String getEtin_new() {
		return etin_new;
	}
	public void setEtin_new(String etin_new) {
		this.etin_new = etin_new;
	}
	public String getValidInvalid() {
		return validInvalid;
	}
	public void setValidInvalid(String validInvalid) {
		this.validInvalid = validInvalid;
	}
	public int getSlno() {
		return slno;
	}
	public void setSlno(int slno) {
		this.slno = slno;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getStockbox() {
		return stockbox;
	}
	public void setStockbox(int stockbox) {
		this.stockbox = stockbox;
	}
	public int getStockbottle() {
		return stockbottle;
	}
	public void setStockbottle(int stockbottle) {
		this.stockbottle = stockbottle;
	}
	public int getRolloverbox() {
		rolloverbottles=size*rolloverbox;
		return rolloverbox;
	}
	public void setRolloverbox(int rolloverbox) {
		this.rolloverbox = rolloverbox;
	}
	public int getRolloverbottles() {
		return rolloverbottles;
	}
	public void setRolloverbottles(int rolloverbottles) {
		this.rolloverbottles = rolloverbottles;
	}
	
	//-----------------------brand and pack id--------------------------------------
	private int brand_id;
	private int package_id;
	public int getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}
	public int getPackage_id() {
		return package_id;
	}
	public void setPackage_id(int package_id) {
		this.package_id = package_id;
	}
	
	//-------------------------------------------------------------flag--------------------------------------------------------------
	private boolean btflag=true;
	private boolean finl_flg;
	
	public boolean isFinl_flg() {
		return finl_flg;
	}
	public void setFinl_flg(boolean finl_flg) {
		this.finl_flg = finl_flg;
	}
	public boolean isBtflag() {
		return btflag;
	}
	public void setBtflag(boolean btflag) {
		this.btflag = btflag;
	}
	//------------------------------------------------------------------------------------------------------------------------------------
	private String Etin_csv;
	private String Casecose_csv;
	private int Slno_csv;
	public String getEtin_csv() {
		return Etin_csv;
	}
	public void setEtin_csv(String etin_csv) {
		Etin_csv = etin_csv;
	}
	public String getCasecose_csv() {
		return Casecose_csv;
	}
	public void setCasecose_csv(String casecose_csv) {
		Casecose_csv = casecose_csv;
	}
	public int getSlno_csv() {
		return Slno_csv;
	}
	public void setSlno_csv(int slno_csv) {
		Slno_csv = slno_csv;
	}
	
	
	private Date date;
	private String challanname;
	private int srNo;
	
	public int getSrNo() {
		return srNo;
	}
	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getChallanname() {
		return challanname;
	}

	public void setChallanname(String challanname) {
		this.challanname = challanname;
	}
	
	
	private boolean payment_flg;

	
	public boolean isPayment_flg() {
		return payment_flg;
	}
	public void setPayment_flg(boolean payment_flg) {
		this.payment_flg = payment_flg;
	}
	
	
	
	
	
	private int seq;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}	
	
	
	
	
	
private boolean com_flg;
    
    public boolean isCom_flg() {
		return com_flg;
	}
	public void setCom_flg(boolean com_flg) {
		this.com_flg = com_flg;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
