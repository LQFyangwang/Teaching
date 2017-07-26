package com.gs.one;

public class OuterClass {
	
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * 直接在外部类的方法 中实例化内部类的实例 
	 */
	public void instanceInnerClass() {
		InnerClassOne one = new InnerClassOne();
		InnerClassFour four = new InnerClassFour();
		InnerClassFive five = new InnerClassFive();
	}
	
	public void innerClass() {
		
		// InnerClass ic = new InnerClass(); // 不能在方法里定义局部内部类之前实例化局部内部类的对象
		
		final int a = 10; // 使用final修饰，不能在局部内部类中修改， 保证数据安全性
		
		class InnerClass {
			
			public void modify() {
				System.out.println(a);
				int b = a + 10;
				// a = 20; // 局部内部类不能去修改包含此局部内部类方法里的局部变量
			}
			
		}
		
		InnerClass ic = new InnerClass();
		ic.modify();
		
		System.out.println(a);
	}
	
	public class InnerClassOne {
		
		private String name;
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public void sayHello() {
			System.out.println("hello " + name);
			System.out.println("hello " + getName()); // 调用InnerClassOne内部类的getName方法， 如果此内部类中没有getName方法 ，则直接去使用外部类的getName方法
			System.out.println("hello " + this.getName());
			System.out.println("hello " + InnerClassOne.this.getName()); // InnerClass.this表示内部类对象本身
			System.out.println("hello " + OuterClass.this.getName()); // OuterClass.this表示外部类对象本身
		}
		
	}
	
	protected class InnerClassTwo {
		public void sayHello() {
			System.out.println("hello " + name);
		}
	}
	
	class InnerClassThree {
		public void sayHello() {
			System.out.println("hello " + name);
		}
	}
	
	private class InnerClassFour {
		public void sayHello() {
			System.out.println("hello " + name);
		}
	}
	
	/**
	 * 静态内部类
	 *
	 */
	public static class InnerClassFive {
		public static int a;
		public void test() {
			
		}
	}
	
	public static void main(String[] args) {
		 // InnerClassOne one = new InnerClassOne();
		OuterClass outClass = new OuterClass();
		OuterClass.InnerClassOne one = outClass.new InnerClassOne();
		OuterClass.InnerClassTwo two = outClass.new InnerClassTwo();
		OuterClass.InnerClassThree three = outClass.new InnerClassThree();
		OuterClass.InnerClassFour four = outClass.new InnerClassFour();
		// OuterClass.InnerClassFive five = outClass.new InnerClassFive(); // 不通过外部类的对象来实例化一个静态内部类
		OuterClass.InnerClassFive five = new OuterClass.InnerClassFive(); // new 外部类类名.内部类类名();来创建外部类中定义的静态内部类
		OuterClass.InnerClassFive five1 = new InnerClassFive();
	}

}
