package com.mentor.action;




	import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletContext;

import org.richfaces.component.UIDataTable;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.mentor.datatable.BWFLGatepass36Accidentaldt;

import com.mentor.impl.BWFLGatepass36AccidentalImpl;

import com.mentor.utility.Validate;

	public class BWFLGatepass36AccidentalAction {

		BWFLGatepass36AccidentalImpl impl = new BWFLGatepass36AccidentalImpl();

		private String bwflName;
		private String bwflAdrs;
		private String hidden;
		private int bwflId;
		public Date dt_date = new Date();
		public Date validtilldt_date;
		public String vch_to;
		private int districtLic;
		public String vch_to_lic_no;
		private String licenseeName;
		private String licenseeAdrs;
		private int licenseeId;
		private String vehicleDrvrName;
		private String vehicleAgencyNmAdrs;
		private int district1;
		private int district2;
		private int district3;
		private String routeDtl;
		private String vehicleNo;
		private double grossWeight = 0;
		private double tierWeight = 0;
		private double netWeight = 0;
		private ArrayList districtList = new ArrayList();
		private ArrayList displaylist = new ArrayList();
		private String officrEmail;
		public String vch_from;
		private int bwflLicenseTypeId;
		private int distillery_id;
		private String vch_gatepass_no;
	

		private boolean licNoflg;
		private boolean licNoflg1;
		private boolean licNoflg2;
		private ArrayList licNmbrList = new ArrayList();
		private String brc_to_lic;
		private boolean drpdwnFlg;
		private boolean drpdwnFlg1;
		private ArrayList brclicNmbrList = new ArrayList();

		
		
		
		

		public int getDistillery_id() {
			return distillery_id;
		}

		public void setDistillery_id(int distillery_id) {
			this.distillery_id = distillery_id;
		}

		

		public String getVch_gatepass_no() {
			return vch_gatepass_no;
		}

		public void setVch_gatepass_no(String vch_gatepass_no) {
			this.vch_gatepass_no = vch_gatepass_no;
		}



		private String licenseeDistrict;

		public String getLicenseeDistrict() {
			return licenseeDistrict;
		}

		public void setLicenseeDistrict(String licenseeDistrict) {
			this.licenseeDistrict = licenseeDistrict;
		}

		public ArrayList getBrclicNmbrList() {
			return brclicNmbrList;
		}

		public void setBrclicNmbrList(ArrayList brclicNmbrList) {
			this.brclicNmbrList = brclicNmbrList;
		}

		public int getLicenseeId() {
			return licenseeId;
		}

		public void setLicenseeId(int licenseeId) {
			this.licenseeId = licenseeId;
		}

		public boolean isDrpdwnFlg() {
			return drpdwnFlg;
		}

		public void setDrpdwnFlg(boolean drpdwnFlg) {
			this.drpdwnFlg = drpdwnFlg;
		}

		public boolean isDrpdwnFlg1() {
			return drpdwnFlg1;
		}

		public void setDrpdwnFlg1(boolean drpdwnFlg1) {
			this.drpdwnFlg1 = drpdwnFlg1;
		}

		public String getBrc_to_lic() {
			return brc_to_lic;
		}

		public void setBrc_to_lic(String brc_to_lic) {
			this.brc_to_lic = brc_to_lic;
		}

		public boolean isLicNoflg() {
			return licNoflg;
		}

		public void setLicNoflg(boolean licNoflg) {
			this.licNoflg = licNoflg;
		}

		public boolean isLicNoflg1() {
			return licNoflg1;
		}

		public void setLicNoflg1(boolean licNoflg1) {
			this.licNoflg1 = licNoflg1;
		}

		public boolean isLicNoflg2() {
			return licNoflg2;
		}

		public void setLicNoflg2(boolean licNoflg2) {
			this.licNoflg2 = licNoflg2;
		}

		public ArrayList getLicNmbrList() {
			return licNmbrList;
		}

		public void setLicNmbrList(ArrayList licNmbrList) {
			this.licNmbrList = licNmbrList;
		}

		public int getBwflLicenseTypeId() {
			return bwflLicenseTypeId;
		}

		public void setBwflLicenseTypeId(int bwflLicenseTypeId) {
			this.bwflLicenseTypeId = bwflLicenseTypeId;
		}

		public String getVch_from() {
			return vch_from;
		}

		public void setVch_from(String vch_from) {
			this.vch_from = vch_from;
		}

		public String getOfficrEmail() {
			return officrEmail;
		}

		public void setOfficrEmail(String officrEmail) {
			this.officrEmail = officrEmail;
		}

		public double getGrossWeight() {
			return grossWeight;
		}

		public void setGrossWeight(double grossWeight) {
			this.grossWeight = grossWeight;
		}

		public double getTierWeight() {
			return tierWeight;
		}

		public void setTierWeight(double tierWeight) {
			this.tierWeight = tierWeight;
		}

		public double getNetWeight() {
			if (this.grossWeight > 0.0 && this.tierWeight > 0.0) {
				this.netWeight = this.grossWeight - this.tierWeight;
			} else {
				this.netWeight = 0.0;
			}
			return netWeight;
		}

		public void setNetWeight(double netWeight) {
			this.netWeight = netWeight;
		}

		public String getBwflName() {
			return bwflName;
		}

		public void setBwflName(String bwflName) {
			this.bwflName = bwflName;
		}

		public String getBwflAdrs() {
			return bwflAdrs;
		}

		public void setBwflAdrs(String bwflAdrs) {
			this.bwflAdrs = bwflAdrs;
		}

		public String getHidden() {
			try {
				impl.getDetails(this);
				impl.getEmailDetails(this);
				if (this.brc_to_lic != null) 
				{
					impl.getlicenseeDetail(this, this.brc_to_lic);
				}
				if (this.vch_to_lic_no != null) {
					impl.getwareLicenseeDetail(this, this.vch_to_lic_no);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return hidden;
		}

		public void setHidden(String hidden) {
			this.hidden = hidden;
		}

		public int getBwflId() {
			return bwflId;
		}

		public void setBwflId(int bwflId) {
			this.bwflId = bwflId;
		}

		public Date getDt_date() {
			return dt_date;
		}

		public void setDt_date(Date dt_date) {
			this.dt_date = dt_date;
		}

		public Date getValidtilldt_date() {
			return validtilldt_date;
		}

		public void setValidtilldt_date(Date validtilldt_date) {
			this.validtilldt_date = validtilldt_date;
		}

		public String getVch_to() {
			return vch_to;
		}

		public void setVch_to(String vch_to) {
			this.vch_to = vch_to;
		}

		public int getDistrictLic() {
			return districtLic;
		}

		public void setDistrictLic(int districtLic) {
			this.districtLic = districtLic;
		}

		public String getVch_to_lic_no() {
			return vch_to_lic_no;
		}

		public void setVch_to_lic_no(String vch_to_lic_no) {
			this.vch_to_lic_no = vch_to_lic_no;
		}

		public String getLicenseeName() {
			return licenseeName;
		}

		public void setLicenseeName(String licenseeName) {
			this.licenseeName = licenseeName;
		}

		public String getLicenseeAdrs() {
			return licenseeAdrs;
		}

		public void setLicenseeAdrs(String licenseeAdrs) {
			this.licenseeAdrs = licenseeAdrs;
		}

		public String getVehicleDrvrName() {
			return vehicleDrvrName;
		}

		public void setVehicleDrvrName(String vehicleDrvrName) {
			this.vehicleDrvrName = vehicleDrvrName;
		}

		public String getVehicleAgencyNmAdrs() {
			return vehicleAgencyNmAdrs;
		}

		public void setVehicleAgencyNmAdrs(String vehicleAgencyNmAdrs) {
			this.vehicleAgencyNmAdrs = vehicleAgencyNmAdrs;
		}

		public int getDistrict1() {
			return district1;
		}

		public void setDistrict1(int district1) {
			this.district1 = district1;
		}

		public int getDistrict2() {
			return district2;
		}

		public void setDistrict2(int district2) {
			this.district2 = district2;
		}

		public int getDistrict3() {
			return district3;
		}

		public void setDistrict3(int district3) {
			this.district3 = district3;
		}

		public String getRouteDtl() {
			return routeDtl;
		}

		public void setRouteDtl(String routeDtl) {
			this.routeDtl = routeDtl;
		}

		public String getVehicleNo() {
			return vehicleNo;
		}

		public void setVehicleNo(String vehicleNo) {
			this.vehicleNo = vehicleNo;
		}

		public ArrayList getDistrictList() {
			try {
				this.districtList = impl.getDistrictList(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return districtList;
		}

		public void setDistrictList(ArrayList districtList) {
			this.districtList = districtList;
		}

		public ArrayList getDisplaylist() {
			return displaylist;
		}

		public void setDisplaylist(ArrayList displaylist) {
			this.displaylist = displaylist;
		}

		private boolean validateInput1;

		public boolean isValidateInput1() 
		{

			validateInput1 = true;
			
			if (!(Validate.validateDate("validtill", this.getValidtilldt_date())))
				validateInput1 = false;
			
			else if (!(Validate.validateStrReq("radioto", this.getVch_to())))
				validateInput1 = false;
		

			
			
			else if (isDrpdwnFlg()) 
			{
				if (!(Validate.validateStrReq("licenseno", this.getVch_to_lic_no())))
					validateInput1 = false;
				else if (!(Validate.validateStrReq("licenseenm",
						this.getLicenseeName())))
					validateInput1 = false;
				else if (!(Validate.validateStrReq("licenseeaddress",
						this.getLicenseeAdrs())))
					validateInput1 = false;
				else if (!(Validate.validateStrReq("routedtl", this.getRouteDtl())))
					validateInput1 = false;
				else if (!(Validate.validateStrReq("vehiclenmbr",
						this.getVehicleNo())))
					validateInput1 = false;
				else if (!(Validate.validateStrReq("vehicledriver",
						this.getVehicleDrvrName())))
					validateInput1 = false;
				else if (!(Validate.validateStrReq("agencyNameAddress",
						this.getVehicleAgencyNmAdrs())))
					validateInput1 = false;
				else if (!(Validate.validateDouble("grossWeight",
						this.getGrossWeight())))
					validateInput1 = false;
				else if (!(Validate.validateDouble("tierweight",
						this.getTierWeight())))
					validateInput1 = false;
				else if (!(this.grossWeight > this.getTierWeight())) {
					validateInput1 = false;
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"GROSS_LESS_THAN_TIER!!! ",
									"GROSS_LESS_THAN_TIER !!!"));
				}
			}

			else if (isDrpdwnFlg1()) {
				if (!(Validate.validateStrReq("licenseno", this.getBrc_to_lic())))
					validateInput1 = false;
				else if (!(Validate.validateStrReq("licenseenm",
						this.getLicenseeName())))
					validateInput1 = false;
				else if (!(Validate.validateStrReq("licenseeaddress",
						this.getLicenseeAdrs())))
					validateInput1 = false;
				else if (!(Validate.validateStrReq("routedtl", this.getRouteDtl())))
					validateInput1 = false;
				else if (!(Validate.validateStrReq("vehiclenmbr",
						this.getVehicleNo())))
					validateInput1 = false;
			
				else if (!(Validate.validateStrReq("vehicledriver",
						this.getVehicleDrvrName())))
					validateInput1 = false;
				
				else if (!(Validate.validateStrReq("agencyNameAddress",
						this.getVehicleAgencyNmAdrs())))
					validateInput1 = false;
				
				else if (!(Validate.validateDouble("grossWeight",
						this.getGrossWeight())))
					validateInput1 = false;
				
				else if (!(Validate.validateDouble("tierweight",
						this.getTierWeight())))
					validateInput1 = false;
				
			/*	else if (!(Validate.validateStrReq("challanno",
						this.getChallanNo())))
					validateInput1 = false;
				
				else if (!(Validate.validateDouble("challanamount",
						this.getChallanAmount())))
					validateInput1 = false;
				
				if (!(Validate.validateDate("challandate", this.getChallanDate())))
					validateInput1 = false;*/
				
				else if (!(this.grossWeight > this.getTierWeight())) 
				{
					validateInput1 = false;
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									" GROSS_GREATER_THAN_TIER!!! ",
									" GROSS_GREATER_THAN_TIER !!!"));
				}
			}
			
			
		/*for (int ii = 0; ii < this.getDisplaylist1().size(); ii++) 
		{
			
		GatepassToDistrictBWFLDT dt1 = (GatepassToDistrictBWFLDT) this.getDisplaylist1().get(ii);
			
		if(dt1.isSelected())
		{
		
			
			for (int i = 0; i < this.getDisplaylist().size(); i++) 
			{
				GatepassToDistrictBWFLDT dt = (GatepassToDistrictBWFLDT) this.getDisplaylist().get(i);
				
				if(dt.getIndentNumber().trim()!=null)
				{
						if(dt1.getIndentNo().trim().equalsIgnoreCase(dt.getIndentNumber().trim())==false)	
						{
							
							System.out.println(dt.getIndentNumber());
							
							
							validateInput1 = false;
							FacesContext.getCurrentInstance().addMessage(
									null,
									new FacesMessage(FacesMessage.SEVERITY_INFO,
											"Indent No Not Matched !!!",
											"Indent No Not Matched!!!"));
							
						}
			
						
						else if(dt.getInt_brand_id()>0)
						{		
							if(dt.getInt_brand_id()!= dt1.getBrandIdIndent())
							{
							System.out.println(dt.getInt_brand_id()+"===b=="+dt1.getBrandIdIndent());
							
							
							validateInput1 = false;
							FacesContext.getCurrentInstance().addMessage(
									null,
									new FacesMessage(FacesMessage.SEVERITY_INFO,
											"Brand  Not Matched !!!",
											"Brand  Not Matched!!!"));
							
							}
						
						}
						else if(dt.getInt_pckg_id()>0)
						{
						 if(dt.getInt_pckg_id() != dt1.getPackageIdIndent())
						{
							
							System.out.println(dt.getInt_pckg_id() +"==p==="+dt1.getPackageIdIndent());
						validateInput1 = false;
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_INFO,
										"Package  Not Matched !!!",
										"Package  Not Matched !!!"));
						
							
						}
						}	
						else if(dt.getDispatchbox()>0)
						{
						 if(dt.getDispatchbox()>dt1.getDispatchboxIndent())
						{
						validateInput1 = false;
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_INFO,
										"Dispatch Box Should Not Greater Than No Of Cases !!!",
										"Dispatch Box Should Not Greater Than No Of Cases !!!"));
				
							
						}
						
						}	
				
				}//indent number
						
			}
		}
			
			
			}	*/
			
			if(this.getVch_to().equalsIgnoreCase("DW")){
				for (int i = 0; i < this.getDisplaylist().size(); i++) 
				{
					BWFLGatepass36Accidentaldt dt = (BWFLGatepass36Accidentaldt) this.getDisplaylist().get(i);
					
					
					if(dt.getIndentNumber().trim().length()==0)
					{
					}
					else
					{
						if(impl.getIndentData(dt.getIndentNumber().trim(),
								dt.getInt_brand_id(),dt.getInt_pckg_id(),dt.getDispatchbox())==false)
						{
							validateInput1 = false;
							FacesContext.getCurrentInstance().addMessage(
									null,
									new FacesMessage(FacesMessage.SEVERITY_INFO,
											"Indent Number "+dt.getIndentNumber().trim()+" Does Not Exist or cases for the Indent are exceeding !!",
											"Indent Number "+dt.getIndentNumber().trim()+" Does Not Exist or cases for the Indent are exceeding !!"));
						}
					}	
				}
			}
			
			
			
			
			
			
			
			
			
			
			for (int i = 0; i < this.displaylist.size(); i++) {
				BWFLGatepass36Accidentaldt dt = (BWFLGatepass36Accidentaldt) displaylist
						.get(i);
				if (dt.getDispatchbox() > 0 && dt.getDispatchbottls() > 0) 
				{
					if (dt.getBatchNo() == null || dt.getBatchNo().equals("")) {
						validateInput1 = false;
						FacesContext.getCurrentInstance()
								.addMessage(
										null,
										new FacesMessage(
												FacesMessage.SEVERITY_ERROR,
												"Enter Batch No At Line No !!! "
														+ (i + 1),
												"Enter Batch No At Line No !!! "
														+ (i + 1)));
						break;
					}
					
					
					
					if(this.vch_to.equalsIgnoreCase("DW")){
						if(dt.getIndentNumber() == null || dt.getIndentNumber().equals(""))
						{

							validateInput1 = false;
							FacesContext.getCurrentInstance().addMessage(
									null,
									new FacesMessage(FacesMessage.SEVERITY_INFO,
											"Enter Indent Number!!!",
											"Enter Indent Number !!!"));
						}
					}
					
					
					
					
					
					
				}
			}
			int sumBottles = 0;
			int sumBoxes = 0;
			for (int i = 0; i < this.displaylist.size(); i++) {
				BWFLGatepass36Accidentaldt dt = (BWFLGatepass36Accidentaldt) displaylist
						.get(i);

				sumBottles += dt.getDispatchbottls();
				sumBoxes += dt.getDispatchbox();

				/*if (dt.getBalance() <= 0) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Invalid Balance !!! ", "Invalid Balance !!!"));
					validateInput1 = false;

				} else if (dt.getDispatchbox() > dt.getBoxAvailable()) {
					FacesContext
							.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_ERROR,
											"Dispatch Boxes Should Be Less Than Available Boxes !!! ",
											"Dispatch Boxes Should Be Less Than Available Boxes !!!"));
					validateInput1 = false;

				}*/
			}
			if (sumBoxes == 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Dispatch Boxes Should Be Greater Than Zero !!! ",
								"Dispatch Boxes Should Be Greater Than Zero !!!"));
				validateInput1 = false;
			} else if (sumBottles == 0) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										"Dispatch Bottles Should Be Greater Than Zero !!! ",
										"Dispatch Bottles Should Be Greater Than Zero !!!"));
				validateInput1 = false;
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			

			return validateInput1;

		}

		public void setValidateInput1(boolean validateInput1) {
			this.validateInput1 = validateInput1;
		}

		public String listMethod(ValueChangeEvent vce) 
		{
			try
			{
			this.flagLic=false;
			this.displaylist = impl.displaylistImpl(this);

			this.brc_to_lic = null;
			this.brclicNmbrList.clear();
			this.vch_to_lic_no = null;
			this.licNmbrList.clear();

			this.licenseeName = null;
			this.licenseeAdrs = null;
			this.licenseeId = 0;
			this.licenseeDistrict = null;

			String val = (String) vce.getNewValue();
			if (val.equalsIgnoreCase("DW")) {

				this.licNmbrList = impl.getlicenseNmbr(this);
				this.setDrpdwnFlg(true);
				this.setDrpdwnFlg1(false);
				if (this.getBwflLicenseTypeId() == 1
						|| this.getBwflLicenseTypeId() == 3) {

					this.setLicNoflg(true);
					this.setLicNoflg1(false);
					this.setLicNoflg2(false);

				} else if (this.getBwflLicenseTypeId() == 2
						|| this.getBwflLicenseTypeId() == 4) {

					this.setLicNoflg(false);
					this.setLicNoflg1(true);
					this.setLicNoflg2(false);

				}
			
				

			} else if (val.equalsIgnoreCase("BRC")) {

				this.brclicNmbrList = impl.getbrcLicenseNo(this);
				this.setLicNoflg(false);
				this.setLicNoflg1(false);
				this.setLicNoflg2(false);
				this.setDrpdwnFlg(false);
				this.setDrpdwnFlg1(true);
			}
			this.licenseeName = null;
			this.licenseeAdrs = null;
			this.licenseeId = 0;
			}catch(Exception e)
			{
				
			}
			return "";
		}

		public String drpdownMethod(ValueChangeEvent vce) {

			String val = (String) vce.getNewValue();
			impl.getlicenseeDetail(this, val);

			return "";
		}

		

		 /* challanamount =Enter Challan Amount
				  challanno=Enter Challan Number
				  challandate=Enter Challan Date*/
		
		
		
		
		public void saveMethod() {
			try {

				if (isValidateInput1()) 
				{

					if(this.getVch_to().equalsIgnoreCase("BRC"))
					{
						if(this.getChallanNo()==null || this.getChallanNo().trim().length()==0 )
						{
							FacesContext.getCurrentInstance().addMessage(null,
									new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Kindly Enter Challan Number","Kindly Enter Challan Number"));
						
							
						}else
						{
						
						
						
							if(this.getChallanDate()!=null)
							{
								if(this.getChallanAmount()>0)
								{
									impl.saveMethodImpl(this);
								}else
								{
									FacesContext.getCurrentInstance().addMessage(null,
											new FacesMessage(FacesMessage.SEVERITY_ERROR,
											"Kindly Enter Challan Amount","Kindly Enter Challan Amount"));
								}
							}else
							{
								FacesContext.getCurrentInstance().addMessage(null,
										new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"Kindly Select Challan Date","Kindly Select Challan Date"));
							}
						}
						
					}
					else
					{
					
					impl.saveMethodImpl(this);
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		private ArrayList table2List = new ArrayList();
		private Date printDate;
		private String printGatePassNo;
		private String pdfname;
		private boolean listFlagForPrint = true;

		public Date getPrintDate() {
			return printDate;
		}

		public void setPrintDate(Date printDate) {
			this.printDate = printDate;
		}

		public String getPrintGatePassNo() {
			return printGatePassNo;
		}

		public void setPrintGatePassNo(String printGatePassNo) {
			this.printGatePassNo = printGatePassNo;
		}

		public String getPdfname() {
			return pdfname;
		}

		public void setPdfname(String pdfname) {
			this.pdfname = pdfname;
		}

		public boolean isListFlagForPrint() {
			return listFlagForPrint;
		}

		public void setListFlagForPrint(boolean listFlagForPrint) {
			this.listFlagForPrint = listFlagForPrint;
		}

		public ArrayList getTable2List() {
			if (this.listFlagForPrint == true) {
				this.table2List = impl.getTable2List(this);
				this.listFlagForPrint = false;
			}
			return table2List;
		}

		public void setTable2List(ArrayList table2List) {
			this.table2List = table2List;
		}

		public void printReport(ActionEvent e) {

			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
			BWFLGatepass36Accidentaldt dt = (BWFLGatepass36Accidentaldt) this.table2List.get(uiTable.getRowIndex());

			this.setPrintDate(dt.getCurrentDate());
			this.setPrintGatePassNo(dt.getGatepassNo());
			if (impl.printReport(this) == true) {

				dt.setPrintFlag(true);

			} else {
				dt.setPrintFlag(false);

			}

		}

		public double db_total_value = 0;
		public double sum = 0;
		public boolean flag = true;
		public double db_total_add_value = 0;
		public double sum_add = 0;
		public boolean addflag = true;

		public double getDb_total_value() {
			double duty = 0.0;
			try {

				for (int i = 0; i < this.displaylist.size(); i++) {
					BWFLGatepass36Accidentaldt dt = (BWFLGatepass36Accidentaldt) this
							.getDisplaylist().get(i);
					duty += dt.getCalculated_duty();

					db_total_value = Math.round(duty);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return db_total_value;
		}

		public void setDb_total_value(double db_total_value) {
			this.db_total_value = db_total_value;
		}

		public double getSum() {
			return sum;
		}

		public void setSum(double sum) {
			this.sum = sum;
		}

		public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

		public double getDb_total_add_value() {
			double addduty = 0.0;
			try {

				for (int i = 0; i < this.displaylist.size(); i++) {
					BWFLGatepass36Accidentaldt dt = (BWFLGatepass36Accidentaldt) this
							.getDisplaylist().get(i);
					addduty += dt.getCalculated_add_duty();
					// System.out.println("duty----------------"+addduty);
				}
				db_total_add_value = Math.round(addduty);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return db_total_add_value;
		}

		public void setDb_total_add_value(double db_total_add_value) {
			this.db_total_add_value = db_total_add_value;
		}

		public double getSum_add() {
			return sum_add;
		}

		public void setSum_add(double sum_add) {
			this.sum_add = sum_add;
		}

		public boolean isAddflag() {
			return addflag;
		}

		public void setAddflag(boolean addflag) {
			this.addflag = addflag;
		}

		public void calculateTotalDuty(ActionEvent ae) {

			if (isFlag()) {
				this.setSum(0);
				for (int i = 0; i < this.displaylist.size(); i++) {
					BWFLGatepass36Accidentaldt dt = (BWFLGatepass36Accidentaldt) this.displaylist
							.get(i);

					this.setSum(this.getSum() + dt.getCalculated_duty());

				}
			}
			this.flag = false;

		}

		public void calculateTotalAddDuty(ActionEvent ae) {

			if (isAddflag()) {
				this.setSum_add(0);
				for (int i = 0; i < this.displaylist.size(); i++) {
					BWFLGatepass36Accidentaldt dt = (BWFLGatepass36Accidentaldt) this.displaylist
							.get(i);

					this.setSum_add(this.getSum_add() + dt.getCalculated_add_duty());

				}
			}
			this.addflag = false;

		}

		// ======================for uploader======================================

		private String excelFilename;
		private String excelFilepath;
		private String scanGatePassNo;
		private int excelCases;
		private boolean gatePassFlag;
		private boolean tableFlag;
		private String scanVchFrom;

		ArrayList getVal = new ArrayList();

		private String draftpdfname;

		public String getDraftpdfname() {
			return draftpdfname;
		}

		public void setDraftpdfname(String draftpdfname) {
			this.draftpdfname = draftpdfname;
		}

		public String getScanVchFrom() {
			return scanVchFrom;
		}

		public void setScanVchFrom(String scanVchFrom) {
			this.scanVchFrom = scanVchFrom;
		}

		public boolean isGatePassFlag() {
			return gatePassFlag;
		}

		public void setGatePassFlag(boolean gatePassFlag) {
			this.gatePassFlag = gatePassFlag;
		}

		public boolean isTableFlag() {
			return tableFlag;
		}

		public void setTableFlag(boolean tableFlag) {
			this.tableFlag = tableFlag;
		}

		public String getExcelFilename() {
			return excelFilename;
		}

		public void setExcelFilename(String excelFilename) {
			this.excelFilename = excelFilename;
		}

		public String getExcelFilepath() {
			return excelFilepath;
		}

		public void setExcelFilepath(String excelFilepath) {
			this.excelFilepath = excelFilepath;
		}

		public String getScanGatePassNo() {
			return scanGatePassNo;
		}

		public void setScanGatePassNo(String scanGatePassNo) {
			this.scanGatePassNo = scanGatePassNo;
		}

		public int getExcelCases() {
			return excelCases;
		}

		public void setExcelCases(int excelCases) {
			this.excelCases = excelCases;
		}

		public ArrayList getGetVal() {
			//this.getVal = impl.getExcelData(this);
			return getVal;
		}

		public void setGetVal(ArrayList getVal) {
			this.getVal = getVal;
		}

		public void scanAndUpload(ActionEvent ae) {
			UIDataTable uiTable = (UIDataTable) ae.getComponent().getParent()
					.getParent();
			BWFLGatepass36Accidentaldt dt = (BWFLGatepass36Accidentaldt) this.table2List
					.get(uiTable.getRowIndex());

			this.setScanGatePassNo(dt.getGatepassNo());
			this.gatePassFlag = true;
			this.tableFlag = true;
			this.getVal = impl.getExcelData(this);
		}

		public void uploadExcel(UploadEvent event) {

			try {
				int size = 0;
				int counter = 0;
				UploadItem item = event.getUploadItem();

				String FullfileName = item.getFileName();

				String path = item.getFile().getPath();

				String fileName = FullfileName.substring(FullfileName
						.lastIndexOf("\\") + 1);

				ExternalContext con = FacesContext.getCurrentInstance()
						.getExternalContext();

				ServletContext sCon = (ServletContext) con.getContext();

				System.out.println("filename" + FullfileName
						+ "---------------filename" + fileName);
				size = item.getFileSize();
				this.excelFilename = FullfileName;
				this.excelFilepath = path;

			} catch (Exception ee) {
				ee.printStackTrace();
				System.out.println("exception in upload@");
			} finally {

			}

		}

		public String importExcel() {
			impl.saveExcelData(this);
			this.gatePassFlag = true;

			return "";
		}

		public void finalSubmit() {
			if(bottlingNotDoneFlag==false){
			//System.out.println(impl.excelCases(this)+"="+impl.recieveCases(this));
			if (impl.excelCases(this) == impl.recieveCases(this)) {
				if (impl.updateDispatch(this) == true) {

					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Data Save Successfully","Data Save Successfully"));
					this.gatePassFlag = false;
					this.tableFlag = false;
				} else {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error Occured while saving !!! ",
									"Error Occured while saving !!!"));
				}
				
			} else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(
					"No.of cases in Gatepass and in Uploaded Excel does not match! ","No.of cases in Gatepass and in Uploaded Excel does not match!"));
			}
		}else {
			FacesContext
			.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage(
							" Invalid Casecode Found !!! ",
							"Invalid Casecode Found !!!"));
	}
		}

		// ----------------------- print drft --------------

		public void printDraftreport(ActionEvent e) {

			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
					.getParent();
			BWFLGatepass36Accidentaldt dt = (BWFLGatepass36Accidentaldt) this.table2List
					.get(uiTable.getRowIndex());

			this.setPrintDate(dt.getCurrentDate());
			this.setPrintGatePassNo(dt.getGatepassNo());
			if (impl.printDraftReport(this) == true) {

				dt.setDraftprintFlag(true);

			} else {
				dt.setDraftprintFlag(false);

			}

		}

		public void delete() {
			impl.deleteData(this);

		}

		public void reset() {
			this.listFlagForPrint = true;
		}

		// -----------------code for cancel gatepass----------------

		private BWFLGatepass36Accidentaldt edidata;

		public BWFLGatepass36Accidentaldt getEdidata() {
			return edidata;
		}

		public void setEdidata(BWFLGatepass36Accidentaldt edidata) {
			this.edidata = edidata;
		}

		public void cancel() {

			// System.out.println("gatePass no -- " + edidata.getGatepassNo());
			impl.cancelGatepassImpl(this, edidata);
		}

		
		
		// ==========================code for csv===================================

		private String csvFilename;
		private String csvFilepath;
		private String exclcsv="C";

		public String getCsvFilename() {
			return csvFilename;
		}

		public void setCsvFilename(String csvFilename) {
			this.csvFilename = csvFilename;
		}

		public String getCsvFilepath() {
			return csvFilepath;
		}

		public void setCsvFilepath(String csvFilepath) {
			this.csvFilepath = csvFilepath;
		}

		public String getExclcsv() {
			return exclcsv;
		}

		public void setExclcsv(String exclcsv) {
			this.exclcsv = exclcsv;
		}

		public void uploadCsv(UploadEvent event) {

			try {
				int size = 0;
				int counter = 0;
				UploadItem item = event.getUploadItem();

				String FullfileName = item.getFileName();

				String path = item.getFile().getPath();

				String fileName = FullfileName.substring(FullfileName
						.lastIndexOf("\\") + 1);

				ExternalContext con = FacesContext.getCurrentInstance()
						.getExternalContext();

				ServletContext sCon = (ServletContext) con.getContext();

				System.out.println("filename" + FullfileName
						+ "---------------filename" + fileName);
				size = item.getFileSize();
				this.setCsvFilename(FullfileName);
				this.setCsvFilepath(path);

			} catch (Exception ee) {
				ee.printStackTrace();
				System.out.println("exception in upload@");
			} finally {

			}

		}

		public String csvSubmit() throws IOException 
		{
			try
			{
			impl.saveCSV(this);
			 this.bottlingNotDoneFlag=false;
			 this.gatePassFlag = true;
			 this.bottlingNotDoneFlag=false;
				//this.tableFlag = true;
				//this.submitFlag = false;
				//this.cancelFlag = true; 
				//this.kindlyUploadFlag = false;
				//this.uploaderFlag = false;
				//this.finalsubmit = true;
				//this.uploaderFlag = false;
			
			this.gatePassFlag = true;
			this.getVal = impl.getExcelData(this);
			}catch(Exception e)
			{
				
			}
			return "";
		}
		
		private String challanNo=null;
		private Date challanDate;
		private double challanAmount;

		public String getChallanNo() {
			return challanNo;
		}

		public void setChallanNo(String challanNo) {
			this.challanNo = challanNo;
		}

		public Date getChallanDate() {
			return challanDate;
		}

		public void setChallanDate(Date challanDate) {
			this.challanDate = challanDate;
		}

		public double getChallanAmount() {
			return challanAmount;
		}

		public void setChallanAmount(double challanAmount) {
			this.challanAmount = challanAmount;
		}
		private boolean bottlingNotDoneFlag=false;

		public boolean isBottlingNotDoneFlag() {
			return bottlingNotDoneFlag;
		}

		public void setBottlingNotDoneFlag(boolean bottlingNotDoneFlag) {
			this.bottlingNotDoneFlag = bottlingNotDoneFlag;
		}
		
		//------------------------------------3
		
		private ArrayList displaylist1 = new ArrayList();

		
		
		
	public ArrayList getDisplaylist1() {
			return displaylist1;
		}

		public void setDisplaylist1(ArrayList displaylist1) {
			this.displaylist1 = displaylist1;
		}
		private boolean flagLic;

		public boolean isFlagLic() {
			return flagLic;
		}

		public void setFlagLic(boolean flagLic) {
			this.flagLic = flagLic;
		}
	public void clearAll() {
		this.flagLic=false;
			this.displaylist1.clear();
			this.licenseeDistrict = null;
			this.bwflName = null;
			this.bwflAdrs = null;
			this.validtilldt_date = null;
			this.vch_to = "";
			this.vch_to_lic_no = "";
			this.displaylist.clear();
			this.displaylist1.clear();
			this.routeDtl = null;
			this.vehicleNo = null;
			this.vehicleDrvrName = null;
			this.vehicleAgencyNmAdrs = null;
			this.district1 = 0;
			this.district2 = 0;
			this.district3 = 0;
			this.districtLic = 0;
			this.tierWeight = 0.0;
			this.netWeight = 0.0;
			this.grossWeight = 0.0;
			this.licenseeName = null;
			this.licenseeAdrs = null;
			this.licenseeId = 0;
			this.brc_to_lic = null;
			this.licNmbrList.clear();
			this.brclicNmbrList.clear();
			this.table2List.clear();
			this.getstockflg=false;
			this.listFlagForPrint = true;
			this.gatePassFlag = false;
			this.tableFlag = false;
			this.gatePassFlag = false;
			this.tableFlag = false;
			this.getVal.clear();

		}


	/*
	 
														<rich:column>
															<f:facet name="header">
																<h:outputText 
																	styleClass="generalHeaderOutputTable" />
															</f:facet>
															<h:selectBooleanCheckbox value="#{list.selected}"
															 disabled="#{gatepassToDistrictBrewAction.getstockflg}">
	 															 </h:selectBooleanCheckbox>
														</rich:column>
	 */


	public String waredrpMethod(ValueChangeEvent vce) {

		
		
		String val = (String) vce.getNewValue();
		impl.getwareLicenseeDetail(this, val);
		
		
		
		
		this.displaylist1=impl.indentDetail(this,val);
		
		
		
		
		this.getstockflg=false;
		return "";
	}

	public void getData(ActionEvent e)
	{
		 
		this.displaylist = impl.displaylistImpl2(this);
		getstockflg=true;

	}
	private boolean getstockflg;



	public boolean isGetstockflg() {
		return getstockflg;
	}

	public void setGetstockflg(boolean getstockflg) {
		this.getstockflg = getstockflg;
	}













	public void fetch1()
	{
		
		 
	if(this.brc_to_lic.length()>0 && this.brc_to_lic != null && this.brc_to_lic !="")
	{
		if(this.brc_to_lic.charAt(0)==' ' || this.brc_to_lic.charAt(this.brc_to_lic.length()-1)==' ')
		{
			FacesContext
			.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage(
							"enter HBR ID without spaces",
							"enter  HBR ID without spaces"));
		}
			else
			{
				impl.checklic(this);
		
			this.setVch_to_lic_noNew(this.brc_to_lic);
			}
		
	}

	else
	{
		FacesContext
		.getCurrentInstance()
		.addMessage(
				null,
				new FacesMessage(
						"enter valid  HBR ID  ",
						"enter valid  HBR ID  "));
	}
	}






	public String vch_to_lic_noNew;






	public String getVch_to_lic_noNew() {
		return vch_to_lic_noNew;
	}

	public void setVch_to_lic_noNew(String vch_to_lic_noNew) {
		this.vch_to_lic_noNew = vch_to_lic_noNew;
	}


	public boolean ceshflag = true;
	public double ceshdb_total_value = 0;
	public double ceshsum = 0;


	public boolean isCeshflag() {
		return ceshflag;
	}

	public void setCeshflag(boolean ceshflag) {
		this.ceshflag = ceshflag;
	}

	public double getCeshdb_total_value() {
		return ceshdb_total_value;
	}

	public void setCeshdb_total_value(double ceshdb_total_value) {
		this.ceshdb_total_value = ceshdb_total_value;
	}

	public double getCeshsum() {
		double addduty = 0.0;
		try {

			for (int i = 0; i < this.displaylist.size(); i++) {
				BWFLGatepass36Accidentaldt dt = (BWFLGatepass36Accidentaldt) this.getDisplaylist().get(i);
				addduty += dt.getCesh_tot();
				// System.out.println("add duty----------------" + addduty);
			}
			ceshsum = addduty;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ceshsum;
	}

	public void setCeshsum(double ceshsum) {
		this.ceshsum = ceshsum;
	}

	public void calculateTotalCeshDuty(ActionEvent ae) {

		if (isCeshflag()) {
			this.setCeshsum(0);
			for (int i = 0; i < this.displaylist.size(); i++) {
				BWFLGatepass36Accidentaldt dt = (BWFLGatepass36Accidentaldt) this.displaylist.get(i);

				this.setCeshsum(this.getCeshsum() + dt.getCesh_tot());

			}
		}
		this.ceshflag = false;

	}

	}


