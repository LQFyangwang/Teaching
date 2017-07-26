import java.util.Scanner;

public class IntOrder {

	public static void order() {
		int[] array = new int[5];
		System.out.println("输入5个整数：");
		Scanner scanner = new Scanner(System.in);
		for (int i = 0, len = array.length; i < len; i++) {
			array[i] = scanner.nextInt();
		}
		scanner.close();

		int pos = 0, temp = 0, max = 0;
		for (int i = 0, len = array.length; i < len; i++) {
			temp = array[i];
			max = array[i];
			pos = i;
			for (int j = i; j < len; j++) {
				if (max < array[j]) {
					pos = j;
					max = array[j];
				}
			}
			array[i] = max;
			array[pos] = temp;
		}
		
		for (int a : array) {
			System.out.print(a+"\t");
		}
	}

	public static void main(String[] args) {
//		System.out.println("输入3个整数：");
//		Scanner scanner = new Scanner(System.in);
//		int a = scanner.nextInt();
//		int b = scanner.nextInt();
//		int c = scanner.nextInt();
//		scanner.close();
//		int max = a > b ? (a > c ? a : c) : (b > c ? b : c);
//		int min = a < b ? (a < c ? a : c) : (b < c ? b : c);
//		System.out.println(max + " " + min);
		
		order();
	}

}
