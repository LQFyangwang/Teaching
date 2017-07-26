package com.ht.test;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.ht.common.DateUtil;
import com.ht.common.DayUtil;
import com.ht.common.EncryptUtil;

import junit.framework.TestCase;

public class UtilTest extends TestCase {

	@Test
	public void testDateUtil() {
		Date date = DateUtil.getDate();
		System.out.println(date);
		System.out.println(DateUtil.getDateStr(date));
	}
	
	@Test
	public void testEncryptUtil() {
		System.out.println(EncryptUtil.encrypt("123456"));
	}
	
	@Test 
	public void testGetAge() {
		try {
			System.out.println(DateUtil.getAge(DateUtil.toDate("1998-11-22")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDateMonth() {
		Date date = DateUtil.getDate();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		System.out.println(c.get(Calendar.MONTH) + 1);
	}
	
	@Test 
	public void testDayUtil() {
		System.out.println(DayUtil.getCurrentMonday());
		System.out.println(DayUtil.getPreviousSunday());
		System.out.println(DayUtil.getMaxMonthDate(DateUtil.getDateStr(DateUtil.getDate())));
		System.out.println(DayUtil.getMinMonthDate(DateUtil.getDateStr(DateUtil.getDate())));
	}
	

	
}
