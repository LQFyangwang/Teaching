package com.ht.dao;

import java.util.List;

import com.ht.bean.Grade;

public interface GradeTypeDAO extends BaseDAO<Grade>{
	
	public List<Grade> queryNameAll();

}
