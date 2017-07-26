package com.gs.two;

public class PersonTest {
	
	public static void main(String[] args) {
		Person person = new Person();
		person.read();
		Worker worker = new Worker();
		// worker.company = "ABC"; // company是private的，只能在Worker类中使用
		worker.read();
		worker.work();
		Teacher teacher = new Teacher();
		teacher.salary = 3000;
		teacher.read();
		// teacher.teachPlan(); // teachPlan()是private的，只能在Teacher类中使用
		teacher.teach();
		TransferWorker tWorker = new TransferWorker();
		tWorker.transfer();
	}

}
