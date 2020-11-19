package com.mentor.action;

import java.util.ArrayList;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.richfaces.component.UIDataTable;

import com.mentor.datatable.Stock_ReportDatatable;
import com.mentor.impl.Stock_ReportImpl;

public class Stock_ReportAction {

	private String radio;
	private String pdfname;	
	private ArrayList showDataTableList;
	private boolean printFlag;
	private boolean radioChangeFlag=false;
	
	Stock_ReportImpl impl = new Stock_ReportImpl();

	
	
	public boolean isRadioChangeFlag() {
		return radioChangeFlag;
	}
	public void setRadioChangeFlag(boolean radioChangeFlag) {
		this.radioChangeFlag = radioChangeFlag;
	}
	public boolean isPrintFlag() {
		return printFlag;
	}
	public void setPrintFlag(boolean printFlag) {
		this.printFlag = printFlag;
	}
	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public String getPdfname() {
		return pdfname;
	}

	public void setPdfname(String pdfname) {
		this.pdfname = pdfname;
	}

	
	public ArrayList getShowDataTableList() {
		try{
			if(this.radio!=null){
				this.showDataTableList = impl.getDataList(this);
			}
		}catch(Exception e){
			e.printStackTrace();
		}				
		return showDataTableList;
	}

	public void setShowDataTableList(ArrayList showDataTableList) {
		this.showDataTableList = showDataTableList;
	}
	
	public void radioChange(ValueChangeEvent e){
		String val = (String)e.getNewValue();
		this.setRadio(val);
		this.radioChangeFlag = true;
		this.printFlag = false;
	}
	
	public void print(ActionEvent e){	
		if (impl.printReport(this) == true) {
			this.setPrintFlag(true);
		} else {
			this.setPrintFlag(false);
		}	
	}
}
