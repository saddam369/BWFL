package com.mentor.utility;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.catalina.connector.RequestFacade;
import org.jboss.portal.portlet.impl.jsr168.api.ActionRequestImpl;
import org.jboss.portal.portlet.impl.jsr168.api.RenderRequestImpl;

import com.mentor.resource.ConnectionToDataBase;
 



public class NewCommomImpl {

	
	public static String serverIpAddressWithPort()
	{
		String url="";
		RenderRequestImpl requestRR=null;
		ActionRequestImpl requestAR=null;
		RequestFacade requestFacade=null;
		try{
			
			Object request1 =FacesContext.getCurrentInstance() .getExternalContext().getRequest();
			String thisIp =InetAddress.getLocalHost().getHostName();
				//System.out.println("impl method------------");
			if(request1 instanceof RenderRequestImpl)
			{
				requestRR=(RenderRequestImpl)request1;

				url="//"+requestRR.getServerName()+":"+requestRR.getServerPort()+"/doc";
			}else if(request1 instanceof ActionRequestImpl)
			{
				requestAR=(ActionRequestImpl)request1;

				url="//"+requestAR.getServerName()+":"+requestAR.getServerPort()+"/doc";
			}
			else if(request1 instanceof RequestFacade)
			{
				requestFacade=(RequestFacade)request1;
				url="//"+requestFacade.getServerName()+":"+requestFacade.getServerPort()+"/doc";
			}
			else
			{
				url="/doc";
			}



		}catch(Exception ex)
		{

			System.out.println("URL Exception");
		}

		return url;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public   ArrayList getSate() {
		
		
		ArrayList list=new ArrayList();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			
			SelectItem item=new SelectItem();
			item.setLabel("--select--");
			item.setValue("0");
			list.add(item);
			try
			{
			String query = " select int_state_id,vch_state_name  FROM  public.state_ind";


					   
			conn = ConnectionToDataBase.getConnection();
			pstmt=conn.prepareStatement(query);
			 	rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				 item=new SelectItem();
				 
				item.setValue(rs.getString(1));
				item.setLabel(rs.getString(2));
				
				
			//	System.out.println("id==111="+rs.getString(1));
				list.add(item);
								
			}
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
	      		try
	      		{	
	      			
	          		if(conn!=null)conn.close();
	          		if(pstmt!=null)pstmt.close();
	          		if(rs!=null)rs.close();
	          		
	          		
	      		}
	      		catch(Exception e)
	      		{
	      			e.printStackTrace();
	      		}
	      	}
			return list;
		}
	public ArrayList getCurrentSessionList(){
		ArrayList list=new ArrayList();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		SelectItem item=new SelectItem();
		 
		String selQr="";
		try{
		
		selQr="SELECT * FROM public.mst_season where active='a'";
		con=ConnectionToDataBase.getConnection();
		ps=con.prepareStatement(selQr);
		rs=ps.executeQuery();
		while(rs.next())
		{
			item=new SelectItem();
			item.setValue(rs.getString(1));
			item.setLabel(rs.getString(2)+"-"+rs.getString(3));
			list.add(item);
		}
		
		}catch(Exception e){e.printStackTrace();}
		
		finally{try{
			if(ps!=null)ps.close();
			if(con!=null) con.close();	

		}catch(SQLException se){
			se.printStackTrace();
		}
		}
		return list ;
	}
	public int getCurrentSessionId(){
		 
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		 
		 int id=0;
		String selQr="";
		try{
		
		selQr="SELECT * FROM public.mst_season where active='a'";
		con=ConnectionToDataBase.getConnection();
		ps=con.prepareStatement(selQr);
		rs=ps.executeQuery();
		if(rs.next())
		{
			id=rs.getInt(1);
		}
		
		}catch(Exception e){e.printStackTrace();}
		
		finally{try{
			if(ps!=null)ps.close();
			if(con!=null) con.close();	

		}catch(SQLException se){
			se.printStackTrace();
		}
		}
		return id ;
	}
	public static   String getCurrentSessionNm(int id){
		 
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String yr="";
		String selQr="";
		try{
		
		selQr="SELECT frm_yr||'-'||to_yr FROM public.mst_season where sesn_id::int="+id;
		con=ConnectionToDataBase.getConnection();
		ps=con.prepareStatement(selQr);
		rs=ps.executeQuery();
		if(rs.next())
		{
			yr=rs.getString(1);
		}
		
		}catch(Exception e){e.printStackTrace();}
		
		finally{try{
			if(ps!=null)ps.close();
			if(con!=null) con.close();	

		}catch(SQLException se){
			se.printStackTrace();
		}
		}
		return yr ;
	}
	

	public static String getEmailUser()
	{
		String user="";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try
		        {
				String query ="SELECT D.vch_email_id FROM " +
						      " COMMON_EMAIL_ACTION D  ";		

				conn = ConnectionToDataBase.getConnection();
				pstmt=conn.prepareStatement(query);
				
				rs=pstmt.executeQuery();
                while(rs.next())
				{			
					user= rs.getString(1);						
				}

		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{	
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();      		


			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		return user;
	}
	
	public static String getEmailUserPassword()
	{
		String user="";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try
		{
			
				String query ="SELECT D.vch_password FROM " +
						" COMMON_EMAIL_ACTION D  ";		

				conn = ConnectionToDataBase.getConnection();
				pstmt=conn.prepareStatement(query);
				
				rs=pstmt.executeQuery();

				while(rs.next())
				{			
					user= rs.getString(1);						
				}

			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{	
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();      		


			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		return user;
	}
	
	// ============ Send Email ====================
	
	public static void sendEmail(String to,String sub,String msg, String txtfrom, String txtpassword){  
		
		System.out.println("========================="+to);
		   
		final String from= txtfrom;
		final String password= txtpassword;
	    Properties props = new Properties();    
	    props.put("mail.smtp.host", "smtp.gmail.com");    
	    props.put("mail.smtp.socketFactory.port", "465");    
	    props.put("mail.smtp.socketFactory.class",    
	              "javax.net.ssl.SSLSocketFactory");    
	    props.put("mail.smtp.auth", "true");    
	    props.put("mail.smtp.port", "465");    
	    //get Session 
	      
	    Session session = Session.getInstance(props,new javax.mail.Authenticator() {    
	    	
	     protected javax.mail.PasswordAuthentication getPasswordAuthentication()
	     {    
	    	 
	     return new javax.mail.PasswordAuthentication(from, password);  
	     
	     }    
	    });    
	    //compose message    
	     try {   
	    	 MimeMessage message = new MimeMessage(session);    
		     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
	    
	     message.setSubject(sub);
	     message.setContent(msg,"text/html" );
	     //send message  
	     Transport.send(message);    
	     System.out.println("message sent successfully");    
	    } catch (MessagingException e) {throw new RuntimeException(e);}    
	       
	}
	
	public static String getSendSmsFrom()
	{
		String user="";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try
		{
			
				String query ="SELECT D.VCH_SMS_FROM FROM " +
						" COMMON_EMAIL_ACTION D  ";		

				conn = ConnectionToDataBase.getConnection();
				pstmt=conn.prepareStatement(query);
				
				rs=pstmt.executeQuery();

				while(rs.next())
				{			
					user= rs.getString(1);						
				}

			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{	
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();      		


			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		return user;
	}
	
	//=======================================sendSMS=========================

	public static String getSendSmsUser()
	{
		String user="";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try
		{
				String query ="SELECT D.VCH_SMS_USER FROM " +
						" COMMON_EMAIL_ACTION D  ";		

				conn = ConnectionToDataBase.getConnection();
				pstmt=conn.prepareStatement(query);
				
				rs=pstmt.executeQuery();

				while(rs.next())
				{			
					user= rs.getString(1);						
				}

		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{	
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();      		


			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		return user;
	}
	

public static String getSendSmsUserPassword()
{
	String user="";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	
	try
	{
		
			String query ="SELECT D.VCH_SMS_PWD FROM " +
					" COMMON_EMAIL_ACTION D  ";		

			conn = ConnectionToDataBase.getConnection();
			pstmt=conn.prepareStatement(query);
			
			rs=pstmt.executeQuery();

			while(rs.next())
			{			
				user= rs.getString(1);						
			}

		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{	
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();      		


		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	return user;
}
}
