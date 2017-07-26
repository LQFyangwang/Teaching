package com.gs.json;

import java.util.ArrayList;
import java.util.List;

import com.gs.bean.Student;
import com.opensymphony.xwork2.ActionSupport;

public class JSONAction extends ActionSupport {
	
	private static final long serialVersionUID = 1811400354584961448L;
	
	private String name;
	private int age;
	private String pwd;
	
	private Student stu;
	private List<Student> stus;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Student getStu() {
		return stu;
	}
	public void setStu(Student stu) {
		this.stu = stu;
	}
	public List<Student> getStus() {
		return stus;
	}
	public void setStus(List<Student> stus) {
		this.stus = stus;
	}
	public String execute() {
		name = "test";
		age = 20;
		pwd = "123456";
		stu = new Student();
		stu.setEmail("test@126.com");
		stu.setDes("学生1");
		stus = new ArrayList<Student>();
		Student stu1 = new Student();
		stu1.setEmail("test1@126.com");
		stu1.setDes("学生2");
		stus.add(stu);
		stus.add(stu1);
		return SUCCESS;
	}
	
}
