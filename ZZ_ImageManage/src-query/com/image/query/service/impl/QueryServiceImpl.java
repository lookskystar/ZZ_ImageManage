package com.image.query.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.image.common.pojo.DictAreas;
import com.image.common.pojo.DictJcstype;
import com.image.common.pojo.DictUsers;
import com.image.common.pojo.DictWorktype;
import com.image.common.pojo.JcRec;
import com.image.common.pojo.ProcedureInfo;
import com.image.common.pojo.ProcedureInfoRec;
import com.image.common.pojo.ProcedureStep;
import com.image.common.pojo.TakedetailRec;
import com.image.common.util.PageModel;
import com.image.query.dao.QueryDao;
import com.image.query.service.QueryService;
import com.image.set.dao.PresetImageDao;

/**
 * @author eleven
 *
 */
public class QueryServiceImpl implements QueryService {
	@Resource(name="queryDao")
	private QueryDao queryDao;
	@Resource(name = "presetImageDao")
	private PresetImageDao presetImageDao;

	public List<Map<String, String>> findAllUploadRecOnJc(String jcType, String jcNum, String taskDate, String areaId, String jcRecId) {
		return queryDao.findAllUploadRecOnJc(jcType, jcNum, taskDate, areaId, jcRecId);
	}

	public List<DictWorktype> findAllDictWorktype() {
		return queryDao.findAllDictWorktype();
	}
	
	public Map<JcRec, Map<DictWorktype, List<ProcedureInfo>>> constructProTree(Long jcRecId){
		Map<JcRec, Map<DictWorktype, List<ProcedureInfo>>> dataRsMap = new LinkedHashMap<JcRec, Map<DictWorktype, List<ProcedureInfo>>>();
		Map<DictWorktype, List<ProcedureInfo>> dictWorkTypeMap = new LinkedHashMap<DictWorktype, List<ProcedureInfo>>();
		List<ProcedureInfo> proList = null;
		JcRec dictJcRec = queryDao.findDictJcRecById(jcRecId);
		List<DictWorktype> dictWorkTypeList = queryDao.findAllDictWorktype();
		for (DictWorktype dictWorktype : dictWorkTypeList) {
			proList = dictWorkTypeMap.get(dictWorktype);
			if(proList == null || proList.size() <0){
				proList = queryDao.finProcedureInfoByWorkId(dictWorktype.getWorkId());
				dictWorkTypeMap.put(dictWorktype, proList);
			}
		}
		dataRsMap.put(dictJcRec, dictWorkTypeMap);
		return dataRsMap;
	}

	public List<Map<String, String>> findDetailUploadRecOnJc(String jcRecId, String proId, String workId) {
		return queryDao.findDetailUploadRecOnJc(jcRecId, proId, workId);
	}

	public PageModel<JcRec> findJcRecAll() {
		return queryDao.findJcRecAll();
	}

	public List<Object[]> findTekeDetailRec(Long jcRecId) {
		return queryDao.findTekeDetailRec(jcRecId);
	}

	public Map<DictWorktype ,Map<ProcedureInfoRec, Map<String, List<Object>>>> findTakedetailRecByPeoecId(
			Long proecId, Long workId) {
		Map<DictWorktype ,Map<ProcedureInfoRec, Map<String, List<Object>>>> workMap = new LinkedHashMap<DictWorktype, Map<ProcedureInfoRec, Map<String, List<Object>>>>();
		Map<ProcedureInfoRec, Map<String, List<Object>>> proMap = new LinkedHashMap<ProcedureInfoRec, Map<String, List<Object>>>();
		Map<String, List<Object>> dataMap = new LinkedHashMap<String, List<Object>>();
		List<Object> imageList = new ArrayList<Object>();
		List<Object> videoList = new ArrayList<Object>();
		List<TakedetailRec> dataList = queryDao.findTakedetailRecByPeoecId(proecId);
		ProcedureInfoRec procedureInfoRec = queryDao.findProcedureInfoRecById(proecId);
		List<ProcedureStep>	procedureStepList = presetImageDao.findProcedureImageById(procedureInfoRec.getProcedureInfo().getProId());
		List<Object> procedureStep2ObjectList = new ArrayList<Object>();
		for (ProcedureStep procedureStep : procedureStepList) {
			procedureStep2ObjectList.add(procedureStep);
		}
		DictWorktype dictWorktype = queryDao.findDictWorkTypeById(workId);
		Integer type = null;
		for (TakedetailRec takedetailRec : dataList) {
			type = takedetailRec.getType();
			if(type == 0){
				imageList.add(takedetailRec);
			}else if(type == 1){
				videoList.add(takedetailRec);
			}
		}
		dataMap.put("image", imageList);
		dataMap.put("video", videoList); 
		dataMap.put("standar", procedureStep2ObjectList);
		proMap.put(procedureInfoRec, dataMap);
		workMap.put(dictWorktype, proMap); 
		return workMap;
	}

	public Map<DictWorktype, Map<ProcedureInfo, Map<ProcedureInfoRec, List<TakedetailRec>>>> listDetailUploadRecOfWorkTypeOnPlan(
			String jcRecId, String workId, String type) {
		Map<DictWorktype, Map<ProcedureInfo, Map<ProcedureInfoRec, List<TakedetailRec>>>> dataMap = new HashMap<DictWorktype, Map<ProcedureInfo, Map<ProcedureInfoRec, List<TakedetailRec>>>>();
		Map<ProcedureInfo, Map<ProcedureInfoRec, List<TakedetailRec>>> proMap = new HashMap<ProcedureInfo, Map<ProcedureInfoRec, List<TakedetailRec>>>();
		Map<ProcedureInfoRec, List<TakedetailRec>>  proecMap = null;
		List<TakedetailRec> takedetailAllRecList = null;
		List<TakedetailRec> takedetailRecList = null;
		//查找DictWorkType
		DictWorktype dictWorktype = queryDao.findDictWorkTypeById(Long.parseLong(workId));
		//查找工序大类下所有工序
		List<ProcedureInfo> proList = queryDao.finProcedureInfoByWorkId(Long.parseLong(workId));
		List<ProcedureInfoRec> proecList = null;
		for (ProcedureInfo procedureInfo : proList) {
			if(procedureInfo.getStatus() == 0){
				Long proId= procedureInfo.getProId();
				proecMap = proMap.get(procedureInfo);
				if(proecMap == null || proecMap.size() <0){
					proecMap = new HashMap<ProcedureInfoRec, List<TakedetailRec>>();
					proMap.put(procedureInfo, proecMap);
				}
				//查找每个工序关联的工序记录
				proecList = queryDao.findProcedureInfoRecByProId(proId);
				Long thisJcRecId = null;
				for (ProcedureInfoRec procedureInfoRec : proecList) {
					//每个工序对应的机车记录
					thisJcRecId = procedureInfoRec.getJcRec().getJcRecId();
					if(Long.parseLong(jcRecId) == thisJcRecId){
						takedetailRecList = proecMap.get(procedureInfoRec);
						if(takedetailRecList == null || takedetailRecList.size() <0){
							takedetailAllRecList = queryDao.findTakedetailRecByPeoecId(procedureInfoRec.getProRecId());
							takedetailRecList = new ArrayList<TakedetailRec>();
							for (TakedetailRec takedetailRec : takedetailAllRecList) {
								if(takedetailRec.getType() == Long.parseLong(type)){
									takedetailRecList.add(takedetailRec);
								}
							}
							proecMap.put(procedureInfoRec, takedetailRecList);
						}
					}
				}
			}
		}
		dataMap.put(dictWorktype, proMap);
		return dataMap;
	}

	public DictAreas findDictArea(long areaId) {
		return queryDao.findDictArea(areaId);
	}

	public JcRec findTakeTime(long jcRecId) {
		return queryDao.findTakeTime(jcRecId);
	}

	public DictAreas findDictAreaByJcRecId(long jcRecId) {
		return queryDao.findDictAreaByJcRecId(jcRecId);
	}

	public List<Object[]> findProInfoByProNum(List<String> proNums) {
		return queryDao.findProInfoByProNum(proNums);
	}

	public List<Object[]> findJcInfoByJcNum(List<String> jcNums) {
		return queryDao.findJcInfoByJcNum(jcNums);
	}

	public List<Object[]> findUserInfoByGonghao(List<String> gonghao) {
		return queryDao.findUserInfoByGonghao(gonghao);
	}

	public Integer findUrlNum(String url) {
		return queryDao.findUrlNum(url);
	}

	//添加总数据信息，总事物一起提交
	public void addJSONData(String jcType,String jcNum,Integer takeId,String takeTime,String takeName,Integer areaId,Long proId,
			String proRank,Integer type,String url,Double videoTime,String uploadTime) {
		JcRec jcRec=queryDao.findJcRec(jcNum, takeTime, areaId);
		ProcedureInfo procedureInfo=queryDao.findProInfoByProId(proId);
		if(jcRec==null){
			jcRec=new JcRec();
			jcRec.setJcType(jcType);
			jcRec.setJcNum(jcNum);
			jcRec.setTaskDate(takeTime);
			jcRec.setAreaId(areaId);
			queryDao.saveOrUpdateJcRec(jcRec);
		}
		Long jcRecId=jcRec.getJcRecId();
		ProcedureInfoRec procedureInfoRec=queryDao.findProcedureInfoRec(jcRecId, proId);
		if(procedureInfoRec==null){
			procedureInfoRec=new ProcedureInfoRec();
			procedureInfoRec.setJcRec(jcRec);
			procedureInfoRec.setProcedureInfo(procedureInfo);
			procedureInfoRec.setProcedureRank(proRank);
			procedureInfoRec.setWorkerName(takeName);
			queryDao.saveOrUpdateProcedureInfoRec(procedureInfoRec);
		}
		TakedetailRec takeDetaiRec=new TakedetailRec();
		takeDetaiRec.setType(type);
		takeDetaiRec.setUrl(url);
		takeDetaiRec.setProcedureInfoRec(procedureInfoRec);
		takeDetaiRec.setTakeId(takeId);
		takeDetaiRec.setTakeName(takeName);
		takeDetaiRec.setTakeTime(takeTime);
		takeDetaiRec.setVideoTime(videoTime);
		takeDetaiRec.setUploadTime(uploadTime);
		queryDao.saveOrUpdateTakedetailRec(takeDetaiRec);
	}
	
	//先添加jcRec和ProcedureInfoRec信息,分开提交信息，因为takeDetaiRec涉及到触发器
	public ProcedureInfoRec addJcRecAndProInfoRec(String jcType, String jcNum,
			String takeTime, String takeName, Integer areaId, Long proId,
			String proRank) {
		JcRec jcRec=queryDao.findJcRec(jcNum, takeTime, areaId);
		ProcedureInfo procedureInfo=queryDao.findProInfoByProId(proId);
		if(jcRec==null){
			jcRec=new JcRec();
			jcRec.setJcType(jcType);
			jcRec.setJcNum(jcNum);
			jcRec.setTaskDate(takeTime);
			jcRec.setAreaId(areaId);
			queryDao.saveOrUpdateJcRec(jcRec);
		}
		Long jcRecId=jcRec.getJcRecId();
		ProcedureInfoRec procedureInfoRec=queryDao.findProcedureInfoRec(jcRecId, proId);
		if(procedureInfoRec==null){
			procedureInfoRec=new ProcedureInfoRec();
			procedureInfoRec.setJcRec(jcRec);
			procedureInfoRec.setProcedureInfo(procedureInfo);
			procedureInfoRec.setProcedureRank(proRank);
			procedureInfoRec.setWorkerName(takeName);
			queryDao.saveOrUpdateProcedureInfoRec(procedureInfoRec);
		}
		return procedureInfoRec;
	}
	
	public void addTakeDetailRec(Integer type,String url,ProcedureInfoRec procedureInfoRec,
			Integer takeId,String takeTime,String takeName,Double videoTime,String uploadTime){
		TakedetailRec takeDetaiRec=new TakedetailRec();
		takeDetaiRec.setType(type);
		takeDetaiRec.setUrl(url);
		takeDetaiRec.setProcedureInfoRec(procedureInfoRec);
		takeDetaiRec.setOrderNum(0);
		takeDetaiRec.setTakeId(takeId);
		takeDetaiRec.setTakeName(takeName);
		takeDetaiRec.setTakeTime(takeTime);
		takeDetaiRec.setVideoTime(videoTime);
		takeDetaiRec.setUploadTime(uploadTime);
		queryDao.saveOrUpdateTakedetailRec(takeDetaiRec);
	}

	public Map<String, String> addMapData(String gonghao, String proNum,
			String jcNum) {
		Map<String,String> map=new HashMap<String,String>();
		DictUsers user=queryDao.findUserInfoByGonghao(gonghao);
		ProcedureInfo procedure=queryDao.findProInfoByProNum(proNum);
		if(procedure==null){
			procedure=new ProcedureInfo();
			procedure.setProName("错误工序");
			procedure.setProNum(proNum);
			queryDao.saveOrUpdateProcedureInfo(procedure);
		}
		DictJcstype jc=queryDao.findJcInfoByJcNum(jcNum);
		map.put("take_id",user==null?"":user.getUserId()+"");
		map.put("take_name",user==null?"":user.getName());
		map.put("pro_id", procedure==null?"":procedure.getProId()+"");
		map.put("pro_name",procedure==null?"":procedure.getProName());
		map.put("jc_type",jc==null?"":jc.getJcStype());
		return map;
	}
}
