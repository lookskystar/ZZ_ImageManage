package com.image.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.image.admin.dao.RolesDao;
import com.image.admin.service.RolesService;
import com.image.common.pojo.DictFunctions;
import com.image.common.pojo.DictRoles;
import com.image.common.pojo.RolesFunction;


public class RolesServiceImpl implements RolesService {

	@Resource(name = "rolesDao")
	private RolesDao rolesDao;

	public long countUsers(long roleID) {
		long count=rolesDao.countUsers(roleID);
		return count;
	}

	public String deleteRole(long roleID) {
		rolesDao.deleteRole(roleID);
		return "success";
		
	}

	public DictRoles getDictRolesById(long roleID) {
		return rolesDao.getDictRolesById(roleID);
	}

	public DictRoles getDictRolesByName(String roleName) {
		return rolesDao.getDictRolesByName(roleName);
	}

	public List<DictRoles> listDictRoles() {
		
		return rolesDao.listDictRoles();
	}

	public void saveRole(DictRoles dictroles) {
		rolesDao.saveRole(dictroles);
		
	}

	public void updateRole(DictRoles dictroles) {
		rolesDao.updateRole(dictroles);
		
	}

	public List<DictFunctions> listMainFunctionPrivs() {
		return rolesDao.listMainFunctionPrivs();
	}

	public List<DictFunctions> listSecFunctionPrivs() {
		return rolesDao.listSecFunctionPrivs();
	}

	public String findFunnameById(Long id) {
		return rolesDao.findFunnameById(id);
	}

	public void saveRolesFunction(String[] ids,Long roleID) {
		RolesFunction rolefun=null;
        rolesDao.deleteRoleFunPrivs(roleID);
        for(String s:ids){
        	rolefun=new RolesFunction();
        	DictFunctions functionprivs=new DictFunctions();
        	functionprivs.setFuncId(Long.parseLong(s));
        	rolefun.setFunctions(functionprivs);
        	DictRoles dictrole=new DictRoles();
        	dictrole.setRoleId(roleID);
        	rolefun.setRoles(dictrole);
        	rolesDao.saveRoleFunPrivs(rolefun);
        }
	}

	public List<RolesFunction> getRolesFunctionById(long roleID) {
		return rolesDao.getRolesFunctionById(roleID);
	}

}
