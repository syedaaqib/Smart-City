package com.smart.struts.dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.smart.conn.DBConn;
import com.smart.struts.form.BusInfoForm;
import com.smart.struts.form.BusinessInfoForm;
import com.smart.util.CoreList;

public class BusinessInfoDao extends DBConn{
		Connection con;
	
		
		
		public boolean businessisert(BusInfoForm ab) throws SQLException, FileNotFoundException
	{
		PreparedStatement ps1=null,ps2=null;
		DBConn db=new DBConn();
		String ps_name=null;
		
		con=db.getConnection();
			
	
		boolean flag = false;
		try
		{
                                            
		ps1=con.prepareStatement("insert into business_info values(?,?,?,?,?,?,?,?,(select nvl(max(business_id),0)+1 from business_info))");
		System.out.println("hai");
		ps1.setInt(1,ab.getState_id());
		ps1.setInt(2,ab.getCity_id());
		ps1.setString(3,ab.getComp_name());
		ps1.setString(4,ab.getDescription());
		ps1.setString(5,ab.getAddress());
		ps1.setString(6,ab.getPhone());
		ps1.setString(7,ab.getEmail());
		ps1.setString(8,ab.getCategory());
		
		int i=ps1.executeUpdate();
		
		if(i>0)
		{
			flag=true;
			con.commit();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseResources(con,ps1);
		}
		return flag;
	}
	
	
	public CoreList viewbusiness() throws SQLException
	{
		CoreList cl=new CoreList();
		String state_id="2";
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		try
		{
         
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select state_id,city_id,comp_name,category,business_id from business_info where state_id='"+state_id+"'");
		
		while(rs.next())
		{
			BusinessInfoForm ad=new BusinessInfoForm();
			ad.setState_id(rs.getInt(1));
			ad.setCity_id(rs.getInt(2));
			ad.setComp_name(rs.getString(3));
			ad.setCategory(rs.getString(4));
			ad.setBusiness_id(rs.getInt(5));
			cl.add(ad);
		}  
		}
		catch(Exception e)
		{
			e.printStackTrace();
			con.close();
		}
		finally
		{
			releaseResources1(con,stmt);
		}
		return cl;
	}
	
	
	public CoreList viewbusiness1(int city_id) throws SQLException
	{
		CoreList cl=new CoreList();
		
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		try
		{
         
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select comp_name,description,address,phone,email,category,business_id from business_info where city_id='"+city_id+"'");
		
		while(rs.next())
		{
			BusInfoForm ad=new BusInfoForm();
			ad.setComp_name(rs.getString(1));
			ad.setDescription(rs.getString(2));
			ad.setAddress(rs.getString(3));
			ad.setPhone(rs.getString(4));
			ad.setEmail(rs.getString(5));
			ad.setCategory(rs.getString(6));
			ad.setBusiness_id(rs.getInt(7));
			cl.add(ad);
		}  
		}
		catch(Exception e)
		{
			e.printStackTrace();
			con.close();
		}
		finally
		{
			releaseResources1(con,stmt);
		}
		return cl;
	}
	
	
	public boolean businessupdate(BusinessInfoForm ab) throws SQLException, FileNotFoundException
	{
		PreparedStatement ps1=null,ps2=null;
		DBConn db=new DBConn();
		String ps_name=null;
		
		con=db.getConnection();
			
	    int business_id=ab.getBusiness_id();
		boolean flag = false;
		try
		{
                                            
		ps1=con.prepareStatement("update business_info set comp_name=?,description=?,address=?,phone=?,email=?,category=? where business_id="+business_id+"");
		System.out.println("hai");
	    ps1.setString(1,ab.getComp_name());
	    ps1.setString(2,ab.getDescription());
	    ps1.setString(3,ab.getAddress());
	    ps1.setString(4,ab.getPhone());
	    ps1.setString(5,ab.getEmail());
	    ps1.setString(6,ab.getCategory());
		
		int i=ps1.executeUpdate();
		
		if(i>0)
		{
			flag=true;
			con.commit();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseResources(con,ps1);
		}
		return flag;
	}

	
}
