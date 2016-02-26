package com.image.common.pojo;

import java.io.Serializable;

/**
 * 手电筒表
 * 
 * @author lll
 * 
 */
public class DevicesInfo implements Serializable {

	private static final long serialVersionUID = -5239917468450578902L;
	
	/**
	 * 主键
	 */
	private Long devicesId;
	/**
	 * 类型
	 */
	private String devicesType;
	
	/**
	 * 班组id
	 */
	private Long teamId;

	/**
	 * 班组名
	 */
	private String teamName;
	
	/**
	 * 编码
	 */
	private String deviceCode;
	/**
	 *状态0:库存 1：使用 2：维修 3:报废
	 */
	private Integer deviceStatus;
	/**
	 * 领取人ID
	 */
	private Integer receiverId;

	/**
	 *领取人姓名
	 */
	private String receiverName;
	/**
	 *领取时间
	 */
	private String receiverTime;

	/**
	 *归还人ID
	 */
	private Integer returnId;
	/**
	 *归还人名字
	 */
	private String returnName;
	/**
	 *归还时间
	 */
	private String returnTime;
	/**
	 *地区
	 */
	private Long areaId;

	/**
	 *删除标识 0:正常 1:已删除 默认为0
	 */
	private Integer isDelete;

	/**
	 *备注
	 */
	private String devicesNote;

	public Long getDevicesId() {
		return devicesId;
	}

	public void setDevicesId(Long devicesId) {
		this.devicesId = devicesId;
	}

	public String getDevicesType() {
		return devicesType;
	}

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

	public void setDevicesType(String devicesType) {
		this.devicesType = devicesType;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public Integer getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(Integer deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverTime() {
		return receiverTime;
	}

	public void setReceiverTime(String receiverTime) {
		this.receiverTime = receiverTime;
	}

	public Integer getReturnId() {
		return returnId;
	}

	public void setReturnId(Integer returnId) {
		this.returnId = returnId;
	}

	public String getReturnName() {
		return returnName;
	}

	public void setReturnName(String returnName) {
		this.returnName = returnName;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getDevicesNote() {
		return devicesNote;
	}

	public void setDevicesNote(String devicesNote) {
		this.devicesNote = devicesNote;
	}
	
	

}
