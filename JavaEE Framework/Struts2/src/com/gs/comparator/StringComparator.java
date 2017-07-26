package com.gs.comparator;

import java.util.Comparator;

public class StringComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		int one = Integer.valueOf(o1.substring(o1.indexOf("_") + 1));
		int two = Integer.valueOf(o2.substring(o2.indexOf("_") + 1));
		return one - two;
	}

}
