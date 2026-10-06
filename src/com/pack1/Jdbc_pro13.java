package com.pack1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Jdbc_pro13 
{
	private String driver="oracle.jdbc.OracleDriver";
	private String db_url="jdbc:oracle:thin:@localhost:1521:orcl21";
	private String db_uname="gaurav";
	private String db_pwd="7992";
	
	String sqlQuery1="insert into mydata values(?,?)";
	String sqlQuery2="select pic_data from mydata where id=?";
	String sqlQuery3="insert into mydata2 values(?,?)";
	String sqlQuery4="select file_data from mydata2 where i=?";
	
	Connection connect()
	{
		Connection con=null;
		try
		{
			Class.forName(driver);
			con=DriverManager.getConnection(db_url,db_uname,db_pwd);
			System.out.println("Connection created");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	void meth1()
	{
		System.out.println("Passing BLOB data in database");
		try
		{
			Connection con=connect();
			PreparedStatement ps1=con.prepareStatement(sqlQuery1);
			ps1.setString(1, "101");
			
			FileInputStream fis=new FileInputStream("C:\\Users\\ADMIN\\Desktop\\o-shop\\o-shop\\public\\cart3d.png");
			ps1.setBlob(2, fis, fis.available());
			
			int rowCount=ps1.executeUpdate();
			if(rowCount==0)
			{
				throw new RuntimeException("Image not inserted");
			}
			else
			{
				System.out.println("Image inserted into the database");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void meth2()
	{
		System.out.println("Reteriving BLOB data from the database");
		try
		{
			Connection con=connect();
			PreparedStatement ps2=con.prepareStatement(sqlQuery2);
			ps2.setString(1, "101");
			ResultSet rs=ps2.executeQuery();
			
			if(rs.next())
			{
				Blob b=rs.getBlob(1);
				byte arr[]=b.getBytes(1, (int) b.length());
				//byte arr[]=b.getBytes(1, 1024*500);
				FileOutputStream fos=new FileOutputStream("C:\\Users\\ADMIN\\Desktop\\o-shop\\o-shop\\public\\cart3d1.png");
				fos.write(arr);
				fos.close();
				System.out.println("Image reterived");
			}
			else
			{
				System.out.println("There is no record with the given id");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void meth3()
	{
		System.out.println("Passing a file into the databse");
		try
		{
			Connection con=connect();
			PreparedStatement ps3=con.prepareStatement(sqlQuery3);
			ps3.setString(1, "101");
			
			//FileReader fr=new FileReader("C:\\Users\\ADMIN\\Desktop\\revision\\boolean.html");
			//ps3.setClob(2, fr);
			
			ps3.setClob(2, new FileReader("C:\\Users\\ADMIN\\Desktop\\revision\\boolean.html"));
			int rowCount=ps3.executeUpdate();
			
			if(rowCount == 0)
			{
				throw new SQLException("file not inserted");
			}
			else
			{
				System.out.println("file inserted");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void meth4()
	{
		System.out.println("Reteriving the file from the database");
		try
		{
			Connection con=connect();
			PreparedStatement ps4=con.prepareStatement(sqlQuery4);
			ps4.setString(1, "101");
			ResultSet rs4=ps4.executeQuery();
			
			if(rs4.next())
			{
				Clob c=rs4.getClob(1);
				Reader data=c.getCharacterStream();
				BufferedReader br=new BufferedReader(data);
				FileWriter fw=new FileWriter("C:\\Users\\ADMIN\\Desktop\\revision\\boolean2.html");
				String line;
				
				while((line=br.readLine())!=null)
				{
					fw.write(line);
				}
				br.close();
				fw.close();
				System.out.println("Clob data reterived");
			}
			else
			{
				throw new SQLException("Invalid id");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String []args)
	{
		Jdbc_pro13 obj=new Jdbc_pro13();
		//obj.meth1();
		//obj.meth2();
		//obj.meth3();
		obj.meth4();
	}
}
