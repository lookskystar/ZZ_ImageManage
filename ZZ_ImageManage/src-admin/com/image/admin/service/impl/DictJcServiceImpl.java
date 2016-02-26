package com.image.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.image.admin.dao.DictJcDao;
import com.image.admin.service.DictJcService;
import com.image.common.pojo.DictJcType;
import com.image.common.pojo.DictJcstype;
import com.image.common.util.PageModel;
import com.sun.org.apache.commons.beanutils.ConvertUtils;

public class DictJcServiceImpl implements DictJcService {
	@Resource(name="dictJcDao")
	private DictJcDao dictJcDao;

	public PageModel<DictJcstype> findDictJcTypeByCondition(String jcNum,
			String jcType, String areaId) {
		return dictJcDao.findDictJcTypeByCondition(jcNum, jcType, areaId);
	}

	public void delDictJcs(String[] dictJcIdArray) {
		dictJcDao.delDictJcs((Long[])ConvertUtils.convert(dictJcIdArray, Long.class));
	}

	public void saveOrUpdateDictJc(DictJcstype dictJcstype) {
		dictJcDao.saveOrUpdateDictJc(dictJcstype);
	}

	public DictJcstype findDictJcstypeById(String dictJcId) {
		return dictJcDao.findDictJcstypeById(dictJcId);
	}

	public List<DictJcType> findAllDictJcType() {
		return dictJcDao.findAllDictJcType();
	}

	public String isExistjcIdentify(String jcIdentify) {
		return dictJcDao.isExistjcIdentify(jcIdentify) > 0 ? "exist" : "noexist";
	}

}
