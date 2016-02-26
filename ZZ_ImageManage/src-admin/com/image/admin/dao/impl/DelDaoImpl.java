package com.image.admin.dao.impl;

import com.image.admin.dao.DelDao;
import com.image.common.pojo.DelRec;
import com.image.common.util.AbstractDao;
import com.image.common.util.PageModel;

public class DelDaoImpl extends AbstractDao implements DelDao {

	@SuppressWarnings("unchecked")
	public PageModel<DelRec> findDelList() {
		return findPageModel("from DelRec d order by d.delTime desc");
	}

	public int  deleteJc(String btime, String etime, long areaId) {
		String hql = "delete from JcRec j where j.taskDate >= ? and j.taskDate <= ? and j.areaId=?";
		return getSession().createQuery(hql).setString(0, btime).setString(1, etime)
				.setLong(2, areaId).executeUpdate();
	}

	public int deleteProcedureInfoRec(String btime, String etime, long areaId) {
		String sql = "delete from procedure_inforec p where p.jcrec_id in (select j.jc_recid from jc_rec j where j.task_date >=? and j.task_date <=? and j.area_id=?)";
		return getSession().createSQLQuery(sql).setString(0, btime)
				.setString(1, etime).setLong(2, areaId).executeUpdate();
	}

	public int deleteTakedetailRec(String btime, String etime, long areaId) {
		String sql = "delete from take_detail_rec t where t.pro_id in(select a.prorec_id from procedure_inforec a where a.jcrec_id in(select b.jc_recid from jc_rec b where b.task_date>=? and b.task_date<=? and b.area_id=?))";
		return getSession().createSQLQuery(sql).setString(0, btime)
				.setString(1, etime).setLong(2, areaId).executeUpdate();
	}

	public void save(DelRec delRec) {
		getHibernateTemplate().save(delRec);
	}
}
