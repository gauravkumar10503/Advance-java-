package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Emp_Reg 
{
	private String driver="oracle.jdbc.OracleDriver";
	private String db_url="jdbc:oracle:thin:@localhost:1521:orcl21";
	private String db_uname="gaurav";
	private String db_pwd="7992";
	static Scanner sc=new Scanner(System.in);
	
	Connection connect()
	{
		Connection con=null;
		try
		{
			Class.forName(driver);
			con=DriverManager.getConnection(db_url,db_uname,db_pwd);
			System.out.println("Connected to database\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	void employeereg()
	{
		Connection con=connect();
		try
		{
			PreparedStatement pst=con.prepareStatement("insert into empreg values (?,?,?,?,?,?)");
			PreparedStatement pst2=con.prepareStatement("select * from empreg");
			
			while(true)
			{
				System.out.println("\n**** Welcome To Employee Registration Potal ****");
				System.out.println("\nPlease Choose Your Operation");
				System.out.println("\n1. Insert the employee details \n2. Retrive the employee details\n3. Exit");
				int choice=Integer.parseInt(sc.nextLine());
				
				switch(choice)
				{
					case 1:
						System.out.println("\nInserting employee details");
						System.out.println("Enter employee name ");
						String ename=sc.nextLine();
						
						System.out.println("Enter employee password ");
						int epwd=Integer.parseInt(sc.nextLine());
						
						System.out.println("Enter employee first name ");
						String fname=sc.nextLine();
						
						System.out.println("Enter employee last name ");
						String lname=sc.nextLine();
						
						System.out.println("Enter employee address ");
						String eadd=sc.nextLine();
						
						System.out.println("Enter employee phone number ");
						long ephone=Long.parseLong(sc.nextLine());
						
						pst.setString(1, ename);
						pst.setInt(2, epwd);
						pst.setString(3, fname);
						pst.setString(4, lname);
						pst.setString(5, eadd);
						pst.setLong(6, ephone);
						
						int rowCount=pst.executeUpdate();
						
						if(rowCount>0)
						{
							System.out.println("\nData successfully inserted into database\n");
						}
						else
						{
							System.out.println("\nPlease insert the proper data");
						}
						break;
						
					case 2 :
						System.out.println("\nRetriving the employee details\n ");
						ResultSet rs=pst2.executeQuery();
						while(rs.next())
						{
							System.out.println(rs.getString(1)+" "+rs.getInt(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getLong(6));
						}
						break;
						
					case 3 :
						System.out.println("Thank you  😊");
						System.exit(0);
						break;
						
					default :
						System.out.println("Please choose a valid option ");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void emplogin()
	{
		Connection con=connect();
		
		try
		{
			PreparedStatement pst=con.prepareStatement("select *  from empreg where ename=? and pword=? ");
			PreparedStatement pst2=con.prepareStatement("select * from empreg");
			PreparedStatement pst3=con.prepareStatement("update empreg set pword=? where ename=? ");
			
			System.out.println("Enter the ename of the employee ");
			String ename=sc.nextLine();
			
			System.out.println("Enter the password of the employee ");
			int pass=Integer.parseInt(sc.nextLine());
			
			pst.setString(1, ename);
			pst.setInt(2, pass);
			ResultSet rs=pst.executeQuery();
			
			boolean found = false;
			
			while(rs.next())
			{
				if(ename.equals(rs.getString("ename")))
				{
					System.out.println(rs.getString(1)+" "+rs.getInt(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getLong(6));
					
				} 
					found = true;
			}
			if(!found) 
			{
				System.out.println("Please Enter the correct Ename and password!");
			}
			else
			{
				System.out.println("\nLogin successful\nPlease choose any of the option given below\n1. View Profile\n2. Update profile\n3. Exit");
				int choice=sc.nextInt();
				
				switch(choice)
				{
				case 1 :
					System.out.println("\nEmployee Details\n");
					ResultSet rs2=pst2.executeQuery();
					while(rs2.next())
					{
						System.out.println(rs2.getString(1)+" "+rs2.getInt(2)+" "+rs2.getString(3)+" "+rs2.getString(4)+" "+rs2.getString(5)+" "+rs2.getLong(6));
					}
					System.out.println();
					break;
					
				case 2 :
					System.out.println("\nUpdata Employee Details\n");
					System.out.println("Enter employee password ");
					int pwd=Integer.parseInt(sc.nextLine());
					
					System.out.println("Enter employee name ");
					String ename2=sc.nextLine();
					
					pst3.setInt(1, pwd);
					pst3.setString(2, ename2);
					
					int rowUpdate=pst3.executeUpdate();
					if(rowUpdate>0)
					{
						System.out.println("\nEmployee data updated successfully");
					}
					else
					{
						System.out.println("\nPlease enter the correct employee data");
					}
					break;
					
				case 3 :
					System.out.println("Thank you for giving our time 😊😊");
					System.exit(0);
					break;
					
				default:
					System.out.println("Please choose a valid option");

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
		Emp_Reg obj=new Emp_Reg();
		//obj.employeereg();
		//obj.emplogin();
		System.out.println("Please choose the given option\n1. View Employee Registration\n2. View Employee Login");
		int choice=Integer.parseInt(sc.nextLine());
		switch(choice)
		{
		case 1 :
			obj.employeereg();
			break;
			
		case 2:
			obj.emplogin();
			break;
			
			default :
				System.out.println("Thank you for giving your time ");
		}
	}
}
