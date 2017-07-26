package com.ht.dao;

import com.ht.bean.info.EmpInfo;
import com.ht.common.bean.Pager4EasyUI;

public interface EmpInfoDAO {
	/**
	 * 分页查询指定的字段
	 * @param pager
	 * @return
	 */
	public Pager4EasyUI<EmpInfo> queryByPager(Pager4EasyUI<EmpInfo> pager);
	
	/**
	 * 计算个数
	 * @return
	 */
	public long count();
}
