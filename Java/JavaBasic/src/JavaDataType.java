public class JavaDataType {
		/**
			隐式类型转化：小范围转大范围
			byte: 1byte 8bit
			short: 2byte 16bit
			int: 4byte 32bit
			long: 8byte 64bit
			float: 4byte 32bit
			double: 8byte 64bit
			char: 2byte 16bit
			boolean: 1byte 8bit true false
			
			显式类型转化（强制类型转化）：大范围类型转化为小范围类型，可能丢失数据
			
			参与运算时，结果的类型判定：
			byte,short,char型的值被提升int型
			其中一个操作数为long型，结果为long型
			其中一个操作数为float型，结果为float型
			其中一个操作数为double型，结果为double型

		*/
		public static void main(String[] args) {
				int a = 100;
				int b = 100;
				float c = 1.0f; // 直接一个小数位的数默认为double类型的，如果赋值给float类型，在后面加f
				double d = 2.0;
				char e = 'A';
				boolean f = true;
				boolean g = false;
				long h = 10000;
				byte i = 127; 
				// 标准输出语句System.out.println可以用+连接相关的字符串或其他数据
				System.out.println("a = " + a + " b = " + b + " c = " + c + "\n"
														+ "d = " + d + " e = " + e + " f = " + f + "\n"
														+ "g = " + g + " h = " + h + " i = " + i); 
														
				// int j = 1000000000000000; // 过大的整数用int类型存储不下,改用long类型存储，long类型数据后面最好加个L
				long k = 1000000000000000L;
				
				byte L = 127; // byte => short => int => long => double或float
				int m = L;
				long n = m;
				double O = n;
				float p = n;
				
				double q = k;
				float r = k; // int long转化成float类型会丢失精度
				System.out.println("L = " + L + " m = " + m + " n = " + n + " O = " + O 
														+ " p = " + p + " q = " + q + " r = " + r);
														
				int s = 127;
				byte t = (byte) s;
				System.out.println("s = " + s + " t = " + t);// byte存储的范围是-128~127，如果把128赋给byte类型，则数据有误，变成了-128
				
				byte  bb = 50;
				char  cc = 'a';
				short ss = 1024;
				int   ii = 50000;
				float ff = 5.67f;
				double dd = .1234; // 0.1234 => .1234
				System.out.println("result = " + ((ff * bb) + ( ii / cc )-(dd * ss)));
				
				System.out.println("" + 'a' + 1); // ""是字符串，"" + 'a' => 字符串"a"，再 "a" + 1 => 字符串"a1"
				
				String str = "刷我滴卡";
				System.out.println("Hello " + "你好！"); // + ：字符串连接运算
				System.out.println(str);
				System.out.println("泰国语：" + str);
				// a+=a-a*2 =>    a = a + (a - a * 2)
				int xx = 10;
				xx += xx - xx * 2;
				System.out.println(xx);
				
				// instanceof 用来判断某个对象是否为某个类的实例
				Person person = new Person();
				Car car = new Car();
				if (person instanceof Person) {
					System.out.println("person对象为Person类的实例");
				} else {
					System.out.println("person对象不是Person类的实例");
				}
				if (car instanceof Car) {
					System.out.println("car对象为Car类的实例");
				} else {
					System.out.println("car对象不是Car类的实例");
				}
				
				boolean isBook = true;
				boolean isBook1 = false;
				// 两个都会判断
				if (isBook & isBook1) {
				}
				// 如果前面这个isBook为假，则整个条件为假，后面不再判断
				// 如果前面为真，接着判断后面的isBook1
				if (isBook && isBook1) {
				}
				// 两个都会判断
				if (isBook | isBook1) {
				}
				// 如果前面的isBook为真，则整个条件为真，后面不再判断
				// 如果前面的为假，接着判断后面的isBook1
				if (isBook || isBook1) {
				}
				
				System.out.println(10 > 20 ? "10 > 20" : "10 < 20");
		}
}

class Person {
	
}

class Car {
	
}