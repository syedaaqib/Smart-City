package com.smart.struts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.smart.conn.DBConn;
import com.smart.struts.form.RegisterForm;
import com.smart.util.DateWrapper;
import com.smart.util.LoggerManager;

public class SecurityDAO extends DBConn
{
     private Connection con;
     private String desc;
     private boolean flag;
    /** Creates a new instance of SecurityDAO 
     * @throws SQLException */
    public SecurityDAO() throws SQLException 
    {
               //getting Database Connection
               con=getConnection();
    }
    
    //Compare Password
    public boolean checkPassword(RegisterForm regbean)
    {
        String loginid=regbean.getLoginname();
        String oldpassword=regbean.getPassword();
                
        try
        {
            con.setAutoCommit(true);
            PreparedStatement pst=con.prepareStatement("select * from logindetails where password=? and loginname=?");
            pst.setString(1,oldpassword);
            pst.setString(2,loginid);
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
                flag=true;
                LoggerManager.writeLogInfo("Login Success");
            }
            else
            {
                flag=false;
            }
        }
        catch (SQLException ex) 
        {
        	LoggerManager.writeLogSevere(ex);
        	LoggerManager.writeLogInfo("Database Connection problem");
            flag=false;
        }
        return flag;
    }
    
  //Compare Password
    public int checkFirstLogin(String loginname)
    {
    	int fstatus=2;        
        try
        {
        	con.setAutoCommit(true);
            PreparedStatement pst=con.prepareStatement("select firstlogin from logindetails where loginname=?");
            pst.setString(1,loginname);
            ResultSet rs=pst.executeQuery();
            
            if(rs.next())
            {
            	fstatus=rs.getInt(1);
            }
           }
        catch (SQLException ex) 
        {
        	LoggerManager.writeLogSevere(ex);
        	LoggerManager.writeLogInfo("Database Connection problem");
            flag=false;
        }
        return fstatus;
    }
    
    //Login Check
    public String loginCheck(RegisterForm regbean)
    {
        String loginid=regbean.getLoginname();
        String password=regbean.getPassword();
        String role="";        
        try
        {
            con.setAutoCommit(true);
            PreparedStatement pst=con.prepareStatement("select logintype,login_status from logindetails where password=? and loginname=?");
            pst.setString(2,loginid);
            pst.setString(1,password);
            System.out.println(loginid);
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
                
                role=rs.getString(1);
                System.out.println("&&&&"+role);
                int ls=rs.getInt(2);
                desc="Login Success";
            }
            else
            {
                flag=false;
                desc="Entered Invalid password";
            }
        }
        catch (SQLException ex) 
        {
        	LoggerManager.writeLogSevere(ex);
            desc="Database Connection problem";
            flag=false;
        }
        loginaudit(loginid,desc);
        return role;
    }
    
    
    //Method for login audit
    public void loginaudit(String loginid,String desc)
    {
        try 
        {
            String newdate=DateWrapper.parseDate(new Date());
            PreparedStatement pst=con.prepareStatement("insert into login_audit values(?,?,?)");
            pst.setString(1,loginid);
            pst.setString(2,newdate);
            pst.setString(3,desc);
            int i=pst.executeUpdate();
            if(i==1)
                con.commit();
            else
                con.rollback();
        }
        catch(Exception e)
        {
            try 
            {
                con.rollback();
            }
            catch (SQLException ex) 
            {
            	LoggerManager.writeLogSevere(ex);
            }
            e.printStackTrace();
        }
    }
    
    //Change Password
    public boolean changePassword(RegisterForm rf)
    {
        
        try 
        {
            con.setAutoCommit(false);
            String newdate=DateWrapper.parseDate(new Date());
            PreparedStatement pst=con.prepareStatement("UPDATE logindetails SET password=?,passmodifieddate=? WHERE loginname=? and password=?");
   
            
            System.out.println("newp"+rf.getNewPassword());
            System.out.println("ln"+rf.getLoginname());
            System.out.println("date"+newdate);
            System.out.println("oldp"+rf.getOldpassword());
            pst.setString(1,rf.getNewPassword());
            pst.setString(2,newdate);
            pst.setString(3,rf.getLoginname());
            pst.setString(4,rf.getOldpassword());
            
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
            catch (SQLException sex) 
            {
            	LoggerManager.writeLogSevere(sex);
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
            catch (SQLException sex) 
            {
            	LoggerManager.writeLogSevere(sex);
            }
        }
        return flag;        
    }
    
    
     //Change Secret Question
    public boolean changeQuestion(RegisterForm regbean)
    {
        String loginid=regbean.getLoginname();
        String password=regbean.getPassword();
        int secretquestid=regbean.getSecretQuestionID();
        String ownsecretquest=regbean.getOwnSecretQuestion();
        String secretans=regbean.getSecretAnswer();
        
        
        System.out.println("loginid"+loginid);
        System.out.println("pwd"+password);
        System.out.println("sqid"+secretquestid);
        System.out.println("ownsecq"+ownsecretquest);
        System.out.println("secans"+secretans);
        PreparedStatement pst;
        int i=0;
        try 
        {
            con.setAutoCommit(false);
            if(checkPassword(regbean))
            {
                if(secretquestid==0)
                {
                    Statement st=con.createStatement();
                    ResultSet rs=st.executeQuery("select (max(question_id))+1 from question_base");
                    
                    if(rs.next())
                        secretquestid=rs.getInt(1);
                        pst = con.prepareStatement("INSERT INTO question_base VALUES(?,?)");
                    
                    pst.setInt(1,secretquestid);
                    pst.setString(2,ownsecretquest);
        
                    pst.executeUpdate();
                }
               
                pst=con.prepareStatement("UPDATE logindetails SET squestionid=?,sanswer=? WHERE loginname=? and password=?");
            
                pst.setInt(1,secretquestid);
                pst.setString(2,secretans);
                pst.setString(3,loginid);
                pst.setString(4,password);
            
                i=pst.executeUpdate();
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
        }
        catch (SQLException ex) 
        {
        	LoggerManager.writeLogSevere(ex);
            flag=false;
            try 
            {
                con.rollback();
            } 
            catch (SQLException sex) 
            {
            	LoggerManager.writeLogSevere(sex);
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
            catch (SQLException sex) 
            {
            	LoggerManager.writeLogSevere(sex);
            }
        }
        return flag;        
    }
    
     //Recover Password using Existed Question
    public String recoverPasswordByExistQuestion(RegisterForm regbean)
    {
        String password;
        String loginid=regbean.getLoginname();
        int secretquestid=regbean.getSecretQuestionID();
        String secretans=regbean.getSecretAnswer();
        try 
        {
            con.setAutoCommit(true);
            PreparedStatement pst=con.prepareStatement("SELECT password FROM logindetails  WHERE loginname=? and squestionid=? and sanswer=?");
            
            pst.setString(1,loginid);
            pst.setInt(2,secretquestid);
            pst.setString(3,secretans);
                       
            ResultSet rs=pst.executeQuery();
            
            if(rs.next())
                password=rs.getString(1);
            else
                password="";
        } 
        catch (SQLException ex) 
        {
        	LoggerManager.writeLogSevere(ex);
            password="";
        }
        catch (Exception e) 
        {
        	LoggerManager.writeLogSevere(e);
            password="";
        }
        return password;        
    }
    
    
     //Recover Password using OWN Question
    public String recoverPasswordByOWNQuestion(RegisterForm regbean)
    {
        String password;
        String loginid=regbean.getLoginname();
        String ownsecretquest=regbean.getOwnSecretQuestion();
        String secretans=regbean.getSecretAnswer();
        try 
        {
            con.setAutoCommit(true);
            PreparedStatement pst=con.prepareStatement("SELECT password FROM logindetails  WHERE loginname=? and squestionid=(select question_id from question_base where question_details=?) and sanswer=?");
            
            pst.setString(1,loginid);
            pst.setString(2,ownsecretquest);
            pst.setString(3,secretans);
            
            
            ResultSet rs=pst.executeQuery();
            
            if(rs.next())
                password=rs.getString(1);
            else
                password="";
        } 
        catch (SQLException ex) 
        {
        	LoggerManager.writeLogSevere(ex);
            password="";
        }
        catch (Exception e) 
        {
        	LoggerManager.writeLogSevere(e);
            password="";
        }
        return password;        
    }
    
    public int checkLoginStatus(String loginname)
    {
    	int fstatus=2;        
        try
        {
        	con.setAutoCommit(true);
            PreparedStatement pst=con.prepareStatement("select login_status from logindetails where loginname=?");
            pst.setString(1,loginname);
            ResultSet rs=pst.executeQuery();
            
            if(rs.next())
            {
            	fstatus=rs.getInt(1);
            }
           }
        catch (SQLException ex) 
        {
        	LoggerManager.writeLogSevere(ex);
        	LoggerManager.writeLogInfo("Database Connection problem");
            flag=false;
        }
        return fstatus;
    }
}

