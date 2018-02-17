package com.jedamenko.giltenGUI;


import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class RecipeDAOTableModel extends AbstractTableModel {
	
	private List<String> column_names=new ArrayList<>();
	private List<String[]> rows = new ArrayList<>();
	public RecipeDAOTableModel(List<String[]> list, List<String> column_names) throws NullPointerException
	{
		super();
		if (!column_names.isEmpty())
		{
			this.column_names=column_names;
		}
		else
		{
			throw new NullPointerException("Column names list is empty!");
		}
		this.column_names=column_names;
		if (!list.isEmpty())
		{
			this.rows=list;
		}
		else
		{
			throw new NullPointerException("List is empty!");
		}
		
	}

	@Override
	public String getColumnName(int column)
	{
		System.out.println("getColumnName: "+column_names.get(column));
		return column_names.get(column);
	}
	
	@Override
	public Class getColumnClass(int columnIndex)
	{
		try {
			Class c = Class.forName("java.lang.String");
			return c;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		System.out.println("getColumnCount: "+this.column_names.size());
		return this.column_names.size();
	}

	@Override
	public int getRowCount() {
		System.out.println("getRowCount: "+this.rows.size());
		// TODO Auto-generated method stub
		return this.rows.size();
	}

	@Override
	public Object getValueAt(int row, int column) 
	{
		String value = rows.get(row)[column];
		
		return value;
	}
	
	
}
