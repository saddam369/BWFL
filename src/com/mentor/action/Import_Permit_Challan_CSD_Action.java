package com.mentor.action;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.mentor.datatable.Import_Permit_Challan_CSD_DT;
import com.mentor.impl.Import_Permit_Challan_CSD_Impl;

public class Import_Permit_Challan_CSD_Action {

	private String hidden;
	private int int_id;
	private int unit_id;
	private int district_id;
	private String login_type;
	private int vch_license_type;
	private String import_fee_challan_no;
	private String spcl_fee_challan_no;
	private ArrayList datalist = new ArrayList();
	private ArrayList importChallanList = new ArrayList();
	private ArrayList spclChallanList = new ArrayList();
	private String excise_duty;
	private ArrayList excise_duty_list = new ArrayList();
	private int int_division_id;
	private boolean flag=true;
	private String scanning_fee;
	private ArrayList scanning_fee_list = new ArrayList();
	private String corona_fee;
	private ArrayList corona_fee_list = new ArrayList();
	
	public String getCorona_fee() {
		return corona_fee;
	}

	public void setCorona_fee(String corona_fee) {
		this.corona_fee = corona_fee;
	}

	public ArrayList getCorona_fee_list() {
		return corona_fee_list;
	}

	public void setCorona_fee_list(ArrayList corona_fee_list) {
		this.corona_fee_list = corona_fee_list;
	}

	public String getScanning_fee() {
		return scanning_fee;
	}

	public void setScanning_fee(String scanning_fee) {
		this.scanning_fee = scanning_fee;
	}

	public ArrayList getScanning_fee_list() {
		return scanning_fee_list;
	}

	public void setScanning_fee_list(ArrayList scanning_fee_list) {
		this.scanning_fee_list = scanning_fee_list;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getInt_division_id() {
		return int_division_id;
	}

	public void setInt_division_id(int int_division_id) {
		this.int_division_id = int_division_id;
	}

	public int getInt_treasury_id() {
		return int_treasury_id;
	}

	public void setInt_treasury_id(int int_treasury_id) {
		this.int_treasury_id = int_treasury_id;
	}

	private int int_treasury_id;

	public String getExcise_duty() {
		return excise_duty;
	}

	public void setExcise_duty(String excise_duty) {
		this.excise_duty = excise_duty;
	}

	public ArrayList getExcise_duty_list() {
		return excise_duty_list;
	}

	public void setExcise_duty_list(ArrayList excise_duty_list) {
		this.excise_duty_list = excise_duty_list;
	}

	Import_Permit_Challan_CSD_Impl impl = new Import_Permit_Challan_CSD_Impl();
	private boolean flg = false;

	public boolean isFlg() {
		return flg;
	}

	public void setFlg(boolean flg) {
		this.flg = flg;
	}

	public String getHidden() {
		   if(flag){
		   impl.getDetails(this);
		   this.datalist=impl.datalist(this);
		   this.flag=false;
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
		/*if (!flg && flag) {
			//System.out.println("---2---");
			this.datalist = impl.datalist(this);
			this.flg = true;
		}*/
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
		this.importChallanList.clear();
		this.spclChallanList.clear();
				impl.ChallanList(this);
			
		
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
        //System.out.println("sv2");
		if (validateInput) {
			if (this.datalist.size() > 0) {
				int count = 0;
				double import_fee = 0, special_fee = 0;
				for (int i = 0; i < datalist.size(); i++) {
                    //System.out.println("sv3");
					Import_Permit_Challan_CSD_DT dt = (Import_Permit_Challan_CSD_DT) this.datalist
							.get(i);
					//System.out.println("0-" + dt.isCheckbox());
					if (dt.isCheckbox()) {
						//System.out.println("count=" + count);
						count++;
						import_fee = import_fee + dt.getImport_fee();
						special_fee = special_fee + dt.getSpecial_fee();
						//System.out.println("import_fee=" + import_fee);
					}
				}
				if (count != 0) {
					////System.out.println("this.login_type="+this.login_type);
					////validateInput = false;
					//if (this.login_type.equalsIgnoreCase("FL2D")) {
						//if (impl.getChallanBalance(this, import_fee,
							//	special_fee)) {
							validateInput = true;
						//}
					/*else{
						if (impl.getChallanBalanceBWFL(this, import_fee,
								special_fee)) {
							validateInput = true;
						}
					}*/
					// getChallanBalanceBWFL
				} else {
					validateInput = false;
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Select atleast one checkbox"));

					//System.out.println("Select atleast one checkbox");
				}

			} else {
				validateInput = false;
				//System.out.println("zero row found");
			}
			if (this.import_fee_challan_no == null) {
				validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Select Import Fee Challan No"));
			} else if (this.spcl_fee_challan_no == null) {
				validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Select Special Fee Challan No"));
			}else if (this.scanning_fee == null) {
				validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Select Scanning Fee Challan No"));
			}else if (this.corona_fee == null) {
				validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Select Spcl Additional Consideration Fee Challan No"));
			}

			
		}
		return validateInput;
	}

	
	public void setValidateInput(boolean validateInput) {
		this.validateInput = validateInput;
	}

	public void save(ActionEvent ex) {
		this.flag=false;
		//System.out.println("sv");
		try {
			
			if (isValidateInput()) {
				//System.out.println("11111111111111111111");
				impl.update(this);
				this.reset();
				this.datalist=impl.datalist(this);
			//	//System.out.println("navigation="+navigation);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	//	return navigation;
	}

	public String navigationBack(){
		//#{importChallanForPermitBWFL_FL2DAction.navigationBack}
		//System.out.println("---nav-"+this.login_type+"--");
		this.flg = false;
		this.reset();
		
		return this.login_type;
		
	}
	
	public void reset(){
		this.importChallanList.clear();
		this.spclChallanList.clear();
		this.excise_duty_list.clear();
		this.datalist=impl.datalist(this);
	}
}
