package com.image.query.service;
import java.util.List;
import java.util.Map;

import com.image.common.pojo.DictAreas;
import com.image.common.pojo.DictWorktype;
import com.image.common.pojo.JcRec;
import com.image.common.pojo.ProcedureInfo;
import com.image.common.pojo.ProcedureInfoRec;
import com.image.common.pojo.TakedetailRec;
import com.image.common.util.PageModel;


/**
 * @author eleven
 *
 */
public interface QueryService {
	
	public List<Map<String, String>> findAllUploadRecOnJc(String jcType, String jcNum, String taskDate, String areaId, String jcRecId);
	
	public List<DictWorktype> findAllDictWorktype();
	
	public Map<JcRec, Map<DictWorktype, List<ProcedureInfo>>> constructProTree(Long jcRecId);
	
	public List<Map<String, String>> findDetailUploadRecOnJc(String jcRecId, String proId, String workId);
	
	public Map<DictWorktype, Map<ProcedureInfo, Map<ProcedureInfoRec, List<TakedetailRec>>>> listDetailUploadRecOfWorkTypeOnPlan(String jcRecId, String workId, String type);
	
	public Map<DictWorktype ,Map<ProcedureInfoRec, Map<String, List<Object>>>> findTakedetailRecByPeoecId(Long proecId, Long workId);
	

	/**
	 * 查询所有的机车记录信息
	 * @return
	 */
	public PageModel<JcRec> findJcRecAll();
	
	/**
	 * 查询上传记录明细信息
	 * @param jcRecId
	 * @return
	 */
	public List<Object[]> findTekeDetailRec(Long jcRecId);
	
	public JcRec findTakeTime(long jcRecId);
	
	public DictAreas findDictArea(long areaId);
	
	/**
	 * 根据机车记录ID查询地区信息
	 * @param jcRecId
	 * @return
	 */
	public DictAreas findDictAreaByJcRecId(long jcRecId);
	
    /**
     * 根据工序编号查询工序信息
     * @param proNums
     * @return
     */
	public List<Object[]> findProInfoByProNum(List<String> proNums);
	
	/**
	 * 根据工号获得用户信息
	 * @param gonghao
	 * @return
	 */
	public List<Object[]> findUserInfoByGonghao(List<String> gonghao);
	
	/**
	 * 根据机车编号获取机车信息
	 * @param jcNums
	 * @return
	 */
	public List<Object[]> findJcInfoByJcNum(List<String> jcNums);
	
	/**
	 * 根据地址判断地址是否存在
	 * @param url
	 * @return
	 */
	public Integer findUrlNum(String url);
	
	/**
	 * 添加JSON数据
	 */
	public void addJSONData(String jcType,String jcNum,Integer takeId,String takeTime,String takeName,Integer areaId,Long proId,
			String proRank,Integer type,String url,Double videoTime,String uploadTime);
	
	/**
	 * 根据相应的条件查询相应的信息
	 * @param gonghao--用户工号得到用户名称
	 * @param proNum--工序编号得到工序名称
	 * @param jcNum--机车编号得到机车类型
	 * @return
	 */
	public Map<String,String> addMapData(String gonghao,String proNum,String jcNum);
	
	/**
	 * 插入JcRec信息和ProcedureInfoRec信息，并将ProcedureInfoRec信息返回回来
	 * 添加机车记录信息和工序记录信息
	 * @param jcType
	 * @param jcNum
	 * @param takeTime
	 * @param takeName
	 * @param areaId
	 * @param proId
	 * @param proRank
	 * @return
	 */
	public ProcedureInfoRec addJcRecAndProInfoRec(String jcType,String jcNum,String takeTime,String takeName,Integer areaId,Long proId,String proRank);
	
	/**
	 * 添加记录明细信息
	 * @param type
	 * @param url
	 * @param procedureInfoRec
	 * @param takeId
	 * @param takeTime
	 * @param takeName
	 * @param videoTime
	 * @param uploadTime
	 */
	public void addTakeDetailRec(Integer type,String url,ProcedureInfoRec procedureInfoRec,Integer takeId,String takeTime,String takeName,Double videoTime,String uploadTime);
	
}
