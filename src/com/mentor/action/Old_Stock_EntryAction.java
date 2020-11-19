package com.mentor.action;


	import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

	import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

	import org.richfaces.component.UIDataTable;



import com.mentor.datatable.Fl2ScanningShowDataTable;
	import com.mentor.datatable.Old_Stock_EntryDT;
import com.mentor.datatable.Old_Stock_EntryDT;

import com.mentor.impl.Old_Stock_EntryImpl;


	public class Old_Stock_EntryAction {
		
		private ArrayList disList = new ArrayList();
		
			


		public ArrayList getDisList() {
			try{
			this.disList=new Old_Stock_EntryImpl().getDisListOup(this);
		}
		catch(Exception e){
			e.printStackTrace();
		}
			
			return disList;
		}

		public void setDisList(ArrayList disList) {
			this.disList = disList;
		}



		private ArrayList disList_state=new ArrayList();
		
		
		public void setDisList_state(ArrayList disList_state) {
			this.disList_state = disList_state;
		}



		private ArrayList brandPackagingDataList=new ArrayList();
		private ArrayList brandPackagingShowDataList=new ArrayList();
		private Fl2ScanningShowDataTable flsd; 
		private Fl2ScanningShowDataTable lsd; 
		private Fl2ScanningShowDataTable tsd; 
		
		
	    public Fl2ScanningShowDataTable getTsd() {
			return tsd;
		}

		public void setTsd(Fl2ScanningShowDataTable tsd) {
			this.tsd = tsd;
		}

		public Fl2ScanningShowDataTable getLsd() {
			return lsd;
		}

		public void setLsd(Fl2ScanningShowDataTable lsd) {
			this.lsd = lsd;
		}

		public Fl2ScanningShowDataTable getFlsd() {
			return flsd;
		}

		public void setFlsd(Fl2ScanningShowDataTable flsd) {
			this.flsd = flsd;
		}

		public ArrayList getBrandPackagingShowDataList() {
	    	
	    	this.brandPackagingShowDataList=new Old_Stock_EntryImpl().getShowDataTable(this);
			return brandPackagingShowDataList;
		}

		public void setBrandPackagingShowDataList(ArrayList brandPackagingShowDataList) {
			this.brandPackagingShowDataList = brandPackagingShowDataList;
		}


		
		// private Date dateOfBottling = "";
		
		private Date dateOfBottling;
		
		
		private String distillery_id;
		private String name;
		private String loginType;
		private String licenceNo;
		private String etin;
		private Date date=new Date();
		private String radio=" ";
		private String drpdown;
		private boolean drpFlg;
		
		
		public String getDrpdown() {
			return drpdown;
		}

		public void setDrpdown(String drpdown) {
			this.drpdown = drpdown;
		}

		public boolean isDrpFlg() {
			return drpFlg;
		}

		public void setDrpFlg(boolean drpFlg) {
			this.drpFlg = drpFlg;
		}

		public String getRadio() {
			return radio;
		}

		public void setRadio(String radio) {
			this.radio = radio;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		
		



		public String getEtin() {
			return etin;
		}

		public void setEtin(String etin) {
			this.etin = etin;
		}

		public String getLicenceNo() {
			return licenceNo;
		}

		public void setLicenceNo(String licenceNo) {
			this.licenceNo = licenceNo;
		}



		private String hidden;
		
		
		
		public boolean validate;
		
		
		public boolean isValidate() {
			
			this.validate=true;
			if(this.dateOfBottling==null)
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Select Date","Please Select Date"));
				this.validate=false;
			}
			
			for (int i = 0; i < this.brandPackagingDataList.size(); i++) {
				Old_Stock_EntryDT dt = (Old_Stock_EntryDT) brandPackagingDataList.get(i);
				if (dt.getBrandPackagingData_NoOfBoxes() <= 0 && dt.getBrandPackagingData_PlanNoOfBottling() <= 0) {
					
						validate = false;
						FacesContext.getCurrentInstance()
								.addMessage(
										null,
										new FacesMessage(
												FacesMessage.SEVERITY_ERROR,
												"No.Of Boxes and Bottles Should be Greater Than Zero at Line "
														+ (i + 1),
												"No.Of Boxes and Bottles Should be Greater Than Zero at Line "
														+ (i + 1)));
						break;
					
				}
			}
			
			return validate;
		}

		public void setValidate(boolean validate) {
			this.validate = validate;
		}

	/*	public String getHidden() {
			try{
				new Old_Stock_EntryImpl().getDetails(this);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return hidden;
		}

		public void setHidden(String hidden) {
			this.hidden = hidden;
		}*/

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLoginType() {
			return loginType;
		}

		public void setLoginType(String loginType) {
			this.loginType = loginType;
		}

		public String getDistillery_id() {
			return distillery_id;
		}

		public void setDistillery_id(String distillery_id) {
			this.distillery_id = distillery_id;
		}
		
		//SimpleDateFormat sd = new SimpleDateFormat("dd/mm/yyyy");
		public Date getDateOfBottling() 
		{
			try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			this.dateOfBottling = sdf.parse("01-03-2018");
			}catch(Exception e){
				e.printStackTrace();
			}
			
			 //   this.dateOfBottling=sd.parse("09/09/2018");
			
			return dateOfBottling;
		}

		public void setDateOfBottling(Date dateOfBottling) {
			this.dateOfBottling = dateOfBottling;
		}

		public ArrayList getBrandPackagingDataList() {
			return brandPackagingDataList;
		}

		public void setBrandPackagingDataList(ArrayList brandPackagingDataList) {
			this.brandPackagingDataList = brandPackagingDataList;
		}

		
		
		
		
		
		
		public String addRowMethodPackaging() {
			
			Old_Stock_EntryDT dt = new Old_Stock_EntryDT();
			dt.setSno(brandPackagingDataList.size() + 1);
			brandPackagingDataList.add(dt);
		
		return "";
	}
		
		
		
		

		public void deleteRowMethodPackaging(ActionEvent e) {
			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
					.getParent();
			Old_Stock_EntryDT dt = (Old_Stock_EntryDT) this.brandPackagingDataList.get(
					uiTable.getRowIndex());
			this.brandPackagingDataList.remove(dt);
		}
		
		
		public void save()
		{
			
			try{
				if(isValidate())
				{
				new Old_Stock_EntryImpl().save(this);
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
		
		public void reset()
		{
			try{
				this.brandPackagingDataList.clear();
			}catch(Exception e)
			{
				
			}
		}
	
		public void chngval(ValueChangeEvent e) {

			
			
		}
		public void radioListener(ValueChangeEvent e) {
			/*this.drpFlg = true;
			
			String val = (String) e.getNewValue();
			try {System.out.println("cxzcz");
				this.List.clear();
				this.List = new Old_Stock_EntryImpl().getLicenceList(this, val);
			} catch (Exception ex) {
				ex.printStackTrace();
			}*/
			
		}
	
	
			
		
	
			public String districtChangeEvent(ValueChangeEvent event)
			{
				
				return "";
			}
			
			
			
			
			
			
			
			public void finalizeData()
			{
				
				
				try{
					
					
				 
					new Old_Stock_EntryImpl().finalizeData(this, flsd);
					
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
				
			}
			
			
			
		public void print()
		{
			
			
			try{
				new Old_Stock_EntryImpl().writeCsv(lsd, this);
				new Old_Stock_EntryImpl().write(lsd, this);
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
			
		}
		public void transfer()
		{
			
			
			try{
				new Old_Stock_EntryImpl().transfer( this,tsd);
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
			
		}
}
