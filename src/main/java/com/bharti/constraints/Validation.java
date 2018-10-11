package com.bharti.constraints;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class Validation {
	
	private static final Logger LOGGER = Logger.getLogger(Validation.class);
	
	private static Pattern emailNamePtrn = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		     
	private static Pattern PASSWORD_PATTERN = Pattern.compile("((?=.*\\d)(?=.*[a-z]).{4,20})");
    
	public static boolean validateEmail(String email){
         
        Matcher mtch = emailNamePtrn.matcher(email);
        if(mtch.matches()){
            return true;
        }
        return false;
    }
    
    public static boolean validatePassword(String password){
        
        Matcher mtch = PASSWORD_PATTERN.matcher(password);
        if(mtch.matches()){
            return true;
        }
        return false;
    }
    
    public static boolean isNotNullNotEmpty(String value){
		if(value != null){
			return !StringUtils.isEmpty(value.trim());
		}
		return false;
	}
    public static boolean isNumeric(String value){
		if(isNotNullNotEmpty(value)){
			return StringUtils.isNumeric(value.trim());
		}
		return false;
	}
    
    
    public static boolean isValidDate(String str) {
    	Date date = null;
    	try {
			date = DateFormats.ddMMyyyy.parse(str);
			if(!str.equals(DateFormats.ddMMyyyy.format(date))) {
				date = null;
			}
			if(date != null) {
				return true;
			}
		}catch (Exception e) {
			LOGGER.info("Invalid date format : " +str);
		}
		return false;
	}
}
