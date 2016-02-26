package com.image.admin.service;

import java.text.SimpleDateFormat;

import com.image.common.pojo.DelRec;
import com.image.common.util.PageModel;

public interface DelService {

	public final static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");

	/**
	 * 查询删除操作记录
	 * */
	public PageModel<DelRec> findDelList();

	/**
	 * 删除记录
	 * */
	public void deleteRec(String btime, String etime, long areaId);
}
