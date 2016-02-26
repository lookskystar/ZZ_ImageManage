package com.image.common.pojo;

import java.io.Serializable;

/**
 * 车型车号表
 * */
public class DictJcstype implements Serializable{

	private static final long serialVersionUID = -8181762919826238557L;
	/**
	 * 主键
	 */
	private Long Id;
	/**
	 * 车号
	 */
	private String jcNum;
	
	/**
	 * 车型
	 */
	private String jcStype;

	/**
	 * 唯一标识
	 */
	private String jcIdentify;
	
	/**
	 * 地区ID
	 */
	private Integer areaId;
	
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getJcNum() {
		return jcNum;
	}

	public void setJcNum(String jcNum) {
		this.jcNum = jcNum;
	}

	public String getJcStype() {
		return jcStype;
	}

	public void setJcStype(String jcStype) {
		this.jcStype = jcStype;
	}

	public String getJcIdentify() {
		return jcIdentify;
	}

	public void setJcIdentify(String jcIdentify) {
		this.jcIdentify = jcIdentify;
	}
	
}
