package com.ht.bean;

import java.util.Date;

/**
 * 工作日志表
 * 
 * @author Xiao-Qiao
 *
 */
public class Report {

	private String reportId;// 工作日志编号
	private Date reportDay;// 日志时间
	private String des;// 描述
	private int status;// 状态，默认为可用

	private Emp emp;

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public Date getReportDay() {
		return reportDay;
	}

	public void setReportDay(Date reportDay) {
		this.reportDay = reportDay;
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

}
