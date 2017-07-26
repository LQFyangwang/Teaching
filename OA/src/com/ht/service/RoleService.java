package com.ht.service;

import com.ht.bean.Role;
import com.ht.common.bean.Pager4EasyUI;
/**
 * 员工角色Service
 * @author Administrator
 *
 */
public interface RoleService extends BaseService<Role>{
	
	/**
	 * 根据名称查询角色id
	 * @return
	 */
	public String queryRoleIdByName(String name);
	
	
	/**
	 * 根据角色名称模糊搜索
	 * @param pager
	 * @param noticeTypeName
	 * @return
	 */
	public Pager4EasyUI<Role> queryByroleName(Pager4EasyUI<Role> pager, String roleName);
}
