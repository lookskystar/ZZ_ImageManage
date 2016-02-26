package com.image.common.pojo;

import java.io.Serializable;

/**
 * 功能表
 * 
 * @author lll
 * 
 */
public class DictFunctions implements Serializable {


	private static final long serialVersionUID = 310889121039603304L;
	/**
	 * 主键
	 */
	private Long funcId;
	/**
	 * 功能名称
	 */
	private String funcName;
	
	/**
	 * 功能访问url
	 */
	private String funcUrl;
	
	/**
	 *功能备注
	 */
	private String funcNote;

	/**
	 *父级ID
	 */
	private Long parentId;
	
	
	
	public Long getFuncId() {
		return funcId;
	}

	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getFuncUrl() {
		return funcUrl;
	}

	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}

	public String getFuncNote() {
		return funcNote;
	}

	public void setFuncNote(String funcNote) {
		this.funcNote = funcNote;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	
	
}
