package com.image.common.pojo;

import java.io.Serializable;

/**
 * 标准工序步骤表
 * 
 * @author lll
 * 
 */
public class ProcedureStep implements Serializable {


	private static final long serialVersionUID = -6547460853340008875L;
	
	/**
	 * 主键
	 */
	private Long stepId;
	/**
	 * 名字
	 */
	private String stepName;

	/**
	 *外键关联工序表
	 */
	private long proId;
	
	/**
	 * 缩略图片
	 */
	private String prestepImage;

	/**
	 * 标准图片
	 */
	private String stepImage;

	/**
	 * 顺序号：越大越靠前
	 */
	private Integer proSn;

	
	
	public Long getStepId() {
		return stepId;
	}

	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public long getProId() {
		return proId;
	}

	public void setProId(long proId) {
		this.proId = proId;
	}

	public String getStepImage() {
		return stepImage;
	}

	public void setStepImage(String stepImage) {
		this.stepImage = stepImage;
	}

	public Integer getProSn() {
		return proSn;
	}

	public void setProSn(Integer proSn) {
		this.proSn = proSn;
	}

	public String getPrestepImage() {
		return prestepImage;
	}

	public void setPrestepImage(String prestepImage) {
		this.prestepImage = prestepImage;
	}

	
	
}
