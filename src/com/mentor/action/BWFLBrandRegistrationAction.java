package com.mentor.action;

import java.util.ArrayList;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import org.richfaces.component.UIDataTable;

import com.mentor.datatable.BWFL_BrandDatatable;
import com.mentor.datatable.BWFL_BrandDetailDataTable;
import com.mentor.datatable.BWFL_Brand_ViewDatatable;
import com.mentor.impl.BWFLBrandRegistrationImpl;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Validate;

public class BWFLBrandRegistrationAction {

	BWFLBrandRegistrationImpl impl = new BWFLBrandRegistrationImpl();
	private ArrayList yearList = new ArrayList();
	private String vch_year;
	private ArrayList unitList = new ArrayList();
	private int int_distillary;
	private int type_id;
	private ArrayList liqCatList = new ArrayList();
	private int cat_id = 0;
	private String brand_name;
	private String licenseing;
	private boolean imfl_beer;
	private boolean wine_importedfl;
	private boolean cl;
	private boolean packaging_flag;
	private String license_details;
	private boolean cl_flag;
	private boolean cl_flag2;
	private String unit_list_input;
	private ArrayList brands_view;
	private int brand_idd;
	private String imfl_type;
	private int distillery_iddd;
	private String dis_license_no;
	private ArrayList getDisName;
	private boolean imfl_flag;
	private String license_no;
	private String brewryid;
	private ArrayList brewryName;
	private int unitid;
	private int districid;
	private String brandcat;
	
	private String vch_unit_type;
	private String hidden;
	private String user_logged_in = ResourceUtil.getUserNameAllReq();
	
	private String distillery_name_logged_in;
	private String brewery_name_logged_in;
	private int distillery_id_logged_in;
	private int brewery_id_logged_in;
	private String distillery_address_logged_in;
	private String brewery_address_logged_in;
	private String brewery_license_number_logged_in;
	private String distillery_license_number_logged_in;
	private int bwfl_id_logged_in;
	private String bwfl_firm_name_logged_in;
	private String bwfl_license_number_logged_in;
	
	public int getBwfl_id_logged_in() {
		return bwfl_id_logged_in;
	}

	public void setBwfl_id_logged_in(int bwfl_id_logged_in) {
		this.bwfl_id_logged_in = bwfl_id_logged_in;
	}

	public String getBwfl_firm_name_logged_in() {
		return bwfl_firm_name_logged_in;
	}

	public void setBwfl_firm_name_logged_in(String bwfl_firm_name_logged_in) {
		this.bwfl_firm_name_logged_in = bwfl_firm_name_logged_in;
	}

	public String getBwfl_license_number_logged_in() {
		return bwfl_license_number_logged_in;
	}

	public void setBwfl_license_number_logged_in(
			String bwfl_license_number_logged_in) {
		this.bwfl_license_number_logged_in = bwfl_license_number_logged_in;
	}

	public String getBrewery_license_number_logged_in() {
		return brewery_license_number_logged_in;
	}

	public void setBrewery_license_number_logged_in(
			String brewery_license_number_logged_in) {
		this.brewery_license_number_logged_in = brewery_license_number_logged_in;
	}

	public String getDistillery_license_number_logged_in() {
		return distillery_license_number_logged_in;
	}

	public void setDistillery_license_number_logged_in(
			String distillery_license_number_logged_in) {
		this.distillery_license_number_logged_in = distillery_license_number_logged_in;
	}

	public String getDistillery_name_logged_in() {
		return distillery_name_logged_in;
	}

	public void setDistillery_name_logged_in(String distillery_name_logged_in) {
		this.distillery_name_logged_in = distillery_name_logged_in;
	}

	public String getBrewery_name_logged_in() {
		return brewery_name_logged_in;
	}

	public void setBrewery_name_logged_in(String brewery_name_logged_in) {
		this.brewery_name_logged_in = brewery_name_logged_in;
	}

	public int getDistillery_id_logged_in() {
		return distillery_id_logged_in;
	}

	public void setDistillery_id_logged_in(int distillery_id_logged_in) {
		this.distillery_id_logged_in = distillery_id_logged_in;
	}

	public int getBrewery_id_logged_in() {
		return brewery_id_logged_in;
	}

	public void setBrewery_id_logged_in(int brewery_id_logged_in) {
		this.brewery_id_logged_in = brewery_id_logged_in;
	}

	public String getDistillery_address_logged_in() {
		return distillery_address_logged_in;
	}

	public void setDistillery_address_logged_in(String distillery_address_logged_in) {
		this.distillery_address_logged_in = distillery_address_logged_in;
	}

	public String getBrewery_address_logged_in() {
		return brewery_address_logged_in;
	}

	public void setBrewery_address_logged_in(String brewery_address_logged_in) {
		this.brewery_address_logged_in = brewery_address_logged_in;
	}

	public String getUser_logged_in() {
		return user_logged_in;
	}

	public void setUser_logged_in(String user_logged_in) {
		this.user_logged_in = user_logged_in;
	}

	public String getHidden() {
		if(user_logged_in != null){
			new BWFLBrandRegistrationImpl().getHidden(this);
		}
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getVch_unit_type() {
		return vch_unit_type;
	}

	public void setVch_unit_type(String vch_unit_type) {
		this.vch_unit_type = vch_unit_type;
	}

	public String getBrandcat() {
		return brandcat;
	}

	public void setBrandcat(String brandcat) {
		this.brandcat = brandcat;
	}

	public int getUnitid() {
		return unitid;
	}

	public void setUnitid(int unitid) {
		this.unitid = unitid;
	}

	public int getDistricid() {
		return districid;
	}

	public void setDistricid(int districid) {
		this.districid = districid;
	}

	public String getBrewryid() {
		return brewryid;
	}

	public void setBrewryid(String brewryid) {
		this.brewryid = brewryid;
	}

 
	public ArrayList getBrewryName() {
		brewryName=impl.getBre_Name(this);
		return brewryName;
	}

	public void setBrewryName(ArrayList brewryName) {
		this.brewryName = brewryName;
	}

	public String getLicense_no() {
		return license_no;
	}

	public void setLicense_no(String license_no) {
		this.license_no = license_no;
	}

	public boolean isImfl_flag() {
		return imfl_flag;
	}

	public void setImfl_flag(boolean imfl_flag) {
		this.imfl_flag = imfl_flag;
	}
	
	public String getDis_license_no() {
		return dis_license_no;
	}

	public void setDis_license_no(String dis_license_no) {
		this.dis_license_no = dis_license_no;
	}

	public ArrayList getGetDisName() {
		this.getDisName = impl.getDis_Name(this);
		return getDisName;
	}

	public void setGetDisName(ArrayList getDisName) {
		this.getDisName = getDisName;
	}

	public int getDistillery_iddd() {
		return distillery_iddd;
	}

	public void setDistillery_iddd(int distillery_iddd) {
		this.distillery_iddd = distillery_iddd;
	}
	
	private String lic_cat;
	
	
	public String getLic_cat() {				
		return lic_cat;
	}

	public void setLic_cat(String lic_cat) {
		this.lic_cat = lic_cat;
	}

	public void add_Packaging_Detail(ActionEvent e) {
		this.shPopupStyle = "position: absolute; top: 600px; right: 0px; left: 0px; display: block; z-index: 1502; animation-name: sh-entry; animation-duration: 0.3s;";
		this.shOverlayStyle = "position: fixed; display: block; width: 100%; height: 100%; top: 0; left: 0; right: 0; bottom: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1500; cursor: pointer; animation-name: sh-entry; animation-duration: 0.3s;";

		this.setPackaging_flag(true);		
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
		BWFL_Brand_ViewDatatable dt = (BWFL_Brand_ViewDatatable) this.getBrands_view().get(uiTable.getRowIndex());		
		this.setBrand_idd(dt.getBrand_id());
		this.setBrandcat(String.valueOf(dt.getLiquor_category()));
		this.setLic_cat(dt.getLicense_category());
		this.addRowPackaging = new BWFLBrandRegistrationImpl().getPackagingDetails(this);
		
		if (dt.getLicense_category() != null) {
			if (dt.getLicense_category().equalsIgnoreCase("IMFL")
					|| dt.getLicense_category().equalsIgnoreCase("BEER")) {
				this.setPackaging_type1(true);
				this.setPackaging_type2(false);
				this.setPackaging_type3(false);
			}
			if (dt.getLicense_category().equalsIgnoreCase("WINE")
					|| dt.getLicense_category().equalsIgnoreCase("LAB")) {
				this.setPackaging_type1(false);
				this.setPackaging_type2(true);
				this.setPackaging_type3(false);
			}
			if (dt.getLicense_category().equalsIgnoreCase("CL")) {
				this.setPackaging_type1(false);
				this.setPackaging_type2(false);
				this.setPackaging_type3(true);
			}
		}
	}

	public String getImfl_type() {
		return imfl_type;
	}

	public void setImfl_type(String imfl_type) {
		this.imfl_type = imfl_type;
	}

	public int getBrand_idd() {
		return brand_idd;
	}

	public void setBrand_idd(int brand_idd) {
		this.brand_idd = brand_idd;
	}

	public boolean isPackaging_flag() {
		return packaging_flag;
	}

	public void setPackaging_flag(boolean packaging_flag) {
		this.packaging_flag = packaging_flag;
	}

	public ArrayList getBrands_view() {
		this.brands_view = new BWFLBrandRegistrationImpl().getBrand_Details(this);
		return brands_view;
	}

	public void setBrands_view(ArrayList brands_view) {
		this.brands_view = brands_view;
	}

	public boolean isCl_flag2() {
		return cl_flag2;
	}

	public void setCl_flag2(boolean cl_flag2) {
		this.cl_flag2 = cl_flag2;
	}

	public String getUnit_list_input() {
		return unit_list_input;
	}

	public void setUnit_list_input(String unit_list_input) {
		this.unit_list_input = unit_list_input;
	}

	public boolean isCl_flag() {
		return cl_flag;
	}

	public void setCl_flag(boolean cl_flag) {
		this.cl_flag = cl_flag;
	}

	public String getLicense_details() {	
		if(license_no!=null){
			license_details=new BWFLBrandRegistrationImpl().get_Distillery_Details(this, license_no);
		}
		return license_details;
	}

	public void setLicense_details(String license_details) {
		this.license_details = license_details;
	}

	private boolean packaging_type1 = true;
	private boolean packaging_type2;
	private boolean packaging_type3;

	public boolean isPackaging_type1() {
		return packaging_type1;
	}

	public void setPackaging_type1(boolean packaging_type1) {
		this.packaging_type1 = packaging_type1;
	}

	public boolean isPackaging_type2() {
		return packaging_type2;
	}

	public void setPackaging_type2(boolean packaging_type2) {
		this.packaging_type2 = packaging_type2;
	}

	public boolean isPackaging_type3() {
		return packaging_type3;
	}

	public void setPackaging_type3(boolean packaging_type3) {
		this.packaging_type3 = packaging_type3;
	}

	private boolean type_flag;

	public boolean isType_flag() {
		return type_flag;
	}

	public void setType_flag(boolean type_flag) {
		this.type_flag = type_flag;
	}

	public void license_change_event(ValueChangeEvent e) {
		String val = (String) e.getNewValue();
		if (val != null) {
			try {license_type=null;
				if (val.equals("IMFL")) {
					this.imfl_beer = false;
					this.imfl_flag = true;
					//this.setLicense_type("FL3A");
					this.wine_importedfl = false;
					this.cl = false;
					this.setCl_flag(true);
					this.setCl_flag2(false);
					this.setType_flag(true);
				}
				if(val.equals("BEER")){
					this.imfl_flag = false;
					this.imfl_beer = true;
					//this.setLicense_type("");
					this.wine_importedfl = false;
					this.cl = false;
					this.setCl_flag(true);
					this.setCl_flag2(false);
					this.setType_flag(true);
				}
				if (val.equals("WINE") || val.equals("LAB")) {
					this.cl = false;
					this.imfl_flag = false;
					this.imfl_beer = false;
					this.wine_importedfl = true;
					//this.setLicense_type("FL2D");
					this.setCl_flag(true);
					this.setCl_flag2(false);
					this.setType_flag(true);
				}
				if (val.equals("CL")) {
					this.imfl_flag = false;
					this.imfl_beer = false;
					this.wine_importedfl = false;
					this.cl = true;
					//this.setLicense_type("CL");
					this.setCl_flag(false);
					this.setCl_flag2(true);
					this.setType_flag(false);
				}				
				this.brands_view = new BWFLBrandRegistrationImpl().getBrand_Details(this);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public String getLicenseing() {
		return licenseing;
	}

	public boolean isImfl_beer() {
		return imfl_beer;
	}

	public void setImfl_beer(boolean imfl_beer) {
		this.imfl_beer = imfl_beer;
	}

	public boolean isWine_importedfl() {
		return wine_importedfl;
	}

	public void setWine_importedfl(boolean wine_importedfl) {
		this.wine_importedfl = wine_importedfl;
	}

	public boolean isCl() {
		return cl;
	}

	public void setCl(boolean cl) {
		this.cl = cl;
	}

	public void setLicenseing(String licenseing) {
		this.licenseing = licenseing;
	}

	private ArrayList addRowPackaging = new ArrayList();
	private ArrayList addRowBranding = new ArrayList();

	public ArrayList getAddRowBranding() {
		return addRowBranding;
	}

	public void setAddRowBranding(ArrayList addRowBranding) {
		this.addRowBranding = addRowBranding;
	}

	private boolean addRowFlagPackge = true;
	private boolean validateReg;	
	private boolean validateReg_packaging;
	
	public boolean isValidateReg_packaging() {
		validateReg_packaging = true;		
			if(validateReg_packaging){				
				if(this.addRowPackaging.size()>0){					
					for(int i=0;i<this.addRowPackaging.size();i++){						
						BWFL_BrandDatatable dttt = new BWFL_BrandDatatable();
						dttt = (BWFL_BrandDatatable)this.addRowPackaging.get(i);						
						if(!(Validate.validateStrReq("package_name", dttt.getPackage_name())))validateReg_packaging = false;						
						else if(!(Validate.validateStrReq("Quantityy",dttt.getQuantity())))validateReg_packaging = false;
						else if(!(Validate.validateOnlyDouble("Quantityy",dttt.getQuantity())))validateReg_packaging = false;
						else if(!(Validate.validateStrReq("package_type",dttt.getPackage_type())))validateReg_packaging = false;
						else if(!(Validate.validateDouble("mrp", dttt.getMrp1())))validateReg_packaging = false;
						else if(!(Validate.validateDouble("edp", dttt.getEdp1())))validateReg_packaging = false;
						else if(!(Validate.validateDouble("duty", dttt.getDuty1())))validateReg_packaging = false;	
						else if(!(Validate.validateDouble("wsMargin", dttt.getWsMargin1())))validateReg_packaging = false;
						else if(!(Validate.validateDouble("retMargin", dttt.getRetMargin1())))validateReg_packaging = false;
					}
				}
			}
		return validateReg_packaging;
	}

	public void setValidateReg_packaging(boolean validateReg_packaging) {
		this.validateReg_packaging = validateReg_packaging;
	}

	private ArrayList descList = new ArrayList();
	public String license_type;

	public String getLicense_type() {
		return license_type;
	}

	public void setLicense_type(String license_type) {
		this.license_type = license_type;
	}

	public ArrayList getDescList() {
		try {
			this.descList = impl.descListImpl(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return descList;
	}

	public void setDescList(ArrayList descList) {
		this.descList = descList;
	}

	public boolean isValidateReg() {
		validateReg = true;
		if(validateReg){
			if(!(Validate.validateStrReq("lic_no", this.getLicense_no())))validateReg=false;   
       	 if(this.addRowBranding.size()>0){       		 
       			for(int i=0;i<this.addRowBranding.size();i++){       				
               	BWFL_BrandDetailDataTable table=new BWFL_BrandDetailDataTable();	
               	table=(BWFL_BrandDetailDataTable)this.addRowBranding.get(i);                	
           		if(!(Validate.validateStrReq("brand_name", String.valueOf(table.getBrandName()))))validateReg=false;
                   else if(!(Validate.validateStrReq("liquor_category", table.getLiquorCategory())))validateReg=false;                   
                   else if(!(Validate.validateStrReq("strengthh", String.valueOf(table.getStrength_addrow()))))validateReg=false;
                   else if(!(Validate.validateOnlyDouble("strengthh", String.valueOf(table.getStrength_addrow()))))validateReg=false;
               	}               	
       	 }        
		}
		return validateReg;		
	}

	public void setValidateReg(boolean validateReg) {
		this.validateReg = validateReg;
	}

	public ArrayList getAddRowPackaging() {
		if (addRowFlagPackge) {
			BWFL_BrandDatatable dt = new BWFL_BrandDatatable();
			dt.setSno(addRowPackaging.size() + 1);
			addRowPackaging.add(dt);
			addRowFlagPackge = false;
		}
		return addRowPackaging;
	}

	public void setAddRowPackaging(ArrayList addRowPackaging) {
		this.addRowPackaging = addRowPackaging;
	}

	public boolean isAddRowFlagPackge() {
		return addRowFlagPackge;
	}

	public void setAddRowFlagPackge(boolean addRowFlagPackge) {
		this.addRowFlagPackge = addRowFlagPackge;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public ArrayList getLiqCatList() {
		try {
			// this.liqCatList = impl.liqCatListImpl(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return liqCatList;
	}

	public void setLiqCatList(ArrayList liqCatList) {
		this.liqCatList = liqCatList;
	}

	public int getCat_id() {
		return cat_id;
	}

	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}

	public ArrayList license_list;
	
	public ArrayList getLicense_list() {
		try {			
				if (vch_year != null && vch_year.length()>0) {
					this.license_list = impl.getLicenselist(this);
				}					
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return license_list;
	}

	public void setLicense_list(ArrayList license_list) {
		this.license_list = license_list;
	}

	public ArrayList getUnitList() {
		try {
			if (vch_year != null && vch_year.length() > 0) {
				this.unitList = impl.unitListImpl(this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return unitList;
	}

	public void setUnitList(ArrayList unitList) {
		this.unitList = unitList;
	}

	public int getInt_distillary() {
		return int_distillary;
	}

	public void setInt_distillary(int int_distillary) {
		this.int_distillary = int_distillary;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getVch_year() {
		return vch_year;
	}

	public void setVch_year(String vch_year) {
		this.vch_year = vch_year;
	}

	public ArrayList getYearList() {
		try {
			this.yearList = impl.yearListImpl(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return yearList;
	}

	public void setYearList(ArrayList yearList) {
		this.yearList = yearList;
	}

	public String saveMethod() {
		if (isValidateReg()) {
			if(this.getAddRowBranding().size()>0){
				impl.saveMethod(this);
			}
		}
		return "";
	}

	public String saveMethod2() {
		if (isValidateReg_packaging()) {
			if(this.addRowPackaging.size()>0){
				impl.saveMethod2(this);
			}			
		}
		return "";
	}

	private int seq_brands;

	public int getSeq_brands() {
		return seq_brands;
	}

	public void setSeq_brands(int seq_brands) {
		this.seq_brands = seq_brands;
	}

	public void reset2() {
		  shPopupStyle = "display: none;";
		  shOverlayStyle = "  display: none;";

		this.addRowPackaging.clear();
		this.packaging_flag = false;		
	}
	
	public String reset() {
		 shPopupStyle = "display: none;";
		  shOverlayStyle = "  display: none;";
		this.type_flag = false;
		this.brand_unit = null;
		this.reg_txt = null;
		this.brand_name = null;	
		this.unit_list_input = "";
		this.addRowBranding.clear();		
		this.cl_flag2 = false;
		this.imfl_beer=false;
		this.wine_importedfl= false;
		this.licenseing="";		
		return "";
	}
	public void reset1(){
		shPopupStyle = "display: none;";
		shOverlayStyle = "display: none;";
		this.type_flag = false;
		this.addRowBranding.clear();		
		this.cl_flag2 = false;
		this.cl_flag=false;
		this.imfl_beer=false;
		this.imfl_flag=false;
		this.wine_importedfl= false;
		this.license_type="";
		this.licenseing="";
		this.license_details="";
	}
	
	
	public String addRowMethodPackaging() {
		if (addRowFlagPackge == false) {
			BWFL_BrandDatatable dt = new BWFL_BrandDatatable();
			dt.setSno(addRowPackaging.size() + 1);
			addRowPackaging.add(dt);
		}
		return "";
	}

	public String addRowMethodBranding() {
		if (addRowFlagPackge == false) {
			BWFL_BrandDetailDataTable dt = new BWFL_BrandDetailDataTable();
			dt.setSno(addRowBranding.size() + 1);
			addRowBranding.add(dt);
		}
		return "";
	}

	public void deleteRowMethodPackaging(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		BWFL_BrandDatatable dt = (BWFL_BrandDatatable) this.getAddRowPackaging().get(
				uiTable.getRowIndex());
		this.addRowPackaging.remove(dt);
	}

	public void deleteRowMethodBranding(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		BWFL_BrandDetailDataTable dt = (BWFL_BrandDetailDataTable) this
				.getAddRowBranding().get(uiTable.getRowIndex());
		this.addRowBranding.remove(dt);
	}

	// =======================================
	private String distillery_name;
	private String distillery_address;
	private String reg_txt;
	private String brand_unit;
	private String user = ResourceUtil.getUserNameAllReq();

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getBrand_unit() {
		return brand_unit;
	}

	public void setBrand_unit(String brand_unit) {
		this.brand_unit = brand_unit;
	}

	public String getReg_txt() {
		return reg_txt;
	}

	public void setReg_txt(String reg_txt) {
		this.reg_txt = reg_txt;
	}

	public String getDistillery_name() {
		if (this.user != null) {
			BWFLBrandRegistrationImpl impll = new BWFLBrandRegistrationImpl();
			this.distillery_name = impll.getdist_name(this);
		}
		return distillery_name;
	}

	public void setDistillery_name(String distillery_name) {
		this.distillery_name = distillery_name;
	}

	public String getDistillery_address() {
		if (this.user != null) {
			BWFLBrandRegistrationImpl impll = new BWFLBrandRegistrationImpl();
			this.distillery_address = impll.getdist_add(this);
		}
		return distillery_address;
	}

	public void setDistillery_address(String distillery_address) {
		this.distillery_address = distillery_address;
	}
	// ===========================================================
	
	private String shPopupStyle = "display: none;";
	private String shOverlayStyle = " display: none;";

	 	public String getShPopupStyle() {
		return shPopupStyle;
	}

	public void setShPopupStyle(String shPopupStyle) {
		this.shPopupStyle = shPopupStyle;
	}

	public String getShOverlayStyle() {
		
		return shOverlayStyle;
	}

	public void setShOverlayStyle(String shOverlayStyle) {
		this.shOverlayStyle = shOverlayStyle;
	}
	public void shShowLoginPopup(ActionEvent e) {
		this.shPopupStyle = "position: absolute; top: 160px; right: 0px; left: 0px; display: block; z-index: 1502; animation-name: sh-entry; animation-duration: 0.3s;";
		this.shOverlayStyle = "position: fixed; display: block; width: 100%; height: 100%; top: 0; left: 0; right: 0; bottom: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1500; cursor: pointer; animation-name: sh-entry; animation-duration: 0.3s";
	}
	
	private boolean bwfl_flag;
	private boolean bwfl_flag_bwfl_case;
	private boolean brewery_flag;
	
	public boolean isBrewery_flag() {
		return brewery_flag;
	}

	public void setBrewery_flag(boolean brewery_flag) {
		this.brewery_flag = brewery_flag;
	}

	public boolean isBwfl_flag_bwfl_case() {
		return bwfl_flag_bwfl_case;
	}

	public void setBwfl_flag_bwfl_case(boolean bwfl_flag_bwfl_case) {
		this.bwfl_flag_bwfl_case = bwfl_flag_bwfl_case;
	}

	public boolean isBwfl_flag() {
		return bwfl_flag;
	}

	public void setBwfl_flag(boolean bwfl_flag) {
		this.bwfl_flag = bwfl_flag;
	}
	
	
}
