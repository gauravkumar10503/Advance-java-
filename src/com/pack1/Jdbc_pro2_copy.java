package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.util.Iterator;
import java.util.Scanner;
//import java.util.TreeSet;

public class Jdbc_pro2_copy {
	private String driver="oracle.jdbc.OracleDriver";
	private String db_url="jdbc:oracle:thin:@localhost:1521:orcl21";
	private String db_uname="gaurav";
	private String db_pwd="7992";
	String sqlquery="select * from employee2";
	String sqlquery2="insert into employee2 values ('123','adam',40000,'londan')";
	Scanner sc=new Scanner(System.in);
	
	public void getData() {
		System.out.println("Retriving the data from database");
		try {
			Class.forName(driver);
			Connection con=DriverManager.getConnection(db_url,db_uname,db_pwd);
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sqlquery);
			
			//TreeSet <Object> ts=new TreeSet<Object>();
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
				//System.out.println(rs.getString(1));
				//ts.add(rs.getString(1));
			}
			
//			System.out.println("Before :- "+ts);
//			Iterator <Object> i=ts.descendingIterator();
//			System.out.print("After :- ");
//			while(i.hasNext()) {
//				System.out.print(i.next()+" ");
//			}
			
			/**
			 * To print in descending order here we're using TreeSet because in TreeSet insertion order is not
			 * preserved. After that we're iterating by build in collection method that is descendingIterator()
			 * after that for printing we use while loop, in while loop we're using hasNext() method. It return
			 * boolean value and for printing value we use i.next() method.
			 */
			
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
			Statement st=con.createStatement();
			int rowCount=st.executeUpdate(sqlquery2);
			
			if(rowCount ==1) {
				System.out.println("Data inserted successfully");
				System.out.println("Do you wanna see the table :- Y/N");
				char choice=sc.nextLine().charAt(0);
				
				switch(choice) {
				case 'y','Y' :
					getData();
				break;
				
				case 'n','N' :
					System.out.println("Thank you for inserting data into database");
				break;
				
				default : System.out.println("Enter valid key !!!! ");
				}
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String []args) {
		Jdbc_pro2_copy obj=new Jdbc_pro2_copy();
		//obj.getData();
		obj.insertData();
	}
}
