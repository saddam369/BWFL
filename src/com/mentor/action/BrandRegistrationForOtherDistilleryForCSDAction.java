package com.mentor.action;

import java.util.ArrayList;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import org.richfaces.component.UIDataTable;

import com.mentor.datatable.BrandRegistrationForOtherDistilleryForCSD_BrandDatatable;
import com.mentor.datatable.BrandRegistrationForOtherDistilleryForCSD_BrandDetailDatatable;
import com.mentor.datatable.BrandRegistrationForOtherDistilleryForCSD_Brand_ViewDatatable;
import com.mentor.impl.BrandRegistrationForOtherDistilleryForCSDImpl;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Validate;

public class BrandRegistrationForOtherDistilleryForCSDAction {

	BrandRegistrationForOtherDistilleryForCSDImpl impl = new BrandRegistrationForOtherDistilleryForCSDImpl();
	private ArrayList yearList = new ArrayList();
	private String vch_year;
	private ArrayList unitList = new ArrayList();
	private int int_distillary;
	private int type_id;
	private int cat_id = 0;
	private String brand_name;
	private String licenseing;
	private boolean imfl_beer;
	private boolean wine_importedfl;
	private boolean cl;
	private boolean packaging_flag;
	private boolean cl_flag;
	private boolean cl_flag2;
	private String unit_list_input;
	private ArrayList brands_view;
	private int brand_idd;
	private String imfl_type;
	private int distillery_iddd;
	private String dis_license_no;
	private boolean imfl_flag;
	private String brewryid;
	private ArrayList brewryName;
	private int unitid;
	private int districid;
	private String brandcat;
	private String vch_unit_type;
	
	private ArrayList fl2_2b_2d_list = new ArrayList();
	private String lic_type_int_id;
	
	
	public String getLic_type_int_id() {
		return lic_type_int_id;
	}

	public void setLic_type_int_id(String lic_type_int_id) {
		this.lic_type_int_id = lic_type_int_id;
	}

	public ArrayList getFl2_2b_2d_list() {
		this.fl2_2b_2d_list = new BrandRegistrationForOtherDistilleryForCSDImpl().license_list(this);
		return fl2_2b_2d_list;
	}

	public void setFl2_2b_2d_list(ArrayList fl2_2b_2d_list) {
		this.fl2_2b_2d_list = fl2_2b_2d_list;
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
		brewryName = impl.getBre_Name(this);
		return brewryName;
	}

	public void setBrewryName(ArrayList brewryName) {
		this.brewryName = brewryName;
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

	
	public int districtid;
	private int appid;
	
	public int getDistrictid() {
		return districtid;
	}

	public void setDistrictid(int districtid) {
		this.districtid = districtid;
	}
	public int getAppid() {
		return appid;
	}

	public void setAppid(int appid) {
		this.appid = appid;
	}

	public void add_Packaging_Detail(ActionEvent e) {
		this.shPopupStyle = "position: absolute; top: 600px; right: 0px; left: 0px; display: block; z-index: 1502; animation-name: sh-entry; animation-duration: 0.3s;";
		this.shOverlayStyle = "position: fixed; display: block; width: 100%; height: 100%; top: 0; left: 0; right: 0; bottom: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1500; cursor: pointer; animation-name: sh-entry; animation-duration: 0.3s;";

		this.setPackaging_flag(true);
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
		BrandRegistrationForOtherDistilleryForCSD_Brand_ViewDatatable dt = (BrandRegistrationForOtherDistilleryForCSD_Brand_ViewDatatable) this.getBrands_view().get(uiTable.getRowIndex());
		this.setBrand_idd(dt.getBrand_id());
		this.setBrandcat(String.valueOf(dt.getLiquor_category()));
		this.setLic_cat(dt.getLicense_category());
		this.setDistricid(dt.getDistillery_id());
		this.setAppid(dt.getAppid_fl2a());
		this.setLicense_type("FL2A");
		this.setLicenseing(dt.getLicense_category());
		this.addRowPackaging = new BrandRegistrationForOtherDistilleryForCSDImpl().getPackagingDetails(this);

		/*if (dt.getLicense_category() != null) {
			if (dt.getLicense_category().equalsIgnoreCase("IMFL") || dt.getLicense_category().equalsIgnoreCase("BEER")) {
				this.setPackaging_type1(true);
				this.setPackaging_type2(false);
				this.setPackaging_type3(false);
			}
			if (dt.getLicense_category().equalsIgnoreCase("WINE") || dt.getLicense_category().equalsIgnoreCase("IMPORTEDFL")) {
				this.setPackaging_type1(false);
				this.setPackaging_type2(true);
				this.setPackaging_type3(false);
			}
			if (dt.getLicense_category().equalsIgnoreCase("CL")) {
				this.setPackaging_type1(false);
				this.setPackaging_type2(false);
				this.setPackaging_type3(true);
			}
		}*/
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
		this.brands_view = new BrandRegistrationForOtherDistilleryForCSDImpl().getBrand_Details(this);
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

/*
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
*/
	private boolean type_flag;

	public boolean isType_flag() {
		return type_flag;
	}

	public void setType_flag(boolean type_flag) {
		this.type_flag = type_flag;
	}

	public void license_change_event(ValueChangeEvent e) {
		String val = (String) e.getNewValue();
		this.license_type = "FL2A";
		if (val != null) {
			try {
				if (val.equals("IMFL")) {
					this.imfl_beer = false;
					this.imfl_flag = true;
					this.wine_importedfl = false;
					this.cl = false;
					this.setCl_flag(true);
					this.setCl_flag2(false);
					this.setType_flag(true);
				}
				if (val.equals("BEER")) {
					this.imfl_flag = false;
					this.imfl_beer = true;
					this.wine_importedfl = false;
					this.cl = false;
					this.setCl_flag(true);
					this.setCl_flag2(false);
					this.setType_flag(true);
				}
				if (val.equals("WINE") || val.equals("IMPORTEDFL")) {
					this.cl = false;
					this.imfl_flag = false;
					this.imfl_beer = false;
					this.wine_importedfl = true;
					this.setLicense_type("FL2D");
					this.setCl_flag(true);
					this.setCl_flag2(false);
					this.setType_flag(true);
				}
				if (val.equals("CL")) {
					this.imfl_flag = false;
					this.imfl_beer = false;
					this.wine_importedfl = false;
					this.cl = true;
					this.setCl_flag(false);
					this.setCl_flag2(true);
					this.setType_flag(false);
				}
				if (val.equals("LAB")) {
					this.imfl_flag = false;
					this.imfl_beer = true;
					this.wine_importedfl = false;
					this.cl = false;
					this.setCl_flag(true);
					this.setCl_flag2(false);
					this.setType_flag(true);
				}
				this.brands_view = new BrandRegistrationForOtherDistilleryForCSDImpl().getBrand_Details(this);
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
		if (validateReg_packaging) {
			if (this.addRowPackaging.size() > 0) {
				for (int i = 0; i < this.addRowPackaging.size(); i++) {
					BrandRegistrationForOtherDistilleryForCSD_BrandDatatable dttt = new BrandRegistrationForOtherDistilleryForCSD_BrandDatatable();
					dttt = (BrandRegistrationForOtherDistilleryForCSD_BrandDatatable) this.addRowPackaging.get(i);
					if (!(Validate.validateStrReq("package_name", dttt.getPackage_name())))validateReg_packaging = false;
					else if (!(Validate.validateStrReq("Quantityy", dttt.getQuantity())))validateReg_packaging = false;
					else if (!(Validate.validateOnlyDouble("Quantityy", dttt.getQuantity())))validateReg_packaging = false;
					else if (!(Validate.validateStrReq("package_type", dttt.getPackage_type())))validateReg_packaging = false;				}
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
		if (!(Validate.validateStrReq("yearr", this.getVch_year())))validateReg = false;
		if (validateReg) {
			if (this.addRowBranding.size() > 0) {
				for (int i = 0; i < this.addRowBranding.size(); i++) {
					BrandRegistrationForOtherDistilleryForCSD_BrandDetailDatatable table = new BrandRegistrationForOtherDistilleryForCSD_BrandDetailDatatable();
					table = (BrandRegistrationForOtherDistilleryForCSD_BrandDetailDatatable) this.addRowBranding.get(i);
					if (!(Validate.validateStrReq("brand_name", String.valueOf(table.getBrandName()))))validateReg = false;
					else if (!(Validate.validateStrReq("liquor_category", table.getLiquorCategory())))validateReg = false;
					else if (!(Validate.validateStrReq("strengthh", String.valueOf(table.getStrength_addrow()))))validateReg = false;
					else if (!(Validate.validateOnlyDouble("strengthh", String.valueOf(table.getStrength_addrow()))))validateReg = false;
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
			BrandRegistrationForOtherDistilleryForCSD_BrandDatatable dt = new BrandRegistrationForOtherDistilleryForCSD_BrandDatatable();
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

	public int getCat_id() {
		return cat_id;
	}

	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
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
			if (this.getAddRowBranding().size() > 0) {
				impl.saveMethod(this);
			}
		}
		return "";
	}

	public String saveMethod2() {
		if (isValidateReg_packaging()) {
			if (this.addRowPackaging.size() > 0) {
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
		this.brand_name = null;
		this.unit_list_input = "";
		this.addRowBranding.clear();
		this.cl_flag2 = false;
		this.imfl_beer = false;
		this.wine_importedfl = false;
		this.licenseing = "";
		return "";
	}

	public void reset1() {
		shPopupStyle = "display: none;";
		shOverlayStyle = "display: none;";
		this.type_flag = false;
		this.addRowBranding.clear();
		this.cl_flag2 = false;
		this.cl_flag = false;
		this.imfl_beer = false;
		this.imfl_flag = false;
		this.wine_importedfl = false;
		this.license_type = "";
		this.licenseing = "";
	}

	public String addRowMethodPackaging() {
		if (addRowFlagPackge == false) {
			BrandRegistrationForOtherDistilleryForCSD_BrandDatatable dt = new BrandRegistrationForOtherDistilleryForCSD_BrandDatatable();
			dt.setSno(addRowPackaging.size() + 1);
			addRowPackaging.add(dt);
		}
		return "";
	}

	public String addRowMethodBranding() {
		if (addRowFlagPackge == false) {
			BrandRegistrationForOtherDistilleryForCSD_BrandDetailDatatable dt = new BrandRegistrationForOtherDistilleryForCSD_BrandDetailDatatable();
			dt.setSno(addRowBranding.size() + 1);
			addRowBranding.add(dt);
		}
		return "";
	}

	public void deleteRowMethodPackaging(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		BrandRegistrationForOtherDistilleryForCSD_BrandDatatable dt = (BrandRegistrationForOtherDistilleryForCSD_BrandDatatable) this
				.getAddRowPackaging().get(uiTable.getRowIndex());
		this.addRowPackaging.remove(dt);
	}

	public void deleteRowMethodBranding(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		BrandRegistrationForOtherDistilleryForCSD_BrandDetailDatatable dt = (BrandRegistrationForOtherDistilleryForCSD_BrandDetailDatatable) this
				.getAddRowBranding().get(uiTable.getRowIndex());
		this.addRowBranding.remove(dt);
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
		this.shOverlayStyle = "position: fixed; display: block; width: 100%; height: 100%; top: 0; left: 0; right: 0; bottom: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1500; cursor: pointer; animation-name: sh-entry; animation-duration: 0.3s;";

	}

}
