import java.util.Scanner;

public class StringEncrypt {

	/**
	 * a b c d e f g 
	 * d e f g h i j
	 * 
	 * u v w x y z
	 * x y z a b c
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("输入要加密的字符串");
		Scanner scanner = new Scanner(System.in);
		String str = scanner.next();
		scanner.close();
		char[] charArray = str.toCharArray(); // 获取一个字符串对应的字符数组
		// int length = str.length(); // 可以获取到一个字符串的长度，字符串包含的字符数
		// for (int i = 0; i < str.length(); i++) {
		for (int i = 0, length = str.length(); i < length; i++) { // for初始化表达式可以有多个，用，隔开，并且可以为初始化变量用方法的返回值赋值
			char c = charArray[i];
			switch(c) {
				case 'x': charArray[i] = 'a'; break;
				case 'y': charArray[i] = 'b'; break;
				case 'z': charArray[i] = 'c'; break;
				case 'X': charArray[i] = 'A'; break;
				case 'Y': charArray[i] = 'B'; break;
				case 'Z': charArray[i] = 'C'; break;
				default: charArray[i] = (char) (c + 3); break;
			}
		}
		System.out.print(charArray);
	}

}
