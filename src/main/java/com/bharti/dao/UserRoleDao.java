package com.bharti.dao;

import java.util.List;

import com.bharti.domain.UserRole;

public interface UserRoleDao {
	
	public List<UserRole> getUserRolesByUserid(String userid);
	
}
