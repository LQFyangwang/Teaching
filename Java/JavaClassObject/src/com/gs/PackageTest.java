package com.gs;

import com.gs.test.PackageTest2; // PackageTest2来源于其他包，所以要进行导入操作，包名后的*表示此包内的所有类

/**
 * package com.gs指明类所处的包
 * com.gs.PackageTest   类的全限定名  ：包名称.类名称 
 * 
 * java.lang包内的所有类默认导入了
 * java.lang下经常 用到的类：Object, String, Math, Byte, Double, Boolean
 *
 *	用*号表示某个包内的所有类，或者 导入了未使用到的类，则编译时间更慢，但不影响运行性能
 *	为什么呢？Java是类加载机制，当实例化某个类的时候，才会把该类加载到内存
 */
public class PackageTest {
	
	public PackageTest() {
		System.out.println("com.gs.PackageTest");
	}
	
	public static void main(String[] args) {
		PackageTest1 p = new PackageTest1();
		PackageTest2 p1 = new PackageTest2(); // 也可以使用全限定名
	}

}
