package com.gs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarTest {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance(); // 获取Calender类的实例
		int year = cal.get(Calendar.YEAR); // Calendar.YEAR表示年的常量
		int month = cal.get(Calendar.MONTH); // MONTH为月
		int day = cal.get(Calendar.DATE); // 日
		int day1 = cal.get(Calendar.DAY_OF_MONTH); // 这个月的第几天
		int weekday = cal.get(Calendar.DAY_OF_WEEK); // 这个礼拜第几天
		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		System.out.println(year + ", " + (month + 1) + ", " + day + ", " + day1 + ", " + weekday + ", " + hour + ", " + minute + ", " + second);
		
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(df.format(cal.getTime()));
		
		// 对时间的修改
		Calendar cal1 = Calendar.getInstance();
		cal1.set(2015, 0, 1, 13, 30, 0);
		System.out.println(df.format(cal1.getTime()));
		cal1.set(Calendar.YEAR, 2018);
		cal1.set(Calendar.HOUR_OF_DAY, 18); // Calendar.HOUR是12小时制 Calendar.HOUR_OF_DAY是24小时制
		cal1.set(Calendar.MINUTE, 0);
		System.out.println(df.format(cal1.getTime()));
		// cal1.add(field, amount); // 表示在某个字段上进行加或减
	}
	
}
