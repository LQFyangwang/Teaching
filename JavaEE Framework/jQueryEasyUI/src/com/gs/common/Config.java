package com.gs.common;

import java.io.IOException;
import java.util.Properties;

public class Config {
	
	private Properties prop; // 读取*.properties配置文件的java类
	
	public Config(String path) {
		prop = new Properties();
		try {
			prop.load(Config.class.getResourceAsStream(path)); 
			// 根据指定的properties文件的路径来把里面的内容读取到Properties对象中
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getInt(String key) {
		return Integer.valueOf(prop.getProperty(key)); // getProperty方法是通过配置文件中的key来获取所对应的value值
	}
	
	public double getDouble(String key) {
		return Double.valueOf(prop.getProperty(key));
	}
	
	public String getString(String key) {
		return prop.getProperty(key);
	}

}
