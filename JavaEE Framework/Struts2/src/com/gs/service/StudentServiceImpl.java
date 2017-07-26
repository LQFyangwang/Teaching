package com.gs.service;

import java.util.List;

import com.gs.bean.Student;
import com.gs.dao.StudentDAO;
import com.gs.dao.StudentDAOImpl;

public class StudentServiceImpl implements StudentService {
	
	private StudentDAO stuDAO;
	
	public StudentServiceImpl() {
		stuDAO = new StudentDAOImpl();
	}

	@Override
	public Student save(Student stu) {
		return stuDAO.save(stu);
	}

	@Override
	public void update(Student stu) {
		stuDAO.update(stu);
	}

	@Override
	public Student show(String id) {
		return stuDAO.show(id);
	}

	@Override
	public List<Student> all() {
		return stuDAO.all();
	}

}
