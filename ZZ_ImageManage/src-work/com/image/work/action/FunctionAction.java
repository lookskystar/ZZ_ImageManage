package com.image.work.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.image.common.pojo.DictFunctions;
import com.image.common.pojo.DictUsers;
import com.image.work.service.DictUsersService;

/**
 * 用户权限读取
 * @author lll
 *
 */
public class FunctionAction {
	@Resource(name="dictUsersService")
	private DictUsersService dictUsersService;
	
	private HttpServletRequest request=ServletActionContext.getRequest();
	
	public String getUserFuncs() throws Exception {
		DictUsers user = (DictUsers) request.getSession().getAttribute("session_user");
		Map<String,List<DictFunctions>> funMap=dictUsersService.getDictFunctionsByUser(user);
		request.setAttribute("funMap", funMap);
		return "left";
	}
}
