package com.mentor.impl;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mentor.action.BWFL_StockReceiveAction;
import com.mentor.action.BarCodeForCSDAction;
import com.mentor.resource.ConnectionToDataBase;
import com.mentor.datatable.BWFL_StockReceiveDataTable;
import com.mentor.datatable.BarCodeForCSDDataTable; 
import com.mentor.utility.Constants;
import com.mentor.utility.NewCommomImpl;
import com.mentor.utility.RandomPasswordGenerator;
import com.mentor.utility.Sender;
import com.mentor.utility.Utility;

public class BarCodeForCSDImpl {
	
	
	
	//---------------------------------- distillery name add etc --------------
	
		public String getMobile_No(BarCodeForCSDAction ac, String app_id) {
			int id = 0;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String mob="";
			try {

				 String
				 queryList=
				 
				" SELECT seq, licence_id, radio_type, csd_name, csd_address, permit_no, issue_date, "+ 
				"		 distillery_brewery_name, distillery_id, brewery_id, distillery_brewery_address,  "+ 
				"		 licence_no, contact_no, created_by , email_id "+
				"		 	FROM fl2d.entry_of_permit_master where licence_id=?  and permit_no ='"+ac.getPermit_No().trim()+"'";
				
				 
				 
				con = ConnectionToDataBase.getConnection();
				pstmt = con.prepareStatement(queryList);
				pstmt.setInt(1, Integer.parseInt(app_id));

				rs = pstmt.executeQuery();

				if (rs.next()) {
					
						ac.setMobile_No(rs.getString("contact_no")+"-"+rs.getString("email_id"));
						mob=rs.getString("contact_no")+"-"+rs.getString("email_id");
				//	 System.out.println("-------  ------"+ rs.getString("contact_no"));

				}

				
			} catch (SQLException se) {
				se.printStackTrace();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (con != null)
						con.close();

				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			return mob;

		}

		
		
		//-------------------------------------
		
		
		public ArrayList getcdsName() {

			

			ArrayList list = new ArrayList();
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			SelectItem item = new SelectItem();
			item.setLabel("--SELECT--");
			item.setValue(0);
			list.add(item);
			String sql = "";

			
			try {

				
				sql = 
						"	SELECT  distinct   a.licence_id ,b.int_app_id ,b.vch_applicant_name "+
						"		FROM fl2d.entry_of_permit_master a, licence.fl2_2b_2d b  where " +
						" a.licence_id=b.int_app_id order by  b.vch_applicant_name";

				con = ConnectionToDataBase.getConnection();
				ps = con.prepareStatement(sql);
			
				
				rs = ps.executeQuery();
				while (rs.next()) {
					item = new SelectItem();
					item.setLabel(rs.getString("vch_applicant_name"));
					item.setValue(rs.getString("licence_id"));
					list.add(item);
				}
			} catch (Exception e) {
			//	e.printStackTrace();
			} finally {
				try {

					if (con != null)
						con.close();
					if (ps != null)
						ps.close();
					if (rs != null)
						rs.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return list;
		}

		//------------------------------------------
		
		
		public ArrayList getDataBrandDetail(BarCodeForCSDAction ac) {
			ArrayList list = new ArrayList();
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			FacesContext facesContext = FacesContext.getCurrentInstance();
			BarCodeForCSDAction bof = (BarCodeForCSDAction) facesContext
					.getApplication()
					.createValueBinding("#{barCodeForCSDAction}")
					.getValue(facesContext);

			String csd_id = bof.getCsd_Id();

			

			String sql =
					
				"	SELECT distinct a.seq, d.code_generate_through,a.licence_id, a.radio_type, a.csd_name, a.csd_address, a.permit_no, a.issue_date,  "+  
				"	a.distillery_brewery_name, a.distillery_id, a.brewery_id, a.distillery_brewery_address,  "+
				"	a.licence_no, a.contact_no, a.created_by, b.finalize_flg,  "+
				"	b.int_id, b.seq, b.brand_id, b.size, b.no_of_bottle,b.cases,b.no_cases, "+
				"	c.brand_id, c.brand_name ,b.finalized_date,b.int_id  "+
				"	FROM fl2d.entry_of_permit_master a , fl2d.entry_of_permit_master_detail b , "+
				"	distillery.brand_registration_19_20 c,distillery.packaging_details_19_20 d "+
				"	where a.seq=b.seq  and d.brand_id_fk=b.brand_id  "+
				"	and b.brand_id=c.brand_id and b.packaging_id =d.package_id   "+
				"	and a.licence_id=?  and a.permit_no ='"+ac.getPermit_No().trim()+"'";
					
					
					
			
			// user_name ='"+ResourceUtil.getUserNameAllReq()+"'";

			 
			try {
				con = ConnectionToDataBase.getConnection();
				ps = con.prepareStatement(sql);
				ps.setInt(1, Integer.parseInt(csd_id));
				
				rs = ps.executeQuery();
				while (rs.next()) {

					BarCodeForCSDDataTable dt = new BarCodeForCSDDataTable();
					dt.setCsd(rs.getInt("licence_id"));
					dt.setShowDataTable_Quntity(rs.getString("size"));
					dt.setShowDataTable_PlanNoOfBottling(rs
							.getString("no_of_bottle"));
					dt.setShowDataTable_NoOfBoxes(rs.getString("no_cases"));
					dt.setBrandPackagingData_Brand_Name(rs.getString("brand_name"));
					dt.setBrandPackagingData_Brand_Size(rs.getString("size"));
					dt.setBrandPackagingData_Brand_No_OF_Bottels(rs.getString("no_of_bottle"));
					dt.setGtinno(rs.getString("code_generate_through"));
					dt.setRequest_id(rs.getInt("int_id"));
					if (rs.getDate("finalized_date") != null) {
						Date dat = Utility.convertSqlDateToUtilDate(rs
								.getDate("issue_date"));
						System.out.println("date finalize" + dat);

						DateFormat formatter = new SimpleDateFormat("yyMMdd");
						String date = formatter.format(dat);
						dt.setFinalizedDateString(date);
						
					}
					dt.setShowDataTable_Date(rs.getDate("issue_date"));	
					dt.setNewsize(rs.getInt("cases"));
					dt.setLicencenoo(rs.getString("permit_no"));
					dt.setFinalizedFlag(rs.getString("finalize_flg"));
					list.add(dt);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (con != null)
						con.close();
					if (ps != null)
						ps.close();
					if (rs != null)
						rs.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return list;
		}

	
	//------------------------------------------------  for OTP --------------------------
		
		public void getAndSendMessage(BarCodeForCSDAction action) {
			
			PreparedStatement pstmt = null,pstmt2=null;
			Connection conn = null;		
			ResultSet rs = null;
			String msgid="";
			String to_num="";
			String to_name="";
			String msg_str="";
			int status=0;
			String otp="0";
			
			String toEmail="";
			
			try{
				conn = ConnectionToDataBase.getConnection();
				
				
				String queryList= " select seq  , contact_no, csd_name, email_id FROM fl2d.entry_of_permit_master where licence_id=? and permit_no='"+action.getPermit_No().trim()+"'";

				pstmt=conn.prepareStatement(queryList);
				pstmt.setInt(1, Integer.parseInt(action.getCsd_Id()));
				rs= pstmt.executeQuery();
				if(rs.next())
				{
					msg_str=rs.getString(1);
					to_num=rs.getString(2);
					to_name=rs.getString(3);
					toEmail=rs.getString(4);
				}rs.close();
				
				
				/*	String selectmax="SELECT ACCOUNTS.INT_MSG_ID_SEQ.NEXTVAL FROM DUAL";
				
					pstmt=conn.prepareStatement(selectmax);
					rs = pstmt.executeQuery();
					if(rs.next())
					{
						msgid=rs.getString(1);	
					}
					rs.close();
					*/
					
					
					
					
					otp=this.password();
					
					String otp1=otp;	
					String query = 
							
						
						
						/*	
						"	INSERT INTO fl2d.comman_sms_db ( "+
						"			msg_id, vch_modual_name, vch_mob_no, csd_id, vch_to_name, vch_msg, "+
						"		    date_submition, vch_sent_status, opt_no , permit_no) "+
						"			VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)  " ;
							*/
					
						
							
							"	INSERT INTO fl2d.comman_sms_db ( "+
							"			msg_id, vch_modual_name, vch_mob_no, csd_id, vch_to_name, vch_msg, "+
							"		    date_submition, vch_sent_status, opt_no , permit_no) "+
							"			VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)  ON CONFLICT ON CONSTRAINT comman_sms_db_pkey " +
							" 	do update set opt_no= '"+otp1+"'  " ;
							
					
					
					
					
					
					
							
	 
					pstmt = conn.prepareStatement(query);
					
					//	pstmt.setString(1, msgid);
					
						pstmt.setInt(1, this.getMax_id());
						pstmt.setString(2, "CSD");
						pstmt.setString(3, to_num);
						pstmt.setString(4,  (action.getCsd_Id()));
						pstmt.setString(5, to_name);
						pstmt.setString(6, "Your OTP for Bar Code Downloading of Permit No.:"+action.getPermit_No().trim()+" is :"+otp);
						pstmt.setDate(7, Utility.convertUtilDateToSQLDate(action.getDtdate()));
						pstmt.setString(8, "SENT");
						pstmt.setString(9, otp);
						pstmt.setString(10, action.getPermit_No());
						status= pstmt.executeUpdate();
						
						if(status>0)
						{System.out.println("----------oooooo-------------");
							status=0;
							String to=to_num.trim();
							
							String temp="Dear User,";
							Sender s=null;
							/*	String to=to_num.trim();
								
								String temp="Dear User,";
								Sender s=null;
								
								String msg="Your OTP for Bar Code Downloading of Permit No.:"+action.getPermit_No().trim()+" is :"+otp;
								
								String SMS_USER_ID="infotechmentor";
								String SMS_USER_PWD="mentor@1234";
								String SMS_SERVER_ADD="http://192.0.1.50:8080";
								String SMS_FROM_NAME="LDAONL";
								String sentstatus="";
								
								s = new Sender(SMS_SERVER_ADD, SMS_USER_ID,SMS_USER_PWD,to,SMS_FROM_NAME, msg);
							 
								sentstatus=s.submitMessage();*/
							  
						
							
										     String msg="Your OTP for Bar Code Downloading of Permit No.:"+action.getPermit_No().trim()+" is :"+otp;
							String Statussave = new Sender(
									"http://priority.muzztech.in/sms_api/sendsms.php",
									NewCommomImpl.getSendSmsUser(),
									NewCommomImpl.getSendSmsUserPassword(),
									to, "UPEXWS", msg)
									.submitMessage();
								
								
								//-------------- email ----------------------
								 
								String sub = "Bar Code OTP ";
								String msgEmail = 	"  Your OTP for  Bar Code Downloading is " + otp + 
												"  ";
										
								 
								String from = NewCommomImpl.getEmailUser();
								String password = NewCommomImpl.getEmailUserPassword();
								NewCommomImpl.sendEmail(toEmail, sub, msgEmail, from,password);
								
								
															
								
						}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();				
					if(rs!=null)rs.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		
		}
		
		
	//-----------------------------------
		

	
			public String password()
			{
				

				int noOfCAPSAlpha = 0;
				int noOfDigits = 5;
				int noOfSplChars = 0;
				int minLen = 5;
				int maxLen = 6;

			
				char[] pswd = RandomPasswordGenerator.generatePswd(minLen, maxLen,
						noOfCAPSAlpha, noOfDigits, noOfSplChars);
				System.out.println("Len = " + pswd.length + ", " + new String(pswd));
				           

				return new String(pswd);
			}
			
			
		
		//-------------------------------- max id for msg -----------------
			
			public int getMax_id() {
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				int id = 0;
				String query = "SELECT max(msg_id) as id FROM fl2d.comman_sms_db ";
				try {
					con = ConnectionToDataBase.getConnection();
					ps = con.prepareStatement(query);
					rs = ps.executeQuery();
					if (rs.next()) {
						id = rs.getInt("id");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (con != null)
							con.close();
						if (ps != null)
							ps.close();
						if (rs != null)
							rs.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return id + 1;
			}
			
			
			
		//----------------------------- validation --------------------
			

			
			
public boolean validateotp(String ot, BarCodeForCSDAction action) {
		
		PreparedStatement pstmt = null,pstmt2=null;
		Connection conn = null;		
		ResultSet rs = null;
		String msgid="";
		String to_num="";String to_name="";
		String msg_str="";
		boolean status=false;
		 
		try{
			conn =ConnectionToDataBase.getConnection();
			 
			String selectmax=
					
				/*	"SELECT distinct INT_RETRY_NO,case when OTPTIME + interval '30' minute <CURRENT_TIMESTAMP   then 1 else 2 end as code" +
					" from ACCOUNTS.COMMON_SMS_DB where VCH_SENT_STATUS='SENT' and VCH_MODULE_NAME='PROPERTY'  and VCH_TO_EMP_ID="+ResourceUtil.getUserNameAllReq();
			
			*/
			
	      " select opt_no, permit_no csd_id from fl2d.comman_sms_db where permit_no=? and csd_id=? " ;
			
			
			pstmt=conn.prepareStatement(selectmax);
			pstmt.setString(1, action.getPermit_No());
			pstmt.setString(2, action.getCsd_Id());
			
		//	System.out.println("-----    permit No------"+ action.getPermit_No());
		//	System.out.println("-----    csd------"+ action.getCsd_Id());
			
				rs = pstmt.executeQuery();
				if(rs.next())
				{	
				
		
				System.out.println("-----  get ot -----"+ot);
				
					if( rs.getString(1).equalsIgnoreCase(ot) ){
						status=true;	
					}
				}
				rs.close();
				
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();				
				if(rs!=null)rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return status;
	
	}
			
			
public void dataFinalize(BarCodeForCSDAction action,
		BarCodeForCSDDataTable dt) {
	Connection conn = null;
	Connection conn1 = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	PreparedStatement pstmt3 = null;
	PreparedStatement pstmt4 = null;
	String gtinNo = "";
	long boxsize = 0;
	long caseno = 0;
	int status=0;
	
	String sql = "INSERT INTO public.bottling_fl2a(  "
			+ " dispatch_date, gtin_no, serial_no_start, serial_no_end,case_no,request_id,csd_id,licence_no,licence_type) "
			+ "	VALUES (?, ?, ?, ?, ?, ?,?,?,'FL2A')";

	

	String update = "UPDATE fl2d.entry_of_permit_master_detail "
			+ " SET   finalize_flg='F' ,finalized_date=? "
			+ "WHERE int_id=?";

	try {
		//gtinNo = getBrandPackagingGtinNo(action, dt);
		gtinNo=dt.getGtinno();
	 	 long	serialno = getSerialNoFl2D(new Double(
				dt.getShowDataTable_PlanNoOfBottling()).longValue(),Utility.convertUtilDateToSQLDate(dt
						.getShowDataTable_Date()));
		
		
		
		
		// long
		// i=(Long.parseLong(dt.getShowDataTable_PlanNoOfBottling())/(Long.parseLong(dt.getShowDataTable_NoOfBoxes())));
	 
		if (!gtinNo.equals("") && serialno != 0) {
			conn = ConnectionToDataBase.getConnection3();
			conn1 = ConnectionToDataBase.getConnection();
			conn.setAutoCommit(false);
			conn1.setAutoCommit(false);
			pstmt1 = conn1.prepareStatement(update);
			pstmt1.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			pstmt1.setInt(2, dt.getRequest_id());
			status = pstmt1.executeUpdate();

		 
				status = 0;
			
				pstmt1 = conn.prepareStatement(sql);
				for (int i = 0; i < Long.parseLong(dt
						.getShowDataTable_NoOfBoxes()); i++) {
					caseno = getcaseNoFl2d(Utility.convertUtilDateToSQLDate(dt
							.getShowDataTable_Date()));

				//	pstmt1.setInt(1, action.getDistillery_id());

					pstmt1.setDate(1, Utility.convertUtilDateToSQLDate(dt
							.getShowDataTable_Date()));
					pstmt1.setString(2, gtinNo);
					
					pstmt1.setString(3, StringUtils.leftPad(String.valueOf(serialno), 8, '0'));
					//pstmt1.setString(6, dt.getLicenceNo());
					//pstmt1.setString(7, dt.getShowDataTable_LicenceType());
					//pstmt1.setLong(5, caseno);
					
					
					Random rand1 = new Random();
					//int n1 = 10+rand1.nextInt(90) ;
					int n1=		rand1.nextInt((80 - 71) + 1) + 71;
					pstmt1.setString(5,n1+""+StringUtils.leftPad(String.valueOf(caseno), 6, '0')+getCheckDigit(n1+""+StringUtils.leftPad(String.valueOf(caseno), 6, '0')) );
					
					
					
					
					
					
					pstmt1.setInt(6, dt.getRequest_id());
					pstmt1.setString(4, StringUtils.leftPad(String.valueOf(serialno+(dt.getNewsize()-1)), 8, '0'));
					
					pstmt1.setInt(7, dt.getCsd());
					pstmt1.setString(8, dt.getLicencenoo());
					serialno+=dt.getNewsize();
					pstmt1.addBatch();
					
				 
			}
			
			int[] status1=pstmt1.executeBatch();
			
			if (status1.length > 0) {
				status = 0;
				boolean flag = write(dt, action, conn);

				if (flag) {
					status = 1;
				} else {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("Excel Not Generated",
									"Excel Not Generated"));
				}
			}
			if (status > 0) {

				conn.commit();
				conn1.commit();

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Finalized SuccessFully",
								"Finalized SuccessFully"));
			} else {
				conn.rollback();
				conn1.rollback();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Not Finalized ",
								" Not Finalized "));
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage("Gtin and Serial No Can Not Zero ",
							" Gtin and Serial No Can Not Zero"));
		}
	}

	catch (Exception e) {

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(e.getMessage(), e.getMessage()));
		try {
			conn.rollback();
			conn1.rollback();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		e.printStackTrace();
	} finally {

		try {

			if (pstmt1 != null)
				pstmt1.close();
			if (pstmt2 != null)
				pstmt2.close();
			if (pstmt3 != null)
				pstmt3.close();
			if (pstmt4 != null)
				pstmt4.close();
			if (conn != null)
				conn.close();
			if (conn1 != null)
				conn1.close();

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}
	}
}



			
		
		
public synchronized long getSerialNoFl2D(long noOfSequenc,Date date) {
	
	Date today = date;
	 SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyy");
    String currentdate=	 sdf.format(today);
	
	
	String sql = " select     nextval('public.fl2a_serial_seq_"+currentdate+"')";
	String sqll = "ALTER SEQUENCE public.fl2a_serial_seq_"+currentdate+" RESTART WITH ? ";
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	long serialNo = 0;
	long currseq = 0;

	try {
		
		System.out.println("CREATE SEQUENCE IF NOT EXISTS public.fl2a_serial_seq_"+currentdate);
		conn = ConnectionToDataBase.getConnection3();
		pstmt=conn.prepareStatement("CREATE SEQUENCE IF NOT EXISTS public.fl2a_serial_seq_"+currentdate);
		pstmt.executeUpdate();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			serialNo = rs.getLong(1);
			if (serialNo == 0) {
				serialNo = serialNo + 1;
			}
			System.out.println("noOfSequenc " + noOfSequenc);

			pstmt1 = conn
					.prepareStatement("ALTER SEQUENCE public.fl2a_serial_seq_"+currentdate+" RESTART WITH "
							+ (noOfSequenc + serialNo));

			
			pstmt1.executeUpdate();

		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {

		try {
			if (rs != null)
				rs.close();

			if (pstmt != null)
				pstmt.close();

			if (conn != null)
				conn.close();

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}
	}

	return serialNo;
}


public synchronized long getcaseNoFl2d(Date date) {
	
	 Date today = date;
	 SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyy");
     String currentdate=	 sdf.format(today);
	String sql = " select     nextval('public.fl2a_casecode_serial_seq_"+currentdate+"')";

	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	long serialNo = 0;
	long currseq = 0;

	try {
		conn = ConnectionToDataBase.getConnection3();
		pstmt=conn.prepareStatement("CREATE SEQUENCE IF NOT EXISTS public.fl2a_casecode_serial_seq_"+currentdate);
		pstmt.executeUpdate();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			serialNo = rs.getLong(1);
			if (serialNo == 0) {
				serialNo = serialNo;
			}

		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {

		try {
			if (rs != null)
				rs.close();

			if (pstmt != null)
				pstmt.close();

			if (conn != null)
				conn.close();

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}
	}

	return serialNo;
}




public boolean write(BarCodeForCSDDataTable dt,
		BarCodeForCSDAction action, Connection conn) {

	

	String fl3 = ""
			+
			
			"	select to_char(y.gs::numeric, 'fm00000000')as GENERATE_SERIES , y.dispatch_date, y.gtin_no,csd_id,"
			+ "	  y.serial_no_start, y.serial_no_end , "
			+ "	to_char(y.case_no::numeric , 'fm000000000')as case_no from( "
			+ "	select  GENERATE_SERIES(x.serial_no_start::numeric ,x.serial_no_end::numeric ) as gs ,x.serial_no_start ,x.serial_no_end,csd_id, "
			+ "	x.case_no,x.dispatch_date,x.gtin_no "
			+ "	from ( "
			+ "	SELECT  csd_id,dispatch_date, gtin_no, serial_no_start, serial_no_end,  case_no "
			+ "	FROM public.bottling_fl2a a "
			+ "	where  dispatch_date=?   and gtin_no=? and request_id=?)x)y";

	
	String relativePath = Constants.JBOSS_SERVER_PATH
			+ Constants.JBOSS_LINX_PATH;
	FileOutputStream fileOut = null;

	PreparedStatement pstmt = null;
	ResultSet rs = null;
	long start = 0;
	long end = 0;
	boolean flag = false;
	long k = 0;
	String noOfUnit = "";
	String date = null;

	try {

		

	 
			pstmt = conn.prepareStatement(fl3);

			
			
			pstmt.setDate(1, Utility.convertUtilDateToSQLDate(dt
					.getShowDataTable_Date()));
			
			pstmt.setString(2, dt.getGtinno());
			pstmt.setInt(3, dt.getRequest_id());
			rs = pstmt.executeQuery();
			 
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet worksheet = workbook.createSheet("Barcode Report");
	
		CellStyle unlockedCellStyle = workbook.createCellStyle();
		unlockedCellStyle.setLocked(false);

		// Sheet sheet = workbook.createSheet();
		worksheet.protectSheet("agristat");
		worksheet.setColumnWidth(0, 3000);
		worksheet.setColumnWidth(1, 8000);
		worksheet.setColumnWidth(2, 8000);
		worksheet.setColumnWidth(3, 8000);
		worksheet.setColumnWidth(4, 6000);
		

		XSSFRow rowhead0 = worksheet.createRow((int) 0);
		
		XSSFCell cellhead0 = rowhead0.createCell((int) 0);
		cellhead0.setCellValue("Barcode Report");

		rowhead0.setHeight((short) 700);
		cellhead0.setCellStyle(unlockedCellStyle);
		XSSFCellStyle cellStyl = workbook.createCellStyle();
		// HSSFCellStyle cellStyl = workbook.createCellStyle();

		cellStyl = workbook.createCellStyle();
		XSSFFont hSSFFont = workbook.createFont();
		hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
		hSSFFont.setFontHeightInPoints((short) 12);
		hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		hSSFFont.setColor(HSSFColor.GREEN.index);
		cellStyl.setFont(hSSFFont);
		cellhead0.setCellStyle(cellStyl);

		// HSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		// -----------------------------

		XSSFCellStyle unlockcellStyle = workbook.createCellStyle();
		unlockcellStyle.setLocked(false);

		// ----------------------------
		XSSFRow rowhead = worksheet.createRow((int) 1);

		XSSFCell cellhead1 = rowhead.createCell((int) 0);
		cellhead1.setCellValue("S.No.");

		cellhead1.setCellStyle(cellStyle);

		XSSFCell cellhead2 = rowhead.createCell((int) 1);
		cellhead2.setCellValue("Gtin");
		cellhead2.setCellStyle(cellStyle);

		XSSFCell cellhead3 = rowhead.createCell((int) 2);
		cellhead3.setCellValue("FinalizeDate");
		cellhead3.setCellStyle(cellStyle);

		XSSFCell cellhead4 = rowhead.createCell((int) 3);
		cellhead4.setCellValue("Case Etn");
		cellhead4.setCellStyle(cellStyle);

		XSSFCell cellhead5 = rowhead.createCell((int) 4);
		cellhead5.setCellValue("BottleBarcode");
		cellhead5.setCellStyle(cellStyle);
		

		int i = 0;
		while (rs.next()) {

			// System.out.println("i==========="+i++);
			Date dat = Utility.convertSqlDateToUtilDate(rs
					.getDate("dispatch_date"));

			DateFormat formatter;

			formatter = new SimpleDateFormat("yyMMdd");
			date = formatter.format(dat);

			String lic = dt.getLicencenoo().replaceAll("/", "");

			// System.out.println("while in");serial_no_start, serial_no_end
			//start = rs.getLong("serial_no_start");
			//end = rs.getLong("serial_no_end");

			k++;
			XSSFRow row1 = worksheet.createRow((int) k);

			XSSFCell cellA1 = row1.createCell((int) 0);
			cellA1.setCellValue(k);

			XSSFCell cellB1 = row1.createCell((int) 1);
			cellB1.setCellValue(rs.getString("gtin_no"));

			XSSFCell cellC1 = row1.createCell((int) 2);
			cellC1.setCellValue(date);
			// cellC1.setCellStyle(unlockcellStyle);

			XSSFCell cellD1 = row1.createCell((int) 3);
			 
			noOfUnit=Integer.toString(dt.getNewsize());
		int	inside_ouside_brand=1;
			if(noOfUnit.length()==2){
				
				
				
				cellD1.setCellValue( rs.getString("gtin_no")+""+inside_ouside_brand+""+ StringUtils.leftPad(String.valueOf(rs.getString("csd_id")), 3, '0')
						+""+ date +""+ "1" +""+ StringUtils.leftPad(String.valueOf(noOfUnit), 3, '0')+""+ rs.getString("case_no")); 
				
				}
				else if(noOfUnit.length()==1)
				{
					cellD1.setCellValue( ""+rs.getString("gtin_no")+""+inside_ouside_brand+""+  StringUtils.leftPad(String.valueOf(rs.getString("csd_id")), 3, '0')
							+""+ date +""+ "1" +""+ StringUtils.leftPad(String.valueOf(noOfUnit), 3, '0')+""+ rs.getString("case_no"));
				}else{
					
					cellD1.setCellValue( rs.getString("gtin_no")+""+inside_ouside_brand+""+ StringUtils.leftPad(String.valueOf(rs.getString("csd_id")), 3, '0')
							+""+ date +""+ "1" +""+ StringUtils.leftPad(String.valueOf(noOfUnit.substring(0,3)), 3, '0')+""+ rs.getString("case_no"));
					
				}
			 

			XSSFCell cellE1 = row1.createCell((int) 4);
			
			Random rand = new Random();
			int n = 10+rand.nextInt(90) ;
		
			 cellE1.setCellValue(rs.getString("gtin_no")
					+ ""+
					 date + StringUtils.leftPad(
								String.valueOf(rs.getString("csd_id")), 3, '0')
								+ "" + "" + n + ""
					+ rs.getString("GENERATE_SERIES") + ""
					+ getCheckDigit(rs.getString("GENERATE_SERIES")));
		}
		fileOut = new FileOutputStream(relativePath + "//ExciseUp//Excel//"+dt.getRequest_id()+""
				+ dt.getGtinno() + "" + date + ".xls");

		XSSFRow row1 = worksheet.createRow((int) k + 1);

		// APoolFinancialReportDataTable dt=(APoolFinancialReportDataTable)
		// list.get(i-2);

		XSSFCell cellA1 = row1.createCell((int) 0);
		cellA1.setCellValue("End");

		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
		flag = true;

	} catch (Exception e) {

		System.out.println("xls2" + e.getMessage());
		e.printStackTrace();
	}

	return flag;
}

public static int getCheckDigit(String n)
{
	//int i=0;
	int sum=0;
	int even=0;
	int odd=0;
	int checkDigit=0;
	char ar[]= n.toCharArray();
	int k=ar.length;
	for (int i = ar.length-1; i >=0; i--)
	{
		
		
		
	   if(i%2!=0)
	   {
		  // System.out.println("evennnn"+ar[i]);
		   odd =odd+ Character.getNumericValue(ar[i]);
	   }else{
		 //  System.out.println("oddddddd"+ar[i]);
		   even=even+Character.getNumericValue(ar[i]);
	   }
	  
	}
	
	sum=(odd*3)+even;
	System.out.println("summm"+sum);
	System.out.println("even"+even);
	System.out.println("odd sum"+odd);
	if(sum%10!=0)
	{
		
		//System.out.println("SUMM    "+sum);
		    checkDigit= (10 - sum % 10);
		 //   System.out.println("checkDigit  "+checkDigit);
	}
	
	
return checkDigit;
}

public synchronized long getcaseNo() {
	String sql = " select     nextval('public.case_seq')";

	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	long serialNo = 0;
	long currseq = 0;

	try {
		conn = ConnectionToDataBase.getConnection3();

		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			serialNo = rs.getInt(1);
			if (serialNo == 0) {
				serialNo = serialNo;
			}

		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {

		try {
			if (rs != null)
				rs.close();

			if (pstmt != null)
				pstmt.close();

			if (conn != null)
				conn.close();

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}
	}

	return serialNo;
}


		

}
