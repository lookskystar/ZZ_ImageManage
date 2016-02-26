package com.image.admin.service.impl;

import javax.annotation.Resource;

import com.image.admin.dao.PlanDao;
import com.image.admin.service.PlanService;
import com.image.common.pojo.TrainPlan;
import com.image.common.util.PageModel;

public class PlanServiceImpl implements PlanService {

	@Resource(name = "planDao")
	private PlanDao planDao;

	public PageModel<TrainPlan> findAllPlan(long areaId, String time) {
		return planDao.findAllPlan(areaId, time);
	}

	public void savePlan(TrainPlan trainPlan) {
		planDao.savePlan(trainPlan);
	}

	public void deletePlan(long id) {
		planDao.deletePlan(id);
	}

	public TrainPlan findPlanById(long id) {
		return planDao.findPlanById(id);
	}

}
