package com.image.work.service;

import java.util.List;
import java.util.Map;

import com.image.common.pojo.DictAreas;
import com.image.common.pojo.DictFunctions;
import com.image.common.pojo.DictUsers;
import com.image.common.pojo.RolesFunction;

public interface DictUsersService {

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
	 * 通过用户，获取角色功能列表
	 * @param user
	 * @return
	 */
	public Map<String,List<RolesFunction>> getRolesFunctionByUser(DictUsers user);
	
	/**
	 * 通过用户，获取功能列表
	 * @param user
	 * @return
	 */
	public Map<String,List<DictFunctions>> getDictFunctionsByUser(DictUsers user);
	
	/**
	 * 根据地区ID查询地区列表
	 * @param roleID
	 * @return
	 */
	public DictAreas  getDictAreasById(Long areaId);
	
	/**
	 * 修改密码
	 * */
	public void updateUser(DictUsers user);
	
}
