package com.ht.service;

import java.util.List;

import com.ht.bean.Dept;

public interface DeptTypeService extends BaseService<Dept>{
	
	public List<Dept> queryNameAll();
}
