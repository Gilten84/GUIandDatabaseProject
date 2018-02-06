package com.jedamenko.gilten;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



public class RecipeDAO {
private static Connection myConn;

public RecipeDAO() throws Exception
{
	//get db connection
	
	Properties props = new Properties();
	props.load(new FileInputStream("sql\\demo.properties"));
	
	String user = props.getProperty("user");
	String password = props.getProperty("password");
	String dburl = props.getProperty("dburl");
	System.out.println(user+" "+password+" "+dburl);
	
	//connect to database
	
	Class.forName("org.gjt.mm.mysql.Driver");
	myConn = DriverManager.getConnection(dburl,user,password);
	System.out.println("Successfully connected to "+dburl);
}

public static Doctor convertRowToDoctor (ResultSet rs)
{
	try 
	{
		Doctor d = new Doctor (rs.getInt("idDoctors"),rs.getString("doctor_last_name"),rs.getString("doctor_first_name"),
		rs.getString("doctor_id_code"));
		return d;
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	
}

//public static void main (String[] args)
public static List<Doctor> getAllDoctors() throws Exception
{
	try {
		RecipeDAO edao = new RecipeDAO();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	List<Doctor> list = new ArrayList<>();
	Statement myStat = null;
	ResultSet myRs = null;
	
	try
	{
		myStat = myConn.createStatement();
		myRs=myStat.executeQuery("select * from doctors");
		System.out.println("I am here");
	
		while (myRs.next())
		{
			System.out.println(myRs.getString("idDoctors")+" "+myRs.getString("doctor_last_name")+" "+myRs.getString("doctor_first_name")+" "+myRs.getString("doctor_id_code"));
			Doctor tempDoctor = convertRowToDoctor(myRs);
			list.add(tempDoctor);
		}
		
		return list;
	}
	catch (Exception ex) {
		//close(myStat, myRs);
	}
	return null;
}

public static void main(String[] args)
{
	try {
		getAllDoctors();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	

}
