package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

public class ConnectionPool 
{
	private String driver, db_url, db_uname, db_pwd;
	Vector <Connection>v=new Vector<Connection>();
	
	public ConnectionPool(String driver, String db_url, String db_uname, String db_pwd)
	{
		this.driver=driver;
		this.db_url=db_url;
		this.db_uname=db_uname;
		this.db_pwd=db_pwd;
	}
	
	void con_Init()
	{
		System.out.println("Creating '5' connection object");
		System.out.println("Connection pool is empty right now");
		
		while(v.size()<5)
		{
			try
			{
				Class.forName(driver);
				Connection con=DriverManager.getConnection(db_url,db_uname,db_pwd);
				v.addElement(con);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		for(Object o :v)
		{
			System.out.println(o);
		}
		System.out.println("\n There are '5' connection object present in the pool");
	}
	
	Connection con_Acquire()
	{
		System.out.println("Assigning the connection object");
		Connection con=v.elementAt(0);
		v.remove(0);
		return con;
	}
	
	void con_Return(Connection con)
	{
		System.out.println("Returninga connection object ");
		v.addElement(con);
		for(Object o : v)
		{
			System.out.println(o);
		}
	}
}
