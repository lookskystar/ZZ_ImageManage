package com.image.common.pojo;

import java.io.Serializable;

/**
 * 班组表
 * 
 * @author lll
 * 
 */
public class DictTeams implements Serializable {


	private static final long serialVersionUID = -4358254416830878020L;
	
	/**
	 * 主键
	 */
	private Long teamId;
	/**
	 * 班组名字
	 */
	private String teamName;
	
	/**
	 * 地区ID
	 */
	private Long areaId;
	
	/**
	 * 等级    1：段级部门   2:所级部门
	 * @return
	 */
	private Integer teamLevel;
	
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public Integer getTeamLevel() {
		return teamLevel;
	}
	public void setTeamLevel(Integer teamLevel) {
		this.teamLevel = teamLevel;
	}
}
