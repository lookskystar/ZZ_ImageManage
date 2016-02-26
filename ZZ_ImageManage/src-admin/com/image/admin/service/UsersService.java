package com.image.admin.service;

import java.util.List;

import com.image.common.pojo.DictAreas;
import com.image.common.pojo.DictRoles;
import com.image.common.pojo.DictTeams;
import com.image.common.pojo.DictUsers;
import com.image.common.util.PageModel;

public interface UsersService {
	
	/**
	 * 根据条件查询人员信息，分页
	 * @return
	 */
	public PageModel<DictUsers> findDictUsers(Long areaId,String name,Long teamId);
	
	/**
	 * 查询本地区所有班组列表
	 */
	public List<DictTeams> listDictTeams(Long areaId);
	
	/**
	 * 查询所有地区
	 */
	public List<DictAreas> listDictArea();
	
	/**
	 * 根据ID查询地区信息
	 */
	public DictAreas getDictArea(Long areaId);
	
	/**
	 * 查询所有角色
	 * */
	public List<DictRoles> listDictRoles();
	
	/**
	 * 新增班组
	 */
	public void saveDictTeams(DictTeams dictteams);
	
	/**
	 * 通过ID查询班组信息
	 * @param DictTeams id
	 */
	public DictTeams getDictTeamsById(long teamId);
	
	/**
	 * 修改班组
	 */
	public void updateDictTeams(DictTeams dictteams);
	
	/**
	 * 删除班组
	 */
	public String delDictTeams(long teamId);
	
	/**
	 * 查询班组下是否有用户
	 */
	public long countUser(long teamId);
	
	/**
	 * 删除用户 
	 */
	public String delDictUsers(String[]  userIdArray);
	
	
	/**
	 * 更改用户班组
	 */
	public String updateUserBz(String[] userIdArray,int bzid);
	
	/**
	 * 根据班组查询班组对象
	 * */
	public DictTeams getDictTeamsByName(String bzName,Long areaId); 
	
	/**
	 * 根据用户ID查询用户信息
	 */
	public DictUsers getDictUsersById(long userId);
	
	/**
	 * 根据角色ID查询角色信息
	 */
	public DictRoles getDictRolesById(long roleId);
	
	
	/**
	 * 新增用户
	 */
	public void saveDictUsers(DictUsers dictusers);
	
	
	/**
	 * 修改用户
	 */
	public void updateDictUsers(DictUsers dictusers);
	
}
