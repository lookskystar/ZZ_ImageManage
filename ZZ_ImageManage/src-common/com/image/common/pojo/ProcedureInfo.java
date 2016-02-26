package com.image.common.pojo;

import java.io.Serializable;

/**
 * 标准工序表
 * 
 * @author lll
 * 
 */
public class ProcedureInfo implements Serializable {

	private static final long serialVersionUID = 504882080044312768L;
	
	/**
	 * 主键
	 */
	private Long proId;
	/**
	 * 名字
	 */
	private String proName;

	/**
	 *类型 0:图片 1：视频
	 */
	private Integer proType;
	/**
	 *图片数量
	 */
	private Integer imageNum;
	/**
	 * 视频时长
	 */
	private Double videoTime;
	/**
	 * 机车类型
	 */
	private String jcType;

	/**
	 *备注
	 */
	private String proNote;

	/**
	 * 顺序号：越大越靠前 默认为0
	 */
	private Integer proSn;
	/**
	 * 图片时差 分钟(第一张与最后一张)
	 */
	private Integer imageTimeDifference;
	/**
	 * 工序次数时间差
	 */
	private Integer proTimeDifference;
	/**
	 * 工序编号
	 * */
	private String proNum;
	/**
	 * 显示类型
	 * 1表示列表显示，2前后对比显示，默认为1
	 */
	private Integer showType;	
	/**
	 * 工序大类(随车 地勤 质检 值班技术 )
	 * @return
	 */
	private DictWorktype dictWorktype;
	/**
	 * 状态
	 * */
	private int status;	
	
	public Long getProId() {
		return proId;
	}
	public void setProId(Long proId) {
		this.proId = proId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public Integer getProType() {
		return proType;
	}
	public void setProType(Integer proType) {
		this.proType = proType;
	}
	public Integer getImageNum() {
		return imageNum;
	}
	public void setImageNum(Integer imageNum) {
		this.imageNum = imageNum;
	}
	public Double getVideoTime() {
		return videoTime;
	}
	public void setVideoTime(Double videoTime) {
		this.videoTime = videoTime;
	}
	public String getJcType() {
		return jcType;
	}
	public void setJcType(String jcType) {
		this.jcType = jcType;
	}
	public String getProNote() {
		return proNote;
	}
	public void setProNote(String proNote) {
		this.proNote = proNote;
	}
	public Integer getProSn() {
		return proSn;
	}
	public void setProSn(Integer proSn) {
		this.proSn = proSn;
	}
	public Integer getImageTimeDifference() {
		return imageTimeDifference;
	}
	public void setImageTimeDifference(Integer imageTimeDifference) {
		this.imageTimeDifference = imageTimeDifference;
	}
	public Integer getProTimeDifference() {
		return proTimeDifference;
	}
	public void setProTimeDifference(Integer proTimeDifference) {
		this.proTimeDifference = proTimeDifference;
	}
	public String getProNum() {
		return proNum;
	}
	public void setProNum(String proNum) {
		this.proNum = proNum;
	}
	public Integer getShowType() {
		return showType;
	}
	public void setShowType(Integer showType) {
		this.showType = showType;
	}
	public DictWorktype getDictWorktype() {
		return dictWorktype;
	}
	public void setDictWorktype(DictWorktype dictWorktype) {
		this.dictWorktype = dictWorktype;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}	
}
