package com.image.admin.dao;

import java.util.List;

import com.image.common.pojo.DictJcType;
import com.image.common.pojo.DictJcstype;
import com.image.common.util.PageModel;

public interface DictJcDao {

	public PageModel<DictJcstype> findDictJcTypeByCondition(String jcNum, String jcType, String areaId);
	
	public void delDictJcs(Long[]  dictJcIdArray);
	
	public void saveOrUpdateDictJc(DictJcstype dictJcstype);
	
	public DictJcstype findDictJcstypeById(String dictJcId);
	
	public List<DictJcType> findAllDictJcType();
	
	public Integer isExistjcIdentify(String jcIdentify);
}
