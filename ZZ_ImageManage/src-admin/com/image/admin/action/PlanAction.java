package com.image.admin.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.image.admin.service.PlanService;
import com.image.admin.service.UsersService;
import com.image.common.pojo.DictUsers;
import com.image.common.pojo.TrainPlan;
import com.image.common.util.PageModel;
import com.image.set.service.PresetImageService;

public class PlanAction {

	@Resource(name = "planService")
	private PlanService planService;
	@Resource(name = "usersService")
	private UsersService usersService;
	@Resource(name = "presetImageService")
	private PresetImageService presetImageService;

	private HttpServletRequest request = ServletActionContext.getRequest();
	private TrainPlan trainPlan;

	/**
	 * 进入计划页面
	 * */
	public String trainPlanInput() {
		DictUsers user = (DictUsers) request.getSession().getAttribute(
				"session_user");
		long area = user.getAreaId();

		String shijian = request.getParameter("time");
		String areaId = request.getParameter("areaId");

		if (areaId != null && !areaId.equals("")) {
			area = Long.parseLong(areaId);
		}

		PageModel<TrainPlan> trainPlan = planService.findAllPlan(area, shijian);
		request.setAttribute("dictAreaList", usersService.listDictArea());
		request.setAttribute("trainPlan", trainPlan);
		request.setAttribute("shijian", shijian);
		request.setAttribute("areaId", area);
		return "trainPlanInput";
	}

	/**
	 * 进入添加计划页面
	 * */
	public String addPlanInput() {
		List jcStypes = presetImageService.findJcstypeAll();
		request.setAttribute("jcStypes", jcStypes);
		return "addPlanInput";
	}

	/**
	 * 添加计划
	 * */
	public String addPlan() {
		DictUsers user = (DictUsers) request.getSession().getAttribute(
				"session_user");
		long areaId = user.getAreaId();
		trainPlan.setAreaId(areaId);
		planService.savePlan(trainPlan);
		request.setAttribute("message", "车组应用计划添加成功！");
		return trainPlanInput();
	}

	/**
	 * 删除计划
	 * */
	public String deletePlan() {
		long planId = Long.parseLong(request.getParameter("planId"));
		planService.deletePlan(planId);
		request.setAttribute("message", "计划已删除！");
		return trainPlanInput();
	}

	/**
	 * 进入修改计划页面
	 * */
	public String updatePlanInput() {
		long planId = Long.parseLong(request.getParameter("planId"));
		List jcStypes = presetImageService.findJcstypeAll();
		TrainPlan trainPlan = planService.findPlanById(planId);

		request.setAttribute("trainPlan", trainPlan);
		request.setAttribute("jcStypes", jcStypes);
		return "updatePlanInput";
	}

	/**
	 * 修改计划
	 * */
	public String updatePlan() {
		long planId = Long.parseLong(request.getParameter("planId"));
		TrainPlan plan = planService.findPlanById(planId);

		plan.setJcStyle(trainPlan.getJcStyle());
		plan.setJcNum(trainPlan.getJcNum());
		plan.setStarted(trainPlan.getStarted());
		plan.setEnded(trainPlan.getEnded());
		plan.setCjNum(trainPlan.getCjNum());
		plan.setTime(trainPlan.getTime());
		plan.setJcCs(trainPlan.getJcCs());

		planService.savePlan(plan);
		request.setAttribute("message", "计划已修改！");
		return trainPlanInput();
	}

	public TrainPlan getTrainPlan() {
		return trainPlan;
	}

	public void setTrainPlan(TrainPlan trainPlan) {
		this.trainPlan = trainPlan;
	}
}
