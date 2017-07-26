package com.gs.two;

public class InstanceTest {
	
	public static void test(InstanceOne instance) {
		instance.one();
	}
	
	public static void main(String[] args) {
		InstanceOne one = new InstanceOne();
		one.one(); // 调用父类的one方法
		InstanceTwo two = new InstanceTwo();
		two.one(); // 调用子类中重写的one方法
		// 如果对象one是类InstanceOne的对象，则返回true，否则返回false
		if (one instanceof InstanceOne) {
			System.out.println("one是InstanceOne类的实例");
		}
		if (two instanceof InstanceTwo) {
			System.out.println("two是InstanceTwo类的实例");
		}
		// InstanceOne是父类，相当于更大范围的数据类型，
		// InstanceTwo是子类，其相当于小范围的数据类型，则父类不能直接转化成子类对象
		// 解决方法：使用强制类型转化，编译阶段不会报错，运行时出错：Exception in thread "main" java.lang.ClassCastException
		// java.lang.ClassCastException 类型转化异常
		//InstanceTwo three = (InstanceTwo) new InstanceOne(); 
		// three.one();
		InstanceOne four = new InstanceTwo(); // 相当于小范围转化为大范围数据
		// 用父类来声明子类的对象，用抽象类来声明子类的对象， 用接口来声明其实现类对象， 调用的方法是子类里的方法或者实现类里的方法
		four.one(); // 调用子类里重写的one方法
		
		// four.two(); // 访问不到这个方法。原因：因为four对象是由父类声明的，four对象只能去使用父类已经声明的方法，而不能使用其自己的声明的方法
		((InstanceTwo) four).two(); // 上一行的解决方法：强制把父类声明的子类对象转化为子类
		
		if (four instanceof InstanceOne) {
			System.out.println("用InstanceOne声明但用InstanceTwo实例化的four对象是属于InstanceOne类型的");
		}
		if (four instanceof InstanceTwo) {
			System.out.println("用InstanceOne声明但用InstanceTwo实例化的four对象是属于InstanceTwo类型的");
		}
		
		/**
		 * 如果以父类作为形式参数，则调用该方法时传递的实参可以为父类或子类对象
		 * 
		 * 通常一个方法的形式参数会用父类，或抽象类，或接口。传递实参时，则用父类本身或子类， 抽象类的实现类，接口的实现类
		 */
		test(one);
		test(four);
	}

}
