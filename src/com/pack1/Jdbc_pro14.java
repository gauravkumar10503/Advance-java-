package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Jdbc_pro14 
{
	private String driver="oracle.jdbc.OracleDriver";
	private String db_url="jdbc:oracle:thin:@localhost:1521:orcl21";
	private String db_uname="gaurav";
	private String db_pwd="7992";
	Scanner sc=new Scanner(System.in);
	
	
	void meth1()
	{
		System.out.println("Implementing Batch Processing ");
		try
		{
			Class.forName(driver);
			Connection con=DriverManager.getConnection(db_url,db_uname,db_pwd);
			Statement st=con.createStatement();
			
			System.out.println("How many query do you wanna execute :- ");
			int no_query=Integer.parseInt(sc.nextLine());
			
			for(int i=1;i<=no_query;i++)
			{
				System.out.println("Enter "+i+" query");
				st.addBatch(sc.nextLine());
			}
			
			System.out.println("All "+no_query+" queries added to the database");
			int arr []=st.executeBatch();
			System.out.print("=======> : ");
			for(int data : arr)
			{
				System.out.print(data+" ");
			}
			st.clearBatch();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String []args)
	{
		Jdbc_pro14 obj=new Jdbc_pro14();
		obj.meth1();
	}
}
