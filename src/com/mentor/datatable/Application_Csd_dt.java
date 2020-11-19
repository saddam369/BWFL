package com.mentor.datatable;

import java.util.ArrayList;

import com.mentor.action.Application_Csd_Action;
import com.mentor.impl.Application_Csd_Impl;

public class Application_Csd_dt {
	private int sno=1;
	private double special_fee;
	private double importFee=0;
	public double cal_importFee = 0;
	private double calculated_duty;
	private double duty;
	public String pckg_id;
	private String packageName;
	private String package_type;
	private double spcl_fee;
	public String brand_id;
	private String quantity;
	private double mrp;
	private String etin;
	private ArrayList packagingList = new ArrayList();
	private long no_of_bottle_per_case;
	private int no_of_cases = 0;
	private int pland_no_of_bottles;
	
	private double dt_import_fee;
	private double dt_duty;
	private double  dt_add_duty;
	private double  dt_special_fee;
	private int  dt_district_id;
	private double calculated_add_duty;
	private double  adduty;
	private String radio;
	private double cal_duty;
	private double cal_scanfee;
	private double cal_coronafee;


    
	public double getCal_coronafee() {
		this.cal_coronafee=this.pland_no_of_bottles *impl.getCeshFee(brand_id, pckg_id);
		return cal_coronafee;
	}

	public void setCal_coronafee(double cal_coronafee) {
		this.cal_coronafee = cal_coronafee;
	}

	public double getCal_scanfee() {
		this.cal_scanfee = this.pland_no_of_bottles * 0.35;
		return cal_scanfee;
	}

	public void setCal_scanfee(double cal_scanfee) {
		this.cal_scanfee = cal_scanfee;
	}

	public double getCal_duty() {
		  cal_duty=this.duty*this.pland_no_of_bottles;
		return cal_duty;
	}

	public void setCal_duty(double cal_duty) {
		this.cal_duty = cal_duty;
	}

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}
	Application_Csd_Impl impl= new Application_Csd_Impl();
	
	private ArrayList brandList = new ArrayList();
	
	public double getDuty() {
		return duty;
	}

	public void setDuty(double duty) {
		this.duty = duty;
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


	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
	public void setPackagingList(ArrayList packagingList) {
		this.packagingList = packagingList;
	}

	public void setNo_of_bottle_per_case(long no_of_bottle_per_case) {
		this.no_of_bottle_per_case = no_of_bottle_per_case;
	}



	
	public double getImportFee() {
		return importFee;
	}


	public void setImportFee(double importFee) {
		this.importFee = importFee;
	}


	public void setPland_no_of_bottles(int pland_no_of_bottles) {
		this.pland_no_of_bottles = pland_no_of_bottles;
	}


	public double getSpcl_fee() {
		return spcl_fee;
	}


	public void setSpcl_fee(double spcl_fee) {
		this.spcl_fee = spcl_fee;
	}

	public void setSpecial_fee(double special_fee) {
		this.special_fee = special_fee;
	}

	public void setCal_importFee(double cal_importFee) {
		this.cal_importFee = cal_importFee;
	}


	public int getSno() {
		return sno;
	}

	
	public void setSno(int sno) {
		this.sno = sno;
	}
	private int unit_id;
	

	/**
	 * @return the unit_id
	 */
	public int getUnit_id() {
		return unit_id;
	}

	/**
	 * @param unit_id the unit_id to set
	 */
	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}

	/**
	 * @param calculated_add_duty the calculated_add_duty to set
	 */
	public void setCalculated_add_duty(double calculated_add_duty) {
		this.calculated_add_duty = calculated_add_duty;
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
	  public double getSpecial_fee() {
			if (quantity != null && quantity.length() > 0) {
				System.out.println("pallned no of bottle=="+this.getPland_no_of_bottles());
				if (this.pland_no_of_bottles > 0) {
					this.special_fee = (this.pland_no_of_bottles*impl.getSpcl(brand_id, pckg_id, this));
					System.out.println("special_fee=="+special_fee);
				} else {
					this.special_fee = 0;
				}
			}
			return special_fee;
		}

	public double getCal_importFee() {
		System.out.println("cal import fee");
		
		if(this.getRadio().equalsIgnoreCase("O")){
          this.cal_importFee = this.pland_no_of_bottles * this.importFee;
		}
		return cal_importFee;
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
     



public String getEtin() {
	if (this.brand_id != null && this.pckg_id != null) {
		this.etin = impl.getEtinNmbr(brand_id, pckg_id,this);
		this.getMrp();
	}
	return etin;
}

public double getMrp(){
	System.out.println("======MRP1======"+this.mrp+"=====Brand1====="+this.getBrand_id()+"=====Pckg1====="+this.getPckg_id());
	if (this.getBrand_id() != null && this.getPckg_id() != null) {
		this.mrp=impl.getmrp(brand_id, pckg_id);
		System.out.println("======MRP2======"+this.mrp+"======Brand2======"+brand_id+"======Pckg2======"+pckg_id);
		if(this.mrp>=2500){
		
			this.no_of_bottle_per_case=1;
		}
	}
	return mrp;
}

public void setEtin(String etin) {
	this.etin = etin;
}

public String getQuantity() {
	
	if (this.brand_id != null && this.pckg_id != null) {
		this.quantity = impl.getqty(brand_id, pckg_id);
		this.importFee = impl.getPermitFee(brand_id, pckg_id);
		this.duty = impl.getDuty(brand_id,pckg_id);
		this.etin = impl.getEtinNmbr(brand_id, pckg_id,this);
		//if(this.getRadio().equalsIgnoreCase("O") || this.getRadio().equalsIgnoreCase("IU")){
		   
		/*}
		else if(this.getRadio().equalsIgnoreCase("D")){
			if(Integer.parseInt(this.quantity)<375)
				this.setSpcl_fee(1.00);
			else if(Integer.parseInt(this.quantity)>375 && Integer.parseInt(this.quantity)<750)
				this.setSpcl_fee(2.00);
			else if(Integer.parseInt(this.quantity)>750 || Integer.parseInt(this.quantity)==750)
				this.setSpcl_fee(3.00);
				
		}
		else if(this.getRadio().equalsIgnoreCase("B")){
			if(Integer.parseInt(this.quantity)<500)
				this.setSpcl_fee(0.50);
			else if(Integer.parseInt(this.quantity)>500 && Integer.parseInt(this.quantity)<650)
				this.setSpcl_fee(1.00);
			if(Integer.parseInt(this.quantity)==650 || Integer.parseInt(this.quantity)>650)
				this.setSpcl_fee(2.00);
		}*/
			
	} 
	return quantity;
}public void setQuantity(String quantity) {
	this.quantity = quantity;
}

public String getBrand_id() {
	return brand_id;
}

public void setBrand_id(String brand_id) {
	this.brand_id = brand_id;
}
public String getPckg_id() {
	return pckg_id;
}

public void setPckg_id(String pckg_id) {
	this.pckg_id = pckg_id;
}
public void setNo_of_cases(int no_of_cases) {
	this.no_of_cases = no_of_cases;
}

public long getNo_of_bottle_per_case() {
	return no_of_bottle_per_case;
}



public int getNo_of_cases() {
	return no_of_cases;
}

public double getCalculated_duty() {
	this.calculated_duty=this.pland_no_of_bottles*this.duty;
	return calculated_duty;
}







private int app_id;

public int getApp_id() {
	return app_id;
}

public void setApp_id(int app_id) {
	this.app_id = app_id;
}

private int dt_app_id;

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

public int getDt_district_id() {
	return dt_district_id;
}

public void setDt_district_id(int dt_district_id) {
	this.dt_district_id = dt_district_id;
	
}

public void setCalculated_duty(double calculated_duty) {
	this.calculated_duty = calculated_duty;
}

public double getCalculated_add_duty() {
	this.calculated_add_duty=this.pland_no_of_bottles*this.adduty;
	return calculated_add_duty;
}
public double getAdduty() {
	return adduty;
}

public void setAdduty(double adduty) {
	this.adduty = adduty;
}


































}


