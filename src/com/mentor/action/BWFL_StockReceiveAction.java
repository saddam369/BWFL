package com.mentor.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.richfaces.component.UIDataTable;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.mentor.datatable.BWFL_StockReceiveDataTable;
import com.mentor.impl.BWFLImportImpl;
import com.mentor.impl.BWFL_StockReceiveImpl;
import com.mentor.utility.Constants;
import com.mentor.utility.Validate;

public class BWFL_StockReceiveAction {

	BWFL_StockReceiveImpl impl = new BWFL_StockReceiveImpl();

	private int distillery_id;
	private String distillery_nm;
	private String distillery_adrs;
	private String hidden;
	private Date dateOfBottling = new Date();

	private String liqureTypeId;
	private ArrayList liqureTypeList = new ArrayList();
	private String licenceType;

	private ArrayList brandPackagingDataList = new ArrayList();

	private ArrayList showDataTableList = new ArrayList();

	private boolean addRowFlagPackge = true;

	private String licenceNoId;
	private int etin_unit_id;
	
	private String popup="N";
public String getPopup() {
		return popup;
	}

	public void setPopup(String popup) {
		this.popup = popup;
	}

private BWFL_StockReceiveDataTable datatable;
	public BWFL_StockReceiveDataTable getDatatable() {
	return datatable;
}

public void setDatatable(BWFL_StockReceiveDataTable datatable) {
	this.datatable = datatable;
}

	public int getEtin_unit_id() {
		return etin_unit_id;
	}

	public void setEtin_unit_id(int etin_unit_id) {
		this.etin_unit_id = etin_unit_id;
	}

	private ArrayList licenceNoList = new ArrayList();

	private Date cr_date;

	private String checkLicenceType;
	private String permitNo;
	// @rvind
	private String unit_id;
	private ArrayList unitlist = new ArrayList();

	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	public String getPermitNo() {
		return permitNo;
	}

	public ArrayList getUnitlist() {

		unitlist = new BWFL_StockReceiveImpl().getUnit();
		return unitlist;
	}

	public void setUnitlist(ArrayList unitlist) {
		this.unitlist = unitlist;
	}

	public void setPermitNo(String permitNo) {
		this.permitNo = permitNo;
	}

	public String getCheckLicenceType() {
		return checkLicenceType;
	}

	public void setCheckLicenceType(String checkLicenceType) {
		this.checkLicenceType = checkLicenceType;
	}

	public Date getCr_date() {
		return cr_date;
	}

	public void setCr_date(Date cr_date) {
		this.cr_date = cr_date;
	}

	public String getLicenceNoId() {
		return licenceNoId;
	}

	public void setLicenceNoId(String licenceNoId) {
		this.licenceNoId = licenceNoId;
	}

	public ArrayList getLicenceNoList() {
		return licenceNoList;
	}

	public void setLicenceNoList(ArrayList licenceNoList) {
		this.licenceNoList = licenceNoList;
	}

	public String addRowMethodPackaging() {

		BWFL_StockReceiveDataTable dt = new BWFL_StockReceiveDataTable();
		dt.setSno(brandPackagingDataList.size() + 1);
		brandPackagingDataList.add(dt);

		return "";
	}

	public void deleteRowMethodPackaging(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		BWFL_StockReceiveDataTable dt = (BWFL_StockReceiveDataTable) this.brandPackagingDataList
				.get(uiTable.getRowIndex());
		this.brandPackagingDataList.remove(dt);
	}

	public boolean isAddRowFlagPackge() {
		return addRowFlagPackge;
	}

	public void setAddRowFlagPackge(boolean addRowFlagPackge) {
		this.addRowFlagPackge = addRowFlagPackge;
	}

	public Date getDateOfBottling() {
		return dateOfBottling;
	}

	public void setDateOfBottling(Date dateOfBottling) {
		this.dateOfBottling = dateOfBottling;
	}

	public String getLiqureTypeId() {
		return liqureTypeId;
	}

	public void setLiqureTypeId(String liqureTypeId) {
		this.liqureTypeId = liqureTypeId;
	}

	public ArrayList getLiqureTypeList() {
		this.liqureTypeList = impl.getLiqureType();
		return liqureTypeList;
	}

	public void setLiqureTypeList(ArrayList liqureTypeList) {
		this.liqureTypeList = liqureTypeList;
	}

	public String getLicenceType() {
		return licenceType;
	}

	public void setLicenceType(String licenceType) {
		this.licenceType = licenceType;
	}

	public ArrayList getBrandPackagingDataList() {

		// this.brandPackagingDataList=impl.getAddRowData(this);
		return brandPackagingDataList;
	}

	public void setBrandPackagingDataList(ArrayList brandPackagingDataList) {
		this.brandPackagingDataList = brandPackagingDataList;
	}

	public ArrayList getShowDataTableList() {
		this.showDataTableList = impl.getData(this);
		return showDataTableList;
	}

	public void setShowDataTableList(ArrayList showDataTableList) {
		this.showDataTableList = showDataTableList;
	}

	public int getDistillery_id() {
		return distillery_id;
	}

	public void setDistillery_id(int distillery_id) {
		this.distillery_id = distillery_id;
	}

	public String getDistillery_nm() {
		return distillery_nm;
	}

	public void setDistillery_nm(String distillery_nm) {
		this.distillery_nm = distillery_nm;
	}

	public String getDistillery_adrs() {
		return distillery_adrs;
	}

	public void setDistillery_adrs(String distillery_adrs) {
		this.distillery_adrs = distillery_adrs;
	}

	public String getHidden() {
		try {
			impl.getSugarmill(this);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;

	}

	public void licencelistener(ValueChangeEvent event) {
		try {
			Object obj = event.getNewValue();

			// int vat=Integer.parseInt(String.valueOf(obj));
			String id = String.valueOf(obj);
			// this.brandPackagingDataList=impl.getAddRowData(this, id);
			////System.out.println("id="+id);
			this.licenceNoList = impl.getLicenseNo(this, id);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void getaddrowdata(ValueChangeEvent event) {
		try {
			Object obj = event.getNewValue();
			String id = String.valueOf(obj);
			//impl.getBondName(this, id);
			//this.brandPackagingDataList = impl.getAddRowData(this,this.getLicenceType());
			// this.licenceNoList=impl.getLicenseNo(this,id);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// --------------------------------------

	public void save() throws ParseException {
		if (isValidateInput()) {

			Date dt = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(dt);
			c.add(Calendar.DATE, 1);
			dt = c.getTime();

			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			/*if (!updateFlg) {
				impl.save(this);
			} else {
				impl.update(this);
			}*/
			
			if (this.updateFlg) {
				////System.out.println("11");
				impl.update(this);
			} else {
				////System.out.println("22");
				if (impl.checkpermit(permitNo, this) == true) {
					////System.out.println("33");
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Permit Already Exists !!!","Permit Already Exists !!!"));
				}
				else{
					////System.out.println("44");
					impl.save(this);
				}
				
			}
		}

	}

	public void reset() 
	{
		this.dateOfBottling = new Date();
		this.liqureTypeId = "";
		this.liqureTypeList.clear();
		this.licenceType = "";
		this.licenceNoId = "";
		this.permitNo = "";
		this.licenceNoList.clear();
		this.brandPackagingDataList.clear();
		this.unit_id = null;
		this.unitlist.clear();
		this.updateFlg = false;
		this.db_totalFees = 0;
		this.other_unit_id=null;
		this.other_unit_list.clear();
	}

	private boolean validateInput;

	public boolean isValidateInput() {

		this.validateInput = true;

		if (!(Validate.validateDate("dateValue", this.getDateOfBottling())))
			validateInput = false;

		else if (!(Validate
				.validateStrReq("LiquorType", this.getLiqureTypeId())))
			validateInput = false;
		else if (!(Validate
				.validateStrReq("LicenseType", this.getLicenceType())))
			validateInput = false;

		else if (!(Validate.validateStrReq("licNo", this.getLicenceNoId())))
			validateInput = false;
		else if (!(Validate.validateStrReq("prmit", this.getPermitNo())))
			validateInput = false;
		else if (!(Validate.validateStrReq("unit_name", this.getUnit_id())))
			validateInput = false;

		if (validateInput) {
			if (this.brandPackagingDataList.size() > 0) {
				for (int i = 0; i < brandPackagingDataList.size(); i++) {
					BWFL_StockReceiveDataTable table = new BWFL_StockReceiveDataTable();
					table = (BWFL_StockReceiveDataTable) brandPackagingDataList
							.get(i);
					if (!(Validate.validateStrReqRow(i, "Brand",
							table.getBrandPackagingData_Brand())))
						validateInput = false;
					else if (!(Validate.validateStrReqRow(i, "Packaging",
							table.getBrandPackagingData_Packaging())))
						validateInput = false;
					else if (!(Validate.validateStrReqRow(i, "Quantity",
							table.getBrandPackagingData_Quantity())))
						validateInput = false;
					else if (!(Validate.validateDouble("PlanNoOfBottling",
							table.getBrandPackagingData_PlanNoOfBottling())))
						validateInput = false;
					else if (!(Validate.validateDouble("PlanNoOfBottling",
							table.getNoOfBottlesPerCase())))
						validateInput = false;
					// else
					// if(!(Validate.validateStrReqRow(i,"",String.valueOf(table.getCapacitySprit()))))validateInput=false;
					// else
					// if(!(Validate.validateDouble("installdCap",table.getCapacitySprit())))validateInput=false;
				}
			}

		}

		return validateInput;
	}

	public void setValidateInput(boolean validateInput) {
		this.validateInput = validateInput;
	}

	BWFL_StockReceiveDataTable bopd;

	public BWFL_StockReceiveDataTable getBopd() {
		return bopd;
	}

	public void setBopd(BWFL_StockReceiveDataTable bopd) {
		this.bopd = bopd;
	}

	public String finalizeData() {

		try {
			new BWFL_StockReceiveImpl().dataFinalize(this, bopd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	private String pdfval = "/";

	public String getPdfval() {
		return pdfval;
	}

	public void setPdfval(String pdfval) {
		this.pdfval = pdfval;
	}

	public void print() {

		try {
			new BWFL_StockReceiveImpl().generateReport(bopd, this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private boolean updateFlg;

	public boolean isUpdateFlg() {
		return updateFlg;
	}

	public void setUpdateFlg(boolean updateFlg) {
		this.updateFlg = updateFlg;
	}

	public void updatelisnr(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		BWFL_StockReceiveDataTable dt = (BWFL_StockReceiveDataTable) this
				.getShowDataTableList().get(uiTable.getRowIndex());
		this.setDateOfBottling(dt.getShowDataTable_Date());
		this.setUnit_id(String.valueOf(dt.getUnit_id()));
		this.setLiqureTypeId(dt.getLiqureTypeId());
		this.setLicenceType(dt.getShowDataTable_LicenceType());
		this.setLicenceNoId(dt.getLicenceNo());
		this.setPermitNo(dt.getShowDataTable_PermitNo());
		 this.setDutyId(dt.getDutyId());
		BWFL_StockReceiveDataTable dt2 = new BWFL_StockReceiveDataTable();
		ArrayList list2 = new ArrayList();
		dt2.setBrandPackagingData_Brand(String.valueOf(dt.getBrandId()));
		dt2.setBrandPackagingData_Packaging(String.valueOf(dt.getPackagingId()));
		dt2.setBrandPackagingData_Quantity(String.valueOf(dt.getNewml()));
		dt2.setBrandPackagingData_NoOfBoxes(Integer.parseInt(dt.getShowDataTable_NoOfBoxes()));
		dt2.setBrandPackagingData_PlanNoOfBottling(Integer.parseInt(dt.getShowDataTable_PlanNoOfBottling()));
		////System.out.println("planNoOfBottling=" + dt.getLicenceNo());
		////System.out.println("NoOfBoxes=" + dt2.getBrandPackagingData_NoOfBoxes());
		////System.out.println("getPermitNo=" + licenceNoId);
		list2.add(dt2);
		this.dutyRegisterId=impl.getDutyRegisterId(this, dt.getShowDataTable_PermitNo());
		brandPackagingDataList.clear();
		// @rvind brandPackagingDataList = list2;
		this.updateFlg = true;
		////System.out.println("unit_id1="+unit_id);
		try {
			this.distillery_id=Integer.parseInt(this.unit_id);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		////System.out.println("dt.getLicenceNo()="+dt.getLicenceNo());
		//this.licenceNoList = impl.getLicenseNo(this, "1");
		ArrayList lic_list = new ArrayList();
		SelectItem item = new SelectItem();
		//item.setLabel("--SELECT--");
		//item.setValue("");
		//lic_list.add(item);
		item.setLabel(dt.getLicenceNo());
		item.setValue(dt.getLicenceNo());
		lic_list.add(item);
		this.licenceNoList=lic_list;
		this.brandPackagingDataList = impl.getAddRowData(this,this.getLicenceType());

	}
	private int int_quantity;
	private double int_value;
	
	public int getInt_quantity() {
		return int_quantity;
	}

	public void setInt_quantity(int int_quantity) {
		this.int_quantity = int_quantity;
	}

	public double getInt_value() {
		return int_value;
	}

	public void setInt_value(double int_value) {
		this.int_value = int_value;
	}

	private int dutyRegisterId;
	public int getDutyRegisterId() {
		return dutyRegisterId;
	}

	public void setDutyRegisterId(int dutyRegisterId) {
		this.dutyRegisterId = dutyRegisterId;
	}
	private ArrayList showDataTableList1 = new ArrayList();

	public ArrayList getShowDataTableList1() {
		this.showDataTableList1 = impl.getData1(this);
		return showDataTableList1;
	}

	public void setShowDataTableList11(ArrayList showDataTableList) {
		this.showDataTableList1 = showDataTableList1;
	}
	
	
	
	public double db_totalFees = 0.0;

	public double getDb_totalFees() {
		db_totalFees = 0.0;
		double duty = 0.0;
		try {

			for (int i = 0; i < this.brandPackagingDataList.size(); i++) {
				BWFL_StockReceiveDataTable dt = (BWFL_StockReceiveDataTable) this.getBrandPackagingDataList().get(i);
				duty += dt.getCalPermitFee_dt();
				////System.out.println("duty----------------" + duty);
			}
			// db_total_value=Math.round(duty);
			db_totalFees = duty;

		} catch (Exception e) {
			e.printStackTrace();
		}
		DecimalFormat formatter = new DecimalFormat("#.##");
		db_totalFees=Double.parseDouble(formatter.format(db_totalFees));
		return db_totalFees;
	}

	public void setDb_totalFees(double db_totalFees) {
		this.db_totalFees = db_totalFees;
	}

	public double sum = 0;
	
	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	// ---------------------------- calculate duty --------------
	
	public void calculateTotalFee(ActionEvent ae) {

		this.setSum(0);
		for (int i = 0; i < this.brandPackagingDataList.size(); i++) {
			BWFL_StockReceiveDataTable dt = (BWFL_StockReceiveDataTable) this.brandPackagingDataList.get(i);

			this.setSum(this.getSum() + dt.getCalPermitFee_dt());

		}

	}
	//private int unit_id1;
	//private String unit_name;
	/*public int getUnit_id1() {
		return unit_id1;
	}

	public void setUnit_id1(int unit_id1) {
		this.unit_id1 = unit_id1;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}*/
	public void unitListnr(ValueChangeEvent event) {
		try {
			Object obj = event.getNewValue();
			String id = String.valueOf(obj);
			//////System.out.println("id="+id);
			int unitid=0;
			try {
				unitid = Integer.parseInt(id);
			} catch (Exception e) {
			}
			this.distillery_id=unitid;
			////System.out.println("distillery_id="+distillery_id);
		 
			impl.getLicNoUnitId(this, id); 
			// this.licenceNoList=impl.getLicenseNo(this,id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	private int dutyId;

	public int getDutyId() {
		return dutyId;
	}

	public void setDutyId(int dutyId) {
		this.dutyId = dutyId;
	}
	private String other_unit_id;
	private ArrayList other_unit_list = new ArrayList();

	

	public String getOther_unit_id() {
		return other_unit_id;
	}

	public void setOther_unit_id(String other_unit_id) {
		this.other_unit_id = other_unit_id;
	}

	public ArrayList getOther_unit_list() {
		//if(this.unit_id=="" || this.unit_id==null || this.unit_id.length()<=0){
		//	////System.out.println("1="+unit_id);
		//}else{
			////System.out.println("22="+unit_id);
			other_unit_list=impl.getOtherUnitList();
		//}
		return other_unit_list;
	}

	public void setOther_unit_list(ArrayList other_unit_list) {
		this.other_unit_list = other_unit_list;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
    private BWFL_StockReceiveDataTable cancelData;	
    private String cancel_order_no;
    private Date cancel_order_date;
    private String uploadedPdfName;
    private boolean popupValidate;
    private boolean doc1upload;
    private String authority_name;
    public String getAuthority_name() {
		return authority_name;
	}

	public void setAuthority_name(String authority_name) {
		this.authority_name = authority_name;
	}

	public BWFL_StockReceiveDataTable getCancelData() {
		
		
	
		
		
		
		return cancelData;
	}

	
	
	public String showpopupData()
	{
		
		
	try{
			
			this.request_id=datatable.getRequest_id();
			int box=Integer.parseInt(datatable.getShowDataTable_NoOfBoxes());
			
			int fl36_box=new BWFL_StockReceiveImpl().checkFl36GatepassBwfl(datatable);
			int fl11_box=new BWFL_StockReceiveImpl().checkFl11GatepassBwfl(datatable); 
			
			if(fl36_box==box&&fl11_box==box)
			{
				this.popup="N";
			}else {
				
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Sorry You Can Not Cancel Because Of Gatepass Generated On This Permit No", "Sorry You Can Not Cancel Because Of Gatepass Generated On This Permit No"));
				this.popup="S";
				
			}
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	return "";
	}
	
	
	public void setCancelData(BWFL_StockReceiveDataTable cancelData) {
		this.cancelData = cancelData;
	}

	public String getCancel_order_no() {
		return cancel_order_no;
	}

	public void setCancel_order_no(String cancel_order_no) {
		this.cancel_order_no = cancel_order_no;
	}

	public Date getCancel_order_date() {
		return cancel_order_date;
	}

	public void setCancel_order_date(Date cancel_order_date) {
		this.cancel_order_date = cancel_order_date;
	}

	public String getUploadedPdfName() {
		return uploadedPdfName;
	}

	public void setUploadedPdfName(String uploadedPdfName) {
		this.uploadedPdfName = uploadedPdfName;
	}

	public boolean isDoc1upload() {
		return doc1upload;
	}

	public void setDoc1upload(boolean doc1upload) {
		this.doc1upload = doc1upload;
	}

	public BufferedInputStream getIn() {
		return in;
	}

	public void setIn(BufferedInputStream in) {
		this.in = in;
	}

	public void setShowDataTableList1(ArrayList showDataTableList1) {
		this.showDataTableList1 = showDataTableList1;
	}

	public void setPopupValidate(boolean popupValidate) {
		this.popupValidate = popupValidate;
	}

	public boolean isPopupValidate() {
    	
    	this.popupValidate=true;
    	 if (!(Validate.validateStrReq("CancellationOrderNo", this.getCancel_order_no())))
    		 this.popupValidate = false;
		 else if (!(Validate.validateDate("OrderDate", this.getCancel_order_date())))
			 this.popupValidate = false;
		 else 	if (!(Validate.validateStrReq("AuthorityName", this.getAuthority_name())))
			 this.popupValidate = false;

		 else 	if (!(Validate.validateBoolean("UploadPermitCancelOrder", this.doc1upload)))
			 this.popupValidate = false;
          return popupValidate;
	}
public void cancelOrder()
	{
		
		
		try{
			if(isPopupValidate())
				{
				new BWFL_StockReceiveImpl().cancelPermit(this.cancelData, this);
					}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void cancelOrderClose()
	{
		try{
			this.cancel_order_date=null;
			this.authority_name="";
			this.doc1upload=false;
			this.cancel_order_no="";
			this.setUploadedPdfName("");
		}catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	 String mypath = Constants.JBOSS_SERVER_PATH
				+ Constants.JBOSS_LINX_PATH + File.separator + "ExciseUp"
				+ File.separator + "Fl2d" + File.separator
				+ "cancelpermit";
	 BufferedInputStream in = null;
	 
	 public void fileUpload(UploadEvent event)
	{
		
	String name="";
		
		try {
			
			InputStream inFile = null;
			UploadItem item = event.getUploadItem();
			String FullfileName = item.getFileName();
			String FullfileExt = null;
			if (FullfileName != null && FullfileName.length() > 4) {
				FullfileExt = FullfileName.substring(FullfileName
						.lastIndexOf("."));
			}
			String path = item.getFile().getAbsolutePath();
			String filePath = item.getFile().getPath();
			if (filePath != null) {
				inFile = new FileInputStream(path);
				boolean success = false;
				if (!(new File(mypath).exists())) {
					File file = new File(mypath);
					success = file.mkdirs();
				}
				inFile = new FileInputStream(path);
				in = new BufferedInputStream(inFile);

				name=new BWFLImportImpl().getUploadSequence()+".pdf";
				FileOutputStream out = new FileOutputStream(this.mypath
						+ File.separator + name );

				BufferedOutputStream outb = new BufferedOutputStream(out);
				int z = 0;
				while ((z = this.in.read()) != -1) {
					outb.write(z);

				}
				
				outb.flush();
				outb.close();
				 
					
					 
					
					doc1upload = true;
				 this.setUploadedPdfName(name);

			} else {
				System.out.println("NO FILE READ STARTED.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//@rvind
		private int request_id;
		private String remark;
		public int getRequest_id() {
			return request_id;
		}
		public void setRequest_id(int request_id) {
			this.request_id = request_id;
		}
		public String getRemark() {
			
			
			System.out.println("POPOPOPOPOPOPOP[POP "+ this.popup);
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		/*public void cancelData(ActionEvent e) {
			try {
				UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
				BWFL_StockReceiveDataTable dt = (BWFL_StockReceiveDataTable) this.showDataTableList1.get(uiTable.getRowIndex());     
				this.request_id=dt.getRequest_id();	
			int box=Integer.parseInt(dt.getShowDataTable_NoOfBoxes());
			
				int fl36_box=new BWFL_StockReceiveImpl().checkFl36GatepassBwfl(dt);
				int fl11_box=new BWFL_StockReceiveImpl().checkFl11GatepassBwfl(dt); 
				
				if(fl36_box==box&&fl11_box==box)
				{
					//this.popup="{rich:component('popup2')}.show()";
				}else {
					
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Sorry You Can Not Cancel Because Of Gatepass Generated On This Permit No", "Sorry You Can Not Cancel Because Of Gatepass Generated On This Permit No"));
					//this.popup="{rich:component('popup2')}.hide()";
					
				}
			
			
			
			} catch (Exception ex) {
				ex.printStackTrace();
			}	 
		}*/
		public void cancelRequest() {
			try {
				if (this.remark != null && this.remark.length() > 0) {
					impl.dataCancel(this);
				} else {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
					" Please Fill Remark !!","Please Fill Remark !!"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
