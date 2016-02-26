package com.image.work.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.image.common.pojo.DictAreas;
import com.image.common.pojo.DictFunctions;
import com.image.common.pojo.DictUsers;
import com.image.common.pojo.RolesFunction;
import com.image.work.dao.DictUsersDao;

public class DictUsersDaoImpl extends HibernateDaoSupport implements
 DictUsersDao {

	@SuppressWarnings("unchecked")
	public DictUsers login(String username, String password) {
		String hql = "from DictUsers u where u.username=? and u.password=?";
		List<DictUsers> users = getHibernateTemplate().find(hql,new Object[]{username,password});
		if(users==null || users.size()==0){
			return null;
		}
		return users.get(0);
	}

	@SuppressWarnings("unchecked")
	public DictUsers login(String idkid) {
		String hql = "from DictUsers u where u.idnum=?";
		List<DictUsers> users = getHibernateTemplate().find(hql,new Object[]{idkid});
		if(users==null || users.size()==0){
			return null;
		}
		if(users.size()>1){
			System.out.println("该卡号存在多个用户");
			return null;
		}
		return users.get(0);
	}

	public DictUsers getUser(String username, String password) {
		String hql="from DictUsers where username=:username  and password=:password";
		Query query = getSession().createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("password", password);
		return (DictUsers)query.uniqueResult();
	}


	public List<RolesFunction> getRoleFunPrivsByRole(Long roleID) {
		String hql="from RolesFunction as rf where rf.roles.roleId=:id";
		Query query = getSession().createQuery(hql);
        query.setParameter("id",roleID);
        return query.list();
	}
	
	public String findFunnameById(Long funID) {
		String hql = "select funcName from DictFunctions f where f.funcId=?";
		return (String) getHibernateTemplate().find(hql, funID).get(0);
	}

	public List<DictFunctions> getFunPrivsByRole(Long roleID) {
		String hql="select rf.functions from RolesFunction as rf where rf.roles.roleId=:id order by rf.functions.parentId";
		Query query = getSession().createQuery(hql);
        query.setParameter("id",roleID);
        return query.list();
	}

	public DictAreas getDictAreaById(Long areaId) {
        return getHibernateTemplate().get(DictAreas.class, areaId);
	}

	public void updateUser(DictUsers user) {
		this.getHibernateTemplate().update(user);
		
	}
}
