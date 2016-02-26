package com.image.admin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.image.admin.dao.PlanDao;
import com.image.common.pojo.TrainPlan;
import com.image.common.util.AbstractDao;
import com.image.common.util.PageModel;

public class PlanDaoImpl extends AbstractDao implements PlanDao {

	@SuppressWarnings("unchecked")
	public PageModel<TrainPlan> findAllPlan(long areaId, String time) {
		String hql = "from TrainPlan t where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (areaId != 0) {
			hql += " and t.areaId =?";
			params.add(areaId);
		}
		if (time != null && !time.equals("")) {
			String btime = time + " 00:00:00";
			String etime = time + " 23:59:59";
			hql += " and t.time > ? and t.time < ?";
			params.add(btime);
			params.add(etime);
		}
		return findPageModel(hql, params.toArray());
	}

	public void savePlan(TrainPlan trainPlan) {
		getHibernateTemplate().saveOrUpdate(trainPlan);
	}

	public void deletePlan(long id) {
		getSession().createQuery("delete from TrainPlan t where t.id=?")
				.setLong(0, id).executeUpdate();
	}

	public TrainPlan findPlanById(long id) {
		return getHibernateTemplate().get(TrainPlan.class, id);
	}

}
