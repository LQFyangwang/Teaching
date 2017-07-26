package com.ht.bean;

import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

/**
 * 支出类型表
 * @author interface
 *
 */
public class PayType {

	private String payTypeId;//支出类别编号
	private String name;//名称
	private String des;//描述
	private int status;//状态，默认为可用
	
	private Set<Pay> pays;
	
	public String getPayTypeId() {
		return payTypeId;
	}
	public void setPayTypeId(String payTypeId) {
		this.payTypeId = payTypeId;
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
	public Set<Pay> getPays() {
		return pays;
	}
	public void setPays(Set<Pay> pays) {
		this.pays = pays;
	}
	
}
