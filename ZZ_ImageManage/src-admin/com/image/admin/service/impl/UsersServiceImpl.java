package com.image.admin.service.impl;


import java.util.List;

import javax.annotation.Resource;

import com.image.admin.dao.UsersDao;
import com.image.admin.service.UsersService;
import com.image.common.pojo.DictAreas;
import com.image.common.pojo.DictRoles;
import com.image.common.pojo.DictTeams;
import com.image.common.pojo.DictUsers;
import com.image.common.util.PageModel;

public class UsersServiceImpl implements UsersService {
	
	@Resource(name="usersDao")
	private UsersDao usersDao;

	public long countUser(long teamId) {
		long count=usersDao.countUser(teamId);
		return count;
	}

	public String delDictTeams(long teamId) {
		usersDao.delDictTeams(teamId);
		return "success";
		
	}

	public String delDictUsers(String[] userIdArray) {
		for (int i = 0; i < userIdArray.length; i++) {
			usersDao.delDictUsers(Long.parseLong(userIdArray[i]));
		}
		return "success";
	}

	public PageModel<DictUsers> findDictUsers(Long areaId, String name,
			Long teamId) {
		return usersDao.findDictUsers(areaId, name, teamId);
	}

	public DictRoles getDictRolesById(long roleId) {
		return usersDao.getDictRolesById(roleId);
	}

	public DictTeams getDictTeamsById(long teamId) {
		return usersDao.getDictTeamsById(teamId);
	}

	public DictTeams getDictTeamsByName(String bzName,Long areaId) {
		return usersDao.getDictTeamsByName(bzName,areaId);
	}

	public DictUsers getDictUsersById(long userId) {
		return usersDao.getDictUsersById(userId);
	}

	public List<DictAreas> listDictArea() {
		
		return usersDao.listDictArea();
	}
	
	public DictAreas getDictArea(Long areaId){
		return usersDao.getDictArea(areaId);
	}
	
	public List<DictTeams> listDictTeams(Long areaId) {
		return usersDao.listDictTeams(areaId);
	}

	public List<DictRoles> listDictRoles() {
		return usersDao.listDictRoles();
	}

	public void saveDictTeams(DictTeams dictteams) {
	    usersDao.saveDictTeams(dictteams);
		
	}

	public void saveDictUsers(DictUsers dictusers) {
		usersDao.saveDictUsers(dictusers);
		
	}

	public void updateDictTeams(DictTeams dictteams) {
		usersDao.updateDictTeams(dictteams);
		
	}

	public void updateDictUsers(DictUsers dictusers) {
		usersDao.updateDictUsers(dictusers);
		
	}

	public String updateUserBz(String[] userIdArray, int bzid) {
		for (int i = 0; i < userIdArray.length; i++) {
			usersDao.updateUserBz(Long.parseLong(userIdArray[i]), bzid);
		}
		return  "success";
		
	}

}
