package com.mentor.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="GATEPASS_DETAILS")

public class GATEPASS_DETAILS {
	
	public GATEPASS_DETAILS()
	{}
	public GATEPASS_DETAILS(List list)
	{
		try{
			this.GATEPASS_DETAIL=list;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private List<GATEPASS_DETAIL> GATEPASS_DETAIL;
	@XmlElement
	public List<GATEPASS_DETAIL> getGATEPASS_DETAIL() {
		return GATEPASS_DETAIL;
	}

	public void setGATEPASS_DETAIL(List<GATEPASS_DETAIL> gATEPASS_DETAIL) {
		GATEPASS_DETAIL = gATEPASS_DETAIL;
	}
	
	
	
	

}
