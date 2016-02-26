package com.image.admin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.image.admin.service.RolesService;
import com.image.common.pojo.DictFunctions;
import com.image.common.pojo.DictRoles;
import com.image.common.pojo.RolesFunction;


public class RolesAction {

	@Resource(name = "rolesService")
	private RolesService roleService;

	private DictRoles role;
	
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();

	/**
	 * 进入角色管理
	 */
	public String rolesInput() throws Exception {
		List<DictRoles> rolePrivsList = roleService.listDictRoles();
		request.setAttribute("rolePrivsList", rolePrivsList);
		return "roles";
	}

	/**
	 * 进入添加角色
	 */
	public String addRoleInput() throws Exception{
		return "addrole";
	}
	
	/**
	 * 添加角色
	 */
	public String addRole() throws Exception {
		DictRoles roleprivs=roleService.getDictRolesByName(role.getRoleName());
		if(roleprivs==null){
			roleService.saveRole(role);
			request.setAttribute("message", "角色信息添加成功!");
		}else{
			request.setAttribute("message", "角色已经存在,添加失败!");
		}
		
		return rolesInput();
	}

	/**
	 * 删除角色
	 */
	public String deleteRoleById() throws Exception {
		long roleID = Long.parseLong(request.getParameter("roleId"));
		long count = roleService.countUsers(roleID);
		if (count == 0) {
			roleService.deleteRole(roleID);
			request.setAttribute("message", "角色信息删除成功!");
		} else {
			request.setAttribute("message", "该角色存在用户,不能删除!");
		}
		return rolesInput();
	}

	/**
	 * 进入编辑角色
	 */
	public String editRoleInput() {
		long roleID = Long.parseLong(request.getParameter("roleId"));
		DictRoles role = roleService.getDictRolesById(roleID);
		request.setAttribute("role", role);
		return "editrole";
	}

	/**
	 * 编辑角色
	 */
	public String editRole() throws Exception {
		long roleID = Long.parseLong(request.getParameter("roleId"));
		DictRoles rolePrivs = roleService.getDictRolesById(roleID);

		rolePrivs.setRoleName(role.getRoleName());
		rolePrivs.setRolePy(role.getRolePy());
		rolePrivs.setRoleLevel(role.getRoleLevel());
		rolePrivs.setRoleNote(role.getRoleNote());

		roleService.updateRole(rolePrivs);
		request.setAttribute("message", "角色信息修改成功!");
		return rolesInput();
	}
	/**
	 * 进入授权页面
	 */
	public String toPowerInput() throws Exception {
		long roleID = Long.parseLong(request.getParameter("roleId"));
		DictRoles role = roleService.getDictRolesById(roleID);
		//列出功能
		List<DictFunctions>  functionprivs=roleService.listSecFunctionPrivs();
		Map<String,List<DictFunctions>> funMap = new LinkedHashMap<String, List<DictFunctions>>();
		Long parentId;
		String funname=null;
		for(DictFunctions fun:functionprivs){
			parentId=fun.getParentId();
			funname=roleService.findFunnameById(parentId);
			if(funMap.get(funname)==null){
				funMap.put(funname, new ArrayList<DictFunctions>());
			}
			funMap.get(funname).add(fun);
		}
		request.setAttribute("funMap", funMap);
		request.setAttribute("role", role);
		return "topower";
	}
	
	/**
	 * 根据角色Id获得该角色所拥有的功能
	 */
	public String getRoleFunPrivsById() throws Exception {
		long roleID = Long.parseLong(request.getParameter("roleId"));
		List<RolesFunction> rolefun=roleService.getRolesFunctionById(roleID);
		JSONObject obj=new JSONObject();
		JSONArray array=new JSONArray();
		JSONObject obj2=null;
		for(RolesFunction list:rolefun){
			obj2=new JSONObject();
			obj2.put("funId", list.getFunctions().getFuncId());
			array.put(obj2);
		}
		obj.put("funIds", array);
		response.setContentType("text/plain");
		response.getWriter().print(obj.toString());
		return null;
	}
	
	/**
	 * 将角色和功能关联
	 * @throws IOException 
	 */
	public String updateRoleFunPrivs() throws IOException{
		long roleID = Long.parseLong(request.getParameter("roleId"));
		String ids=request.getParameter("ids");
		if(ids!=null){
	    	String[] str=ids.split("-");
	    	roleService.saveRolesFunction(str, roleID);
	    }else{
	    	roleService.saveRolesFunction(new String[]{},roleID);
	    }
	    response.setContentType("text/plain");
		response.getWriter().print("success");
		return null;
	}

	public DictRoles getRole() {
		return role;
	}

	public void setRole(DictRoles role) {
		this.role = role;
	}
	

}
