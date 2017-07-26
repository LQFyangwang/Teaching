package com.ht.bean;

import java.util.Date;

/**
 * 对应工资发放表
 * 
 * @author xiaoqiang
 *
 */
public class Salary {

	private String salaryId; // 工资发放编号
	private String empId; // 员工编号
	private double extraSalary; // 奖励
	private double subSalary; // 扣罚
	private Date salaryDay; // 发放时间
	private double totalSalary; // 总工资

	public String getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(String salaryId) {
		this.salaryId = salaryId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public double getExtraSalary() {
		return extraSalary;
	}

	public void setExtraSalary(double extraSalary) {
		this.extraSalary = extraSalary;
	}

	public double getSubSalary() {
		return subSalary;
	}

	public void setSubSalary(double subSalary) {
		this.subSalary = subSalary;
	}

	public Date getSalaryDay() {
		return salaryDay;
	}

	public void setSalaryDay(Date salaryDay) {
		this.salaryDay = salaryDay;
	}

	public double getTotalSalary() {
		return totalSalary;
	}

	public void setTotalSalary(double totalSalary) {
		this.totalSalary = totalSalary;
	}

}
