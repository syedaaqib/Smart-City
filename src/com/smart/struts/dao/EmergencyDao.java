package com.smart.struts.dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.smart.conn.DBConn;
import com.smart.struts.form.EmergencyInfoForm;
import com.smart.struts.form.HotelInfoForm;
import com.smart.util.CoreList;

public class EmergencyDao extends DBConn {
	
	Connection con;
	public boolean emergency_insert(EmergencyInfoForm ab) throws SQLException, FileNotFoundException
	{
		PreparedStatement ps1=null,ps2=null;
		DBConn db=new DBConn();
		String ps_name=null;
		
		con=db.getConnection();
		
		boolean flag = false;
		try
		{
                                            
		ps1=con.prepareStatement("insert into emergency_info values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		System.out.println("hai");
	       	
		ps1.setInt(1,ab.getState_id());
		System.out.println(ab.getState_id());
		ps1.setInt(2,ab.getCity_id());
		ps1.setString(3,ab.getPolice_number());
		ps1.setString(4,ab.getFire_number());
		ps1.setString(5,ab.getAput_number());
		ps1.setString(6,ab.getDistcollec_number());
		ps1.setString(7,ab.getHospital_name());
		ps1.setString(8,ab.getHaddress());
		ps1.setString(9,ab.getHphone_number());
		ps1.setString(10,ab.getBbank_name());
		ps1.setString(11,ab.getBbaddress());
		ps1.setString(12,ab.getBbphone_number());
		ps1.setString(13,ab.getEnq_name());
		ps1.setString(14,ab.getEnq_number());
		
		
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
	
	
	public CoreList emergency_view(String city_id) throws SQLException
	{
		CoreList cl=new CoreList();
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		try
		{
         
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select police_number,fire_nubmer,aput_number,distcollec_number,hospital_name,haddress,hphone_number,bbank_name,bbaddress,bbphone_number,enq_name,enq_number from emergency_info where city_id='"+city_id+"'");
		
		while(rs.next())
		{
			EmergencyInfoForm ef=new EmergencyInfoForm();
			ef.setPolice_number(rs.getString(1));
			ef.setFire_number(rs.getString(2));
			ef.setAput_number(rs.getString(3));
			ef.setDistcollec_number(rs.getString(4));
			ef.setHospital_name(rs.getString(5));
			ef.setHaddress(rs.getString(6));
			ef.setHphone_number(rs.getString(7));
			ef.setBbank_name(rs.getString(8));
			ef.setBbaddress(rs.getString(9));
			ef.setBbphone_number(rs.getString(10));
			ef.setEnq_name(rs.getString(11));
			ef.setEnq_number(rs.getString(12));
			cl.add(ef);			
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

	

}
