package com.image.work.service;

public interface FtpValidateService {
	
	public static final int VALIDATE_SUCCESS = 0;//成功
	public static final int ERROR_GONGHAO = 1;//工号错误
	public static final int ERROR_CHEHAO = 2;//车号错误
	public static final int ERROR_GONGXU = 3;//工序错误
	
	/**
	 * 根据关键字判断是否存在
	 * @param type 1:工号 2：车号 3：工序
	 * @param identify
	 * @return 0:表示不存在
	 */
	public int validate(int type,String identify);
}
