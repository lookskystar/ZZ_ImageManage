package com.image.common.pojo;

import java.io.Serializable;

public class TrainPlan implements Serializable{

	/**
	 * 车次计划表
	 */
	private static final long serialVersionUID = -7009664634341349361L;
	/**
	 * 主键ID
	 * */
	private long id;
	/**
	 * 车号
	 * */
	private String jcNum;
	/**
	 * 始发站
	 * */
	private String started;
	/**
	 * 终点站
	 * */
	private String ended;
	/**
	 * 车次号
	 * */
	private String cjNum;
	/**
	 * 计划时间
	 * */
	private String time;
	/**
	 * 检查次数
	 * */
	private String jcCs;
	/**
	 * 车型
	 * */
	private String jcStyle;
	/**
	 * 地区
	 * */
	private long areaId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getJcNum() {
		return jcNum;
	}
	public void setJcNum(String jcNum) {
		this.jcNum = jcNum;
	}
	public String getStarted() {
		return started;
	}
	public void setStarted(String started) {
		this.started = started;
	}
	public String getEnded() {
		return ended;
	}
	public void setEnded(String ended) {
		this.ended = ended;
	}
	public String getCjNum() {
		return cjNum;
	}
	public void setCjNum(String cjNum) {
		this.cjNum = cjNum;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getJcCs() {
		return jcCs;
	}
	public void setJcCs(String jcCs) {
		this.jcCs = jcCs;
	}
	public String getJcStyle() {
		return jcStyle;
	}
	public void setJcStyle(String jcStyle) {
		this.jcStyle = jcStyle;
	}
	public long getAreaId() {
		return areaId;
	}
	public void setAreaId(long areaId) {
		this.areaId = areaId;
	}
		
}
