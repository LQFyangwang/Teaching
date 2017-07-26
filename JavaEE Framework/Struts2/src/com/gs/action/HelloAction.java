package com.gs.action;

public class HelloAction {
	
	/**
	 * 在配置文件中指定一个Action，这个Action会默认执行execute方法
	 * @return
	 */
	public String execute() {
		System.out.println("进入到Action的execute方法");
		return "success";
	}

}
