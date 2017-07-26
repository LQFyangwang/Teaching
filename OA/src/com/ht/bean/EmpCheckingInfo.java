package com.ht.bean;

/**
 * 员工考勤时间信息表
 * @author Xiao-Qiao
 *
 */
public class EmpCheckingInfo {

	private String checkingInfoId;// 考勤编号
	private String name;// 考勤信息
	private String checkingTime; // 考勤时间
	
	public String getCheckingInfoId() {
		return checkingInfoId;
	}
	public void setCheckingInfoId(String checkingInfoId) {
		this.checkingInfoId = checkingInfoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCheckingTime() {
		return checkingTime;
	}
	public void setCheckingTime(String checkingTime) {
		this.checkingTime = checkingTime;
	}
	
}
