package com.ht.bean;

import java.util.Date;

/**
 * 对应课程进度表
 * 
 * @author xiaoqiang
 *
 */
public class Progress {

	private String progressId; // 课程进度编号
	private String des; // 进度详情
	private Date progressDay; // 添加进度的时间
	private int status; // 状态，默认为可用
	
	private Emp emp; // 关联emp表
	private Grade grade; // 关联grade表

	public String getProgressId() {
		return progressId;
	}

	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Date getProgressDay() {
		return progressDay;
	}

	public void setProgressDay(Date progressDay) {
		this.progressDay = progressDay;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

}
