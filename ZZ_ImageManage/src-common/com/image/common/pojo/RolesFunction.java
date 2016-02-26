package com.image.common.pojo;

import java.io.Serializable;

/**
 * 角色功能表
 * 
 * @author lll
 * 
 */
public class RolesFunction implements Serializable {


	private static final long serialVersionUID = -5957148633342013620L;
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 角色
	 */
	private DictRoles roles;
	
	/**
	 * 功能
	 */
	private DictFunctions functions;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DictRoles getRoles() {
		return roles;
	}

	public void setRoles(DictRoles roles) {
		this.roles = roles;
	}

	public DictFunctions getFunctions() {
		return functions;
	}

	public void setFunctions(DictFunctions functions) {
		this.functions = functions;
	}
	
}
