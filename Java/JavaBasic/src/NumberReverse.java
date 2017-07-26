import java.util.Scanner;

public class NumberReverse {

	public static void main(String[] args) {
		System.out.println("输入一个正整数：");
		Scanner scanner = new Scanner(System.in);
		String str = String.valueOf(scanner.nextInt()); //String.valueOf(int args) 把int类型的args转化成String字符串
		scanner.close();
		char[] charArray = str.toCharArray();
		for (int i = charArray.length - 1; i >= 0; i--) {
			System.out.print(charArray[i]);
		}
	}
}
