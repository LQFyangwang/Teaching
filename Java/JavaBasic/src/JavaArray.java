public class JavaArray {
	public static void main(String... args) {
		int a[] = {1, 2, 3}; // int a[] = {},不要人为地告诉a数组的长度
		for (int i = 0; i < 3; i++) {
			System.out.println(a[i]);
		}
		int[] b = {1, 3, 3}; // java里，数组的[]放在类型后面
		for (int i = 0; i < 3; i++) {
			System.out.println(b[i]);
		}
		/**
			int, short,long, byte: 0
			char: ''
			float, double: 0.0
			boolean: false
			String: null
		*/
		int[] c = new int[3]; // 如果想要指定数组的长度，需要使用new 类型[长度];
		for (int i = 0; i < 3; i++) {
			System.out.println(c[i]);
		}
		int[] cc = new int[3]; // 如果想要指定数组的长度，需要使用new 类型[长度];
		for (int i = 0; i < cc.length; i++) { // 数组的长度可以由length属性获取
			System.out.println(cc[i]);
		}
		
		byte[] e = new byte[3];
		for (int i = 0; i < 3; i++) {
			System.out.println(e[i]);
		}
		char[] d = new char[3];
		for (int i = 0; i < 3; i++) {
			System.out.println(d[i]);
		}
		boolean[] f = new boolean[3];
		for (int i = 0; i < 3; i++) {
			System.out.println(f[i]);
		}
		String[] s = new String[3];
		for (int i = 0; i < 3; i++) {
			System.out.println(s[i]);
		}
		
		int[][] x = {{1, 2, 3}, {4, 5, 6}, {7, 8}}; // 每一行中的列数量可以不相同
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[i].length; j++) {
				System.out.print(" " + x[i][j]); //如果直接用j < 3; 则出现 ：java.lang.ArrayIndexOutOfBoundsException 数组索引越界异常
			}
		}
		int[][] xx;
		int[][] xxx = new int[2][3];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				xxx[i][j] = i * j;
				System.out.println(xxx[i][j]);
			}
		}
		for (int i = 0; i < xxx.length; i++) {
			for (int j = 0; j < xxx[i].length; j++) {
				System.out.println(xxx[i][j]);
			}
		}
		
		System.out.println("array xxx row length：" + xxx.length); // 对二维数组，.length属性获取的是行数
		System.out.println("array xxx column length: " + xxx[0].length);
	}
}