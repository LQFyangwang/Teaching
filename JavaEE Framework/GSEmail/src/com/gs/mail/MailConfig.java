package com.gs.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MailConfig {
	private static Properties props;
	
	public static Properties readProperties(String path) {
		props = new Properties();
		try {
			props.load(MailConfig.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
	
	public static Properties readProperties(File file) {
		props = new Properties();
		try {
			props.load(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
	
	public static String getString(String key) {
		return props.getProperty(key);
	}
	
	public static int getInt(String key) {
		return Integer.valueOf(props.getProperty(key));
	}

}
