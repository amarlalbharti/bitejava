package com.bharti.utils;



import com.bharti.constraints.Validation;

public class Util 
{
	public static int RPP = 10;
	
	public static Integer getNumeric(String value){
		if(Validation.isNumeric(value)){
			return Integer.parseInt(value);
		}
		return 0;
	}
	
	public static Long getLong(String value){
		if(Validation.isNumeric(value)){
			return Long.parseLong(value);
		}
		return 0L;
	}
	
	public static Integer getNumericPositive(String value){
		if(Validation.isNumeric(value)){
			if(Integer.parseInt(value) > 0) {
				return Integer.parseInt(value);
			}
		}
		return 0;
	}
	
	public static String projectURL = "http://localhost:7070/bookstore/";
}
