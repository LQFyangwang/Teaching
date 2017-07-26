package com.ht.bean;

import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

/**
 * 员工角色表
 * @author 邹敏祥
 *
 */
public class Role {
	
	private String roleId; // 角色编号
	private String name; // 名称
	private String des; // 描述
	private int status; // 状态，默认为可用
	
	private Set<Emp>emps;//关联到员工表
	private Set<AuthorityRole> authorityRoles; // 关联权限关联表
	
	@JSON(serialize=false)
	public Set<Emp> getEmps() {
		return emps;
	}
	public void setEmps(Set<Emp> emps) {
		this.emps = emps;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@JSON(serialize=false)
	public Set<AuthorityRole> getAuthorityRoles() {
		return authorityRoles;
	}
	public void setAuthorityRoles(Set<AuthorityRole> authorityRoles) {
		this.authorityRoles = authorityRoles;
	}
	
	
}
