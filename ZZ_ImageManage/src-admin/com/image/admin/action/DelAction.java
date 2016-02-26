package com.image.admin.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.enterprisedt.net.ftp.FileTransferClient;
import com.image.admin.service.DelService;
import com.image.admin.service.UsersService;
import com.image.common.pojo.DelRec;
import com.image.common.pojo.DictAreas;
import com.image.common.util.FTPUnit;
import com.image.common.util.PageModel;

public class DelAction {

	@Resource(name="delService")
	private DelService delService;
	@Resource(name="usersService")
	private UsersService usersService;
	
	private HttpServletRequest request = ServletActionContext.getRequest();
	
	/**
	 * 进入文件删除页面
	 * */
	public String delListInput(){
		PageModel<DelRec> dels = delService.findDelList();
		request.setAttribute("dels", dels);
		request.setAttribute("dictAreaList", usersService.listDictArea());
		return "delListInput";
	}
	
	/**
	 * 删除记录
	 * */
	public String deleteRec(){
		Boolean flag = true;
		
		String btime = request.getParameter("btime");
		String etime = request.getParameter("etime");
		long areaId = Long.parseLong(request.getParameter("areaid"));
		try{
			 delService.deleteRec(btime, etime ,areaId);
		}catch(Exception e){
			flag = false;
			request.setAttribute("message", "删除异常,没有记录！");
		}
		if(flag){
			try{
				int begin = Integer.parseInt(btime.replace("-", ""));
				int end = Integer.parseInt(etime.replace("-", ""));
				DictAreas areas = usersService.getDictArea(areaId);
				String[] arr = new String[end-begin+1];
				int j=0;
				for (int i = begin; i<=end; i++) {
					arr[j++] = i+"";
				}
				FileTransferClient ftpCilent = FTPUnit.getFileTransferClient(areas.getFtpIp(), areas.getFtpPort(), areas.getFtpUsername(), areas.getFtpPassword());
				FTPUnit.delete(arr, 1, ftpCilent);
				FTPUnit.closeFileTransferClient(ftpCilent);
				
				request.setAttribute("message", "文件记录删除成功！");
			}catch(Exception e){
				e.printStackTrace();
				request.setAttribute("message", "删除异常,文件不存在！");
			}
		}
		return delListInput();
	}
}
