package com.image.common.pojo;

import java.io.Serializable;

/**
 * 手电筒表记录表
 * 
 * @author lll
 * 
 */
public class DevicesRec implements Serializable {


	private static final long serialVersionUID = 2950209911703870186L;
	/**
	 * 主键
	 */
	private Long devicesrecId;
	/**
	 * 手电
	 */
	private Long devicesId;

	/**
	 * 使用人ID
	 */
	private Integer userId;

	/**
	 *使用人姓名
	 */
	private String userName;
	/**
	 * 使用时间
	 */
	private String useTime;
	/**
	 * 类型 0:领取 1：归还 2：送修  3：返修回来  4：报废
	 */
	private Integer useType;
	/**
	 *备注
	 */
	private String recNote;
	
	
	

	public Long getDevicesrecId() {
		return devicesrecId;
	}

	public void setDevicesrecId(Long devicesrecId) {
		this.devicesrecId = devicesrecId;
	}

	public Long getDevicesId() {
		return devicesId;
	}

	public void setDevicesId(Long devicesId) {
		this.devicesId = devicesId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	public Integer getUseType() {
		return useType;
	}

	public void setUseType(Integer useType) {
		this.useType = useType;
	}

	public String getRecNote() {
		return recNote;
	}

	public void setRecNote(String recNote) {
		this.recNote = recNote;
	}
	
	

}
