package com.mentor.action;







import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.richfaces.component.UIDataTable;

import com.mentor.datatable.FL2D_Permit_validity_ExtDataTable;
import com.mentor.datatable.PendingPermitDT;
import com.mentor.impl.FL2D_Permit_validity_ExtImpl;

public class FL2D_Permit_validity_ExtAction {

	FL2D_Permit_validity_ExtImpl impl = new FL2D_Permit_validity_ExtImpl();
	FL2D_Permit_validity_ExtDataTable dt;
	private FL2D_Permit_validity_ExtDataTable fl2dImportDataTable;
	 private boolean data_list_reload_Flag=true;
	public boolean isData_list_reload_Flag() {
		return data_list_reload_Flag;
	}

	public void setData_list_reload_Flag(boolean data_list_reload_Flag) {
		this.data_list_reload_Flag = data_list_reload_Flag;
	}

	public FL2D_Permit_validity_ExtDataTable getFl2dImportDataTable() {
		return fl2dImportDataTable;
	}

	public void setFl2dImportDataTable(
			FL2D_Permit_validity_ExtDataTable fl2dImportDataTable) {
		this.fl2dImportDataTable = fl2dImportDataTable;
	}
	private ArrayList displayDataListExt = new ArrayList();

	public ArrayList getDisplayDataListExt() {

		try {
if(this.isData_list_reload_Flag())
{
			this.displayDataListExt = impl.getDisplayListExt(this);

this.data_list_reload_Flag=false;
}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return displayDataListExt;
	}

	public void setDisplayDataListExt(ArrayList displayDataListExt) {
		this.displayDataListExt = displayDataListExt;
	}
		
/*public void updateValidDate(){ 
		
		
		try {
		
		
			
			impl.update(this,dt);
			
		

	} catch (Exception e) {
		e.printStackTrace();
	}

	}*/


public void updateValidDate(ActionEvent e) {
	UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
			.getParent();
	FL2D_Permit_validity_ExtDataTable dt = (FL2D_Permit_validity_ExtDataTable) this.displayDataListExt
			.get(uiTable.getRowIndex());
	if(dt.getNewvalidityDate().after(dt.getOldvalidityDate()))
	{
	impl.update(this,dt);
	this.displayDataListExt = impl.getDisplayListExt(this);
	}else{
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Enter Correct Date", "Please Enter Correct Date"));	
	}
	

	

}
public void rePrint()
{
	 
	 try{
		 new FL2D_Permit_validity_ExtImpl().printReportFL2D(this.fl2dImportDataTable);
	 }catch(Exception e)
	 {
		 e.printStackTrace();
	 }
	 
}
		

}
