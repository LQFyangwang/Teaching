package com.gs.test;

import org.junit.Test;

import com.gs.common.Config;

public class ConfigTest {
	
	@Test
	public void testConfig() {
		// 如果以/开关，则表示从src路径开始，如果不是以/开关，则表示以Config类所在的包为根路径
		Config config = new Config("/config/config.properties"); 
		System.out.println(config.getString("max_file_size"));
	}

}
