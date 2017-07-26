package com.gs.datastructure;

public class MyStack<E> {
	
	private int size;
	private int currPos = -1; // 记录当前数组在哪个索引
	private Object[] elements;
	
	public MyStack() {
		elements = new Object[10];
	}
	
	public void push(E e) {
		// TODO 扩充容量
		elements[++currPos] = e;
		size++;
	}
	
	public E pop() {
		E e = (E) elements[currPos];
		currPos--;
		size--;
		return e;
	}
	
	public E peek() {
		return (E) elements[currPos];
	}
	
	public int size() {
		return size;
	}

}
