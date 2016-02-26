package com.image.common.pojo;

/**
 * 统计实体类
 * @author Administrator
 *
 */
public class CountJcBean implements Cloneable {
	private String area_name;	//	地区名称
	private int totalNum ;		//总数
	private int badNum ;		//异常数
	private int area_id;		//地区id
	public int getArea_id() {
		return area_id;
	}
	public void setArea_id(int areaId) {
		area_id = areaId;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String areaName) {
		area_name = areaName;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getBadNum() {
		return badNum;
	}
	public void setBadNum(int badNum) {
		this.badNum = badNum;
	}
	@Override
	public CountJcBean clone() throws CloneNotSupportedException {
		return (CountJcBean)super.clone();
	}
}
