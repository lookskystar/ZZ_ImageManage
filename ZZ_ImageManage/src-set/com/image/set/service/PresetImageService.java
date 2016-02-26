package com.image.set.service;

import java.util.List;

import com.image.common.pojo.DictWorktype;
import com.image.common.pojo.ProcedureInfo;
import com.image.common.pojo.ProcedureStep;
import com.image.common.util.PageModel;

public interface PresetImageService {

	/**
	 * 查询所有工序标准
	 * */
	public PageModel<ProcedureInfo> findPresetAll(String jcStype, String proName);

	/**
	 * 查询所有车型
	 * */
	public List<String> findJcstypeAll();

	/**
	 * 查询所有工序
	 * */
	public List<String> findProcedureAll();

	/**
	 * 删除工序标准
	 * */
	public void deletePreset(long id);

	/**
	 * 保存标准
	 * */
	public void savePreset(ProcedureInfo procedureInfo);

	/**
	 * 根据ID查找工序标准
	 * */
	public ProcedureInfo findProcedureById(long id);

	/**
	 * 根据ID查找工序标准图片
	 * */
	public List<ProcedureStep> findProcedureImageById(long id);

	/**
	 * 保存标准工序步骤
	 * */
	public void saveProcedureStep(ProcedureStep procedureStep);

	/**
	 * 根据stepID查找工序标准图片
	 * */
	public ProcedureStep findProcedureStepById(long stepId);

	/**
	 * 根据stepID删除工序标准图片
	 * */
	public void deleteProcedureStepById(long stepId);
	
	/**
	 * 检查工序编码是否存在
	 * */
	public boolean findProNum(String proNum);
	
	/**
	 * 查询工序大类
	 * */
	public List<DictWorktype> findDictWorktype();
}
