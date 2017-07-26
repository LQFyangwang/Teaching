package com.ht.bean;

import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

/**
 * 权限表
 * 
 * @author Administrator
 *
 */
public class Authority {

	private int authorityId; // 权限的id
	private String name; // 方法的全限定名
	private String des; // 简单描述
	
	private Set<AuthorityRole> authorityRoles; // 关联权限关系 

	public int getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	@JSON(serialize=false)
	public Set<AuthorityRole> getAuthorityRoles() {
		return authorityRoles;
	}

	public void setAuthorityRoles(Set<AuthorityRole> authorityRoles) {
		this.authorityRoles = authorityRoles;
	}

}
