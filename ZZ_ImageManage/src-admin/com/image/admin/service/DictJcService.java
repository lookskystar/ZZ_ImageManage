package com.image.admin.service;

import java.util.List;

import com.image.common.pojo.DictJcType;
import com.image.common.pojo.DictJcstype;
import com.image.common.util.PageModel;

public interface DictJcService {
	
	public PageModel<DictJcstype> findDictJcTypeByCondition(String jcNum, String jcType, String areaId);
	
	public void delDictJcs(String[]  dictJcIdArray);
	
	public void saveOrUpdateDictJc(DictJcstype dictJcstype);
	
	public DictJcstype findDictJcstypeById(String dictJcId);
	
	public List<DictJcType> findAllDictJcType();
	
	public String isExistjcIdentify(String jcIdentify);
}
