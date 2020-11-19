package com.mentor.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.richfaces.component.UIDataTable;



import com.mentor.datatable.Fl2ScanningDataTable;
import com.mentor.datatable.Fl2ScanningShowDataTable;
import com.mentor.impl.Fl2ScanningImpl;




public class Fl2ScanningAction {
	
	public String finalizedLast;
	
	
	public String getFinalizedLast() {
		return finalizedLast;
	}

	public void setFinalizedLast(String finalizedLast) {
		this.finalizedLast = finalizedLast;
	}


 public String hidden;

	private ArrayList brandPackagingDataList=new ArrayList();
	public ArrayList brandPackagingShowDataList=new ArrayList();
	
    public ArrayList getBrandPackagingShowDataList() {
    	
    	//this.brandPackagingShowDataList=new Fl2ScanningImpl().getShowDataTable(this);
		return brandPackagingShowDataList;
	}

	public void setBrandPackagingShowDataList(ArrayList brandPackagingShowDataList) {
		this.brandPackagingShowDataList = brandPackagingShowDataList;
	}


	
	// private Date dateOfBottling = "";
	
	private Date dateOfBottling;
	
	
	private int distillery_id;
	private String name;
	private String loginType;
	
	
	
	
	
	public boolean resetFlag=true;
	
	
	public boolean isResetFlag() {
		return resetFlag;
	}

	public void setResetFlag(boolean resetFlag) {
		this.resetFlag = resetFlag;
	}


	public boolean validate;
	
	
	public boolean isValidate() {
		
		this.validate=true;
		if(this.dateOfBottling==null)
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Select Date","Please Select Date"));
			this.validate=false;
		}
		
		for (int i = 0; i < this.brandPackagingDataList.size(); i++) {
			Fl2ScanningDataTable dt = (Fl2ScanningDataTable) brandPackagingDataList.get(i);
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

	public String getHidden() {
		try{
			new Fl2ScanningImpl().getDetails(this);
			if(this.resetFlag)
			{
			this.brandPackagingShowDataList=new Fl2ScanningImpl().getShowDataTable(this);
			this.resetFlag=false;
			}
			if(this.brandPackagingShowDataList.size()==0)
			{
				this.finalizedLast="N";
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

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

	public int getDistillery_id() {
		return distillery_id;
	}

	public void setDistillery_id(int distillery_id) {
		this.distillery_id = distillery_id;
	}
	
	
	public Date getDateOfBottling()throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String dateInString="";
		 
		if(this.loginType.equalsIgnoreCase("FL2B"))
		{
			
			dateInString = "09-09-2018";
		}
		else if(this.loginType.equalsIgnoreCase("CL2"))
		{
			dateInString = "07-03-2019";
			
		}
		 this.dateOfBottling = sdf.parse(dateInString);
		
	
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
		
		Fl2ScanningDataTable dt = new Fl2ScanningDataTable();
		dt.setSno(brandPackagingDataList.size() + 1);
		brandPackagingDataList.add(dt);
	
	return "";
}
	
	
	
	

	public void deleteRowMethodPackaging(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		Fl2ScanningDataTable dt = (Fl2ScanningDataTable) this.brandPackagingDataList.get(
				uiTable.getRowIndex());
		this.brandPackagingDataList.remove(dt);
	}
	
	
	public void save()
	{
		
		try{
			if(isValidate())
			{
			new Fl2ScanningImpl().save(this);
			this.brandPackagingShowDataList=new Fl2ScanningImpl().getShowDataTable(this);
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
			this.brandPackagingShowDataList=new Fl2ScanningImpl().getShowDataTable(this);
		}catch(Exception e)
		{
			
		}
	}
	
	Fl2ScanningImpl impl=new Fl2ScanningImpl();
	
	public void update(ActionEvent e)
	{
		
		
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
		Fl2ScanningShowDataTable dt = (Fl2ScanningShowDataTable) this.brandPackagingShowDataList.get(uiTable.getRowIndex());
		
		
		
		this.setBranidF(dt.getBranidF());
		this.setPckidF(dt.getPckidF());
		this.setFl2idF(dt.getFl2idF());
		this.setSeqF(dt.getSeqF());
		this.setNo_of_box_New(dt.getNo_of_box());
		this.setNo_of_planned_bottle_New(dt.getNo_of_planned_bottle());
		
		if(dt.getNo_of_planned_bottle()>0)
		{
			impl.updateBottLe(this);
		}
		
		
		
	}
	
	
	
	public void finalize( )
	{
		impl.finalize(this);
		this.brandPackagingShowDataList=new Fl2ScanningImpl().getShowDataTable(this);
	}
	
	
	
	
	
	
	
	
	
	private int no_of_box_New;
	private int no_of_planned_bottle_New;
	private int branidF;
	private int pckidF;
	private int fl2idF;
	private int seqF;
	
	
	
	public int getSeqF() {
		return seqF;
	}

	public void setSeqF(int seqF) {
		this.seqF = seqF;
	}

	public int getBranidF() {
		return branidF;
	}

	public void setBranidF(int branidF) {
		this.branidF = branidF;
	}

	public int getPckidF() {
		return pckidF;
	}

	public void setPckidF(int pckidF) {
		this.pckidF = pckidF;
	}

	public int getFl2idF() {
		return fl2idF;
	}

	public void setFl2idF(int fl2idF) {
		this.fl2idF = fl2idF;
	}

	public int getNo_of_box_New() {
		return no_of_box_New;
	}

	public void setNo_of_box_New(int no_of_box_New) {
		this.no_of_box_New = no_of_box_New;
	}

	public int getNo_of_planned_bottle_New() {
		return no_of_planned_bottle_New;
	}

	public void setNo_of_planned_bottle_New(int no_of_planned_bottle_New) {
		this.no_of_planned_bottle_New = no_of_planned_bottle_New;
	}
	
	
	
	
	
	
	
	private String pdfname;

	private boolean printFlag=false;
	
	
	//-------------------print report-----------------
	
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



	 

	public void print() {
		new Fl2ScanningImpl().printReport(this);
	}

	
	
	private boolean panelFlg;


	public boolean isPanelFlg() {
		
		panelFlg=new Fl2ScanningImpl().panelFlg();
		
		return panelFlg;
	}

	public void setPanelFlg(boolean panelFlg) {
		this.panelFlg = panelFlg;
	}
	
	
}
