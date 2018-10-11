package com.bharti.constraints;

import com.bharti.domain.Registration;

public class RegistrationUtils {
	
	public static String getFullName(Registration reg) {
		StringBuilder sb = new StringBuilder();
		if(reg != null) {
			if(reg.getName() != null) {
				sb.append(reg.getName());
			}
		}
		return sb.toString();	
	}
	
	public static String getUserFullDetail(Registration reg) {
		StringBuilder sb = new StringBuilder();
		if(reg != null) {
			sb.append("Name-");
			if(reg.getName() != null) {
				sb.append(reg.getName());
			}
			sb.append(", ");
			sb.append("Email-");
			sb.append(reg.getLoginInfo().getUserid() +", ");
			sb.append("Gender-");
			sb.append(reg.getGender());
			sb.append("Contact No-");
			sb.append(reg.getContactno());
			
		}
		return sb.toString();	
	}

}
