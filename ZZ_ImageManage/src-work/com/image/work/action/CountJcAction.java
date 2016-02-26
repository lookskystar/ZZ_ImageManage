package com.image.work.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.image.common.pojo.CountJcBean;
import com.image.common.pojo.DictUsers;
import com.image.work.service.CountJcService;



/**
 * 统计上传
 * @author T
 */
public class CountJcAction {

	@Resource(name="countJcService")
	private CountJcService countJcService;
	
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response=ServletActionContext.getResponse();
	private static final SimpleDateFormat YMD_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	/*
	 * 统计上传车组数
	 */
	public String CountJc(){
		DictUsers user = (DictUsers) request.getSession().getAttribute("session_user");
		String date = request.getParameter("date");
		
		if(date==null || "".equals(date)){
			date = YMD_FORMAT.format(new Date());
		}
		
		List<Object[]> listAll = countJcService.findJcRec(user.getUserId(), date);
		List<Object[]> listBad = countJcService.findBadJcRec(user.getUserId(), date);
		List<CountJcBean> list = new ArrayList<CountJcBean>();
		
		try {
			if(listAll != null && listBad != null){
				for(int i=0;i< listAll.size();i++){
					Object[] newBean = ((Object[])listAll.get(i));
					CountJcBean countJC = new CountJcBean();
					countJC.setArea_name((String) newBean[0]);
					countJC.setTotalNum(Integer.parseInt(newBean[1].toString()));
					countJC.setArea_id(Integer.parseInt(newBean[3].toString()));
					for(Object[] badBean : listBad){
						if(newBean[0].equals(badBean[0])){
							countJC.setBadNum(Integer.parseInt(badBean[2].toString()));
							break;
						}
					}
					list.add(countJC);
				}
//				for(CountJcBean allBean : listAll){
//					CountJcBean newBean = allBean.clone();
//					for(CountJcBean badBean : listBad){
//						if(allBean.getArea_name().equals(badBean.getArea_name())){
//							newBean.setBadNum(badBean.getBadNum());
//							break;
//						}
//					}
//					list.add(newBean);
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("countJc", list);
		request.setAttribute("date", date);
		return "countJc";
	}
}
