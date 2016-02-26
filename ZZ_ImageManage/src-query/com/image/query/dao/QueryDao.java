package com.image.query.dao;
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
import com.image.common.util.PageModel;


public interface QueryDao {
	public List<Map<String, String>> findAllUploadRecOnJc(String jcType, String jcNum, String taskDate, String areaId, String jcRecId);
	
	public List<DictWorktype> findAllDictWorktype();
	
	public List<ProcedureInfo> finProcedureInfoByWorkId(Long workId);
	
	public List<Map<String, String>> findDetailUploadRecOnJc(String jcRecId, String proId, String workId);
	
	public JcRec findDictJcRecById(Long jcRecId);
	
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
	
	public DictWorktype findDictWorkTypeById(Long workId);
	
	public List<TakedetailRec> findTakedetailRecByPeoecId(Long proecId);
	
	public List<ProcedureInfoRec> findProcedureInfoRecByProId(Long proId);
	
	public ProcedureInfoRec findProcedureInfoRecById(Long proecId);
	
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
     * 根据工序编号查询工序信息
     * @param proNums
     * @return
     */
	public ProcedureInfo findProInfoByProNum(String proNum);
	
    /**
     * 根据工序编号查询工序信息
     * @param proNums
     * @return
     */
	public ProcedureInfo findProInfoByProId(Long proId);
	
	/**
	 * 保存工序信息
	 * @param procedureInfo
	 */
	public void saveOrUpdateProcedureInfo(ProcedureInfo procedureInfo);
	
	/**
	 * 根据工号获得用户信息
	 * @param gonghao
	 * @return
	 */
	public List<Object[]> findUserInfoByGonghao(List<String> gonghao);
	
	/**
	 * 根据工号获得用户信息
	 * @param gonghao
	 * @return
	 */
	public DictUsers findUserInfoByGonghao(String gonghao);
	
	/**
	 * 保存用户信息
	 * @param user
	 */
	public void saveOrUpdateUser(DictUsers user);
	
	/**
	 * 根据机车编号获取机车信息
	 * @param jcNums
	 * @return
	 */
	public List<Object[]> findJcInfoByJcNum(List<String> jcNums);
	
	/**
	 * 根据机车编号获取机车信息
	 * @param jcNums
	 * @return
	 */
	public DictJcstype findJcInfoByJcNum(String jcNum);
	
	/**
	 * 根据地址判断地址是否存在
	 * @param url
	 * @return
	 */
	public Integer findUrlNum(String url);
	
	/**
	 * 根据条件获得jcRec信息
	 * @param jcNum
	 * @param takeTime
	 * @param areaId
	 * @return
	 */
	public JcRec findJcRec(String jcNum,String takeTime,Integer areaId);
	
	/**
	 * 保存机车记录信息
	 * @param jcNum
	 * @param takeTime
	 * @param areaId
	 */
	public void saveOrUpdateJcRec(JcRec jcRec);
	
	/**
	 * 查询工序记录信息
	 * @param jcRecId
	 * @param proId
	 * @return
	 */
	public ProcedureInfoRec findProcedureInfoRec(Long jcRecId,Long proId);
	
	/**
	 * 保存工序记录信息
	 * @param procedureInfoRec
	 */
	public void saveOrUpdateProcedureInfoRec(ProcedureInfoRec procedureInfoRec);
	
	/**
	 * 保存上传记录明细信息
	 * @param takeDetaiRec
	 */
	public void saveOrUpdateTakedetailRec(TakedetailRec takeDetaiRec);
	
}
