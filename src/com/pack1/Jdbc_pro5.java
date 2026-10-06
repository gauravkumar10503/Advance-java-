package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Jdbc_pro5
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
		}
		catch(Exception e)
		{
			System.out.println("Database connection failed");
			e.printStackTrace();
		}
		return con;
	}
	
	void patient_operation()
	{
		Connection con=connect();
		try
		{
			PreparedStatement pstmt1=con.prepareStatement("insert into patient values (?,?,?,?)");
			PreparedStatement pstmt2=con.prepareStatement("select * from patient");
			PreparedStatement pstmt3=con.prepareStatement("select * from patient where pid=?");
			PreparedStatement pstmt4=con.prepareStatement("update patient set age=? where pid=?");
			PreparedStatement pstmt5=con.prepareStatement("delete from patient where pid=?");
			
			while(true)
			{
				System.out.println("\nWelcome to patient portal");
				System.out.println("Please choose your operation");
				System.out.println("1. Add patient \n2. Retrive all patient \n3. Retrive patient data \n4. Update patient data \n5. Delete patient data \n6. Exit ");
				int choice=Integer.parseInt(sc.nextLine());
				
				switch(choice)
				{
					case 1 : 
						System.out.println("\nAdd patient data ");
						
						System.out.println("Enter the patient id :- ");
						String pid=sc.nextLine();
						
						System.out.println("Enter the patient name :- ");
						String pname=sc.nextLine();
						
						System.out.println("Enter the patient age :- ");
						int age=Integer.parseInt(sc.nextLine());
						
						System.out.println("Enter the patient contact :- ");
						long contact=Long.parseLong(sc.nextLine());
						
						pstmt1.setString(1, pid);
						pstmt1.setString(2, pname);
						pstmt1.setInt(3, age);
						pstmt1.setLong(4, contact);
						
						int rowcount1=pstmt1.executeUpdate();
						if(rowcount1>0)
						{
							System.out.println("Patient data inserted successfully");
						}
						else
						{
							System.out.println("Patient data insertion failed");
						}
						break;
						
					case 2 :
						System.out.println("\nRetrive all patient ");
						ResultSet rs1=pstmt2.executeQuery();
						while(rs1.next())
						{
							System.out.println(rs1.getString(1)+" "+rs1.getString(2)+" "+rs1.getInt(3)+" "+rs1.getLong(4));
						}
						break;
						
					case 3 :
						System.out.println("\nRetrive patient data ");
						System.out.println("Enter the patient id");
						String pid2=sc.nextLine();
						pstmt3.setString(1, pid2);
						ResultSet rs2=pstmt3.executeQuery();
						
						if(rs2.next())
						{
							System.out.println(rs2.getString(1)+" "+rs2.getString(2)+" "+rs2.getInt(3)+" "+rs2.getLong(4));
						}
						else
						{
							System.out.println("There is no patient available with pid :- "+pid2);
						}
						break;
						
					case 4 :
						System.out.println("\nUpdate patient data based on pid ");
						System.out.println("Enter the patient age");
						int age4=Integer.parseInt(sc.nextLine());
						System.out.println("Enter the patient id ");
						String pid4=sc.nextLine();
						
						pstmt4.setInt(1, age4);
						pstmt4.setString(2, pid4);
						int rowUpdate=pstmt4.executeUpdate();
						
						if(rowUpdate>0)
						{
							System.out.println("Patient with "+pid4+" age has been updated to :- "+age4);
						}
						else
						{
							System.out.println("There is no patient with id :- "+pid4);
						}
						break;
						
					case 5 :
						System.out.println("\nDeleting  patient data by patient id ");
						System.out.println("Enter the patient id ");
						String pid5=sc.nextLine();
						pstmt5.setString(1, pid5);
						int rowdelete=pstmt5.executeUpdate();
						
						if(rowdelete>0)
						{
							System.out.println("Patient with id : "+pid5+" record has been deleted");
						}
						else
						{
							System.out.println("Patient with id : "+pid5+" is not available in database");
						}
						break;
						
					case 6 :
						System.out.println("Thank you for visiting our patient portal\nBe safe 😊");
						System.exit(0);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String []args)
	{
		Jdbc_pro5 obj=new Jdbc_pro5();
		obj.patient_operation();
	}
}
