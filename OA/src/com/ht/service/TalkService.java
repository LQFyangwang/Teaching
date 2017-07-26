package com.ht.service;

import java.io.Serializable;

import com.ht.bean.Talk;
import com.ht.common.bean.Pager4EasyUI;

public interface TalkService extends BaseService<Talk> {

	/**
	 * 根据员工姓名和类对象的简单类名模糊搜索数据，注意：必须是和Emp形成多对一的关系才可以调用此方法
	 * @param pager 分页查询
	 * @param empName 员工姓名
	 * @param beanObject 类的简单类名
	 * @return
	 */
	public Pager4EasyUI<Talk> queryByEmpName(Pager4EasyUI<Talk> pager, Serializable empName, Serializable beanObject);
}
