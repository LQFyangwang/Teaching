package com.ht.bean.info;

import java.io.Serializable;

/**
 * 今日值班的老师信息（名称，电话，地点）
 * @author Administrator
 *
 */
public class TodayDutyInfo implements Serializable {

	private static final long serialVersionUID = -8678951910013837233L;
	private String name; // 员工姓名
	private String add; // 地点
	private String phone; // 员工电话

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
