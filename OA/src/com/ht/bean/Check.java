package com.ht.bean;

import java.util.Date;

/**
 * 巡查表
 * 
 * @author Administrator
 *
 */
public class Check {

	private String checkId; // 巡查编号
	private Date checkTime; // 巡查时间
	private String weekDay; // 周几
	private String des; // 描述

	private Emp emp;
	private Grade grade;
	private Room room;

	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}


	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}


}
