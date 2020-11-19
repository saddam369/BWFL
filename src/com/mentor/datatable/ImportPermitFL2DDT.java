package com.mentor.datatable;

import java.util.ArrayList;
import java.util.Date;

import com.mentor.impl.ImportPermitFL2DImpl;

public class ImportPermitFL2DDT {
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
	private double scanfee;
	private double importFee=0;
	public double cal_importFee = 0;
	public double cal_scanfee;
	private double duty;
	private double adduty;
	private double calculated_duty;
	private double calculated_add_duty;
	private ArrayList brandList = new ArrayList();
	private ArrayList packagingList = new ArrayList();
	ImportPermitFL2DImpl impl = new ImportPermitFL2DImpl();
	private int dt_app_id;
	private double dt_import_fee;
	private double dt_duty;
	private double dt_add_duty;
	private double dt_special_fee;
	private double dt_scan_fee;
	private String dt_vch_status;
	private String dt_vch_approved;
	private String dt_bottlng_type;
	private int dt_district_id;
	private Date validUpto;
	private double spcl_fee;
	private double mrp;
	private boolean ren_flg=false;
	private int current_stock;
	
	
	public int getCurrent_stock() {
		return current_stock;
	}
	public void setCurrent_stock(int current_stock) {
		this.current_stock = current_stock;
	}
	public double getScanfee() {
		return scanfee;
	}
	public void setScanfee(double scanfee) {
		this.scanfee = scanfee;
	}
	public double getCal_scanfee() {
		this.cal_scanfee=this.no_of_bottle_per_case*0.35;
		return cal_scanfee;
	}
	public void setCal_scanfee(double cal_scanfee) {
		this.cal_scanfee = cal_scanfee;
	}
	public double getDt_scan_fee() {
		return dt_scan_fee;
	}
	public void setDt_scan_fee(double dt_scan_fee) {
		this.dt_scan_fee = dt_scan_fee;
	}
	public String getScan_fee_challan_no() {
		return scan_fee_challan_no;
	}
	public void setScan_fee_challan_no(String scan_fee_challan_no) {
		this.scan_fee_challan_no = scan_fee_challan_no;
	}
	public boolean isRen_flg() {
		return ren_flg;
	}
	public void setRen_flg(boolean ren_flg) {
		this.ren_flg = ren_flg;
	}
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
			this.importFee = impl.getPermitFee(brand_id, pckg_id);
			this.current_stock = impl.getCurrentstock(brand_id, pckg_id, quantity);
			
		} 
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	

	public double getMrp() {
		System.out.println("======MRP1======"+this.mrp+"=====Brand1====="+this.getBrand_id()+"=====Pckg1====="+this.getPckg_id());
		if (this.getBrand_id() != null && this.getPckg_id() != null) {
			this.mrp=impl.getmrp(brand_id, pckg_id);
			System.out.println("======MRP2======"+this.mrp+"======Brand2======"+brand_id+"======Pckg2======"+pckg_id);
			if(this.mrp>=2500){
				this.ren_flg=true;
				this.no_of_bottle_per_case=1;
				
			}else{
				this.ren_flg=false;
				//this.no_of_bottle_per_case=0;
			}
		}
		return mrp;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}	
	
	
	public String getEtin() {
		if (this.brand_id != null && this.pckg_id != null) {
			this.etin = impl.getEtinNmbr(brand_id, pckg_id,this);
			this.getMrp();
		}
		return etin;
	}

	public void setEtin(String etin) {
		this.etin = etin;
	}

	public int getPland_no_of_bottles() {
		if (quantity != null && quantity.length() > 0) {
			if (this.no_of_cases > 0) {
				this.pland_no_of_bottles = (this.no_of_cases * new Double(this.no_of_bottle_per_case).intValue());
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
		//System.out.println("importFee="+importFee);
		this.cal_importFee = this.pland_no_of_bottles * this.importFee;
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
		this.calculated_duty=this.pland_no_of_bottles*this.duty;
		return calculated_duty;
	}

	public void setCalculated_duty(double calculated_duty) {
		this.calculated_duty = calculated_duty;
	}

	public double getCalculated_add_duty() {
		this.calculated_add_duty=this.pland_no_of_bottles*this.adduty;
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
		//System.out.println("1="+quantity+pland_no_of_bottles+special_fee);
		if (quantity != null && quantity.length() > 0) {
			if (this.pland_no_of_bottles > 0) {
				this.special_fee = (this.pland_no_of_bottles * Double.parseDouble(quantity)*this.spcl_fee)/1000;
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
	public String scan_fee_challan_no;

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
	private String consignor_nm_adrs;
	public String getConsignor_nm_adrs() {
		return consignor_nm_adrs;
	}
	public void setConsignor_nm_adrs(String consignor_nm_adrs) {
		this.consignor_nm_adrs = consignor_nm_adrs;
	}
	
}
