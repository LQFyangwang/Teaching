package com.ht.bean;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

/**
 * 学生反馈表
 * @author Asa
 *
 */
public class StuFeedback {

	private String feedbackId; //反馈编号
	private Date feedbackDay; //反馈时间
	private String des; //反馈详情
	private int status; //状态，默认可用
	
	private Stu stu; // 学生
	
	public String getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}
	public Date getFeedbackDay() {
		return feedbackDay;
	}
	public void setFeedbackDay(Date feedbackDay) {
		this.feedbackDay = feedbackDay;
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
	@JSON(serialize = true)
	public Stu getStu() {
		return stu;
	}
	public void setStu(Stu stu) {
		this.stu = stu;
	}
	
}
