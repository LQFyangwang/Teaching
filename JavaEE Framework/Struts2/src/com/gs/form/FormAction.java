package com.gs.form;

import com.gs.bean.Student;
import com.opensymphony.xwork2.ActionSupport;

public class FormAction extends ActionSupport {

	private static final long serialVersionUID = -5883827748027501744L;

	private Student stu;
	
	public Student getStu() {
		return stu;
	}

	public void setStu(Student stu) {
		this.stu = stu;
	}

	public String editUser() {
		stu = new Student();
		stu.setId("1001");
		stu.setEmail("test@126.com");
		stu.setGender("male");
		stu.setDes("ÄãºÃ");
		return "edit";
	}
	
	public String showUser() {
		return "show";
	}
	
}
