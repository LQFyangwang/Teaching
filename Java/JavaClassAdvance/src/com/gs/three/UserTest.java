package com.gs.three;

public class UserTest {
	
	public static void main(String[] args) {
		User user = new User();
		user.setName("钻石王老五");
		
		Address address = new Address();
		address.setProvice("江西");
		address.setCity("赣州");
		user.setAddress(address);
		
		Car bmw = new Car();
		bmw.setName("宝马");
		CarEngine carEngine = new CarEngine();
		carEngine.setTopSpeed(400);
		bmw.setCarEngine(carEngine);
		
		Car benz = new Car();
		benz.setName("奔驰");
		CarEngine carEngine1 = new CarEngine();
		carEngine1.setTopSpeed(400);
		benz.setCarEngine(carEngine1);
		
		Car[] cars = {bmw, benz};
		
		user.setCars(cars);
		
		System.out.println("来自于" + user.getAddress().getProvice() + user.getAddress().getCity()
				+ "的" + user.getName() + "有" + cars.length + "辆车");
		System.out.println("如下：");
		for (int i = 0, length = cars.length; i < length; i++) {
			System.out.println(cars[i].getName() + ", top speed: " + cars[i].getCarEngine().getTopSpeed());
		}
	}

}
