package com.smart.struts.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.smart.conn.DBConn;
import com.smart.struts.form.AddCityInfoForm;
import com.smart.struts.form.BusRouteForm;
import com.smart.struts.form.PlacesForm;
import com.smart.struts.form.TheatreForm;
import com.smart.util.CoreList;

public class TheatresDao extends DBConn{
		Connection con;
	
	public boolean addTheatres(TheatreForm tf) throws SQLException, FileNotFoundException
	{
		PreparedStatement ps1=null;
		DBConn db=new DBConn();
		
		con=db.getConnection();
		boolean flag = false;
		String img=tf.getCinemaimg();

    	File f1=new File(img);
    	FileInputStream fis1=new FileInputStream(f1);
		try
		{
                                            
		ps1=con.prepareStatement("insert into THEATRES values((select nvl(max(theatreid),0)+1 from THEATRES),?,?,?,?,?,?,?,?,?,?)");
		ps1.setInt(1,tf.getCityid());
		ps1.setString(2,tf.getTheatrename());
		ps1.setString(3,tf.getCinema());
		ps1.setBinaryStream(4,fis1,(int)f1.length());
		ps1.setString(5,tf.getMshotime());
        ps1.setString(6,tf.getMattime());
        ps1.setString(7,tf.getFirshowtime());
        ps1.setString(8,tf.getSecshowtime());
        ps1.setString(9,tf.getAddress());
        ps1.setString(10,tf.getPhone());
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
	
	public CoreList getTheatres(int city,String imagepath) throws SQLException
	{
		CoreList cl=new CoreList();
		cl.clear();
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		System.out.println("sssss"+imagepath);
		try
		{
         
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select ct.city_name,t.* from city_info ct,theatres t where ct.city_id=t.cityid and t.cityid="+city+"");
		
		while(rs.next())
		{
			TheatreForm ad=new TheatreForm();
			String pl=ad.getCinema();
			ad.setCityname(rs.getString(1));
			ad.setTheatreid(rs.getInt(2));
			ad.setCityid(rs.getInt(3));
			ad.setTheatrename(rs.getString(4));
			ad.setCinema(rs.getString(5));
			Blob bb =rs.getBlob(6);
			byte bb1[]=bb.getBytes(1,(int)bb.length());
			OutputStream fout1=new FileOutputStream(imagepath+"/"+pl+".jpg");
			fout1.write(bb1);
			ad.setCinemaimg((pl+".jpg"));
			ad.setMshotime(rs.getString(7));
			ad.setMattime(rs.getString(8));
			ad.setFirshowtime(rs.getString(9));
			ad.setSecshowtime(rs.getString(10));
			ad.setAddress(rs.getString(11));
			ad.setPhone(rs.getString(12));
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
	
	public boolean deleteTheatre(int tid) throws SQLException
	{
		PreparedStatement ps=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		try
		{
         
		ps=con.prepareStatement("delete theatres where theatreid=?");
		ps.setInt(1,tid);
		int i=ps.executeUpdate();
		if(i>0)
		{
			flag=true;
			con.commit();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			con.close();
		}
		finally
		{
			releaseResources(con,ps);
		}
		return flag;
	}
	
	public CoreList getTheatre(int tid) throws SQLException
	{
		CoreList cl=new CoreList();
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		try
		{
         
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from theatres where theatreid="+tid+"");
		
		while(rs.next())
		{
			TheatreForm ad=new TheatreForm();
			ad.setTheatreid(rs.getInt(1));
			ad.setCityid(rs.getInt(2));
			ad.setTheatrename(rs.getString(3));
			ad.setCinema(rs.getString(4));
			ad.setMshotime(rs.getString(5));
			ad.setMattime(rs.getString(6));
			ad.setFirshowtime(rs.getString(7));
			ad.setSecshowtime(rs.getString(8));
			ad.setAddress(rs.getString(9));
			ad.setPhone(rs.getString(10));
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
	
	public boolean UpdateTheatres(TheatreForm tf) throws SQLException, FileNotFoundException
	{
		PreparedStatement ps1=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		String citymap=tf.getCinemaimg();
    	File f1=new File(citymap);
    	FileInputStream fis1=new FileInputStream(f1);
		try
		{
                                            
		ps1=con.prepareStatement("update THEATRES set theatrename=?,cinema=?,cinemaimg=?,morshowtime=?,matshowtime=?,firshowtime=?,secshowtime=?,address=?,phone=? where theatreid=?");
		ps1.setString(1,tf.getTheatrename());
		ps1.setString(2,tf.getCinema());
		ps1.setBinaryStream(3,fis1,(int)f1.length());
		ps1.setString(4,tf.getMshotime());
        ps1.setString(5,tf.getMattime());
        ps1.setString(6,tf.getFirshowtime());
        ps1.setString(7,tf.getSecshowtime());
        ps1.setString(8,tf.getAddress());
        ps1.setString(9,tf.getPhone());
        ps1.setInt(10,tf.getTheatreid());
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
