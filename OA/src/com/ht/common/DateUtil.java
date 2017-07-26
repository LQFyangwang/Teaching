package com.ht.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 用于对时间进行操作的工具类
 * @author Administrator
 *
 */
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
		return new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(date);
	}
	
	/** 
     * 将时间字符串转换为Date类型 
     * @param dateStr 
     * @return Date 
     */  
    public static Date toDate(String dateStr) {  
        Date date = null;  
        SimpleDateFormat formater = new SimpleDateFormat();  
        formater.applyPattern("yyyy-MM-dd");  
        try {  
            date = formater.parse(dateStr);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return date;  
    } 
	
	/**
	 * 自动计算天数的方法
	 * @param early
	 * @param late
	 * @return
	 */
	public static int countDay(Date startDay, Date endDay) { 
        java.util.Calendar calst = java.util.Calendar.getInstance();   
        java.util.Calendar caled = java.util.Calendar.getInstance();   
        calst.setTime(startDay);   
         caled.setTime(endDay);   
         //设置时间为0时   
         calst.set(java.util.Calendar.HOUR_OF_DAY, 0);   
         calst.set(java.util.Calendar.MINUTE, 0);   
         calst.set(java.util.Calendar.SECOND, 0);   
         caled.set(java.util.Calendar.HOUR_OF_DAY, 0);   
         caled.set(java.util.Calendar.MINUTE, 0);   
         caled.set(java.util.Calendar.SECOND, 0);   
        //得到两个日期相差的天数   
         int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst   
                .getTime().getTime() / 1000)) / 3600 / 24;   
         
        return days;   
	}   
	
	/**
     * 根据传递进来的时间，自动计算星期
     * 
     * @param dt 传递进来的时间
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    
    /**
     * 计算年龄的方法
     * @param birthDay
     * @return
     * @throws Exception
     */
    public static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                "出生时间大于当前时间!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;//注意此处，如果不加1的话计算结果是错误的
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                //monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                } else {
                    //do nothing
                }
            } else {
                //monthNow>monthBirth
                age--;
            }
        } else {
            //monthNow<monthBirth
            //donothing
        }

        return age;
    }
    
    /**
     * 根据当前时间获取当前月份
     * @return
     */
    public static int getDateMonth() {
    	Date date = DateUtil.getDate();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
    	return c.get(Calendar.MONTH) + 1;
    }
}
