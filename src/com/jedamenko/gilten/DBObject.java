package com.jedamenko.gilten;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public abstract class DBObject 
{
	private String dbName;
	private String tableName;
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	abstract DBObject convertRowToDBObject (ResultSet rs);
	abstract List<DBObject> getAllDBObjects(Connection conn);
	
}
