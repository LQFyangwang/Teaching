package com.gs.qq.ui.common;

import java.util.List;
import java.util.Vector;

import com.gs.bean.Account;

public class List2TableVector {
	
	/**
	 * 把所有账户转化成JTable中的每一行
	 * @param accounts
	 * @return
	 */
	public Vector<Vector<String>> toVector(List<Account> accounts) {
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		for (Account a : accounts) {
			Vector<String> rowData = new Vector<String>(); // 一个account对应JTable中的一行
			rowData.add(a.getNumber()); // 把每一个字段添加到vector中
			rowData.add(a.getNickname());
			data.add(rowData); // 把每一行添加到Vector中
		}
		return data;
	}

}
