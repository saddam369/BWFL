package com.mentor.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.richfaces.component.UIDataTable;


import com.mentor.datatable.Application_Csd_dt;

import com.mentor.impl.Application_Csd_Impl;
import com.mentor.utility.Utility;
import com.mentor.utility.Validate;


public class Application_Csd_Action {
	
//==============================variables====================
	private String name;
	private String disname;
	private String radio;
	private boolean radioflag;
	private String unit_id;
	private String add;
	private ArrayList displaylist = new ArrayList();
	private ArrayList unit_list = new ArrayList();
	private String panel = "T";
	private ArrayList importChallanList = new ArrayList();
	private ArrayList spclChallanList = new ArrayList();
	private String flag = "F";
	private ArrayList hislist = new ArrayList();
	private Date cr_date = Utility.convertUtilDateToSQLDate(new Date());
	private String total_import_fees = "0";
	private String total_special_fee = "0";
	private boolean validateInput;
	private int vch_firm_district_id;
	private int total_no_of_cases;
	private double special_fee;
	private double importFee=0;
	private double cal_importFee = 0;
	private int int_id;
	private String import_fee_challan_no;
	private String spcl_fee_challan_no;
	private String hidden;
	private int disid;
	private String a="A";
	private String lic_no;
	private String bottlngType;
	private String route_detail;
	private String route_road_radio;
	private String total_duty;
	private int bond_id=0;
	private boolean iuFlag;
	private boolean balance_flag;
	private ArrayList bond_list = new ArrayList();
	private String total_scanning_fee;
	private String total_corona_fee;
	private double balance_import_fee;
	private double balance_spcl_fee;
	private double balance_duty_fee;
	private double balance_corona_fee;
	private double balance_scanning_fee;


	
	//==================================== Getter Setter============================


	public double getBalance_import_fee() {
		return balance_import_fee;
	}

	public boolean isBalance_flag() {
		return balance_flag;
	}

	public void setBalance_flag(boolean balance_flag) {
		this.balance_flag = balance_flag;
	}

	public void setBalance_import_fee(double balance_import_fee) {
		this.balance_import_fee = balance_import_fee;
	}

	public double getBalance_spcl_fee() {
		return balance_spcl_fee;
	}

	public void setBalance_spcl_fee(double balance_spcl_fee) {
		this.balance_spcl_fee = balance_spcl_fee;
	}

	public double getBalance_duty_fee() {
		return balance_duty_fee;
	}

	public void setBalance_duty_fee(double balance_duty_fee) {
		this.balance_duty_fee = balance_duty_fee;
	}

	public double getBalance_corona_fee() {
		return balance_corona_fee;
	}

	public void setBalance_corona_fee(double balance_corona_fee) {
		this.balance_corona_fee = balance_corona_fee;
	}

	public double getBalance_scanning_fee() {
		return balance_scanning_fee;
	}

	public void setBalance_scanning_fee(double balance_scanning_fee) {
		this.balance_scanning_fee = balance_scanning_fee;
	}

	public String getTotal_corona_fee() {

		double total_coronaFee = 0.0;
		try {
			for (int i = 0; i < this.displaylist.size(); i++) {
				Application_Csd_dt dt = (Application_Csd_dt) this.getDisplaylist().get(i);
				total_coronaFee += dt.getCal_coronafee();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		DecimalFormat formatter = new DecimalFormat("#.##");
		this.total_corona_fee = formatter.format(total_coronaFee);
		return total_corona_fee;
	}

	public void setTotal_corona_fee(String total_corona_fee) {
		this.total_corona_fee = total_corona_fee;
	}

	public String getTotal_scanning_fee() {
		double total_scanningFee = 0.0;
		try {
			for (int i = 0; i < this.displaylist.size(); i++) {
				Application_Csd_dt dt = (Application_Csd_dt) this.getDisplaylist().get(i);
				total_scanningFee += dt.getCal_scanfee();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		DecimalFormat formatter = new DecimalFormat("#.##");
		this.total_scanning_fee = formatter.format(total_scanningFee);
		return total_scanning_fee;
	}

	public void setTotal_scanning_fee(String total_scanning_fee) {
		this.total_scanning_fee = total_scanning_fee;
	}

	public boolean isIuFlag() {
		return iuFlag;
	}

	public void setIuFlag(boolean iuFlag) {
		this.iuFlag = iuFlag;
	}

	public int getBond_id() {
		return bond_id;
	}

	public void setBond_id(int bond_id) {
		this.bond_id = bond_id;
	}

	public ArrayList getBond_list() {
		return bond_list;
	}

	public void setBond_list(ArrayList bond_list) {
		this.bond_list = bond_list;
	}

	public String getBottlngType() {
		return bottlngType;
	}

	public void setBottlngType(String bottlngType) {
		this.bottlngType = bottlngType;
	}

	public String getRoute_detail() {
		return route_detail;
	}

	public void setRoute_detail(String route_detail) {
		this.route_detail = route_detail;
	}

	public String getRoute_road_radio() {
		return route_road_radio;
	}

	public void setRoute_road_radio(String route_road_radio) {
		this.route_road_radio = route_road_radio;
	}

	public String getLic_no() {
		return lic_no;
	}

	public void setLic_no(String lic_no) {
		this.lic_no = lic_no;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}


	Application_Csd_Impl impl=new Application_Csd_Impl();
	
	
	
	public int getDisid() {
		return disid;
	}

	public void setDisid(int disid) {
		this.disid = disid;
	}

	public String getHidden() {
		//System.out.println("AA=="+this.getA());
		if(this.getA()=="A")
		  impl.getDetails(this);
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

	public String getName() {
		return name;
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
	
	public void setName(String name) {
		this.name = name;
	}




	public String getDisname() {
		return disname;
	}




	public void setDisname(String disname) {
		this.disname = disname;
	}


	public String getRadio() {
		return radio;
	}



	public void setRadio(String radio) {
		this.radio = radio;
	}




	public boolean isRadioflag() {
		return radioflag;
	}




	public void setRadioflag(boolean radioflag) {
		this.radioflag = radioflag;
	}













	public String getAdd() {
		return add;
	}




	public void setAdd(String add) {
		this.add = add;
	}
	
	

	
	public ArrayList getDisplaylist() {
		
		
		return displaylist;
	}


	public void setDisplaylist(ArrayList displaylist) {
		this.displaylist = displaylist;
	}



	public ArrayList getUnit_list() {
		return unit_list;
	}



	public void setUnit_list(ArrayList unit_list) {
		this.unit_list = unit_list;
	}




	public String getPanel() {
		return panel;
	}




	public void setPanel(String panel) {
		this.panel = panel;
	}


	public ArrayList getImportChallanList() {
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



	public String getFlag() {
		return flag;
	}



	public void setFlag(String flag) {
		this.flag = flag;
	}



	public ArrayList getHislist() {
		return hislist;
	}



	public void setHislist(ArrayList hislist) {
		this.hislist = hislist;
	}

	public Date getCr_date() {
		return cr_date;
	}


	public void setCr_date(Date cr_date) {
		this.cr_date = cr_date;
	}



	public int getVch_firm_district_id() {
		return vch_firm_district_id;
	}


	public void setVch_firm_district_id(int vch_firm_district_id) {
		this.vch_firm_district_id = vch_firm_district_id;
	}

	public void setTotal_import_fees(String total_import_fees) {
		this.total_import_fees = total_import_fees;
	}

	public void setTotal_special_fee(String total_special_fee) {
		this.total_special_fee = total_special_fee;
	}

	public void setValidateInput(boolean validateInput) {
		this.validateInput = validateInput;
	}


	public int getTotal_no_of_cases() {

		int no_of_cases = 0;
		try {
			for (int i = 0; i < this.displaylist.size(); i++) {
				Application_Csd_dt dt = (Application_Csd_dt) this.getDisplaylist().get(i);
				no_of_cases += dt.getNo_of_cases();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return no_of_cases;
	}

	public void setTotal_no_of_cases(int total_no_of_cases) {
		this.total_no_of_cases = total_no_of_cases;
	}


	public void importer_unit_lsnr(ValueChangeEvent e1) {
		Object obj = e1.getNewValue();
		String val = String.valueOf(obj);
		this.setUnit_id(val);
		try {
			
		 this.displaylist.clear();
			System.out.println("radio=============="+this.getRadio());
			if (this.radio.equalsIgnoreCase("IU")) {
				this.iuFlag=true;
				this.bond_list = impl.getBondUnitList(this, unit_id);
			}else{
				this.iuFlag=false;
			}
			if(this.radio.equalsIgnoreCase("IU") || this.radio.equalsIgnoreCase("O")){
				impl.getBalance(this);
				this.balance_flag=true;
			}else{
				this.balance_flag=false;
			}
		 } catch (Exception e) {
			e.getStackTrace();
		}

	}
	
	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	public void Bond_lsnr(ValueChangeEvent e1) {
		Object obj = e1.getNewValue();
		String val = String.valueOf(obj);
		this.setBond_id(Integer.parseInt(val));
		try {
			
		 
			/*else if (radio.equalsIgnoreCase("B")) {
				impl.getbup(this , (int) val);
			}
			else if (radio.equalsIgnoreCase("O")) {
				impl.getoup(this ,(int) val);
			}*/
		 } catch (Exception e) {
			e.getStackTrace();
		}

	}


	public void method(ActionEvent e){
		String val=(String) ((EditableValueHolder) e.getComponent().getParent())
				.getValue();
		this.setRadio(val);
		
		System.out.println(val);
        if (this.radio.equalsIgnoreCase("D")) {
        	
        	 this.unit_list=impl.getdisUnitList();
        	
		}
        else if (this.radio.equalsIgnoreCase("B")) {
    	  this.unit_list=impl.getbupUnitList();
		}
        else if (this.radio.equalsIgnoreCase("O")) {
    	   this.unit_list=impl.getoupUnitList();
        }
        else if(this.radio.equalsIgnoreCase("IU")){
        	this.unit_list = impl.getIUList();
        	//this.unit_list = impl.getImportUnitList(this);
        }
	}
	public String getTotal_duty() {
		double total_duty = 0.0;
		try {
			for (int i = 0; i < this.displaylist.size(); i++) {
				Application_Csd_dt dt = (Application_Csd_dt) this.getDisplaylist().get(
						i);
				total_duty += dt.getCal_duty();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		DecimalFormat formatter = new DecimalFormat("#.##");
		this.total_duty =formatter.format(total_duty);
		return this.total_duty;
	}

	public void setTotal_duty(String total_duty) {
		this.total_duty = total_duty;
	}

	public String getTotal_import_fees() {
		double total_importFees = 0.0;
		try {
			for (int i = 0; i < this.displaylist.size(); i++) {
				Application_Csd_dt dt = (Application_Csd_dt) this.getDisplaylist().get(
						i);
				total_importFees += dt.getCal_importFee();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		DecimalFormat formatter = new DecimalFormat("#.##");
		this.total_import_fees =formatter.format(total_importFees);
		return total_import_fees;
	}
	public String getTotal_special_fee() {
		double total_specialFee = 0.0;
		try {
			for (int i = 0; i < this.displaylist.size(); i++) {
				Application_Csd_dt dt = (Application_Csd_dt) this.getDisplaylist().get(i);
				total_specialFee += dt.getSpecial_fee();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		DecimalFormat formatter = new DecimalFormat("#.##");
		this.total_special_fee = formatter.format(total_specialFee);
		return total_special_fee;
	}

	
	
	public void save() {
		try {
			if (isValidateInput()) {
				
			 if(this.radio.equalsIgnoreCase("O")){
				 if(Double.parseDouble(this.total_duty)>0.0 && Double.parseDouble(this.total_import_fees)>0.0 
						 && Double.parseDouble(this.total_special_fee)>0.0){
					   if(impl.save(this)){
						this.setPanel("N");
						this.flag = "F";
						this.reset();
						this.hislist = impl.hislistImpl(this);
					
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error ! Special Fee, Duty, Import Fee Cannot be Zero !!"));}
			 }
			 else 
				 if(this.radio.equalsIgnoreCase("IU")){
				 if(Double.parseDouble(this.total_duty)>0.0 
						 && Double.parseDouble(this.total_special_fee)>0.0){
					   if(impl.save(this)){
						this.setPanel("N");
						this.flag = "F";
						this.reset();
						this.hislist = impl.hislistImpl(this);
					
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error ! Special Fee, Duty, Import Fee Cannot be Zero !!"));}
			 }
			 else {
				  if(impl.save(this)){
						this.setPanel("N");
						this.flag = "F";
						this.reset();
						this.hislist = impl.hislistImpl(this);
					
				}
			 }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public boolean isValidateInput() {
		this.validateInput = true;
        
		if (validateInput) {
			if (this.displaylist.size() > 0) {
				for (int i = 0; i < displaylist.size(); i++) {

					Application_Csd_dt table = new Application_Csd_dt();
					table = (Application_Csd_dt) displaylist.get(i);
					if (!(Validate.validateStrReqRow(i + 1, "Brand",
							table.getBrand_id())))
						validateInput = false;
					else if (!(Validate.validateStrReqRow(i + 1, "Packaging",
							table.getPckg_id())))
						validateInput = false;
					else if (!(Validate.validateStrReqRow(i + 1, "Quantity",
							table.getQuantity())))
						validateInput = false;
					else if (!(Validate.validateStrReqRow(i + 1, "etin",
							table.getEtin())))
						validateInput = false;
					else if (!(Validate.validateDouble("NoOfBoxes",
							table.getNo_of_cases())))
						validateInput = false;
					else if (!(Validate.validateDouble("bottle_per_case",
							table.getNo_of_bottle_per_case())))
						validateInput = false;
					else if (!(Validate.validateInteger("PlanNoOfBottling",
							table.getPland_no_of_bottles())))
						validateInput = false;
					
				}
				
				}
			if(this.radio.equalsIgnoreCase("IU") || this.radio.equalsIgnoreCase("O")){
			if(this.balance_import_fee<Double.parseDouble(this.total_import_fees)){
				validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Total Import Fee Should Not Be Greatee Than Available Import Fee!! ", "Total Import Fee Should Not Be Greatee Than Available Import Fee!!"));	
			}
			else if(this.balance_spcl_fee<Double.parseDouble(this.total_special_fee)){
				validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Total Special Fee Should Not Be Greatee Than Available Special Fee!! ", "Total Special Fee Should Not Be Greatee Than Available Special Fee!!"));
			}
			else if(this.balance_duty_fee<Double.parseDouble(this.total_duty)){
				validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Total Excise Duty  Should Not Be Greatee Than Available Duty !! ", "Total Excise Duty  Should Not Be Greatee Than Available Duty !! "));
			}
			else if(this.balance_scanning_fee<Double.parseDouble(this.total_scanning_fee)){
				validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Total Scanning Fee Should Not Be Greatee Than Available Scanning Fee !! ", "Total Scanning Fee Should Not Be Greatee Than Available Scanning Fee !! "));
			}
			else if(this.balance_corona_fee<Double.parseDouble(this.total_corona_fee)){
				validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Total Addtional Consideration Fee  Should Not Be Greatee Than Available Addtional Consideration Fee !! ", "Total Addtional Consideration Fee Should Not Be Greatee Than Available Addtional Consideration Fee !! "));
			}
			
			}
		
			}
		
	/* if (!(Validate.validateStrEdit("routedtl", this.getRoute_detail())))
			validateInput = false;*/
		return validateInput;
	}
	
	
	public void reset() {
		this.displaylist.clear();
		this.total_import_fees = "0";
		this.total_special_fee = "0";
	    this.route_detail=null;
	    this.route_road_radio="";
	    this.radio=null;
		this.importChallanList.clear();
		this.spclChallanList.clear();
		this.unit_list.clear();
		this.total_corona_fee="0";
		this.total_scanning_fee="0";
		this.total_duty="0";
		this.balance_corona_fee=0;
		this.balance_duty_fee=0;
		this.balance_import_fee=0;
		this.balance_scanning_fee=0;
		this.balance_spcl_fee=0;
		
	}
     
	
	//====================input =======================
	private int appid;
	private String appdate;
	private String utype;
	private String uid;
	private String uadd;
	private String tpfee;
	private String tsfee;
	private String fl2adis;
  private String uname;
  

	/**
 * @return the uname
 */
public String getUname() {
	return uname;
}




/**
 * @param uname the uname to set
 */
public void setUname(String uname) {
	this.uname = uname;
}




	/**
	 * @return the special_fee
	 */
	public double getSpecial_fee() {
		return special_fee;
	}




	/**
	 * @param special_fee the special_fee to set
	 */
	public void setSpecial_fee(double special_fee) {
		this.special_fee = special_fee;
	}




	/**
	 * @return the importFee
	 */
	public double getImportFee() {
		return importFee;
	}




	/**
	 * @param importFee the importFee to set
	 */
	public void setImportFee(double importFee) {
		this.importFee = importFee;
	}




	/**
	 * @return the cal_importFee
	 */
	public double getCal_importFee() {
		return cal_importFee;
	}




	/**
	 * @param cal_importFee the cal_importFee to set
	 */
	public void setCal_importFee(double cal_importFee) {
		this.cal_importFee = cal_importFee;
	}




	/**
	 * @return the impl
	 */
	public Application_Csd_Impl getImpl() {
		return impl;
	}




	/**
	 * @param impl the impl to set
	 */
	public void setImpl(Application_Csd_Impl impl) {
		this.impl = impl;
	}




	/**
	 * @return the appid
	 */
	public int getAppid() {
		return appid;
	}




	/**
	 * @param appid the appid to set
	 */
	public void setAppid(int appid) {
		this.appid = appid;
	}




	/**
	 * @return the appdate
	 */
	public String getAppdate() {
		return appdate;
	}




	/**
	 * @param appdate the appdate to set
	 */
	public void setAppdate(String appdate) {
		this.appdate = appdate;
	}




	/**
	 * @return the utype
	 */
	public String getUtype() {
		return utype;
	}




	/**
	 * @param utype the utype to set
	 */
	public void setUtype(String utype) {
		this.utype = utype;
	}




	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}




	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}




	/**
	 * @return the uadd
	 */
	public String getUadd() {
		return uadd;
	}




	/**
	 * @param uadd the uadd to set
	 */
	public void setUadd(String uadd) {
		this.uadd = uadd;
	}


private int brand_id;


	/**
 * @return the brand_id
 */
public int getBrand_id() {
	return brand_id;
}

/**
 * @param brand_id the brand_id to set
 */
public void setBrand_id(int brand_id) {
	this.brand_id = brand_id;
}

	/**
	 * @return the tpfee
	 */
	public String getTpfee() {
		return tpfee;
	}




	/**
	 * @param tpfee the tpfee to set
	 */
	public void setTpfee(String tpfee) {
		this.tpfee = tpfee;
	}




	/**
	 * @return the tsfee
	 */
	public String getTsfee() {
		return tsfee;
	}




	/**
	 * @param tsfee the tsfee to set
	 */
	public void setTsfee(String tsfee) {
		this.tsfee = tsfee;
	}




	/**
	 * @return the fl2adis
	 */
	public String getFl2adis() {
		return fl2adis;
	}




	/**
	 * @param fl2adis the fl2adis to set
	 */
	public void setFl2adis(String fl2adis) {
		this.fl2adis = fl2adis;
	}
	
	public String addRowMethodPackaging() {
		
		//if (displaylist.size() < 5) {
                                                                                               
			Application_Csd_dt dt = new Application_Csd_dt();                                  
			dt.setSno(displaylist.size() + 1);                                                 
			                                                                                   
			displaylist.add(dt);   
			dt.setRadio(this.getRadio());
			                                                                                   
		//} else {                                                                             
		//	System.out.println("excd");                                                        
		//}                                                                                    
		return "";                                                                             
	}                                                                                          
	public void deleteRowMethodPackaging(ActionEvent e) {                                      
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()                       
				.getParent();                                                                  
	   Application_Csd_dt dt = (Application_Csd_dt) this.displaylist.get(uiTable               
				.getRowIndex());                                                               
		this.displaylist.remove(dt);                                                           
	}                                                                                          
                                                                                               
	public void method1(ActionEvent e) {                                                  
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
	   Application_Csd_dt dt = (Application_Csd_dt) this.getDisplaylist().get(uiTable
				.getRowIndex());
	   
	   dt.setRadio(this.getRadio());
		try {
			this.brand_id=Integer.parseInt(dt.getBrand_id());
			
			//System.out.println("==================brsnd id"+brand_id);
			//System.out.println("action method");
				impl.getmethod(this,dt);
			
		 } catch (Exception e1) {
			e1.getStackTrace();
		}

	}
	
	
	
}
