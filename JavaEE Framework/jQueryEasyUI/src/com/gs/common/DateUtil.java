package com.gs.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String DEAULT_DATE_PATTERN = "yyyy-MM-dd";
	
	public static String getDateStr(Date date) {
		return new SimpleDateFormat(DEAULT_DATE_PATTERN).format(date);
	}
	
	public static String getDatetimeStr(Date date) {
		return new SimpleDateFormat(DEFAULT_DATE_TIME_PATTERN).format(date);
	}

}
