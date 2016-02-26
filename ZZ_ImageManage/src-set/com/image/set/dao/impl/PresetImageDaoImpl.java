package com.image.set.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.image.common.pojo.DictWorktype;
import com.image.common.pojo.ProcedureInfo;
import com.image.common.pojo.ProcedureStep;
import com.image.common.util.AbstractDao;
import com.image.common.util.PageModel;
import com.image.set.dao.PresetImageDao;

public class PresetImageDaoImpl extends AbstractDao implements PresetImageDao {

	@SuppressWarnings("unchecked")
	public PageModel<ProcedureInfo> findPresetAll(String jcStype, String proName) {
		String hql = "from ProcedureInfo p where p.status = 0";
		List<Object> params = new ArrayList<Object>();
		if (jcStype != null && !jcStype.equals("")) {
			hql += " and p.jcType=?";
			params.add(jcStype);
		}
		if (proName != null && !proName.equals("")) {
			hql += " and p.proName=?";
			params.add(proName);
		}
		return findPageModel(hql, params.toArray());
	}

	@SuppressWarnings("unchecked")
	public List<String> findJcstypeAll() {
		String sql = "select distinct jcstype from dict_jcstype";
		List<String> jcStypes = this.getSession().createSQLQuery(sql).list();
		return jcStypes;
	}

	@SuppressWarnings("unchecked")
	public List<String> findProcedureAll() {
		String sql = "select distinct pro_name from procedure_info";
		List<String> pros = this.getSession().createSQLQuery(sql).list();
		return pros;
	}

	public void deletePreset(long id) {
		String hql = "update ProcedureInfo p set p.status = 1 where p.proId=?";
		getSession().createQuery(hql).setLong(0, id).executeUpdate();
	}

	public void savePreset(ProcedureInfo procedureInfo) {
		getHibernateTemplate().saveOrUpdate(procedureInfo);
	}

	public ProcedureInfo findProcedureById(long id) {
		return getHibernateTemplate().get(ProcedureInfo.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<ProcedureStep> findProcedureImageById(long id) {
		return getSession().createQuery("from ProcedureStep p where p.proId=? order by p.proSn desc")
				.setLong(0, id).list();
	}

	public void saveProcedureStep(ProcedureStep procedureStep) {
		getHibernateTemplate().save(procedureStep);
	}

	public void deleteProcedureStepById(long stepId) {
		getHibernateTemplate().delete(this.findProcedureStepById(stepId));

	}

	public ProcedureStep findProcedureStepById(long stepId) {
		return getHibernateTemplate().get(ProcedureStep.class, stepId);
	}

	@SuppressWarnings("unchecked")
	public boolean findProNum(String proNum) {
		List<ProcedureInfo> procedureInfos = getHibernateTemplate().find(
				"from ProcedureInfo p where p.proNum = " + proNum + "");
		if (procedureInfos != null && procedureInfos.size() > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<DictWorktype> findDictWorktype() {
		return getHibernateTemplate().find("from DictWorktype");
	}
}
