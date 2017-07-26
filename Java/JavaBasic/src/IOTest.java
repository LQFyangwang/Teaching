import java.util.Scanner; // import导入类

public class IOTest {
	public static void main(String[] args) {
		System.out.println("请输入一个整数：");
		// 使用new关键字实例化一个对象： 请创建一个Scanner类的实例（对象），实例（对象）名称为scanner
		// new Scanner(System.in) 表示从“标准输入”输入相关的数据
		Scanner scanner = new Scanner(System.in); 
		int a = scanner.nextInt(); // nextInt()表示输入整数
		String s = scanner.next(); // next()表示输入字符串
		scanner.close();// 关闭输入流
		System.out.println("a = " + a);
		System.out.println("s: " + s);
	}
}