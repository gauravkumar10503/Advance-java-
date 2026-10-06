package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Scanner;

public class Bank_Transaction_Management 
{
	private String driver="oracle.jdbc.OracleDriver";
	private String db_url="jdbc:oracle:thin:@localhost:1521:orcl21";
	private String db_uname="gaurav";
	private String db_pwd="7992";
	Scanner sc=new Scanner(System.in);
	
	String sqlQuery="update bank set balance=balance-? where acc_no=?";
	
	
	void meth1()
	{
		System.out.println("Implementing the Bank Transaction Management\n");
		try
		{
			Class.forName(driver);
			Connection con=DriverManager.getConnection(db_url,db_uname,db_pwd);
			System.out.println("getAutoCommit() :- "+con.getAutoCommit());
			con.setAutoCommit(false);
			
			PreparedStatement ps1=con.prepareStatement(sqlQuery);
			
			System.out.println("Enter the transfer amount :- ");
			int at=Integer.parseInt(sc.nextLine());
			
			System.out.println("Enter the account number :- ");
			int acc_no=Integer.parseInt(sc.nextLine());
			
			ps1.setInt(1, at);
			ps1.setInt(2, acc_no);
			int transfer=ps1.executeUpdate();
			
			if(transfer==0)
			{
				throw new SQLException("Transfer failed");
			}
			else
			{
				System.out.println("\nAmount "+at+" "+" deducted from "+acc_no);
			}
			Savepoint sp=con.setSavepoint();
			
			PreparedStatement ps2=con.prepareStatement("update bank set balance=balance+? where acc_no=? ");
			System.out.println("Enter the account number where you wanna transfer the money :- ");
			int acc_no2=Integer.parseInt(sc.nextLine());
			ps2.setInt(1, at);
			ps2.setInt(2, acc_no2);
			int received=ps2.executeUpdate();
			
			if(received==0)
			{
				con.rollback(sp);
				throw new SQLException("Money not received");
			}
			else
			{
				System.out.println("\nAmount "+at+" "+" received to the "+acc_no2);
				System.out.println("\nTransaction completed successfully");
				con.commit();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String []args)
	{
		new Bank_Transaction_Management().meth1();
	}
	
}
