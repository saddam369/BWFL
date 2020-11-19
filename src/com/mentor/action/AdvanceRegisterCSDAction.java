package com.mentor.action;

import java.text.ParseException;
import java.util.*;

import javax.faces.event.ValueChangeEvent;

import com.mentor.impl.AdvanceRegisterCSDImpl;

public class AdvanceRegisterCSDAction {
  AdvanceRegisterCSDImpl impl = new AdvanceRegisterCSDImpl();
	//====================variables=============
	private String name;
	private String address;
	private ArrayList unitList=new ArrayList();
	private ArrayList showData=new ArrayList();
	private Date date;
	private String unit_id;
	private int int_id;
	private int int_district_id;
	private String login_type;
	private String radio;
	private String depo_id;
	private ArrayList depoList = new ArrayList();
	private String unitradio;
	private String hidden;
	private double total_deposite;
	private double total_permit;
	private double total_bal;
	private int i=0;
	
	//=================Getter Setter=======
	
	public String getLogin_type() {
		
		return login_type;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}

   public double getTotal_deposite() {
		return total_deposite;
	}

	public void setTotal_deposite(double total_deposite) {
		this.total_deposite = total_deposite;
	}
	public double getTotal_permit() {
		return total_permit;
	}
	public void setTotal_permit(double total_permit) {
		this.total_permit = total_permit;
	}
	public double getTotal_bal() {
		return total_bal;
	}
	public void setTotal_bal(double total_bal) {
		this.total_bal = total_bal;
	}
	public String getHidden() {
		impl.getDetails(this);
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	public String getUnitradio() {
		return unitradio;
	}
	public void setUnitradio(String unitradio) {
		this.unitradio = unitradio;
	}
	public String getDepo_id() {
		return depo_id;
	}
	public void setDepo_id(String depo_id) {
		this.depo_id = depo_id;
	}
	public ArrayList getDepoList() {
		this.depoList=impl.getDepoList1(this);
		return depoList;
	}
	public void setDepoList(ArrayList depoList) {
		this.depoList = depoList;
	}
	public String getRadio() {
		if(this.i>1){
			this.radio=null;
		}
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	public void setLogin_type(String login_type) {
		this.login_type = login_type;
	}
	public int getInt_id() {
		return int_id;
	}
	public void setInt_id(int int_id) {
		this.int_id = int_id;
	}
	public int getInt_district_id() {
		return int_district_id;
	}
	public void setInt_district_id(int int_district_id) {
		this.int_district_id = int_district_id;
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
	public ArrayList getUnitList() {
		return unitList;
	}
	public void setUnitList(ArrayList unitList) {
		this.unitList = unitList;
	}
	public ArrayList getShowData() {
		
		return showData;
	}
	public void setShowData(ArrayList showData) {
		this.showData = showData;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}
	
//==============================Methods==============	
	
	public void listener(ValueChangeEvent e){
		String val=(String) e.getNewValue();
		this.radio=val;
		this.total_bal=0;
		this.total_deposite=0;
		this.total_permit=0;
		i=1;
		this.showData=impl.datalist(this);
	}
	
	public void listener1(ValueChangeEvent e){
		String val=(String) e.getNewValue();
		this.unitradio=val;
		reset();
		if(this.unitradio.equalsIgnoreCase("D")){
			this.unitList=impl.getDepoList(this);
		}else if(this.unitradio.equalsIgnoreCase("U")){
			this.unitList=impl.getUnitList();
		}
	}
	
	public void listener2(ValueChangeEvent e){
		String val=(String) e.getNewValue();
		this.depo_id=val;
		i++;
		reset();
	}
	public void listener3(ValueChangeEvent e){
		
		String val=(String) e.getNewValue();
		this.unit_id=val;
		this.i++;
		reset();
	}
	
	public void reset(){
		this.showData.clear();
		this.total_bal=0;
		this.total_deposite=0;
		this.total_permit=0;
	}
}
