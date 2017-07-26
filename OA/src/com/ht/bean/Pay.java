package com.ht.bean;

import java.util.Date;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

/**
 * 支出信息表
 * @author interface
 *
 */
public class Pay {
	private String payId;
//	private String payTypeId;
	private Date payDay;
	private String des;
	private double pay;
//	private String empId;
	private String toName;
	private String toPhone;
	
	private Emp emp;
	private PayType pt;
	
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
//	public String getPayTypeId() {
//		return payTypeId;
//	}
//	public void setPayTypeId(String payTypeId) {
//		this.payTypeId = payTypeId;
//	}
	public Date getPayDay() {
		return payDay;
	}
	public void setPayDay(Date payDay) {
		this.payDay = payDay;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public double getPay() {
		return pay;
	}
	public void setPay(double pay) {
		this.pay = pay;
	}
//	public String getEmpId() {
//		return empId;
//	}
//	public void setEmpId(String empId) {
//		this.empId = empId;
//	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getToPhone() {
		return toPhone;
	}
	public void setToPhone(String toPhone) {
		this.toPhone = toPhone;
	}
	
	public Emp getEmp() {
		return emp;
	}
	public void setEmp(Emp emp) {
		this.emp = emp;
	}
	public PayType getPt() {
		return pt;
	}
	public void setPt(PayType pt) {
		this.pt = pt;
	}
}
