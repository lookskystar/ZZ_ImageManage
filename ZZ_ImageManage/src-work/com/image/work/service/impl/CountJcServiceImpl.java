package com.image.work.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import com.image.common.pojo.CountJcBean;
import com.image.work.dao.CountJcDao;
import com.image.work.service.CountJcService;

public class CountJcServiceImpl implements CountJcService{
	@Resource(name="countJcDao")
	private CountJcDao countJcDao;
	
	/**
	 * 格式化时间
	 */
	public static final SimpleDateFormat YMD_SDFORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public List<Object[]> findJcRec(long userid, String date) {
		if(date == null || "".equals(date)){
			date = YMD_SDFORMAT.format(new Date());
		}
		return countJcDao.findJcRec(userid, date);
	}

	public List<Object[]> findBadJcRec(long userid, String date) {
		if(date == null || "".equals(date)){
			date = YMD_SDFORMAT.format(new Date());
		}
		return countJcDao.findBadJcRec(userid, date);
	}

	
}
