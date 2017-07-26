package com.ht.bean;

import java.util.Set;

/**
 * 部门表
 * @author 邹敏祥
 *
 */
public class Dept {
	
	private String depId; // 部门编号
	private String name; // 名称
	private String des; // 描述
	private int status; // 状态，默认可用
	private String empId; 
	
	
	private Set<Emp> emps; // 关联到Emp表
	
	
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public Set<Emp> getEmps() {
		return emps;
	}
	
	public void setEmps(Set<Emp> emps) {
		this.emps = emps;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	

	
}
