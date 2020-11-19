package com.mentor.action;

import java.util.ArrayList;
import com.mentor.impl.ListOfApprovedBrandsImpl;

public class ListOfApprovedBrandsAction {
	
	private String licenseing="";

	public ArrayList getListdetails() {
		this.listdetails = new ListOfApprovedBrandsImpl().getDataList(this);
		return listdetails;
	}

	public void setListdetails(ArrayList listdetails) {
		this.listdetails = listdetails;
	}

	private String license_type;

	public ArrayList listdetails =new ArrayList();
	
	
	public String getLicense_type() {
		return license_type;
	}

	public void setLicense_type(String license_type) {
		this.license_type = license_type;
	}

	public String getLicenseing() {
		return licenseing;
	}

	public void setLicenseing(String licenseing) {
		this.licenseing = licenseing;
	}
	
	/*public void radiochangeevent(ValueChangeEvent e){
		String val = (String)e.getNewValue();
		ListOfApprovedBrandsImpl impl = new ListOfApprovedBrandsImpl();
		this.listdetails = impl.getDataList(this,val);
		
	}*/

	public void printMethod(){
		
	}
	
	public void reset() {
		this.licenseing = "";

	}

	private String pdfname;
	private boolean printFlag;

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
	
	public void print() 
	{
		System.out.println("hello");
		ListOfApprovedBrandsImpl loab=new ListOfApprovedBrandsImpl();
		loab.print(this);
	}
	
	



	
}
