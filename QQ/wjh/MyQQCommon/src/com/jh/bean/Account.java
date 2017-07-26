package com.jh.bean;

import java.io.Serializable;

/**
 * QQ账号类，用来存储QQ用户的一些基本信息
 * 
 * 实现Serializable接口提供序列号，可以用来在网络上传送对象
 * 
 * @author Administrator
 *
 */
public class Account implements Serializable {

	private static final long serialVersionUID = -1144616260318367649L;
	
	private String number; // QQ账号
	private String pwd; // QQ密码
	private String nickname; // QQ昵称
	private String gender; // 性别
	private int age; // 年龄
	private String autograph; // 签名
	private String provice; // 省
	private String city; // 市
	private String area; // 区
	private String head; // 头像
	private String mobile; // 电话号码
	private String status; // 在线状态
	private String skin; // 皮肤

	public Account() {
	}

	public Account(String number, String pwd, String nickname) {
		this.number = number;
		this.pwd = pwd;
		this.nickname = nickname;
	}
	
	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAutograph() {
		return autograph;
	}

	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}

	public String getProvice() {
		return provice;
	}

	public void setProvice(String provice) {
		this.provice = provice;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Override
	public String toString() {
		return "Account [number=" + number + ", pwd=" + pwd + ", nickname=" + nickname + ", gender=" + gender + ", age="
				+ age + ", autograph=" + autograph + ", provice=" + provice + ", city=" + city + ", area=" + area
				+ ", head=" + head + ", mobile=" + mobile + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
}
