package com.mentor.action;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.mentor.datatable.WholesaleBreakageStockDT;
import com.mentor.impl.WholesaleBreakageStockImpl;

public class WholesaleBreakageStockAction {

	WholesaleBreakageStockImpl impl = new WholesaleBreakageStockImpl();

	private String hidden;
	private int loginUserId;
	private String loginUserNm;
	private String loginUserAdrs;
	private String loginUserType;
	private String licenseNmbr;
	private ArrayList stockList = new ArrayList();
	private ArrayList showDataList = new ArrayList();

	public String getHidden() {
		try {
			impl.getDetails(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public int getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(int loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getLoginUserNm() {
		return loginUserNm;
	}

	public void setLoginUserNm(String loginUserNm) {
		this.loginUserNm = loginUserNm;
	}

	public String getLoginUserAdrs() {
		return loginUserAdrs;
	}

	public void setLoginUserAdrs(String loginUserAdrs) {
		this.loginUserAdrs = loginUserAdrs;
	}

	public String getLoginUserType() {
		return loginUserType;
	}

	public void setLoginUserType(String loginUserType) {
		this.loginUserType = loginUserType;
	}

	public String getLicenseNmbr() {
		return licenseNmbr;
	}

	public void setLicenseNmbr(String licenseNmbr) {
		this.licenseNmbr = licenseNmbr;
	}

	private boolean listFlagForPrint = true;

	public boolean isListFlagForPrint() {
		return listFlagForPrint;
	}

	public void setListFlagForPrint(boolean listFlagForPrint) {
		this.listFlagForPrint = listFlagForPrint;
	}

	public ArrayList getStockList() {
		try {
			if (this.listFlagForPrint == true) {
				this.stockList = impl.showStockList(this);
				this.listFlagForPrint = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stockList;
	}

	public void setStockList(ArrayList stockList) {
		this.stockList = stockList;
	}
	

	public ArrayList getShowDataList() {
		try {			
				this.showDataList = impl.displaySavedData(this);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showDataList;
	}

	public void setShowDataList(ArrayList showDataList) {
		this.showDataList = showDataList;
	}

	private boolean validateInput;

	public boolean isValidateInput() {

		validateInput = true;

		int sumBreakg = 0;

		for (int i = 0; i < this.stockList.size(); i++) {
			WholesaleBreakageStockDT dt = (WholesaleBreakageStockDT) this.stockList.get(i);

			if (dt.getBreakage_dt() > 0) {
				sumBreakg += dt.getBreakage_dt();
				if ((dt.getStockBottle_dt()) < 0) {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Stock Bottles Should Be greater than 0 at Line "+ (i + 1) + "  !!! ",
					"Stock Bottles Should Be greater than 0 at Line "+ (i + 1) + "  !!!"));
					validateInput = false;

				}
			}

		}
		
		if (sumBreakg == 0) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
			"Breakage Should Be Greater Than Zero !!! ","Breakage Should Be Greater Than Zero !!!"));
			
			validateInput = false;
		} 

		return validateInput;
	}

	public void setValidateInput(boolean validateInput) {
		this.validateInput = validateInput;
	}

	public void saveMethod() {

		try {
			if(isValidateInput()){
				impl.saveMethodImpl(this);
			}else{
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void reset() {
		this.listFlagForPrint = true;
	}

}
