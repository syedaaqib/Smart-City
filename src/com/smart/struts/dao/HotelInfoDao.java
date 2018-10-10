package com.smart.struts.dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.smart.conn.DBConn;
import com.smart.struts.form.HotelInfosForm;

import com.smart.util.CoreList;

public class HotelInfoDao extends DBConn{
		Connection con;
	public boolean hotelinfo_insert(HotelInfosForm ab) throws SQLException, FileNotFoundException
	{
		PreparedStatement ps1=null,ps2=null;
		DBConn db=new DBConn();
		String ps_name=null;
		
		con=db.getConnection();
		
				
	
		boolean flag = false;
		System.out.println("hai");
		try
		{
                                            
		ps1=con.prepareStatement("insert into hotel_info values(?,?,?,?,?,?)");
		System.out.println("hai");
		ps1.setInt(1,ab.getState_id());
		ps1.setInt(2,ab.getCity_id());
		ps1.setString(3,ab.getHotel_name());
		ps1.setString(4,ab.getRating());
		ps1.setString(5,ab.getAddress());
		ps1.setString(6,ab.getPhone_number());
		
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
	
	public CoreList hotels(String city_id) throws SQLException
	{
		CoreList cl=new CoreList();
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		try
		{
         
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select hotel_name,rating,address,phone_number from hotel_info where city_id='"+city_id+"'");
		
		while(rs.next())
		{
			HotelInfosForm hf=new HotelInfosForm();
			hf.setHotel_name(rs.getString(1));
			hf.setRating(rs.getString(2));
			hf.setAddress(rs.getString(3));
			hf.setPhone_number(rs.getString(4));
			System.out.println(rs.getString(1));
			cl.add(hf);
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
