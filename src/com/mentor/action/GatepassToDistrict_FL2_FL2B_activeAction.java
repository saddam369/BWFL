package com.mentor.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletContext;

import org.richfaces.component.UIDataTable;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.mentor.datatable.GatepassToDistrict_FL2_FL2B_activeDt;
import com.mentor.datatable.GatepassToDistrict_FL2_FL2B_activeDt;
import com.mentor.impl.GatepassToDistrict_FL2_FL2B_activeImpl;
import com.mentor.impl.GatepassToDistrict_FL2_FL2B_activeImpl;
import com.mentor.utility.Validate;

public class GatepassToDistrict_FL2_FL2B_activeAction {

	GatepassToDistrict_FL2_FL2B_activeImpl impl = new GatepassToDistrict_FL2_FL2B_activeImpl();

	private boolean disable_flag;
	
	
	public boolean isDisable_flag() {
		return disable_flag;
	}

	public void setDisable_flag(boolean disable_flag) {
		this.disable_flag = disable_flag;
	}

	private String draftpdfname;

	private String DraftPdfname;

	public String getDraftpdfname() {
		return draftpdfname;
	}

	public void setDraftpdfname(String draftpdfname) {
		this.draftpdfname = draftpdfname;
	}

	private String fl2_fl2bName;
	private String fl2_fl2bAdrs;
	private String hidden;
	private int fl2_fl2bId;
	public Date dt_date = new Date();
	public Date validtilldt_date;
	public String vch_to;
	private int districtLic;
	public String vch_to_lic_no;
	private String licenseeName;
	private String licenseeAdrs;
	private int licenseeId;
	private String vehicleDrvrName;
	private String vehicleAgencyNmAdrs;
	private int district1;
	private int district2;
	private int district3;
	private String routeDtl;
	private String vehicleNo;
	private double grossWeight = 0;
	private double tierWeight = 0;
	private double netWeight = 0;
	private ArrayList districtList = new ArrayList();
	private ArrayList displaylist = new ArrayList();
	private String officrEmail;
	public String vch_from;
	private String fl2LicenseType;
	private ArrayList licNmbrList = new ArrayList();
	private String brc_to_lic;
	private boolean drpdwnFlg;
	private boolean drpdwnFlg1;
	private ArrayList brclicNmbrList = new ArrayList();
	public Date table_dt = new Date();
	
	private String challan_no;
	private Date challan_dt;
	private String challan_amt;
	public String vch_to_lic_noNew;
	private int total_avl_bottle;
	private double advance_duty;
	private double total_permit_fee;
	
	
	
	public double getTotal_permit_fee() {
		double permit_fee=0.0;
		for (int i = 0; i < this.displaylist.size(); i++) {
			GatepassToDistrict_FL2_FL2B_activeDt dt = (GatepassToDistrict_FL2_FL2B_activeDt) displaylist
					.get(i);
			if (dt.getDispatchbox() > 0 && dt.getDispatchbottls() > 0) {
			
			 if(dt.getLicense_category().equalsIgnoreCase("FL") || dt.getLicense_category().equalsIgnoreCase("WINE") || dt.getLicense_category().equalsIgnoreCase("IMPORTED FL")
					 || dt.getLicense_category().equalsIgnoreCase("IMFL") || dt.getLicense_category().equalsIgnoreCase("IMPORTED WINE")){
				  permit_fee=permit_fee+dt.getDispatchbottls()*10;
			     }else if(dt.getLicense_category().equalsIgnoreCase("IMPORTED BEER") || dt.getLicense_category().equalsIgnoreCase("BEER") || dt.getLicense_category().equalsIgnoreCase("IMPORTED FL")
						 || dt.getLicense_category().equalsIgnoreCase("LAB")){
			    	 permit_fee=permit_fee+dt.getDispatchbottls()*5;
			     }
			}
			
			}
		this.total_permit_fee=permit_fee;
		return total_permit_fee;
	}

	public void setTotal_permit_fee(double total_permit_fee) {
		this.total_permit_fee = total_permit_fee;
	}

	public double getAdvance_duty() {
		return advance_duty;
	}

	public void setAdvance_duty(double advance_duty) {
		this.advance_duty = advance_duty;
	}

	public int getTotal_avl_bottle() {
		return total_avl_bottle;
	}

	public void setTotal_avl_bottle(int total_avl_bottle) {
		this.total_avl_bottle = total_avl_bottle;
	}

	public String getVch_to_lic_noNew() {
		return vch_to_lic_noNew;
	}

	public void setVch_to_lic_noNew(String vch_to_lic_noNew) {
		this.vch_to_lic_noNew = vch_to_lic_noNew;
	}

	public String getChallan_no() {
		return challan_no;
	}

	public void setChallan_no(String challan_no) {
		this.challan_no = challan_no;
	}

	public Date getChallan_dt() {
		return challan_dt;
	}

	public void setChallan_dt(Date challan_dt) {
		this.challan_dt = challan_dt;
	}

	public String getChallan_amt() {
		return challan_amt;
	}

	public void setChallan_amt(String challan_amt) {
		this.challan_amt = challan_amt;
	}

	public Date getTable_dt() {
		return table_dt;
	}

	public void setTable_dt(Date table_dt) {
		this.table_dt = table_dt;
	}

	public String getFl2_fl2bName() {
		return fl2_fl2bName;
	}

	public void setFl2_fl2bName(String fl2_fl2bName) {
		this.fl2_fl2bName = fl2_fl2bName;
	}

	public String getFl2_fl2bAdrs() {
		return fl2_fl2bAdrs;
	}

	public void setFl2_fl2bAdrs(String fl2_fl2bAdrs) {
		this.fl2_fl2bAdrs = fl2_fl2bAdrs;
	}

	public String getHidden() {
		try {
			
			
			
			impl.getDetails(this);
			
			if(this.fl2LicenseType.equalsIgnoreCase("CL2"))
			{
				this.brcFlag=false;
				this.rtFlag=true;
			}else
			{
				this.brcFlag=true;
				this.rtFlag=false;
			}
			
			
			//impl.getEmailDetails(this);
			if (this.brc_to_lic != null) {
				// impl.getlicenseeDetail(this, this.brc_to_lic);
			}
			if (this.vch_to_lic_no != null) {
				// impl.getretailLicenseeDetail(this, this.vch_to_lic_no);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public int getFl2_fl2bId() {
		return fl2_fl2bId;
	}

	public void setFl2_fl2bId(int fl2_fl2bId) {
		this.fl2_fl2bId = fl2_fl2bId;
	}

	public Date getDt_date() {
		return dt_date;
	}

	public void setDt_date(Date dt_date) {
		this.dt_date = dt_date;
	}

	public Date getValidtilldt_date() {
		return validtilldt_date;
	}

	public void setValidtilldt_date(Date validtilldt_date) {
		this.validtilldt_date = validtilldt_date;
	}

	public String getVch_to() {
		return vch_to;
	}

	public void setVch_to(String vch_to) {
		this.vch_to = vch_to;
	}

	public int getDistrictLic() {
		return districtLic;
	}

	public void setDistrictLic(int districtLic) {
		this.districtLic = districtLic;
	}

	public String getVch_to_lic_no() {
		return vch_to_lic_no;
	}

	public void setVch_to_lic_no(String vch_to_lic_no) {
		this.vch_to_lic_no = vch_to_lic_no;
	}

	public String getLicenseeName() {
		return licenseeName;
	}

	public void setLicenseeName(String licenseeName) {
		this.licenseeName = licenseeName;
	}

	public String getLicenseeAdrs() {
		return licenseeAdrs;
	}

	public void setLicenseeAdrs(String licenseeAdrs) {
		this.licenseeAdrs = licenseeAdrs;
	}

	public int getLicenseeId() {
		return licenseeId;
	}

	public void setLicenseeId(int licenseeId) {
		this.licenseeId = licenseeId;
	}

	public String getVehicleDrvrName() {
		return vehicleDrvrName;
	}

	public void setVehicleDrvrName(String vehicleDrvrName) {
		this.vehicleDrvrName = vehicleDrvrName;
	}

	public String getVehicleAgencyNmAdrs() {
		return vehicleAgencyNmAdrs;
	}

	public void setVehicleAgencyNmAdrs(String vehicleAgencyNmAdrs) {
		this.vehicleAgencyNmAdrs = vehicleAgencyNmAdrs;
	}

	public int getDistrict1() {
		return district1;
	}

	public void setDistrict1(int district1) {
		this.district1 = district1;
	}

	public int getDistrict2() {
		return district2;
	}

	public void setDistrict2(int district2) {
		this.district2 = district2;
	}

	public int getDistrict3() {
		return district3;
	}

	public void setDistrict3(int district3) {
		this.district3 = district3;
	}

	public String getRouteDtl() {
		return routeDtl;
	}

	public void setRouteDtl(String routeDtl) {
		this.routeDtl = routeDtl;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public double getTierWeight() {
		return tierWeight;
	}

	public void setTierWeight(double tierWeight) {
		this.tierWeight = tierWeight;
	}

	public double getNetWeight() {
		if (this.grossWeight > 0.0 && this.tierWeight > 0.0) {
			this.netWeight = this.grossWeight - this.tierWeight;
		} else {
			this.netWeight = 0.0;
		}
		return netWeight;
	}

	public void setNetWeight(double netWeight) {
		this.netWeight = netWeight;
	}

	public ArrayList getDistrictList() {
	/*	try {
			this.districtList = impl.getDistrictList(this);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return districtList;
	}

	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}

	public ArrayList getDisplaylist() {

		return displaylist;
	}

	public void setDisplaylist(ArrayList displaylist) {
		this.displaylist = displaylist;
	}

	public String getOfficrEmail() {
		return officrEmail;
	}

	public void setOfficrEmail(String officrEmail) {
		this.officrEmail = officrEmail;
	}

	public String getVch_from() {
		return vch_from;
	}

	public void setVch_from(String vch_from) {
		this.vch_from = vch_from;
	}

	public String getFl2LicenseType() {
		return fl2LicenseType;
	}

	public void setFl2LicenseType(String fl2LicenseType) {
		this.fl2LicenseType = fl2LicenseType;
	}

	public ArrayList getLicNmbrList() {
		return licNmbrList;
	}

	public void setLicNmbrList(ArrayList licNmbrList) {
		this.licNmbrList = licNmbrList;
	}

	public String getBrc_to_lic() {
		return brc_to_lic;
	}

	public void setBrc_to_lic(String brc_to_lic) {
		this.brc_to_lic = brc_to_lic;
	}

	public boolean isDrpdwnFlg() {
		return drpdwnFlg;
	}

	public void setDrpdwnFlg(boolean drpdwnFlg) {
		this.drpdwnFlg = drpdwnFlg;
	}

	public boolean isDrpdwnFlg1() {
		return drpdwnFlg1;
	}

	public void setDrpdwnFlg1(boolean drpdwnFlg1) {
		this.drpdwnFlg1 = drpdwnFlg1;
	}

	public ArrayList getBrclicNmbrList() {
		return brclicNmbrList;
	}

	public void setBrclicNmbrList(ArrayList brclicNmbrList) {
		this.brclicNmbrList = brclicNmbrList;
	}

	public String listMethod(ValueChangeEvent vce) {
		String val = (String) vce.getNewValue();
		this.vch_to=val;
		
		this.displaylist = impl.displaylistImpl(this);
		impl.getDistrictList(this);
		this.disable_flag=false;
		this.flagshop=false;
		this.brc_to_lic = null;
		this.brclicNmbrList.clear();
		this.vch_to_lic_no = null;
		this.licNmbrList.clear();
		this.licenseeName = null;
		this.licenseeAdrs = null;
		this.licenseeId = 0;

		this.licenseeName = null;
		this.licenseeAdrs = null;
		this.licenseeId = 0;
		this.printDraft = true;
		this.printFlag = false;
		return "";

	}
	public String listMethod1(ValueChangeEvent vce) {
		Date ev=(Date) vce.getNewValue();
		this.table2List = impl.getTable2List1(this,ev);
		return "";

	}

	public String drpdownMethod(ValueChangeEvent vce) {

		String val = (String) vce.getNewValue();
		impl.getlicenseeDetail(this, val);

		return "";
	}

	public String retaildrpMethod(ValueChangeEvent vce) {

		String val = (String) vce.getNewValue();
		impl.getretailLicenseeDetail(this, val);

		return "";
	}

	public double db_total_value = 0;
	public double sum = 0;
	public boolean flag = true;
	public double db_total_add_value = 0;
	public double sum_add = 0;
	public boolean addflag = true;

	public double getDb_total_value() {
		return db_total_value;
	}

	public void setDb_total_value(double db_total_value) {
		this.db_total_value = db_total_value;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public double getDb_total_add_value() {
		return db_total_add_value;
	}

	public void setDb_total_add_value(double db_total_add_value) {
		this.db_total_add_value = db_total_add_value;
	}

	public double getSum_add() {
		return sum_add;
	}

	public void setSum_add(double sum_add) {
		this.sum_add = sum_add;
	}

	public boolean isAddflag() {
		return addflag;
	}

	public void setAddflag(boolean addflag) {
		this.addflag = addflag;
	}

	public void calculateTotalDuty(ActionEvent ae) {

	}

	private boolean validateInput1;

	public boolean isValidateInput1() {

		validateInput1 = true;
		if (!(Validate.validateDate("validtill", this.getValidtilldt_date())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("radioto", this.getVch_to())))
			validateInput1 = false;

		// ///////////////////////////////////
		else if (this.vch_to.equalsIgnoreCase("BRC") ) {
			if (!(Validate.validateStrReq("licenseno", this.getVch_to_lic_no())))
				validateInput1 = false;
			else if (!(Validate.validateStrReq("licenseenm",
					this.getLicenseeName())))
				validateInput1 = false;
			else if (!(Validate.validateStrReq("lic enseeaddress",
					this.getLicenseeAdrs())))
				validateInput1 = false;
			else if (!(Validate.validateStrReq("routedtl", this.getRouteDtl())))
				validateInput1 = false;
			else if (!(Validate.validateStrReq("vehiclenmbr",
					this.getVehicleNo())))
				validateInput1 = false;
			else if (!(Validate.validateStrReq("vehicledriver",
					this.getVehicleDrvrName())))
				validateInput1 = false;
			else if (!(Validate.validateStrReq("agencyNameAddress",
					this.getVehicleAgencyNmAdrs())))
				validateInput1 = false;
		
		
		}

		else if (this.vch_to.equalsIgnoreCase("RT") ) {
		/*	if (!(Validate.validateStrReq("licenseno", this.getBrc_to_lic())))
				validateInput1 = false;*/
			
			 if (!(Validate.validateStrReq("shopno",
					this.getShopno())))
				validateInput1 = false;
			 
				
			else if (!(Validate.validateStrReq("shopname",
						this.getShopName())))
					validateInput1 = false;
			 
			 
			 else if (!(Validate.validateStrReq("licenseenm",
					this.getLicenseeName())))
				validateInput1 = false;
			
		
			
			else if (!(Validate.validateStrReq("licenseeaddress",
					this.getLicenseeAdrs())))
				validateInput1 = false;
			else if (!(Validate.validateStrReq("routedtl", this.getRouteDtl())))
				validateInput1 = false;
			else if (!(Validate.validateStrReq("vehiclenmbr",
					this.getVehicleNo())))
				validateInput1 = false;
			else if (!(Validate.validateStrReq("vehicledriver",
					this.getVehicleDrvrName())))
				validateInput1 = false;
			else if (!(Validate.validateStrReq("agencyNameAddress",
					this.getVehicleAgencyNmAdrs())))
				validateInput1 = false;
	
		    }else if (this.vch_to.equalsIgnoreCase("PR") ) {
		/*	if (!(Validate.validateStrReq("licenseno", this.getBrc_to_lic())))
				validateInput1 = false;*/
			
			 if (!(Validate.validateStrReq("shopno",
					this.getShopno())))
				validateInput1 = false;
			 
				
			else if (!(Validate.validateStrReq("shopname",
						this.getShopName())))
					validateInput1 = false;
			 
			 
			 else if (!(Validate.validateStrReq("licenseenm",
					this.getLicenseeName())))
				validateInput1 = false;
			
		
			
			else if (!(Validate.validateStrReq("licenseeaddress",
					this.getLicenseeAdrs())))
				validateInput1 = false;
			else if (!(Validate.validateStrReq("routedtl", this.getRouteDtl())))
				validateInput1 = false;
			else if (!(Validate.validateStrReq("vehiclenmbr",
					this.getVehicleNo())))
				validateInput1 = false;
			else if (!(Validate.validateStrReq("vehicledriver",
					this.getVehicleDrvrName())))
				validateInput1 = false;
			else if (!(Validate.validateStrReq("agencyNameAddress",
					this.getVehicleAgencyNmAdrs())))
				validateInput1 = false;
	
		    }

		// ////////////////////////////////////

		/*
		 * else if (this.drpdwnFlg==true) { if
		 * (!(Validate.validateStrReq("licenseno", this.getVch_to_lic_no())))
		 * validateInput1 = false; }
		 * 
		 * else if (this.drpdwnFlg1==true) { if
		 * (!(Validate.validateStrReq("licenseno", this.getBrc_to_lic())))
		 * validateInput1 = false; }
		 * 
		 * else if (!(Validate.validateStrReq("licenseenm",
		 * this.getLicenseeName()))) validateInput1 = false; else if
		 * (!(Validate.validateStrReq("licenseeaddress",
		 * this.getLicenseeAdrs()))) validateInput1 = false; else if
		 * (!(Validate.validateStrReq("routedtl", this.getRouteDtl())))
		 * validateInput1 = false; else if
		 * (!(Validate.validateStrReq("vehiclenmbr", this.getVehicleNo())))
		 * validateInput1 = false; else if
		 * (!(Validate.validateStrReq("vehicledriver",
		 * this.getVehicleDrvrName()))) validateInput1 = false; else if
		 * (!(Validate.validateStrReq("agencyNameAddress",
		 * this.getVehicleAgencyNmAdrs()))) validateInput1 = false; else if
		 * (!(Validate .validateDouble("grossWeight", this.getGrossWeight())))
		 * validateInput1 = false; else if
		 * (!(Validate.validateDouble("tierweight", this.getTierWeight())))
		 * validateInput1 = false; else if (!(this.grossWeight >
		 * this.getTierWeight())) { validateInput1 = false;
		 * 
		 * FacesContext.getCurrentInstance().addMessage( null, new
		 * FacesMessage(FacesMessage.SEVERITY_ERROR,
		 * "GROSS_GREATER_THAN_TIER !!! ", "GROSS_GREATER_THAN_TIER !!!")); }
		 */
          int total_bottle=0;
		for (int i = 0; i < this.displaylist.size(); i++) {
			GatepassToDistrict_FL2_FL2B_activeDt dt = (GatepassToDistrict_FL2_FL2B_activeDt) displaylist
					.get(i);
			if (dt.getDispatchbox() > 0 && dt.getDispatchbottls() > 0) {
				  if(!check_economy(dt.getBrandIdDt())){
					  eco_flag = true;
				  total_bottle=total_bottle+dt.getDispatchbottls();
				}
				if (dt.getBatchNo() == null || dt.getBatchNo().equals("")) {
					validateInput1 = false;
					FacesContext.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_ERROR,
											"Enter Batch No At Line No !!! "
													+ (i + 1),
											"Enter Batch No At Line No !!! "
													+ (i + 1)));
					break;
				}
			}
		}
		
		double tat_amt=0.0;
		if(this.vch_from.equalsIgnoreCase("FL2")){
			tat_amt=total_bottle*10;
		}else if (this.vch_from.equalsIgnoreCase("FL2B")){
			tat_amt=total_bottle*5;
		}
		if(this.advance_duty<tat_amt && this.vch_to.equalsIgnoreCase("BRC")){
			validateInput1 = false;
			FacesContext.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Gatepass Amount Should Not Be Greater Than Advance Special Duty !!! ",
							"Gatepass Amount Should Not Be Greater Than Advance Special Duty !!! "));
		    }
		int sumBottles = 0;
		int sumBoxes = 0;
		for (int i = 0; i < this.displaylist.size(); i++) {
			GatepassToDistrict_FL2_FL2B_activeDt dt = (GatepassToDistrict_FL2_FL2B_activeDt) displaylist
					.get(i);

			
			if (dt.getDispatchbottls() > 0 ) {
				sumBottles += dt.getDispatchbottls();
	   if ((dt.getDispatchbottls()) > dt.getInt_bottle_avaliable()) {
					FacesContext
							.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_ERROR,
											"Dispatch Bottles Should Not Be More Than Available Bottles at Line "+(i + 1)+"  !!! ",
											"Dispatch Bottles Should Not Be More Than Available Bottles at Line "+(i + 1)+"  !!!"));
					validateInput1 = false;

				}
	   if(this.vch_to.equalsIgnoreCase("RT")){
		   if(impl.check_wine(dt.getInt_brand_id())==true && this.getShopTyp().equalsIgnoreCase("BEER") && this.getVch_from().equalsIgnoreCase("FL2")) {
					FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"Only Wine brands are allowed to be dispatched on Beer Shop at Line "+(i + 1)+"  !!! ",
									"Only Wine brands are allowed to be dispatched on Beer Shop at Line "+(i + 1)+"  !!!"));
			validateInput1 = false;
				}
	    }  
	   }
		
			 
		}
		 
		
		if (sumBottles == 0) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"Dispatch Bottles Should Be Greater Than Zero !!! ",
									"Dispatch Bottles Should Be Greater Than Zero !!!"));
			validateInput1 = false;
		} 

		return validateInput1;

	}

	public void setValidateInput1(boolean validateInput1) {
		this.validateInput1 = validateInput1;
	}
	
	public boolean eco_flag;
	

	public void saveMethod() {
		try {
			if (isValidateInput1()) {
				if(this.dt_date.after(this.validtilldt_date)){
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Valid Till Date !!", "Invalid Valid Till Date !!"));
				}
				else{
					
					boolean ecoflg=false;
					
					
				if(this.vch_to.equalsIgnoreCase("PR") && !this.vch_from.equalsIgnoreCase("CL2") && impl.checkPRBrand(this)){
					impl.saveMethodImpl(this);
					this.displaylist = impl.displaylistImpl(this);
				}
				else if(this.vch_to.equalsIgnoreCase("RT")  ){
					impl.saveMethodImpl(this);
					this.displaylist = impl.displaylistImpl(this);
				}
				/*else if(this.vch_to.equalsIgnoreCase("RT") &&  this.vch_from.equalsIgnoreCase("CL2") && impl.checkmgq(this)){
					impl.saveMethodImpl(this);
					this.displaylist = impl.displaylistImpl(this);
				}*/
				
				else if(this.vch_to.equalsIgnoreCase("BRC")){
						
					/*for(int i =0; i <this.displaylist.size();i++){
				
							 GatepassToDistrict_FL2_FL2B_activeDt dt = (GatepassToDistrict_FL2_FL2B_activeDt) this.displaylist.get(i);
						if(!check_economy(dt.getBrandIdDt())){
							eco_flag = true;
							if(this.challan_no == null || this.challan_dt==null || this.challan_amt==null){
							ecoflg=true;
							FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"FILL CHALLAN INFORMATION!!! ","FILL CHALLAN INFORMATION!!!"));
							
						return;
						}
					}
					}
						if(!ecoflg){*/
							if(impl.checklic(this).equalsIgnoreCase("F")){
							impl.saveMethodImpl(this);
							this.displaylist = impl.displaylistImpl(this);
							}else{
								FacesContext.getCurrentInstance().addMessage(
										null,
										new FacesMessage(FacesMessage.SEVERITY_ERROR,
												"Validate  HBR ID!!! ",
												"Validate  HBR ID!!!"));
								return;
							}
							//}
					}
				
					
			 
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public boolean check_economy(int id){
		if(impl.check_economy(id))
			return true;
		return false;
	}

	public void clearAll() {
		this.disable_flag=false;
		this.flagshop=false;
		this.shopno="";
		this.shopName="";
		this.fl2_fl2bName = null;
		this.fl2_fl2bAdrs = null;
		this.validtilldt_date = null;
		this.vch_to = "";
		this.vch_to_lic_no = "";
		this.displaylist.clear();
		this.routeDtl = null;
		this.vehicleNo = null;
		this.vehicleDrvrName = null;
		this.vehicleAgencyNmAdrs = null;
		this.district1 = 0;
		this.district2 = 0;
		this.district3 = 0;
		this.districtLic = 0;
		this.tierWeight = 0.0;
		this.netWeight = 0.0;
		this.grossWeight = 0.0;
		this.licenseeName = null;
		this.licenseeAdrs = null;
		this.licenseeId = 0;
		this.brc_to_lic = null;
		this.licNmbrList.clear();
		this.brclicNmbrList.clear();
		this.advance_duty=0;
	//	this.table2List.clear();
		dt_date =new Date();
		table_dt=new Date();
		this.total_permit_fee=0.0;

	}

	private ArrayList table2List = new ArrayList();
	private Date printDate;
	private String printGatePassNo;

	private String pdfname;
	private String pdfDraft;
	private boolean listFlagForPrint = true;
	private String scanGatePassNo;
	private String shopid;
	private String shoptype;
	
	
	
	
	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid;
	}

	public String getShoptype() {
		return shoptype;
	}

	public void setShoptype(String shoptype) {
		this.shoptype = shoptype;
	}

	public String getScanGatePassNo() {
		return scanGatePassNo;
	}

	public void setScanGatePassNo(String scanGatePassNo) {
		this.scanGatePassNo = scanGatePassNo;
	}

	public ArrayList getTable2List() {
		if (this.listFlagForPrint == true) {
			this.table2List = impl.getTable2List(this);
			this.listFlagForPrint = false;
		}

		return table2List;
	}

	public void setTable2List(ArrayList table2List) {
		this.table2List = table2List;
	}

	public Date getPrintDate() {
		return printDate;
	}

	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}

	public String getPrintGatePassNo() {
		return printGatePassNo;
	}

	public void setPrintGatePassNo(String printGatePassNo) {
		this.printGatePassNo = printGatePassNo;
	}

	public String getPdfname() {
		return pdfname;
	}

	public void setPdfname(String pdfname) {
		this.pdfname = pdfname;
	}

	public boolean isListFlagForPrint() {
		return listFlagForPrint;
	}

	public void setListFlagForPrint(boolean listFlagForPrint) {
		this.listFlagForPrint = listFlagForPrint;
	}

private String pdfname_CaseCode;

	public String getPdfname_CaseCode() {
		return pdfname_CaseCode;
	}

	public void setPdfname_CaseCode(String pdfname_CaseCode) {
		this.pdfname_CaseCode = pdfname_CaseCode;
	}
	
	private String vchToPrint;
	
	public String getVchToPrint() {
		return vchToPrint;
	}

	public void setVchToPrint(String vchToPrint) {
		this.vchToPrint = vchToPrint;
	}

	public void printReport(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		GatepassToDistrict_FL2_FL2B_activeDt dt = (GatepassToDistrict_FL2_FL2B_activeDt) this.table2List
				.get(uiTable.getRowIndex());

		this.setPrintDate(dt.getCurrentDate());
		this.setPrintGatePassNo(dt.getGatepassNo());
		this.printFlag = true;
		this.setVchToPrint(dt.getVchTO());
		if (impl.printReport(this, dt) == true) {
			dt.setPrintFlag(true);
		}  else {
			dt.setPrintFlag(false); 
		}
		this.viewReportFlag = true;
	}

	public void printDraftReport(ActionEvent e) {

		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		GatepassToDistrict_FL2_FL2B_activeDt dt = (GatepassToDistrict_FL2_FL2B_activeDt) this.table2List
				.get(uiTable.getRowIndex());

		this.setPrintDate(dt.getCurrentDate());
		this.setPrintGatePassNo(dt.getGatepassNo());
		// System.out.println("-------- action gate pass ----"+this.getPrintGatePassNo());
		if (impl.printDraftReport(this, dt) == true) {
			dt.setPrintDraftFlagDt(true);
		} else {
			dt.setPrintDraftFlagDt(false);

		}
		this.printDraftFlag = true;
		this.scanUploadFlag = true;

	}

	private boolean printFlag;
	private boolean printDraftFlag;
	private boolean scanUploadFlag;
	private boolean uploaderFlag;
	private boolean submitFlag;
	private boolean cancelFlag;
	private boolean finalsubmit;
	private boolean tableFlag;
	private boolean deleteFlag;
	private boolean kidnlyUploadFlag;
	private boolean gatePassFlag;
	private boolean viewDraftFlag;
	private boolean viewReportFlag;
	private int excelCases;
	private int recieveCases;
	private boolean printDraft;

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public int getRecieveCases() {
		return recieveCases;
	}

	public void setRecieveCases(int recieveCases) {
		this.recieveCases = recieveCases;
	}

	public boolean isViewDraftFlag() {
		return viewDraftFlag;
	}

	public void setViewDraftFlag(boolean viewDraftFlag) {
		this.viewDraftFlag = viewDraftFlag;
	}

	public boolean isViewReportFlag() {
		return viewReportFlag;
	}

	public void setViewReportFlag(boolean viewReportFlag) {
		this.viewReportFlag = viewReportFlag;
	}

	public boolean isKidnlyUploadFlag() {
		return kidnlyUploadFlag;
	}

	public void setKidnlyUploadFlag(boolean kidnlyUploadFlag) {
		this.kidnlyUploadFlag = kidnlyUploadFlag;
	}

	public boolean isGatePassFlag() {
		return gatePassFlag;
	}

	public void setGatePassFlag(boolean gatePassFlag) {
		this.gatePassFlag = gatePassFlag;
	}

	// ------------------ excel scan and upload ----------------------------
	public void scanAndUpload(ActionEvent ae) {
		UIDataTable uiTable = (UIDataTable) ae.getComponent().getParent()
				.getParent();
		GatepassToDistrict_FL2_FL2B_activeDt dt = (GatepassToDistrict_FL2_FL2B_activeDt) this.table2List
				.get(uiTable.getRowIndex());

		this.setScanGatePassNo(dt.getGatepassNo());
		this.setShopid(dt.getLicenseNo());
		this.setShoptype(dt.getVchTO());
		this.submitFlag = true;
		this.cancelFlag = true;
		this.uploaderFlag = true;
		this.tableFlag = true;
		this.finalsubmit = true;
		this.kidnlyUploadFlag = true;
		this.gatePassFlag = true;
		this.scanUploadFlag = true;
		//this.getVal = impl.getExcelData(this);
		// this.scanUploadFlag=true;
		// this.printDraft=true;

	}

	// ------------------- excel submit------------------

	public String importExcel() {
		/*if(this.fl2LicenseType.equalsIgnoreCase("FL2")){
			new GatepassToDistrict_FL2_FL2B_activeImpl().saveExcelDatafl(this);
		}else{*/
		new GatepassToDistrict_FL2_FL2B_activeImpl().saveExcelData(this);
		//}
		this.tableFlag = true;
		this.submitFlag = false;
		this.cancelFlag = true;
		this.gatePassFlag = false;
		this.kidnlyUploadFlag = false;
		this.scanUploadFlag = true;

		this.finalsubmit = true;
		this.uploaderFlag = false;

		return "";
	}

	// -------------------final submit------------------
	public void finalSubmitcl2(ActionEvent ae) {

		UIDataTable uiTable = (UIDataTable) ae.getComponent().getParent()
				.getParent();
		GatepassToDistrict_FL2_FL2B_activeDt dt = (GatepassToDistrict_FL2_FL2B_activeDt) this.table2List
				.get(uiTable.getRowIndex());

		this.setScanGatePassNo(dt.getGatepassNo());
		GatepassToDistrict_FL2_FL2B_activeImpl impl = new GatepassToDistrict_FL2_FL2B_activeImpl();
		 
		listFlagForPrint = true;
	}

	public void finalSubmit() {

		GatepassToDistrict_FL2_FL2B_activeImpl impl = new GatepassToDistrict_FL2_FL2B_activeImpl();
		// impl.recieveCases(this);
		if (impl.excelCases(this) == impl.recieveCases(this)) {

			if (impl.updateFL3(this) == true) {
				this.printFlag = true;
				this.scanUploadFlag = false;
				this.submitFlag = false;
				this.cancelFlag = false;
				this.finalsubmit = true;
				this.uploaderFlag = false;
				this.finalsubmit = false;
				this.deleteFlag = false;
				this.tableFlag = false;
				this.kidnlyUploadFlag = false;
				this.gatePassFlag = false;
				this.printFlag = true;
				listFlagForPrint = true;

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Data Save Successfully",
								"Data Save Successfully"));
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Error Occured while saving !!! ",
								"Error Occured while saving !!!"));
			}
		} else

		{
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									"No.of casecodes uploaded by you is not equal to number of cases mentioned in gatepass.",
									"No.of casecodes uploaded by you is not equal to number of cases mentioned in gatepass."));
		}
	}

	private String excelFilename;
	private String excelFilepath;

	public String getExcelFilename() {
		return excelFilename;
	}

	public void setExcelFilename(String excelFilename) {
		this.excelFilename = excelFilename;
	}

	public String getExcelFilepath() {
		return excelFilepath;
	}

	public void setExcelFilepath(String excelFilepath) {
		this.excelFilepath = excelFilepath;
	}

	// --------------------------------------------delete excel
	// data------------------
	public void delete() {
		new GatepassToDistrict_FL2_FL2B_activeImpl().deleteData(this);
		this.tableFlag = false;
		this.finalsubmit = false;
		this.cancelFlag = false;
		this.gatePassFlag = false;
		this.kidnlyUploadFlag = false;
		this.uploaderFlag = false;
		this.submitFlag = false;
		this.scanUploadFlag = false;
		listFlagForPrint = true;
		this.getVal.clear();
	}

	public void uploadExcel(UploadEvent event) {

		try {
			int size = 0;
			int counter = 0;
			UploadItem item = event.getUploadItem();

			String FullfileName = item.getFileName();

			String path = item.getFile().getPath();

			String fileName = FullfileName.substring(FullfileName
					.lastIndexOf("\\") + 1);

			ExternalContext con = FacesContext.getCurrentInstance()
					.getExternalContext();

			ServletContext sCon = (ServletContext) con.getContext();

			 
			size = item.getFileSize();
			this.excelFilename = FullfileName;
			this.excelFilepath = path;

		} catch (Exception ee) {
		 
			 
		} finally {

		}

	}

	private boolean valFlag;
	public ArrayList getVal = new ArrayList();
	public ArrayList tempList = new ArrayList();
	
	
	
	
	
	public ArrayList getTempList() {
		return tempList;
	}

	public void setTempList(ArrayList tempList) {
		this.tempList = tempList;
	}

	public ArrayList getGetVal() {
	
		return getVal;
	}

	public void setGetVal(ArrayList getVal) {
		this.getVal = getVal;
	}

	public boolean isPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(boolean printFlag) {
		this.printFlag = printFlag;
	}

	public boolean isScanUploadFlag() {
		return scanUploadFlag;
	}

	public void setScanUploadFlag(boolean scanUploadFlag) {
		this.scanUploadFlag = scanUploadFlag;
	}

	public boolean isUploaderFlag() {
		return uploaderFlag;
	}

	public void setUploaderFlag(boolean uploaderFlag) {
		this.uploaderFlag = uploaderFlag;
	}

	public boolean isSubmitFlag() {
		return submitFlag;
	}

	public void setSubmitFlag(boolean submitFlag) {
		this.submitFlag = submitFlag;
	}

	public boolean isCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public boolean isFinalsubmit() {
		return finalsubmit;
	}

	public void setFinalsubmit(boolean finalsubmit) {
		this.finalsubmit = finalsubmit;
	}

	public boolean isTableFlag() {
		return tableFlag;
	}

	public void setTableFlag(boolean tableFlag) {
		this.tableFlag = tableFlag;
	}

	public boolean isPrintDraftFlag() {
		return printDraftFlag;
	}

	public void setPrintDraftFlag(boolean printDraftFlag) {
		this.printDraftFlag = printDraftFlag;
	}

	public String getPdfDraft() {
		return pdfDraft;
	}

	public void setPdfDraft(String pdfDraft) {
		this.pdfDraft = pdfDraft;
	}

	public int getExcelCases() {
		return excelCases;
	}

	public void setExcelCases(int excelCases) {
		this.excelCases = excelCases;
	}

	public boolean isValFlag() {
		return valFlag;
	}

	public void setValFlag(boolean valFlag) {
		this.valFlag = valFlag;
	}

	public boolean isPrintDraft() {
		return printDraft;
	}

	public void setPrintDraft(boolean printDraft) {
		this.printDraft = printDraft;
	}

	
	private String vch_TO_Print=null;
	
	
	public String getVch_TO_Print() {
		return vch_TO_Print;
	}

	public void setVch_TO_Print(String vch_TO_Print) {
		this.vch_TO_Print = vch_TO_Print;
	}
	

	public void printDraft(ActionEvent e) {
		this.vch_TO_Print=null;
		
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		GatepassToDistrict_FL2_FL2B_activeDt dt = (GatepassToDistrict_FL2_FL2B_activeDt) this.table2List
				.get(uiTable.getRowIndex());

		this.setPrintDate(dt.getDt_date());
		this.setPrintGatePassNo(dt.getGatepassNo());
		
		this.setVch_TO_Print(dt.getVchTO());

		 
		

		if (impl.printDraftReport(this, dt) == true) {
			dt.setDraftprintFlag(true);

		} else {

			dt.setDraftprintFlag(false);

		}

	}

	public String getDraftPdfname() {
		return DraftPdfname;
	}

	public void setDraftPdfname(String draftPdfname) {
		DraftPdfname = draftPdfname;
	}

	// ----------------------------code for cancel
	// gatepass----------------------

	public void cancelGatepass(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		GatepassToDistrict_FL2_FL2B_activeDt dt = (GatepassToDistrict_FL2_FL2B_activeDt) this.table2List
				.get(uiTable.getRowIndex());

		this.setPrintDate(dt.getDt_date());
		this.setPrintGatePassNo(dt.getGatepassNo());

		impl.cancelGatepassImpl(this);
		
	}
	// ==========================code for csv===================================

		private String csvFilename;
		private String csvFilepath;
		private String exclcsv="C";

		public String getCsvFilename() {
			return csvFilename;
		}

		public void setCsvFilename(String csvFilename) {
			this.csvFilename = csvFilename;
		}

		public String getCsvFilepath() {
			return csvFilepath;
		}

		public void setCsvFilepath(String csvFilepath) {
			this.csvFilepath = csvFilepath;
		}

		public String getExclcsv() {
			
			return exclcsv;
		}

		public void setExclcsv(String exclcsv) {
			this.exclcsv = exclcsv;
		}

		public void uploadCsv(UploadEvent event) {

			try {
				int size = 0;
				int counter = 0;
				UploadItem item = event.getUploadItem();

				String FullfileName = item.getFileName();

				String path = item.getFile().getPath();

				String fileName = FullfileName.substring(FullfileName
						.lastIndexOf("\\") + 1);

				ExternalContext con = FacesContext.getCurrentInstance()
						.getExternalContext();

				ServletContext sCon = (ServletContext) con.getContext();

				 
				size = item.getFileSize();
				this.setCsvFilename(FullfileName);
				this.setCsvFilepath(path);

			} catch (Exception ee) {
				ee.printStackTrace();
				System.out.println("exception in upload@");
			} finally {

			}

		}
		 ArrayList sourceList = new ArrayList<String>();
		 
		 
		 
		public ArrayList getSourceList() {
			return sourceList;
		}

		public void setSourceList(ArrayList sourceList) {
			this.sourceList = sourceList;
		}
		
		/*
		 * @author atul
		 * @date 04-12-2019
		 * Assigned By Mitali Mam
		 * Work Definition :: In the below method implement the logic of if gatepass no exist then csv file can not upload 
		 * 
		 *  other wise upload 
		 * 
		 * 
		 */
		
		
		public int checkData()
		{
			int status=0;
			int i=0;
			int j=0;
			int k=0;
			try{
				if(this.getFl2LicenseType().equalsIgnoreCase("CL2")){
					i = impl.getExcelData(this.getScanGatePassNo().toUpperCase().trim()).size();	
				}else if(this.getFl2LicenseType().equalsIgnoreCase("FL2")){
					j = impl.getExcelDatafl2(this.getScanGatePassNo().toUpperCase().trim()).size();	
				}
				else if(this.getFl2LicenseType().equalsIgnoreCase("FL2B")){
					k = impl.getExcelDatafl2b(this.getScanGatePassNo().toUpperCase().trim()).size();	
				}
				
			if(i==0&&j==0&&k==0)	
			{
				status=1;
			}
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return status;
		}
		
		
		
		
		
		
		
		
		
		

		public String csvSubmit() throws IOException {
			/*	if(this.fl2LicenseType.equalsIgnoreCase("FL2")){
			impl.saveCSVfl(this);	
		}else{*/
		boolean checkdata=	impl.checkUploadedData(this);
		if(checkdata)
		{
		tempList.clear();
		ArrayList matchdata=new ArrayList<>();
		if(checkData()==1)
		{
		impl.saveCSV(this);
		}else{
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data All Ready Uploaded On THAT GATEPASS No first clear old data Then again Upload ", "Data All Ready Uploaded On THAT GATEPASS No first clear old data Then again Upload "));
		}
	 
			if(this.getFl2LicenseType().equalsIgnoreCase("CL2")){
				this.getVal = impl.getExcelData(this.getScanGatePassNo().toUpperCase().trim());	
			}else if(this.getFl2LicenseType().equalsIgnoreCase("FL2")){
				this.getVal = impl.getExcelDatafl2(this.getScanGatePassNo().toUpperCase().trim());	
			}
			else if(this.getFl2LicenseType().equalsIgnoreCase("FL2B")){
				this.getVal = impl.getExcelDatafl2b(this.getScanGatePassNo().toUpperCase().trim());	
			}
			
			 for(int ik=0;ik<getVal.size();ik++)
			 {
			 GatepassToDistrict_FL2_FL2B_activeDt dt=	 (GatepassToDistrict_FL2_FL2B_activeDt)this.getGetVal().get(ik);
			     
			 String casecode=dt.getCasecode().trim();
			 Date date=dt.getCasecodedt();
			 SimpleDateFormat sdf=new SimpleDateFormat("yyMMdd");
			      
			String datt= sdf.format(date);
			 
			
			 for(int p=0; p<this.tempList.size();p++)
			 {
				String s= (String)this.tempList.get(p);
				
				String dat=s.substring(16, 22).trim();
				String caseno=s.substring(26, s.length()).trim();
			  if(datt.equals(dat)&&caseno.equals(casecode))
				 {
			     this.tempList .remove(this.tempList.get(p) );
				 }
			 }
			     sourceList=tempList;
			   
			 }
		 
		}
	//	}
		this.scanUploadFlag = true;
		
		
//this.getVal = impl.getExcelData(this);
		return "";
	}
		public String shopno;
		private boolean flagshop;
		private boolean flaglicense;
		public String shopName;
		public String shopTyp;
	
		
		
		public String getShopTyp() {
			return shopTyp;
		}

		public void setShopTyp(String shopTyp) {
			this.shopTyp = shopTyp;
		}

		public String getShopName() {
			return shopName;
		}

		public void setShopName(String shopName) {
			this.shopName = shopName;
		}

		public boolean isFlagshop() {
			return flagshop;
		}

		public void setFlagshop(boolean flagshop) {
			this.flagshop = flagshop;
		}

		public boolean isFlaglicense() {
			return flaglicense;
		}

		public void setFlaglicense(boolean flaglicense) {
			this.flaglicense = flaglicense;
		}
		public String getShopno() {
			return shopno;
		}
		
		public void setShopno(String shopno) {
			this.shopno = shopno;
		}
		public void fetch()
		{
		if(this.shopno.length()>0 && this.shopno != null && this.shopno !="")
		{
			if(this.shopno.charAt(0)==' ' || this.shopno.charAt(this.shopno.length()-1)==' ')
			{
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(
				"enter shop id without spaces","enter shop id without spaces"));
			}
				else
				{
					impl.getShopDetails(this);
					
					String s = this.getShopno();					
					 
					
					String newShopId = s.replaceFirst("^0+(?!$)", "");
					 
			
					this.setShopnoNew(newShopId);
				}
			
		}
		
		else
		{
			FacesContext
			.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage(
							"enter valid shop id  ",
							"enter valid shop id  "));
		}
		}
		
		private String vch_licence_no;

		public String getVch_licence_no() {
			return vch_licence_no;
		}

		public void setVch_licence_no(String vch_licence_no) {
			this.vch_licence_no = vch_licence_no;
		}
		
		
		public boolean rtFlag;
		public boolean brcFlag;
		public String shopnoNew;

		public boolean isRtFlag() {
			return rtFlag;
		}

		public void setRtFlag(boolean rtFlag) {
			this.rtFlag = rtFlag;
		}

		public boolean isBrcFlag() {
			return brcFlag;
		}

		public void setBrcFlag(boolean brcFlag) {
			this.brcFlag = brcFlag;
		}

		public String getShopnoNew() {
			return shopnoNew;
		}

		public void setShopnoNew(String shopnoNew) {
			this.shopnoNew = shopnoNew;
		}
		
		
		public void fetch1()
		{
		if(this.vch_to_lic_no.length()>0 && this.vch_to_lic_no != null && this.vch_to_lic_no !="")
		{
			if(this.vch_to_lic_no.charAt(0)==' ' || this.vch_to_lic_no.charAt(this.vch_to_lic_no.length()-1)==' ')
			{
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(
				"enter HBR ID without spaces","enter  HBR ID without spaces"));
			}
				else
				{
					if(impl.checklic(this).equalsIgnoreCase("F")){
					       this.advance_duty=impl.getAdvanceSpclDuty(this);
					}
					
					this.setVch_to_lic_noNew(this.vch_to_lic_no);
				}
			
		}
		
		else
		{
			FacesContext
			.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage(
							"enter valid  HBR ID  ",
							"enter valid  HBR ID  "));
		}
		}




}
