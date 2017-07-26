/**
 * 基本数据类型，判断可用==，不是对象
 * 对应每一个基本数据类型，Java提供了相对应的类，基本数据类型所对应的包装类
 * 
 * 基本数据类型与包装类都可以直接赋值，且值相等
 * 
 * 基本数据类型与包装类型可以直接用 == 进行判断
 * 
 * 包装类提供了很多方法，如获取基本数据类型值的方法，可以用创建对象的方式实例化对象
 * 
 * Integer.valueOf()
 * Integer.parseInt()可以把字符串转化为数字，但是该字符串必须只包含数字，如果有其他字符，
 * 则抛出Exception in thread "main" java.lang.NumberFormatException（数字格式化异常）
 *
 * byte Byte byteValue()
 * int Integer intValue()
 * short Short shortValue()
 * long Long longValue()
 * float Float floatValue()
 * double Double doubleValue()
 * char Character charValue()
 * boolean Boolean booleanValue()
 *
 */
public class DataClass {

	public static void main(String[] args) {
		byte a = 10;
		int b = 10;
		short c = 10;
		long d = 10;
		float e = 10.f;
		double f = 10.0;
		char g = 'A';
		boolean h = true;
		
		Byte aa = 10;
		Byte aaa = new Byte((byte)10);
		Integer bb = 10;
		Integer bbb = new Integer(10);
		Short cc = 10;
		Long dd = 10L;
		Float ee = 10.f;
		Double ff = 10.0;
		Character gg = 'A';
		Character ggg = new Character('A');
		Boolean hh = true;
		Boolean hhh = new Boolean(false);
		
		System.out.println("byte a = " + a);
		System.out.println("Byte aa = " + aa);
		System.out.println("int b = " + b);
		System.out.println("Integer bb = " + bb);
		
		System.out.println("byte a == Byte aa: " + (a == aa));
		System.out.println("long d == Long dd： " + (d == dd));
		System.out.println("char g == Character gg： " + (g == gg));
		
		System.out.println(aa.byteValue());
		System.out.println(ff.doubleValue());
		System.out.println(gg.charValue());
		
		String str = "123";
		int strInt = Integer.valueOf(str);
		int strInt1 = Integer.parseInt(str);
		System.out.println("strInt = " + strInt + " strInt1 = " + strInt1);
	}

}
