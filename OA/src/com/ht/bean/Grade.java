package com.ht.bean;

import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

/**
 * 对应班级表
 * 
 * @author xiaoqiang
 *
 */
public class Grade {

	private String gradeId; // 班级编号
	private String name; // 班级名称
	private String des; // 描述
	private int quantity; // 班级最大人数
	private int status; // 状态，默认为可用
	
	private Emp emp1; //班主任
	private Emp emp2; // 任课
	private Emp emp3; //辅导
	
	private String empId1;
	private String empId2;
	private String empId3;
	
	private Set<Check> checks;
	private Set<Progress> progress;
	private Set<Stu> stus;

	@JSON(serialize=true)
	public Set<Stu> getStus() {
		return stus;
	}

	public void setStus(Set<Stu> stus) {
		this.stus = stus;
	}

	public String getEmpId1() {
		return empId1;
	}

	public void setEmpId1(String empId1) {
		this.empId1 = empId1;
	}

	public String getEmpId2() {
		return empId2;
	}

	public void setEmpId2(String empId2) {
		this.empId2 = empId2;
	}

	public String getEmpId3() {
		return empId3;
	}

	public void setEmpId3(String empId3) {
		this.empId3 = empId3;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	@JSON(serialize=true)
	public Emp getEmp1() {
		return emp1;
	}
	
	public void setEmp1(Emp emp1) {
		this.emp1 = emp1;
	}
	@JSON(serialize=true)
	public Emp getEmp2() {
		return emp2;
	}

	public void setEmp2(Emp emp2) {
		this.emp2 = emp2;
	}
	@JSON(serialize=true)
	public Emp getEmp3() {
		return emp3;
	}

	public void setEmp3(Emp emp3) {
		this.emp3 = emp3;
	}

	public Set<Check> getChecks() {
		return checks;
	}

	public void setChecks(Set<Check> checks) {
		this.checks = checks;
	}

	@JSON(serialize=false)
	public Set<Progress> getProgress() {
		return progress;
	}

	public void setProgress(Set<Progress> progress) {
		this.progress = progress;
	}

}
