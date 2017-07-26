package com.ht.bean;

/**
 * 物品类型表
 * @author Administrator
 *
 */
public class GoodsType {

	private String goodsTypeId; // 物品类型编号
	private String name; // 物品名称
	private String des; // 物品描述
	private int status; // 状态，默认可用
	public String getGoodsTypeId() {
		return goodsTypeId;
	}
	public void setGoodsTypeId(String goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
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
	
	
}
