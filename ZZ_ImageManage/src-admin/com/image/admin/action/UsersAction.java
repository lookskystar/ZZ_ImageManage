package com.image.admin.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.image.admin.service.UsersService;
import com.image.common.pojo.DictTeams;
import com.image.common.pojo.DictUsers;
import com.image.common.util.PageModel;



public class UsersAction{
	@Resource(name="usersService")
	private UsersService usersService;
	
	private DictTeams dictteams;
	private DictUsers dictUsers;
	private Long areaId;
	private String name;
	private Long teamId;
	
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response=ServletActionContext.getResponse();
	
	
	/**
	 * 进入用户管理页面
	 */
	public String usersInput() throws Exception {
		DictUsers user = (DictUsers) request.getSession().getAttribute("session_user");
		request.setAttribute("dictTeamsList", usersService.listDictTeams(user.getAreaId()));//查询班组
		request.setAttribute("rolePrivsList",usersService.listDictRoles());//查询角色
		return "userindex";
	}
	
	/**
	 * 查询班组用户
	 */
	public String listUsers() throws Exception{
		DictUsers user = (DictUsers) request.getSession().getAttribute("session_user");
		PageModel<DictUsers> pm=usersService.findDictUsers(user.getAreaId(), name, teamId);
		List<DictUsers> usersPrivsList=null;
		request.setAttribute("pm", pm);
		request.setAttribute("usersPrivsList", usersPrivsList);
		request.setAttribute("dictAreaList", usersService.listDictArea());
		request.setAttribute("rolePrivsList",usersService.listDictRoles());
		request.setAttribute("teamId", teamId);
		return "users";
	}
	
	/**
	 * 进入新增班组
	 */
	public String addDictTeamsInput() throws Exception {
		
		return "adddictteams";
	}

	/**
	 * 新增班组
	 * @return
	 * @throws Exception
	 */
	public String addDictTeams() throws Exception{
		DictUsers user = (DictUsers) request.getSession().getAttribute("session_user");
		DictTeams dictTeams=usersService.getDictTeamsByName(dictteams.getTeamName(),user.getAreaId());
		if(dictTeams==null){
			dictteams.setAreaId(user.getAreaId());
			usersService.saveDictTeams(dictteams);
			request.setAttribute("message", "班组添加成功");
		}else{
			request.setAttribute("message", "班组已经存在,添加失败");
		}
		return usersInput();
	}
	
	/**
	 * 进入编辑班组
	 */
	public String editDictTeamsInput() throws Exception {
		DictTeams dictteams=usersService.getDictTeamsById(Long.valueOf(request.getParameter("teamId")));
		request.setAttribute("dictteams", dictteams);
		return "editdictteams";
	}
	
	/**
	 * 编辑班组
	 * @return
	 * @throws Exception
	 */
	public String editDictTeams() throws Exception{
		DictTeams teams=usersService.getDictTeamsById(Long.valueOf(request.getParameter("teamId")));
		teams.setTeamName(dictteams.getTeamName());
		teams.setTeamLevel(dictteams.getTeamLevel());
		usersService.updateDictTeams(teams);
		request.setAttribute("message", "班组修改成功");
		return usersInput();
	}
	
	/**
	 * 删除班组
	 */
	public String delDictTeams() throws Exception {
		long teamId = Long.parseLong(request.getParameter("teamId"));
		long count = usersService.countUser(teamId);
		if (count == 0) {
			usersService.delDictTeams(teamId);
			request.setAttribute("message", "班组信息删除成功");
		} else {
			request.setAttribute("message", "该班组存在用户不能删除");
		}
		return usersInput();
	}
	
	/**
	 * 删除用户
	 * @return
	 * @throws Exception
	 */
	public String delUsersPrivs() throws Exception {
		String result="failure";
		String userIdArray[] = ServletActionContext.getRequest().getParameter("users").split(",");
		if (userIdArray.length > 0) {
			usersService.delDictUsers(userIdArray);
			result = "success";
		}
		response.setContentType("text/plain");
		response.getWriter().write(result);
		return null;
	}
	
	/**
	 * 进入更改用户班组
	 */
	public String updateUserBzInput() throws Exception {
		String userIds= request.getParameter("userIds");
		DictUsers user = (DictUsers) request.getSession().getAttribute("session_user");
		request.setAttribute("dictTeamList", usersService.listDictTeams(user.getAreaId()));
		request.setAttribute("userIds", userIds);
		return "updateuserbz";
	}

	/**
	 * 更改用户班组
	 */
	public String updateUserBz() throws Exception {
		//参数
		String userIds = request.getParameter("userIds");
		String bzid = request.getParameter("bzid");
		String result="failure";
		String userIdArray[] =userIds.split(",");
		if(userIdArray.length>0){
			usersService.updateUserBz(userIdArray, Integer.parseInt(bzid));
		    result="success";
		  }
		response.setContentType("text/plain");
		response.getWriter().write(result);
		request.setAttribute("message", "用户班组信息修改成功");
		return usersInput();
	}
	
	/**
	 * 进入查看用户详情
	 */
	public String userInfoListInput() throws Exception {
		DictUsers user=usersService.getDictUsersById(Long.valueOf(request.getParameter("userId")));
		request.setAttribute("dictTeamsList", usersService.listDictTeams(user.getAreaId()));
		request.setAttribute("dictAreaList", usersService.listDictArea());
		request.setAttribute("rolePrivsList",usersService.listDictRoles());
		request.setAttribute("user", user);
		return "userinfo";
	}
	
	/**
	 * 进入新增用户
	 */
	public String addUserPrivsInput() throws Exception {
		DictUsers user = (DictUsers) request.getSession().getAttribute("session_user");
		request.setAttribute("dictTeamsList", usersService.listDictTeams(user.getAreaId()));
		request.setAttribute("rolePrivsList",usersService.listDictRoles());
		request.setAttribute("dictAreaList", usersService.listDictArea());
		return "adduser";
	}
	
	/**
	 * 新增用户
	 * @return
	 * @throws Exception
	 */
	public String addUserPrivs() throws Exception{
		try{
			DictUsers user = (DictUsers) request.getSession().getAttribute("session_user");
			dictUsers.setAreaId(user.getAreaId());
			dictUsers.setIsuser(1);
			usersService.saveDictUsers(dictUsers);
			request.setAttribute("message", "用户信息添加成功");
		}catch (Exception e) {
			request.setAttribute("message", "用户信息添加失败");
		}
		return usersInput();
	}
	
	
	/**
	 * 进入编辑用户
	 */
	public String editUserPrivsInput() throws Exception {
		DictUsers user=usersService.getDictUsersById(Long.valueOf(request.getParameter("userId")));
		request.setAttribute("dictTeamsList", usersService.listDictTeams(user.getAreaId()));
		request.setAttribute("dictAreaList", usersService.listDictArea());
		request.setAttribute("rolePrivsList",usersService.listDictRoles());
		request.setAttribute("user", user);
		return "edituser";
	}
	
	/**
	 * 编辑用户
	 * @throws Exception
	 */
	public String editUserPrivs() throws Exception{
		Long userId = Long.valueOf(request.getParameter("userId"));
		//获得当前的UsersPrivs
		DictUsers user = usersService.getDictUsersById(userId);
		
		user.setUsername(dictUsers.getUsername());
		user.setPassword(dictUsers.getPassword());
		user.setRoleId(dictUsers.getRoleId());
		user.setName(dictUsers.getName());
		user.setTeamId(dictUsers.getTeamId());
		user.setGonghao(dictUsers.getGonghao());
		user.setPy(dictUsers.getPy());
		user.setIdnum(dictUsers.getIdnum());
		
		usersService.updateDictUsers(user);
		request.setAttribute("message", "用户信息编辑成功");
		return usersInput();
	}
	
	
	
	public DictTeams getDictteams() {
		return dictteams;
	}

	public void setDictteams(DictTeams dictteams) {
		this.dictteams = dictteams;
	}

	public DictUsers getDictUsers() {
		return dictUsers;
	}

	public void setDictUsers(DictUsers dictUsers) {
		this.dictUsers = dictUsers;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}


	
}
