package com.smart.struts.dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.smart.conn.DBConn;

import com.smart.struts.form.JobsInfoForm;
import com.smart.struts.form.JobsInfosForm;
import com.smart.util.CoreList;

public class JobsDao extends DBConn{
		Connection con;
	public boolean jobs_info(JobsInfosForm ab) throws SQLException, FileNotFoundException
	{
		PreparedStatement ps1=null,ps2=null;
		DBConn db=new DBConn();
		String ps_name=null;
		
		con=db.getConnection();
			
	
		boolean flag = false;
		try
		{
                                           
			ps1=con.prepareStatement("insert into cities_jobs values(?,?,?,?,?,?,?)");
		
		ps1.setInt(1,ab.getState_id());
		ps1.setInt(2,ab.getCity_id());
		ps1.setString(3,ab.getComp_name());
		ps1.setString(4,ab.getComp_address());
		ps1.setString(5,ab.getComp_phno());
		ps1.setString(6,ab.getJob_name());
		ps1.setString(7,ab.getQualification());
		System.out.println("hai");
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
	
	public CoreList jobs_view(String city_id) throws SQLException
	{
		CoreList cl=new CoreList();
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		try
		{
         
		stmt=con.createStatement();
		System.out.println(city_id);
	System.out.println("hai");
		ResultSet rs=stmt.executeQuery("select comp_name,comp_address,comp_phno,job_name,qulification from cities_jobs where city_id='"+city_id+"'");
		
		while(rs.next())
		{
			JobsInfoForm jf=new JobsInfoForm();
			jf.setComp_name(rs.getString(1));
			jf.setComp_address(rs.getString(2));
			jf.setComp_phno(rs.getString(3));
		    jf.setJob_name(rs.getString(4));
			jf.setQualification(rs.getString(5));
			
			cl.add(jf);			
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


