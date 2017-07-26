package com.gs.comparator;

import java.util.Comparator;

public class MyCompatator implements Comparator<Integer> {

	/**
	 * 表示对指定类型的数据进行排序比较
	 * 如果是Integer类型，则直接比较大小，第一个参数 - 第二个参数的值，如果==0，则两个参数相等，如果>0，则第一个参数大于第二个参数，如果<0，则第一个参数小于第二个参数
	 */
	@Override
	public int compare(Integer o1, Integer o2) {
		return o1 - o2; // == 0 相等， >   o1 > o2  < o1 < o2
	}

}
