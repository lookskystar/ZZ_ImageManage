package com.image.work.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.image.work.service.FtpValidateService;

/**
 * 用于FTP上传 异步判断
 * @author Administrator
 *
 */
public class FtpValidateAction {
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	
	@Resource(name="ftpValidateService")
	private FtpValidateService ftpValidateService;
	
	/**
	 * Ajax判断上传信息
	 */
	public String validate() throws Exception {
		String gh = request.getParameter("gh");//工号
		String ch = request.getParameter("ch");//车号
		String gx = request.getParameter("gx");//工序
		
		int result = 0;
		if(gh!=null && !"".equals(gh)){
			result = ftpValidateService.validate(1, gh);
		}
		if(ch!=null && !"".equals(ch)){
			result = ftpValidateService.validate(2, ch);
		}
		if(gx!=null && !"".equals(gx)){
			result = ftpValidateService.validate(3, gx);
		}
		response.setContentType("text/plain");
		response.getWriter().print(result);
		return null;
	}
}
