package com.image.work.dao.impl;

import java.util.ArrayList;
import java.util.List;


import com.image.common.pojo.DevicesInfo;
import com.image.common.pojo.DictAreas;
import com.image.common.pojo.DictTeams;
import com.image.common.pojo.DictUsers;
import com.image.common.util.AbstractDao;
import com.image.common.util.PageModel;
import com.image.work.dao.DeviceDao;

public class DeviceDaoImpl extends AbstractDao implements DeviceDao{

	@SuppressWarnings("unchecked")
	public List<DevicesInfo> listDevicesInfo() {
		String hql="from DevicesInfo r";
		return getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	public List<DictTeams> listDictTeams() {
		return getHibernateTemplate().find("from DictTeams d");
	}
	

	@SuppressWarnings("unchecked")
	public PageModel<DevicesInfo> findDevicesInfo(Integer deviceStatus,
			Long teamId, String receiverName, String deviceCode,Long areaId) {
		StringBuilder builder=new StringBuilder();
		builder.append("from DevicesInfo r where 1=1");
		List<Object> params=new ArrayList<Object>();
		if(deviceStatus!=null&&!deviceStatus.equals("")){
			builder.append(" and r.deviceStatus = ?");
			params.add(deviceStatus);
		}
		if(teamId!=null&&!teamId.equals("")){
			builder.append(" and r.teamId = ?");
			params.add(teamId);
		}
		
		if(receiverName!=null&&!receiverName.equals("")){
			builder.append(" and r.receiverName like ?");
			params.add("%"+receiverName+"%");
		}
		if(deviceCode!=null&&!deviceCode.equals("")){
			builder.append(" and r.deviceCode like ?");
			params.add("%"+deviceCode+"%");
		}
		if(areaId!=null&&!areaId.equals("")){
			builder.append(" and r.areaId = ?");
			params.add(areaId);
		}
		builder.append("  order by r.deviceCode asc,r.deviceStatus asc ,r.returnTime desc,r.devicesId asc");
		return findPageModel(builder.toString(),params.toArray());
	}

	public void saveDevicesInfo(DevicesInfo devicesInfo) {
		this.getHibernateTemplate().save(devicesInfo);
		
	}

	public DevicesInfo findDevicesInfoById(Long devicesId) {
		return getHibernateTemplate().get(DevicesInfo.class,devicesId);
	}

	@SuppressWarnings("unchecked")
	public List<DictAreas> listDictAreas() {
		return getHibernateTemplate().find("from DictAreas d");
	}

	public void backDevicesInfo(Long devicesId ) {
		String hql = "update DevicesInfo set deviceStatus=0,teamId=null,teamName=null,receiverId=null,receiverName=null," +
				"receiverTime=null,returnId=null,returnName=null,returnTime=null,devicesNote=null where devicesId=?";
		getSession().createQuery(hql).setLong(0, devicesId).executeUpdate();
		
		String sql = "insert into DEVICES_REC(DEVICES_RECID,DEVICES_ID,USE_TYPE) VALUES(seq_devic_rec.nextval,"+devicesId+",3)";
        getSession().createSQLQuery(sql).executeUpdate();  //记录表生成记录
	}

	public void discardDevicesInfo(Long devicesId) {
		String hql = "update DevicesInfo set deviceStatus=3 where devicesId=?";
		getSession().createQuery(hql).setLong(0, devicesId).executeUpdate();
		
		String sql = "insert into DEVICES_REC(DEVICES_RECID,DEVICES_ID,USE_TYPE) VALUES(seq_devic_rec.nextval,"+devicesId+",4)";
        getSession().createSQLQuery(sql).executeUpdate();
		
	}

	public void repairDevicesInfo(Long devicesId) {
		String hql = "update DevicesInfo set deviceStatus=2 where devicesId=?";
		getSession().createQuery(hql).setLong(0, devicesId).executeUpdate();
		
		String sql = "insert into DEVICES_REC(DEVICES_RECID,DEVICES_ID,USE_TYPE) VALUES(seq_devic_rec.nextval,"+devicesId+",2)";
        getSession().createSQLQuery(sql).executeUpdate();
	}

	public void receiveDevicesInfo(DevicesInfo devicesInfo) {
		this.getHibernateTemplate().update(devicesInfo);
		
		Long deviceId=devicesInfo.getDevicesId();
		Integer userId=devicesInfo.getReceiverId();
		String userName=devicesInfo.getReceiverName();
		String useTime=devicesInfo.getReceiverTime();
		String recNote=devicesInfo.getDevicesNote();
		
		String sql = "insert into DEVICES_REC(DEVICES_RECID,DEVICES_ID,USER_ID,USER_NAME,USE_TIME,USE_TYPE,REC_NOTE) VALUES(seq_devic_rec.nextval,"+deviceId+","+userId+",'"+userName+"','"+useTime+"',0,'"+recNote+"')";
        getSession().createSQLQuery(sql).executeUpdate();
		
	}

	public void returnDevicesInfo(DevicesInfo devicesInfo) {
		this.getHibernateTemplate().update(devicesInfo);
		
		Long deviceId=devicesInfo.getDevicesId();
		Integer userId=devicesInfo.getReturnId();
		String userName=devicesInfo.getReturnName();
		String useTime=devicesInfo.getReturnTime();
		String recNote=devicesInfo.getDevicesNote();
		
		String sql = "insert into DEVICES_REC(DEVICES_RECID,DEVICES_ID,USER_ID,USER_NAME,USE_TIME,USE_TYPE,REC_NOTE) VALUES(seq_devic_rec.nextval,"+deviceId+","+userId+",'"+userName+"','"+useTime+"',1,'"+recNote+"')";
        getSession().createSQLQuery(sql).executeUpdate();
	}

	public List<Object[]> findResume(Long devicesId) {
		String sql="select di.devices_type,di.device_code,dr.user_name,dt.team_name,dr.use_type,dr.use_time,dr.rec_note from devices_rec dr " +
				"left join devices_info di on dr.devices_id=di.devices_id " +
				"left join dict_users du on dr.user_id = du.user_id " +
				"left join dict_teams dt on du.team_id = dt.team_id " +
				"where dr.devices_id=? order by dr.use_time";
		return (List<Object[]>)getSession().createSQLQuery(sql).setLong(0, devicesId).list();
	}

	@SuppressWarnings("unchecked")
	public List<DictUsers> findUsersByTeamId(Long teamId) {
		String hql="from DictUsers t where t.teamId=? order by t.py";
		return getHibernateTemplate().find(hql,teamId);
	}

	public DevicesInfo findDevicesInfoByTypeCode(String deviceType, String deviceCode) {
		String hql="from DevicesInfo t where t.devicesType=? and t.deviceCode=?";
		List list=getHibernateTemplate().find(hql,new Object[]{deviceType,deviceCode});
		if(list!=null&&list.size()>0){
			return (DevicesInfo)list.get(0);
		}
		return null;
	}

	public DevicesInfo findDevicesInfoByRecevier(Integer reveiverId) {
		String hql="from DevicesInfo t where t.receiverId=? and t.deviceStatus=1";
		List list=getHibernateTemplate().find(hql,reveiverId);
		if(list!=null&&list.size()>0){
			return (DevicesInfo)list.get(0);
		}
		return null;
	}
	
}
