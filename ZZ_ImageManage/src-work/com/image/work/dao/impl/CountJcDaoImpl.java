package com.image.work.dao.impl;

import java.util.List;

import com.image.common.util.AbstractDao;
import com.image.work.dao.CountJcDao;


public class CountJcDaoImpl extends AbstractDao implements CountJcDao{

	public List<Object[]> findJcRec(long userid,String date) {
		String sql ="select da.area_name as area_name ,count(distinct d.jc_num) as totalNum, 0 as badNum,da.area_id from dict_areas da " +
				"left join (select dj.* from jc_rec dj where dj.task_date=?) d on da.area_id = d.area_id " +
				"left join dict_users du on da.parent_id = du.area_id where du.user_id=? group by da.area_name,da.area_id";
		return (List<Object[]>)getSession().createSQLQuery(sql).setString(0, date).setLong(1, userid).list();
	}
	
	public List<Object[]> findBadJcRec(long userid,String date){
		String sql = "select da.area_name as area_name,0 as totalNum, count(distinct d.jc_num) as badNum,da.area_id from dict_areas da " +
				"left join (select dj.* from jc_rec dj " +
					"where dj.task_date=? and dj.jc_recid in (select pi.jcrec_id from PROCEDURE_INFOREC pi where  pi.status=1)) d " +
					"on da.area_id = d.area_id " +
					"left join dict_users du on da.parent_id = du.area_id where du.user_id=? group by da.area_name,da.area_id";
		
		return (List<Object[]>)getSession().createSQLQuery(sql).setString(0, date).setLong(1, userid).list();
	}
	
}
