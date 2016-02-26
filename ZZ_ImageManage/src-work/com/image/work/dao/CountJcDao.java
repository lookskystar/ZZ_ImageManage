package com.image.work.dao;

import java.util.List;


/**
 * 统计车组
 * @author T
 */
public interface CountJcDao {

	/**
	 * 查询一天上传机车组数
	 */
	public List<Object[]> findJcRec(long userid, String date);
	
	/**
	 * 查询一天上传异常机车组数
	 */
	//public List findBadJcRec(long userid, String dates);

	public List<Object[]> findBadJcRec(long userid, String date);
}
