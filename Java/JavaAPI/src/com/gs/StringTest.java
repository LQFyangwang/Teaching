package com.gs;

public class StringTest {
	
	public static void main(String[] args) {
		String a = "abc";
		String b = new String("abc");
		String c = new String("abc");
		System.out.println("a == b: " + a == b); // false
		System.out.println("b == c: " + b == c); // false
		// 使用equals方法判断字符串是否相等 
		System.out.println("a equals b: " + a.equals(b)); // true
		System.out.println("b equals c: " + b.equals(c)); // true
		// 不区分大小写的相等判断
		String aa = "Abc";
		String bb = "abc";
		System.out.println("aa equals ignore case bb: " + aa.equalsIgnoreCase(bb));
		// 字符串的连接，使用+, 使用concat()方法也可以进行连接操作
		String d = a + b;
		System.out.println("a + b: " + d);
		System.out.println("a.concat(b): " + a.concat(b));
		// 字符串的截取 abcdefg，只需要前3个字符
		String e = "abcdefg";
		System.out.println(e.substring(0)); // abcdefg substring(int beginIndex)表示从beginIndex指定的索引开始截取字符，一直至字符串的结尾
		System.out.println(e.substring(4)); // efg
		System.out.println(e.substring(0, 3)); // substring(int beginIdex, int endIndx)表示从beginIndex开始截取（包括beginIndex）,到endIndex结束（不包括endIndex）
		// 大小写转化
		String f = "AbcD";
		String ff = f.toLowerCase(); // 转化成小写
		String fff = f.toUpperCase(); // 转化成大写
		System.out.println("lower case: " + ff + ", upper case: " + fff);
		// 获取指定索引的字符
		String g = "abcdefg";
		char gg = g.charAt(2);
		System.out.println("char at 2: " + gg);
		// 获取字符串的长度，length()
		String h = "abc";
		System.out.println("length of abc: " + h.length());
		// 判断是否以某个字符串开关startsWith, 判断是否以某个字符串结尾 endsWith， 返回true或false
		String i = "Hello, 你好";
		if (i.startsWith("Hello")) {
			System.out.println("字符串i以Hello开头： " + i);
		}
		// 查找字符串中是否存在某个字符，并返回该字符在字符串中的索引号
		String j = "abcdefg";
		int index = j.indexOf("cde"); // 如果某个字符存在，则返回该字符的索引，如果不存在，则返回-1
		System.out.println("b 在 abcdefg中的索引： " + index);
		// 查找某个字符串出现在指定字符串中的最后一次的位置,不存在则返回-1
		String k = "abcdbc";
		int lastIndex = k.lastIndexOf("b");
		System.out.println("last b in abcdbc: " + lastIndex);
		// 判断字符串是否为空 isEmpty()
		String L = ""; // 空字符串表示没有任何的字符，空格不是空串
		System.out.println("L is empty: " + L.isEmpty());
		// 字符串的分割
		String m = "ab,bc,cd,de";
		String[] mm = m.split(",");
		for (int ii = 0, len = mm.length; ii < len; ii++) {
			System.out.println(mm[ii]);
		}
		// 字符串的替换
		String n = "ABcdefgg  ";
		String nn =n.replaceAll("gg  ", "a");
		System.out.println(nn);
		// 取消空格操作，取消字符串的前后空格，中间出现的空格不动
		String o = " a  aa ";
		String oo = o.trim();
		System.err.println(oo);
		
	}

}
