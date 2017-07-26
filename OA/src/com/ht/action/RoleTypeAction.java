package com.ht.action;

import java.util.ArrayList;
import java.util.List;

import com.ht.bean.Role;
import com.ht.common.bean.ComboBox4EasyUI;
import com.ht.service.RoleTypeService;
import com.opensymphony.xwork2.ActionSupport;

public class RoleTypeAction  extends BaseAction{

	private static final long serialVersionUID = -1102632733314314112L;
	
	private RoleTypeService roleTypeService;
	private List<ComboBox4EasyUI> results;

	public List<ComboBox4EasyUI> getResults() {
		return results;
	}

	public void setRoleTypeService(RoleTypeService roleTypeService) {
		this.roleTypeService = roleTypeService;
	}
	public String RoleTypeAll(){
		System.out.println("进入该函数");
		List<Role> roles = new ArrayList<Role>();
		results = new ArrayList<ComboBox4EasyUI>();
		roles = roleTypeService.queryNameAll();
		for(Role de : roles){
			ComboBox4EasyUI bobox = new ComboBox4EasyUI();
			bobox.setId(de.getRoleId());
			bobox.setText(de.getName());
			String roleId = req.getParameter("RoleID");
			System.out.println(roleId);
			if (de.getRoleId().equals(roleId)) {
				bobox.setSelected(true);
			}
			results.add(bobox);
		}
		return "roleTypeAll";
	}

}
