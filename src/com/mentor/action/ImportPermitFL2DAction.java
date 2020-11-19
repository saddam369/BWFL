package com.mentor.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.richfaces.component.UIDataTable;
 
import com.mentor.datatable.ImportPermitFL2DDT;
import com.mentor.impl.ImportPermitFL2DImpl;
import com.mentor.utility.Utility;
import com.mentor.utility.Validate;

public class ImportPermitFL2DAction {

	ImportPermitFL2DImpl impl = new ImportPermitFL2DImpl();
	private String hidden;
	private int int_id;
	private int unit_id=0;;
	private String vch_license_number;
	private int vch_license_type;
	private int stateid;
	private String state_name;
	private String vch_firm_name;
	private String vch_firm_add;
	private int vch_firm_district_id;
	private String vch_firm_district_name;
	private String vch_associated_unit_name;
	private Date application_date;
	private String bottlngType = "m";
	private String panel = "T";
	private String total_import_fees = "0";//change double to String
	private String total_special_fee = "0";//change double to String
	private double total_duty = 0;
	private double total_add_duty = 0;
	private String flag = "F";
	private Date cr_date = Utility.convertUtilDateToSQLDate(new Date());
	private double duty_balance;
	private double importFee_balance;
	private double specialFee_balance;
	private int total_no_of_cases;
	 
	private String route_road_radio = "road";
	private String route_detail;
	private String vch_reg_office_add;
	private String duty_msg;//change double to String 
	private ArrayList displaylist = new ArrayList();
	private ArrayList hislist = new ArrayList();
	private ArrayList brand_pack_list = new ArrayList();

	private String import_fee_challan_no;
	private String spcl_fee_challan_no;
	

	

	

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
	
	public Date getCr_date() {
		return cr_date;
	}

	public void setCr_date(Date cr_date) {
		this.cr_date = cr_date;
	}

	public String getHidden() {
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

	public int getUnit_id() {
		
		return unit_id;
	}

	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}

	public String getVch_license_number() {
		return vch_license_number;
	}

	public void setVch_license_number(String vch_license_number) {
		this.vch_license_number = vch_license_number;
	}

	public int getVch_license_type() {
		return vch_license_type;
	}

	public void setVch_license_type(int vch_license_type) {
		this.vch_license_type = vch_license_type;
	}

	public int getStateid() {
		return stateid;
	}

	public void setStateid(int stateid) {
		this.stateid = stateid;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getVch_firm_name() {
		return vch_firm_name;
	}

	public void setVch_firm_name(String vch_firm_name) {
		this.vch_firm_name = vch_firm_name;
	}

	public String getVch_firm_add() {
		return vch_firm_add;
	}

	public void setVch_firm_add(String vch_firm_add) {
		this.vch_firm_add = vch_firm_add;
	}

	public int getVch_firm_district_id() {
		return vch_firm_district_id;
	}

	public void setVch_firm_district_id(int vch_firm_district_id) {
		this.vch_firm_district_id = vch_firm_district_id;
	}

	public String getVch_firm_district_name() {
		return vch_firm_district_name;
	}

	public void setVch_firm_district_name(String vch_firm_district_name) {
		this.vch_firm_district_name = vch_firm_district_name;
	}

	public String getVch_associated_unit_name() {
		return vch_associated_unit_name;
	}

	public void setVch_associated_unit_name(String vch_associated_unit_name) {
		this.vch_associated_unit_name = vch_associated_unit_name;
	}

	public Date getApplication_date() {
		return application_date;
	}

	public void setApplication_date(Date application_date) {
		this.application_date = application_date;
	}

	public String getBottlngType() {
		return bottlngType;
	}

	public void setBottlngType(String bottlngType) {
		this.bottlngType = bottlngType;
	}

	public String getPanel() {
		return panel;
	}

	public void setPanel(String panel) {
		this.panel = panel;
	}

	public ArrayList getDisplaylist() {
		return displaylist;
	}

	public void setDisplaylist(ArrayList displaylist) {
		this.displaylist = displaylist;
	}

	public void new_indent() {
		this.setPanel("N");
		//impl.getBalance(this);
		this.importChallanList.clear();
		this.spclChallanList.clear();
		impl.ChallanList(this);
		this.getImporter_unit_list();

	}

	public void his_indent() {
		this.setPanel("H");
		this.flag = "F";
		this.hislist = impl.hislistImpl(this);
	}

	public ArrayList getHislist() {
		return hislist;
	}

	public void setHislist(ArrayList hislist) {
		this.hislist = hislist;
	}

	public String addRowMethodPackaging() {
		//if (displaylist.size() < 5) {
			ImportPermitFL2DDT dt = new ImportPermitFL2DDT();
			dt.setSno(displaylist.size() + 1);
			displaylist.add(dt);
		//} else {
		//	System.out.println("excd");
		//}
		return "";
	}

	public void deleteRowMethodPackaging(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		ImportPermitFL2DDT dt = (ImportPermitFL2DDT) this.displaylist.get(uiTable
				.getRowIndex());
		this.displaylist.remove(dt);
	}

	public String getTotal_import_fees() {
		double total_importFees = 0.0;
		try {
			for (int i = 0; i < this.displaylist.size(); i++) {
				ImportPermitFL2DDT dt = (ImportPermitFL2DDT) this.getDisplaylist().get(
						i);
				total_importFees += dt.getCal_importFee();
			}
			//this.total_import_fees = String.valueOf(total_importFees);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DecimalFormat formatter = new DecimalFormat("#.##");
		this.total_import_fees =formatter.format(total_importFees);
		return total_import_fees;
	}

	public void setTotal_import_fees(String total_import_fees) {
		this.total_import_fees = total_import_fees;
	}

	public String getTotal_special_fee() {
		double total_specialFee = 0.0;
		try {
			for (int i = 0; i < this.displaylist.size(); i++) {
				ImportPermitFL2DDT dt = (ImportPermitFL2DDT) this.getDisplaylist().get(i);
				total_specialFee += dt.getSpecial_fee();
			}
			//this.total_special_fee = total_specialFee;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DecimalFormat formatter = new DecimalFormat("#.##");
		this.total_special_fee = formatter.format(total_specialFee);
		return total_special_fee;
	}

	public void setTotal_special_fee(String total_special_fee) {
		this.total_special_fee = total_special_fee;
	}

	public double getTotal_duty() {
		double totalDuty = 0.0;
		try {
			for (int i = 0; i < this.displaylist.size(); i++) {
				ImportPermitFL2DDT dt = (ImportPermitFL2DDT) this.getDisplaylist().get(
						i);
				totalDuty += dt.getCalculated_duty();
			}
			total_duty = totalDuty;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DecimalFormat formatter = new DecimalFormat("#.##");
		total_duty = Double.parseDouble(formatter.format(total_duty));
		return total_duty;
	}

	public void setTotal_duty(double total_duty) {
		this.total_duty = total_duty;
	}

	public double getTotal_add_duty() {
		double total_addDuty = 0.0;
		try {
			for (int i = 0; i < this.displaylist.size(); i++) {
				ImportPermitFL2DDT dt = (ImportPermitFL2DDT) this.getDisplaylist().get(
						i);
				total_addDuty += dt.getCalculated_add_duty();
			}
			total_add_duty = total_addDuty;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DecimalFormat formatter = new DecimalFormat("#.##");
		total_add_duty = Double.parseDouble(formatter.format(total_add_duty));
		System.out.println("total_duty="+total_duty+" -2="+total_add_duty);
		System.out.println("33-"+this.total_duty+this.total_add_duty);
		double abc=total_duty+total_add_duty;
		System.out.println("abc="+abc);
		//this.duty_msg=Double.parseDouble(formatter.format(this.duty_msg));
		return total_add_duty;
	}

	public void setTotal_add_duty(double total_add_duty) {
		this.total_add_duty = total_add_duty;
	}

	public void home() {
		this.reset();
		this.panel = "T";

	}

	public void reset() {
		this.displaylist.clear();
		this.total_import_fees = "0";
		this.total_special_fee = "0";
		this.total_duty = 0;
		this.total_add_duty = 0;
		this.bottlngType = "M";
		//this.validUpto = null;
		this.route_road_radio = "road";
		this.route_detail = null;
		this.import_fee_challan_no="";
		this.spcl_fee_challan_no="";
		this.importChallanList.clear();
		this.spclChallanList.clear();
		impl.ChallanList(this);
	}

	private boolean validateInput;

	public boolean isValidateInput() {
		this.validateInput = true;
        
		if (validateInput) {
			if (this.displaylist.size() > 0) {
				for (int i = 0; i < displaylist.size(); i++) {

					ImportPermitFL2DDT table = new ImportPermitFL2DDT();
					table = (ImportPermitFL2DDT) displaylist.get(i);
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
					else if (!(Validate.validateDouble("amount",
							table.getSpecial_fee())))
						validateInput = false;
				}
				System.out.println("this.consignor_nm_adrs="+this.consignor_nm_adrs+"-");
				 if(this.route_detail==null){
						validateInput = false;
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fill Route Detail"));		
					}
					else if(this.route_detail.trim().length()<1){
						validateInput = false;
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fill Route Detail"));
					/*}else if(this.import_fee_challan_no==null){
						validateInput = false;
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Select Import Fee Challan No"));
					}else if(this.spcl_fee_challan_no==null){
						validateInput = false;
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Select Special Fee Challan No"));
			*/		}else if(this.consignor_nm_adrs==null || this.consignor_nm_adrs.trim().equalsIgnoreCase("")){
						validateInput = false;
						FacesContext.getCurrentInstance().addMessage(null,new FacesMessage (FacesMessage.SEVERITY_ERROR,
								"Fill Name and Address of Unit from where import is to be done." ,
								"Fill Name and Address of Unit from where import is to be done."));
					 
					}
			}
		}
	/* if (!(Validate.validateStrEdit("routedtl", this.getRoute_detail())))
			validateInput = false;*/
		return validateInput;
	}

	public void setValidateInput(boolean validateInput) {
		this.validateInput = validateInput;
	}

	public int getTotal_no_of_cases() {

		int no_of_cases = 0;
		try {
			for (int i = 0; i < this.displaylist.size(); i++) {
				ImportPermitFL2DDT dt = (ImportPermitFL2DDT) this.getDisplaylist().get(
						i);
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

	public void save() {
		try {
			if (isValidateInput()) {
				//if(impl.getChallanBalance(this)){
				if(impl.save(this)){
					this.setPanel("H");
					this.flag = "F";
					this.hislist = impl.hislistImpl(this);
				//}
				}
			} else {
				System.out.println("validation fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void view(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		ImportPermitFL2DDT dt = (ImportPermitFL2DDT) this.hislist.get(uiTable
				.getRowIndex());
		this.flag = "T";
		this.brand_pack_list = impl.viewdetailImpl(this, dt.getDt_app_id(),
				dt.getDt_district_id());

	}
	public void deletelisnr(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		ImportPermitFL2DDT dt = (ImportPermitFL2DDT) this.hislist.get(uiTable
				.getRowIndex());
		 impl.delete(this, dt.getDt_app_id(),dt.getDt_district_id());
 
	}
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public ArrayList getBrand_pack_list() {
		return brand_pack_list;
	}

	public void setBrand_pack_list(ArrayList brand_pack_list) {
		this.brand_pack_list = brand_pack_list;
	}

	public Double getDuty_balance() {
		return duty_balance;
	}

	public void setDuty_balance(Double duty_balance) {
		this.duty_balance = duty_balance;
	}

	public Double getImportFee_balance() {
		return importFee_balance;
	}

	public void setImportFee_balance(Double importFee_balance) {
		this.importFee_balance = importFee_balance;
	}

	public Double getSpecialFee_balance() {
		return specialFee_balance;
	}

	public void setSpecialFee_balance(Double specialFee_balance) {
		this.specialFee_balance = specialFee_balance;
	}

	
	public String getRoute_road_radio() {
		return route_road_radio;
	}

	public void setRoute_road_radio(String route_road_radio) {
		this.route_road_radio = route_road_radio;
	}

	public String getRoute_detail() {
		return route_detail;
	}

	public void setRoute_detail(String route_detail) {
		this.route_detail = route_detail;
	}

	public void close() {
		this.reset();
		this.flag = "F";
		// this.disableFlg = true;
	}

	public String getVch_reg_office_add() {
		return vch_reg_office_add;
	}

	public void setVch_reg_office_add(String vch_reg_office_add) {
		this.vch_reg_office_add = vch_reg_office_add;
	}

	public String getDuty_msg() {
		double totalDuty = 0.0;
		double total_addDuty = 0.0;
		try {
			for (int i = 0; i < this.displaylist.size(); i++) {
				ImportPermitFL2DDT dt = (ImportPermitFL2DDT) this.getDisplaylist().get(i);
				totalDuty += dt.getCalculated_duty();
			}
			 

			for (int i = 0; i < this.displaylist.size(); i++) {
				ImportPermitFL2DDT dt = (ImportPermitFL2DDT) this.getDisplaylist().get(i);
				total_addDuty += dt.getCalculated_add_duty();
			}
			
		double duty1=total_addDuty+totalDuty;
			DecimalFormat formatter = new DecimalFormat("#.##");
			duty_msg =formatter.format(duty1);
			
			System.out.println("11="+totalDuty+",22="+total_addDuty+", 33="+duty_msg+", 44="+duty1);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		 	//duty_msg=this.total_duty+this.total_add_duty;
		return duty_msg;
	}

	public void setDuty_msg(String duty_msg) {
		this.duty_msg = duty_msg;
	}
	private ArrayList importer_unit_list = new ArrayList();
	private int importer_unit_id;
	
	public int getImporter_unit_id() {
		return importer_unit_id;
	}

	public void setImporter_unit_id(int importer_unit_id) {
		this.importer_unit_id = importer_unit_id;
	}

	public ArrayList getImporter_unit_list() {
		 this.importer_unit_list=impl.getOtherUnitList();
		
		return importer_unit_list;
	}

	public void setImporter_unit_list(ArrayList importer_unit_list) {
		this.importer_unit_list = importer_unit_list;
	}
	public void importer_unit_lsnr(ValueChangeEvent event) {
		try {
			this.displaylist.clear();
			
			Object obj = event.getNewValue();
			//String id = String.valueOf(obj);
			int id=Integer.parseInt(String.valueOf(obj));
			this.custom_unit_list=impl.getcustomlist(id);
		 } catch (Exception e) {
			e.getStackTrace();
		}

	}
	/*
			        public void blendinglistner(ValueChangeEvent vat) {
			                try {
			                        String val = (String) vat.getNewValue();
			                        int vat_id=Integer.parseInt(String.valueOf(val));
			                        new Transfer_Of_Sprit_To_Blending_VatImpl().getBlendingdata(vat_id,this);
			                        //impl.getQuantity(this, val);

			                } catch (Exception e) {

			                }*/
	/*private String cal_import_fee_heading;

	public String getCal_import_fee_heading() {
		if(this.vch_license_type==99){
		this.cal_import_fee_heading="Import Fee and Duty";
		}else{
			this.cal_import_fee_heading="Import Fee";
		}
		return cal_import_fee_heading;
	}

	public void setCal_import_fee_heading(String cal_import_fee_heading) {
		this.cal_import_fee_heading = cal_import_fee_heading;
	}*/
	//Duty-#{importPermitAction.duty_msg} jsp save popup variable
	private ArrayList importChallanList = new ArrayList();
	private ArrayList spclChallanList = new ArrayList();
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
	private String consignor_nm_adrs;


	public String getConsignor_nm_adrs() {
		return consignor_nm_adrs;
	}

	public void setConsignor_nm_adrs(String consignor_nm_adrs) {
		this.consignor_nm_adrs = consignor_nm_adrs;
	}
	public String mappingPage(){
		this.setPanel("N");
	
		return "go";
	}
	
	
	
	public void custom_unit(ValueChangeEvent event) {
		try {
			//this.displaylist.clear();
			Object obj = event.getNewValue();
			String id = String.valueOf(obj);
		 } catch (Exception e) {
			e.getStackTrace();
		}

	}
	private ArrayList custom_unit_list = new ArrayList();
	private String custom_unit_id;



	public String getCustom_unit_id() {
		return custom_unit_id;
	}

	public void setCustom_unit_id(String custom_unit_id) {
		this.custom_unit_id = custom_unit_id;
	}

	public ArrayList getCustom_unit_list() {
		return custom_unit_list;
	}

	public void setCustom_unit_list(ArrayList custom_unit_list) {
		this.custom_unit_list = custom_unit_list;
	}
	
}
