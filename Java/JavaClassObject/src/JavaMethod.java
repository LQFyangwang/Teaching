
/**
 * 
 * 1、构造方法，用来实例化对象
 * 	public 方法名() {
 * 
 * 	}
 * 
 * 	public 方法名(形式参数) {
 * 
 * 	}
 * 
 * 	通过方法名称(形式参数)来判断是否为同一个方法，不看返回值类型，如果有不同，则表示不同的方法 : 方法重载
 * 
 * 	无参构造方法：new 类名称(); 如果没有指定无参构造方法，则Java默认创建一个无参构造方法
 * 	有参构造方法：new 类名称(实参列表);
 * 
 * 	如果自定义了有参的构造器，则需要手动写出无参构造器，JVM此时无法自动创建无参构造器
 * 
 * 	this关键字表示类本身，this去引用某个实例变量，表示直接使用本类对象的某个实例变量。最好不要用this访问静态变量
 *
 *	2、普通方法，供对象调用的方法（成员方法），必须指定返回值类型
 *		实例方法，只能通过对象来访问，不用static关键字修饰
 *
 *		类方法，用static关键字修饰，放在返回类型前，通过类来访问，对象也可以访问（不要这么用）
 */
public class JavaMethod {
	
	String name;
	char gender;
	int age;
	int height;
	int weight;
	static int height1;
	
	/**
	 * 无参构造方法
	 */
	public JavaMethod() { // 构造方法的名称与类名称一致
		
	}
	
	public JavaMethod(String name) {
		this.name = name; // 给本类的实例变量name在实例化时进行赋值操作
	}
	
	public JavaMethod(String name, int age) {
		// this.name = name;
		this(name); // 表示在构造方法内部去调用另外一个构造方法，this(name)匹配JavaMethod(String name)这个方法
		this.age = age;
	}
	
	public JavaMethod(String name, char gender, int age, int height, int weight) {
		this(name, age);
		// this.name = name;
		this.gender = gender;
		// this.age = age;
		this.height = height;
		this.weight = weight;
	}
	
	/**
	 * 有参构造方法 
	 * @param a
	 */
	public JavaMethod(int a) {
		System.out.println("JavaMethod a: " + a);
	}
	
	public JavaMethod(float b) {
		System.out.println("JavaMethod b: " + b);
	}
	
	public void eat() {
		System.out.println("吃饭");
	}
	
	public String getName(String firstName, String lastName) {
		return firstName + lastName;
	}
	
	public String getName(String firstName, String middleName, String lastName) {
		return firstName + "·"+ middleName + "·" + lastName;
	}
	
	/**
	 * 方法重载与返回值类型无关，只与方法名称和参数的类型与顺序有关
	 * @return
	 */
	public int getName() {
		return 0;
	}
	
	public static void sleep() {
		System.out.println("静态方法sleep");
	}
	
	/**
	 * 实例方法内部可以调用其他的实例方法或类方法 
	 * 实例方法内部可以访问实例变量或静态变量（类变量）
	 */
	public void methodInvoke() {
		name = "aa";
		height1 = 180;
		eat();
		sleep();
	}
	
	/**
	 *	类方法（静态方法）内部只能调用类方法 ，而不能调用实例方法
	 *	类方法内部只能访问类变量，不能访问实例变量
	 */
	public static void methodInvoke1() {
		// name  = "aa"; name变量是实例变量，不能被静态方法访问
		height1 = 180;
		// eat(); eat()为实例方法，不能在静态方法内部调用
		sleep();
	}
	
	/**
	 * public String toString()是Object类定义的方法，可以重新实现这个方法，输出本类相关的属性值
	 * 可以在想要输出类属性时调用该方法
	 * 
	 * System.out.println(jm.toString());
	 * System.out.println(jm);
	 * 是一样的，jm实际上默认调用toString()方法
	 * 
	 * 如果没有在自己 的类中重新实现toString()方法，则toString()输出类名称+@hashcode，所以需要自己重新实现toString
	 */
	public String toString() {
		return "name: " + name + // 如果是获取属性值，可以省略this.，默认就是当前类对象的属性
				"gender: " + gender +
				"age: " + age +
				"height: " + this.height +
				"weight: " + this.weight;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWeight() {
		return weight;
	}

	public static void main(String[] args) {
		JavaMethod jm = new JavaMethod();
		JavaMethod jm1 = new JavaMethod(100);
		JavaMethod jm2 = new JavaMethod(100.f);
		JavaMethod jm3 = new JavaMethod("小李");
		JavaMethod jm4 = new JavaMethod("小张", 20);
		
		JavaMethod jm5 = new JavaMethod("小齐", '女', 20, 190, 150);
		JavaMethod jm6 = new JavaMethod("小刘", '女', 26, 170, 90);
		System.out.println(jm5);
		System.out.println(jm6.toString());
		
		jm.eat();
		System.out.println("getName(String, String) : " + jm.getName("王", "二小"));
		System.out.println("getName(String, String, String) : " + jm.getName("A", "B", "C"));
		jm.sleep();
		JavaMethod.sleep(); // 使用类调用静态方法（类方法）
		
	}
}
