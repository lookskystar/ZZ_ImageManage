package com.image.common.pojo;

import java.io.Serializable;

/**
 * 删除操作记录表
 */
public class DelRec implements Serializable{


	private static final long serialVersionUID = 4999756323703844616L;
	
	/**
	 * 主键
	 * */
	private int id;
	/**
	 * 开始时间
	 * */
	private String btime;
	/**
	 * 结束时间
	 * */
	private String etime;
	/**
	 * 删除操作时间
	 * */
	private String delTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBtime() {
		return btime;
	}
	public void setBtime(String btime) {
		this.btime = btime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getDelTime() {
		return delTime;
	}
	public void setDelTime(String delTime) {
		this.delTime = delTime;
	}
	
}
