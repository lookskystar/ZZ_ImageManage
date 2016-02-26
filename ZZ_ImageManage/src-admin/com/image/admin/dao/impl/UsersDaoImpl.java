package com.image.admin.dao.impl;


import java.util.ArrayList;
import java.util.List;

import com.image.admin.dao.UsersDao;
import com.image.common.pojo.DictAreas;
import com.image.common.pojo.DictRoles;
import com.image.common.pojo.DictTeams;
import com.image.common.pojo.DictUsers;
import com.image.common.util.AbstractDao;
import com.image.common.util.PageModel;



public class UsersDaoImpl extends AbstractDao implements
		UsersDao {

	public long countUser(long teamId) {
		String hql = "select count(*) from DictUsers u where u.teamId=?";
		return (Long) getHibernateTemplate().find(hql, teamId).get(0);
	}

	public void delDictTeams(long teamId) {
		String hql = "delete from DictTeams d where d.teamId=?";
		getSession().createQuery(hql).setLong(0, teamId).executeUpdate();
		
	}

	public void delDictUsers(long userId) {
		String hql = "update from DictUsers u set u.isuser=0 where  u.userId=?";
		getSession().createQuery(hql).setLong(0, userId).executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	public PageModel<DictUsers> findDictUsers(Long areaId,String name,Long teamId) {
		StringBuilder builder=new StringBuilder();
		builder.append("from DictUsers t where t.isuser=1");
		List<Object> params=new ArrayList<Object>();
		if(areaId!=null&&!areaId.equals("")){
			builder.append("  and t.areaId = ?");
			params.add(areaId);
		}
		if(name!=null&&!name.equals("")){
			builder.append("  and t.name like ?");
			params.add("%"+name+"%");
		}
		if(teamId!=null&&!teamId.equals("")){
			builder.append("  and t.teamId = ?");
			params.add(teamId);
		}
		builder.append(" order by t.userId");
		return findPageModel(builder.toString(),params.toArray());
	}

	public DictTeams getDictTeamsById(long teamId) {
		return getHibernateTemplate().get(DictTeams.class, teamId);
	}

	@SuppressWarnings("unchecked")
	public DictTeams getDictTeamsByName(String bzName,Long areaId) {
		String hql = "from DictTeams d where d.teamName=? and d.areaId="+areaId;
		List list=getHibernateTemplate().find(hql,bzName);
		if(list!=null&&list.size()>0){
			return (DictTeams)list.get(0);
		}
		return null;
	}

	public DictRoles getDictRolesById(long roleId) {
		return getHibernateTemplate().get(DictRoles.class,roleId);
	}

	public DictUsers getDictUsersById(long userId) {
		return getHibernateTemplate().get(DictUsers.class,userId);
	}

	@SuppressWarnings("unchecked")
	public List<DictAreas> listDictArea() {
		return getHibernateTemplate().find("from DictAreas d");
	}
	
	public DictAreas getDictArea(Long areaId){
		return getHibernateTemplate().get(DictAreas.class, areaId);
	}

	@SuppressWarnings("unchecked")
	public List<DictTeams> listDictTeams(Long areaId) {
		return getHibernateTemplate().find("from DictTeams d where d.areaId="+areaId +" order by d.teamName");
	}

	@SuppressWarnings("unchecked")
	public List<DictRoles> listDictRoles() {
		String hql = "from DictRoles d ";
		return getHibernateTemplate().find(hql);
	}


	public void saveDictTeams(DictTeams dictteams) {
		getHibernateTemplate().save(dictteams);
		
	}

	public void saveDictUsers(DictUsers dictusers) {
		getHibernateTemplate().save(dictusers);
		
	}

	public void updateDictTeams(DictTeams dictteams) {
		getHibernateTemplate().update(dictteams);
		
	}

	public void updateDictUsers(DictUsers dictusers) {
		getHibernateTemplate().update(dictusers);
		
	}

	public void updateUserBz(long userId, int bzid) {
		String hql = "update DictUsers u set u.teamId=:bzid where u.userId=:userId";
		getSession().createQuery(hql).setLong("userId", userId).setInteger("bzid", bzid).executeUpdate();
		
	}

	
}
