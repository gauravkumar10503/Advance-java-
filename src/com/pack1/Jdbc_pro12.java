package com.pack1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.sql.RowSetMetaData;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class Jdbc_pro12 
{
	private String driver="oracle.jdbc.OracleDriver";
	private String db_url="jdbc:oracle:thin:@localhost:1521:orcl21";
	private String db_uname="gaurav";
	private String db_pwd="7992";
	
	String sqlQuery="select eid,ename from employee2";
	String sqlQuery2="select eid,ename,esal from employee2 where eid=?";
	
	void meth1()
	{
		System.out.println("Implementing METADATA in JDBC\n");
		
		try
		{
			Class.forName(driver);
			Connection con=DriverManager.getConnection(db_url,db_uname,db_pwd);
			
			DatabaseMetaData dbmt=con.getMetaData();
			System.out.println("\n************* DataBaseMetadata ***************\n");
			System.out.println("getDatabaseProductName() :- "+dbmt.getDatabaseProductName());
			System.out.println("getDatabaseProductVersion () :- "+dbmt.getDatabaseProductVersion());
			System.out.println("getDriverName() :- "+dbmt.getDriverName());
			System.out.println("supportsStoredProcedures() :- "+dbmt.supportsStoredProcedures());
			
			PreparedStatement ps1=con.prepareStatement(sqlQuery2);
			ps1.setString(1, "101");
			ResultSet rs1=ps1.executeQuery();
			
			ParameterMetaData pmmd=ps1.getParameterMetaData();
			System.out.println("\n************* ParameterMetadata ***************\n");
			System.out.println("getParameterCount() :- "+pmmd.getParameterCount());
			System.out.println("getParameterType() :- "+pmmd.getParameterType(1));
			System.out.println("getParameterMode(int ___ ) :- "+pmmd.getParameterMode(1));
			System.out.println("isNullable(int ____) :- "+pmmd.isNullable(1));
			
			ResultSetMetaData rsmd=rs1.getMetaData();
			System.out.println("\n************* ResultSetMetadata ***************\n");
			System.out.println("getColumnCount() :- "+rsmd.getColumnCount());
			System.out.println("getColumnName(int ___) :- "+rsmd.getColumnName(1));
			System.out.println("getColumnDisplaySize(int ___) :- "+rsmd.getColumnDisplaySize(1));
			System.out.println("isAutoIncrement(int ____) :- "+rsmd.isAutoIncrement(1));
			
			RowSetFactory rsf=RowSetProvider.newFactory();
			CachedRowSet crs=rsf.createCachedRowSet();
			crs.setUrl(db_url);
			crs.setUsername(db_uname);
			crs.setPassword(db_pwd);
			crs.setCommand("select eid,ename,esal from employee2");
			crs.execute();
			RowSetMetaData rmd=(RowSetMetaData)crs.getMetaData();
			System.out.println("\n************* RowSetMetadata ***************\n");
			System.out.println("getColumnCount() :- "+rmd.getColumnCount());
			System.out.println("getColumnName(int ____) :- "+rmd.getColumnName(1));
			System.out.println("getColumnDisplaySize(int ___) :- "+rmd.getColumnDisplaySize(1));
			System.out.println("isAutoIncrement(int ____) :- "+rmd.isAutoIncrement(1));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String []args)
	{
		Jdbc_pro12 obj=new Jdbc_pro12();
		obj.meth1();
	}
}
