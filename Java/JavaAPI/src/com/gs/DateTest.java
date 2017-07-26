package com.gs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
	
	public static void main(String[] args) {
		Date date = new Date(); // 获取当前时间对象
		long time = date.getTime(); // System.currentTimeMillis()
		int year = date.getYear(); // 2016 - 1900 = 116年
		int month = date.getMonth(); // month 从0开始算
		int day = date.getDate();
		int day1 = date.getDay();
		int hour = date.getHours();
		int minute = date.getMinutes();
		int seconds = date.getSeconds();
		System.out.println("year:" + year + ", month: " + (month + 1) +
				",date: " + day + ", day: " + day1 +
				", hour: " + hour + ", minute: " + minute + ", seconds: " + seconds);
		DateFormat df = new SimpleDateFormat(); // 是用默认的格式输出日期和时间 y-M-d 上午/下午H:m
		String dateStr = df.format(date);
		System.out.println(dateStr);
		DateFormat df1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(df1.format(date));
		DateFormat df2 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		System.out.println(df2.format(date));
	}

}
