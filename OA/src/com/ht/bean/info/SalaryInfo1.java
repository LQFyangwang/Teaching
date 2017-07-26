package com.ht.bean.info;

import java.util.Date;

/**
 * 工资条，工资发放情况
 * 
 * @author Administrator
 *
 */
public class SalaryInfo1 {

	private String empId; // 员工编号
	private String empName; // 员工姓名
	private double basicSalary; // 基本工资
	private double jobSalary; // 岗位工资
	private double extraSalary; // 奖励工资
	private double subSalary; // 扣罚工资
	private Date salaryDay; // 发放工资的时间
	private double totalSalary; // 总工资
	private double shouldSalary; // 应发工资
	private String salaryId; // 工资的id
	private String salaryInfoId; // 工资信息的id

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public double getJobSalary() {
		return jobSalary;
	}

	public void setJobSalary(double jobSalary) {
		this.jobSalary = jobSalary;
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

	public double getShouldSalary() {
		return shouldSalary;
	}

	public void setShouldSalary(double shouldSalary) {
		this.shouldSalary = shouldSalary;
	}

	public String getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(String salaryId) {
		this.salaryId = salaryId;
	}

	public String getSalaryInfoId() {
		return salaryInfoId;
	}

	public void setSalaryInfoId(String salaryInfoId) {
		this.salaryInfoId = salaryInfoId;
	}

}
