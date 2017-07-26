package com.xk.qq.ui.common;

import java.util.List;
import java.util.Vector;

import com.xk.bean.Account;

public class ListTableVector {
	
	public Vector<Vector<String>> toVector(List<Account> accounts){
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		for(Account a: accounts){
			Vector<String> rowsData = new Vector<String>();
			rowsData.add(a.getNumber());
			rowsData.add(a.getNickname());
			rowsData.add(a.getGender());
			rowsData.add(String.valueOf(a.getAge()));
			rowsData.add(a.getAdres());
			rowsData.add(a.getState());
			data.add(rowsData);
		}
		return data;
	}
}
