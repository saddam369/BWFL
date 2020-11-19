package com.mentor.datatable;

import java.util.ArrayList;
import java.util.Date;

public class RequestForAccidentalCaseBWFLDt {
	


		

			
			private int srno;
			private int req_id;
			private String gatepass_type;
			private String gatepass_no;
			private Date gatepass_date;
			private String accident_address;
			private String accident_district_name;
			private Date accident_date;
			private Date req_dt;
			
			public Date getReq_dt() {
				return req_dt;
			}
			public void setReq_dt(Date req_dt) {
				this.req_dt = req_dt;
			}
			public int getSrno() {
				return srno;
			}
			public void setSrno(int srno) {
				this.srno = srno;
			}
			public int getReq_id() {
				return req_id;
			}
			public void setReq_id(int req_id) {
				this.req_id = req_id;
			}
			public String getGatepass_type() {
				return gatepass_type;
			}
			public void setGatepass_type(String gatepass_type) {
				this.gatepass_type = gatepass_type;
			}
			public String getGatepass_no() {
				return gatepass_no;
			}
			public void setGatepass_no(String gatepass_no) {
				this.gatepass_no = gatepass_no;
			}
			public Date getGatepass_date() {
				return gatepass_date;
			}
			public void setGatepass_date(Date gatepass_date) {
				this.gatepass_date = gatepass_date;
			}
			public String getAccident_address() {
				return accident_address;
			}
			public void setAccident_address(String accident_address) {
				this.accident_address = accident_address;
			}
			public String getAccident_district_name() {
				return accident_district_name;
			}
			public void setAccident_district_name(String accident_district_name) {
				this.accident_district_name = accident_district_name;
			}
			public Date getAccident_date() {
				return accident_date;
			}
			public void setAccident_date(Date accident_date) {
				this.accident_date = accident_date;
			}
			

		}






