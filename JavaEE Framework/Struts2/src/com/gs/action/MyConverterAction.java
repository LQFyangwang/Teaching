package com.gs.action;

import com.opensymphony.xwork2.ActionSupport;

public class MyConverterAction extends ActionSupport {
	
	private static final long serialVersionUID = -4066696392612723154L;
	private int[] array;

	public int[] getArray() {
		return array;
	}

	public void setArray(int[] array) {
		this.array = array;
	}
	
	public String execute() {
		if (array != null && array.length >0) {
			for (int a : array) {
				System.out.println(a);
			}
		}
		return SUCCESS;
	}

}
