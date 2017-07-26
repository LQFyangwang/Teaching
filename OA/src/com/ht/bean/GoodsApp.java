package com.ht.bean;

import java.util.Date;

/**
 * 物品申购表
 * @author Administrator
 *
 */
public class GoodsApp {

	private String goodsAppId; // 物品申购编号
	private String empId; // 员工编号
	private String goodsTypeId;
	private Date appDay; // 申购时间
	private String goodsName; // 申购的物品名称
	private double price;
	private int quantity; // 申购物品的数量
	private String des; // 申购物品的描述
	private int status; // 状态，默认可用
	private int appStatus; // 申购状态，成功则为1，失败则为0
	public String getGoodsAppId() {
		return goodsAppId;
	}
	public void setGoodsAppId(String goodsAppId) {
		this.goodsAppId = goodsAppId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public Date getAppDay() {
		return appDay;
	}
	public void setAppDay(Date appDay) {
		this.appDay = appDay;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	public int getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(int appStatus) {
		this.appStatus = appStatus;
	}
	public String getGoodsTypeId() {
		return goodsTypeId;
	}
	public void setGoodsTypeId(String goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
