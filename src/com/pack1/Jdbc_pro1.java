package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;

public class Jdbc_pro1 {
	private String driver="oracle.jdbc.OracleDriver";
	private String db_url="jdbc:oracle:thin:@localhost:1521:orcl21";
	private String db_uname="gaurav";
	private String db_pwd="7992";
	
	public void connect() {
		System.out.println("Connecting java program to the database");
		try {
			Class.forName(driver);
			Connection con=DriverManager.getConnection(db_url,db_uname,db_pwd);
			System.out.println("Connection Created");
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String []args) {
		new Jdbc_pro1().connect();
	}
}
