package com.ht.service.impl;

import java.util.List;

import com.ht.bean.Grade;
import com.ht.dao.GradeTypeDAO;
import com.ht.service.GradeTypeService;

public class GradeTypeServiceImpl implements GradeTypeService{
	
	private GradeTypeDAO gradeTypeDAO;

	public GradeTypeDAO getGradeTypeDAO() {
		return gradeTypeDAO;
	}

	public void setGradeTypeDAO(GradeTypeDAO gradeTypeDAO) {
		this.gradeTypeDAO = gradeTypeDAO;
	}

	@Override
	public List<Grade> queryNameAll() {
		return gradeTypeDAO.queryNameAll();
	}

}
