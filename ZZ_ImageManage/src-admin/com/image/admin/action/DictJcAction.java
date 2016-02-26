package com.image.admin.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.image.admin.service.DictJcService;
import com.image.admin.service.UsersService;
import com.image.common.pojo.DictAreas;
import com.image.common.pojo.DictJcType;
import com.image.common.pojo.DictJcstype;
import com.image.common.pojo.DictUsers;
import com.image.common.util.PageModel;

public class DictJcAction {
	@Resource(name="dictJcService")
	private DictJcService dictJcService;
	@Resource(name="usersService")
	private UsersService usersService;
	
	private String jcNum;
	private String jcType;
	private DictJcstype dictJcstype;
	
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response=ServletActionContext.getResponse();
	
	/**
	 * 查询车型车号表记录
	 * @return
	 */
	public String queryDictJcByCondition(){
		String areaId = "";
		DictUsers user = (DictUsers) request.getSession().getAttribute("session_user");
		if("".equals(areaId) || null == areaId){
			areaId = user.getAreaId().toString();
		}
		PageModel<DictJcstype> dictJcPm = dictJcService.findDictJcTypeByCondition(jcNum, jcType, areaId);
		List<DictJcType> jcTypeList =  dictJcService.findAllDictJcType();
		List<DictAreas> dictAreaList = usersService.listDictArea();
		request.setAttribute("dictJcPm", dictJcPm);
		request.setAttribute("jcTypeList", jcTypeList);
		request.setAttribute("dictAreaList", dictAreaList);
		return "listDictJc";
	}
	
	/**
	 * 新增车型车号表记录INPUT
	 */
	public String addDictJcInput(){
		List<DictJcType> jcTypeList =  dictJcService.findAllDictJcType();
		List<DictAreas> dictAreaList = usersService.listDictArea();
		request.setAttribute("jcTypeList", jcTypeList);
		request.setAttribute("dictAreaList", dictAreaList);
		return "adddictjcinput";
	}
	
	/**
	 * 新增(修改)车型车号表记录
	 * @throws IOException 
	 */
	public void addDictJc() throws IOException{
		String result = "success";
		String dictJcId = request.getParameter("dictJcId");
		String areaId = request.getParameter("areaId");
		String jcType = request.getParameter("jcType");
		String jcNum = request.getParameter("jcNum");
		String jcIdentify = request.getParameter("jcIdentify");
		try {
			DictJcstype dictJcstype =  new DictJcstype();
			dictJcstype.setAreaId(Integer.parseInt(areaId));
			dictJcstype.setJcStype(jcType);
			dictJcstype.setJcNum(jcNum);
			dictJcstype.setJcIdentify(jcIdentify);
			if(dictJcId != null && !"".equals(dictJcId)){
				dictJcstype.setId(Long.parseLong(dictJcId));
			}
			dictJcService.saveOrUpdateDictJc(dictJcstype);
		} catch (Exception e) {
			e.printStackTrace();
			result = "failure";
		}
		response.setContentType("text/plain");
		response.getWriter().write(result);
	}
	
	
	/**
	 * 删除车型车号表记录
	 * @return
	 * @throws IOException 
	 */
	public void deleteDictJc() throws IOException{
		String result="failure";
		String dictJcIdArray[] = request.getParameter("dictJcs").split(",");
		if (dictJcIdArray.length > 0) {
			dictJcService.delDictJcs(dictJcIdArray);
			result = "success";
		}
		response.setContentType("text/plain");
		response.getWriter().write(result);
	}
	
	
	/**
	 * 修改车型车号表记录INPUT
	 * @return
	 */
	public String updateDictJcInput(){
		String dictJcId = request.getParameter("dictJcId");
		DictJcstype dictJcstype = dictJcService.findDictJcstypeById(dictJcId);
		List<DictJcType> jcTypeList =  dictJcService.findAllDictJcType();
		List<DictAreas> dictAreaList = usersService.listDictArea();
		request.setAttribute("jcTypeList", jcTypeList);
		request.setAttribute("dictAreaList", dictAreaList);
		request.setAttribute("dictJcstype", dictJcstype);
		return "editdictjcinput";
	}
	
	
	/**
	 * 采集仪标志符是否唯一
	 * @return
	 * @throws IOException 
	 */
	public void isExistOfJcIdentify() throws IOException{
		String jcIdentify = request.getParameter("jcIdentify");
		String result = dictJcService.isExistjcIdentify(jcIdentify);
		response.setContentType("text/plain");
		response.getWriter().write(result);
	}
	
	public String getJcNum() {
		return jcNum;
	}

	public void setJcNum(String jcNum) {
		this.jcNum = jcNum;
	}

	public String getJcType() {
		return jcType;
	}

	public void setJcType(String jcType) {
		this.jcType = jcType;
	}

	public DictJcstype getDictJcstype() {
		return dictJcstype;
	}

	public void setDictJcstype(DictJcstype dictJcstype) {
		this.dictJcstype = dictJcstype;
	}
	
}
