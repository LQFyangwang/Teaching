package com.ht.bean;

import java.util.Date;


/**
 * 物品领用表
 * @author interface
 *
 */
public class GoodsUse {
	
	private String useId;//领用编号
	private String goodsId;//物品编号
	private String empId;//员工编号
	private int quantity;//领用的物品数量
	private Date useDay;//领用时间
	private Date ereturnDay;//预估归还时间
	private Date returnDay;//归还时间
	private int returnStatus;
	private int checkStatus;
	
	public String getUseId() {
		return useId;
	}
	public void setUseId(String useId) {
		this.useId = useId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getUseDay() {
		return useDay;
	}
	public void setUseDay(Date useDay) {
		this.useDay = useDay;
	}
	public Date getEreturnDay() {
		return ereturnDay;
	}
	public void setEreturnDay(Date ereturnDay) {
		this.ereturnDay = ereturnDay;
	}
	public Date getReturnDay() {
		return returnDay;
	}
	public void setReturnDay(Date returnDay) {
		this.returnDay = returnDay;
	}
	public int getReturnStatus() {
		return returnStatus;
	}
	public void setReturnStatus(int returnStatus) {
		this.returnStatus = returnStatus;
	}
	public int getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(int checkStatus) {
		this.checkStatus = checkStatus;
	}
	
	
}
