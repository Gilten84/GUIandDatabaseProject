package com.jedamenko.gilten.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassAnalysisTools 
{
	public static List<String> generateGettersList(Class<?> clazz)
	{
		Field[] fields = clazz.getDeclaredFields();
		Method[] methods = clazz.getDeclaredMethods();
		List<String> field_names = Stream.of(fields).map
				(f -> f.getName()).collect(Collectors.toList());
		List<String> method_names = Stream.of(methods).map
				(f -> f.getName()).collect(Collectors.toList());
		List<String> list_of_getters = new ArrayList<>();
		for (String fn : field_names)
			for(String mn : method_names)
				if (mn.toLowerCase().equals("get"+fn.toLowerCase()))
					list_of_getters.add(mn);
				 
			
		return list_of_getters;	
	}


}
