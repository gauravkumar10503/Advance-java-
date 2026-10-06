package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Jdbc_pro4
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
			e.printStackTrace();
		}
		return con;
	}
	
	void getBookDetalis()
	{
		System.out.println("Retriving the book detalis from database");
		try
		{
			Connection con=connect();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("Select * from library");
			
			while(rs.next())
			{
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getInt(5));
			}
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void insertBookDetails()
	{
		System.out.println("Inserting the book details into database");
		try
		{
			Class.forName(driver);
			Connection con=connect();
			Statement st=con.createStatement();
			
			System.out.println("Enter the book id :- ");
			String bookid=sc.nextLine();
			
			System.out.println("Enter the book name :- ");
			String bookname=sc.nextLine();
			
			System.out.println("Enter the author name :- ");
			String author=sc.nextLine();
			
			System.out.println("Enter the book genere :- ");
			String genere=sc.nextLine();
			
			System.out.println("Enter the book cost :- ");
			int bookcost=Integer.parseInt(sc.nextLine());
			
			int rowcount=
			st.executeUpdate("insert into library values ('"+bookid+"','"+bookname+"','"+author+"','"+genere+"','"+bookcost+"')");
			
			if(rowcount==1)
			{
				System.out.println("Data successfully inserted into database");
				System.out.println("Do you wanna see the table? Y/N ");
				char choice=sc.nextLine().charAt(0);
				switch(choice)
				{
				case 'y','Y' -> getBookDetalis();
				case 'n','N' -> System.out.println("Thank you for inserting the data ");
				default -> System.out.println("Enter the valid key !!!");
				}
			}
			else
			{
				System.out.println("please enter the valid detalis");
			}
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void getDataById()
	{
		System.out.println("Retriving the data by the book id ");
		try
		{
			Connection con=connect();
			Statement st=con.createStatement();
			
			System.out.println("Enter the book id :- ");
			String bookid=sc.nextLine();
			
			ResultSet rs=st.executeQuery("Select * from library where bookid='"+bookid+"'");
			while(rs.next())
			{
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getInt(5));
			}
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void deleteData()
	{
		System.out.println("Deleting the data from database ");
		try
		{
			Connection con=connect();
			Statement st=con.createStatement();
			System.out.println("Enter the book id you wanna delete :- ");
			String bookid=sc.nextLine();
			
			int rowDelete=st.executeUpdate("delete from library where bookid='"+bookid+"'");
			if(rowDelete==1)
			{
				System.out.println("Data deleted successfully ");
				System.out.println("Do you wanna see the table :- Y/N");
				char choice=sc.nextLine().charAt(0);
				switch(choice)
				{
				case 'y','Y' -> getBookDetalis();
				case 'n','N' -> System.out.println("Thank you for giving you time ");
				default -> System.out.println("Please enter the valid key ");
				}
			}
			else
			{
				System.out.println("Please enter the valid id ");
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
		Jdbc_pro4 obj4=new Jdbc_pro4();
		//obj4.getBookDetalis();
		//obj4.insertBookDetails();
		//obj4.getDataById();
		obj4.deleteData();
	}
}
