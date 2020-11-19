package com.mentor.action;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.richfaces.component.UIDataTable;

import com.mentor.datatable.Fl2ScanningDataTable;
import com.mentor.datatable.StockAdjustmentDT;
import com.mentor.impl.StockAdjustmentImpl;
import com.mentor.utility.Validate;

public class StockAdjustmentAction {

	StockAdjustmentImpl impl = new StockAdjustmentImpl();

	private String hidden;
	private int loginId;
	private String loginName;
	private String loginAdrs;
	private String loginType;
	private String loginLicenseNmbr;
	private String radioType="X";
	private ArrayList brandPackagingDataList = new ArrayList();
	private ArrayList brandPackagingShowDataList = new ArrayList();

	public String getHidden() {
		try {
			impl.getLoginDetails(this);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getRadioType() {
		return radioType;
	}

	public void setRadioType(String radioType) {
		this.radioType = radioType;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginAdrs() {
		return loginAdrs;
	}

	public void setLoginAdrs(String loginAdrs) {
		this.loginAdrs = loginAdrs;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getLoginLicenseNmbr() {
		return loginLicenseNmbr;
	}

	public void setLoginLicenseNmbr(String loginLicenseNmbr) {
		this.loginLicenseNmbr = loginLicenseNmbr;
	}

	public ArrayList getBrandPackagingDataList() {
		return brandPackagingDataList;
	}

	public void setBrandPackagingDataList(ArrayList brandPackagingDataList) {
		this.brandPackagingDataList = brandPackagingDataList;
	}

	public ArrayList getBrandPackagingShowDataList() {
		try{
			if (!this.radioType.equalsIgnoreCase("X")) {
			this.brandPackagingShowDataList = impl.getShowDataTable(this);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return brandPackagingShowDataList;
	}

	public void setBrandPackagingShowDataList(
			ArrayList brandPackagingShowDataList) {
		this.brandPackagingShowDataList = brandPackagingShowDataList;
	}

	public String addRowMethodPackaging() {

		StockAdjustmentDT dt = new StockAdjustmentDT();
		dt.setSno(brandPackagingDataList.size() + 1);
		brandPackagingDataList.add(dt);

		return "";
	}

	public void deleteRowMethodPackaging(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
		StockAdjustmentDT dt = (StockAdjustmentDT) this.brandPackagingDataList.get(uiTable.getRowIndex());
		this.brandPackagingDataList.remove(dt);
	}

	public void radioListiner(ValueChangeEvent e) {
		
		try {
			String val = (String) e.getNewValue();
			this.setRadioType(val);
			
			if (!this.radioType.equalsIgnoreCase("X")) {
				this.brandPackagingShowDataList = impl.getShowDataTable(this);
				}
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
	
	
public boolean validate;
	
	
	public boolean isValidate() {
		
		this.validate=true;
		if(this.radioType==null)
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Select Radio","Please Select Radio"));
			this.validate=false;
		}
		
		for (int i = 0; i < this.brandPackagingDataList.size(); i++) {
			StockAdjustmentDT dt = (StockAdjustmentDT) brandPackagingDataList.get(i);
			if(!(Validate.validateStrReqRow(i+1,"brandName", dt.getBrandPackagingData_Brand())))
			validate=false;
	    	else if(!(Validate.validateStrReqRow(i+1,"packaging", dt.getBrandPackagingData_Packaging())))
	    	validate=false;
	    	else if (dt.getBrandPackagingData_NoOfBoxes() <= 0 && dt.getBrandPackagingData_PlanNoOfBottling() <= 0) {				
					validate = false;
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"No.Of Boxes and Bottles Should be Greater Than Zero at Line "+ (i + 1),
					"No.Of Boxes and Bottles Should be Greater Than Zero at Line "+ (i + 1)));
					
					
					break;
				
			}
		}
		
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public void save() {

		try {
			if (isValidate()) {
				if(this.radioType.equalsIgnoreCase("D")){
					impl.saveDispatch(this);					
				}
				
				else if(this.radioType.equalsIgnoreCase("R")){
					impl.saveRecieved(this);					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void reset() {
		this.radioType="X";
		this.brandPackagingDataList.clear();
	}
//------------------------------------------------------------------
	private boolean flgtab;

	public boolean isFlgtab() {
		return flgtab;
	}

	public void setFlgtab(boolean flgtab) {
		this.flgtab = flgtab;
	}
	
	
	
}
