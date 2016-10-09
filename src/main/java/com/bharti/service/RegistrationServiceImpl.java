package com.bharti.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bharti.dao.RegistrationDao;
import com.bharti.domain.Registration;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService
{
	@Autowired private RegistrationDao registrationDao;
	
	public Registration getRegistrationByUserid(String userid)
	{
		return this.registrationDao.getRegistrationByUserid(userid);
	}
	
	/**
	 * Returns the list of object of registration class
	 * @return
	 */
	public List<Registration> getRegistrationList()
	{
		return this.registrationDao.getRegistrationList();
	}
	
	public List<Registration> getEmpRegistrationList() {
		return this.registrationDao.getEmpRegistrationList();
	}
	
	public long countEmployees()
	{
		return this.registrationDao.countEmployees();
	}
	
	public boolean updateRegistration(Registration registration)
	{
		return this.registrationDao.updateRegistration(registration);
	}

	public long countOnlyEmployees() 
	{
		return this.registrationDao.countUserEmployees();
	}

	public List<Registration> getEmpRegistrationListByCountry(int countryId)
	{
		return this.registrationDao.getEmpRegistrationListByCountry(countryId);
	}
	
	public List<Registration> getEmpRegistrationListByBranch(int branchId)
	{
		return this.registrationDao.getEmpRegistrationListByBranch(branchId);
	}
	
}
