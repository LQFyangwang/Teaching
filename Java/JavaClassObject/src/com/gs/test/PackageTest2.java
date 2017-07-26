package com.gs.test;

import com.gs.PackageTest;
// import com.gs.test1.PackageTest; // 有冲突

/**
 * 如果引用了不同包中同名的类，则分以下情况：
 * 1）如果一个类来源于另外一个包，一个类来源于本类所处的包，那么实例化来源于外部包的类
 * 2)如果两个类都来源于另外两个不同的包，则编译阶段报错，说两个同名（简单类名）的类有冲突，
 * 		所以不能这样导入，如果确实要用到同名的一个类，应该在实例化时使用全限定名
 */
public class PackageTest2 {

	public static void main(String[] args) {
		PackageTest pt = new PackageTest(); // com.gs.PackageTest
		PackageTest pt1 = new PackageTest(); // com.gs.PackageTest
		com.gs.test1.PackageTest pt2 = new com.gs.test1.PackageTest();
	}
}
