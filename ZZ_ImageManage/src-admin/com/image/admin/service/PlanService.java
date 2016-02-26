package com.image.admin.service;

import com.image.common.pojo.TrainPlan;
import com.image.common.util.PageModel;

public interface PlanService {

	/**
	 * 查询所有计划
	 * */
	public PageModel<TrainPlan> findAllPlan(long areaId, String time);

	/**
	 * 保存计划
	 * */
	public void savePlan(TrainPlan trainPlan);

	/**
	 * 删除计划
	 * */
	public void deletePlan(long id);

	/**
	 * 根据主键Id查找计划
	 * */
	public TrainPlan findPlanById(long id);
}
