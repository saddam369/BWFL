package com.mentor.action;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.richfaces.component.UIDataTable;

import com.mentor.datatable.BWFLAdjustmentOfflinePassDT;
import com.mentor.impl.BWFLAdjustmentOfflinePassImpl;

public class BWFLAdjustmentOfflinePassAction {

	BWFLAdjustmentOfflinePassImpl impl = new BWFLAdjustmentOfflinePassImpl();

	private String hidden;
	private int userId;
	private String userName;
	private String userAdrs;
	private String userType;
	private int userTypeId;
	private ArrayList availableStockList = new ArrayList();
	private ArrayList displayStockList = new ArrayList();

	public String getHidden() {
		try {
			impl.getUserDetails(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAdrs() {
		return userAdrs;
	}

	public void setUserAdrs(String userAdrs) {
		this.userAdrs = userAdrs;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	private boolean listFlag = true;

	public boolean isListFlag() {
		return listFlag;
	}

	public void setListFlag(boolean listFlag) {
		this.listFlag = listFlag;
	}

	public ArrayList getAvailableStockList() {
		
		try{
			if(this.listFlag == true){
				this.availableStockList = impl.availableStockImpl(this);
				this.listFlag = false;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return availableStockList;
	}

	public void setAvailableStockList(ArrayList availableStockList) {
		this.availableStockList = availableStockList;
	}

	public ArrayList getDisplayStockList() {
		try{
				this.displayStockList = impl.showSavedDataImpl(this);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return displayStockList;
	}

	public void setDisplayStockList(ArrayList displayStockList) {
		this.displayStockList = displayStockList;
	}
	
	private boolean validateInput;
	
	

	public boolean isValidateInput() {
		return validateInput;
	}

	public void setValidateInput(boolean validateInput) {
		this.validateInput = validateInput;
	}

	public void saveDetail(ActionEvent e) {

		try{
			
			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
			BWFLAdjustmentOfflinePassDT dt = (BWFLAdjustmentOfflinePassDT) this.availableStockList.get(uiTable.getRowIndex());
			
			//impl.saveMethodImpl(this, dt);
			
			if(dt.getDispatchBox_dt()<=dt.getAvailableBox_dt()){
				impl.saveMethodImpl(this, dt);
			}
			else{
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"No.Of Boxes Should be Less Than or equal to Available Boxes !! ","No.Of Boxes Should be Less Than or equal to Available Boxes !!"));
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
