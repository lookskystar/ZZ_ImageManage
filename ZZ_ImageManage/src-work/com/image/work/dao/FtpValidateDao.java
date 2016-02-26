package com.image.work.dao;

public interface FtpValidateDao {
	
	/**
	 * 判断工号是否存在
	 */
	public long countGH(String gongHao);
	
	/**
	 * 判断车号是否存在
	 */
	public long countCH(String cheHao);
	
	/**
	 * 判断工序是否存在
	 */
	public long countGX(String gongXu);
}
