package com.ht.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DayUtil {

	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			return -6;
		} else {
			return 2 - dayOfWeek;
		}
	}

	// 获得当前周- 周一的日期
	public static String getCurrentMonday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获得当前周- 周日 的日期
	public static String getPreviousSunday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	// 获得当前月--开始日期
	public static String getMinMonthDate(String date) {
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(dateFormat.parse(date));
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			return dateFormat.format(calendar.getTime());
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 获得当前月--结束日期
	public static String getMaxMonthDate(String date) {
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(dateFormat.parse(date));
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			return dateFormat.format(calendar.getTime());
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
