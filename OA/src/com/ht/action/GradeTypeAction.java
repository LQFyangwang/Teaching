package com.ht.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ht.bean.Grade;
import com.ht.common.bean.ComboBox4EasyUI;
import com.ht.service.GradeTypeService;
import com.opensymphony.xwork2.ActionSupport;

public class GradeTypeAction extends ActionSupport{

	private static final long serialVersionUID = 8880892918240755844L;
	
	private GradeTypeService gradeTypeService;
	private List<ComboBox4EasyUI> results;

	public void setGradeTypeService(GradeTypeService gradeTypeService) {
		this.gradeTypeService = gradeTypeService;
	}

	public List<ComboBox4EasyUI> getResults() {
		return results;
	}
	
	public String GradeTypeAll() throws IOException{
		List<Grade> grades = new ArrayList<Grade>();
		results = new ArrayList<ComboBox4EasyUI>();
		grades = gradeTypeService.queryNameAll();
		for(Grade gr : grades){
			ComboBox4EasyUI bobox = new ComboBox4EasyUI();
			bobox.setId(gr.getGradeId());
			bobox.setText(gr.getName());
			bobox.setSelected(true);
			results.add(bobox);
		}
		return "gradeTypeAll";
	}

}
