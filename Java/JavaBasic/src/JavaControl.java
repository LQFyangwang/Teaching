public class JavaControl {
	
	public static void eat() {
		System.out.println("我在吃饭！");
	}
	
	public static int add(int a, int b) {
		return a + b;
	} 
	
	public static void main(String[] args) {
		int a = 100;
		boolean b = false;
		if (a > 100) {
			System.out.println("a > 100");
		} else {
			System.out.println("a <= 100");
		}
		
		if (b == true) {
			System.out.println("b is true");
		} else {
			System.out.println("b is false");
		}
		
		if (b) { // 如果某个变量的值为true或false，则该变量在if判断时，直接使用该变量，而不需要写==true或==false
			System.out.println("b is true");
		} else {
			System.out.println("b is false");
		}
		
		// switch判断的也是常量,可以对int，char类型进行判断
		// JDK1.7及以上，还可以对字符串进行判断
		switch(a) { 
			case 10: 
				//
			break;
			case 100:
				
			break;
			case 200:
				
			break;
		}
		String str = "hello";
		switch(str) {
			case "hello":
				System.out.println("OK!");
			break;
		}
		
		int i;
		for (i = 0; i < 10; i++) {
			//
		}
		
		for (int j = 0; j < 10; j++) {
			System.out.print(j + " ");
		}
		
		for (int k = 0, m =10; k < m; k++, m--) {
			System.out.print(k + " ");
		}
		
		System.out.println();
		eat();
		System.out.println(add(10, 10));
	}

}