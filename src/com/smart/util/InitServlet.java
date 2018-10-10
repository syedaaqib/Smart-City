package com.smart.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import com.smart.conn.DBConn;
import com.smart.conn.DBFactory;

public class InitServlet extends HttpServlet
{
	DBConn dobject;

	public void init(ServletConfig sc)
	{
		ServletContext ctx = sc.getServletContext();
		InputStream fis = ctx.getResourceAsStream(sc.getInitParameter("config"));
		Properties props = new Properties();
		
		try
		{
			props.load(fis);
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		dobject = new DBConn();
		dobject.setProperties(props);

		LoggerManager.logger = new LoggerManager().getLogger(props.getProperty("logfile"));
		LoggerManager.writeLogInfo("Logger Instantiated");

		try
		{
			new DBFactory();
		}
		catch (NullPointerException npe)
		{
			LoggerManager.writeLogWarning("Connection to database FAILED");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
