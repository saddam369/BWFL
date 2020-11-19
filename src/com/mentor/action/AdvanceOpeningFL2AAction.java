package com.mentor.action;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.component.EditableValueHolder;

import com.mentor.impl.AdvanceOpeningFL2AImpl;

public class AdvanceOpeningFL2AAction {
	AdvanceOpeningFL2AImpl impl = new AdvanceOpeningFL2AImpl();
	private double amount;
	private Date date;
	private ArrayList unit_list = new ArrayList();
	private String unit_id;
	private boolean flag = true;

	public String getUnit_id() {
		return unit_id;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	public ArrayList getUnit_list() {
		
		if (this.radio.equals("DEPO")) {
			this.unit_type = "IMPORT FEE";
			
			System.out.println("===="+this.getUnit_type());
			if (flag == true) {
				this.unit_list = impl.getdisUnitList(this);

				//impl.loginmethod1(this);
				impl.Districtmethod1(this);
				this.flag = false;
			}
		}
		/*
		 * if (this.radio != null) { if (this.radio.equalsIgnoreCase("D")) { if
		 * (flag == true) { this.unit_list = impl.getdisUnitList(this);
		 * impl.loginmethod1(this); impl.Districtmethod1(this); this.flag =
		 * false; } } else if (this.radio.equalsIgnoreCase("U")) { if (flag ==
		 * true) { this.unit_list = impl.getdisUnitList(this);
		 * impl.loginmethod1(this); this.flag = false; } } }
		 */
		return unit_list;
	}

	public void setUnit_list(ArrayList unit_list) {
		this.unit_list = unit_list;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	private String radio = "DEPO";

	public String getRadio() {
		this.flag = true;
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public void radioVal(ValueChangeEvent ee) {
		String val = (String) ee.getNewValue();
		this.setRadio(val);
		
	/*	String val=(String) ((EditableValueHolder) ee.getComponent().getParent())
				.getValue();*/
		this.setRadio(val);
		System.out.println("==getRadio kjkk22=="+this.getRadio());
		if (this.radio != null) {
			if (this.radio.equals("DEPO")) {
				this.unit_type = "IMPORT FEE";
				
				System.out.println("===="+this.getUnit_type());
				if (flag == true) {
					this.unit_list = impl.getdisUnitList(this);

					//impl.loginmethod1(this);
					impl.Districtmethod1(this);
					this.flag = false;
				}
			} else if (this.radio.equals("UNIT")) {
				if (flag == true) {
					this.unit_list = impl.getdisUnitList(this);
					impl.loginmethod1(this);
					System.out.println("===="+this.getUnit_type());
					if (this.unit_type.equals("EXCISE DUTY")) {
						this.unit_type = "EXCISE DUTY";
					} else if (this.unit_type.equals("SPECIAL FEE")) {
						this.unit_type = "SPECIAL FEE";
					} else if (this.unit_type.equals("SCANING FEE")) {
						this.unit_type = "SCANING FEE";
					} else if (this.unit_type.equals("SPECIAL CONSIDERATION FEE")) {
						this.unit_type = "SPECIAL CONSIDERATION FEE";
					}

					this.flag = false;
				}
			}
		}
		/*
		 * if (this.radio.equalsIgnoreCase("D")) {
		 * 
		 * this.unit_type = "IF";
		 * 
		 * System.out.println("==============" + getUnit_type()); }
		 */

	}

	public void save() {
		System.out.println("=savesave=getUnit_type==="+this.getUnit_type());
		if(this.unit_type.length() > 0  &&this.unit_type != null){
		if (this.unit_id.length() > 0 && this.unit_id != null) {
			if (this.amount > 0.0) {
				
				if (impl.checksavedetail(this) == false){
				impl.saveImpl(this);
				this.reset();
				}
				else{
					impl.updatedata(this);
					this.reset();
				}
			
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Please Enter Amount !!",
								"Please Enter Amount !!  "));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Please Select Unit !!", "Please Select Unit !!"));
		}
		}
		else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Please Select Fee TYPE !!", "Please Select Fee TYPE!!"));
		}
	}

	public void unitChangeEvent(ValueChangeEvent ee) {
		String val = (String) ee.getNewValue();
		this.setRadio(val);
		System.out.println("==unitChangeEvent==" + getRadio());
		if (this.radio != null) {
			if (this.radio.equals("DEPO")) {
				this.unit_type = "IMPORT FEE";
				System.out.println("===unit_type==" + this.getUnit_type());
			} else {
				if (this.unit_type.equals("EXCISE DUTY")) {
					this.unit_type = "EXCISE DUTY";
				} else if (this.unit_type.equals("SPECIAL FEE")) {
					this.unit_type = "SPECIAL FEE";
				} else if (this.unit_type.equals("SCANING FEE")) {
					this.unit_type = "SCANING FEE";
				} else if (this.unit_type.equals("SPECIAL CONSIDERATION FEE")) {
					this.unit_type = "SPECIAL CONSIDERATION FEE";
				}
			}//valueChangeListener="#{advanceOpeningFL2AAction.unitChangeEvent}"

		}
	}



	public void reset() {
		//this.unit_list.clear();
		this.unit_id = null;
		this.amount = 0.0;
		this.date = null;
		this.unit_type = "0";
	}

	private String login_id;

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	private String open_date = "01-04-2020";

	public String getOpen_date() {
		return open_date;
	}

	public void setOpen_date(String open_date) {
		this.open_date = open_date;
	}

	private String unit_type;

	public String getUnit_type() {
		/*
		 * if (this.radio != null) { if (this.radio.equals("D")) {
		 * this.unit_type = "IMPORT FEE"; } else { if
		 * (this.unit_type.equals("EXCISE DUTY")) { this.unit_type =
		 * "EXCISE DUTY"; } else if (this.unit_type.equals("SPECIAL FEE")) {
		 * this.unit_type = "SPECIAL FEE"; } else if
		 * (this.unit_type.equals("SCANING FEE")) { this.unit_type =
		 * "SCANING FEE"; } else if
		 * (this.unit_type.equals("SPECIAL CONSIDERATION FEE")) { this.unit_type
		 * = "SPECIAL CONSIDERATION FEE"; } }
		 * 
		 * }
		 */
		return unit_type;
	}

	public void setUnit_type(String unit_type) {
		this.unit_type = unit_type;
	}

	private ArrayList getshowdata = new ArrayList();

	public ArrayList getGetshowdata() {
		this.getshowdata = impl.viewdetail(this);
		return getshowdata;
	}

	public void setGetshowdata(ArrayList getshowdata) {
		this.getshowdata = getshowdata;
	}

	private int distict_id;

	private String depo_nm;

	public String getDepo_nm() {
		return depo_nm;
	}

	public int getDistict_id() {
		return distict_id;
	}

	public void setDistict_id(int distict_id) {
		this.distict_id = distict_id;
	}

	public void setDepo_nm(String depo_nm) {
		this.depo_nm = depo_nm;
	}
	
	private int open_int_id;

	public int getOpen_int_id() {
		return open_int_id;
	}

	public void setOpen_int_id(int open_int_id) {
		this.open_int_id = open_int_id;
	}

}
