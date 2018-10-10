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
import com.smart.struts.form.AddStateInfoForm;
import com.smart.struts.form.AlertForm;
import com.smart.struts.form.BusRouteForm;
import com.smart.struts.form.PlacesForm;
import com.smart.struts.form.TheatreForm;
import com.smart.util.CoreList;
import com.smartcity.struts.form.StateForm;

public class CityInfoDao extends DBConn{
		Connection con;
	
		
		
		public boolean city_info(AddCityInfoForm ab) throws SQLException, FileNotFoundException
	     {
		PreparedStatement ps1=null,ps2=null;
		DBConn db=new DBConn();
		String ps_name=null;
		
		con=db.getConnection();
		
		
	
		String citymap=ab.getCity_map();

    	File f1=new File(citymap);
    	FileInputStream fis1=new FileInputStream(f1);
	
		boolean flag = false;
		try
		{
                                            
		ps1=con.prepareStatement("insert into city_info values(?,?,?,(select nvl(max(city_id),0)+1 from city_info),?,?,?,?,?,?,?)");
		System.out.println("hai");
		ps1.setInt(1,ab.getState_id());
		ps1.setString(2,ab.getZone());
		ps1.setString(3,ab.getCity_name());;
		ps1.setBinaryStream(4,fis1,(int)f1.length());
		ps1.setString(5,ab.getHistory());
		ps1.setString(6,ab.getMayor());
		ps1.setString(7,ab.getOffaddr());
		ps1.setString(8,ab.getPhone());
		ps1.setString(9,ab.getNewspaper());
		ps1.setString(10,ab.getTvchannels());
			
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
	
	
	public CoreList city_get(String state_id) throws SQLException
	{
		CoreList cl=new CoreList();
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		try
		{
         
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select city_id,city_name,zone_india from city_info where state_id="+state_id+"");		
		while(rs.next())
		{
			AddCityInfoForm ad=new AddCityInfoForm(); 
			ad.setCity_id(rs.getInt(1));
			ad.setCity_name(rs.getString(2));
			ad.setZone(rs.getString(3));
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
	
	public CoreList getCities() throws SQLException
	{
		CoreList cl=new CoreList();
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		try
		{
         
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select city_id,city_name,zone_india from city_info");
		
		while(rs.next())
		{
			AddCityInfoForm ad=new AddCityInfoForm(); 
			ad.setCity_id(rs.getInt(1));
			ad.setCity_name(rs.getString(2));
			ad.setZone(rs.getString(3));
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
	
	public boolean addAlert(AlertForm af) throws SQLException
	{
		PreparedStatement ps1=null;
		DBConn db=new DBConn();
		
		con=db.getConnection();
		boolean flag = false;
		try
		{
                                            
		ps1=con.prepareStatement("insert into ALERTS values(?,?,?)");
		ps1.setInt(1,af.getCity_id());
		ps1.setString(2,af.getAlerttype());
		ps1.setString(3,af.getAlert());
		
		int i=ps1.executeUpdate();
		
		if(i>0)
		{
			flag=true;
			con.commit();
     	    flag=new MessageDAO().SendMailAlert(af);
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
	
	public boolean subscribeAlerts(AlertForm af) throws SQLException
	{
		PreparedStatement ps1=null;
		DBConn db=new DBConn();
		
		con=db.getConnection();
		boolean flag = false;
		try
		{                                  
		ps1=con.prepareStatement("insert into subscription values((select nvl(max(sid),0)+1 from subscription),?,?,?,?)");
		ps1.setString(1,af.getUsername());
		ps1.setString(2,af.getEmail());
		ps1.setInt(3,af.getCity_id());
		ps1.setString(4,af.getAlerttype());
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
	
	public boolean unsubscribeAlerts(AlertForm af) throws SQLException
	{
		PreparedStatement ps1=null;
		DBConn db=new DBConn();
		
		con=db.getConnection();
		boolean flag = false;
		try
		{
                                            
		ps1=con.prepareStatement("delete from subscription where username='"+af.getUsername()+"' and alerttype='"+af.getAlerttype()+"'");
		
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
	
	public boolean addBusRoute(BusRouteForm br) throws SQLException
	{
		PreparedStatement ps1=null;
		DBConn db=new DBConn();
		
		con=db.getConnection();
		boolean flag = false;
		try
		{
                                            
		ps1=con.prepareStatement("insert into busroutes values(?,?,?,?,?)");
		ps1.setInt(1,br.getCityid());
		ps1.setString(2,br.getBusno());
		ps1.setString(3,br.getOrigin());
		ps1.setString(4,br.getVia());
		ps1.setString(5,br.getDestination());

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
	
	public CoreList getBusRoutes(int city) throws SQLException
	{
		CoreList cl=new CoreList();
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		try
		{
         
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select ct.city_name,b.* from city_info ct,busroutes b where ct.city_id=b.cityid and cityid="+city+"");
		
		while(rs.next())
		{
			BusRouteForm ad=new BusRouteForm();
			ad.setCityname(rs.getString(1));
			ad.setCityid(rs.getInt(2));
			ad.setBusno(rs.getString(3));
			ad.setOrigin(rs.getString(4));
			ad.setVia(rs.getString(5));
			ad.setDestination(rs.getString(6));
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
	
	public boolean deleteBusRoute(String busno) throws SQLException
	{
		PreparedStatement ps=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		try
		{
         
		ps=con.prepareStatement("delete busroutes where busno=?");
		ps.setString(1,busno);
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
	
	public boolean addPlaceToSee(PlacesForm pf) throws SQLException, FileNotFoundException
	{
		PreparedStatement ps1=null;
		DBConn db=new DBConn();
		
		con=db.getConnection();
		boolean flag = false;
		String placemap=pf.getImg();

    	File f1=new File(placemap);
    	FileInputStream fis1=new FileInputStream(f1);
		try
		{
                                            
		ps1=con.prepareStatement("insert into places values(?,(select nvl(max(placeid),0)+1 from places),?,?,?,?,?)");
		ps1.setInt(1,pf.getCityid());
		ps1.setString(2,pf.getPlace());
		ps1.setString(3,pf.getLocation());
		ps1.setString(4,pf.getHistory());
		ps1.setBinaryStream(5,fis1,(int)f1.length());
        ps1.setString(6,pf.getPhone());
		
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
	
	public CoreList getPlaces(int city,String imagepath) throws SQLException
	{
		CoreList cl=new CoreList();
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		System.out.println("sssss"+imagepath);
		try
		{
         
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select ct.city_name,p.* from city_info ct,places p where ct.city_id=p.cityid and p.cityid="+city+"");
		
		while(rs.next())
		{
			PlacesForm ad=new PlacesForm();
			String pl=rs.getString(4);
			ad.setCityname(rs.getString(1));
			ad.setCityid(rs.getInt(2));
			ad.setPlaceid(rs.getInt(3));
			ad.setPlace(rs.getString(4));
			ad.setLocation(rs.getString(5));
			ad.setHistory(rs.getString(6));
			Blob bb =rs.getBlob(7);
			byte bb1[]=bb.getBytes(1,(int)bb.length());
			OutputStream fout1=new FileOutputStream(imagepath+"/"+pl+".jpg");
			fout1.write(bb1);
			ad.setImg((pl+".jpg"));
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
	
	   
	public CoreList city_get3(int state_id) throws SQLException
	{
		CoreList cl=new CoreList();
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		try
		{
         
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select city_id,city_name,zone_india from city_info where state_id="+state_id+"");
		
		while(rs.next())
		{
			AddCityInfoForm ad=new AddCityInfoForm(); 
			ad.setCity_id(rs.getInt(1));
			ad.setCity_name(rs.getString(2));
			ad.setZone(rs.getString(3));
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
	
	public CoreList upcity_gets(int state_id) throws SQLException
	{
		CoreList cl=new CoreList();
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		try
		{
         
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select city_name,zone_india,city_id,newspaper,tvchannels from city_info where state_id="+state_id+"");
		
		while(rs.next())
		{
			AddCityInfoForm ad=new AddCityInfoForm();     
			ad.setCity_name(rs.getString(1));
			ad.setZone(rs.getString(2));
			ad.setCity_id(rs.getInt(3));
			
			ad.setNewspaper(rs.getString(4));
			ad.setTvchannels(rs.getString(5));
			
			System.out.println(rs.getString(1));
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
	
	
	public CoreList upcity_gets1(int city_id) throws SQLException
	{
		System.out.println("city id in dao====================="+city_id);
		CoreList cl=new CoreList();
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		try
		{
         
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select zone_india,city_name,history,mayor,officeaddr,phone,newspaper,tvchannels from city_info where city_id="+city_id+"");
		
		while(rs.next())
		{
			AddCityInfoForm ad=new AddCityInfoForm();     
			ad.setZone(rs.getString(1));
			ad.setCity_name(rs.getString(2));
			ad.setHistory(rs.getString(3));
			ad.setMayor(rs.getString(4));
			ad.setOffaddr(rs.getString(5));
			ad.setPhone(rs.getString(6));
			ad.setNewspaper(rs.getString(7));
			ad.setTvchannels(rs.getString(8));
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
	
	
	public CoreList upstate_gets1(int stateid) throws SQLException
	{
		System.out.println("stateid id in dao====================="+stateid);
		CoreList cl=new CoreList();
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		try
		{
         
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select STATE_NAME,CM,GOVERNOR,LANGUAGES from STATE_INFO where STATE_ID="+stateid+"");
		
		while(rs.next())
		{
			AddStateInfoForm ad=new AddStateInfoForm();     
			ad.setState_name(rs.getString(1));
			ad.setCm(rs.getString(2));
			ad.setGovernor(rs.getString(3));
			ad.setLanguages(rs.getString(4));
			
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean updatecity_info(AddCityInfoForm ad) throws SQLException, FileNotFoundException
	{
		PreparedStatement ps1=null,ps2=null;
		DBConn db=new DBConn();
		String ps_name=null;
		
		con=db.getConnection();

		
       
    	
        int city_id=ad.getCity_id();

		boolean flag = false;
		try
		{
		System.out.println("sadsdd");                         
		ps1=con.prepareStatement("update city_info set zone_india=?,CITY_NAME=?,mayor=?,officeaddr=?,phone=?,newspaper=?,tvchannels=? where city_id="+city_id+"");
		System.out.println("hai");
		
		ps1.setString(1,ad.getZone());
		ps1.setString(2,ad.getCity_name());
		
		ps1.setString(3,ad.getMayor());
		ps1.setString(4,ad.getOffaddr());
		ps1.setString(5,ad.getPhone());
		ps1.setString(6,ad.getNewspaper());
		ps1.setString(7,ad.getTvchannels());
		
		
			
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
	
	
	public boolean updateState_info(AddStateInfoForm ad) throws SQLException, FileNotFoundException
	{
		PreparedStatement ps1=null,ps2=null;
		DBConn db=new DBConn();
		String ps_name=null;
		
		con=db.getConnection();

		
       
		 System.out.println("cmmmmmmmmm====="+ad.getCm());
        int ststeid=ad.getState_id();
        System.out.println("state id====="+ststeid);

		boolean flag = false;
		try
		{
		System.out.println("sadsdd");                         
		ps1=con.prepareStatement("update STATE_INFO set CM=?,GOVERNOR=?,LANGUAGES=? where STATE_ID="+ststeid+"");
		System.out.println("hai");
		
		ps1.setString(1,ad.getCm());
		ps1.setString(2,ad.getGovernor());
		
		ps1.setString(3,ad.getLanguages());
		
		
		
			
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
	
	
	
	
	public CoreList city_getimage(int city_id,String imagepath) throws SQLException
	{
		CoreList cl=new CoreList();
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		try
		{
         
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select city_id,citymap,city_name,history from city_info where city_id='"+city_id+"'");
		
		while(rs.next())
		{
			
			AddCityInfoForm af=new AddCityInfoForm();
			int city_id1=rs.getInt(1);
			af.setCity_id(rs.getInt(1));
			af.setCity_name(rs.getString(3));
			af.setHistory(rs.getString(4));
			Blob bb =rs.getBlob(2);
			byte bb1[]=bb.getBytes(1,(int)bb.length());
			OutputStream fout1=new FileOutputStream(imagepath+"/"+city_id1+".gif");
			fout1.write(bb1);
			af.setCity_map((city_id1+".gif"));
			System.out.println(bb1);
			cl.add(af);
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
	
	public CoreList getTheatre(int tid) throws SQLException
	{
		CoreList cl=new CoreList();
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		try
		{
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from THEATRES where theatreid="+tid+"");

		while(rs.next())
		{
			int thid=rs.getInt(1);
			TheatreForm td=new TheatreForm();
			td.setTheatreid(rs.getInt(1));
			td.setCityid(rs.getInt(2));
			td.setTheatrename(rs.getString(3));
			td.setCinema(rs.getString(4));
			/*Blob bb =rs.getBlob(5);
			byte bb1[]=bb.getBytes(1,(int)bb.length());
			OutputStream fout1=new FileOutputStream(imagepath+"/"+thid+".jpg");
			fout1.write(bb1);
			td.setCinemaimg((thid+".jpg"));
			//td.setCinemaimg(rs.getString(5));*/
			td.setMshotime(rs.getString(6));
			td.setMattime(rs.getString(7));
			td.setFirshowtime(rs.getString(8));
			td.setSecshowtime(rs.getString(9));
			td.setAddress(rs.getString(10));
			td.setPhone(rs.getString(11));
			cl.add(td);
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
