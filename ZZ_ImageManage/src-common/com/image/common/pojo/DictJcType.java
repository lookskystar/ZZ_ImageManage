package com.image.common.pojo;

import java.io.Serializable;

/**
 * 机车类型表
 * */
public class DictJcType implements Serializable{

	private static final long serialVersionUID = 8222310916393529030L;
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 车型
	 */
	private String jcType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getJcType() {
		return jcType;
	}
	public void setJcType(String jcType) {
		this.jcType = jcType;
	}
}
