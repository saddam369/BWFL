package com.mentor.datatable;

import java.util.*;

public class CSD_Application_Tracking_DT {
   private int srNo;
   private int app_id;
   private Date app_date;
   private String permit_no;
   private String status;
   private boolean flag;
   
public boolean isFlag() {
	return flag;
}
public void setFlag(boolean flag) {
	this.flag = flag;
}
public int getSrNo() {
	return srNo;
}
public void setSrNo(int srNo) {
	this.srNo = srNo;
}
public int getApp_id() {
	return app_id;
}
public void setApp_id(int app_id) {
	this.app_id = app_id;
}
public Date getApp_date() {
	return app_date;
}
public void setApp_date(Date app_date) {
	this.app_date = app_date;
}
public String getPermit_no() {
	return permit_no;
}
public void setPermit_no(String permit_no) {
	this.permit_no = permit_no;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
   
}
