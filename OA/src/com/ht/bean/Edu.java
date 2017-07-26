package com.ht.bean;

import java.util.Date;

/**
 * 员工教育背景表
 * @author 邹敏祥
 *
 */

public class Edu {
	
	private String eduId; // 教育经历编号
	private	String empId; // 员工编号，来源于员工表
	private String school; // 毕业学校
	private Date startDay; // 开始时间
	private Date endDay; // 结束时间
	private String des; // 描述
	public String getEduId() {
		return eduId;
	}
	public void setEduId(String eduId) {
		this.eduId = eduId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public Date getStartDay() {
		return startDay;
	}
	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}
	public Date getEndDay() {
		return endDay;
	}
	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}

}
