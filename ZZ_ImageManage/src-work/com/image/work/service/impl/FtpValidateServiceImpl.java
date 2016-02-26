package com.image.work.service.impl;

import javax.annotation.Resource;

import com.image.work.dao.FtpValidateDao;
import com.image.work.service.FtpValidateService;

public class FtpValidateServiceImpl implements FtpValidateService{
	
	@Resource(name="ftpValidateDao")
	private FtpValidateDao ftpValidateDao;
	
	public int validate(int type, String identify) {
		int result = VALIDATE_SUCCESS;
		long count = 0;
		switch(type){
			case 1 : {
				count = ftpValidateDao.countGH(identify);
				if(count==0){
					result = ERROR_GONGHAO;
				}
				break;
			}
			case 2 :{
				count = ftpValidateDao.countCH(identify);
				if(count==0){
					result = ERROR_CHEHAO;
				}
				break;
			}
			case 3 :{
				count = ftpValidateDao.countGX(identify);
				if(count==0){
					result = ERROR_GONGXU;
				}
				break;
			}
			default : break;
		}
		return result;
	}
}
