
package com.image.common.pojo;

import java.io.Serializable;

/**
 * 地区表
 * 
 * @author lll
 * 
 */
public class DictAreas implements Serializable {

	private static final long serialVersionUID = 4860813463782790885L;
	/**
	 * 主键
	 */
	private Long areaId;
	/**
	 * 地区名
	 */
	private String areaName;
	
	/**
	 * ftp的IP地址
	 */
	private String ftpIp;
	/**
	 *ftp的端口号
	 */
	private Integer ftpPort;
	/**
	 *ftp用户名
	 */
	private String ftpUsername;
	/**
	 * ftp密码
	 */
	private String ftpPassword;
	/**
	 * 级别   1:动车段 2:动车所
	 */
	private Integer areaLevel;
	/**
	 * 父级ID
	 */
	private Long parentId;
	
	/**
	 *备注	
	 */
	private String areaNote;
	
	/**
	 * 视频路径
	 */
	private String videoPath;

	
	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getFtpIp() {
		return ftpIp;
	}

	public void setFtpIp(String ftpIp) {
		this.ftpIp = ftpIp;
	}

	public Integer getFtpPort() {
		return ftpPort;
	}

	public void setFtpPort(Integer ftpPort) {
		this.ftpPort = ftpPort;
	}

	public String getFtpUsername() {
		return ftpUsername;
	}

	public void setFtpUsername(String ftpUsername) {
		this.ftpUsername = ftpUsername;
	}

	public String getFtpPassword() {
		return ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	public Integer getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(Integer areaLevel) {
		this.areaLevel = areaLevel;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getAreaNote() {
		return areaNote;
	}

	public void setAreaNote(String areaNote) {
		this.areaNote = areaNote;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
}
