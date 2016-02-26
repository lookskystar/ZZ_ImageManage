package com.image.admin.dao;

import com.image.common.pojo.DelRec;
import com.image.common.util.PageModel;

public interface DelDao {

	/**
	 * 查询删除操作记录
	 * */
	public PageModel<DelRec> findDelList();

	/**
	 * 根据起始、结束时间删除车组记录
	 * */
	public int deleteJc(String btime, String etime, long areaId);

	/**
	 * 根据起始、结束时间删除所有工序记录
	 * */
	public int deleteProcedureInfoRec(String btime, String etime, long areaId);

	/**
	 * 根据起始、结束时间删除所有上传记录
	 * */
	public int deleteTakedetailRec(String btime, String etime, long areaId);

	/**
	 * 插入删除操作数据
	 * */
	public void save(DelRec delRec);
}
