package com.gs.config;

import java.io.IOException;
import java.util.Properties;

/**
 * 
 * 读取配置文件的工具类
 *
 */
public class ConfigUtil {

	private Properties prop;
	
	public ConfigUtil() {
		
	}
	
	public ConfigUtil(String path) {
		prop = new Properties(); // Properites类是由JDK提供专门用于处理.properties文件的类
		try {
			prop.load(ConfigUtil.class.getResourceAsStream(path)); // 读取配置文件
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getString(String key) {
		return prop.getProperty(key);
	}
	
	public int getInt(String key) {
		return prop.getProperty(key) == null ? 0 : Integer.valueOf(prop.getProperty(key)); // getProperites(String key)通过配置文件中的key来获取其对应的值，返回值 是String类型
	}
	
	public double getDouble(String key) {
		return prop.getProperty(key) == null ? 0 : Double.valueOf(prop.getProperty(key));
	}
	 
}
