package com.gs.config;

public class ConfigUtilTest {

	public static void main(String[] args) {
		ConfigUtil cu = new ConfigUtil("/config/config.properties");
		System.out.println(cu.getInt("width"));
		System.out.println(cu.getInt("height"));
		System.out.println(cu.getInt("size"));
		System.out.println(cu.getString("title"));
	}
	
}
