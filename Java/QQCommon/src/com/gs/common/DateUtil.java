package com.gs.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	/**
	 * 获取一个时间
	 * @return
	 */
	public static Date getDate() {
		return Calendar.getInstance().getTime();
	}
	
	/**
	 * 获取格式化的时间字符串
	 * @param date
	 * @return
	 */
	public static String getDateStr(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

}
