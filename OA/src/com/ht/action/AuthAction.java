package com.ht.action;

import com.ht.common.bean.ControllerResult;
import com.ht.common.web.WebUtil;
import com.ht.service.AuthorityRoleService;

public class AuthAction extends BaseAction {

	private static final long serialVersionUID = 4851875227500157169L;
	
	private AuthorityRoleService authorityRoleService;
	private String methodName;
	private ControllerResult result;

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public void setAuthorityRoleService(AuthorityRoleService authorityRoleService) {
		this.authorityRoleService = authorityRoleService;
	}

	public ControllerResult getResult() {
		return result;
	}

	public String authority() {
		String roleId = WebUtil.getRoleId(req);
		if (authorityRoleService.queryByRole(methodName, roleId)) {
			result = ControllerResult.setSuccessResult("有权限");
		} else {
			result = ControllerResult.setFailResult("抱歉，您没有权限操作");
		}
		return "authority";
	}

}
