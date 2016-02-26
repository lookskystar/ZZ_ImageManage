package com.image.work.dao;

import java.util.List;


import com.image.common.pojo.DictAreas;
import com.image.common.pojo.DictFunctions;
import com.image.common.pojo.DictUsers;
import com.image.common.pojo.RolesFunction;

public interface DictUsersDao {

	/**
	 * 用户登陆
	 */
	DictUsers login(String username, String password);
	
	/**
	 * 用户刷卡登陆
	 * @param idkid 卡号
	 * @return
	 */
	DictUsers login(String idkid);
	
	/**
	 * 根据用户名和密码查询一个用户
	 */
	public DictUsers getUser(String username,String password);
	
	
	/**
	 * 根据角色ID查询角色功能列表
	 */
	public List<RolesFunction>  getRoleFunPrivsByRole(Long roleID);
	
	/**
	 * 根据角色ID查询功能列表
	 * @param roleID
	 * @return
	 */
	public List<DictFunctions>  getFunPrivsByRole(Long roleID);
	
	/**
	 * 根据角色ID查询功能名y
	 * */
	public String findFunnameById(Long funID);
	
	/**
	 * 根据地区ID查询地区列表
	 * @param roleID
	 * @return
	 */
	public DictAreas  getDictAreaById(Long areaId);
	
	/**
	 * 修改密码
	 * */
	public void updateUser(DictUsers user);
	
}
