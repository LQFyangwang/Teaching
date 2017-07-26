package com.ht.bean;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

/**
 * 学生谈心表
 * @author Administrator
 *
 */
public class Talk {

	private String talkId; // 谈心编号
	private Date talkDay; // 谈心时间
	private String des; // 谈心详情
	private int status; // 状态，默认为可用状态，0表示不可用，1表示可用
	
	private Emp emp; // 员工
	private Stu stu; // 学生
	
	public String getTalkId() {
		return talkId;
	}
	public void setTalkId(String talkId) {
		this.talkId = talkId;
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
	@JSON(serialize = false)
	public Emp getEmp() {
		return emp;
	}
	public void setEmp(Emp emp) {
		this.emp = emp;
	}
	@JSON(serialize = false)
	public Stu getStu() {
		return stu;
	}
	public void setStu(Stu stu) {
		this.stu = stu;
	}
	
	
	
	
}
