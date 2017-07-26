package com.gs.service;

import java.util.List;

import com.gs.bean.Student;

public interface StudentService {
	public Student save(Student stu);
	public void update(Student stu);
	public Student show(String id);
	public List<Student> all();
}
