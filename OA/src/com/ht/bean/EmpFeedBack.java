package com.ht.bean;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;
/**
 * 员工反馈表
 * @author Xiao-Qiao
 *
 */
public class EmpFeedBack {

	private String feedBackId;// 反馈编号
	private Date feedBackDay;// 反馈时间
	private String des;// 反馈详情
	private int status;// 状态，默认为可用
	
	private Emp emp; // 员工

	public Date getFeedBackDay() {
		return feedBackDay;
	}
	public void setFeedBackDay(Date feedBackDay) {
		this.feedBackDay = feedBackDay;
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
	public Emp getEmp() {
		return emp;
	}
	public void setEmp(Emp emp) {
		this.emp = emp;
	}
	public String getFeedBackId() {
		return feedBackId;
	}
	public void setFeedBackId(String feedBackId) {
		this.feedBackId = feedBackId;
	}

}
