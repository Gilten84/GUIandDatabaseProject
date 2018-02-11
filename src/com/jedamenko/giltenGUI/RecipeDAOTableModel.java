package com.jedamenko.giltenGUI;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.table.AbstractTableModel;

import com.jedamenko.gilten.DBCommonObject;

public class RecipeDAOTableModel extends AbstractTableModel {
	
	private Class<?> clazz;
	private List<String> column_names=new ArrayList<>();
	private List<DBCommonObject> rows = new ArrayList<>();
	private Field[] fields;
	private Method[] methods;
	public RecipeDAOTableModel(List<DBCommonObject> list) throws NullPointerException
	{
		super();
		if (!list.isEmpty())
		{
			this.clazz = list.get(0).getClass();
			this.rows=list;
	
		}
		else
		{
			throw new NullPointerException("List is empty!");
		}
		
		this.methods = clazz.getMethods();
		this.fields = clazz.getDeclaredFields();
		Stream.of(this.fields).forEach( (s) -> column_names.add(s.getName())); 
		
		
		
		
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
		System.out.println("getColumnClass: "+this.fields[columnIndex].getType());
		return this.fields[columnIndex].getType();
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
		DBCommonObject obj = rows.get(row);
		List<String> getters = generateGettersList(clazz);
		for (Method method : methods)
		{
			if (method.getName().equals(getters.get(column)))
			{
				try {
					Object value = method.invoke(obj);
					return value;
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<String> generateGettersList(Class<?> clazz)
	{
		Field[] fields = clazz.getDeclaredFields();
		
		List<String> field_names = Stream.of(fields).map
				(f -> f.getName()).collect(Collectors.toList());
		List<String> list_of_getters = new ArrayList<>();
		for (String s : field_names)
		{
			if (!s.equals("getters"))
			{
				char[] arr = s.toCharArray();
		        if (arr[0] >= 97 && arr[0] <= 122) 
		        {
		             s=s.replaceFirst("[a-z]", "get"+((char)(arr[0] - 32)));
		             
		        }
		        list_of_getters.add(s);
				 
			}
		}
		
		return list_of_getters;	
	}

}
