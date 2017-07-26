package com.gs.log;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.SimpleLayout;

public class Log4jTest {
	
	private Logger logger = Logger.getLogger(Log4jTest.class);
	
	public void test() {
		BasicConfigurator.configure(); // 使用log4j的默认配置，默认的appender是终端
		logger.setLevel(Level.ALL); // 设置了默认级别后，只有大于等于此级别的信息才会输出。如果不修改默认的级别为debug.ALL表示所有级别
		SimpleLayout simpleLayout = new SimpleLayout();
		HTMLLayout htmlLayout = new HTMLLayout();
		PatternLayout patternLayout = new PatternLayout("[LOG] %p-%c-%t-%r-%l-%m %d{yyyy/MM/dd HH:mm:ss:SSS}%n");
		try {
			FileAppender fileAppender = new FileAppender(patternLayout, "a.log");
			logger.addAppender(fileAppender);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.trace("looger trace");
		logger.debug("logger debug");
		logger.info("logger info");
		logger.error("logger error");
		logger.fatal("logger fatal");
	}
	
	public void test1() {
		PropertyConfigurator.configure("src/log4j.properties");
		logger.setLevel(Level.ALL); // 设置了默认级别后，只有大于等于此级别的信息才会输出。如果不修改默认的级别为debug.ALL表示所有级别
		logger.trace("looger trace");
		logger.debug("logger debug");
		logger.info("logger info");
		logger.error("logger error");
		logger.fatal("logger fatal");
	}
	
	public void test2() {
		logger.setLevel(Level.ALL); // 设置了默认级别后，只有大于等于此级别的信息才会输出。如果不修改默认的级别为debug.ALL表示所有级别
		logger.trace("looger trace");
		logger.debug("logger debug");
		logger.info("logger info");
		logger.error("logger error");
		logger.fatal("logger fatal");
	}
	
	public static void main(String[] args) {
		Log4jTest log = new Log4jTest();
		log.test();
		log.test1();
		log.test2();
	}

}
