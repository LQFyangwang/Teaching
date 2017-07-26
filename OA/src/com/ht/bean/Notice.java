package com.ht.bean;

import java.util.Date;

/**
 * 公告表
 * @author Administrator
 *
 */
public class Notice {

	private String noticeId; // 公告编号
	private String name; // 公告标题
	private String des; // 公告详情
	private Date noticeDay; // 发布时间
	private int status; //状态
	private NoticeType noticetype; //公告类型
	
	private Emp emp; // 发布人
	
	public NoticeType getNoticetype() {
		return noticetype;
	}
	
	public void setNoticetype(NoticeType noticetype) {
		this.noticetype = noticetype;
	}
	public String getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Date getNoticeDay() {
		return noticeDay;
	}
	public void setNoticeDay(Date noticeDay) {
		this.noticeDay = noticeDay;
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
}
