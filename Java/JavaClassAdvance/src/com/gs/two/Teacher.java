package com.gs.two;

public class Teacher extends Worker {

	String school;
	String subject; // 课程
	
	/**
	 * 如果本类中的方法可以拆分为多个步骤，则这多个步骤可以用多个private修饰的方法来实现
	 */
	private void teachPlan() {
		
	}
	
	public void teach() {
		teachPlan();
	}
	
	
}
