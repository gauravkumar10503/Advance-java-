package com.pack1;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class Jdbc_pro7 
{
	@SuppressWarnings("unused")
	private String driver="oracle.jdbc.OracleDriver";
	private String db_url="jdbc:oracle:thin:@localhost:1521:orcl21";
	private String db_uname="gaurav";
	private String db_pwd="7992";
	
	public void meth1()
	{
		System.out.println("Implementing JdbcRowSet interface\n");
		try
		{
			RowSetFactory rsf=RowSetProvider.newFactory();
			JdbcRowSet jrs=rsf.createJdbcRowSet();
			jrs.setUrl(db_url);
			jrs.setUsername(db_uname);
			jrs.setPassword(db_pwd);
			jrs.setCommand("select * from employee2");
			jrs.execute();
			
			//jrs.close(); it will give us exception, JdbcRowSet only works in connected mode.
			
			while(jrs.next())
			{
				System.out.println(jrs.getString(1)+" "+jrs.getString(2)+" "+jrs.getInt(3));
			}
			
			System.out.println("--------------------------------\n");
			jrs.absolute(-2);
			System.out.println(jrs.getString(1)+" "+jrs.getString(2)+" "+jrs.getInt(3));
			
			System.out.println("--------------------------------\n");
			while(jrs.previous())
			{
				System.out.println(jrs.getString(1)+" "+jrs.getString(2)+" "+jrs.getInt(3));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	void meth2()
	{
		System.out.println("Implementing CachedRowSet interface\n");
		try
		{
			RowSetFactory rsf=RowSetProvider.newFactory();
			CachedRowSet crs=rsf.createCachedRowSet();
			crs.setUrl(db_url);
			crs.setUsername(db_uname);
			crs.setPassword(db_pwd);
			crs.setCommand("select eid,ename,esal from employee2");
			crs.execute();
			
			while(crs.next())
			{
				String id=crs.getString(1);
				if(id.equals("103"))
				{
					crs.updateInt("esal", 50000);
					crs.updateRow();
					System.out.println("Database updated");
				}
			}
			//crs.acceptChanges(); for manually commit we use acceptChanges()
			crs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String []args)
	{
		Jdbc_pro7 obj7=new Jdbc_pro7();
		//obj7.meth1();
		obj7.meth2();
	}
}
