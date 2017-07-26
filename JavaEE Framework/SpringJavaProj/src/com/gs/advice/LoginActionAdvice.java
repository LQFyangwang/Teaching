package com.gs.advice;

public class LoginActionAdvice {
	
	public void beforeExecute() {
		System.out.println("before");
	}
	
	public void afterExecute() {
		System.out.println("登录成功，记录信息到数据库");
	}
	
	public void throwExecute() {
		System.out.println("出现了异常");
	}

}
