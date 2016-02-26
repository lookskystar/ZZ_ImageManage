package com.image.common.util;

import com.image.common.pojo.DictUsers;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SessionInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4756613723246123058L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext=invocation.getInvocationContext();
		Class clazz=invocation.getAction().getClass();
		String clazzName=clazz.getName();
		DictUsers user=(DictUsers)actionContext.getSession().get("session_user");
		if(clazzName.equals("com.image.work.action.LoginAction")
				||clazzName.equals("com.image.work.action.FtpValidateAction")
				||clazzName.equals("com.image.query.action.QueryAction")||user!=null){
			return invocation.invoke();
		}
		//如果session失效，返回登录页面
		return "redirect_login";
	}

}
