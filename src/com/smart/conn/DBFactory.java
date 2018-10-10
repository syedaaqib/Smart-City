package com.smart.conn;

import java.sql.SQLException;


public class DBFactory
{
	public DBFactory() throws SQLException
	{
		new DBConn().getConnection();
	}
}
