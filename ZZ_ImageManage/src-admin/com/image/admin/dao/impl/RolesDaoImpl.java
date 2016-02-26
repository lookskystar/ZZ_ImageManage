package com.image.admin.dao.impl;

import java.util.List;

import com.image.admin.dao.RolesDao;
import com.image.common.pojo.DictFunctions;
import com.image.common.pojo.DictRoles;
import com.image.common.pojo.RolesFunction;
import com.image.common.util.AbstractDao;


public class RolesDaoImpl extends AbstractDao implements RolesDao {

	public void saveRole(DictRoles dictroles) {
		getHibernateTemplate().save(dictroles);
	}


	public void updateRole(DictRoles dictroles) {
        getHibernateTemplate().update(dictroles);
	}
	
	public void deleteRole(long roleID) {
		String hql = "delete from DictRoles r where r.roleId=?";
		this.getSession().createQuery(hql).setLong(0, roleID).executeUpdate();
	}

	
	public long countUsers(long roleID) {
		String hql = "select count(*) from  DictUsers d where d.roleId =?";
		return (Long) getHibernateTemplate().find(hql, roleID).get(0);
	}

	
	public DictRoles getDictRolesById(long roleID) {
		return getHibernateTemplate().get(DictRoles.class,roleID);
	}


	public DictRoles getDictRolesByName(String roleName) {
		String hql = "from DictRoles d where d.roleName=?";
		List list=getHibernateTemplate().find(hql,roleName);
		if(list!=null&&list.size()>0){
			return (DictRoles)list.get(0);
		}
		return null;
	}


	@SuppressWarnings("unchecked")
	public List<DictRoles> listDictRoles() {
		String hql = "from DictRoles  order by roleId";
		return getHibernateTemplate().find(hql);
	}


	@SuppressWarnings("unchecked")
	public List<DictFunctions> listMainFunctionPrivs() {
		String hql = "from DictFunctions where parentId is null ";
		return getHibernateTemplate().find(hql);
	}


	@SuppressWarnings("unchecked")
	public List<DictFunctions> listSecFunctionPrivs() {
		String hql = "from DictFunctions where parentId is  not null order by funcId asc";
		return getHibernateTemplate().find(hql);
	}


	public String findFunnameById(Long id) {
		String hql = "select funcName from DictFunctions f where f.funcId=?";
		return (String) getHibernateTemplate().find(hql, id).get(0);
	}


	public void saveRoleFunPrivs(RolesFunction rolefunprivs) {
		getHibernateTemplate().saveOrUpdate(rolefunprivs);
		
	}


	public void deleteRoleFunPrivs(long roleID) {
		String hql = "delete from RolesFunction r where r.roles.roleId=?";
		this.getSession().createQuery(hql).setLong(0, roleID).executeUpdate();
		
	}


	@SuppressWarnings("unchecked")
	public List<RolesFunction> getRolesFunctionById(long roleID) {
		String hql="from RolesFunction rf where rf.roles.roleId=?";
		return getHibernateTemplate().find(hql,roleID);
	}

	
}
