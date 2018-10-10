package com.smart.struts.dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.smart.conn.DBConn;
import com.smart.struts.form.CityTourInfoForm;
import com.smart.util.CoreList;

public class CityTourInfoDao extends DBConn{
		Connection con;
	public boolean citytourinfo_insert(CityTourInfoForm ab) throws SQLException, FileNotFoundException
	{
		PreparedStatement ps1=null,ps2=null;
		DBConn db=new DBConn();
		String ps_name=null;
		
		con=db.getConnection();

		boolean flag = false;
		try
		{
                                            
		ps1=con.prepareStatement("insert into city_tourinfo values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		System.out.println("hai");
	       	
		ps1.setInt(1,ab.getState_id());
		ps1.setString(2,ab.getZone());
		ps1.setString(3,ab.getLatitude());
		ps1.setString(4,ab.getLogitude());
		ps1.setString(5,ab.getArea());
		ps1.setString(6,ab.getClimate());
		ps1.setString(7,ab.getMax_temp());
		ps1.setString(8,ab.getMin_tem());
		ps1.setString(9,ab.getAvg_rainfall());
		ps1.setString(10,ab.getPopulation());
		ps1.setString(11,ab.getLanguages());
		ps1.setString(12,ab.getBest_timevisit());
		ps1.setString(13,ab.getClothing());
		ps1.setInt(14,ab.getCity_id());
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
	
	public CoreList citytour_view(String city_id) throws SQLException
	{
		CoreList cl=new CoreList();
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		try
		{
         System.out.println(city_id);
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select location,latitude,logitude,area,climate,max_temp,min_tem,avg_rainfall,population,languages,best_timevisit,clothing from city_tourinfo where city_id='"+city_id+"'");
		
		while(rs.next())
		{
			  System.out.println("Iam executed");
			CityTourInfoForm ef=new CityTourInfoForm();
			ef.setZone(rs.getString(1));
			ef.setLatitude(rs.getString(2));
			ef.setLogitude(rs.getString(3));
			ef.setArea(rs.getString(4));
			ef.setClimate(rs.getString(5));
			ef.setMax_temp(rs.getString(6));
			ef.setMin_tem(rs.getString(7));
			ef.setAvg_rainfall(rs.getString(8));
			ef.setPopulation(rs.getString(9));
			ef.setLanguages(rs.getString(10));
			ef.setBest_timevisit(rs.getString(10));
			ef.setClothing(rs.getString(12));
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
