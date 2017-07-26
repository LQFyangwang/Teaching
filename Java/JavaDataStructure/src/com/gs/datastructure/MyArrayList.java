package com.gs.datastructure;

import java.util.Arrays;

public class MyArrayList<E> {
	
	private static final int growth = 10;
	
	private int initSize = 10; //初始大小 
	
	private int actualSize = initSize; // 实际大小
	
	private int size; // 存放了多少个元素
	
	private Object[] elements;
	
	public MyArrayList() {
		elements = new Object[initSize];
	}
	
	public void add(E e) {
		if (size == actualSize) {
			// TODO 扩充容量，当重新创建一个更大容量的elements数组时，需要把原先数组copy到新的数组里
			actualSize = actualSize + growth;
			elements = Arrays.copyOf(elements, actualSize);
		}
		elements[size] = e;
		size++;
		
	}
	
	public E get(int index) throws ArrayIndexOutOfBoundsException {
		if (index > size - 1) {
			throw new ArrayIndexOutOfBoundsException("只有" + size + "个元素，最大索引只能为" + (size - 1));
		}
		return  (E) elements[index];
	}
	
	public E remove(int index) {
		if (index > size - 1) {
			throw new ArrayIndexOutOfBoundsException("只有" + size + "个元素，最大索引只能为" + (size - 1));
		}
		E e = (E) elements[index];
		// 删除元素（移动元素的问题）
		if (index < size - 1) {
			// 否则需要移动元素的位置，把删除元素后面的所有元素顺序往前移一个位置
			for (int i = index; i < size - 1; i++) {
				elements[i] = elements[i + 1];
			}
		}
		size--;
		return  e;
	}
	
	public int size() {
		return size;
	}

}
