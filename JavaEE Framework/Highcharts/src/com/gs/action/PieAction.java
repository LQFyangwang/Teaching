package com.gs.action;

import java.util.ArrayList;
import java.util.List;

import com.gs.bean.Pie;
import com.opensymphony.xwork2.ActionSupport;

public class PieAction extends ActionSupport {

	private static final long serialVersionUID = 6348991141993408112L;
	
	private List<Pie> pies;
	
	public List<Pie> getPies() {
		return pies;
	}
	
	public String execute() {
		pies = new ArrayList<Pie>();
		Pie p1 = new Pie();
		p1.setName("男");
		p1.setY(45);
		Pie p2 = new Pie();
		p2.setName("女");
		p2.setY(45);
		Pie p3 = new Pie();
		p3.setName("其他");
		p3.setY(10);
		p3.setSelected(true);
		p3.setSliced(true);
		pies.add(p1);
		pies.add(p2);
		pies.add(p3);
		return SUCCESS;
	}

}
