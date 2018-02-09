package com.jedamenko.gilten;
import java.sql.*;
import java.io.*;
import java.util.*;


public class RecipeDAO {
private Connection myConn;
private List<String> accepted_tables= new ArrayList<String>();
private Properties props = new Properties();
public RecipeDAO (File properties, String dburl, String user, String password)
{
	super(); 
	try 
	{
		this.props.load(new FileInputStream(properties));

		Enumeration keys = props.keys();
		while (keys.hasMoreElements())
		{
			this.accepted_tables.add((String)keys.nextElement());
		}
		//connect to database
	
		Class.forName("org.gjt.mm.mysql.Driver");
		myConn = DriverManager.getConnection(dburl,user,password);
		System.out.println("Successfully connected to "+dburl);
	}
	catch (FileNotFoundException e) {e.printStackTrace();}
	catch (ClassNotFoundException ex) {ex.printStackTrace();}
	catch (SQLException ex) {ex.printStackTrace();}
	catch (IOException e) {e.printStackTrace();}
}






//public static void main (String[] args)
public List<DBCommonObject> getAllObjects(String table) throws Exception
{
	List<DBCommonObject> list = new ArrayList<>();
	Statement myStat = null;
	ResultSet myRs = null;
	
	try
	{
		myStat = myConn.createStatement();
		myRs=myStat.executeQuery("select * from "+table);
		
		Class<?> c = Class.forName(props.getProperty(table));
		System.out.println(c.getName()+" "+c.getSimpleName());
		Class<ResultSet> clazz = (Class<ResultSet>)Class.forName("java.sql.ResultSet");
		Class[] classes = new Class[] {clazz};
		Object[] args = new Object[]{myRs};
		
		while (myRs.next())
		{
			DBCommonObject obj = (DBCommonObject) c.getDeclaredConstructor(classes).newInstance(args);
			list.add(obj);
		}
		
		return list;
	}
	catch (Exception ex) 
	{ ex.printStackTrace();}
	return null;
}

public List<DBCommonObject> searchInColumn(String table, String column_name, String value) throws Exception
{
	List<DBCommonObject> list = new ArrayList<>();
	Statement myStat = null;
	ResultSet myRs = null;
	try
	{ 		
		myStat = myConn.createStatement();
		myRs=myStat.executeQuery("select * from "+table+" where "+column_name+" like \""+value+"\"");
		Class<?> c = Class.forName(props.getProperty(table));
		System.out.println(c.getName()+" "+c.getSimpleName());
		Class<ResultSet> clazz = (Class<ResultSet>)Class.forName("java.sql.ResultSet");
		Class[] classes = new Class[] {clazz};
		Object[] args = new Object[]{myRs};
		while (myRs.next())
		{
				DBCommonObject obj = (DBCommonObject) c.getDeclaredConstructor(classes).newInstance(args);
				list.add(obj);
			
		}
		return list;
	
	}catch (Exception ex) 
	{ex.printStackTrace();}
	

	return null;
}



public static void main(String[] args)
{
	
	
	File file = new File("sql//schema.properties");
	String user="root";
	String password="toporpales#?2345";
	String dburl="jdbc:mysql://localhost:3306/medication_assistant?autoReconnect=true&useSSL=false";
	RecipeDAO dao = new RecipeDAO(file,dburl,user,password);
	try {
		List<DBCommonObject> dbc = dao.getAllObjects("doctors");
		dbc = dao.searchInColumn("recipes", "Doctors_idDoctors","2");
		for (DBCommonObject obj : dbc)
		{
			Recipe r = (Recipe) obj;
			System.out.println(r.getIdRecipes());
			System.out.println(r.getRecipe_medicament());
			System.out.println(r.getRecipe_morning_dosage());
			System.out.println(r.getRecipe_day_dosage());
			System.out.println(r.getRecipe_evening_dosage());
			System.out.println(r.getPatients_idPatient());
			System.out.println(r.getDoctors_idDoctors());
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	

}
