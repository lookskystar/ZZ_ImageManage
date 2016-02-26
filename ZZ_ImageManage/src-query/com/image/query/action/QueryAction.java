package com.image.query.action;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.image.common.pojo.DictAreas;
import com.image.common.pojo.DictUsers;
import com.image.common.pojo.DictWorktype;
import com.image.common.pojo.JcRec;
import com.image.common.pojo.ProcedureInfo;
import com.image.common.pojo.ProcedureInfoRec;
import com.image.common.pojo.TakedetailRec;
import com.image.common.util.PageModel;
import com.image.query.service.QueryService;
import com.image.set.service.PresetImageService;


/**
 * @author eleven
 *
 */
public class QueryAction {
	@Resource(name="queryService")
	private QueryService queryService;
	@Resource(name = "presetImageService")
	private PresetImageService presetImageService;
	private String jcType;
	private String jcNum;
	private String taskDate;
	
	
	private  HttpServletRequest request = ServletActionContext.getRequest();
	private  HttpServletResponse response = ServletActionContext.getResponse();
	public static final SimpleDateFormat YMDHMS_SDFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat YMD_SDFORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat YMDNOSPRIT_SDFORMAT = new SimpleDateFormat("yyyyMMdd");
	
	public String queryUploadRec(){
		String areaId = request.getParameter("areaId");
		DictUsers user = (DictUsers) request.getSession().getAttribute("session_user");
		if("".equals(areaId) || null == areaId){
			areaId = user.getAreaId().toString();
		}
		if(taskDate == null || "".equals(taskDate)){
			taskDate = YMD_SDFORMAT.format(new Date());
		}
		List<String> jcTypeList = presetImageService.findJcstypeAll();
		List<DictWorktype> workTypeList = queryService.findAllDictWorktype();
		List<Map<String, String>> uploadRecList = queryService.findAllUploadRecOnJc(jcType, jcNum, taskDate, areaId, null); 
		request.setAttribute("uploadRecList", uploadRecList);
		request.setAttribute("workTypeList", workTypeList);
		request.setAttribute("jcTypeList", jcTypeList);
		return "listofuploadrec";
	}
	
	public String queryUpload(){
		String areaId = request.getParameter("areaId");
		DictUsers user = (DictUsers) request.getSession().getAttribute("session_user");
		if("".equals(areaId) || null == areaId){
			areaId = user.getAreaId().toString();
		}
		List<String> jcTypeList = presetImageService.findJcstypeAll();
		List<DictWorktype> workTypeList = queryService.findAllDictWorktype();
		List<Map<String, String>> uploadRecList = queryService.findAllUploadRecOnJc(jcType, jcNum, taskDate, areaId,null); 
		request.setAttribute("uploadRecList", uploadRecList);
		request.setAttribute("workTypeList", workTypeList);
		request.setAttribute("jcTypeList", jcTypeList);
		return "listofupload";
	}
	
	public String constractTreeOfUploadRecOnPlan(){
		String jcRecId = request.getParameter("jcRecId");
		Map<JcRec, Map<DictWorktype, List<ProcedureInfo>>> dataMap = queryService.constructProTree(Long.parseLong(jcRecId));
		request.setAttribute("dataMap", dataMap);
		request.setAttribute("jcRecId", jcRecId);
		return "constractreeofuploadreconplan";
	}
	
	public String queryUploadRecOnPlan(){
		String jcRecId = request.getParameter("jcRecId");
		String proId = request.getParameter("proId");
		String workId = request.getParameter("workId");
		List<Map<String, String>> uploadRecListOnPlan = queryService.findDetailUploadRecOnJc(jcRecId, proId, workId);
		request.setAttribute("uploadRecListOnPlan", uploadRecListOnPlan);
		request.setAttribute("jcRecId", jcRecId);
		return "listofuploadreonplan";
	}
	
	/**
	 * 查询所有的机车列表
	 * @return
	 */
	public String queryJcList(){
		PageModel<JcRec> pm=queryService.findJcRecAll();
		request.setAttribute("pm", pm);
		return "jc_list";
	}
	
	/**
	 * 进入视频播放列表
	 * @return
	 */
	public String videoInput(){
		String recId=request.getParameter("jcRecId");
		String url=request.getParameter("url");
		String videoPath=null;
		if(recId!=null){
			Long jcRecId=Long.parseLong(recId);
			DictAreas dictArea=queryService.findDictAreaByJcRecId(jcRecId);
			videoPath=dictArea.getVideoPath();
			List<Object[]> detailRecs=queryService.findTekeDetailRec(jcRecId);
			Map<String,Map<String,List<String>>> maps=null;
			List<Map<String,Object>> list=null;
			if(detailRecs!=null&&detailRecs.size()>0){
				list=new ArrayList<Map<String,Object>>();
				Map<String,Object> map=null;
				for(int i=0;i<detailRecs.size();i++){
					map=new HashMap<String,Object>();
					Object[] obj=(Object[])detailRecs.get(i);
					map.put("detailId", obj[0]);
					map.put("workType", obj[1]);
					map.put("proName", obj[2]);
					map.put("proRank", obj[3]);
					map.put("url", obj[4]);
					map.put("fileName", getFileName(obj[4]));
					list.add(map);
				}
			}
			request.setAttribute("detailRecs", list);
		}
		if(url!=null){
			request.setAttribute("url", videoPath+url);
		}
		request.setAttribute("video_path",videoPath);
		return "video";
	}
	
	public String videoInputNew(){
		String recId=request.getParameter("jcRecId");
		String url=request.getParameter("url");
		String videoPath=null;
		if(recId!=null){
			Long jcRecId=Long.parseLong(recId);
			DictAreas dictArea=queryService.findDictAreaByJcRecId(jcRecId);
			videoPath=dictArea.getVideoPath();
			List<Object[]> detailRecs=queryService.findTekeDetailRec(jcRecId);
			Map<String,Map<String,List<Map<String,Object>>>> maps=new LinkedHashMap<String,Map<String,List<Map<String,Object>>>>();
			Map<String,Object> map=null;
			if(detailRecs!=null&&detailRecs.size()>0){
				for(int i=0;i<detailRecs.size();i++){
					Object[] obj=(Object[])detailRecs.get(i);
					String firstDir=obj[1]+"-"+obj[2];
					String secondDir=obj[3]+"";
					if(maps.get(firstDir)==null){
						maps.put(firstDir, new LinkedHashMap<String,List<Map<String,Object>>>());
					}
					if(maps.get(firstDir).get(secondDir)==null){
						maps.get(firstDir).put(secondDir, new ArrayList<Map<String,Object>>());
					}
					map=new HashMap<String,Object>();
					map.put("videoUrl", obj[4]);
					map.put("videoName", getFileName(obj[4]));
					maps.get(firstDir).get(secondDir).add(map);
				}
			}
			request.setAttribute("maps", maps);
		}
		if(url!=null){
			request.setAttribute("url", videoPath+url);
		}
		request.setAttribute("video_path",videoPath);
		return "video";
	}
	
	public String queryDetailUploadRecOnPlan(){
		String jcRecId = request.getParameter("jcRecId");
		String workId = request.getParameter("workId");
		String type = request.getParameter("type");
		Map<DictWorktype, Map<ProcedureInfo, Map<ProcedureInfoRec, List<TakedetailRec>>>> dataMap = 
			queryService.listDetailUploadRecOfWorkTypeOnPlan(jcRecId, workId, type);
		request.setAttribute("dataMap", dataMap);
		request.setAttribute("jcRecId", jcRecId);
		request.setAttribute("workId", workId);
		request.setAttribute("type", type);
		return "listofdetailuploadreconplan";
	}
	
	public String queryTakeDetailRecOnProec(){
		String proecId = request.getParameter("proecId");
		String jcRecId = request.getParameter("jcRecId");
		String workId = request.getParameter("workId");
		Map<DictWorktype ,Map<ProcedureInfoRec, Map<String, List<Object>>>> dataMap = 
			queryService.findTakedetailRecByPeoecId(Long.parseLong(proecId), Long.parseLong(workId));
		request.setAttribute("dataMap", dataMap);
		request.setAttribute("jcRecId", jcRecId);
		return "listakedetailreconproec";
	}

	public String reportInput(){
		String areaId = request.getParameter("areaId");
		DictUsers user = (DictUsers) request.getSession().getAttribute("session_user");
		if("".equals(areaId) || null == areaId){
			areaId = user.getAreaId().toString();
		}
		String jcRecId = request.getParameter("jcRecId");
		JcRec jcRec = queryService.findTakeTime(Long.parseLong(jcRecId));
		DictAreas area = queryService.findDictArea(Long.parseLong(areaId));
		List<DictWorktype> workTypeList = queryService.findAllDictWorktype();
		List<Map<String, String>> uploadRecList = queryService.findAllUploadRecOnJc(null, null, null, areaId, jcRecId);
		request.setAttribute("uploadRecList", uploadRecList);
		request.setAttribute("workTypeList", workTypeList);
		request.setAttribute("jcRec", jcRec);
		request.setAttribute("area", area);
		return "reportInput";
	}
	
	/**
	 * 以文件夹方式打开FTP目录
	 * @throws IOException
	 */
	public void ajaxExplorerFtp() throws IOException{
		String msg = "failure";
		String execString = "explorer.exe ftp://10.172.2.239/";
		String taskDate = request.getParameter("taskDate");
		String dateTime = "";
		String[] taskDateArray = taskDate.split("-");
		for (String string : taskDateArray) {
			dateTime += string;
		}
		try {
			Runtime.getRuntime().exec(execString + dateTime);
			msg = "success";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			response.getWriter().write(msg);
		}
	}
	
	/**
	 * ajax根据条件获得JSON数据
	 * proNum:工序编号
	 * gonghao:用户工号
	 * jcNum:机车编号
	 * 根据相应的条件，得到相应的结果
	 * @return
	 */
	public String ajaxGetJSONData() throws Exception{
		//工序编号
		String proNum=request.getParameter("proNum");
		//用户工号
		String gonghao=request.getParameter("gonghao");
		//机车编号
		String jcNum=request.getParameter("jcNum");
		String result="";
		JSONArray jsonArray=null;
		JSONObject jsonObject=null;
		JSONObject object=new JSONObject();
		if(proNum!=null&&!"".equals(proNum)){
			jsonArray=new JSONArray();
			List<String> proNums=getStrList(proNum);
			List<Object[]> proInfos=queryService.findProInfoByProNum(proNums);
			if(proInfos!=null&&proInfos.size()>0){
				for(Object[] obj:proInfos){
					jsonObject=new JSONObject();
					jsonObject.put(obj[0]+"", obj[1]);
					jsonArray.put(jsonObject);
				}
				object.put("proInfo", jsonArray);
			}
		}
		if(gonghao!=null&&!"".equals(gonghao)){
			jsonArray=new JSONArray();
			List<String> gonghaos=getStrList(gonghao);
			List<Object[]> userInfos=queryService.findUserInfoByGonghao(gonghaos);
			if(userInfos!=null&&userInfos.size()>0){
				for(Object[] obj:userInfos){
					jsonObject=new JSONObject();
					jsonObject.put(obj[0]+"", obj[1]);
					jsonArray.put(jsonObject);
				}
				object.put("userInfo", jsonArray);
			}
		}
		if(jcNum!=null&&!"".equals(jcNum)){
			jsonArray=new JSONArray();
			List<String> jcNums=getStrList(jcNum);
			List<Object[]> jcInfos=queryService.findJcInfoByJcNum(jcNums);
			if(jcInfos!=null&&jcInfos.size()>0){
				for(Object[] obj:jcInfos){
					jsonObject=new JSONObject();
					jsonObject.put(obj[0]+"", obj[1]+"-"+obj[0]);
					jsonArray.put(jsonObject);
				}
				object.put("jcInfo", jsonArray);
			}
		}
		result=object.toString();
		response.setContentType("text/plain");
		response.getWriter().write(result);
		return null;
	}
	
	/**
	 * 判断视频或者图片地址是否存在
	 * 传入参数：
	 * url:/20130917/11061101/1_30900010_095413.JPG,/20130917/11061101/1_30900010_095414.AVI
	 * 得到结果：
	 * [{"/20130917/11061101/1_30900010_095413.JPG":1},{"/20130917/11061101/1_30900010_095414.AVI":0}]
	 * 1:存在 0:不存在
	 * @return
	 */
	public String ajaxExistUrl()throws Exception{
		String url=request.getParameter("url");
		String[] urls=url.split(",");
		JSONArray jsonArray=null;
		JSONObject jsonObject=null;
		String result="";
		Integer count=0;
		if(url!=null&&!"".equals(url)){
			jsonArray=new JSONArray();
			for(String str:urls){
				jsonObject=new JSONObject();
				count=queryService.findUrlNum(str);
				jsonObject.put(str, count);
				jsonArray.put(jsonObject);
			}
			result=jsonArray.toString();
		}
		response.setContentType("text/plain");
		response.getWriter().write(result);
		return null;
	}
	
	/**
	 * 通过ajax添加数据
	 * status 0：新增 1：已存在  2：工号不存在 3：车号不存在 4：工序错误
	 * @return
	 */
	public String ajaxAddJSONData()throws Exception{
		String jsonStr=request.getParameter("jsonStr");
//		String jsonStr="[{'pro_rank':'0007','jc_num':'2063','pro_num':'999999','area_id':1," +
//				"'server_url':'/20140311/10000/0111220007/123456789.JPG','video_time':0.0," +
//				"'take_time':'2014-03-11 12:06:35','local_url':'F:\\test\\120618.JPG','type':0,'take_gh':'0'}]";
		JSONObject jsonObject=null;
		String server_url=null;
		Integer count=null;
		Map<String,String> map=null;
		JSONArray array=new JSONArray();
		JSONArray jsonArray=new JSONArray(jsonStr);
		for(int i=0;i<jsonArray.length();i++){
			jsonObject=jsonArray.optJSONObject(i);
			server_url=jsonObject.optString("server_url");
			String gonghao=jsonObject.optString("take_gh");
			String proNum=jsonObject.optString("pro_num");
			String jcnum=jsonObject.optString("jc_num");
			map=queryService.addMapData(gonghao, proNum, jcnum);
			System.out.println("pro_name="+map.get("pro_name"));
			count=queryService.findUrlNum(server_url);
			try{
			   if(count!=null&&count==0){
				    String take_id="".equals(map.get("take_id"))?"0":map.get("take_id");
				    String pro_id="".equals(map.get("pro_id"))?"0":map.get("pro_id");
					Integer takeId=Integer.parseInt(take_id);
					String takeTime=jsonObject.optString("take_time");
					Integer areaId=jsonObject.optInt("area_id",0);
					Long proId=Long.parseLong(pro_id);
					String proRank=jsonObject.optString("pro_rank");
					String url=jsonObject.optString("server_url");
					Integer type=jsonObject.optInt("type");
					Double videoTime=jsonObject.optDouble("video_time",0.0);
					addJSONData(map.get("jc_type"), jcnum, takeId, takeTime, 
							map.get("take_name"), areaId, proId, proRank, type, url, videoTime, YMDHMS_SDFORMAT.format(new Date()));
					if("".equals(map.get("take_name"))||"错误工序".equals(map.get("pro_name"))||"".equals(map.get("jc_type"))){
						jsonObject.put("status", getStatus(map));
					}else{
						jsonObject.put("status", 0);
					}
				}else{
					jsonObject.put("status", 1);
				}
				jsonObject.put("take_name", map.get("take_name"));
				jsonObject.put("pro_name", map.get("pro_name"));
				jsonObject.put("jc_type", map.get("jc_type"));
			    array.put(jsonObject);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		response.setContentType("text/plain");
		response.getWriter().write(array.toString());
		return null;
	}
	
	/**
	 * 添加JSON数据
	 */
	@SuppressWarnings("unused")
	private void addJSONData(String jcType,String jcNum,Integer takeId,String takeTime,String takeName,Integer areaId,Long proId,
			String proRank,Integer type,String url,Double videoTime,String uploadTime){
		ProcedureInfoRec procedureInfoRec=queryService.addJcRecAndProInfoRec(jcType, jcNum, takeTime.substring(0,10), takeName, areaId, proId, proRank);
		queryService.addTakeDetailRec(type, url, procedureInfoRec, takeId, takeTime, takeName, videoTime, uploadTime);
	}
	
	private List<String> getStrList(String str){
		List<String> list=null;
		if(str!=null&&!"".equals(str)){
			list=new ArrayList<String>();
			String[] arrays=str.split(",");
			for(String s:arrays){
				list.add(s);
			}
		}
		return list;
	}
	
	private int getStatus(Map<String,String> map){
		int status=-1;
		//用户不存在
		if("".equals(map.get("take_name"))){
			status=2;
		}
		//机车号不存在
		if("".equals(map.get("jc_type"))){
			status=3;
		}
		//工序不存在
		if("错误工序".equals(map.get("pro_name"))){
			status=4;
		}
		return status;
	}
	
	public String getJcType() {
		return jcType;
	}

	public void setJcType(String jcType) {
		this.jcType = jcType;
	}

	public String getJcNum() {
		return jcNum;
	}

	public void setJcNum(String jcNum) {
		this.jcNum = jcNum;
	}


	public String getTaskDate() {
		return taskDate;
	}


	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}
	
	private String getFileName(Object url){
		if(url!=null&&!"".equals(url)){
			String[] str=url.toString().split("/");
			return str[3];
		}
		return "";
	}
	
	
}
