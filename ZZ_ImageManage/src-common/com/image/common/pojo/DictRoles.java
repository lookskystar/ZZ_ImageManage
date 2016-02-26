package com.image.common.pojo;

import java.io.Serializable;

/**
 * 角色表
 * 
 * @author lll
 * 
 */
public class DictRoles implements Serializable {


	private static final long serialVersionUID = 5252592915611349458L;
	/**
	 * 主键
	 */
	private Long roleId;
	/**
	 * 角色名称
	 */
	private String roleName;
	
	/**
	 * 角色拼音
	 */
	private String rolePy;
	/**
	 *角色级别 0:车间级 1:段级
	 */
	private Integer roleLevel;
	/**
	 *角色备注
	 */
	private String roleNote;
	
	
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRolePy() {
		return rolePy;
	}
	public void setRolePy(String rolePy) {
		this.rolePy = rolePy;
	}
	public Integer getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(Integer roleLevel) {
		this.roleLevel = roleLevel;
	}
	public String getRoleNote() {
		return roleNote;
	}
	public void setRoleNote(String roleNote) {
		this.roleNote = roleNote;
	}


}
