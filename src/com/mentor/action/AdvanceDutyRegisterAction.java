package com.mentor.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.mentor.impl.AdvanceDutyRegisterImpl;

public class AdvanceDutyRegisterAction {
	
	AdvanceDutyRegisterImpl impl=new AdvanceDutyRegisterImpl();
	
	
	private String hidden;
	public int dist_id;
	public String name;
	public String address;
	private Date advDate;
	private String type;
	private Date fromdate;
	private Date todate;
	private ArrayList showData = new ArrayList();
	private Date toOpningDate;
	private int sessionId;
	private String session_Name;
	private boolean from_Date_flag=true;
	private int bal;
	private String typep;
	
	
	
	
	
	
	
	public String getTypep() {
		return typep;
	}

	public void setTypep(String typep) {
		this.typep = typep;
	}

	public int getBal() {
		return bal;
	}

	public void setBal(int bal) {
		this.bal = bal;
	}

	public boolean isFrom_Date_flag() {
		return from_Date_flag;
	}

	public void setFrom_Date_flag(boolean from_Date_flag) {
		this.from_Date_flag = from_Date_flag;
	}

	public String getHidden() {
		try {
			impl.getDetails(this);
			impl.getSeasonDetails(this);

		} catch (Exception e) {
		}
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	
	public Date getToOpningDate() throws ParseException {
		
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(expiredDate);
		this.toOpningDate = date1;
		
	
		return toOpningDate;
	}

	public void setToOpningDate(Date toOpningDate) {
		this.toOpningDate = toOpningDate;
	}

	
	
	
	
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

	
	

	public ArrayList getShowData() {
		return showData;
	}

	public void setShowData(ArrayList showData) {
		this.showData = showData;
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

	public Date getAdvDate() {
		return advDate;
	}

	public void setAdvDate(Date advDate) {
		this.advDate = advDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	
	
	

	public String getSession_Name() {
		return session_Name;
	}

	public void setSession_Name(String session_Name) {
		this.session_Name = session_Name;
	}

	
	
	
	
	
	
	public void getdata1()
	{
		this.showData=impl.getShowData(this);
	}

	String st = "01/04/2019";

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	String expiredDate = null;

	public String getdata() throws ParseException {

	Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(st);

	

			if (this.fromdate.before(date1)) {

				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
				"From Date should not be less than 01 APRIL 2019","From Date should not be less than 01 APRIL 2019"));

				this.showData.clear();

			} else {

				

				try {
					

					if(this.type!=null && this.todate!=null){
						String currentDate = dateFormat.format(this.todate);
						Calendar cal = Calendar.getInstance();
						cal.setTime(dateFormat.parse(currentDate));
						cal.add(Calendar.DATE, -1);
						expiredDate = dateFormat.format(cal.getTimeInMillis());
						
					this.showData=impl.getShowData(this);
				
					
					}else{
						FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"All Field Mandatory","All Field Mandatory"));
					}
					
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
				

			}

		

		return "";
	}
	
	
	
	
	
	
	
	
	public void reset()
	{
		this.fromdate=null;
		this.todate=null;
		this.printFlag=false;
		this.showData.clear();
		this.type=null;
		
	}


	private ArrayList typeList = new ArrayList();



	public ArrayList getTypeList() {
		this.typeList = impl.getTypeList();
		return typeList;
	}

	public void setTypeList(ArrayList typeList) {
		this.typeList = typeList;
	}
	private String pdfname;
	private boolean printFlag=false;
	public String getPdfname() {
		return pdfname;
	}

	public void setPdfname(String pdfname) {
		this.pdfname = pdfname;
	}

	public boolean isPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(boolean printFlag) {
		this.printFlag = printFlag;
	}

	public void print() throws Exception
	{
		if(this.type!=null && this.todate!=null){
			
			//impl.getopening(this);
			
			impl.print(this);
		
			
			}else{
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"All Field Mandatory",
								"All Field Mandatory"));
			}
		
	}

	
}
