package com.ht.service.impl;

import com.ht.bean.info.DeptInfo;
import com.ht.common.bean.Pager4EasyUI;
import com.ht.dao.DeptInfoDAO;
import com.ht.service.DeptInfoService;

public class DeptInfoServiceImpl implements DeptInfoService {

	private DeptInfoDAO deptInfoDAO;
	
	public DeptInfoDAO getDeptInfoDAO() {
		return deptInfoDAO;
	}

	public void setDeptInfoDAO(DeptInfoDAO deptInfoDAO) {
		this.deptInfoDAO = deptInfoDAO;
	}

	@Override
	public Pager4EasyUI<DeptInfo> queryByPager(Pager4EasyUI<DeptInfo> pager) {
		pager = deptInfoDAO.queryByPager(pager);
		pager.setTotal(pager.getRows().size());
		return pager;
	}

	@Override
	public Pager4EasyUI<DeptInfo> queryBeforeEmp(Pager4EasyUI<DeptInfo> pager, String beforeId) {
		return deptInfoDAO.queryBeforeEmp(pager, beforeId);
	}
	
}
