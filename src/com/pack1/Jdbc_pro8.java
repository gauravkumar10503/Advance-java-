package com.pack1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class Jdbc_pro8 
{
	private String driver="oracle.jdbc.OracleDriver";
	private String db_url="jdbc:oracle:thin:@localhost:1521:orcl21";
	private String db_uname="gaurav";
	private String db_pwd="7992";
	Scanner sc=new Scanner(System.in);
	
	Connection connect()
	{
		Connection con=null;
		try
		{
			Class.forName(driver);
			con=DriverManager.getConnection(db_url,db_uname,db_pwd);
			System.out.println("Connection created successfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	void meth1()
	{
		System.out.println("Implenting callable statement ====> Procedure");
		Connection con=connect();
		try
		{
			CallableStatement cst=con.prepareCall("{call InsertEmpData(?,?,?,?,?)}");
			
			System.out.println("Enter employee id :- ");
			String eid=sc.nextLine();
			
			System.out.println("Enter employee name :- ");
			String ename=sc.nextLine();
			
			System.out.println("Enter the employee desg :- ");
			String edesg=sc.nextLine();
			
			System.out.println("Enter employee salary :- ");
			int ebsal=Integer.parseInt(sc.nextLine());
			
			float etsal=(float) (ebsal+(0.45*ebsal)+(0.25*ebsal));
			
			cst.setString(1, eid);
			cst.setString(2, ename);
			cst.setString(3, edesg);
			cst.setInt(4, ebsal);
			cst.setFloat(5, etsal);
			cst.execute();
			
			System.out.println("Data inserted successfully into the database");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void meth2()
	{
		System.out.println("Implementing the callable statement ======> procedure ");
		Connection con=connect();
		try
		{
			CallableStatement cst=con.prepareCall("{call ReteriveData(?,?,?,?,?)}");
			
			System.out.println("Enter the employee id ");
			String eid=sc.nextLine();
			
			cst.setString(1, eid);
			
			cst.registerOutParameter(2, Types.VARCHAR);
			cst.registerOutParameter(3, Types.VARCHAR);
			cst.registerOutParameter(4, Types.INTEGER);
			cst.registerOutParameter(5, Types.FLOAT);
			
			cst.execute();
			
			System.out.println("\n**********************Employee Data************************");
			System.out.println("Employee id :- "+eid);
			System.out.println("Employee name :- "+cst.getString(2));
			System.out.println("Employee designation :- "+cst.getString(3));
			System.out.println("Employee basic salary :- "+cst.getInt(4));
			System.out.println("Employee total salary :- "+cst.getFloat(5));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void meth3()
	{
		System.out.println("Implementing callable statement =======> function");
		Connection con=connect();
		try
		{
			CallableStatement cst=con.prepareCall("{call ?:=ReteriveTsal(?)}");
			
			System.out.println("Enter employee id ");
			String eid=sc.nextLine();
			
			cst.setString(2, eid);
			cst.registerOutParameter(1, Types.FLOAT);
			cst.execute();
			
			System.out.println("\n***********Employee Data*************");
			System.out.println("Employee id :- "+eid);
			System.out.println("Employee sal :- "+cst.getFloat(1));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String []args)
	{
		Jdbc_pro8 obj8=new Jdbc_pro8();
		//obj8.meth1();
		//obj8.meth2();
		obj8.meth3();
	}
}
