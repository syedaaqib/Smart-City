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
import com.smart.util.CoreList;
import com.smartcity.struts.form.CityHistoryForm;

public class CityHistory extends DBConn{
		Connection con;
	public boolean city_info(CityHistoryForm cf ) throws SQLException, FileNotFoundException
	{
		PreparedStatement ps1=null,ps2=null;
		DBConn db=new DBConn();
		String ps_name=null;
		
		con=db.getConnection();
		

	
		String citymap=cf.getCity_image();
        System.out.println(citymap);
    	File f1=new File(citymap);
    	FileInputStream fis1=new FileInputStream(f1);
    	
    	boolean flag = false;
		try
		{
		                     
		ps1=con.prepareStatement("insert into history_city values(?,?,?,?)");
		
	      ps1.setInt(1,cf.getState_id());
	      ps1.setInt(2, cf.getCity_id());
	  	  ps1.setBinaryStream(3,fis1,(int)f1.length());
	  	  ps1.setString(4,cf.getCity_history());
	  	  
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
		return flag;

}
	
	public CoreList cityhistory_getimage(int city_id,String imagepath) throws SQLException
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
	
	

}