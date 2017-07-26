package com.ht.bean;

import java.util.Date;

/**
 * 收入表
 * @author interface
 *
 */
public class Income{
	private String incomeId;//收入的编号
//	private String incomeTypeId;//收入类型的编号
	private Date incomeDay;//收入的时间
	private String des;//收入的详情
	private double income;//收入的金额
//	private String empId;//员工编号
//	private String stuId;//学生的编号
	
	private Emp emp;
	private Stu stu;
	private IncomeType it;
	
	public String getIncomeId() {
		return incomeId;
	}
	public void setIncomeId(String incomeId) {
		this.incomeId = incomeId;
	}
//	public String getIncomeTypeId() {
//		return incomeTypeId;
//	}
//	public void setIncomeTypeId(String incomeTypeId) {
//		this.incomeTypeId = incomeTypeId;
//	}
	public Date getIncomeDay() {
		return incomeDay;
	}
	public void setIncomeDay(Date incomeDay) {
		this.incomeDay = incomeDay;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
//	public String getEmpId() {
//		return empId;
//	}
//	public void setEmpId(String empId) {
//		this.empId = empId;
//	}
//	public String getStuId() {
//		return stuId;
//	}
//	public void setStuId(String stuId) {
//		this.stuId = stuId;
//	}
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
	public IncomeType getIt() {
		return it;
	}
	public void setIt(IncomeType it) {
		this.it = it;
	}
}
