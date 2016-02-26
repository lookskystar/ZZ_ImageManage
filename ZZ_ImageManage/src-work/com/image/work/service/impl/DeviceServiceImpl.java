package com.image.work.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.image.common.pojo.DevicesInfo;
import com.image.common.pojo.DictAreas;
import com.image.common.pojo.DictTeams;
import com.image.common.pojo.DictUsers;
import com.image.common.util.PageModel;
import com.image.work.dao.DeviceDao;
import com.image.work.service.DeviceService;

public class DeviceServiceImpl implements DeviceService{
	@Resource(name="deviceDao")
	private DeviceDao deviceDao;

	public PageModel<DevicesInfo> findDevicesInfo(Integer deviceStatus,
			Long teamId, String receiverName, String deviceCode,Long areaId) {
		return deviceDao.findDevicesInfo(deviceStatus, teamId, receiverName, deviceCode,areaId);
	}

	public List<DevicesInfo> listDevicesInfo() {
		return deviceDao.listDevicesInfo();
	}

	public List<DictTeams> listDictTeams() {
		return deviceDao.listDictTeams();
	}

	public void saveDevicesInfo(DevicesInfo devicesInfo) {
		deviceDao.saveDevicesInfo(devicesInfo);

		
	}

	public DevicesInfo findDevicesInfoById(Long devicesId) {
		return deviceDao.findDevicesInfoById(devicesId);
	}

	public List<DictAreas> listDictAreas() {
		return deviceDao.listDictAreas();
	}

	public String backDevicesInfo(String devicesId) {
		deviceDao.backDevicesInfo(Long.parseLong(devicesId));
		return "success";
	}

	public String discardDevicesInfo(String devicesId) {
		deviceDao.discardDevicesInfo(Long.parseLong(devicesId));
		return "success";
	}

	public String repairDevicesInfo(String devicesId) {
		deviceDao.repairDevicesInfo(Long.parseLong(devicesId));
		return "success";
	}

	public void updateReceiveDevicesInfo(DevicesInfo devicesInfo) {
		deviceDao.receiveDevicesInfo(devicesInfo);
		
	}

	public void updateReturnDevicesInfo(DevicesInfo devicesInfo) {
		deviceDao.returnDevicesInfo(devicesInfo);
		
	}

	public List<Object[]> findResume(Long devicesId) {
		return deviceDao.findResume(devicesId);
	}

	public List<DictUsers> findUsersByTeamId(Long teamId) {
		return deviceDao.findUsersByTeamId(teamId);
	}

	public DevicesInfo findDevicesInfoByTypeCode(String deviceType, String deviceCode) {
		return deviceDao.findDevicesInfoByTypeCode(deviceType, deviceCode);
	}

	public DevicesInfo findDevicesInfoByRecevier(Integer reveiverId) {
		return deviceDao.findDevicesInfoByRecevier(reveiverId);
	}
	
}
