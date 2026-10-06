package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

public class Jdbc_pro10 
{
	private String driver="oracle.jdbc.OracleDriver";
	private String db_url="jdbc:oracle:thin:@localhost:1521:orcl21";
	private String db_uname="gaurav";
	private String db_pwd="7992";
	
	String sqlQuery1="update trainseatavailability set AVAILABLE_SEATS=AVAILABLE_SEATS-1 where TRAIN_ID=? and JOURNEY_DATE=? and CLASS=?";
	String sqlQuery2="insert into bookingdetails values(?,?,?,?,?)";
	String sqlQuery3="select PAYMENT_STATUS from customerpayment where CUSTOMER_ID=?";
	String sqlQuery4="update bookingdetails set status='Confirmed' where CUSTOMER_ID=?";
	
	public void meth1()
	{
		System.out.println("Implementing Transaction Management ");
		
		try
		{
			Class.forName(driver);
			Connection con=DriverManager.getConnection(db_url,db_uname,db_pwd);
			
			System.out.println("getAutoCommit() : "+con.getAutoCommit());
			con.setAutoCommit(false);
			System.out.println("getAutoCommit : "+con.getAutoCommit());
			
			PreparedStatement ps1=con.prepareStatement(sqlQuery1);
			ps1.setString(1, "12345");
			ps1.setString(2, "2024-10-10");
			ps1.setString(3, "sleeper");
			int rowCount1=ps1.executeUpdate();
			
			if(rowCount1==0)
			{
				throw new SQLException("Seats are not available!!!");
			}
			else
			{
				System.out.println("Seat is looked");
			}
			Savepoint sp=con.setSavepoint();
			
			PreparedStatement ps2=con.prepareStatement(sqlQuery2);
			ps2.setString(1, "B101");
			ps2.setString(2, "12345");
			ps2.setString(3, "c123");
			ps2.setInt(4, 1);
			ps2.setString(5, "payment pending");
			
			int rowCount2=ps2.executeUpdate();
			if(rowCount2==0)
				throw new SQLException("Booking not confirmed");
			System.out.println("Booking record inserted successfully");
			System.out.println("Awaiting for payment confirmation");
			
			PreparedStatement ps3=con.prepareStatement(sqlQuery3);
			ps3.setString(1, "c123");
			ResultSet rs1=ps3.executeQuery();
			String status="Failed";
			
			if(rs1.next())
			{
				status=rs1.getString(1);
			}
			if(status.equals("success"))
			{
				PreparedStatement ps4=con.prepareStatement(sqlQuery4);
				ps4.setString(1, "c123");
				int rowCount3=ps4.executeUpdate();
				if(rowCount3==0)
					throw new SQLException("Transaction terminated due to server side problem");
				con.commit();
				System.out.println("Seat booked successfully");
				System.out.println("Transaction done");
				System.out.println("All savepoint released");
			}
			else
			{
				System.out.println("Payment not done !!!!");
				System.out.println("We are rolling back to the last savepoint");
				con.rollback(sp);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String []args)
	{
		new Jdbc_pro10().meth1();
	}
}
