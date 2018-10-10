package com.smart.struts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.smart.conn.DBConn;
import com.smart.struts.form.RegisterForm;
import com.smart.util.CoreList;
import com.smart.util.DateWrapper;
import com.smart.util.LoggerManager;

public class ProfileDAO extends DBConn{
    
    static Connection con;

    private boolean flag;
    /** Creates a new instance of ProfileDAO 
     * @throws SQLException */
    public ProfileDAO() throws SQLException 
    {
           //getting Database Connection
           con=getConnection();  
    }
    

    //User Registration
    public boolean registration(RegisterForm reg)
    {
        int secretquest=reg.getSecretQuestionID();
        System.out.println("--------"+secretquest);
        String ownsecretquest=reg.getOwnSecretQuestion();
        String secretans=reg.getSecretAnswer();
            
        try 
        {
           con.setAutoCommit(false);
           PreparedStatement pst=null;
           Statement st=con.createStatement();
           int i=0;
           if(secretquest==0)
           {
                ResultSet rs=st.executeQuery("select (max(question_id))+1 from question_base");
                if(rs.next())
                    secretquest=rs.getInt(1);
                pst = con.prepareStatement("INSERT INTO question_base VALUES(?,?)");
        
                pst.setInt(1,secretquest);
                pst.setString(2,ownsecretquest);
        
                pst.executeUpdate();
           }
           String newdate=DateWrapper.parseDate(new Date());
            System.out.println("bbbbbbb"+newdate);
           pst=con.prepareStatement("insert into LOGINDETAILS values(?,?,?,?,?,?,?,?,?,?,?)");
                        
           pst.setString(1,reg.getLoginname());
           pst.setString(2,reg.getPassword());
           pst.setString(3,reg.getLoginType());
           
           System.out.println("aaaaa"+reg.getFirstName());
           System.out.println("aaaaa"+reg.getLastName());
           
           pst.setString(4,reg.getFirstName());
           pst.setString(5,reg.getLastName());
           pst.setInt(6,reg.getStatus());
           pst.setString(7,newdate);
           pst.setInt(8,secretquest);
           pst.setString(9,secretans);
           pst.setString(10,newdate);
           pst.setInt(11,1);
           i=pst.executeUpdate();
           if(i==1)
           {
                pst=con.prepareStatement("insert into LOGIN_PROFILE values(?,?,?,?,?,?)");
                pst.setString(1,reg.getLoginname());
                pst.setString(2,reg.getBirthDate());
                pst.setInt(3,reg.getCity_id());
                pst.setString(4,reg.getState());
                pst.setString(5,reg.getCountry());
                pst.setString(6,reg.getEmail());

                i=pst.executeUpdate();
            }
            if(i==1)
            {
                flag=true;
                con.commit();
            }
            else
            {
                flag=false;
                con.rollback();
            }      
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
            flag=false;
            try 
            {
                con.rollback();
            } 
            catch (SQLException sex) 
            {
                sex.printStackTrace();
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            flag=false;
            try 
            {
                con.rollback();
            } 
            catch (SQLException se) 
            {
                se.printStackTrace();
            }
        }
        return flag;
    }
    
    //Getting profile
    public RegisterForm getProfile(String loginname)
    {
        RegisterForm rb=null;
        try
        {
           Statement st = con.createStatement();
           System.out.println("login Name  : "+loginname);
           ResultSet rs = st.executeQuery("select ld.first_name,ld.last_name,lp.birthdate,lp.city,lp.email from logindetails ld,login_profile lp where ld.loginname=lp.loginid and ld.loginname='"+loginname+"'");
           if(rs.next())
           {
        	   rb=new RegisterForm();
        	   rb.setLoginname(loginname);
        	   rb.setFirstName(rs.getString(1));
        	   rb.setLastName(rs.getString(2));
        	   rb.setBirthDate(rs.getString(3));
        	   rb.setCity(rs.getString(4));
        	   rb.setEmail(rs.getString(5));
           }
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        return rb;
    } 
    
    public CoreList getProfilesOfUser()
    {
        RegisterForm rb=null;
        CoreList cl=new CoreList();
        try
        {
        	con=getConnection();
           String type="user";
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("select ld.loginname,ld.first_name,ld.last_name,lp.idno,lp.idtype,lp.city,lp.address from logindetails ld,login_profile lp where ld.loginname=lp.loginid and ld.login_status<>1 and ld.logintype='"+type+"'");
           while(rs.next())
           {
        	   rb=new RegisterForm();
        	   rb.setLoginname(rs.getString(1));
        	   rb.setFirstName(rs.getString(2));
        	   rb.setLastName(rs.getString(3));
        	   rb.setIdno(rs.getString(4));
        	   rb.setIdtype(rs.getString(5));
        	   rb.setCity(rs.getString(6));
        	   rb.setAddress(rs.getString(7));
        	   cl.add(rb);
           }
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        return cl;
    } 
    
    public boolean AcceptUser(String loginname)
    {
        RegisterForm rb=null;
        CoreList cl=null;
        boolean flag=false;
        try
        {
           PreparedStatement st = con.prepareStatement("update logindetails set login_status=1,firstlogin=1 where loginname=?");
           st.setString(1,loginname);
           int i= st.executeUpdate();
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
    // Modify Profile
    public boolean modifyProfile(RegisterForm reg)
    {
        String loginid=reg.getLoginname();
        String bdate=DateWrapper.parseDate(reg.getBirthDate());
        String city=reg.getCity();
        String state=reg.getState();
        String country=reg.getCountry();
        
        String firstname=reg.getFirstName();
        String lastname=reg.getLastName();
        
        
        try 
        {
            con.setAutoCommit(false);
            PreparedStatement pst=con.prepareStatement("UPDATE login_profile SET birthdate=?,city=?,state=?,country=?,profilemodifieddate=? WHERE loginid=?");
            PreparedStatement pst1=con.prepareStatement("UPDATE logindetails SET first_name=?,last_name=? WHERE loginname=?");
            
            pst.setString(1,bdate);
            pst.setString(2,city);
            pst.setString(3,state);
            pst.setString(4,country);
            pst.setString(5, DateWrapper.parseDate(new Date(0)));
            pst.setString(6,loginid);
           
            pst1.setString(1, firstname);
            pst1.setString(2, lastname);
            pst1.setString(3,loginid);
            
            int i=pst.executeUpdate();
            
            if(i!=0)
            {
            	i=pst1.executeUpdate();
            	if(i!=0)
            	{
                flag=true;
                con.commit();
            	}
            	else
            	{
            		flag=false;
            		con.rollback();
            	}
            }
            else
            {
                flag=false;
                con.rollback();
            }
        } 
        catch (SQLException ex) 
        {
        	ex.printStackTrace();
            LoggerManager.writeLogSevere(ex);
            flag=false;
            try 
            {
                con.rollback();
            } 
            catch (SQLException se) 
            {
                LoggerManager.writeLogSevere(se);
            }
        }
        catch (Exception e) 
        {
        	LoggerManager.writeLogSevere(e);
            flag=false;
            try 
            {
                con.rollback();
            } 
            catch (SQLException se) 
            {
            	LoggerManager.writeLogSevere(se);
            }
        }
        return flag;
    }
    
    public boolean changeAccountStatus(String loginid,int status)
    {
        try 
        {
            con.setAutoCommit(false);
            PreparedStatement pst=con.prepareStatement("UPDATE login_details SET login_status=? WHERE loginid=?");
            
            pst.setInt(1,status);
            pst.setString(2,loginid);
            int i=pst.executeUpdate();

            if(i==1)
            {
                flag=true;
                con.commit();
            }
            else
            {
                flag=false;
                con.rollback();
            }
        } 
        catch (SQLException ex) 
        {
        	LoggerManager.writeLogSevere(ex);
            flag=false;
            try 
            {
                con.rollback();
            } 
            catch (SQLException se) 
            {
            	LoggerManager.writeLogSevere(se);
            }
        }
        catch (Exception e) 
        {
        	LoggerManager.writeLogSevere(e);
            flag=false;
            try 
            {
                con.rollback();
            } 
            catch (SQLException se) 
            {
            	LoggerManager.writeLogSevere(se);
            }
        }
        return flag;
    }
    
    public ArrayList<RegisterForm> search(RegisterForm rform)
	{
		//String name = acdto.getLoginname();
		//int yearofpass = acdto.getYearofpass();
		//int yearofjoin = acdto.getYearofjoining();
		//String rollno = acdto.getRollno( 
		ArrayList<RegisterForm> al = new ArrayList<RegisterForm>();
		RegisterForm rf = null;
		
		try {
			
		   PreparedStatement pst=con.prepareStatement("select ld.first_name,ld.last_name,lp.email from logindetails ld,login_profile lp where ld.loginname=lp.loginid and upper(ld.first_name)=upper(?)");
		   pst.setString(1,rform.getFirstName());
		   System.out.println("fname:"+rform.getFirstName());
		   System.out.println("lname:"+rform.getLastName());
		   ResultSet rs = pst.executeQuery();
		   while(rs.next())
		   {
			   rf = new RegisterForm();
			   rf.setFirstName(rs.getString(1));
			   rf.setLastName(rs.getString(2));
			   rf.setEmail(rs.getString(3));
			   
			   System.out.println("fn:"+rs.getString(1));
			   System.out.println("ln:"+rs.getString(2));
			   System.out.println("em:"+rs.getString(3));
				al.add(rf);
			  
		   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
		
	}
}
    


