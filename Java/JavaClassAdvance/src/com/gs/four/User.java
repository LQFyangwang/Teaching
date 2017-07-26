package com.gs.four;

public class User {
	
	private String email;
	private String nickname;
	private String password;
	private String gender;
	private boolean active; // 用户是否激活
	
	public User() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isActive() { // is+boolean类型的属性名称
		return active;
	}

}
