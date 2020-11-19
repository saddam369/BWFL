package com.mentor.action;



	import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.richfaces.component.UIDataTable;

import com.mentor.datatable.CSD_Permit_validity_ExtDT;

import com.mentor.impl.CSD_Permit_validity_ExtImpl;


	public class CSD_Permit_validity_ExtAction {

		CSD_Permit_validity_ExtImpl impl = new CSD_Permit_validity_ExtImpl();
		CSD_Permit_validity_ExtDT dt;
		private CSD_Permit_validity_ExtDT csdImportDT;
		 private boolean data_list_reload_Flag=true;
		public boolean isData_list_reload_Flag() {
			return data_list_reload_Flag;
		}

		public void setData_list_reload_Flag(boolean data_list_reload_Flag) {
			this.data_list_reload_Flag = data_list_reload_Flag;
		}

		public CSD_Permit_validity_ExtDT getcsdImportDT() {
			return csdImportDT;
		}

		public void setcsdImportDT(
				CSD_Permit_validity_ExtDT csdImportDT) {
			this.csdImportDT = csdImportDT;
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
		CSD_Permit_validity_ExtDT dt = (CSD_Permit_validity_ExtDT) this.displayDataListExt
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
			 new CSD_Permit_validity_ExtImpl().printReportFL2D(this.csdImportDT);
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 
	}
			

	}


