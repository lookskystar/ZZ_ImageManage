package com.image.set.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.enterprisedt.net.ftp.FileTransferClient;
import com.image.common.pojo.DictWorktype;
import com.image.common.pojo.ProcedureInfo;
import com.image.common.pojo.ProcedureStep;
import com.image.common.util.Contains;
import com.image.common.util.FTPUnit;
import com.image.common.util.PageModel;
import com.image.set.service.PresetImageService;

public class PresetImageAction {

	@Resource(name = "presetImageService")
	private PresetImageService presetImageService;
	private HttpServletRequest request = ServletActionContext.getRequest();

	private ProcedureInfo procedureInfo;

	private File file;
	private String fileFileName;

	private long proId;

	/**
	 * 进入工序标准预设页面
	 * */
	public String presetList() {
		PageModel<ProcedureInfo> procedures = presetImageService.findPresetAll(
				null, null);
		List<String> jcStypes = presetImageService.findJcstypeAll();
		List<String> procedure = presetImageService.findProcedureAll();

		request.setAttribute("procedures", procedures);
		request.setAttribute("jcStypes", jcStypes);
		request.setAttribute("procedure", procedure);
		return "presetList";
	}

	/**
	 * 根据条件查询工序标准
	 * */
	public String presetListByName() {
		String jcStype = request.getParameter("jcStypes");
		String proce = request.getParameter("procedure");
		PageModel<ProcedureInfo> procedures = presetImageService.findPresetAll(
				jcStype, proce);
		List<String> jcStypes = presetImageService.findJcstypeAll();
		List<String> procedure = presetImageService.findProcedureAll();

		request.setAttribute("jcStype", jcStype);
		request.setAttribute("proce", proce);
		request.setAttribute("procedures", procedures);
		request.setAttribute("jcStypes", jcStypes);
		request.setAttribute("procedure", procedure);
		return "presetList";
	}

	/**
	 * 删除工序标准
	 * */
	public String deletePresetNode() {
		long id = Long.parseLong(request.getParameter("id"));
		presetImageService.deletePreset(id);
		request.setAttribute("message", "工序标准删除成功！");
		return presetList();
	}

	/**
	 * 进入添加标准页面
	 * */
	public String inputAddProcedure() {
		List<String> jcStypes = presetImageService.findJcstypeAll();
		List<DictWorktype> workers = presetImageService.findDictWorktype();
		request.setAttribute("workers", workers);
		request.setAttribute("jcStypes", jcStypes);
		return "inputAddProcedure";
	}

	/**
	 * 添加标准页面
	 * */
	public String addPreset() {
		presetImageService.savePreset(procedureInfo);
		request.setAttribute("message", "工序标准添加成功!");
		return presetList();
	}

	/**
	 * 进入修改标准页面
	 * */
	public String updatePresetInput() {
		long id = Long.parseLong(request.getParameter("id"));
		ProcedureInfo procedureInfo = presetImageService.findProcedureById(id);
		List<String> jcStypes = presetImageService.findJcstypeAll();
		List<DictWorktype> workers = presetImageService.findDictWorktype();
		
		request.setAttribute("workers", workers);
		request.setAttribute("jcStypes", jcStypes);
		request.setAttribute("procedureInfo", procedureInfo);
		return "updatePresetInput";
	}

	/**
	 * 修改标准工序
	 * */
	public String updatePreset() {
		long id = Long.parseLong(request.getParameter("id"));
		ProcedureInfo procedure = presetImageService.findProcedureById(id);

		procedure.setImageNum(procedureInfo.getImageNum());
		procedure
				.setImageTimeDifference(procedureInfo.getImageTimeDifference());
		procedure.setJcType(procedureInfo.getJcType());
		procedure.setProName(procedureInfo.getProName());
		procedure.setProTimeDifference(procedureInfo.getProTimeDifference());
		procedure.setVideoTime(procedureInfo.getVideoTime());
		procedure.setProNote(procedureInfo.getProNote());
		procedure.setProSn(procedureInfo.getProSn());
		procedure.setProType(procedureInfo.getProType());
		procedure.setDictWorktype(procedureInfo.getDictWorktype());
		presetImageService.savePreset(procedure);
		request.setAttribute("message", "工序标准修改成功！");
		return presetList();
	}

	/**
	 * 进入标准工序图片上传页面
	 * */
	public String presetImageUplodInput() {
		long id = Long.parseLong(request.getParameter("id"));
		ProcedureInfo procedure = presetImageService.findProcedureById(id);
		List<ProcedureStep> procedureStep = presetImageService
				.findProcedureImageById(id);

		request.setAttribute("procedureStep", procedureStep);
		request.setAttribute("procedure", procedure);
		return "presetImageUplodInput";
	}

	/**
	 * 上传工序标准图片
	 * 
	 * @return
	 */
	public String nodeImageUpload() throws Exception {
		try {
			FileTransferClient ftpClient = FTPUnit.getFileTransferClient(
					(String) request.getSession().getAttribute(
							"session_ftp_url"), Integer.parseInt(request
							.getSession().getAttribute("session_ftp_port")
							+ ""), (String) request.getSession().getAttribute(
							"session_ftp_username"), (String) request
							.getSession().getAttribute("session_ftp_password"));

			long id = Long.parseLong(request.getParameter("nodeId"));
			ProcedureInfo procedure = presetImageService.findProcedureById(id);

			// 文件FTP上传

			String folderPath = "/" + Contains.STANDARD_IMAGE_FOLDER + "/"
					+ procedure.getJcType() + "/" + procedure.getProNum() + "/";

			Map<String, String[]> map = FTPUnit.getImagePath(
					new String[] { fileFileName }, folderPath);
			String remoteFilePath = map.get("imgurl")[0];// 原文件
			String minRemoteFilePath = map.get("preimgurl")[0];// 压缩文件

			FTPUnit.upload(file, remoteFilePath, folderPath, ftpClient);
			// 图像压缩
			FTPUnit.zipWidthHeightImageFile(remoteFilePath, minRemoteFilePath,
					Contains.IMAGE_ZIP_WIDTH, Contains.IMAGE_ZIP_HEIGHT,
					Contains.IMAGE_ZIP_QUALITY, ftpClient);

			FTPUnit.closeFileTransferClient(ftpClient);

			ProcedureStep procedureStep = new ProcedureStep();
			procedureStep.setProId(procedure.getProId());
			procedureStep.setStepName(request.getParameter("title"));
			procedureStep.setProSn(Integer.parseInt(request
					.getParameter("stepNum")));
			procedureStep.setPrestepImage(minRemoteFilePath);
			procedureStep.setStepImage(remoteFilePath);

			presetImageService.saveProcedureStep(procedureStep);
			ServletActionContext.getResponse().getWriter().print("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 进入修改节点标准图片
	 * 
	 * @return
	 */
	public String updateNodeImageInput() throws Exception {
		long imageId = Long.parseLong(request.getParameter("imageId"));
		ProcedureStep nodeImage = presetImageService
				.findProcedureStepById(imageId);
		request.setAttribute("nodeImage", nodeImage);
		request.setAttribute("nodeId", proId);
		return "stepUpdate";
	}

	/**
	 * 修改节点标准图片
	 * 
	 * @return
	 */
	public String updateNodeImage() throws Exception {
		long imageId = Long.parseLong(request.getParameter("imageId"));
		ProcedureStep nodeImage = presetImageService
				.findProcedureStepById(imageId);
		ProcedureInfo procedure = presetImageService.findProcedureById(proId);

		if (file != null) {
			FileTransferClient ftpClient = FTPUnit.getFileTransferClient(
					(String) request.getSession().getAttribute(
							"session_ftp_url"), Integer.parseInt(request
							.getSession().getAttribute("session_ftp_port")
							+ ""), (String) request.getSession().getAttribute(
							"session_ftp_username"), (String) request
							.getSession().getAttribute("session_ftp_password"));

			FTPUnit.delete(new String[] { nodeImage.getStepImage(),
					nodeImage.getPrestepImage() }, 0, ftpClient);
			// 文件FTP上传
			String folderPath = "/" + Contains.STANDARD_IMAGE_FOLDER + "/"
					+ procedure.getJcType() + "/" + procedure.getProNum() + "/";

			Map<String, String[]> map = FTPUnit.getImagePath(
					new String[] { fileFileName }, folderPath);
			String remoteFilePath = map.get("imgurl")[0];// 原文件
			String minRemoteFilePath = map.get("preimgurl")[0];// 压缩文件

			FTPUnit.upload(file, remoteFilePath, folderPath, ftpClient);
			// 图像压缩
			FTPUnit.zipWidthHeightImageFile(remoteFilePath, minRemoteFilePath,
					Contains.IMAGE_ZIP_WIDTH, Contains.IMAGE_ZIP_HEIGHT,
					Contains.IMAGE_ZIP_QUALITY, ftpClient);

			nodeImage.setStepImage(remoteFilePath);
			nodeImage.setPrestepImage(minRemoteFilePath);

			FTPUnit.closeFileTransferClient(ftpClient);
		}

		nodeImage.setStepName(request.getParameter("title"));
		nodeImage.setProSn(Integer.parseInt(request.getParameter("stepNum")));
		presetImageService.saveProcedureStep(nodeImage);
		ServletActionContext.getResponse().getWriter().print("success");
		return null;
	}

	/**
	 * 删除工序标准图片
	 * 
	 * @return
	 */
	public String deleteNodeImage() throws Exception {
		FileTransferClient ftpClient = FTPUnit.getFileTransferClient(
				(String) request.getSession().getAttribute("session_ftp_url"),
				Integer.parseInt(request.getSession().getAttribute(
						"session_ftp_port")
						+ ""), (String) request.getSession().getAttribute(
						"session_ftp_username"), (String) request.getSession()
						.getAttribute("session_ftp_password"));

		long imageId = Long.parseLong(request.getParameter("imageId"));
		ProcedureStep procedureStep = presetImageService
				.findProcedureStepById(imageId);
		FTPUnit.delete(new String[] { procedureStep.getStepImage(),
				procedureStep.getPrestepImage() }, 0, ftpClient);
		FTPUnit.closeFileTransferClient(ftpClient);
		presetImageService.deleteProcedureStepById(imageId);
		ServletActionContext.getResponse().getWriter().print("success");
		return null;
	}

	/**
	 * 检查工序编码是否存在
	 * @throws IOException 
	 * */
	public String checkNum() throws IOException{
		String num = request.getParameter("proNum");
		Boolean flag = presetImageService.findProNum(num);
		if(flag){
			ServletActionContext.getResponse().getWriter().print("success");
		}
		return null;
	}
	
	public ProcedureInfo getProcedureInfo() {
		return procedureInfo;
	}

	public void setProcedureInfo(ProcedureInfo procedureInfo) {
		this.procedureInfo = procedureInfo;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public long getProId() {
		return proId;
	}

	public void setProId(long proId) {
		this.proId = proId;
	}

}
