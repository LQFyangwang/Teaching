package com.ht.bean;

import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

/**
 * 收入类型表
 * @author interface
 *
 */
public class IncomeType {
	private String incomeTypeId;//收入类别编号
	private String name;//名称
	private String des;//描述
	private int status;//状态
	
	private Set<Income> incomes;
	
	public String getIncomeTypeId() {
		return incomeTypeId;
	}
	public void setIncomeTypeId(String incomeTypeId) {
		this.incomeTypeId = incomeTypeId;
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
	@JSON(serialize=false)
	public Set<Income> getIncomes() {
		return incomes;
	}
	public void setIncomes(Set<Income> incomes) {
		this.incomes = incomes;
	}
	
}
