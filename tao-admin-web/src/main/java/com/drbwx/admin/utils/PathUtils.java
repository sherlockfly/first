package com.drbwx.admin.utils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drbwx.admin.web.BaseController;

public class PathUtils {
	private static Logger log = LoggerFactory.getLogger(PathUtils.class);
	
	public static String getStaticPath(Class c) {
		URL url = c.getProtectionDomain().getCodeSource().getLocation();
		
		String filePath = getProjectPath(url);
		
		return filePath + "static/";
	}
	
	public static String getProjectPath(URL url){
		try {
			String filePath = URLDecoder.decode(url.getPath(), "utf-8");
			int i = filePath.lastIndexOf("WEB-INF/");
			filePath = filePath.substring(0, i);
			
			return filePath;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "";
	}

	public static void main(String[] args) {
		String path = getStaticPath(BaseController.class);
		System.out.println(path);
		
		log.debug(path);
	}
}
