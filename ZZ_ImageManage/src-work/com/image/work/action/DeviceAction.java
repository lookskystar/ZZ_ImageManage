package com.image.work.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.image.common.pojo.DevicesInfo;
import com.image.common.pojo.DictUsers;
import com.image.common.util.PageModel;
import com.image.work.service.DeviceService;



/**
 * 设备管理Action
 * @author L
 */
public class DeviceAction {

	@Resource(name="deviceService")
	private DeviceService deviceService;
	
	private DevicesInfo devicesInfo;
	private Integer deviceStatus;
	private Long teamId;
	private String receiverName;
	private String deviceCode;
	
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response=ServletActionContext.getResponse();
	public static final SimpleDateFormat YMD_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	
	/**
	 * 进入设备管理
	 */
	public String deviceInput() throws Exception {
		DictUsers user = (DictUsers) request.getSession().getAttribute("session_user");
		List<DevicesInfo> devicesInfolist = deviceService.listDevicesInfo();//查询设备信息
		request.setAttribute("devicesInfolist", devicesInfolist);
		request.setAttribute("dictteamslist", deviceService.listDictTeams());
		PageModel<DevicesInfo>  pm=deviceService.findDevicesInfo(deviceStatus, teamId, receiverName, deviceCode,user.getAreaId());
		request.setAttribute("deviceStatus", deviceStatus);
		request.setAttribute("pm", pm);
		return "device";
	}
	
	/**
	 * 进入新增设备
	 */
	public String addDeviceInput() throws Exception{
		DictUsers user = (DictUsers) request.getSession().getAttribute("session_user");
		request.setAttribute("dictareaslist", deviceService.listDictAreas());
		request.setAttribute("areaId", user.getAreaId());
		return "adddevice";
	} 
	
	/**
	 * 新增设备
	 */
	public String addDevice() throws Exception{
		try{
			devicesInfo.setDeviceStatus(0);
			devicesInfo.setIsDelete(0);
			deviceService.saveDevicesInfo(devicesInfo);
			request.setAttribute("message", "设备信息添加成功!");
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "设备信息添加失败!");
		}
		return deviceInput();
	}
	
	/**
	 * 查看设备详情
	 */
	public String deviceInfoInput() throws Exception{
		DevicesInfo device = deviceService.findDevicesInfoById(Long.valueOf(request.getParameter("devicesId")));
		request.setAttribute("dictteamslist", deviceService.listDictTeams());
		request.setAttribute("dictareaslist", deviceService.listDictAreas());
		request.setAttribute("device", device);
		return "deviceInfo";
	}
	
	/**
	 * 设备返修回来
	 * @return
	 */
	public String backDevicesInfo() throws Exception{
		String result = "failure";
		String devicesId = ServletActionContext.getRequest().getParameter("device");
		deviceService.backDevicesInfo(devicesId);
		result = "success";
		response.setContentType("text/plain");
		response.getWriter().write(result);
		return null;
		
	}
	
	/**
	 * 设备报废
	 * @return
	 */
	public String discardDevicesInfo() throws Exception{
		String result = "failure";
		String devicesId = ServletActionContext.getRequest().getParameter("device");
		deviceService.discardDevicesInfo(devicesId);
		result = "success";
		response.setContentType("text/plain");
		response.getWriter().write(result);
		return null;
	}

	/**
	 * 设备送修
	 * @return
	 */
	public String repairDevicesInfo() throws Exception{
		String result = "failure";
		String devicesId = ServletActionContext.getRequest().getParameter("device");
	    deviceService.repairDevicesInfo(devicesId);
		result = "success";
		response.setContentType("text/plain");
		response.getWriter().write(result);
		return null;
	}
	
	/**
	 * 进入设备领取
	 */
	public String receiveDeviceInput() throws Exception{
		DevicesInfo device = deviceService.findDevicesInfoById(Long.valueOf(request.getParameter("devicesId")));
		String ntime=YMD_FORMAT.format(new Date());
		request.setAttribute("device", device);
		request.setAttribute("dictteamslist", deviceService.listDictTeams());
		request.setAttribute("dictareaslist", deviceService.listDictAreas());
		request.setAttribute("ntime", ntime);
		return "receivedevice";
	} 
	
	/**
	 * 设备领取
	 */
	public String receiveDevice() throws Exception{
		DevicesInfo device = deviceService.findDevicesInfoById(Long.valueOf(request.getParameter("devicesId")));

		device.setTeamId(devicesInfo.getTeamId());
		device.setTeamName(devicesInfo.getTeamName());
		device.setDeviceStatus(1);// 状态设为1(使用中)
		device.setReceiverId(devicesInfo.getReceiverId());
		device.setReceiverName(devicesInfo.getReceiverName());
		device.setReceiverTime(devicesInfo.getReceiverTime());
		device.setDevicesNote(devicesInfo.getDevicesNote());
		device.setReturnId(null);
		device.setReturnName(null);
		device.setReturnTime(null);//设置归还Id，归还人，归还时间为空
		
		deviceService.updateReceiveDevicesInfo(device);
		request.setAttribute("message", "设备领取成功");
		return deviceInput();
	}
	
	/**
	 * 进入设备归还
	 */
	public String returnDeviceInput() throws Exception{
		DevicesInfo device = deviceService.findDevicesInfoById(Long.valueOf(request.getParameter("devicesId")));
		String ntime=YMD_FORMAT.format(new Date());
		request.setAttribute("device", device);
		request.setAttribute("ntime", ntime);
		request.setAttribute("dictteamslist", deviceService.listDictTeams());
		request.setAttribute("dictareaslist", deviceService.listDictAreas());
		return "returndevice";
	} 
	
	/**
	 * 设备归还
	 */
	public String returnDevice() throws Exception{
		DevicesInfo device = deviceService.findDevicesInfoById(Long.valueOf(request.getParameter("devicesId")));
		
		device.setDeviceStatus(0);// 状态设为0(库存)
		device.setReturnId(devicesInfo.getReturnId());
		device.setReturnName(devicesInfo.getReturnName());
		device.setReturnTime(devicesInfo.getReturnTime());
		device.setDevicesNote(devicesInfo.getDevicesNote());
		device.setTeamId(devicesInfo.getTeamId());
		device.setTeamName(devicesInfo.getTeamName());
		device.setReceiverId(null);
		device.setReceiverName(null);
		device.setReceiverTime(null);//设置领取Id,领取人,领取时间为空
		
		deviceService.updateReturnDevicesInfo(device);
		request.setAttribute("message", "设备归还成功");
		return deviceInput();
	}
	
	/**
	 * 查询设备履历
	 * @return
	 */
	public String resumeInput() throws Exception{
		List<Object[]> list= deviceService.findResume(Long.valueOf(request.getParameter("devicesId")));
		List<Map<String,Object>> resume = new ArrayList<Map<String,Object>>();
		for(Object[] obj:list){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("device_type", obj[0]);
			map.put("device_code", obj[1]);
			map.put("user", obj[2]);
			map.put("team", obj[3]);
			map.put("use_type", obj[4]);
			map.put("use_time", obj[5]);
			map.put("rec_note", obj[6]);
			resume.add(map);
		}
		request.setAttribute("resume", resume);
//		request.setAttribute("devicesId", request.getParameter("devicesId"));
		return "resume";
	}
	
	/**
	 * ajax得到班组下的用户信息
	 * @return
	 * @throws JSONException 
	 * @throws IOException 
	 */
	public String ajaxGetTeamUser() throws JSONException, IOException{
		Long teamId=Long.parseLong(request.getParameter("teamId"));
		List<DictUsers> users=deviceService.findUsersByTeamId(teamId);
		JSONObject obj=null;
		JSONArray array=new JSONArray();
		if(users!=null&&users.size()>0){
			for(DictUsers user:users){
				obj=new JSONObject();
				obj.put("userId", user.getUserId());
				obj.put("userName", user.getName());
				array.put(obj);
			}
		}
		JSONObject json=new JSONObject();
		json.put("users", array);
		response.getWriter().print(json.toString());
		return null;
	}
	
	/**
	 * ajax根据设备型号和编码判断设备是否存在
	 * @return
	 * @throws IOException 
	 */
	public String ajaxDeviceExist() throws IOException{
		String deviceType=request.getParameter("deviceType");
		String deviceCode=request.getParameter("deviceCode");
		String result="failure";
		DevicesInfo deviceInfo=deviceService.findDevicesInfoByTypeCode(deviceType, deviceCode);
		if(deviceInfo!=null){
			result="success";
		}
		response.getWriter().print(result);
		return null;
	}
	
	/**
	 * ajax判断领取人是否领取了设备
	 * @return
	 * @throws IOException 
	 */
	public String ajaxReceiverExist() throws IOException{
		Integer receiverId=Integer.parseInt(request.getParameter("reveiverId"));
		DevicesInfo deviceInfo=deviceService.findDevicesInfoByRecevier(receiverId);
		String result="failure";
		if(deviceInfo!=null){
			result="success";
		}
		response.getWriter().print(result);
		return null;
	}
	
	public DevicesInfo getDevicesInfo() {
		return devicesInfo;
	}

	public void setDevicesInfo(DevicesInfo devicesInfo) {
		this.devicesInfo = devicesInfo;
	}

	public Integer getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(Integer deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	
	
}
