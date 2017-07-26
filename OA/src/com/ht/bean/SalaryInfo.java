package com.ht.bean;

/**
 * 对应工资基本信息表
 * 
 * @author xiaoqiang
 *
 */
public class SalaryInfo {

	private String salaryinfoId; // 工资情况编号
//	private String empId; // 员工编号
	private double basicSalary; // 基本工资
	private double jobSalary; // 岗位工资
	
	private Emp emp;

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public String getSalaryinfoId() {
		return salaryinfoId;
	}

	public void setSalaryinfoId(String salaryinfoId) {
		this.salaryinfoId = salaryinfoId;
	}

//	public String getEmpId() {
//		return empId;
//	}
//
//	public void setEmpId(String empId) {
//		this.empId = empId;
//	}

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

}
