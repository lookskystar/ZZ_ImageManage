package com.image.common.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;


public class WebUtil {
		
	/**
	 * 日期转字符串
	 */
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public static String dateConvertString(Date date) {
		if (null != date) {
			return format.format(date);
		} else {
			return null;
		}
	}
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (null != str && !"".equals(str.trim())) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断字符串数组是否为空
	 * @param strs
	 * @return
	 */
	public static boolean isEmpty(String[] strs) {
		if (null != strs && strs.length > 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 字符串比较
	 * @param args1
	 * @param args2
	 * @return
	 */
	public static boolean compare(String args1, String args2) {
		if (!isEmpty(args1) && !isEmpty(args2)) {
			if (args1.equalsIgnoreCase(args2)) return true;
		}
		return false;
	}
	
	/**
	 * 文件上传
	 * @param file
	 * @param fileNames
	 * @param savePath
	 * @return
	 * @throws IOException
	 */
	public static String fileUpload(File file, String fileName, String savePath) throws IOException {
		String newFileName = UUID.randomUUID() + fileName.substring(fileName.indexOf("."));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String filePath = savePath + "/" +  dateFormat.format(new Date());
		String fileRealPath=ServletActionContext.getServletContext().getRealPath(filePath);
		File sysmfile = new File(fileRealPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		FileUtils.copyFile(file, new File(sysmfile.getPath() + "/" + newFileName));
		return filePath + "/" + newFileName;
	}
}
