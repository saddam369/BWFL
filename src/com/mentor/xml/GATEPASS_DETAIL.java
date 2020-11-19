package com.mentor.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


public class GATEPASS_DETAIL {
	
	
	public GATEPASS_DETAIL()
	{}

	public String getSIZE() {
		return SIZE;
	}
	public void setSIZE(String sIZE) {
		SIZE = sIZE;
	}
	 String BRAND_ID;
	 String  PACKAGE_ID;
 String BRAND_NAME;
	 String PACKAGE_NAME;
	 String BOX_RECIVE;
	 String PACKAGE_ML;
	 String BATCH_NO;
	 String BOTTLE_RECV;
	 String UNIT_ID;
	String UNIT_TYPE;
	 String SERIAL_NUMBER;
	 String SIZE;
	
	public String getBRAND_ID() {
		return BRAND_ID;
	}
	public String getPACKAGE_ID() {
		return PACKAGE_ID;
	}
	public String getBRAND_NAME() {
		return BRAND_NAME;
	}
	public String getPACKAGE_NAME() {
		return PACKAGE_NAME;
	}
	public String getBOX_RECIVE() {
		return BOX_RECIVE;
	}
	public String getPACKAGE_ML() {
		return PACKAGE_ML;
	}
	public String getBATCH_NO() {
		return BATCH_NO;
	}
	public String getBOTTLE_RECV() {
		return BOTTLE_RECV;
	}
	public String getUNIT_ID() {
		return UNIT_ID;
	}
	public String getUNIT_TYPE() {
		return UNIT_TYPE;
	}
	public String getSERIAL_NUMBER() {
		return SERIAL_NUMBER;
	}
	
	
	
	
	
	
	
	//////////////////////////Setter/////////////////////////////////
	public void setBRAND_ID(String bRAND_ID) {
		BRAND_ID = bRAND_ID;
	}
	public void setPACKAGE_ID(String pACKAGE_ID) {
		PACKAGE_ID = pACKAGE_ID;
	}
	public void setBRAND_NAME(String bRAND_NAME) {
		BRAND_NAME = bRAND_NAME;
	}
	public void setPACKAGE_NAME(String pACKAGE_NAME) {
		PACKAGE_NAME = pACKAGE_NAME;
	}
	public void setBOX_RECIVE(String bOX_RECIVE) {
		BOX_RECIVE = bOX_RECIVE;
	}
	public void setPACKAGE_ML(String pACKAGE_ML) {
		PACKAGE_ML = pACKAGE_ML;
	}
	public void setBATCH_NO(String bATCH_NO) {
		BATCH_NO = bATCH_NO;
	}
	public void setBOTTLE_RECV(String bOTTLE_RECV) {
		BOTTLE_RECV = bOTTLE_RECV;
	}
	public void setUNIT_ID(String uNIT_ID) {
		UNIT_ID = uNIT_ID;
	}
	public void setUNIT_TYPE(String uNIT_TYPE) {
		UNIT_TYPE = uNIT_TYPE;
	}
	public void setSERIAL_NUMBER(String sERIAL_NUMBER) {
		SERIAL_NUMBER = sERIAL_NUMBER;
	}
	
	
	
}
