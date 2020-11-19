package com.mentor.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.richfaces.component.UIDataTable;

import com.mentor.datatable.Application_Csd_dt;
import com.mentor.datatable.ImportChallanForPermitFL2D_20_21DT;
import com.mentor.datatable.ImportChallanForPermitFL2D_20_21DT;
import com.mentor.datatable.Import_Permit_FL2D_20_21DT;
import com.mentor.impl.ImportChallanForPermitFL2D_20_21Impl;

public class ImportChallanForPermitFL2D_20_21Action {

	private String hidden;
	private int int_id;
	private int unit_id;
	private int district_id;
	private String login_type;
	private int vch_license_type;
	private String import_fee_challan_no;
	private String spcl_fee_challan_no;
	private String scan_fee_challan_no;
	private ArrayList datalist = new ArrayList();
	private ArrayList importChallanList = new ArrayList();
	private ArrayList spclChallanList = new ArrayList();
	private ArrayList scanfeeChallanList = new ArrayList();
	
	
	
public String addRowMethodPackaging() {
		
		//if (displaylist.size() < 5) {
                                                                                               
			ImportChallanForPermitFL2D_20_21DT dt = new ImportChallanForPermitFL2D_20_21DT();                                  
			dt.setSrNo(importChallanList.size() + 1);                                                 
			                                                                                   
			importChallanList.add(dt);   
			                                                                                   
		//} else {                                                                             
		//	System.out.println("excd");                                                        
		//}                                                                                    
		return "";                                                                             
	}


public void deleteRowMethodPackaging(ActionEvent e) {                                      
	UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()                       
			.getParent();                                                                  
   ImportChallanForPermitFL2D_20_21DT dt = (ImportChallanForPermitFL2D_20_21DT) this.importChallanList.get(uiTable               
			.getRowIndex());                                                               
	this.importChallanList.remove(dt);                                                           
}   



public String addRowMethodPackaging1() {
	
	//if (displaylist.size() < 5) {
                                                                                           
		ImportChallanForPermitFL2D_20_21DT dt = new ImportChallanForPermitFL2D_20_21DT();                                  
		dt.setSrNo1(spclChallanList.size() + 1);                                                 
		                                                                                   
		spclChallanList.add(dt);   
		                                                                                   
	//} else {                                                                             
	//	System.out.println("excd");                                                        
	//}                                                                                    
	return "";                                                                             
}


public void deleteRowMethodPackaging1(ActionEvent e) {                                      
UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()                       
		.getParent();                                                                  
ImportChallanForPermitFL2D_20_21DT dt = (ImportChallanForPermitFL2D_20_21DT) this.spclChallanList.get(uiTable               
		.getRowIndex());                                                               
this.spclChallanList.remove(dt);                                                           
} 

	public String getScan_fee_challan_no() {
		return scan_fee_challan_no;
	}

	public void setScan_fee_challan_no(String scan_fee_challan_no) {
		this.scan_fee_challan_no = scan_fee_challan_no;
	}

	public ArrayList getScanfeeChallanList() {
		return scanfeeChallanList;
	}

	public void setScanfeeChallanList(ArrayList scanfeeChallanList) {
		this.scanfeeChallanList = scanfeeChallanList;
	}

	ImportChallanForPermitFL2D_20_21Impl impl = new ImportChallanForPermitFL2D_20_21Impl();
	private boolean flg = false;

	public boolean isFlg() {
		return flg;
	}

	public void setFlg(boolean flg) {
		this.flg = flg;
	}

	public String getHidden() {
		if (impl.getDetails(this)) {
			System.out.println("----1---"+flg);
			if (!flg) {
				System.out.println("---2---");
				this.datalist = impl.datalist(this);
				this.flg = true;
			}
		}
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public int getInt_id() {
		return int_id;
	}

	public void setInt_id(int int_id) {
		this.int_id = int_id;
	}

	public int getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}

	public int getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(int district_id) {
		this.district_id = district_id;
	}

	public ArrayList getDatalist() {
		// impl.datalist(this);
		return datalist;
	}

	public void setDatalist(ArrayList datalist) {
		this.datalist = datalist;
	}

	public String getLogin_type() {
		return login_type;
	}

	public void setLogin_type(String login_type) {
		this.login_type = login_type;
	}

	public int getVch_license_type() {
		return vch_license_type;
	}

	public void setVch_license_type(int vch_license_type) {
		this.vch_license_type = vch_license_type;
	}

	public String getImport_fee_challan_no() {
		return import_fee_challan_no;
	}

	public void setImport_fee_challan_no(String import_fee_challan_no) {
		this.import_fee_challan_no = import_fee_challan_no;
	}

	public String getSpcl_fee_challan_no() {
		return spcl_fee_challan_no;
	}

	public void setSpcl_fee_challan_no(String spcl_fee_challan_no) {
		this.spcl_fee_challan_no = spcl_fee_challan_no;
	}

	public ArrayList getImportChallanList() {
		/*this.importChallanList.clear();
		this.spclChallanList.clear();
		if (this.login_type != null) {
			if (this.login_type.equalsIgnoreCase("BWFL")) {
				impl.ChallanList(this);
			} else if (this.login_type.equalsIgnoreCase("FL2D")) {
				impl.ChallanList1(this);
			}
		}*/
		return importChallanList;
	}

	public void setImportChallanList(ArrayList importChallanList) {
		this.importChallanList = importChallanList;
	}

	public ArrayList getSpclChallanList() {
		return spclChallanList;
	}

	public void setSpclChallanList(ArrayList spclChallanList) {
		this.spclChallanList = spclChallanList;
	}

	private boolean validateInput;

	public boolean isValidateInput() {
		this.validateInput = true;

		if (validateInput) {
			if (this.datalist.size() > 0) {
				int count = 0;
				double import_fee = 0, special_fee = 0;
				for (int i = 0; i < datalist.size(); i++) {

					ImportChallanForPermitFL2D_20_21DT dt = new ImportChallanForPermitFL2D_20_21DT();
					dt = (ImportChallanForPermitFL2D_20_21DT) this.datalist
							.get(i);
					System.out.println("0-" + dt.isCheckbox());
					if (dt.isCheckbox()) {
						System.out.println("count=" + count);
						count++;
						import_fee = import_fee + dt.getImport_fee();
						special_fee = special_fee + dt.getSpecial_fee();
						System.out.println("import_fee=" + import_fee);
					}
				}
				if (count != 0) {
					System.out.println("this.login_type="+this.login_type);
					validateInput = false;
					if (this.login_type.equalsIgnoreCase("FL2D")) {
						if (impl.getChallanBalance(this, import_fee,
								special_fee)) {
							validateInput = true;
						}
					}else{
						if (impl.getChallanBalanceBWFL(this, import_fee,
								special_fee)) {
							validateInput = true;
						}
					}
					// getChallanBalanceBWFL
				} else {
					validateInput = false;
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Select atleast one checkbox"));

					System.out.println("Select atleast one checkbox");
				}

			} else {
				validateInput = false;
				System.out.println("zero row found");
			}
			/*if (this.import_fee_challan_no == null) {
				validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Select Import Fee Challan No"));
			} else if (this.spcl_fee_challan_no == null) {
				validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Select Special Fee Challan No"));
			}*/
		}
		return validateInput;
	}

	public void setValidateInput(boolean validateInput) {
		this.validateInput = validateInput;
	}

	public void save() throws SQLException {
		//String navigation="NA";
		boolean flg=false;
		System.out.println("2222");
		for(int i=0;i<this.getImportChallanList().size();i++){
			ImportChallanForPermitFL2D_20_21DT dt = (ImportChallanForPermitFL2D_20_21DT) this.getImportChallanList().get(i);
			if(impl.getImpoetChallanDetail(this, dt)){
				flg=true;
			}else{
				flg=false;
				return;
			}
		}
		
     for(int i=0;i<this.getSpclChallanList().size();i++){
    	 ImportChallanForPermitFL2D_20_21DT dt = (ImportChallanForPermitFL2D_20_21DT) this.getSpclChallanList().get(i);
    	 if(impl.getSpclChallanDetail(this, dt)){
    			flg=true;
			}else{
				flg=false;
				return;
			}
		}
		
		try {
			if (isValidateInput()) {
				if(flg){
				   if(impl.update(this)){
					   this.importChallanList.clear();
					   this.spclChallanList.clear();
				   }
				}else{

					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Challan Not Found "));
				}
			//	System.out.println("navigation="+navigation);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	//	return navigation;
	}

	public String navigationBack(){
		//#{importChallanForPermitBWFL_FL2DAction.navigationBack}
		System.out.println("---nav-"+this.login_type+"--");
		this.flg = false;
		
		return this.login_type;
		
	}
}
