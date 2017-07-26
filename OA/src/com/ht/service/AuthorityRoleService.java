package com.ht.service;

public interface AuthorityRoleService {

	/**
	 * 根据方法的全限定名和角色的id查询是否有权限
	 * @param methodName 方法的全限定名
	 * @param roleId 角色的id
	 * @return 如果有权限则返回true， 否则返回false
	 */
	public boolean queryByRole(String methodName, String roleId);
}
