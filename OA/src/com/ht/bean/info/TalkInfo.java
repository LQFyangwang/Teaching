package com.ht.bean.info;

import java.util.Date;

/**
 * 学生谈心管理对应的Bean
 * @author Administrator
 *
 */
public class TalkInfo {

	private String talkId; // 谈心编号
	private String empId; // 员工的编号
	private String empName; // 员工的名称
	private String stuId; // 学生的编号
	private String stuName; // 学生的名称
	private Date talkDay; // 谈心时间
	private String des; // 谈心详情
	private int status; // 状态
	
	
	public String getTalkId() {
		return talkId;
	}
	public void setTalkId(String talkId) {
		this.talkId = talkId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public Date getTalkDay() {
		return talkDay;
	}
	public void setTalkDay(Date talkDay) {
		this.talkDay = talkDay;
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
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
}
