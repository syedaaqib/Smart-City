package com.smart.struts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.smart.conn.DBConn;
import com.smart.struts.form.AlertForm;
import com.smart.struts.form.MessageForm;
import com.smart.util.DateWrapper;
import com.smart.util.LoggerManager;

public class MessageDAO extends DBConn{
	
	Connection con;
	public MessageDAO() throws SQLException
	{
		con=getConnection();
	}
 
	//send message
	
	public boolean sendMessage(MessageForm msgdto)
	{
		boolean flag = false;
	    try
	    {
	    	Statement st = con.createStatement();
	    	ResultSet rs = st.executeQuery("select max(messageid) from mails");
	    	int messageid=0;
	    	if(rs.next())
	    	     messageid = rs.getInt(1);
	        messageid++;
	        String tdate=DateWrapper.parseDate(new Date());
	    	PreparedStatement pst = con.prepareStatement("insert into mails values(?,?,?,?,?,?,1,1)");
	    	
	    	pst.setInt(1, messageid);
	    	pst.setString(2, msgdto.getFrom());
	    	pst.setString(3, msgdto.getTo());
	    	pst.setString(4, tdate);
	    	pst.setString(5, msgdto.getSubject());
	    	pst.setString(6, msgdto.getMessage());
	    	
	    	int i = pst.executeUpdate();
	    	
	    	if(i!=0)
	    	   flag = true;
	    	else
	    		flag= false;
	    }
	    catch(SQLException sqx)
	    {
	    	flag = false;
	    	sqx.printStackTrace();
	         
	    	LoggerManager.writeLogSevere(sqx);
	    }
	    catch(Exception e)
	    {
	    	flag = false;
	    	e.printStackTrace();
	         
	    	LoggerManager.writeLogSevere(e);
	    }
	    return flag;
	}
	
	public boolean SendMailAlert(AlertForm af) throws SQLException
	{
		boolean flag = false;
		ResultSet rs2=null;
		con=getConnection();
	    try
	    {
	    	Statement st = con.createStatement();
	    	ResultSet rs = st.executeQuery("select max(messageid) from mails");
	    	int messageid=0;
	    	if(rs.next())
	    	     messageid = rs.getInt(1);
	        messageid++;
	        String tdate=DateWrapper.parseDate(new Date());
	        
	        Statement st1 = con.createStatement();
	        System.out.println("------"+af.getAlerttype());
	    	ResultSet rs1 = st1.executeQuery("select * from subscription where alerttype='"+af.getAlerttype()+"'");
	        
	    	while(rs1.next())
	    	{	
	    		String uname=rs1.getString(2);
	    		String email=rs1.getString(3);
	    		int cityid=rs1.getInt(4);
	    		String alerttype=rs1.getString(5);
	    	
	       	PreparedStatement pst = con.prepareStatement("insert into mails values(?,?,?,?,?,?,1,1)");
	    	
	    	pst.setInt(1, messageid);
	    	pst.setString(2,"SMART CITY");
	    	pst.setString(3, email);
	    	pst.setString(4, tdate);
	    	pst.setString(5, "Mail Alert");
	    	pst.setString(6, af.getAlert());
	    	
	    	int i = pst.executeUpdate();
	    	
	    	if(i!=0)
	    	   flag = true;
	    	else
	    		flag= false;
	    	}
	    }
	    catch(SQLException sqx)
	    {
	    	flag = false;
	    	sqx.printStackTrace();
	         
	    	LoggerManager.writeLogSevere(sqx);
	    }
	    catch(Exception e)
	    {
	    	flag = false;
	    	e.printStackTrace();
	         
	    	LoggerManager.writeLogSevere(e);
	    }
	    return flag;
	}
	
	
	public boolean RejectLicenseMSG(String mail) throws SQLException
	{
		boolean flag = false;
		ResultSet rs2=null;
		con=getConnection();
	    try
	    {
	    	Statement st = con.createStatement();
	    	ResultSet rs = st.executeQuery("select max(messageid) from mails");
	    	int messageid=0;
	    	if(rs.next())
	    	     messageid = rs.getInt(1);
	        messageid++;
	        String tdate=DateWrapper.parseDate(new Date());
	       	PreparedStatement pst = con.prepareStatement("insert into mails values(?,?,?,?,?,?,1,1)");
	    	
	    	pst.setInt(1, messageid);
	    	pst.setString(2,"POLICE DEPT");
	    	pst.setString(3, mail);
	    	pst.setString(4, tdate);
	    	pst.setString(5, "License Approval");
	    	pst.setString(6, "Your Application for License was Not Approved");
	    	
	    	int i = pst.executeUpdate();
	    	
	    	if(i!=0)
	    	   flag = true;
	    	else
	    		flag= false;
	    }
	    catch(SQLException sqx)
	    {
	    	flag = false;
	    	sqx.printStackTrace();
	         
	    	LoggerManager.writeLogSevere(sqx);
	    }
	    catch(Exception e)
	    {
	    	flag = false;
	    	e.printStackTrace();
	         
	    	LoggerManager.writeLogSevere(e);
	    }
	    return flag;
	}
	
	
	
	
	//View Inbox
	
	public ArrayList<MessageForm> viewInbox(String loginname)
	{
		ArrayList<MessageForm> al = new ArrayList<MessageForm>();
		MessageForm messagedto;
		String email=null;
		try
		{
			PreparedStatement pst=con.prepareStatement("select email from login_profile where loginid=?");
			pst.setString(1,loginname);
			ResultSet rs1=pst.executeQuery();
			if(rs1.next())
				 email=rs1.getString(1);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select messageid,fromname,subject,Senddate from mails where toname='"+email+"' and Receiverstatus=1");
			
			while(rs.next())
			{
				messagedto = new MessageForm();
				messagedto.setMessageid(rs.getInt(1));
				messagedto.setFrom(rs.getString(2));
				messagedto.setSubject(rs.getString(3));
				messagedto.setSenddate1(DateWrapper.parseDate(rs.getDate(4)));
				
				al.add(messagedto);
			}
		}
		catch(SQLException sqlex)
		{
			LoggerManager.writeLogSevere(sqlex);
		}
		catch(Exception ex)
		{
			LoggerManager.writeLogSevere(ex);
		}
		return al;
	}
	
//View Outbox
	
	public ArrayList<MessageForm> viewOutbox(String loginname)
	{
		ArrayList<MessageForm> al = new ArrayList<MessageForm>();
		MessageForm messagedto;
		try
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select messageid,toname,subject,Senddate from mails where fromname='"+loginname+"' and senderstatus=1");
			
			while(rs.next())
			{
				messagedto = new MessageForm();
				messagedto.setMessageid(rs.getInt(1));
				messagedto.setTo(rs.getString(2));
				messagedto.setSubject(rs.getString(3));
				messagedto.setSenddate1(DateWrapper.parseDate(rs.getDate(4)));
				
				al.add(messagedto);
			}
		}
		catch(SQLException sqlex)
		{
			LoggerManager.writeLogSevere(sqlex);
		}
		catch(Exception ex)
		{
			LoggerManager.writeLogSevere(ex);
		}
		return al;
	}
	
//View Message
	
	public MessageForm viewMessage(int messageid)
	{
		MessageForm messagedto = new MessageForm();
		try
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select fromname,toname,senddate,subject,message from mails where messageid="+messageid);
			
			if(rs.next())
			{				
				messagedto.setFrom(rs.getString(1));
				messagedto.setTo(rs.getString(2));
				messagedto.setSenddate1(DateWrapper.parseDate(rs.getDate(3)));
				messagedto.setSubject(rs.getString(4));
				messagedto.setMessage(rs.getString(5));
			}
		}
		catch(SQLException sqlex)
		{
			LoggerManager.writeLogSevere(sqlex);
		}
		catch(Exception ex)
		{
			LoggerManager.writeLogSevere(ex);
		}
		return messagedto;
	}
	
//Change Status of message
	
	public boolean changeMessageStatus(int messageid,String field)
	{
		boolean flag = false;
		try
		{
			Statement st = con.createStatement();
			int i = st.executeUpdate("update mails set "+field+"=0 where messageid="+messageid);
			
			if(i!=0)
			{				
				flag = true;
			}
		}
		catch(SQLException sqlex)
		{
			flag = false;
			LoggerManager.writeLogSevere(sqlex);
		}
		catch(Exception ex)
		{
			flag = false;
			LoggerManager.writeLogSevere(ex);
		}
		return flag;
	}
}

