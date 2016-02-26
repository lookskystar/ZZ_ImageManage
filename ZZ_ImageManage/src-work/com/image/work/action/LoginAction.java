package com.image.work.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.image.common.pojo.DictAreas;
import com.image.common.pojo.DictUsers;
import com.image.work.service.DictUsersService;


/**
 * 用户登陆Action
 * @author lll
 *
 */
public class LoginAction {

	@Resource(name="dictUsersService")
	private DictUsersService dictUsersService;
	
	private HttpServletRequest request=ServletActionContext.getRequest();
	private DictUsers user;
	
	/**
	 * 用户登陆
	 * @return
	 */
	public String login(){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String idkid = request.getParameter("idkid");
		
		if(idkid!=null && !"".equals(idkid)){
			user = dictUsersService.login(idkid);
			if(user == null){
				request.setAttribute("loginError", "读卡错误，请确认IC卡");
				return "loginOut";
			}
		}else{
			user = dictUsersService.login(username,password);
			if(user == null){
				request.setAttribute("loginError", "你输入的用户名或密码错误，请重新输入");
				return "loginOut";
			}
		}
		request.getSession().setAttribute("session_user", user);
		if(user.getAreaId()!=null){
			DictAreas dictarea=dictUsersService.getDictAreasById(user.getAreaId());
			if(dictarea!=null){
				request.getSession().setAttribute("session_ftp_url",
						dictarea.getFtpIp());
				request.getSession().setAttribute("session_ftp_port", dictarea.getFtpPort());
				request.getSession().setAttribute("session_ftp_username", dictarea.getFtpUsername());
				request.getSession().setAttribute("session_ftp_password", dictarea.getFtpPassword());
			}
		}
		return "main";
	}
	
	/**
	 * 用户退出
	 * @return
	 */
	public String loginOut(){
		request.getSession().removeAttribute("session_user");
		request.getSession().setAttribute("session_user", null);
		return "loginOut";
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	public String updatePwd(){
		HttpServletResponse response=ServletActionContext.getResponse();
		String new_pwd=request.getParameter("new_pwd");
		DictUsers user = (DictUsers) request.getSession().getAttribute("session_user");
		String result="failure";
		if(user==null){
			result="session_null";
		}else{
			user.setPassword(new_pwd);
			dictUsersService.updateUser(user);
			result="success";
		}
		response.setContentType("text/plain");
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取服务器时间
	 * @throws Exception 
	 */
	public String getServerTime() throws Exception{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/hh:mm:ss");
		String timeStr = dateFormat.format(new Date());
		ServletActionContext.getResponse().getWriter().print(timeStr);
		return null;
	}
}
