package com.image.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.enterprisedt.net.ftp.FileTransferClient;
import com.image.admin.service.DelService;

public class QuartzJob {
	
	@Resource(name="delService")
	private DelService delService;
	
	private Logger logger = Logger.getLogger(QuartzJob.class);
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 定时调度删除文件以及记录
	 */
	public void work(){
		System.out.println("开始调度");
		logger.info("开始调度");
		Boolean flag = true;
		int num = Integer.parseInt(PropertyUtil.getResourceByKey("delete_day_num"));
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE,-(num+10));
		String btime = format.format(calendar.getTime());
		
		calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE,-num);
		String etime = format.format(calendar.getTime());
		
		System.out.println("删除记录："+btime+"--"+etime);
		logger.info("删除记录："+btime+"--"+etime);
		long areaId = Long.parseLong(PropertyUtil.getResourceByKey("areaid"));
		try{
			 delService.deleteRec(btime, etime ,areaId);
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
			logger.error("定时删除失败："+e.getMessage());
		}
		if(flag){
			try{
				int begin = Integer.parseInt(btime.replace("-", ""));
				int end = Integer.parseInt(etime.replace("-", ""));
				String[] arr = new String[end-begin+1];
				int j=0;
				for (int i = begin; i<=end; i++) {
					arr[j++] = i+"";
				}
				
				FileTransferClient ftpCilent = FTPUnit.getFileTransferClient(PropertyUtil.getResourceByKey("ftp_url"),
													Integer.parseInt(PropertyUtil.getResourceByKey("ftp_port")),
													PropertyUtil.getResourceByKey("ftp_username"),
													PropertyUtil.getResourceByKey("ftp_password"));
				
				FTPUnit.delete(arr, 1, ftpCilent);
				FTPUnit.closeFileTransferClient(ftpCilent);
			}catch(Exception e){
				e.printStackTrace();
				logger.error("定时删除失败："+e.getMessage());
			}
		}
		System.out.println("调度结束");
		logger.info("调度结束");
	}
}
