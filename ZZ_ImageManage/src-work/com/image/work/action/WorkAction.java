package com.image.work.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.image.common.pojo.DictUsers;
import com.image.common.pojo.DictWorktype;
import com.image.query.service.QueryService;
import com.image.set.service.PresetImageService;

public class WorkAction {
	@Resource(name="queryService")
	private QueryService queryService;
	@Resource(name = "presetImageService")
	private PresetImageService presetImageService;
	private String jcType;
	private String jcNum;
	private String taskDate;
	
	private  HttpServletRequest request = ServletActionContext.getRequest();
	private  HttpServletResponse response = ServletActionContext.getResponse();
	public static final SimpleDateFormat YMD_SDFORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public String workInput(){
		String areaId = request.getParameter("areaId");
		DictUsers user = (DictUsers) request.getSession().getAttribute("session_user");
		if("".equals(areaId) || null == areaId){
			areaId = user.getAreaId().toString();
		}
		List<String> jcTypeList = presetImageService.findJcstypeAll();
		List<DictWorktype> workTypeList = queryService.findAllDictWorktype();
		if(taskDate==null || taskDate==""){
			taskDate = YMD_SDFORMAT.format(new Date());
		}
		List<Map<String, String>> uploadRecList = queryService.findAllUploadRecOnJc(jcType, jcNum, taskDate, areaId, null); 
		request.setAttribute("uploadRecList", uploadRecList);
		request.setAttribute("workTypeList", workTypeList);
		request.setAttribute("jcTypeList", jcTypeList);
		return "work_input";
	}

	public String getJcType() {
		return jcType;
	}

	public void setJcType(String jcType) {
		this.jcType = jcType;
	}

	public String getJcNum() {
		return jcNum;
	}

	public void setJcNum(String jcNum) {
		this.jcNum = jcNum;
	}

	public String getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}
}
