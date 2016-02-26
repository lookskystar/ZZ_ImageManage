package com.image.work.service;

import java.util.List;

public interface CountJcService {
	
	/**
	 * 查询一天上传机车组数
	 */
	public List<Object[]> findJcRec(long userid,String date);
	
	/**
	 * 查询一天上传异常机车组数
	 */
	public List<Object[]> findBadJcRec(long userid,String date);
}
