package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Batch_Pro 
{
	private String driver="oracle.jdbc.OracleDriver";
	private String db_url="jdbc:oracle:thin:@localhost:1521:orcl21";
	private String db_uname="gaurav";
	private String db_pwd="7992";
	Scanner sc=new Scanner(System.in);
	
	void meth1()
	{
		System.out.println("Implementing Btach Processing ");
		try
		{
			Class.forName(driver);
			Connection con=DriverManager.getConnection(db_url,db_uname,db_pwd);
			Statement st=con.createStatement();
			
			System.out.println("How many queries do you wanna execute :- ");
			int query=Integer.parseInt(sc.nextLine());
			
			for(int i=1;i<=query;i++)
			{
				System.out.println("Enter "+i+" query");
				st.addBatch(sc.nextLine());
			}
			
			System.out.println("All "+query+" query added to database");
			int arr[]=st.executeBatch();
			System.out.print("=====> : ");
			for(int output : arr)
			{
				System.out.print(output+" ");
			}
			st.clearBatch();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*void meth2()
	{
		try
		{
			Class.forName(driver);
			Connection con=DriverManager.getConnection(db_url, db_uname, db_pwd);
			Statement st2=con.createStatement();
			
			System.out.println("How many queries do you wanna execute :- ");
			int query2=Integer.parseInt(sc.nextLine());
			
			for(int i=1;i<=query2;i++)
			{
				System.out.println("Enter "+i+" query :- ");
				st2.addBatch(sc.nextLine());
			}
			
			System.out.println("All "+query2+" are added to the database");
			int arr2 []=st2.executeBatch();
			System.out.println("=========> : ");
			for(int result : arr2)
			{
				System.out.print(result+" ");
			}
			st2.clearBatch();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}*/
	
	public static void main(String []args)
	{
		Batch_Pro obj=new Batch_Pro();
		obj.meth1();
		//obj.meth2();
	}
}
