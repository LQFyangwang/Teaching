package com.ht.dao;

import com.ht.bean.Role;
import com.ht.common.bean.Pager4EasyUI;
/**
 * 员工角色DAO
 * @author Administrator
 *
 */
public interface RoleDAO extends BaseDAO<Role>{
	
	
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
