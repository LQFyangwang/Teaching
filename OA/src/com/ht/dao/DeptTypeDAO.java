package com.ht.dao;

import java.util.List;

import com.ht.bean.Dept;

public interface DeptTypeDAO extends BaseDAO<Dept>{
	
	public List<Dept> queryNameAll();
}
