package com.image.work.dao.impl;

import com.image.common.util.AbstractDao;
import com.image.work.dao.FtpValidateDao;

public class FtpValidateDaoImpl extends AbstractDao implements FtpValidateDao{

	public long countCH(String cheHao) {
		String sql = "select count(*) from DICT_JCSTYPE where JCIDENTIFY=?";
		return Long.parseLong(getSession().createSQLQuery(sql).setString(0, cheHao).uniqueResult()+"");
	}

	public long countGH(String gongHao) {
		String sql = "select count(*) from DICT_USERS where GONGHAO=?";
		return Long.parseLong(getSession().createSQLQuery(sql).setString(0, gongHao).uniqueResult()+"");
	}

	public long countGX(String gongXu) {
		String sql = "select count(*) from PROCEDURE_INFO where PRO_NUM=?";
		return Long.parseLong(getSession().createSQLQuery(sql).setString(0, gongXu).uniqueResult()+"");
	}

}
