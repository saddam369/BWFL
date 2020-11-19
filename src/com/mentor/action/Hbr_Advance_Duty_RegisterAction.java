package com.mentor.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mentor.impl.Hbr_Advance_Duty_RegisterImpl;

public class Hbr_Advance_Duty_RegisterAction {
	
	
	
	Hbr_Advance_Duty_RegisterImpl impl = new Hbr_Advance_Duty_RegisterImpl();
	
	private ArrayList hbr_list=new ArrayList();
	private ArrayList data_list=new ArrayList();
	private String hbr_id;
	private String radio="F";
	private Date fromdate;
	private Date todate;

public Date getFromdate() throws ParseException{
		
		
		String nov = "01/04/2020";
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(nov);
		this.fromdate = date1;
		
		
		
		return fromdate;
	}
	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public Date getTodate() {
		return todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public String getHbr_id() {
		return hbr_id;
	}

	public void setHbr_id(String hbr_id) {
		this.hbr_id = hbr_id;
	}

	public ArrayList getHbr_list() {
		
		try{
			this.hbr_list=new Hbr_Advance_Duty_RegisterImpl().getHotelBarRest(this);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return hbr_list;
	}

	public void setHbr_list(ArrayList hbr_list) {
		this.hbr_list = hbr_list;
	}
	
	public void getData()
	{
		try{
			
			//System.out.println("comee in for fetch data");
			this.data_list=new Hbr_Advance_Duty_RegisterImpl().getAdvanceDutyRegister(this);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public ArrayList getData_list() {
		return data_list;
	}
	public void setData_list(ArrayList data_list) {
		this.data_list = data_list;
	}
	public void reset()
	{
		try{
			this.data_list.clear();
			this.todate=null;
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
//===============
	public int dis_id ;
	public int getDis_id() {
		return dis_id;
	}
	public void setDis_id(int dis_id) {
		this.dis_id = dis_id;
	}
	
	public ArrayList hidden = new ArrayList();
	
	public ArrayList getHidden() 
	{

		impl.getDetails(this);

		return hidden;
	}
	public void setHidden(ArrayList hidden) {
		this.hidden = hidden;
	}

}
