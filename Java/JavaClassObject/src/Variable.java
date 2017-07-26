/**
 * 成员变量：类里定义的变量
 * 	实例变量
 * 		通过对象去引用的变量叫做实例变量，只能通过对象访问，不能通过类来访问 
 * 	类变量（静态变量）
 * 		变量定义前加 static 关键字，通过类来访问。 不要通过对象来访问
 * 		static:表示静态变量，不通过对象来访问
 * 		静态变量是所有对象共享的
 * 
 * 局部变量：在方法内部定义的变量，需要初始化
 * 
 * 常量定义：final, 常量只能被使用，而不能修改，用大写，如果多个单词，用_连接
 * 	可以增加static修饰符，表示静态常量，直接用类来访问，或者使用对象访问（通常不用）
 */
public class Variable {
	
	String name;
	int age;
	int height;
	int weight;
	
	static char gender;
	
	final int DEFAULT_AGE = 20;
	static int DEFAULT_HEIGHT = 180;
	
	public static void main(String... args) {
		Variable v = new Variable();
		v.name = "小王";
		v.age = 20;
		// v.gender = 'A'; 不要用对象访问 static修饰的静态变量
		Variable.gender = 'B';
		Variable v1 = new Variable();
		v1.name = "小张";
		v1.age = 19;
		System.out.println(Variable.gender);
		System.out.println(v.gender + " " + v1.gender);
		v.gender = 'A';
		System.out.println(v.gender + " " + v1.gender);
		System.out.println("default age: " + v.DEFAULT_AGE);
		System.out.println("static default height: " + v.DEFAULT_HEIGHT);
		System.out.println("static default height: " + Variable.DEFAULT_HEIGHT);
	}

}
