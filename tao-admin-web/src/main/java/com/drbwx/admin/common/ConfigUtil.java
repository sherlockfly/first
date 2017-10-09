package com.drbwx.admin.common;

import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConfigUtil {
	private static final Log log = LogFactory.getLog(ConfigUtil.class);
	private static final String configFilePath = "config/config";
	
	public ConfigUtil() {

	}

	private static String getProperty(String propertiesFileName,
			String propertyName) {
		String propertyValue = null;
		ResourceBundle resources = null;

		try {
			resources = ResourceBundle.getBundle(propertiesFileName);
			propertyValue = resources.getString(propertyName);
		} catch (Exception e) {
			log.fatal("getProperties(propertiesFileName, propertyName)" + e);
		} finally {
			resources = null;
		}

		return propertyValue;
	}

	public static String getPath(String pathName) {
		String pth = getProperty(configFilePath, pathName);
		if (pth == null) {
			log.fatal(pathName);
		}

		return pth;
	}

	public static String getPath(String pathName, String sdefault) {
		String pth = getProperty(configFilePath, pathName);
		if (pth == null) {
			pth = sdefault;
		}

		return pth;
	}

	public static String getValue(String pathName) {
		String pth = getProperty(configFilePath, pathName);
		if (pth == null) {
			log.fatal(pathName);
		}

		return getProperty(pth, pathName);
	}
}
