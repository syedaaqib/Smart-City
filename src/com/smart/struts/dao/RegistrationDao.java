package com.smart.struts.dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.smart.conn.DBConn;
import com.smart.struts.form.RegistrationForm;

public class RegistrationDao extends DBConn {
	
			
		Connection con;
		public boolean Registration(RegistrationForm rb) throws SQLException, FileNotFoundException
		{
			PreparedStatement ps1=null,ps2=null;
			DBConn db=new DBConn();
			String ps_name=null;
			
			con=db.getConnection();
			
			boolean flag = false;
			try
			{
	                                            
			ps1=con.prepareStatement("insert into registration values(?,?,?,?,?,?,?,?,?,?,?,?)");
			System.out.println("hai");
		    
			ps1.setString(1,rb.getFirst_name());
			ps1.setString(2,rb.getLast_name());
			ps1.setString(3,rb.getMiddle_name());
			ps1.setString(4,rb.getSex());
			ps1.setString(5,rb.getIlloftype());
			ps1.setString(6,rb.getLogin_id());
			ps1.setString(7,rb.getPassword());
			ps1.setString(8,rb.getConfirmpwd());
			ps1.setString(9,rb.getSecurity_question());
			ps1.setString(10,rb.getAnswer());
			ps1.setString(11,rb.getSequrity_type());
			ps1.setString(12,rb.getSecurity_id());
			
			
			
			
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
