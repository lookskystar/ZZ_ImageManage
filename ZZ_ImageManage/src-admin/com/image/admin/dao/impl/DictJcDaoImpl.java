package com.image.admin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.image.admin.dao.DictJcDao;
import com.image.common.pojo.DictJcType;
import com.image.common.pojo.DictJcstype;
import com.image.common.util.AbstractDao;
import com.image.common.util.PageModel;

public class DictJcDaoImpl extends AbstractDao implements DictJcDao {

	@SuppressWarnings("unchecked")
	public PageModel<DictJcstype> findDictJcTypeByCondition(String jcNum,
			String jcType, String areaId) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" from DictJcstype dj where 1=1");
		if(jcNum != null && !"".equals(jcNum)){
			sqlBuilder.append(" and dj.jcNum=?");
			params.add(jcNum);
		}
		if(jcType != null && !"".equals(jcType)){
			sqlBuilder.append(" and dj.jcStype=?");
			params.add(jcType);
		}
		if(areaId != null && !"".equals(areaId)){
			sqlBuilder.append(" and dj.areaId=?");
			params.add(Integer.parseInt(areaId));
		}
		sqlBuilder.append(" order by dj.areaId, dj.id");
		return findPageModel(sqlBuilder.toString(), params.toArray());
	}

	public void delDictJcs(Long[] dictJcIdArray) {
		this.getSession().createQuery("delete from DictJcstype dj where dj.Id in(:dictJcIds)").setParameterList("dictJcIds", dictJcIdArray).executeUpdate();
	}

	public void saveOrUpdateDictJc(DictJcstype dictJcstype) {
		this.getHibernateTemplate().saveOrUpdate(dictJcstype);
	}

	public DictJcstype findDictJcstypeById(String dictJcId) {
		return this.getHibernateTemplate().get(DictJcstype.class, Long.parseLong(dictJcId));
	}

	@SuppressWarnings("unchecked")
	public List<DictJcType> findAllDictJcType() {
		return this.getHibernateTemplate().find(" from DictJcType dj order by dj.Id");
	}

	public Integer isExistjcIdentify(String jcIdentify) {
		return this.getHibernateTemplate().find(" from DictJcstype dj where dj.jcIdentify=?", new Object[]{jcIdentify}).size();
	}

}
