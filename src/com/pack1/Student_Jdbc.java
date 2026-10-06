package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Student_Jdbc 
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
			System.out.println("Database connection is not establish");
			e.printStackTrace();
		}
		return con;
	}
	
	void student()
	{
		Connection con=connect();
		
		try
		{
			PreparedStatement ps=con.prepareStatement("insert into student values (?,?,?,?,?)");
			PreparedStatement ps2=con.prepareStatement("Select * from student");
			PreparedStatement ps3=con.prepareStatement("select * from student where sid=?");
			PreparedStatement ps4=con.prepareStatement("update student set result=? where sid=?");
			PreparedStatement ps5=con.prepareStatement("delete from student where sid=?");
			
			while(true)
			{
				System.out.println("\nWelcome to Student portal");
				System.out.println("Please choose any opertion given below");
				System.out.println("1. Insert the student data\n2. Retrive all student data\n3. Retrive student data by sid\n4. Update student data by sid\n5. Delete student data by sid\n6. Exit");
				int choice=Integer.parseInt(sc.nextLine());
				
				switch(choice)
				{
				case 1 :
					System.out.println("\nInserting the student data");
					System.out.println("Enter the student id ");
					String sid1=sc.nextLine();
					
					System.out.println("Enter Student class ");
					int class1=Integer.parseInt(sc.nextLine());
					
					System.out.println("Enter student roll");
					int roll1=Integer.parseInt(sc.nextLine());
					
					System.out.println("Enter student result ");
					String result1=sc.nextLine();
					
					System.out.println("Enter student name ");
					String name1=sc.nextLine();
					
					ps.setString(1, sid1);
					ps.setInt(2, class1);
					ps.setInt(3, roll1);
					ps.setString(4, result1);
					ps.setString(5, name1);
					
					int rowInsert=ps.executeUpdate();
					if(rowInsert>0)
					{
						System.out.println("Student data successfully inserted into data");
					}
					else
					{
						System.out.println("Student data insertion failed");
					}
					break;
					
				case 2 :
					System.out.println("\nRetrive all student data");
					ResultSet rs=ps2.executeQuery();
					while(rs.next())
					{
						System.out.println(rs.getString(1)+" "+rs.getInt(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getString(5));
					}
					break;
					
				case 3 :
					System.out.println("\nRetrive student data by sid");
					System.out.println("Enter student id ");
					String sid3=sc.nextLine();
					ps3.setString(1, sid3);
					ResultSet rs3=ps3.executeQuery();
					while(rs3.next())
					{
						System.out.println(rs3.getString(1)+" "+rs3.getString(2)+" "+rs3.getInt(3)+" "+rs3.getString(4)+" "+rs3.getString(5));
					}
					break;
					
				case 4 :
					System.out.println("\nUpdate student data by sid");
					System.out.println("Enter student id ");
					String sid4=sc.nextLine();
					
					System.out.println("Enter student result ");
					String result4=sc.nextLine();
					
					ps4.setString(1, result4);
					ps4.setString(2, sid4);
					int rowupdate=ps4.executeUpdate();
					if(rowupdate>0)
					{
						System.out.println("Student data updated successfully");
					}
					else
					{
						System.out.println("There is no student available with student id : "+sid4);
					}
					break;
					
				case 5 :
					System.out.println("\nDeleting student data by sid");
					System.out.println("Enter the student id");
					String sid5=sc.nextLine();
					
					ps5.setString(1, sid5);
					int rowdelete=ps5.executeUpdate();
					if(rowdelete>0)
					{
						System.out.println("Student data deleted successfully");
					}
					else
					{
						System.out.println("Student with "+sid5+" is not present in our database");
					}
					break;
					
				case 6 :
					System.out.println("Thank you for giving you time");
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
		Student_Jdbc obj=new Student_Jdbc();
		obj.student();
	}
}
