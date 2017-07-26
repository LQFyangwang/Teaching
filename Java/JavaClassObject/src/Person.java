/**
 * public:
 * 
 * 类的名称为：Person
 * 
 * 内存分配问题：
 * 不同的对象分配不同的存储空间
 * 每一个对象都在栈内存中分配一个地址，由此对象指向某块堆内存空间，堆内存存储该对象的成员变量（成员变量区）和方法（方法区）
 * 
 * 如果一个对象直接赋值给同一个类型的另一个对象，则这两个对象共用同一块堆内容空间
 * 
 * 同一个类的所有对象共用堆内存中的同一个方法区
 *
 *	所有Java类都 继承自java.lang.Object类，Object类是所有类的父类，
 *	但是不需要显式地指定类继承自Object，默认就是继承自Object
 *
 */
public class Person {
	
	/**
	 * 一个类把所有属性放在前面
	 * 类的属性可以叫做成员变量，成员变量可不被初始化，有默认值 
	 * 还有一种叫做局部变量，局部变量一定要初始化
	 */
	String name = "小王"; // 默认为null，可以在此赋初始值
	char gender; // 默认为空字符
	int age; // 默认为0
	int height;
	int weight;
	
	/**
	 * 方法放在后面
	 */
	void eat() {
		int count = 3;
		System.out.println(name + "正在吃饭！吃了" + count + "碗");
	}
	
	void sayHello(String n) {
		System.out.println(name + "说：你好， " + n);
	}

	public static void main(String[] args) {
		// 使用new关键字生成类名称为Person的实例
		// 类名称 实例名称 = new 类名称();
		Person person = new Person();
		person.eat(); // 使用成员运算符.调用对象的方法
		person.sayHello("小明");
		person.name = "person"; 
		person.gender = '男';
		person.age = 20;
		person.height = 170;
		person.weight = 120;
		System.out.println("对象person的属性如下：name " 
				+ person.name + " age " + person.age
				+ " gender " + person.gender 
				+ " height " + person.height
				+ " weight " + person.weight);
		Person person1 = new Person();
		person1.eat();
		person1.sayHello("小李");
		person1.name = "person1";
		person1.age = 30;
		person1.gender = '女';
		person1.height = 190;
		person1.weight = 100;
		System.out.println("对象person1的属性如下：name " 
				+ person1.name + " age " + person1.age
				+ " gender " + person1.gender 
				+ " height " + person1.height
				+ " weight " + person1.weight);
		
		Person person2 = person1; // person1与person2共用同一个堆内存空间，所以person1进行相应的改变，person2也会改变
		System.out.println("person1 name: " + person1.name + ", person2 name: " + person2.name);
		person2.name = "new name";
		System.out.println("person1 name: " + person1.name + ", person2 name: " + person2.name);
		Person person3 = null;
		// 如果一个对象为空 null，那么此对象没有被分配内存空间，则不能去使用该对象。
		// 如果使用一个为null的对象，则会抛出java.lang.NullPointerException异常
		// Exception in thread "main" java.lang.NullPointerException at Person.main(Person.java:79)
		// person3.eat(); 
		
		Person person4 = new Person();
		if (person4 != null) { // 判断一个对象是否为null，如果不为null，则可以使用该对象
			person4.eat();
		}
		
		System.out.println("判断person1 == person2: " + (person1 == person2)); // true
		System.out.println("判断person1 == person4: " + (person1 == person4)); // false
		
		String str1 = new String("abc");
		String str2 = new String("abc");
		String str3 = str1;
		System.out.println("str1 == str2: " + (str1 == str2)); // false
		System.out.println("str1 == str3: " + (str1 == str3)); // true
		
		/**
			 使用equals方法来判断对象是否为同一个对象
			equals方法来自于java.lang.Object类，它的实现是直接对两个对象进行==判断，==判断对某些类不适用，所以对类型的相等判断应该用equals方法 
			== 与 equals的区别:
			== 实际是对两个类变量的内存地址进行判断，同一起引用的时候返回真
			equals 实际是对两个对象所持有的数据进行判断，只有数据相等的情况下才返回真
			
			对于基本数据类型，不是类，可以直接用==判断
		*/
		System.out.println("使用equals判断person1 == person2: " + (person1.equals(person2))); // true
		System.out.println("使用equals判断person1 == person4: " + (person1.equals(person4))); // false
		System.out.println("使用equals判断str1 == str2: " + str1.equals(str2)); // true
		System.out.println("使用equals判断str1 == str3: " + str1.equals(str3)); // true
	}
	
}
