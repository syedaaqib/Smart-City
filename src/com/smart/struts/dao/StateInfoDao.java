package com.smart.struts.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.smart.conn.DBConn;
import com.smart.struts.form.AddStateInfoForm;
import com.smart.util.CoreList;
import com.smartcity.struts.form.StateForm;

public class StateInfoDao extends DBConn{
		Connection con;
	public boolean state_info(StateForm ab) throws SQLException, FileNotFoundException
	{
		PreparedStatement ps1=null,ps2=null;
		DBConn db=new DBConn();
		String ps_name=null;
		
		con=db.getConnection();
		
		
	
		String statemap=ab.getSitemap();

    	File f1=new File(statemap);
    	FileInputStream fis1=new FileInputStream(f1);
    	
	
		boolean flag = false;
		try
		{
                                            
		ps1=con.prepareStatement("insert into state_info values((select nvl(max(state_id),0)+1 from state_info),?,?,?,?,?,?)");
		System.out.println("hai");
		ps1.setString(1,ab.getState_name());
		ps1.setString(2,ab.getCapital());
		ps1.setString(3,ab.getCm());;
		ps1.setString(4,ab.getGovernor());
		ps1.setBinaryStream(5,fis1,(int)f1.length());
		ps1.setString(6,ab.getLanguages());
		
			
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
	
	
	public CoreList state_get() throws SQLException
	{
		CoreList cl=new CoreList();
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		try
		{
         
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select state_name,state_id from state_info");
		
		while(rs.next())
		{
			AddStateInfoForm ad=new AddStateInfoForm();     
			ad.setState_name(rs.getString(1));
			ad.setState_id(rs.getInt(2));
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
	
	public boolean addMinisters(AddStateInfoForm af) throws SQLException
	{
		PreparedStatement ps=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		try
		{
         
		ps=con.prepareStatement("insert into ministers values(?,?,?)");
		ps.setInt(1,af.getState_id());
		ps.setString(2,af.getMinistry());
		ps.setString(3,af.getMinister());
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

	public CoreList getMinisters(int sid) throws SQLException
	{
		CoreList cl=new CoreList();
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		try
		{
         
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select st.state_name,m.* from state_info st,ministers m where st.state_id=m.state_id and st.state_id="+sid+"");
		
		while(rs.next())
		{
			AddStateInfoForm ad=new AddStateInfoForm();
			ad.setState_name(rs.getString(1));
			ad.setState_id(rs.getInt(2));
			ad.setMinistry(rs.getString(3));
			ad.setMinister(rs.getString(4));
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

	public boolean deleteMinister(String ministry) throws SQLException
	{
		PreparedStatement ps=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		try
		{
         
		ps=con.prepareStatement("delete ministers where ministry=?");
		ps.setString(1,ministry);
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
	
	public CoreList state_gets() throws SQLException
	{
		CoreList cl=new CoreList();
		Statement stmt=null;
		DBConn db=new DBConn();
		con=db.getConnection();
		boolean flag = false;
		try
		{
         
		stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select state_id,state_name,state_capital from state_info");
		
		while(rs.next())
		{
			AddStateInfoForm ad=new AddStateInfoForm();     
			ad.setState_id(rs.getInt(1));
			ad.setState_name(rs.getString(2));
			ad.setCapital(rs.getString(3));
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
	
}
