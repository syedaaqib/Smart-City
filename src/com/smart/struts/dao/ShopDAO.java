package com.smart.struts.dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.smart.conn.DBConn;
import com.smart.struts.form.BusRouteForm;
import com.smart.struts.form.ShopsForm;
import com.smart.util.CoreList;

public class ShopDAO extends DBConn{
	Connection con;
	public boolean addShopDetails(ShopsForm ab) throws SQLException, FileNotFoundException
	{
		PreparedStatement ps1=null,ps2=null;
		DBConn db=new DBConn();
		String ps_name=null;
		
		con=db.getConnection();
		
		boolean flag = false;
		try
		{
                                            
		ps1=con.prepareStatement("insert into shops values((select nvl(max(shopid),0)+1 from shops),?,?,?,?,?,?,?)");
		System.out.println("hai");
	
		ps1.setString(1,ab.getShopname());
		ps1.setString(2,ab.getAddress());;
		ps1.setString(3,ab.getFamousfor());
		ps1.setString(4,ab.getOffers());
		ps1.setString(5,ab.getPhone());
		ps1.setInt(6,ab.getState_id());
		ps1.setInt(7,ab.getCityid());
		
			
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
	
	public CoreList getShops(int city) throws SQLException
	{
		CoreList cl=new CoreList();
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		try
		{
         
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select ct.city_name,s.* from city_info ct,shops s where ct.city_id=s.cityid and s.cityid="+city+"");
		
		while(rs.next())
		{
			ShopsForm ad=new ShopsForm();
			ad.setCityname(rs.getString(1));
			ad.setShopid(rs.getInt(2));
			ad.setCityid(rs.getInt(3));
			ad.setShopname(rs.getString(4));
			ad.setAddress(rs.getString(5));
			ad.setFamousfor(rs.getString(6));
			ad.setOffers(rs.getString(7));
			ad.setPhone(rs.getString(8));
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
	
	public boolean deleteShop(int sid) throws SQLException
	{
		PreparedStatement ps=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		try
		{
         
		ps=con.prepareStatement("delete shops where shopid=?");
		ps.setInt(1,sid);
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
	
}
