package com.image.common.pojo;

import java.io.Serializable;

/**
 * 工序记录表
 * 
 * @author eleven
 * 
 */
public class ProcedureInfoRec implements Serializable {

	private static final long serialVersionUID = 504882080044312768L;
	
	/**
	 * 主键
	 */
	private Long proRecId;
	
	/**
	 * 所属车记录
	 */
	private JcRec jcRec;
	

	/**
	 * 对应工序
	 */
	private ProcedureInfo procedureInfo;
	
	/**
	 * 工序次数
	 */
	private String procedureRank;
	
	/**
	 * 状态 0:正常 1：异常(图片缺省) 2:异常(图片时间异常)
	 */
	private Integer status;
	/**
	 * 作业者
	 */
	private String workerName;
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getProRecId() {
		return proRecId;
	}

	public void setProRecId(Long proRecId) {
		this.proRecId = proRecId;
	}

	public JcRec getJcRec() {
		return jcRec;
	}

	public void setJcRec(JcRec jcRec) {
		this.jcRec = jcRec;
	}

	public ProcedureInfo getProcedureInfo() {
		return procedureInfo;
	}

	public void setProcedureInfo(ProcedureInfo procedureInfo) {
		this.procedureInfo = procedureInfo;
	}

	public String getProcedureRank() {
		return procedureRank;
	}

	public void setProcedureRank(String procedureRank) {
		this.procedureRank = procedureRank;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
}
