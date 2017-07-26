package com.jh.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	 * 获取到当前时间
	 * @return
	 */
	public static Date getDate() {
		return Calendar.getInstance().getTime();
	}
	
	/**
	 * 获取当前时间所对应的字符串
	 * @param date
	 * @return
	 */
	public static String getDateStr(Date date) {
		return new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss").format(date);
	}
}
