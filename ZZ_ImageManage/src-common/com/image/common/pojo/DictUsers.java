package com.image.common.pojo;

import java.io.Serializable;

/**
 * 用户表
 * 
 * @author lll
 * 
 */
public class DictUsers implements Serializable {


	private static final long serialVersionUID = -7326632630727575354L;
	
	/**
	 * 主键
	 */
	private Long userId;
	/**
	 * 登录名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 角色
	 */
	private Long roleId;
	
	/**
	 * 地区
	 */
	private Long areaId;
	
	/**
	 *姓名	
	 */
	private String name;
	
	/**
	 *班组ID	
	 */
	private Long teamId;
	
	/**
	 *id卡号	
	 */
	private String idnum;
	
	/**
	 *拼音缩写
	 */
	private String py;
	
	/**
	 *工号
	 */
	private String gonghao;

	/**
	 * 是否启用
	 * 0禁用    1启用
	 */
	private Integer isuser;
	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	public String getGonghao() {
		return gonghao;
	}

	public void setGonghao(String gonghao) {
		this.gonghao = gonghao;
	}

	public String getPy() {
		return py;
	}

	public void setPy(String py) {
		this.py = py;
	}

	public Integer getIsuser() {
		return isuser;
	}

	public void setIsuser(Integer isuser) {
		this.isuser = isuser;
	}

	
}
