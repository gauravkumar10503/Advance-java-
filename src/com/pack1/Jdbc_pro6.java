package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Jdbc_pro6 
{
	private String driver="oracle.jdbc.OracleDriver";
	private String db_url="jdbc:oracle:thin:@localhost:1521:orcl21";
	private String db_uname="gaurav";
	private String db_pwd="7992";
	
	Connection connect()
	{
		Connection con=null;
		try
		{
			Class.forName(driver);
			con=DriverManager.getConnection(db_url,db_uname,db_pwd);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	void meth1()
	{
		System.out.println("Implementing the scrollble resultset \n");
		Connection con=connect();
		try
		{
			Statement st=con.createStatement(1004,1007);
			//Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//We can use both line no 37 and 38 its upto us
			ResultSet rs=st.executeQuery("select * from employee2");
			
//			while(rs.next())
//			{
//				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
//			}
			System.out.println("\n");
			
			/**
			 * rs.afterLast();
			 * System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
			 * 
			 * when we use afterLast() method then we will face a exception that said ' Result set after last row'
			 * Its means our cursor is out side of our ResultSet that's why it give us exception 
			 */
			
			/**
			 * rs.beforeFirst();
			 * System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
			 * 
			 * when we use beforeFirst() method then we will face a exception that said ' ResultSet.next was not called'
			 * Its means our cursor is out side of our ResultSet that's why it give us exception 
			 */
			
			/**
			 * rs.last();
			 * System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
			 * 
			 * when we use last() method then we will get only  the last row of our table
			 */
			
			/**
			 * rs.first();
			 * System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
			 * 
			 * when we use last() method then we will get only the first row of our table
			 */
			
			/**
			 * rs.absolute(int index);
			 * System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
			 * 
			 * when we use absolute(int index) method then we can jump into that row directly in the table.
			 * int can be positive and negetive.
			 */
			
			rs.absolute(4);
			System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
			
			rs.relative(-2);
			System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void meth2()
	{
		System.out.println("Implementing scrollble resultset");
		Connection con=connect();
		try
		{
			Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st.executeQuery("select eid,ename,esal from employee2");
			
			while(rs.next())
			{
				String id=rs.getString(1);
				if(id.equals("103"))
				{
					rs.updateInt(3, 30000);
					rs.updateRow();
				}
			}
			
			Statement st2=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs2=st2.executeQuery("SELECT * FROM EMPLOYEE2");
			rs2.absolute(2);
			System.out.println(rs2.getString(1)+" "+rs2.getString(2)+" "+rs2.getInt(3)+" "+rs2.getString(4));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String []args)
	{
		Jdbc_pro6 obj=new Jdbc_pro6();
		//obj.meth1();
		obj.meth2();
	}
}
