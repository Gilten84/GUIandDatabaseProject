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


public List<DBCommonObject> selectData(String table, String statement) throws Exception
{
	if (!this.accepted_tables.contains(table)) return null;
	List<DBCommonObject> list = new ArrayList<>();
	Statement myStat = null;
	ResultSet myRs = null;
	try
	{ 		
		myStat = myConn.createStatement();
		myRs=myStat.executeQuery(statement);
		Class<?> c = Class.forName(props.getProperty(table));
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



}
