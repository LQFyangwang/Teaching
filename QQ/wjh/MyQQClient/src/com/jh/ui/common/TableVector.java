package com.jh.ui.common;

import java.util.List;
import java.util.Vector;

import com.jh.bean.Account;

/**
 * 用来存放table的值
 * @author Administrator
 *
 */
public class TableVector {

	public Vector<Vector<String>> toVector(List<Account> accounts) {
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		for (Account a : accounts) {
			Vector<String> rowData = new Vector<String>();
			rowData.add(a.getNumber());
			rowData.add(a.getNickname());
			rowData.add(a.getGender());
			rowData.add(String.valueOf(a.getAge()));
			rowData.add(a.getMobile());
			data.add(rowData);
		}
		return data;
	}
}
