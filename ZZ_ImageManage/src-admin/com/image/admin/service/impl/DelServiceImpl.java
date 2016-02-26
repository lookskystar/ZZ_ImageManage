package com.image.admin.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import com.image.admin.dao.DelDao;
import com.image.admin.service.DelService;
import com.image.common.pojo.DelRec;
import com.image.common.util.PageModel;

public class DelServiceImpl implements DelService {

	@Resource(name = "delDao")
	private DelDao delDao;

	public PageModel<DelRec> findDelList() {
		return delDao.findDelList();
	}

	public void deleteRec(String btime, String etime, long areaId) {
		int result = 0;
		result = delDao.deleteTakedetailRec(btime, etime, areaId);
		result = delDao.deleteProcedureInfoRec(btime, etime, areaId);
		result = delDao.deleteJc(btime, etime, areaId);
		
		if(result>0){
			DelRec delRec = new DelRec();
			delRec.setBtime(btime);
			delRec.setEtime(etime);
			delRec.setDelTime(dateFormat.format(new Date()));
			delDao.save(delRec);
		}
	}
}
