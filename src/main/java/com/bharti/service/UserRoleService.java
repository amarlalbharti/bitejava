package com.bharti.service;

import java.util.List;

import com.bharti.domain.UserRole;

public interface UserRoleService {
	public List<UserRole> getUserRolesByUserid(String userid);
}
