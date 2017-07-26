package com.gs.log;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;

public class LogbackTest {
	
	private static Logger logger = (Logger) LoggerFactory.getLogger(LogbackTest.class);
	
	public static void test() {
		logger.setLevel(Level.ALL);
		logger.trace("logger trace");
		logger.debug("logger debug");
		logger.info("logger info");
		logger.warn("logger warn");
		logger.error("logger error");
	}
	
	public static void test2() { 
		/**
		 * 格式编码器
		 */
		PatternLayoutEncoder encoder = new PatternLayoutEncoder();  
	     encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n");  
	     encoder.setContext(logger.getLoggerContext()); // 日志上下文
	     encoder.start(); // 启动编码器
		
	     /**
	      * 转出到哪个位置
	      */
		 FileAppender<ILoggingEvent> appender = new FileAppender<ILoggingEvent>();
		 appender.setFile("logback.log");
		 
	     appender.setEncoder(encoder); // 指定格式 
	     appender.setContext(logger.getLoggerContext()); // 日志上下文 
	     appender.start(); // 启动appender
	     logger.addAppender(appender);
	     
	     logger.trace("logger trace1");
	     logger.debug("logger debug1");
	     logger.info("logger info1");
	     logger.warn("logger warn1");
	     logger.error("logger error1");
	}
	
	public static void main(String[] args) {
		test();
		test2();
	}

}
