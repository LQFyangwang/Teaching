
public class Student {

	String name;
	int age;
	float score;
	
	public Student() {
		
	}
	
	public Student(String name, int age, float score) {
		this.name = name;
		this.age = age;
		this.score = score;
	}
	
	public int parseInt(String str) {
		return Integer.valueOf(str);
	}
	
	public float parseFloat(String str) {
		return Float.valueOf(str);
	}
	
	/**
	 * ·½·¨ÖØÐ´
	 */
	@Override
	public String toString() {
		return "name: " + name + ", age: " + age + ", score: " + score;
	}
	
	public static void main(String[] args) {
		Student stu = new Student("AAA", 20, 100.f);
		System.out.println(stu);
	}
}
