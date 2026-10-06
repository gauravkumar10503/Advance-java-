package com.pack1;

import java.sql.Connection;

public class Jdbc_pro11 
{
	private String driver="oracle.jdbc.OracleDriver";
	private String db_url="jdbc:oracle:thin:@localhost:1521:orcl21";
	private String db_uname="gaurav";
	private String db_pwd="7992";
	
	ConnectionPool cp=new ConnectionPool(driver,db_url,db_uname,db_pwd);
	
	void meth1()
	{
		System.out.println("Calling con_Init()");
		cp.con_Init();
		System.out.println("Remaining connection object :- "+cp.v.size());
		
		System.out.println("\n**** USER 1 *****");
		Connection con1=cp.con_Acquire();
		System.out.println("Remaining connection object :- "+cp.v.size());
		
		System.out.println("\n**** USER 2 *****");
		Connection con2=cp.con_Acquire();
		System.out.println("Remaining connection object :- "+cp.v.size());
		
		System.out.println("\n**** USER 3 ******");
		Connection con3=cp.con_Acquire();
		System.out.println("Remaining connection object : -"+cp.v.size());
		
		System.out.println("----------------------------------------");
		cp.con_Return(con1);
		System.out.println();
		cp.con_Return(con2);
		System.out.println();
		cp.con_Return(con3);
	}
	
	public static void main(String []args)
	{
		new Jdbc_pro11().meth1();
	}
}
