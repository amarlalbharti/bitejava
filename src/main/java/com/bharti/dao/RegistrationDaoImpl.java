package com.bharti.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bharti.constraints.Roles;
import com.bharti.domain.Registration;
import com.bharti.domain.UserRole;

@Repository
public class RegistrationDaoImpl implements RegistrationDao
{
	@Autowired private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public Registration getRegistrationByUserid(String userid)
	{
		List<Registration> list = this.sessionFactory.getCurrentSession().createCriteria(Registration.class)
				.createAlias("loginInfo", "logAlias")
				.add(Restrictions.eq("logAlias.userid", userid))
				.setFetchMode("logAlias", FetchMode.JOIN)
				.list();
		if(!list.isEmpty())
		{
			return list.get(0);
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<Registration> getRegistrationList()
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Registration.class)
				.addOrder(Order.desc("regdate"))
				.setFetchMode("loginInfo", FetchMode.JOIN)
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Registration> getEmpRegistrationList() {
		
		List<UserRole> list = this.sessionFactory.getCurrentSession().createCriteria(UserRole.class)
                .add(Restrictions.or(Restrictions.eq("userrole", Roles.ROLE_ADMIN.toString()),Restrictions.eq("userrole", Roles.ROLE_MANAGER.toString())))
                .list();
        List<String> rl = new ArrayList<String>();
        for(UserRole role : list)
        {
            rl.add(role.getLoginInfo().getUserid());
        }
        
        return this.sessionFactory.getCurrentSession().createCriteria(Registration.class)
                .add(Restrictions.not(Restrictions.in("userid",rl)))
                .setFetchMode("loginInfo", FetchMode.JOIN)
                .list();
	}
	
	public long countEmployees()
	{
		return (Long)this.sessionFactory.getCurrentSession().createCriteria(Registration.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.setProjection(Projections.rowCount())
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public long countUserEmployees()
	{
		List<UserRole> list = this.sessionFactory.getCurrentSession().createCriteria(UserRole.class)
                .add(Restrictions.or(Restrictions.eq("userrole", Roles.ROLE_ADMIN.toString()),Restrictions.eq("userrole", Roles.ROLE_MANAGER.toString())))
                .list();
        List<String> rl = new ArrayList<String>();
        for(UserRole role : list)
        {
            rl.add(role.getLoginInfo().getUserid());
        }
		return (Long)this.sessionFactory.getCurrentSession().createCriteria(Registration.class)
				.add(Restrictions.not(Restrictions.in("userid",rl)))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.setProjection(Projections.rowCount())
				.uniqueResult();
	}
	
	public boolean updateRegistration(Registration registration)
	{
		try 
		{
			this.sessionFactory.getCurrentSession().update(registration);
			this.sessionFactory.getCurrentSession().flush();
			return true;
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public List<Registration> getEmpRegistrationListByCountry(int countryId)
	{
		List<UserRole> list = this.sessionFactory.getCurrentSession().createCriteria(UserRole.class)
                
				.add(Restrictions.or(Restrictions.eq("userrole", Roles.ROLE_ADMIN.toString()),Restrictions.eq("userrole", Roles.ROLE_MANAGER.toString())))
                .list();
        List<String> rl = new ArrayList<String>();
        for(UserRole role : list)
        {
            rl.add(role.getLoginInfo().getUserid());
        }
        
        return this.sessionFactory.getCurrentSession().createCriteria(Registration.class)
        		.createAlias("branch", "branchAlias")
        		.createAlias("branchAlias.country", "countryAlias")
        		
                .add(Restrictions.not(Restrictions.in("userid",rl)))
                .add(Restrictions.eq("country.countryId", countryId))
                .setFetchMode("loginInfo", FetchMode.JOIN)
                .list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Registration> getEmpRegistrationListByBranch(int branchId)
	{
		List<UserRole> list = this.sessionFactory.getCurrentSession().createCriteria(UserRole.class)
				.add(Restrictions.or(Restrictions.eq("userrole", Roles.ROLE_ADMIN.toString()),Restrictions.eq("userrole", Roles.ROLE_MANAGER.toString())))
                .list();
        List<String> rl = new ArrayList<String>();
        for(UserRole role : list)
        {
            rl.add(role.getLoginInfo().getUserid());
        }
        
        return this.sessionFactory.getCurrentSession().createCriteria(Registration.class)
        		.add(Restrictions.eq("branch.branchId", branchId))
                .add(Restrictions.not(Restrictions.in("userid",rl)))
                .setFetchMode("loginInfo", FetchMode.JOIN)
                .list();
	}

	
	
}
