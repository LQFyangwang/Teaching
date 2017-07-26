import java.util.Scanner;

public class Calculation {

	public static void main(String[] args) {
		System.out.println("请输入两个整数：");
		Scanner scanner = new Scanner(System.in);
		int count = 0;
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		System.out.println("按顺序输入答案：和，积，差，商(如果有小数，直接取整数部分)，余数");
		if (a + b == scanner.nextInt()) {
			count++;
		}
		if (a * b == scanner.nextInt()) {
			count++;
		}
		if (a - b == scanner.nextInt()) {
			count++;
		}
		if (a / b == scanner.nextInt()) {
			count++;
		}
		if (a % b == scanner.nextInt()) {
			count++;
		}
		scanner.close();
		System.out.println("正确的个数： " + count);
	}

}
