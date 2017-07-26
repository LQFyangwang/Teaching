package com.ht.bean;

/**
 * 权限和角色的关联关系表
 * @author Administrator
 *
 */
public class AuthorityRole {

	private int authorityRoleId; // 关联关系的id
	
	private Authority authority; // 权限
	private Role role; // 角色

	public int getAuthorityRoleId() {
		return authorityRoleId;
	}

	public void setAuthorityRoleId(int authorityRoleId) {
		this.authorityRoleId = authorityRoleId;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


}
