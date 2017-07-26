package com.ht.service.impl;

import com.ht.dao.AuthorityRoleDAO;
import com.ht.service.AuthorityRoleService;

public class AuthorityRoleServiceImpl implements AuthorityRoleService {
	
	private AuthorityRoleDAO authorityRoleDAO;

	public AuthorityRoleDAO getAuthorityRoleDAO() {
		return authorityRoleDAO;
	}

	public void setAuthorityRoleDAO(AuthorityRoleDAO authorityRoleDAO) {
		this.authorityRoleDAO = authorityRoleDAO;
	}

	@Override
	public boolean queryByRole(String methodName, String roleId) {
		return authorityRoleDAO.queryByRole(methodName, roleId);
	}

}
