package com.image.set.service.impl;

import java.util.List;
import javax.annotation.Resource;

import com.image.common.pojo.DictWorktype;
import com.image.common.pojo.ProcedureInfo;
import com.image.common.pojo.ProcedureStep;
import com.image.common.util.PageModel;
import com.image.set.dao.PresetImageDao;
import com.image.set.service.PresetImageService;

public class PresetImageServiceImpl implements PresetImageService {

	@Resource(name = "presetImageDao")
	private PresetImageDao presetImageDao;

	public PageModel<ProcedureInfo> findPresetAll(String jcStype, String proName) {
		return presetImageDao.findPresetAll(jcStype, proName);
	}

	public List<String> findJcstypeAll() {
		return presetImageDao.findJcstypeAll();
	}

	public List<String> findProcedureAll() {
		return presetImageDao.findProcedureAll();
	}

	public void deletePreset(long id) {
		presetImageDao.deletePreset(id);
	}

	public void savePreset(ProcedureInfo procedureInfo) {
		presetImageDao.savePreset(procedureInfo);
	}

	public ProcedureInfo findProcedureById(long id) {
		return presetImageDao.findProcedureById(id);
	}

	public List<ProcedureStep> findProcedureImageById(long id) {
		return presetImageDao.findProcedureImageById(id);
	}

	public void saveProcedureStep(ProcedureStep procedureStep) {
		presetImageDao.saveProcedureStep(procedureStep);
	}

	public void deleteProcedureStepById(long stepId) {
		presetImageDao.deleteProcedureStepById(stepId);
	}

	public ProcedureStep findProcedureStepById(long stepId) {
		return presetImageDao.findProcedureStepById(stepId);
	}

	public boolean findProNum(String proNum) {
		return presetImageDao.findProNum(proNum);
	}

	public List<DictWorktype> findDictWorktype() {
		return presetImageDao.findDictWorktype();
	}

}
