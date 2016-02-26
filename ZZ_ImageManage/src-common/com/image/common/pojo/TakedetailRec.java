package com.image.common.pojo;

import java.io.Serializable;

/**
 * 上传记录明细表
 * 
 * @author lll
 * 
 */
public class TakedetailRec implements Serializable {

	
	private static final long serialVersionUID = 2798922991283529463L;
	
	/**
	 * 主键
	 */
	private Long detailId;
	/**
	 * 类型0：图片 1：视频
	 */
	private Integer type;

	/**
	 * 排序号:默认为0
	 */
	private Integer orderNum;

	/**
	 *存放路径
	 */
	private String url;
	
	/**
	 *缩略图存放路径
	 */
	private String preurl;

	/**
	 * 对应工序记录
	 */
	private ProcedureInfoRec procedureInfoRec;

	/**
	 * 工序步骤
	 */
	private Integer stepId;

	/**
	 *工序步骤名字
	 */
	private String stepName;
	/**
	 * 工序步骤标准图片
	 */
	private String stepImage;
	
	/**
	 * 缩略图
	 */
	private String prestepImage;
	/**
	 * 拍摄人ID
	 */
	private Integer takeId;

	/**
	 *拍摄人
	 */
	private String takeName;

	/**
	 *拍摄人时间
	 */
	private String takeTime;

	/**
	 * 视频时长
	 */
	private Double videoTime;
	
	/**
	 * 上传时间
	 */
	private String uploadTime;

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ProcedureInfoRec getProcedureInfoRec() {
		return procedureInfoRec;
	}

	public void setProcedureInfoRec(ProcedureInfoRec procedureInfoRec) {
		this.procedureInfoRec = procedureInfoRec;
	}

	public Integer getStepId() {
		return stepId;
	}

	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public String getStepImage() {
		return stepImage;
	}

	public void setStepImage(String stepImage) {
		this.stepImage = stepImage;
	}

	public Integer getTakeId() {
		return takeId;
	}

	public void setTakeId(Integer takeId) {
		this.takeId = takeId;
	}

	public String getTakeName() {
		return takeName;
	}

	public void setTakeName(String takeName) {
		this.takeName = takeName;
	}

	public String getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(String takeTime) {
		this.takeTime = takeTime;
	}

	public Double getVideoTime() {
		return videoTime;
	}

	public void setVideoTime(Double videoTime) {
		this.videoTime = videoTime;
	}

	public String getPreurl() {
		return preurl;
	}

	public void setPreurl(String preurl) {
		this.preurl = preurl;
	}

	public String getPrestepImage() {
		return prestepImage;
	}

	public void setPrestepImage(String prestepImage) {
		this.prestepImage = prestepImage;
	}

	
	
}
