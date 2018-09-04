package com.bharti.constraints;

import com.bharti.domain.Registration;

public class RegistrationUtils {
	
	public static String getFullName(Registration reg) {
		StringBuilder sb = new StringBuilder();
		if(reg != null) {
			if(reg.getFirstName() != null) {
				sb.append(reg.getFirstName());
				if(reg.getLastName() != null) {
					sb.append(" "+reg.getLastName());
				}
			}else if(reg.getLastName() != null) {
				sb.append(reg.getLastName());
			}
		}
		return sb.toString();	
	}
	
	public static String getUserFullDetail(Registration reg) {
		StringBuilder sb = new StringBuilder();
		if(reg != null) {
			sb.append("Name-");
			if(reg.getFirstName() != null) {
				sb.append(reg.getFirstName());
				if(reg.getLastName() != null) {
					sb.append(" "+reg.getLastName());
				}
			}else if(reg.getLastName() != null) {
				sb.append(reg.getLastName());
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
