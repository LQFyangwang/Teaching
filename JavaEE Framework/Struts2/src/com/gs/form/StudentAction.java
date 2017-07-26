package com.gs.form;

import java.util.List;

import com.gs.bean.Student;
import com.gs.service.StudentService;
import com.gs.service.StudentServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class StudentAction extends ActionSupport {

	private static final long serialVersionUID = -5883827748027501744L;

	private String id;
	private Student stu;
	private List<Student> stus;
	
	private StudentService stuService;
	
	public StudentAction() {
		stuService = new StudentServiceImpl();
	}
	
	public void setId(String id) {
		this.id = id;
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
	
	
	
	public String all() {
		stus = stuService.all();
		return "all";
	}
	
	public String show() {
		stu = stuService.show(id);
		return "show";
	}
	
	public String update() {
		stuService.update(stu);
		return "update";
	}
	
	public String save(){
		stuService.save(stu);
		return "save";
	}
	
	public String edit() {
		stu = stuService.show(id);
		return "edit";
	}
	
	public String showAddPage() {
		return "show_add";
	}
	
}
