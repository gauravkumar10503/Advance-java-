package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

public class Jdbc_pro3
{
	private String driver="oracle.jdbc.OracleDriver";
	private String db_url="jdbc:oracle:thin:@localhost:1521:orcl21";
	private String db_uname="gaurav";
	private String db_pwd="7992";
	String select="select * from employee2";
	Scanner sc=new Scanner(System.in);
	
	Connection connect()
	{
		Connection con=null;
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(db_url,db_uname,db_pwd);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public void fetchData() 
	{
		System.out.println("Retriving the data from database ");
		try
		{
			Connection con=connect();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(select);
			while(rs.next())
			{
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
			}
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void insertData()
	{
		System.out.println("Inserting the data into database");
		try
		{
			Connection con=connect();
			Statement st=con.createStatement();
			
			System.out.println("Enter the id of the employee :- ");
			String eid=sc.nextLine();
			
			System.out.println("Enter the name of the employee :- ");
			String ename=sc.nextLine();
			
			System.out.println("Enter the salary of the employee :- ");
			int esal=Integer.parseInt(sc.nextLine());
			
			System.out.println("Enter the add of the employee :- ");
			String eadd=sc.nextLine();
			
			int rowCount=st.executeUpdate("Insert into employee2 values ('"+eid+"','"+ename+"',"+esal+",'"+eadd+"')");
			if(rowCount==1)
			{
				System.out.println("Data inserted successfully");
				System.out.println("Do you wanna see the data :- Y/N");
				char choice=sc.nextLine().charAt(0);
				
				switch(choice)
				{
				case 'y','Y' :
					fetchData();
				break;
				
				case 'n','N' :
					System.out.println("Thank you for inserting the data ");
				break;
				
				default : System.out.println("Please enter the valid character !!!");
				}
			}
			else
			{
				System.out.println("Data is not inserted");
			}
			con.close();
		}
		catch(SQLIntegrityConstraintViolationException si)
		{
			System.out.println("Duplicate data is not allowed ");
			insertData();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void updateData()
	{
		System.out.println("Updating the database data");
		try
		{
			Connection con=connect();
			Statement st=con.createStatement();
			
			System.out.println("Enter the new employee id :- ");
			String neid=sc.nextLine();
			
			System.out.println("Enter the old employee id :- ");
			String oeid=sc.nextLine();
			
			int rowUpdate=st.executeUpdate("update employee2 set eid='"+neid+"' where eid='"+oeid+"'");
			
			if(rowUpdate==1)
			{
				System.out.println("Database updated");
				System.out.println("Do you wanna see the updated database :- Y/N");
				char choice=sc.nextLine().charAt(0);
				
				switch(choice)
				{
				case 'y','Y' -> fetchData();
				case 'n','N' -> System.out.println("Thank you for updating the database");
				default -> System.out.println("Enter the valid key !!!");
				}
			}
			else
			{
				System.out.println("Database is not updated please try again !!!");
				updateData();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void deleteData()
	{
		System.out.println("Deleting the data from the database");
		try
		{
			Connection con=connect();
			Statement st=con.createStatement();
			
			System.out.println("Enter the employee id :- ");
			String eid=sc.nextLine();
			
			int rowDelete=st.executeUpdate("delete from employee2 where eid='"+eid+"'");
			
			if(rowDelete==1)
			{
				System.out.println("Data deleted successfully from database");
				System.out.println("Do you wanna see the database :- Y/N");
				char choice=sc.nextLine().charAt(0);
				
				switch(choice)
				{
				case 'y','Y' -> fetchData();
				case 'n','N' -> System.out.println("Thank you for giving your time ");
				default -> System.out.println("Enter the valid key !!!");
				}
			}
			else
			{
				System.out.println("Employee id "+eid+" is not matching in the database please try again !!!!");
				deleteData();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String []args)
	{
		Jdbc_pro3 obj3=new Jdbc_pro3();
		obj3.fetchData();
		//obj3.insertData();
		//obj3.updateData();
		//obj3.deleteData();
	}
}
