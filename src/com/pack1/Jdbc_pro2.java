package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

public class Jdbc_pro2 {
	private String driver="oracle.jdbc.OracleDriver";
	private String db_url="jdbc:oracle:thin:@localhost:1521:orcl21";
	private String db_uname="gaurav";
	private String db_pwd="7992";
	String sqlquery="select * from employee2";
	String sqlquery2="insert into employee2 values ('122','harsh',35000,'pune')";
	Scanner sc=new Scanner(System.in);
	
	public void getData() {
		System.out.println("Reterving the data from employee table");
		try {
			Class.forName(driver);
			Connection con=DriverManager.getConnection(db_url,db_uname,db_pwd);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(sqlquery);
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
				//break;
				//System.out.println(rs.getString(1));
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void insertData() {
		System.out.println("Inserting the data into database");
		try {
			Class.forName(driver);
			Connection con=DriverManager.getConnection(db_url,db_uname,db_pwd);
			Statement stmt=con.createStatement();
			
			System.out.println("Enter the employee id :- ");
			String eid=sc.nextLine();
			
			System.out.println("Enter the employee name :- ");
			String ename=sc.nextLine();
			
			System.out.println("Enter the employee salary :- ");
			int esal=Integer.parseInt(sc.nextLine());
			
			System.out.println("Enter the employee address :- ");
			String eadd=sc.nextLine();
			
			
			int rowCount=
					stmt.executeUpdate("insert into employee2 values ('"+eid+"','"+ename+"',"+esal+",'"+eadd+"')");
			
			if(rowCount==1) {
				System.out.println("Data inserted successfully");
				System.out.println("Do you wanna see the table :- Y/N");
				char choice=sc.nextLine().charAt(0);
				
				switch(choice) {
				case 'Y','y' :
					getData();
				break;
				
				case 'N','n' :
					System.out.println("Thank you for updating the details");
				break;
				
				default : System.out.println("Enter the valid data ");
				}
			}else {
				System.out.println("Data is not updated");
			}
			con.close();
			
		}catch(SQLIntegrityConstraintViolationException sicve) {
			System.out.println("Duplicate employee id are not allowed");
			insertData();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String []args) {
		Jdbc_pro2 obj=new Jdbc_pro2();
		//obj.getData();
		obj.insertData();
	}
	
}
