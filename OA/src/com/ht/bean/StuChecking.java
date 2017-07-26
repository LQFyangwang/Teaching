package com.ht.bean;

import java.util.Date;

/**
 * 学生考勤表
 * @author Asa
 *
 */
public class StuChecking {

	private String checkingId; //学生考勤编号
	private Date checkingDay; //考勤日期
	private String checking1; //早上 
	private String checking2; //中午
	private String checking3; //晚上
	
	private Stu stu; // 学生
	
	public String getCheckingId() {
		return checkingId;
	}
	public void setCheckingId(String checkingId) {
		this.checkingId = checkingId;
	}
	public Date getCheckingDay() {
		return checkingDay;
	}
	public void setCheckingDay(Date checkingDay) {
		this.checkingDay = checkingDay;
	}
	public String getChecking1() {
		return checking1;
	}
	public void setChecking1(String checking1) {
		this.checking1 = checking1;
	}
	public String getChecking2() {
		return checking2;
	}
	public void setChecking2(String checking2) {
		this.checking2 = checking2;
	}
	public String getChecking3() {
		return checking3;
	}
	public void setChecking3(String checking3) {
		this.checking3 = checking3;
	}
	public Stu getStu() {
		return stu;
	}
	public void setStu(Stu stu) {
		this.stu = stu;
	}
	
}
