package com.jedamenko.gilten;
import java.sql.*;
import java.io.*;
import java.util.*;


public class RecipeDAO {
private Connection myConn;
private List<String> column_names = new ArrayList<>();



private List<String> accepted_tables= new ArrayList<String>();

public RecipeDAO (String dburl, String user, String password)
{
	super(); 
	try 
	{
		
		//connect to database
	
		Class.forName("org.gjt.mm.mysql.Driver");
		myConn = DriverManager.getConnection(dburl,user,password);
		System.out.println("Successfully connected to "+dburl);
		//receive table names
		DatabaseMetaData md = myConn.getMetaData();		
		ResultSet rs = md.getTables(null, null, "%", null);
		while (rs.next()) 
		{
		  accepted_tables.add(rs.getString(3));
		}
		
		
		
	}
	
	catch (ClassNotFoundException ex) {ex.printStackTrace();}
	catch (SQLException ex) {ex.printStackTrace();}
	
}






//public static void main (String[] args)


public List<String[]> selectData(String table, String statement) throws Exception
{
	Statement stat = myConn.createStatement();
    ResultSet set = stat.executeQuery("select * from "+table);
    ResultSetMetaData data = set.getMetaData();
    int number_of_columns = data.getColumnCount();
    column_names.clear();

    for (int i = 1; i <= number_of_columns; i++) 
        column_names.add(data.getColumnName(i));
    
	List<String[]> list_of_entries = new ArrayList<>();
	Statement myStat = null;
	ResultSet myRs = null;
	try
	{ 		
		myStat = myConn.createStatement();
		myRs=myStat.executeQuery(statement);
		
		while (myRs.next())
		{
			
			String[] entry = new String[number_of_columns];
			for (int i = 0; i < number_of_columns; i++)
			{
				entry[i] = myRs.getString(column_names.get(i));
			}
			list_of_entries.add(entry);
			
		}
		return list_of_entries; 
	
	}catch (Exception ex) 
	{ex.printStackTrace();}
	
	return null;
}

public List<String[]> updateData(String table, String statement) throws Exception
{
	if (!this.accepted_tables.contains(table)) return null;
	Statement myStat = null;
	try
	{ 		
		myStat = myConn.createStatement();
		myStat.executeUpdate(statement);
		
		
	}catch (Exception ex) 
	{ex.printStackTrace();}
	
	return null;
}

public List<String> getColumn_names() 
{
	return column_names;
}

}
