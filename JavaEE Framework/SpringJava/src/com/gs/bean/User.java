package com.gs.bean;

import java.util.List;
import java.util.Map;

public class User {

	private int id;
	private String name;
	private String pwd;
	
	private List<String> books;
	private Map<String, String> bookMap;
	
	public User() {
		
	}
	
	public User(int id, String name, String pwd) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public List<String> getBooks() {
		return books;
	}

	public void setBooks(List<String> books) {
		this.books = books;
	}

	public Map<String, String> getBookMap() {
		return bookMap;
	}

	public void setBookMap(Map<String, String> bookMap) {
		this.bookMap = bookMap;
	}
	
}
