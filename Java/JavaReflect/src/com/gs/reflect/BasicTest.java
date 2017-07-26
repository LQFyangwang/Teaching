package com.gs.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class BasicTest {
	
	@Test
	public void testBasic() {
		Basic basic = new Basic();
		basic.test();
	}
	
	@Test
	public void testBasic1() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class clazz = Class.forName("com.gs.reflect.Basic"); // 把此名称所代表的类加载到JVM虚拟机中
		Basic b = (Basic) clazz.newInstance(); // 通过Class来创建类的实例
		b.test();
	}
	
	@Test
	public void testFields() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Basic b = new Basic("001", "001", 10);
		Class clazz = b.getClass();
		Field field = clazz.getField("age"); // getField通过类的属性名称来获取与属性对应的Field类，此方法只能获取public的属性
		System.out.println(field.getName());
		
		Field field1 = clazz.getDeclaredField("id"); // getDeclaredField可以获取任何修饰的属性
		System.out.println(field1.getName());
		
		// 不通过属性名称来获取
		Field[] fields = clazz.getFields(); // 只能获取public修饰的属性
		for (Field f : fields) {
			System.out.println(f.getName());
			System.out.println(f.get(b)); // get(Object obj)表示去获取指定对象上的属性值
		}
		
		Field[] fields1 = clazz.getDeclaredFields(); // 可以获取任意修饰符的属性
		for (Field f : fields1) {
			f.setAccessible(true); // 设置属性是否可以被访问，对于私有属性来说，默认为false
			if (f.getName().equals("id")) {
				f.set(b, "002"); // 如果是id属性则把值 改成002
			}
			System.out.println(f.getName());
			System.out.println(f.get(b));
		}
	}
	
	@Test
	public void testMethod() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Basic b = new Basic("001", "001", 20);
		Class clazz = Basic.class;
		Method method = clazz.getMethod("method", String.class, int.class); // 指定method方法的参数的类型， 没有参数用null
		System.out.println(method.getName());
		System.out.println(method.getModifiers()); // 返回修饰符对应的int常量值 ,public 1, private, 2 protected 4 final 16, static 8, abstract 1024, infterface 512
		System.out.println(method.getReturnType()); // 获取返回值的类型
		System.out.println(method.getParameterCount()); // 获取参数个数
		Class[] clazzs = method.getParameterTypes();	// 获取类型
		for (Class c : clazzs) {
			System.out.println(c.getName());
		}
		
		Method method1 = clazz.getDeclaredMethod("method1", null); // 获取任意修饰符的方法
		
		method.invoke(b, "1001", 20); // 使用method类的invoke方法，可以动态地调用任意类的任意方法。需要指定在哪个对象上调用，参数值也需要指定
		method1.setAccessible(true); // 表示设置访问权限，可以访问私有方法
		method1.invoke(b, null);
		
		Constructor[]  cc = clazz.getConstructors(); //   返回一个 Constructor 对象，它反映此 Class 对象所表示的类的指定公共构造方法。
		for (Constructor c : cc) {
			System.out.println(c.getName());
			System.out.println(c.getParameterCount()); // 获取参数个数
			// c.newInstance(null) // 使用newInstance方法实例化对象
		}
		
	}
	
	@Test
	public void testArray() {
		int[] a = new int[]{10, 11, 12};
		System.out.println(Array.get(a, 2));
		String[] str = (String[]) Array.newInstance(String.class, 10); // 动态地创建数组，第一个参数是存放在数组里的数据类型，第二个参数是数组的长度
		str[0] = "aaaa";
		System.out.println(str[0]);
	}

}
