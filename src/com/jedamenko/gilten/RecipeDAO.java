package com.jedamenko.gilten;
import java.sql.*;
import java.io.*;
import java.util.*;


public class RecipeDAO {
private Connection myConn;
private String table_name;
private List<String> column_names = new ArrayList<>();
private List<String> schemas_list = new ArrayList<>();



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
			String t = rs.getString(3); if (!t.equals("schemas")) accepted_tables.add(t);
		}
		
		try
		{
			for (String t : accepted_tables)
			{
				Statement myStat=myConn.createStatement();
				String sql = "SELECT `schema` FROM medication_assistant.`schemas` WHERE `table`='"+t+"'";
				ResultSet myResult = myStat.executeQuery(sql);
				File schema = new File ("schemas\\"+t+".xsd");
				FileOutputStream output = new FileOutputStream(schema);
				if (myResult.next())
				{
					InputStream input = myResult.getBinaryStream("schema");
					
					byte[] buffer = new byte[1024];
					while (input.read(buffer) > 0)
					{
						output.write(buffer);
					}
				}
				output.close();
				
			}
		}
		catch (IOException ex) {ex.printStackTrace();}
		
		
	}
	
	catch (ClassNotFoundException ex) {ex.printStackTrace();}
	catch (SQLException ex) {ex.printStackTrace();}
	
}






//public static void main (String[] args)


public List<String[]> selectData(String table, String statement) throws Exception
{
	this.table_name=table;
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
				System.out.println(entry[i]);
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

public String getTable_name() {
	return table_name;
}


}
