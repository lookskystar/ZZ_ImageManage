package com.image.common.pojo;

import java.io.Serializable;


/**
 * 车记录表
 * 
 * @author eleven
 * 
 */

public class JcRec implements Serializable{

	private static final long serialVersionUID = -828432801317993963L;
	
	/**
	 * 代理主键
	 */
	private Long jcRecId;
	
	/**
	 * 车型
	 */
	private String jcType;
	
	/**
	 * 车号
	 */
	private String jcNum;
	
	/**
	 * 作业日期
	 */
	private String taskDate;

	/**
	 * 地区
	 */
	private Integer areaId;
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Long getJcRecId() {
		return jcRecId;
	}

	public void setJcRecId(Long jcRecId) {
		this.jcRecId = jcRecId;
	}

	public String getJcType() {
		return jcType;
	}

	public void setJcType(String jcType) {
		this.jcType = jcType;
	}

	public String getJcNum() {
		return jcNum;
	}

	public void setJcNum(String jcNum) {
		this.jcNum = jcNum;
	}

	public String getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}
	
}
