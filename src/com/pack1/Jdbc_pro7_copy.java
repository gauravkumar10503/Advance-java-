package com.pack1;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class Jdbc_pro7_copy 
{
	@SuppressWarnings("unused")
	private String driver="oracle.jdbc.OracleDriver";
	private String db_url="jdbc:oracle:thin:@localhost:1521:orcl21";
	private String db_uname="gaurav";
	private String db_pwd="7992";
	
	void meth1()
	{
		System.out.println("Implementing the RowSet\n");
		try
		{
			RowSetFactory rsf= RowSetProvider.newFactory();
			JdbcRowSet jrs=rsf.createJdbcRowSet();
			jrs.setUrl(db_url);
			jrs.setUsername(db_uname);
			jrs.setPassword(db_pwd);
			jrs.setCommand("select * from employee2");
			jrs.execute();
			
			while(jrs.next())
			{
				System.out.println(jrs.getString(1)+" "+jrs.getString(2)+" "+jrs.getInt(3)+" "+jrs.getString(4));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void meth2()
	{
		System.out.println("Impleteing the RowSet \n");
		try
		{
			RowSetFactory rsf=RowSetProvider.newFactory();
			CachedRowSet crs=rsf.createCachedRowSet();
			crs.setUrl(db_url);
			crs.setUsername(db_uname);
			crs.setPassword(db_pwd);
			crs.setCommand("select * from employee2");
			crs.execute();
			
			while(crs.next())
			{
				String id=crs.getString(1);
				if(id.equals("112"))
				{
					crs.updateInt("esal",10000);
					crs.updateRow();
					System.out.println("Database updated");
				}
			}
			crs.acceptChanges();
			crs.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String []args)
	{
		Jdbc_pro7_copy obj=new Jdbc_pro7_copy();
		obj.meth1();
		//obj.meth2();
	}
	
}
