package com.gs.datastructure;

public class MyLinkedList<E> {
	
	private int size; // 存放了多少个元素
	
	private Element head; 	// 第一个链表
	
	class Element {
		
		private E self; 	// 第一个
		private Element previous; 	// 下一个
		private Element next;		// 上一个
		public E getSelf() {
			return self;
		}
		public void setSelf(E self) {
			this.self = self;
		}
		public Element getPrevious() {
			return previous;
		}
		public void setPrevious(Element previous) {
			this.previous = previous;
		}
		public Element getNext() {
			return next;
		}
		public void setNext(Element next) {
			this.next = next;
		}
		
	}
	
	public void add(E e) {
		if (head == null) {
			head = new Element();
			head.setSelf(e);
			size++;
		} else {
			Element currentElement = head; // 头节点
			for (int i = 0; i < size - 1; i++) {
				currentElement = currentElement.getNext(); // 头节点下一个位置赋值给头节点
			}
			Element ele = new Element();
			ele.setSelf(e);
			currentElement.setNext(ele);
			ele.setPrevious(currentElement);
			size++;
		}
		
	}
	
	public E remove(int index) {
		if (index >= size) {
			throw new ArrayIndexOutOfBoundsException("越界");
		}
		Element currentEle = head;
		if (index == 0) {
			head = head.getNext();
		} else {
			for (int i = 0; i < index; i++) {
				Element previousEle = null;
				if (i == index - 1) {
					previousEle = currentEle;
				}
				currentEle = currentEle.getNext(); // 
				Element nextEle = currentEle.getNext();// 下一个的下一个
				if (previousEle != null) {
					previousEle.setNext(nextEle);
				}
			}
		}
		size--;
		return currentEle.getSelf();
	}
	
	public void removeAll() {
		size = 0;
	}
	
	public E get(int index) throws ArrayIndexOutOfBoundsException {
		if (index >= size) {
			throw new ArrayIndexOutOfBoundsException("越界");
		}
		Element currentEle = head;
		for (int i = 0; i < index; i++) {
			currentEle = currentEle.getNext();
		}
		return currentEle.getSelf();
	}
	
	public int size() {
		return size;
	}

}
