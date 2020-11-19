package com.mentor.datatable;

import java.util.ArrayList;
import java.util.Date;

import com.mentor.impl.ImportPermitImpl_20_21;

public class ImportPermitDT_20_21 {
	private int sno;
	public String brand_id;
	public String brand_name;
	public String pckg_id;
	private String packageName;
	private String package_type;
	private String quantity;
	private String etin;
	private int no_of_cases = 0;
	private long no_of_bottle_per_case;
	private int pland_no_of_bottles;
	private double special_fee;
	private double importFee = 0;
	public double cal_importFee = 0;
	private double duty;
	private double adduty;
	private double calculated_duty;
	private double calculated_add_duty;
	private ArrayList brandList = new ArrayList();
	private ArrayList packagingList = new ArrayList();
	private int dt_app_id;
	private double dt_import_fee;
	private double dt_duty;
	private double dt_add_duty;
	private double dt_special_fee;
	private String dt_vch_status;
	private String dt_vch_approved;
	private String dt_bottlng_type;
	private int dt_district_id;
	private Date validUpto;
	private double spcl_fee;
	ImportPermitImpl_20_21 impl=new ImportPermitImpl_20_21();
	public double getSpcl_fee() {
		return spcl_fee;
	}

	public void setSpcl_fee(double spcl_fee) {
		this.spcl_fee = spcl_fee;
	}

	public ArrayList getBrandList() {
		this.brandList = impl.getBrandName();
		return brandList;
	}

	public void setBrandList(ArrayList brandList) {
		this.brandList = brandList;
	}

	public ArrayList getPackagingList() {
		if (this.brand_id != null) {

			this.packagingList = impl.getPackagingName(brand_id);
		}
		return packagingList;
	}

	public void setPackagingList(ArrayList packagingList) {
		this.packagingList = packagingList;
	}

	public String getQuantity() {
		if (this.brand_id != null && this.pckg_id != null) {
			this.quantity = impl.getqty(brand_id, pckg_id);
			// this.importFee = impl.getPermitFee(brand_id, pckg_id);
			this.importFee = impl.getBondPermitSpecialFee(this);
		}

		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getEtin() {
		if (this.brand_id != null && this.pckg_id != null) {
			this.etin = impl.getEtinNmbr(brand_id, pckg_id, this);
		}
		return etin;
	}

	public void setEtin(String etin) {
		this.etin = etin;
	}

	public int getPland_no_of_bottles() {
		if (quantity != null && quantity.length() > 0) {
			if (this.no_of_cases > 0) {
				this.pland_no_of_bottles = (this.no_of_cases * new Double(
						this.no_of_bottle_per_case).intValue());
			} else {
				this.pland_no_of_bottles = 0;
			}
		}
		return pland_no_of_bottles;
	}

	public double getImportFee() {

		return importFee;
	}

	public void setImportFee(double importFee) {
		this.importFee = importFee;
	}

	public double getCal_importFee() {
		System.out.println(this.quantity+"-importFee=" + importFee);
		if (this.quantity!=null) {
			if (this.quantity.length() > 0) {
				this.cal_importFee = (this.pland_no_of_bottles * this.importFee * Integer.parseInt(this.quantity)) / 1000;
			}
		}
		return cal_importFee;
	}

	public double getDuty() {
		return duty;
	}

	public void setDuty(double duty) {
		this.duty = duty;
	}

	public double getAdduty() {
		return adduty;
	}

	public void setAdduty(double adduty) {
		this.adduty = adduty;
	}

	public double getCalculated_duty() {
		this.calculated_duty = this.pland_no_of_bottles * this.duty;
		return calculated_duty;
	}

	public void setCalculated_duty(double calculated_duty) {
		this.calculated_duty = calculated_duty;
	}

	public double getCalculated_add_duty() {
		this.calculated_add_duty = this.pland_no_of_bottles * this.adduty;
		return calculated_add_duty;
	}

	public void setCalculated_add_duty(double calculated_add_duty) {
		this.calculated_add_duty = calculated_add_duty;
	}

	public void setCal_importFee(double cal_importFee) {
		this.cal_importFee = cal_importFee;
	}

	public void setPland_no_of_bottles(int pland_no_of_bottles) {
		this.pland_no_of_bottles = pland_no_of_bottles;
	}

	public int getNo_of_cases() {
		return no_of_cases;
	}

	public void setNo_of_cases(int no_of_cases) {
		this.no_of_cases = no_of_cases;
	}

	public long getNo_of_bottle_per_case() {
		return no_of_bottle_per_case;
	}

	public void setNo_of_bottle_per_case(long no_of_bottle_per_case) {
		this.no_of_bottle_per_case = no_of_bottle_per_case;
	}

	public String getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getPckg_id() {
		return pckg_id;
	}

	public void setPckg_id(String pckg_id) {
		this.pckg_id = pckg_id;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackage_type() {
		return package_type;
	}

	public void setPackage_type(String package_type) {
		this.package_type = package_type;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getDt_app_id() {
		return dt_app_id;
	}

	public void setDt_app_id(int dt_app_id) {
		this.dt_app_id = dt_app_id;
	}

	public double getDt_import_fee() {
		return dt_import_fee;
	}

	public void setDt_import_fee(double dt_import_fee) {
		this.dt_import_fee = dt_import_fee;
	}

	public double getDt_duty() {
		return dt_duty;
	}

	public void setDt_duty(double dt_duty) {
		this.dt_duty = dt_duty;
	}

	public double getDt_add_duty() {
		return dt_add_duty;
	}

	public void setDt_add_duty(double dt_add_duty) {
		this.dt_add_duty = dt_add_duty;
	}

	public double getDt_special_fee() {
		return dt_special_fee;
	}

	public void setDt_special_fee(double dt_special_fee) {
		this.dt_special_fee = dt_special_fee;
	}

	public String getDt_vch_status() {
		return dt_vch_status;
	}

	public void setDt_vch_status(String dt_vch_status) {
		this.dt_vch_status = dt_vch_status;
	}

	public String getDt_vch_approved() {
		return dt_vch_approved;
	}

	public void setDt_vch_approved(String dt_vch_approved) {
		this.dt_vch_approved = dt_vch_approved;
	}

	public String getDt_bottlng_type() {
		return dt_bottlng_type;
	}

	public void setDt_bottlng_type(String dt_bottlng_type) {
		this.dt_bottlng_type = dt_bottlng_type;
	}

	public int getDt_district_id() {
		return dt_district_id;
	}

	public void setDt_district_id(int dt_district_id) {
		this.dt_district_id = dt_district_id;
	}

	public double getSpecial_fee() {
		System.out.println("1=" + quantity + pland_no_of_bottles + special_fee);
		if (quantity != null && quantity.length() > 0) {
			if (this.pland_no_of_bottles > 0) {
				this.special_fee = (this.pland_no_of_bottles* Double.parseDouble(this.quantity) * this.spcl_fee)/1000;
			} else {
				this.special_fee = 0;
			}
		}
		return special_fee;
	}

	public void setSpecial_fee(double special_fee) {
		this.special_fee = special_fee;
	}

	public Date getValidUpto() {
		return validUpto;
	}

	public void setValidUpto(Date validUpto) {
		this.validUpto = validUpto;
	}
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
	private int app_id;

	public int getApp_id() {
		return app_id;
	}

	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}
 	private String digital_sign_pdf_name;

	public String getDigital_sign_pdf_name() {
		return digital_sign_pdf_name;
	}

	public void setDigital_sign_pdf_name(String digital_sign_pdf_name) {
		this.digital_sign_pdf_name = digital_sign_pdf_name;
	}
 	
 

}
