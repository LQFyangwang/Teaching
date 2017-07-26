package com.ht.action;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import com.ht.bean.Dept;
import com.ht.common.bean.ComboBox4EasyUI;
import com.ht.service.DeptTypeService;

public class DeptTypeAction extends BaseAction{

	private static final long serialVersionUID = 2075500253235424827L;
	
	private DeptTypeService deptType;
	private List<ComboBox4EasyUI> results;
	
	public List<ComboBox4EasyUI> getResults() {
		return results;
	}

	public void setDeptType(DeptTypeService deptType) {
		this.deptType = deptType;
	}

	public String DeptTypeAll() throws IOException{
		List<Dept> depts = new ArrayList<Dept>();
		results = new ArrayList<ComboBox4EasyUI>();
		depts = deptType.queryNameAll();
		for(Dept de : depts){
			ComboBox4EasyUI bobox = new ComboBox4EasyUI();
			bobox.setId(de.getDepId());
			bobox.setText(de.getName());
			String depId = req.getParameter("deptRowId");
			if(de.getDepId().equals(depId)){
				bobox.setSelected(true);
			}
			results.add(bobox);
		}
		return "typeAll";
	}

	

}
