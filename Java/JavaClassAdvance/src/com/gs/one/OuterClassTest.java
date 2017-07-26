package com.gs.one;

import com.gs.one.OuterClass.InnerClassFive;
// import com.gs.one.OuterClass.InnerClassFour;
import com.gs.one.OuterClass;

public class OuterClassTest {

	public static void main(String[] args) {
		OuterClass outClass = new OuterClass();
		OuterClass.InnerClassOne one = outClass.new InnerClassOne();
		OuterClass.InnerClassTwo two = outClass.new InnerClassTwo();
		OuterClass.InnerClassThree three = outClass.new InnerClassThree();
		// OuterClass.InnerClassFour four = outClass.new InnerClassFour(); // 无法在其他类中创建一个用private修饰符修饰的类
		OuterClass.InnerClassFive five = new OuterClass.InnerClassFive();
		OuterClass.InnerClassFive five1 = new InnerClassFive();
	}
	
}
