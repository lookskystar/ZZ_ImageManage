package com.image.query.dao.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.image.common.pojo.DictAreas;
import com.image.common.pojo.DictJcstype;
import com.image.common.pojo.DictUsers;
import com.image.common.pojo.DictWorktype;
import com.image.common.pojo.JcRec;
import com.image.common.pojo.ProcedureInfo;
import com.image.common.pojo.ProcedureInfoRec;
import com.image.common.pojo.TakedetailRec;
import com.image.common.util.AbstractDao;
import com.image.common.util.PageModel;
import com.image.query.dao.QueryDao;

/**
 * @author eleven
 *
 */
public class QueryDaoImpl extends AbstractDao implements QueryDao {
	
	/**
	 * 查询(所)上传影像记录
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> findAllUploadRecOnJc(String jcType, String jcNum, String taskDate, String areaId, String jcRecId){
		List<Map<String, String>> dataRsList = new ArrayList<Map<String, String>>();
		List<DictWorktype> dictWorktypeList = this.findAllDictWorktype();
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("select dj.jc_recid, dj.jc_type, dj.jc_num, dj.task_date,");
		sqlBuilder.append("(select count(*) from take_detail_rec tdr where tdr.pro_id in (select pir.prorec_id from procedure_inforec pir where pir.jcrec_id = dj.jc_recid and pir.pro_id in (select pi.pro_id from procedure_info pi where pi.status=0) and pir.status != 0)) status,");
		for(int i=0;i<=dictWorktypeList.size()-1; i++){
			Long workId = dictWorktypeList.get(i).getWorkId();
			sqlBuilder.append(" (select count(*) from take_detail_rec tdr where tdr.pro_id in " +
									"(select pir.prorec_id from procedure_inforec pir where pir.jcrec_id = dj.jc_recid and pir.pro_id in (select pi.pro_id from procedure_info pi where pi.work_id="+ workId +" and pi.status=0)");
			sqlBuilder.append(") and tdr.type = 0) "+ transNumToLetter(i) +"_pic,");
			
			sqlBuilder.append(" (select count(*) from take_detail_rec tdr where tdr.pro_id in " +
									"(select pir.prorec_id from procedure_inforec pir where pir.jcrec_id = dj.jc_recid and pir.pro_id in (select pi.pro_id from procedure_info pi where pi.work_id="+ workId +" and pi.status=0)");
			sqlBuilder.append(") and tdr.type = 1) "+ transNumToLetter(i) +"_vid");
			if(i!=dictWorktypeList.size()-1){
				sqlBuilder.append(", ");
			}
		}
		sqlBuilder.append(" from jc_rec dj where 1=1 and dj.area_id=" + Integer.parseInt(areaId));
		if(jcRecId != null && !"".equals(jcRecId)){
			sqlBuilder.append(" and dj.jc_recid=" + Long.parseLong(jcRecId));
		}
		if(jcType != null && !"".equals(jcType)){
			sqlBuilder.append(" and dj.jc_type='"+ jcType +"'");
		}
		if(jcNum!=null && !"".equals(jcNum)){
			sqlBuilder.append(" and dj.jc_num='"+ jcNum +"'");
		}
		if(taskDate!=null && !"".equals(taskDate)){
			sqlBuilder.append(" and dj.task_date='"+ taskDate +"'");
		}
		sqlBuilder.append(" order by dj.jc_recid");
		Map<String, String> dbRsMap = null;
		List dataDbList = this.getSession().createSQLQuery(sqlBuilder.toString()).list();
		for(int i=0; i<=dataDbList.size()-1; i++ ){
			Object[] obj = (Object[])dataDbList.get(i);   
			dbRsMap = new HashMap<String, String>();
			dbRsMap.put("jcRecId", obj[0] == null ? "" : obj[0].toString());
			dbRsMap.put("jcType", obj[1] == null ? "" : obj[1].toString());
			dbRsMap.put("jcNum", obj[2] == null ? "" : obj[2].toString());
			dbRsMap.put("taskDate", obj[3] == null ? "" : obj[3].toString());
			dbRsMap.put("status", obj[4] == null ? "" : obj[4].toString());
			dbRsMap.put("onePic", obj[5] == null ? "" : obj[5].toString());
			dbRsMap.put("oneVid", obj[6] == null ? "" : obj[6].toString());
			dbRsMap.put("twoPic", obj[7] == null ? "" : obj[7].toString());
			dbRsMap.put("twoVid", obj[8] == null ? "" : obj[8].toString());
			dbRsMap.put("thrPic", obj[9] == null ? "" : obj[9].toString());
			dbRsMap.put("thrVid", obj[10] == null ? "" : obj[10].toString());
			dbRsMap.put("fourPic", obj[11] == null ? "" : obj[11].toString());
			dbRsMap.put("fourVid", obj[12] == null ? "" : obj[12].toString());
			dataRsList.add(dbRsMap);
		}
		return dataRsList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> findDetailUploadRecOnJc(String jcRecId, String proId, String workId){
		Map<String, String> dataDbMap = null;
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("select dj.jc_type, dj.jc_num, proecdw.status, proecdw.prorec_id, proecdw.pro_name, proecdw.pro_rank, proecdw.image_num, proecdw.video_time, proecdw.workid, proecdw.worktype," +
				" (select WMSYS.WM_CONCAT(distinct tdr.take_name) from take_detail_rec tdr where tdr.pro_id in(select procedure_inforec.prorec_id from procedure_inforec where procedure_inforec.prorec_id = proecdw.prorec_id))," +
				" (select count(*) from take_detail_rec tdr where tdr.pro_id in(select procedure_inforec.prorec_id from procedure_inforec where procedure_inforec.prorec_id = proecdw.prorec_id))," +
				" (select sum(tdr.video_time) from take_detail_rec tdr where tdr.pro_id in(select procedure_inforec.prorec_id from procedure_inforec  where procedure_inforec.prorec_id = proecdw.prorec_id))" +
				" from jc_rec dj, (select proec.jcrec_id, proec.pro_rank, proec.prorec_id, proec.status, proec.pro_name, proec.image_num, proec.video_time, dw.workid, dw.worktype from dict_worktype dw," +
				" (select pir.jcrec_id, pir.pro_rank, pir.prorec_id, pir.status, pi.pro_name, pi.image_num, pi.video_time, pi.work_id from procedure_inforec pir, procedure_info pi where pir.pro_id = pi.pro_id and pi.status=0");
		if(jcRecId != null && !"".equals(jcRecId)){
			sqlBuilder.append(" and pir.jcrec_id="+ Long.parseLong(jcRecId));
		}
		if(proId != null && !"".equals(proId)){
			sqlBuilder.append(" and pi.pro_id="+ Long.parseLong(proId));
		}
		sqlBuilder.append(" ) proec where dw.workid = proec.work_id");
		if(workId != null && !"".equals(workId)){
			sqlBuilder.append(" and dw.workid ="+ Long.parseLong(workId));
		}
		sqlBuilder.append(" ) proecdw where dj.jc_recid = proecdw.jcrec_id");
		List  dataDbList =  this.getSession().createSQLQuery(sqlBuilder.toString()).list();
		for(int i = 0; i <= dataDbList.size()-1; i++) {
			Object[] obj = (Object[])dataDbList.get(i);
			dataDbMap = new HashMap<String, String>();
			dataDbMap.put("jcType", obj[0] == null ? "" : obj[0].toString());
			dataDbMap.put("jcNum", obj[1] == null ? "" : obj[1].toString());
			dataDbMap.put("status", obj[2] == null ? "" : obj[2].toString());
			dataDbMap.put("proecId", obj[3] == null ? "" : obj[3].toString());
			dataDbMap.put("proName", obj[4] == null ? "" : obj[4].toString());
			dataDbMap.put("proRank", obj[5] == null ? "" : obj[5].toString());
			dataDbMap.put("imageNumStd", obj[6] == null ? "" : obj[6].toString());
			dataDbMap.put("videoTimeStd", obj[7] == null ? "" : obj[7].toString());
			dataDbMap.put("workId", obj[8] == null ? "" :obj[8].toString());
			dataDbMap.put("workType", obj[9] == null ? "" : obj[9].toString());
			dataDbMap.put("takeName", obj[10] == null ? "" : obj[10].toString());
			dataDbMap.put("imageNumAct", obj[11] == null ? "" : obj[11].toString());
			dataDbMap.put("videoTimeAct", obj[12] == null ? "" : obj[12].toString());
			dataList.add(dataDbMap);
		}
		return dataList;
	}
	
	/**
	 * 查询工序大类
	 */
	@SuppressWarnings("unchecked")
	public List<DictWorktype> findAllDictWorktype(){
		return this.getHibernateTemplate().find("from DictWorktype dw order by dw.workId");
	}
	
	/**
	 * 查询标准工序表
	 * @param workId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProcedureInfo> finProcedureInfoByWorkId(Long workId){
		return this.getHibernateTemplate().find("from ProcedureInfo pi where pi.dictWorktype.workId=?", new Object[]{workId});
	}
	
	/**
	 * 查询机车记录表
	 * @param jcRecId
	 * @return
	 */
	public JcRec findDictJcRecById(Long jcRecId){
		return this.getHibernateTemplate().get(JcRec.class, jcRecId);
	}
	
	private String transNumToLetter(Integer num){
		String result = "";
		if(num == 0){
			result += "o";
		}else if(num == 1){
			result += "one";
		}else if(num == 2){
			result += "two";
		}else if(num == 3){
			result += "thr";
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public PageModel<JcRec> findJcRecAll() {
		return findPageModel("from JcRec t order by t.taskDate desc");
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findTekeDetailRec(Long jcRecId) {
		String sql="select t1.detail_id,t4.worktype, t3.pro_name,t2.pro_rank,t1.url from Take_detail_rec t1 " +
				"left join Procedure_inforec t2 on t1.pro_id=t2.prorec_id left join Procedure_info  t3 on " +
				"t2.pro_id=t3.pro_id left join dict_worktype t4 on t3.work_id=t4.workid where t1.type=1 and t2.jcrec_id=? order by t4.workid";
		return getSession().createSQLQuery(sql).setLong(0, jcRecId).list();
	}

	public DictWorktype findDictWorkTypeById(Long workId) {
		return this.getHibernateTemplate().get(DictWorktype.class, workId);
	}

	@SuppressWarnings("unchecked")
	public List<TakedetailRec> findTakedetailRecByPeoecId(Long proecId) {
		return this.getHibernateTemplate().find("from TakedetailRec tdr where tdr.procedureInfoRec.proRecId=?", new Object[]{proecId});
	}

	public ProcedureInfoRec findProcedureInfoRecById(Long proecId) {
		return this.getHibernateTemplate().get(ProcedureInfoRec.class, proecId);
	}

	@SuppressWarnings("unchecked")
	public List<ProcedureInfoRec> findProcedureInfoRecByProId(Long proId) {
		return this.getHibernateTemplate().find("from ProcedureInfoRec pir where pir.procedureInfo.proId=?", new Object[]{proId});
	}

	public JcRec findTakeTime(long jcRecId) {
		return getHibernateTemplate().get(JcRec.class, jcRecId);
	}

	public DictAreas findDictArea(long areaId) {
		return getHibernateTemplate().get(DictAreas.class, areaId);
	}

	public DictAreas findDictAreaByJcRecId(long jcRecId) {
		String sql="select t2.* from jc_rec t1,dict_areas t2 where t1.area_id=t2.area_id and t1.jc_recid=?";
		List list=getSession().createSQLQuery(sql).addEntity(DictAreas.class).setLong(0, jcRecId).list();
		if(list!=null&&list.size()>0){
			return (DictAreas)list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findProInfoByProNum(List<String> proNums) {
		String sql="select pro_num,pro_name from procedure_info where pro_num in (:PRONUMS)";
		List list=getSession().createSQLQuery(sql).setParameterList("PRONUMS", proNums).list();
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ProcedureInfo findProInfoByProNum(String proNum) {
		String hql="from ProcedureInfo t where t.proNum=?";
		List list=getHibernateTemplate().find(hql,proNum);
		if(list!=null&&list.size()>0){
			return (ProcedureInfo)list.get(0);
		}
		return null;
	}
	
	public ProcedureInfo findProInfoByProId(Long proId){
		return getHibernateTemplate().get(ProcedureInfo.class, proId);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findJcInfoByJcNum(List<String> jcNums) {
		String sql="select jcnum,jcstype  from dict_jcstype where jcnum in (:JCNUMS)";
		List list=getSession().createSQLQuery(sql).setParameterList("JCNUMS", jcNums).list();
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public DictJcstype findJcInfoByJcNum(String jcNum) {
		String hql="from DictJcstype t where t.jcNum=?";
		List list=getHibernateTemplate().find(hql,jcNum);
		if(list!=null&&list.size()>0){
			return (DictJcstype)list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findUserInfoByGonghao(List<String> gonghao) {
		String sql="select gonghao,username from dict_users where gonghao in (:GONGHAO)";
		List list=getSession().createSQLQuery(sql).setParameterList("GONGHAO", gonghao).list();
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public DictUsers findUserInfoByGonghao(String gh) {
		String hql="from DictUsers t where t.gonghao=?";
		List list=getHibernateTemplate().find(hql,gh);
		if(list!=null&&list.size()>0){
			return (DictUsers)list.get(0);
		}
		return null;
	}

	public Integer findUrlNum(String url) {
		String sql="select count(detail_id) from take_detail_rec where url=?";
		Object obj=getSession().createSQLQuery(sql).setString(0, url).uniqueResult();
		return Integer.parseInt(obj+"");
	}

	@SuppressWarnings("unchecked")
	public JcRec findJcRec(String jcNum, String takeTime, Integer areaId) {
		String hql="from JcRec t where t.jcNum=? and t.taskDate=? and t.areaId=?";
		List list=getHibernateTemplate().find(hql,new Object[]{jcNum,takeTime,areaId});
		if(list!=null&&list.size()>0){
			return (JcRec)list.get(0);
		}
		return null;
	}

	public void saveOrUpdateJcRec(JcRec jcRec) {
		getHibernateTemplate().saveOrUpdate(jcRec);
	}

	public ProcedureInfoRec findProcedureInfoRec(Long jcRecId, Long proId) {
		String hql="from ProcedureInfoRec t where t.jcRec.jcRecId=? and t.procedureInfo.proId=?";
		List list=getHibernateTemplate().find(hql,new Object[]{jcRecId,proId});
		if(list!=null&&list.size()>0){
			return (ProcedureInfoRec)list.get(0);
		}
		return null;
	}

	public void saveOrUpdateProcedureInfoRec(ProcedureInfoRec procedureInfoRec) {
		getHibernateTemplate().saveOrUpdate(procedureInfoRec);
	}

	public void saveOrUpdateTakedetailRec(TakedetailRec takeDetaiRec) {
		getHibernateTemplate().saveOrUpdate(takeDetaiRec);
	}

	public void saveOrUpdateUser(DictUsers user) {
		getHibernateTemplate().saveOrUpdate(user);
	}

	public void saveOrUpdateProcedureInfo(ProcedureInfo procedureInfo) {
		getHibernateTemplate().saveOrUpdate(procedureInfo);
	}
}
