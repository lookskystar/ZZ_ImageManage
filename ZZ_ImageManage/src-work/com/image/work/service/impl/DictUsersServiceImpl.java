package com.image.work.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.image.common.pojo.DictAreas;
import com.image.common.pojo.DictFunctions;
import com.image.common.pojo.DictUsers;
import com.image.common.pojo.RolesFunction;
import com.image.work.dao.DictUsersDao;
import com.image.work.service.DictUsersService;

public class DictUsersServiceImpl implements DictUsersService {
	
	@Resource(name="dictUsersDao")
	private DictUsersDao dictUsersDao;

	public DictAreas getDictAreasById(Long areaId) {
		return dictUsersDao.getDictAreaById(areaId);
	}

	public DictUsers getUser(String username, String password) {
		return dictUsersDao.getUser(username, password);
	}

	public DictUsers login(String username, String password) {
		return dictUsersDao.login(username, password);
	}

	public DictUsers login(String idkid) {
		return dictUsersDao.login(idkid);
	}

	public void updateUser(DictUsers user) {
		dictUsersDao.updateUser(user);
		
	}
	
	public Map<String, List<RolesFunction>> getRolesFunctionByUser(
			DictUsers user) {
		Map<String, List<RolesFunction>> menu=new HashMap<String, List<RolesFunction>>();
		List<RolesFunction> rfList=null;
        rfList=dictUsersDao.getRoleFunPrivsByRole(user.getRoleId());//获取角色功能表
		List<RolesFunction> subMenu;
		RolesFunction rf=null;
		DictFunctions secFun=null;
		String mainName=null;
		for(int j=0;j<rfList.size();j++){
			rf=rfList.get(j);
			secFun=rf.getFunctions();
			mainName=dictUsersDao.findFunnameById(secFun.getFuncId());
			if(menu.get(mainName)==null){
				subMenu=new ArrayList<RolesFunction>();
				menu.put(mainName, subMenu);
				subMenu.add(rf);
			}else{
				subMenu=menu.get(mainName);
				if(!contain(subMenu, rf)){
					subMenu.add(rf);
				}
			}
			
		}  
		
		return menu;
	}
	
	private  boolean contain(List<RolesFunction> list,RolesFunction rf){
		RolesFunction r=null;
		for(int i=0;i<list.size();i++){
			r=list.get(i);
			if(r.getFunctions().getFuncId().equals(rf.getFunctions().getFuncId())){
				return true;
			}
		}
		 return false;
		}

	public Map<String, List<DictFunctions>> getDictFunctionsByUser(DictUsers user) {
		Map<String, List<DictFunctions>> funMap = new LinkedHashMap<String, List<DictFunctions>>();
		//列出功能
		List<DictFunctions>  functionprivs=dictUsersDao.getFunPrivsByRole(user.getRoleId());
		Long parentId;
		String funname=null;
		for(DictFunctions fun:functionprivs){
			parentId=fun.getParentId();
			funname=dictUsersDao.findFunnameById(parentId);
			if(funMap.get(funname)==null){
				funMap.put(funname, new ArrayList<DictFunctions>());
			}
			funMap.get(funname).add(fun);
		}
		return funMap;
	}
}
