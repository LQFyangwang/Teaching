package com.ht.bean;

import java.util.Date;

/**
 * 对应员工请假表
 * 
 * @author xiaoqiang
 *
 */
public class EmpLeave {

	private String leaveId; // 请假编号
	private Date startTime; // 开始时间
	private Date endTime; // 结束时间
	private Date leaveDay; // 提交时间
	private String des; // 请假描述
	private int status; // 请假状态
	private int firstLevel; // 直属上司审核，默认为不通过
	private int secondLevel; // 老板审核，默认为不通过
	private int pass; // 是否通过，默认为不通过
	private int leaveCount;
	private Emp emp;

	public String getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getLeaveDay() {
		return leaveDay;
	}

	public void setLeaveDay(Date leaveDay) {
		this.leaveDay = leaveDay;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getFirstLevel() {
		return firstLevel;
	}

	public void setFirstLevel(int firstLevel) {
		this.firstLevel = firstLevel;
	}

	public int getSecondLevel() {
		return secondLevel;
	}

	public void setSecondLevel(int secondLevel) {
		this.secondLevel = secondLevel;
	}

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

	
	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public int getLeaveCount() {
		return leaveCount;
	}

	public void setLeaveCount(int leaveCount) {
		this.leaveCount = leaveCount;
	}

}
