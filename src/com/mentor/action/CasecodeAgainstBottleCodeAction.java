package com.mentor.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.mentor.impl.CasecodeAgainstBottleCodeImpl;
import com.mentor.utility.Constants;

public class CasecodeAgainstBottleCodeAction {
	
	
	
	private boolean renderFlag;
	private String  excelPath;
	public boolean isRenderFlag() {
		return renderFlag;
	}



	public void setRenderFlag(boolean renderFlag) {
		this.renderFlag = renderFlag;
	}



	public String getExcelPath() {
		return excelPath;
	}



	public void setExcelPath(String excelPath) {
		this.excelPath = excelPath;
	}



	public ArrayList getUploadedCases() {
		return uploadedCases;
	}



	public void setUploadedCases(ArrayList uploadedCases) {
		this.uploadedCases = uploadedCases;
	}



	public String getUploadedexcelwholesalepath() {
		return uploadedexcelwholesalepath;
	}



	public void setUploadedexcelwholesalepath(String uploadedexcelwholesalepath) {
		this.uploadedexcelwholesalepath = uploadedexcelwholesalepath;
	}



	ArrayList uploadedCases=new ArrayList();
	public String uploadedexcelwholesalepath;
	public void excelUploadForWholesale(UploadEvent event) throws Exception
	{
		
		UploadItem item = event.getUploadItem();
		String FullfileName = item.getFileName();
		this.uploadedexcelwholesalepath=  item.getFile().getPath();
		//System.out.println(this.uploadedexcelwholesalepath);
	   
	}
	
	

public void getUploadedExcelDataForCasecode()
{
	int count=0;
	try{
		
		this.uploadedCases.clear();
		FileInputStream file =null;
		try{
		
		 file = new FileInputStream(new File(this.uploadedexcelwholesalepath));
		}catch(Exception e)
		{
			this.renderFlag=false;
			this.setExcelPath("");
			  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("File Can Not Be Read", "File Can Not Be Read"));
		}
		 
        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        
        Iterator<Row> rowIterator = sheet.iterator();
       
      while (rowIterator.hasNext())
        {
        //System.out.println("333333333333333333333333333333============================");
    	  
        	
        	 Row row = rowIterator.next();
        	  if(count>0)
	    	  {
        		  String bottlecode=row.getCell(0).getStringCellValue();
        		  System.out.println("bottlecode "+bottlecode);
        		this.uploadedCases.add(new CasecodeAgainstBottleCodeImpl().getCasecode(bottlecode));
        	       
	    	  }
	    	  count++;
        
        //System.out.println("dfgfdgddf============================");
        
        }
        System.out.println("sizeeeee listttt============================ "+this.uploadedCases.size());
        if(this.uploadedCases.size()>0)
        {
        	
        	//this.
        	
        	int row=0;
        	
        	String path = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH
    				+ File.separator + "ExciseUp" + File.separator + "casecode"
    				+ File.separator;

        	
        	try{
        		 XSSFWorkbook workbook1 = new XSSFWorkbook();
        		 XSSFSheet spreadsheet = workbook1.createSheet( "CaseCode Against Bottle Code ");
        		 for(int i=0;i<this.uploadedCases.size();i++)
        		 {
        		XSSFRow row1= spreadsheet.createRow(row);
        		XSSFCell cell=row1.createCell(0);
        		
        		System.out.println("hahhaha"+(String)this.uploadedCases.get(i));
        		cell.setCellValue((String)this.uploadedCases.get(i));
        		 row++;
        		 }
        		 
        		 Random rn = new Random();
     			int ra = rn.nextInt(250) + 1;
     			FileOutputStream fileOut = new FileOutputStream(path + "casecode"
     					+ ra + ".xls");
     			workbook1.write(fileOut);
     			fileOut.close();
     			this.setExcelPath("casecode"
     					+ ra + ".xls");
     			this.renderFlag=true;
        		 
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        		this.renderFlag=false;
    			this.setExcelPath("");
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
        	}
        }else{
        	this.renderFlag=false;
        	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("SORRY NO CASECODE FOUND CORRESPONDING BOTTLE", "SORRY NO CASECODE FOUND CORRESPONDING BOTTLE"));
        }
        
        
        
}catch(Exception e)
{
	e.printStackTrace();
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage(), e.getMessage()));
}
	
}
	
}
