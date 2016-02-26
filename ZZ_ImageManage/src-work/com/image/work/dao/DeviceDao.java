package com.image.work.dao;

import java.util.List;

import com.image.common.pojo.DevicesInfo;
import com.image.common.pojo.DictAreas;
import com.image.common.pojo.DictTeams;
import com.image.common.pojo.DictUsers;
import com.image.common.util.PageModel;


/**
 * 设备管理
 * @author L
 */
public interface DeviceDao {

	/**
	 * 查询设备信息
	 */
	public List<DevicesInfo> listDevicesInfo();
	
	/**
	 * 查询班组信息
	 */
	public List<DictTeams> listDictTeams();
	
	/**
	 * 查询地区信息
	 */
	public List<DictAreas> listDictAreas();
	
	/**
	 * 根据条件查询设备信息,分页
	 * @return
	 */
	public PageModel<DevicesInfo> findDevicesInfo(Integer deviceStatus,Long teamId,String receiverName,String deviceCode,Long areaId);
	
	/**
	 * 新增设备
	 */
	 public void saveDevicesInfo(DevicesInfo devicesInfo);
	
	/**
	 * 设备查看
	 */
	public DevicesInfo findDevicesInfoById(Long devicesId);
	
	/**
	 * 设备返修回来
	 */
	public void  backDevicesInfo(Long devicesId);
	
	/**
	 * 设备报废
	 */
	public void  discardDevicesInfo(Long devicesId);
	
	/**
	 * 设备送修
	 */
	public void  repairDevicesInfo(Long devicesId);
	
	/**
	 * 设备领取
	 */
	 public void receiveDevicesInfo(DevicesInfo devicesInfo);
	 
	 /**
	  * 设备归还
	  */
	public void returnDevicesInfo(DevicesInfo devicesInfo);
	
	/**
	 * 查看履历
	 */
	public List<Object[]> findResume(Long devicesId);
	
	/**
	 * 根据班组ID查询班组下的用户
	 * @param teamId
	 * @return
	 */
	public List<DictUsers> findUsersByTeamId(Long teamId);
	
	/**
	 * 根据设备型号和编码查询设备信息
	 * @param deviceType
	 * @param deviceCode
	 * @return
	 */
	public DevicesInfo findDevicesInfoByTypeCode(String deviceType,String deviceCode);
	
	/**
	 * 根据领取人ID判断领取人是否已经领取设备
	 * @param reveiverId
	 * @return
	 */
	public DevicesInfo findDevicesInfoByRecevier(Integer reveiverId);
}
