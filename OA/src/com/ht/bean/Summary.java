package com.ht.bean;

import java.util.Date;

/**
 * 学生总结表
 * @author Administrator
 *
 */
public class Summary {

	private String summaryId; // 总结编号
	private Date summaryDay; // 总结时间
	private int term; // 第几个学期的总结
	private String des; // 总结详情
	private int status; // 状态，默认为可用状态，0表示不可用，1表示可用
	
	private Emp emp; // 员工
	private Stu stu; // 学生
	
	public String getSummaryId() {
		return summaryId;
	}
	public void setSummaryId(String summaryId) {
		this.summaryId = summaryId;
	}
	public Date getSummaryDay() {
		return summaryDay;
	}
	public void setSummaryDay(Date summaryDay) {
		this.summaryDay = summaryDay;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
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
	public Emp getEmp() {
		return emp;
	}
	public void setEmp(Emp emp) {
		this.emp = emp;
	}
	public Stu getStu() {
		return stu;
	}
	public void setStu(Stu stu) {
		this.stu = stu;
	}
	
	
	
	
}
