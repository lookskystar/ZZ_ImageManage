package com.image.common.pojo;

import java.io.Serializable;

/**
 * 作业大类表
 * 
 * @author lll
 * 
 */
public class DictWorktype implements Serializable {



	private static final long serialVersionUID = 6868871960681827460L;
	
	/**
	 * 主键
	 */
	private Long workId;
	/**
	 * 类型
	 */
	private String workType;
	
	
	public Long getWorkId() {
		return workId;
	}
	public void setWorkId(Long workId) {
		this.workId = workId;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}

}
