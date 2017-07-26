package com.ht.service.impl;

import com.ht.bean.info.EmpInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.EmpInfoDAO;
import com.ht.service.EmpInfoService;

public class EmpInfoServiceImpl implements EmpInfoService{
	private EmpInfoDAO empInfoDAO;
	
	public EmpInfoDAO getEmpInfoDAO() {
		return empInfoDAO;
	}

	public void setEmpInfoDAO(EmpInfoDAO empInfoDAO) {
		this.empInfoDAO = empInfoDAO;
	}

	@Override
	public Pager4EasyUI<EmpInfo> queryByPager(Pager4EasyUI<EmpInfo> pager) {
		pager = empInfoDAO.queryByPager(pager);
		pager.setTotal(pager.getRows().size());
		return pager;
	}

	@Override
	public long count() {
		return empInfoDAO.count();
	}

}
