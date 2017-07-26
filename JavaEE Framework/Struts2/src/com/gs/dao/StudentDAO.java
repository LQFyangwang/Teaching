package com.gs.dao;

import java.util.List;

import com.gs.bean.Student;

public interface StudentDAO {
	
	public Student save(Student stu);
	public void update(Student stu);
	public Student show(String id);
	public List<Student> all();

}
