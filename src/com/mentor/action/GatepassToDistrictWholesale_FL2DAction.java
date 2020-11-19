package com.mentor.action;

import java.io.IOException;
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

import com.mentor.datatable.GatepassToDistrictWholesale_FL2DDataTable;
import com.mentor.impl.GatepassToDistrictWholesale_FL2DImpl;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Validate;

public class GatepassToDistrictWholesale_FL2DAction {

	GatepassToDistrictWholesale_FL2DImpl impl = new GatepassToDistrictWholesale_FL2DImpl();

	private String hidden;
	private String dispatch = "0";

	private String licenceenm;
	private String licenceeadd;
	private String session;
	private boolean withflag;
	private boolean underflag;
	private String authName;
	public String undr_bond = "2";
	private boolean licNoflg;
	private boolean authNmflg;
	private int licenceeid;

	private String vehicleDrvrName;
	private String vehicleAgencyNmAdrs;
	private int district1;
	private int district2;
	private int district3;
	private String routeDtl;
	private String vehicleNo;
	private String officrEmail;

	public String getOfficrEmail() {
		return officrEmail;
	}

	public void setOfficrEmail(String officrEmail) {
		this.officrEmail = officrEmail;
	}

	private int districtLic;

	public int getDistrictLic() {
		return districtLic;
	}

	public void setDistrictLic(int districtLic) {
		this.districtLic = districtLic;
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

	public double getDb_total_add_value() {

		double addduty = 0.0;
		try {
			// this.db_total_value=Math.round(this.getSum()*100.0)/100.0;
			for (int i = 0; i < this.displaylist.size(); i++) {
				GatepassToDistrictWholesale_FL2DDataTable dt = (GatepassToDistrictWholesale_FL2DDataTable) this
						.getDisplaylist().get(i);

				addduty += dt.getCalculated_add_duty();
				// System.out.println("duty----------------" + addduty);
			}
			db_total_add_value = Math.round(addduty);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return db_total_add_value;

	}

	public void setDb_total_add_value(double db_total_add_value) {
		this.db_total_add_value = db_total_add_value;
	}

	public int getLicenceeid() {		
		return licenceeid;
	}

	public void setLicenceeid(int licenceeid) {
		this.licenceeid = licenceeid;
	}

	public String getLicenceenm() {		
		return licenceenm;
	}

	public void setLicenceenm(String licenceenm) {
		this.licenceenm = licenceenm;
	}

	public String getLicenceeadd() {		
		return licenceeadd;
	}

	public void setLicenceeadd(String licenceeadd) {
		this.licenceeadd = licenceeadd;
	}

	public boolean isLicNoflg() {
		return licNoflg;
	}

	public void setLicNoflg(boolean licNoflg) {
		this.licNoflg = licNoflg;
	}

	public boolean isAuthNmflg() {
		return authNmflg;
	}

	public void setAuthNmflg(boolean authNmflg) {
		this.authNmflg = authNmflg;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getUndr_bond() {
		return undr_bond;
	}

	public void setUndr_bond(String undr_bond) {
		this.undr_bond = undr_bond;
	}

	public boolean isWithflag() {
		return withflag;
	}

	public void setWithflag(boolean withflag) {
		this.withflag = withflag;
	}

	public boolean isUnderflag() {
		return underflag;
	}

	public void setUnderflag(boolean underflag) {
		this.underflag = underflag;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getDispatch() {
		return dispatch;
	}

	public void setDispatch(String dispatch) {
		this.dispatch = dispatch;
	}

	public String getHidden() {
		try {
			impl.getDetails(this);
			impl.getSeasonDetails(this);
			impl.getEmailDetails(this);
			
			if (this.brc_to_lic != null) {
				impl.licenceedet(this, this.brc_to_lic);
				System.out.println("-----------------------------------------"+1);
			}
			
			
			
			/*else{
				System.out.println("-----------------------------------------"+3);
				this.licenceenm = null;
				this.licenceeadd = null;
				this.licenceeid = 0;
			}*/
		} catch (Exception e) {
		}
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public int dist_id;
	public String name;
	public String address = "NA";

	public String vch_gatepass_no;
	public Date dt_date = new Date();
	public String vch_from;
	public String vch_to;
	public String vch_from_lic_no = "NA";
	public String vch_to_lic_no;
	public boolean flag = true;
	public String vch_bond = "1";
	public boolean addflag = true;

	public Date validtilldt_date;

	public Date getValidtilldt_date() {
		return validtilldt_date;
	}

	public void setValidtilldt_date(Date validtilldt_date) {
		this.validtilldt_date = validtilldt_date;
	}

	public void clearAll() {
		// this.dist_id=0;
		this.shopno="";
		this.shopName="";
		this.licenseeName="";
		this.licenseeAdrs="";
		this.name = "";
		this.address = "";
		this.vch_gatepass_no = "";
		this.validtilldt_date = null;
		this.dt_date = new Date();;
		this.vch_from = "";

		licenceenm = null;
		licenceeadd = null;
		this.vch_to = "";
		this.vch_from_lic_no = "";
		this.vch_to_lic_no = "";
		this.vch_bond = "";
		this.fromliclist.clear();
		this.toliclist.clear();
		this.displaylist.clear();
		this.db_total_value = 0;
		this.db_total_add_value = 0;
		this.sum = 0;
		this.setSum(0.0);
		this.setDb_total_value(0.0);
		this.routeDtl = null;
		this.vehicleNo = null;
		this.vehicleDrvrName = null;
		this.vehicleAgencyNmAdrs = null;
		this.district1 = 0;
		this.district2 = 0;
		this.district3 = 0;
		this.brc_to_lic = null;
		this.brclicNmbrList.clear();
		this.drpdwnFlg = false;
		this.drpdwnFlg1 = false;
		this.grossWeight = 0;
		this.tierWeight = 0;
		this.netWeight = 0;

		this.districtLic = 0;

		this.printFlag = false;
		this.listFlagForPrint = true;

		this.printFlag = false;

	}

	// ---------------------save----------------

	public void saveMethod() {
		try {
			if(this.vch_to.equalsIgnoreCase("RT"))
			{
				if (isValidateInput()) 
				{
				impl.saveMethodImpl(this);
				}
				
			}
			
			else 
			{
				if (isValidateInput1()) 
				{
				impl.saveMethodImpl(this);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// --------------------------------------------------------------------------
	private boolean validateInput1;

	private double grossWeight = 0;
	private double tierWeight = 0;

	private double netWeight = 0;

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

	public boolean isValidateInput1() {
		validateInput1 = true;

		if (!(Validate.validateStrReq("radiofrom", this.getVch_from())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("radioto", this.getVch_to())))
			validateInput1 = false;

		if (this.getVch_from_lic_no() == null
				|| this.getVch_from_lic_no().equals(null)
				|| this.getVch_from_lic_no().equalsIgnoreCase("0")) {
			ResourceUtil.addErrorMessage(Constants.SELECT_FROM,
					Constants.SELECT_FROM);
			validateInput1 = false;
		}
		if (isLicNoflg()) {
			if (this.getVch_to_lic_no() == null
					|| this.getVch_to_lic_no().equals(null)
					|| this.getVch_to_lic_no().equalsIgnoreCase("0")) {
				ResourceUtil.addErrorMessage(Constants.SELECT_TO,
						Constants.SELECT_TO);
				validateInput1 = false;
			}
		}
		
		if(isDrpdwnFlg()){
			if (!(Validate.validateStrReq("licenseno", this.getVch_to_lic_no())))
				validateInput1 = false;
		}
		if(isDrpdwnFlg1()){
			if (!(Validate.validateStrReq("licenseno", this.getBrc_to_lic())))
				validateInput1 = false;
		}
		
		if(this.getDistrictLic()==0)
		{
			if (!(Validate.validateInteger("licencee_district", this.getDistrictLic())))
				validateInput1 = false;
		}
		
		else if (!(Validate.validateStrReq("firmname", this.getLicenceenm())))
			validateInput1 = false;
		else if (!(Validate
				.validateStrReq("firmaddress", this.getLicenceeadd())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("routedtl", this.getRouteDtl())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("vehiclenmbr", this.getVehicleNo())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("vehicledriver",
				this.getVehicleDrvrName())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("agencyNameAddress",
				this.getVehicleAgencyNmAdrs())))
			validateInput1 = false;
		else if (!(Validate
				.validateDouble("grossWeight", this.getGrossWeight())))
			validateInput1 = false;
		else if (!(Validate.validateDouble("tierweight", this.getTierWeight())))
			validateInput1 = false;
		else if (!(this.grossWeight > this.getTierWeight())) {
			validateInput1 = false;

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"GROSS_LESS_THAN_TIER!!! ",
							"GROSS_LESS_THAN_TIER !!!"));
		}

		/*
		 * for(int i=0;i<this.displaylist.size();i++){
		 * GatepassToDistrictWholesale_FL2DDataTable
		 * dt=(GatepassToDistrictWholesale_FL2DDataTable)displaylist.get(i);
		 * if(dt.getBalance()<=0){
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(FacesMessage.SEVERITY_ERROR,"Invalid Balance !!! "
		 * ,"Invalid Balance !!!")); validateInput1=false; }
		 * 
		 * 
		 * if (dt.getInt_bottle_avaliable() >= dt.getDispatchbottls()) {
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(FacesMessage
		 * .SEVERITY_ERROR,"Dispatch Bottles Less Then Available Bottle !!! "
		 * ,"Dispatch Bottles Less Then Available Bottle !!!")); validateInput1
		 * = false; }
		 * 
		 * }
		 */

		int sumBottles = 0;
		int sumBoxes = 0;

		for (int i = 0; i < this.displaylist.size(); i++) {
			GatepassToDistrictWholesale_FL2DDataTable dt = (GatepassToDistrictWholesale_FL2DDataTable) displaylist
					.get(i);

			sumBottles += dt.getDispatchbottls();
			sumBoxes += dt.getDispatchbox();

			if (dt.getBalance() <= 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Invalid Balance !!! ", "Invalid Balance !!!"));
				validateInput1 = false;

			} else if (dt.getDispatchbox() > dt.getBoxAvailable()) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										"Dispatch Boxes Should Be Less Than Available Boxes !!! ",
										"Dispatch Boxes Should Be Less Than Available Boxes !!!"));
				validateInput1 = false;

			}
		}

		if (sumBoxes == 0) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Dispatch Boxes Should Be Greater Than Zero !!! ",
							"Dispatch Boxes Should Be Greater Than Zero !!!"));
			validateInput1 = false;
		} else if (sumBottles == 0) {
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

		/*
		 * if( this.vch_quantitySpiritType=="Q"){
		 * if(!(Validate.validateDouble("quantity"
		 * ,this.getDb_quantity_input())))validateInput1=false; }
		 * 
		 * if( this.vch_quantitySpiritType=="C"){
		 * if(!(Validate.validateDouble("quantity"
		 * ,this.getDb_costOf_spirit())))validateInput1=false; }
		 * 
		 * if(this.getVch_vat_tank_no().equalsIgnoreCase("0")){
		 * ResourceUtil.addErrorMessage(Constants.DENATURE_VAT,
		 * Constants.DENATURE_VAT); validateInput1=false; }
		 */

		// else
		// if(!(Validate.validateDouble("removebl",this.getDb_removeBL())))validateInput1=false;
		// else
		// if(!(Validate.validateStrReq("upoutide",this.getUpoutside())))validateInput1=false;

		/*
		 * if(this.getDb_removeBL()>this.getDb_quantity()){
		 * 
		 * ResourceUtil.addErrorMessage(Constants.AL_BL_CHECK,
		 * Constants.AL_BL_CHECK); validateInput1=false; }
		 */
		/*
		 * if(this.getVch_distillary()=="0"){
		 * ResourceUtil.addErrorMessage(Constants.SELECT_DISITLLARY,
		 * Constants.SELECT_DISITLLARY); validateInput1=false; //
		 * if(!(Validate.validateStrReq
		 * ("selectdistillary",this.getVch_distillary())))validateInput1=false;
		 * }
		 * 
		 * else if(!(Validate.validateStrReq("permintnumberrequired",this.
		 * getVch_permit_number())))validateInput1=false; else
		 * if(!(Validate.validateDate
		 * ("permitdaterequired",this.getDt_permit_date
		 * ())))validateInput1=false; else
		 * if(!(Validate.validateDouble("quantityimportrequired"
		 * ,this.getDb_quantity_import())))validateInput1=false;
		 */

		return validateInput1;
	}

	public void setValidateInput1(boolean validateInput1) {
		this.validateInput1 = validateInput1;
	}

	// ===========list import
	ArrayList fromliclist = new ArrayList();

	private String districtcode;

	ArrayList districtList = new ArrayList();

	public ArrayList getDistrictList() {
		districtList = impl.getDistList();
		return districtList;
	}

	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}

	public String getDistrictcode() {
		return districtcode;
	}

	public void setDistrictcode(String districtcode) {
		this.districtcode = districtcode;
	}

	public ArrayList getFromliclist() {

		this.fromliclist = impl.fromliclistImpl(this);

		return fromliclist;
	}

	public String fromListMethod(ValueChangeEvent vce) {
		this.db_total_value = 0;
		this.sum = 0;
		this.setSum(0.0);
		this.setDb_total_value(0.0);
		this.vch_to = null;
		Object obj = vce.getNewValue();
		String s = (String) obj;

		if (s.equalsIgnoreCase("FL1A")) {
			this.vch_from = "FL1A";
			this.fromliclist = impl.fromliclistImpl(this);
			this.displaylist = impl.displaylistImpl(this, vch_from_lic_no);
		}

		else if (s.equalsIgnoreCase("FL1")) {
			this.vch_from = "FL1";
			this.fromliclist = impl.fromliclistImpl(this);

			this.displaylist = impl.displaylistImpl(this, vch_from_lic_no);
			// System.out.println("ijhfejrfeijfei");

		}

		else {
		}
		return "";
	}

	private String brc_to_lic="";
	private boolean drpdwnFlg;
	private boolean drpdwnFlg1;
	private ArrayList brclicNmbrList = new ArrayList();
		
	
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
	private boolean drpdwnFlg3;
	
	public boolean isDrpdwnFlg3() 
	{
	return drpdwnFlg3;
	}

	public void setDrpdwnFlg3(boolean drpdwnFlg3) 
	{
	this.drpdwnFlg3 = drpdwnFlg3;
	}

	public String listMethod(ValueChangeEvent vce) {
		System.out.println("hhhhhhhhhhhhhhhhhhh"); 
		
		this.licenceenm = null;
		this.licenceeadd = null;
		this.licenceeid = 0;
		this.brc_to_lic = null;
		this.brclicNmbrList.clear();
		this.vch_to_lic_no = null;		
		this.districtLic = 0;
		
		String val = (String) vce.getNewValue();
		this.displaylist = impl.displaylistImpl(this, val);
		
		if(val.equalsIgnoreCase("FL2") || val.equalsIgnoreCase("FL2B"))
		{
			this.brclicNmbrList = impl.getlicenseNmbr(this, val);
			this.setDrpdwnFlg1(true);
			this.setDrpdwnFlg(false);
			this.setDrpdwnFlg3(true);
			this.setFlagshop(false);
			this.setFlagshopS(true);
		}
		else if(val.equalsIgnoreCase("RT"))
		{
			this.setDrpdwnFlg1(false);
			this.setDrpdwnFlg(false);
			this.setDrpdwnFlg3(false);
		}
		else if(val.equalsIgnoreCase("HBR"))
		{
			this.setFlagshopS(true);
			this.setFlagshop(false);
			this.setDrpdwnFlg1(false);
			this.setDrpdwnFlg(true);
			this.setDrpdwnFlg3(true);
		}
		else{
			
			this.setDrpdwnFlg1(false);
			this.setDrpdwnFlg(true);
			this.setDrpdwnFlg3(true);
			
		}
		
		
		this.licenceenm = null;
		this.licenceeadd = null;
		this.licenceeid = 0;
		
		return "";
	}

	
	
	public String drpdownMethod(ValueChangeEvent vce) {

		String val = (String) vce.getNewValue();
		impl.licenceedet(this, val);

		return "";
	}
	
	
	
	ArrayList toliclist = new ArrayList();

	public ArrayList getToliclist() {
		if (districtcode != null && districtcode.length() > 0) {
			this.toliclist = impl.toliclistImpl2a(this);
		}
		return toliclist;
	}

	// ------------display-------------------------------
	public ArrayList displaylist = new ArrayList();

	public ArrayList getDisplaylist() {

		return displaylist;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isAddflag() {
		return addflag;
	}

	public void setAddflag(boolean addflag) {
		this.addflag = addflag;
	}

	public double getSum_add() {
		return sum_add;
	}

	public void setSum_add(double sum_add) {
		this.sum_add = sum_add;
	}

	public void setDisplaylist(ArrayList displaylist) {
		this.displaylist = displaylist;
	}

	/*
	 * public void calculateDuty(ActionEvent ae){ this.flag=true;
	 * //System.out.println("inside"); UIDataTable
	 * uiTable=(UIDataTable)ae.getComponent().getParent().getParent(); int
	 * rowId=uiTable.getRowIndex(); //System.out.println("rowid"+rowId);
	 * GatePassToDistrictWholesaleDatatable
	 * dt=(GatePassToDistrictWholesaleDatatable) this.displaylist.get(rowId);
	 * 
	 * //System.out.println("printty"+dt.getDb_duty()+
	 * "   "+dt.getInt_dispatch()); dt.setCalculated_duty (
	 * Math.round(((dt.getDb_duty()*dt.getInt_dispatch()))*100.0)/100.0 );
	 * 
	 * 
	 * 
	 * }
	 */

	public double db_total_value = 0;
	public double sum = 0;

	public void calculateTotalBalance(ActionEvent ae) {
		// System.out.println("inside2");

		this.setSum(0);
		for (int i = 0; i < this.displaylist.size(); i++) {
			GatepassToDistrictWholesale_FL2DDataTable dt = (GatepassToDistrictWholesale_FL2DDataTable) this.displaylist
					.get(i);

			this.setSum(this.getSum() + dt.getBalance());

		}

	}

	// ==================FOR ADDITIONAL TOTAL DUTY==========================

	public double db_total_add_value = 0;
	public double sum_add = 0;

	// ///////////////////////////////////////////////////////////////////

	public void setFromliclist(ArrayList fromliclist) {
		this.fromliclist = fromliclist;
	}

	public void setToliclist(ArrayList toliclist) {
		this.toliclist = toliclist;
	}

	public int getDist_id() {
		return dist_id;
	}

	public void setDist_id(int dist_id) {
		this.dist_id = dist_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVch_gatepass_no() {
		return vch_gatepass_no;
	}

	public void setVch_gatepass_no(String vch_gatepass_no) {
		this.vch_gatepass_no = vch_gatepass_no;
	}

	public Date getDt_date() {
		return dt_date;
	}

	public void setDt_date(Date dt_date) {
		this.dt_date = dt_date;
	}

	public String getVch_from() {
		return vch_from;
	}

	public void setVch_from(String vch_from) {
		this.vch_from = vch_from;
	}

	public String getVch_to() {
		return vch_to;
	}

	public void setVch_to(String vch_to) {
		this.vch_to = vch_to;
	}

	public String getVch_from_lic_no() {
		return vch_from_lic_no;
	}

	public void setVch_from_lic_no(String vch_from_lic_no) {
		this.vch_from_lic_no = vch_from_lic_no;
	}

	public String getVch_to_lic_no() {
		return vch_to_lic_no;
	}

	public void setVch_to_lic_no(String vch_to_lic_no) {
		this.vch_to_lic_no = vch_to_lic_no;
	}

	public double getDb_total_value() {
		double duty = 0.0;
		try {
			// this.db_total_value=Math.round(this.getSum()*100.0)/100.0;
			for (int i = 0; i < this.displaylist.size(); i++) {
				GatepassToDistrictWholesale_FL2DDataTable dt = (GatepassToDistrictWholesale_FL2DDataTable) this
						.getDisplaylist().get(i);
				duty += dt.getCalculated_duty();
				// System.out.println("duty----------------"+duty);
			}
			// db_total_value=Math.round(duty);
			db_total_value = duty;

		} catch (Exception e) {
			// TODO: handle exception
		}
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

	public String getVch_bond() {
		return vch_bond;
	}

	public void setVch_bond(String vch_bond) {
		this.vch_bond = vch_bond;
	}

	private boolean listFlagForPrint = true;

	public boolean isListFlagForPrint() {
		return listFlagForPrint;
	}

	public void setListFlagForPrint(boolean listFlagForPrint) {
		this.listFlagForPrint = listFlagForPrint;
	}

	private ArrayList getListWholesale = new ArrayList();

	public ArrayList getGetListWholesale() {

		if (this.listFlagForPrint == true) 
		{
			this.getListWholesale = new GatepassToDistrictWholesale_FL2DImpl()
					.getGatePassWholeSaleListManufacture(this);
			this.listFlagForPrint = false;
		}
		return getListWholesale;
	}

	public void setGetListWholesale(ArrayList getListWholesale) {
		this.getListWholesale = getListWholesale;
	}

	// ------------------------------- pRINT REPORT ------------------

	private boolean printFlag;
	private String pdfname;
	private Date printDate;
	private String printGatePassNo;

	public String getPrintGatePassNo() {
		return printGatePassNo;
	}

	public void setPrintGatePassNo(String printGatePassNo) {
		this.printGatePassNo = printGatePassNo;
	}

	public Date getPrintDate() {
		return printDate;
	}

	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}

	public boolean isPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(boolean printFlag) {
		this.printFlag = printFlag;
	}

	public String getPdfname() {
		return pdfname;
	}

	public void setPdfname(String pdfname) {
		this.pdfname = pdfname;
	}

	/*
	 * public void printDetail(ActionEvent e) {
	 * 
	 * UIDataTable uiTable = (UIDataTable)
	 * e.getComponent().getParent().getParent();
	 * GatePassToDistrictWholesaleDatatable dt =
	 * (GatePassToDistrictWholesaleDatatable)
	 * this.getListWholesale.get(uiTable.getRowIndex());
	 * 
	 * this.setPrintDate(dt.getDt_date());
	 * this.setPrintGatePassNo(dt.getVch_gatepass_no());
	 * if(impl.printReport(this,dt)==true) { dt.setPrintFlag(true);
	 * 
	 * } else {
	 * 
	 * dt.setPrintFlag(false);
	 * 
	 * }
	 * 
	 * }
	 */

	public void printreport(ActionEvent e) {

		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		GatepassToDistrictWholesale_FL2DDataTable dt = (GatepassToDistrictWholesale_FL2DDataTable) this.getListWholesale
				.get(uiTable.getRowIndex());

		this.setPrintDate(dt.getDt_date());
		this.setPrintGatePassNo(dt.getGatepassNo());
		this.setVch_to_Print(dt.getVch_to());
		if (impl.printReport(this) == true) {
			dt.setPrintFlag(true);

		} else {

			dt.setPrintFlag(false);

		}

	}

	// ---------------------------- calculate duty --------------

	public void calculateTotalDuty(ActionEvent ae) {
		// System.out.println("inside2");
		if (isFlag()) {
			this.setSum(0);
			for (int i = 0; i < this.displaylist.size(); i++) {
				GatepassToDistrictWholesale_FL2DDataTable dt = (GatepassToDistrictWholesale_FL2DDataTable) this.displaylist
						.get(i);

				this.setSum(this.getSum() + dt.getCalculated_duty());

			}
		}
		this.flag = false;

	}

	// ==================FOR ADDITIONAL TOTAL DUTY==========================

	public void calculateTotalAddDuty(ActionEvent ae) {
		// System.out.println("inside2");
		if (isAddflag()) {
			this.setSum_add(0);
			for (int i = 0; i < this.displaylist.size(); i++) {
				GatepassToDistrictWholesale_FL2DDataTable dt = (GatepassToDistrictWholesale_FL2DDataTable) this.displaylist
						.get(i);

				this.setSum_add(this.getSum_add() + dt.getCalculated_add_duty());

			}
		}
		this.addflag = false;

	}

	// --------------------for draft report------------------------

	private String draftpdfname;

	public String getDraftpdfname() {
		return draftpdfname;
	}

	public void setDraftpdfname(String draftpdfname) {
		this.draftpdfname = draftpdfname;
	}

	
	
	
	public void printDraftreport(ActionEvent e) {

		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		GatepassToDistrictWholesale_FL2DDataTable dt = (GatepassToDistrictWholesale_FL2DDataTable) this.getListWholesale
				.get(uiTable.getRowIndex());

		this.setPrintDate(dt.getDt_date());
		this.setPrintGatePassNo(dt.getGatepassNo());
		this.setVch_to_Draft(dt.getVch_to());
		
		
		if (impl.printDraftReport(this) == true) 
		{
			dt.setDraftprintFlag(true);

		}
		else 
		{

			dt.setDraftprintFlag(false);

		}

	}

	// ======================for uploader======================================

	private String excelFilename;
	private String excelFilepath;
	private String scanGatePassNo;
	private int excelCases;
	private boolean gatePassFlag;
	private boolean tableFlag;
	private String scanVchFrom;
	ArrayList getVal = new ArrayList();

	public String getScanVchFrom() {
		return scanVchFrom;
	}

	public void setScanVchFrom(String scanVchFrom) {
		this.scanVchFrom = scanVchFrom;
	}

	public boolean isGatePassFlag() {
		return gatePassFlag;
	}

	public void setGatePassFlag(boolean gatePassFlag) {
		this.gatePassFlag = gatePassFlag;
	}

	public boolean isTableFlag() {
		return tableFlag;
	}

	public void setTableFlag(boolean tableFlag) {
		this.tableFlag = tableFlag;
	}

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

	public String getScanGatePassNo() {
		return scanGatePassNo;
	}

	public void setScanGatePassNo(String scanGatePassNo) {
		this.scanGatePassNo = scanGatePassNo;
	}

	public int getExcelCases() {
		return excelCases;
	}

	public void setExcelCases(int excelCases) {
		this.excelCases = excelCases;
	}

	public ArrayList getGetVal() 
	{
		
		return getVal;
	}

	public void setGetVal(ArrayList getVal) {
		this.getVal = getVal;
	}
	
	private String shopId;
	private String shopType;
	private String hbrLicenseNmbr;


	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}


	public String getHbrLicenseNmbr() {
		return hbrLicenseNmbr;
	}

	public void setHbrLicenseNmbr(String hbrLicenseNmbr) {
		this.hbrLicenseNmbr = hbrLicenseNmbr;
	}

	public void scanAndUpload(ActionEvent ae) {
		UIDataTable uiTable = (UIDataTable) ae.getComponent().getParent().getParent();
		GatepassToDistrictWholesale_FL2DDataTable dt = (GatepassToDistrictWholesale_FL2DDataTable) this.getListWholesale.get(uiTable.getRowIndex());

		this.setScanGatePassNo(dt.getGatepassNo());
		this.setScanVchFrom(dt.getVch_from());
		this.setShopId(dt.getShopId_dt());
		this.setShopType(dt.getVch_to());
		this.setHbrLicenseNmbr(dt.getVch_to_lic_no());
		this.gatePassFlag = true;
		this.tableFlag = true;
		
		
		this.getVal = impl.getExcelData(this);
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

			System.out.println("filename" + FullfileName
					+ "---------------filename" + fileName);
			size = item.getFileSize();
			this.excelFilename = FullfileName;
			this.excelFilepath = path;

		} catch (Exception ee) {
			ee.printStackTrace();
			System.out.println("exception in upload@");
		} finally {

		}

	}

	public String importExcel() {
		impl.saveExcelData(this);
		this.gatePassFlag = true;

		return "";
	}

	public void finalSubmit() {
		if (impl.excelCases(this) == impl.recieveCases(this)) {

			if (impl.updateDispatch(this) == true) {

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Data Save Successfully",
								"Data Save Successfully"));
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Error Occured while saving !!! ",
								"Error Occured while saving !!!"));
			}
		} else {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									"No.of cases in Gatepass and in Uploaded CSV does not match! ",
									"No.of cases in Gatepass and in Uploaded CSV does not match!"));
		}

	}

	public void delete() 
	{
		impl.deleteData(this);
		this.finalSubmitFlag=true;
		this.csvFilename=null;

	}

	// ----------------------------code for cancel
	// gatepass----------------------

	private double cancelDuty;

	public double getCancelDuty() {
		return cancelDuty;
	}

	public void setCancelDuty(double cancelDuty) {
		this.cancelDuty = cancelDuty;
	}

	public void cancelGatepass(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		GatepassToDistrictWholesale_FL2DDataTable dt = (GatepassToDistrictWholesale_FL2DDataTable) this.getListWholesale
				.get(uiTable.getRowIndex());

		this.setPrintDate(dt.getDt_date());
		this.setPrintGatePassNo(dt.getGatepassNo());
		this.setCancelDuty(dt.getTotalDuty());
		impl.cancelGatepassImpl(this);
	}
	
	

	// ==========================code for csv===================================

	private String csvFilename;
	private String csvFilepath;
	private String exclcsv = "C";

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

	public String csvSubmit() throws IOException 
	{
		
		try
		{
			if(this.csvFilename !=null)
			{
			impl.saveCSV(this);
			this.gatePassFlag = true;
			this.getVal = impl.getExcelData(this);
			}else
			{
			
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	
		

		return "";
	}
	
	public String shopno="";
	private boolean flagshop=false;
	private String licenseeName;
	private String licenseeAdrs;
	public String shopName;
	public boolean flagshopS=true;

	
	
	

	public boolean isFlagshopS() {
		return flagshopS;
	}

	public void setFlagshopS(boolean flagshopS) {
		this.flagshopS = flagshopS;
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

	public String getShopno() {
		return shopno;
	}

	public void setShopno(String shopno) {
		this.shopno = shopno;
	}

	public boolean isFlagshop() {
		return flagshop;
	}

	public void setFlagshop(boolean flagshop) {
		this.flagshop = flagshop;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	public void fetch()
	{
	if(this.shopno.length()>0 && this.shopno != null && this.shopno !="")
	{
		if(this.getDistrictLic()>0)
		{
			if(this.shopno.charAt(0)==' ' || this.shopno.charAt(this.shopno.length()-1)==' ')
			{
				FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								"enter shop id without spaces",
								"enter shop id without spaces"));
			}
					else
					{
					impl.getShopDetails(this);
					}
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"select district!!! ",
							"select district !!!"));
		}
	}
	else
	{

		FacesContext
		.getCurrentInstance()
		.addMessage(
				null,
				new FacesMessage(
						"enter valid shop id ",
						"enter valid shop id "));
	}
	}
	
	
	
	
	
	
	private boolean validateInput;

	public boolean isValidateInput() {
		
		
		
		validateInput = true;

		if (!(Validate.validateStrReq("radiofrom", this.getVch_from())))
			validateInput = false;
		else if (!(Validate.validateStrReq("radioto", this.getVch_to())))
			validateInput = false;

		if(this.getDistrictLic()==0)
		{
			if (!(Validate.validateInteger("licencee_district", this.getDistrictLic())))
				validateInput = false;
		}
		
		else if (!(Validate.validateStrReq("firmname", this.getLicenseeName())))
			validateInput = false;
		else if (!(Validate
				.validateStrReq("firmaddress", this.getLicenseeAdrs())))
			validateInput = false;
		else if (!(Validate.validateStrReq("routedtl", this.getRouteDtl())))
			validateInput = false;
		else if (!(Validate.validateStrReq("vehiclenmbr", this.getVehicleNo())))
			validateInput = false;
		else if (!(Validate.validateStrReq("vehicledriver",
				this.getVehicleDrvrName())))
			validateInput = false;
		else if (!(Validate.validateStrReq("agencyNameAddress",
				this.getVehicleAgencyNmAdrs())))
			validateInput = false;
		else if (!(Validate
				.validateDouble("grossWeight", this.getGrossWeight())))
			validateInput = false;
		else if (!(Validate.validateDouble("tierweight", this.getTierWeight())))
			validateInput = false;
		else if (!(this.grossWeight > this.getTierWeight())) {
			validateInput = false;

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"GROSS_LESS_THAN_TIER!!! ",
							"GROSS_LESS_THAN_TIER !!!"));
		}

	
		int sumBottles = 0;
		int sumBoxes = 0;

		for (int i = 0; i < this.displaylist.size(); i++) {
			GatepassToDistrictWholesale_FL2DDataTable dt = (GatepassToDistrictWholesale_FL2DDataTable) displaylist
					.get(i);

			sumBottles += dt.getDispatchbottls();
			sumBoxes += dt.getDispatchbox();

			if (dt.getBalance() <= 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Invalid Balance !!! ", "Invalid Balance !!!"));
				validateInput = false;

			} else if (dt.getDispatchbox() > dt.getBoxAvailable()) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										"Dispatch Boxes Should Be Less Than Available Boxes !!! ",
										"Dispatch Boxes Should Be Less Than Available Boxes !!!"));
				validateInput = false;

			}
		}

		if (sumBoxes == 0) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Dispatch Boxes Should Be Greater Than Zero !!! ",
							"Dispatch Boxes Should Be Greater Than Zero !!!"));
			validateInput = false;
		}
		else if (sumBottles == 0) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"Dispatch Bottles Should Be Greater Than Zero !!! ",
									"Dispatch Bottles Should Be Greater Than Zero !!!"));
		}
		
		return validateInput;
	}

	public void setValidateInput(boolean validateInput) {
		this.validateInput = validateInput;
	}
	
	
	
	
	
	private String vch_licence_no;
	private String vch_to_Draft;
	private String vch_to_Print;
	
	
	
	public String getVch_to_Draft() {
		return vch_to_Draft;
	}

	public void setVch_to_Draft(String vch_to_Draft) {
		this.vch_to_Draft = vch_to_Draft;
	}

	public String getVch_to_Print() {
		return vch_to_Print;
	}

	public void setVch_to_Print(String vch_to_Print) {
		this.vch_to_Print = vch_to_Print;
	}

	public String getVch_licence_no() {
		return vch_licence_no;
	}

	public void setVch_licence_no(String vch_licence_no) {
		this.vch_licence_no = vch_licence_no;
	}
	
	
	
	
	
	
	
	private boolean finalSubmitFlag=true;

	public boolean isFinalSubmitFlag() {
		return finalSubmitFlag;
	}

	public void setFinalSubmitFlag(boolean finalSubmitFlag) {
		this.finalSubmitFlag = finalSubmitFlag;
	}
	
	
	
	
	
	

	public void fetch1()
	{
	if(this.vch_to_lic_no.length()>0 && this.vch_to_lic_no != null && this.vch_to_lic_no !="")
	{
		if(this.vch_to_lic_no.charAt(0)==' ' || this.vch_to_lic_no.charAt(this.vch_to_lic_no.length()-1)==' ')
		{
			FacesContext
			.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage(
							"enter HBR ID without spaces",
							"enter  HBR ID without spaces"));
		}
			else
			{
				impl.checklic(this);
		
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

	
public String vch_to_lic_noNew;
	
	
	
	
	
	
	public String getVch_to_lic_noNew() {
		return vch_to_lic_noNew;
	}

	public void setVch_to_lic_noNew(String vch_to_lic_noNew) {
		this.vch_to_lic_noNew = vch_to_lic_noNew;
	}
	
	
	

}
