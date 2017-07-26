package com.gs.one;

public class User {
	
	private String name;
	private int age;
	private int height;
	private int weight;
	
	public User() {
		
	}
	
	public User(String name, int age, int height, int weight) {
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}
	
	/**
	 * set方法  setter
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * get方法    getter
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	public void setAge(int age) {
		if (age < 0 || age > 200) {
			this.age = 0;
		}
		this.age = age;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void print() {
		System.out.println(User.this.getName()); // User.this ==> this
	}
	
	public static void main(String[] args) {
		User user = new User("AAAA", 20, 170, 100);
		User.Address address = user.new Address();
		System.out.println(user.name);
	}
	
	private class Address {
		private String provice;
		private String city;
		private String postCode;
		public String getProvice() {
			return provice;
		}
		public void setProvice(String provice) {
			this.provice = provice;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getPostCode() {
			return postCode;
		}
		public void setPostCode(String postCode) {
			this.postCode = postCode;
		}
		
	}

}
